# String 字符串

- String（字符串）：Java 程序中的所有字符串、字面量都作为此类的实例实现。String字面量被认为是常量，不能更改。String是一个final类，代表不可变的字符序列，实现了Serializable接口、Comparable接口。

```java
// JDK8
private final char[] value;
// JDK9
@Stable
private final byte[] value;
private final byte coder;
```

- String的不可变性：

1. 字符串重新赋值时，需要重新指定内存区域赋值，不能使用原有的value进行赋值。
2. 对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
3. 调用String的replace()等方法修改指定的字符或字符串时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。

<img src="../../pictures/245100421227353.png" width="1000"/>    

## String类型转换

### String、基本数据类型/包装类

```java
//parseXxx() String-->基本数据类型/包装类
基本数据类型/包装类 变量 = 包装类.parseXxx(String str);

//基本数据类型/包装类-->String 
String 变量 = String.valueOf(基本数据类型/包装类); //调用String类重载的valueOf()。
String 变量 = 基本数据类型/包装类 + ""; //+连接符 
```

### String、char[]

```java
//String-->char[]

//String#getChars()：将指定索引范围内的字符串存放到char[]。
public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin){...};
  
//String#toCharArray()
String str1 = "abc123";
char[] chars = str1.toCharArray();
for(int i =0; i < chars.length; i++){
    System.out.println(chars[i]);
}
```

```java
//char[]-->String new String(char[] chars)
char chars[] = new char[]{'h','e','l','l','o'};
String str = new String(chars);
```

### String、byte[]

- 编码: 字符串-->字节（二进制数据）。
- 解码: 字节-->字符串（编码的逆过程）。 解码时使用的字符集必须与编码时使用的字符集相一致，否则乱码。

```java
//Sting-->byte[] 编码
//String#getBytes("指定编码集")：throws UnsupportedEncodingException。
byte[] bytes = "str".getBytes("utf-8");
```

```java
//byte[]-->String 解码
//String 字符串 = new String(字节数组,"指定编码集");
String str = new String(bytes,"utf-8");
```

```java
String str = "abc123中国";

//编码 使用默认的字符集UTF-8，进行转换，byte[]内为ASCII码。
byte[] utf8s = str.getBytes(); //[97, 98, 99, 49, 50, 51, -28, -72, -83, -27, -101, -67] 
//getBytes("gbk") 使用指定的gbk字符集编码
byte[] gbks = str.getBytes("gbk");
System.out.println(Arrays.toString(gbks));//[97, 98, 99, 49, 50, 51, -42, -48, -71, -6]

//默认的字符集utf-8解码
String str2 = new String(utf8s); //abc123中国
String str2 = new String(gbks);  //abc123�й� gbk和utf-8编码不一样，出现乱码
//指定解码的gbk字符集
String str4 = new String(gbks, "gbk"); //abc123中国
```

## 字符串拼接

### 字面量拼接

- 常量拼接（字面量、final）：参与拼接的都是常量、常量引用，结果在常量池（编译期优化），相当于String\#intern()。

> final在编译时就分配，准备阶段会显式初始化。

```java
// .java
String str = "a" + "b" + "c";
// .class 经过编译期优化：相当于调用intern()
String str = "abc";
```

### 变量拼接

- 变量拼接：只要拼接的一方是变量，则结果在堆，相当于new String()。
- `s1 = s1 + "b"; `：实际上原来的“a”字符串对象已经丢弃了，现在在堆空间中产生了一个字符串s1+"b"（也就是"ab")。如果多次执行这些改变串内容的操作，会导致大量副本字符串对象存留在内存中，降低效率。如果这样的操作放到循环中，会极大影响程序的性能

