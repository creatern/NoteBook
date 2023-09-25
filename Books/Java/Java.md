# Java基础

## 配置与介绍

### JDK配置

#### Windows

| 环境变量  | 配置                 |
| --------- | -------------------- |
| JAVA_HOME | D:\Java\jdk-18.0.1.1 |
| Path追加  | %JAVA_HOME%\bin      |

#### Linux

```shell
# 1.下载tar.gz后缀的
#把下载的文件 jdk-8u151-linux-x64.tar.gz 放在/usr/java/目录下。
# 2.在/usr/目录下创建java目录，
mkdir /usr/java
cd /usr/java
# 3.解压 JDK
tar -zxvf jdk-8u151-linux-x64.tar.gz
# 4.设置环境变量
#修改 /etc/profile
#在 profile 文件中添加如下内容并保存：
set java environment
JAVA_HOME=/usr/java/jdk1.8.0_151        
JRE_HOME=/usr/java/jdk1.8.0_151/jre     
CLASS_PATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib
PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin
export JAVA_HOME JRE_HOME CLASS_PATH PATH
#注意：其中 JAVA_HOME， JRE_HOME 请根据自己的实际安装路径及 JDK 版本配置。
#让修改生效：
source /etc/profile
# 5.测试
java -version
#显示 java 版本信息，则说明 JDK 安装成功：
java version "1.8.0_151"
Java(TM) SE Runtime Environment (build 1.8.0_151-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.151-b12, mixed mode)
```

### Java介绍

| Java平台 | 说明       |
| -------- | ---------- |
| JavaSE   | 标准版     |
| JavaEE   | 企业版     |
| J2ME     | 嵌入式系统 |

| Java | 说明                                       |
| ---- | ------------------------------------------ |
| JDK  | Java Development Kit（Java开发工具包）。   |
| JRE  | Java Runtime Environment（Java运行环境）。 |

<img src="../../pictures/62a9a0e609475431290f52bb.png" width="300"/>  

- Java是一种简单的、面向对象的、分布式的、强壮的、安全的、体系结构中立的、高性能的、多线程的和动态的语言。
- **Java是单继承的语言**， 其接口可以实现多继承。

## 基本语法

- Java语言是面向对象的程序设计语言，Java程序的基本组成单元就是类，类体中又可包括属性与方法两部分。
- 主类：包含main()方法的类。每一个Java本地应用程序都必须包含一个main()方法，main()方法为Java应用程序的入口。
- 一个完整的主类结构通常由定义包语句、定义类语句、定义主方法语句、定义变量语句和导入API类库5部分组成。

### 变量

**命名规则**

- 26个英文字母大小写，0-9，_，$。
- 数字不可以开头。
- 不能使用关键字和保留字。
- 严格区分大小写。
- 不能包含空格。

| 命名规范      | 说明                                                         | 示例    |
| ------------- | ------------------------------------------------------------ | ------- |
| 包名          | 多单词时都小写                                               | xxxyyy  |
| 类名/接口名   | 多单词时每个单词首字母大写                                   | XxxYy   |
| 变量名/方法名 | 多单词时第一个单词首字母小写，<br />第二个单词开始每个单词首字母大写 | xxxYyy  |
| 常量名        | 所有字母都大写，多单词用下划线连接                           | XXX_YYY |

| 变量     | 声明位置         | 具体类型                                   | 初始化值特点           |
| -------- | ---------------- | ------------------------------------------ | ---------------------- |
| 成员变量 | 方法体外且类体内 | 实例变量<br />类变量（static）             | 生命周期               |
| 局部变量 | 方法体内         | 形参<br />方法局部变量<br />代码块局部变量 | 除形参外，均要显式初始 |

- 变量：内存中的一个存储区域，该区域的数据可以在同一类型范围内不断变化。变量是程序中最基本的存储单元，包含变量类型、变量名、存储的值。  

- 定义变量：告诉编译器（compiler）这个变量是属于哪一种数据类型，这样编译器才知道需要配置多少空间给它，以及它能存放什么样的数据。

1. Java每个变量必须先声明，后使用。
2. 使用变量名来访问这块区域的数据。
3. 变量的作用域：其定义所在的一对`{}`（函数体）内，变量只有在其作用域内才有效。
4. 同一个作用域内，不能定义重名的变量。

