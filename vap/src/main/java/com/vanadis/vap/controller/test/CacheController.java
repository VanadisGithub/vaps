package com.vanadis.vap.controller.test;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author: Created by 遥远 on 2019-03-22 15:59
 */
@RestController
@Api(tags = "CacheController", description = "缓存")
public class CacheController {

    @Cacheable(value = "cache")
    @ApiOperation(value = "测试", notes = "测试备注")
    @ApiImplicitParam(name = "test", value = "123")
    @GetMapping("cacheAble")
    public String cacheAble(@RequestParam(required = false) String test) {
        return String.valueOf(new Random().nextInt(100));
    }

    @Cacheable(value = "cache")
    @ApiOperation(value = "测试List", notes = "测试备注")
    @ApiImplicitParam(name = "test", value = "123")
    @GetMapping("cacheAbleList")
    public List<Integer> cacheAbleList(@RequestParam(required = false) String test) {
        List<Integer> list = Lists.newArrayList();
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        return list;
    }

}
