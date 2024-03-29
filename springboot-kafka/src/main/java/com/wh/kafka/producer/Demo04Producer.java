package com.wh.kafka.producer;

import com.wh.kafka.message.Demo04Message;
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
 * @date 2021/07/16
 */
@Component
public class Demo04Producer {

	@Resource
	private KafkaTemplate<Object, Object> kafkaTemplate;

	public SendResult<Object, Object> syncSend(Integer id) throws ExecutionException, InterruptedException {
		// 创建消息
		Demo04Message message = new Demo04Message();
		message.setId(id);

		// 同步发送消息
		return kafkaTemplate.send(Demo04Message.TOPIC, message).get();
	}
}
