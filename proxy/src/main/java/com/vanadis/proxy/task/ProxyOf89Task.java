package com.vanadis.proxy.task;

import com.google.common.collect.Lists;
import com.vanadis.lang.http.HttpUtils;
import com.vanadis.proxy.model.Proxy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-09 23:48
 */
@Component
public class ProxyOf89Task extends BaseProxyTask {

    private static String name = "89";

    public ProxyOf89Task() {
        super.setTaskName(name);
    }

    @Override
    public List<Proxy> getProxyList() {
        List<Proxy> proxyList = Lists.newArrayList();

        String url = "http://www.89ip.cn/tqdl.html?api=1&num=1000&port=&address=&isp=";
        String result = HttpUtils.get(url);
        result = result.substring(result.indexOf("</script>") + "</script>".length(), result.length());
        result = result.substring(result.indexOf("</script>\n") + "</script>".length(), result.indexOf("高效高匿名代理IP提取地址"));
        String[] ips = result.split("<br>");
        for (int i = 0; i < ips.length; i++) {
            String ip = ips[i];
            String[] ipp = ip.split(":");
            if (ipp.length > 1) {
                proxyList.add(new Proxy(ipp[0].trim(), ipp[1].trim(), name));
            }
        }
        return proxyList;
    }
}
