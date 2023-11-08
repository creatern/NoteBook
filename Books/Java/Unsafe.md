# Unsafe

- Unsafe支持少量的硬件指令（low\-level），提供硬件级别的原子性操作，越过JVM的安全机制，可以访问任意的内存地址。

| 不同包的Unsafe           | 说明                                         |
| ------------------------ | -------------------------------------------- |
| sun.misc.Unsafe          | Java标准库的一部分，Java 9标记为不推荐使用。 |
| jdk.internal.misc.Unsafe | JDK内部使用的API，不应该被用户使用。         |

1. 直接操作对象字段：
   - public native long objectFieldOffset(Field field)：获取给定字段的偏移量。
   - public native int getInt(Object obj, long offset)：获取指定对象中偏移量处的int类型字段的值。
   - public native void putInt(Object obj, long offset, int value)：将指定对象中偏移量处的int类型字段设置为指定的值。
2. 内存释放与分配操作：
   - public native long allocateMemory(long bytes)：分配指定大小的内存块。
   - public native void freeMemory(long address)：释放之前分配的内存块。
   - public native void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes)：将指定大小的内存块从源地址复制到目标地址。
3. CAS（Compare-and-Swap）操作：
   - public final native boolean compareAndSwapObject(Object obj, long offset, Object expect, Object update)：比较并交换对象字段的值。
   - public final native boolean compareAndSwapInt(Object obj, long offset, int expect, int update)：比较并交换int字段的值。
   - public final native boolean compareAndSwapLong(Object obj, long offset, long expect, long update)：比较并交换long字段的值。
4. 锁控制操作：
   - public native void park(boolean isAbsolute, long time)：挂起调用线程。
   - public native void unpark(Object thread)：唤醒指定线程。

## getUnsafe()

- Unsafe位于rt.jar包，由Bootstrap类加载器加载，普通的类（非JDK核心库）不能直接访问（java.lang.ExceptionInInitializerError）。

```java
// JDK核心库 直接使用Unsafe
Unsafe.getUnsafe();

@CallerSensitive
public static Unsafe getUnsafe() {
    Class<?> caller = Reflection.getCallerClass();
    
    //return loader == null || loader == ClassLoader.getPlatformClassLoader();
    if (!VM.isSystemDomainLoader(caller.getClassLoader()))
        throw new SecurityException("Unsafe");
    
    return theUnsafe;
}
```

```java
// 反射使用Unsafe NoSuchFieldException, IllegalAccessException
Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
theUnsafe.setAccessible(true);
Unsafe unsafe = (Unsafe) theUnsafe.get(null);
```

# CAS Compare\-and\-Swap

- CAS（Compare and Swap算法）解决多线程下的原子性操作，防止竞态条件（Race Condition）的发生。CAS 是原子性的，即它在一个步骤中完成，不会被其他线程中断。如果多个线程同时使用 CAS 操作来修改同一个变量，只有一个线程会成功，其他线程则需要自旋重试或执行其他逻辑。
- CAS一般需要以下参数，原子操作的对象的引用（this）、原子操作的字段在该对象中的内存偏移量（valueOffset：objectFieldOffset()）、旧的预期值（oldValue：getIntVolatile(this, valueOffset)）、新的值（newValue）。如果内存位置的值等于预期值oldValue，则将该位置的值替换为新的值（newValue）并返回true，否则，不进行任何操作并返回false。

```java
unsafe.compareAndSwapInt(this, valueOffset, oldValue, newValue)
```

```java
Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
theUnsafe.setAccessible(true);
Unsafe unsafe = (Unsafe) theUnsafe.get(null);

MyClass obj = new MyClass();
long offset = unsafe.objectFieldOffset(MyClass.class.getDeclaredField("value"));

Thread t1 = new Thread(() -> {
    for (int i = 0; i < 10000; i++) {
        //obj.value++;
        int old;
        int newV;
        do {
            old = unsafe.getIntVolatile(obj, offset);
            newV = old + 1;
        } while (!unsafe.compareAndSwapInt(obj, offset, old, newV));
    }
});

Thread t2 = new Thread(() -> {
    for (int i = 0; i < 10000; i++) {
        //obj.value--;
        int old;
        int newV;
        do {
            old = unsafe.getIntVolatile(obj, offset);
            newV = old - 1;
        } while (!unsafe.compareAndSwapInt(obj, offset, old, newV));
    }
});

t1.start();
t2.start();

t1.join();
t2.join();

System.out.println(obj.value);
```

```java
class MyClass{
    int value;
}
```

# 操作对象字段

## getInt、getIntVolatile、getIntAcquire 获取

- getXxx(Object o, long offset)：在给定偏移量处获取给定对象 o 中的字段或数组元素。除非满足以下情况之一，否则结果未定义：

1. 偏移量是从某个Java字段的Field通过objectFieldOffset(..)获取的，且引用的对象（o）的类型与该字段的Field兼容。
2. 偏移量和对象引用o（null 或非 null）都是通过staticFieldOffset(..)和staticFieldBase(..)（分别） 从某些 Java 字段的反射获得的Field。
3. 引用的对象是一个数组，偏移量是满足B\+N\*S模式的整数。（N 是数组的有效索引、B由arrayBaseOffset(...)从该数组类型获取、S由arrayIndexScale(..)从该数组类型获取）

- public native int getInt(Object o, long offset)：从指定的内存地址读取一个普通的 int 类型的数据。它不对内存访问进行额外的控制，仅仅是简单地读取指定地址上的值并返回。

- public native int getIntVolatile(Object o, long offset)：从指定内存地址读取一个 volatile int 类型的值。可见性和有序性的特性，对 volatile 变量的读操作将会获取最新的值，并且读操作不会被重排序。
- public final int getIntAcquire(Object o, long offset) ：从指定的内存地址读取一个 int 类型的数据，并且具有获取操作的语义（底层调用public native int getIntVolatile(Object o, long offset)）。确保在读取该数据之前，不会发生其他内存访问的重排序，从而确保获取到的数据是最新的。

## getAndXxxYyy 操作并返回

| 原子操作之后，返回操作后的值                          | 底层原理                  |
| ----------------------------------------------------- | ------------------------- |
| getAndXxxYyy(Object o, long offset, int delta)        | getIntVolatile(o, offset) |
| getAndXxxYyyRelease(Object o, long offset, int delta) | getInt(o, offset)         |
| getAndXxxYyyAcquire(Object o, long offset, int delta) | getIntAcquire(o ,offset)  |

```java
//对于浮点数类型，需要保证精度和范围
expectedBits = getIntVolatile(o, offset);
v = Float.intBitsToFloat(expectedBits);
```

