package com.wh.kafka;

import com.wh.kafka.producer.Demo06Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
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
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo06ProducerTest {

	private Logger log = LoggerFactory.getLogger(Demo06ProducerTest.class);

	@Autowired
	private Demo06Producer producer;

	@Test
	public void syncSend() throws ExecutionException, InterruptedException {
		for (int i = 0; i < 10; i++) {
			int id = (int) (System.currentTimeMillis() / 1000);
			SendResult<Object, Object> result = producer.syncSend(id);
			log.info("[syncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
		}

		// 阻塞等待，确保消费
		new CountDownLatch(1).await();
	}

	@Test
	public void syncPartitionsSend() throws ExecutionException, InterruptedException {
		for (int i = 0; i < 10; i++) {
			int id = 1;
			SendResult<Object, Object> result = producer.syncSend(id);
			log.info("[syncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
		}

		// 阻塞等待，确保消费
		new CountDownLatch(1).await();
	}
}
