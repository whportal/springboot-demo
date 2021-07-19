package com.wh.kafka.producer;

import com.wh.kafka.message.Demo02Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * <p>
 *测试批量发送消息，发送方式和普通发送一致，需要在配置文件 producer 配置以下三个参数
 * spring.kafka.producer.batch-size
 * spring.kafka.producer.buffer-memory
 * spring.kafka.producer.properties.linger.ms
 * 在发送消息的时候 kafka 将消息“收集”成批，当触发上面三个任一参数的时候进行批量发送
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2021/07/16
 */
@Component
public class Demo02Producer {

	@Resource
	private KafkaTemplate<Object, Object> kafkaTemplate;

	public ListenableFuture<SendResult<Object, Object>> asyncSend(Integer id) {
		Demo02Message message = new Demo02Message();
		message.setId(id);
		// 异步发送消息
		return kafkaTemplate.send(Demo02Message.TOPIC, message);
	}
}
