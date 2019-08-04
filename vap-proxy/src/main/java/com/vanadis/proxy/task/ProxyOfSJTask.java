package com.vanadis.proxy.task;

import com.google.common.collect.Lists;
import com.vanadis.lang.http.HttpUtils;
import com.vanadis.proxy.model.Proxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-09 23:48
 */
@Component
public class ProxyOfSJTask extends BaseProxyTask {

    private static String name = "sj";

    public ProxyOfSJTask() {
        super.setTaskName(name);
    }

    @Override
    public List<Proxy> getProxyList() {

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
            Proxy proxy = new Proxy(ip, port, name);
            proxyList.add(proxy);
        }

        return proxyList;
    }
}
