# Java基础

## 配置与介绍

### JDK配置

#### Windows

| 环境变量  | 配置                 |
| --------- | -------------------- |
| JAVA_HOME | D:\Java\jdk-18.0.1.1 |
| Path追加  | %JAVA_HOME%\bin      |

#### Linux

```shell
# 1.下载tar.gz后缀的
#把下载的文件 jdk-8u151-linux-x64.tar.gz 放在/usr/java/目录下。
# 2.在/usr/目录下创建java目录，
mkdir /usr/java
cd /usr/java
# 3.解压 JDK
tar -zxvf jdk-8u151-linux-x64.tar.gz
# 4.设置环境变量
#修改 /etc/profile
#在 profile 文件中添加如下内容并保存：
set java environment
JAVA_HOME=/usr/java/jdk1.8.0_151        
JRE_HOME=/usr/java/jdk1.8.0_151/jre     
CLASS_PATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib
PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin
export JAVA_HOME JRE_HOME CLASS_PATH PATH
#注意：其中 JAVA_HOME， JRE_HOME 请根据自己的实际安装路径及 JDK 版本配置。
#让修改生效：
source /etc/profile
# 5.测试
java -version
#显示 java 版本信息，则说明 JDK 安装成功：
java version "1.8.0_151"
Java(TM) SE Runtime Environment (build 1.8.0_151-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.151-b12, mixed mode)
```

### Java介绍

| Java平台 | 说明       |
| -------- | ---------- |
| JavaSE   | 标准版     |
| JavaEE   | 企业版     |
| J2ME     | 嵌入式系统 |

| Java | 说明                                       |
| ---- | ------------------------------------------ |
| JDK  | Java Development Kit（Java开发工具包）。   |
| JRE  | Java Runtime Environment（Java运行环境）。 |

<img src="../../pictures/62a9a0e609475431290f52bb.png" width="300"/>  

- Java是一种简单的、面向对象的、分布式的、强壮的、安全的、体系结构中立的、高性能的、多线程的和动态的语言。
- **Java是单继承的语言**， 其接口可以实现多继承。

## 基本语法

- Java语言是面向对象的程序设计语言，Java程序的基本组成单元就是类，类体中又可包括属性与方法两部分。
- 主类：包含main()方法的类。每一个Java本地应用程序都必须包含一个main()方法，main()方法为Java应用程序的入口。
- 一个完整的主类结构通常由定义包语句、定义类语句、定义主方法语句、定义变量语句和导入API类库5部分组成。

### 变量

**命名规则**

- 26个英文字母大小写，0-9，_，$。
- 数字不可以开头。
- 不能使用关键字和保留字。
- 严格区分大小写。
- 不能包含空格。

| 命名规范      | 说明                                                         | 示例    |
| ------------- | ------------------------------------------------------------ | ------- |
| 包名          | 多单词时都小写                                               | xxxyyy  |
| 类名/接口名   | 多单词时每个单词首字母大写                                   | XxxYy   |
| 变量名/方法名 | 多单词时第一个单词首字母小写，<br />第二个单词开始每个单词首字母大写 | xxxYyy  |
| 常量名        | 所有字母都大写，多单词用下划线连接                           | XXX_YYY |

| 变量     | 声明位置         | 具体类型                                   | 初始化值特点           |
| -------- | ---------------- | ------------------------------------------ | ---------------------- |
| 成员变量 | 方法体外且类体内 | 实例变量<br />类变量（static）             | 生命周期               |
| 局部变量 | 方法体内         | 形参<br />方法局部变量<br />代码块局部变量 | 除形参外，均要显式初始 |

- 变量：内存中的一个存储区域，该区域的数据可以在同一类型范围内不断变化。变量是程序中最基本的存储单元，包含变量类型、变量名、存储的值。  

- 定义变量：告诉编译器（compiler）这个变量是属于哪一种数据类型，这样编译器才知道需要配置多少空间给它，以及它能存放什么样的数据。

1. Java每个变量必须先声明，后使用。
2. 使用变量名来访问这块区域的数据。
3. 变量的作用域：其定义所在的一对`{}`（函数体）内，变量只有在其作用域内才有效。
4. 同一个作用域内，不能定义重名的变量。

- 系统的内存可大略的分为3个区域：系统区（OS）、程序区（Program）、数据区（Data）。当程序执行时，程序代码会加载到内存中的程序区，数据暂时存储在数据区中。

<img src="../../pictures/567682923220659.png" width="400"/> 

### 数据类型

## 常用类

# 高级特性

## `<T>` 泛型

- 泛型：允许在定义类、接口时通过一个标识表示类中某个属性的类型或者是某个方法的返回值及参数类型。类型参数在使用时确定（即传入实际的类型参数，也称为类型实参）。
- 泛型中的类型必须是类。（基本数据类型则使用包装类）

> 1. 解决元素存储的安全性问题。
> 2. 解决获取数据元素时，需要类型强制转换的问题。
>
> - 泛型可以保证如果程序在编译时没有发出警告，运行时就不会产生`ClassCastException`异常。代码更加简洁、健壮。

### 自定义泛型

- 自定义泛型（结构中使用了泛型的）：泛型类、泛型接口、泛型方法。

1. 泛型的声明：`interface List<T> 和 class GenTest<K,V> ` 。其中，T、K、V不代表值，而是表示类型。（任意字母都可以，常用T（Type）表示）
2. 泛型的实例化（确定类型）：指明泛型中字母代表的类型。

```java
//以下的String就是对泛型的实例化。
List<String> strList = new ArrayList<String>();
```

> 泛型之间是可以嵌套使用的。

#### 泛型类/接口

| 泛型实例化         | 说明                                                         |
| ------------------ | ------------------------------------------------------------ |
| 没有指明泛型的类型 | 默认类型为java.lang.Object类型，且编译不检查类型（泛型擦除）。 |
| 指明泛型的类型     | 编译时检查类型，内部结构使用到泛型的位置，都指定为指明的泛型类型。 |

- 泛型使用时类似于Object，但不等同于Object。

```java
//泛型擦除，编译不会类型检查
ArrayList list = new ArrayList();
//一旦指定Object，编译会类型检查，必须按照Object处理
ArrayList<Object> list2 = new ArrayList<Object>();
```

- 类型推断：可以只写一部分的`<>`泛型指明。

```java
Map<String,Integer> map = new HashMap<>();
```

- 泛型类可能有多个参数，此时应将多个参数一起放在尖括号内：`<E1,E2,E3>`。
- 实例化后，操作原来泛型位置的结构必须与指定的泛型类型一致。
- 泛型不同的引用不能相互赋值。

```java
ArrayList<String> list1 = null;
ArrayList<Integer> list2 = null;
//ArrayList<String>、ArrayList<Integer>是不同的两种类型。
list1 = list2; //错误
```

| B extends A  | 关系                                                         |
| ------------ | ------------------------------------------------------------ |
| `G<A>、G<B>` | A、B是对G中的泛型的指明。<br />不具备子父类关系。<br />`G<?>`是`G<A>、G<B>`的公共父类。 |
| `A<G>、B<G>` | A、B是类，而G是对A、B中的泛型的指明。<br />具备子父类关系。  |

