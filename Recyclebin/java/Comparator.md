# 比较器

- 比较器：Java中的对象在正常情况下，只能使用`== 或  !=`比较，而不能使用` > 或 < `比较。

| 接口       | 比较器   | 说明                                 |
| ---------- | -------- | ------------------------------------ |
| Comparable | 自然排序 | 实现类的对象在任何位置都可以比较大小 |
| Comparator | 定制排序 | 临时性的比较，定制排序大于自然排序。 |

# Comparable 自然排序

> String、包装类等实现了Comparable接口，重写了compareTo()方法，给出了比较两个对象大小的方法。

- 自然排序：自定义类实现Comparable接口，重写comparaTo()指明如何排序。

- 重写compareTo()的规则：

1. 如果当前对象this大于形参对象obj，则返回正整数，
2. 如果当前对象this小于形参对象obj，则返回负整数，
3. 如果当前对象this等于形参对象obj，则返回零。

```java
Goods[] arr = new Goods[4];
arr[0] = new Goods("联想",199);
arr[1] = new Goods("华硕",100);
arr[2] = new Goods("星际",100);
arr[3] = new Goods("盛大",2321);

Arrays.sort(arr);

System.out.println(Arrays.toString(arr));//[Goods{name='星际', price=99.0}, Goods{name='华硕', price=100.0}, Goods{name='联想', price=199.0}, Goods{name='盛大', price=2321.0}]
```

```java
public class Goods implements Comparable {
    private String name;
    private double price;

    //指明Goods的排序，按价格从低到高排序，再按照产品名称从高到低排序
    @Override
    public int compareTo(Object o) {
        if (o instanceof Goods) {
            Goods goods = (Goods) o;
            //方式一
            if (this.price > goods.price) {
                return 1;
            } else if (this.price < goods.price) {
                return -1;

            } else {
                //return 0;
                //使用String类重写的compareTo()方法排序
                //注意负号 从高到低
                return -this.name.compareTo(goods.name);
            }
            //方式二
            //return  Double.compare(this.price,goods.price);
        }

        throw new RuntimeException("数据类型不一致");
    }
}
```

# Comparator 定制排序

- Comparator对象强行对多个对象进行整体排序的比较：没有实现java.lang.Comparable接口、 实现了java.lang.Comparable接口的排序规则不适合当前的操作。定制排序大于自然排序，如果存在自然排序，则被定制排序覆盖。

- 重写compare(Object o1,Object o2)规则：

1. 返回正整数，则表示o1大于o2；
2. 返回0，表示相等；
3. 返回负整数，表示o1小于o2。

```java
Goods[] arr = new Goods[5];
arr[0] = new Goods("联想", 199);
arr[1] = new Goods("华硕", 100);
arr[2] = new Goods("星际", 100);
arr[3] = new Goods("盛大", 2321);
arr[4] = new Goods("盛大", 221);

Arrays.sort(arr, new Comparator() {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Goods && o2 instanceof Goods) {
            Goods g1 = (Goods)o1;
            Goods g2 = (Goods)o2;

            if(g1.getName().equals(g2.getName())){
                return -Double.compare(g1.getPrice(),g2.getPrice());
            }else{
                return g1.getName().compareTo(g2.getName());
            }
        }
        throw new RuntimeException("数据类型不一致");
    }
});

System.out.println(Arrays.toString(arr));//[Goods{name='星际', price=99.0}, Goods{name='华硕', price=100.0}, Goods{name='联想', price=199.0}, Goods{name='盛大', price=2321.0}]
```
