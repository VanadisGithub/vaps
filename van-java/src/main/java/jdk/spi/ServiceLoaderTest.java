package jdk.spi;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import org.springframework.context.ApplicationContext;

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

    //Spring
    //@Autowired
    private ApplicationContext applicationContext;

    /**
     * 从Spring 容器及 SPI 加载服务.
     *
     * @param providerClass
     */
    public <T extends Provider> List<T> loadProvider(Class<T> providerClass) {
        List<T> providers = new ArrayList<>();
        // Spring container
        applicationContext.getBeansOfType(providerClass)
            .forEach((name, provider) -> providers.add(provider));

        // SPI
        ServiceLoader<T> serviceLoader = ServiceLoader.load(providerClass);
        serviceLoader.forEach(providers::add);
        return providers;
    }

}

