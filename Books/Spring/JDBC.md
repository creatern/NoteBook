# JdbcTemplate

```xml
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-jdbc</artifactId>
```

| 方法   | 说明       |
| ------ | ---------- |
| query  | 查询       |
| update | 写入、更新 |

- GeneratedKeyHolder

## JdbcOperations

# JDBC


```xml
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-jdbc</artifactId>
```

| 领域类注解 | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| @Id        | 对象的唯一标识属性。<br />若没有唯一标识字段则不用设置。     |
| @Table     | 可选，默认基于领域类的名称映射到数据库的表（TacoOrder--Taco_Order）。<br />可显式指定表名（类）。 |
| @Column    | 可选，默认根据属性名自动映射到数据库的列（ccCVV--cc_cvv）。<br />可显式指定字段名。 |

- Persistable

