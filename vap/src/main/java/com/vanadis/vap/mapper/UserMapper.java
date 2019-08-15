package com.vanadis.vap.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vanadis.vap.conf.security.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-08-08 10:43
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
