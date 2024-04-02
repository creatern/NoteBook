<table>
    <thead>
        <caption>spring-context</caption>
        <tr>
            <th>范围</th>
            <th colspan="2">描述</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td width="10%" rowspan="2">singleton</td>
            <td width="10%" rowspan="2">单例</td>
            <td width="80%">默认使用singleton。Spring容器创建的时候，就会进行Bean的实例化，并存储到容器内部的单例池singletonObjects中</td>
        </tr>
        <tr>
            <td>每次getBean()时都是从单例池中获取相同的Bean实例</td>
        </tr>
        <tr>
            <td rowspan="2">prototype</td>
            <td rowspan="2">原型</td>
            <td>Spring容器初始化时不会创建Bean实例，当调用getBean()时才会实例化Bean</td>
        </tr>
        <tr>
            <td>每次getBean()都会创建一个新的Bean实例。信息存放在 beanDefinitionMap</td>
        </tr>
    </tbody>
</table>
<table>
    <thead>
        <caption>spring-web</caption>
        <tr>
            <th>范围</th>
            <th colspan="2">描述</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td width="10%" rowspan="2">request</td>
            <td width="10%" rowspan="2">请求</td>
            <td width="80%"></td>
        </tr>
        <tr>
            <td></td>
        </tr>
        <tr>
            <td rowspan="2">session</td>
            <td rowspan="2">会话</td>
            <td></td>
        </tr>
        <tr>
            <td></td>
        </tr>
    </tbody>
</table>



