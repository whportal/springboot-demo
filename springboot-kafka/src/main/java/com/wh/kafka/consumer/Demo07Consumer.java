package com.wh.kafka.consumer;

import com.wh.kafka.message.Demo07Message;
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
 * @date 2021/07/19
 */
@Component
public class Demo07Consumer {

	private final Logger log = LoggerFactory.getLogger(Demo07Consumer.class);

	@KafkaListener(topics = Demo07Message.TOPIC, groupId = "demo07-consumer-group-" + Demo07Message.TOPIC)
	public void onMessage(Demo07Message message) {
		log.info("[onMessage][线程编号：{} 消息内容：{}]", Thread.currentThread().getId(), message);
	}
}
