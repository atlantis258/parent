package com.atlantis;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DtpMonitor implements ApplicationRunner {

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

    @Override
    public void run(ApplicationArguments args) throws Exception {

        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            monitor();
            alarm();
        },5, 5, TimeUnit.SECONDS);


    }

    private void monitor() {
        for (String name : DtpUtil.getAllDtpExecutorNames()) {
            DtpExecutor dtpExecutor = DtpUtil.getDtpExecutor(name);
            System.out.println(String.format("线程池名字：%s", name));
            System.out.println(String.format("线程池核心线程数：%s", dtpExecutor.getCorePoolSize()));
            System.out.println(String.format("线程池最大线程数：%s", dtpExecutor.getMaximumPoolSize()));
            System.out.println(String.format("线程池当前线程数：%s", dtpExecutor.getActiveCount()));
        }
    }

    private void alarm() {
        // 读取配置
        int max = 10;

        for (DtpExecutor executor : DtpUtil.getAllDtpExecutor()) {
            int activeCount = executor.getActiveCount();
            if (activeCount >= max) {
                System.out.println(String.format("告警，当前线程池的线程个数为%s, 告警阈值为%s", activeCount, max));
            }
        }
    }
}
