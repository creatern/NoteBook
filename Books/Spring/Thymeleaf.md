# SpEL

```html
<!--html头部声明和使用Thymeleaf-->
<html xmlns:th="http://www.thymeleaf.org">
```

## @\{\} 链接表达式

- @\{\}链接资源地址，默认根目录为static。

## \$\{\} 变量表达式

- \$\{\}可选择的变量为Model中的变量。

```html
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

## \*\{\} 选择变量表达式

- \*\{\}选定对象（th:object）而不是整个上下文评估表达式，若没有选定对象，则等同于\$\{\}。

```html
<!--*{}选择变量表达式-->
<div th:object="${user}">
    <p>Name: <span th:text="*{name}">赛</span>.</p>
    <p>Age: <span th:text="*{age}">18</span>.</p>
    <p>Detail: <span th:text="*{detail}">好好学习</span>.</p>
</div>
```

## \#\{\} 消息表达式

- #\{\}消息表达（文本外部化），读取配置文件，用于国际化。

```html
<!--#{}消息表达-->
<!--配置文件：/resources/test.properties-->
<!--application.properties中加入：test，直接使用配置文件中的键值对-->
<span th:text="#{tom.name}"></span>
```

# \#工具类

# th:

| 普通替换 | 替换内容 |
| -------- | -------- |
| th:id    | id       |
| th:text  | 文本     |
| th:utext | html文本 |
| th:src   | 资源     |
| th:href  | 超链接   |
| th:value | 值       |
| th:field | 分组     |

## th:object 对象替换

- th:object所在元素的子元素可以使用\*\{\}来选择th:object选定的对象的属性，而不是直接从上下文中获取。

```html
<tr th:each="user:${users}" th:object="${user}">
    <td th:text="*{id}"></td>
    <td th:text="*{name}"></td>
    <td th:text="*{age}"></td>
</tr>
```

## th:if 判断

## th:each 迭代

```html
<!--List循环-->
<td th:each="user:${userList}" th:text="${user}"></td>

<!--Map循环-->
<tr th:each="person:${personMap}">
    <td th:text="${person.key}"></td>
    <td th:text="${person.value}"></td>
</tr>
```

## th:errors 校验

- th:errors通常搭配validation校验数据的正确性。

```html
<label for="ccNumber">Create Card #: </label>
<input type="text" th:field="*{ccNumber}"/>
<span class = "validationError" 
      th:if="${#fields.hasErrors('ccNumber')}"
      th:errors="*{ccNumber}">CC Num Error</span>
<!--显示的错误信息：检验注解的message-->
```

# ThymeleafResolver

- 引入spring-boot-starter-thymeleaf依赖后，Spring Boot自动配置生成ThymeleafResolver到Spring MVC中，控制器可以引用此视图解析器。

```yaml
spring: 
	thymeleaf: 
    	prefix: classpath:/templates/
    	suffix: .html
```

