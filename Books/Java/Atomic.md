# 原子变量

## AtomicLong

- AtomicLong是以原子方式更新的long类型，但不能作为Long类型的替代品，其内的大部分方法都是对Unsafe方法的封装。

```java
private static final Unsafe U = Unsafe.getUnsafe();
private static final long VALUE
    = U.objectFieldOffset(AtomicLong.class, "value");

private volatile long value;
```

## LongAdder