package com.vanadis.lang.code;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vanadis.lang.code.object.MethodObject;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-06-28 15:14
 */
public class VapCodeGenerator {

    private String authName = "遥远";

    /**
     * 项目根目录
     */
    private String userDir = System.getProperty("user.dir");

    /**
     * 子项目路径
     */
    private String codePath = "/lang";

    private String packageName = "com.vanadis.CodeGenerator";

    /**
     * 生成信息：类名[类注释]{返回类型 方法名,返回类型 方法名}
     */
    private String[] fileNames = {"aa[测试]{Long selectById,String selectByName}", "bb"};

    private String targetPath = userDir + codePath + "/src/main/java/" + packageName.replace(".", "/");

    /**
     * 模版路径
     */
    private String templateDir = "/lang/src/main/resources/templates";

    /**
     * 模版路径（全局）
     */
    private String templatePath = userDir + templateDir;

    private VelocityEngine engine = newVelocityEngine();

    /**
     * 需要生成的表名
     */
    private String tableNames = "dt_user";

    /**
     * 数据库配置
     */
    private static DataSourceConfig dsc = new DataSourceConfig(){};

    static {
        dsc.setUrl("jdbc:oracle:thin:@172.16.8.121:1521:dtstack");
        dsc.setDriverName("oracle.jdbc.driver.OracleDriver");
        dsc.setUsername("mobile");
        dsc.setPassword("abc123");
    }

    public static void main(String[] args) throws Exception {
        new VapCodeGenerator()
//                .setCodePath(scanner("项目路径"))
//                .setPackageName(scanner("包名(com.xxx.xxx)"))
//                .setFileNames(scanner("文件名，多个英文逗号分割").split(","))
                .runService();
//                .runEntity();
    }

    /**
     * 自动生成业务代码
     *
     * @return
     * @throws Exception
     */
    private VapCodeGenerator runService() throws Exception {

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
        return this;
    }

    /**
     * 创建模版引擎
     *
     * @return
     */
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

        // 判断是否有注释
        if (fileName.contains("[") && fileName.contains("]")) {
            String description = fileName.substring(fileName.indexOf("[") + 1, fileName.indexOf("]"));
            context.put("description", description);
            classNameIndex = fileName.indexOf("[") < classNameIndex ? fileName.indexOf("[") : classNameIndex;
        }

        // 判断是否有方法
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

    /**
     * 自动生成实体代码
     *
     * @return
     */
    private VapCodeGenerator runEntity() {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 数据源配置
        mpg.setDataSource(dsc);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = userDir + codePath;
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(authName);
        gc.setOpen(false);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);


        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageName);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        templateConfig.setService("");
        templateConfig.setServiceImpl("");
        templateConfig.setController("");

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude(tableNames);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

        return this;
    }

    public VapCodeGenerator setAuthName(String authName) {
        this.authName = authName;
        return this;
    }

    public VapCodeGenerator setUserDir(String userDir) {
        this.userDir = userDir;
        return this;
    }

    public VapCodeGenerator setCodePath(String codePath) {
        this.codePath = codePath;
        return this;
    }

    public VapCodeGenerator setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public VapCodeGenerator setFileNames(String[] fileNames) {
        this.fileNames = fileNames;
        return this;
    }

    public VapCodeGenerator setTargetPath(String targetPath) {
        this.targetPath = targetPath;
        return this;
    }

    public VapCodeGenerator setTemplateDir(String templateDir) {
        this.templateDir = templateDir;
        return this;
    }

    public VapCodeGenerator setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
        return this;
    }

    public VapCodeGenerator setEngine(VelocityEngine engine) {
        this.engine = engine;
        return this;
    }

    public VapCodeGenerator setTableNames(String tableNames) {
        this.tableNames = tableNames;
        return this;
    }

    public static void setDsc(DataSourceConfig dsc) {
        VapCodeGenerator.dsc = dsc;
    }
}
