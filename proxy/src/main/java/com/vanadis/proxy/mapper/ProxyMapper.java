package com.vanadis.proxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vanadis.proxy.model.Proxy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: Created by 遥远 on 2019-02-01 15:06
 */
@Mapper
@Component
public interface ProxyMapper extends BaseMapper<Proxy> {

    @Update("update proxy set error_num = error_num + 1 where ip = #{ip}")
    boolean addErrorNum(@Param("ip") String ip);

    @Update("update proxy set error_num = error_num - 1 where ip = #{ip}")
    boolean subErrorNum(@Param("ip") String ip);

    @Select("select * from proxy where error_num < #{num}")
    List<Proxy> selectByErrorNum(int num);

}
