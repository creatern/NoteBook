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

## OOP



## 常用类

### Object 根父类

- java.lang.Object（根父类）：所有的java类(除java.lang.Object类之外)都直接或间接的继承于java.lang.Object类。  

> 数组也可以作为Object类的子类出现，可以调用Object类中声明的方法。

- 如果在类的声明中未使用extends关键字指明其父类，则默认父类为java.lang.Object类。

#### equals()

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

#### hashCode()

- 返回一个int类型的哈希值，由对象中一般不变的属性映射得到。
- 相同对象的哈希值必须相同，不同对象的哈希值有可能相同。
- 子类如果重写了equals()方法，也必须重写hashCode()方法：对两个对象，如果equals()返回true，则其hashCode也必须相同。
- 哈希值通常是质数：

**基本原则**

- 在程序运行时，同一个对象多次调用 hashCode() 方法应该返回相同的值。
- 当两个对象的 equals() 方法比较返回 true 时，这两个对象的 hashCode() 方法的返回值也应相等。
- 对象中用作 equals() 方法比较的 Field，都应该用来计算 hashCode 值。

**问题：为什么复写hashCode方法，有31这个数字？**

- 选择系数的时候要选择尽量大的系数。因为如果计算出来的hash地址越大，所谓的“冲突”就越少，查找起来效率也会提高。（减少冲突）
- 并且31只占用5bits,相乘造成数据溢出的概率较小。
- 31可以 由`i*31== (i<<5)-1`来表示,现在很多虚拟机里面都有做相关优化。（提高算法效率）
- 31是一个素数，素数作用就是如果我用一个数字来乘以这个素数，那么最终出来的结果只能被素数本身和被乘数还有1来整除！(减少冲突)

#### toString() 

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

## Exception 异常

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

### `<?>` 通配符

- `<?>`（通配符）允许所有泛型的引用调用。

| 上/下限限制条件 | 描述       | 说明                                          |
| --------------- | ---------- | --------------------------------------------- |
| `? extends 类`  | 上限（<=） | 指定的类型必须是(该类的子类)继承某个类/接口。 |
| `? super 类`    | 下限（>=） | 指定的类型（该类的父类）不能小于操作的类。    |

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

## reflect 反射

- 反射：动态语言的关键（Java是准动态语言） ，在运行时借助于Reflection API取得任何类的内部信息，并能直接操作任意对象的内部属性及方法。

<img src="../../pictures/54754423239580.png" width="600"/>    

- 运行时的反射：判断任意一个对象所属的类、构造任意一个类的对象、判断任意一个类所具有的成员变量和方法、获取泛型信息、调用任意一个对象的成员变量和方法、处理注解、动态代理。

| 反射相关API                   | 说明     |
| ----------------------------- | -------- |
| java.lang.Class               | 类       |
| java.lang.reflect.Method      | 方法     |
| java.lang.reflect.Field       | 成员变量 |
| java.lang.reflect.Constructor | 构造器   |

```java
//反射之后，对Person类的操作
Class<Person> personClass = Person.class;

//1.通过反射，创建Person类的对象
Constructor<Person> constructor = personClass.getConstructor(String.class, int.class);
Person tom = constructor.newInstance("Tom", 21);
System.out.println(tom);

//2.通过反射，调用对象指定的属性、方法
//调用属性
Field age = personClass.getDeclaredField("age");
age.set(tom,10);
System.out.println(tom);
//调用方法
Method show = personClass.getDeclaredMethod("show");
show.invoke(tom);

//3.通过反射可以调用Person类的私有结构
//调用私有构造器
Constructor<Person> constructor1 = personClass.getDeclaredConstructor(String.class);
constructor1.setAccessible(true);
Person jac = constructor1.newInstance("Jac");
System.out.println(jac);
//调用私有属性
Field name = personClass.getDeclaredField("name");
name.setAccessible(true);
name.set(jac,"Mac");
System.out.println(jac);
//调用私有方法
Method showNation = personClass.getDeclaredMethod("showNation", String.class);
showNation.setAccessible(true);
String china = (String) showNation.invoke(jac, "China");
//invoke() 相当于：p1.showNation("China")
System.out.println(china);
```

### ClassLoader 类加载器

> 类的加载过程：JVM部分。

```java
//获取对应的类加载器
class.getClassLoader();

//获取父级类加载器（不存在则返回null）
classLoader.getParent(); 
```

### Class 类对象

| 方法                | 说明                  |
| ------------------- | --------------------- |
| forName("全限定名") | 返回对应的Class对象。 |
| getClassLoader()    | 返回该类的类加载器。  |

- java.lang.Class是反射的根源，针对任何想动态加载、运行的类，唯有先获得相应的Class对象。加载到内存中的运行时类，会缓存一定的时间，在此时间之内，可以通过各种方式来获取此运行时类。
- Class 对象只能由系统建立对象，一个Class对象对应一个加载到JVM中的.class文件（一个加载的类在JVM中只会有一个Class实例）。对于每个类而言，JRE 都为其保留一个不变的 Class 类型的对象。（每个类的实例都会记得自己是由哪个 Class 实例所生成） 

```java
//1.已知具体的类，通过类的class属性获取，该方法最为安全可靠，程序性能最高
Class clazz = String.class;

//2.已知某个类的实例
Class clazz = "aaa".getClass(); //获取String对象的class

//3.已知一个类的全限定名，且该类在类路径下。不存在则ClassNotFoundException。
Class clazz = Class.forName("java.lang.String");

//4.类的加载器
Class clazz4 = this.getClass()
    .getClassLoader()
    .loadClass("类的全限定名");

//1.2.3.4.指向的都是同一个Class对象：一个加载的类在JVM中只会有一个Class实例。
```

- Class 对象包含了某个特定结构（`class、interface、enum、@interface、primitive、type、基本数据类型 、void、[]数组 `）的有关信息，通过Class可以完整地得到一个类中的所有被加载的结构：

1. class： 外部类、成员（成员内部类，静态内部类）、局部内部类、匿名内部类。 

2. 数组：只要数组的数据类型、维度一样（地址相同），则属于同一个Class。

```java
//数据类型int、维度一维
int[] a = new int[10];
int[] b = new int[1];
System.out.println(a.getClass() == b.getClass()); //true
```

3. 某个类的属性、方法和构造器、某个类到底实现了哪些接口。

```java
Class<Object> objectClass = Object.class;//class 类
Class<Comparable> comparableClass = Comparable.class; //interface 接口
Class<String[]> aClass = String[].class; //数组
Class<int[][]> aClass1 = int[][].class;  //数组
Class<ElementType> elementTypeClass = ElementType.class; //enum 枚举
Class<Override> overrideClass = Override.class; //annotion 注解
Class<Integer> integerClass = int.class; //基本数据类型
Class<Void> voidClass = void.class; //void
Class<Class> classClass = Class.class;//Class类
```

#### 运行时类

