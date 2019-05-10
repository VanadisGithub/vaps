package com.vanadis.proxy.schedule;

import com.vanadis.proxy.task.ProxyOfXiciTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-28 10:46
 */
@Slf4j
@Component
public class SyncProxySchedule {

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void syncProxyOfXici() {
        new ProxyOfXiciTask().start();
    }
}