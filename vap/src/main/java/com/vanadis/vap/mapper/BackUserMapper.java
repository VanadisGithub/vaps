package com.vanadis.vap.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vanadis.vap.conf.security.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-08-08 10:43
 */
@DS("vap-back")
@Mapper
@Component
public interface BackUserMapper extends BaseMapper<User> {
}
