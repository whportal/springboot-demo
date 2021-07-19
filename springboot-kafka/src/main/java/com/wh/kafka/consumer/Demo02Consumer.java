package com.wh.kafka.consumer;

import com.wh.kafka.message.Demo02Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

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
public class Demo02Consumer {

	private final Logger log = LoggerFactory.getLogger(Demo02Consumer.class);

	@KafkaListener(topics = Demo02Message.TOPIC, groupId = "demo02-consumer-group-" + Demo02Message.TOPIC)
	public void onMessage(Demo02Message message) {
		log.info("[onMessage][线程编号：{}] 消息内容：{}", Thread.currentThread().getId(), message);
	}
}
