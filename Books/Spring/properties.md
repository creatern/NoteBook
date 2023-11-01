# 配置日志

- Spring Boot默认使用Logback配置日志，日志以INFO级别写入控制台。

| 配置方式        | 说明                                 |
| --------------- | ------------------------------------ |
| logback.xml     | src/main/resources路径下创建该文件。 |
| application.yml | loggin属性。                         |

# 自定义配置属性 @ConfigurationProperties

<img src="../../pictures/Spring-SpringBoot-属性源-数据源.drawio.svg" width="600"/> 

| 注解 | @ConfigurationProperties                                     |
| ---- | ------------------------------------------------------------ |
| 位置 | 类（通常设置一个类专门持有配置属性）                         |
| 属性 | prefix：配置属性的前缀。                                     |
| 作用 | 从Spring环境中找到对应前缀的属性注入到持有者类中同名的属性。 |

- 配置属性的元数据（可选）：src/main/resources/META-INFO/additional-spring-configuration-metadata.json，为配置属性提供一个最小化的文档。

> Spring Boot的命名机制十分灵活，允许属性名出现不同的变种，如page-size等价于pageSize。
