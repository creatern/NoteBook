# JdbcTemplate

```xml
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-jdbc</artifactId>
```

| 方法   | 说明       |
| ------ | ---------- |
| query  | 查询       |
| update | 写入、更新 |

- GeneratedKeyHolder

## JdbcOperations

# JDBC


```xml
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-jdbc</artifactId>
```

<table>
	<thead>
		<tr>
			<th width="12%">自动脚本文件</th>
			<th width="88%">描述</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>schema.sql</td>
			<td>src/main/resources/schema.sql
				<br />模式定义，若应用的根路径下存在该文件，则应用启动时会基于数据库执行该文件
			</td>
		</tr>
		<tr>
			<td>data.sql</td>
			<td>src/main/resources/data.sql
				<br />预加载数据，同上，在数据源bean初始化时执行，适用于任何关系型数据库
			</td>
		</tr>
	</tbody>
</table>
<table>
	<thead>
		<tr>
			<th width="12%">领域类注解</th>
			<th width="88%">描述</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>@Id</td>
			<td>对象的唯一标识属性。
				<br />若没有唯一标识字段则不用设置。
			</td>
		</tr>
		<tr>
			<td>@Table</td>
			<td>可选，默认基于领域类的名称映射到数据库的表（TacoOrder&rarr;Taco_Order）。
				<br />可显式指定表名（类）。
			</td>
		</tr>
		<tr>
			<td>@Column</td>
			<td>可选，默认根据属性名自动映射到数据库的列（ccCVV&rarr;cc_cvv）。
				<br />可显式指定字段名。
			</td>
		</tr>
	</tbody>
</table>


- Persistable

