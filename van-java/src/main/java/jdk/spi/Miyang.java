package jdk.spi;

import com.google.auto.service.AutoService;

/**
 * @program: van
 * @description: @AutoService spi自动注入实现类，不需要配置文件
 * @author: 遥远
 * @create: 2020-06-24 11:50
 */
@AutoService(Say.class)
public class Miyang implements Say {
    @Override
    public void sayHello() {
        System.out.println("i am miyang");
    }
}
