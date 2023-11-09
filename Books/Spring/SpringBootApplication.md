# @SpringBootApplication

- @SpringBootApplication：SpringBoot启动类，默认所在包为扫描路径。

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
