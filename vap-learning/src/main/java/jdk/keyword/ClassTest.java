package jdk.keyword;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: van
 * @description:
 * @author: 遥远
 * @create: 2020-05-27 11:45
 */
public class ClassTest {

    public static void main(String[] args)
        throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException,
        InvocationTargetException {
        A a = new A();
        //Class c = Class.forName("test.ClassTest.A");
        Class c = a.getClass();
        Method m1 = c.getDeclaredMethod("test", String.class);
        m1.invoke(a, "abc");

    }

    public static class A {

        public A() {
        }

        public void test(String s) {
            System.out.println(s);
        }
    }

}
