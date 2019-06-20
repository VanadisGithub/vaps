package com.vanadis.proxy.schedule;

import com.vanadis.proxy.service.ProxyService;
import com.vanadis.proxy.task.ProxyOf89Task;
import com.vanadis.proxy.task.ProxyOfKuaiTask;
import com.vanadis.proxy.task.ProxyOfSJTask;
import com.vanadis.proxy.task.ProxyOfXiciTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-28 10:46
 */
@Slf4j
@Component
public class SyncProxySchedule {

    @Autowired
    private ProxyService proxyService;

    @Autowired
    private ProxyOf89Task proxyOf89Task;

    @Autowired
    private ProxyOfKuaiTask proxyOfKuaiTask;

    @Autowired
    private ProxyOfSJTask proxyOfSJTask;

    @Autowired
    private ProxyOfXiciTask proxyOfXiciTask;

    @Scheduled(cron = "* * * * * ?")
    public void test() {
        log.info("1");
    }

    @Scheduled(cron = "0 30 0/1 * * ?")
    public void refreshProxy() {
        proxyService.refreshProxy();
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void syncProxyOf89() {
        proxyOf89Task.start();
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void syncProxyOfKuai() {
        proxyOfKuaiTask.start();
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void syncProxyOfSJ() {
        proxyOfSJTask.start();
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void syncProxyOfXici() {
        proxyOfXiciTask.start();
    }


}
