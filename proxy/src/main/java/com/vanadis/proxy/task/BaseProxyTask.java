package com.vanadis.proxy.task;

import com.vanadis.proxy.manager.ProxyManager;
import com.vanadis.proxy.model.Proxy;

import java.util.List;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-10 10:27
 */
public abstract class BaseProxyTask extends BaseTask {

    private Boolean isSync;

    private ProxyManager proxyManager;

    public abstract List<Proxy> result();

    @Override
    public void process() {
        if (isSync) {
            result().forEach(proxy -> proxyManager.add(proxy));
        }
    }

}
