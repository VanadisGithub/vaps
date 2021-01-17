package jdk.math;

/**
 * MathTest
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/1/11 5:41 下午
 */
public class MathTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(exp(0.6));
        }
    }

    public static boolean exp(double probabilityTrue) {
        System.out.println(Math.random());
        return Math.random() >= 1.0 - probabilityTrue;
    }
}
