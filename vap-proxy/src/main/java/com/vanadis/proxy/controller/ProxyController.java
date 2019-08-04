package com.vanadis.proxy.controller;

import com.vanadis.proxy.common.ProxyLevel;
import com.vanadis.proxy.manager.ProxyManager;
import com.vanadis.proxy.mapper.ProxyMapper;
import com.vanadis.proxy.model.Proxy;
import com.vanadis.proxy.task.ProxyOf89Task;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-10 09:59
 */
@RestController
public class ProxyController {

    @Autowired
    private ProxyManager proxyManager;

    @Autowired
    private ProxyMapper proxyMapper;

    @GetMapping("test")
    public void test() {
        proxyOf89Task.start();
    }

    @Autowired
    private ProxyOf89Task proxyOf89Task;

    @GetMapping("get-proxy")
    public Proxy getProxy() {
        List<Proxy> list = proxyManager.getProxyList(ProxyLevel.GOOD);
        return list.get(new Random().nextInt(list.size()));
    }

    @GetMapping("get-proxy-list")
    public List<Proxy> getProxyList(@Param("num") int num) {
        return proxyManager.getProxyList(ProxyLevel.GOOD).subList(0, num);
    }

    @GetMapping("get-proxy-all")
    public List<Proxy> getProxyAll() {
        return proxyManager.getProxyList(ProxyLevel.GOOD);
    }


}
