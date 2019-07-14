package com.vanadis.test.service;

import com.vanadis.test.mapper.BbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 业务层
 * @author: Created by 遥远 on 2019-06-30T23:19:02.356
 */
@Service
public class BbService {

    @Autowired
    private BbMapper bbMapper;

    
}