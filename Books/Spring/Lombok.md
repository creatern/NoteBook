- Lombok用于在编译期自动生成类的方法（@Data），而在生成jar、war时自动剔除Lombok。

<table>
	<tbody>
		<tr>
			<td width="30%">@Data</td>
			<td width="70%">自动为需要构造器、初始化方法...的创建对应方法。</td>
		</tr>
		<tr>
			<td>@Slf4j</td>
			<td>自动生成SLF4J Logger静态属性。</td>
		</tr>
		<tr>
			<td>@AllArgsConstructor</td>
			<td>所有属性的构造器</td>
		</tr>
		<tr>
			<td>@NoArgsConstructor(access, force)</td>
			<td>空参构造器
				<br />access：权限修饰
				<br />force：设置默认值（final属性需要）
			</td>
		</tr>
	</tbody>
</table>

