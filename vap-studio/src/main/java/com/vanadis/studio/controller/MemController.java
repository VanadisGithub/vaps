package com.vanadis.studio.controller;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

import com.vanadis.lang.file.FileUtils;
import com.vanadis.studio.entity.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MemController {

    @Autowired
    private Data data;

    @GetMapping("write")
    public void write(String text) {
        data.write(text, text);
    }

    @GetMapping("read")
    public JSONObject read() {
        return data.read();
    }
}
