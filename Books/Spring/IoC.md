# IoC容器

## IoC与Bean

- IoC（Inversion of Control，控制反转）是一种决定容器如何装配组件/业务对象（Bean）的模式。容器根据所提供的元数据来创建并管理这些Bean（可重用组件），Spring对Bean没有特殊要求，只要是最普通的Java对象（POJO，Plain Old Java Object）即可。

> JavaBeans是Java中一种特殊的类，可以将多个对象封装到一个对象（Bean）中。特点是可序列化，提供无参构造器，提供Getter方法和Setter方法访问对象的属性。

- Bean的定义通常包含如下部分：

1. Bean的名称，一般是Bean的id，也可为Bean指定别名（alias）。Spring自动生成的Bean的名称是类名称的首字母小写加驼峰（camel-cased）命名法。例如，HelloService&rarr;helloService。
2. Bean的具体类信息（全限定类名）。
3. Bean的作用域，单例（singleton）、原型（prototype）。
4. 依赖注入（DI）相关信息，构造方法参数、属性以及自动织入（autowire）方式。
5. 创建销毁相关信息，懒加载模式、初始化回调方法与销毁回调方法。

## Bean的配置

<table>
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







