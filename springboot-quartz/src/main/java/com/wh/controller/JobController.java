package com.wh.controller;

import com.wh.service.QuartzJobService;
import com.wh.task.SayHelloJob;
import com.wh.task.TaskDefine;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangwenhao
 * @description JobController
 * @date 2020-06-29 13:40
 */
@RestController
@RequestMapping("job")
public class JobController {

    private QuartzJobService quartzJobService;

    @Autowired
    public JobController(QuartzJobService quartzJobService) {
        this.quartzJobService = quartzJobService;
    }

    private final JobKey jobKey = JobKey.jobKey("HelloWorld-job", "Group-1");

    /**
     * 启动 Hello World 定时任务
     */
    @GetMapping("hello/start")
    public String startHelloWorldJob() throws SchedulerException {

        // 创建一个定时任务
        TaskDefine task = TaskDefine.builder()
                .jobKey(jobKey)
                .cronExpression("0/2 * * * * ?")
                .jobClass(SayHelloJob.class)
                .description("这是 HELLO WORLD 的定时任务")
                .build();
        quartzJobService.scheduleJob(task);
        return "Start Hello World Job Success";
    }

    @GetMapping("hello/pause")
    public String pauseHelloWorldJob() throws SchedulerException {
        quartzJobService.pauseJob(jobKey);
        return "Pause Hello World Job Success";
    }

    @GetMapping("hello/resume")
    public String resumeHelloWorldJob() throws SchedulerException {
        quartzJobService.resumeJob(jobKey);
        return "Resume Hello World Job Success";
    }

    @GetMapping("hello/delete")
    public String deleteHelloWorldJob() throws SchedulerException {
        quartzJobService.deleteJob(jobKey);
        return "Delete Hello World Job Success";
    }

    @GetMapping("hello/cron/modify")
    public String modifyHelloWorldJobCron() throws SchedulerException {
        TaskDefine modifyTaskDefine = TaskDefine.builder()
                .jobKey(jobKey)
                .cronExpression("0/5 * * * * ?")
                .jobClass(SayHelloJob.class)
                .description("这是修改后的 HELLO WORLD 定时任务")
                .build();
        if (quartzJobService.modifyJobCron(modifyTaskDefine)) {
            return "Modify Hello World Job Cron Success";
        }

        return "Modify Hello World Job Cron Fail";
    }

    @GetMapping("get")
    public String getTest(HttpServletResponse response,String param) {
        System.out.println(param);
        System.out.println("111111111111111111111111111");
        response.setHeader("wwh","1874");
        return "Success";
    }


}
