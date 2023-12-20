# 单例模式 SingleTon

- 该类只能存在一个对象实例，且只提供一个获取其对象实例的方法。

| 单例方式   | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| 饿汉式     | 静态常量、静态代码块                                         |
| 懒汉式     | 线程不安全<br />线程安全、同步方法<br />线程安全、同步代码块（双重检查） |
| 静态内部类 |                                                              |
| 枚举       |                                                              |

# 饿汉式

1. 构造器私有化：private。

2. 类的内部创建对象：private static。

3. 向外暴露一个静态的公共方法：getInstance()。

- 类装载时完成实例化，避免线程同步问题；但没有实现Lazy Loading，可能造成内存浪费。

```java
public class Singleton {

    private static Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
```

```java
public class Singleton {

    private static Singleton instance;

    static{
        instance = new Singleton();
    }

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
```

# 懒汉式

- Lazy Loading

1. 线程不安全：

```java
public class LazySingleton {

    private static LazySingleton instance = null;

    private LazySingleton() {}

    public synchronized static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
```

2. 线程安全：同步方法，效率低

```java
public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {
    }

    public synchronized static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
```

3. 线程安全：同步代码块（双重检查）

```java
public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
```

> 错误写法：可能有多个线程已经经过了判断，等待进入同步代码块，此时仍然会创建多个实例。
>
> ```java
> if (instance == null) {
>  synchronized (LazySingleton.class) {
>      instance = new LazySingleton();
>  }
> }
> ```

# 静态内部类

- Lazy loading：类装载机制：静态内部类在外部类被装载时不会被装载，而是在需要实例时才会装载。
- 由JVM保证线程安全：类的静态属性只会在第一次加载类时初始化。

```java
public class LazySingleton {
    private static class SingletonHolder{
        private static StaticSingleton instance = new StaticSingleton();
    }

    private LazySingleton(){}

    public static LazySingleton getInstance(){
        return SingletonHolder.instance;
    }
}
```

# 枚举 enum

- 避免线程安全问题，防止反序列化重新创建新的对象。

```java
enum Singleton{
    INSTANCE;
}
```
