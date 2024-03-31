- <code>@Profile</code>标注当前产生的Bean从属于哪个环境，而没有被@Profile标注的，也就是默认处于激活的环境中。

1. 只有激活了当前环境，被标注的Bean才能被注册到Spring容器里。
2. 不指定环境的Bean，任何环境下都能注册到Spring容器里。

```java
@Repository("userDao")
@Profile("test")
public class UserDaoImpl implements UserDao{}

@Component
public class OtherBean {
    @Bean("service")
    @Profile("test")
    public UserService userService(@Qualifier("userServiceImpl") UserService userService) {
        return userService;
    }
}
```

<table>
	<tbody>
		<tr>
			<td width="10%">JVM</td>
			<td width="90%">
				<code> -Dspring.profiles.active=环境名</code>
			</td>
		</tr>
		<tr>
			<td>代码</td>
			<td>
				<code>System.setProperty(&quot;spring.profiles.active&quot;,&quot;环境名&quot;)</code>
				<br />必须在创建Spring容器前。
			</td>
		</tr>
	</tbody>
</table>

1. 默认激活最外层的`<beans>`；如果激活了子级的`<beans>`，则其父级也被激活。即：如果激活了最内层的`<beans>`则依次向外激活直到最外层的`<beans>`也被激活。（向外扩散激活）
2. 如果外层和内存的`<beans>`存在相同id或class的`<bean>`，在二者都被激活的情况下，如果是getBean(id)或getBean(class)会出错。

