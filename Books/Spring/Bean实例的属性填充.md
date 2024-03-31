# Bean实例属性填充

- BeanDefinition将当前Bean实体的注入信息存储在propertyValues属性。

<img src="../../pictures/Snipaste_2023-04-05_23-45-21.png" width="600"/>   

<table>
    <thead>
        <tr>
            <th width="15%">属性注入</th>
            <th width="85%">描述</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>普通属性</td>
            <td>String、int、存储基本类型的集合时，直接通过set方法的反射设置</td>
        </tr>
        <tr>
            <td>单向对象引用</td>
            <td>从容器中getBean()获取后通过set方法反射设置进去。
                <br />如果容器中没有，等待被注入对象Bean实例完成整个生命周期后，再进行注入操作。
            </td>
        </tr>
        <tr>
            <td>双向对象引用</td>
            <td>循环引用（循环依赖）问题。</td>
        </tr>
    </tbody>
</table>
# 单项对象引用


- 单项对象引用：Bean对象的创建是按照在配置文件xml中`<bean>`的位置来确定先后顺序的。因此，尽量将被注入Bean的`<bean>`放在上面。

<img src="../../pictures/Snipaste_2023-04-05_16-25-34.png" width="900"/>   

# 循环依赖与三级缓存存储

## 循环依赖问题

- 循环依赖是多个实体之间相互依赖并形成闭环的情况

<img src="../../pictures/Snipaste_2023-04-05_16-22-45.png" width="900"/>   

<img src="../../pictures/Spring-循环引用问题.drawio.svg" width="1200"/>   

## 三级缓存存储

- Spring提供三级缓存存储完整Bean实例与半成品Bean实例，以此解决循环引用问题。
- 在DefaultListableBeanFactory的上四级父类<b>DefaultSingletonBeanRegistry</b>中提供如下三个Map：

```java
public class DefaultSingletonBeanRegistry ... {

    Map<String, Object> singletonObjects = new ConcurrentHashMap(256);

    Map<String, Object> earlySingletonObjects = new ConcurrentHashMap(16);

    Map<String, ObjectFactory<?>> singletonFactories = new HashMap(16);
}
```

<table>
	<thead>
		<tr>
			<th width="15%">缓存</th>
			<th width="85%">描述</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>三级缓存：
				<br />singletonFactories
			</td>
			<td>对象Bean创建时就放入三级缓存，还未完成注入。
				<br />单例Bean的工厂池，缓存半成品对象，对象未被引用，使用时在通过工厂创建Bean。
			</td>
		</tr>
		<tr>
			<td>二级缓存：
				<br />earlySingletonObjects
			</td>
			<td>对象Bean被注入到其他Bean中时，如果在三级缓存中，则移入到二级缓存。
				<br />早期Bean单例池，缓存半成品对象，且当前对象已经被其他对象引用了。
			</td>
		</tr>
		<tr>
			<td>一级缓存：
				<br />singletoObjects
			</td>
			<td>对象Bean完成实例化和初始化。
				<br />最终存储单例Bean成品（实例化和初始化都完成）的容器。
			</td>
		</tr>
	</tbody>
</table>

- 假设UserService和UserDao循环依赖：

1. UserService 实例化对象，但尚未初始化，将UserService存储到三级缓存；
2. UserService 属性注入，需要UserDao，从缓存中获取，没有UserDao；
3. UserDao实例化对象，但尚未初始化，将UserDao存储到到三级缓存；
4. UserDao属性注入，需要UserService，依次从一二三级缓存查找，在三级缓存中发现并获取UserService，UserService从三级缓存移入二级缓存；
5. UserDao执行其他生命周期过程，最终成为一个完成Bean，存储到一级缓存，删除二三级缓存；
6. UserService 注入UserDao；
7. UserService执行其他生命周期过程，最终成为一个完成Bean，存储到一级缓存，删除二三级缓存。

<img src="../../pictures/三级缓存源码剖析流程_00.png" width="3000"/>   