- 系统的内存可大略的分为3个区域：系统区（OS）、程序区（Program）、数据区（Data）。当程序执行时，程序代码会加载到内存中的程序区，数据暂时存储在数据区中。

<img src="../../pictures/567682923220659.png" width="400"/> 

### 数据类型

# 高级特性

## Lambda

> 从Java8开始支持Lambda。

- Lambda实质上是返回一个匿名对象。

```java
(lambda参数) -> {lambda体}
```

```java
//无参无返回值
Runnable r2 = () -> System.out.println("我是中国人");
//有参无返回值
Consumer<String> consumer1 = (String s) -> {
    System.out.println(s);
};
//类型推断，数据类型可以省略，可由编译器推断得出。
Consumer<String> consumer1 = (s) -> {
    System.out.println(s);
};
//Lambda如果只需要一个参数，参数的小括号可以省略。
Consumer<String> consumer = s -> {
    System.out.println(s);
};
//Lambda需要两个或以上的参数，多条执行语句，并且可以有返回值
Comparator<Integer> comparator1 = (o1, o2) -> {
    System.out.println(o1);
    System.out.println(o2);
    return o1.compareTo(o2);
};
//当Lambda体只有一条语句时，return与大括号可以省略。
Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
```

### Lambda作用域

- 不继承父类的属性。

**变量遮蔽与捕获**

- 变量遮蔽：内层作用域中声明的变量会覆盖外层作用域中同名的变量。

  - 只能隐藏和其它作用域中的变量名相同的变量，而不能创建一个同名的新变量。

- 变量捕获：隐式的引用外部作用域变量，而不是使用同名局部变量。

  1. 捕获局部变量：值传递，捕获变量的值不能修改。

  - 局部变量应该声明为final 。
  - 局部变量和 Lambda 表达式中的参数不能同名。
  - 引用类型的局部变量（数组等）：lambda可以对其属性内容进行修改。

  2. 捕获实例变量和静态变量：引用

  - 线程安全问题：如果 Lambda 表达式中需要修改实例变量或静态变量，必须使用 `Atomic` 类或者 `synchronized` 方法来保证线程安全，或者将变量作为参数传递给 Lambda 表达式中进行修改。

- this、super：lambda体中的this指向的是lambda体外层环境的类对象、类对象的父类，而不是引用自身。

```java
//局部变量捕获
public void lambdaDemo01() {
    int t = 1;
    int[] arr = {1,2,4};
    
    Runnable r1 = () -> {
        //t = 2; 值传递：捕获变量的值不能修改。
        System.out.println(t);
        //引用类型的局部变量（数组等）：lambda可以对其属性内容进行修改
        arr[0] = 3;
    };
    
    //Runnable r2 = t -> System.out.println(t);
    //局部变量和 Lambda 表达式中的参数不能同名  
}
```

```java
//实例变量捕获
class Foo {
    int i,j;
    int[] arr = {1,2,3};
    
    IntUnaryOperator iuo = i -> {
        //不与lambda中参数同名：没有被变量遮蔽
        j = 2; //Foo成员变量
        arr[0] = 2; //Foo成员变量
        
        //this代表Foo对象，Foo的成员变量i
        this.i = 1;
        
        //变量遮蔽：以下的i，j不是Foo的成员变量
        i = 2;
        int j = 3; //lambda中的局部变量
        return i + j;
    };
}
```

### 函数式接口

> OOF：面向函数编程。

- 函数式接口`@FunctionalInterface`：只声明了一个抽象方法的接口。
- lambda前提：对函数式接口实例的引用有效（上下文提供了合适的目标类型）。

| 类型   | 函数式接口<br />java.util.function | 方法              |
| :----- | :--------------------------------- | :---------------- |
| 消费型 | `Consumer<T>`                      | void accept(T t)  |
| 供给型 | `Supplier<T>`                      | T get()           |
| 函数型 | `Function<T,R>`                    | R apply(T t)      |
| 断定型 | `Predicate<T>`                     | boolean test(T t) |

<img src="../../pictures/functionInterface.svg"/>

### 目标类型

- 目标类型：lambda会实现一个函数式接口，上下文需要提供足够多的信息才能推断出确切的类型。

