# AOP 面向切面

- AOP是横向抽取方法（属性、对象等）思想，组装成一个功能性切面。

<table>
	<thead>
		<tr>
			<th width="10%" align="center">编程思想</th>
			<th width="90%">描述</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center">OOP</td>
			<td>纵向对一个事物的抽象</td>
		</tr>
		<tr>
			<td align="center">AOP</td>
			<td>横向的对不同事物的抽，属性与属性、方法与方法、对象与对象都可以组成一个切面</td>
		</tr>
	</tbody>
</table>
<img src="../../pictures/Snipaste_2023-04-01_11-24-45.png" width="700"/> 

<table>
    <caption>AOP相关概念</caption>
	<thead>
		<tr>
			<th width="12%">概念</th>
			<th width="10%">名词</th>
			<th width="78%">解释</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>目标对象</td>
			<td>Target</td>
			<td>被增强方法</td>
		</tr>
		<tr>
			<td>代理对象</td>
			<td>Proxy</td>
			<td>对目标对象进行增强后的对象，即：客户端实际调用的对象</td>
		</tr>
		<tr>
			<td>连接点</td>
			<td>Joinpoint</td>
			<td>目标对象可以被增强的方法</td>
		</tr>
		<tr>
			<td>切入点</td>
			<td>Pointcut</td>
			<td>目标对象实际被增强的方法</td>
		</tr>
		<tr>
			<td>通知/增强</td>
			<td>Advice</td>
			<td>增强部分的代码逻辑</td>
		</tr>
		<tr>
			<td>切面</td>
			<td>Aspect</td>
			<td>增强和切入点的组合</td>
		</tr>
		<tr>
			<td>织入</td>
			<td>Weaving</td>
			<td>将通知和切入点组合动态组合的过程</td>
		</tr>
	</tbody>
</table>

<img src="../../pictures/Snipaste_2023-04-10_17-27-26.png" width="1200"/> 

<img src="../../pictures/Snipaste_2023-04-01_11-27-35.png" width="600"/> 

# Advice 通知

## @Pointcut 切点表达式抽取

| 注解 | @Pointcut("execution") |
| ---- | ---------------------- |
| 位置 | 通知类中定义的方法     |
| 作用 | 标注切点表达式         |

```java
//execution 切入表达式 被增强的目标
execution([访问修饰符] 返回值类型 包名.类名.方法名(参数))
```

- 访问修饰符可以省略不写；

- 返回值类型、某一级包名、类名、方法名 可以使用 * 表示任意；

- 包名与类名之间使用单点 . 表示该包下的类，使用双点 .. 表示该包及其子包下的类；

- 参数列表可以使用两个点 .. 表示任意参数。

```java
//表示访问修饰符为public、无返回值、在com.itheima.aop包下的TargetImpl类的无参方法show
execution(public void com.itheima.aop.TargetImpl.show())
    
//表述com.itheima.aop包下的TargetImpl类的任意方法
execution(* com.itheima.aop.TargetImpl.*(..))
    
//表示com.itheima.aop包下的任意类的任意方法
execution(* com.itheima.aop.*.*(..))
    
//表示com.itheima.aop包及其子包下的任意类的任意方法
execution(* com.itheima.aop..*.*(..))
    
//表示任意包中的任意类的任意方法
execution(* *..*.*(..))
```

- 通知中使用如：@Before("通知类.标记方法()") 的形式获取切入。

```java
@Component
public class UserServiceAdvice{
    @Pointcut("execution(* com.zjk.service.*.*(..))")
    public void userServiceAdvicePointcut(){};

    @Before("userServiceAdvicePointcut()")
    public void before(){
        System.out.println("before");
    }
}
```

## @Aspect 织入

| 注解     | @Aspect                     |
| -------- | --------------------------- |
| 位置     | 增强类                      |
| 说明     |                             |
| **注解** | **@EnableAspectJAutoProxy** |
| 位置     | 配置类                      |
| 说明     | 开启AOP自动代理             |