```java
public void show(List<Object> list){}
public void show1(List list){}

//此时的list1和list2不具备子父类关系，是并列的。
List<Object> list1 = null;
List<String> list2 = null;
list1 = list2; //错误 (泛型不同的引用不能相互赋值。)

test.show(list1);
//show(List<Object> list) 参数也不能是是list2
//test.show(list2);

test.show1(list1);
test.show1(list2);

//此时的list01和list02具备子父类关系
List<String> list01 = null;
ArrayList<String> list02 = null;
list01 = list02;
```

- 如果泛型结构是一个接口/抽象类，则不可创建泛型类的对象。
- 在类/接口上声明的泛型，在本类或本接口中即代表某种类型，可以作为非静态属性的类型、非静态方法的参数类型、非静态方法的返回值类型，但在静态方法中不能使用类的泛型。
- 异常类不能是泛型的。

```java
E[] arr = new E[10]; //错误
E[] arr = (E[])new Object[10]; //正确
```

```java
public class Order<T> {
    private String orderName;
    private int orderId;

    //类的内部结构就可以使用类的泛型
    T orderT;

    //泛型类的构造器不是：public GenericClass<E>(){}
    public Order() {}

    //静态方法中不能使用类的泛型
    //public static void show(T orderT){
    //    System.out.println(orderT);
    //}

    public T getOrderT() {return orderT;}

    public void setOrderT(T orderT) {this.orderT = orderT;}
}
```

#### 泛型方法

- 泛型方法：普通类/泛型类中的方法都可以被泛型化。
- 泛型方法中出现泛型结构（`<T>`，不是T）时，泛型的参数与类的泛型参数没有任何关系。
- 泛型方法可以被static修饰。（在方法调用时指明泛型，而非实例化时），但不能在静态方法中使用类的泛型（类的泛型是在实例化的时候指明的，而静态方法在类中实现）。

```java
//[访问权限] <泛型> 返回类型 方法名([泛型标识 参数名称]) 抛出的异常
public static <E> List<E> copyFormArrayToList(E[] arr) {
    ArrayList<E> list = new ArrayList<>();

    for (E e : arr) {
        list.add(e);
    }
    return list;
}
```

### 泛型继承

- 当父类有泛型时，子类除了指定或保留父类的泛型，还可以增加泛型。

```java
class Father<T1, T2> {
}

//1. 子类不保留父类的泛型

// 1)没有类型（擦除）等价于class Son1 extends Father<Object,Object>{}
class Son1 extends Father {}
// 2)指明具体类型。子类在继承带泛型的父类时：如果指明了泛型类型，则实例化子类对象时，不再需要指明泛型
class Son2 extends Father<Integer, String> {
}

//2. 子类保留父类的泛型（泛型子类）

// 1)全部保留
class Son3<T1, T2> extends Father<T1, T2> {}
// 2)部分保留
class Son4<T2> extends Father<Integer, T2> {}
```

### `<?>` 上/下限

| 限制条件       | 描述       | 说明                                          |
| -------------- | ---------- | --------------------------------------------- |
| `? extends 类` | 上限（<=） | 指定的类型必须是(该类的子类)继承某个类/接口。 |
| `? super 类`   | 下限（>=） | 指定的类型（该类的父类）不能小于操作的类。    |

```java
//Person继承Object，Student继承Person：Object > Person > Student。

//<? extends Person> 需要小于等于Person
List<? extends Person> list = new ArrayList<Object>(); //错误
List<? extends Person> list = new ArrayList<Person>();
List<? extends Person> list = new ArrayList<Student>();

//<? super Person> 需要大于等于Person
List<? super Person> list = new ArrayList<Student>(); //错误
List<? super Person> list = new ArrayList<Person>();
List<? super Person> list = new ArrayList<Object>();
```

- `<?>`允许所有泛型的引用调用。

```java
//读取数据
Person p1 = list1.get(0);
//Student s1 = list1.get(0); //编译不通过
Object o2 = list2.get(0);
//Person p2 = list2.get(0); //编译不通过

//写入数据
//list1.add(new Person()); 编译不通过
//list1.add(new Student()); 编译不通过
list2.add(new Person());
list2.add(new Student());
```

## Collection/Map 集合

### Collection

**数组的局限性**

- Java容器：集合和数组都是对多个数据进行内存层面的存储操作。

1. 数组一旦初始化以后，其 长度、元素的数据类型 不可改变。
2. 数组提供的方法非常有限，对于添加，删除，插入数据等操作，非常不便，同时效率不高。
3. 数组存储有序、可重复的数据。数组无法满足无序、不可重复的数据需求。

**集合体系**

| Collection接口                              | 单列数据，定义了存取一组对象(一个一个的对象)的方法的集合 |
| ------------------------------------------- | -------------------------------------------------------- |
| List接口<br>ArrayList / LinkedList / Vector | 元素有序，可重复的集合  -->“动态数组”                    |
| Set接口<br>HashSet / LinkedSet / TreeSet    | 元素无序，不可重复的集合 --> 类型于数学中的”集合“        |

<img src="../../pictures/51232021239497.png" width="696"/>  

| Map接口                                                    | 双列数据，保存具有映射关系"key-value"键值对(一对一对的数据)的集合 |
| ---------------------------------------------------------- | ------------------------------------------------------------ |
| HashMap / LinkedHashMap / TreeMap / Hashtable / Properties | 一个key只能对应一个value，而一个value可以对应多个key         |

<img src="../../pictures/236772121227364.png" width="662"/>    

| 动作                 | 方法                               | 说明                                                         |
| -------------------- | ---------------------------------- | ------------------------------------------------------------ |
| 添加                 | add(Object obj)                    | 将元素e添加到集合中                                          |
|                      | addAll(Collection coll)            | 将coll集合中的元素添加到当前的集合中                         |
| 获取有效元素的个数   | int size()                         |                                                              |
| 清空集合             | void clear()                       |                                                              |
| 是否是空集合         | boolean isEmpty()                  |                                                              |
| 是否包含某个元素     | boolean contains(Object obj)       | 通过元素的equals()方法来判断是否是同一个对象<br> 向Collection接口的实现类中添加数据obj时，要求obj所在类要重写equals()方法，用来判断 |
|                      | boolean containsAll(Collection c)  | 调用元素的equals()方法来比较的。拿两个集合的元素挨个比较     |
| 删除                 | boolean remove(Object obj)         | 通过元素的equals方法判断是否是要删除的那个元素。<br>只会删除找到的第一个元素 |
|                      | boolean removeAll(Collection coll) | 取当前集合的差集                                             |
| 交集                 | boolean retainAll(Collection c)    | 把交集的结果存在当前集合中，不影响c集合<br>从当前集合中返回coll中匹配的元素,并将当前集合改为交集 |
| 集合是否相等         | boolean equals(Object obj)         | 调用元素内的equals()方法一一进行比较<br>如果是ArrayList() 则还要求有序 |
| 转成对象数组         | Object[] toArray()                 | 集合-->数组<br>数组-->集合 调用Arrays类静态方法 aslist()     |
| 获取集合对象的哈希值 | hashCode()                         |                                                              |
| 遍历                 | iterator()                         | 返回迭代器对象，用于集合遍历 <br>返回Iterator接口的实例，用于遍历元素。 |

