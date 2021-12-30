package com.vanadis.lang.file;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * FileUtils
 *
 * @author yaoyuan
 * @date 2021/3/11 8:32 下午
 */
public class FileUtils {

    public static String read(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        return String.join("\n", lines);
    }

    public static void write(String path, String content) throws IOException {
        Files.write(Paths.get(path), content.getBytes(StandardCharsets.UTF_8));
    }

    public static boolean mkdir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return file.mkdir();
        }
        return true;
    }
}
