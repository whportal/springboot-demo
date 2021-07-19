package com.wh.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

/**
 * <p>
 * kafka 消息重试配置
 * spring-kafka 的消息重试功能，通过实现自定义的 SeekToCurrentErrorHandler，在 consumer 消费消息异常的时候，进行拦截处理：
 * 在重试小于最大次数时，重新投递该消息给 consumer，让 consumer 有机会重新消费消息，实现消费成功
 * 在重试到达最大次数时，consumer 还是消费失败时，该消息就会发送到死信队列，如 topic 是 DEMO_04，则对应的死信队列的 topic 就是 DEMO_04.DLT
 *
 * 实现逻辑:
 * 在消息消费失败时，SeekToCurrentErrorHandler 会钓鱼 kafka consumer 的 #seek(TopicPartition partition,long offset)方法
 * 将 consumer 对于该消息对应的 TopicPartition 分区的本地进度设置为该消息的位置，这样 consumer 在下次从 kafka broker 拉取消息的时候，又能
 * 重新拉取到这条消费失败的消息，并且是第一条
 * 同时，spring-kafka 使用 FailedRecordTracker 对每个 topic 的每个 TopicPartition 消费失败次数进行计数，这样相当于该 TopicPartition
 * 的第一条消费失败的消息的消费次数进行计数
 * 另外，在 FailedRecordTracker 中，会调用 BackOff 进行计算，该消息的下一次重新消费的时间，通过 Thread#sleep(...) 方法，实现重新消费的时间间隔
 * 注意：FailedRecordTracker 提供的计数是客户端级别的，重启 JVM 应用后，计数会丢失，若想对计数持久化需要自己实现该类，使用 Zookeeper 存储计数
 * SeekToCurrentErrorHandler 是只针对消息的单条消费失败的消息重试处理，若想有消息的批量消费失败的消费重试处理，可以使用 SeedToCurrentBatchErrorHandler
 * 但暂不支持死信队列配置
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2021/07/16
 */
@Configuration
public class KafkaConfiguration {

	@Bean
	@Primary
	public ErrorHandler kafkaErrorHandler(KafkaTemplate<?, ?> template) {
		// <1> 创建 DeadLetterPublishingRecoverer 负责实现在重试到达最大次数时，consumer 还是消费失败时，该消息就会发送到死信队列
		DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(template);
		// <2> 创建 FixedBackOff 对象 配置重试 3 次，每次固定间隔 30 秒，或者使用 ExponentialBackOff 实现指数递增的间隔时间
		FixedBackOff backOff = new FixedBackOff(10 * 1000L, 3L);
		// <3> 创建 SeekCurrentErrorHandler 对象，负责处理异常，串联整个消费重试的整个过程
		return new SeekToCurrentErrorHandler(recoverer, backOff);
	}
}
