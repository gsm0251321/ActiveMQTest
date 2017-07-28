package com.test.activemq.publish2Publish;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscriber {

	private ConnectionFactory factory;
	private Connection connection;

	private Session session;
	Destination[] destinations;

	private String brokerURL = "tcp://localhost:61616";

	private String[] jobs = new String[5];

	public Subscriber() throws JMSException {
		factory = new ActiveMQConnectionFactory(brokerURL);
		connection = factory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	public static void main(String[] args) throws JMSException {
		Subscriber subscriber = new Subscriber();
		for (String job : subscriber.jobs) {
			Destination destination = subscriber.getSession().createQueue("JOBS." + job);
			MessageConsumer messageConsumer = subscriber.getSession().createConsumer(destination);
			messageConsumer.setMessageListener(new MessageListener() {

				@Override
				public void onMessage(Message message) {
					try {
						// do something here
						System.out.println(job + " id:" + ((ObjectMessage) message).getObject());
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
		}
	}

	public Session getSession() {
		return session;
	}

}