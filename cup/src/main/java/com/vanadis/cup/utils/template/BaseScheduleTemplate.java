package com.vanadis.cup.utils.template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Created by 遥远 on 2018-09-26 20:51
 */
@Slf4j
public abstract class BaseScheduleTemplate {

    private Boolean isSync;

    private String taskName;

    public String getTaskName() {
        return taskName;
    }

    protected BaseScheduleTemplate(Boolean isSync, String taskName) {
        this.isSync = isSync;
        this.taskName = taskName;
    }

    public abstract void process();

    public void execute() {
        try {
            if (!isSync) {
                return;
            }
            Long start = System.currentTimeMillis();
            log.info("定时任务【{}】开始...", taskName);
            process();
            log.info("定时任务【{}】结束..., 耗时：{}", taskName, System.currentTimeMillis() - start);

        } catch (Exception e) {
            log.error("定时任务【{}】出错！！！,信息：{}", taskName, e);
        }
    }

}
