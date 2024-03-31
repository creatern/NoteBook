# IoC

## IoC容器

- IoC（Inversion of Control，控制反转）是一种决定容器如何装配组件/业务对象（Bean）的模式。容器根据所提供的元数据来创建并管理这些Bean（可重用组件）。

## BeanFactory与ApplicationContext

<table>
    <tr>
        <td width="15%"><a href="./BeanFactory.md">BeanFactory</a></td>
        <td width="15%">spring-core<br/>spring-beans</td>
        <td>BeanFactory是容器的基础接口（工厂设计模式）</td>
    </tr>
    <tr>
        <td><a href="ApplicationContext">ApplicationContext</a></td>
        <td>spring-context</td>
        <td>Application接口继承了BeanFactory，并增加了更多企业级应用的特性</td>
    </tr>
</table>

<img src="../../pictures/ApplicationContextImplements2023_4_1_14_10.png" width="500"/>  

1. BeanFactory是Spring的早期接口（Bean工厂）；ApplicationContext是后期更高级接口（Spring容器）。
2. ApplicationContext在BeanFactory基础上对功能进行了扩展，监听功能、国际化功能等。BeanFactory的API更偏向底层，ApplicationContext的API大多数是对这些底层API的封装。ApplicationContext除了继承了BeanFactory外，还继承了ApplicationEventPublisher（事件发布器）、ResouresPatternResolver（资源解析器）、MessageSource（消息资源）等。但是ApplicationContext的核心功能还是BeanFactory。
3. ApplicationContext与BeanFactory既有继承关系，又有融合关系。Bean创建的主要逻辑和功能都被封装在BeanFactory中，ApplicationContext不仅继承了BeanFactory，而且ApplicationContext内部还维护着BeanFactory的引用。 
4. Bean的初始化时机不同，原始BeanFactory是在首次调用getBean("id")时才进行Bean的创建，而ApplicationContext是加载配置文件、容器创建时就将所有的Bean实例都创建好了，存储到一个单例池中，当调用getBean时直接从单例池中获取Bean实例返回。

# Bean

## Bean的定义

- Spring对Bean没有特殊要求，只要是最普通的Java对象（POJO，Plain Old Java Object）即可。

> JavaBeans是Java中一种特殊的类，可以将多个对象封装到一个对象（Bean）中。特点是可序列化，提供无参构造器，提供Getter方法和Setter方法访问对象的属性。

- Bean的定义通常包含如下部分：

1. Bean的名称，一般是Bean的id，也可为Bean指定别名（alias）。Spring自动生成的Bean的名称是类名称的首字母小写加驼峰（camel-cased）命名法。例如，HelloService&rarr;helloService。
2. Bean的具体类信息（全限定类名）。
3. Bean的作用域，单例（singleton）、原型（prototype）。
4. 依赖注入（DI）相关信息，构造方法参数、属性以及自动织入（autowire）方式。
5. 创建销毁相关信息，懒加载模式、初始化回调方法与销毁回调方法。

## DI 依赖注入

<table>
    <tr>
        <td width="20%">基于构造方法的注入</td>
        <td width="80%"><a href="./constructor-arg标签.md">&lt;constructor-arg/&gt;</a></td>
    </tr>
    <tr>
        <td>基于Setter方法的注入</td>
        <td><a href="./property标签.md">&lt;property/&gt;</a></td>
    </tr>
</table>

##  Bean的配置

<table>
    <caption>配置Bean的三种方式</caption>
    <tr>
        <td width="20%">基于XML文件的配置</td>
        <td width="80%"><a href="./基于XML文件的Bean配置.md">&lt;bean/&gt;</a></td>
    </tr>
    <tr>
        <td>基于注解的配置</td>
        <td><a href="./基于注解的Bean配置.md">@Component</a></td>
    </tr>
    <tr>
        <td>基于Java类的配置</td>
        <td><a href="./基于Java类的Bean配置.md">@Bean</a></td>
    </tr>
