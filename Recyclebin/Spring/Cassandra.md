- Cassandra：分布式、高性能、始终可用、最终一致、列分区存储的NoSQL数据库。Cassandra处理写入表中的数据行，这些数据被分区到一对多的分布式节点。

```xml
<!--非反应式-->
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-cassandra</artifactId>
```

| spring.data.cassandra设置 | 选项                                                         |
| ------------------------- | ------------------------------------------------------------ |
| schema-action             | none：默认，不采取任何措施。<br />recreate：应用启动时，所有表和用户定义类型都被废弃并重建。 |
| local-datacenter          | 本地数据中心名称，以设置负载均衡策略。<br />单节点：datacenter1。 |
| contact-points            | 默认设置为localhost。<br />设置主机列表，则尝试每个联系点，直到有一个连接成功。 |
| port                      | 默认监听9042端口。                                           |
| username<br />password    | 用户名<br />密码                                             |

| 注解             | 说明                                                         |
| ---------------- | ------------------------------------------------------------ |
| @Table           | 表映射                                                       |
| @Column          | 列映射                                                       |
| @PrimaryKey      | 分区键、默认排序的集群键                                     |
| @PrimaryColumn   | PrimaryKeyType.PARTITION：分区键<br />PrimaryKeyType.CLUSTERED：集群键（搭配ordering） |
| @UserDefinedType | 用户自定义类型                                               |
