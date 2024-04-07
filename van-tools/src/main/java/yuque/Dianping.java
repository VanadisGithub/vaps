package yuque;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.methods.HttpGet;
import taobao.HttpUtils;

/**
 * Dianping
 *
 * @author yaoyuan
 * @date 2022/3/10 11:54 AM
 */
public class Dianping {

    public static void main(String[] args) throws URISyntaxException {
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(new URI("https://www.dianping.com/search/keyword/6/0_%E5%A9%9A%E7%BA%B1%E6%91%84%E5%BD%B1"));
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Pragma", "no-cache");
        httpGet.setHeader("Cache-Control", "no-cache");
        httpGet.setHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"");
        httpGet.setHeader("sec-ch-ua-mobile", "?0");
        httpGet.setHeader("sec-ch-ua-platform", "macOS");
        httpGet.setHeader("Upgrade", " 1");
        httpGet.setHeader("User",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844"
                + ".51 Safari/537.36");
        httpGet.setHeader("Accept",
            "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,"
                + "application/signed-exchange;v=b3;q=0.9");
        httpGet.setHeader("Sec", " none");
        httpGet.setHeader("Sec", " navigate");
        httpGet.setHeader("Sec", " ?1");
        httpGet.setHeader("Sec", " document");
        httpGet.setHeader("Accept", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7,zh-TW;q=0.6");
        httpGet.setHeader("Cookie",
            "fspop=test; _lxsdk_cuid=17f71e0928ac8-0d4be690ed41d-113f645d-13c680-17f71e0928ac8; "
                + "_lxsdk=17f71e0928ac8-0d4be690ed41d-113f645d-13c680-17f71e0928ac8; _hc"
                + ".v=23a58c6e-a3a4-eab1-6b35-619103088791.1646883017; "
                + "Hm_lvt_602b80cf8079ae6591966cc70a3940e7=1646883019; s_ViewType=10; "
                + "lgtoken=0d82d244a-1665-4372-b1a7-e98b1fc079e3; dplet=17f5336d9ea56fc85b36c37184bfe46d; "
                + "dper"
                +
                "=dd9702b21256ac6bda2c603a656ddb680aa45d8259be2235ed1e0120bdbfd650b0ec6f70b8317f0110493a3aad5dba81fb84f3811d00819e156b8289aec4fd600e4a716f5c90607c3e990c98d62b1ea7076968328634d8ae25e6c82aab0a089a; ll=7fd06e815b796be3df069dec7836c3df; ua=V.anadis; ctu=31f28fd2a56070656bb7e647a2e39e5fe7468ca8aa57978d8fa6906ba9ade128; uamo=18857117153; cy=6; cye=suzhou; _lxsdk_s=17f71e0928c-12f-736-9ba%7C%7C260; Hm_lpvt_602b80cf8079ae6591966cc70a3940e7=1646884367");
        String http = HttpUtils.http(httpGet, null, null, false);
        System.out.println(http);

        Pattern pattern = Pattern.compile("<h4>(.*?)</h4>");
        Matcher matcher = pattern.matcher(http);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
