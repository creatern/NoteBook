# @SpringBootApplication

- `@SpringBootApplication` SpringBoot启动类。

```java
@SpringBootApplication
public class BootStudyApplication {

    public static void main(String[] args) {
        //启动相应的SpringBoot容器之前可以对容器进行一系列的设置
        //创建Spring应用上下文：配置类、命令行参数。
        SpringApplication.run(BootStudyApplication.class, args);
    }
}
```

| 内含注解                 | 说明             |
| ------------------------ | ---------------- |
| @EnableAutoConfiguration | 自动配置         |
| @SpringBootConfiguration | SpringBoot配置类 |
| @ComponentScan           | 组件扫描         |

**自动配置**

- @EnableAutoConfiguration 、@SpringBootApplication

```java
CONDITIONS EVALUATION REPORT  //debug
Positive matches:  //生效的自动配置
Negative matches:  //不生效的自动配置
Exclusions:        //指定不生效的自动配置
Unconditional classes: //无条件生效的自动配置
```

**指定不生效的自动配置**

```java
@SpringBootApplication(exclude = MultipartAutoConfiguration.class)
public class BootStudyApplication {}

Exclusions:
-----------
    org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration
```

# Spring Boot DevTools

> 应用部署后DevTools禁用自身。

| DevTools特性   | 说明                                                         |
| -------------- | ------------------------------------------------------------ |
| 应用自动重启   | DevTools启动时：应用程序加载到JVM的两个独立类加载器中（base、restart）。 |
| 浏览器自动刷新 |                                                              |
| 禁用模板缓存   |                                                              |
| 内置h2控制台   | http://localhost:8080/h2-console                             |

> LiveReload浏览器插件。

| 类加载器 | 属性            | 作用                                                         |
| -------- | --------------- | ------------------------------------------------------------ |
| base     | restart.exclude | 加载依赖的库的类加载器，代码变更时原封不动。                 |
| restart  | restart.include | 加载Java代码、属性文件、main路径<br>代码变更时触发重启：restart类加载器实例及其加载的内容都会被丢弃，并重新创建一个restart类加载器实例。 |

> 默认情况下：IDEA中打开的开发项目中所有的代码都会被restart类加载加载。

>  默认配置 DevToolsPropertyDefaultsPostProcessor。

```java
spring.devtools.addproperties=false //关闭默认配置
```

- `/resources/application.properties`：SpringBoot启动类默认读取该配置文件。
- 直接在文件修改 或者 `System.setProperty();`。

| spring.devtools.restart参数         | 说明                                                         |
| ----------------------------------- | ------------------------------------------------------------ |
| log-condition-evaluationdelta=false | 禁用condition evaluation日志                                 |
| additional-paths=目录               | 指定触发自动重启的目录<br>默认监控classpath除静态目录之外的文件的变化 |
| exclude=剔除的目录                  | 修改默认不触发重启目录配置                                   |
| enabled=false                       | 禁用自动重启                                                 |
| trigger-file=类路径文件             | 指定触发器文件<br>当设置了触发器文件后：只有该触发器文件被修改才会导致重启。 |

# CommandLineRunner、ApplicationRunner 预加载

- CommandLineRunner、ApplicationRunner：函数式接口。应用启动时，应用上下文中实现了这两个接口的bean都会执行其run()方法（应用上下文和所有bean装配完毕之后、所有其他功能执行之前）。

| 接口              | 区别：传递给run()的参数                                      |
| ----------------- | ------------------------------------------------------------ |
| CommandLineRunner | String类型的可变长度参数                                     |
| ApplicationRunner | ApplicationArguments参数<br />（提供访问已解析命令行参数的方法） |

```java
//通常在配置类中定义
@Bean
public CommandLineRunner testMethod() {
    return args -> {};
}
```

# 配置属性

<img src="../../pictures/Spring-SpringBoot-属性源-数据源.drawio.svg" width="600"/> 

## 配置日志

- Spring Boot默认使用Logback配置日志，日志以INFO级别写入控制台。

| 配置方式        | 说明                                 |
| --------------- | ------------------------------------ |
| logback.xml     | src/main/resources路径下创建该文件。 |
| application.yml | loggin属性。                         |

## 自定义配置属性 @ConfigurationProperties

| 注解 | @ConfigurationProperties                                     |
| ---- | ------------------------------------------------------------ |
| 位置 | 类（通常设置一个类专门持有配置属性）                         |
| 属性 | prefix：配置属性的前缀。                                     |
| 作用 | 从Spring环境中找到对应前缀的属性注入到持有者类中同名的属性。 |

- 配置属性的元数据（可选）：src/main/resources/META-INFO/additional-spring-configuration-metadata.json，为配置属性提供一个最小化的文档。

> Spring Boot的命名机制十分灵活，允许属性名出现不同的变种，如page-size等价于pageSize。
