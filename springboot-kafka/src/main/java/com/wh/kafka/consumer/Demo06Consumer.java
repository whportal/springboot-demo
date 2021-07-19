package com.wh.kafka.consumer;

import com.wh.kafka.message.Demo06Message;
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
public class Demo06Consumer {

	private final Logger log = LoggerFactory.getLogger(Demo06Consumer.class);

	@KafkaListener(topics = Demo06Message.TOPIC, groupId = "demo06-consumer-group-" + Demo06Message.TOPIC, concurrency = "2")
	public void onMessage(Demo06Message message) {
		log.info("[onMessage][线程编号：{}] 消息内容：{}", Thread.currentThread().getId(), message);
	}
}
