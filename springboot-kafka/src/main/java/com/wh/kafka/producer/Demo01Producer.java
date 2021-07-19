package com.wh.kafka.producer;

import com.wh.kafka.message.Demo01Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

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
public class Demo01Producer {

	@Resource
	private KafkaTemplate<Object, Object> kafkaTemplate;

	public SendResult<Object, Object> syncSend(Integer id) throws ExecutionException, InterruptedException {

		Demo01Message message = new Demo01Message();
		message.setId(id);
		/*
		在序列化时，使用 JsonSerializer 序列化 Message 消息对象，它会在 kafka 消息 Headers 的 __TypeId__ 上，值为 Message 消息
		对应的全类名
		在反序列化时，使用 JsonDeserializer 反序列化出 Message 消息对象，它会根据 Kafka 消息 Headers 的 __TypeId__ 的值，反序列化
		消息内容成该 Message 对象
		 */
		// 同步发送消息
		return kafkaTemplate.send(Demo01Message.TOPIC, message).get();
	}

	public ListenableFuture<SendResult<Object, Object>> asyncSend(Integer id) {
		Demo01Message message = new Demo01Message();
		message.setId(id);
		// 异步发送消息
		return kafkaTemplate.send(Demo01Message.TOPIC, message);
	}
}
