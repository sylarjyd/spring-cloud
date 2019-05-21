package cn.jyd.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    /**
     * 每隔1秒执行, 单位：ms。
     */
    @Scheduled(fixedRate = 1000)
    public void testFixRate() {
        System.out.println("每隔1秒执行一次：" + dateFormat.format());
    }
}
