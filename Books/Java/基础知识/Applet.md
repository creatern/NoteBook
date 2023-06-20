# Applet概述

- Applet是能够嵌入到HTML页面中，并能够在浏览器中运行的Java类
- Applet本身不能运行，必须嵌入到其他应用程序中运行（如Web浏览器或Java applet View）。

**Applet和Aplication的区别主要在于执行方式上：**

- Application以main()函数为入口运行
- Applet必须嵌入到其他应用程序中运行，更复杂。

**运行过程**

1. 浏览器加载指定URL中的HTML文件
2. 浏览器解析HTML文件
3. 浏览器加载HTML文件中指定的Applet类
4. 浏览器中的Java运行环境运行该Applet

# 生命周期

1. 加载Applet

   - 当一个Applet被下载到本地系统时，将发生如下操作：

   1. 产生1个Applet主类的实例
   2. 对Applet自身进行初始化
   3. 启动Applet运行，将Applet完全显示出来

2. 离开或返回Applet所在Web页

   - 当用户离开Applet所在的Web页，Applet将停止自身运行。而当用户再回到Applet所在Web页时，Applet又一次启动运行。

3. 重新加载Applet

   - 当用户执行浏览器的刷新操作时，浏览器将先卸载该Applet，再加载该Applet。即：Applet先停止自身的运行，接着实现善后处理，释放Applet占用的所有资源，然后加载Applet，加载过程和之前的加载过程相同。

4. 退出浏览器

   - 当用户退出浏览器时，Applet停止自身执行，实现善后处理，才让浏览器退出。

<img src="../../pictures/Snipaste_2023-03-02_22-54-14.png" width="800"/>  

# Applet的类层次结构

- 任何嵌入到Web页面或appletView中的Applet接口必须是Java中Applet类的子类。
- Applet类定义了Applet与其运行环境之间的一个标准的接口，主要包括Applet生命周期环境交互等一些方法。

- java.lang.Object
  - java.awt.Component
    - java.awt.Container
      - java.awt.Window
        - java.awt.frame
          - java.swing.JFrame  
      - java.awt.panel  
        - java.applet.Applet
          - javax.swing.JApplet

# Applet的显示

**Applet类继承了Component类**的组件绘制和显示的方法，有 **paint()、update()、repaint()** 方法。

- paint(Graphics g)： 向Applet中画图、显示字符串时使用该方法。每当Applet初次显示或更新时，浏览器都将调用paint(Graphics g)方法。
  - 该方法的参数为java.awt.Graphics类的实例，包括了Applet的Panel的图形上下文信息。 
- update()：用于更新Applet的显示。该方法将首先清除背景，然后再调用paint()方法进行Applet的具体绘制。用户定义的Applet该方法一般不用重写。
- repaint()：主要用于对Applet的重新显示，调用update()方法实现对Applet的更新。Applet程序可以在需要显示更新时调用该方法，通知系统刷新显示。

**Applet的线程更新由一个AWT线程控制，有以下两种控制：**

1. 在Applet的**初次显示，或运行过程中浏览器窗口大小发生变化**而引起Applet的显示发生变化时，该线程将调用Applet的**paint()**方法来进行Applet绘制。
2. Applet代码需要**更新显示内容**，从程序中调用repaint()方法，则AWT线程在接受到该方法的调用后，将调用Applet的update()方法，而update()方法再调用组件的paint()方法实现显示的更新。即：**repaint()-->update()-->paint()**

**Graphics类**