# 代理模式 Proxy

- 代理模式 Proxy：通过代理对象访问目标对象，可扩展目标对象的功能。
  - 被代理对象：远程对象、创建开销大的对象、需要安全控制的对象。

1. 静态代理。
2. 动态代理
   1. JDK代理、接口代理。
   2. Cglib代理、子类代理。

<img src="../../pictures/设计模式-Proxy.drawio.svg" width="500"/> 

# 静态代理

- 静态代理：目标对象和代理对象一起实现相同的接口 或 继承相同的父类，代理对象通过调用相同的方法来调用目标对象的方法。

1. 在不改变目标对象的功能前提下，通过代理对象对目标功能扩展。
2. 代理对象需要和目标对象实现一样的接口，会产生多个代理类。
3. 一旦接口增加方法，目标对象和代理对象都需要维护。

<img src="../../pictures/设计模式-Proxy-Static.drawio.svg" width="400"/> 

```java
TeacherDaoProxy proxy = new TeacherDaoProxy(new TeacherDao());
proxy.teach();
```

```java
public interface TeacherDaoInterface{
    void teach();
}

class TeacherProxy implements TeacherDao{

    private TeacherDaoInterface target;

    public TeacherProxy(TeacherDaoInterface target){
        this.target = target;
    }

    public void teach(){
        System.out.println("代理开始...");
        target.teach();
        System.out.println("提交...");
    }
}

class TeacherDao implements TeacherDaoInterface{
    public void teach(){
        System.out.println("teach");
    }
}
```

# JDK代理

- JDK代理：代理对象不需要实现接口，而目标对象需要实现接口。代理对象通过java.lang.reflect.Proxy生成，动态地在内存中构建代理对象。

1. 代理的类不能是final修饰的：javal.ang.IllegaArgumentException。
2. 目标对象的方法如果为final/static，则不会被拦截，不执行额外的扩展功能。 

```java
public static Object newProxyInstance(ClassLoader loader,
                                      Class<?>[] interfaces,
                                      InvocationHandler h)
```

| 参数                    | 说明                                                         |
| ----------------------- | ------------------------------------------------------------ |
| ClassLoader loader      | 指定当前目标对象的类加载器，获取加载器的方法固定。           |
| `Class<?>[] interfaces` | 目标对象实现的接口类型。                                     |
| InvocationHandler h     | 事件处理：执行目标对象方法时，触发事件处理器方法。<br />传入目标对象方法。 |

```java
TeacherDaoInterface proxy = (TeacherDaoInterface)new ProxyFactory(new TeacherDao()).getProxy();
proxy.say();
proxy.teach();
System.out.println(proxy.getClass());//class $Proxy0
```

```java
public class ProxyFactory {

    private Object target;

    public ProxyFactory(TeacherDao target) {
        this.target = target;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("代理开始...");
                        Object returnVal = method.invoke(target, args);
                        System.out.println("提交...");
                        return returnVal;
                    }
                });
    }
}

interface TeacherDaoInterface {
    void teach();

    void say();
}

class TeacherDao implements TeacherDaoInterface {
    public void teach() {
        System.out.println("teach");
    }

    public void say() {
        System.out.println("say");
    }
}
```

# Cglib代理

- Cglib代理（子类代理）：目标子类对象实现代理，在内存中创建子类对象来实现对目标对象功能扩展。不需要实现任何接口。

  - 代理的类不能是final修饰的。

- AOP编程选择代理模式：

1. 目标对象需要实现接口，用JDK代理。

2. 目标对象不需要实现接口，用Cglib代理。

- Cglib底层：字节码处理框架ASM转换字节码并生成新的类。

```java
class ProxyFactory implements MethodInterceptor{
    private Object target;

    public ProxyFactory(Object target){
        this.target = target;
    }

    public Object getProxy(){
        Enhancer enhancer = new Enhance();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib-代理开始...");
        Object re = method.invoke(target, objects);
        System.out.println("cglib-代理提交...");
        return re;
    }
}

class TeacherDao{
    public void teach() {
        System.out.println("teach");
    }

    public void say() {
        System.out.println("say");
    }
}
```

# 变体

1. 防火墙代理：内网通过代理穿透防火墙，实现对公网的访问。
2. 缓存代理：请求资源时，先从缓存代理中获取，如果缓存代理中取不到，再去公网、数据库中获取。
3. 远程代理：将远程对象当作本地对象来调用。
4. 同步代理：多线程间同步工作。

