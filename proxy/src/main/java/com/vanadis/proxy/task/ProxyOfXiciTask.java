package com.vanadis.proxy.task;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.vanadis.lang.http.HttpUtils;
import com.vanadis.proxy.model.Proxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-09 23:48
 */
@Component
public class ProxyOfXiciTask extends BaseProxyTask {

    private static String name = "xici";

    public ProxyOfXiciTask() {
        super.setTaskName(name);
    }

    @Override
    public List<Proxy> getProxyList() {
        List<Proxy> proxyList = Lists.newArrayList();

        CountDownLatch latch = new CountDownLatch(12);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("proxy-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(12, 12, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        String[] pageName = {"nt", "nn", "wt", "wn"};
        for (int index = 0; index < pageName.length; index++) {
            for (int page = 1; page <= 3; page++) {
                int finalPage = page;
                int finalIndex = index;
                pool.submit(() -> {
                    try {
                        String url = "http://www.xicidaili.com/" + pageName[finalIndex] + "/";
                        if (finalPage > 1) {
                            url += finalPage;
                        }
                        String html = HttpUtils.get(url);
                        Document doc = Jsoup.parse(html);
                        Elements trs = doc.select("tr");
                        for (int i = 1; i < trs.size(); i++) {
                            Elements tds = trs.get(i).select("td");
                            String ip = tds.get(1).html().trim();
                            String port = tds.get(2).html().trim();
                            Proxy proxy = new Proxy(ip, port, name);
                            proxyList.add(proxy);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                });
            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();

        return proxyList;
    }
}
