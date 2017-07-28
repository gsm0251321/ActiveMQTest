package com.test.activemq.publish2Subscribe;

import java.text.DecimalFormat;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscriber {

	private ConnectionFactory factory;
	private Connection connection;

	private Session session;
	Destination[] destinations;

	private String brokerURL = "tcp://localhost:61616";

	public Subscriber() throws JMSException {
		factory = new ActiveMQConnectionFactory(brokerURL);
		connection = factory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	public static void main(String[] args) throws JMSException {
		Subscriber subscriber = new Subscriber();
		for (String stock : args) {
			Destination destination = subscriber.getSession().createTopic("STOCKS." + stock);
			MessageConsumer messageConsumer = subscriber.getSession().createConsumer(destination);

			// 注册listener为异步方式接收处理消息,consumer.receive()方式为同步接收
			messageConsumer.setMessageListener(new MessageListener() {

				@Override
				public void onMessage(Message message) {
					try {
						MapMessage map = (MapMessage) message;
						String stock = map.getString("stock");
						double price = map.getDouble("price");
						double offer = map.getDouble("offer");
						boolean up = map.getBoolean("up");
						DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
						System.out.println(stock + "\t" + df.format(price) + "\t" + df.format(offer) + "\t"
								+ (up ? "up" : "down"));
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