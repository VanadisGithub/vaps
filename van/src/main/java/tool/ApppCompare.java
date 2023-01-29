package tool;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

/**
 * AppCompare
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/5/27 4:18 下午
 */
public class ApppCompare {

    public static void main(String[] args) throws IOException {

        String file1 = "./van/src/main/java/tool/file1";
        String file2 = "./van/src/main/java/tool/file2";

        List<String> strings1 = Files.readAllLines(Paths.get(file1), StandardCharsets.UTF_8);
        List<String> strings2 = Files.readAllLines(Paths.get(file2), StandardCharsets.UTF_8);
        
        Set<String> set1 = clear(strings1);
        Set<String> set2 = clear(strings2);
        
        System.out.println("文件1：缺");
        for (String s : Sets.difference(set2, set1)) {
            System.out.println(s);
        }
        
        System.out.println("文件2：缺");
        for (String s : Sets.difference(set1, set2)) {
            System.out.println(s);
        }

    }

    public static Set<String> clear(List<String> strings) {
        return strings.stream()
            .filter(s -> !s.startsWith("#") && !s.startsWith("\n") && !s.isEmpty() && s.contains("="))
            .map(s -> s.substring(0, s.indexOf("=")).trim())
            .collect(Collectors.toSet());
    }

}
