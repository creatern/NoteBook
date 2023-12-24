# Stream 流

- Stream是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。

1. Stream 不持有值，不存储元素。
2. Stream 不会改变源对象，而是返回一个持有结果的新Stream。
3. 惰性执行：Stream只有进行了终止操作才会开始计算结果。

<img src="../../pictures/Snipaste_2022-11-19_13-07-50.png" width="700"/>

1. 数据源：创建Stream，获取一个流。
2. 管道：中间操作链，对数据源的数据进行处理。
3. 终止流：一旦执行终止操作，就执行中间操作链，并产生结果，之后流被耗尽，不能被再次使用。

```java
//流的惰性执行：只有执行终止操作时，流才对内部的元素处理。
List<Integer> il = new ArrayList<>();
Stream<Integer> is = il.stream();
il.add(1);
System.out.println(is.count());
```

# 流的创建

| 方法                                  | 返回的流                  |
| ------------------------------------- | ------------------------- |
| Collect#stream()<br />Arrays#stream() | StreamSupport构造的各种流 |
| Stream#of()                           | 元素为给定值的流          |
| Stream#empty()                        | 空的流                    |
| Files#lines()                         | 包含文件中所有行的流      |
| Pattern#splitAsStream()               | 界定分割字符串的流        |

```java
Files.lines(Path.of("/home/zjk/Desktop/temp01.md"), StandardCharsets.UTF_8)
    .toList()
```

```java
Pattern.compile("\\PL+").splitAsStream("t e s t")
    .toList()
```

## 无限流

| 无限流                                | 说明                                              |
| ------------------------------------- | ------------------------------------------------- |
| `iterate(T seed, UnaryOperator<T> f)` | 迭代。<br />seed：种子（初始值）<br />f：增长函数 |
| `generate(Suppulier<T> s)`            | 生成。<br />反复调用函数s                         |

```java
Stream.iterate(1, f -> f * 2)
    .limit(10)
    .max(Integer::compareTo)
```

```java
Stream.generate(() -> "Echo")
    .limit(10)
    .toList()
```

## 原生流（基本流）

- 原生流：避免基本数据类型和包装类之间频繁的自动装箱和自动拆箱。

| 基本流       | 对应类型                  | 流类型转换                       | 装箱（对象流） | 拆箱       |
| ------------ | ------------------------- | -------------------------------- | -------------- | ---------- |
| IntStream    | char<br />short<br />byte | asLongStream<br />asDoubleStream | boxed()        | mapToInt() |
| LongStream   | long                      | asDoubleStream                   | boxed()        |            |
| DoubleStream | float<br />double         |                                  | boxed()        |            |

## 并行流

| 并行流                                             | 产生的流                   |
| -------------------------------------------------- | -------------------------- |
| Stream#parallel()<br />Collection#parallelStream() | 与当前流中元素相同的并行流 |
| Stream#unordered()                                 | 与当前流中元素相同的无序流 |

- 只要在终结方法执行时，流处于并行模式，则所有的中间流操作都将被并行化。并行流中的操作应当可以以任意顺序执行，且要确保能安全地执行。

> 并行流使用fork-join池来操作流的各个部分。

# 管道（转换流）

- 流的转换会产生新的流，其元素派生自另一个流的元素。
- 转换流可以相互嵌套、不断转换。

| 过滤、映射 | 返回的流                                                     |
| ---------- | ------------------------------------------------------------ |
| filter()   | 满足过滤条件的流                                             |
| map()      | 将函数应用到所有元素后所产生结果的流                         |
| flatMap()  | 将函数应用到所有元素后所产生结果连接到一起的流<br />每个结果都是一个流 |

```java
Stream.of("t e s t".split("\\PL+"))
    .filter(v -> v.equals("t"))
    .toList()
```

```java
Stream.of("t e s t".split("\\PL+"))
    .map(String::toUpperCase)
    .toList()
```

