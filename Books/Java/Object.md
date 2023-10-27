# Object 根父类

- java.lang.Object（根父类）：所有的java类(除java.lang.Object类之外)都直接或间接的继承于java.lang.Object类。  

> 数组也可以作为Object类的子类出现，可以调用Object类中声明的方法。

- 如果在类的声明中未使用extends关键字指明其父类，则默认父类为java.lang.Object类。

# equals()

| ==                                                           | equals()                                                     |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| 基本类型比较值：只要两个变量的值相等，即为true。(不一定类型相等)<br>引用类型比较引用(是否指向同一个对象)：只有指向同一个对象时，==才返回true。 | 只能比较引用类型：比较两个对象的实体内容是否相同             |
| 符号两边的数据类型必须兼容(可自动转换的基本数据类型除外)，否则编译出错 | 将比较的字符串放在之前，避免可能出现的空指针异常：`"".equals(str)` |

```java
public boolean equals(Object obj){
    return (this == obj);
}
```

- File、String、Date及包装类（Wrapper Class）等：重写了Object类的equals()方法。

```java
//String中的equals()重写
public boolean equals(Object anObject) {
    if (this == anObject) {
        return true;
    }
    return (anObject instanceof String aString)
        && (!COMPACT_STRINGS || this.coder == aString.coder)
        && StringLatin1.equals(value, aString.value);
}
```

# hashCode()

- hashCode()：返回一个int类型的哈希值（通常是质数），由对象中一般不变的属性映射得到。相同对象的哈希值必须相同，不同对象的哈希值有可能相同。
- 子类如果重写了equals()方法，也必须重写hashCode()方法：对两个对象，如果equals()返回true，则其hashCode也必须相同。

1. 在程序运行时，同一个对象多次调用 hashCode() 方法应该返回相同的值。
2. 当两个对象的 equals() 方法比较返回 true 时，这两个对象的 hashCode() 方法的返回值也应相等。
3. 对象中用作 equals() 方法比较的 Field，都应该用来计算 hashCode 值。

> **问题：为什么复写hashCode方法，有31这个数字？**
>
> - 选择系数的时候要选择尽量大的系数。因为如果计算出来的hash地址越大，所谓的“冲突”就越少，查找起来效率也会提高。（减少冲突）
> - 31只占用5bits,相乘造成数据溢出的概率较小。
> - 31可以由`i*31== (i<<5)-1`来表示,现在很多虚拟机里面都有做相关优化。（提高算法效率）
> - 31是一个素数，素数作用就是如果我用一个数字来乘以这个素数，那么最终出来的结果只能被素数本身和被乘数还有1来整除！(减少冲突)

# toString() 

- 输出一个对象的引用时，实际上就是调用当前对象的toString()
- toString()方法在Object类中定义，其返回值是String类型，返回类名和它的引用地址。

> Java中的地址值并不是真实的物理地址值，而是hashCode算出来的虚拟地址值

```java
public String toString(){  
    return getClass().getName + "@" + Integer.toHexStrign(hashCode());
}
```

- String、Date、File、包装类等都重写了Object类中的toString()，自定义类也可以重写toString()方法，当调用此方法时，返回对象的实体内容。
- String与其它类型数据的连接时，自动调用toString()方法。
- 基本类型数据转换为String类型时，调用对应包装类的toString()方法。

```java
System.out.println("now=" + 1); 
//System.out.println("now=" + Integer.valueOf(1).toString());
```

- char[]数组（String的底层存储）输出字符串，其他数组都是地址值。

```java
char[] arr = new char[] { 'a', 'b', 'c' };
System.out.println(arr); //abc

int[] arr1 = new int[] { 1, 2, 3 };
System.out.println(arr1); //[I@37d31475
```
