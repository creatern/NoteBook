# @Value 普通数据

- 普通数据：基本数据类型、String。

| 注解 | @Value                                                       |
| ---- | ------------------------------------------------------------ |
| 位置 | 属性、形参、方法<br />参数列表内对单个参数注解，方法上对所有参数注解。 |
| 作用 | 对普通数据传值注入                                           |

```java
//最终username属性为tom。
@Component
public class UserDaoImpl implements UserDao {
 @Value("zjk")
 private String username;

 @Value("tom")
 public void setUsername(String username) {
     this.username = username;
 }
}
```

# @Autowired 自动装配

| 注解 | @Autowired                                                   |
| ---- | ------------------------------------------------------------ |
| 位置 | 属性、形参、方法<br />参数列表内对单个参数注解，方法上对所有参数注解。 |
| 说明 | 优先根据类型自动装配。<br />从Spring容器中匹配 setXxx()、id注入。 |

> @Autowired用于属性注入：
>
> 隐式自动装配：只有一个构造器的，Spring会隐式地通过该构造器的参数应用依赖的自动装配。
> 若具有多个构造器，可在指定的构造器上标注@Autowired。

| 需要注入的Bean数量 | @Autowired说明                                               |
| :----------------- | :----------------------------------------------------------- |
| 1                  | 注入类型匹配的Bean。<br />若同一类型的Bean实例有多个，会尝试通过被注入属性的名称进行二次匹配；如果不存在匹配的beanName，则报错。 |
| n                  | 将通过类型匹配的Bean实例都注入到集合中。                     |

```java
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDaoImpl;
}
```

# @Qualifier 指定名称注入

| 注解 | @Qualifier                                                   |
| ---- | ------------------------------------------------------------ |
| 位置 | 属性、形参、方法<br />参数列表内对单个参数注解，方法上对所有参数注解。 |
| 说明 | Bean类型匹配。<br />匹配setXxx(Yyy 参数)的数据类型Yyy，在容器中查找class的类型是Yyy的。<br />同一类型只能匹配一个Bean，匹配出多个相同Bean类型时（class相同包括继承、实现），报错。 |
| 搭配 | 搭配@Autowired根据名称注入Bean实例。                         |

```java
@Service
public class UserServiceImpl implements UserService {
    //@Autowired 默认使用
    @Qualifier("userDaoImpl")
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    //@Autowired 默认使用
    @Qualifier("userDaoImpl2")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
```

# @Resource

> @Resource注解存在于 javax.annotation 包中，Spring对其进行解析。

| 注解 | @Resource                                                    |
| ---- | ------------------------------------------------------------ |
| 位置 | 属性、形参、方法<br />参数列表内对单个参数注解，方法上对所有参数注解。 |
| 说明 | 不指定名称参数name-->根据类型注入。<br />指定名称参数name-->根据名称注入。 |
| 对比 | 当存在多个相同类型的Bean实例时，不会像@Autowired一样报错。   |

```java
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    @Resource(name="userDaoImpl2")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
```

# @PropertySource 配置文件资源加载

| 注解 | @PropertySource                |
| ---- | ------------------------------ |
| 位置 | 配置类                         |
| 说明 | 加载properties文件到Spring容器 |

```java
@Configuration
@PropertySource("classpath:jdbc.properties")
public class SpringConfig{}
```

- @Value在载入properties文件后，SpEL表达式注入properties文件中的属性。

```java
@Component
public class UserDaoImpl implements UserDao {
    @Value("${jdbc.username}")
    private String username;

    public String getUsername() {
        return username;
    }
}
```
