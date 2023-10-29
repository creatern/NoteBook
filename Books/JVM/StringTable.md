# String

## 底层存储

- JDK8及之前：

```java
private final char[] value;
```

- JDK9及之后：

```java
@Stable
private final byte[] value;
private final byte coder; //编码集的识别
```

## 不可变性

# StringTableSize

- String Pool（StringTable）：固定大小的HashTable。

- 放进String Pool的String过多时，会造成Hash冲突严重，导致链表过长，调用String.intern()时性能大幅下降。

- 设置StringTable长度：-XX:StringTableSize。

> StringTableSize默认长度：
>
> - Jdk6及之前：1009
>
> - Jdk7及之后：60013
>
> StringTableSize可设置的最小值：
>
> - Jdk8之后：1009

# 常量池

- 常量池中不会存放相同内容的常量：相同的字符串字面量（包括相同的Unicode字符序列：码点序列），必须指向同一个String对象。

> 常量池：基本数据类型的常量池由系统协调，String常量池比较特殊。
>
> 字符串池：最初为空，由String类私有地维护。

1. 字面量：声明的String对象直接存储在常量池中。
2. String#intern()。

> 字符串常量池位置的演变：
>
> - Jdk6及之前：字符串常量池存放在永久代。
> - Jdk7及之后：字符串常量池存放在Java堆内。
>   - permSize默认值较小。
>   - 永久代垃圾收集频率低。

# 字符串拼接

## 字面量拼接

- 常量拼接：参与拼接的都是常量、常量引用，结果在常量池（编译期优化），调用String#intern()。
  - 常量：字面量、final。

> final在编译时就分配，准备阶段会显式初始化

```java
// .java
String str = "a" + "b" + "c";
// .class 经过编译期优化：相当于调用intern()
String str = "abc";
```

## 变量拼接

- 变量拼接：拼接的一方是变量，结果在堆，new String()。

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
2. StringBuilder#append()：s7.append("javaEE");、s7.append("hadoop");
3. StringBuilder#toString()：s7.toString();

> JDK5及之前：StringBuffer。

- StringBuilder#toString()：没有在字符串常量池存放字符串。

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

# intern()

- intern()：当调用intern方法时，如果池中已经包含一个由equals(object)方法确定的与此String对象相等的字符串，则返回池中的字符串。否则，将此String对象添加到池中，并返回对该String对象的引用。

> intern()的空间使用效率：对于大量重复存在的字符串（返回引用，而不是new），可节省空间。

- new String()：共创建2个对象（字节码）：赋值的变量的引用是new的地址。
  - new堆空间创建的：new。
  - 字符串常量池中的对象：ldc。

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

- 对于s3.intern();
- Jdk6及之前：串池新创建一个字符串"11"。
  - 如果字符串常量池中没有，将此对象复制一份，放入常量池中，并返回对该String对象的引用。
- Jdk7及之后：串池中没有创建字符串"11"，而是创建一个引用，指向当前堆中的字符串"11"。
  - 如果字符串常量池中没有，将此对象的引用地址复制一份，放入常量池中，并返回对该String对象的引用。

```java
String s3 = new String("1") + new String("1");//字符串常量池中不存在"11"
String s4 = "11"; //字符串常量池中创建"11"对象，且堆中也创建一个相应的new String() 
String s5 = s3.intern(); //s5地址和s4地址相同
System.out.println(s3 == s4);//jdk6：false  jdk7/8：false
```

# StringTable的垃圾回收

> -XX:+PrintStringTableStatistics

- StringTable中存在垃圾回收机制。
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

