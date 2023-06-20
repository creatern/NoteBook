# Object概述

- java.lang.Object（根父类）：所有的java类(除java.lang.Object类之外)都直接或间接的继承于java.lang.Object类。  

> 数组也可以作为Object类的子类出现，可以调用Object类中声明的方法。

- 如果在类的声明中未使用extends关键字指明其父类，则默认父类为java.lang.Object类。

```java
public class Object {

    @IntrinsicCandidate
    public Object() {}

    @IntrinsicCandidate
    public final native Class<?> getClass();

    @IntrinsicCandidate
    public native int hashCode();

    public boolean equals(Object obj) {
        return (this == obj);
    }

    @IntrinsicCandidate
    protected native Object clone() throws CloneNotSupportedException;

    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    @IntrinsicCandidate
    public final native void notify();

    @IntrinsicCandidate
    public final native void notifyAll();

    public final void wait() throws InterruptedException {
        wait(0L);
    }

    public final native void wait(long timeoutMillis) throws InterruptedException;

    public final void wait(long timeoutMillis, int nanos) throws InterruptedException {
        if (timeoutMillis < 0) {
            throw new IllegalArgumentException("timeoutMillis value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                "nanosecond timeout value out of range");
        }

        if (nanos > 0 && timeoutMillis < Long.MAX_VALUE) {
            timeoutMillis++;
        }

        wait(timeoutMillis);
    }

    @Deprecated(since="9", forRemoval=true)
    protected void finalize() throws Throwable { }
}
```

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

- String类中的equals()重写：

```java
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

- 返回一个int类型的哈希值，由对象中一般不变的属性映射得到。
- 相同对象的哈希值必须相同，不同对象的哈希值有可能相同。
- 子类如果重写了equals()方法，也必须重写hashCode()方法：对两个对象，如果equals()返回true，则其hashCode也必须相同。
- 哈希值通常是质数：

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
