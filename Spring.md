# Spring 基本

| 基本思想                              | 说明                                                         |
| ------------------------------------- | ------------------------------------------------------------ |
| [IoC 控制反转](./Books/Spring/IOC.md) | 将创造Bean的权利交给Spring进行管理                           |
| [DI 依赖注入](./Books/Spring/DI.md)   | 某个Bean的完整创建依赖于其他Bean（或普通参数）的注入<br />注入顺序（后面的覆盖前面的）：字面量/声明 \<\-\- 属性标注 \<\-\- setXxx()标注 |
| [AOP 面向切面](./Books/Spring/AOP.md) | 横向抽取方法（属性、对象等）思想，组装成一个功能性切面       |

[MVC框架](./Books/Spring/MVC.md)

# Spring Boot

| Initializr结构                                               | 说明             |
| ------------------------------------------------------------ | ---------------- |
| [@SpringBootApplication](./Books/Spring/SpringBootApplication.md) | SpringBoot启动类 |
| /static                                                      | 静态资源         |
| /templates                                                   | 模板文件         |
| application.properties<br />application.yml                  | 配置文件         |

- Starter依赖管理（spring\-boot\-starter）：Spring对依赖包的集中描述，本身不包含库代码，而是传递性地拉取其他库。
- [Spring DevTools](./Books/Spring/DevTools.md)：Spring开发环境工具，应用部署后DevTools禁用自身。
- [配置属性](./Books/Spring/properties.md)：Spring从各个属性源获取数据并注入到各个Bean。

| 依赖库                                     | 功能                                                         |
| ------------------------------------------ | ------------------------------------------------------------ |
| [Lombok](./Books/Spring/Lombok.md)         | 编译期自动生成类的方法（@Data），生成jar、war时自动剔除Lombok |
| [视图模板库](./Books/Spring/View.md)       | [Thymeleaf](./Books/Spring/Thymeleaf.md)                     |
| [validation](./Books/Spring/validation.md) | 校验（JSR-303）                                              |

> 模板缓存：模板默认只有第一次使用时解析，防止每次请求时多余的模板解析（对生产友好、不利于开发）。Spring Boot Devtools默认禁用模板缓存（应用部署后DevTools禁用自身）：spring.thymeleaf.cache。
>

# Spring Data

| 依赖库                                               | 说明                       |
| ---------------------------------------------------- | -------------------------- |
| [JDBC](./Books/Spring/JDBC.md)                       |                            |
| [JPA](./Books/Spring/JPA.md)                         | 适用于SQL和NoSQL           |
| [Cassandra](./Books/Spring/Cassandra.md)             |                            |
| [MongoDB](./Books/Spring/MongoDB.md)                 |                            |
| [Spring Data REST](./Books/Spring/SpringDataREST.md) | 基于储存库自动生成REST API |
