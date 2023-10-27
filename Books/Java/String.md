# String 字符串

- String（字符串）：Java 程序中的所有字符串字面值都作为此类的实例实现。
- String是一个final类，代表不可变的字符序列，实现Serializable接口、Comparable接口。
- 字符串（双引号）是常量，值在创建之后不能更改。String内部定义了final char[] value用于存储字符串数据。
- 字符串常量池中是不会存储相同内容（String#equals()==true）的字符串的。

# 不可变性

1. 当字符串重新赋值时，需要重新指定内存区域赋值，不能使用原有的value进行赋值。
2. 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
3. 当调用String的replace()方法修改指定的字符或字符串时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。

<img src="../../pictures/245100421227353.png" width="562"/>    
<img src="../../../../pictures/11970921247519.png" width="564"/>    

<img src="../../pictures/185481721240188.png" width="551"/>    

```java
public class StringTest {
    String str = new String("good");
    char[] ch = {'t', 'e', 's', 't'};

    public void change(String str, char ch[]) {
        str = "test ok";
        ch[0] = 'b';
    }

    public static void main(String[] args) {
        StringTest ex = new StringTest();
        ex.change(ex.str, ex.ch);
        System.out.println(ex.str);//good
        //String的不可变性
        System.out.println(ex.ch);//best
    }
}
```

# 实例化

**String s = new String("abc")；方式创建对象，在内存中创建了几个对象？**

- 两个：一个堆空间中的new结构，一个char[]对应的常量池中的数据"abc"。

<img src="../../pictures/377974621232497.png" width="560"/>    
<img src="../../../../pictures/350640322245483.png" width="549"/>    
<img src="../../pictures/207800822226724.png" width="556"/>    

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

# String常量池

`intern()方法`

```java
String s1 = "javaEE";
String s2 = "hadoop";

//字面量：s3,s4都在常量池内
String s3 = "javaEEhadoop";
String s4 = "javaEE" + "hadoop";
//变量：堆空间中开辟
String s5 = s1 + "hadoop";
String s6 = "javaEE" + s2;
String s7 = s1 + s2;

System.out.println(s3 == s4); //true
System.out.println(s3 == s5); //false
System.out.println(s3 == s6); //false
System.out.println(s3 == s7); //false

System.out.println(s4 == s5); //false
System.out.println(s4 == s6); //false
System.out.println(s4 == s7); //false

System.out.println(s5 == s6); //false
System.out.println(s5 == s7); //false

System.out.println(s6 == s7); //false

String s8 = s5.intern();
//返回值得到的s8是使用常量池中已经存在的"javaEEhadoop"
System.out.println(s3 == s8); //true
```

- `String s1 = "a"; `：在字符串常量池中创建了一个字面量为"a"的字符串。
- `s1 = s1 + "b"; `：实际上原来的“a”字符串对象已经丢弃了，现在在堆空间中产生了一个字符串s1+"b"（也就是"ab")。如果多次执行这些改变串内容的操作，会导致大量副本字符串对象存留在内存中，降低效率。如果这样的操作放到循环中，会极大影响程序的性能。
- `String s2 = "ab";`：直接在字符串常量池中创建一个字面量为"ab"的字符串。
- `String s3 = "a" + "b";`：s3指向字符串常量池中已经创建的"ab"的字符串。
- `String s4 = s1.intern();`：堆空间的s1对象在调用intern()之后，会将常量池中已经存在的"ab"字符串赋值给s4。

# String类型转换

## String、基本数据类型/包装类

```java
//parseXxx() String-->基本数据类型/包装类
基本数据类型/包装类 变量 = 包装类.parseXxx(String str);

//基本数据类型/包装类-->String 
String 变量 = String.valueOf(基本数据类型/包装类); //调用String类重载的valueOf()。
String 变量 = 基本数据类型/包装类 + ""; //+连接符 
```

## String、char[]

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

## String、byte[]

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

# StringBuffer、StringBuilder

| 比较       | String   | StringBuffer                     | StringBuilder        |
| ---------- | -------- | -------------------------------- | -------------------- |
| 不可变性   | 不可变性 | 可变                             | 可变                 |
| 底层存储   | char[]   | char[]                           | char[]               |
| 安全、效率 | 效率最低 | 线程安全（sychronized)，效率较低 | 线程不安全，效率较高 |

```java
StringBuffer sb1 = new StringBuffer("abc");
sb1.setCharAt(0,'m');
System.out.println(sb1); //mbc StringBuffer可变性
```

  - `StringBuffer(int capacity) 或 StringBuilder(int capacity);`指定额外的数组长度（默认16）。
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
