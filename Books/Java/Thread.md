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

# 线程创建

## Thread

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

## Runnable

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

## ThreadGroup 线程组

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

## ThreadFactory

```java
ThreadFactory factory = Executors.defaultThreadFactory();
factory.newThread(new MyThread());
```

## Callable

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

## 线程池

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

# 调度 优先级

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

# 生命周期

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

# 同步

## synchronized 同步锁机制

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
>
> 1. 专门的算法、原则
> 2. 尽量减少同步资源的定义
> 3. 尽量避免嵌套同步

### 临界区、对象锁

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

### 对象锁的释放

**释放锁的操作**

- 当前线程的同步方法、同步代码块执行结束。
- 当前线程在同步代码块、同步方法中遇到break、return终止了该代码块、该方法的继续执行。
- 当前线程在同步代码块、同步方法中出现了未处理的Error或Exception，导致异常结束。
- 当前线程在同步代码块、同步方法中执行了线程对象的wait()方法，当前线程暂停，并释放锁。

**不会释放锁的操作**

- 线程执行同步代码块或同步方法时，程序调用`Thread.sleep()、Thread.yield()方法`暂停当前线程的执行
- 线程执行同步代码块时，其他线程调用了该线程的`suspend()方法`将该线程挂起，该线程不会释放锁（同步监视器）。

> 尽量避免使用suspend()、resume()来控制线程。

### 不同临界区的对象锁

| 临界区     | 对象锁                                                       |
| ---------- | ------------------------------------------------------------ |
| 同步方法   | 一个线程类中的所有静态方法共用同一把锁（类名.class），所有非静态方法共用同一把锁（this）。 |
| 同步代码块 | 自己指定（指定需谨慎）<br/>this、类名.class                  |

- 必须确保使用同一个资源的多个线程共用一把锁，否则就无法保证共享资源的安全

### 同步代码块

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

### 同步方法

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

## Lock 同步锁对象

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

# 通信

| 通信方法    | 说明                                                         |
| ----------- | ------------------------------------------------------------ |
| wait()      | 令当前线程挂起并放弃CPU、同步资源并等待，使别的线程可访问并修改共享资源，而当前线程排队等候其他线程调用`notify()或notifyAll()`方法唤醒，唤醒后等待重新获得对监视器的所有权后才能继续执行。 |
| notify()    | 唤醒正在排队等待同步资源的线程中优先级最高者结束等待。       |
| notifyAll() | 唤醒正在排队等待资源的所有线程结束等待。                     |

- 这三个方法**只有在`synchronized方法或synchronized代码块`中才能使用**，否则会报`java.lang.IllegalMonitorStateException`异常。

- 因为这三个方法**必须由锁对象(即必须是同步代码/同步方法的同步监视器)调用**，而任意对象都可以作为synchronized的同步锁，因此这三个方法只能在**Object类中声明**。

## wait()

- 线程对象.wait()：使当前线程进入等待（某对象）状态 ，直到另一线程对该对象发出 notify (或notifyAll) 为止。
- 调用方法的必要条件：当前线程必须具有对该对象的监控权（加锁）。
- 调用此方法后，当前线程将释放对象监控权（释放锁，即打开了锁，其他线程得以进入），然后进入等待。当前线程被notify后，要重新获得监控权，然后从断点处继续代码的执行。

| 异同                                      | sleep()                    | wait()                           |
| ----------------------------------------- | -------------------------- | -------------------------------- |
| 相同                                      | 使得当前的线程进入阻塞状态 | 使得当前的线程进入阻塞状态       |
| 声明位置不同                              | 在Thread类中声明           | 在Object类中声明                 |
| 调用的要求不同                            | 可以在任何需要的场景下调用 | 必须使用在同步代码块和同步方法中 |
| 是否释放同步监视器（同步代码块/同步方法） | 不释放                     | 释放                             |

## notify()、notifyAll()

- 在当前线程中调用方法： 线程对象.notify()。唤醒等待该对象监控权的一个/所有线程(被wait()的线程)。
- 调用方法的必要条件：当前线程必须具有对该对象的监控权（加锁）。
