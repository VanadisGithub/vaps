package jdk.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.facebook.presto.jdbc.internal.guava.collect.Queues;

/**
 * QueueTest
 *
 * @author yaoyuan
 * @date 2021/5/6 5:19 下午
 */
public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = Queues.newArrayBlockingQueue(3);

        //add()和remove()方法在失败的时候会抛出异常(不推荐)
        queue.add(1);
        queue.remove();

        queue.put(2);
        queue.offer(3);
        queue.offer(4, 1L, TimeUnit.SECONDS);

        System.out.println(queue.take());
        System.out.println(queue.poll());
        System.out.println(queue.poll(1L, TimeUnit.SECONDS));

    }
}
