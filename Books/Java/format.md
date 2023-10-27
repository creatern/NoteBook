# SimpleDateFormat

- java.text.SimpleDateFormat：不与语言环境有关的方式来格式化和解析日期的具体类。

| 构造器                           | 说明                                                       |
| -------------------------------- | ---------------------------------------------------------- |
| new SimpleDateFormat()           | 默认格式                                                   |
| new SimpleDateFormat("具体格式") | 指定方式格式化和解析<br />如：yyyyy.MMMMM.dd GGG hh:mm aaa |

- format()：格式化，日期-->字符串。

```
SimpleDateFormat对象.format(Date对象);
```

- parse()：解析，字符串-->日期  (格式化的逆过程)。
  - throws ParseException。

```
SimpleDateFormat对象.parse("识别的格式");
```

```java
@Test
public void testSimpleDateFormat() throws ParseException {
    //实例化SimpleDateFormat 使用默认的构造器
    SimpleDateFormat sdf = new SimpleDateFormat();

    Date date = new Date();
    System.out.println(date);
    //Thu Oct 27 22:11:08 CST 2022

    //格式化：日期-->字符串
    String format = sdf.format(date);
    System.out.println(format);
    //2022/10/27 下午10:11

    //解析
    String str = "2022/10/23 上午10:10";
    //格式要求默认："yyyy/MM/dd aaa hh:mm:ss " 不同版本不一样
    Date date1 = sdf.parse(str);
    System.out.println(date1);
    //Sun Oct 23 10:10:00 CST 2022

    //指定方式格式化和解析 调用带参的构造器
    //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    //格式化
    String format1 = sdf1.format(date);
    System.out.println(format1);
    //02022.十月.27 公元 10:20 下午
    //2022-23-27 10:23:55
    //解析: 要求字符串必须符合SimpleDateFormat识别的格式（通过构造器参数实现)
    //否则报错
    Date date2 = sdf1.parse("1999-12-31 23:59:59");
    System.out.println(date2);
    //Sun Jan 31 23:59:59 CST 1999
}
```

# DateTimeFormatter

- java.time.format.DateTimeFormatter：格式化与解析日期或时间

1. 预定义的标准格式：
   - ISO_LOCAL_DATE_TIME
   - ISO_LOCAL_DATE
   - ISO_LOCAL_TIME
2. 本地化相关的格式：如 ofLocalizedDateTime(FormatStyle.LONG)。
3. 自定义的格式：如 ofPattern(“yyyy-MM-dd hh:mm:ss”)。

| 方法                                        | 说明                                     |
| ------------------------------------------- | ---------------------------------------- |
| ofPattern(String pattern)                   | 静态方法 ， 返回一个指定字符串格式的     |
| DateTimeFormatterformat(TemporalAccessor t) | 格式化一个日期、时间，返回字符串         |
| parse(CharSequence text)                    | 将指定格式的字符序列解析为一个日期、时间 |

```java
@Test
public void test3(){
//方式1预定义的标准格式。
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
      //格式化 日期-->字符串
    LocalDateTime localDateTime = LocalDateTime.now();
    String str1 = dateTimeFormatter.format(localDateTime);
    dateTimeFormatter.format(localDateTime);
    System.out.println(str1);
    System.out.println(localDateTime);
      //2022-10-28T17:00:23.5674663
      //2022-10-28T17:00:23.567466300
      //解析 字符串-->日期
    TemporalAccessor parse = dateTimeFormatter.parse("2019-02-18T17:00:23.22222");
    System.out.println(parse);
       //{},ISO resolved to 2019-02-18T17:00:23.222220
//方式2本地化相关的格式。
    DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
    //格式化
    String format = dateTimeFormatter1.format(localDateTime);
    System.out.println(format);
    //2022/10/28 下午5:04

    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
    //格式化
    String format1 = formatter.format(LocalDate.now());
    System.out.println(format1);
    //2022年10月28日星期五

//方式3自定义的格式。
    DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    //格式化
    String str = dateTimeFormatter2.format(LocalDateTime.now());
    System.out.println(str);
    //2022-10-28 05:10:10

    //解析
    TemporalAccessor parse1 = dateTimeFormatter2.parse("2019-10-23 12:12:23");
    System.out.println(parse1);
    //{MilliOfSecond=0, HourOfAmPm=0, NanoOfSecond=0, SecondOfMinute=23, MinuteOfHour=12, MicroOfSecond=0},ISO resolved to 2019-10-23
}
```