**提供合适目标类型的上下文**

| 上下文           | 目标类型                                     |
| ---------------- | -------------------------------------------- |
| 方法或构造器参数 | 参数类型                                     |
| 变量声明与赋值   | 被赋值的类型                                 |
| 返回语句         | 方法的返回类型                               |
| lambda体         | lambda体所期望的类型<br />由外层目标类型推导 |
| 三元表达式       |                                              |
| 类型转换表达式   | 显式提供                                     |

```java
//变量声明与赋值
Comparator<String> comparator = (s1, s2) -> s1.compareToIgnoreCase(s2);
//返回语句
public Runnable getRunner(){
    return () -> System.out.println("run");
}
//lambdaa体
Callable<Runnable> callable = () -> () -> System.out.println("run");
//三元表达式
boolean flag = true;
Callable<Integer> callable1 = flag ? (() -> 1) : (() -> 2);
//类型转换表达式
Object o = (Supplier) () -> "sup";
```

### 原生特化

- 原生特化：使用原生类型替换类型参数。

| 函数式接口         | 方法                        |
| ------------------ | --------------------------- |
| `LongFunction<R>`  | R apply(long value)         |
| `ToIntFunction<T>` | int applyAsInt(T value);    |
| LongToIntFunction  | int applyAsInt(long value); |

### 方法引用

- 方法引用：使用已经实现的方法来完成特定操作。

- 实现接口的抽象方法的参数列表和返回值类型，必须与方法引用的方法的参数列表和返回值类型保持一致。

| 方法引用类型 | 格式                                                   |
| ------------ | :----------------------------------------------------- |
| 静态         | `类::静态方法名`                                       |
| 绑定实例     | `对象::实例方法名`                                     |
| 未绑定实例   | `类::实例方法名`<br />第一个参数作为第二个参数的调用者 |
| 构造器       | `类::new`                                              |

```java
//静态方法引用 引用Integer的compare方法
Integer[] arr = {1, 2, 3};
Arrays.sort(arr,Integer::compare);

Comparator<String> comparator1 = String::compareTo;

//实例方法引用 引用System.out PrintStream对象的print方法
List<String> list = Arrays.asList("a", "b", "c");
list.forEach(System.out::print); //abc

Consumer<String> consumer = ps::println;
consumer.accept("你好");

Comparator<Integer> comparator = Integer::compare;
System.out.println(comparator.compare(12, 23));

//未绑定实例方法引用 通过comparing创建Comparator
Comparator comparator = Comparator.comparing(Person::getName);

//构造器引用 创建文件对象a.txt、b.txt
Stream<File> fileStream = Stream.of("a.txt", "b.txt")
    .map(File::new);

//空参构造器引用：
Supplier<List<String>> supplier = ArrayList::new;
List<String> stringList = supplier.get();
//带一个参数的构造器
Function<Integer, Employee> function = Employee::new;
System.out.println(function.apply(1002));
//带两个参数的构造器
BiFunction<Integer, String, Employee> biFunction = Employee::new;
System.out.println(biFunction.apply(1003, "Jac"));
```

### 类型推断、重载解析

- 聚合表达式：类在一定程度上由上下文决定的lambda表达式。

```java
//Comparator部分代码
default <U> Comparator<T> thenComparing(
    Function<? super T, ? extends U> keyExtractor,
    Comparator<? super U> keyComparator)
{
    return thenComparing(comparing(keyExtractor, keyComparator));
}

default <U extends Comparable<? super U>> Comparator<T> thenComparing(
    Function<? super T, ? extends U> keyExtractor)
{
    return thenComparing(comparing(keyExtractor));
}

default Comparator<T> thenComparingInt(ToIntFunction<? super T> keyExtractor) {
    return thenComparing(comparingInt(keyExtractor));
}
```

#### 类型检查

- 类型检查：函数式接口的函数类型。
- 函数类型：函数式接口的唯一抽象方法的参数类型、返回类型、抛出异常类型。

