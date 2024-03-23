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

# 旧的时间API

## Date

### java.util.Date

- java.util.Date：表示特定的瞬间，精确到毫秒。

| 构造器          | 说明               |
| --------------- | ------------------ |
| Date()          | 获取本地当前时间   |
| Date(long date) | 通过时间戳获取时间 |

| 方法       | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| getTime()  | 返回自 1970 年 1 月 1 日 00:00:00 GMT 以来，此Date 对象表示的毫秒数 |
| toString() | 将此 Date 对象转换为以下形式的 String：<br /><img src="../../pictures/166405516247526.png" width="300"/> |

```java
//空参构造器：创建当前时间的Date对象
Date date1 = new Date();
//Wed Oct 26 16:51:02 CST 2022
//星期 月 日  时间   时间标准 年

//时间戳
System.out.println(date1.getTime());
//1666774415833

//指定时间的构造器
Date date2 = new Date(1666774415833L);
System.out.println(date2);
//Wed Oct 26 16:53:35 CST 2022
```

### java.sql.Date

- java.sql.Date：对应数据库中的日期类型的变量，继承自java.util.Date。

```java
//创建java.sql.Date的对象
java.sql.Date date = new java.sql.Date(174242344342L);
System.out.println(date); //1975-07-11
```

- java.util.Date、java.sql.Date转换：

```java
//import java.util.Date：因此java.sql.Date需要使用全限定名来区分

//java.sql.Date --> java.util.Date
Date date1 = new java.sql.Date(13819381293819L);
java.sql.Date date2 = (java.sql.Date)date1;

//java.util.Date --> java.sql.Date
Date date3 = new Date();
//java.sql.Date date4 = (java.sql.Date)date3; 报错
java.sql.Date date4 = new java.sql.Date(date.getTime());
```

## Calendar 日历

- Calendar：抽象基类，主用用于完成日期字段之间相互操作的功能。

| 实例化                        | 说明 |
| ----------------------------- | ---- |
| Calendar.getInstance()        |      |
| 子类GregorianCalendar的构造器 |      |

- Calendar实例：系统时间的抽象表示，`get(int field)`取得想要的时间信息，如：YEAR、MONTH、DAY_OF_WEEK、HOUR_OF_DAY 、MINUTE、SECOND。

| 方法                                  | 说明                    |
| ------------------------------------- | ----------------------- |
| public int get(int field)             | 获取日历                |
| public void set(int field,int value)  | 设置时间                |
| public void add(int field,int amount) | 增加或减少时间 , 负数减 |
| public final Date getTime()           | 日历类-->Date类         |
| public final void setTime(Date date)  | Date类-->日历类         |

- 获取月份时：一月是0，二月是1，... ，12月是11
- 获取星期时：周日是1，周二是2 ... 周六是7

```java
//1.实例化
//方式一：创建子类GregorianCalendar的对象
//方式二：调用其静态方法getInstance()
Calendar calendar = Calendar.getInstance();
//System.out.println(calendar.getClass());
//class java.util.GregorianCalendar 仍然来自于其子类

//get()
int days = calendar.get(Calendar.DAY_OF_MONTH);
//get(Calendar.该类内部的静态属性)  具体查看API
System.out.println(days); //返回本个月的第几天
System.out.println(calendar.get(Calendar.DATE)); //返回-日

//set()
calendar.set(Calendar.DAY_OF_MONTH,22);
//修改calendar对象内的属性 //Calendar类的属性不变：不可变性
days = calendar.get(Calendar.DAY_OF_MONTH);
System.out.println(days); //22 返回修改后的日期

//add()
calendar.add(Calendar.DAY_OF_MONTH,3); //负数则减
//增加3天
days = calendar.get(Calendar.DAY_OF_MONTH);
System.out.println(days); //25 返回增加后的日期
calendar.add(Calendar.DAY_OF_MONTH,-1); //负数则减
//减1天
days = calendar.get(Calendar.DAY_OF_MONTH);
System.out.println(days); //24 返回减后的日期

//getTime() 日历类-->Date类
Date time = calendar.getTime();
System.out.println(time);
//Mon Oct 24 23:29:51 CST 2022

//setTime() Date类-->日历类
Date date = new Date();
calendar.setTime(date);
System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//27
```

# Instant、Duration 时间戳

- java.time包是基于纳秒计算的：Instant的精度可以达到纳秒级。 

> $$
> 1 ns = 10^{-9} s \\
> 
> 1s = 1000 ms = 10^6 us =10^9ns
> $$