```java
public class CollectionTest {

    @Test
    public void test1() {
        Collection coll = new ArrayList();
        // add() 添加
        coll.add(123);
        coll.add("Jack");
        coll.add(false);
        coll.add(new String("Tom"));
        coll.add(new Person("Mac", 20));

        Collection coll1 = new ArrayList();
        // addAll(Collection coll) 添加coll集合中的所有元素
        coll1.addAll(coll);
        System.out.println(coll); // [123, Jack, false, Tom, com.zjk.Person@76ed1b7c]
        // 调用了String中的toString()方法
        // Person中为重写toString()方法

        coll1.clear();

        // isEmpty() 判断当前集合是否为空
        coll1.isEmpty();

        // contains(Object obj) 判断当前集合中是否包含obj
        // 在判断时，会调用obj对象所在类的equals()方法
        boolean contains = coll.contains(123);
        System.out.println(contains); // true
        System.out.println(coll.contains(new String("Tom"))); // true
        // 调用String重写的equals()方法
        System.out.println(coll.contains(new Person("Mac", 20))); // false
        // Person类中未重写equals()方法

        // containsAll(Collection coll) 判断形参coll中的所有元素是否都存在于当前集合中
        Collection coll2 = new ArrayList();
        coll2.add(123);

        System.out.println(coll.containsAll(coll2)); //true

        //remove(Object obj) 移除
        coll.remove(123);
        System.out.println(coll.remove(new String("Tom"))); //true
        //调用了equals()方法
        System.out.println(coll.remove(new Person("Mac", 20))); //false
        //Person类中未重写equals()方法，所以无法匹配来删除
        System.out.println(coll); //[Jack, false, com.zjk.java2.Person@75bd9247]

        //removeAll(Collection coll) 从当前集合中移除coll中的所有元素
        coll.removeAll(coll2);
        System.out.println(coll); //[Jack, false, com.zjk.java2.Person@75bd9247]

        coll1.add(new Person("Mac", 20));
        //retainAll(Collection coll) （交集）从当前集合中返回coll中匹配的元素,并将当前集合改为交集。
        //调用equals()方法来匹配
        System.out.println(coll); //[Jack, false, com.zjk.java2.Person@75bd9247]
        System.out.println(coll1); //[com.zjk.java2.Person@7d417077]
        coll.retainAll(coll1);
        System.out.println(coll); //[]

        Collection coll3 = new ArrayList();
        coll3.add(new String("Tom"));
        coll3.add(new Person("Mac", 20));
        Collection coll4 = new ArrayList();
        coll4.add(new String("Tom"));
        coll4.add(new Person("Mac", 20));

        //equals(Object obj)
        System.out.println(coll3.equals(coll4)); //false
        //调用元素内的equals()方法一一进行比较
        //且如果是ArrayList() 则还要求有序

        //hashCode()
        System.out.println(coll.hashCode()); //1

        //toArray() 集合-->数组
        Object[] arr = coll3.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        //数组-->集合 调用Arrays类静态方法 aslist()
        List<Object> list = Arrays.asList(arr);
        System.out.println(list);//[Tom, com.zjk.java2.Person@7dc36524]

        List<int[]> ints = Arrays.asList(new int[]{123, 456});
        System.out.println(ints);//[[I@35bbe5e8] 只有一个元素

        List ints1 = Arrays.asList(new Integer[]{123, 456});
        System.out.println(ints1);//[123, 456]  包装类

        List ints2 = Arrays.asList(123, 456);
        System.out.println(ints2); //[123, 456]

        //iterator() 返回Iterator接口的实例 用于遍历元素，放在Iterator.Test.java中
    }
}
```

#### Iterator 迭代器

- Iterator对象称为迭代器(设计模式)，主要用于遍历 Collection 集合中的元素。

> 迭代器模式：GOF给迭代器模式的定义为：提供一种方法访问一个容器(container)对象中各个元素，而又不需暴露该对象的内部细节。

- Collection接口继承`java.lang.Iterable`接口，所有实现了Collection接口的集合类都有iterator()方法，用以返回一个实现了Iterator接口的对象。（每次通过iterator()方法创建的Iterator对象都是新的对象）

```java
Iterator iterator = coll.iterator();
```

- Iterator 仅用于遍历集合，Iterator 本身并不提供承装对象的能力。如果需要创建Iterator 对象，则必须有一个被迭代的集合。
- 集合对象每次调用iterator()方法都得到一个全新的迭代器对象，默认游标都在集合的第一个元素之前。

| 方法      | 说明                                                         |
| --------- | ------------------------------------------------------------ |
| hasNext() | 如果迭代存在下一个元素，则返回true，否则false。<br />迭代器的其他方法执行需要该方法的判断（NoSuchElementException）。 |
| next()    | 返回迭代的下一个元素（游标移动）。                           |
| remove()  | 删除此迭代器返回的下一个元素。（对集合删除有效）<br />如果游标未移动，再次调用则IllegalStateException。 |

```java
//提通过Collection接口的iterator()方法创建Iterator对象
Iterator iterator = coll.iterator();
//hasNext():判断是否还有下一个元素
while(iterator.hasNext()){
    //next():①指针下移 ②将下移以后集合位置上的元素返回
    System.out.println(iterator.next());
}
```

```java
//错误1 it.next() 报错NoSuchElementException
Iterator iterator = coll.iterator();
Object obj;
while((obj = iterator.next()) != null){
    System.out.println(obj);
}

//错误2：每次通过coll.iterator()方法创建的Iterator对象都是新的对象
//跳不出循环
while((coll.iterator().next()).hasNext){
    System.out.println(obj);
}
```

#####  foreach

- foreach循环迭代访问Collection和数组：不需获取Collection或数组的长度，无需使用索引访问元素，底层调用Iterator完成操作。

```java
for(要遍历的集合/数组的元素的类型 在循环中使用的元素名称 : 要遍历的集合/数组){
    ...
}
```

##### Iterable

#### List

- List：存储有序的，可重复的数据，每个元素都有对应的顺序索引。

| List实现类 | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| ArrayList  | 主要实现类<br>线程不安全，效率高<br>底层存储Object[] elementData |
| LinkedList | 频繁的插入和删除操作 <br>底层存储双向链表                    |
| Vector     | 古老实现类<br />线程安全，效率低 <br />底层存储Object[] elementData |

- `List subList(int fromIndex, int toIndex)`:返回从fromIndex到toIndex(不包括toIndex)位置的子集合

| 方法                                                         | 作用                                               |
| ------------------------------------------------------------ | -------------------------------------------------- |
| add(int index, Object ele)<br />addAll(int index, Collection eles) | 插入                                               |
| remove(int index)<br />remove(Object obj)                    | 删<br />并返回此元素。                             |
| set(int index, Object obj)                                   | 改                                                 |
| Object get(int index)                                        | 查                                                 |
| size()                                                       | 长度                                               |
| int indexOf(Object obj)<br />int lastIndexOf(Object obj)     | 首次出现的位置，否则返回-1。<br />最后出现的位置。 |

##### ArrayList

###### add() 自动扩增

- ArrayList.add()超过长度时，对底层数组（elementData）的扩展。

```java
public void add(int index, E element) {
    rangeCheckForAdd(index);
    modCount++;
    final int s;
    Object[] elementData;
    if ((s = size) == (elementData = this.elementData).length)
        elementData = grow(); //增加ArrayList的长度：
    System.arraycopy(elementData, index,
                     elementData, index + 1,
                     s - index);
    elementData[index] = element;
    size = s + 1;
}

private Object[] grow(int minCapacity) {
    int oldCapacity = elementData.length;
    if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        int newCapacity = ArraysSupport.newLength(oldCapacity,
                                                  minCapacity - oldCapacity, 
                                                  oldCapacity >> 1 );
        return elementData = Arrays.copyOf(elementData, newCapacity);
    } else {
        return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
    }
}
```

