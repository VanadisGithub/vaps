package com.vanadis.proxy.task;

import com.vanadis.proxy.manager.ProxyManager;
import com.vanadis.proxy.model.Proxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-10 10:27
 */
@Data
public class BaseProxyTask extends BaseTask implements ProxyInterface {

    @Value("${app.schedule.is-sync}")
    private Boolean isSync;

    @Autowired
    private ProxyManager proxyManager;

    @Override
    public void process() {
        if (isSync) {
            getProxyList().forEach(proxy -> proxyManager.add(proxy));
        }
    }

    @Override
    public List<Proxy> getProxyList() {
        return new ArrayList<>();
    }
}