| 条件     | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| 参数数量 | lambd要与函数类型具有相同的参数数量                          |
| 参数类型 | 如果lambda的类型是显式声明的，则需要与函数类型的参数类型匹配 |
| 返回类型 | 函数类型返回void：lambda必须是语句表达式、没有返回值的块体<br />函数类型有返回值：lambda必须返回和该返回值兼容的值 |
| 抛出类型 | lambda检查抛出异常的前提：<br />函数类型声明抛出了该异常 或 该异常的父类 |

- 对于抛出类型：需要在函数式接口中的唯一抽象方法声明异常抛出，否则编译不通过。

#### 重载解析

- 选择和所需要的更接近的结果的方法。

```java
default Comparator<T> thenComparingInt(ToIntFunction<? super T> keyExtractor) {}
default Comparator<T> thenComparingLong(ToLongFunction<? super T> keyExtractor){}
```

- 对于方法引用的重载：

```java
//构造器
public Exception(){}
public Exception(String msg){} 

//重载方法
<T> void foo(Supplier<T> factory){}
<T,U> void foo(Function<T,U> transformer){}

//错误调用
foo(Excption::new);

//显式指定接收者
this.<Exception>foo(Exception::new);
this.<String,Exception>foo(Exception::new);
```

```java
//重载方法
void bar(IntFunction<String> f){}
void bar(DoubleFunction<String> f){}

//强制类型
bar((IntFunction<String>) String::valueOf());
bar((DoubleFunction<String>) String::valueOf());
```


## Stream 流

- Stream是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。

1. Stream 不持有值，不存储元素。
2. Stream 不会改变源对象。相反，会返回一个持有结果的新Stream。
3. 延迟计算：Stream只有进行了终止操作才会开始计算结果。
4. 串行处理、并行处理。

| 类型       | 说明                                             |
| ---------- | ------------------------------------------------ |
| Collection | 静态的内存数据结构，主要面向内存，存储在内存中。 |
| Stream     | 有关计算的，主要面向 CPU，通过 CPU 实现计算。    |

| 对比               | Stream                         | Iterator                   |
| ------------------ | ------------------------------ | -------------------------- |
| 处理值             | 无序                           | 有序                       |
| 可操作性           | 中间操作                       | 逐个进行                   |
| 保留值             | 保留值本身、关于源的属性的信息 | 只保留值本身               |
| null（无元素处理） | 友好的处理                     | hasNext()返回false，易出错 |

<img src="../../pictures/Snipaste_2022-11-19_13-07-50.png" width="700"/>

1. 数据源：创建Stream，获取一个流。
2. 管道：中间操作链，对数据源的数据进行处理。
3. 终止流：一旦执行终止操作，就执行中间操作链，并产生结果，之后流被耗尽，不能被再次使用。

### 数据源

| 数据源              | 方法                                                         | 类型       |
| ------------------- | ------------------------------------------------------------ | ---------- |
| 集合 Collection接口 | `default Stream<E> stream()`                                 | 顺序流     |
|                     | `default Stream<E> parallelStream()`                         | 并行流     |
| 数组                | `static <T> Stream<T> stream(T[] array)`                     | 流         |
| Stream#of()         | `public static<T> Stream<T> of(T... values)`                 | 返回一个流 |
| 无限流              | `public static<T> Stream<T> iterate(final T seed,final UnaryOperator<T> f)` | 迭代       |
|                     | `public static<T> Stream<T> generate(Supplier<T> s)`         | 生成       |

- 原生流：避免基本数据类型和包装类之间频繁的自动装箱和自动拆箱。

| 原生流       | 对应类型          | 流类型转换                   | 装箱    | 拆箱       |
| ------------ | ----------------- | ---------------------------- | ------- | ---------- |
| IntStream    | char、short、byte | asLongStream、asDoubleStream | boxed() | mapToInt() |
| LongStream   | long              | asDoubleStream               | boxed() |            |
| DoubleStream | float、double     |                              | boxed() |            |

### 管道（中间操作）

- 惰性求值：多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理，而在终止操作时一次性全部处理。

