package google.subscribe;

/**
 * MyPublish
 *
 * @author yaoyuan
 * @date 2021/3/17 8:40 下午
 */
public class MyPublisher {

    public static void main(String[] args) {

        MySubscribe observer1 = new MySubscribe();
        EventBusCenter.register(observer1);

        EventBusCenter.post("Hello world!");
        System.out.println("end");
    }
}
