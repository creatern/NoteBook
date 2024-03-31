- autowire：如果被注入的属性类型是Bean引用，可以在`<bean>` 标签中使用 autowire 属性去配置自动注入方式。

<table>
	<thead>
		<tr>
			<th width="10%">注解</th>
			<th width="10%">属性</th>
			<th width="80%">描述</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>@Autowired</td>
			<td>byName</td>
			<td>属性名自动装配：匹配 setXxx()和id，在配置文件查找id=&quot;xxx&quot;（或name=&quot;xxx&quot;）的
				<code>&lt;bean&gt;</code> ，如果存在该
				<code>&lt;bean&gt;</code>则使用该Bean来装配。
			</td>
		</tr>
		<tr>
			<td>@Autowired
				<br />@Qualifier
			</td>
			<td>byType</td>
			<td>Bean的类型从容器中匹配：匹配setXxx(Yyy 参数)的数据类型Yyy，在配置文件中查找class的类型是Yyy的。
				<br />只要和set()方法的参数类型匹配，就会为该set()方法提供参数。  
				<br />同一类型只能匹配一个
				<code>&lt;bean&gt;</code>，匹配出多个相同Bean类型时（class相同：包括继承、实现），报错。
			</td>
		</tr>
	</tbody>
</table>

```xml
<bean id="userService" class="com.zjk.service.impl.UserServiceImpl" autowire="byName"></bean>
<bean id="userDao" class="com.zjk.dao.impl.UserDaoImpl"></bean>
```

```xml
<bean id="userService" class="com.zjk.service.impl.UserServiceImpl" autowire="byType"></bean>
<bean id="userDao" class="com.zjk.dao.impl.UserDaoImpl"></bean>
```
