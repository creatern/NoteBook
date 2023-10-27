# Lambda 对象表达式

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

# Lambda作用域

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

# 函数式接口

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

# 目标类型

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

# 原生特化

- 原生特化：使用原生类型替换类型参数。

| 函数式接口         | 方法                        |
| ------------------ | --------------------------- |
| `LongFunction<R>`  | R apply(long value)         |
| `ToIntFunction<T>` | int applyAsInt(T value);    |
| LongToIntFunction  | int applyAsInt(long value); |

# 方法引用

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

# 类型推断、重载解析

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

## 类型检查

- 类型检查：函数式接口的函数类型。
- 函数类型：函数式接口的唯一抽象方法的参数类型、返回类型、抛出异常类型。

| 条件     | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| 参数数量 | lambd要与函数类型具有相同的参数数量                          |
| 参数类型 | 如果lambda的类型是显式声明的，则需要与函数类型的参数类型匹配 |
| 返回类型 | 函数类型返回void：lambda必须是语句表达式、没有返回值的块体<br />函数类型有返回值：lambda必须返回和该返回值兼容的值 |
| 抛出类型 | lambda检查抛出异常的前提：<br />函数类型声明抛出了该异常 或 该异常的父类 |

- 对于抛出类型：需要在函数式接口中的唯一抽象方法声明异常抛出，否则编译不通过。

## 重载解析

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

# 