- 运行时类获取的对应结构：Field、Method、Constructor、Superclass、Interface、Annotation（@Interface）、GenericSuperclass（`<?>`）。

| Class                        | 获取当前运行时类的结构                             |
| ---------------------------- | -------------------------------------------------- |
| newInstance()                | 对象。                                             |
| Package getPackage()         | 所在的包。                                         |
| `Class<?>[] getInterfaces()` | 实现的所有接口<br />（不包括父类实现的）。         |
| getSuperclass()              | 父类。                                             |
| getAnnotations()             | 所有public注解<br />（包括所有父类的public属性）。 |
| `getDeclaredAnnotations() `  | 全部注解<br />（不包含父类中有而该类没有的）       |

- newInstance()要求运行时类必须提供空参构造器、空参构造器的访问权限，否则InstantiationException。

```java
//转换为对应的修饰符名称：private-2、public-1、缺省-0（缺省转为空）
Modifier.toString(int i);
//Method、Field、Constructor对象对于非public权限的属性需要启动和禁用访问安全检查的开关、保证当前是可访问的。
//指示反射的对象在使用时应该取消Java语言访问检查，使得原本无法访问的私有成员也可以访问。
m.setAccessible(true);
```

| Class                                              | 获取                                           |
| -------------------------------------------------- | ---------------------------------------------- |
| `Constructor<T>[] getConstructors()`               | 所有public构造器。                             |
| `Constructor<T>[] getDeclaredConstructors()`       | 所有构造器。                                   |
| `getDeclaredConstruct(Class<?> ... parameterType)` | 指定的构造器<br />（参数列表指定，空表示无参） |
| **Constructor**                                    | **操作**                                       |
| int getModifiers()                                 | 修饰符                                         |
| String getName()                                   | 名称                                           |
| `Class<?>[] getParameterTypes()`                   | 参数的类型                                     |

| Class                                               | 获取                                                         |
| --------------------------------------------------- | ------------------------------------------------------------ |
| Method[] getMethods()                               | 所有public方法<br />（包括所有父类的public方法）。           |
| Method[] getDeclaredMethods()                       | 全部方法<br />（不包含父类中有而该类没有的）                 |
| getDeclaredMethod(Sting methodName, Class ... args) | 指定的方法<br />（方法名+形参列表 指定）                     |
| **Method**                                          | **说明**                                                     |
| getAnnotations()                                    | 所有注解                                                     |
| int getModifiers()                                  | 修饰符                                                       |
| `Class<?> getReturnType()`                          | 返回值类型                                                   |
| getName()                                           | 方法名                                                       |
| `Class<?>[] getParameterTypes()`                    | 形参列表                                                     |
| `Class<?>[] getExceptionTypes()`                    | 异常信息                                                     |
| Object invoke(Object obj, Object ... args)          | 返回值对应原方法的返回值<br />若原方法无返回值，则返回null。<br />若原方法为静态方法，形参Object obj可为null或对应的class。<br />若原方法形参列表为空，则Object[] args为null。 |

> Method类中：
>
> ```
> @注解
> 权限修饰符 返回值类型 方法名(参数类型 参数名) throws XxxException{}
> ```

| Class                              | 获取                                               |
| ---------------------------------- | -------------------------------------------------- |
| Field[] getFields()                | 所有public属性<br />（包括所有父类的public属性）。 |
| Field[] getDeclaredFields()        | 全部属性<br />（不包含父类中有而该类没有的）       |
| getField(String fieldName)         | 指定的public属性                                   |
| getDeclaredFiled(String fieldName) | 指定的属性                                         |
| **Field**                          | **说明**                                           |
| int getModifiers()                 | 修饰符                                             |
| `Class<?> getType()`               | 数据类型                                           |
| String getName()                   | 属性名                                             |
| Object get(Object obj)             | 值                                                 |
| set(Object obj,Object value)       | 设置该属性值<br />（对象，属性值）                 |

> Field：权限修饰符 数据类型 属性名。

| Class                             | 获取               |
| --------------------------------- | ------------------ |
| `Type getGenericSuperclass()`     | 父类泛型类型       |
| **ParameterizedType（Type子类）** | **说明**           |
| `getActualTypeArguments()`        | 所有指明的泛型类型 |

### Proxy 动态代理

> 动态代理技术：在运行期间，对目标对象的方法进行增强，代理对象同名方法内可以执行原有逻辑的同时嵌入执行其他增强逻辑或其他对象的方法。使用一个代理将对象包装起来, 然后用该代理对象取代原始对象。任何对原始对象的调用都要通过代理。代理对象决定是否以及何时将方法调用转到原始对象上。抽象角色中（接口）声明的所有方法都被转移到调用处理器一个集中的方法中处理。
>
> - AOP思想。

- 动态代理机制：java.lang.reflect 包的InvocationHandler接口、Proxy类。

<img src="../../pictures/Snipaste_2023-04-10_17-24-05.png" width="500"/> 

- Proxy 动态代理：提供用于创建动态代理类和动态代理对象的静态方法（在JVM内存中动态的构建Proxy对象）。当调用原对象的方法时，由增强的Proxy对象替代执行。

```java
//返回指定接口的代理类对象(Object)。
public static Object newProxyInstance(ClassLoader loader, 
                                      Class<?>[] interfaces, 
                                      InvocationHandler h)
```

| newProxyInstance参数  | 说明                                            |
| --------------------- | ----------------------------------------------- |
| ClassLoader loader    | 被代理类的类加载器                              |
| Class<?>[] interfaces | 被代理类实现的所有接口                          |
| InvocationHandler h   | 事件处理器<br />InvocationHandler实现类的实例。 |

- Proxy对象不需要实现接口。

```java
//原对象
Object bean = new Object();

//代理对象
Object beanProxy = Proxy.newProxyInstance(
        bean.getClass().getClassLoader(),
        bean.getClass().getInterfaces(),
        (proxy, method, args) -> {
            //增强方法
            
            //调用原方法
            Object result = method.invoke(bean, args);
            
            //增强方法
            
            //返回原方法的返回值（如果有，否则返回null）
            return result;
        }
);
```

> **静态代理**
>
> ```java
> interface ClothFactory {
>     void produceCloth();
> }
> 
> //代理类
> class ProxyClothFactory implements ClothFactory{
> 
>     private ClothFactory factory; //用被代理类对象进行实例化
> 
>     public ProxyClothFactory(ClothFactory factory){
>         this.factory = factory;
>     }
> 
>     @Override
>     public void produceCloth() {
>         System.out.println("代理工厂");
> 
>         factory.produceCloth();
> 
>         System.out.println("后续");
>     }
> }
> 
> //被代理类
> class NikeClothFactory implements ClothFactory{
>     @Override
>     public void produceCloth() {
>         System.out.println("Nike工厂生产");
>     }
> }
> 
> public class ProxyTest {
>     public static void main(String[] args) {
>         //创建被代理类的对象
>         NikeClothFactory nikeClothFactory = new NikeClothFactory();
>         //创建代理类的对象
>         ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nikeClothFactory);
> 
>         proxyClothFactory.produceCloth();
>     }
> }
> ```

