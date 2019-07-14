package com.vanadis.test.service;

import com.vanadis.test.mapper.AaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 测试业务层
 * @author: Created by 遥远 on 2019-06-30T23:19:00.692
 */
@Service
public class AaService {

    @Autowired
    private AaMapper aaMapper;

            public Long selectById (){
            return aaMapper.selectById ();
        }
            public String selectByName (){
            return aaMapper.selectByName ();
        }
    
}