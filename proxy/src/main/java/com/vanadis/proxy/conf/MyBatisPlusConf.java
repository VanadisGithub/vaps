package com.vanadis.proxy.conf;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-10 01:17
 */
@Configuration
public class MyBatisPlusConf {

    /**
     * 元对象字段填充控制器
     *
     * @return
     */
    @Bean
    public VapMetaObjectHandler metaObjectHandler() {
        return new VapMetaObjectHandler();
    }

    /**
     * 元对象字段填充控制器
     *
     * @return
     */
    class VapMetaObjectHandler implements MetaObjectHandler {
        @Override
        public void insertFill(MetaObject metaObject) {
            setFieldValByName("createTime", new Date(), metaObject);
            setFieldValByName("updateTime", new Date(), metaObject);
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
    }

}
