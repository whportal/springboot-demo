package com.wh.kafka;

import com.wh.kafka.producer.Demo08Producer;
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
public class Demo08ProducerTest {

	private final Logger log = LoggerFactory.getLogger(Demo08ProducerTest.class);

	@Autowired
	private Demo08Producer producer;

	@Test
	public void testSyncSend() throws ExecutionException, InterruptedException {
		for (int i = 1; i <= 2; i++) {
			SendResult<Object, Object> result = producer.syncSend(i);
			log.info("[syncSend][发送编号：[{}] 发送结果：[{}]]", i, result);
		}

		// 阻塞等待，保证消费
		new CountDownLatch(1).await();
	}
}
