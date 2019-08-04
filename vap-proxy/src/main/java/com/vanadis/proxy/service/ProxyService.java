package com.vanadis.proxy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mysql.cj.util.StringUtils;
import com.vanadis.lang.http.HttpUtils;
import com.vanadis.proxy.common.Constant;
import com.vanadis.proxy.manager.ProxyManager;
import com.vanadis.proxy.mapper.ProxyMapper;
import com.vanadis.proxy.model.Proxy;
import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-11 09:55
 */
@Service
public class ProxyService {

    @Autowired
    private ProxyManager proxyManager;

    @Autowired
    private ProxyMapper proxyMapper;

    public void refreshProxy() {
        List<Proxy> proxyList = proxyMapper.selectList(new QueryWrapper<Proxy>().eq("status", 0));

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("refresh-proxy-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        proxyList.forEach(proxy -> pool.execute(() -> {
            HttpHost proxyHost = new HttpHost(proxy.getIp(), Integer.valueOf(proxy.getPort()));
            String resultStr = HttpUtils.visit(Constant.VISIT_URL, proxyHost);
            if (StringUtils.isNullOrEmpty(resultStr)) {
                proxyMapper.addErrorNum(proxy);
            } else {
                proxyMapper.subErrorNum(proxy);
            }
        }));
    }
}
