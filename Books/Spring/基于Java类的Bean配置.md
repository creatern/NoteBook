# @Bean

- <code>@Bean</code>  将方法的返回值作为Bean实例注册到Spring容器中。

<table>
    <tr>
        <th width="15%">属性</th>
        <th width="15%">默认值</th>
        <th width="70%">描述</th>
    </tr>
    <tr>
        <td>value</td>
        <td>{}</td>
        <td>同name</td>
    </tr>
    <tr>
        <td>name</td>
        <td>{}</td>
        <td>Bean的名称，默认同方法名</td>
    </tr>
    <tr>
        <td>autowire</td>
        <td>Autowire.NO</td>
        <td>自动织入方式</td>
    </tr>
    <tr>
        <td>autowireCandicate</td>
        <td>true</td>
        <td>是否是自动织入的候选Bean</td>
    </tr>
    <tr>
        <td>initMethod</td>
        <td>""</td>
        <td>初始化方法名</td>
    </tr>
    <tr>
        <td>destroyMethod</td>
        <td>AbstractBeanDefinition.INFER_METHED</td>
        <td>销毁方法名</td>
    </tr>
</table>

# @Configuration 配置类

<table>
	<thead>
		<tr>
			<th width="10%" align="center"></th>
			<th width="45%" align="center">配置类 SpringConfig.class</th>
			<th width="45%" align="center">配置文件 beans.xml</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center">配置方式</td>
			<td align="center">@Configuration</td>
			<td align="center">&lt;beans&gt;</td>
		</tr>
		<tr>
			<td align="center">容器</td>
			<td align="center">AnnotationConfigApplicationContext</td>
			<td align="center">ClassPathXmlApplicationContext</td>
		</tr>
	</tbody>
</table>

```java
@Configuration
public class ApplicationContextConfig {}
```

```java
//注解方式加载配置文件
AnnotationConfigApplicationContext applicationContext =  new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
```

<table>
    <caption>搭配@Configuration配置类的使用</caption>
    <tr>
        <td width="15%">@Profile</td>
        <td width="85%"><a href="./profile环境切换.md">环境切换</a></td>
    </tr>
    <tr>
        <td>@Import</td>
        <td><a href="./import导入其他配置类.md">导入其他配置类</a></td>
    </tr>
    <tr>
        <td>@ComponentScan</td>
        <td><a href="./componentscan组件扫描.md">组件扫描</a></td>
    </tr>
    <tr>
        <td>@PropertySource</td>
        <td><a href="./properties配置文件资源导入.md">properties配置文件资源导入</a></td>
    </tr>
    <tr>
        <td>@Primary</td>
        <td><a href="./primary同类型注入优先.md">同类型注入优先</a></td>
    </tr>
</table>



## 

### 


### ImportBeanDefinitionRegistrar接口实现类



## @ComponentScan 组件扫描

| 注解 | @ComponentScan                                               |
| ---- | ------------------------------------------------------------ |
| 位置 | 配置类                                                       |
| 作用 | 指定一个或多个包名：扫描指定包及其子包下标记的类。<br />不配置包名：扫描当前@ComponentScan标注类所在包及其子包下的类。 |
| 扫描 | 精确范围。<br />过滤器：excludeFilters、includeFilgters。    |

```java
@Configuration
@ComponentScan("com.zjk")
public class ApplicationContextConfig {
}
```

```java
@Configuration
@ComponentScan({"com.zjk.service","com.zjk.dao"})
public class ApplicationContextConfig {
}
```

| 过滤器          | 说明                                 |
| --------------- | ------------------------------------ |
| excludeFilters  | 设置扫描加载bean时，排除的过滤规则。 |
| includeFilgters | 加载指定的bean。                     |

| type属性        | 过滤                      |
| :-------------- | :------------------------ |
| ANNOTATION      | 注解                      |
| ASSIGNABLE_TYPE | 指定的类型                |
| ASPECTJ         | Aspectj表达式（基本不用） |
| REGEX           | 正则表达式                |
| CUSTOM          | 自定义规则                |

```java
@ComponentScan(value = "com.zjk",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION, //指定注解类型的Bean
                classes = Controller.class //排除指定注解的Bean
        )
)
```

<img src="../../pictures/Snipaste_2023-04-10_00-40-11.png" width="700"/>  

- component-scan是一个context命名空间下的自定义标签，要找到对应的命名空间处理器（NamespaceHandler）和解析器，查看spring-context包下的spring.handlers文件。将标注的@Component的类生成的对应的BeanDefiition进行注册。

```xml
<context:conponent-scan base-package="com.zjk"></context>
```

<img src="../../pictures/Snipaste_2023-04-09_22-05-52.png" width="1200"/>

- AnnotationConfigApplicationContext在进行创建时，内部调用了如下代码，该工具注册了几个Bean后处理器。

```java
AnnotationConfigUtils.registerAnnotationConfigProcessors(this.registry)
```

- 其中，ConfigurationClassPostProcessor 是一个 BeanDefinitionRegistryPostProcessor，经过一系列源码调用，最终也被指定到了ClassPathBeanDefinitionScanner 的doScan 方法（与xml方式最终终点一致）。

<img src="../../pictures/Snipaste_2023-04-10_00-43-27.png" width="800"/>  

<img src="../../pictures/Snipaste_2023-04-10_00-38-31.png" width="1200"/>      

## @PropertySource properties资源加载

| 注解 | @PropertySource            |
| ---- | -------------------------- |
| 位置 | 配置类                     |
| 作用 | 加载外部properties资源配置 |

```java
@Configuration
@ComponentScan
@PropertySource({"classpath:jdbc.properties","classpath:xxx.properties"})
public class ApplicationContextConfig {}
```

## @Primary 同类型注入优先

| 注解 | @Primary                                           |
| ---- | -------------------------------------------------- |
| 位置 | 类                                                 |
| 作用 | 标注相同类型的Bean优先被使用权。                   |
| 说明 | 搭配@Component/@Bean使用，标注该Bean的优先级更高。 |

> 通过类型获取Bean（getBean(class)）、@Autowired根据类型进行注入时，会选用优先级更高的Bean。
>
> @Qualifier指定名称注入，而不是优先级。

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