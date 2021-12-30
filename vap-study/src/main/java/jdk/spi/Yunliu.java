package jdk.spi;

/**
 * @program: van
 * @description:
 * @author: 遥远
 * @create: 2020-06-24 11:50
 */
//@AutoService(Say.class)
public class Yunliu implements Say {
    @Override
    public void sayHello() {
        System.out.println("you are sb");
    }
}
