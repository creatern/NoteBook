- 享元模式 Flyweight（蝇量模式）：运用共享技术有效地支持大量细粒度的对象。

1. 解决重复对象的内存浪费问题：对象的状态大部分可以外部化时，将大量对象的创建改为从池中获取。
2. 池技术：StringTable、数据库连接池、缓冲池、SingletonObject。

<img src="../../pictures/设计模式-Flyweight.drawio.svg" width="700"/> 

| 对象的信息 | 说明                                                     | 示例：围棋          |
| ---------- | -------------------------------------------------------- | ------------------- |
| 内部状态   | 对象共享的信息，存储在享元对象内部且不随环境改变而关闭。 | 棋子的颜色（黑/白） |
| 外部状态   | 对象得以依赖的标记，随环境改变而改变、不可共享。         | 棋子在棋盘上的位置  |

- Integer#valueOf()的缓存技术（`-128~127`）：

```java
@IntrinsicCandidate
public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}

cache = archivedCache;
// range [-128, 127] must be interned (JLS7 5.1.7)
assert IntegerCache.high >= 127;
```

```java
Integer i1 = Integer.valueOf(127);
Integer i2 = Integer.valueOf(127);
System.put.println(i1 == i2); //true;

Integer j1 = new Integer(127);
Integer j2 = new Integer(127);
System.put.println(j1 == j2); //false;
System.put.println(i1 == j1); //false;
```