| Instant                       | 时间戳<br />不可修改，返回的都是新的实例。                   |
| ----------------------------- | ------------------------------------------------------------ |
| now()                         | 返回当前时间的时间戳（Instant）。                            |
| ofEpochMilli(long epochMilli) | 静态方法，返回在1970-01-01 00:00:00基础上加上指定毫秒数之后的Instant类的对象 |
| atOffset(ZoneOffset offset)   | 结合即时的偏移来创建一个 OffsetDateTime                      |
| toEpochMilli()                | 返回1970-01-01 00:00:00到当前时间的毫秒数，即为时间戳        |
| **Duration**                  | **时间量：**秒数（long）+纳秒数（int）。<br />**不可修改，返回的都是新的实例。** |
| between()                     | 返回两个Instant之间的时间量（Duration）。                    |

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

- System\#currentTimeMillis\(\)：时间戳，返回当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差（long）。

# LocalDate、LocalTime、LocalDateTime 本地日期/时间

- LocalDate、LocalTime、LocalDateTime：不可变性，分别表示使用 ISO-8601日历系统的日期、时间、日期和时间，提供了简单的本地日期或时间，并不包含当前的时间信息，也不包含与时区相关的信息。

| LocalDate                                  | 本地日期（没有时区信息）IOS格式的日期（yyyy-MM-dd）          |
| ------------------------------------------ | ------------------------------------------------------------ |
| new()<br />ofXxx()                         | 静态，返回一个LocalDate。<br />当前时间、指定时间（月份从1开始）。 |
| plus()、minus()<br />plusXxx()、minusXxx() | 在当前时间上加/减一个Duration、Period。<br />在当前时间上加/减一个对应时间单位。 |
| withXxx()                                  | 返回一个新的（修改了指定时间部分的）LocalDate实例。          |
| getXxx()                                   | 获取指定单位的时间。                                         |
| until()                                    | 获取Period，两个日期之间的时长。<br />unit：按照指定的ChronUnit计算时长。 |
| isBefore()<br />isAfter()                  | 将当前的LocalDate和另一个LocalDate比较。                     |
| isLeapYear()                               | 是否为闰年。                                                 |
| **LocalTime**                              | **本地时间，当日时刻**                                       |
| \-                                         | 大部分方法和LocalDate相同                                    |
| plus操作<br />minus操作                    | 以24小时循环操作。                                           |
| **LocalDateTime**                          | **本地日期和时间**                                           |
| \-                                         | 大部分方法和LocalDate相同                                    |

> 对于不存在的日期，如2-31会返回当前月最后一个有效日期。

```java
LocalDate now = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
LocalDate post = LocalDate.of(2022, 1, 2);
long until = post.until(now, ChronoUnit.DAYS);
```

## TemporalAdjuster 日期调整器

- TemporalAdjuster（日期调整器）提供大量常见调整的静态方法，也可实现该接口来自定义调整方法。

- TemporalAdjuster接口实现的Lambda表达式的参数是Temporal，需要强转为LocalDate；而TemporalAdjusters.ofDateAdjuster()方法期望Lambda是`UnaryOperator<LocalDate>`，不需要强转。

```java
TemporalAdjuster NEXT_WORKDAY = TemporalAdjusters.ofDateAdjuster(w -> {
    LocalDate result = w;
    do {
        result = result.plusDays(1);
    } while (result.getDayOfWeek().getValue() >= 6);
    return result;
});
LocalDate localDate = LocalDate.of(2023, Month.OCTOBER,22).with(NEXT_WORKDAY);
```

# ZoneDateTime 时区时间（夏令时）

> [互联网编码分配管理机构](https://www.iana.org/time-zones)（Internet Assigned Numbers Authority，IANA）保存的IANA数据库存储者世界上所有已知的时区，Java使用该数据库。
>
> ```java
> //Java支持的所有时区ID
> Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
> ```

> 格林威治皇家天文台时间（Coordinated Universal Time，UTC）不考虑夏令时。

| ZoneId            | 时区ID                                        |
| ----------------- | --------------------------------------------- |
| of                | 返回指定id的ZoneId对象。                      |
| **ZoneDateTime**  | **时区日期时间**                              |
| -                 | 大部分的方法和LocalDateTime相同。             |
| **LocalDateTime** | **本地日期时间**                              |
| atZone()          | 指定ZoneId将LocalDateTime转换为ZoneDateTime。 |

- 对于跨越夏令时边界的日期，应该使用Period类设置间隔，而不应该使用Duration。
