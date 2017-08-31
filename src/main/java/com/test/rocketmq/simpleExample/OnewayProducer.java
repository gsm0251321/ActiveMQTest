package com.test.rocketmq.simpleExample;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;

/**
 * 单向传输
 * 
 * 应用：单向传输用于要求中等可靠性的情况，如日志采集。
 * 
 * @author gsm02
 *
 */
public class OnewayProducer {
	public static void main(String[] args) throws Exception {
		// Instantiate with a producer group name.
		DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");
		// Launch the instance.
		producer.start();
		for (int i = 0; i < 100; i++) {
			// Create a message instance, specifying topic, tag and message body.
			Message msg = new Message("TopicTest" /* Topic */, "TagA" /* Tag */,
					("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
			);
			// Call send message to deliver message to one of brokers.
			producer.sendOneway(msg);

		}
		// Shut down once the producer instance is not longer in use.
		producer.shutdown();
	}
}
