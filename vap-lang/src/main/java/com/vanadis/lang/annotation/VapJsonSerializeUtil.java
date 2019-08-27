package com.vanadis.lang.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-08-26 00:30
 */
@Slf4j
public class VapJsonSerializeUtil extends JsonSerializer<Object> implements ContextualSerializer {

    private double diverse = 100;

    private int floatNum = 0;

    public VapJsonSerializeUtil() {
        log.info("VapJsonSerializeUtil.初始化");
    }

    public VapJsonSerializeUtil(double diverse, int floatNum) {
        this.diverse = diverse;
        this.floatNum = floatNum;
    }

    /**
     * 系列化
     *
     * @param o
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (Objects.nonNull(o)) {
            String priceStr = String.valueOf(o);
            if (!Strings.isNullOrEmpty(priceStr) && NumberUtils.isNumber(priceStr)) {
                BigDecimal priceDecimal = new BigDecimal(priceStr).divide(BigDecimal.valueOf(diverse), floatNum, BigDecimal.ROUND_HALF_UP);
                jsonGenerator.writeString(priceDecimal.toString());
            }
        }
    }

    /**
     * 在序列化时获取字段注解的属性
     *
     * @param serializerProvider
     * @param beanProperty
     * @return
     * @throws JsonMappingException
     */
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        VapJsonSerialize serialize = beanProperty.getAnnotation(VapJsonSerialize.class);
        return new VapJsonSerializeUtil(serialize.diverse(), serialize.floatNum());
    }

}
