package jdk.keyword;

/**
 * GitTest
 *
 * @author yy287502@alibaba-inc.com
 * @date 2020/11/13 10:56 上午
 */
public class TryCatchFinallyTest {

    public static void main(String[] args) {
        try {
            //throw new Exception("0");
            System.out.println(0);
            return;
        } catch (Exception e) {
            System.out.println(1);
        } finally {
            System.out.println(2);
        }
        System.out.println(3);
    }
}
