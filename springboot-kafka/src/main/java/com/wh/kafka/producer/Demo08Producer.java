package com.wh.kafka.producer;

import com.wh.kafka.message.Demo08Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 * 如果 Kafka Producer 开启了事务的功能，则所有发送的消息，都必须处于 Kafka 事务之中，否则会抛出异常
 * 如果在业务中，即存在需要事务的情况，也存在不需要事务的情况，需要分别定义两个 KafkaTemplate（Kafka Producer）
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2021/07/19
 */
@Component
public class Demo08Producer {

	private final Logger log = LoggerFactory.getLogger(Demo08Producer.class);

	@Resource
	private KafkaTemplate<Object, Object> kafkaTemplate;

	public SendResult<Object, Object> syncSend(Integer id) throws ExecutionException, InterruptedException {
		Demo08Message message = new Demo08Message();
		message.setId(id);

		// 同步发送消息并且指定分区路由 key
		return kafkaTemplate.send(Demo08Message.TOPIC, String.valueOf(id), message).get();
	}
}
