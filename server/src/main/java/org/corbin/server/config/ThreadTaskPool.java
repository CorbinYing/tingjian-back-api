package org.corbin.server.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class ThreadTaskPool {


    /**
     * 使用ThreadPoolTaskExecutor线程池
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor myThreadPoolTaskExcuror() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        ThreadPoolProperties properties = this.instanceThreadPoolProperties();

        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setAllowCoreThreadTimeOut(properties.getAllowCoreThreadTimeOut());
        executor.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        executor.setThreadNamePrefix(properties.getThreadName());
        executor.setMaxPoolSize(properties.getMaxPoolSize());


        switch (properties.getRejectedExecutionHandler()) {
            case "CallerRunsPolicy":
                executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
                break;
            case "DiscardOldestPolicy":
                executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
                break;
            case "DiscardPolicy":
                executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
                break;
            default:
                executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
                break;
        }
        executor.initialize();
        return executor;

    }


    /**
     * @ConfigurationProperties(prefix = "spring.thread.pool")
     * 生成单独的被托管的bean
     */
    @Bean
    public ThreadPoolProperties instanceThreadPoolProperties() {
        return new ThreadPoolProperties();
    }

    /**
     * 定义线程池的属性
     */
    @Getter
    @Setter
    @Configuration
    @ConfigurationProperties(prefix = "spring.thread.pool")
    @PropertySource(value = "classpath:thread-pool.properties", ignoreResourceNotFound = true, encoding = "UTF-8")
    public class ThreadPoolProperties {
        /**
         * 线程前缀名
         */
        private String threadName;
        /**
         * 核心线程池大小
         */
        private Integer corePoolSize;
        /**
         * 最大线程数
         */
        private Integer maxPoolSize;
        /**
         * 线程池维护空闲线程存在时间
         */
        private Integer keepAliveSeconds;
        /**
         * 拒绝策略,默认AbortPolicy策略
         * ThreadPoolExecutor.AbortPolicy策略，是默认的策略,处理程序遭到拒绝将抛出运行时 RejectedExecutionException；
         * ThreadPoolExecutor.CallerRunsPolicy策略 ,调用者的线程会执行该任务,如果执行器已关闭,则丢弃；
         * ThreadPoolExecutor.DiscardPolicy策略，不能执行的任务将被丢弃；
         * ThreadPoolExecutor.DiscardOldestPolicy策略，如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）
         */
        private String rejectedExecutionHandler;

        /**
         * 当设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭
         */
        private Boolean allowCoreThreadTimeOut;

    }


}