###### Arrays.asList()

- Arrays.asList() 返回的不是java.util下的ArrayList，而是Arrays的内部类（继承AbstractList，但没有重写add()方法）。

###### sublist()

- subList()返回的是ArrayList的内部类SubList（ArraysList的视图，不是ArrayList）。对于SubList子列表的所有操作最终会反映到原列表上。

| 修改   | 元素（set()） | 结构（add()、remove()）                                      |
| ------ | ------------- | ------------------------------------------------------------ |
| 子集合 | 影响原集合    | 对子集合调用的remove()，此时删除的是对应于该子集合的下标位置的元素， 且只能是子集合中包含的元素。 |
| 原集合 | 影响子集合    | 对父集合元素的增加/删除，均会导致子列表的遍历、增加、删除操作抛出ConcurrentModificationException。 |

###### remove() 

```java
list.remove(2);  //视为索引2 而不是数据2
list.remove(new Integer(2)); //删除数据2
```

##### LinkedList

- 对于频繁的插入/删除元素的操作，建议使用LinkedList类，效率较高。
- LinkedList：双向链表。`LinkedList list = new LinkedList(); `//内部声明了Node类型的first和last属性，默认值为null。同时，定义`内部类Node`，作为LinkedList中保存数据的基本结构。

```java
//Node：体现了LinkedList的双向链表
private static class Node<E> {
    E item;
    Node<E> next; //记录下一个元素的位置
    Node<E> prev; //记录前一个元素的位置

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```

```java
list.add(123); //将123封装到Node中，创建了Node对象。
```

##### Vector

- Vector()构造器创建对象时，底层创建长度为10的数组，add()扩容时默认2倍。

#### Set

- Set：存储无序，不可重复的数据。
  - 无序性：不等于随机性  遍历的顺序仍然是添加的顺序。存储的数组在底层数组并非按照数组索引的顺序添加，而是根据数据的Hash值决定的。
  - 不可重复性：保证添加的元素按照equals()方法判断时，相同的元素只能添加一次。
- Collection的子接口，Set没有提供额外的方法。
- Set 不允许存在相同的元素（ equals() 判断），相同时add()返回false。

> 向Set中添加的数据，其所在类一定要重写hashCode()和equals()。相等的对象必须具有相等的散列码 。

| Set实现类     | 说明                                                      |
| ------------- | --------------------------------------------------------- |
| HashSet       | 主要实现类<br />线程不安全<br />可存储null。              |
| LinkedHashSet | HashSet子类<br />遍历其内部数据时，可按照添加的顺序遍历。 |
| TreeSet       | 可按照添加对象的指定属性排序。                            |

**添加元素的过程**

- 向hashSet中添加元素a，首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，此哈希值接着通过某种算法计算出在hashSet底层数组中的存放位置（索引），判断数组此位置上是否已经有元素。
- 如果此位置上没有其他元素，则元素a添加成功 --情况1。
- 如果此位置上有其他元素b（或以链表形式存在的多个元素），则比较元素a于元素b的hash值
  - 如果hash值不相同，则元素a添加成功 --情况2。
  - 如果hash值相同，进而需要调用元素a所在类的equals()方法。
    - equals()返回true，元素a添加失败。
    - equals()返回false，则元素a添加成功  --情况3。
- 对于添加成功的情况2和情况3而言，元素a，与已经存在指定索引位置上数据以链表的方式存储。

> HashSet底层：数组+链表
>
> - jdk 1.7 ：元素a放到数组中，指向原来的元素
> - jdk 1.8 ：原来的元素在数组中，指向元素a

<img src="../../pictures/539705218221142.png" width="661"/>   

<img src="../../pictures/141841619239568.png" width="669"/>   


```java
Set set = new HashSet();
set.add(456);
set.add(123);
set.add("AA");
set.add("BB");
set.add(new Person("Mac",21));
set.add(new String("CC"));
System.out.println(set); //[AA, BB, CC, 456, com.zjk.java2.Person@75bd9247, 123]

//不可重复性
set.add(123);
System.out.println(set); //[AA, BB, CC, 456, com.zjk.java2.Person@75bd9247, 123]
//调用equals()方法比较,需要用到hashCode()方法，Integer 123发现相同，所以不添加

//Person类中未重写hashCode()方法和equals()方法,比较的还是地址，所以认为不一样
set.add(new Person("Mac",21));
System.out.println(set);//[AA, BB, CC, com.zjk.java2.Person@7d417077, 456, com.zjk.java2.Person@75bd9247, 123]
```

<img src="../../pictures/227085513239570.png" width="696"/> 

```java
HashSet hashSet = new HashSet();

Person p1 = new Person("Mac", 21);
Person p2 = new Person("Tom", 21);
hashSet.add(p1);
hashSet.add(p2);

p1.setName("AA");
//对应集合中的元素也改变,但是此时存放的位置还是按照原先p1("Mac",21)计算的哈希值存放的。
hashSet.forEach(System.out::println);
//Person{name='AA', age=21}
//Person{name='Tom', age=21}

hashSet.remove(p1);
//此时remove()寻找的哈希值，是修改后的p1("AA",21)的哈希值，无法和集合中p1的哈希值匹配，
// 故找不到，无法成功remove()
hashSet.forEach(System.out::println);
//Person{name='AA', age=21}
//Person{name='Tom', age=21}

Person p3 = new Person("AA",21);
hashSet.add(p3);
//同理，此时在集合中，存放("AA",21)计算的哈希值的位置实际上是空的，所以可以添加
hashSet.forEach(System.out::println);
//Person{name='AA', age=21}
//Person{name='Tom', age=21}
//Person{name='AA', age=21}
```

##### HashSet

- HashSet 按 Hash 算法来存储集合中的元素。

1. 不能保证元素的排列顺序、HashSet 不是线程安全的、集合元素可以是 null。
2. HashSet 集合判断两个元素相等的标准：两个对象通过 hashCode() 方法比较相等，并且两个对象的 equals() 方法返回值也相等。
3. 对于存放在Set容器中的对象，对应的类一定要重写equals()和hashCode(Object obj)方法，以实现对象相等规则。即：“相等的对象必须具有相等的散列码”。

##### LinkedHashSet

- LinkedHashSet在添加数据的同时，每个数据还维护了两个引用，记录此数据前一个数据和后一个数据。
- 对于频繁的遍历操作，LinkedHashSet效率高于HashSet。

<img src="../../pictures/208262222221142.png" width="609"/>   

##### TreeSet

- TreeSet：红黑树结构。向TreeSet中添加的数据，要求是相同类的对象   

### Map

- Map：双列数据，存储键值对Entry（key-value）。

<img src="../../pictures/453525713247603.png" width="502"/>   

| 实现类     | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| HashMap    | 主要实现类<br />线程不安全，效率高<br />可存储null的key和value。 |
| LinkedMap  | 按照添加的顺序实现遍历（频繁遍历操作）。                     |
| TreeMap    | 按照添加的key排序。                                          |
| Hashtable  | 古老的实现类<br />线程安全，效率低<br />不能存储null的key和value。 |
| Properties | 处理配置文件<br />key和value都是String类型。                 |

- Map与Collection并列存在，保存具有映射关系的数据（key-value）。
- key 和 value 之间存在单向一对一关系（通过指定的 key 总能找到唯一的、确定的 value）。

