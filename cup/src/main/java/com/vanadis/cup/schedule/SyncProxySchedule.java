package com.vanadis.cup.schedule;

import com.vanadis.cup.utils.template.BaseScheduleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-28 10:46
 */
@Slf4j
@Component
public class SyncProxySchedule {

    @Value("${cup.schedule.is-sync}")
    private Boolean isSync;

    @Scheduled(cron = "0/5 * * * * ?")
    public void syncProxyOfKuai() {

        new BaseScheduleTemplate(isSync, "测试") {
            @Override
            public void process() {
                log.info(this.getTaskName());
            }

        }.execute();
    }
}