```java
String s1 = "javaEE";
String s2 = "hadoop";

String s3 = "javaEEhadoop";
String s4 = "javaEE" + "hadoop"; //编译期优化："javaEEhadoop"
//如果拼接符号的前后出现了变量，则相当于在堆空间中new String()，具体的内容为拼接的结果：javaEEhadoop
String s5 = s1 + "hadoop";
String s6 = "javaEE" + s2;
String s7 = s1 + s2;

System.out.println(s3 == s4);//true
System.out.println(s3 == s5);//false
System.out.println(s3 == s6);//false
System.out.println(s3 == s7);//false
System.out.println(s5 == s6);//false
System.out.println(s5 == s7);//false
System.out.println(s6 == s7);//false

String s8 = s6.intern();
System.out.println(s3 == s8);//true
```

- 变量间拼接的原理：`s7=s1+s2`。创建了新的StringBuilder、String对象。

1. String  s7 = new StringBuiler()：`new #9 <java/lang/StringBuilder>`
2. StringBuilder\#append()：s7.append("javaEE");、s7.append("hadoop");
3. StringBuilder\#toString()：s7.toString();

> JDK5及之前：StringBuffer。

- StringBuilder\#toString()：没有在字符串常量池存放字符串。

> new String("ab")存放到字符串常量池：`ldc #5 <ab>`。

```
 0 new #80 <java/lang/String>
 3 dup
 4 aload_0
 5 getfield #234 <java/lang/StringBuilder.value : [C>
 8 iconst_0
 9 aload_0
10 getfield #233 <java/lang/StringBuilder.count : I>
13 invokespecial #291 <java/lang/String.<init> : ([CII)V>
16 areturn
```

## String实例化方式

### new String("abc")

- new String("abc")的实例化方式一共创建了两个对象，（1） Java堆中的new结构；（2）byte\[\]对应的字符串常量池中的数据"abc"。

<img src="../../pictures/377974621232497.png" width="1260"/>    

```java
//通过字面量定义的方式：此时的s1和s2的数据javaEE声明在方法区中的字符串常量池中。
String s1 = "javaEE";
String s2 = "javaEE";
//通过new + 构造器的方式：此时的s3和s4保存的地址值，是数据在堆空间开辟空间以后对应的地址值。
String s3 = new String("javaEE");
String s4 = new String("javaEE");

System.out.println(s1 == s2);//true
System.out.println(s1 == s3);//false
System.out.println(s1 == s4);//false
System.out.println(s3 == s4);//false

Person p1 = new Person("Tom",12);
Person p2 = new Person("Tom",12);
System.out.println(p1.name == p2.name);//true
Person p3 = new Person(new String("Tom"),12);
Person p4 = new Person(new String("Tom"),12);
System.out.println(p1.name == p3.name);//false
System.out.println(p3.name == p4.name);//false
```

### intern()

- intern()：当调用intern方法时，如果池中已经包含一个由equals(object)方法确定的与此String对象相等的字符串，则返回池中的字符串。否则，将此String对象添加到池中，并返回对该String对象的引用。

> intern()的空间使用效率：对于大量重复存在的字符串（返回引用，而不是new），可节省空间。

```java
public native String intern();
```

```java
String s = new String("1"); //s指向的是new堆空间创建的，而不是字符串常量池中的
String s1 = s.intern(); //常量池中已经存在，返回池中的字符串，s1和s2相同
String s2 = "1"; //获取字符串常量池中的
System.out.println(s == s2);//jdk6：false   jdk7/8：false
```

```java
String s3 = new String("1") + new String("1"); //字符串常量池中不存在"11"
s3.intern(); //s3-"11"在字符串常量池中不存在，则将此String对象添加到池中，并返回对该String对象的引用。
//Jdk6：创建新的对象"11"，放入字符串常量池。
//Jdk7/8：字符串常量池中创建指向堆空间s3中的引用。
String s4 = "11"; //s4指向和上一行相同的字符串常量池中的地址
System.out.println(s3 == s4);//jdk6：false  jdk7/8：true
```

**对于s3.intern();**

- Jdk6及之前：串池新创建一个字符串"11"。如果字符串常量池中没有，将此对象复制一份，放入常量池中，并返回对该String对象的引用。
- Jdk7及之后：串池中没有创建字符串"11"，而是创建一个引用，指向当前堆中的字符串"11"。如果字符串常量池中没有，将此对象的引用地址复制一份，放入常量池中，并返回对该String对象的引用。

