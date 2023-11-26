- Spring Data REST：基于储存库自动生成REST API，尝试使用相关实体类的复数来创建端点。Spring Data REST暴露了一个主资源，包含所有端点的链接。

```xml
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-rest</artifactId>
```

```properties
# 设置API的基础路径
spring.data.rest.base-path=/
```

> Spring HATEOAS为Spring MVC控制器的响应中添加超链接，提供通用的支持。

| 注解 | @RestResource                                    |
| ---- | ------------------------------------------------ |
| 位置 | 类注解                                           |
| 作用 | 为实体类提供指定的关系名和路径                   |
| 参数 | rel：关系名<br />path：路径（基于base-path拼接） |

- 主资源上的所有链接都提供了可选的page、size、sort参数，默认请求集合资源返回首页的20个条目。（page从0开始-首页）

| 消费方式     | 说明                                                         |
| ------------ | ------------------------------------------------------------ |
| RestTemplate | Spring提供的简单、同步REST客户端。                           |
| Traverson    | 对RestTemplate的包装，由Spring HATEOAS提供的支持超链接、同步的REST客户端。 |
| WebClient    | 反应式、异步REST客户端。                                     |