package com.wh.kafka.producer;

import com.wh.kafka.message.Demo07Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaOperations;
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
public class Demo07Producer {

	private final Logger log = LoggerFactory.getLogger(Demo07Producer.class);

	@Resource
	private KafkaTemplate<Object, Object> kafkaTemplate;

	public String syncSendInTransaction(Integer id, Runnable runner) throws ExecutionException, InterruptedException {
		return kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback<Object, Object, String>() {
			@Override
			public String doInOperations(KafkaOperations<Object, Object> kafkaOperations) {
				// 创建消息
				Demo07Message message = new Demo07Message();
				message.setId(id);

				// 发送消息
				try {
					SendResult<Object, Object> result = kafkaOperations.send(Demo07Message.TOPIC, message).get();
					log.info("[doInOperations][发送编号：[{}] 发送结果：[{}]]", id, result);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
					throw new RuntimeException();
				}

				// 本地业务逻辑
				runner.run();

				// 返回结果
				return "success";
			}
		});
	}
}
