/**
 * Test
 *
 * @author yaoyuan
 * @date 2020/12/15 10:28 上午
 */
public class Test {

    public static void main(String[] args) {
        newThread(1);
    }

    public static void newThread(Integer i) {
        final Integer finalI = i;
        if (i == 100) {
            return;
        }
        new Thread(() -> {
            newThread(finalI + 1);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(finalI + 1);
        }).start();

    }

}