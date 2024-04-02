- BeanPostProcessor（Bean后处理器）：实现了该接口并被容器管理的BeanPostProcessor会在创建每个Bean的流程节点上被Spring自动调用。

```java
public interface BeanPostProcessor {
    //在属性注入完毕，init初始化方法执行之前被回调
    @Nullable
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    //在init初始化方法执行之后，被添加到单例池singletonObjects之前被回调
    @Nullable
    default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
```

<table>
	<tbody>
		<tr>
            <td width="10%" rowspan="2">方法参数</td>
			<td width="20%">Object bean</td>
			<td width="75%">当前被实例化的Bean</td>
		</tr>
		<tr>
			<td>String beanName</td>
			<td>当前Bean在容器中的名称</td>
		</tr>
		<tr>
			<td>返回值</td>
            <td>Object</td>
			<td>加入到singletonObjects单例池中的bean</td>
		</tr>
	</tbody>
</table>

- Proxy 动态代理在运行期间执行增强操作：

1. 代理设计模式和包装设计模式。
2. 使用动态代理 对目标bean进行增强，返回proxy对象，存储在singletonObjects单例池中。
3. 主要是在postProcessAfterInitialization(Object bean, String beanName)中，对已经init初始化的bean进行增强。

