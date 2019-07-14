package com.vanadis.lang.code;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-06-28 15:14
 */
public class CodeGenerator {

    private String authName = "遥远";

    private String userDir = System.getProperty("user.dir");

    private String codePath = userDir + "/lang";

    private String packageName = "com.vanadis.test";

    private String[] fileNames = {"aa[测试]{Long selectById,String selectByName}", "bb"};

    private String targetPath = codePath + "/src/main/java/" + packageName.replace(".", "/");

    private String templateDir = "/lang/src/main/resources/templates";

    private String templatePath = userDir + templateDir;

    private VelocityEngine engine = newVelocityEngine();

    public static void main(String[] args) throws Exception {
        new CodeGenerator()
//                .setCodePath(scanner("项目路径"))
//                .setPackageName(scanner("包名(com.xxx.xxx)"))
//                .setFileNames(scanner("文件名，多个英文逗号分割").split(","))
                .run();
    }

    private void run() throws Exception {

        for (String fileName : fileNames) {

            //参数处理
            VelocityContext context = context(fileName);

            //根据模版生成文件
            for (Map.Entry<String, String> template : templateMap((String) (context.get("className"))).entrySet()) {
                Template t = engine.getTemplate(template.getKey(), "UTF-8");
                String targetFile = targetPath + "/" + template.getValue();
                createFile(t, context, targetFile);
            }
        }

    }

    private VelocityEngine newVelocityEngine() {
        Properties pro = new Properties();
        pro.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        pro.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        pro.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, templatePath);
        return new VelocityEngine(pro);
    }

    /**
     * 参数处理
     *
     * @param fileName
     * @return
     */
    private VelocityContext context(String fileName) {

        VelocityContext context = new VelocityContext();

        int classNameIndex = fileName.length();

        if (fileName.contains("[") && fileName.contains("]")) {
            String description = fileName.substring(fileName.indexOf("[") + 1, fileName.indexOf("]"));
            context.put("description", description);
            classNameIndex = fileName.indexOf("[") < classNameIndex ? fileName.indexOf("[") : classNameIndex;
        }

        if (fileName.contains("{") && fileName.contains("}")) {
            List<MethodObject> methods = Lists.newArrayList();
            String[] methodStrings = fileName.substring(fileName.indexOf("{") + 1, fileName.indexOf("}")).split(",");
            for (String methodString : methodStrings) {
                String[] methodParams = methodString.split(" ");
                MethodObject methodObject = new MethodObject();
                methodObject.setReturnClass(methodParams[0]);
                methodObject.setMethodName(methodParams[1]);
                methods.add(methodObject);
            }
            context.put("methods", methods);
            classNameIndex = fileName.indexOf("{") < classNameIndex ? fileName.indexOf("{") : classNameIndex;
        }

        String className = fileName.substring(0, 1).toUpperCase() + fileName.substring(1, classNameIndex);

        String sClassName = className.substring(0, 1).toLowerCase() + className.substring(1);

        context.put("className", className);
        context.put("sClassName", sClassName);
        context.put("authName", authName);
        context.put("packageName", packageName);
        context.put("dateTime", LocalDateTime.now().format((DateTimeFormatter.ISO_DATE_TIME)));

        return context;

    }

    /**
     * 模版
     *
     * @param className
     * @return
     */
    private Map<String, String> templateMap(String className) {
        Map<String, String> map = Maps.newHashMap();
        map.put("ControllerTemplate.java.vm", "controller/" + className + "Controller.java");
        map.put("ServiceTemplate.java.vm", "service/" + className + "Service.java");
        map.put("MapperTemplate.java.vm", "mapper/" + className + "Mapper.java");
        return map;
    }

    /**
     * 创建文件
     *
     * @param template
     * @param context
     * @param targetFile
     * @throws Exception
     */
    private static void createFile(Template template, VelocityContext context, String targetFile) throws Exception {

        File file = new File(targetFile);
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (!file.exists())
            file.createNewFile();

        FileOutputStream outStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outStream, StandardCharsets.UTF_8);
        BufferedWriter sw = new BufferedWriter(writer);
        template.merge(context, sw);
        sw.flush();
        sw.close();
        outStream.close();
        System.out.println("成功生成Java文件:" + targetFile);
    }

    public static String scanner(String tip) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!Strings.isNullOrEmpty(ipt)) {
                return ipt;
            }
        }
        throw new Exception("输入错误");
    }

    public CodeGenerator setAuthName(String authName) {
        this.authName = authName;
        return this;
    }

    public CodeGenerator setUserDir(String userDir) {
        this.userDir = userDir;
        return this;
    }

    public CodeGenerator setCodePath(String codePath) {
        this.codePath = codePath;
        return this;
    }

    public CodeGenerator setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public CodeGenerator setFileNames(String[] fileNames) {
        this.fileNames = fileNames;
        return this;
    }

    public CodeGenerator setTargetPath(String targetPath) {
        this.targetPath = targetPath;
        return this;
    }

    public CodeGenerator setTemplateDir(String templateDir) {
        this.templateDir = templateDir;
        return this;
    }

    public CodeGenerator setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
        return this;
    }
}
