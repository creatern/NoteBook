# Bean

## 工厂实例化

| 实例化方式                       | 说明                                                         |
| -------------------------------- | ------------------------------------------------------------ |
| 静态工厂<br />（factory-method） | 配置一个工厂Bean，提供一个静态方法用于生产Bean实例，而不需要配置被生产的Bean。 |
| 实例工厂<br />（@Bean）          | 工厂对象调用非静态方法，先配置工厂Bean，再配置目标Bean。<br />实例化Bean对象时，先实例化工厂Bean对象，再通过工厂Bean对象调用getXxx()来获取Bean对象。 |
| FactoryBean接口                  | 延迟实例化Bean。                                             |

## @Bean 实例工厂

| 注解 | @Bean    |
| ---- | -------- |
| 位置 | 工厂方法 |
| 作用 | 。       |
| 说明 |          |

<img src="../../pictures/Snipaste_2023-04-02_21-36-25.png" width="500"/> 

```java
@Component
public class OtherBean {
    @Bean
    public DruidDataSource dataSource(@Value("${jdbc.url}") String url,
                                      @Value("${jdbc.username}") String userName,
                                      @Value("1234") String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public UserDao userDao(UserDaoImpl userDao){
        return userDao;
    }

    @Bean
    //@Autowired 可以省略@Autowired 
    public UserDao userService(@Qualifier("userDaoImpl2") UserDao dao) {
        //由于UserDao类型的Bean实例存在多个，需要使用@Qualifier指定注入的beanName
        return dao;
    }
}
```

## FactoryBean接口

```java
// 实现FactoryBean接口，再交给Spring管理即可
public interface FactoryBean<T> {
    String OBJECT_TYPE_ATTRIBUTE = "factoryBeanObjectType";
    T getObject() throws Exception; //获得实例对象方法
    Class<?> getObjectType(); //获得实例对象类型方法
    default boolean isSingleton() {
        return true;
    }
}
```

- Spring容器创建时，FactoryBean被实例化并存储到singletonObjects中，但getObject() 方法尚未被执行，UserDaoImpl也没被实例化，当首次用到UserDaoImpl时，才调用getObject() 。此工厂方式产生的Bean实例不会存储到singletonObjects中，而是存储到factoryBeanObjectCache中，之后每次使用到userDao都从该缓存池中获取同一个userDao实例。

<img src="../../pictures/Snipaste_2023-04-02_21-55-12.png" width="500"/>   

```java
@Component
public class UserDaoFactoryBean implements FactoryBean<UserDao> {

    @Override
    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
```