```java
String s3 = new String("1") + new String("1");//字符串常量池中不存在"11"
String s4 = "11"; //字符串常量池中创建"11"对象，且堆中也创建一个相应的new String() 
String s5 = s3.intern(); //s5地址和s4地址相同
System.out.println(s3 == s4);//jdk6：false  jdk7/8：false
```

# StringBuffer、StringBuilder

| 比较       | String   | StringBuffer           | StringBuilder            |
| ---------- | -------- | ---------------------- | ------------------------ |
| 不可变性   | 不可变   | 可变                   | 可变                     |
| 底层存储   | byte\[\] | byte\[\]               | byte\[\]                 |
| 安全、效率 | 效率最低 | 线程安全<br />效率较低 | 线程不安全<br />效率较高 |

```java
StringBuffer sb1 = new StringBuffer("abc");
sb1.setCharAt(0,'m');
System.out.println(sb1); //mbc StringBuffer可变性
```

  - StringBuffer(int capacity)、StringBuilder(int capacity)指定额外的数组长度（默认16）。
  - 底层数组扩容：默认情况下扩容为原来的2倍+2，同时将原有数组中的元素复制到新的数组中，如果还是不满足，则直接将该数组作为新的数组。

```java
String str = new String(); //new char[0];
String str1 = new String("abc"); //new char[3]{'a','b','c'};

StringBuffer sb1 = new StringBuffer(); //char[] value = new char[16];
System.out.println(sb1.length()); //0 而不是16
sb1.append('a'); //value[0] = 'a';
sb1.append('b'); //value[1] = 'b';

//此时，字符串常量池中已经存在"abc"
StringBuffer sb2 = new StringBuffer("abc"); // char[] value = new char["abc".length() + 16];
System.out.println(sb2.length()); //3 而不是19
```

# StringTable 字符串常量池

- StringTable（字符串常量池）中不会存放相同内容的常量，相同的字符串字面量（包括相同的Unicode字符序列：码点序列）必须指向同一个String对象。
- 基本数据类型的常量池由系统协调；而String常量池比较特殊，其最初为空，由String类私有地维护。

- StringTable位置演变：Jdk6及之前，字符串常量池存放在永久代。Jdk7及之后，由于permSize默认值较小、永久代垃圾收集频率低，字符串常量池存放在Java堆内。

## StringTableSize

- String Pool（StringTable）是固定大小的HashTable。放进String Pool的String过多时，会造成Hash冲突严重，导致链表过长、String\.intern()性能大幅下降。

```shell
# 设置StringTable长度 StringTableSize
-XX:StringTableSize
```

- StringTableSize默认长度，Jdk6及之前为1009、Jdk7及之后为60013。

- StringTableSize可设置的最小值，Jdk8之后为1009。

## StringTable的垃圾回收

> -XX:+PrintStringTableStatistics

- G1的String去重操作（针对堆空间中new的String）：equals()的结果为true的String对象。

1. 当垃圾收集器工作时，会访问堆上存活的对象。对每个访问的对象都会检查是否是候选的要去重的String对象。
2. 如果是，将该对象的一个引用插入到队列中等待后续的处理。一个去重的线程在后台运行，处理这个队列。处理队列的一个元素，从队列中删除该元素，并尝试去重其引用的String对象。
3. 使用一个HashTable来记录所有的被String对象使用的不重复的char数组。当去重时，会查找该HashTable，查看堆中是否已经存在一个一模一样的char数组。
4. 如果存在，String对象会被调整引用那个数组，释放对原来数组的引用，最终会被垃圾收集器回收。
5. 如果查找失败，char数组会被插入到HashTable中，之后就可共享该数组。

```
UseStringDeduplication 开启String去重，默认不开启
PrintStringDeduplicationStatistics 打印详细的去重统计信息
StringDeduplicationAgeThreshold 设置去重的String对象的年龄阈值，作为去重的候选对象。
```

<img src="../../pictures/StringTable的G1去重.svg" width="800"/> 

