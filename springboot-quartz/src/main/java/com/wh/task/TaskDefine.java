package com.wh.task;

import lombok.Builder;
import lombok.Data;
import org.quartz.Job;
import org.quartz.JobKey;

import java.util.Map;

/**
 * @author wangwenhao
 * @description 任务定义
 * @date 2020-06-29 11:11
 */
@Data
@Builder
public class TaskDefine {

    /**
     * 定时任务的名字和分组名 JobKey{@link org.quartz.JobKey}
     */
    private JobKey jobKey;

    /**
     * 定时任务的描述 可以是定时任务本身的描述也可以是触发器的
     */
    private String description;

    /**
     * 定时任务的执行 cron（Trigger的CronScheduleBuilder的cronExpression）
     */
    private String cronExpression;

    /**
     * 定时任务的元数据
     */
    private Map<?, ?> jobDataMap;

    /**
     * 定时任务的具体执行逻辑类
     */
    private Class<? extends Job> jobClass;
}