</table>
<table>
    <thead>
        <tr>
            <th width="20%">&lt;bean&gt;属性</th>
            <th width="20%">注解</th>
            <th width="60%">描述</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                id、class、name
            </td>
            <td>@Component</td>
            <td>Bean的id、全限定名或别名配置；并在指定扫描范围内被Spring加载并实例化
            </td>
        </tr>
        <tr>
            <td>
                scope
            </td>
            <td>@Scope</td>
            <td><a href="./Bean的作用范围.md">Bean的作用范围</a>；<q>singleton</q>和<q>prototype</q>
            </td>
        </tr>
        <tr>
            <td>lazy-init</td>
            <td>@Lazy</td>
            <td>Bean的实例化时机，是否<a href="./lazy-init延迟加载.md">延迟加载</a>；BeanFactory作为容器时无效。</td>
        </tr>
        <tr>
            <td><span name="init-method">init-method</span></td>
            <td>@PostConstruct</td>
            <td>Bean实例化后自动执行的初始化方法，method指定方法名</td>
        </tr>
        <tr>
            <td>
                destroy-method
            </td>
            <td>@PreDestroy</td>
            <td>Bean实例销毁前的方法，method指定方法名
            </td>
        </tr>
        <tr>
            <td>
                autowire、byType
            </td>
            <td>@AutoWired</td>
            <td>设置自动注入模式；按照类型"byType"或名字"byName"
            </td>
        </tr>
        <tr>
            <td>factory-bean<br/>factory-method</td>
            <td>@Bean</td>
            <td>指定工厂Bean的方法完成Bean的创建</td>
        </tr>
    </tbody>
</table> 


## Bean的生命周期

- Spring Bean的生命周期是从Bean实例化之后（反射创建出对象之后），到Bean成为一个完整对象，最终存储到单例池的过程。
- Spring容器在进行初始化时，会将配置的Bean的信息封装成一个BeanDefinition对象，所有的BeanDefinition存储到beanDefinitionMap，Spring对该Map遍历、使用反射创建Bean实例对象。创建好的Bean对象存储在singletonObjects，当调用getBean()方法时则最终从singletonObjects中取出Bean实例对象返回。

<img src="../../pictures/202304062302.png" width="100%"/> 

1. 实例化阶段
   1. 加载配置类，扫描每个Bean的信息，封装成一个个的BeanDefinition对象;
   2. 将BeanDefinition存储在<a href="./beanDefinitionMap.md">beanDefinitionMap</a>（`Map<String,BeanDefinition>`）;
   3. 执行<a href="./BeanFactoryPostProcessor.md">BeanFactoryPostProcessor</a>（Bean工厂<a href="./后处理器.md">后处理器</a>）内定义的逻辑，对BeanDefinition对象进行增强;
   4. ApplicationContext底层遍历beanDefinitionMap，创建Bean实例对象;
   5. 创建好的Bean实例对象被存储到<a href="./beanDefinitionMap.md">singletonObjects</a>（`Map<String,Object>`）;
6. 当执行applicationContext.getBean(beanName)时，从singletonObjects去匹配Bean实例返回。   
   
2. 初始化阶段
   1. <a href="./Bean实例的属性填充.md">Bean实例的属性填充</a>。
   2. <a href="./Aware.md">Aware</a>接口属性注入。
   3. <a href="BeanPostProcessor">BeanPostProcessor</a>的Object <u>postProcessBeforeInitialization</u>(Object bean, String beanName)方法回调。
   4. <a href="./InitializingBean.md">InitializingBean</a>接口的afterPropertiesSet()初始化方法回调。
   5. 自定义初始化方法init回调 <a href="#init-method">init-method</a>。
   6. BeanPostProcessor的Object <u>postProcessAfterInitialization</u>(Object bean, String beanName)方法回调。

