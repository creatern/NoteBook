# ClassLoader

- ClassLoader：抽象类，其后所有的类加载器都继承自ClassLoader（除了启动类加载器 Bootstrap ClassLoader）。ClassLoader（类加载器）是JVM执行类加载机制的前提，所有的Class都是ClassLoader加载的。ClassLoader以各种方式将Class信息的二进制数据读入JVM内部，转换为一个与目标类对应的java.lang.Class实例，之后由JVM进行链接、初始化等操作。
- ClassLoader只负责class文件的加载（Loading：`.class文件 --> JVM --> DNA元数据模板`），由Execution Engine决定是否可以运行。

> 类的唯一性：任何类都需要由加载其的类加载器和该类共同确认其在JVM中的唯一性。

- 每个类加载器都有一个独立的命名空间，由该类加载器和所有的父类加载器所加载的类组成。当两个类由同一个类加载器加载时，命名空间用于比较这两个类是否相同。在同一个命名空间中，不允许出现全类名相同的两个类。而即使这两个类来自同一个class文件且被同一个JVM加载，只要加载这两个类的类加载器不同，就一定不相等（不处于同一个命名空间）。

> 不同的类加载器加载的类是不同的，相互之间不能转换和兼容。

- 类加载机制的三个基本特征：

1. 双亲委派模型，并非所有类加载都遵守。
2. 可见性，子类加载器可以访问父类加载器加载的类型，而父类加载器则无法访问子类加载器加载的类型。
3. 单一性，父类加载器加载过的类型不会在子类加载器中重复加载。但是，由于同一级的类加载器之间不可见，同一个类型可以被同一级的类加载器加载多次。

> 自定义的类（Class.forName()）默认使用系统加载器。

- 数组类型的类加载器并非是加载时获取的，而是自动在运行时通过Class.getClassLoader()获取，且与数组中元素类型的类加载器相同，若元素为基本数据类型，则数组没有类加载器（基本数据类型由JVM预定义，不需要类加载器）。

| 获取ClassLoader                                | 说明                        |
| ---------------------------------------------- | --------------------------- |
| clazz.getClassLoader()                         | 当前类的ClassLoader         |
| Thread.currentThread().getContextClassLoader() | 当前线程上下文的ClassLoader |
| ClassLoader.getSystemClassLoader()             | 系统ClassLoader             |
| DriverManager                                  | 调用者的ClassLoader         |

## Classloader主要方法

| ClassLoader         | 说明                                                         |
| ------------------- | ------------------------------------------------------------ |
| getParent()         | 返回该类加载器的父类加载器                                   |
| loaderClass(..)     | 加载名称为name的类<br />返回java.lang.Class实例              |
| findClass(..)       | 查找名称为name的类<br />返回java.lang.Class实例              |
| findLoadedClass(..) | 查找名称为name的已经被加载过的类<br />返回java.lang.Class实例 |
| defineClass(..)     | 把字节数组b中的内容转化为一个Java类<br />返回java.lang.Class实例 |
| resolveClass(..)    | 连接指定的一个Java类                                         |

### loadClass(..) 双亲委派模型

1. Invoke **findLoadedClass(String)** to check if the class has already been loaded.
2. Invoke the **loadClass** method on the parent class loader. If the parent is null the class loader built into the virtual machine is used, instead.
3. Invoke the **findClass(String)** method to find the class.

| 方法                      | 区别                                              |
| ------------------------- | ------------------------------------------------- |
| Class.forName(..)         | 静态方法，class文件加载到内存时，执行类的初始化。 |
| ClassLoader.loadClass(..) | 实例方法，指定类第一次使用时，执行类的初始化。    |

### findClass(..)

- findClass(..)：父类加载器加载失败后，由当前类加载器尝试加载。

> findClass(..)、defindClass(..)主要由URLClassLoader实现。

### defineClass(..)

- defineClass(..)：JDK为核心类库提供的保护机制，所有派生于ClassLoader的类加载器最终都要调用java\.lang\.ClassLoader\.defineClass(String, byte\[\], int, int, ProtectionDomain\)，该方法执行preDefineClass()接口（提供对JDK核心库的保护）。

