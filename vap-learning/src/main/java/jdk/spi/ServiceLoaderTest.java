package jdk.spi;

import java.util.ServiceLoader;

/**
 * @program: van
 * @description:
 * @author: 遥远
 * @create: 2020-06-24 11:44
 */
public class ServiceLoaderTest {

    /**
     * SPI加载
     *
     * 方法一：读取resource/META-INFO/services/接口类路径名 配置文件
     * 方法二：实现类注解@AutoService spi自动注入实现类，不需要配置文件（和方法一冲突）
     *
     * @param args
     */
    public static void main(String[] args) {
        ServiceLoader<Say> says = ServiceLoader.load(Say.class);
        for (Say say : says) {
            say.sayHello();
        }
    }

}