## Annotation 注解（@interface）

- Annotation（注解）：Java对元数据(MetaData) 的支持。可以在编译、类加载、 运行时被读取, 并执行相应的处理。在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息。注解必须配上注解的信息处理流程才有意义。
- `@interface` 可用于修饰包、类、 构造器、 方法、 成员变量、 参数、 局部变量的声明，这些信息被保存在Annotation的 “name-value” 键值对中。

> 一定程度上： 框架 = 注解 + 反射 + 设计模式。

| 生成文档相关注解 | 说明                                                         |
| :--------------- | :----------------------------------------------------------- |
| `@author`        | 标明开发该类模块的作者，多个作者之间使用`,`分割              |
| `@version`       | 标明该类模块的版本                                           |
| `@see`           | 参考转向，也就是相关主题                                     |
| `@since`         | 从哪个版本开始增加的                                         |
| `@param`         | 对方法中某参数的说明，如果没有参数就不能写<br />@param 形参名 形参类型 形参说明 |
| `@return`        | 对方法返回值的说明，如果方法的返回值类型是void就不能写<br />@return 返回值类型 返回值说明 |
| `@exception`     | 对方法可能抛出的异常进行说明 ，如果方法没有用throws显式抛出的异常就不能写<br />@exception 异常类型 异常说明 |

| 编译时格式检查注解  | 说明                                 |
| :------------------ | :----------------------------------- |
| `@Override`         | 限定重写父类方法, 该注解只能用于方法 |
| `@Deprecated`:      | 所修饰的元素(类, 方法等)已过时。     |
| `@SuppressWarnings` | 抑制编译器警告                       |

> 反射获取注解信息：JDK 5.0 在 java.lang.reflect 包下新增了 AnnotatedElement 接口, 该接口代表程序中可以接受注解的程序元素。当一个 Annotation 类型被定义为运行时注解（@Retention中声明的生命周期状态为RUNTIME）后, 该注解才是运行时可见，当 class 文件被载入时保存在 class 文件中的 Annotation 才会被虚拟机读取。

### 自定义注解

- 注解声明： `@interface` 、继承`java.lang.annotation.Annotation接口`。
- 如果定义的注解含有配置参数且无默认值，则使用时必须指定其参数值：`参数名 = 参数值`。
- 如果只有一个参数成员，建议使用参数名为value：使用时可以省略`value=`。

| 注解类型 | 说明         |
| :------- | :----------- |
| 标记     | 没有成员定义 |
| 元数据   | 包含成员变量 |

**成员变量：无参方法声明** 

- 返回值：变量类型（基本数据类型、String类型、Class类型、enum类型、Annotation类型、以上所有类型的数组）。
- 方法名：变量名称。
- 默认值 ：default 关键字。

```java
public @interface MyAnnocation {
    String value() default "hi";
}

@MyAnnocation(value = "hello")
class Person {}
```

### 元注解

- 元注解：对现有的注解进行解释说明的注解，用于修饰其他注解定义。（注解也可以修饰其他注解）

> JDK5.0提供了4个标准的meta-annotation类型： Retention 、 Target 、Documented、Inherited。

```java
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
public @interface MyAnnocation {
    String value() default "hi";
}

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, MODULE, PARAMETER, TYPE})
public @interface Deprecated {...}
```

#### @Retention 生命周期

| 参数                    | 说明                                                         |
| :---------------------- | :----------------------------------------------------------- |
| RetentionPolicy.SOURCE  | 源文件保留<br />在源文件中有效，编译器直接丢弃这种策略的注释 |
| RetentionPolicy.CLASS   | 默认，class保留<br />在class文件中有效，当运行 Java 程序时, JVM 不会保留注解 |
| RetentionPolicy.RUNTIME | 运行时保留<br />在运行时有效，当运行 Java 程序时, JVM 会保留注释。<br>只有说明为RUNTIME生命周期的注解，才可以通过反射获取该注释。 |

#### @Target 类型

- @Target指定被修饰的注解类型能用于修饰哪些元素。

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Target {
    ElementType[] value();
}
```

| ElementType枚举值 | 说明 注解使用位置                          |
| ----------------- | ------------------------------------------ |
| TYPE              | 类、接口（包括@interface）、枚举、记录声明 |
| FIELD             | 属性成员、Enum对象成员                     |
| METHOD            | 方法                                       |
| PARAMETER         | 形参                                       |
| CONSTRUCTOR       | 构造器                                     |
| LOCAL_VARIABLE    | 局部变量                                   |
| ANNOTATION_TYPE   | 注解接口                                   |
| PACKAGE           | 包                                         |
| TYPE_PARAMETER    | 类型变量的声明语句中（泛型声明等）         |
| TYPE_USE          | 使用类型的任何语句中                       |
| MODULE            | 模块                                       |
| RECORD_COMPONENT  | 记录组件                                   |

```java
@Inherited
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE,TYPE_PARAMETER,TYPE_USE})
public @interface MyAnnotation {
    String value() default "hi";
}

class Generic<@MyAnnotation T>{  //TYPE_PARAMETER
    //TYPE_USE
    public void show()throws @MyAnnotation RuntimeException{
        ArrayList<@MyAnnotation String> list = new ArrayList<>();

        int num = (@MyAnnotation int)10L;
    }
}
```

#### @Documented 文档

- @Documented指定被修饰的注解类将被javadoc 工具提取成文档。

- 默认情况下，javadoc是不包括注解的，使用`@Documented`时必须设置`@Retention`值为RUNTIME。

#### @Inherited 继承性

- 如果把标有`@Inherited`注解的自定义注解标注在类级别上，则子类继承父类类级别的注解。

#### @Repeatable 可重复

- `@Repeatable(存放该重复注解的注解.class)`：同时需要相同的`@Inherited，@Tartget和@Rentention`。

```java
@Inherited
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
public @interface MyAnnotation {
    String value() default "hi";
}

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
public @interface MyAnnotations {
    MyAnnotation[] value();
}

