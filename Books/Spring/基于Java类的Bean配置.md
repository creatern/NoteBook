# @Bean 组件

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
