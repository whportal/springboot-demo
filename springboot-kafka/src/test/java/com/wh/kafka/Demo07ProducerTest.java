package com.wh.kafka;

import com.wh.kafka.producer.Demo07Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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
public class Demo07ProducerTest {

	private final Logger log = LoggerFactory.getLogger(Demo07ProducerTest.class);

	@Autowired
	private Demo07Producer producer;

	@Test
	public void testSyncSendInTransaction() throws InterruptedException, ExecutionException {
		int id = (int) (System.currentTimeMillis() / 1000);
		producer.syncSendInTransaction(id, new Runnable() {
			@Override
			public void run() {
				log.info("[run][我要开始睡觉了]");
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
				log.info("[run][我睡醒了]");

			}
		});

		// 阻塞等待，保证消费
		new CountDownLatch(1).await();
	}
}
