package com.vanadis.proxy.task;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.vanadis.lang.http.HttpUtils;
import com.vanadis.proxy.manager.ProxyManager;
import com.vanadis.proxy.mapper.ProxyMapper;
import com.vanadis.proxy.object.Proxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-09 23:48
 */
public class ProxyOfXiciTask extends BaseTask {

    @Autowired
    private ProxyManager proxyManager;

    public ProxyOfXiciTask(Boolean isSync) {
        super(isSync, "西刺");
    }

    @Override
    public void process() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("proxy-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(12, 12, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        String[] name = {"nt", "nn", "wt", "wn"};
        for (int index = 0; index < name.length; index++) {
            for (int page = 1; page <= 3; page++) {
                int finalPage = page;
                int finalIndex = index;
                singleThreadPool.execute(() -> {
                    String url = "http://www.xicidaili.com/" + name[finalIndex] + "/";
                    if (finalPage > 1) {
                        url += finalPage;
                    }
                    String html = HttpUtils.get(url, null, null);
                    Document doc = Jsoup.parse(html);
                    Elements trs = doc.select("tr");
                    for (int i = 1; i < trs.size(); i++) {
                        Elements tds = trs.get(i).select("td");
                        String ip = tds.get(1).html().trim();
                        String port = tds.get(2).html().trim();
                        Proxy proxy = new Proxy(ip, port);
                        proxyManager.add(proxy);
                    }
                });
            }
        }
    }
}
