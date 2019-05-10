package com.vanadis.proxy.task;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.vanadis.lang.http.HttpUtils;
import com.vanadis.proxy.manager.ProxyManager;
import com.vanadis.proxy.model.Proxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-09 23:48
 */
@Component
public class ProxyOfSJTask extends BaseProxyTask {

    @Value("${app.schedule.is-sync}")
    private Boolean isSync;

    @Autowired
    private ProxyManager proxyManager;

    public ProxyOfSJTask() {
        super.taskName = "神鸡";
    }

    @Override
    public List<Proxy> result() {

        List<Proxy> proxyList = Lists.newArrayList();

        String url = "http://www.shenjidaili.com/open/";
        String html = HttpUtils.get(url);
        Document doc = Jsoup.parse(html);
        Elements trs = doc.select("tr");
        for (int i = 1; i < trs.size(); i++) {
            Elements tds = trs.get(i).select("td");
            String ip = tds.get(0).html().trim();
            if ("免费ip".equals(ip)) {
                continue;
            }
            String port = tds.get(1).html().trim();
            Proxy proxy = new Proxy(ip, port, "sj");
            proxyList.add(proxy);
        }

        return proxyList;
    }
}
