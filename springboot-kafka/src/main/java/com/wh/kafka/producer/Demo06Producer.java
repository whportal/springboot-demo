package com.wh.kafka.producer;

import com.wh.kafka.message.Demo06Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

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
public class Demo06Producer {

	@Resource
	private KafkaTemplate<Object, Object> kafkaTemplate;

	public SendResult<Object, Object> syncSend(Integer id) throws ExecutionException, InterruptedException {
		Demo06Message message = new Demo06Message();
		message.setId(id);

		// 同步发送消息并且指定分区路由 key
		return kafkaTemplate.send(Demo06Message.TOPIC,String.valueOf(id), message).get();
	}
}
