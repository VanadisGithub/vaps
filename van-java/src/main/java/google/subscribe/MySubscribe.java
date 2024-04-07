package google.subscribe;

import com.google.common.eventbus.Subscribe;

/**
 * MySubscribe
 *
 * @author yaoyuan
 * @date 2021/3/17 8:39 下午
 */
public class MySubscribe {

    @Subscribe
    public void handle(String msg) throws InterruptedException {
        Thread.sleep(10000L);
        System.out.println(msg);
    }

    @Subscribe
    public void handle2(String msg) throws InterruptedException {
        System.out.println("handle2" + msg);
    }
}
