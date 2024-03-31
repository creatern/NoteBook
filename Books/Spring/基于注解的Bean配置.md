# @Componnet

<table>
    <tr>
        <td width="10%">@Component</td>
        <td width="90%">将类标识为普通的组件</td>
    </tr>
    <tr>
        <td>@Service</td>
        <td>将类标识为服务层的服务Service</td>
    </tr>
    <tr>
        <td>@Repository</td>
        <td>将类标识为数据层的数据仓库（DAO，Data Access Object）</td>
    </tr>
    <tr>
        <td>@Controller</td>
        <td>将类标识为Web层的Web控制器，并针对REST服务设置了@RestController</td>
    </tr>
</table>

```xml
<bean id="userDao" class="com.zjk.dao.impl.UserDaoImpl"></bean>
<bean class="com.zjk.dao.impl.UserDaoImpl"></bean>
```

```java
applicationContext.getBean("com.zjk.dao.impl.UserDaoImpl")
```

```java
@Component
public class UserDaoImpl implements UserDao {
}
```

```java
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
UserDaoImpl userDao = applicationContext.getBean("userDaoImpl", UserDaoImpl.class);
System.out.println(userDao);
```

# @PostConstruct、@PreDestory 初始化、销毁

- Bean实例化后，可以执行指定的初始化方法完成一些初始化的操作；Bean销毁前也可以执行指定的销毁方法完成一些操作。

> ClassPathXmlApplicationContext类的close()方法关闭容器时会销毁其中的Bean。

> Bean的销毁不一定调用Bean的销毁方法：有可能在Spring容器关闭之后，还未来得及调用Bean的销毁方法，尽管如此Bean还是被销毁了。

| 注解 | @PostConstruct | @PreDestory |
| ---- | -------------- | ----------- |
| 位置 | 方法           | 方法        |
| 标注 | 初始化方法     | 销毁方法    |

> @PostConstruct和 @PreDestroy注解位于javax.annotation包，需要引入javax.annotation-api依赖。

```java
public class UserDaoImpl implements UserDao {
    @PostConstruct
    public void init(){ System.out.println("初始化方法..."); }
    @PreDestory
    public void destroy(){ System.out.println("销毁方法..."); }
}
```
