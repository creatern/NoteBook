# &lt;property /&gt;

```xml
<bean id="..." class="...">
    <property name="xxx">
        <!-- 直接定义一个内部的bean -->
        <bean class="..."/>
    </property>
    
    <property>
        <!-- 定义依赖的bean -->
        <ref bean="..."/>
    </property>
    
    <property>
        <!-- 定义一个列表 -->
        <list>
            <value>aaa</value>
            <value>bbb</value>
        </list>
    </property>
</bean>
```

#  &lt;property /&gt;与集合类型

<table>
    <thead>
        <tr>
            <th width="10%">集合类型</th>
            <th width="90%">&lt;property&gt;内部标签</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>List&lt;T&gt;</td>
            <td>&lt;list&gt;</td>
        </tr>
        <tr>
            <td>Set&lt;T&gt;</td>
            <td>&lt;set&gt;</td>
        </tr>
        <tr>
            <td>Map&lt;T&gt;</td>
            <td>&lt;map&gt;<br>&nbsp;&nbsp;&lt;entry key或key-ref="" value或value-ref=""&gt;&lt;/entry&gt;</td>
        </tr>
        <tr>
            <td>Properties</td>
            <td>&lt;props&gt;<br>&nbsp;&nbsp;&lt;prop key=""&gt;value&lt;/prop&gt;<br>&lt;/props&gt;</td>
        </tr>
    </tbody>
</table>

```xml
<bean id="dao1" class="com.zjk.dao.impl.UserDaoImpl"></bean>
<bean id="dao2" class="com.zjk.dao.impl.UserDaoImpl"></bean>
<bean id="dao3" class="com.zjk.dao.impl.UserDaoImpl"></bean>

<!--引用类型-->
<bean id="userService" class="com.zjk.service.impl.UserServiceImpl">
    <property>
        <ref bean="dao1"> <!--对id=dao1的bean标签的引用-->
        <bean id="dao2" class="com.zjk.dao.impl.UserDaoImpl"></bean>
    </property>
</bean>

<!--List-->
<bean id="userService" class="com.zjk.service.impl.UserServiceImpl">
    <property name="userDaoList">
        <list>
            <ref bean="dao1"></ref>
            <bean id="dao2" class="com.zjk.dao.impl.UserDaoImpl"></bean>
            <bean id="dao3" class="com.zjk.dao.impl.UserDaoImpl"></bean>
        </list>
    </property>
</bean>
    
<!--Map-->
<bean id="userService" class="com.zjk.service.impl.UserServiceImpl">
    <property name="userDaoMap">
        <map>
            <entry key="1" value-ref="dao1"></entry>
            <entry key="2" value-ref="dao2"></entry>
            <entry key="3" value-ref="dao3"></entry>
        </map>
    </property>
</bean>

<!--properties-->
<context:property-placeholder location="classpath:jdbc.properties"/>
<bean id="userService" class="com.zjk.service.impl.UserServiceImpl">
    <property name="properties">
        <props>
            <prop key="username">zjk</prop>
            <prop key="password">123</prop>
        </props>
    </property>
</bean>
```

