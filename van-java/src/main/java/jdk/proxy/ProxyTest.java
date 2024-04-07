package jdk.proxy;

import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * ProxyTest
 *
 * @author yaoyuan
 * @date 2021/1/15 3:18 下午
 */
public class ProxyTest {

    /**
     * https://www.jianshu.com/p/46d092bb737d
     *
     * 在Spring的AOP编程中:
     * 如果加入容器的目标对象有实现接口,用JDK代理
     * 如果目标对象没有实现接口,用Cglib代理
     *
     * @param args
     */
    public static void main(String[] args) {
        proxy();
        cglib();
    }

    /**
     * 动态代理
     */
    private static void proxy() {
        Say person = new Person();

        Say say = (Say)Proxy.newProxyInstance(
            person.getClass().getClassLoader(),
            person.getClass().getInterfaces(),
            (proxy, method, args1) -> {
                System.out.print("Person替你说：");
                return method.invoke(person, args1);
            });
        say.sayHello();
    }

    private static void cglib() {
        Person2 person = new Person2();

        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(person.getClass());
        //3.设置回调函数
        en.setCallback((MethodInterceptor)(obj, method, args, methodProxy) -> {
            System.out.print("Person2替你说：");
            return methodProxy.invokeSuper(obj, args);
        });
        //4.创建子类(代理对象)
        Person2 say = (Person2)en.create();
        say.sayHello();
    }

}
