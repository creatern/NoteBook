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