| 单元  | 存储       | 数据          |
| ----- | ---------- | ------------- |
| key   | Set        | 任何引用类型  |
| value | Collection | 任何引用类型  |
| Entry | Set        | key-value构成 |

<img src="../../pictures/235545813240272.png" width="400"/>   

| 方法                                                         | 操作                        |
| ------------------------------------------------------------ | --------------------------- |
| Object put(Object key,Object value)<br />void putAll(Map map) | 添加                        |
| Object remove(Object key)<br />void clear()                  | 删除（返回value）<br />清空 |
| Object get(Object key)<br />boolean containsKey(Object key)<br />boolean containsValue(Object value)<br />int size()<br />boolean isEmpty()<br />boolean equals(Object obj) | 查                          |
| Set keySet()<br />Collection values()<br />Set entrySet()    | 视图                        |

#### HashMap

> HashMap的底层实现：
>
> - jdk1.7之前 : 数组+链表 
> - jdk1.8 : 数组+链表+红黑树   

| 属性                       | 说明                                                         |
| :------------------------- | :----------------------------------------------------------- |
| `DEFAULT_INITIAL_CAPACITY` | HashMap的默认容量，16                                        |
| `MAXIMUM_CAPACITY`         | HashMap的最大支持容量，2^30                                  |
| `DEFAULT_LOAD_FACTOR`      | HashMap的默认加载因子(0.75)                                  |
| `TREEIFY_THRESHOLD`        | Bucket中链表长度大于该默认值，转化为红黑树(8)                |
| `UNTREEIFY_THRESHOLD`      | Bucket中红黑树存储的Node小于该默认值，转化为链表             |
| `MIN_TREEIFY_CAPACITY`     | 桶中的Node被树化时最小的hash表容量。<br>当桶中Node的数量大到需要变红黑树时，若hash表容量小于MIN_TREEIFY_CAPACITY时，此时应执行resize扩容操作这MIN_TREEIFY_CAPACITY的值至少是TREEIFY_THRESHOLD的4倍。 |
| `table`                    | 存储元素的数组，总是2的n次幂                                 |
| `entrySet`                 | 存储具体元素的集                                             |
| `size`                     | HashMap中存储的键值对的数量                                  |
| `modCount`                 | HashMap扩容和结构改变的次数。                                |
| `threshold`                | 扩容的临界值，= 容量`*`填充因子 (`16*0.75`)<br>提前扩容，为了减少链表的长度。 |
| `loadFactor`               | 填充因子                                                     |

> **负载因子值的大小，对HashMap有什么影响？**
>
> - 负载因子的大小决定了HashMap的数据密度。负载因子越大密度越大，发生碰撞的几率越高，数组中的链表越容易长,造成查询或插入时的比较次数增多，性能会下降。负载因子越小，就越容易触发扩容，数据密度也越小，意味着发生碰撞的几率越小，数组中的链表也就越短，查询和插入时比较的次数也越小，性能会更高。但是会浪费一定的内容空间。而且经常扩容会影响性能，建议初始化预设大一点的空间。
> - 按照其他语言的参考及研究经验，会考虑将负载因子设置为0.7~0.75，此时平均检索长度接近于常数。

##### 底层实现

**HashMap的底层实现原理 以jdk1.7为例**

- `HashMap map = new HashMap()`;
  - 在实例化以后，底层创建了长度是16的一维数组Entry[] table。
- ...假设之前可能已经执行过多次put...
- `map.put(key1,value1)`;
  - 首先，调用key1所在类的hashCode()计算key1的哈希值，此哈希值经过某种算法计算之后，得到Entry数组中的存放位置。
  - 如果此位置上的数据为空，此时的key1-value1添加成功   **情况1**
  - 如果此位置上的数据不为空，（意味着此位置上存在一个或多个数据（以链表形式存在）），比较key1和已经存在的一个或多个数据的哈希值：
    - 如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1-value1添加成功  **情况2**
    - 如果key1的哈希值和已经存在的某一个数据(key2-value2)的哈希值相同，继续比较：调用key1所在类的equals()，比较：
      - 如果equals()返回false：此时key1-value1添加成功  **情况3**
      - 如果equals()返回true：使用value1替换相同key的value值(value2)。 
- 关于情况2和情况3：此时key1-value1和原来的数据以链表的方式存储
- 在不断的添加过程中会涉及到扩容问题：
  - 当超过临界值threadshould（12）时，且要存放的位置非空时，扩容。
  - 默认的扩容方式：扩容为原来容量的2倍，并将原来的数据复制过来。

<img src="../../pictures/331595916239572.png" width="851"/>   

**HashMap的底层实现原理 jdk1.8相较与jdk1.7的不同**

- `new HashMap()`底层没有创建一个长度为16 的数组
- jdk1.8 底层的数组是 Node[]  而非Entry[];
- 首次调用put()方法时，底层创建长度为16的数组 
- jdk1.7的底层结构只有数组+链表；jdk1.8的底层结构：数组+链表+红黑树
  - jdk1.7新的元素指向旧的元素，jdk1.8旧的元素指向新的元素. 
  - 当数组的某一个索引位置上的元素以链表形式存在的数据个数 > 8 且当前数组的长度 > 64，此时，此索引位置上的所有数据改为使用红黑树存储。 

<img src="../../pictures/506635916227439.png" width="920"/>   

#### LinkedHashMap

- LinkedMap中定义内部类Node替换了HashMap中的Entry，在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素。

```java
static class Entry<K,V> extends HashMap.Node<K,V> {
    Entry<K,V> before, after;
    Entry(int hash, K key, V value, Node<K,V> next) {
        super(hash, key, value, next);
    }
}
```

#### TreeMap

- TreeMap存储键值对时，需要根据键值对进行排序，保证所有的键值对处于有序状态。
- TreeMap底层使用红黑树结构存储数据，要求所有的 Key 应该是同一个类的对象，否则将会抛出 ClasssCastException。

| Key排序  | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| 自然排序 | 所有的 Key 必须实现 Comparable 接口。                        |
| 定制排序 | 创建 TreeMap 时，传入一个Comparator对象负责对TreeMap 中的所有 key 进行排序。<br />此时不需要Key实现Comparable 接口。 |


- TreeMap判断两个key相等的标准：两个key通过compareTo()方法、compare()方法返回0。

```java
Person p1 = new Person("Tom", 21);
Person p2 = new Person("Jac", 23);
Person p3 = new Person("Mac", 19);

TreeMap map = new TreeMap();

map.put(p1, 99);
map.put(p2, 98);
map.put(p3, 100);

//自然排序
//Person类中重写了Comparable接口的compareTo()方法
Set entrySet = map.entrySet();
Iterator iterator = entrySet.iterator();
while (iterator.hasNext())
    System.out.println(iterator.next());

TreeMap treeMap = new TreeMap(new Comparator() {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Person && o2 instanceof Person) {
            Person p1 = (Person) o1;
            Person p2 = (Person) o2;

            return p1.getAge() - p2.getAge();
        }
        throw new RuntimeException("数据类型不一致");
    }
});
treeMap.putAll(map);


Set entrySet1 = treeMap.entrySet();
Iterator iterator1 = entrySet1.iterator();
while (iterator1.hasNext())
    System.out.println(iterator1.next());
}
```

#### Properties

- Properties 类是 HashTable 的子类，该对象用于处理属性文件。由于属性文件里的 key、value 都是字符串类型，所以 Properties 里的 key 和 value 都是字符串类型。

