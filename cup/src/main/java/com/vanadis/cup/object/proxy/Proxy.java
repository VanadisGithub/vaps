package com.vanadis.cup.object.proxy;

import lombok.Data;

/**
 * @description:
 * @author: Created by 遥远 on 2019-02-01 14:28
 */
@Data
public class Proxy {

    private String ip;
    private String port;
    private int type;
    private int errorNum;
    private int status;

    public Proxy(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }
}