## 用户自定义类加载器

- 用户自定义的类加载器派生于java\.lang\.ClassLoader，且父类加载器为系统类加载器（AppClassLoader）。

> 隔离加载类、修改类加载的方式、扩展加载源、防止源码泄露。

- 不建议重写loadClass\(\)，而是将自定义的类加载逻辑重写findClass\(\)。（尽量保留双亲委派机制）

> 系统类加载器等（非用户自定义加载器）的findClass()方法默认的根目录（寻找class文件的起点）一般是out目录，用户自定义类加载器的根目录可以由用户指定（修改findClass()方法）。

```java
public class MyClassLoader extends ClassLoader {
    private String byteCodePath;

    public MyClassLoader(String byteCodePath) {
        this.byteCodePath = byteCodePath;
    }

    public MyClassLoader(ClassLoader parent, String byteCodePath) {
        super(parent);
        this.byteCodePath = byteCodePath;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(byteCodePath + className + ".class"));
             ByteArrayOutputStream baos = new ByteArrayOutputStream()
            ) {
            //具体读入数据并写出的过程
            int len;
            byte[] data = new byte[1024];
            while ((len = bis.read(data)) != -1) {
                baos.write(data, 0, len);
            }

            //获取内存中的完整的字节数组的数据
            byte[] byteCodes = baos.toByteArray();
            //调用defineClass()，将字节数组的数据转换为Class的实例。
            Class clazz = defineClass(null, byteCodes, 0, byteCodes.length);
            return clazz;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
/**
MyClassLoader loader = new MyClassLoader(System.getProperty("user.dir"));
Class clazz = loader.loadClass("/out/production/chapter04/com/atguigu/java1/Demo1");
*/
```

# 类加载器分类

<img src="../../pictures/JVM-ClassLoader-sp.drawio.svg" width="600"/> 

-  上下层的类加载器之间是包含关系，而不是继承关系。抽象类ClassLoader内部定义了一个对上层类加载器的引用（parent），而自定义类加载器都派生于该类，因此，下层的类加载器包含上层类加载器的引用。
-  JDK9：平台类加载器和应用程序类加载器不再继承自java.net.URLClassLoader；启动类加载器、平台类加载器、应用程序类加载器都继承于jdk.internal.loader.BuiltinClassLoader。且可以获取类加载器的名称（getName\(\)）。

```java
//系统类加载器
ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
System.out.println(systemClassLoader); //jdk.internal.loader.ClassLoaders$AppClassLoader@78308db1
//平台类加载器
ClassLoader extClassLoader = systemClassLoader.getParent();
System.out.println(extClassLoader); //jdk.internal.loader.ClassLoaders$PlatformClassLoader@16b98e56
//引导类加载器：获取不到 null
ClassLoader bootClassLoader = extClassLoader.getParent();
System.out.println(bootstrapClassLoader); //null

//用户自定义类：默认使用 系统类加载器 加载
ClassLoader classLoader = StackStruTest.class.getClassLoader();
System.out.println(classLoader); //jdk.internal.loader.ClassLoaders$AppClassLoader@78308db1

//String类等Java核心类库：使用引导类加载器加载。
ClassLoader stringClassLoader = String.class.getClassLoader();
System.out.println(stringClassLoader); //null
```

## BootClassLoader 启动类加载器

- JDK8：BootstrapClassLoader 引导类加载器，使用C/C++语言实现，嵌套于JVM内部，并不继承java.lang.ClassLoader，没有父加载器。
- JDK9：BootClassLoader 启动类加载器，在JVM内部和Java类库共同协作实现的类加载器（不再是C\+\+实现），但试图获取启动类加载器时，仍然返回null。

1. 加载JDK核心库，用于提供JVM自身需要的类。
2. 加载扩展类、应用程序类加载器，并指定为他们的父加载器。
3. 出于安全，Bootstrap启动类加载器只加载包名为java、javax、sun等开头的类。

## PlatformClassLoader 平台类加载器 

- PlatformClassLoader 平台类加载器：Java语言编写，、派生于ClassLoader类，父类加载器为启动类加载器。

