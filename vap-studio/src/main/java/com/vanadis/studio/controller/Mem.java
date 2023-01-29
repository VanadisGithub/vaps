package com.vanadis.studio.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSONObject;

import com.vanadis.lang.file.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Mem
 *
 * @author yaoyuan
 * @date 2023/1/28 17:24
 */
@RestController
@RequestMapping("")
public class Mem {

    private static final String DATA_PATH = "./vap-studio/data.json";
    private JSONObject data = new JSONObject();

    @GetMapping("write")
    public void write(String text) throws IOException {
        data.put(text, text);
        FileUtils.write(DATA_PATH, data.toJSONString());
    }

    @GetMapping("read")
    public JSONObject read() throws IOException {
        String dataStr = FileUtils.read(DATA_PATH);
        return JSONObject.parseObject(dataStr);
    }

    @GetMapping("run")
    public String run() throws IOException, InterruptedException {
        BufferedReader reader = null;
        Process process = Runtime.getRuntime().exec("ls");
        int exitValue = process.waitFor();
        // 返回值
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println("mac@wxw %  " + line);
        }
        return "1";
    }
}
