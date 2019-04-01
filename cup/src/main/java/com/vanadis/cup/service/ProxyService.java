package com.vanadis.cup.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vanadis.cup.mapper.ProxyMapper;
import com.vanadis.cup.object.proxy.Proxy;
import com.vanadis.cup.utils.http.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-30 10:00
 */
@Service
public class ProxyService {

    @Autowired
    private ProxyMapper proxyMapper;

    /**
     * 获取西刺代理
     *
     * @return
     */
    public Boolean getProxyOfXici() {

        String[] name = {"nt", "nn", "wt", "wn"};
        for (int index = 0; index < name.length; index++) {
            for (int page = 1; page <= 3; page++) {
                String url = "http://www.xicidaili.com/" + name[index] + "/";
                if (page > 1) {
                    url += page;
                }
                String html = HttpUtils.doGet(url, null, null);
                Document doc = Jsoup.parse(html);
                Elements trs = doc.select("tr");
                for (int i = 1; i < trs.size(); i++) {
                    Elements tds = trs.get(i).select("td");
                    String ip = tds.get(1).html().trim();
                    String port = tds.get(2).html().trim();
                    Proxy proxy = new Proxy(ip, port);
                    if (proxyMapper.selectCount(Wrappers.<Proxy>lambdaQuery().eq(Proxy::getIp, ip)) < 1) {
                        proxyMapper.insert(proxy);
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return Boolean.TRUE;
    }
}
