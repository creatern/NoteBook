# Random

- <code>java.util.Random</code>是使用广泛的随机数生成工具类，<code>java.lang.Math</code>中的随机数生成也使用的是<code>java.util.Random</code>的实例。

```java
public Random(long seed) {
    if (getClass() == Random.class)
        this.seed = new AtomicLong(initialScramble(seed));
    else {
        // subclass might have overridden setSeed
        this.seed = new AtomicLong();
        setSeed(seed);
    }
}
```

```java
protected int next(int bits) {
    long oldseed, nextseed;
    AtomicLong seed = this.seed;
    do {
        oldseed = seed.get();
        nextseed = (oldseed * multiplier + addend) & mask;
    } while (!seed.compareAndSet(oldseed, nextseed)); //CAS操作 
    return (int)(nextseed >>> (48 - bits));
}
```

# ThreadLocalRandom