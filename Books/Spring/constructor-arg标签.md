<table>
    <caption>&lt;constructor-arg/&gt;的可配置属性</caption>
    <tr>
        <th width="10%">属性</th>
        <th width="90%">描述</th>
    </tr>
    <tr>
        <td>value</td>
        <td>传递给构造方法参数的值</td>
    </tr>
    <tr>
        <td>ref</td>
        <td>传递给构造方法参数的Bean ID</td>
    </tr>
    <tr>
        <td>type</td>
        <td>构造方法参数对应的类型</td>
    </tr>
    <tr>
        <td>index</td>
        <td>构造方法参数对应的位置，从0计数</td>
    </tr>
    <tr>
        <td>name</td>
        <td>构造方法参数对应的名称</td>
    </tr>
</table>
1. 如果存在多个构造方法，则可以根据`<consturctor-arg>`的参数来选择相应的构造器。
2. 只要是为了实例化Bean对象而传递的参数都可以通过`<constructor-arg>`标签完成。

```xml
<consturctor-arg name="构造方法中的参数名称" value="参数的值"></consturctor-arg>
```

```xml
<bean id="userDao" name="com.zjk.dao.impl.UserDaoImpl">
    <consturctor-arg name="name" value="Tom"></consturctor-arg>
    <constructor-arg name="age" value="18"></constructor-arg>
</bean>
```

```java
public UserDaoImpl(String name,int age){
}
```


