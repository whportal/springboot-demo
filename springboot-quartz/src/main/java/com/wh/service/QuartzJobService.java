package com.wh.service;

import com.wh.task.TaskDefine;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wangwenhao
 * @description QuartzJobService
 * @date 2020-06-29 11:20
 */
@Slf4j
@Service
public class QuartzJobService {

    /**
     * Quartz定时任务核心的功能实现类
     */
    private Scheduler scheduler;

    @Autowired
    public QuartzJobService(SchedulerFactoryBean schedulerFactoryBean) {
        this.scheduler = schedulerFactoryBean.getScheduler();
    }

    /**
     * 创建和启动任务
     *
     * @param task 定时任务
     */
    public void scheduleJob(TaskDefine task) throws SchedulerException {
        // 定时任务的 名字和组名
        JobKey jobKey = task.getJobKey();

        // 定时任务的元数据
        JobDataMap jobDataMap = getJobDataMap(task.getJobDataMap());

        // 定时任务 的 描述
        String description = task.getDescription();

        // 定时任务的逻辑实现类型
        Class<? extends Job> jobClass = task.getJobClass();

        // 定时任务的cron表达式
        String cron = task.getCronExpression();

        // 获取任务定义
        JobDetail jobDetail = getJobDetail(jobKey, description, jobDataMap, jobClass);

        // 获取触发器
        Trigger trigger = getTrigger(jobKey, description, jobDataMap, cron);

        // 开启任务
        scheduler.scheduleJob(jobDetail, trigger);

    }

    /**
     * 暂停任务
     */
    public void pauseJob(JobKey jobKey) throws SchedulerException {
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复暂停的任务
     */
    public void resumeJob(JobKey jobKey) throws SchedulerException {
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除任务
     */
    public void deleteJob(JobKey jobKey) throws SchedulerException {
        scheduler.deleteJob(jobKey);
    }

    public boolean modifyJobCron(TaskDefine task) throws SchedulerException {
        String cronExpression = task.getCronExpression();
        // 如果cron表达式的格式不正确 则返回修改失败
        if (!CronExpression.isValidExpression(cronExpression)) {
            return false;
        }

        JobKey jobKey = task.getJobKey();
        TriggerKey triggerKey = new TriggerKey(jobKey.getName(), jobKey.getGroup());

        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        JobDataMap jobDataMap = getJobDataMap(task.getJobDataMap());
        // r如果cron发生了变化 则按新cron触发 进行重新启动定时任务
        if (!cronTrigger.getCronExpression().equalsIgnoreCase(cronExpression)) {
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                    .usingJobData(jobDataMap)
                    .build();
            scheduler.rescheduleJob(triggerKey, trigger);
        }
        return true;
    }

    /**
     * 获取Trigger Job的触发器 执行规则
     *
     * @param jobKey         定时任务的名称 组名
     * @param description    定时任务的描述
     * @param jobDataMap     定时任务的元数据
     * @param cronExpression 定时任务的 执行cron 表达式
     */
    private Trigger getTrigger(JobKey jobKey, String description, JobDataMap jobDataMap, String cronExpression) {
        return TriggerBuilder.newTrigger()
                .withIdentity(jobKey.getName(), jobKey.getGroup())
                .withDescription(description)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .usingJobData(jobDataMap)
                .build();
    }

    /**
     * 获取定时任务的定义 JobDetail是任务的定义 Job是任务的执行逻辑
     *
     * @param jobKey      定时任务的名称 组名
     * @param description 定时任务的描述
     * @param jobDataMap  定时任务的元数据
     * @param jobClass    定时任务的真正执行逻辑定义类
     */
    private JobDetail getJobDetail(JobKey jobKey, String description, JobDataMap jobDataMap, Class<? extends Job> jobClass) {
        return JobBuilder.newJob(jobClass)
                .withIdentity(jobKey)
                .withDescription(description)
                .setJobData(jobDataMap)
                .usingJobData(jobDataMap)
                .requestRecovery()
                .storeDurably()
                .build();
    }

    private JobDataMap getJobDataMap(Map<?, ?> map) {
        return map == null ? new JobDataMap() : new JobDataMap(map);
    }
}
