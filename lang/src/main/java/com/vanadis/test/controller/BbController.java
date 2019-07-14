package com.vanadis.test.controller;

import com.vanadis.test.service.BbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 控制层
 * @author: Created by 遥远 on 2019-06-30T23:19:02.356
 */
@RestController
public class BbController {

    @Autowired
    private BbService bbService;

    
}