package com.vanadis.vap.conf.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.vanadis.vap.common.VapAnnotation;
import com.vanadis.vap.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Created by 遥远 on 2019-03-21 23:36
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @VapAnnotation
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("[security]获取用户信息");
        if (userService.count() == 0) {
            userService.save(new User("admin", new BCryptPasswordEncoder().encode("123")));
        }
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("abc"));
        return new User("root", new BCryptPasswordEncoder().encode("123"));
    }
}
