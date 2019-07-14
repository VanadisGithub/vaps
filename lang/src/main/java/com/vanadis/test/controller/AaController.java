package com.vanadis.test.controller;

import com.vanadis.test.service.AaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试控制层
 * @author: Created by 遥远 on 2019-06-30T23:19:00.692
 */
@RestController
public class AaController {

    @Autowired
    private AaService aaService;

            public Long selectById (){
            return aaService.selectById ();
        }
            public String selectByName (){
            return aaService.selectByName ();
        }
    
}