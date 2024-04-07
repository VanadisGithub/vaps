package com.vanadis.common.lang.annotation;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Data;

/**
 * NumberFormatTest
 *
 * @author yaoyuan
 * @date 2024/2/2 14:06
 */
public class NumberFormatTest {

    @Data
    public static class Num {

        @NumberFormat(diverse = 100, floatNum = 2)
        private String s;

    }

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Num.class, new NumberFormatHandler());
        mapper.registerModule(module);

        Num num = new Num();
        num.setS("123456");
        System.out.println(mapper.writeValueAsString(num));
    }
}
