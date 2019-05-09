package com.vanadis.proxy.object;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @description:
 * @author: Created by 遥远 on 2019-02-01 14:28
 */
@Data
@Entity
public class Proxy extends Base implements Serializable {

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
