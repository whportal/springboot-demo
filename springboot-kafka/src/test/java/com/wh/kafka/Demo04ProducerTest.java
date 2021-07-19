package com.wh.kafka;

import com.wh.kafka.producer.Demo04Producer;
import org.apache.kafka.common.protocol.types.Field;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
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
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo04ProducerTest {

	private Logger log = LoggerFactory.getLogger(Demo04ProducerTest.class);

	@Autowired
	private Demo04Producer producer;

	@Test
	public void testSyncSend() throws ExecutionException, InterruptedException {
		int id = (int) (System.currentTimeMillis() / 1000);
		SendResult<Object, Object> result = producer.syncSend(id);
		log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

		// 阻塞等待，保证消费
		new CountDownLatch(1).await();
	}
}
