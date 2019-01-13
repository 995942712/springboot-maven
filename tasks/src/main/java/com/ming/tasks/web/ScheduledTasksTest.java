package com.ming.tasks.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.EnableScheduling;

@Component
@EnableScheduling//开启定时任务
public class ScheduledTasksTest {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasksTest.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * @Scheduled(fixedRate = 5000)//上一次开始执行时间点之后5秒再执行
     * @Scheduled(fixedDelay = 5000)//上一次执行完毕时间点之后5秒再执行
     * @Scheduled(initialDelay=1000, fixedRate=5000//第一次延迟1秒后执行,之后按fixedRate的规则每5秒执行一次
     * cron表达式顺序: 秒(0~59) 分钟(0~59) 小时(0~23) 天(0~31) 月(0~11) 星期(1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT) 年份(1970－2099)
     * cron = *\30 * * * * ?//每隔30秒执行一次
     * @Scheduled(cron = "00 00 01 * * ?")//每天上午01点执行一次
     */
    @Async//异步执行
    @Scheduled(cron="*/5 * * * * ?")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

}