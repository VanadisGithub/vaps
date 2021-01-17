package jdk.thread;

import java.util.concurrent.Callable;

/**
 * Sum
 *
 * @author yy287502@alibaba-inc.com
 * @date 2020/10/30 3:51 下午
 */
public class Sum implements Callable {

    private Integer a;
    private Integer b;

    public Sum(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(1000);
        System.out.println(a);
        return a + b;
    }
}
