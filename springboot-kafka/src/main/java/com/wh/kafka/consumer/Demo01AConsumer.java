package com.wh.kafka.consumer;

import com.wh.kafka.message.Demo01Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Kafka 内置的 ConsumerRecord 类，通过 ConsumerRecord 类，我们可以获取到消费的消息的更多信息，例如：
 * 消息的所属队列、创建时间等待属性，不过消息的内容 value 需要自己去反序列化，一般情况下，我们不会使用该类
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2021/07/15
 */
@Component
public class Demo01AConsumer {

	private final Logger log = LoggerFactory.getLogger(Demo01AConsumer.class);

	@KafkaListener(topics = Demo01Message.TOPIC, groupId = "demo01-A-consumer-group-" + Demo01Message.TOPIC)
	public void onMessage(ConsumerRecord<Integer,String> record) {
		log.info("[onMessage][线程编号：{} 消息内容：{}]", Thread.currentThread().getId(), record);
	}
}
