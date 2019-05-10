package com.vanadis.proxy.model;

import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;

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
    private String source;
    private int type;
    private int errorNum;
    private int status;

    public Proxy() {
    }

    public Proxy(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public Proxy(String ip, String port, String source) {
        this.ip = ip;
        this.port = port;
        this.source = source;
    }

    public HttpHost getHttpHost() {
        return new HttpHost(ip, Integer.valueOf(port));
    }
}
