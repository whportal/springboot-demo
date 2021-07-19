package com.wh.kafka;

import com.wh.kafka.producer.Demo01Producer;
import com.wh.kafka.producer.Demo02Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;
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
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo01ProducerTest {

	private Logger log = LoggerFactory.getLogger(Demo01ProducerTest.class);

	@Autowired
	private Demo01Producer producer;

	@Test

	public void testSyncSend() throws ExecutionException, InterruptedException {
		int id = (int) (System.currentTimeMillis() / 1000);
		SendResult<Object, Object> result = producer.syncSend(id);
		log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

		// 阻塞等待，保证消费
		new CountDownLatch(1).await();
	}

	@Test
	public void testAsyncSend() throws InterruptedException {
		int id = (int) (System.currentTimeMillis() / 1000);
		producer.asyncSend(id).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
			@Override
			public void onFailure(Throwable throwable) {
				log.info("[testAsyncSend][发送编号：[{}] 发送异常]", id, throwable);
			}

			@Override
			public void onSuccess(SendResult<Object, Object> result) {
				log.info("[testAsyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, result);
			}
		});

		// 阻塞等待，保证消费
		new CountDownLatch(1).await();
	}
}
