package utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * CmdUtils
 *
 * @author yaoyuan
 * @date 2024/4/7 13:57
 */
public class CmdUtils {

    public static String[] deleteFirst(String[] arr) {
        String[] temp = new String[arr.length - 1];
        System.arraycopy(arr, 1, temp, 0, temp.length);
        return temp;
    }

    public static void exec(String[] command) {
        try {
            Log.info("exec: " + String.join(" ", command));
            Process pro = Runtime.getRuntime().exec(command);
            pro.waitFor();
            InputStream in = pro.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            String result;
            while ((result = read.readLine()) != null) {
                Log.info(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
