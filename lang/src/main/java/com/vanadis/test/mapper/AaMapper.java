package com.vanadis.test.mapper;

import org.springframework.stereotype.Component;

/**
 * @description: 测试
 * @author: Created by 遥远 on 2019-06-30T23:19:00.692
 */
@Component
public interface  AaMapper {

            Long selectById ();
            String selectByName ();
    
}