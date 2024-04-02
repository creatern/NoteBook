# property-placeholder标签

- `<context:property-placeholder location=""/>`

# @PropertySource

- <code>@PropertySource</code>加载外部properties资源配置。

```java
@Configuration
@ComponentScan
@PropertySource({"classpath:jdbc.properties","classpath:xxx.properties"})
public class ApplicationContextConfig {}
```

