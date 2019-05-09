package com.vanadis.proxy.task;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Created by 遥远 on 2018-09-26 20:51
 */
@Slf4j
public abstract class BaseTask {

    private Boolean isOpen;

    private String taskName;

    public String getTaskName() {
        return taskName;
    }

    public BaseTask(Boolean isSync, String taskName) {
        this.isOpen = isSync;
        this.taskName = taskName;
    }

    /**
     * 执行方法
     */
    public abstract void process();

    public void start() {
        try {
            if (isOpen) {
                Long start = System.currentTimeMillis();
                log.info("[Vanadis.Task][{}] start ...", taskName);
                process();
                log.info("[Vanadis.Task][{}] end , spend time:{}", taskName, System.currentTimeMillis() - start);
            }
        } catch (Exception e) {
            log.error("[Vanadis.Task][{}] error !!! message:{}", taskName, e);
        }
    }

}
