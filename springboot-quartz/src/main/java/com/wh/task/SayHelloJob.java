package com.wh.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author wangwenhao
 * @description SayHelloJob
 * @date 2020-06-29 11:17
 */
@Slf4j
@DisallowConcurrentExecution
public class SayHelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("          HELLO   WORLD           ");
    }
}
