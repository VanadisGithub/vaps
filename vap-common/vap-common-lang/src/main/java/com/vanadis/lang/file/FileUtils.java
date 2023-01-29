package com.vanadis.lang.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 * FileUtils
 *
 * @author yaoyuan
 * @date 2021/3/11 8:32 下午
 */
public class FileUtils {

    private static final String BASE64_PNG_HEADER = "data:image/png;base64,";

    public static String read(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        return String.join("\n", lines);
    }

    public static void write(String path, String content) throws IOException {
        Files.write(Paths.get(path), content.getBytes(StandardCharsets.UTF_8));
    }

    public static void writeAppend(String path, String content) throws IOException {
        Files.write(Paths.get(path), content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }

    public static boolean mkdir(String path) {
        File file = new File(path);
        return file.exists() || file.mkdir();
    }

    public static String encodeImg(InputStream inputStream) throws IOException {
        byte[] fileData = IOUtils.toByteArray(inputStream);
        inputStream.close();
        return BASE64_PNG_HEADER + Base64.encodeBase64String(fileData);
    }

    //public static String decodeImg(String imageStr) throws IOException {
    //    byte[] fileData = IOUtils.toByteArray(inputStream);
    //    inputStream.close();
    //    return BASE64_PNG_HEADER + Base64.decodeBase64(fileData);
    //}
    //
    //private void responseFile(HttpServletResponse response, File imgFile) {
    //    try(InputStream is = new FileInputStream(imgFile);
    //        OutputStream os = response.getOutputStream();){
    //        byte [] buffer = new byte[1024]; // 图片文件流缓存池
    //        while(is.read(buffer) != -1){
    //            os.write(buffer);
    //        }
    //        os.flush();
    //    } catch (IOException ioe){
    //        ioe.printStackTrace();
    //    }
    //}
}
