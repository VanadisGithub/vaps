package com.vanadis.proxy.common;

import lombok.Data;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-11 10:07
 */
public enum ProxyLevel {

    /**
     *
     */
    PREFER(50),

    /**
     *
     */
    GOOD(0);

    private Integer errorNum;

    ProxyLevel(Integer errorNum) {
        this.errorNum = errorNum;
    }

    public Integer getErrorNum() {
        return errorNum;
    }
}
