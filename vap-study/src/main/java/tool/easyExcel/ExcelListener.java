package tool.easyExcel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;

import com.google.common.base.Strings;
import lombok.SneakyThrows;

public class ExcelListener implements ReadListener<Map<Integer, String>> {

    static String filePath =
        "/Users/yaoyuan/IdeaProjects/dataphin/dataphin-openapi-sdk/aliyun-java-sdk-dataphin/src/test/java/com"
            + "/aliyuncs/dataphin/model/v20200830/";
    static Map<String, String> fileMap = getFiles(filePath);
    List<Map<Integer, String>> apiMap = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //StringBuffer buffer = new StringBuffer();
        //new ExcelListener().addEnd(buffer, "PublishObject");
        //System.out.println(buffer);
    }

    public static Map<String, String> getFiles(String path) {
        Map<String, String> fileMap = new HashMap<>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                fileMap.put(tempList[i].getName(), tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {
                fileMap.putAll(getFiles(tempList[i].toString()));
            }
        }
        return fileMap;
    }

    public static String getSubUtilSimple(String soap, String regx) {
        Pattern pattern = Pattern.compile(regx);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return "";
    }

    public void addEnd(StringBuffer buffer, String appName, String sheetName) throws IOException {
        String filePath = fileMap.get(String.format("%sTest.java", appName));
        //List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        //String codeAll = String.join("\n", lines);
        //String code = codeAll.substring(codeAll.indexOf("throws Exception")).substring("throws Exception".length());
        buffer.append("\n");
        buffer.append("\n");
        buffer.append("## 示例\n"
            + "\n"
            + "```java\n"
            + "\n"
            + "```\n"
            + "\n"
            + "\n"
            + "\n"
            + "## 错误码\n"
            + "\n"
            + "\n"
            + "\n"
            + "访问错误中心查看更多错误码.");
        String filename = "/Users/yaoyuan/IdeaProjects/my/vaps/vap-study/src/main/java/tool/md/" + sheetName;
        File file = new File(filename);
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }

        Files.write(Paths.get(String.format("%s/%s.md", filename, appName)),
            buffer.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void onException(Exception e, AnalysisContext analysisContext) throws Exception {

    }

    @Override
    public void invokeHead(Map<Integer, CellData> map, AnalysisContext analysisContext) {
        apiMap.add(
            map.entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, entry -> (String)entry.getValue().getStringValue())));
    }

    @Override
    public void invoke(Map<Integer, String> o, AnalysisContext analysisContext) {
        apiMap.add(o);
    }

    @Override
    public void extra(CellExtra cellExtra, AnalysisContext analysisContext) {

    }

    @SneakyThrows
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        String sheetName = analysisContext.readSheetHolder().getSheetName();
        String apiName = apiMap.get(0).get(1);
        StringBuffer buffer = new StringBuffer(String.format("# %s", apiName));
        apiMap.remove(0);
        Boolean isReturn = false;
        for (Map<Integer, String> o : apiMap) {
            String head = Optional.ofNullable(o.get(0)).orElse("");
            String name = Optional.ofNullable(o.get(1)).orElse("");
            String type = Optional.ofNullable(o.get(2)).orElse("");
            String isRequired = Optional.ofNullable(o.get(3)).orElse("");
            String desc = Optional.ofNullable(o.get(4)).orElse("").replace("\n", "<br>");
            String example = Optional.ofNullable(o.get(5)).orElse("").replace("\n", "<br>");

            if (Strings.isNullOrEmpty(head) && head.contains("API名称")) {
                addEnd(buffer, apiName, sheetName);
                System.out.println(buffer);
                buffer = new StringBuffer();
                apiName = name;
                buffer = new StringBuffer();
                buffer.append("# ");
                buffer.append(name);
                continue;
            }
            if (Strings.isNullOrEmpty(head) && head.contains("参数列表")) {
                isReturn = false;
                buffer.append("\n");
                buffer.append("\n");
                buffer.append("## 请求参数\n"
                    + "\n"
                    + "| 名称 | 类型 | 是否必选 | 示例值 | 描述 |\n"
                    + "| ---- | ---- | -------- | ------ | ---- |\n");
                continue;
            }
            if (Strings.isNullOrEmpty(head) && head.contains("返回值")) {
                isReturn = true;
                buffer.append("\n");
                buffer.append("\n");
                buffer.append("## 返回数据\n"
                    + "\n"
                    + "| 名称 | 类型 | 示例值 | 描述 |\n"
                    + "| ---- | ---- | -------- | ------ |\n");
                continue;
            }
            if (Strings.isNullOrEmpty(head) && !head.contains("说明")) {
                buffer.append("\n");
                buffer.append("### ");
                buffer.append(head);
                buffer.append("\n");
                buffer.append("\n");
                if (isReturn) {
                    buffer.append("| 名称 | 类型 | 示例值 | 描述 |\n"
                        + "| ---- | ---- | -------- | ------ |\n");
                } else {
                    buffer.append("| 名称 | 类型 | 是否必选 | 示例值 | 描述 |\n"
                        + "| ---- | ---- | -------- | ------ | ---- |\n");
                }
                continue;
            }
            if (Strings.isNullOrEmpty(head)) {
                if (isReturn) {
                    buffer.append(
                        String.format("| %s | %s | %s | %s |", name, type, example, desc));
                    buffer.append("\n");
                } else {
                    buffer.append(String
                        .format("| %s | %s | %s | %s | %s |", name, type, isRequired, example, desc));
                    buffer.append("\n");
                }
                continue;
            }
        }
        addEnd(buffer, apiName, sheetName);
        System.out.println(buffer);

    }

    @Override
    public boolean hasNext(AnalysisContext analysisContext) {
        return true;
    }
}