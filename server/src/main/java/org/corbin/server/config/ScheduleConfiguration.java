//package org.corbin.server.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//
///**
// * 配置定时任务
// */
//@Configuration
//public class ScheduleConfiguration implements SchedulingConfigurer {
//    @Autowired
//    @Qualifier("myThreadPoolTaskExcuror")
//    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.setScheduler(threadPoolTaskExecutor);
//    }
//}