//重复注解
@MyAnnotation(value = "hello")
@MyAnnotation(value = "abc")
class Person {
  ...
}
```

> 重复注解 jdk8.0之前。
>
> ```java
> @Inherited
> @Retention(RetentionPolicy.RUNTIME)
> @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
> public @interface MyAnnotation {
>     String value() default "hi";
> }
> 
> public @interface MyAnnotations {
>     MyAnnotation[] value();
> }
> 
> @MyAnnotations({@MyAnnotation(value = "hello"),@MyAnnotation(value = "hi")})
> class Person(){
>     ...
> }
> ```

## Lambda 对象表达式

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

# IO、NIO

# 多线程

| 名词          | 说明                                                         |
| ------------- | ------------------------------------------------------------ |
| 程序(program) | 为完成特定任务、用某种语言编写的一组指令的集合（静态的代码）。<br/>作为执行蓝本的同一段程序，可以被多次加载到系统的不同内存区域执行，形成不同进程。 |
| 进程(process) | 进程的生命周期：程序的一次动态执行过程，或是正在运行的一个程序。<br/>由代码、数据、内核状态和一组寄存器组成。<br/>内核级的实体：进程结构的所有成分都在内核空间中，用户程序不能直接访问这些数据。<br/>资源分配的单位：系统在运行时会为每个进程分配不同的内存区域。 |
| 线程(thread)  | 一个程序内部的一条执行路径（执行流）：一个进程在其执行的过程中，可以产生多个线程，形成多个执行流。每个线程也有其生命周期。<br/>由表示程序运行状态的寄存器（程序计数器，栈指针等）以及堆栈组成。<br/>用户级的实体：线程结构驻留在用户空间中，能被普通的用户级函数直接访问。<br/>调度和执行的单位：每个线程拥有独立的运行栈和程序计数器(pc)，线程切换的开销小。<br/>一个进程中的多个线程共享相同的内存单元/内存地址空间，线程包含进程地址空间中的代码和数据。（多个线程共享同一个进程中的方法区和堆，从同一堆中分配对象，可以访问相同的变量和对象。）<br/>线程被认为是以CPU为主体的行为：一个执行流是由CPU运行程序代码并操作程序的数据所形成的。 |

> 多线程：一个进程同一时间并行执行多个线程。
>
> - 提高应用程序的响应。对图形化界面更有意义，可增强用户体验。
> - 提高计算机系统CPU的利用率
> - 改善程序结构。将既长又复杂的进程分为多个线程，独立运行，利于理解和修改。
>
> 一个Java应用程序java.exe至少有三个线程：main()主线程，gc()垃圾回收线程，异常处理线程。

| 执行 | 说明                                |
| ---- | ----------------------------------- |
| 并行 | 多个CPU同时执行多个任务。           |
| 并发 | 一个CPU（时间片）同时执行多个任务。 |

- JVM允许程序运行多个线程：`java.lang.Thread类`。
1. 继承Thread类。

2. 实现Runnable接口。 
- Java中的线程分为两类：守护线程、用户线程。唯一区别是判断JVM何时离开。

- 守护线程：程序终止时，只剩下守护线程。服务用户线程、start()方法前调用。

```java
//把一个用户线程变成一个守护线程
Thread.setDaemon(true);
```

## 多线程基础

### 线程创建

#### Thread

- 每个线程都是通过某个特定Thread对象的run()方法来完成操作的，把run()方法的主体称为**线程体**。

| 构造器                                                | 说明                                               |
| :---------------------------------------------------- | :------------------------------------------------- |
| Thread()                                              | 创建新的Thread对象                                 |
| Thread(String threadname)                             | 创建线程并指定线程实例名                           |
| Thread(Runnable target)                               | 指定实现Runnable接口的线程体对象                   |
| Thread(Runnable target, String name)                  | 指定实现Runnable接口的线程体对象、线程名称         |
| Thread(ThreadGroup group,Runnable target,String name) | 指明该线程所属的线程组、提供线程体的对象、线程名称 |

1. 子类继承Thread类，重写run()方法（线程执行的操作声明在run()方法）。
2. 创建Thread子类对象，即创建了线程对象。
3. 调用线程对象start()方法：启动线程，调用run方法。

**注意点：**

1. 调用start()方法启动线程，线程体run()方法由JVM调用（OS的CPU调度决定）。如果直接调用run()方法则没有启动线程。
2. 一个线程对象只能调用一次start()方法启动，如果重复调用了，则将抛出异常“IllegalThreadStateException”。

```java
public class ThreadTest {
    public static void main(String[] args) { //主线程
      //3.创建Thread类的子类的对象 //主线程
        MyThread t1 = new MyThread();
      //4.通过此对象调用start()，启动t1线程 //主线程
        t1.start();
      //在主线程中创建t1对象，在当前线程中执行(与main线程一起执行)
      //4.1 启动当前线程
      //4.2 调用当前线程的run()

        //其余代码仍然是在main线程中执行的。
    }
}

//1.继承Thread
class MyThread extends Thread{
    //2.重写run()
    @Override
    public void run() {

    }
}
```

| 功能   | 方法               | 说明                                                         |
| ------ | :----------------- | :----------------------------------------------------------- |
| 启动   | start()            | 启动并调用当前线程的run()方法。                              |
| 线程体 | run()              | 线程在被调度时执行的操作，重写此方法。                       |
| 让步   | yield()            | 暂停当前正在执行的线程，把执行机会让给优先级相同或更高的线程。（释放当前CPU的执行权)（有可能立刻又被赋予执行权）<br> 若队列中没有同优先级的线程，忽略此方法。 |
| 阻塞   | join()             | 在某个线程中调用其他线程的 join() 方法时，该线程将被阻塞，直到 join() 方法加入的其他线程执行完，该线程才结束阻塞状态。 |
| 睡眠   | sleep(long millis) | 线程睡眠(毫秒)，进入阻塞状态；指定时间到后重排队。<br> 抛出InterruptedException异常。 |

> sleep()是Thread类的静态方法。

#### Runnable

- Runnable实现类的对象作为Thread类构造器的参数传入，以此创建线程对象。多个线程可以共享同一个Runnable接口实现类的对象。

> Thread实现Ruannable接口：
>
> ```java
> public class Thread extends Object implements Runnable
> ```

```java
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
```

```java
public class RunnableTest {
    public static void main(String[] args) {
      //3.Thread类构造器：new Thread(Ruannable target)
        Thread t = new Thread(new RunTest());
      //4.调用Thread类的start方法：开启线程，调用Runnable子类接口的run方法。
        t.start();
    }
}

