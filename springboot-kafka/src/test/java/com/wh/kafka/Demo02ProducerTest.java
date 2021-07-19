package com.wh.kafka;

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

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2021/07/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo02ProducerTest {

	private Logger log = LoggerFactory.getLogger(Demo02ProducerTest.class);

	@Autowired
	private Demo02Producer producer;

	@Test
	public void testBatchAsyncSend() throws InterruptedException {
		// 测试批量发送消息
		log.info("[testAsyncSend][开始执行]");

		for (int i = 0; i < 3; i++) {
			int id = (int) (System.currentTimeMillis() / 1000);
			producer.asyncSend(id).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
				@Override
				public void onFailure(Throwable e) {
					log.info("[testAsyncSend][发送编号：[{}],发送异常]", id, e);
				}

				@Override
				public void onSuccess(SendResult<Object, Object> result) {
					log.info("[testAsyncSend][发送编号：[{}]，发送成功，结果为：[{}]]", id, result);
				}
			});

			// 故意每条消息之间，隔离 10 秒
			Thread.sleep(10 * 1000L);
		}

		// 阻塞等待，保证消费
		new CountDownLatch(1).await();
	}
}
