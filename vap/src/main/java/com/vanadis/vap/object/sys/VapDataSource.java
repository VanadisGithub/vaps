package com.vanadis.vap.object.sys;

import com.vanadis.vap.object.Base;

import javax.persistence.Entity;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-08-30 11:31
 */
@Entity
public class VapDataSource extends Base {

    private String dbType;

    private String url;

    private String dbName;

    private String username;

    private String password;
}
