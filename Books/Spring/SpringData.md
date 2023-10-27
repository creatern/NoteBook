# Spring Data

> ```xml
><!--H2 DB-->
> <groupId>com.h2database</groupId>
> <artifactId>h2</artifactId>
> <scope>runtime</scope>
> ```

| 自动脚本文件 | 说明                                                         |
| ------------ | ------------------------------------------------------------ |
| schema\.sql  | src/main/resources/schema\.sql。<br />模式定义，若应用的根路径下存在该文件，则应用启动时会基于数据库执行该文件。 |
| data\.sql    | src/main/resources/data.sql。<br />预加载数据，同上，在数据源bean初始化时执行，适用于任何关系型数据库。 |

| 角色                                        | 类/接口           |
| ------------------------------------------- | ----------------- |
| pojo                                        | Xxx               |
| 存储库接口                                  | XxxRepository     |
| 存储库实现<br />（@Reposity、JdbcTemplate） | JdbcXxxRepository |

# Repository 存储库

- 基于存储库规范接口自动创建存储库，而不需要编写其实现类。

```java
Xxx extends Repository<持久化对象类型,持久化对象ID类型>{}
```

- CrudRepository：为存储库规范接口提供一些常用的操作。

> 持久化对象对应的类（领域类）。

> 数据预加载：CommandLineRunner、ApplicationRunner接口，对关系型、非关系型数据库均有效。

## 自定义查询

- Spring Data在生成存储库实现时，检查存储库接口的所有方法，解析方法的名称，并基于被持久化的对象来试图推测方法的目的（DSL 领域特定语言）。持久化的细节都是通过存储库方法的签名实现的。

> Spring Data会忽略大多数的主题单词。

```java
public Interface TacoOrder extends CrudRepository{
    //动词+[主题]+关键词By+断言
    findByDeliveryZip(String deliveryZip);//动词find、主题暗含TacoOrder、关键词By、断言DeliveryZip。
}
```

| 方法签名的操作符 | 说明 |
| ---------------- | ---- |
|                  |      |

| 注解 | @Query                                         |
| ---- | ---------------------------------------------- |
| 位置 | 查询方法                                       |
| 作用 | 指明方法调用时执行的查询，而不是根据方法签名。 |
| 参数 | SQL查询语句<br />（JPA中可以使用JPA查询）      |

# 使用方式

## [JDBC](./JDBC.md)

## [JPA](./JPA.md)


## [Cassandra](./Cassandra.md)

## [MongoDB](./MongoDB.md)

## [MyBatis](./MyBatis.md)
