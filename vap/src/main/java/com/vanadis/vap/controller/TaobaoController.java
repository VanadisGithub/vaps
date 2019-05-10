package com.vanadis.vap.controller;

import com.vanadis.vap.object.taobao.TaobaoBuyUrl;
import com.vanadis.vap.service.TaobaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @description:
 * @author: Created by 遥远 on 2019-01-26 15:24
 */
@RestController
@Api(tags = "TaobaoController", description = "淘宝")
@RequestMapping("taobao")
public class TaobaoController {

    @Autowired
    private TaobaoService taobaoService;

    @ApiOperation(value = "淘宝优惠券", notes = "获取淘宝优惠券")
    @ApiImplicitParam(name = "taobaoUrl", value = "https://detail.tmall.com/item.htm?id=547717653043", required = true)
    @GetMapping("coupon")
    public List<TaobaoBuyUrl> coupon(@RequestParam String taobaoUrl) {
        return taobaoService.coupon(taobaoUrl);
    }
}
