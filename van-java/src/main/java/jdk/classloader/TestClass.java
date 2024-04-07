package jdk.classloader;

/**
 * TestClass
 *
 * @author yaoyuan
 * @date 2021/3/29 2:19 下午
 */
public class TestClass implements TestInterface {

    @Override
    public void run() {
        System.out.println(456);
    }
}
