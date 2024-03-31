- <code>@Primary</code>同类型注入优先，标注相同类型的Bean优先被使用权，通常搭配@Component/@Bean使用，标注该Bean的优先级更高。

1. 通过类型获取Bean（getBean(class)）、@Autowired根据类型进行注入时，会选用优先级更高的Bean。
2. @Qualifier指定名称注入，而不是优先级。

```java
@Repository
@Primary
public class UserDaoImpl implements UserDao{}
```

```java
@Bean
@Primary
public UserDao getUserDao01(){return new UserDaoImpl();}
```