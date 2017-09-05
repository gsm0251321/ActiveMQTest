package com.test.rocketmq.scheduleExample;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

public class ScheduledMessageConsumer {

	public static void main(String[] args) throws Exception {
		// Instantiate message consumer
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ExampleConsumer");
		// Subscribe topics
		consumer.subscribe("TestTopic", "*");
		// Register message listener
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages,
					ConsumeConcurrentlyContext context) {
				for (MessageExt message : messages) {
					// Print approximate delay time period
					System.out.println("Receive message[msgId=" + message.getMsgId() + "] "
							+ (System.currentTimeMillis() - message.getStoreTimestamp()) + "ms later");
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		// Launch consumer
		consumer.start();
	}
}