| 抽取     | 返回的流          |
| -------- | ----------------- |
| limit()  | 包含前n个元素的流 |
| skip()   | 跳过前n个元素的流 |
| concat() |                   |

| 去重       | 返回的流                                             |
| ---------- | ---------------------------------------------------- |
| distinct() | 去重的流                                             |
| **排序**   | **返回的流**                                         |
| sorted()   | 排序的流<br />Comparator指定排序                     |
| **查看**   | **返回的流**                                         |
| peek()     | 每获取一个元素就传递给函数<br />该函数并不会改变元素 |

```java
Stream.iterate(1, f -> f * 2)
    .peek(System.out::println)
    .limit(10)
    .toList()
```

# 终结操作

## 约简

- 约简：将流约简为可使用的非流值。 

| 约简                                        | 返回long                                                     |
| ------------------------------------------- | ------------------------------------------------------------ |
| count()                                     | 流中元素的数量                                               |
| **约简**                                    | **返回Optional**                                             |
| max()<br />min()                            | 最大值、最小值<br />Comparator指定比较规则                   |
| findFirst()<br />findAny()                  | 第一个元素<br />任意一个元素                                 |
| anyMatch()<br />allMatch()<br />noneMatch() | 任意元素匹配时，断言true。<br />所有元素匹配时，断言true。<br />没有元素匹配时，断言true。 |
| 迭代                                        | 返回Iterator                                                 |
| iterator()                                  | 当前流元素的迭代器                                           |

### Optional

- `Optinal<T>`包装器对象，对于null有两种处理策略来避免空指针。

1. 值不存在时会产生一个可替代物，而不是null。
2. 只有在值存在的情况下才可以消费该值。

| 策略                         | 若Optinal为空（`"" != 空`）                                  |
| ---------------------------- | ------------------------------------------------------------ |
| orElse()                     | 产生指定的值                                                 |
| orElseGet()                  | 产生函数的结果                                               |
| orElseThrow()                | 抛出函数的异常                                               |
| isPresent()<br />isPresent() | 无操作<br />若Optinal不为空，则返回true                      |
| get()                        | 抛出NoSuchElementException                                   |
| map()                        | 若Optinal为空，产生空Optional<br />否则，产生该Optional的值传递给函数的结果 |
| flatMap()                    | 若Optinal为空，产生空Optional<br />否则，产生该Optional的值传递给函数的结果<br />每个结果都是一个Optional |

| Optional     | 产生的Optional                                               |
| ------------ | ------------------------------------------------------------ |
| of()         | 给定值的Optional<br />若值为null，则抛出NoSuchElementException |
| empty()      | 空的Optional                                                 |
| ofNullable() | 给定值的Optional<br /><br />若值为null，则产生空的Optional   |

### reduce()

| reduce()                                  | 返回组合之后的值                                             |
| ----------------------------------------- | ------------------------------------------------------------ |
| `U identity`                              | 幺元值，若流为空，则返回该幺元值。<br />若不设置且流为空，则返回空Optional。 |
| `BiFunction<U, ? super T, U> accumulator` | 累积器，约简操作。<br />应当是可结合的（组合可不按顺序），提供并行的解决。 |
| `BinaryOperator<U> combiner`              | 组合器，将分别累积的部分汇总。                               |

```java
Stream.iterate(1,f->f*2)
    .limit(10)
    .reduce(0,Integer::sum,Integer::sum)
```

> Rcollect()

## 收集

| 遍历      | 说明                                           |
| --------- | ---------------------------------------------- |
| forEach() | 流的每个元素都调用函数                         |
| **收集**  | **说明**                                       |
| collect() | 给定的收集器（Collectors）来收集流中的元素     |
| toArray() | 产生一个对象数组<br />引用构造器来指定数组类型 |

```java
Stream.iterate(1, f -> f * 2)
    .limit(10)
    .toArray(Integer[]::new);
```

### Collectors