| 方法                                 | 操作 |
| ------------------------------------ | ---- |
| setProperty(String key,String value) | 存   |
| getProperty(String key)              | 取   |

```java
Properties properties = new Properties();

FileInputStream fileIn = new FileInputStream("jdbc.properties");

properties.load(fileIn); //加载流对应的文件

String name = properties.getProperty("name");
String password = properties.getProperty("password");

System.out.println("name=" + name + "\n" + "passsword=" + password);
fileIn.close();
```

### Collections

- Collections 是一个操作 Set、List 和 Map 等集合的工具类。
- Collections 中提供了一系列静态的方法对集合元素进行排序、查询和修改等操作，还提供了对集合对象设置不可变、对集合对象实现同步控制等方法。

| 方法（static）                                               | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| reverse(List)                                                | 反转                                                         |
| shuffle(List)                                                | 随机排序                                                     |
| sort(List)<br />sort(List, Comparator)                       | 根据元素的自然顺序按升序排序<br />根据指定的Comparator排序   |
| swap(List, int, int)                                         | 指定i处元素和j处元素进行交换                                 |
| Object max(Collection)<br />Object max(Collection，Comparator)<br />Object min(Collection)<br />Object min(Collection，Comparator) | 查找                                                         |
| int frequency(Collection，Object)                            |                                                              |
| void copy(List dest,List src)                                | src中的内容复制到dest中<br />IndexOutOfBoundsException: Source does not fit in dest |
| boolean replaceAll(List list，Object oldVal，Object newVal)  | 替换                                                         |

- Collections的同步控制：提供多个 synchronizedXxx() 方法，将指定集合包装成线程同步的集合，从而解决多线程并发访问集合时的线程安全问题。

```java
List list = new ArrayList();
list.add(123);

//Collections.synchronizedXxx() 返回线程安全的
//返回线程安全的list
List safeList = Collections.synchronizedList(list);
```

## enum 枚举

> - JDK1.5之前需要自定义枚举类
> - JDK 1.5 新增的 enum 关键字用于定义枚举类
> - 若枚举只有一个对象, 则可以作为一种单例模式的实现方式。

- 枚举类的实例对象是有限个且确定的（已经在枚举类中定义）。

- 枚举类对象的属性不允许被改动, 使用 private final 修饰。
- 枚举类的构造器是私有的。若枚举类显式的定义了带参数的构造器, 则在列出枚举值时也必须对应的传入参数。

> 自定义枚举类
>
> 1. 私有化类的构造器，保证不能在类的外部创建其对象。
> 2. 在枚举类的内部创建枚举类的实例。声明为：public static final。
> 3. 对象如果有实例变量，应该声明为private final，并在构造器中初始化。
>
> ```java
> class Season {
>     //    1.声明Season对象的属性
>     private final String seasonName;
>     private final String seasonDesc;
> 
>     //    2.私有化类的构造器,并为对象属性赋值
>     private Season(String seasonName, String seasonDesc) {
>         this.seasonName = seasonName;
>         this.seasonDesc = seasonDesc;
>     }
> 
> //    3.提供当前枚举类的多个对象 public static final
>     public static final Season SPRING = new Season("春天","万物复苏");
>     public static final Season SUMMER = new Season("夏天","赤日炎炎");
>     public static final Season AUTUMN = new Season("秋天","硕果累累");
>     public static final Season WINTER = new Season("冬天","天寒地冻");
> 
> //    其他诉求：getter获取枚举类对象的属性
>     public String getSeasonName() {
>         return seasonName;
>     }
> 
>     public String getSeasonDesc() {
>         return seasonDesc;
>     }
> }
> ```

### enum

- enum关键字定义的枚举类默认继承了 java.lang.Enum类。

1. 构造器必须私有。
2. 所有实例必须在枚举类中显式列出（默认 public static final ）。多个对象之间用 `逗号,` 隔开，最后一个对象以`分号 ;`结束。
3. 必须在枚举类的第一行声明枚举类对象（枚举类的对象要移到最前面）。

```java
enum Season{

//    1.提供当前枚举类的对象,
    SPRING("春天","万物复苏"),
    SUMMER("夏天","赤日炎炎"),
    AUTUMN("秋天","硕果累累"),
    WINTER("冬天","天寒地冻");

//    2.声明Season对象的属性
    private final String seasonName;
    private final String seasonDesc;

//    3.私有化类的构造器,并为对象属性赋值
    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
    
    //其他诉求：

    // getter获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    //toString() Enum类中已经重写，默认只打印当前对象名
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
```

#### 选择实现其他接口

| 选择                           | 说明                                                         |
| ------------------------------ | ------------------------------------------------------------ |
| 枚举类中实现接口的方法         | 每个枚举值在调用实现的接口方法呈现相同的行为方式。           |
| 枚举类的实例分别实现接口的方法 | 每个枚举值在调用实现的接口方法呈现不同的行为方式。<br />哪怕只有一个枚举类对象实现也可实现该接口，不会报异常。 |

- 以上两种选择可同时存在。除非每个枚举值都实现了接口的方法，否则选择1必须存在，作为枚举值默认的实现。

```java
public class SeasonTest {
    public static void main(String[] args) {
        Season season = Season.SPRING;
        season.show();
    }
}

interface Info{
    void show();
}

enum Season implements Info{

//    情况二：枚举类的对象分别实现接口中的方法
    SPRING("春天","万物复苏"){
    },
    SUMMER("夏天","赤日炎炎"){
        @Override
        public void show() {
            System.out.println("夏天");
        }
    },
    AUTUMN("秋天","硕果累累"){
        @Override
        public void show() {
            System.out.println("秋天");
        }
    },
    WINTER("冬天","天寒地冻"){
        @Override
        public void show() {
            System.out.println("冬天");
        }
    };
    
    private final String seasonName;
    private final String seasonDesc;

    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }

//    情况一：实现接口，在枚举类中实现抽象方法
    @Override
    public void show() {
        System.out.println("季节");
    }
}
```

### Enum

## Lambda

> 从Java8开始支持Lambda。

- Lambda实质上是返回一个匿名对象。

```java
(lambda参数) -> {lambda体}
```

```java
//无参无返回值
Runnable r2 = () -> System.out.println("我是中国人");
//有参无返回值
Consumer<String> consumer1 = (String s) -> {
    System.out.println(s);
};
//类型推断，数据类型可以省略，可由编译器推断得出。
Consumer<String> consumer1 = (s) -> {
    System.out.println(s);
};
//Lambda如果只需要一个参数，参数的小括号可以省略。
Consumer<String> consumer = s -> {
    System.out.println(s);
};
//Lambda需要两个或以上的参数，多条执行语句，并且可以有返回值
Comparator<Integer> comparator1 = (o1, o2) -> {
    System.out.println(o1);
    System.out.println(o2);
    return o1.compareTo(o2);
};
//当Lambda体只有一条语句时，return与大括号可以省略。
Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
```

### Lambda作用域

- 不继承父类的属性。

**变量遮蔽与捕获**

- 变量遮蔽：内层作用域中声明的变量会覆盖外层作用域中同名的变量。

  - 只能隐藏和其它作用域中的变量名相同的变量，而不能创建一个同名的新变量。

