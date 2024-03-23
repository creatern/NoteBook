# Annotation 注解（@interface）

- Annotation（注解）：Java对元数据(MetaData) 的支持。可以在编译、类加载、 运行时被读取, 并执行相应的处理。在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息。注解必须配上注解的信息处理流程才有意义。
- `@interface` 可用于修饰包、类、 构造器、 方法、 成员变量、 参数、 局部变量的声明，这些信息被保存在Annotation的 “name-value” 键值对中。

> 一定程度上： 框架 = 注解 + 反射 + 设计模式。

| 生成文档相关注解 | 说明                                                         |
| :--------------- | :----------------------------------------------------------- |
| `@author`        | 标明开发该类模块的作者，多个作者之间使用`,`分割              |
| `@version`       | 标明该类模块的版本                                           |
| `@see`           | 参考转向，也就是相关主题                                     |
| `@since`         | 从哪个版本开始增加的                                         |
| `@param`         | 对方法中某参数的说明，如果没有参数就不能写<br />@param 形参名 形参类型 形参说明 |
| `@return`        | 对方法返回值的说明，如果方法的返回值类型是void就不能写<br />@return 返回值类型 返回值说明 |
| `@exception`     | 对方法可能抛出的异常进行说明 ，如果方法没有用throws显式抛出的异常就不能写<br />@exception 异常类型 异常说明 |

| 编译时格式检查注解  | 说明                                 |
| :------------------ | :----------------------------------- |
| `@Override`         | 限定重写父类方法, 该注解只能用于方法 |
| `@Deprecated`:      | 所修饰的元素(类, 方法等)已过时。     |
| `@SuppressWarnings` | 抑制编译器警告                       |

> 反射获取注解信息：JDK 5.0 在 java.lang.reflect 包下新增了 AnnotatedElement 接口, 该接口代表程序中可以接受注解的程序元素。当一个 Annotation 类型被定义为运行时注解（@Retention中声明的生命周期状态为RUNTIME）后, 该注解才是运行时可见，当 class 文件被载入时保存在 class 文件中的 Annotation 才会被虚拟机读取。

# 自定义注解

- 注解声明： `@interface` 、继承`java.lang.annotation.Annotation接口`。
- 如果定义的注解含有配置参数且无默认值，则使用时必须指定其参数值：`参数名 = 参数值`。
- 如果只有一个参数成员，建议使用参数名为value：使用时可以省略`value=`。

| 注解类型 | 说明         |
| :------- | :----------- |
| 标记     | 没有成员定义 |
| 元数据   | 包含成员变量 |

**成员变量：无参方法声明** 

- 返回值：变量类型（基本数据类型、String类型、Class类型、enum类型、Annotation类型、以上所有类型的数组）。
- 方法名：变量名称。
- 默认值 ：default 关键字。

```java
public @interface MyAnnocation {
    String value() default "hi";
}

@MyAnnocation(value = "hello")
class Person {}
```

# 元注解

- 元注解：对现有的注解进行解释说明的注解，用于修饰其他注解定义。（注解也可以修饰其他注解）

> JDK5.0提供了4个标准的meta-annotation类型： Retention 、 Target 、Documented、Inherited。

```java
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
public @interface MyAnnocation {
    String value() default "hi";
}

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, MODULE, PARAMETER, TYPE})
public @interface Deprecated {...}
```

## @Retention 生命周期

| 参数                    | 说明                                                         |
| :---------------------- | :----------------------------------------------------------- |
| RetentionPolicy.SOURCE  | 源文件保留<br />在源文件中有效，编译器直接丢弃这种策略的注释 |
| RetentionPolicy.CLASS   | 默认，class保留<br />在class文件中有效，当运行 Java 程序时, JVM 不会保留注解 |
| RetentionPolicy.RUNTIME | 运行时保留<br />在运行时有效，当运行 Java 程序时, JVM 会保留注释。<br>只有说明为RUNTIME生命周期的注解，才可以通过反射获取该注释。 |

## @Target 类型

- @Target指定被修饰的注解类型能用于修饰哪些元素。

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Target {
    ElementType[] value();
}
```

| ElementType枚举值 | 说明 注解使用位置                          |
| ----------------- | ------------------------------------------ |
| TYPE              | 类、接口（包括@interface）、枚举、记录声明 |
| FIELD             | 属性成员、Enum对象成员                     |
| METHOD            | 方法                                       |
| PARAMETER         | 形参                                       |
| CONSTRUCTOR       | 构造器                                     |
| LOCAL_VARIABLE    | 局部变量                                   |
| ANNOTATION_TYPE   | 注解接口                                   |
| PACKAGE           | 包                                         |
| TYPE_PARAMETER    | 类型变量的声明语句中（泛型声明等）         |
| TYPE_USE          | 使用类型的任何语句中                       |
| MODULE            | 模块                                       |
| RECORD_COMPONENT  | 记录组件                                   |

```java
@Inherited
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE,TYPE_PARAMETER,TYPE_USE})
public @interface MyAnnotation {
    String value() default "hi";
}

class Generic<@MyAnnotation T>{  //TYPE_PARAMETER
    //TYPE_USE
    public void show()throws @MyAnnotation RuntimeException{
        ArrayList<@MyAnnotation String> list = new ArrayList<>();

        int num = (@MyAnnotation int)10L;
    }
}
```

## @Documented 文档

- @Documented指定被修饰的注解类将被javadoc 工具提取成文档。

- 默认情况下，javadoc是不包括注解的，使用`@Documented`时必须设置`@Retention`值为RUNTIME。

## @Inherited 继承性

- 如果把标有`@Inherited`注解的自定义注解标注在类级别上，则子类继承父类类级别的注解。

## @Repeatable 可重复

- `@Repeatable(存放该重复注解的注解.class)`：同时需要相同的`@Inherited，@Tartget和@Rentention`。

```java
@Inherited
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
public @interface MyAnnotation {
    String value() default "hi";
}

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
public @interface MyAnnotations {
    MyAnnotation[] value();
}

//重复注解
@MyAnnotation(value = "hello")
@MyAnnotation(value = "abc")
class Person {
  ...
}
```

> 重复注解 jdk8.0之前。
>
> ```java
> @Inherited
> @Retention(RetentionPolicy.RUNTIME)
> @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
> public @interface MyAnnotation {
>  String value() default "hi";
> }
> 
> public @interface MyAnnotations {
>  MyAnnotation[] value();
> }
> 
> @MyAnnotations({@MyAnnotation(value = "hello"),@MyAnnotation(value = "hi")})
> class Person(){
>  ...
> }
> ```

# 