| 通知类型     | 注解                                          |
| :----------- | :-------------------------------------------- |
| 前置通知     | @Before("切点表达式")                         |
| 后置通知     | @AfterReturning("切点表达式")                 |
| 环绕通知     | @Around("切点表达式")                         |
| 异常抛出通知 | @Throwable(pointcut="切点表达式" throwing="") |
| 最终通知     | @Afters("切点表达式")                         |

```java
@Configuration
@ComponentScan("com.zjk")
@EnableAspectJAutoProxy
public class ApplicationContextConfig {}
```

```java
@Component
@Aspect
public class UserServiceAdvice{
    @Pointcut("execution(* com.zjk.service.impl.*.*(..))")
    public void userServicePointcut() {
    }

    @Before("userServicePointcut()")
    public void before() {
        System.out.println("前置增强");
    }

    @Around("userServicePointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前增强");
        joinPoint.proceed();
        System.out.println("环绕后增强");
    }

    @AfterThrowing(pointcut = "userServicePointcut()", throwing = "e")
    public void afterThrowable(Throwable e) {
        e.printStackTrace();
    }
}
```

## 获取增强的Proxy对象

- xml配置：spring-aop jar包的META-INF下的spring.handlers和sprin.schemas

- AopNamespaceHandler：spring.handlers

```xml
http\://www.springframework.org/schema/aop=org.springframework.aop.config.AopNamespaceHandler
```

<img src="../../pictures/Snipaste_2023-04-12_14-26-57.png" width="1000"/> 

- **wrapIfNecessary()** 方法最终返回的就是一个Proxy对象：`return this.wrapIfNecessary(bean, beanName, cacheKey);`

## AOP自动代理

- `<aop:aspectj-autoproxy/>`

<img src="../../pictures/Snipaste_2023-04-13_00-13-28.png" width="1200"/> 

- @EnableAspectJAutoProxy

<img src="../../pictures/Snipaste_2023-04-13_00-19-49.png" width="500"/> 

<img src="../../pictures/2023_04_13_0_20.png" width="1000"/> 

# 声明式事务控制

> JDBC使用connection对事务进行控制；MyBatis使用SqlSession对事务进行控制。当切换数据库访问技术时，事务控制的方式总会变化，Spring提供了统一的控制事务的接口。

- 事物控制：保证事务的原子性。

| 事务控制方式   | 解释                                                         |
| :------------- | :----------------------------------------------------------- |
| 编程式事务控制 | Spring提供了事务控制的类和方法，使用编码的方式对业务代码进行事务控制，事务控制代码和业务操作代码耦合到了一起，开发中不使用。 |
| 声明式事务控制 | Spring将事务控制的代码封装，对外提供了xml和注解配置方式，通过配置的方式完成事务的控制，可以达到事务控制与业务操作代码解耦合，开发中使用。 |

> **编程式事务控制**
>
> **Spring事务编程相关的类**
>
> | 事务控制相关类                            | 解释                                                         |
> | :---------------------------------------- | :----------------------------------------------------------- |
> | 平台事务管理器 PlatformTransactionManager | 接口标准，实现类都具备事务提交、回滚和获得事务对象的功能，不同持久层框架可能会有不同实现方案 |
> | 事务定义 TransactionDefinition            | 封装事务的隔离级别、传播行为、过期时间等属性信息             |
> | 事务状态 TransactionStatus                | 存储当前事务的状态信息，如果事务是否提交、是否回滚、是否有回滚点等 |
>
> spring-jdbc坐标已经引入的spring-tx坐标。
>
> **平台事务管理器**
>
> - MyBatis作为持久层框架时，使用的平台事务管理器实现是DataSourceTransactionManager。
>
> - Hibernate作为持久层框架时，使用的平台事务管理器是HibernateTransactionManager。
>
> **Mybatis平台事务管理器**
>
> - MyBatis使用的平台事务管理器： **DataSourceTransactionManager**
>
> <img src="../../pictures/Snipaste_2023-04-13_23-11-23.png" width="600"/> 
>
> - 需要注入的属性：
>
> ```java
> private DataSource dataSource;
> ```

## @Transactional 事务控制 

| 位置 | @Transactional                                               |
| ---- | ------------------------------------------------------------ |
| 类   | 该类下的所有方法都使用这注释的事务。                         |
| 方法 | 只对该方法使用该注释的事务。<br />优先级大于对类注释（就近原则）。 |

