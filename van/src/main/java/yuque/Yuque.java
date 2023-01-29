package yuque;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import taobao.HttpUtils;

/**
 * Yuque
 *
 * API文档
 * https://www.yuque.com/yuque/developer/doc
 *
 * @author yy287502@alibaba-inc.com
 * @date 2022/3/3 5:06 PM
 */
public class Yuque {

    private static final String YUQUE_TOKEN = "dMEcBP89AojcE5gJL3WbvM1KoCxdmCrxYcpVN9d8";

    private static final String NAMESPACE = "yaoyuan-jufvo/ium6xl";

    private static final String DOC_ID = "68459241";

    private static final String DOC_SLUG = "ckw5y5";

    public static void main(String[] args) throws URISyntaxException {

        //HttpPut put = new HttpPut();
        //URI uri = new URI("https://www.yuque.com/api/v2/repos/:repo_id/docs/:id");
        //put.setURI(uri);
        //put.setParams();

        System.out.println(appendDocBody(NAMESPACE, DOC_ID, DOC_SLUG, "0304 新增"));
    }

    public static String appendDocBody(String newBody) {
        return appendDocBody(NAMESPACE, DOC_ID, DOC_SLUG, newBody);
    }

    public static String unDoDocBody() {
        String docBody = getDocBody(NAMESPACE, DOC_SLUG);
        return updateDocBody(NAMESPACE, DOC_ID, docBody.substring(docBody.indexOf("\n")));
    }

    public static String appendDocBody(String namespace, String docId, String docSlug, String newBody) {
        String docBody = getDocBody(namespace, docSlug);
        return updateDocBody(namespace, docId, newBody + "\n" + docBody);
    }

    public static String getDocBody(String namespace, String docSlug) {
        HttpGet get = new HttpGet();
        try {
            String url = String.format("https://www.yuque.com/api/v2/repos/%s/docs/%s", namespace, docSlug);
            get.setURI(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-Auth-Token", YUQUE_TOKEN);
        String result = HttpUtils.http(get, headerMap, null, false);
        JSONObject jsonObject = JSONObject.parseObject(result);

        return jsonObject.getJSONObject("data").getString("body");
    }

    public static String updateDocBody(String namespace, String docId, String docBody) {
        HttpPut put = new HttpPut();
        try {
            String url = String.format("https://www.yuque.com/api/v2/repos/%s/docs/%s", namespace, docId);
            put.setURI(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        JSONObject body = new JSONObject();
        body.put("body", docBody);
        body.put("_force_asl", 1);
        StringEntity entity = new StringEntity(body.toJSONString(), Charset.forName("utf-8"));
        entity.setContentType("application/json");
        put.setEntity(entity);

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-Auth-Token", YUQUE_TOKEN);
        headerMap.put("Content-Type", "application/json");

        String result = HttpUtils.http(put, headerMap, null, false);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.getJSONObject("data").getString("body");
    }
}
