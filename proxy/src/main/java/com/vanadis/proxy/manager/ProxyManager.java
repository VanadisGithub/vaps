package com.vanadis.proxy.manager;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vanadis.proxy.mapper.ProxyMapper;
import com.vanadis.proxy.object.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-10 01:25
 */
@Component
public class ProxyManager {

    @Autowired
    private ProxyMapper proxyMapper;

    public boolean add(Proxy proxy) {
        if (proxyMapper.selectCount(Wrappers.<Proxy>lambdaQuery()
                .eq(Proxy::getIp, proxy.getIp()).eq(Proxy::getPort, proxy.getPort())) < 1) {
            return proxyMapper.insert(proxy) > 0;
        }
        return false;
    }
}