| 操作       | 方法                           | 说明                                                         |
| ---------- | ------------------------------ | ------------------------------------------------------------ |
| 筛选与切片 | filter(Predicate p)            | 接受Lambda，从流中排除某些元素                               |
|            | distinct()                     | 筛选，通过流所生成元素的hashCode()和equals()去除重复元素     |
|            | limit(long maxSize)            | 截断流，使其元素不超过给定数量                               |
|            | skip(long n)                   | 跳过元素，返回一个丢弃了前n个元素的流<br />若流中元素不足n个，则返回一个空流。<br />与limit(n)互补 |
| 映射       | map(Function f)                | 接收一个函数作为参数，该函数应用到每个元素上<br />并将其映射为一个新的元素。 |
|            | mapToDouble(ToDoubleFuntion f) | 接收一个函数作为参数，该函数应用到每个元素上<br />产生一个新的DoubleStream |
|            | mapToInt(ToIntFuntion f)       | 接收一个函数作为参数，该函数应用到每个元素上<br />产生一个新的IntStream |
|            | mapToLong(ToLongFuntion f)     | 接收一个函数作为参数，该函数应用到每个元素上<br />产生一个新的LongStream |
|            | flatMap(Function f)            | 接收一个函数作为参数，将流中的每个只都换成另一个流，然后把所有的流连接成一个流。 |
| 排序       | sorted()                       | 产生一个新流，其中按自然顺序排序                             |
|            | sorted(Conparator com)         | 产生一个新流，其中按比较器顺序排序                           |

### 终止流

- 终端操作会从流的流水线生成结果。其结果可以是任何不是流的值，例如：List、Integer，甚至是 void 。
- 流进行了终止操作后，不能再次使用。

| 操作       | 方法                             | 说明                                                         |
| ---------- | -------------------------------- | ------------------------------------------------------------ |
| 匹配与查找 | allMatch(Predicate p)            | 检查是否匹配所有元素                                         |
|            | anyMatch(Predicate p)            | 检查是否至少匹配一个元素                                     |
|            | noneMatch(Predicate p)           | 检查是否没有匹配所有元素                                     |
|            | findeFirst()                     | 返回第一个元素                                               |
|            | findAny()                        | 返回当前流中的任意元素                                       |
|            | count()                          | 返回流中元素总数                                             |
|            | max(Comparator c)                | 返回流中最大值                                               |
|            | min(Comparator c)                | 返回流中最小值                                               |
|            | forEach(Consumer c)              | 内部迭代：Stream<br />外部迭代：Collection接口               |
| 归约       | reduce(T iden,BinaryOperrator b) | 将流中元素反复结合，得到新的值，返回T                        |
|            | reduce(BinaryOperrator b)        | 将流中元素反复结合，得到新的值，返回`Optional<T>`            |
| 收集       | collect(Collector c)             | 将流转为其他形式。<br />接收一个Collector接口的实现，用于给Stream中元素做汇总的方法。<br />Collector 接口中方法的实现决定了如何对流执行收集的操作(如收集到 List、Set、Map) |

### Collectors 收集器

### Optional 容器

- `Optional<T> `
  - 保存类型T的值，代表这个值存在：`isPresent()`返回true，调用`get()`方法会返回该对象。
  - 保存null，表示这个值不存在，可以避免空指针异常。


| 操作                   | 方法                                                     | 说明                                                         |
| ---------------------- | -------------------------------------------------------- | ------------------------------------------------------------ |
| 创建Optional实例       | Optional.of(T t)                                         | 创建一个Optional实例<br />t必须非null                        |
|                        | Optional.empty()                                         | 创建一个空的Optional实例                                     |
|                        | Optional.ofNullable(T t)                                 | 创建一个Optional实例<br />t可以为null                        |
| 判断容器是否包含对象   | boolean isPresent()                                      | 是否包含对象                                                 |
|                        | `void ifPresent(Consumer<? super T> consumer)`           | 如果有值，执行Consumer接口的实现代码，并且该值作为参数传递给它 |
| 获取Optional容器的对象 | T get()                                                  | 如果调用对象包含值，返回该值，<br />否则抛出NoSuchElementException，不安全。 |
|                        | T orElse(T other)                                        | 如果有值，则将其返回<br />否则返回指定的other对象            |
|                        | `T orElseGet(Supplier<? extends T> other)`               | 如果有值，将其返回<br />否则返回由Supplier接口实现提供的对象 |
|                        | `T orElseThrow(Supplier<? extends X> exceptionSupplier)` | 如果有值，将其返回<br />否则抛出Supplier接口实现提供的异常   |