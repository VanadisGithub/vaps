# Java技巧

1.SPI实现:加载接口的不同实现类
ServiceLoaderTest

2.函数式编程：把方法向下传递给下层方法调用（可以带service向下）
FunctionTest

# Spring技巧

## 加载不同的配置文件application-xxx.properties
spring.profiles.active=xxx

## 自动加载引入jar的autoConfig
1.start 路径：/src/main/resources/META-INF
org.springframework.boot.autoconfigure.EnableAutoConfiguration=
2.扫描路径中的AutoConfiguration
@EnableAutoConfiguration 

## 排除
spring.autoconfigure.exclude=

## 是否加载bean
@ConditionalOnProperty(name = "xxx.enabled", havingValue = "true")

# Guava技巧

## spi的注入
@AutoService

# Maven技巧

1.通过profiles引入不同的依赖和参数