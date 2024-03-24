# <code>java.lang.reflect</code> 反射

- 反射：动态语言的关键（Java是准动态语言） ，在运行时借助于Reflection API取得任何类的内部信息，并能直接操作任意对象的内部属性及方法。

<img src="../../pictures/54754423239580.png" width="600"/>    

- 运行时的反射：判断任意一个对象所属的类、构造任意一个类的对象、判断任意一个类所具有的成员变量和方法、获取泛型信息、调用任意一个对象的成员变量和方法、处理注解、动态代理。

<table>
	<thead>
		<tr>
			<th width="30%">反射相关API</th>
			<th width="70%">说明</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>java.lang.Class</td>
			<td>类</td>
		</tr>
		<tr>
			<td>java.lang.reflect.Method</td>
			<td>方法</td>
		</tr>
		<tr>
			<td>java.lang.reflect.Field</td>
			<td>成员变量</td>
		</tr>
		<tr>
			<td>java.lang.reflect.Constructor</td>
			<td>构造器</td>
		</tr>
	</tbody>
</table>

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

# ClassLoader 类加载器

> 类的加载过程：JVM部分。

```java
//获取对应的类加载器
class.getClassLoader();

//获取父级类加载器（不存在则返回null）
classLoader.getParent(); 
```

# Class 类对象

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

## 运行时类

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

# Proxy 动态代理

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
>  void produceCloth();
> }
> 
> //代理类
> class ProxyClothFactory implements ClothFactory{
> 
>  private ClothFactory factory; //用被代理类对象进行实例化
> 
>  public ProxyClothFactory(ClothFactory factory){
>      this.factory = factory;
>  }
> 
>  @Override
>  public void produceCloth() {
>      System.out.println("代理工厂");
> 
>      factory.produceCloth();
> 
>      System.out.println("后续");
>  }
> }
> 
> //被代理类
> class NikeClothFactory implements ClothFactory{
>  @Override
>  public void produceCloth() {
>      System.out.println("Nike工厂生产");
>  }
> }
> 
> public class ProxyTest {
>  public static void main(String[] args) {
>      //创建被代理类的对象
>      NikeClothFactory nikeClothFactory = new NikeClothFactory();
>      //创建代理类的对象
>      ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nikeClothFactory);
> 
>      proxyClothFactory.produceCloth();
>  }
> }
> ```

# 
