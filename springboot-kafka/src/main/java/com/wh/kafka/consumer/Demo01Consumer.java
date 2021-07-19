package com.wh.kafka.consumer;

import com.wh.kafka.message.Demo01Message;
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
 * @date 2021/07/15
 */
@Component
public class Demo01Consumer {

	private Logger log = LoggerFactory.getLogger(Demo01Consumer.class);

	@KafkaListener(topics = Demo01Message.TOPIC, groupId = "demo01-consumer-group-" + Demo01Message.TOPIC)
	public void onMessage(Demo01Message message) {
		log.info("[onMessage][线程编号：{} 消息内容：{}]", Thread.currentThread().getId(), message);
	}
}
