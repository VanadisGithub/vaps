package com.vanadis.lang.object;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-07-25 11:34
 */
@Slf4j
public class Objects {

    /**
     * 获取当前对象属性的值 Integer
     *
     * @param obj       对象
     * @param fieldName 属性名
     */
    public static Integer getValueOfInteger(Object obj, String fieldName) {
        return (Integer) Optional.ofNullable(getValue(obj, fieldName)).orElse(0);
    }

    /**
     * 获取当前对象属性的值 Long
     *
     * @param obj       对象
     * @param fieldName 属性名
     */
    public static Long getValueOfLong(Object obj, String fieldName) {
        return (Long) Optional.ofNullable(getValue(obj, fieldName)).orElse(0);
    }

    /**
     * 获取当前对象属性的值 String
     *
     * @param obj       对象
     * @param fieldName 属性名
     */
    public static String getValueStr(Object obj, String fieldName) {
        return Optional.ofNullable(getValue(obj, fieldName)).orElse("").toString();
    }

    /**
     * 获取当前对象属性的值
     *
     * @param obj       对象
     * @param fieldName 属性名
     */
    public static Object getValue(Object obj, String fieldName) {
        if (obj == null || obj == fieldName || fieldName.length() == 0) {
            return null;
        }
        // 获取对象的属性
        Class<?> clazz = obj.getClass();
        try {
            //向上循环  遍历父类
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                Field[] field = clazz.getDeclaredFields();
                for (Field f : field) {
                    f.setAccessible(true);
                    if (f.getName().equals(fieldName)) {
                        return f.get(obj);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行set方法
     *
     * @param o         执行对象
     * @param fieldName 属性
     * @param value     值
     */
    public static void invokeSet(Object o, String fieldName, Object value) {
        Method method = getSetMethod(o.getClass(), fieldName);
        try {
            method.invoke(o, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行get方法
     *
     * @param o         执行对象
     * @param fieldName 属性
     * @author: 云流
     * @date: 2018/12/13  16:02
     */
    public static Object invokeGet(Object o, String fieldName) {
        Method method = getGetMethod(o.getClass(), fieldName);
        try {
            return method.invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * java反射bean的get方法
     *
     * @param objectClass
     * @param fieldName
     */
//    @SuppressWarnings("unchecked")
    public static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * java反射bean的set方法
     *
     * @param objectClass
     * @param fieldName
     */
//    @SuppressWarnings("unchecked")
    public static Method getSetMethod(Class objectClass, String fieldName) {
        try {
            Class[] parameterTypes = new Class[1];
            Field field = objectClass.getDeclaredField(fieldName.trim());
            field.setAccessible(true);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            return objectClass.getMethod(sb.toString(), parameterTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
