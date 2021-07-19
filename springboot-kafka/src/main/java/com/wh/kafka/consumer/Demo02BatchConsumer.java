package com.wh.kafka.consumer;

import com.wh.kafka.message.Demo02Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 批量消费消息，需要修改配置文件中的 consumer 配置和 listener 配置
 * spring.kafka.listener.type
 * spring.kafka.consumer.max-poll-records
 * spring.kafka.consumer.fetch-min-size
 * spring.kafka.consumer.fetch-max-wait
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2021/07/16
 */
@Component
public class Demo02BatchConsumer {

	private final Logger log = LoggerFactory.getLogger(Demo02BatchConsumer.class);

	@KafkaListener(topics = Demo02Message.TOPIC, groupId = "demo02-consumer-group-" + Demo02Message.TOPIC)
	public void onMessage(List<Demo02Message> message) {
		log.info("[onMessage][线程编号：{}] 批量消费消息条数：{}", Thread.currentThread().getId(), message.size());
	}
}
