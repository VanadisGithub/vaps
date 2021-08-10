package com.vanadis.future.controller;

import java.util.Set;

import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Sets;

/**
 * Test
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/8/10 5:30 下午
 */
public class Test {

    private static Set<String> set = Sets.newHashSet();

    public static void main(String[] args) {
        set.add("1");
        set.add("2");
        set.add("3");
        set.remove("1");
        set.remove("2");
        set.remove("4");
        System.out.println(JSONObject.toJSON(set));
    }
}
