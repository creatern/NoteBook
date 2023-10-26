# MongoDB

## SQL to MongoDB

- MongoDB是基于分布式文件存储的数据库，所有存储在集合中的数据都是BSON格式（Binary JSON）。

| SQL      | MongoDB    | MongoDB说明                                                  |
| -------- | ---------- | ------------------------------------------------------------ |
| database | database   | 一个数据库包含多个集合                                       |
| table    | collection | 一个集合可以存放多个文档。 <br />集合的结构（schema）是动态的，不需要预先声明一个严格的表结构。<br />默认情况下 MongoDB 并不会对写入的数据做任何schema的校验。 |
| row      | document   | 一个文档由多个字段组成，并采用bson格式表示。                 |
| column   | field      | 字段的类型是固定的、区分大小写、并且文档中的字段也是有序的。 |

[SQL to MongoDB Mapping Chart](https://www.mongodb.com/docs/manual/reference/sql-comparison/)

[MongoDB概述](../../../attach/Docs/了解 MongoDB 看这一篇就够了 - 美码师 - 博客园.html)

# Cassandra

# Redis