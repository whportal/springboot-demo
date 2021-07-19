package com.wh.kafka.consumer;

import com.wh.kafka.message.Demo04Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2021/07/16
 */
// @Component
public class Demo04Consumer {

	private AtomicInteger count = new AtomicInteger(0);

	private final Logger log = LoggerFactory.getLogger(Demo04Consumer.class);

	@KafkaListener(topics = Demo04Message.TOPIC, groupId = "demo04-consumer-group-" + Demo04Message.TOPIC)
	public void onMessage(Demo04Message message) {
		log.info("[onMessage][线程编号：{} 消息内容：{}]", Thread.currentThread().getId(), message);
		// 此处抛出一个异常模拟消费失败
		throw new RuntimeException("模拟消费失败");
	}
}
