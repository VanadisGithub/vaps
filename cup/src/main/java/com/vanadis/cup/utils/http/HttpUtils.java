package com.vanadis.cup.utils.http;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HttpUtils {

    public static String UserAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Maxthon/4.9.2.1000 Chrome/39.0.2146.0 Safari/537.36";

    private static int timeout = 30000;

    private static RequestConfig baseRequestConfig = RequestConfig.custom()
            .setMaxRedirects(20)
            .setCircularRedirectsAllowed(true)
            .setConnectTimeout(timeout)
            .setSocketTimeout(timeout)
            .build();

    public static String doPost(String url, String dataStr, Map<String, Object> headerMap, HttpHost proxy) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost post = new HttpPost(url);

            if (!Strings.isNullOrEmpty(dataStr)) {
                StringEntity entity = new StringEntity(dataStr, Charset.forName("utf-8"));
                entity.setContentEncoding("utf-8");
                entity.setContentType("application/json");
                post.setEntity(entity);
            }

            //默认添加请求头
            post.addHeader("user-agent", UserAgentUtils.getUserAgent());


            if (headerMap != null) {
                for (String key : headerMap.keySet()) {
                    post.setHeader(key, String.valueOf(headerMap.get(key)));
                }
            }

            if (proxy != null) {
                RequestConfig requestConfig = RequestConfig.copy(baseRequestConfig).setProxy(proxy).build();
                post.setConfig(requestConfig);
            }

            HttpResponse response = httpClient.execute(post);

            int statusCode = response.getStatusLine().getStatusCode();

            log.info("doPost:" + url + " " + statusCode);

            if (statusCode == HttpStatus.SC_OK) {
                String resultStr = EntityUtils.toString(response.getEntity(), "utf-8");
                return resultStr;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Map<String, Object> testPost(String url, String dataStr, Map<String, Object> headerMap) {
        Map<String, Object> result = new HashMap<String, Object>();
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost post = new HttpPost(url);

            if (!Strings.isNullOrEmpty(dataStr)) {
                StringEntity entity = new StringEntity(dataStr, Charset.forName("utf-8"));
                entity.setContentEncoding("utf-8");
                entity.setContentType("application/json");
                post.setEntity(entity);
            }

            if (headerMap != null) {
                for (String key : headerMap.keySet()) {
                    post.setHeader(key, String.valueOf(headerMap.get(key)));
                }
            }

            HttpResponse response = httpClient.execute(post);

            int statusCode = response.getStatusLine().getStatusCode();

            result.put("status", statusCode);

            log.info("testPost:" + url + " " + statusCode);

            if (statusCode == HttpStatus.SC_OK) {
                String resultStr = EntityUtils.toString(response.getEntity(), "utf-8");
                result.put("result", resultStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param url
     * @param headerMap
     * @return
     */
    public static String doGet(String url, Map<String, Object> headerMap) {
        return doGet(url, headerMap, null);
    }

    /**
     * @param url
     * @param headerMap
     * @param proxy
     * @return
     */
    public static String doGet(String url, Map<String, Object> headerMap, HttpHost proxy) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet get = new HttpGet(url);

            if (headerMap != null) {
                for (String key : headerMap.keySet()) {
                    get.setHeader(key, String.valueOf(headerMap.get(key)));
                }
            }

            //默认添加请求头
            get.addHeader("user-agent", UserAgentUtils.getUserAgent());

            if (proxy != null) {
                RequestConfig requestConfig = RequestConfig.copy(baseRequestConfig).setProxy(proxy).build();
                get.setConfig(requestConfig);
            }

            HttpResponse response = httpClient.execute(get);

            int statusCode = response.getStatusLine().getStatusCode();

            log.info("【Get】{} {}", url, statusCode);

            if (statusCode == HttpStatus.SC_OK) {
                String resultStr = EntityUtils.toString(response.getEntity(), "utf-8");
                return resultStr;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String visitGet(String url, Map<String, Object> headerMap, HttpHost proxy) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);

        if (headerMap != null) {
            for (String key : headerMap.keySet()) {
                get.setHeader(key, String.valueOf(headerMap.get(key)));
            }
        }

        //默认添加请求头
        get.addHeader("user-agent", UserAgentUtils.getUserAgent());

        if (proxy != null) {
            RequestConfig requestConfig = RequestConfig.copy(baseRequestConfig).setProxy(proxy).build();
            get.setConfig(requestConfig);
        }

        HttpResponse response = httpClient.execute(get);

        int statusCode = response.getStatusLine().getStatusCode();

        log.info("visitGet:" + url + " " + statusCode);

        if (statusCode == HttpStatus.SC_OK) {
            String resultStr = EntityUtils.toString(response.getEntity(), "utf-8");
            return resultStr;
        }
        return null;
    }

    public static Map<String, Object> postmanGet(String url, Map<String, Object> headerMap) {
        Map<String, Object> result = new HashMap<String, Object>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet get = new HttpGet(url);

            if (headerMap != null) {
                for (String key : headerMap.keySet()) {
                    get.setHeader(key, String.valueOf(headerMap.get(key)));
                }
            }

            HttpResponse response = httpClient.execute(get);

            int statusCode = response.getStatusLine().getStatusCode();

            log.info("postmanGet:" + url + " " + statusCode);

            result.put("status", statusCode);

            if (statusCode == HttpStatus.SC_OK) {
                String resultStr = EntityUtils.toString(response.getEntity(), "utf-8");
                result.put("result", resultStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
