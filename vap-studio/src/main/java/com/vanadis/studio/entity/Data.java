package com.vanadis.studio.entity;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

import com.vanadis.lang.file.FileUtils;
import org.springframework.stereotype.Component;

/**
 * Data
 *
 * @date 2023/1/31 10:37
 */
@Component
public class Data {

    private static final String DATA_PATH = "./vap-studio/data.json";

    private static JSONObject data;

    public Data() {
        if (data == null) {
            String dataStr = null;
            try {
                dataStr = FileUtils.read(DATA_PATH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            data = JSONObject.parseObject(dataStr);
        }
    }

    public void write(String key, String value) {
        data.put(key, value);
        try {
            FileUtils.write(DATA_PATH, data.toJSONString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject read() {
        return data;
    }

}
