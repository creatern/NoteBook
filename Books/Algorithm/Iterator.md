- 迭代器模式 Iterator：遍历集合元素。

1. 提供统一的方法遍历对象，客户不必考虑集合的类型。
2. 隐藏集合的内部信息，客户遍历时只能获取遍历器。
3. 单一职责。
4. 每个集合对象都需要一个迭代器。

```java
public interface Iterator<E> {

    boolean hasNext();

    E next();

    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
```

- Arrays内部类：ArrayItr

```java
public Iterator<E> iterator() {
    return new ArrayItr<>(a);
}
```

```java
private static class ArrayItr<E> implements Iterator<E> {
    private int cursor;
    private final E[] a;

    ArrayItr(E[] a) {
        this.a = a;
    }

    @Override
    public boolean hasNext() {
        return cursor < a.length;
    }

    @Override
    public E next() {
        int i = cursor;
        if (i >= a.length) {
            throw new NoSuchElementException();
        }
        cursor = i + 1;
        return a[i];
    }
}
```

