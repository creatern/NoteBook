[SQL to MongoDB Mapping Chart](https://www.mongodb.com/docs/manual/reference/sql-comparison/)

[MongoDB概述](../../attach/Docs/了解 MongoDB 看这一篇就够了 - 美码师 - 博客园.html)

```xml
<!--非反应式-->
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-mongodb</artifactId>

<!--嵌入式-->
<groupId>de.flapdoodle.embed</groupId>
<artifactId>de.flapdoodle.embed.mongo</artifactId>
```

> 默认，MongoDB监听27017端口。

```shell
docker run -p 27017:27017 -d mongo:latest
```

| spring.data.mongodb    | 说明               |
| ---------------------- | ------------------ |
| host                   | 默认localhost      |
| port                   | 默认27017          |
| username<br />password | 用户名<br />密码   |
| database               | 数据库名，默认test |

| 注解       | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| @Id        | 指定某个属性为文档的ID<br />注解任何Serializable类型的属性（String、Long ...） |
| @Document  | 将领域类声明为持久化的文档（默认类名第一个字母小写）<br />collect：指定集合名 |
| @Field     | 声明持久化文档中的该属性的字段名<br />可选择性的配置顺序     |
| @Transient | 声明该属性是否持久化                                         |

- 聚合根需要@Document、@Id标注；聚合成员（子文档）则不需要。