//1.定义子类，实现Runnable接口
class RunTest implements Runnable {
   //2.子类中重写Runnable接口中的run方法
    @Override
    public void run() {
    }
}
```

#### ThreadGroup 线程组

- Java中每个线程都属于某个线程组（ThreadGroup）。线程组使一组线程可以作为一个对象进行统一处理/维护。

- 一个线程只能在创建时设置其所属的线程组，在线程创建后就不允许将线程从一个线程组移到另一个线程组。

```java
public Thread(ThreadGroup group,Runnable target);
public Thread(ThreadGroup group,String name);
public Thread(ThreadGroup group,Runnable target,String name);
```

- 若在线程创建时并没有显式指定线程组，则新创建的线程自动属于父线程所在的线程组。

> main线程组：Java应用程序启动时，为该应用程序创建了一个“main”线程组（最顶层线程组），如果以后创建的线程没有指定线程组，则这些线程都将属于main线程组。

- 一个线程组可以包含任意数目的线程；不仅可以包含线程，还可以包含其他线程组。

> 以main为根的线程与线程组的树状结构：在main中可以创建线程或线程组，并且可以在main的线程组中进一步创建线程组。

#### ThreadFactory

```java
ThreadFactory factory = Executors.defaultThreadFactory();
factory.newThread(new MyThread());
```

#### Callable

| 接口/实现类  | 说明                                                         |
| ------------ | ------------------------------------------------------------ |
| Callable接口 | 支持泛型的返回值（需要借助FutureTask类，比如获取返回结果）。<br/>call()方法可以抛出异常、返回值。 |
| Future接口   | 对具体Runnable、Callable任务的执行结果进行取消、查询是否完成、获取结果等。 |
| FutrueTask   | Futrue接口的唯一实现类。<br/>同时实现了Runnable, Future接口。既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值 |

1. Callable接口实现类，实现call()方法，将此线程需要执行的操作声明在call()方法中
2. 创建一个Callable接口实现类的对象，传递到FutureTask构造器中，创建FutureTask对象
3. 将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()方法启动线程。
4. （可选）获取callable中的call方法的返回值 

```java
public class ThreadNew {
    public static void main(String[] args) {
//      3. 创建一个Callable接口实现类的对象
        NuThreadd nThreadad = new Threadead();
//      4. 将Callable接口实现类的对象传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask futureTask = new FutureTaskThreadread);
//        FutureTask<Integer> futureTask = new FutureTask<IntegerThreadThread); //支持泛型
//      5. 将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()方法启动线程
        new Thread(futureTask).start();

        try {
//            6. 获取callable中的call方法的返回值
            //get()的返回值即为FutureTask构造器参数Callable实现类重写的call()方法的返回值
            Object sum = futureTask.get();
//            Integer sum = futureTask.get();//支持泛型
            System.out.println(sum);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

//1. 创建一个Callable接口的实现类
//claThreadThreadd implements Callable<Integer> { //支持泛型
//public Integer call() throws Exception {//支持泛型
clThreadThreadad implements Callable {

    //2. 实现call()方法，将此线程需要执行的操作声明在call()方法中
    @Override
    public Object call() throws Exception {
        return null;
    }
}
```

#### 线程池

1. 提高响应速度（减少了创建新线程的时间）

2. 降低资源消耗（重复利用线程池中线程，不需要每次都创建）

3. 便于线程管理

> corePoolSize：核心池的大小
>
> maximumPoolSize：最大线程数
>
> keepAliveTime：线程没有任务时最多保持多长时间后会终止

**Executors：工具类、线程池的工厂类，用于创建并返回不同类型的线程池**

| 方法                                | 说明                              |
| ----------------------------------- | --------------------------------- |
| Executors.newCachedThreadPool()     | 可根据需要创建新线程的线程池      |
| Executors.newFixedThreadPool(n);    | 可重用固定线程数的线程池          |
| Executors.newSingleThreadExecutor() | 只有一个线程的线程池              |
| Executors.newScheduledThreadPool(n) | 线程池，给定延迟后运行/定期执行。 |

**ExecutorService：真正的线程池接口。常见子类ThreadPoolExecutor**

| 方法                                     | 说明                      |
| ---------------------------------------- | ------------------------- |
| void execute(Runnable command)           | 执行任务/命令，没有返回值 |
| `<T> Future<T> submit(Callable<T> task)` | 执行任务，有返回值        |
| void shutdown()                          | 关闭连接池                |

```java
public class ThreadPool {
    public static void main(String[] args) {
//        1.提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

//        设置线程池的属性
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
//        System.out.println(service.getClass());
        service1.setCorePoolSize(15);
//        service1.setKeepAliveTime();

//        2.执行指定的线程的操作，需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new NumberThread()); //适合使用于Runnable
        service.execute(new NumberThread1()); //适合使用于Runnable
//        service.submit(Callable接口的实现类对象); //适合使用于Callable
        service.shutdown();
    }
}

class NumberThread implements Runnable {
    @Override
    public void run() {
    }
}

class NumberThread1 implements Runnable {
    @Override
    public void run() {
    }
}
```

### 调度 优先级

- 线程的调度：在单个CPU上以某种顺序运行多个线程。

| 调度策略 | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| 时间片   | 同优先级线程组成先进先出队列（先到先服务），使用时间片策略。<br/><img src="../../pictures/9075515221057.png" width="212"/> |
| 抢占式   | 高优先级的线程抢占CPU（对高优先级，使用优先调度的抢占式策略）。 |

| 调度方法                     | 说明             |
| ---------------------------- | ---------------- |
| getPriority()                | 返回线程优先值   |
| setPriority(int newPriority) | 改变线程的优先级 |

| 优先级        | 数值 |
| :------------ | :--- |
| MAX_PRIORITY  | 10   |
| MIN_PRIORITY  | 1    |
| NORM_PRIORITY | 5    |

- 一般，主线程具有普通优先级NORM_PRIOPITY。
- 线程创建时继承父线程的优先级。

**基于优先级的抢先式调度**

- Java基于线程的优先级选择高优先级的线程运行。该线程（当前线程）将持续运行，直到它中止运行，或其他高优先级线程成为可运行的。
  - 当其他高优先级线程可运行时，低优先级线程被高优先级线程抢占运行。
  - 低优先级只是获得调度的概率低，并非一定是在高优先级线程之后才被调用。
- 在Java运行系统中可以按优先级设置多个线程等待池，JVM先运行高优先级池中的线程，高优先级等待池空后，才考虑低优先级。如果线程运行中有更高优先级的线程成为可运行的，则CPU将被高优先级线程抢占。
- 抢先式调度可能是分时的，即每个同等优先级池中的线程轮流运行，也可能不是，即线程逐个运行，由具体JVM而定。

> 线程一般通过使用sleep()等方法保证给其他线程运行时间。

### 生命周期

<img src="../../pictures/204051510227351.png" />

**JDK中用Thread.State类定义了线程的几种状态**

- 新建状态 new： 当一个Thread类或其子类的对象被声明并创建时，新生的线程对象处于新建状态。线程还未被分配有关的系统资源。 
- 就绪（可运行）状态 runnable：处于新建状态的线程被start()后，将进入线程队列等待CPU时间片，此时它已具备了运行的条件，只是没分配到CPU资源（此时线程仅仅是可以运行）。

> start()方法使系统为线程分配必要的资源，将线程中虚拟的CPU置为Runnable状态，并将线程交给系统调度。
>
> 在多线程程序设计中，系统中往往会有多个线程同时处于Runnable状态，竞争有限的CPU资源，运行系统根据线程调度策略进行调度。

- 运行状态：当就绪的线程被调度并获得CPU资源时,便进入运行状态（线程占有CPU并实际运行的状态）， run()方法定义了线程的操作和功能 。此时线程状态的变迁有以下三种：

| 运行状态切换 | 方式                                                         |
| ------------ | ------------------------------------------------------------ |
| 终止状态     | 线程正常执行结束<br/>应用程序停止运行                        |
| 可运行状态   | 当前线程执行了yield()<br/>当前线程因调度策略由系统控制进入可运行状态 |
| 阻塞状态     | 当前线程执行sleep()、wait()<br/>其他线程执行join()<br/>线程中使用synchronized来请求对象的锁未获得时<br/>线程中需要交互输入/输出操作时。待输入/输出操作结束后，线程进入可运行状态。<br/>在某种特殊情况下，被人为挂起或执行输入输出操作时，让出 CPU 并临时中止自己的执行 |

> 执行过程中，有一个更高优先级的线程进入可运行状态，这个线程立即被调度执行，当前线程占有的CPU被抢占；或在分时方式时，当前执行线程执行完当前时间片

- 阻塞状态：

| 阻塞       | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| 对象锁阻塞 | 如果线程中使用synchronized来请求对象的锁但未获得时，进人对象锁阻塞状态。<br/>当获得对象锁后，将进人可运行状态。 |
| 等待阻塞   | 线程调用wait()方法时，线程由运行状态进人等待阻塞状态。<br/>在等待阻塞状态下的线程若被notify()和notifyAll()唤醒，被interrupt()中断或者等待时间到，线程将进入对象锁阻塞状态。 |
| 其他阻塞   | sleep()方法而进人其他阻塞状态的线程，睡眠时间到时将进人可运行状态，<br/>join()方法而进人其他阻塞状态的线程，当其他线程结束或等待时间到时，进入可运行状态。 |

- 死亡（终止）状态：线程完成了它的全部工作或线程被提前强制性地中止或出现异常导致结束，没有任何方法可改变该状态。     

### 同步

#### synchronized 同步锁机制

- 对于并发工作，需要防止两个任务访问相同的资源（共享资源竞争）。 当资源被一个任务使用时，在其上加锁。第一个访问某项资源的任务必须锁定这项资源，使其他任务在其被解锁之前，无法访问它。而在其被解锁后，另一个任务就可以锁定并使用它了。

> 对多条操作共享数据（多个线程共同操作的变量，如static）的语句，只能让一个线程都执行完，在执行过程中，其他线程不可以参与执行。

**同步的范围**

1. 如何找问题，即代码是否存在线程安全？
   1. 明确哪些代码是多线程运行的代码。
   2. 明确多个线程是否有共享数据。
   3. 明确多线程运行代码中是否有多条语句操作共享数据。
2. 如何解决呢？
   - 对多条操作共享数据的语句，只能让一个线程都执行完，在执行过程中，其他线程不可以参与执行。即所有操作共享数据的这些语句都要放在同步范围中。
3. 切记：
   - 范围太小：没锁住所有有安全问题的代码。
   - 范围太大：没发挥多线程的功能。

> **死锁**
>
> - **不同的线程分别占用对方需要的同步资源不放弃** ，都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
> - 出现死锁后， **不会出现异常，不会出现提示** ，只是所有的线程都处于阻塞状态，无法继续
> 1. 专门的算法、原则
> 2. 尽量减少同步资源的定义
> 3. 尽量避免嵌套同步

##### 临界区、对象锁

- 临界区（critical sections）：一个程序的多个并发线程中对同一个对象进行访问的代码段。临界区可以是一个语句块/方法，用synchronized关键字标识。

- 对象锁（monitor）：控制临界区。由`synchronized(obj){}`语句指定的对象obj设置一个锁，称为对象锁。

- 对象锁是一种独占的排他锁（exclusive locks）：一个线程获得了该对象锁（如：obj）后，便独占**所有**使用obj作为对象锁的临界区（即使该线程未使用其余临界区），其他想要进入使用obj对象锁的临界区的线程进入等待状态。

- 任意对象都可以作为同步锁：所有对象都自动含有单一的锁（监视器）。 
1. 对象锁的返还。对象的锁在如下几种情况下由持有线程返还。
   - 临界区执行完后。
   - 临界区抛出异常。
   - 持有锁的线程调用该对象的wait()方法。此时该线程将释放对象锁，该对象锁被放人对象的wait pool中，等待某种事件的发生。
2. 共享数据的所有访问都必须作为临界区，使用synchronized进行加锁控制。
3. synchronized保护的共享数据必须是私有的。将共享数据定义为私有的，使线程不能直接访问这些数据，必须通过对象的方法。而对象的方法中带有由synchronized标记的临界区，实现对并发操作多个线程的控制。
4. 同步方法：如果一个方法的整个方法体都包含在synchronized语句块中，则可以把该关键字放在方法的声明中。但控制对象锁的时间稍长，并发执行的效率会受到一定的影响。
5. 对象锁具有可重入性：一个线程在持有某个对象锁时，可以再次请求并获得该对象锁；避免单个线程因为自已已经持有的锁而产生死锁。

##### 对象锁的释放

**释放锁的操作**

- 当前线程的同步方法、同步代码块执行结束。
- 当前线程在同步代码块、同步方法中遇到break、return终止了该代码块、该方法的继续执行。
- 当前线程在同步代码块、同步方法中出现了未处理的Error或Exception，导致异常结束。
- 当前线程在同步代码块、同步方法中执行了线程对象的wait()方法，当前线程暂停，并释放锁。

**不会释放锁的操作**

- 线程执行同步代码块或同步方法时，程序调用`Thread.sleep()、Thread.yield()方法`暂停当前线程的执行
- 线程执行同步代码块时，其他线程调用了该线程的`suspend()方法`将该线程挂起，该线程不会释放锁（同步监视器）。

> 尽量避免使用suspend()、resume()来控制线程。

##### 不同临界区的对象锁

| 临界区     | 对象锁                                                       |
| ---------- | ------------------------------------------------------------ |
| 同步方法   | 一个线程类中的所有静态方法共用同一把锁（类名.class），所有非静态方法共用同一把锁（this）。 |
| 同步代码块 | 自己指定（指定需谨慎）<br/>this、类名.class                  |

- 必须确保使用同一个资源的多个线程共用一把锁，否则就无法保证共享资源的安全

##### 同步代码块

```java
synchronized (obj){  
    // 需要被同步的代码；(即操作共享数据的代码)
}
```

1. 操作共享数据（static修饰的等）的代码，即需要被同步的代码。
2. 同步监视器（对象锁）：多个线程必须要共用同一把锁。
3. 解决了线程的安全问题。操作同步代码时，只能有一个线程参与，其他线程等待。

| 线程             | 对象锁                                                       |
| ---------------- | ------------------------------------------------------------ |
| 实现Runnable接口 | 创建多个线程时是同一个Runnable对象内的方法体。<br/>this、类名.class、run()方法外的对象。 |
| 继承Thread类     | 创建多个线程时是不同的Thread对象内的方法体。<br/>慎用this、类名.class、run()方法外的静态对象（static）。 |

```java
class Window extends Thread {
    private static int tickets = 100;
    static Object obj = new Object(); //static 确保线程使用同一把锁

    @Override
    public void run() {
        while (true) {
//            Object obj = new Object(); 错误
            //synchronized (obj) { //要求线程必须共用同一把锁
            synchroniaed(Window.class){  //通过类作为锁，类也是对象。
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(getName() + "卖票，票号为：" + tickets);
                    tickets--;
                } else {
                    break;
                }
            }
        }
    }
}
```

##### 同步方法

- synchronized放在方法声明中，表示整个方法为同步方法。

| 线程             | 同步监视器                                         |
| ---------------- | -------------------------------------------------- |
| 实现Runnable接口 | this                                               |
| 继承Thread类     | 如果此时的同步方法是静态的，则同步监视器为当前类。 |

```java
class Windows implements Runnable {
    private int ticket = 100;
    Object obj = new Object();

    @Override
    public void run() {
        while (true) {
//            Object obj = new Object(); 错误
            changeTicket();
        }

    }

    //static修饰，则使用当前类作为对象锁
    public synchronized void changeTicket() {//同步监视器：this
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + ticket);
            ticket--;
        }
    }
}
```

#### Lock 同步锁对象

- java.util.concurrent.locks.Lock接口：显式定义的同步锁对象，每次只能有一个线程对Lock对象加锁，线程开始访问共享资源之前应先获得Lock对象。

- ReentrantLock 类实现了 Lock ，拥有与 synchronized 相同的并发性和内存语义，在实现线程安全的控制中，比较常用的是ReentrantLock，可以显式加锁、释放锁。

- 对于继承Thread类的方式，对ReentrantLock对象加static。

- 优先使用顺序：Lock --> 同步代码块（已经进入了方法体，分配了相应资源）--> 同步方法（在方法体之外）

| 对比 | synchronized                                                 | Lock                                   |
| ---- | ------------------------------------------------------------ | -------------------------------------- |
| 锁   | 隐式锁，离开作用域自动释放锁。                               | 显式锁：手动开启lock()、关闭锁unlock() |
| 同步 | 只有代码块锁                                                 | 有代码块锁和方法锁                     |
| 花费 | 将花费较少的时间来调度线程，性能更好。<br/>具有更好的扩展性（提供更多的子类）。 | -                                      |

```java
class Window implements Runnable {
    private int ticket = 100;
    //1.实例化
    private ReentrantLock lock = new ReentrantLock();
//    private ReentrantLock lock = new ReentrantLock(true);
//    true：公平 即不会一直都是一个线程抢占执行
//    默认false

