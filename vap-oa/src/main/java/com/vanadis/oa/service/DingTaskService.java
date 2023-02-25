package com.vanadis.oa.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * DingTaskService
 *
 * @author yaoyuan
 * @date 2021/12/6 10:11 AM
 */
@Service
public class DingTaskService {

    private static final Logger log = LoggerFactory.getLogger(DingTaskService.class);

    public static String SECRET = "SECf8cf02fc332c8569ebb70d648b8aab456af06c5e4b45b95320d7a785595310d0";

    public static String MESSAGE_URL =
        "https://oapi.dingtalk.com/robot/send"
            + "?access_token=6a4dfd7f4ece5cbfa1513b36acc9d16af6f857cfb7c199d240cac2bdd845a2f4"
            + "&timestamp=%d&sign=%s";

    public static void main(String[] args) throws Exception {
        DingTaskService dingTaskService = new DingTaskService();
        dingTaskService.sendMessageToGroup("羡慕袋鼠云");
    }

    private static String sign(Long timestamp)
        throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        String stringToSign = timestamp + "\n" + SECRET;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        return URLEncoder.encode(new String(Base64.encodeBase64(signData)), StandardCharsets.UTF_8.displayName());
    }

    private void sendMessageToGroup(String content)
        throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {

        Long timestamp = System.currentTimeMillis();
        String url = String.format(MESSAGE_URL, timestamp, sign(timestamp));

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        JSONObject text = new JSONObject();
        text.put("content", content);

        JSONObject param = new JSONObject();
        param.put("msgtype", "text");
        param.put("text", text);

        HttpEntity<String> formEntity = new HttpEntity<>(param.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JSONObject> forEntity = restTemplate.postForEntity(url, formEntity, JSONObject.class);
        JSONObject result = forEntity.getBody();
        log.info(result.toString());
    }
}
