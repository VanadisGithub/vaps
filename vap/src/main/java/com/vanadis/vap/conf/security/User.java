package com.vanadis.vap.conf.security;

import com.baomidou.mybatisplus.annotation.TableField;
import com.vanadis.vap.object.Base;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

/**
 * @description:
 * @author: Created by 遥远 on 2019-03-21 23:39
 */
@Data
@Entity
public class User extends Base implements UserDetails {

    private String username;

    private String password;

    private String userNick;

    @Transient //Hibernate 忽略该字段映射
    @TableField(exist = false) // mybatis plus 忽略该字段
    private List<GrantedAuthority> authorities;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