```java
@Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,readOnly = false,timeout = 5)
```

| 属性        | 说明                                                         |
| :---------- | :----------------------------------------------------------- |
| isolation   | 事务的隔离级别：解决事务并发问题                             |
| timeout     | 设置事务执行的超时时间，单位是秒。<br />如果超过该时间限制但事务还没有完成，则自动回滚事务，不再继续执行。<br />默认值是-1，即没有超时时间限制 |
| read-only   | 设置当前的只读状态。<br />如果是查询则设置为true，可以提高查询性能。<br />如果是DML（增删改）操作则设置为false。 |
| propagation | 设置事务的传播行为，主要解决是A方法调用B方法时，事务的传播方式问题的。<br />例如：使用单方的事务，还是A和B都使用自己的事务等 |

| isolation属性    | 指定事务的隔离级别，事务并发存在三大问题：脏读、不可重复读、幻读/虚读。<br />通过设置事务的隔离级别来保证并发问题的出现，常用的是READ_COMMITTED 和 REPEATABLE_READ。 |
| :--------------- | :----------------------------------------------------------- |
| DEFAULT          | 默认隔离级别，取决于当前数据库隔离级别。<br />例如MySQL默认隔离级别是REPEATABLE_READ |
| READ_UNCOMMITTED | A事务可以读取到B事务尚未提交的事务记录，不能解决任何并发问题。<br />安全性最低，性能最高。 |
| READ_COMMITTED   | A事务只能读取到其他事务已经提交的记录，不能读取到未提交的记录。<br />可以解决脏读问题，但是不能解决不可重复读和幻读。 |
| REPEATABLE_READ  | A事务多次从数据库读取某条记录结果一致。<br />可以解决不可重复读，不可以解决幻读。 |
| SERIALIZABLE     | 串行化，可以解决任何并发问题。<br />安全性最高，但是性能最低。 |

| propagation<br />事务传播行为 | 解释                                                         |
| :---------------------------- | :----------------------------------------------------------- |
| REQUIRED（默认值）            | A调用B，B需要事务。<br />如果A有事务，B就加入A的事务中，<br />如果A没有事务，B就自己创建一个事务。 |
| REQUIRED_NEW                  | A调用B，B需要新事务。<br />如果A有事务就挂起，B自己创建一个新的事务。 |
| SUPPORTS                      | A调用B，B有无事务无所谓。<br />A有事务就加入到A事务中，<br />A无事务B就以非事务方式执行。 |
| NOT_SUPPORTS                  | A调用B，B以无事务方式执行。<br />A如有事务则挂起。           |
| NEVER                         | A调用B，B以无事务方式执行。<br />A如有事务则抛出异常。       |
| MANDATORY                     | A调用B，B要加入A的事务中。<br />如果A无事务就抛出异常。      |
| NESTED                        | A调用B，B创建一个新事务。<br />A有事务就作为嵌套事务存在，<br />A没事务就以创建的新事务执行。 |

## @EnableTransactionManagemen 事务的自动代理

| 注解 | @EnableTransactionManagemen                                  |
| ---- | ------------------------------------------------------------ |
| 位置 | 配置类                                                       |
| 说明 | 事务的自动代理。<br />默认查找 **transactionManager** 命名的Bean。 |

```java
@Configuration
@MapperScan("com.zjk.mapper")
@PropertySource("classpath:jdbc.properties")
@ComponentScan("com.zjk")
@EnableTransactionManagement
public class ApplicationContextConfig {
    @Bean
    public DataSource dataSource(@Value("${jdbc.url}") String url,
                                 @Value("${jdbc.username}") String username,
                                 @Value("${jdbc.password}") String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    //平台事务管理器
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
```

```java
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public AccountMapper getAccountMapper() {
        return accountMapper;
    }

    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    //被事务控制的方法
    @Transactional
    public void trancate(Integer money, String fromAccountName,String toAccountName) {
        accountMapper.decrMoney(money, fromAccountName);
        accountMapper.incrMoney(money, toAccountName);
    }
}
```