    @Override
    public void run() {
        while (true) {
            try{
//                2.调用锁定lock()方法
                lock.lock();
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + ", 票号:" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }finally {
//                3. 调用解锁unlock()的方法
                lock.unlock();
            }

        }
    }
}
```

### 通信

| 通信方法    | 说明                                                         |
| ----------- | ------------------------------------------------------------ |
| wait()      | 令当前线程挂起并放弃CPU、同步资源并等待，使别的线程可访问并修改共享资源，而当前线程排队等候其他线程调用`notify()或notifyAll()`方法唤醒，唤醒后等待重新获得对监视器的所有权后才能继续执行。 |
| notify()    | 唤醒正在排队等待同步资源的线程中优先级最高者结束等待。       |
| notifyAll() | 唤醒正在排队等待资源的所有线程结束等待。                     |

- 这三个方法**只有在`synchronized方法或synchronized代码块`中才能使用**，否则会报`java.lang.IllegalMonitorStateException`异常。

- 因为这三个方法**必须由锁对象(即必须是同步代码/同步方法的同步监视器)调用**，而任意对象都可以作为synchronized的同步锁，因此这三个方法只能在**Object类中声明**。

#### wait()

- 线程对象.wait()：使当前线程进入等待（某对象）状态 ，直到另一线程对该对象发出 notify (或notifyAll) 为止。
- 调用方法的必要条件：当前线程必须具有对该对象的监控权（加锁）。
- 调用此方法后，当前线程将释放对象监控权（释放锁，即打开了锁，其他线程得以进入），然后进入等待。当前线程被notify后，要重新获得监控权，然后从断点处继续代码的执行。

| 异同                                      | sleep()                    | wait()                           |
| ----------------------------------------- | -------------------------- | -------------------------------- |
| 相同                                      | 使得当前的线程进入阻塞状态 | 使得当前的线程进入阻塞状态       |
| 声明位置不同                              | 在Thread类中声明           | 在Object类中声明                 |
| 调用的要求不同                            | 可以在任何需要的场景下调用 | 必须使用在同步代码块和同步方法中 |
| 是否释放同步监视器（同步代码块/同步方法） | 不释放                     | 释放                             |

#### notify()、notifyAll()

- 在当前线程中调用方法： 线程对象.notify()。唤醒等待该对象监控权的一个/所有线程(被wait()的线程)。
- 调用方法的必要条件：当前线程必须具有对该对象的监控权（加锁）。

## 多线程设计模式

### Single Threaded Execution

- Single Threaded Execution：对应于synchronized。

```java
package com.zjk.test;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadTest {
    public static void main(String[] args) {
        Resource resource = new Resource(3);
        for (int i = 0; i < 10; i++) {
            new MyThread(resource).start();
        }
    }
}

