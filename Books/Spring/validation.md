```xml
<groupId>org.springframwork.boot</groupId>
<artifactId>spring-boot-starter-validation</artifactId>
```

<table>
	<thead>
		<tr>
			<th width="20%">注解</th>
			<th width="80%">检验</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>@Valid</td>
			<td>执行该对象（属性已存在以下检验）的检验<br />如果检验错误，返回Errors对象到该方法的Errors参数（errors# <code>Boolean hasErrors()</code>）
			</td>
		</tr>
		<tr>
			<td>@NotNull</td>
			<td>非null</td>
		</tr>
		<tr>
			<td>@NotBlank</td>
			<td>非空&quot;&quot;</td>
		</tr>
		<tr>
			<td>@Size</td>
			<td>min、max：长度限制
				<br />message：提示信息
			</td>
		</tr>
		<tr>
			<td>@CreditCardNumber</td>
			<td>合法的信用卡号</td>
		</tr>
		<tr>
			<td>@Pattern</td>
			<td>正则
				<br />regexp
			</td>
		</tr>
		<tr>
			<td>@Digits</td>
			<td>integer：整数位数上限
				<br />fraction：小数位数上限
			</td>
		</tr>
	</tbody>
</table>

