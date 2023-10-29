- 本地方法（Native Method）（native关键字）：Java调用非Java代码的接口（本地方法接口 native），方法体通过非Java代码实现。除非与硬件有关，否则较少使用。

```java
private native void suspend0();
public static native void yield();
public static native void sleep(long millis) throws InterruptedException;
//native不能和abstract一起使用
```

- 与Java环境外交互：Java与一些底层系统的交互。
- 与操作系统交互：实现JRE和底层系统的交互，JVM的部分由C语言编写。
- Sun's Java：Sun的解释器使用C实现，使得其能像普通的C一样与外部交互。