- JDK8：扩展类加载器（ExtensionClassLoader）由sun\.misc\.Launch\$ExtClassLoader实现。从java\.ext\.dirs系统属性所指定的目录中加载类库、或从JDK的安装目录的jre/lib/ext扩展目录中加载类库。（如果用户创建的jar放在此目录中，也会自动由扩展类加载器加载）

- JDK9：基于模块化构建，tool.jar、rt.jar被拆分为多个JMOD文件，移除了扩展机制（/lib/ext目录），扩展类加载器（ExtClassLoade）被平台类加载器（PlatformClassLoader）替代。

```java
//获取平台类加载器
ClassLoader.getPlatformClassLoader();
```

## AppClassLoader 应用程序类加载器

- AppClassLoader 应用程序加载器（系统类加载器），Java语言编写：sun\.misc\.Launch\$AppClassLoader实现、派生于ClassLoader类，父类加载器为扩展类加载器。
- 负责加载环境变量classpath、或系统属性java.class.path 指定路径下的类库。
- 程序中默认的类加载器：一般的Java应用的类都是由系统类加载器完成加载。

```java
//获取系统类加载器
ClassLoader.getSystemClassLoader();
```

# 双亲委派机制

- 双亲委派机制：如果一个类加载器收到类加载的请求，则将这个请求委托给父类的加载器执行，如果父类加载器存在其父类加载器，则依次向上委托，请求最终到达顶层的启动类加载器。如果父类加载器可以完成类加载器，就成功返回；否则由子加载器尝试加载。

- JDK8：只有当父类加载器反馈自己无法完成该加载请求时，子类加载器才会尝试自己去完成加载。
- JDK9：在委派给父类加载器之前，先判断该类是否能够归属到某一个系统模块中，若找到相应的归属关系，则优先委派给负责该系统模块的类加载器来完成加载。

> ClassLoader\#loadClass\(\.\.\) 默认实现双亲委派机制，重写该方法可以修改委派机制。但用户自定义类加载器仍然不能加载核心类库，因为defineClass(..)提供对核心类库的保护。

<img src="../../pictures/JVM-ClassLoader-delegion-model.drawio.svg" width="1000"/>  

1. 避免类的重复加载（命名空间）。
2. 保护程序安全，防止核心API被篡改。 

## 破坏双亲委派机制

- 双亲委派机制检查类是否加载的委托过程是单向的，上层的类加载器无法访问下层的类加载器加载的类。为了屏蔽这点，而破坏双亲委派机制。

1. 第一次破坏：JDK1\.2，添加findClass\(\.\.\)。
2. 第二次破坏：JDK1\.3，线程上下文类加载器（Thread Context ClassLoader），通过Thread\#setContextClassLoader()设置，违背双亲委派机制的一般性原则。

<img src="../../pictures/JVM-ContextClassLoader-delegation-destory.drawio.svg" width="600"/> 

3. 第三次破坏：满足用户对线程动态性的追求（代码热替换 Hot Swap、模块热部署 Hot Deployment 等）。OSGI的每一个程序模块（Bundle）都有一个独立的类加载器，当需要更换一个Bundle时，连同其类加载器一同替换；不再是双亲委派模型的树状结构，而是网状结构。

<img src="../../pictures/JVM-OSGi-delegation-destory3.drawio.svg" width="400"/> 

### 热替换

- 热替换：服务不能中断，修改必须能立即表现在正在运行的系统之中。

> 不同的类加载器加载的类是不同的，相互之间不能转换和兼容。

<img src="../../pictures/JVM-Hot-replace-delegation-destory.drawio.svg" width="400"/> 

# 沙箱安全机制

- 沙箱安全机制：将代码限定在JVM特定的运行范围中，并严格限制代码对系统资源的访问。 

- JDK1.6引入域（Domain）的概念。JVM将所有代码加载到不同的系统和应用域。系统域部分专门负责与关键资源交互，而各个应用域部分则通过系统域的部分代理来对各种需要的资源进行访问。JVM中不同的受保护域（Protected Domain）对应不同的权限（Permission）。存在于不同域的类文件就具有了当前域的全部权限。

<img src="../../pictures/JVM-sandbox-JDK6.drawio.svg" width="500"/> 


