<table>
	<thead>
		<tr>
			<th align="left" colspan="2">spring-context</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="left" width="20%">ClassPathXmlApplicationContext</td>
			<td align="left" width="80%">加载类路径下的xml配置的ApplicationContext</td>
		</tr>
		<tr>
			<td align="left">FileSystemXmlApplicationContext</td>
			<td align="left">加载磁盘路径下的xml配置的ApplicationContext</td>
		</tr>
		<tr>
			<td align="left">AnnotationConfigApplicationContext</td>
			<td align="left">加载注解配置类的ApplicationContext</td>
		</tr>
		<tr>
            <th colspan="2">spring-web</th>
		</tr>
		<tr>
			<td>XmlWebApplicationContext</td>
			<td>web环境下，加载类路径下的xml配置的ApplicationContext</td>
		</tr>
		<tr>
			<td>AnnotationConfigWebApplicationContext</td>
			<td>web环境下，加载磁盘路径下的xml配置的ApplicationContext</td>
		</tr>
	</tbody>
</table>


```java
//1. 加载配置文件，实例化容器
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//2. 获取Bean实例对象
UserService userService = (UserService) applicationContext.getBean("userService");

System.out.println(userService);
```