class Resource {
    private final int permits;
    private final Semaphore semaphore;

    public Resource(int permits) {
        this.permits = permits;
        this.semaphore = new Semaphore(permits);
    }

    public void use() throws InterruptedException {
        semaphore.acquire();
        try {
            System.out.println(Thread.currentThread().getName() + " 开始使用：" + (permits - semaphore.availablePermits()));
            Thread.sleep(new Random(314159).nextInt(500));
            System.out.println(Thread.currentThread().getName() + " 结束使用：" + (permits - semaphore.availablePermits()));
        } finally {
            semaphore.release();
        }
    }
}

class MyThread extends Thread {
    private final Resource resource;

    public MyThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            while (true) {
                resource.use();
                Thread.sleep(new Random(314159).nextInt(3000));
            }
        } catch (InterruptedException ex) {

        }
    }
}
```

### Immutable

- Immutable：不可变性（final）。

> UML 
>
> `{concurrent}`：不用声明为synchronized也线程安全。
>
> `{frozen}`：final。

> Copy-On-Write：写时复制。

### Guarded Suspension

<img src="../../pictures/Java-Guarded_Suspension.drawio.svg"/>

```java
while(守护条件){
    //如果不满足守护条件，则一直等待。
    wait();
}
//需要执行的代码。
```

- Guarded Suspension模式需要注意守护条件的失效，避免线程一直陷入while循环。

> Busy wait：Thread.yield()并不会释放锁，而wait()会释放；且该模式中的守护条件必须是 volatile。
>
> ```java
> while(守护条件){
>  Thread.yield();
> }
> ```

### Balking

- Balking：守护条件不成立时，线程立即返回。

> interrupt()：通知线程中断。处于等待状态的线程会重新获取锁，并抛出InterruptedException。

> Guarded Timed：超时处理。
>
> 1. 异常通知。
>
> 2. 返回值通知。

### Producer-Consumer

# 网络

> Java提供的网络类库可以实现无痛的网络连接，联网的底层细节被隐藏在 Java 的本机安装系统里，由 JVM 进行控制。并且 Java 实现了一个跨平台的网络库，程序员面对的是一个统一的网络编程环境。

## InetAddress IP地址

- InetAddress对象含有一个网络主机地址的域名和IP地址。分为Inet4Address、Inet6Address。

| 方法                   | 获取           |
| ---------------------- | -------------- |
| getByName(String host) | 域名对应IP地址 |
| getLocalHost()         | 127.0.0.1      |
| getHostName()          | 域名           |
| getHostAddress()       | 本机IP地址     |

## Socket 套接字

### TCP

**1. 客户端发送数据到服务端**

```java
package com.zjk.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPTest {

    //客户端
    @Test
    public void client() {

        Socket socket = null;
        OutputStream outputStream = null;
        try {
            //1. 创建Socket对象,指明服务器的IP和端口号
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet, 22112);
            //2. 获取一个输出流，输出数据
            outputStream = socket.getOutputStream();
            //3. 写出数据
            outputStream.write("客户端".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //4. 关闭资源
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //服务端
    @Test
    public void server(){
        ServerSocket serverSocket = null;
        Socket accept = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //1. 创建服务器端的ServerSocket，指明自己的端口号
            serverSocket = new ServerSocket(22112);
            //2. 调用accept()方法 表示接收来自客户端的Socket
            accept = serverSocket.accept();
            //3. 获取输入流
            inputStream = accept.getInputStream();

            //不建议，可能会有乱码
//        byte[] buffer = new byte[20];
//        int len;
//        while ((len = inputStream.read(buffer)) != -1) {
//            String str = new String(buffer, 0, len);
//            System.out.println(str);
//        }

            //4. 读取输入流中的数据
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[20];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }

            System.out.println(byteArrayOutputStream);

            System.out.println(accept.getInetAddress().getHostAddress());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //5. 关闭资源
            try {
                if(byteArrayOutputStream != null)
                    byteArrayOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if(inputStream!=null)
                    inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if(accept!=null) {
                    accept.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if(serverSocket!=null)
                    serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
```

**2. 客户端发送文件到服务端，服务端将文件保存到本地**

```java
package com.zjk.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTest2 {
    @Test
    public void client() {
        Socket socket = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 22112);
            outputStream = socket.getOutputStream();
            bufferedInputStream = new BufferedInputStream(new FileInputStream("th.jpg"));

            byte[] buffer = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bufferedInputStream != null)
                    bufferedInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void server() {
        ServerSocket serverSocket = null;
        Socket accept = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            serverSocket = new ServerSocket(22112);
            accept = serverSocket.accept();
            inputStream = accept.getInputStream();
            fileOutputStream = new FileOutputStream(new File("thClient.jpg"));

            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (accept != null)
                    accept.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (serverSocket != null)
                    serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
```

**3.从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端。并关闭相应的连接。**

```java
package com.zjk.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTest3 {
    @Test
    public void client() {
        Socket socket = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 22112);
            outputStream = socket.getOutputStream();
            bufferedInputStream = new BufferedInputStream(new FileInputStream("th.jpg"));

            byte[] buffer = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            //关闭数据的输出
            socket.shutdownOutput();

            //接收服务端的反馈，并显示在控制台
            inputStream = socket.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer2 = new byte[20];
            int len2;
            while ((len2 = inputStream.read(buffer2)) != -1) {
                byteArrayOutputStream.write(buffer2, 0, len2);
            }

            System.out.println(byteArrayOutputStream.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bufferedInputStream != null)
                    bufferedInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (inputStream!=null)
                    inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if(byteArrayOutputStream!=null)
                    byteArrayOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void server() {
        ServerSocket serverSocket = null;
        Socket accept = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        OutputStream outputStream = null;

        try {
            serverSocket = new ServerSocket(22112);
            accept = serverSocket.accept();
            inputStream = accept.getInputStream();
            fileOutputStream = new FileOutputStream(new File("thClient2.jpg"));

            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }

            //服务器端给予客户端反馈
            outputStream = accept.getOutputStream();
            outputStream.write("服务端：已接收客户端文件。".getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (accept != null)
                    accept.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (serverSocket != null)
                    serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if(outputStream!=null)
                    outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
```

### UDP

```java
package com.zjk.java1;


import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPTest {

    //发送端
    @Test
    public void sender() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();

            String str = "UDP:发送端";
            byte[] date = str.getBytes();
            InetAddress inet = InetAddress.getLocalHost();
            DatagramPacket packet = new DatagramPacket(date, 0, date.length, inet, 21122);

            socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    //接收端
    @Test
    public void receiver() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(21122);

            byte[] buffer = new byte[100];
            DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);

            socket.receive(packet);

            System.out.println(new String(packet.getData(), 0, packet.getLength()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
```

### URL

- URL对象一旦创建就不可修改。

| 构造器                                                       | 说明                                                      |
| ------------------------------------------------------------ | --------------------------------------------------------- |
| public URL (String spec)                                     | `new URL ("http://www. atguigu.com/") `                   |
| public URL(URL context, String spec)                         | `new URL(url, “download.html")`                           |
| public URL(String protocol, String host, String file)        | `new URL("http", "www.atguigu.com", “download. html")`    |
| public URL(String protocol, String host, int port, String file) | `new URL("http", "www.atguigu.com", 80, “download.html")` |

| 方法           | 获取                         |
| -------------- | ---------------------------- |
| getProtocol( ) | 协议名                       |
| getHost( )     | 主机名                       |
| getPort( )     | 端口号<br />如果没有则返回-1 |
| getPath( )     | 全路径                       |
| getRef()       | 相对路径                     |
| getFile( )     | 文件名                       |
| getQuery( )    | 查询名                       |

```java
//URL对象信息的获取

package com.zjk.java1;

import java.net.MalformedURLException;
import java.net.URL;

public class URLtest1 {
    public static void main(String[] args) {
        try {
            URL url = new URL("http:/java.sun.com:80/docs/books/tutorial/index.html#DOWNLOADING");

            //getProtocol() 获取协议名
            System.out.println(url.getProtocol());//http
            //getHost() 获取主机
            System.out.println(url.getHost());
            //getPort() 获取端口
            System.out.println(url.getPort());//-1
            //getRef() 获取相对路径
            System.out.println(url.getRef());//DOWNLOADING
            //getFile() 获取文件名
            System.out.println(url.getFile());// /java.sun.com:80/docs/books/tutorial/index.html
            //getQuery() 获取查询名
            System.out.println(url.getQuery());//null
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

