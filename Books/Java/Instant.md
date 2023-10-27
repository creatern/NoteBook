# 日期时间API

> 1. jdk 1.0 Date类。
> 2. jdk 1.1 Calendar类，一定程度上替换Date类。
> 3. jdk 1.8 提出一套新的API：
>    - java.time – 包含值对象的基础包
>    - java.time.chrono – 提供对不同的日历系统的访问
>    - java.time.format – 格式化和解析时间和日期
>    - java.time.temporal – 包括底层框架和扩展特性
>    - java.time.zone – 包含时区支持的类
>
> **前两代的问题**
>
> - 可变性：像日期和时间这样的类应该是不可变的。
> - 偏移性：Date中的年份是从1900开始的，而月份都从0开始。
> - 格式化：格式化只对Date有用，Calendar则不行。
> - 线程不安全；不能处理闰秒等。

> **计算世界时间的主要标准有：**
>
> - UTC（Coordinated Universal Time）
> - GMT（Greenwich Mean Time）
> - CST（Central Standard Time）

# Instant、Duration 时间戳

- java.time包是基于纳秒计算的：Instant的精度可以达到纳秒级。 

> $$
> 1 ns = 10^{-9} s \\
> 
> 1s = 1000 ms = 10^6 us =10^9ns
> $$

| Instant      | 时间戳<br />不可修改，返回的都是新的实例。                   |
| ------------ | ------------------------------------------------------------ |
| now()        | 返回当前时间的时间戳（Instant）。                            |
| **Duration** | **时间量：**秒数（long）+纳秒数（int）。<br />**不可修改，返回的都是新的实例。** |
| between()    | 返回两个Instant之间的时间量（Duration）。                    |

| Instant、Duration                              | 说明                                                         |
| ---------------------------------------------- | ------------------------------------------------------------ |
| plus()、minus()<br />plusXxx()、minusXxx()     | 在当前的Instant/Duration上加/减一个Duration。<br />在当前的Instant/Duration上加/减一个对应时间单位。 |
| multipliedBy()<br />dividedBy()<br />negated() | 返回当前的Duration的相应运算结果。<br />可以缩放Duration、而不能缩放Instant。 |
| isZero()<br />isNegative()                     | 判断当前的Duration是否为0/负值。                             |

```java
Instant start = Instant.now();
Instant end = Instant.now();
Duration between = Duration.between(start, end);
Duration plusDays = between.plusDays(1);
//between.plusDays(1) != between
//between.plusDays(1) != plusDays
```

# LocalDate、LocalTime 本地日期/时间

| LocalDate                                  | 本地日期（没有时区信息）                                     |
| ------------------------------------------ | ------------------------------------------------------------ |
| new()<br />ofXxx()                         | 静态，返回一个LocalDate。<br />当前时间、指定时间（月份从1开始）。 |
| plus()、minus()<br />plusXxx()、minusXxx() | 在当前时间上加/减一个Duration、Period。<br />在当前时间上加/减一个对应时间单位。 |
| withXxx()                                  | 返回一个新的（修改了指定时间部分的）LocalDate实例。          |
| getXxx()                                   | 获取指定单位的时间。                                         |
| until()                                    | 获取Period，两个日期之间的时长。<br />unit：按照指定的ChronUnit计算时长。 |
| isBefore()<br />isAfter()                  | 将当前的LocalDate和另一个LocalDate比较。                     |
| isLeapYear()                               | 是否为闰年。                                                 |

> 对于不存在的日期，如2-31会返回当前月最后一个有效日期。

```java
LocalDate now = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
LocalDate post = LocalDate.of(2022, 1, 2);
long until = post.until(now, ChronoUnit.DAYS);
```

# TemporalAdjusters 日期调整器

| TemporalAdjusters | 日期调整器 |
| ----------------- | ---------- |
|                   |            |
