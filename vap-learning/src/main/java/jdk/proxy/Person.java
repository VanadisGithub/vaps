package jdk.proxy;

/**
 * Person
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/1/15 3:29 下午
 */
public class Person implements Say {
    @Override
    public void sayHello() {
        System.out.println("Hello!");
    }
}
