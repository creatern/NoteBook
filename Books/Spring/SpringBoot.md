# Spring Boot 基础

> Tomcat作为Spring Boot的一部分运行。

## Initializr

| Initializr结构                              | 说明                                           |
| ------------------------------------------- | ---------------------------------------------- |
| `@SpringBootApplication`                    | SpringBoot启动类。<br />默认所在包为扫描路径。 |
| `/static`                                   | 静态资源                                       |
| `/templates`                                | 模板文件                                       |
| application.properties<br />application.yml | 配置文件                                       |

- Starter依赖管理：Spring对依赖包的集中描述。本身不包含库代码，而是传递性地拉取其他库。

```xml
spring-boot-starter
```

## @SpringBootApplication

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

## Spring Boot DevTools

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

| 参数                                                        | 说明                                                         |
| ----------------------------------------------------------- | ------------------------------------------------------------ |
| spring.devtools.restart.log-condition-evaluationdelta=false | 禁用condition evaluation日志                                 |
| spring.devtools.restart.additional-paths=目录               | 指定触发自动重启的目录<br>默认监控classpath除静态目录之外的文件的变化 |
| spring.devtools.restart.exclude=剔除的目录                  | 修改默认不触发重启目录配置                                   |
| spring.devtools.restart.enabled=false                       | 禁用自动重启                                                 |
| spring.devtools.restart.trigger-file=类路径文件             | 指定触发器文件<br>当设置了触发器文件后：只有该触发器文件被修改才会导致重启。 |

## CommandLineRunner、ApplicationRunner 预加载

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

# Lombok

- Lombok：编译期自动生成类的方法（@Data）。（生成jar、war时自动剔除Lombok）

> IDE中需要安装相应的Lombok插件。

```xml
<groupId>org.projectlombok</groupId>
<artifactId>lombok</artifactId>
```

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <excludes>
                    <exclude>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

| 注解                              | 说明                                                         |
| --------------------------------- | ------------------------------------------------------------ |
| @Data                             | 自动为需要构造器、初始化方法...的创建对应方法。              |
| @Slf4j                            | 自动生成SLF4J Logger静态属性。                               |
| @AllArgsConstructor               | 所有属性的构造器                                             |
| @NoArgsConstructor(access, force) | 空参构造器<br /><br />access：权限修饰<br />force：设置默认值（final属性需要） |

# 视图模板库

- pom.xml中存在对应模板库依赖（Thymeleaf等）时，Spring Boot会探测到对应视图模板库并配置相应的Bean（Thymeleaf Bean等）。在`/classpath:/templates（src/main/resources/tempaltes）`编写模板即可。
- 控制器不能带有`@ResponseBody、@RestController`，由Controller方法的String返回值（视图名）进行页面跳转。

> @ResponseBody、@RestController（@Controller+@ResponseBody）返回值作为响应内容，而不是视图名。

- 模板默认只有第一次使用时解析，防止每次请求时多余的模板解析（对生产友好、不利于开发）。

> Spring Boot Devtools默认禁用模板缓存（应用部署后DevTools禁用自身）。

| 模板             | 启用/禁用缓存属性（默认ture）<br />application.properties |
| ---------------- | --------------------------------------------------------- |
| FreeMarker       | spring.freemarker.cache                                   |
| Groovy Templates | spring.groovy.template.cache                              |
| Mustache         | spring.mustache.cache                                     |
| Thymeleaf        | spring.thymeleaf.cache                                    |

## Thymeleaf

```html>
<!--html头部声明和使用Thymeleaf-->
<html xmlns:th="http://www.thymeleaf.org">
```

### EL表达式

| EL表达式 | 表达式                                                       |
| -------- | ------------------------------------------------------------ |
| `@{}`    | 链接资源<br />默认根目录为static                             |
| `${}`    | （Model中的）变量                                            |
| `*{}`    | 选定对象（th:object）而不是整个上下文评估表达式<br />若没有选定对象，则等同于`${}`。 |
| `#{}`    | 消息表达（文本外部化）：读取配置文件                         |

