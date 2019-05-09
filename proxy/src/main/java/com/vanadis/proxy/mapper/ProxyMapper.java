package com.vanadis.proxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vanadis.proxy.object.Proxy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Created by 遥远 on 2019-02-01 15:06
 */
@Mapper
@Component
public interface ProxyMapper extends BaseMapper<Proxy> {

}
