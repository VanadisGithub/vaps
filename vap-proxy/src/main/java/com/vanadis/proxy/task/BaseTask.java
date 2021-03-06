package com.vanadis.proxy.task;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Created by 遥远 on 2018-09-26 20:51
 */
@Slf4j
@Data
public abstract class BaseTask {

    private String taskName;

    /**
     * 执行方法
     */
    public abstract void process();

    public void start() {
        try {
            Long start = System.currentTimeMillis();
            log.info("[Vap.Task][{}] start ...", taskName);
            process();
            log.info("[Vap.Task][{}] end , spend time : {}s", taskName, (System.currentTimeMillis() - start) / 1000.000);
        } catch (Exception e) {
            log.error("[Vap.Task][{}] error !!! message : {}", taskName, e.getMessage(), e);
        }
    }

}



