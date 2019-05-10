package com.vanadis.proxy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysql.cj.util.StringUtils;
import com.vanadis.lang.http.HttpUtils;
import com.vanadis.proxy.manager.ProxyManager;
import com.vanadis.proxy.mapper.ProxyMapper;
import com.vanadis.proxy.model.Proxy;
import com.vanadis.proxy.task.ProxyOfXiciTask;
import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-10 09:59
 */
@RestController
public class ProxyController {

    @Autowired
    private ProxyOfXiciTask proxyOfXiciTask;

    @Autowired
    private ProxyManager proxyManager;

    @Autowired
    private ProxyMapper proxyMapper;

    @GetMapping("test")
    public void test() {

        ExecutorService pool = Executors.newCachedThreadPool();

        List<Proxy> proxyList = proxyMapper.selectList(new QueryWrapper<Proxy>().eq("status", 0));
        proxyList.forEach(p -> {
            HttpHost proxy = new HttpHost(p.getIp(), Integer.valueOf(p.getPort()));
            String resultStr = HttpUtils.get("http://www.baidu.com", null, proxy);
            if (StringUtils.isNullOrEmpty(resultStr)) {
                proxyMapper.addErrorNum(proxy.getHostName());
            } else {
                proxyMapper.subErrorNum(proxy.getHostName());
            }
        });
    }

    @GetMapping("get")
    public void get() {

        List<Proxy> list = proxyMapper.selectByErrorNum(1);
        String r = HttpUtils.get("http://www.xicidaili.com/", list.get(new Random().nextInt(list.size())).getHttpHost());
        System.out.println(r);
        //        new ProxyOfXiciTask().result().forEach(proxy -> proxyManager.add(proxy));
    }
}
