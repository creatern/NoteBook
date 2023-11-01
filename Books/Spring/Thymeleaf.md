# SpEL

| EL表达式 | 表达式                                                       |
| -------- | ------------------------------------------------------------ |
| @\{\}    | 链接资源<br />默认根目录为static                             |
| \$\{\}   | （Model中的）变量                                            |
| \*\{\}   | 选定对象（th:object）而不是整个上下文评估表达式<br />若没有选定对象，则等同于`${}`。 |
| \#\{\}   | 消息表达（文本外部化）：读取配置文件                         |

```html
<!--${}变量表达式-->

<!--普通字符串-->
<p th:text="${name}"></p>
<!--POJO类型 person(name,age)-->
<p th:text="${person.name}"></p>
<p th:text="${person['name']}"></p>
<p th:text="${person.getName()}"></p>

<!--List-->
<tr th:each="item:${userlist}">
    <td th:text="${item}"></td>
</tr>

<!--Map取值-->
<td th:text="${map.place}"></td>
<td th:text="${map.['place']}"></td>
<td th:text="${map.get('place')}"></td>

<!--Map遍历-->
<tr th:each="item:${map}">
    <td th:text="${item.key}"></td>
    <td th:text="${item.value}"></td>
</tr>
```

```html
<!--*{}选择变量表达式-->
<div th:object="${user}">
    <p>Name: <span th:text="*{name}">赛</span>.</p>
    <p>Age: <span th:text="*{age}">18</span>.</p>
    <p>Detail: <span th:text="*{detail}">好好学习</span>.</p>
</div>
```

```html
<!--#{}消息表达-->
<!--配置文件：/resources/test.properties-->
<!--application.properties中加入：test，直接使用配置文件中的键值对-->
<span th:text="#{tom.name}"></span>
```

# th标签

| 替换               | -                         |
| ------------------ | ------------------------- |
| th:id              | id                        |
| th:text            | 文本                      |
| th:utext           | html文本                  |
| th:src             | 资源                      |
| th:href            | 超链接                    |
| th:object          | 选定对象（搭配`*{}`使用） |
| th:value           | 值                        |
| **流程控制**       | **-**                     |
| th:if<br>th:unless | 判断                      |
| th:each            | 循环                      |
| **检验**           | **-**                     |
| th:errors          | 异常（搭配validation）    |
| **取值**           | **-**                     |
| th:field           | 输入域<br />#fields       |

```html
<!--List循环-->
<td th:each="user:${userList}" th:text="${user}"></td>

<!--Map循环-->
<table>
    <tr th:each="person:${personMapper}">
        <td th:text="${person.key}"></td>
        <td th:text="${person.value}"></td>
    </tr>
</table>
```
