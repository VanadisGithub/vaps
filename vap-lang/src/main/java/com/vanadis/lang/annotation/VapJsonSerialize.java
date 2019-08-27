package com.vanadis.lang.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-08-26 00:27
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
@JacksonAnnotationsInside
@JsonSerialize(using = VapJsonSerializeUtil.class)
public @interface VapJsonSerialize {

    /**
     * 除数
     *
     * @return
     */
    double diverse() default 1;

    /**
     * 保留小数
     *
     * @return
     */
    int floatNum() default 0;
}
