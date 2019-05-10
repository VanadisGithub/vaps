package com.vanadis.proxy.manager;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vanadis.proxy.mapper.ProxyMapper;
import com.vanadis.proxy.model.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-10 01:25
 */
@Component
public class ProxyManager {

    private static final int GOOD = 1;

    @Autowired
    private ProxyMapper proxyMapper;

    public List<Proxy> goodProxy() {
        return proxyMapper.selectByErrorNum(GOOD);
    }

    public boolean add(Proxy proxy) {
        if (proxyMapper.selectCount(Wrappers.<Proxy>lambdaQuery()
                .eq(Proxy::getIp, proxy.getIp()).eq(Proxy::getPort, proxy.getPort())) < 1) {
            return proxyMapper.insert(proxy) > 0;
        }
        return false;
    }


}
