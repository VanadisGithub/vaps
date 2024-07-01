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
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of cores: " + cores);
    }

}