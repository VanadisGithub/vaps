package com.vanadis.vap.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vanadis.vap.conf.security.User;
import com.vanadis.vap.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-08-08 10:42
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
