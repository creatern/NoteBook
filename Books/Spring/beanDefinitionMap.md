# beanDefinitionMap

- Spring会取出beanDefinitionMap中的每个BeanDefinition信息，反射构造方法或调用指定的工厂方法生成Bean实例对象。只要将BeanDefinition注册到beanDefinitionMap，Spring就会进行对应的Bean的实例化操作。

<img src="../../pictures/Snipaste_2023-04-03_13-42-50.png" width="1200"/>   

<img src="../../pictures/Snipaste_2023-04-04_23-36-40.png" width="600"/>   

> BeanDefinition接口：RootBeanDefinition。
>
> <img src="../../pictures/Snipaste_2023-04-05_01-06-17.png" width="500"/>   

- <b>DefaultListableBeanFactory</b>内部维护着beanDefinitionMap。

```java
public class DefaultListableBeanFactory extends ... implements ... {
    //存储<bean>标签对应的BeanDefinition对象
    //key:beanName，value:Bean定义对象BeanDefinition
    private final Map<String, BeanDefinition> beanDefinitionMap;
}
```

<img src="../../pictures/DefaultListableBeanFactory.png" width="1200"/>   

# singletonObjects 单例池

- beanDefinitionMap中的BeanDefinition会被转化成对应的Bean实例对象，存储到<b>单例池singletonObjects</b>中去，在DefaultListableBeanFactory的上四级父类DefaultSingletonBeanRegistry中，维护着singletonObjects。

```java
public class DefaultSingletonBeanRegistry extends ... implements ... {
    //存储Bean实例的单例池
    //key:beanName，value:Bean的实例对象
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap(256);
}
```

<img src="../../pictures/DefaultListableBeanFactory.png" width="1200"/>   