```html
<!--${}变量表达式-->

<!--普通字符串-->
<p th:text="${name}"></p>
<!--POJO类型 person(name,age)-->
<p th:text="${person.name}"></p>
<p th:text="${person['name']}"></p>
<p th:text="${person.getName()}"></p>

<!--List-->
<tr th:each="item:${userlist}">
    <td th:text="${item}"></td>
</tr>

<!--Map取值-->
<td th:text="${map.place}"></td>
<td th:text="${map.['place']}"></td>
<td th:text="${map.get('place')}"></td>

<!--Map遍历-->
<tr th:each="item:${map}">
    <td th:text="${item.key}"></td>
    <td th:text="${item.value}"></td>
</tr>
```

```html
<!--*{}选择变量表达式-->
<div th:object="${user}">
    <p>Name: <span th:text="*{name}">赛</span>.</p>
    <p>Age: <span th:text="*{age}">18</span>.</p>
    <p>Detail: <span th:text="*{detail}">好好学习</span>.</p>
</div>
```

```html
<!--#{}消息表达-->
<!--配置文件：/resources/test.properties-->
<!--application.properties中加入：test，直接使用配置文件中的键值对-->
<span th:text="#{tom.name}"></span>
```

### th标签

| 替换               | -                         |
| ------------------ | ------------------------- |
| th:id              | id                        |
| th:text            | 文本                      |
| th:utext           | html文本                  |
| th:src             | 资源                      |
| th:href            | 超链接                    |
| th:object          | 选定对象（搭配`*{}`使用） |
| th:value           | 值                        |
| **流程控制**       | **-**                     |
| th:if<br>th:unless | 判断                      |
| th:each            | 循环                      |
| **检验**           | **-**                     |
| th:errors          | 异常（搭配validation）    |
| **取值**           | **-**                     |
| th:field           | 输入域<br />#fields       |

```html
<!--List循环-->
<td th:each="user:${userList}" th:text="${user}"></td>

<!--Map循环-->
<table>
    <tr th:each="person:${personMapper}">
        <td th:text="${person.key}"></td>
        <td th:text="${person.value}"></td>
    </tr>
</table>
```

# validation 校验（JSR-303）

```xml
<groupId>org.springframwork.boot</groupId>
<artifactId>spring-boot-starter-validation</artifactId>
```

| 注解              | 位置 | 检验                                                         |
| ----------------- | ---- | ------------------------------------------------------------ |
| @Valid            | 形参 | 执行该对象（属性已存在以下检验）的检验。<br />如果检验错误，返回Errors对象到该方法的Errors参数<br />（errors# `Boolean hasErrors()`）。 |
| @NotNull          | 属性 | 非null                                                       |
| @NotBlank         | 属性 | 非空""                                                       |
| @Size             | 属性 | min、max：长度限制<br />message：提示信息                    |
| @CreditCardNumber | 属性 | 合法的信用卡号                                               |
| @Pattern          | 属性 | 正则<br />regexp                                             |
| @Digits           | 属性 | integer：整数位数上限<br />fraction：小数位数上限            |

```html
<!--Thymeleaf渲染检验信息-->
<label for="ccNumber">Create Card #: </label>
<input type="text" th:field="*{ccNumber}"/>
<span class = "validationError" 
      th:if="${#fields.hasErrors('ccNumber')}"
      th:errors="*{ccNumber}">CC Num Error</span>
<!--显示的错误信息：检验注解的message-->
```

# Spring Boot测试

## SpringBootTest 单元测试

```java
@SpringBootTest
class TacoCloudApplicationTests {

    @Test
    void contextLoads() {
    }

}
```

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class BookServiceTest {
    @Test
    public void testGetById(){}
}
```

## @WebMvcTest 测试

- @WebMvcTest：测试注解，在SpringMVC应用的上下文中执行。

```java
mockMvc.perform(MockHttpServletRequestBuilder).andExpect(MockHttpServletRequestBuilder);
```

| 类                            | 说明           |
| ----------------------------- | -------------- |
| MockMvc                       | 模拟MVC。      |
| MockHttpServletRequestBuilder | 模拟请求信息。 |
| MockMvcResultMatchers         |                |
