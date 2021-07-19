package com.wh.kafka.consumer;

import com.wh.kafka.message.Demo08Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 手动提交消费进度
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2021/07/19
 */
@Component
public class Demo08Consumer {

	private final Logger log = LoggerFactory.getLogger(Demo08Consumer.class);

	@KafkaListener(topics = Demo08Message.TOPIC,groupId = "demo08-consumer-group-"+Demo08Message.TOPIC)
	public void onMessage(Demo08Message message, Acknowledgment acknowledgment) {
		log.info("[onMessage][线程编号：{}] 消息内容：{}", Thread.currentThread().getId(), message);
		// 提交消费进度
		if (message.getId() % 2 == 1) {
			acknowledgment.acknowledge();
		}
	}
}