- 变量捕获：隐式的引用外部作用域变量，而不是使用同名局部变量。

  1. 捕获局部变量：值传递，捕获变量的值不能修改。

  - 局部变量应该声明为final 。
  - 局部变量和 Lambda 表达式中的参数不能同名。
  - 引用类型的局部变量（数组等）：lambda可以对其属性内容进行修改。

  2. 捕获实例变量和静态变量：引用

  - 线程安全问题：如果 Lambda 表达式中需要修改实例变量或静态变量，必须使用 `Atomic` 类或者 `synchronized` 方法来保证线程安全，或者将变量作为参数传递给 Lambda 表达式中进行修改。

- this、super：lambda体中的this指向的是lambda体外层环境的类对象、类对象的父类，而不是引用自身。

```java
//局部变量捕获
public void lambdaDemo01() {
    int t = 1;
    int[] arr = {1,2,4};
    
    Runnable r1 = () -> {
        //t = 2; 值传递：捕获变量的值不能修改。
        System.out.println(t);
        //引用类型的局部变量（数组等）：lambda可以对其属性内容进行修改
        arr[0] = 3;
    };
    
    //Runnable r2 = t -> System.out.println(t);
    //局部变量和 Lambda 表达式中的参数不能同名  
}
```

```java
//实例变量捕获
class Foo {
    int i,j;
    int[] arr = {1,2,3};
    
    IntUnaryOperator iuo = i -> {
        //不与lambda中参数同名：没有被变量遮蔽
        j = 2; //Foo成员变量
        arr[0] = 2; //Foo成员变量
        
        //this代表Foo对象，Foo的成员变量i
        this.i = 1;
        
        //变量遮蔽：以下的i，j不是Foo的成员变量
        i = 2;
        int j = 3; //lambda中的局部变量
        return i + j;
    };
}
```

### 函数式接口

> OOF：面向函数编程。

- 函数式接口`@FunctionalInterface`：只声明了一个抽象方法的接口。
- lambda前提：对函数式接口实例的引用有效（上下文提供了合适的目标类型）。

| 类型   | 函数式接口<br />java.util.function | 方法              |
| :----- | :--------------------------------- | :---------------- |
| 消费型 | `Consumer<T>`                      | void accept(T t)  |
| 供给型 | `Supplier<T>`                      | T get()           |
| 函数型 | `Function<T,R>`                    | R apply(T t)      |
| 断定型 | `Predicate<T>`                     | boolean test(T t) |

<img src="../../pictures/functionInterface.svg"/>

### 目标类型

- 目标类型：lambda会实现一个函数式接口，上下文需要提供足够多的信息才能推断出确切的类型。

**提供合适目标类型的上下文**

| 上下文           | 目标类型                                     |
| ---------------- | -------------------------------------------- |
| 方法或构造器参数 | 参数类型                                     |
| 变量声明与赋值   | 被赋值的类型                                 |
| 返回语句         | 方法的返回类型                               |
| lambda体         | lambda体所期望的类型<br />由外层目标类型推导 |
| 三元表达式       |                                              |
| 类型转换表达式   | 显式提供                                     |

```java
//变量声明与赋值
Comparator<String> comparator = (s1, s2) -> s1.compareToIgnoreCase(s2);
//返回语句
public Runnable getRunner(){
    return () -> System.out.println("run");
}
//lambdaa体
Callable<Runnable> callable = () -> () -> System.out.println("run");
//三元表达式
boolean flag = true;
Callable<Integer> callable1 = flag ? (() -> 1) : (() -> 2);
//类型转换表达式
Object o = (Supplier) () -> "sup";
```

### 原生特化

- 原生特化：使用原生类型替换类型参数。

| 函数式接口         | 方法                        |
| ------------------ | --------------------------- |
| `LongFunction<R>`  | R apply(long value)         |
| `ToIntFunction<T>` | int applyAsInt(T value);    |
| LongToIntFunction  | int applyAsInt(long value); |

### 方法引用

- 方法引用：使用已经实现的方法来完成特定操作。

- 实现接口的抽象方法的参数列表和返回值类型，必须与方法引用的方法的参数列表和返回值类型保持一致。

| 方法引用类型 | 格式                                                   |
| ------------ | :----------------------------------------------------- |
| 静态         | `类::静态方法名`                                       |
| 绑定实例     | `对象::实例方法名`                                     |
| 未绑定实例   | `类::实例方法名`<br />第一个参数作为第二个参数的调用者 |
| 构造器       | `类::new`                                              |

```java
//静态方法引用 引用Integer的compare方法
Integer[] arr = {1, 2, 3};
Arrays.sort(arr,Integer::compare);

Comparator<String> comparator1 = String::compareTo;

//实例方法引用 引用System.out PrintStream对象的print方法
List<String> list = Arrays.asList("a", "b", "c");
list.forEach(System.out::print); //abc

Consumer<String> consumer = ps::println;
consumer.accept("你好");

Comparator<Integer> comparator = Integer::compare;
System.out.println(comparator.compare(12, 23));

//未绑定实例方法引用 通过comparing创建Comparator
Comparator comparator = Comparator.comparing(Person::getName);

//构造器引用 创建文件对象a.txt、b.txt
Stream<File> fileStream = Stream.of("a.txt", "b.txt")
    .map(File::new);

//空参构造器引用：
Supplier<List<String>> supplier = ArrayList::new;
List<String> stringList = supplier.get();
//带一个参数的构造器
Function<Integer, Employee> function = Employee::new;
System.out.println(function.apply(1002));
//带两个参数的构造器
BiFunction<Integer, String, Employee> biFunction = Employee::new;
System.out.println(biFunction.apply(1003, "Jac"));
```

### 类型推断、重载解析

- 聚合表达式：类在一定程度上由上下文决定的lambda表达式。

```java
//Comparator部分代码
default <U> Comparator<T> thenComparing(
    Function<? super T, ? extends U> keyExtractor,
    Comparator<? super U> keyComparator)
{
    return thenComparing(comparing(keyExtractor, keyComparator));
}

default <U extends Comparable<? super U>> Comparator<T> thenComparing(
    Function<? super T, ? extends U> keyExtractor)
{
    return thenComparing(comparing(keyExtractor));
}

default Comparator<T> thenComparingInt(ToIntFunction<? super T> keyExtractor) {
    return thenComparing(comparingInt(keyExtractor));
}
```

#### 类型检查

- 类型检查：函数式接口的函数类型。
- 函数类型：函数式接口的唯一抽象方法的参数类型、返回类型、抛出异常类型。

| 条件     | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| 参数数量 | lambd要与函数类型具有相同的参数数量                          |
| 参数类型 | 如果lambda的类型是显式声明的，则需要与函数类型的参数类型匹配 |
| 返回类型 | 函数类型返回void：lambda必须是语句表达式、没有返回值的块体<br />函数类型有返回值：lambda必须返回和该返回值兼容的值 |
| 抛出类型 | lambda检查抛出异常的前提：<br />函数类型声明抛出了该异常 或 该异常的父类 |

- 对于抛出类型：需要在函数式接口中的唯一抽象方法声明异常抛出，否则编译不通过。

#### 重载解析

- 选择和所需要的更接近的结果的方法。

```java
default Comparator<T> thenComparingInt(ToIntFunction<? super T> keyExtractor) {}
default Comparator<T> thenComparingLong(ToLongFunction<? super T> keyExtractor){}
```

- 对于方法引用的重载：

```java
//构造器
public Exception(){}
public Exception(String msg){} 

//重载方法
<T> void foo(Supplier<T> factory){}
<T,U> void foo(Function<T,U> transformer){}

//错误调用
foo(Excption::new);

//显式指定接收者
this.<Exception>foo(Exception::new);
this.<String,Exception>foo(Exception::new);
```

