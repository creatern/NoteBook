# System

- java.lang.System：代表系统，系统级的很多属性和控制方法都放置在该类的内部。

- System的构造器都是private的，而内部的成员变量和成员方法都是static的。

| System成员变量 | 说明           |
| -------------- | -------------- |
| in             | 标准输入流     |
| out            | 标准输出流     |
| err            | 标准错误输出流 |

| System成员方法                  | 说明                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| native long currentTimeMillis() | 返回当前的计算机时间（时间戳）<br />当前计算机时间和GMT时间1970年1月1号0时0分0秒所差的毫秒数。 |
| void exit(int status)           | 退出程序<br />status的值为0代表正常退出，非零代表异常退出    |
| void gc()                       | 请求系统进行垃圾回收                                         |
| String getProperty(String key)  | 获得系统中属性名为key的属性对应的值                          |

<img src="../../../../pictures/89133321239495.png" width="600"/>    


```java
String javaVersion = System.getProperty("java.version");
System.out.println("java的version:" + javaVersion);

String javaHome = System.getProperty("java.home");
System.out.println("java的home:" + javaHome);

String osName = System.getProperty("os.name");
System.out.println("os的name:" + osName);

String osVersion = System.getProperty("os.version");
System.out.println("os的version:" + osVersion);

String userName = System.getProperty("user.name");
System.out.println("user的name:" + userName);

String userHome = System.getProperty("user.home");
System.out.println("user的home:" + userHome);

String userDir = System.getProperty("user.dir");
System.out.println("user的dir:" + userDir);
```
