package jdk.jni;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * JavaNativeInterfaceTest
 *
 * @author yaoyuan
 * @date 2021/1/22 5:17 下午
 */
public class JavaNativeInterfaceTest {

    public static void main(String[] args) {
        HelloInter INSTANCE =
            Native.loadLibrary(
                Platform.isWindows() ? "msvcrt" : "c",
                HelloInter.class);
        INSTANCE.printf("Hello, World\n");
        String[] strs = new String[] {"芙蓉", "如花", "凤姐"};
        for (int i = 0; i < strs.length; i++) {
            INSTANCE.printf("人物 %d: %s\n", i, strs[i]);
        }
        System.out.println("pow(2d,3d)==" + INSTANCE.pow(2d, 3d));
        System.out.println("toupper('a')==" + (char)INSTANCE.toupper((int)'a'));
    }

    interface HelloInter extends Library {
        int toupper(int ch);
        double pow(double x,double y);
        void printf(String format,Object... args);
    }

}