```java
//重载方法
void bar(IntFunction<String> f){}
void bar(DoubleFunction<String> f){}

//强制类型
bar((IntFunction<String>) String::valueOf());
bar((DoubleFunction<String>) String::valueOf());
```


## Stream 流

- Stream是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。

1. Stream 不持有值，不存储元素。
2. Stream 不会改变源对象。相反，会返回一个持有结果的新Stream。
3. 延迟计算：Stream只有进行了终止操作才会开始计算结果。
4. 串行处理、并行处理。

| 类型       | 说明                                             |
| ---------- | ------------------------------------------------ |
| Collection | 静态的内存数据结构，主要面向内存，存储在内存中。 |
| Stream     | 有关计算的，主要面向 CPU，通过 CPU 实现计算。    |

| 对比               | Stream                         | Iterator                   |
| ------------------ | ------------------------------ | -------------------------- |
| 处理值             | 无序                           | 有序                       |
| 可操作性           | 中间操作                       | 逐个进行                   |
| 保留值             | 保留值本身、关于源的属性的信息 | 只保留值本身               |
| null（无元素处理） | 友好的处理                     | hasNext()返回false，易出错 |

<img src="../../pictures/Snipaste_2022-11-19_13-07-50.png" width="700"/>

1. 数据源：创建Stream，获取一个流。
2. 管道：中间操作链，对数据源的数据进行处理。
3. 终止流：一旦执行终止操作，就执行中间操作链，并产生结果，之后流被耗尽，不能被再次使用。

### 数据源

| 数据源              | 方法                                                         | 类型       |
| ------------------- | ------------------------------------------------------------ | ---------- |
| 集合 Collection接口 | `default Stream<E> stream()`                                 | 顺序流     |
|                     | `default Stream<E> parallelStream()`                         | 并行流     |
| 数组                | `static <T> Stream<T> stream(T[] array)`                     | 流         |
| Stream#of()         | `public static<T> Stream<T> of(T... values)`                 | 返回一个流 |
| 无限流              | `public static<T> Stream<T> iterate(final T seed,final UnaryOperator<T> f)` | 迭代       |
|                     | `public static<T> Stream<T> generate(Supplier<T> s)`         | 生成       |

- 原生流：避免基本数据类型和包装类之间频繁的自动装箱和自动拆箱。

| 原生流       | 对应类型          | 流类型转换                   | 装箱    | 拆箱       |
| ------------ | ----------------- | ---------------------------- | ------- | ---------- |
| IntStream    | char、short、byte | asLongStream、asDoubleStream | boxed() | mapToInt() |
| LongStream   | long              | asDoubleStream               | boxed() |            |
| DoubleStream | float、double     |                              | boxed() |            |

### 管道（中间操作）

- 惰性求值：多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理，而在终止操作时一次性全部处理。

| 操作       | 方法                           | 说明                                                         |
| ---------- | ------------------------------ | ------------------------------------------------------------ |
| 筛选与切片 | filter(Predicate p)            | 接受Lambda，从流中排除某些元素                               |
|            | distinct()                     | 筛选，通过流所生成元素的hashCode()和equals()去除重复元素     |
|            | limit(long maxSize)            | 截断流，使其元素不超过给定数量                               |
|            | skip(long n)                   | 跳过元素，返回一个丢弃了前n个元素的流<br />若流中元素不足n个，则返回一个空流。<br />与limit(n)互补 |
| 映射       | map(Function f)                | 接收一个函数作为参数，该函数应用到每个元素上<br />并将其映射为一个新的元素。 |
|            | mapToDouble(ToDoubleFuntion f) | 接收一个函数作为参数，该函数应用到每个元素上<br />产生一个新的DoubleStream |
|            | mapToInt(ToIntFuntion f)       | 接收一个函数作为参数，该函数应用到每个元素上<br />产生一个新的IntStream |
|            | mapToLong(ToLongFuntion f)     | 接收一个函数作为参数，该函数应用到每个元素上<br />产生一个新的LongStream |
|            | flatMap(Function f)            | 接收一个函数作为参数，将流中的每个只都换成另一个流，然后把所有的流连接成一个流。 |
| 排序       | sorted()                       | 产生一个新流，其中按自然顺序排序                             |
|            | sorted(Conparator com)         | 产生一个新流，其中按比较器顺序排序                           |

### 终止流

- 终端操作会从流的流水线生成结果。其结果可以是任何不是流的值，例如：List、Integer，甚至是 void 。
- 流进行了终止操作后，不能再次使用。

| 操作       | 方法                             | 说明                                                         |
| ---------- | -------------------------------- | ------------------------------------------------------------ |
| 匹配与查找 | allMatch(Predicate p)            | 检查是否匹配所有元素                                         |
|            | anyMatch(Predicate p)            | 检查是否至少匹配一个元素                                     |
|            | noneMatch(Predicate p)           | 检查是否没有匹配所有元素                                     |
|            | findeFirst()                     | 返回第一个元素                                               |
|            | findAny()                        | 返回当前流中的任意元素                                       |
|            | count()                          | 返回流中元素总数                                             |
|            | max(Comparator c)                | 返回流中最大值                                               |
|            | min(Comparator c)                | 返回流中最小值                                               |
|            | forEach(Consumer c)              | 内部迭代：Stream<br />外部迭代：Collection接口               |
| 归约       | reduce(T iden,BinaryOperrator b) | 将流中元素反复结合，得到新的值，返回T                        |
|            | reduce(BinaryOperrator b)        | 将流中元素反复结合，得到新的值，返回`Optional<T>`            |
| 收集       | collect(Collector c)             | 将流转为其他形式。<br />接收一个Collector接口的实现，用于给Stream中元素做汇总的方法。<br />Collector 接口中方法的实现决定了如何对流执行收集的操作(如收集到 List、Set、Map) |

### Collectors 收集器

### Optional 容器

- `Optional<T> `
  - 保存类型T的值，代表这个值存在：`isPresent()`返回true，调用`get()`方法会返回该对象。
  - 保存null，表示这个值不存在，可以避免空指针异常。


| 操作                   | 方法                                                     | 说明                                                         |
| ---------------------- | -------------------------------------------------------- | ------------------------------------------------------------ |
| 创建Optional实例       | Optional.of(T t)                                         | 创建一个Optional实例<br />t必须非null                        |
|                        | Optional.empty()                                         | 创建一个空的Optional实例                                     |
|                        | Optional.ofNullable(T t)                                 | 创建一个Optional实例<br />t可以为null                        |
| 判断容器是否包含对象   | boolean isPresent()                                      | 是否包含对象                                                 |
|                        | `void ifPresent(Consumer<? super T> consumer)`           | 如果有值，执行Consumer接口的实现代码，并且该值作为参数传递给它 |
| 获取Optional容器的对象 | T get()                                                  | 如果调用对象包含值，返回该值，<br />否则抛出NoSuchElementException，不安全。 |
|                        | T orElse(T other)                                        | 如果有值，则将其返回<br />否则返回指定的other对象            |
|                        | `T orElseGet(Supplier<? extends T> other)`               | 如果有值，将其返回<br />否则返回由Supplier接口实现提供的对象 |
|                        | `T orElseThrow(Supplier<? extends X> exceptionSupplier)` | 如果有值，将其返回<br />否则抛出Supplier接口实现提供的异常   |