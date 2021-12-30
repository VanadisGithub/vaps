package com.vanadis.lang.http;

import java.io.*;
import java.net.InetAddress;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-07-26 18:04
 */
public class IPUtils {

    private static String url = "https://api.ipify.org";

    public static void main(String[] args) throws Exception {
        System.out.println("本机的外网IP是：" + getWebIP());
        System.out.println("本机的内网IP是：" + getLocalIP());
    }


    /**
     * 获取外网地址
     *
     * @return
     */
    public static String getWebIP() {
        return HttpUtils.get(url);
    }

    /**
     * 获取内网IP
     *
     * @return
     * @throws Exception
     */
    public static String getLocalIP() throws Exception {
        return InetAddress.getLocalHost().getHostAddress();
    }

    static void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[1];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        }
    }

}
