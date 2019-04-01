package com.vanadis.cup.controller;

import com.vanadis.cup.service.ProxyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-26 15:24
 */
@RestController
@Api(tags = "ProxyController", description = "代理")
@RequestMapping("proxy")
public class ProxyController {

    @Autowired
    private ProxyService proxyService;

    @ApiOperation(value = "获取西刺代理", notes = "获取西刺代理")
    @GetMapping("getProxyOfXici")
    public Boolean getProxyOfXici() {
        return proxyService.getProxyOfXici();
    }
}