| Collectors                                                   | 生成的收集器                                                 |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| toCollection()<br />toList()、toSet()                        | 将元素收集到指定集合的收集器                                 |
| joining()                                                    | 连接字符串<br />指定字符串之间的分隔符<br />                 |
| summarizingInt()<br />summarizingLong()<br />summarizingDouble() | (Int/Long/Double)SummaryStatistics对象的收集器<br />需要指定数值的转换函数 |

```java
Stream.iterate(1, f -> f * 2)
    .limit(10)
    .collect(Collectors.toCollection(ArrayList<Integer>::new))
    .forEach(System.out::println); //ArrayList的forEach
```

```java
Stream.of(new String[]{"abc","def"})
    .collect(Collectors.joining(", ","pre-[","]-suf"))
```

#### SummaryStatistics

| XxxSummaryStatistics   | 产生的值                                                     |
| ---------------------- | ------------------------------------------------------------ |
| getCount()             | 汇总后的元素的个数                                           |
| getSum()               | 汇总后的元素的总和<br />没有元素时，返回0                    |
| getAverage()           | 汇总后的元素的平均值<br />没有元素时，返回0                  |
| getMax()<br />getMin() | 汇总后的元素的最大值、最小值<br />没有元素时，返回Xxx.MaxValue、Xxx.MinValue |

```java
Stream.iterate(1, f -> f * 2)
    .limit(10)
    .collect(Collectors.summarizingInt(Integer::valueOf))
    .getAverage()
```

#### toMap() 映射表

| toMap() 函数引元                               | 说明                                                         |
| ---------------------------------------------- | ------------------------------------------------------------ |
| `Function<? super T, ? extends K> keyMapper`   | key                                                          |
| `Function<? super T, ? extends U> valueMapper` | value                                                        |
| `BinaryOperator<U> mergeFunction`              | 若存入的键值对的key已经存在，采取的策略<br />否则IllegalStateException |
| `Supplier<M> mapFactory`                       | Map的具体类型                                                |

```java
Stream.of(Locale.getAvailableLocales())
        .collect(
                Collectors.toMap(
                        Locale::getDisplayLanguage,
                        Function.identity(),
                        (existingValue, newValue) -> existingValue,
                        TreeMap::new
                ))
        .forEach((k, v) -> System.out.println(k + ":" + v)); //Map的forEach
```

> toConcurrentMap()：并发映射表。

#### 群组、分区

| 群组             | 产生的映射表                                        |
| ---------------- | --------------------------------------------------- |
| groupingBy()     | 函数应用于每个元素的结果，相同key的value组成列表。  |
| **分区**         | **产生的映射表**                                    |
| partitioningBy() | key是true/false，由满足条件与否的元素分别组成列表。 |

```java
Stream.of(Locale.getAvailableLocales())
    .collect(Collectors.groupingBy(Locale::getCountry))
    .forEach((k, v) -> System.out.println(k + ":" + v));
```

```java
Stream.of(Locale.getAvailableLocales())
    .collect(Collectors.partitioningBy(l -> l.getLanguage().equals("en")))
    .forEach((k, v) -> System.out.println(k + ":" + v));
```

> groupingByConcurrent()：并行流组装的并行映射表。

#### 下流收集器

- 下流收集器应该搭配群组和分区一起使用，处理下流映射表中的值。

| Collectors                                           | 返回值                                                       |
| ---------------------------------------------------- | ------------------------------------------------------------ |
| summingInt()<br />summingLong()<br />summingDouble() | 函数应用于每个元素后计算总和。                               |
| maxBy()<br />minBy()                                 | 返回Optional：元素的最大值、最小值<br />Comparator指定排序。 |
| mapping()                                            | 函数应用于每个元素的结果作为key<br />指定收集器来收集相同key的元素。 |

```java
Stream.of(Locale.getAvailableLocales())
    .collect(Collectors.groupingBy(
        Locale::getCountry,
        Collectors.mapping(Locale::getDisplayLanguage,
                           Collectors.maxBy(String::compareTo))))
    .forEach((k, v) -> System.out.println(k + ":" + v.orElse("none")));
```
