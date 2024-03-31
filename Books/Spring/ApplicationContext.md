# ApplicationContext


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

# Aware

- Spring Framwork提供了很多Aware接口，使得Bean能够感知到容器的信息或者通过容器来注入一些与容器相关的Bean。

1. 实现BeanFactoryAware或ApplicationContextAware接口。
2. @Autowired注解来注入ApplicationContext等容器。

# 事件机制

## 监听事件

- ApplicationContext提供了一套事件机制（自定义事件和内置事件）：

1. 在容器发生变动时，可以通过ApplicationEvent的子类通知ApplicationListener接口的实现类。
2. 通过实现ApplicationListener接口或@EventListner注解标识，就可以监听这些事件。

```java
/* 实现ApplicationListener<T>接口 */
@Component
@Order(1)
public class MyContextClosedEventListner implements ApplicationListener<ContextClosedEvent> {

    /* 当Spring容器关闭时执行的逻辑 */
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.printf("\n\n\n\n################## ApplicationContext closed.....###################\n\n\n\n");
    }
}
```

```java
/* 在组件或配置类内部定义@EventListner标识的方法 */
@EventListener(ContextClosedEvent.class)
@Order(2)
public void closedEventListner() {
    System.out.println("Application is closed");
}
```

## 定义事件

```java
/* 自定义的事件 */
public class CustomEvent extends ApplicationEvent {
    public CustomEvent(Object source) {
        super(source);
    }
}
```

```java
/* 事件推送 */
@Component
public class CustomerEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    public void fire() {
        publisher.publishEvent(new CustomEvent("Hello"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}

```

```java
/* 事件监听 */
@EventListener
public void customerEventListner(CustomEvent customEvent) {
    System.out.println("##############################################");
    System.out.println("CustomEvent Source:" + customEvent.getSource());
}
```

```java
// 当容器中使用customEventPublisher的fire()方法时，触发事件
applicationContext.getBean("customEventPublisher", CustomEventPublisher.class)
    .fire();
```

