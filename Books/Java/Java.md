# 安装配置

## 环境变量  

Path是系统的管道符变量，它的作用类似于管道，可以想象成系统利用管道打通指定的路径，使这些路径相同，那么所有这些路径中的内容在任何位置就都可以使用了。例如“System32”文件夹是系统预设在Path变量中的，这个文件夹里的所有命令都可以在任意位置执行，如果在Path变量中删除“System32”文件夹的路径，那么ipconfig等系统命令就不能在任意位置运行。

**系统环境变量**  

1. 新建 JAVA_HOME C:\Program Files\Common Files\Oracle\Java\javapath  
2. Path 新建%JAVA_HOME%\bin并移动到最上面  
    -  C:\Program Files\Common Files\Oracle\Java\javapath   --javac  
      -  报错原因：没有这个环境变量  
      - C:\Program Files (x86)\Common Files\Oracle\Java\javapath  
- 完成  

- 或者将%JAVA_HOME%\bin换为E:\Java\jdk-18.0.1.1\bin

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_00-58-11.png =400x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_01-00-14.png =400x)

- 用户环境变量也可如上配置，只对一个用户有效

**检查指向的是JDK，而不是JRE**

- cmd运行：`%JAVA_HOME%\bin\javac -version`不报错则是JDK。

1. 请区别Java与JavaC命令，Java命令是运行Java程序的命令，被内置在JRE和JDK环境中，JRE安装向导默认的行为会将Java命令复制到系统System32文件夹中，所以不配置环境变量，这个命令也是可以正常执行的。JavaC命令是Java语言的编译命令，用于把“.java”文件编译为“.class”字节码文件，它是JDK独有的命令，所以经常使用它作为配置环境变量后的测试命令。  
这两个命令在视觉上目前最大的区别是Java命令返回英文提示，而JavaC命令返回中文提示。

2. cmd系统控制台窗口在启动时读取环境变量，并配置到自己的环境中。但是如果用户在启动cmd控制台窗口后，修改了环境变量，已经启动的cmd控制台窗口是无法感知的。很多读者就是在这样的情况下，屡次修改；测试，结果都以失败告终。如果修改了环境变量，一定要在新启动的cmd控制台窗口进行测试。

***
Path是系统中重要的环境变量，如果它被破坏会导致系统部分命令不可用，或致使某些软件不能运行。如果把该变量值备份到另一个环境变量中，就可以在Path环境变量遭到破坏时对其进行恢复。实现过程如下：

（1）在“计算机”图标上单击鼠标右键，选择“属性”命令，在弹出的对话框中选择“高级系统设置”选项，然后单击“环境变量”按钮，将弹出“环境变量”对话框。

（2）在“环境变量”对话框下方找到“Path”变量，双击该变量进行编辑，复制完整的变量值。

（3）新创建一个环境变量“PathBackup”，然后把复制的Path值，粘贴到新的环境变量中，这样就给Path环境变量值做了备份。

![](https://pic.imgdb.cn/item/62a9a13f09475431290fe2a5.jpg =600x)

**安装路径尽量不用中文**

## Eclipse

IDE 集成开发环境

### 安装

1. JavaEE
2. 设置透视图(perspective)
     - 进入 Eclipse 编辑界面以后,首先选择,设置透视图
     - javaEE
3. 添加透视图需要显示的结构
     - 结构 1：Package Explorer
     - 结构 2：Navigator
     - 结构 3：Outline
     - 结构 4：Console
4. 关闭其它不需要的结构
     - ![](https://pic.imgdb.cn/item/62ab448d0947543129c83e59.jpg)
5. 设置编码集 utf-8
     - window> preforence>General>wokerspace>
     - 设置字体,字形,字体大小
     - window> preforence>General>Appearence>Colords and Fonts>Text Font
6. 设置 package explorer 中右键:new 下显示的结构
    - ![](https://pic.imgdb.cn/item/62ab450a0947543129c9e59f.jpg)
7. 保存当前透视图
    - window>perspective>Save Perspective As...
    - JavaEE
    
### 插件安装

- help --> install New Software

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/278101611240276.png =248x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/571911411227441.png =581x)

- 右下角进度条

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/271841511247607.png =250x)

**WindowBulder**
 
`http://download.eclipse.org/windowbuilder/latest/`

### 卸载

eclipse是绿色安装软件，在控制面板的卸载程序中找不到卸载eclipse的地方。只需要在文件资源管理器中删除以下文件就行了：  

1. 删除你安装eclipse的文件夹。  
    - 打开 eclipse.exe 的安装目录后，在它的父目录中查找，直到看到名为“eclipse”的文件夹，点击该文件夹的父文件夹。
2. 删除用户中的".p2”、“eclipse-workspace”、“.eclipse”文件夹即可
    - .p2
    - .eclipse //安装目录
    - eclipse-workspace  //工作空间

### Eclipse启动错误修复教程
**eclipse启动失败，提示“发生了错误，请参阅日志文件.log**

### 编码问题
utf-8    

- 项目的编码
- 输出显示的编码
- 代码文件的编码

### 导入文件

1.新建项目
2.src右键 选择导入Import
3. 
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/434584411220960.png =410x)
4.
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/379394511239386.png =494x)

### 自动产生get和set方法
Source -> Generate getters and setters...

或者`Alt + Shift + S` -> Generate getters and setters...

### 创建构造器
`Alt + Shift + S` -> Generate Constructor using Fileds
***
- 选择父类中带参的

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/314882023221047.png =332x)
### 创建eqals()的重写

`Alt + Shift + S` -> Generate hashCode() And equals()

### 创建toString的重写

`Alt + Shift + S` -> Generate toString()


### 创建类

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/536772223239473.png =342x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/327712323227340.png =352x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/87102423247506.png =344x)

### 自动包装try

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/497925823247509.png =441x)

### 快捷键

```
package com.atguigu.java;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

/*
 * Eclipse中的快捷键：
 * 1.补全代码的声明：alt + /
 * 2.快速修复: ctrl + 1  
 * 3.批量导包：ctrl + shift + o
 * 4.使用单行注释：ctrl + /
 * 5.使用多行注释： ctrl + shift + /   
 * 6.取消多行注释：ctrl + shift + \
 * 7.复制指定行的代码：ctrl + alt + down 或 ctrl + alt + up
 * 8.删除指定行的代码：ctrl + d
 * 9.上下移动代码：alt + up  或 alt + down
 * 10.切换到下一行代码空位：shift + enter
 * 11.切换到上一行代码空位：ctrl + shift + enter
 * 12.如何查看源码：ctrl + 选中指定的结构   或  ctrl + shift + t
 * 13.退回到前一个编辑的页面：alt + left 
 * 14.进入到下一个编辑的页面(针对于上面那条来说的)：alt + right
 * 15.光标选中指定的类，查看继承树结构：ctrl + t
 * 16.复制代码： ctrl + c
 * 17.撤销： ctrl + z
 * 18.反撤销： ctrl + y
 * 19.剪切：ctrl + x 
 * 20.粘贴：ctrl + v
 * 21.保存： ctrl + s
 * 22.全选：ctrl + a
 * 23.格式化代码： ctrl + shift + f
 * 24.选中数行，整体往后移动：tab
 * 25.选中数行，整体往前移动：shift + tab
 * 26.在当前类中，显示类结构，并支持搜索指定的方法、属性等：ctrl + o
 * 27.批量修改指定的变量名、方法名、类名等：alt + shift + r
 * 28.选中的结构的大小写的切换：变成大写： ctrl + shift + x
 * 29.选中的结构的大小写的切换：变成小写：ctrl + shift + y
 * 30.调出生成getter/setter/构造器等结构： alt + shift + s
 * 31.显示当前选择资源(工程 or 文件)的属性：alt + enter
 * 32.快速查找：参照选中的Word快速定位到下一个 ：ctrl + k
 * 
 * 33.关闭当前窗口：ctrl + w
 * 34.关闭所有的窗口：ctrl + shift + w
 * 35.查看指定的结构使用过的地方：ctrl + alt + g
 * 36.查找与替换：ctrl + f
 * 37.最大化当前的View：ctrl + m
 * 38.直接定位到当前行的首位：home
 * 39.直接定位到当前行的末位：end
 */

public class EclipseKeys{

	public static void main(String[] args) {
		System.out.println();

		Scanner scanner = new Scanner(System.in);

		int[] arr = new int[] { 33, 44, 5, 2, 4, 53, 2 };

		int max = 0;
		int temp = 0;
		String string = new String();
		char c = string.charAt(0);

		for (int i = 0; i < arr.length; i++) {

		}

		EclipseKeys e = new EclipseKeys();
		e.method();
		e.num = 10;

		InputStream is = null;
		
		HashMap map1 = null;
		map1 = new HashMap();
		map1.put(null, null);
		
		System.out.println(map1);
		
	}

	int num = 10;

	public void method() {

	}
}
```

#### 自动提示补完

windows -> preference ->Java -> Editor->Content Assist -> Auti Activation -> Auto activation for Java ->abcdefghijklmnopqrstuvwxyz.

#### 快捷键设置

preference 搜索 keys

### Debug

如何调试
1. 设置断点 --双击行号
    - 注意：可以设置多个断点
2. debug as java application
3. 常用操作
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/18454012227258.png =659x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/328293012220965.png =430x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/520643012239391.png =432x)

#### Setdep Into 失灵

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/581875512247424.png =572x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/245315712240093.png =772x)
将JRE改为JDK即可

## Editplus安装

配置用户工具  

- path
- $Filenama
- $FileDir  

## IDEA

### Debug调试

### 导入

#### 导入Eclipse的工程

1. 查看Eclipse中存放的物理地址，并复制文件
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/499751215221070.png =376x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/112101515227363.png =474x)
2. 到IDEA中的文件位置黏贴
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/448351815247529.png =489x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/47042115240198.png =117x)
3. 将该过程设置为可用
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/228242215236753.png =272x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/37412315232507.png =498x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/358822315250387.png =249x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/65532415247991.png =394x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/488702415245493.png =476x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/435192515226734.png =565x)

#### 导入第三方jar包

1. 新建一个文件夹，通常命名为lib
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/121441116247612.png =709x)
2. 把要导入的jar包黏贴到目标文件目录
3. 使其作为API来使用
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/331611416240281.png =574x)


### 切换JDK版本

**需要修改JDK的路径：%JAVA_HOME%**

- 对于项目
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-20_17-22-03.png =500x)
- 对于模块
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-20_17-24-24.png =500x)
- 导入新的JDK
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-20_17-26-26.png =500x)

### idea jar包导出

1.
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-21_14-03-57.png =500x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-21_14-05-05.png =500x)
2.
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-21_14-05-54.png =500x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-21_14-07-25.png =500x)

## Linux安装Java


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

# 基础先导

## DOS命令

常用的DOS命令

- dir : 列出当前目录下的文件以及文件夹
- md : 创建目录
- rd : 删除目录
- cd : 进入指定目录
- cd.. : 退回到上一级目录
- cd\: 退回到根目录
- del : 删除文件
- exit : 退出 dos 命令行
- 补充：echo javase>1.doc
- 常用快捷键
  - ← →：移动光标
  - ↑ ↓：调阅历史操作命令
  - Delete和Backspace：删除字符


## 注释


### 单行注释 //

### 多行注释 /**/

### 文档注释

Javadoc注释根据写法和位置的不同，主要分为以下3类：

- 写在类/方法上面的Javadoc注释
- 写在字段上面的Javadoc
- 写在包上面的Javadoc

注意：

- 所有的Javadoc注释都以/**开头，*/结尾。
- 每种Javadoc注释都要和其后对应的类/方法/字段/包保持同样的缩进

在Java源文件编辑器中，选择某个成员方法或成员变量，然后按`<Alt>+<Shift>+<J>`快捷键，Eclipse会自动添加JavaDoc文档注释结构，如果是方法的话，还会自动添加参数名称。

```
class student { 
/** 
* 没有和下面的方法保持同样的缩进，是错误的. 
*/ 
    public int getName(int ID){
            ... 
    } 
}
```

```
/**
@author 指定java程序的作者
@version 指定源文件的版本
*/
```

***

#### 写在类/方法上面的Javadoc注释

此种注释分为三段，顺序如下：

1. 概要描述 (main description)。
     - 用一句话或者一段话简要描述该类的作用，以英文句号结束，这句话会被提取并放到索引目录中。(当识别到第一个英文句号时，系统会自动认为概要描述已经结束，紧接其后的话都不会被放到概要中。要避免这种情况，可以在英文句号后加上`&nbsp;` 即可，这样系统将判定 `&nbsp;`后的下一个英文句号为概要描述结束的标志)
2. 详细描述。
     - 用一段话或者多段话详细描述该类的作用，每段话都以英文句号结束。详细描述中可以使用html标签，如`<p> (定义段落，写在段前), <pre> (放在该标签内的内容可以保留“原始样子”), <a> (定义超链接), <li> (定义列表项目) 等标签。`
3. 文档标记，即类似小标签一样的说明。  

- 描述部分和文档标记之间必须空一行。

```
/**
* Graphics is the abstract base class for all graphics contexts
* which allow an application to draw onto components realized on 
* various devices or onto off-screen images.
* <p>
* A Graphics object encapsulates the state information needed
* for the various rendering operations that Java supports. This
* state information includes:
* <ul>
* <li>The current clip
* <li>The current color
* <li>The current font
* </ul>
* <p>
* Some important points to consider are that drawing a figure that
* covers a given rectangle will occupy one extra row of pixels on
* the right and bottom edges compared to filling a figure that is
* bounded by that same rectangle.
* <p>
* All coordinates which appear as arguments to the methods of this
* Graphics object are considered relative to the translation origin
* of this Graphics object prior to the invocation of the method.
* 
* @author      Sami Shaio
* @since       1.0
*/
public abstract class Graphics {
    /** 
    * Draws as much of the specified image as is currently available
    * with its northwest corner at the specified coordinate (x, y).
    * This method will return immediately in all cases.
    * <p>
    * If the current output representation is not yet complete then
    * the method will return false and the indicated 
    * {@link ImageObserver} object will be notified as the
    * conversion process progresses.
    *
    * @param img       the image to be drawn
    * @param x         the x-coordinate of the northwest corner
    *                  of the destination rectangle in pixels
    * @param y         the y-coordinate of the northwest corner
    *                  of the destination rectangle in pixels
    * @param observer  the image observer to be notified as more
    *                  of the image is converted.  May be 
    *                  <code>null</code>
    * @return          <code>true</code> if the image is completely 
    *                  loaded and was painted successfully; 
    *                  <code>false</code> otherwise.
    * @see             Image
    * @see             ImageObserver
    * @since           1.0
    */
    public abstract boolean drawImage(Image img, int x, int y, 
ImageObserver observer);
```

***

#### 写在包上面的Javadoc

格式和写在类、方法上面的javadoc的格式一样。内容方面主要是介绍这个包是干嘛的，包的结构，在使用方面要注意什么等信息。
包注释是写在package-info.java这个文件里的，该文件在创建包时会顺带生成。

```
/**
 * Provides the classes necessary to create an applet and the classes an applet uses 
 * to communicate with its applet context. 
 * <p>
 * The applet framework involves two entities: 
 * the applet and the applet context. An applet is an embeddable window (see the 
 * {@link java.awt.Panel} class) with a few extra methods that the applet context 
 * can use to initialize, start, and stop the applet.
 *
 * @since 1.0
 * @see java.awt
 */
package java.lang.applet;
```

***

#### 写在字段上面的Javadoc

只有public的字段才需要注释，通常是static的

```
/**
* the static field a
*/
public static int a = 1;
```

#### javadoc

注释内容可以被JDK提供的工具 javadoc 所解析，生成一套以网页文件形式体现的该程序的说明文档。  
只提取文档注释
文档注释只放在类、接口、成员变量、方法之前，因为 Javadoc 只处理这些地方的文档注释，而忽略其它地方的文档注释。  
API 帮助文档相当于产品说明书，而说明书只需要介绍那些供用户使用的部分，所以 Javadoc 默认只提取 public、protected 修饰的部分。如果要提取 private 修饰的部分，需要使用 -private。
Javadoc 工具可以识别文档注释中的一些特殊标签，这些标签一般以@开头，后跟一个指定的名字，有的也以{@开头，以}结束。Javadoc 可以识别的标签如下表所示：  
![](https://pic.imgdb.cn/item/62ab4e500947543129ea40a2.jpg)
对两种标签格式的说明：

- @tag 格式的标签（不被{ }包围的标签）为块标签，只能在主要描述（类注释中对该类的详细说明为主要描述）后面的标签部分（如果块标签放在主要描述的前面，则生成 API 帮助文档时会检测不到主要描述）。
- {@tag} 格式的标签（由{ }包围的标签）为内联标签，可以放在主要描述中的任何位置或块标签的注释中。

Javadoc 标签注意事项：

- Javadoc 标签必须从一行的开头开始，否则将被视为普通文本。
- 一般具有相同名称的标签放在一起。
- Javadoc 标签区分大小写，代码中对于大小写错误的标签不会发生编译错误，但是在生成 API 帮助文档时会检测不到该注释内容。

cmd（命令提示符）中输入`javadoc -help`就可以看到 Javadoc 的用法和选项（前提是安装配置了JDK）  

| 名称                 | 说明                                    |
| :------------------ | :------------------------------------- |
| -public             | 仅显示 public 类和成员                   |
| -protected          | 显示 protected/public 类和成员（默认值） |
| -package            | 显示 package/protected/public 类和成员  |
| -private            | 显示所有类和成员                         |
| -d <directory>      | 输出文件的目标目录                       |
| -version           | 包含 @version 段                        |
| -author             | 包含 @author 段                         |
| -splitindex         | 将索引分为每个字母对应一个文件            |
| -windowtitle <text> | 文档的浏览器窗口标题                     |

```
/**
 @author scott
 @version v1.0123
*/
public class Hello{
        /**
         如下的方式是main()
         @author system
         @version v2.100
         */
        public static void main(String[] args){
                //单行注释
                System.out.println("Hello Java !");
        }
}

cmd>javadoc -author -version Hello.java 
cmd>javadoc [-d 文件夹名] -author -version 文件名.java 

文件夹中 index.html
```

Test.java

```
/**
* @author C语言中文网
* @version jdk1.8.0
*/
public class Test{
    /**
     * 求输入两个参数范围以内整数的和
     * @param n 接收的第一个参数，范围起点
     * @param m 接收的第二个参数，范围终点
     * @return 两个参数范围以内整数的和
     */
    public int add(int n, int m) {
        int sum = 0;
        for (int i = n; i <= m; i++) {
            sum = sum + i;
        }
        return sum;
    }
} 

cmd>javadoc -author -version Test.java
当前文件夹中的Test.html
```

***

##### javadoc 不是内部或外部命令

环境变量的问题

1. 检查jdk/bin目录 是否是有javadocw.exe
    - 在该目录下执行 javadoc 可以
2. 在用户变量里面也需要添加路径
    - 环境变量->_用户变量_（是用户变量不是系统变量）->Path 然后点击编辑，新建%JAVA_HOME%\bin的文件路径并上移到最上，点击确定

***

##### 注释中有汉字可能会乱码

使用

```
javadoc -encoding UTF-8 -charset UTF-8 Test.java
```

***

## 注意

1. Java源文件以“java”为扩展名。源文件的基本组成部分是类（class），如本例中的HelloWorld类。
2. Java应用程序的执行入口是main()方法。它有固定的书写格式：
3. public主类不可以少
   - 错误: 找不到或无法加载主类 Test
   - 原因: java.lang.ClassNotFoundException: Test

```
public static void main(String[] args) {...}
```

3. Java语言严格区分大小写。
4. Java方法由一条条语句构成，每个语句以“;”结束。
5. 大括号都是成对出现的，缺一不可。
6. 一个源文件中最多只能有一个public类。其它类的个数不限，如果源文件包含一个public类，则文件名必须按该类名命名。

## javac编译错误: 编码UTF8/GBK的不可映射字符

在win命令行下编译java文件时，出现了“编码GBK的不可映射字符”错误，
原因是源代码中出现了中文字符。

1. 解决方法是添加encoding 参数
2. 此问题是java文件不是UTF-8格式的，另存为UTF-8格式即可

```
javac -encoding utf-8 main.java
```

## 初步使用

Java源代码文件的名称必须和定义的类或接口名称一直，然后添加“.java”后缀

### Hello.java

文件名 Hello.java  
//声明为public的类应与文件名一致

```
public class Hello{
        public static void main(String[] args){ 
                System.out.println("Hello Java");  //输出语句
        }
}

cmd> cd 到存放此java文件的路径下
cmd> javac Hello.java //把“.java”文件编译为“.class”字节码文件
cmd> java Hello  //执行编译后的“Hello.class”字节码文件
```

1. 将 Java 代码编写到扩展名为 .java 的文件中。
2. 通过 javac 命令对该 java 文件进行编译。
3. 通过 java 命令对生成的 class 文件进行运行。

![](https://pic.imgdb.cn/item/62a9a19309475431291066a4.jpg =500x)

因为一个程序的执行需要一个起始点或者入口，所以在Test类中的加入

```
public static void main(String[] args){ }
```

### 输出星号字符组成的三角形.exe

Triangle.java

```
public class Hello{
        public static void main(String[] args){
                System.out.println("   *   ");
                System.out.println("  ***  ");
                System.out.println(" ***** ");
                System.out.println("*******");
        }
}
```

### 计算两个整数的和.exe

SumNum.java

```
public class SumNum{
        public static void main(String[] args){
                int a = 1,b = 2;
                int c = a + b;
                System.out.println(c);
        }
}
```

### 输出当前日期

PrintDate.java

```
import java.util.Date;
public class PrintDate{
        public static void main(String[] args){
                Date date = new Date();
                String str = date.toLocaleString();
                System.out.println("now_date:"+str);
        }
}

注: PrintDate.java使用或覆盖了已过时的 API。
注: 有关详细信息, 请使用 -Xlint:deprecation 重新编译。
```

### 在控制台接收用户输入数字.exe

Receiavinput.java

```
import java.util.Scanner;
public class ReceiveInput{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);
                System.out.println("input your name:");
                String name  = scan.nextLine();
                System.out.println("input your age:");
                int age = scan.nextInt();
                System.out.println("input your height");
                double stature = scan.nextDouble();
                System.out.println("over");
                System.out.println("your age:" + age + "\n" + "your name:" + name + "\n" + "your height:" + stature);
        }
}
```

创建ReceiveInput类，在该类的主方法中创建Scanner扫描器来封装System类的in输入流，然后提示用户输入姓名、年龄和身高信息，并输出由这些信息组成的说明信息。  
程序中用到了System类的输入流也就是类变量in，它可以接收用户的输入信息，并且是标准的输入流实例对象。另外Scanner类是Java的扫描器类，它可以从输入流中读取指定类型的数据或字符串。本实例使用Scanner类封装了输入流对象，并使用nextLine()、nextInt()、nextDouble()方法从输入流中获取用户输入的整行文本字符串、整数数据和double类型数据。

说明：在使用Scanner类时，必须在源代码顶端导入这个类，导入语句为“import java.util.Scanner;”

### 计算用户输入数字的乘积.exe

AverNum.java

```
import java.util.Scanner;
public class AverNum{
 public static void main(String[] args){
  Scanner scan = new Scanner(System.in);
  System.out.println("input n1:");
  double n1 = scan.nextDouble();
  System.out.println("input n2:");
  double n2  =scan.nextDouble();
  System.out.println("-----------------");
  System.out.println(n1 + "*" + n2 + "=" + n1 * n2);
 }
} 
```

### 爱心

PrintHeart1.java

```
class PrintHeart1 {
 public static void main(String[] args) {
  System.out.print("\t" + "*" + "\t\t\t\t\t\t\t\t\t\t\t\t" + "*" + "\t" + "\n");
  System.out.print("*" + "\t\t" + "*" + "\t\t\t\t" + "I love Java" + "\t\t\t\t\t" + "*" + "\t\t" + "*" + "\n");
  System.out.print("\t" + "*" + "\t\t\t\t\t\t\t\t\t\t\t\t" + "*" + "\t" + "\n");
  System.out.print("\t\t" + "*" + "\t\t\t\t\t\t\t\t\t\t" + "*" + "\t\t" + "\n");
  System.out.print("\t\t\t" + "*" + "\t\t\t\t\t\t\t\t" + "*" + "\t" + "\n");
  System.out.print("\t\t\t\t" + "*" + "\t\t\t\t\t\t" + "*" + "" + "\t" + "\n");
  System.out.print("\t\t\t\t\t" + "*" + "\t\t\t\t" + "*" + "" + "\t\t" + "\n");
  System.out.print("\t\t\t\t\t\t" + "*" + "\t\t" + "*" + "" + "\t\t" + "\n");
  System.out.print("\t\t\t\t\t\t\t" + "*" + "\n");
 }
}
```

### 输出基本信息(姓名后空一行,再输出性别/年龄)

```
姓名:   小米
性别:   男
年龄:   18  
```

```
public class PrintInfo{
        public static void main(String[] args){
                System.out.println("姓名:" + "\t" + "小米");
                System.out.println();
                System.out.println("性别:" + "\t" + "男");
                System.out.println("年龄:" + "\t" + 18);
        }
}
```

# Java介绍

![](https://pic.imgdb.cn/item/62a99d30094754312908accb.jpg =800x)

## Java平台分类

**JavaSE**

JavaSE就是Java的标准版，主要用于桌面应用程序的开发，同时也是Java的基础，它包含Java语言基础、JDBC数据库操作、I/O输入输出、网络通讯、多线程等技术。

**JavaEE**

JavaEE是Java2的企业版，主要用于开发企业级分布式的网络程序，如电子商务网站和ERP系统，其核心为EJB。

**J2ME**

J2ME主要应用于嵌入式系统开发，如掌上电脑、手机等移动通讯电子设备。

### JDK(Java Development Kit Java开发工具包)

JDK是提供给Java开发人员使用的，其中包含了java的开发工具，也包括了JRE。所以安装了JDK，就不用在单独安装JRE了。其中的开发工具：编译工具(javac.exe) 打包工具(jar.exe)等  

### JRE(Java Runtime Environment Java运行环境)

包括Java虚拟机(JVM Java Virtual Machine)和Java程序所需的核心类库等，如果想要运行一个开发好的Java程序，计算机中只需要安装JRE即可。

#### JDK,JRE和JVM的关系

JDK包含JRE，JRE包含JVM.

- JDK = JRE + 开发工具集（例如Javac编译工具等）
- JRE = JVM + Java SE标准类库
![](https://pic.imgdb.cn/item/62a9a0e609475431290f52bb.jpg =300x)

### Java API的文档

Java API也可以说是JDK文档，它的全称是Application Programming Interface应用程序编程接口，简称API，是Java程序开发不可缺少的编程词典，它记录了Java语言中海量的API，主要包括类的继承结构、成员变量和成员方法、构造方法、静态成员的详细说明和描述信息。  
Java语言提供了大量的基础类，因此 Oracle 也为这些基础类提供了相应的API文档，用于告诉开发者如何使用这些类，以及这些类里包含的方法。  
下载API：
<http://www.oracle.com/technetwork/java/javase/downloads/index.html>

Additional Resources-Java SE 8 Documentation下载。

## Java

### Java语言

- Java是一种简单的、面向对象的、分布式的、强壮的、安全的、体系结构中立的、高性能的、多线程的和动态的语言。
- **Java是单继承的语言**，尽管接口可以实现多继承。
- 在Java文件.class中最多只能存在一个public类

### 编译和执行

1. Java程序.java由编译器进行编译，产生Java字节码.class
2. JVM对Java字节码进行解释运行。

# Java基本语法

## Java主类结构

Java语言是面向对象的程序设计语言，Java程序的基本组成单元就是类，类体中又可包括属性与方法两部分。每一个Java本地应用程序都必须包含一个main()方法，main()方法为Java应用程序的入口，包含main()方法的类称之为主类。一个完整的主类结构通常由定义包语句、定义类语句、定义主方法语句、定义变量语句和导入API类库5部分组成，

### 定义包

开发应用程序时，通常将Java类放在指定的包中，每个包内通常放置同类的Java类，这样可以使应用程序的结构更加清晰，便于以后维护。在Java中，可以使用package关键字指定Java类所在包
Java包，在计算机硬盘上的表现形式为文件夹，例如，将First.java类放置在com包中，那么该Java文件将被放置在com文件夹中。
定义类包为structure的代码如下：  

```
package structure;      
```

### 定义类

一个Java应用程序是由若干个类组成的。通过class关键字来定义类，
例如，要定义一个名称为FirstClass的公共类，可以使用以下代码：

```
public class FirstClass {    

}
```

### 定义主方法

main()方法是类体中的主方法。该方法从“{”号开始，至“}”号结束，中间的是方法体。
public、static、void分别是main方法的权限修饰符、静态修饰符和返回值类型声明，
在Java程序中的main()方法，必须声明为public static void。
String arg[]是一个字符串类型的数组，它是main()方法的参数。
main()方法是程序开始执行的位置。
main()方法是程序入口方法。它有固定的格式，方法的参数也固定为一个字符串数组。

### 定义变量

通常将类的属性称之为类的全局变量（或成员变量）。将方法中的属性称之为局部变量。全局变量声明在类体当中，局部变量声明在方法体当中。全局变量和局部变量都有各自的应用范围。

### 导入API类库

Java类必须执行导入才能够被当前类使用。在Java语言中可以通过“import”关键字导入相关的类。
例如，要导入java.util.Date类，可以使用以下代码：

```
import java.util.Date;
```

### 例1

```
package structure;                                                         //定义类包

public class FirstClass {                                                 //类声明
      private static String className = "FirstClass";           //定义成员变量
      /**
       * @param args
       */
      public static void main(String[] args) {                      //主方法
                  String info = "，我的第一个Java类";                   //定义局部变量
            System.out.println(className+info);                    //输出变量className+info
      }
}
```


## 关键字和保留字

### 关键字

### 保留字

- goto
- const

## 标识符

### 命名规则

- 26个英文字母大小写，0-9，_，$
- 数字不可以开头
- 不能使用关键字和保留字
- 严格区分大小写
- 不能包含空格

### 命名规范

- 包名：多单词时都小写 xxxyyy
- 类名/接口名：多单词时每个单词首字母大写 XxxYyy
- 变量名/方法名：多单词时第一个单词首字母小写，第二个单词开始每个单词首字母大写xxxYyy
- 常量名：所有字母都大写，多单词用下划线连接 XXX_YYY

### java使用unicode字符集可以用中文，尽量别，奇怪的bug

***

## 变量

### 变量概念

- 内存中的一个存储区域
- 该区域的数据可以在同一类型范围内不断变化
- 变量是程序中最基本的存储单元。包含变量类型，变量名和存储的值。  

定义变量？  
简单的说，就是要告诉编译器（compiler）这个变量是属于哪一种数据类型，这样编译器才知道需要配置多少空间给它，以及它能存放什么样的数据。在程序运行过程中，空间内的值是变化的，所以称为变量。为了便于操作，给这个空间取个名字，称为变量名，内存空间内的值就是变量值。

变量的作用

- 用于在内存中保存数据

使用变量注意：

- Java每个变量必须先声明，后使用
- 使用变量名来访问这块区域的数据
- 变量的作用域：其定义所在的一对{}内,即函数体内
- 变量只有在其作用域内才有效
- 同一个作用域内，不能定义重名的变量

#### 系统的内存可大略的分为3个区域

- 系统区（OS）、
- 程序区（Program）、
- 数据区（Data）。

当程序执行时，程序代码会加载到内存中的程序区，数据暂时存储在数据区中。假设上述两个变量定义在方法体中，则程序加载到程序区中。当执行此行程序代码时，会在数据区配置空间给这两个变量

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/567682923220659.png)

***
格式：

```
数据类型 变量名 = 变量值
```

```
public class VariableTest{
        public static void main(String[] args){
                int id; //变量的声明
                int age = 12;  //变量的定义
                System.out.println(age);   //变量的使用
                id = 001;
                System.out.println(id);
        }
}
```

***

### 基本数据类型

#### 按数据类型分

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/544083715232095.png =636x)

#### 按声明位置不同

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/162523815249975.png =478x)
***

#### 整数类型：byte、short、int、long

- Java各整数类型有固定的表数范围和字段长度，不受具体OS的影响，以保
证java程序的可移植性。
- java的整型常量默认为 int 型，声明long型常量须后加‘l’或‘L’
- java程序中变量通常声明为int型，除非不足以表示较大的数，才使用long

500MB 1MB = 1024KB 1KB= 1024B B= byte ? bit?

- bit: 计算机中的最小存储单位。
- byte:计算机中基本存储单元。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/64484315247579.png =480x)

```
public class Test{
        public static void main(String[] args){
                byte b1 = -128;
                //byte b2 = 128; 超出范围-128~127 编译错误
                System.out.println(b1);

                short s1 = 128;
                System.out.println(s1);
                
                int i1 = 1234;
                System.out.println(i1);
                
                long l1 = 12334578L; //声明long型常量须后加‘l’或‘L’，否则默认int
                System.out.println(l1);
                //long l2 = 123456789010;  //认为是int自动转long，大于2^31，超过int的范围，编译失败:过大的整数
        }
}
```

***

#### 浮点类型：float、double

与整数类型类似，Java 浮点类型也有固定的表数范围和字段长度，不受具体操作系统的影响。
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/338745915245081.png =482x)

Java 的浮点型常量默认为double型，声明float型常量，须后加‘f’或‘F’。
double 可以加d/D，也可以不加

浮点型常量有两种表示形式：

- 十进制数形式：如：
  - 5.12
  - 512.0f
  - .512 (必须有小数点）
- 科学计数法形式：如：
  - 5.12e2
  - 512E2
  - 100E-2

##### float:单精度，尾数可以精确到7位有效数字。很多情况下，精度很难满足需求

##### double:双精度，精度是float的两倍。通常采用此类型

```
public class Test{
        public static void main(String[] args){
                double d1 = 123.3;
                System.out.println(d1);
                
                float f1 = 12.3F;   //声明float型常量，须后加‘f’或‘F’。否则默认double
                System.out.println(f1);
                //float f2 = 12.3  //编译失败;被认为是自动类型转换，但不能自动大转小
        }
}
```

***

#### float与long的注意

- 整型常数，默认为int型
- 浮动型常数，默认为double型

因此

```
long l2 = 123456789010;  
//认为是int自动转long，大于2^31，超过int的范围，编译失败:过大的整数

float f2 = 12.3 
 //编译失败;被认为是自动类型转换，但不能自动大转小
```

***

#### 字符类型：char

char 型数据用来表示通常意义上“字符”(2字节)
Java中的所有字符都使用Unicode编码，故一个字符可以存储一个字母，一个汉字，或其他书面语的一个字符。  

字符型变量的三种表现形式：

- 字符常量是用单引号(‘ ’)括起来的单个字符。只能写一个字符,且不能为空(可以是空格):
  - char c1 = 'a';
  - char c2 = '中';
  - char c3 = '9';
- 也可以不使用单引号 ' ' 即ASCII, //非常少见  

```
public class Test{
    public static void main(String[] args){
        char c1 = 'a'; 
        char c2 = 97;  //ASCII //'a'
        System.out.println(c2);  //a
        
        char c3 = 5; //ASCII //口
        char c4 = '5'; // 5
        System.out.println(c3);   //口
    }
}
```

- Java中还允许使用转义字符‘\’来将其后的字符转变为特殊字符型常量。例如：
  - char c3 = ‘\n’; // '\n'表示换行符
  - ![](C:/Users/zjk10/OneDrive/NoteBook/pictures/94890716226322.png =149x)
- 直接使用 Unicode 值来表示字符型常量：‘\uXXXX’。其中，XXXX代表一个十六进制整数。
  - 如：\u000a 表示 \n。

char类型是可以进行运算的。因为它都对应有Unicode码。

```
public class Test{
        public static void main(String[] args){
                char c1 = 'a';
                System.out.println(c1);
                char c2 = '中';
                System.out.println(c2);
                char c3 = '9'
                System.out.println(c3);

                char c4 = '一'; //可以是日语,韩文等
                System.out.println(c4);

                char c5 = '\n';
                System.out.print("NNN" + c5);
                System.out.print("YYY");

                char c6 = '\u0043';
                System.out.println(c6);
        }
}
```

***

#### 布尔类型：boolean

boolean 类型用来判断逻辑条件，一般用于程序流程控制：

- if条件控制语句；
- while循环控制语句；
- do-while循环控制语句；
- for循环控制语句；

boolean类型数据只允许取值true和false，无null。

- 不可以使用0或非 0 的整数替代false和true，这点和C语言不同。
- Java虚拟机中没有任何供boolean值专用的字节码指令，Java语言表达所操作的boolean值，在编译之后都使用java虚拟机中的int数据类型来代替：true用1表示，false用0表示。———《java虚拟机规范 8版》

```
public class Test{
        public static void main(String[] args){
                boolean bb1 = true;
                System.out.println(bb1);

                boolean isMarried = true;
                if(isMarried){
                        System.out.println("你就有个小可爱了！");
                }
                else{
                        System.out.println("你可真可怜!");
                }
        }
}
```

***

### 基本数据类型变量间转换

#### 自动类型提升

容量(数的范围)小的类型自动转换为容量大的数据类型。
数据类型按容量大小排序为：  
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/153093916248762.png =701x)

有多种类型的数据混合运算时，系统首先自动将所有数据转换成容量最大的那种数据类型，然后再进行计算。

- byte,short,char之间不会相互转换，他们三者在计算时首先转换为int类型。
  - 不兼容的错误，可能损坏数据，编译不予通过
- boolean类型不能与其它数据类型运算。
- 当把任何基本数据类型的值和字符串(String)进行连接运算时(+)，基本数据类型的值将自动转化为字符串(String)类型。

```
public class Test{
        public static void main(String[] args){
                //byte
                byte b1 = 12;
                int i1 = 123;
                //byte b2 = b1 + i1; 超过范围
                int i2 = b1 + i1;
                System.out.println(i2);

                float f1 = b1 + i1;
                System.out.println(f1);
                
                //char
                char c1 = 'a';  //97 ASCII
                int i3 = 10;
                int i4 = c1 + i3;
                System.out.println(i4);  //107
                
                //三者之间byte/short/char 不兼容的错误，可能损坏数据，编译不予通过
                /*
                byte b01 = 1;
                char c01 = 'a';
                short s01 = 1;
                byte b02 = b01 + c01;
                */
        }
}
```

##### 方式时机

###### 1)变量赋值

当为变量赋值的数据类型与变量类型不一致，并且赋值的数据类型级别低于变量类型的级别时，自动数据类型转换会将赋值数据自动转换为变量的类型。

```
byte byteNum=23;                           // 声明byte类型变量
int intNum=byteNum;                      // 把byte型数据赋值给int型变量
```

###### 2)方法调用

```
public static void say(int num){
   System.out.println("这是int类型数值："+num);
}
public static void main(String[] args) {
   byte byteNum=23;
   say(byteNum);        // 把byte数值传递给方法的int参数
}
```

###### 3) 一元运算

在一元运算中（除了++和—运算符），如果操作数的类型低于int类型（即byte、short和char类型），则操作数会自动转换为int类型。

- i++ 和 ++i的区别
1.赋值

```
i++ 先赋值在运算,例如 a=i++,先赋值a=i,后运算i=i+1,所以结果是a==1
++i 先运算在赋值,例如 a=++i,先运算i=i+1,后赋值a=i,所以结果是a==2
```

2.运算

```
public class Test009 {
	public static void main(String args[]) {
		int i = 1;
		System.out.println("   " + i++);
		i = 1;
		int num = i++ * 3;
		System.out.println(num); // 3
	}
}
```
###### 4)二元运算符

在二元运算中，所有低级的数据类型都会转换为运算中级别最高的数据类型。也就是说，如果有一个操作数是double类型，其他操作数都会自动转换为double类型。如果运算中最高的数据类型是float，其他操作数都会转换为float类型。如果最高的数据类型为long，那么所有操作数都会自动转换为long类型。如果最高的数据类型为int那么所有操作数都会自动转换成int类型。至于byte、short、char类型会自动转换为int或运算中最高的数据类型。

##### 在项目中创建类Conversion，在主方法中创建不同数值型的变量，实现将各变量的自动类型转换

```
public class Conversion{
        public static void main(String[] args){
                byte b1 = 34;
                short s1 = 123;
                char c1 = 'B';
                int i1 = 40;
                long l1 = 900L;
                float f1 = 8.11f;
                double d1 = 3.1415926;
                System.out.println("double/byte+char->double\t"+(d1/b1+c1));
                System.out.println("lobng+short->long\t\t"+(11+s1));
                System.out.println("char+byte+short->int\t\t"+(c1+b1+s1));
                System.out.println("float+b1/s1->float\t\t"+(f1+b1/s1));
                System.out.println("int+double->double\t\t"+(i1+d1));
        }
}
```

#### 强制类型转换

自动类型转换的逆过程

```
(数据类型)变量
强转符()   
```

但可能造成精度降低或溢出
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/228581200247119.png =843x)

```
public class Test{
        public static void main(String[] args){
                double d1 = 12.3;
                int i1 = (int)d1;
                System.out.println(i1);
                
                //转为byte较特殊 超过精度范围的:
                int i2 = 128;
                byte b1 = (byte)i2;   //-128
                System.out.println(b1);
                int i3 = 200;
                byte b2 = (byte)i3;
                System.out.println(b2);  //-56
                int i4 = 2000;
                byte b3 = (byte)i4;
                System.out.println(b3);  //-48
        }
}
```

***

#### boolean类型不可以转换为其它的数据类型

***

### 基本数据类型与String间转换

#### 字符串类型：String

String不是基本数据类型，属于引用数据类型

- 使用方式与基本数据类型一致。例如：
  - String str = “abcd”;
- 一个字符串可以串接另一个字符串，也可以直接串接其他类型的数据。例如：
  - str = str + “xyz” ;
  - int n = 100;
  - str = str + n;

```
public class StringTest{
        public static void main(String[] args){
                String s1 = "Hellp World !";
                System.out.println(s1);
                String s2 = "a";
                String s3 = "";  //string可以为空

                //char c = '';  //char型不能为空
        }
}
```

- String 可以和其他8种基本数据类型做运算，且运算只能是连接运算 +

```
public class StringTest{
        public static void main(String[] args){
                String idstr = "编号：";
                int id = 19;
                String info = idstr + id;
                System.out.println(info);

                //Stirng test01 = idstr - id;    //编译不通过
                //String test02 = idstr - info;   //编译不通过
        }
}
```

#### + 加号与连接符的判断

- char型与int/float等从左到右更近，则+为加法
- char型与String从左到右更近，则+为连接符
  - 连锁反应：于String相连接后变String

```
public class StringTest{
        public static void main(String[] args){
                char c = 'a';
                int num = 10;
                String str = "Hello";

                System.out.println(c + num + str); //107Hello
                System.out.println(c + str + num); //aHello10
                System.out.println(c + (num + str)); //a10Hello
                System.out.println((c + num) + str); //107Hello
                System.out.println(str + num + c); //Hello10a
        }
}
```

```
public class StringTest{
        public static void main(String[] args){
                System.out.println("*   *"); //*       *
                System.out.println('*' + '\t' + '*');  //93
                System.out.println('*' + '\t' + "*");  //51*
                System.out.println('*' + "\t" + '*');   //*     *
                System.out.println('*' +( '\t' + "*")); //*     *
        }
}
```

```
String str1 = 4; //判断对错：no
String str2 = 3.5f + ""; //判断str2对错：yes 
System.out.println(str2); //输出："3.5"//3.5
System.out .println(3+4+"Hello!"); //输出：7Hello!
System.out.println("Hello!"+3+4); //输出：Hello!34
System.out.println('a'+1+"Hello!"); //输出：98Hello!
System.out.println("Hello"+'a'+1); //输出：Helloa1

判断是否能通过编译
1）
      short s = 5;
      s = s - 2; //判断：no  //2 默认int 自动转换不能大转小
2） 
      byte b = 3;
      b = b + 4; //判断：no  //4 默认int
      b = (byte)(b + 4); //判断：yes
3）
      char c = ‘a’;
      int i = 5;
      float d = .314F;
      double result = c + i + d; //判断：yes
4） 
      byte b = 5;
      short s = 3;
      short t = s + b; //判断：no  // short/byte/char之间不能转换
```

#### String字符串不能直接转换为基本类型，但通过基本类型对应的包装类则可以实现把字符串转换成基本类型

如：

```
String a = "43"; 
int i = Integer.parseInt(a);
```

***

### 进制与进制间转换

#### 进制

所有数字在计算机底层都以二进制形式存在。  
对于整数，有四种表示方式：  

- 二进制(binary)：0,1 ，满2进1.
  - 以0b或0B开头。
- 十进制(decimal)：0-9 ，满10进1。
- 八进制(octal)：0-7 ，满8进1.
  - 以数字0开头表示。
- 十六进制(hex)：0-9及A-F，满16进1.
  - 以0x或0X开头表示。此处的A-F不区分大小写。
  - 如：0x21AF +1= 0X21B0

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/23553120220658.png =554x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/291703120239084.png =540x)

```
 public class BinaryTest{
        public static void main(String[] args){
                int num1 = 0b110;
                int num2 = 110;
                int num3 = 0127;
                int num4 = 0x110A;
                
                System.out.println(num1); //6  //1 * 2^2 + 1 * 2^1 + 1 * 2^0
                System.out.println(num2); //110
                System.out.println(num3); //87 //1 * 8^2 + 2 * 8^1 + 7 * 8^0
                System.out.println(num3); //4362 //1 * 16^3 + 1 * 16^2 +  0 * 16^1 + 10 * 16^0 
        }
}
```

***

#### 二进制

Java整数常量默认是int类型，

- 当用二进制定义整数时,int，其第32位是符号位；  
- 当是long类型时，二进制默认占64位，第64位是符号位

二进制的整数有如下三种形式：

- 原码：直接将一个数值换成二进制数。最高位是符号位
- 负数的反码：是对原码按位取反，只是最高位（符号位）确定为1。
- 负数的补码：其反码加1。

计算机以二进制补码的形式保存所有的整数。

- 正数的原码、反码、补码都相同
- 负数的补码是其反码+1

为什么要使用原码、反码、补码表示形式呢？  
计算机辨别“符号位”显然会让计算机的基础电路设计变得十分复杂! 于是人们想出了将符号位也参与运算的方法. 我们知道, 根据运算法则减去一个正数等于加上一个负数, 即: 1-1 = 1 + (-1) = 0 , 所以机器可以只有加法而没有减法, 这样计算机运算的设计就更简单了。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/520842515220659.png =690x)

#### 二级制转十进制

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/227482615239085.png =767x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/416642615226952.png =760x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/538902615247118.png =762x)

#### 十进制转二进制

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/85662715239787.png =767x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/238122715236342.png =768x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/366432715232096.png =764x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/573932715249976.png =769x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/250802815245082.png =766x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/134702815247580.png =764x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/540332815226323.png =766x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/84532915248763.png =767x)

#### 进制间转化

- 十进制 二进制互转
 - 二进制转成十进制 乘以2的幂数
 - 十进制转成二进制 除以2取余数
- 二进制 八进制互转
- 二进制 十六进制互转
- 十进制 八进制互转
- 十进制 十六进制互转

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/153723815243899.png =532x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/468893815237445.png =766x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/4453915230579.png =573x)

***

### 声明常量 final

在程序运行过程中一直不会改变的量称为常量（constant），通常也被称为“final变量”。常量在整个程序中只能被赋值一次。在为所有对象共享值时，常量是非常有用的。

在Java语言中声明一个常量，除了要指定数据类型外，还需要通过final关键字进行限定。声明常量的标准语法为：

```
final 数据类型 常量名称[=值]
```

常量名通常使用大写字母，这也符合Java编码规范，但这并不是必须的，声明常量时，完全可以和变量名一样使用小写字母。但是，那样容易造成混淆，降低代码可读性，所以才有规范要求常量名全部大写，并使用“_”下划线字符分割多个单词

- 当定义的final变量属于“成员变量”，则必须在定义的时候就设定它的初值。否则将会有编译错误。

```
public class Part{
        static final double PI = 3.14;
        static int age = 23;
        public static void main(String[] args){
                final int number;
                number = 1235;
                age = 22;
                //number = 1111;    //number final变量只能赋值一次
                System.out.println(PI);   //3.14
                System.out.println(number);  //1235
                System.out.println(age);   //22
        }
}
```

***

### 变量的有效范围

由于变量被定义出来后，只是暂存在内存中，等到程序执行到某一个点后，该变量会被释放掉，也就是说变量有它的生命周期。因此变量的有效范围是指程序代码能够访问该变量的区域，若超出该区域访问变量则编译时会出现错误。在程序中，一般会根据变量的有效范围，将变量分为“成员变量”和“局部变量”。

#### 成员变量

在类体中所定义的变量被称为成员变量。成员变量在整个类中都有效，所以成员变量也称作全局变量。

成员变量的有效范围是整个类的代码段，也就是在类体中的任何位置都可以使用该变量。

声明成员变量，并在不同方法中为成员变量赋值

```
public class Temp{
        int num = 0;
        float price = 3.50f;
        public void method01(){
                num = 1;
                price = 12.55f;
        }
        public void method02(){
                num = num * 10;
                price = price * 0.5f;
        }
}
```

- 类的成员变量又可分为静态变量和实例变量两种。

##### 静态变量 static

- 静态变量的有效范围是整个类，并且可以被类的所有实例共享。可通过“类名.静态变量名”的方式来访问静态变量。
- 静态变量的生命周期取决于类的生命周期，当类被加载时，为类中的静态变量分配内存空间，当卸载类时，释放静态变量占用的空间，静态变量被销毁。
- 类加载时，就为静态变量分配内存空间，之后无论创建了多少类的实例，都不会再为静态变量分配内存空间，这些实例会使用同一个静态变量。

```
 static int y = 90;   //定义静态变量
```

##### 实例变量

- 实例变量与类的实例对应，它的有效范围是整个实例。
- 每创建一个类的实例，都会为当前实例分配实例变量的内存空间。
- 所以实例变量的生命周期取决于实例的生命周期，实例被创建时，为实例变量分配内存空间，当销毁实例时，释放实例变量占用的内存空间。

```
int x = 45;          //定义实例变量
```

#### 局部变量

- 在方法体中定义的变量就是一种局部变量，局部变量只在当前代码块（即花括号之内）中有效。
- 局部变量的生命周期取决于声明位置的代码块，超出这个代码块的范围就不能在使用代码块内的局部变量。

就拿方法类说，方法内声明的变量、包括参数变量都属于局部变量，当方法被调用时，Java虚拟机为方法中的局部变量分配内存空间，当该方法的调用结束后，则会释放方法中局部变量占用的内存空间，局部变量也将会销毁，然后Java的垃圾回收机制会在某一时刻清理该内存空间。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/596674923239085.png)

##### 局部变量可与全局变量的名字相同，此时全局变量将被隐藏，但是可以使用“this.”做前缀来访问全局变量

```
public class ValClass{
        static String words = "成员变量";
        public static void main(String[] args){
                String words = "局部变量";
                System.out.println("words变量现在是:" + words); //局部变量
        }
}
```

***

## 运算符

### 算术运算符

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/432854115221109.png =611x)

#### 算术运算符的注意问题

##### 如果对负数取模，可以把模数负号忽略不记

- 如：5%-2=1。
- 但被模数是负数则不可忽略。
- 此外，取模运算的结果不一定总是整数。

##### 对于除号“/”，它的整数除和小数除是有区别的：整数之间做除法时，只保留整数部分而舍弃小数部分

- 例如：
- int x = 3510;
- x=x / 1000 * 1000;  
- x的结果是3

##### “+”除字符串相加功能外，还能把非字符串转换成字符串.例如

- System.out.println(“5+5=”+ 5 + 5); //打印结果是 5+5=55  //字符串

##### 自增1/自减1不会改变本身变量的数据类型

```
short s1

s1 = s1 +1 //编译失败
应该是
s1 = (short)(s1 +1);

整数默认是int
```

##### 超过范围的++

```
byte b1 = 127;
b1++;
System.out.println(b1);  //-128  
```

##### 自增2 (num++)++ //编译失败

```
num += 2;
```

#### 练习1：除 转换

```
public class SignTest{
        public static void main(String[] args){
                int num01 = 12;
                int num02 = 5;

                int result01 = num01 / num02;
                System.out.println(result01);   //2

                int result02 = num01 / num02 * num02;
                System.out.println(result02);   //10

                double result03 = num01 / num02;
                System.out.println(result03);  //2.0

                double result04 = num01 / num02 + 0.0;
                System.out.println(result04);   //2.0

                double result05 = num01 / (num02 + 0.0);
                System.out.println(result05);   //2.4

                double result06 = (double)num01 / num02;
                System.out.println(result06);   //2.4

                double result07 = (double)(num01 / num02);
                System.out.println(result07);   //2.0
        }
}
```

#### 练习2：随意给出一个整数，打印显示它的个位数，十位数，百位数的值

```
格式如下：
数字xxx的情况如下：
个位数：
十位数：
百位数：
例如：
数字153的情况如下：
个位数：3
十位数：5
百位数：1
```

```
public class NumberPrint{
        public static void main(String[] args){
                int num = 123;
                int a = num / 100;
                int b = (num % 100 ) / 10;
                int c = num % 10;
                System.out.println("数字" + num + "的情况如下:");
                System.out.println("百位数:" + a );
                System.out.println("十位数:" + b );
                System.out.println("个位数:" + c );
        }
}
```

#### 练习3：算术运算符：自加、自减

```
public class SignTest{
        public static void main(String[] args){
                int i = 1;
                System.out.println("i\t" + i);
                System.out.println("i++\t" + (i++));
                i = 1;
                System.out.println("++i\t" + (++i));
                i = 1;
                System.out.println("i--\t" + (i--));
                i = 1;
                System.out.println("--i\t" + (--i));
        }
}

i       1
i++     1                                                                                                               ++i     2
i--     1
--i     0  
```

```
public class SignTest{
        public static void main(String[] args){
                int i1 = 10;
                int i2 = 20;
                int i = i1++;
                System.out.println("i=" + i); 
                System.out.println("i1=" + i1);
                i = ++i1;
                System.out.println("i=" + i);
                System.out.println("i1=" + i1);
                i = i2--;
                System.out.println("i=" + i);
                System.out.println("i2=" + i2);
                i = --i2;
                System.out.println("i=" + i);
                System.out.println("i2=" + i2);
        }
}

i=10
i1=11
i=12
i1=12
i=20
i2=19
i=18
i2=18;   
```

#### 为抵抗洪水，战士连续作战89小时，编程计算共多少天零多少小时？

```
public class TimeCount{
        public static void main(String[] args){
                int workTime = 89;
                int day = workTime / 24;
                int hour = workTime % 24;

                System.out.println("共" + day + "天" + hour + "小时");
        }
}
```

#### 今天是周二，100天以后是周几？

```
public class TimeCount{
        public static void main(String[] args){
                int days = 100;
                int weekday = days % 7 + 2;
                System.out.println("周" + weekday);
        }
}
```

#### 总结

```
class SuanShu{
 public static void main(String[] args){
  int a = 23;
  int b = 12;
  System.out.println(a + "+" + b + "=" + (a+b));
  int sum = a + b;
  System.out.println(a + "+" + b + "=" + sum);
  
  int sub = a - b;
  //System.out.println(a + "-" + b + "=" + a-b);
  //错误，原因是a + "-" + b + "=" + a的结果是字符串，字符串不能进行减法
  System.out.println(a + "-" + b + "=" + (a-b));
  System.out.println(a + "-" + b + "=" + sub);
  
  int mul = a * b;
  System.out.println(a + "*" + b + "=" + a*b);
  System.out.println(a + "*" + b + "=" + mul);
  
  //整数相除，只保留整数部分
  int div = a / b;
  System.out.println(a + "/" + b + "=" + a/b);
  System.out.println(a + "/" + b + "=" + div);
  
  double d = (double)a/b;//先把a的类型进行转换，转换成double类型，然后再和b相除
  System.out.println(a + "/" + b + "=" + d);
  
  int yu = a % b;
  System.out.println(a + "%" + b + "=" + yu);
  
  System.out.println("------------------特殊的取模----------------------");
  System.out.println(5%2);//1
  System.out.println(-5%-2);//-1
  System.out.println(-5%2);//-1
  System.out.println(5%-2);//1
  
  System.out.println("------------------负号----------------------");
  int num1 = 12;
  int num2 = -num1;
  System.out.println("num2=" + num2);
  
  System.out.println("------------------自增----------------------");
  int i = 0;
  System.out.println("自增之前i=" + i);
  i++;
  System.out.println("自增第一次之后i=" + i);
  ++i;
  System.out.println("自增第二次之后i=" + i);
  int j = ++i;//把i自增1，然后结果赋值给j，或者说，先算++i，然后再赋值
  System.out.println("自增第三次之后i=" + i);
  System.out.println("j=" + j);
  int k = i++;//先算赋值，把i的值赋值给k，i原来是3，把3赋值给k，然后i在自增1，i变成4
  System.out.println("自增第四次之后i=" + i);
  System.out.println("k=" + k);
  
  //面试题：陷阱题
  i = i++;//先赋值，把i原来的值重新赋值给i，不变，然后i自增，但是这个自增后的值没有在放回变量i的位置
  System.out.println("自增第五次之后i=" + i);
 }
}

```

#### 小明要到美国旅游，可是那里的温度是以华氏度为单位记录的。它需要一个程序将华氏温度（80度）转换为摄氏度，并以华氏度和摄氏度为单位分别显示该温度

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/575720823220662.png =645x)

```
public class ChangeCF{
        public static void main(String[] args){
                double celsius;
                int fahrenheit = 80;

                celsius = (fahrenheit - 32) / 1.8;
                System.out.println("华氏度：" + fahrenheit);
                System.out.println("摄氏度：" + celsius);
        }
}
```

### 赋值运算符

符号：=
当“=”两侧数据类型不一致时，可以使用自动类型转换或使用强制类型转换原则进行处理。
支持连续赋值。

```
public class SetValueTest{
        public static void main(String[] args){
                int i,j;
                i = j = 1;
                System.out.println("i=" + i + "|" + "j=" + j);
        }
}

i=1|j=1
```

#### 扩展赋值运算符： +=, -=, *=, /=, %=

##### 不会改变本身数据类型

而`s1 = s1 + 10` 改变//整数默认int

```
public class SetValueTest{
        public static void main(String[] args){
                int num;
                num = 10;
                num += 2;
                System.out.println(num);

                num = 10;
                num = num + 2;
                System.out.println(num);

                short s1 = 10;
                //s1 = s1 + 2; 编译失败
                s1 += 2;
                System.out.println(s1);
        }
}
```

#### 思考1

```
short s = 3; 
s = s + 2; ① //编译失败
s += 2; ②
①和②有什么区别？
```

#### 思考2

```
int i = 1;
i *= 0.1;
System.out.println(i); //0
i++;
System.out.println(i); //1
```

#### 思考3

```
int m = 2;
int n = 3;
n *= m++;  //2 * 3 == 6
System.out.println("m=" + m); //3
System.out.println("n=" + n); //6
```

#### 思考4：自增/自减的运算在同一行，运算不是全部按照原来的数一个个运算，而是按顺序在前面运算后的基础上运算

```
int n = 10;
n += (n++) + (++n);  //10 + 12 + 10 == 32
System.out.println(n);  //32
```

### 比较运算符（关系运算符）

- 比较运算符的结果都是boolean型，也就是要么是true，要么是false。
  - 不是0，1
- 比较运算符“==”不能误写成“=” 。
- ==  != 不仅能使用在数值类型之间，还可以使用在其他引用类型变量之间

```
Account acct1 = new Account(1000)

Account acct2 = new Account(1000)

boolean b1 = (acct1 == actt2) //比较两个Account是否是同一个账户 而不是有多少钱
//false
```

- `> < >= <=` 只能使用在数值类型的数据中之间

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/371082210220660.png =772x)

#### 思考1

```
boolean b1 = false;
//区分好==和=的区别。
if(b1==true){
    System.out.println("结果为真");
}
else{
    System.out.println("结果为假");
}
//结果为假

if(b1=true){    //有误，但编译通过
    System.out.println("结果为真");
}
else{
    System.out.println("结果为假");
}
//结果为假
```

***

### 逻辑运算符

- & —逻辑与
- | —逻辑或
- ！—逻辑非
- && —短路与
- || —短路或
- ^ —逻辑异或

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/331722510239086.png =608x)

逻辑运算符用于连接布尔型表达式，在Java中不可以写成`3<x<6`，应该写成`x>3 & x<6`。

- "&”和“&&”的区别：
  - 单&时，左边无论真假，右边都进行运算；
  - 双&时，如果左边为真，右边参与运算，如果左边为假，那么右边不参与运算。
- "|”和“||”的区别同理，
  - ||表示：当左边为真，右边不参与运算。
- 异或( ^ )与或( | )的不同之处是：
  - 当左右都为true时，结果为false。
  - 理解：异或，追求的是“异”!

```
public class LogicTest{
        public static void main(String[] args){
                boolean b1 = true;
                int num1 = 10;
                if(b1 & (num1++ > 0)){
                        System.out.println("北京");
                }
                else{
                        System.out.println("南京");
                }
                System.out.println("num1=" + num1);

                boolean b2 = true;
                int num2 = 10;
                if(b2 && (num2++ > 0)){
                        System.out.println("北京");
                }
                else{
                        System.out.println("南京");
                }
                System.out.println("num2=" + num2);

                boolean b3 = false;
                int num3 = 10;
                if(b3 & (num3++ > 0)){
                        System.out.println("北京");
                }
                else{
                        System.out.println("南京");
                }
                System.out.println("num3=" + num3);

                boolean b4 = false;
                int num4 = 10;
                if(b4 && (num4++ > 0)){
                        System.out.println("北京");
                }
                else{
                        System.out.println("南京");
                }
                System.out.println("num4=" + num4);
        }
}

北京
num1=11
北京
num2=11
南京
num3=11
南京
num4=10 
```

#### 练习：请写出每题的输出结果

##### 1

```
int x = 1;
int y = 1;
if(x++ == 2 & ++y == 2){
    x = 7;
}
System.out.println("x=" + x + ",y=" + y);  
//x=2,y=2
```

##### 2

```
int x = 1,y = 1;
if(x++ == 2 && ++y == 2){
    x = 7;
}
System.out.println("x=" + x + ",y=" + y);
//x=2,y=1
```

##### 3

```
int x = 1,y = 1;
if(x++ == 1 | ++y == 1){
    x = 7;
}
System.out.println("x=" + x + ",y=" + y);
//x=7,y=2
```

##### 4

```
int x = 1,y = 1;
if(x++ == 1 || ++y == 1){
    x = 7;
}
System.out.println("x=" + x + ",y=" + y);
//x=7,y=1
```

#### 【面试题】程序输出

```
class Test {
    public static void main (String[] args) {
        boolean x = true;
        boolean y = false;
        short z = 42;
        //if(y == true)
        if((z++ == 42)&&(y = true)) z++; //z == 44;y == true
        if((x = false) || (++z == 45)) z++;  //x == false;z == 46
        
        System. out.println(“z=”+z);  
    }
}

结果为：z=46

```

***

### 位运算符

#### 位运算是直接对整数的二进制进行的运算

- 位运算符操作的都是整型的数据
- 注意数据类型的长度
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/310843610226953.png =625x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/257723710239788.png =602x)

```
public class BitTest{
        public static void main(String[] args){
                byte num = 2;
                System.out.println("num << 8 = " + (num << 8));
        }
}

num << 8 = 512 ???
```

#### <<  乘2的n次方

- 在范围内，`<< n`即`乘2的n次方`
- 超过范围，负数...

```
public class BitTest{
        public static void main(String[] args){
                int i = 21;

                System.out.println("i << 2 :" + (i << 2));
                System.out.println("i << 27 :" + (i << 27));
        }
}

i << 2 :84
i << 27 :-1476395008
```

##### 最高效计算2*8

- 注：乘数中要有一个是2的倍数

```
public class BitTest{
        public static void main(String[] args){
                int num = 2;
                System.out.println("2 * 8 = "+ (num << 3));
        }
}

public class BitTest{
    public static void main(String[] args){
        System.out.println("2 * 8 = " + (8 << 1));
    }
}
```

#### >> 除以2的n次方

- 在范围内，`>>n`即`除以2的n次方`

#### >>>

#### & 有0则0，都1则1

```
public class BitTest{
        public static void main(String[] args){
                System.out.println(2 & 5);
        }
}
//0

//2  ...010
//5  ...101
//...000
```

#### | 有1则1，都0则0

```
public class BitTest{
        public static void main(String[] args){
                System.out.println(2 | 5);
        }
}
//7

//2  ...010
//5  ...101
//..111
```

#### ^ 不同则1，同则0

```
public class BitTest{
        public static void main(String[] args){
                System.out.println(2 ^ 5);
        }
}
//7
//2  ...010
//5  ...101
//...111
```

```
public class BitTest{
        public static void main(String[] args){
                System.out.println(1 ^ 5);
                System.out.println(1 | 5);
        }
}
// ^ ...100 //4
// | ...101 //5

//1  ...001
//5  ...101

```

#### ~ 取反

```
public class BitTest{
        public static void main(String[] args){
                System.out.println(~6);
        }
}
//-7
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/485374314239086.png =585x)

#### 如何求一个0~255范围内的整数的十六进制值，例如60的十六进制表示形式3C

```
//方式一：自动实现
public class BitTest{
        public static void main(String[] args){
                String str1 = Integer.toBinaryString(60);
                String str2 = Integer.toHexString(60);

                System.out.println(str1);
                System.out.println(str2);
        }
}

//方式二：手动实现
public class BitTest{
        public static void main(String[] args){
            int i1 = 60;      //0111100
            int i2 = i1 & 15; //0001111
            String j = (i2 > 9)? (char)(i2-10 + 'A')+"" : i2+"";
            int temp = i1 >>> 4;
            i2 = temp & 15;
            String k = (i2 > 9)? (char)(i2-10 + 'A')+"" : i2+"";
            System.out.println(k+""+j);
        }
}

```

#### 练习 交换两个变量的值

```
public class ExchangeNum{
        public static void main(String[] args){
                int num01 = 10;
                int num02 = 20;

                //方法1  定义临时变量
                int temp;
                temp = num01;
                num01 = num02;
                num02 = temp;
                System.out.println("num01=" + num01);
                System.out.println("num02=" + num02);
                
                //方法2  不用定义临时变量;但是可能超过数据范围;有局限性,只能是数值型
                num01 = num01 + num02;
                num02 = num01 - num02;
                num01 = num01 - num02;
                System.out.println("num01=" + num01);
                System.out.println("num02=" + num02);
                
                //方法3 ^ 不用定义临时变量;但是可能超过数据范围;有局限性,只能是数值型
                num01 = (num01 ^ num02);
                num02 = (num01 ^ num02);
                num01 = (num01 ^ num02);
                System.out.println("num01=" + num01);
                System.out.println("num02=" + num02);

        }
}
```

#### 错误 实现加法 sum = (num01 ^ num02) | num01 | num02 错误

```
public class AddNum{
        public static void main(String[] args){
                int num01 = 12;
                int num02 = 20;
                int sum;

                sum = (num01 ^ num02) | num01 | num02;
                System.out.println("sum=" + sum);
        }
}

//32
/*
num01 ^ num02
...01100 ^ 
...10100  == ...11000

(num01 ^ num02) | num01 | num02
...11000 |
...01100 == 
...11100 |
...10100 == ...11100
*/
```

#### 实现减法

```

/*
num01 ^ num02
...01100 ^ 
...10100  == ...11000

*/
```

#### 实现乘法

```

```

#### 实现除法

```

```

***

### 三元运算符

```
(条件表达式)?表达式1：表达式2；
```

- 条件表达式的结果为boolean类型
- 条件表达式为true，运算后的结果是表达式1；
- 条件表达式为false，运算后的结果是表达式2；  
- 表达式1和表达式2为要求是同种类型, 或者可以自动转换的
- 可以嵌套使用

```
public class SanYuan{
        public static void main(String[] args){
                int m = 12;
                int n = 5;

                int max = (m > n)? m : n;
                System.out.println(max);
                
                double num = (m > n)? 1 : 1.0;
                System.out.println(num);
                
                //(m > n)? 1:"你好"; //无法自动转换，类型不统一//编译错误
                
                String maxStr = (m > n)? "m大":"n大";
                System.out.println(maxStr);
                
                int max = (((m > n)? m : n) == m)? m : n;
                System.out.println(maxNum);
        }
}
```

#### 获取三个数的最大值

```
public class SanYuan{
        public static void main(String[] args){
                int x = 12;
                int y = 5;
                int z = 9;
                //
                int maxNum = (x > y)? x : y;
                int maxNum = (maxNum > z)? maxNum : z;
                System.out.println(maxNum);
                //
                int max = (((x > y)? x : y) > z)? ((x > y)? x : y) : z;
                System.out.println(max);
        }
}
```

#### 要求用户输入两个数a和b，如果a能被b整除或者a加b大于1000，则输出a；否则输出b

```
import java.util.Scanner;

public class SangYuanTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.print("a: ");
                int a = scan.nextInt();
                System.out.println();
                System.out.print("b: ");
                int b = scan.nextInt();
                System.out.println();

                System.out.println(((a % b == 0) || (a + b > 1000))? a : b);
        }
}
```

#### 使用条件结构实现，如果用户名等于字符‘青’，密码等于数字‘123’，就输出“欢迎你，青”，否则就输出“对不起，你不是青”。 ???

提示：先声明两个变量，一个是char型的，用来存放用户名，一个是int型的，用来存放密码。

```
import java.util.Scanner;

public class SangYuanTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.print("user: ");
                String user = scan.next();
                System.out.println();
                System.out.print("password: ");
                int password = scan.nextInt();
                System.out.println();

                System.out.println(((user == "青") && (password == 123))? "欢迎你！青" : "对不起，你不是青");
        }
}
```

#### 三元运算符与if-else的联系与区别

1. 三元运算符可简化if-else语句,优先三元运算符
2. 三元运算符要求必须返回一个结果。
3. if后的代码块可有多个语句

***

### 运算符的优先级

运算符有不同的优先级，所谓优先级就是表达式运算中的运算顺序。如表，上一行运算符总优先于下一行。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/433304110226324.png =378x)

只有单目运算符、三元运算符、赋值运算符是从右向左运算的。

#### 考查运算符的优先级

```
写出输出的结果.
class Demo{
    public static void main(String[] args){
        int x=0,y=1;
        if(++x==y-- & x++==1||--y==0){
            System.out.println("x="+x+",y="+y);   //x = 2,y = 0;
        }
        else{
         System.out.println("y="+y+",x="+x);
        }
     }
}
```

***

## 程序流程控制

### 键盘输入不同类型的变量 Scanner类

1. 导包 `import java.util.Scanner;`
2. Scanner的实例化 `Scanner scan = new Scanner(System.in);`
3. 调用Scanner类的相关方法，来获取指定类型的数据

- 需要根据相应的方法，来输入指定类型的数据，如果输入的数据类型与要求不匹配，报异常：.InputMismatchException，导致程序终止.
- 通过Scanner类的`next()`和`nextline()`方法获取输入的字符串，在读取前一般需要使用`hasNext()`和`hasNextLine()`判断是否还有输入的数据

#### 使用

```
import java.util.Scanner;

public class ScannerTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                int num = scan.nextInt();
                System.out.println(num);
        }
}
```

- scan.next() 字符串
- scan.nextInt() 整型
- scan.nextDouble() 浮点double
- scan.nextBoolean() 布尔  //true/false //而不是0/1
- 没有nextChar()

```
import java.util.Scanner;

public class ScannerTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.println("请输入姓名：");
                String name = scan.next();
                System.out.println(name);

                System.out.println("请输入年龄：");
                int age = scan.nextInt();
                System.out.println(age);

                System.out.println("请输入体重：");
                double weight = scan.nextDouble();
                System.out.println(weight);

                System.out.println("是否单身？");
                boolean isLove = scan.nextBoolean();
                System.out.println(isLove);

                //对于char型的获取。Scanner没有提供方法，只能获取一个字符串
                System.out.println("请输入性别 男/女");
                String gender = scan.next();
                char genderChar = gender.charAt(0); //获取索引位置上的字符
                System.out.println(genderChar);
        }
}
```

```
Exception in thread "main" java.util.InputMismatchException
        at java.base/java.util.Scanner.throwFor(Scanner.java:943)
        at java.base/java.util.Scanner.next(Scanner.java:1598)
        at java.base/java.util.Scanner.nextBoolean(Scanner.java:1898)
        at ScannerTest.main(ScannerTest.java:20)
```

#### next的用法

```
package com.cdoudou.scanner;

import java.util.Scanner;

public class Demo01 {
    public static void main(String[] args) {

        //创建一个扫描器对象，用于接收键盘数据
        Scanner scanner = new Scanner(System.in);
        System.out.println("用next的方式接受");

        //判断用户有没有输入字符串
        if(scanner.hasNext()){
            //使用next接收
            String str = scanner.next();
            System.out.println("输出内容为："+str);
        }

        //注意使用完要注意关掉  凡是从属于IO流的类，如果使用完不关闭会一直占用资源
        scanner.close();
    }
} 
```

#### nextLine()用法

```
package com.cdoudou.scanner;

import java.util.Scanner;

public class Demo02 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("使用nextLine接收字符串:");

        if(scanner.hasNextLine()){
            String str = scanner.nextLine();
            System.out.println("接受的内容为："+str);
        }
        scanner.close();


//        int sum = 0;
//        for (int i = 0; i < 10; i++) {
//            sum += i;
//            if(i % 3 == 0){
//                break;
//            }
//        }
//        System.out.println(sum);
    }
}
```

#### next() nextLine()

- next()

1. 一定要读取到有效字符后才可以结束输入
2. 对输入有效字符之前遇到的空白，next()方法会自动将其去掉
3. 只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符
4. next()不能得到带有空格的字符串

- nextLine()

1. 以Enter为结束符，也就是说nextLine()方法返回的是输入回车之前的所有字符
2. 可以获得空白

##### 也可以输入中文

```
package com.cdoudou.scanner;

import java.util.Scanner;

public class Demo03 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("使用nextLine接收字符串:");

        String str = scanner.nextLine();
        System.out.println("接受的内容为："+str);
        scanner.close();
        /*
        *   使用nextLine接收字符串:
            可以输入中文的
            接受的内容为：可以输入中文的
        * */
    }
}
```

#### Java 输入输出流 解决中文乱码问题

***

### 顺序结构

程序从上到下逐行地执行，中间没有任何判断和跳转。
Java中定义成员变量时采用合法的前向引用

```
public class Test{
    int num1 = 12;
    int num2 = num1 + 2;
}
错误形式：
public class Test{
    int num2 = num1 + 2；
    int num1 = 12;
}

```

### 分支结构

根据条件，选择性地执行某段代码。
有if…else和switch-case两种分支语句。

#### if...else

```
if(条件表达式){
    执行代码块;
}
```

```
if(条件表达式){
    执行代码块;
}
else{
    执行代码块;
}
```

```
if(条件表达式1){
    执行代码块;
}
else if(条件表达式2){
    执行代码块;
}
else{   //可以不要
    执行代码块;
}
```

- 条件表达式必须是布尔表达式（关系表达式或逻辑表达式）、布尔变量
- 语句块只有一条执行语句时，一对{}可以省略，但建议保留
- if-else语句结构，根据需要可以嵌套使用
- 当if-else结构是“多选一”时，最后的else是可选的，根据需要可以省略
- 当多个条件是“互斥”关系时，条件判断语句及执行语句间顺序无所谓
- 当多个条件是“包含”("例题1(2)")关系时，“小上大下 / 子上父下”
- 注意变量的赋值 在if外的变量应该要先赋值，再使用，如果不满足条件，则未赋值，编译不通过

```
public class Iftest{
        public static void main(String[] args){
                //例1
                int heartBeats = 79;
                if(heartBeats < 60 || heartBeats > 100){
                        System.out.println("需要进一步检查");
                }
                System.out.println("检查结束");

                //例2
                int age = 23;
                if(age < 18){
                        System.out.println("你只能看动画片");
                }
                else{
                        System.out.println("你还是不可以色色的哦！");
                }

                //例3
                if(age < 0){
                        System.out.println("输入数据不合法");
                }
                else if(age < 18){
                        System.out.println("小孩你好呀！");
                }
                else if(age < 30){
                        System.out.println("青壮年时期");
                }
                else if(age < 60){
                        System.out.println("人到中年");
                }
                else if(age < 120){
                        System.out.println("老年");
                }
                else{
                        System.out.println("你不对劲");
                }
        }
}
```

##### 例0

```
public class Iftest{
        public static void main(String[] args){
                int age = 75;
                if(age < 0){
                        System.out.println("不可能！还没出生！");
                }
                else if(age > 250){
                        System.out.println("老妖怪！！！");
                }
                else{
                        System.out.println("芳龄：" + age);
                }
        }
}
```

##### 例题1 成绩

```
岳小鹏参加Java考试，他和父亲岳不群达成承诺：
如果：
成绩为100分时，奖励一辆BMW；
成绩为(80，99]时，奖励一台iphone xs max；
当成绩为[60,80]时，奖励一个 iPad；
其它时，什么奖励也没有。
请从键盘输入岳小鹏的期末成绩，并加以判断
```

```
1)
import java.util.Scanner;

public class IfTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                int score = scan.nextInt();

                if(score > 80 && score <= 99){
                        System.out.println("iphone xs max");
                }
                else if(score >= 60 && score <=80){
                        System.out.println("iPad");
                }
                else{
                        System.out.println("Null");
                }
        }
}

2)
import java.util.Scanner;

public class IfTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.print("成绩为：");
                int score = scan.nextInt();

                System.out.println("奖励为：");
                if(score > 100){
                        System.out.println("作弊");
                }
                else if(score > 80){
                        System.out.println("iphone");
                }
                else if(score >= 60){
                        System.out.println("ipad");
                }
                else{
                        System.out.println("null");
                }
        }
}
```

##### 例题2 排序

```
编写程序：由键盘输入三个整数分别存入变量num1、num2、num3，
对它们进行排序(使用 if-else if-else),并且从大到小输出。
```

```
import java.util.Scanner;

public class IfTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.println("请输入数字1");
                int num1 = scan.nextInt();
                System.out.println("请输入数字2");
                int num2 = scan.nextInt();
                System.out.println("请输入数字3");
                int num3 = scan.nextInt();

                System.out.println("输出:");

                if(num1 >= num2 && num1 >= num3){
                        System.out.println("first:" + num1);

                        if(num2 >= num3){
                                System.out.println("seconde:" + num2);
                                System.out.println("third:" + num3);
                        }
                        else{
                                System.out.println("seconde:" + num3);
                                System.out.println("third:" + num2);
                        }
                }
                else if (num2 >= num1 && num2 >= num3){
                        System.out.println("first:" + num2);

                        if(num1 >= num3){
                                System.out.println("seconde:" + num1);
                                System.out.println("third:" + num3);
                        }
                        else{
                                System.out.println("seconde:" + num3);
                                System.out.println("third:" + num1);
                        }
                }
                else if(num3 >= num1 && num3 >= num2){
                        System.out.println("first:" + num3);

                        if(num1 >= num2){
                               System.out.println("seconde:" + num1);
                               System.out.println("third:" + num2);
                        }
                        else{
                                System.out.println("seconde:" + num2);
                                System.out.println("third:" + num1);
                        }
                }
                else{
                        System.out.println("有误！！！");
                }
        }
}

2)
import java.util.Scanner;

public class IfTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.print("第一个整数：");
                int num1 = scan.nextInt();
                System.out.print("第二个整数：");
                int num2 = scan.nextInt();
                System.out.print("第三个整数：");
                int num3 = scan.nextInt();

                if(num1 >= num2){
                        if(num3 >= num1){
                                System.out.println(num3 + "\t" + num1 + "\t" + num2);
                        }
                        else if(num3 <= num2){
                                System.out.println(num1 + "\t" + num2 + "\t" + num3);
                        }
                        else{
                                System.out.println(num1 + "\t" + num3 + "\t" + num2);
                        }
                }
                else{
                        if(num3 >= num2){
                                System.out.println(num3 + "\t" + num2 + "\t" + num1 );
                        }
                        else if(num3 <= num1){
                                System.out.println(num2 + "\t" + num1 + "\t" + num3);
                        }
                        else{
                                System.out.println(num2 + "\t" + num3 + "\t" + num1);
                        }
                }
        }
}
```

##### if语句练习1

```
1)对下列代码，若有输出，指出输出结果。
int x = 4;
int y = 1;
if (x > 2) {
    if (y > 2) 
    System.out.println(x + y);
System.out.println("atguigu");
} 
else
    System.out.println("x is " + x);

atguigu
x is 4
//大括号的省略，有缩进要求

2) 
boolean b = true;
//如果写成if(b=false)能编译通过吗？如果能，结果是？
if(b == false) 
    System.out.println("a");
else if(b)
    System.out.println("b");
else if(!b)
    System.out.println("c");
else
    System.out.println("d");
```

##### if语句练习2

```
1) 编写程序，声明2个int型变量并赋值。判断两数之和，如果大于等于50，打印“hello world!”
2) 编写程序，声明2个double型变量并赋值。判断第一个数大于10.0，且第2个数小于20.0，打印两数之和。否则，打印两数的乘积。
3) 我家的狗5岁了，5岁的狗相当于人类多大呢？其实，狗的前两年每一年相当于人类的10.5岁，之后每增加一年就增加四岁。那么5岁的狗相当于人类多少年龄呢？应该是：10.5 + 10.5 + 4 + 4 + 4 = 33岁。
编写一个程序，获取用户输入的狗的年龄，通过程序显示其相当于人类的年龄。如果用户输入负数，请显示一个提示信息。
```

```
1)
import java.util.Scanner;

public class IfTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.println("请输入数字1:");
                int num1 = scan.nextInt();
                System.out.println("请输入数字2:");
                int num2 = scan.nextInt();

                if((num1 + num2) >= 50){
                        System.out.println("Hello wirld!");
                }
                else{
                        System.out.println("not in");
                }
        }
}

2)
import java.util.Scanner;

public class IfTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.println("请输入数字1");
                double num1 = scan.nextDouble();
                System.out.println("请输入数字2");
                double num2 = scan.nextDouble();

                System.out.println("结果是：");
                if(num1 > 10.0 && num2 <20.0){
                        System.out.println("两数相加：" + (num1 + num2));
                }
                else{
                        System.out.println("两数相乘：" + (num1 * num2));
                }
        }
}

3)
import java.util.Scanner;

public class IfTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.println("请输入狗的年龄:");
                double dogAge = scan.nextDouble();
                double humanAge;

                if(dogAge >= 0){
                        if(dogAge <= 2){
                                humanAge = (dogAge * 10.5);
                                System.out.println("人类年龄为：" + humanAge);
                        }
                        else{
                                humanAge = ((dogAge - 2) * 4 +2 * 10.5);
                                System.out.println("人类年龄为：" + humanAge);
                        }
                }
                else{
                        System.out.println("有误!!!");
                }
        }
}
```

##### if语句练习3 彩票 使用(int)(Math.random() * 90 + 10)产生随机数

```
假设你想开发一个玩彩票的游戏，程序随机地产生一个两位数的彩票，提示用户输入一个两位数，然后按照下面的规则判定用户是否能赢。
1)如果用户输入的数匹配彩票的实际顺序，奖金10 000美元。
2)如果用户输入的所有数字匹配彩票的所有数字，但顺序不一致，奖金 3 000美元。
3)如果用户输入的一个数字仅满足顺序情况下匹配彩票的一个数字，奖金1 000美元。
4)如果用户输入的一个数字仅满足非顺序情况下匹配彩票的一个数字，奖金500美元。
5)如果用户输入的数字没有匹配任何一个数字，则彩票作废。
提示：使用(int)(Math.random() * 90 + 10)产生随机数。
Math.random() : [0,1) * 90 [0,90) + 10 [10,100)  [10,99]
```

```
import java.util.Scanner;

public class IfTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.println("请输入你的数字(必须是两位数)");
                int num = scan.nextInt();
                int firstNum = num / 10;
                int secondeNum = num % 10;

                int reword = (int)(Math.random() * 90 + 10);
                //第一位中奖号码
                int firstReword = reword / 10;
                //第二位中奖号码
                int secondeReword = reword % 10;
                //逆序的中奖号码
                int rReword = ((reword / 10) + (reword % 10) * 10);

                if(num >= 10 && num <=99){
                        if(num == reword){
                                System.out.println("大奖10,000美金");
                        }
                        else if(num == rReword){
                                System.out.println("3,000美金");
                        }
                        else if((firstNum == firstReword && secondeNum != secondeReword) || ((firstNum != firstReword) && (secondeNum == secondeReword))){
                                System.out.println("1,000美金");
                        }
                        else{
                                System.out.println("很遗憾！！!");
                        }
                }
                else{
                        System.out.println("有误！！！超过范围");
                }
        }
}
```

##### if语句练习4

```
大家都知道，男大当婚，女大当嫁。那么女方家长要嫁女儿，当然要提出一定的条件：
高：180cm以上；
富：财富1千万以上；
帅：是。
 如果这三个条件同时满足，则：“我一定要嫁给他!!!”
 如果三个条件有为真的情况，则：“嫁吧，比上不足，比下有余。”
 如果三个条件都不满足，则：“不嫁！”
Sysout(“身高: (cm))
scanner.nextInt();
Sysout(“财富: (千万))
scanner.nextDouble();
Sysout(“帅否: (true/false)) (是/否)
scanner.nextBoolean(); scanner.next(); “是”.equals(str) 
```

```
import java.util.Scanner;

public class IfTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.println("请输入你的条件:");
                System.out.println("请输入身高：(cm)");
                int hight = scan.nextInt();
                System.out.println("请输入财富(千万)");
                double wealth = scan.nextDouble();
                System.out.println("请输入帅否？(false/true)");
                boolean handsome = scan.nextBoolean();

                if(hight >= 180 && wealth >= 1 && handsome == true){
                        System.out.println("我一定要嫁给他");
                }
                else if(hight <180 && wealth <1 && handsome == false){
                        System.out.println("不嫁");
                }
                else{
                        System.out.println("嫁吧。");
                }
        }
}
```

##### 求ax2+bx+c=0方程的根

```
求ax2+bx+c=0方程的根。a,b,c分别为函数的参数，
如果a≠0，那么：
（1）当b2-4ac>0，则有两个解； 
（2）当b2-4ac=0，则有一个解； 
（3）当b2-4ac<0，则无解；
如果a=0,b≠0，那么， 

提示1：Math.sqrt(num);  sqrt指平方根
例如：
求x2-4x+1=0方程的根
求x2-2x+1=0方程的根
```

```
import java.util.Scanner;

class Exer5{
 public static void main(String[] args){
  Scanner input = new Scanner(System.in);
  
  System.out.println("一元二次方程：ax^2+bx+c=0");
  System.out.print("请输入参数a：");
  double a = input.nextDouble();
  
  System.out.print("请输入参数b：");
  double b = input.nextDouble();
   
  System.out.print("请输入参数c：");
  double c = input.nextDouble();

  if(a!=0){
   double temp = b*b - 4*a*c;
   if(temp==0){
    double x = -b/(2*a);
    System.out.println("该方程是一元二次方法，有两个相同解：" + x);
   }else if(temp>0){
    double sqrt = Math.sqrt(temp);
    double x1 = (-b+ sqrt)/(2*a);
    double x2 = (-b- sqrt)/(2*a);
    System.out.println("该方程是一元二次方法，两个不同解：" + x1 +"," + x2);
   }else{
    System.out.println("该方程是一元二次方法，在实数范围内无解！");
   }
  }else{
   if(b!=0){
    double x = -c/b;
    System.out.println("该方程是一元一次方程，有一个解：" + x);
   }else{
    System.out.println("不是方程，是一个等式");
                if(c == 0){
                    System.out.println("等式成立");
}else{
    System.out.println("等式不成立");
}
   }
  }
 }
}
```

#### switch-case
##### 冒号case:
1. 根据switch表达式中的值，一次匹配各个case中的常量，一旦匹配成功，则进入相应的case结构中，调用其执行语句。当调用完执行语句以后，则仍然继续向下执行其他case结构中的执行语句，直到遇到break关键字或者此swtich-case结构末尾结束为止。
2. break;可以使用在switch-case结构中，表示一旦执行到此关键字就跳出switch-case结构
3. switch结构的表达式只能是以下6种之一： 不能是boolean(不能是布尔表达式)
      - byte
      - short
      - char
      - int
      - 枚举类型
      - String类型
4. case子句中的值必须是常量，不能是变量名或不确定的表达式值；
5. 同一个switch语句，所有case子句中的常量值互不相同；
6. break语句用来在执行完一个case分支后使程序跳出switch语句块；如果没有break，程序会顺序执行到switch结尾
7. default子句是可任选的。同时，位置也是灵活的(都是最后执行的)。当没有匹配的case时，执行default
8. case:可以合并 case 1: case 2: ....

- 凡是可以使用switch-case结构的都可以使用if-else，分之，不成立
- 两者均可时，且switch中表达式取值情况不多时，优先选择switch-case效率稍高

```
switch(表达式){
case 常量1:
    语句1;
    // break;
case 常量2:
    语句2;
    // break;
… …
case 常量N:
    语句N;
    // break;
default:
    语句;
    // break;
}
```

###### 控制流贯穿
- 无break; 时在第一个满足条件之后的执行语句都执行

```
public class SwitchTest{
        public static void main(String[] args){
                int num = 2;
                switch(num){
                        case 0:
                                System.out.println("zero");
                                break;
                        case 1:
                                System.out.println("one");
                                break;
                        case 2:
                                System.out.println("two");
                                break;
                        default:
                                System.out.println("three");
                                break;

                }
        }
}

//two

public class SwitchTest{
        public static void main(String[] args){
                int num = 2;
                switch(num){
                        case 0:
                                System.out.println("zero");
                        case 1:
                                System.out.println("one");
                        case 2:
                                System.out.println("two");
                        default:
                                System.out.println("three");

                }
        }
}

//two
//three
```

###### 例1 把小写类型的 char型转为大写

```
1.使用 switch 把小写类型的 char型转为大写。只转换 a, b, c, d, e. 其它的输出 “other”。
提示：String word = scan.next(); char c = word.charAt(0); switch(c){}
```

```
import java.util.Scanner;

public class SwitchTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                String word = scan.next();
                char c = word.charAt(0);
                switch(c){
                        case 'a':
                                System.out.println('A');
                                break;
                        case 'b':
                                System.out.println('B');
                                break;
                        case 'c':
                                System.out.println('C');
                                break;
                        default:
                                System.out.println("other");
                                break;
                }
        }
}
```

```
import java.util.Scanner;

public class SwitchTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.print("请输入:");
                String str = scan.next();
                switch(str){
                        case "a":
                                str = "A";
                                break;
                        case "b":
                                str = "B";
                                break;
                        case "c":
                                str = "C";
                                break;
                        case "d":
                                str = "D";
                                break;
                        case "e":
                                str = "E";
                                break;
                        default:
                                str = "other";
                                break;
                }
                System.out.print("结果是: " + str);
        }
}
```

###### 例2 成绩大于60分

```
2.对学生成绩大于60分的，输出“合格”。低于60分的，输出“不合格”。
```

```
import java.util.Scanner;

public class SwitchTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.print("请输入成绩：");
                int score = scan.nextInt();

                switch(score / 60){
                        case 1:
                               System.out.println("合格");
                               break;
                        case 0:
                               System.out.println("不合格");
                               break;
                        default:
                               System.out.println("成绩有误");
                }
        }
}
```

###### 例3 该月份所属的季节

```
3.根据用于指定月份，打印该月份所属的季节。
3,4,5 春季 6,7,8 夏季 9,10,11 秋季 12, 1, 2 冬季
```

```
import java.util.Scanner;

public class SwitchTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.print("请输入月份：");
                int month = scan.nextInt();

                switch(month){
                        case 3: case 4: case 5:
                                System.out.println("春季");
                                break;
                        case 6: case 7: case 8:
                                System.out.println("夏季");
                                break;
                        case 9: case 10: case 11:
                                System.out.println("秋季");
                                break;
                        case 1: case 2: case 12:
                                System.out.println("冬季");
                                break;
                        default:
                                System.out.println("有误");
                }
        }
}
```

###### 例4 2019年的第几天

```
4. 编写程序：从键盘上输入2019年的“month”和“day”，要求通过程序输出输入的日期为2019年的第几天。
```

```
import java.util.Scanner;

public class SwitchTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.print("month = ");
                int month = scan.nextInt();
                System.out.print("day = ");
                int day = scan.nextInt();
                int days;
                int temp = month;

                switch(month){
                        case 12:
                                month += 30;
                        case 11:
                                month += 31;
                        case 10:
                                month += 30;
                        case 9:
                                month += 31;
                        case 8:
                                month += 31;
                        case 7:
                                month += 30;
                        case 6:
                                month += 31;
                        case 5:
                                month += 30;
                        case 4:
                                month += 31;
                        case 3:
                                month += 28;
                        case 2:
                                month += 31;
                        case 1:
                                month += 0;
                                break;
                }
                days = month + day - temp;
                System.out.println("2019年的第" + days + "天");
        }
}
```

###### switch语句练习1 判断这一天是当年的第几天

```
从键盘分别输入年、月、日，判断这一天是当年的第几天
注：判断一年是否是闰年的标准：
1）可以被4整除，但不可被100整除
或
2）可以被400整除
```

```
import java.util.Scanner;

class Test{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.println("请依次输入年月日:");
                int year = scan.nextInt();
                int month = scan.nextInt();
                int day = scan.nextInt();
                int days = 0;
                int temp = 0;
                
                if (((year % 4 == 0) && ((year % 100) != 0)) || (year % 400 == 0)){
                    temp = 0;
                }
                else{
                    temp = 1;
                }
                switch(temp){
                        case 0:
                                switch(month){
                                        case 12:
                                                days += 30;
                                        case 11:
                                                days += 31;
                                        case 10:
                                                days += 30;
                                        case 9:
                                                days += 31;
                                        case 8:
                                                days += 31;
                                        case 7:
                                                days += 30;
                                        case 6:
                                                days += 31;
                                        case 5:
                                                days += 30;
                                        case 4:
                                                days += 31;
                                        case 3:
                                                days += 29;
                                        case 2:
                                                days += 31;
                                        case 1:
                                                days += day;
                                                break;
                                }
                                break;
                        default:
                                switch(month){
                                        case 12:
                                                days += 30;
                                        case 11:
                                                days += 31;
                                        case 10:
                                                days += 30;
                                        case 9:
                                                days += 31;
                                        case 8:
                                                days += 31;
                                        case 7:
                                                days += 30;
                                        case 6:
                                                days += 31;
                                        case 5:
                                                days += 30;
                                        case 4:
                                                days += 31;
                                        case 3:
                                                days += 28;
                                        case 2:
                                                days += 31;
                                        case 1:
                                                days += day;
                                                break;
                                }
                                break;
                }
                System.out.println(year + "年" + month + "月" + day + "日" + "是第" + days + "天");
        }
}
```

###### switch语句练习2 改写下列if

```
使用switch语句改写下列if语句：
int a = 3;
int x = 100;
if(a == 1)
    x += 5;
else if(a == 2)
    x += 10;
else if(a == 3)
    x += 16;
else
    x += 34;
```

```
public class Test{
        public static void main(String[] args){
                int a = 3;
                int x = 100;

                switch(a){
                        case 1:
                                x += 5;
                                break;
                        case 2:
                                x += 3;
                                break;
                        case 3:
                                x += 16;
                                break;
                        default:
                                x += 34;
                                break;
                }
                System.out.println(x);
        }
}
```

###### switch语句练习3

```
编写程序：从键盘上读入一个学生成绩，存放在变量score中，根据score的
值输出其对应的成绩等级：

score>=90 等级: A
70<=score<90 等级: B 
60<=score<70 等级: C
score<60 等级: D

方式一：使用if-else
方式二：使用switch-case: score / 10: 0 - 10
```

```
1.
import java.util.Scanner;

public class Test{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.print("成绩:");
                int score = scan.nextInt();

                if(score >= 90){
                        System.out.println("等级:A");
                }
                else if(score >= 70){
                        System.out.println("等级:B");
                }
                else if(score >= 60){
                        System.out.println("等级:C");
                }
                else{
                        System.out.println("等级:D");
                }
        }
}
2.
import java.util.Scanner;

public class Test{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.print("成绩:");
                int score = scan.nextInt();

                switch(score / 10){
                        case 9:
                                System.out.println("等级:A");
                                break;
                        case 8: case 7:
                                System.out.println("等级:B");
                                break;
                        case 6:
                                System.out.println("等级:C");
                                break;
                        default:
                                System.out.println("等级:D");
                                break;
                }
        }
}
```

###### switch语句练习4

```
编写一个程序，为一个给定的年份找出其对应的中国生肖。中国的生肖基于12年一个周期，每年用一个动物代表：rat、ox/tiger、rabbit、dragon、snake、horse、sheep、monkey、rooster、dog、pig。
```

```
import java.util.Scanner;

public class Test{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.println("year:");
                int year = scan.nextInt();

                switch(year % 12){
                        case 1:
                                System.out.println("鼠");
                                break;
                        case 2:
                                System.out.println("牛");
                                break;
                        case 3:
                                System.out.println("虎");
                                break;
                        case 4:
                                System.out.println("兔");
                                break;
                        case 5:
                                System.out.println("龙");
                                break;
                        case 6:
                                System.out.println("蛇");
                                break;
                        case 7:
                                System.out.println("马");
                                break;
                        case 8:
                                System.out.println("羊");
                                break;
                        case 9:
                                System.out.println("猴");
                                break;
                        case 10:
                                System.out.println("鸡");
                                break;
                        case 11:
                                System.out.println("狗");
                                break;
                        case 0:
                                System.out.println("猪");
                                break;
                }
        }
}
```

##### 箭头case->
```
case label_1,label_2,...,label_n -> expression;|throw - statement;|block
```
- label中任意一个匹配成功，箭头右侧的代码都被执行，且在这些代码结束之后，不会产生控制流贯穿问题
- throw语句
- block 代码块

###### yield
- 大多数时候，在switch表达式内部，我们会返回简单的值。
- 但是，如果需要复杂的语句，我们也可以写很多语句，放到{…}里，然后，用yield返回一个值作为switch语句的返回值：

- yield 和 return 的区别在于：return 会直接跳出当前循环或者方法，而 yield 只会跳出当前 switch 块；
```
public class Test004 {
	public static void main(String args[]) {
		String fruit = "orange";
		
		int opt = switch(fruit){
		case "apple" -> 1;
		case "banana" -> 2;
		default ->{
			int code = fruit.hashCode();
			yield code;
			}
		};
		
		System.out.println(opt);
	}
}
```

###### throw
- 除了可以通过throws关键字抛出异常外,还可以使用throw关键字抛出异常。
- 与throws有所不同的是,
    - throw用于方法体内,并且抛出的是一个异常类对象,
    - 而throws关键字用在方法声明中,用来指明方法可能抛出的多个异常。
- 通过throw关键字抛出异常后，还需要使用throws关键字或try…catch对异常进行处理。
- 要注意的是，如果throw抛出的是Error、RuntimeException或它们的子类异常现象无需使用throws关键字或try…catch对异常进行处理。

```
public class Example34 {
	// 定义printAge()输出年龄
	public static void printAge(int age) throws Exception {
		if (age < 0) {
			// 对业务逻辑进行判断，当输入年龄为负数的时候抛出异常
			throw new Exception("输入的年龄有误，必须是正整数!");
		} else {
			System.out.println("此人年龄为：" + age);
		}
	}

	public static void main(String[] args) {
		// 下面的代码定义了一个try...catch语句用于捕获异常
		int age = -1;
		try {
			printAge(age);
		} catch (Exception e) {
			System.out.println("捕获的异常信息为：" + e.getMessage());
		}
	}
}
```

###### 例1

```
public class Test001 {
	public static void main(String args[]) {
		int numLetters = 0;

		Day day = Day.wednesday;
		
		switch(day) {
		case monday,friday,sunday -> numLetters = 6;
		case tuesday -> numLetters = 7;
		case thursday -> numLetters = 8;
		case wednesday -> numLetters = 9;
//		default -> throw new IllegaStateException("Invalid day: " + day);
		default -> numLetters = 0;
		}
		
		System.out.println(numLetters);
	}
}

enum Day{monday,tuesday,wednesday,thursday,friday,satarday,sunday;}
```

###### 例2

```
public class Test003 {
	public static void main(String args[]) {
		Day1 day = Day1.wednesday;
		
		int j = switch(day) {
		case monday -> 0;
		case tuesday -> 1;
		default -> {
			int k = day.toString().length();
			int result = k;
			yield result;
			}
		};
	}
}

enum Day1{monday,tuesday,wednesday,thursday,friday,satarday,sunday;}
```


***

### 循环结构

根据循环条件，重复性的执行某段代码。
有while、do…while、for三种循环语句。
注：JDK1.5提供了foreach循环，方便的遍历集合、数组元素。

#### for

```
for (①初始化部分; ②循环条件部分; ④迭代部分)｛
③循环体部分;
｝
```

- 执行过程：
①-②-③-④-②-③-④-②-③-④-.....-②
  - 则循环条件在循环失败时也执行了一次

- 循环条件部分为boolean类型表达式，当值为false时，退出循环
- 初始化部分可以声明多个变量，但必须是同一个类型，用逗号分隔
- 可以有多个变量更新，用逗号分隔

```
public class ForTest{
        public static void main(String[] args){
                int result = 0;
                for(int i = 1; i <= 100; i++){
                        result += i;
                        System.out.println("result=" + result);
                }
        }
}
```

```
public class Fortest{
        public static void main(String[] args){
                int num = 1;
                for(System.out.println('a');num <= 5;System.out.println('c')){
                        System.out.println('b');
                        num++;
                }
        }
}
```

##### for语句例题1 每个3的倍数行上打印出“foo”,在每个5的倍数行上打印“biz”,在每个7的倍数行上打印输出“baz”

```
编写程序从1循环到150，并在每行打印一个值，另外在每个3的倍数行上打印出“foo”,在每个5的倍数行上打印“biz”,在每个7的倍数行上打印输出“baz”。
```

```
public class ForTest{
        public static void main(String[] args){
                for(int i = 1;i <= 150;i++){
                        System.out.print(i);
                        if(i % 3 == 0){
                                System.out.print("\t" + "foo");
                        }
                        if(i % 5 == 0){
                                System.out.print("\t" + "biz");
                        }
                        if(i % 7 == 0){
                                System.out.print("\t" + "baz");
                        }
                        System.out.println();
                }
        }
}
```

##### for语句例题2 输入两个正整数m和n，求其最大公约数和最小公倍数

```
题目：输入两个正整数m和n，求其最大公约数和最小公倍数。
比如：12和20的最大公约数是4，最小公倍数是60。
说明：break关键字的使用
```

```
import java.util.Scanner;

public class Fortest{
        public  static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                System.out.print("请输入第一个正整数：");
                int n = scan.nextInt();
                System.out.print("请输入第二个正整数：");
                int m = scan.nextInt();

                int min = (n > m)? m : n;
                for(int i = min; i >= 1;i--){
                        if(m % i == 0 && n % i == 0){
                                System.out.println("最大公约数为: " + i);
                                break;
                        }
                }

                int max = (n > m)? n: m;
                for(int i = max; i <= m * n; i++){
                        if(i % m == 0 && i % n == 0){
                                System.out.println("最下公倍数为：" + i);
                                break;
                        }
                }
        }
}
```

```
public class ForTest{
        public static void main(String[]  args){
                Scanner scan = new Scanner(System.in);

                System.out.print("请输入正整数n:");
                int n = scan.nextInt();
                System.out.println();
                System.out.print("请输入正整数m:");
                int m = scan.nextInt();
                int t1 = 0;
                int t2 = n * m;

                for(int i = 1;i <= (n * m);i++){
                        if((n % i == 0) && (m % i == 0)){
                                if(t1 <= i){
                                        t1 = i;
                                }
                        }
                        if((i % n == 0) && (i % m == 0)){
                                if(t2 >= i){
                                        t2 = i;
                                }
                        }
                }
                System.out.println("最大公约数: " + t1);
                System.out.println("最小公倍数: " + t2);
        }
}
```

##### for语句练习1.打印1~100之间所有奇数的和

```
public class ForTest{
        public static void main(String[] args){
                int sum = 0;

                for(int i = 1;i <= 100;i++){
                        if( i % 2 == 1){
                                sum += i;
                        }
                }
                System.out.println(sum);
        }
}
```

##### for语句练习2.打印1~100之间所有是7的倍数的整数的个数及总和（体会设置计数器的思想）

```
public class ForTest{
        public static void main(String[] args){
                int count = 0;
                int sum = 0;

                for(int i = 1; i <= 100; i++){
                        if(i % 7 == 0){
                                count += 1;
                                sum += i;
                        }
                }
                System.out.println("count: " + count);
                System.out.println("sum " + sum);
        }
}
```

##### for语句练习3.输出所有的水仙花数，所谓水仙花数是指一个3位数，其各个位上数字立方和等于其本身

`例如： 153 = 1*1*1 + 3*3*3 + 5*5*5`

```
public class ForTest{
        public static void main(String[] args){
                int num1,num2,num3;
                for(int i = 100; i <= 999; i++){
                        num1 = i / 100;
                        num2 = (i % 100) / 10;
                        num3 = i % 10;

                        if(i == num1 * num1 * num1 + num2 * num2 * num2 + num3 * num3 * num3){
                                System.out.println(i);
                        }
                }
        }
}
```

##### 找出1000以内的所有完数

```
 一个数如果恰好等于它的因子之和，这个数就称为"完数"。（因子：除去这个数本身的约数）
例如6=1＋2＋3.编程 找出1000以内的所有完数
```

```
class ForTest{
 public static void main(String[] args){
  int factor = 0;
 
  for(int i = 1; i <= 1000; i++){
   
   for(int j = 1; j < i; j++){
   
    if(i % j == 0){
     factor += j;
    }
   }

   if(i == factor){
    System.out.println(i);
   }

   factor = 0;
  }
 }
}
```

优化

```
class ForTest{
 public static void main(String[] args){
  int factor = 0;
 
  for(int i = 1; i <= 1000; i++){
   
   for(int j = 1; j < i/2; j++){
   
    if(i % j == 0){
     factor += j;
    }
   }

   if(i == factor){
    System.out.println(i);
   }

   factor = 0;
  }
 }
}
```

#### 增强的for循环

```
for(类型 标识符: 可迭代类型的表达式) {
    语句;
} 
```
- 可迭代的数据类型按一般是数组或集合
```
public class Test008 {
	public static void main(String args[]) {
		int[] numbers = new int[] {1,2,3,4,5,6,7,8,9,10};
		
		for(int element : numbers) {
			System.out.println(element);
		}
	}
}
```

```
public class Test009 {
	public static void main(String args[]) {
		String[] strs = new String[] {"One","Two","There"};
		
		for(String str : strs) {
			System.out.println(str.toLowerCase());
		}
	}
}
```
- string.toLowCase()
    - 使用默认地区的规则将此String中的所有字符转换为小写字符。
    - 这相当于调用toLowerCase(Locale.getDefault())

#### while

```
①初始化部分
while(②循环条件部分)｛
    ③循环体部分;
    ④迭代部分;
}
```

- 执行过程：  
①-②-③-④-②-③-④-②-③-④-...-②
-
- 注意不要忘记声明④迭代部分。否则，循环将不能结束，变成死循环。
- for循环和while循环可以相互转换

```
public class WhileTest{
        public static void main(String[] args){
                int result = 0;
                int i = 1;
                while (i <= 100){
                        result += i;
                        i++;
                }
                System.out.println("result=" + result);
                //注 结果i为101
        }
}
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/289043723220669.png =696x)
##### 字符串复制

```
public class Test002 {
	public static void main(String args[]) {
		
		String copyFromMe = "Copy this string until you encounter the";
		String copyToMe = "";

		 int i = 0;
		 char c= copyFromMe.charAt(i);
		 
		 while(c != 'g') {
			 copyToMe = copyToMe + c;
			 c = copyFromMe.charAt(++i);
		 }
		 
		 System.out.println(copyToMe);
	}
}
```

###### charAt()方法
- charAt() 方法用于返回指定索引处的字符。索引范围为从 0 到 length() - 1。

```
public class Test {
    public static void main(String args[]) {
        String s = "www.runoob.com";
        
        char result = s.charAt(6);
        
        System.out.println(result); //n
    }
}
```


#### for和while区别

##### for循环和while循环的初始化作用域范围不同

- for 只在循环内部
- while 在外部

##### i++ 的值不一样

```
int i = 1;
while (i <= 100){
       result += i;
       i++;
}
// i = 101; 满足条件 i <= 100 后仍然i++;i++之后才不满足条件则停止，会比范围多1
//其i++本质上是执行语句

for(int i = 1; i <= 100; i++){
    ...    
}
//i = 100; 执行到 i <= 100 就停止;不会超过范围
```

#### do...while

```
①初始化部分;
do{
    ③循环体部分
    ④迭代部分
}while(②循环条件部分);
```

- 执行过程：
①-③-④-②-③-④-②-③-④-...②
- do-while循环至少执行一次循环体。
  - 即在不满足条件的时候也会执行一次
- 但如果满足执行多次，do...while和while没有区别，并不会多出1次

```
public class DoWhileTest{
        public static void main(String[] args){
                int result = 0,i = 1;
                do{
                        result += i;
                        i++;
                }while(i <= 100);
                System.out.println("result=" + result);
        }
}
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/199433823239095.png =689x)

##### 遍历100以内的偶数，并计算所有偶数的和及偶数的个数

```
public class DoWhiletest{
        public static void main(String[] args){
                int i = 1;
                int count = 0;
                int sum = 0;

                do{
                        if(i % 2 == 0){
                                count += 1;
                                sum += i;
                        }
                        i++;
                }while(i <= 100);
                System.out.println("个数：" + count);
                System.out.println("和: " + sum);
                System.out.println(i);
        }
}
```

##### 猜数字游戏

```
随机生成一个100以内的数，猜数字游戏：
从键盘输入数，如果大了提示，大了，如果小了，提示小了，如果对了，就不再猜了，并统计一共猜了多少次？
提示：随机数
import java.util.Random;
Random rand = new Random();
int num= rand.nextInt(100);
```

```
import java.util.Random;
import java.util.Scanner;

public class TestDoWhileLoop{
 public static void main(String[] args){
  //1、随机产生一个100以内的整数
  Random rand = new Random();
  //int num = rand.nextInt();//产生的是任意大小的整数
  int num = rand.nextInt(100);//产生[0,100)的整数
  System.out.println(num);
  
  //2、键盘输入
  Scanner input = new Scanner(System.in);
  
  //声明变量
  int guess;
  int count = 0;
  do{
   //循环体至少执行一次
   System.out.print("请输入一个整数：");
   guess = input.nextInt();//为变量赋值
   
   count++;//输入一次，计数一次
   
   if(guess>num){
    System.out.println("大了");
   }else if(guess < num){
    System.out.println("小了");
   }else{
    System.out.println("猜对了");
   }
  }while(guess != num);
  
  System.out.println("一共猜了：" + count + "次");
 }
 
}

public static void main(String[] args) {
  Random rand = new Random();
  int num= rand.nextInt(100);
  Scanner input = new Scanner(System.in);
  int count =0 ;
  do{
   count++;
   System.out.println("请猜：");
   int temp = input.nextInt();
   if(temp<num){
    System.out.println("小了");
    continue;
   }
   if(temp>num){
    System.out.println("大了");
    continue;
   }
   if(temp == num){
    break;
   }
  }while(true);
  System.out.println("总共猜了"+count+"次");
 }

```

#### 循环语句综合例题 无限循环

最简单“无限” 循环格式：

- `while(true)`
- `for(;;)`
- 无限循环存在的原因是并不知道循环多少次，需要根据循环体内部某些条件，来控制循环的结束。

##### 从键盘读入个数不确定的整数，并判断读入的正数和负数的个数，输入为0时结束程序

```
题目：
从键盘读入个数不确定的整数，并判断读入的正数和负数的个数，输入为0时结束程序。
```

```
1.while(true){}
import java.util.Scanner;

public class WhileTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);
                int countPositive = 0;
                int countNegative = 0;

                int i = 1;
                while(true){
                        System.out.print("请输入数字" + i + ":");
                        int num = scan.nextInt();
                        System.out.println();

                        if(num > 0){
                                countPositive += 1;
                        }
                        if(num < 0){
                                countNegative += 1;
                        }
                        if(num == 0){
                                break;
                        }
                        i++;
                }
                System.out.println("负数的个数为：" + countNegative);
                System.out.println("正数的个数为：" + countPositive);
        }
}

2.for(;;){}
import java.util.Scanner;

public class ForTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);

                int countPositive = 0;
                int countNegative = 0;

                for(;;){
                        System.out.print("请输入数字" + i + ": ");
                        int num = scan.nextInt();

                        if(num > 0 ){
                                countPositive += 1;
                        }
                        if(num < 0 ){
                                countNegative += 1;
                        }
                        if(num == 0){
                                break;
                        }
                }
                System.out.println("正数的个数为：" + countPositive);
                System.out.println("负数的个数为：" + countNegative);
        }
}
```

#### 嵌套循环(多重循环)

- 将一个循环放在另一个循环体内，就形成了嵌套循环。其中，for ,while ,do…while均可以作为外层循环或内层循环。
- 实质上，嵌套循环就是把内层循环当成外层循环的循环体。当只有内层循环的循环条件为false时，才会完全跳出内层循环，才可结束外层的当次循环，开始下一次的循环。
- 设外层循环次数为m次，内层为n次，则内层循环体实际上需要执行m*n次。

##### 0) 练习图形

- 内层控制行数
- 外层控制列数

###### 矩形

```
*****
***** 
***** 
***** 
*****
```

```
public class RForTest{
        public static void main(String[] args){
                for(int i = 1; i <= 5; i++){
                        System.out.println();
                        for (int j = 1; j <= 5; j++){
                                System.out.print("*");
                        }
                }
        }
}
```

###### 直角三角形

```
*
**
***
****
*****
```

```
public class RForTest{
        public static void main(String[] args){
                for(int i = 1; i <= 5; i++){
                        System.out.println();
                        for (int j = 1; j <= i; j++){
                                System.out.print("*");
                        }
                }
        }
}
```

###### 倒直角三角形

```
*****
****
***
**
*
```

```
public class RForTest{
        public static void main(String[] args){
                for(int i = 1; i <= 5; i++){
                        System.out.println();
                        for (int j = 1; j <= (5 - i + 1); j++){
                                System.out.print("*");
                        }
                }
        }
}
```

###### 半菱形

```
*
**
***
****
*****
****
***
**
*
```

```
public class RForTest{
        public static void main(String[] args){
                for(int i = 1; i <= 9; i++){
                        System.out.println();
                        if (i <= 5){
                                for (int j = 1; j <= i; j++){
                                        System.out.print("*");
                                }
                        }
                        else{
                                for (int j = 1; j <= (9 - i + 1); j++){
                                        System.out.print("*");
                                }
                        }
                }
        }
}
```

###### 菱形

```
   *
  ***
 *****
  ***
   *
```

```
public class RForTest{
        public static void main(String[] args){
                for(int i = 1; i <= 5; i++){
                        System.out.println();
                        if(i <= 3){
                               for(int j = 1; j <= (6 - i); j++){
                                       System.out.print(" ");
                               }
                               for(int k = 1; k <= (i * 2 - 1); k++){
                                       System.out.print("*");
                               }
                        }
                        else{
                                for(int j = 1; j <= i; j++){
                                        System.out.print(" ");
                                }
                                for(int k = 1; k <= ((5 - i) * 2 + 1); k++){
                                       System.out.print("*");
                                }
                        }
                }
        }
}
```

###### 菱形2

```
**********        
****  ****        
***    ***        
**      **        
*        *        
**      **
***    ***
****  ****
**********
```

```
public class RForTest{
        public static void main(String[] args){
                for(int i = 1; i <= 9; i++){
                        System.out.println();
                        if(i <= 5){
                               for(int j = 1; j <= (6 - i); j++){
                                       System.out.print("*");
                               }
                               for(int k = 1; k <= (i * 2 - 1); k++){
                                       System.out.print(" ");
                               }
                               for(int h = 1; h <= (6 - i); h++){
                                       System.out.print("*");
                               }
                        }
                        else{
                                for(int j = 1; j <= (i - 4); j++){
                                        System.out.print("*");
                                }
                                for(int k = 1; k <= ((9 - i) * 2 + 1); k++){
                                       System.out.print(" ");
                                }
                                for(int h = 1; h <= (i - 4); h++){
                                       System.out.print("*");
                                }
                        }
                }
        }
}
```

###### 心形

```
      ******       ******          
    **********   **********         
  ************* *************  
 *****************************     
 *****************************     
 *****************************    
  ***************************       
    ***********************         
      *******************          
        ***************             
          ***********             
            *******                
              ***                  
               *    
```

```
public class RForTest{
        public static void main(String[] args){
                for(int i = 1; i <= 18; i++){
                        System.out.println();
                        if(i <= 3){
                                for(int j = 1; j <=(10 - i) ; j++){
                                        System.out.print(" ");
                                }
                                for(int k = 1; k <= (4 + 2 * i); k++){
                                        System.out.print("*");
                                }
                                for(int h = 1; h <= (5 - ( i - 1) * 2); h++){
                                        System.out.print(" ");
                                }
                                for(int m = 1; m <= (4 + 2 * i); m++){
                                        System.out.print("*");
                                }
                        }
                        else if( i <= 6 && i >= 3){
                                //int num = 15 + 2 * i;
                                int num = 23;
                                for(int j = 1; j <= 6; j++){
                                        System.out.print(" ");
                                }
                                for(int k = 1; k <= num; k++){
                                        System.out.print("*");
                                }
                        }
                        else{
                                for(int j = 1;j <= (6 + i - 6); j++){
                                        System.out.print(" ");
                                }
                                for(int k = 1;k <= (23 - 2 * (i - 6)); k++){
                                        System.out.print("*");
                                }
                        }

                }
        }
}
```

##### 1）九九乘法表

```
public class RForTest{
        public static void main(String[] args){
                for (int i = 1; i <= 9; i++){
                        for (int j = 1; j <= i; j++){
                                System.out.print( i + "*" + j + "=" + (i * j) + "\t");
                                if(i == j){
                                        System.out.println();
                                }
                        }
                }
        }
}
```

```
public class RForTest{
        public static void main(String[] args){
                for (int i = 1; i <= 9; i++){
                        System.out.println();
                        for (int j = 1; j <= 9; j++){
                                System.out.print( j + "*" + i + "=" + (i * j) + "\t");
                                if(i == j){
                                        break;
                                }
                        }
                }
        }
}
```

##### 2）100以内的所有质数

```
public class RForTest{
        public static void main(String[] args){
                for(int i = 1; i <= 100; i++){
                        int count = 0;
                        for(int j = 1; j <= i; j++){
                                if(i % j == 0){
                                        count++;
                                }
                        }
                        if(count == 2){
                                System.out.println(i);
                        }
                }
        }
}
```

```
class RForTest{
        public static void main(String[] args){                       
 lable:               for(int i = 2; i <= 100; i++){
      //优化2：只对本身是质数的自然数有效
                        for(int j = 2; j <= Math.sqrt(i); j++){
                                if(i % j == 0){
                                    continue lable;
                                }
                        }
                        System.out.println(i);
                }
        }
}
```

```
public class RForTest{
        public static void main(String[] args){
                for(int i = 2; i <= 100; i++){
                        boolean isFlag = true;
                        for(int j = 2; j < i; j++){
                                if(i % j == 0){
                                        isFlag = false;
                                }
                        }
                        if(isFlag){
                                System.out.println(i);
                        }
                }
        }
}
```

###### 优化 100000 以内

```
class RForTest{
        public static void main(String[] args){
                boolean isFlag = true; //标识i是否被j除尽               
    int count = 0; //记录质数的个数
                //获取当前时间距离1970-01-01 00:00:00 的毫秒数
                long start = System.currentTimeMillis();
                
                for(int i = 2; i <= 100000; i++){
      //优化2：只对本身是质数的自然数有效
                        for(int j = 2; j <= Math.sqrt(i); j++){

                                if(i % j == 0){
                                        isFlag = false;
                                        break;  //优化1：只对本身非质数的自然数有效
                                }
                        }

                        if(isFlag){
                                //System.out.println(i);
        count++;   //优化3：
                        }
      //重置isflag
                        isFlag = true;
                }
                //获取当前时间距离1970-01-01 00:00:00 的毫秒数
                long end = System.currentTimeMillis();
                System.out.println("质数的个数: " + count);
                System.out.println("所花费的时间是：" + (start - end));
        }
}
```

#### 特殊关键字的使用

- break
- continue

##### break 语句

- break语句用于终止某个语句块的执行,跳出最近的一个循环体

```
{   ……
    break;
    ……
}
```

###### 带label的break

```
int students[][] = {{10, 12, 11}, {8, 9, 1}, {9, 15, 7}};
jumpOutHere:
for (int i = 0; i < 3; i++) {
    System.out.println(String.format("start outer for loop index %d", i));
    int j = 0;
    while (j < 3) {
        System.out.println(String.format("current retrieve value %d", students[i][j]));
        if (students[i][j] == 9) {
            break jumpOutHere;
        } else {
            j++;
        }
    }
    System.out.println(String.format("end outer for loop index %d", i));
}
```

- break语句出现在多层嵌套的语句块中时，可以通过标签指明要终止的是哪一层语句块

```
label1: { ……
label2:     { ……
label3:         { ……
                break label2;
                ……
                }
            }
        }
```

###### 输入密码的次数

```
import java.util.Scanner;

class WhileTest{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);
label1:
    while (true)
    {
     System.out.println("请输入账号: 输入0退出");
     int id = scan.nextInt();

     if (id == 1001)
     {
      System.out.println("当前账号为: " + id);
      for(int i = 1; i <= 3; i++){
       System.out.println("请输入密码:");
       int code = scan.nextInt();
       if(code == 1234){
         System.out.println("登录成功");
         break label1;
          }
       else{
         System.out.println("登录失败");
         i++;
         System.out.println("还有" + ( 3 - i) + "次机会");
       }
      }
     }
     else if (id == 1002)
     {
      System.out.println("当前账号为: " + id);
      for(int i = 1; i <= 3; i++){
       System.out.println("请输入密码:");
       int code = scan.nextInt();
       if(code == 1234){
         System.out.println("登录成功");
         break label1;
          }
       else{
         System.out.println("登录失败");
         i++;
         System.out.println("还有" + ( 3 - i) + "次机会");
       }
      }
     }
     else if (id == 0)
     {
      System.out.println("已推出!");
      break label1;
     }
     else{
      System.out.println("无此账号");
     }
    }

        }
}
```

##### continue 语句

- continue只能使用在循环结构中
- continue语句用于跳过其所在循环语句块的一次执行，继续下一次循环
- continue语句出现在多层嵌套的循环语句体中时，可以通过标签指明要跳过的是哪一层循环

##### 带标签的continue

###### 在一个字符串中搜索另一个字符串
```
public class Test005 {
	public static void main(String args[]) {
		StringBuffer searchMe = new StringBuffer(
				"peter piper picked a peck of pickled peppers"
				);
		String subString = "pick";
		boolean foundIt = false;
		int max = searchMe.length() - subString.length();
		
		test:
			for(int i = 0; i <= max; i++) {
				int n = subString.length();
				int j = i;
				int k = 0;
				
				while(n-- != 0) {
					if(searchMe.charAt(j++) != subString.charAt(k++)) {
						continue test;
					}
				}
				foundIt = true;
			}
		System.out.println(foundIt?"found":"no");
	}
}

```
###### 打印1-100之间非13的倍数，使用continue语句
```  
public class ForTest {
	public static void main(String[] args) {
		for(int i = 1; i < 100; i++) {
			if(i % 13 != 0 || i == 13) {
				continue;
			}
			System.out.println(i);
		}
	}
}
```

###### 在字符串缓冲区中寻找指定字符并进行处理
```
对一个字符串缓冲区中的字符逐一检查。
如果当前字符不是字母p，则continue语句跳过循环的其余语句并开始处理下一个字符；
如果是字母p，则程序将计数器加1并把p转换为大写
```

```
public class Test007 {
	public static void main(String args[]) {
		StringBuffer search = new StringBuffer(
				"peter piper picked this"
				);
		
		Test007 test = new Test007();
		test.checkString(search);  //引用类型 地址值传递
		System.out.println(search);
	}
	
	public void checkString(StringBuffer str) {
		int times = 0;
		
		for(int i = 0; i < str.length(); i++) {
			
			if(str.charAt(i) == 'p') {
				times++;
				str.setCharAt(i,'P');
			}
		}
	}
}
```


##### return 特殊流程控制语句3

- return：并非专门用于结束循环的，它的功能是结束一个方法。
- 当一个方法执行到一个return语句时，这个方法将被结束。
- 与break和continue不同的是，return直接结束整个方法，不管这个return处于多少层循环之内

##### 特殊流程控制语句说明

- break只能用于switch语句和循环语句中。
- continue 只能用于循环语句中。
  - 二者功能类似，但continue是终止本次循环，break是终止本层循环。
- break、continue之后不能有其他的语句，因为程序永远不会执行其后的语句。
- 标号语句必须紧接在循环的头部。标号语句不能用在非循环语句的前面。
- 很多语言都有goto语句，goto语句可以随意将控制转移到程序中的任意一条语句上，然后执行它。但使程序容易出错。Java的break和continue是不同于goto的。

***

### 项目一：家庭收支记账软件

#### 需求说明

```
• 模拟实现基于文本界面的《家庭记账软件》。
• 该软件能够记录家庭的收入、支出，并能够打印收支明细表。
• 项目采用分级菜单方式。主菜单如下：
-----------------家庭收支记账软件-----------------
                1 收支明细
                2 登记收入
                3 登记支出
                4 退 出
                请选择(1-4)：_
```

```
• 假设家庭起始的生活基本金为10000元。
• 每次登记收入（菜单2）后，收入的金额应累加到基本金上，并记录本次收入明细，以便后续的查询。
• 每次登记支出（菜单3）后，支出的金额应从基本金中扣除，并记录本次支出明细，以便后续的查询。
• 查询收支明细（ 菜单1）时，将显示所有的收入、支出名细列表
```

```
• “登记收入”的界面及操作过程如下所示：
-----------------家庭收支记账软件-----------------
                1 收支明细
                2 登记收入
                3 登记支出
                4 退 出
                请选择(1-4)：2
                本次收入金额：1000
                本次收入说明：劳务费_
```

```
• “登记支出”的界面及操作过程如下所示：
-----------------家庭收支记账软件-----------------
                1 收支明细
                2 登记收入
                3 登记支出
                4 退 出
                请选择(1-4)：3
                本次支出金额：800
                本次支出说明：物业费_
```

```
• “收支明细”的界面及操作过程如下所示：
-----------------家庭收支记账软件-----------------
                1 收支明细
                2 登记收入
                3 登记支出
                4 退 出
                请选择(1-4)：1
-----------------当前收支明细记录-----------------
收支     账户金额     收支金额     说 明
收入     11000        1000       劳务费
支出     10200        800        物业费
--------------------------------------------------
• 提示：明细表格的对齐，可以简单使用制表符‘\t’来实现
```

```
• “退 出”的界面及操作过程如下所示：
-----------------家庭收支记账软件-----------------
                1 收支明细
                2 登记收入
                3 登记支出
                4 退 出
                请选择(1-4)：4
                确认是否退出(Y/N)：_
```

#### 基本金和收支明细的记录

```
• 基本金的记录可以使用int类型的变量来实现：
  int balance = 10000;
• 收支明细记录可以使用Sting类型的变量来实现，其初始值为明细表的表头。例如：
  String details = "收支\t账户金额\t收支金额\t说 明\n";
• 在登记收支时，将收支金额与balance相加或相减，收支记录直接串接到details后面即可
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/391195310239093.png =764x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/526725310226960.png =747x)

#### 键盘访问的实现

```
• 项目中提供了Utility.java类，可用来方便地实现键盘访问。
• 该类提供了以下静态方法：
 public static char readMenuSelection() ：该方法读取键盘，如果用户键入’1’-’4’中的任意字符，则方法返回。返回值为用户键入字符。
 public static int readNumber() ：该方法从键盘读取一个不超过4位长度的整数，并将其作为方法的返回值。
 public static String readString() ：该方法从键盘读取一个不超过8位长度的字符串，并将其作为方法的返回值。
 public static char readConfirmSelection() ：该方法从键盘读取‘Y’或’N’，并将其作为方法的返回值。
```

#### 第1步 — 实现主程序结构

1. 创建FamilyAccount类及main方法
2. 在main方法中，参照主流程图，实现程序主体结构
3. 测试程序，确认可以正常执行第1和第4菜单选项

#### 第2步 — 实现收入和支出登记处理

1. 在main方法中，参照收入和支出流程，实现“登记收入”功能
2. 测试“登记收入”功能
3. 在main方法中，参照收入和支出流程，实现“登记支出”功能
4. 测试“登记支出”功能

#### 方案0

##### Utility 工具类

```
import java.util.Scanner;
/**
Utility工具类：
将不同的功能封装为方法，就是可以直接通过调用方法使用它的功能，而无需考虑具体的功能实现细节。
*/
public class Utility {
    private static Scanner scanner = new Scanner(System.in);
    /**
 用于界面菜单的选择。该方法读取键盘，如果用户键入’1’-’4’中的任意字符，则方法返回。返回值为用户键入字符。
 */
 public static char readMenuSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1);
            c = str.charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4') {
                System.out.print("选择错误，请重新输入：");
            } else break;
        }
        return c;
    }
 /**
 用于收入和支出金额的输入。该方法从键盘读取一个不超过4位长度的整数，并将其作为方法的返回值。
 */
    public static int readNumber() {
        int n;
        for (; ; ) {
            String str = readKeyBoard(4);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("数字输入错误，请重新输入：");
            }
        }
        return n;
    }
 /**
 用于收入和支出说明的输入。该方法从键盘读取一个不超过8位长度的字符串，并将其作为方法的返回值。
 */
    public static String readString() {
        String str = readKeyBoard(8);
        return str;
    }
 
 /**
 用于确认选择的输入。该方法从键盘读取‘Y’或’N’，并将其作为方法的返回值。
 */
    public static char readConfirmSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("选择错误，请重新输入：");
            }
        }
        return c;
    }
 
 
    private static String readKeyBoard(int limit) {
        String line = "";

        while (scanner.hasNext()) {
            line = scanner.nextLine();
            if (line.length() < 1 || line.length() > limit) {
                System.out.print("输入长度（不大于" + limit + "）错误，请重新输入：");
                continue;
            }
            break;
        }

        return line;
    }
}
```

##### 实现

```
class FamilyAccount{

 public static void main(String[] args){

  boolean isFlag = true;
  //用于记录用户的收入支出情况
  String details = "收支\t账户金额\t收支金额\t说   明\n";
  //初始金额
  int balance = 10000;

  while (isFlag){
   System.out.println("---------------------家庭收支记账软件-----------------\n");
   System.out.println("                       1 收支明细");
   System.out.println("                       2 登记收入");
   System.out.println("                       3 登记支出");
   System.out.println("                       4 退    出");
   System.out.print("                      请选择(1-4): ");
   //获取用户的选择
   char selection = Utility.readMenuSelection();
   switch (selection){
    case '1':
     System.out.println("---------------------1 收支明细-----------------");
     System.out.println(details);
     System.out.println("------------------------------------------------");
     break;
    case '2':
     System.out.print("本次收入金额：");
     int addMoney = Utility.readNumber();
     System.out.print("本次收入说明：");
     String addInfo = Utility.readString();
     System.out.println("----------------------登记完成------------------");
     //处理balance
     balance += addMoney;

     //处理details
     details += ("收入\t" + balance + "\t\t" + addMoney + "\t\t" + addInfo + "\n");
     break;
    case '3':
     System.out.print("本次支出金额：");
     int minusMoney = Utility.readNumber();
     System.out.print("本次支出说明：");
     String minusInfo = Utility.readString();
     System.out.println("----------------------登记完成------------------");
     //处理balance
     if(balance >= minusMoney){
      balance -= minusMoney;
      //处理details
      details += ("支出\t" + balance + "\t\t" + minusMoney + "\t\t" + minusInfo + "\n");
     }
     else{
      System.out.println("超出金额,支付失败");
     }
     break;
    case '4':
     System.out.println("4 退    出");
     System.out.println("确认是否退出(Y/N)");
     char isExit = Utility.readConfirmSelection();
     if(isExit == 'Y'){
      isFlag = false;
     }
     break;
   }
  }
 }
}
```

#### 方案1 我的初步

```
import java.util.Scanner;

class Project1{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);
    
    int income = 0;  //收入  
    int outcome = 0; //支出
    int inCount = 0; //记录收入的次数
    int outCount = 0; //记录支出的次数
    String trucationIn = "";
    String trucationOut = "";
    int basic = 10000;
    
    while(true){

     System.out.println("---------家庭收支记账软件----------");
     System.out.println("\t1 收支明细");
     System.out.println("\t2 登记收入");
     System.out.println("\t3 登记支出");
     System.out.println("\t4 退出");
     System.out.print("\t请选择(1-4)：");

     int choice = scan.nextInt();
     System.out.println();

     if(choice == 1){
       System.out.println("---------当前收支明细记录----------");
       System.out.println("收支\t账户金额\t收支金额\t说明");

        System.out.println("收入\t" + basic + "\t" + income + "\t" + trucationIn);
        System.out.println("支出\t" + basic + "\t" + outcome + "\t" + trucationOut);
     }
     else if(choice == 2){  //登记收入
       System.out.print("\t本次收入金额：");
       int tempIncome = scan.nextInt();
       System.out.println();
       
       System.out.print("\t本次收入说明：");
          trucationIn = scan.next();
       System.out.println();
       
       basic += tempIncome;
       income = tempIncome;
       //inCount++;
     }
     else if(choice == 3){  //登记支出
       System.out.print("\t本次支出金额：");
       int tempOutcome = scan.nextInt();
       System.out.println();
       
       System.out.print("\t本次支出说明：");
       trucationOut = scan.next();
       System.out.println();

       basic -= tempOutcome;
       outcome = tempOutcome;
       //outCount++;
     }
     else if(choice == 4){  //退出
      System.out.println("是否确认退出:(1/0)");
      int outChoice = scan.nextInt();
      if(outChoice == 1){
       break;
      }
      else{
       continue;
      }
     }
     else{  //报错
       System.out.println("输入选项不正确!请重新输入!");
     }
    }
        }
}
```

#### 方案2 添加记录查询功能 for

```
import java.util.Scanner;

class Project1{
        public static void main(String[] args){
                Scanner scan = new Scanner(System.in);
    
    int income = 0;  //收入  
    int outcome = 0; //支出
    int inCount = 0; //记录收入的次数
    int outCount = 0; //记录支出的次数
    String trucationIn = "";
    String trucationOut = "";
    int basic = 10000;
    
    while(true){

     System.out.println("---------家庭收支记账软件----------");
     System.out.println("\t1 收支明细");
     System.out.println("\t2 登记收入");
     System.out.println("\t3 登记支出");
     System.out.println("\t4 退出");
     System.out.print("\t请选择(1-4)：");

     int choice = scan.nextInt();
     System.out.println();

     if(choice == 1){
       System.out.println("---------当前收支明细记录----------");
       System.out.println("收支\t账户金额\t收支金额\t说明");
       /*
       for (int i = 1; i <= inCount; i++)  //遍历每一次收入及其说明
       {
        System.out.println("收入\t" + basic + "\t" + income + "\t" + trucationIn);
       }
       
       for (int i = 1; i <= outCount; i++)  //遍历每一次支出及其说明
       {
        System.out.println("支出\t" + basic + "\t" + outcome + "\t" + trucationOut);
       }
       */
        System.out.println("收入\t" + basic + "\t" + income + "\t" + trucationIn);
        System.out.println("支出\t" + basic + "\t" + outcome + "\t" + trucationOut);
     }
     else if(choice == 2){  //登记收入
       System.out.print("\t本次收入金额：");
       int tempIncome = scan.nextInt();
       System.out.println();
       
       System.out.print("\t本次收入说明：");
          trucationIn = scan.next();
       System.out.println();
       
       basic += tempIncome;
       income = tempIncome;
       //inCount++;
     }
     else if(choice == 3){  //登记支出
       System.out.print("\t本次支出金额：");
       int tempOutcome = scan.nextInt();
       System.out.println();
       
       System.out.print("\t本次支出说明：");
       trucationOut = scan.next();
       System.out.println();

       basic -= tempOutcome;
       outcome = tempOutcome;
       //outCount++;
     }
     else if(choice == 4){  //退出
      System.out.println("是否确认退出:(1/0)");
      int outChoice = scan.nextInt();
      if(outChoice == 1){
       break;
      }
      else{
       continue;
      }
     }
     else{  //报错
       System.out.println("输入选项不正确!请重新输入!");
     }
    }
        }
}
```

***

## 衡量一个功能代码的优劣

1. 正确性
2. 可读性
3. 健壮性
4. 高效率与低存储：时间复杂度/空间复杂度 （衡量算法的好坏）

# 数组

数组(Array)，是多个相同类型数据按一定顺序排列的集合，并使用一个名字命名，并通过编号的方式对这些数据进行统一管理。

数组的常见概念

- 数组名
- 下标(或索引)
- 元素
- 数组的长度
-
- 数组本身是引用数据类型，而数组中的元素可以是任何数据类型，包括基本数据类型和引用数据类型。
- 创建数组对象会在内存中开辟一整块连续的空间，而数组名中引用的是这块连续空间的首地址。
- 数组的长度一旦确定，就不能修改。
- 我们可以直接通过下标(或索引)的方式调用指定位置的元素，速度很快。
-
- 数组的分类：
- 按照维度：
  - 一维数组、
  - 二维数组、
  - 三维数组、
  - …
- 按照元素的数据类型分：
  - 基本数据类型元素的数组、
  - 引用数据类型元素的数组(即对象数组)

## 一维数组

### 一维数组的声明方式

```
type var[] 或 type[] var；
例如：
int a[];
int[] a1;
double b[];
String[] c; //引用类型变量数组
```

Java语言中声明数组时不能指定其长度(数组中元素的数)， 例如： `int a[5];`//非法

### 初始化
```
public class ArrayTest2 {
	
	public static void main(String[] args) {
		
		//一维数组
		//1.1 静态初始化
		int[] ids;
		ids = new int[] {1001,1002,1003,1004};
		//1.2动态初始化
		String[] names = new String[5];
		
		//调用
		//数组索引调用 0开始，-1结尾
		names[0] = "test0";
		names[1] = "test1";
		names[2] = "test2";
		names[3] = "test3";
		names[4] = "test4";
		
		//names[5] = "周扬"; 
		//超过范围
		
		//获取数组长度  .length
		System.out.println(names.length);
		System.out.println(ids.length);
		
		//遍历数组
		for(int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}
}

```
#### 动态初始化：数组声明且为数组元素分配空间与赋值的操作分开进行

```
int[] arr = new int[3];
arr[0] = 3;
arr[1] = 9;
arr[2] = 8;
```

```
String names[];
names = new String[3];
names[0] = “钱学森”;
names[1] = “邓稼先”;
names[2] = “袁隆平”;
```

#### 静态初始化：在定义数组的同时就为数组元素分配空间并赋值

```
int arr[] = new int[]{ 3, 9, 8};
或
int[] arr = {3,9,8};
```

```
String names[] = {
“李四光”,“茅以升”,“华罗庚”
}
```

#### 数组元素的引用

- 定义并用运算符new为之分配空间后，才可以引用数组中的每个元素；
- 数组元素的引用方式：数组名[数组元素下标]
- 数组元素下标可以是整型常量或整型表达式。如`a[3] , b[i] , c[6*i];`
- 数组元素下标从0开始；长度为n的数组合法下标取值范围: `0 —>n-1`；
  - 如`int a[]=new int[3];` 可引用的数组元素为a[0]、a[1]、a[2]
- 每个数组都有一个属性length指明它的长度，例如：a.length 指明数组a的长度(元素个数)
- 数组一旦初始化，其长度是不可变的

#### 数组元素的默认初始化值

数组是引用类型，它的元素相当于类的成员变量，因此数组一经分配空间，其中的每个元素也被按照成员变量同样的方式被隐式初始化。例如：

- 对于基本数据类型而言，默认初始化值各有不同
- 对于引用数据类型而言，默认初始化值为null(注意与0不同！)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/564975123220667.png =598x)

```
public class Test {
    public static void main(String args[]){
        int a[]= new int[5];
        System.out.println(a[3]); //a[3]的默认值为0
    }
}
```
```
public class ArrayTest2 {
	
	public static void main(String[] args) {
		
		int[] arr = new int[4];
		
		for(int i = 0; i < arr.length; i++) {
				System.out.println(arr[i]);
		}
		System.out.println("**********");
		
		short[] arr1 = new short[4];
		for(int i =0; i < arr1.length; i++) {
			System.out.println(arr1[i]);
		}
		System.out.println("**********");
		
		float[] arr2 = new float[4];
		for(int i = 0; i < arr2.length; i++) {
			System.out.println(arr2[i]);
		}
		System.out.println("**********");
		
		char[] arr3 = new char[4];
		for(int i = 0; i < arr3.length; i++) {
			System.out.println(arr3[i]);
		}
		if(arr3[0] == 0) {
			System.out.println("== 0");
		}
		System.out.println("**********");
		
		boolean[] arr4 = new boolean[4];
		for(int i = 0; i < arr4.length; i++) {
			System.out.println(arr4[i]);
		}
		System.out.println("**********");
		
		String[] arr5 = new String[4];
		for(int i = 0; i < arr5.length; i++) {
			System.out.println(arr[i]);
		}
		if(arr5[0] == null) {
			System.out.println("null");
		}
		System.out.println("**********");
	}
}
```
#### 创建基本数据类型数组
##### 创建基本数据类型数组 (1)

Java中使用关键字new来创建数组
如下是创建基本数据类型元素的一维数组

```
public class Test{
    public static void main(String args[]){
        int[] s;
        s = new int[10];
        for ( int i=0; i<10; i++ ) {
            s[i] =2*i+1;
            System.out.println(s[i]);
        }
    }
}
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/531785323239093.png =296x)

##### 创建基本数据类型数组 (2)

```
public class Test{
    public static void main(String args[]){
        int[] s;
        s = new int[10];
        //int[] s=new int[10];
        //基本数据类型数组在显式赋值之前，
        //Java会自动给他们赋默认值。
        for ( int i=0; i<10; i++ ) {
            s[i] =2*i+1;
            System.out.println(s[i]);
        }
    }
}
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/397325523247126.png =330x)

##### 创建基本数据类型数组 (3)

```
public class Test{
    public static void main(String args[]){
        int[] s;
        s = new int[10];
        for ( int i=0; i<10; i++ ) {
            s[i] =2*i+1;
            System.out.println(s[i]);
        }
    }
}
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/39505523226960.png =294x)


#### 一维数组的内存解析

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/170022423239096.png =507x)

```
int[] arr = new int[4]
arr[1] = 10;

arr = new int[3]
//新的内存空间
arr[1] == 0
```
#### 练习1用索引读数组

```        
升景坊单间短期出租4个月，550元/月（水电煤公摊，网费35元/月），空调、卫生间、厨房齐全。
屋内均是IT行业人士，喜欢安静。所以要求来租者最好是同行或者刚毕业的年轻人，爱干净、安静。
```
```
package com.atguigu.java;

public class ArrayTest3 {
	public static void main(String[] args) {
		int[] arr = new int[] {8,2,1,0,3};
		int[] index = new int[] {2,0,3,2,4,0,1,3,2,3,3};
		String tel = "";
		for(int i = 0; i < index.length; i++) {
			tel += arr[index[i]];
		}
		System.out.println("connection:" + tel);
	}
}

//18013820100
```

#### 练习2从键盘读入学生成绩，找出最高分，并输出学生成绩等级。
```
2. 从键盘读入学生成绩，找出最高分，并输出学生成绩等级。
成绩>=最高分-10 等级为’A’ 
成绩>=最高分-20 等级为’B’
成绩>=最高分-30 等级为’C’ 
其余 等级为’D’
提示：先读入学生人数，根据人数创建int数组，存放学生成绩。
```
```
import java.util.Scanner;

public class ArrayStudentTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("student number:")
		int students = scan.nextInt();
		int[] arr = new int[students];
		int max = 0;
		
		for(int i = 0; i < students;i++) {
			System.out.print("student" + i + "\'s " + "score: ");
			int num = scan.nextInt();
			System.out.print(num);
			arr[i] = num;
			if(num < 0) {
				break;
			}
			if(i == 0) {
				max = arr[i];
			}
			else {
				if(arr[i - 1] < arr[i]) {
					max = arr[i];
				}
			}
			System.out.println();
		}
		System.out.println("max score is :" + max);
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] >= (max - 10)) {
				System.out.println("student" + i + "is " + arr[i] + " grade" + ":A");
			}
			else if(arr[i] >= (max - 20)) {
				System.out.println("student" + i + "is " + arr[i] + " grade" + ":B");
			}
			else if(arr[i] >= (max - 30)) {
				System.out.println("student" + i + "is " + arr[i] + " grade" + ":C");
			}
			else {
				System.out.println("student" + i + "is " + arr[i] + " grade" + ":D");
			}
		}
	}
}
```
```
import java.util.Scanner;

public class ArrayStudentTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//学生数量
		System.out.print("student number is: ");
		int stuNum = scan.nextInt();
		System.out.println();
		
		//每个学生的成绩录入
		int[] scores = new int[stuNum];
		for(int i = 0; i < stuNum; i++) {
			System.out.print("student" + i + "\'s" + "score is");
			scores[i] = scan.nextInt();
			System.out.println(scores[i]);
		}
		
		//最大值
		int max = 0;
		for(int i = 0; i < scores.length - 1; i++) {
			if(scores[i] > scores[i + 1]) {
				max = scores[i];
			}
		}
		System.out.println("max scores is " + max);
		
		//成绩评价
		for(int i = 0; i < scores.length; i++) {
			System.out.print("student" + i + "\'s grade is ");
			char level = ' ';
			if(scores[i] >= (max - 10)) {
				level = 'A';
			}
			else if(scores[i] >= (max -20)) {
				level = 'B';
			}
			else if(scores[i] >= (max -30)) {
				level = 'C';
			}
			else {
				level = 'D';
			}
			System.out.println(level);
		}	
	}
}
```
## 二维数组[][]：数组中的数组
 二维数组就相当于是一个表格

```
public class ArrayTest02 {
	public static void main(String[] args) {
		int[][] arr1 = new int[][] {{1,2,3},{4,5},{6,7,8}};
		
		String[][] arr2 = new String[3][2];
		
		String[][] arr3 = new String[3][];
	}
}
```
```
public class ArrayTest03 {
	public static void main(String[] args) {
		int[][] arr = new int[4][3];
		
		System.out.println(arr[0]);  //[I@36baf30c  [ 一维数组  地址值
		System.out.println(arr[0][0]);  //0 默认值
		
		System.out.println(arr);  //[[I@7a81197d  [[ 二维数组  地址值
		
		String[][] arr2 = new String[4][3];
		System.out.println(arr2);  //[[Ljava.lang.String;@5ca881b5 二维数组地址 包括String包的地址
		System.out.println(arr[0]);  //[I@36baf30c 一维数组地址
		System.out.println(arr[0][0]); //null 空
		}
}
```
### 格式1（动态初始化）：int[][] arr = new int[3][2];
- 定义了名称为arr的二维数组 
- 二维数组中有3个一维数组
- 每一个一维数组中有2个元素
- 一维数组的名称分别为arr[0], arr[1], arr[2]
- 给第一个一维数组1脚标位赋值为78写法是：arr[0][1] = 78;

其中
- 外层元素的初始化值为：地址值
- 内层元素的初始化值为：与一维数组的初始化情况相同
### 格式2（动态初始化）：int[][] arr = new int[3][];
- 二维数组中有3个一维数组。
- 每个一维数组都是默认初始化值null (注意：区别于格式1）
- 可以对这个三个一维数组分别进行初始化
- arr[0] = new int[3]; arr[1] = new int[1]; arr[2] = new int[2];
- 注：
    - int[][]arr = new int[][3]; //非法

其中
- 外层元素的初始化值为：null
- 内层元素的初始化值为：不能调用，否则出错
### 格式3（静态初始化）：int[][] arr = new int[][]{{3,8,2},{2,7},{9,0,1,6}};
- 定义一个名称为arr的二维数组，二维数组中有三个一维数组
- 每一个一维数组中具体元素也都已初始化
   - 第一个一维数组 arr[0] = {3,8,2};
   - 第二个一维数组 arr[1] = {2,7};
   - 第三个一维数组 arr[2] = {9,0,1,6};
   - 第三个一维数组的长度表示方式：arr[2].length;
- 注意特殊写法情况：int[] x,y[]; x是一维数组，y是二维数组。
- Java中多维数组不必都是规则矩阵形式

### 获取数组的长度 行数
```
int[][] numTable = new int[][] {{3,5,8},{12,9},{7,0,6,4}};
numTable.length == 3
```
### 遍历二维数组
初始化方式为：`int[][] arr = new arr[][]{{},{}}`

若遍历的位置`arr[i][j]`无相应的数值，则报错
```
public class ArrayTest02 {
	public static void main(String[] args) {

		int[][] numTable = new int[][] {{1,2,3},{4,5,9,10},{6,7,8}};
		
		for(int i = 0; i < numTable.length; i++) {
		
			for(int j = 0; j < numTable[i].length; j++) {
				System.out.print(numTable[i][j]);
			}
			
			System.out.println();
		}
	}
}
```
### 练习 2 获取arr数组中所有元素的和。
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/50595520220671.png =288x)

```
public class ArrayTest02 {
	public static void main(String[] args) {
		int[][] numTable = new int[][] {{3,5,8},{12,9},{7,0,6,4}};

		for(int i = 0; i < numTable.length; i++) {
			
			for(int j = 0; j < numTable[i].length; j++) {
				System.out.print(numTable[i][j]);
			}
			
			System.out.println();
		}
	}
}
```

### 练习3
```
声明：int[] x,y[]; 在给x,y变量赋值以后，以下选项允许通过编译的是：
a ) x[0] = y; no
b) y[0] = x; yes
c) y[0][0] = x; no
d) x[0][0] = y; no
e) y[0][0] = x[0]; yes
f) x = y; no
提示：
一维数组：int[] x 或者int x[] 
二维数组：int[][] y 或者 int[] y[] 或者 int y[][]
```
### 练习4 使用二维数组打印一个 10 行杨辉三角。

```
【提示】
1. 第一行有 1 个元素, 第 n 行有 n 个元素
2. 每一行的第一个元素和最后一个元素都是 1
3. 从第三行开始, 对于非第一个元素和最后一个元素的元素。即：
yanghui[i][j] = yanghui[i-1][j-1] + yanghui[i-1][j]
```
```
public class ArrayTest{
        public static void main(String[] args){

                int[][] yangHui = new int[10][10];

                for(int i = 0; i < yangHui.length; i++){
                        for(int j = 0; j <= i; j++){
                                yangHui[i][0] = 1;
                                yangHui[i][i] = 1;
                                if(i >= 2 && j > 0 && j < i){
                                        yangHui[i][j] = yangHui[i - 1][j - 1] + yangHui[i - 1][j];
                                }
                        }
                }

                for(int i = 0; i < yangHui.length; i++){
                        for(int j = 0; j <= i; j++){
                                System.out.print(yangHui[i][j] + " , ");
                        }
                        System.out.println();
                }
        }
}
```
```
public class test002 {
	public static void main(String[] args) {
		int[][] yanghui = new int[10][];
		
		for(int i = 0; i < yanghui.length; i++) {
			yanghui[i]= new int [i + 1];
			yanghui[i][0] = yanghui[i][i] = 1;
			if(i > 1) {
				for(int j = 1; j < yanghui[i].length - 1; j ++) {
					yanghui[i][j] = yanghui[i-1][j-1] + yanghui[i-1][j];
				}
			}
		}
		
		for(int i = 0; i < yanghui.length; i++) {
			System.out.println();
			for(int j = 0; j < yanghui[i].length; j++) {
				System.out.print(yanghui[i][j] + "\t");
			}
		}
	}
}
```
### 【拓展之笔试题】创建一个长度为6的int型数组，要求数组元素的值都在1-30之间，且是随机赋值。同时，要求元素的值各不相同。
```
```
***

## 数组中涉及到的常见算法
1. 数组元素的赋值(杨辉三角、回形数等)
2. 求数值型数组中元素的最大值、最小值、平均数、总和等
3. 数组的复制、反转、查找(线性查找、二分法查找)
4. 数组元素的排序算法

### 练习5 随机数
```
定义一个int型的一维数组，包含10个元素，分别赋一些随机整数，
然后求出所有元素的最大值，最小值，和值，平均值，并输出出来。
要求：所有随机数都是两位数。
提示；
[0,1) * 90 [0,90) + 10  [10,100) [10,99]
(int)(Math.random() * 90 + 10)
```
```
public class Test002 {
	public static void main(String args[]) {
		int[] arr = new int[10];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * (99 - 10 + 1) + 10);
		}
		
		//最大值
		int maxValue = arr[0];
		for(int i = 0; i < arr.length; i++) {
			if(maxValue < arr[i]) {
				maxValue = arr[i];
			}
		}
		System.out.println("最大值: " + maxValue);
		
		//最小值
		int minValue = arr[0];
		for(int i = 0; i < arr.length; i++) {
			if(minValue > arr[i]) {
				minValue = arr[i];
			}
		}
		System.out.println("最大值: " + minValue);
		
		//和值
		int sumValue = 0;
		for(int i = 0; i < arr.length; i++) {
			sumValue += arr[i];
		}
		System.out.println("和值: " + sumValue);
		
		//均值
		float avgValue = sumValue / (float)(arr.length);
		System.out.println("均值: " + avgValue);
	}
}
```
***
### 练习 6 数组复制 System.arraycopy()
```
使用简单数组
(1)创建一个名为ArrayTest的类，在main()方法中声明array1和array2两个变量，
他们是int[]类型的数组。
(2)使用大括号{}，把array1初始化为8个素数：2,3,5,7,11,13,17,19。
(3)显示array1的内容。
(4)赋值array2变量等于array1，修改array2中的偶索引元素，使其等于索引值
(如array[0]=0,array[2]=2)。打印出array1。
思考：array1和array2是什么关系？
拓展：修改题目，实现array2对array1数组的复制
```
```
public class Test003 {
	public static void main(String args[]) {
		int[] array1 = {2,3,5,7,11,13,17,19};
		
		for(int i = 0; i < array1.length; i++) {
			System.out.println(array1[i]);
		}  
		
		int[] array2 = new int[array1.length];
		System.arraycopy(array1, 0, array2, 0, array1.length);
		
////		不是数组的复制 类似于快捷方式的创建
//		int[] array2;
//		array2 = array1;
		
//		for(int i = 0; i < array1.length; i++) {
//			array2[i] = array1[i];
//		}
		
		for(int i = 0; i < array2.length; i++) {
			if(i % 2 == 0) {
				array2[i] = i;
			}
			
			System.out.println(array2[i]);
		}
	}
}}
}
```
***
### 数组的复制，反转，查找(线性查找，二分法查找)
```
public class Test004 {
	
	public static void main(String args[]) {
		
		String[] arr = new String[] {"JJ", "DD", "MM" , "BB","AA" }; 
		
		System.out.print("原值" + "\t");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
		
//		数组的复制
		String[] arr1 = new String[arr.length];
		for(int i = 0; i < arr.length; i++) {
			arr1[i] = arr[i];
		}
		
//		数组的反转
//		1.
		String[] arr2 = new String[arr.length];
		for(int i = 0; i < arr.length; i++) {
			arr2[arr.length - i - 1] = arr[i];
		}
//		2.
		for(int i = 0; i < arr.length / 2; i++) {
			String temp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = temp;
		}
//		3.
		for(int i = 0, j = arr1.length - 1; i < j; i++, j--) {
			String temp = arr1[i];
			arr1[i] = arr1[j];
			arr1[j] = temp;
		}
		
		System.out.print("复制" + "\t");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr1[i] + "\t");
		}
		System.out.println();
		
		System.out.print("反转1" + "\t");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr2[i] + "\t");
		}
		System.out.println();
		
		System.out.print("反转2" + "\t");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
		
		System.out.print("反转3" + "\t");
		for(int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + "\t");
		}
		System.out.println();
		
//		数组的查找
//		1.1线性查找
		String dest = "BB";
		boolean isFlag = true;
		
		for(int i = 0; i < arr.length; i++) {
			if(dest.equals(arr[i])) {
				System.out.println("找到指定元素的位置为：" + i);
				isFlag = false;
				break;
			}
		}
		
		if(isFlag) {
			System.out.println("查询不到");
		}
		
//      1.2
		
        for(int i = 0; i < arr.length; i++) {
			if(dest == arr[i]) {
				System.out.println("已找到：" + i);
				break;
			}
			if( i == arr.length - 1) {
				System.out.println("未找到");
			}
		}
		

//		2.二分法查找 
//		前提：所要查找的数组必须有序
		int[] arr02 = new int[] {-98,-34,2,34,54,66,79,105,210,333};
		
		int dest1 = 2;
		int head = 0; 
		int end = arr02.length - 1;
		boolean isFlag02 = true;
		
		while(head <= end) {
			
			int middle = (head + end) / 2;
			
			if(dest1 == arr02[middle]) {
				System.out.println("找到指定元素的位置为：" + middle);
				isFlag02 = false;
				break;
			}
			else if(arr02[middle] > dest1) {
				end = middle - 1;
			}
			else {
				head = middle + 1;
			}
		}
		
		if(isFlag02) {
			System.out.println("没有找到");
		}
	}
}
```
***
### 排序(十大内部排序)
#### 选择排序（直接选择排序，堆排序）

#### 交换排序（冒泡排序，快速排序）  
##### 冒泡排序
```
public class Test005 {
	public static void main(String args[]) {
		int[] arr = new int[] {1,2,3,4,6,7,8,90,4,3,2,5,8};
		
		System.out.print("原值：\t");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
		
		System.out.print("排序：\t");
		
		//冒泡排序
		for(int i = 0; i < arr.length - 1; i++) {
			
			for (int j = 0; j < arr.length - 1 - i; j++) {
				
                if(arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
	}
}
```
##### 快速排序
#### 插入排序（直接插入排序，拆半插入排序，Shell排序）
#### 归并排序
#### 桶式排序
#### 基数排序

***

## java.util.Arrays Arrays工具类
- Arrays.equals(arr1,arr2)  返回boolean类型
- arr.toString() 返回String类型
- fill(arr,value) 往arr中填充value
- sort(arr) 排序
- binarySearch(arr,value) 二分法查找，在arr中查找value 

***

## 数组中常见异常
### 数组角标越界的异常 ArrayIndexOutofBoundsExcetion --下标超过数组范围
### 空指针异常  NullPointerException 
```
public class Test008 {
	public static void main(String args[]) {
//		情况一：
		int[] arr1 = new int[]{12,3,4};
		arr1 = null;
		System.out.println(arr1[0]);  //报空指针错
		
//		情况二
		int[][] arr2 = new int[4][];
		System.out.println(arr2[0]); //返回null
		System.out.println(arr2[0][0]); //报空指针错

//		情况三
		String[] arr3 = new String[] {"AA","BB","CC"};
		arr3[0] = null;
		System.out.println(arr3[0].toString());
	}
}
```
## 对象数组


***

# 面向对象
## 概要
### Java学习三条主线

- 面向对象的三大特征
  1. 封装性
  2. 继承性
  3. 多态性
  4. （抽象性）

- Java类及类的成员
   - 属性，
   - 方法，
   - 构造器，
   - 代码块
   - 内部类 

- 其他关键字
   - this
   - super
   - static
   - final
   - abstract
   - interface
   - package
   - import
   - ...
   - 
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/238793222227242.png =593x)

### 面向过程与面向对象
- 面向过程 POP
   - 强调的是功能行为，以函数为最小单位，考虑怎么做
- 面向对象 OOP
   - 强调具备功能的对象，以类/对象为最小单位，考虑谁来做 

### 面向对象的两个要素
- 类
   - 对一类事物的描述，是抽象的，概念上的定义
- 对象（实例）
   - 是实际存在的该类事物的每个个体

1. 面向对象程序设计的重点是类的设计
2. 设计类，就是设计类的成员

### 类和对象的使用（面向对象思想落地的实现）

1. 创建类，设计类的成员
2. 创建类的对象
3. 通过”对象.属性“，”对象.方法“调用对象的结构

- 如果创建了一个类的多个对象，则每个对象都独立的拥有一套类的属性（非static的）
   - 意味着，如果我们修改一个对象的属性a，则不影响另外一个对象属性a的值

```
package StudyTest;

//  测试类
public class Test010 {
	public static void main(String args[]) {
//		创建Person类的对象 = 类的实例化 = 实例化类
		Person p1 = new Person();
		
//		调用对象的结构：属性，方法
//		调用属性：对象.属性
		p1.name = "Tom";
		p1.isMale = true;
		System.out.println(p1.name);
		
//		调用方法：对象.方法
		p1.eat();
		p1.sleep();
		p1.talk("Chinese");
		
		Person p2 = new Person();
		System.out.println(p2.name);
//		输出 null 默认初始化值
		System.out.println(p2.isMale);
//		输出 false 默认初始值
		
//		将p1变量保存的对象地址值赋给p3，导致p1和p3指向了维空间的同一个对象实体
//		因而改变p3的属性的同时也改变了p1的属性
		Person p3 = p1;
		System.out.println(p3.name); //Tom
		
		p3.age = 10;
		System.out.println(p1.age);  //10
	}
}

class Person{
	
//	属性
	String name;
	int age = 1;
	boolean isMale;
	
//	方法
	public void eat() {
		System.out.println("人可以吃饭");
	}
	
	public void sleep() {
		System.out.println("人可以睡觉");
	}
	
	public void talk(String language) {
		System.out.println("人可以说话,语言为: " + language);
	}
}
```

### 对象的内存解析

编译完程序以后，生成一个或多个字节码文件

我们使用JVM中的类的加载器和解释器对生成的字节码文件进行解释运行。意味着，需要将字节码文件对应的类加载到内存中，涉及到内存解析。

#### JVM

- 堆（Heap）
   - 此内存区域的唯一目的就是存放对象实例，几乎所有的对象实例都在这里分配内存。
   - 这一点在Java虚拟机规范中的描述是：所有的对象实例以及数组都要在堆上分配。
- 通常所说的栈（Stack），是指虚拟机栈。
  - 虚拟机栈用于存储局部变量等。
  - 局部变量表存放了编译期可知长度的各种基本数据类型（boolean、byte、char 、 short 、 int 、 float 、 long 、double）、对象引用（reference类型，它不等同于对象本身，是对象在堆内存的首地址）。 方法执行完，自动释放。
- 方法区（Method Area）
  - 用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。

《JVM规范》

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/451485022220954.png =781x)

#### 内存解析的说明

- 引用类型的变量，只可能存储两类值：null 或 地址值（含变量的类型）

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/242364122239382.png =688x)

### 匿名对象
- 不定义对象的句柄，而直接调用这个对象的方法。这样的对象叫做匿名对象。
   - 如：new Person().shout(); 
- 使用情况
   - 如果对一个对象只需要进行一次方法调用，那么就可以使用匿名对象。
   - 我们经常将匿名对象作为实参传递给一个方法调用。

1. 理解：创建的变量没有显式的赋给一个变量名，即为一个匿名对象
2. 特征：匿名对象只能调用一次
3. 使用：

```java
public class Test20 {
	public static void main(String args[]) {
		Phone p = new Phone();
//		p = null  //打印 == null 
//		则p.price 报错
		System.out.println(p);  //返回地址值
		
		p.sendEmail();
		p.playGame();
		
//		匿名对象
		new Phone().sendEmail();
		new Phone().playGame();
//		上两个语句并不是同一个对象
		new Phone().price = 1999;
		new Phone().showPrice();  //不是1999 而是 默认值0.0

//		匿名对象的使用
		PhoneMall mall = new PhoneMall();
//		mall.show(p);
		mall.show(new Phone());
	}
}

class PhoneMall{
	
	public void show(Phone phone) {
		phone.sendEmail();
		phone.playGame();
	}
}

class Phone{
	double price; //价格
	
	public void sendEmail(){
		System.out.println("发送邮件");
	}
	
	public void playGame() {
		System.out.println("玩游戏");
	}
	
	public void showPrice() {
		System.out.println("手机价格为：" + price);
	}
}
```
### 属性

**语法格式：**

`修饰符 数据类型 属性名 = 初始化值 ; `

- 说明1: 修饰符
   - 常用的权限修饰符(声明属性时，指明其权限; 封装性)有：private、缺省、protected、public
   - 其他修饰符：static、final (暂不考虑)
- 说明2：数据类型
   - 任何基本数据类型(如int、Boolean) 或 任何引用数据类型。
- 说明3：属性名
   - 属于标识符，符合命名规则和规范即可。
   
#### 变量的分类：成员变量与局部变量

- 在方法体外，类体内声明的变量称为成员变量。
- 在方法体内部声明的变量称为局部变量。
- 注意：二者在初始化值方面的异同:
    - 同：都有生命周期
    - 异：局部变量除形参外，均需显式初始化。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/328105822227247.png =497x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/2235922247413.png =691x)

**对象属性的默认初始化赋值**

- 属性：类的属性，根据其类型都有默认的初始值
- 局部变量没有默认初始化值
   - 形参在调用时赋值即可
- 当一个对象被创建时，会对其中各种类型的成员变量自动进行初始化赋值。除了基本数据类型之外的变量类型都是引用类型，如上面的Person及前面讲过的数组。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/465885922240082.png =357x)

```java
public class Test011 {
	public static void main(String args[]) {
		User u1 = new User();
		System.out.println(u1.name);
		System.out.println(u1.age);
		System.out.println(u1.isMale);
		
		u1.talk("汉语");
		
		u1.eat();
	}
}

class User {
//	属性
//	private String name;  //权限修饰符
	String name;
	public int age;
	boolean isMale;
	
	public void talk(String language) {  //language 形参;也是局部变量
		System.out.println("我们使用" + language + "进行交流");
		
	}
	
	public void eat() {
		String food = "烙饼";
//		public String foodTest = "烙饼"; 报错，局部变量不可以使用权限修饰符
//		final String foodTest = "烙饼"; 局部变量可以使用final修饰符
		System.out.println("北方人喜欢吃" + food);
	}
	
}
```
### 方法(method)

- 描述类应该具有的功能
- 比如：
     - Math类：sqrt() \ random() \...
     - Scanner类：nextXxx() ...
     - Arrays类：sort() \ binarySearch() \ toString() \ equals() \ ...
     
1. 举例:
    - public void eat(){}
    - public void sleep(int hour){}
    - public String getName(){}
    - public String getNation(String nation){}
2. 方法的声明: 

```java
权限修饰符 返回值类型 方法名(形参列表){
        方法体;    
}
```
```java
public class Test012 {
	public static void main(String args[]) {
		
	}
}

class Customer{
	String name;
	int age;
	boolean isMale;
	
	public void eat() {
		System.out.println("客户吃饭");
	}
	
	public void sleep(int hour) {
		System.out.println("休息了" + hour + "小时");
	}
	
	public String getName() {
		return name;
	}
	
	public String getNation(String nation) {
		String info = "我的国籍是：" + nation;
		return info;
	}
	
}
```

#### 权限修饰符

Java规定的4种权限修饰符：private ,  public , 缺省 , protected 

#### 返回值类型 
- 有返回值
   - 如果方法有返回值，则必须在方法声明时，指定返回值的类型。
   - 同时方法中需要使用return关键字来返回指定类型的变量或常量。
 
```java
 	public String getName() {
		
		if(age > 18) {
			return name;
		}
	}
	报错，必须要有返回值，if可能没有返回值
```

- 没返回值
   - 方法声明时，使用void来表示
   - 通常没有返回值的方法中不需要使用return，如果使用，只能`return;`表示结束此方法的意思
   
```java
	public void eat() {
		System.out.println("客户吃饭");
		return;  //表示结束
//      return后不可以声明表达式
    	System.out.println("你好"); //无法到达 Unreachable code
	}
```

##### return关键字的使用

1. 使用范围：使用在方法体中
2. 使用：
    - 结束方法
    - 针对有返回值的方法，使用”`return 数据`“的方法返回所要的数据
3. return关键字后面不可以声明执行语句 
- 该不该有返回值
    - 题目要求
    - 凭经验
#### 方法名：

- 属于标识符，遵循标识符的规则和规范

#### 形参列表：

- 方法可以声明零个，一个或多个形参
- 格式：`数据类型1 形参1,数据类型2 形参2,...`
- 该不该定义形参
    - 题目要求
    - 凭经验，具体问题具体分析 
      
#### 方法体

- 方法功能的体现

```java
public class Test012 {
	public static void main(String args[]) {
		Customer cust1 = new Customer();
		
		int[] arrTest = new int[] {1,2,3,4,7,5,6};
		cust1.outPrint(arrTest);
		cust1.sort(arrTest);
		cust1.outPrint(arrTest);
	}
}

class Customer{
	String name;
	int age;
	boolean isMale;
	
	public void sort(int[] arr) {
		for(int i = 0; i < arr.length - 1; i++) {
			for(int j = 0; j < arr.length - 1 - i; j++) {
				if(arr[i] > arr[i + 1]) {
					int temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			}
		}
	}
	
	public void outPrint(int[] arr) {
		System.out.print("数组为: \t");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " , ");
		}
		System.out.println();
	}
}
```

#### 方法的使用中，可以调用当前类的属性或方法

- 方法的内部可以调用方法
     - 递归：方法中再次调用自身

```java
public class Test012 {
	public static void main(String args[]) {
		Customer cust1 = new Customer();
		
		cust1.eat();
			
		cust1.sleep(1);
	}
}

class Customer{

	public void eat() {
		System.out.println("客户吃饭");
	}
	
	public void sleep(int hour) {
		System.out.println("休息了" + hour + "小时");
		
		eat();
	}
}
```
- 不能在方法中声明方法

```
public void test(){
    public void testTwice(){} //报错，不可以在方法体内部声明方法
}
```

### 练习
#### 1.创建一个Person类

```java
要求：
(1)创建Person类的对象，设置该对象的name、age和sex属性，
   调用study方法，输出字符串“studying”，
   调用showAge()方法显示age值，
   调用addAge()方法给对象的age属性值增加2岁。
(2)创建第二个对象，执行上述操作，体会同一个类的不同对象之间的关系。
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/238341115220955.png =166x)

```java
public class Test013 {
	public static void main(String args[]) {
		PersonTest per1 = new PersonTest();
		
		per1.name = "kk";
		per1.age = 10;
		per1.sex = 1;
		
		per1.study();
		per1.showAge();
		System.out.println(per1.addAge(2));
		
	}
}

class PersonTest{
	String name;
	int age;
	int sex;
	
	public void study() {
		System.out.println("studing");
	}
	
	public void showAge() {
		System.out.println("age = " + age);
	}
	
	public int addAge(int i) {
		age += i;
		return age;
	}
}
```
#### 2.利用面向对象的编程方法，设计类Circle计算圆的面积。

```java
public class Test014 {
	public static void main(String args[]) {
		Circle c1 = new Circle();
		c1.radius = 10;
		System.out.println("圆的面积为：" + c1.findArea());
	}
}

class Circle{
	//属性
	double radius;
	
	//求圆的面积
	public double findArea() {
		double area = Math.PI * radius *radius;
		return area;
	}
}
```
```
public class Test014 {
	public static void main(String args[]) {
		Circle c1 = new Circle();
		
		c1.r = 10;
		c1.findArea();
	}
}

class Circle{
	double r;
	public void findArea() {
		double area = r * r * Math.PI;
		System.out.println("圆的面积为：" + area);
	}
}
```
```java
public class Test014 {
	public static void main(String args[]) {
		Circle c1 = new Circle();
		
		System.out.println("圆的面积为：" + c1.findArea(10));
		
	}
}

class Circle{
	public double findArea(double r) {
		double area = r * r * Math.PI;
		return area;
	}
}
```

#### 3.1 编写程序，声明一个method方法，在方法中打印一个`10*8 的*型矩形`，在main方法中调用该方法。
```
public class Test015 {
	public static void main(String args[]) {
		Test015 test = new Test015();
		test.method(10,8);
	}
	
	public void method(int column,int row) {
		for(int i = 0; i < column; i++) {
			for(int j = 0; j < row; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
```
#### 3.2 修改上一个程序，在method方法中，除打印一个`10*8的*型矩形`外，再计算该矩形的面积，并将其作为方法返回值。在main方法中调用该方法，接收返回的面积值并打印。

#### 3.3 修改上一个程序，在method方法提供m和n两个参数，方法中打印一个`m*n的*型矩形`，并计算该矩形的面积， 将其作为方法返回值。在main方法中调用该方法，接收返回的面积值并打印。
```
public class Test015 {
	public static void main(String args[]) {
		Test015 test = new Test015();
		test.method(10,8);
	}
	
	public void method(int column,int row) {
		for(int i = 0; i < column; i++) {
			for(int j = 0; j < row; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		System.out.println("面积为：" + countArea(column,row));
	}
	
	public int countArea(int column,int row) {
		return column * row;
	}
}
```
```
public class Test016 {
	public static void main(String args[]) {
		Test016 test = new Test016();
		System.out.println("面积为 " + test.method(10,8));
	}
	
	public int method(int m, int n) {
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		return m * n;
	}
}
```
#### 对象数组题目：
```
定义类Student，包含三个属性：
  学号number(int)，
  年级state(int)，
  成绩score(int)。 
创建20个学生对象，学号为1到20，年级和成绩都由随机数确定。
问题一：打印出3年级(state值为3）的学生信息。
问题二：使用冒泡排序按学生成绩排序，并遍历所有学生信息
提示：
1) 生成随机数：Math.random()，返回值类型double; 
2) 四舍五入取整：Math.round(double d)，返回值类型long。
```
```
public class Test017 {
	public static void main(String args[]) {
//		声明Student类型的数组
		Student[] stus = new Student[20];
		
		for(int i = 0; i < stus.length; i++) {
//			给数组元素赋值
			stus[i] = new Student();
//			给Student对象的属性赋值
			stus[i].number = ( i + 1);
//			年级[1,6]
			stus[i].state = (int)(Math.random() * (6 - 1 + 1) + 1);
//			成绩[0,100]
			stus[i].score = (int)(Math.random() * (100 - 0 + 1));
		}
		
//		遍历学生数组
		for(int i = 0; i < stus.length; i++) {
//			System.out.println(stus[i]);  返回地址值
			System.out.println(stus[i].info());
		}
		
//		问题1：打印出3年级学生的信息
		System.out.println("3年级学生的信息如下：");
		for(int i = 0; i < stus.length; i++) {
			if(stus[i].state == 3) {
				System.out.println(stus[i].info());
			}
		}
		
//		问题2：使用冒泡排序按学生成绩排序，并遍历学生信息
		System.out.println("排序：");
		for(int i = 0; i < stus.length - 1; i++) {
			for(int j = 0; j < stus.length - 1 - i; j++) {
				if(stus[j].score < stus[j + 1].score) {
					Student temp = stus[j];
					stus[j] = stus[j + 1];
					stus[j + 1] = temp;
				}
			}
		}
		
		for(int i = 0; i < stus.length; i++) {
			System.out.println(stus[i].info());
		}
	}
}
```
封装改进
```
public class Test018 {
	public static void main(String args[]) {
//		声明StudentTest类型的数组
		StudentTest[] stus = new StudentTest[20];
		
		for(int i = 0; i < stus.length; i++) {
//			给数组元素赋值
			stus[i] = new StudentTest();
//			给StudentTest对象的属性赋值
			stus[i].number = ( i + 1);
//			年级[1,6]
			stus[i].state = (int)(Math.random() * (6 - 1 + 1) + 1);
//			成绩[0,100]
			stus[i].score = (int)(Math.random() * (100 - 0 + 1));
		}
		
		Test018 test = new Test018();
		
//		遍历学生数组
		test.print(stus);
		test.searchState(stus,3);
		test.sort(stus);
	}
	
//	遍历学生数组  改进------------------------------------
	public void print(StudentTest[] stus) {
		for(int i = 0; i < stus.length; i++) {
			System.out.println(stus[i].info());
		}
	}
	
//	问题1：打印出3年级学生的信息  改进-----------------------
	/**
	 * @Description 查找Student数组中指定年级的学生信息
	 * @param stus 要查找的数组
	 * @param state 要查找的年级
	 */
	public void searchState(StudentTest[] stus, int state) {
		System.out.println(state +"年级学生的信息如下：");
		for(int i = 0; i < stus.length; i++) {
			if(stus[i].state == state) {
				System.out.println(stus[i].info());
			}
		}
	}
	
//	问题2：使用冒泡排序按学生成绩排序，并遍历学生信息 改进----------
	/**
	 * Description 给StudentTest数组冒泡排序
	 * @param stus 要排序的数组
	 */
	public void sort(StudentTest[] stus) {
		System.out.println("排序：");
		for(int i = 0; i < stus.length - 1; i++) {
			for(int j = 0; j < stus.length - 1 - i; j++) {
				if(stus[j].score < stus[j + 1].score) {
					StudentTest temp = stus[j];
					stus[j] = stus[j + 1];
					stus[j + 1] = temp;
				}
			}
		}
		
		for(int i = 0; i < stus.length; i++) {
			System.out.println(stus[i].info());
		}
	}
}

class StudentTest{
	int number;//学号
	int state;//年级
	int score;//成绩
	
	public String info() {
		return "学号：\t" + number + "\t 年级：\t" + state + "\t 成绩：\t" + score;
	}
}
```
#### 5.声明一个日期类型MyDate：有属性：年year,月month，日day。创建2个日期对象，分别赋值为：你的出生日期，你对象的出生日期，并显示信息。
```
public class Test019 {
	public static void main(String args[]) {
		MyDate mine = new MyDate();
		mine.year = 2003;
		mine.month = 3;
		mine.day = 8;
		
		MyDate lover = new MyDate();
		lover.year = 2002;
		lover.month = 2;
		lover.day = 27;	
		
		mine.showDate();
		lover.showDate();
	}
}

class MyDate{
	int year;
	int month;
	int day;
	
	public void showDate() {
		System.out.println("出生日期 : " + year + "年" + month + "月" + day + "日");
	}
}
```

## 自定义数组的工具类
### 工具类

```java
package StudyTest;

public class Test021 {
	public static void main(String args[]) {
		
	}
	
//	求数组的最大值
	public int getMax(int[] arr) {
		int maxValue = arr[0];
		
		for(int i = 0; i < arr.length; i++) {
			if(maxValue < arr[i]) {
				maxValue = arr[i];
			}
		}
		
		return maxValue;
	}
	
//	求数组的最小值
	public int getMin(int[] arr) {
		int minValue = arr[0];
		
		for(int i = 0; i < arr.length; i++) {
			if(minValue > arr[i]) {
				minValue = arr[i];
			}
		}
		
	    return minValue;
	}
	
//	求数组的总和
	public int getSum(int[] arr) {
		int sumValue = 0;
		
		for(int i = 0; i < arr.length; i++) {
			sumValue += arr[i];
		}
		
		return sumValue;
	}
	
//	求数组的平均值
	public int getAvg(int[] arr) {
		
		return getSum(arr) / arr.length;
		
//		int sumValue = 0;
//		
//		for(int i = 0; i < arr.length; i++) {
//			sumValue += arr[i];
//		}
//		
//		int avgValue = sumValue / arr.length;
//		
//		return avgValue;
	}
	
//	反转数组
	public void reverse(int[] arr) {
		for(int i = 0; i < arr.length / 2; i++) {
			int temp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = temp; 
		}
	}
	
//	复制数组
	public int[] copy(int[] arr) {
		int[] arrCopy = new int[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			arrCopy[i] = arr[i];
		}
		
		return arrCopy;
	}
	
//	数组排序
	public void sort(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			
			for(int j = 0; j < arr.length - 1; j++) {
			
				if(arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
	
//	遍历数组
	public void print(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
		
		System.out.println();
	}
	
//	查找指定元素
	public int getIndex(int[] arr,int dest) {
		
		for(int i = 0; i < arr.length; i ++) {
		
			if(arr[i] == dest) {
				return i;
			}
		}
		
		return -1; //-1表示未找到
	}
}
```
### 使用类

```java
package StudyTest;

public class Test022 {
	public static void main(String args[]) {
		Test021 util = new Test021();
		int[] arr = new int[] {32,34,32,5,3,54,645};
		int max = util.getMax(arr);
		System.out.println("最大值为：" + max);
		
		System.out.println("排序前");
		util.print(arr);
		
		System.out.println("排序后");
		util.sort(arr);
		util.print(arr);
		
		System.out.println("查找");
		int index = util.getIndex(arr, 5);
		if(index >= 0) {
			System.out.println("找到了：" + index);
		}
		else {
			System.out.println("未找到");
		}
	}
}
```

## 方法
### 方法的重载

- 重载的概念
   - 在同一个类中，允许存在一个以上的同名方法，只要它们的参数个数或者参数类型不同即可。
- 重载的特点：
   - 与返回值类型无关，只看参数列表，且参数列表必须不同。(参数个数或参数类型)。
   - 调用时，根据方法参数列表的不同来区别。
- 重载示例：

```
//返回两个整数的和
int add(int x,int y){
    return x+y;
}

//返回三个整数的和
int add(int x,int y,int z){
    return x+y+z;
}

//返回两个小数的和
double add(double x,double y){
    return x+y;
}
```
```
public class Test023 {
	public static void main(String args[]) {
		Test023 test = new Test023();
		test.getSum(2,3);
	}
	
	public void getSum(int i, int j) {
		System.out.println(i + j + "  1 ");
	}
	
//  重载与返回类型/形参变量名/方法体无关
//	public int getSum(int m, int n) {
//		System.out.println(i + j);
//	}
	
	public void getSum(double i, double j) {
		System.out.println(i + j + "  2 ");
	}
	
	public void getSum(String i, int j) {
		System.out.println(i + j + "  3 ");
	}
	
	public void getSum(int i, String j) {
		System.out.println(i + j + "  4 ");
	}
}
```
#### 练习
##### 1.
```
编写程序，定义三个重载方法并调用。方法名为mOL。
三个方法分别接收一个int参数、两个int参数、一个字符串参数。
分别执行平方运算并输出结果，相乘并输出结果，输出字符串信息。
在主类的main ()方法中分别用参数区别调用三个方法。
```
```
public class Test024 {
	public static void main(String args[]) {
		Test024 test = new Test024();
		
		test.mOL(2);
		test.mOL(2,3);
		test.mOL("test exam");
	}
	
	public void mOL(int i) {
		System.out.println(i * i);
	}
	
	public void mOL(int i, int j) {
		System.out.println( i * j);
	}
	
	public void mOL(String i) {
		System.out.println(i);
	}
}
```
##### 2.
```
3.定义三个重载方法max()，
第一个方法求两个int值中的最大值，
第二个方法求两个double值中的最大值，
第三个方法求三个double值中的最大值，
并分别调用三个方法。
```
```
public class Test025 {
	public static void main(String args[]) {
		Test025 test = new Test025();
		
		test.max(2, 3);
		test.max(2.34, 3.456);
		test.max(3.12, 5.343, 3.123);
	}
	
	public void max(int i, int j) {
		System.out.println((i > j) ? i : j);
	}
	
	public void max(double i, double j) {
		System.out.println((i > j) ? i : j);
	}
	
	public void max(double i, double j, double k) {
		double maxValue = (i > j) ? i : j;
		maxValue = (maxValue > k) ? maxValue : k;
		System.out.println(maxValue);
	}
}
```

#### 方法的重载与重写的区别


### 可变形参的方法

- JavaSE 5.0 中提供了Varargs(variable number of arguments)机制，允许直接定义能和多个实参相匹配的形参。从而，可以用一种更简单的方式，来传递个数可变的实参。
    - //JDK 5.0以前：采用数组形参来定义方法，传入多个同一类型变量`public static void test(int a ,String[] books);`
    - //JDK5.0：采用可变个数形参来定义方法，传入多个同一类型变量`public static void test(int a ,String…books);`
- 说明：
1. 声明格式：方法名(参数的类型名 ...参数名)
2. 可变参数：方法参数部分指定类型的参数个数是可变多个：0个，1个或多个
3. 可变个数形参的方法与本类中同名的方法之间，彼此构成重载
4. 可变参数方法的使用与方法参数部分使用数组是一致的 不能构成重载，即不能共存
5. 方法的参数部分有可变形参，需要放在形参声明的最后
6. 在一个方法的形参位置，最多只能声明一个可变个数形参

```java
public class Test026 {
	public static void main(String args[]) {
		Test026 test = new Test026();
		test.show(12);
		test.show();
		test.show("Hello");
		test.show("Hello","world");
	}
	
	public void show(int i) {
		
	}
	
	public void show(String i) {
		System.out.println(i + " show(String i)");
	}
	
//	可变形参
	public void show(String ... strs) {
//		输出的strs为地址值
		System.out.println(strs + " show(String .. strs)");
		
//		可变形参的输出和数组类似
		for(int i = 0; i < strs.length; i++) {
			System.out.println(strs[i]);
		}
	}	
	
//	编译器认为数组和可变形参的定义相同，不能构成重载
//	public void show(String[] strs) {
//		
//	}
	
//  可变形参必须放在形参位置的末尾
//	public void test(int ... i; i) {
//		
//	}
}
```

### 方法参数的值传递机制

- 关于变量的赋值
   - 基本数据类型 （赋值的是数据值）
   - 引用数据类型（赋值的是地址值）String也是
- 方法，必须由其所在类或对象调用才有意义。若方法含有参数：
   - 形参：方法声明时的参数
   - 实参：方法调用时实际传给形参的参数值
- Java的实参值如何传入方法呢？
- Java里方法的参数传递方式只有一种：值传递。 即将实际参数值的副本（复制品）传入方法内，而参数本身不受影响。
   - 形参是基本数据类型：将实参基本数据类型变量的“数据值”传递给形参
   - 形参是引用数据类型：将实参引用数据类型变量的“地址值”传递给形参
       - 对象作为参数时，参数的值是对该对象的引用，这时对象的内容可以在方法中改变，但是对象的引用不会改变。 
   
- String也是引用数据类型
    -  即底层是用char[]存储
    - 特征：字符串常量值；不可变

```java
public class Test027 {
	public static void main(String args[]) {
	
		int m = 10;
		int n = 20;
		System.out.println("m=" + m + "," + "n=" + n);
//		交换两个变量的值的操作
//		int temp = m;
//		m = n;
//		n = temp;
//		System.out.println("m=" + m + "," + "n=" + n);

//		实参的值未发生交换
//		仅形参发生交换，未改变实参
//		形参是基本数据类型：将实参基本数据类型变量的“数据值”传递给形参
		Test027 test = new Test027();
		test.swap(m,n);
		System.out.println("m=" + m + "," + "n=" + n);
	}
	
	public void swap(int i, int j) {
		int temp = i;
		i = j;
		j= temp;
	}
}
```

```java
public class Test029 {
	public static void main(String args[]) {
		Data data = new Data();
		
		data.m = 10;
		data.n = 20;
		
		System.out.println("m=" + data.m + " , n=" + data.n);
		
//		交换m和n的值
//		int temp = data.m;
//		data.m = data.n;
//		data.n = temp;
		
		Test029 test = new Test029();
		test.swap(data);
		System.out.println("m=" + data.m + " , n=" + data.n);
	}
	
//	形参是引用数据类型：将实参引用数据类型变量的“地址值”传递给形参
	public void swap(Data data) {
		int temp = data.m;
		data.m = data.n;
		data.n = temp;
	}
}

class Data{
	int m;
	int n;
}
```
```java
public class Test028 {
	public static void main(String args[]) {
		int[] arr = new int[] {1,2,3,4,2,3};
		
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
		
		Test028 test = new Test028();
//		错误.实际上并未改变实参，arr[i]传递的仍然只是数据值
		test.swap(arr[1],arr[0]);
		
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
		
//		正确的，交换数组中指定俩个位置元素的值
		test.swap(arr, 1,0);
		
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}

//	错误.实际上并未改变实参，arr[i]传递的仍然只是数据值	
	public void swap(int i , int j) {
		int temp = i;
		i = j;
		j = temp;
	}
	
//	正确的，交换数组中指定俩个位置元素的值
	public void swap(int[] arr, int i,int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
```

#### 练习1 地址值改变的输出值

##### 1.

```java
public class Test30 {
	public static void main(String args[]) {
		Test30 test = new Test30();
		test.first();
	}
	
	public void first() {
		int i = 5;
		
		Value v = new Value(); //设为0x5566
		v.i = 25;
		
		second(v, i);  //15 0
		//second()方法结束
		//0x8899地址值被丢弃
		//v 指向地址值0x5566 v.i ==20
		
		System.out.println(v.i);  //20
	}
	
	public void second(Value v, int i) {
		i = 0;  //实参i==5 形参i==0
		v.i = 20; //地址值0x5566的实参v.i==20
		
		Value val = new Value();
		v = val; 
		//此时v的地址值0x5566变为val的地址值0x8899
		//v.i 指向 val.i == 15
		
		System.out.println(v.i + " " + i);
		// v.i == 15 ; 形参i == 0 ;但是实参i仍然为i == 5 
	}
}
class Value {
	int i = 15;
}

输出
15 0
20
```

##### String也是引用数据类型，但是较特殊,不可变

```java
public class Test043 {
	public static void main(String args[]) {
		String s1 = "hello";
		
		Test043 test = new Test043();
		
		test.change(s1);
		
		System.out.println(s1); //hello
	}
	
	public void change(String s) {
		s = "hi";    
		//虽然传入s1地址值，但s新建指向hi，s1仍然指向hello
	}
}
```

#### 练习2 陷阱题

```
public class Test031 {
	public static void main(String args[]) {
		int a = 10;
		int b = 10;
		method(a,b);
//		需要在method方法被调用之后，仅打印出a=100,b=200,请携程method方法的代码
		System.out.println("a=" + a);
		System.out.println("b=" + b);
	}
	
//	代码编写处
}
```

1. System.exit(status)

```
System.exit(status)是用来结束当前正在运行中的java虚拟机。

System.exit(0) 是正常退出程序，
System.exit(1) 或者说非0表示非正常退出程序
System.exit(status)不管status为何值都会退出程序。

和return 相比有以下不同点：return是回到上一层，而System.exit(status)是回到最上层
```
```
public class Test031 {
	public static void main(String args[]) {
		int a = 10;
		int b = 10;
		method(a,b);
//		需要在method方法被调用之后，仅打印出a=100,b=200,请携程method方法的代码
		System.out.println("a=" + a);
		System.out.println("b=" + b);
	}
	
//	代码编写处
	public static void method(int a, int b) {
		a = a * 10;
		b = b * 20;
		System.out.println("a=" + a);
		System.out.println("b=" + b);
		System.exit(0); //终止JVM的执行，程序退出
		//使之后的语句无法执行
	}
}
```
2.PrintStream 打印流 ???
```
public class Test031 {
	public static void main(String args[]) {
		int a = 10;
		int b = 10;
		method(a,b);
//		需要在method方法被调用之后，仅打印出a=100,b=200,请携程method方法的代码
		System.out.println("a=" + a);
		System.out.println("b=" + b);
	}
	

	public static void method(int a,int b) {
		PrintStream ps = new PrintStream(System.out) {
			@Override
			public void println(String x) {
				
				if("a=10".equals(x)) {
					x = "a=100";
				}
				else if("b=10".equals(x)) {
					x = "b=200";
				}
				super.println(x);
			}
		};
		
		System.setOut(ps);
	}
}
```
#### 练习3 陷阱题
```
定义一个int型的数组：int[] arr = new int[]{12,3,3,34,56,77,432};
让数组的每个位置上的值去除以首位置的元素，得到的结果，作为该位置上的新值。
遍历新的数组。
```
```
public class Test032 {
	public static void main(String args[]) {
		int[] arr = new int[]{12,3,3,34,56,77,432};
		
		Test032 test = new Test032();
		
		test.traverse(arr);
		
		test.reAddition(arr);
		test.traverse(arr);
	}
//	让数组的每个位置上的值去除以首位置的元素，得到的结果，作为该位置上的新值。
//	遍历新的数组。
	public void reAddition(int[] arr) {
		int head = arr[0];
		
		for(int i = 0; i <arr.length; i++) {
			arr[i] = arr[i] / head;
		}
	}
	
	public void traverse(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
		
		System.out.println();
	}
}
```

#### 练习4 陷阱题

```
public class Test035 {
	public static void main(String args[]) {
		int[] arr = new int[] {1,2,3};
		System.out.println(arr); //地址值
//		void java.io.PrintStream.println(Object x)
		
		char[] arr1 = new char[] {'a','b','c'};
		System.out.println(arr1); //abc
//		void java.io.PrintStream.println(char[] x)
	}
}
```

#### 练习5：将对象作为参数传递给方法
（1）定义一个Circle类，包含一个double型的radius属性代表圆的半径，一个findArea()方法返回圆的面积。
```
import java.util.Scanner;

public class Test033 {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
			
		CircleTest c1 = new CircleTest();
		
		c1.radius = scan.nextDouble();
		System.out.println(c1.findArea());
		
	}
}

class CircleTest{
	double radius;
	
	public double findArea() {
		return radius * radius * Math.PI;
	}
}
```
（2）定义一个类PassObject，在类中定义一个方法printAreas()，该方法的定义如下：
public void printAreas(Circle c, int time)
在printAreas方法中打印输出1到time之间的每个整数半径值，以及对应的面积。
例如，times为5，则输出半径1，2，3，4，5，以及对应的圆面积。
```
public class Test034 {
	public static void main(String args[]) {
		PassObject p1 = new PassObject();
		Circle c1 = new Circle();
		p1.printAreas(c1, 5);
	}
}

class PassObject{
	public void printAreas(Circle c, int time) {
		for(int i = 1; i <= time; i++) {
			System.out.println("r=" + i + "  Area=" + c.findArea(i));
		}
	}
}
```
（3）在main方法中调用printAreas()方法，调用完毕后输出当前半径值。程序运行结果如图所示
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/129730620220958.png =349x)
```
public class Test034 {
	public static void main(String args[]) {
		PassObject p1 = new PassObject();
		Circle c1 = new Circle();
		p1.printAreas(c1, 5);
	}
}

class PassObject{
	public void printAreas(Circle c, int time) {
		System.out.println("radius \t Area");
		
		for(double i = 1; i <= time; i++) {
			System.out.println( i + "\t" + c.findArea(i));
		}
	}
}
```
### 递归方法 
- 递归方法：一个方法体内调用它自身。
- 方法递归包含了一种隐式的循环，它会重复执行某段代码，但这种重复执行无须循环控制。
- 递归一定要向已知方向递归，否则这种递归就变成了无穷递归，类似于死循环。
#### 计算1-100之间所有自然数的和
```
public class Test036 {
	public static void main(String args[]) {
		Test036 test = new Test036();
		
		int sum = test.getSum(100);
		
		System.out.println(sum);
	}

	public int getSum(int num) {
		if(num == 1) {
			return 1;
		}
		else {
			return num + getSum(num - 1);
		}
	}
}
```
```
public class Test037 {
	public static void main(String args[]) {
		Test037 test = new Test037();
		
		System.out.println(test.sum(1));
	}
	
	public static int sum(int num) {
		if(num <= 100 && num >0) {
			return num + sum(num + 1);
		}
		else {
			return 0;
		}
	}
}
```
#### 练习7.1：请用Java写出递归求阶乘(n!)的算法

```
public class Test038 {
	public static void main(String args[]) {
		Test038 test = new Test038();
		
		System.out.println(test.factorial(10));
	}
	
	public int factorial(int num) {
		if(num == 1) {
			return 1;
		}
		else {
			return num * factorial(num - 1);
		}
	}
}
```
#### 练习7.2：
```
已知有一个数列：f(0) = 1,f(1) = 4,f(n+2)=2*f(n+1) + f(n),
其中n是大于0的整数，求f(10)的值。
```

```
//f(n) = 2 * f(n -1) + f(n -2)

public class Test039 {
	public static void main(String args[]) {
		Test039 test = new Test039();
		System.out.println(test.arrCount(3));
	}
	
	public int arrCount(int num) {
		if(num == 0) {
			return 1;
		}
		else if(num == 1) {
			return 2 * arrCount(num - 1) + 2;
		}
		else {
			return 2 * arrCount(num - 1) + arrCount(num - 2);
		}
	}
}
```
#### 练习7.3：
```
已知一个数列：f(20) = 1,f(21) = 4,f(n+2) = 2*f(n+1)+f(n),
其中n是大于0的整数，求f(10)的值。
```
```

```

#### 练习7.4：斐波那契数列
```
输入一个数据n，计算斐波那契数列(Fibonacci)的第n个值
1 1 2 3 5 8 13 21 34 55
规律：一个数等于前两个数之和
要求：计算斐波那契数列(Fibonacci)的第n个值，并将整个数列打印出来
```
```
public class Test041 {
	public static void main(String args[]) {
		Test041 test = new Test041();
	
		int n = 10;
		System.out.println(test.fibonacci(n));
		
		for(int i = 1; i <= n; i++) {
			System.out.print(test.fibonacci(i) + "\t");
		}
		System.out.println();
		
	}
	
	public int fibonacci(int n) {
		if(n == 1) {
			return 1;
		}
		else if(n == 2){
			return fibonacci(n - 1) + 0;
		}
		else {
			return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}
}
```

## 面向对象特征之一：封装和隐藏

**高内聚低耦合**

- 高内聚 ：类的内部数据操作细节自己完成，不允许外部干涉；
- 低耦合 ：仅对外暴露少量的方法用于使用。

- 隐藏对象内部的复杂性，只对外公开简单的接口。便于外界调用，从而提高系统的可扩展性、可维护性。通俗的说，把该隐藏的隐藏起来，该暴露的暴露出来。这就是封装性的设计思想。

- 封装性的体现:
   - Java中通过将数据声明为私有的(private)，
   - 再提供公共的（public）方法:getXxx()和setXxx()实现对该属性的操作，
   - 以实现下述目的：
   1. 隐藏一个类中不需要对外提供的实现细节；
   2. 使用者只能通过事先定制好的方法来访问数据，可以方便地加入控制逻辑，限制对属性的不合理操作；
   3. 便于修改，增强代码的可维护性；


**封装性的体现：**  

1. 如上
2. 不对外暴露的方法
3. 单例模式(将构造器私有化)
4. 如果不希望类在包外被调用，可以将类设置为缺省的

### 封装性的体现需要访问权限修饰符  == 封装性

- Java权限修饰符public、protected、(缺省)、private置于类的成员定义前，用来限定对象对该类成员的访问权限。
- 可以用来修饰类及类的内部结构：属性，方法，构造器，内部类
- 具体：
    - 4种权限都可以用来修饰类的内部结构；属性，方法，构造器，内部类
    - 修饰类的话，只能使用：缺省，public
 
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/228253722220960.png =805x)

- 对于class的权限修饰只可以用public和default(缺省)。
   - public类可以在任意地方被访问。
   - default类只可以被同一个包内部的类访问。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/56643822239386.png =411x)

### 例1

```java
public class Test001 {
	public static void main(String args[]) {
		
		Animal a = new Animal();
//		a.name = "大黄";  //private
//		a.age = 1;  //private
//		a.legs = 1; //private 
		//The field Animal.legs is not visible
		
		a.setName("大黄");
		a.setAge(10);
		a.setLegs(4);
		
		a.show();
	}
}

class Animal{
	
	String name;
	private int age;
	private int legs;
	
//	对legs属性的设置
	public void setLegs(int leg) {
		if(leg >= 0 && leg % 2 == 0) {
			legs = leg;
		}
		else {
			legs = -1;
		}
	}
	
//	对legs属性的获取
	public int getLegs() {
		return legs;
	}
	
//	对age属性的设置
	public void setAge(int a) {
		if(a >= 0 && a <= 100) {
			age = a;
		}
		else {
			age = -1;
		}
	}
	
//	对age属性的获取
	public int getAge() {
		return age;
	}
	
//	对name属性的设置
	public void setName(String str) {
		name = str;
	}
	
//	对name属性的获取
	public String getName() {
		return name;
	}
	
	public void eat() {
		System.out.println("动物进食");
	}
	
	public void show() {
		System.out.println("name = " + name + "\t age = " + age + "\t legs = " + legs);
	}
}
```

### 例2
- 同一类中

```
package StudyTest02;

public class Test002 {
	
	private int orderPrivate;
	int orderDefault;
	public int orderPublic;
	
	private void methodPrivate() {
		orderPrivate = 1;
		orderDefault = 2;
		orderPublic  = 3;
	}
	
	void methodDefault() {
		orderPrivate = 1;
		orderDefault = 2;
		orderPublic  = 3;
	}
	
	public void methodPublic() {
		orderPrivate = 1;
		orderDefault = 2;
		orderPublic  = 3;
	}
}
```

- 同一个包中

```
package StudyTest02;

public class Test003 {
	public static void main(String args[]) {
		
		Test002 order = new Test002();
		
		order.orderDefault = 1;
		order.orderPublic = 2;
//		在Order类之外，私有的结构就不可调用了
//		order.orderPrivate = 3;
//		he field Test002.orderPrivate is not visible
		
		order.methodDefault();
		order.methodPublic();
//		order.methodPrivate();
//		The method methodPrivate() from the type Test002 is not visible

	}
}
```

- 同一个项目中

```
package StudyTest;

import StudyTest02.Test002;

public class Test045 {
	public static void main(String args[]) {
		
		Test002 order = new Test002();
		
//      在order包之外，缺省的结构就不可调用了
//		order.orderDefault = 1;
//		The field Test002.orderDefault is not visible
		order.orderPublic = 2;
//		在Order类之外，私有的结构就不可调用了
//		order.orderPrivate = 3;
//		he field Test002.orderPrivate is not visible
		
//		order.methodDefault();
//		The method methodDefault() from the type Test002 is not visible
		order.methodPublic();
//		order.methodPrivate();
//		The method methodPrivate() from the type Test002 is not visible
	}
}
```

### 1.创建程序,在其中定义两个类：Person和PersonTest类。
```
定义如下：
用setAge()设置人的合法年龄(0~130)，用getAge()返回人的年龄。
在 PersonTest 类 中实例化 Person 类的对象 b ， 
调 用 setAge() 和getAge()方法，体会Java的封装性。
```
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/172641809220961.png =252x)
```
public class Test004 {
	public static void main(String args[]) {
		Person p1 = new Person();
		PersonTest t1 = new PersonTest();
		
		t1.setAge(p1, 1110);
		System.out.println(t1.getAge(p1));
	}
}

class Person{
	protected int age;
}

class PersonTest{
	public void setAge(Person b, int a) {
		if(a >= 0 && a <= 130) {
			b.age = a;
		}
		else {
			b.age = -1;
			throw new RuntimeException("输入的数据有误");
//			System.out.println("输入年龄有误");
		}
	}
	
	public int getAge(Person b) { 
		return b.age;
	}
}
```

## 构造器(或构造方法)

### 概要

- 构造器不是类的方法

- 构造器的特征
   - 它具有与类相同的名称
   - 它不声明返回值类型。（与声明为void不同）
   - 不能被static、final、synchronized、abstract、native修饰，
   - 不能有return语句返回值
- 构造器的作用：创建对象；给对象进行初始化
   - 如：
   - Order o = new Order(); 
   - Person p = new Person(“Peter”,15);
- 如果没有显式的定义类的构造器的话，则系统默认提供一个空参的构造器
- 语法格式：
```
修饰符 类名 (参数列表) {
    初始化语句；
} 
```

- 创建Animal类的实例：Animal a = new Animal();调用构造器，将legs初始化为4。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/211974122247419.png =358x)

- 根据参数不同，构造器可以分为如下两类：
   - 隐式无参构造器（系统默认提供）
   - 显式定义一个或多个构造器（无参、有参）
- 注 意：
   - Java语言中，每个类都至少有一个构造器
   - 默认构造器的修饰符与所属类的修饰符一致
   - 一旦显式定义了构造器，则系统不再提供默认构造器（空参）; 注意此时空参构造器需要自己构造
   - 一个类可以创建多个重载的构造器
   - 父类的构造器不可被子类继承
   
#### 例1

```
public class Test001 {
	public static void main(String args[]) {
//		创建类的对象： new + 构造器Person()
		Person p1 = new Person();
		p1.eat();
		
		Person p2 = new Person("大户",12);
		p2.show();
	}
}

class Person{
	String name;
	int age;
	
	public Person() {
		System.out.println("Person....");
	}
	
	public Person(String n,int a) {
		name = n;
		age = a;
	}
	
	public void eat() {
		System.out.println("人吃饭");
	}
	
	public void study() {
		System.out.println("人学习");
	}
	
	public void show() {
		System.out.println("name=" + name + ", age=" + age);
	}
}
```

#### 练习
- 练习1. 在前面定义的Person类中添加构造器，利用构造器设置所有人的age属性初始值都为18。
- 练习2. 修改上题中类和构造器，增加name属性,使得每次创建Person对象的同时初始化对象的age属性值和name属性值。

```
package javatest001;

public class Test001 {
	public static void main(String args[]) {
//		创建类的对象： new + 构造器Person()
		Person p1 = new Person();
		p1.eat();
		p1.show();
		Person p2 = new Person("大户",12);
		p2.show();
	}
}

class Person{
	String name;
	int age;
	
	public Person() {
		System.out.println("Person....");
		age = 18;
		name = "null";
	}
	
	public Person(String n,int a) {
		name = n;
		age = a;
	}
	
	public void eat() {
		System.out.println("人吃饭");
	}
	
	public void study() {
		System.out.println("人学习");
	}
	
	public void show() {
		System.out.println("name=" + name + ", age=" + age);
	}
}
```

#### 练习3.编写两个类，TriAngle和TriAngleTest，

```
其中TriAngle类中声明私有的底边长base和高height，同时声明公共方法访问私有变量。
此外，提供类必要的构造器。另一个类中使用这些公共方法，计算三角形的面积。
```

```
public class Test002 {
	public static void main(String args[]) {
		TriAngle t = new TriAngle(3,4);
		t.getTriAngle();
		System.out.println(t.getArea(t));
	}
}

class TriAngle{
	private int base;
	private int height;
	
	public TriAngle() {
		System.out.println("null");
	}
	
	public TriAngle(int b, int h) {
		base = b;
		height = h;
	}
	
	public void getTriAngle() {
		System.out.println("base = " + base + ", height = " + height);
	}
	
	public int getArea(TriAngle t) {
		return t.base * t.height / 2;
	}
}
```

### 构造器重载

- 构造器一般用来创建对象的同时初始化对象。如

```
class Person{
    String name;
    int age;
    
    public Person(String n , int a){ 
        name=n; age=a;
    }
}
```
- 构造器重载使得对象的创建更加灵活，方便创建各种不同的对象。
- 构造器重载举例：

```
public class Person{
    public Person(String name, int age, Date d) {
        this(name,age);
        …
    }
    public Person(String name, int age) {…}
    public Person(String name, Date d) {…}
    public Person(){…}
}
```

- 构造器重载，参数列表必须不同

#### 构造器重载举例

```
public class Person { 
    private String name;
    private int age;
    private Date birthDate;

    public Person(String n, int a, Date d) {
        name = n;
        age = a;
        birthDate = d;
    }
    
    public Person(String n, int a) {
        name = n;
        age = a;
    }

    public Person(String n, Date d) {
        name = n;
        birthDate = d;
    }

    public Person(String n) {
        name = n;
        age = 30;
    }
}
```

#### 练习
```
(1)定义Student类,有4个属性：
String name; 
int age; 
String school; 
String major;
(2)定义Student类的3个构造器:
第一个构造器Student(String n, int a)设置类的name和age属性；
第二个构造器Student(String n, int a, String s)设置类的name, age 和school属性；
第三个构造器Student(String n, int a, String s, String m)设置类的name, age ,school和major属性；
(3)在main方法中分别调用不同的构造器创建的对象，并输出其属性值。
```

```
public class Test003 {
	public static void main(String args[]) {
		Student s1 = new Student("1号",12);
		Student s2 = new Student("2号",14,"2号学府");
		Student s3 = new Student("3号",15,"3号学府","3号职务");
		
		s1.show();
		s2.show();
		s3.show();
	}
}

class Student{
	private String name;
	private int age;
	private String school;
	private String major;
	
	public Student(String n, int a) {
		name = n;
		age = a;
	}
	
	public Student(String n, int a, String s) {
		name = n;
		age = a;
		school = s;
	}
	
	public Student(String n, int a, String s, String m) {
		name = n;
		age = a;
		school = s;
		major = m;
	}
	
	public void show() {
		System.out.println("name = " + name + " , age = " + age + " , school = " + school + " , major = " + major);
	}
}
```

## 属性赋值过程

- 赋值的位置：
   - ① 默认初始化
   - ② 显式初始化
   - ③ 构造器中初始化
   - ④ 通过“对象.属性“或“对象.方法”的方式赋值

- 赋值的先后顺序：
   - ① - ② - ③ - ④
   
## JavaBean
- JavaBean是一种Java语言写成的可重用组件。
- 所谓javaBean，是指符合如下标准的Java类：
   - 类是公共的
   - 有一个无参的公共的构造器
   - 有属性，且有对应的get、set方法
- 用户可以使用JavaBean将功能、处理、值、数据库访问和其他任何可以用Java代码创造的对象进行打包，并且其他的开发者可以通过内部的JSP页面、Servlet、其他JavaBean、applet程序或者应用来使用这些对象。用户可以认为JavaBean提供了一种随时随地的复制和粘贴的功能，而不用关心任何改变。

```java
public class JavaBean {
	private String name; // 属性一般定义为private
	private int age;
	
	public JavaBean() {
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int a) {
		age = a;
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}
}
```

```
public class Customer {
	private int id;
	private String name;
	
	public Customer() {
		
	}
	
	public void setId(int i) {
		id = i;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
}
```

## UML类图

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/332602419227254.png =746x)

## this 关键字

- 它在方法内部使用，即这个方法所属对象的引用；
- 它在构造器内部使用，表示该构造器正在初始化的对象。
- this 可以调用类的属性、方法和构造器
- 什么时候使用this关键字呢？
   - 当在方法内需要用到调用该方法的对象时，就用this。(当需要在方法中使用对象时)
   - 具体的：我们可以用this来区分属性和局部变量。(类的属性与方法的形参同名时)
   - 比如：this.name = name;
   
```
public class PersonTest {
	public static void main(String args[]) {
		Person p1 = new Person();
		
		p1.setAge(1);
		System.out.println(p1.getAge());
		p1.eat();
	}
}

class Person{
	private String name;
	private int age;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name; //自动加上this.
//		return this.name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
	
	public void eat() {
		System.out.println("吃饭");
		study(); //自动加上this.
//		this.study();
	}
	
	public void study() {
		System.out.println("study");
	}
}
```

1. 在任意方法或构造器内，如果使用当前类的成员变量或成员方法可以在其前面添加this，增强程序的阅读性。不过，通常我们都习惯省略this。
2. 当形参与成员变量同名时，如果在方法内或构造器内需要使用成员变量，必须添加this来表明该变量是类的成员变量
3. 使用this访问属性和方法时，如果在本类中未找到，会从父类中查找
4. this可以作为一个类中构造器相互调用的特殊格式

- 注意：
   - 可以在类的构造器中使用"this(形参列表)"的方式，调用本类中重载的其他的构造器！
- 明确：
   - 构造器中不能通过"this(形参列表)"的方式调用自身构造器
   - 如果一个类中声明了n个构造器，则最多有 n - 1个构造器中使用了"this(形参列表)"
   - "this(形参列表)"必须声明在类的构造器的首行！
   - 在类的一个构造器中，最多只能声明一个"this(形参列表)"
   
```
public class PersonTest {
	public static void main(String args[]) {
		Person p1 = new Person();
		
		p1.setAge(1);
		System.out.println(p1.getAge());
		p1.eat();
		
		Person p2 = new Person("Jerry",20);
	}
}

class Person{
	private String name;
	private int age;
	
	public Person() {
//      this() //构造器中不能通过"this(形参列表)"的方式调用自身构造器		

//      this(age) //与Person(int age)[有this()]形成一个死循环
//Cannot refer to an instance field age while explicitly invoking a constructor
        eat();
		
		String info = "hello----------";
		System.out.println(info);
	}
	
	public Person(String name) {
		this();  //调用Person()
		this.name = name;
	}
	
	public Person(int age) {
		this();
		this.age = age;
	}
	
	public Person(String name, int age) {
//	    this()  //在类的一个构造器中，最多只能声明一个"this(形参列表)"
		this(age);
		this.name = name;
		this.age = age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
}
```
### 练习 添加必要的构造器，综合应用构造器的重载，this关键字

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/139281721239387.png =558x)

```
package javatest003;

public class Boy {
	private String name;
	private int age;
		
	public Boy() {
		
	}
	
	public Boy(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void marry(Girl girl) {
		System.out.println("娶" + girl.getName());
	}
	public void shout() {
		if(age >= 22) {
			System.out.println("登记结婚");
		}
		else {
			System.out.println("不可");
		}
	}
}
```

```
package javatest003;

public class Girl {
	private String name;
	private int age;
	
	public Girl() {
		
	}
	
	public Girl(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void marry(Boy boy) {
		System.out.println("嫁" + boy.getName());
		boy.marry(this);  //this代表当前对象
	}
	
	/**
	 * @Description 比较两个对象的大小
	 * @param girl
	 * @return 正数：当前对象大；负数：当前对象小; 0：当前对象与形参对象相等
	 */
	public int compare(Boy boy) {
//		if(this.age > girl.age) {
//			return 1;
//		}
//		else if(this.age < girl.age){
//			return -1;
//		}
//		else {
//			return 0;
//		}
		
		return this.age - boy.getAge();
	}
}
```

```
package javatest003;

public class BoyGirlTest {
	public static void main(String args[]) {
		Boy boy = new Boy("郑继凯",22);
		boy.shout();
		
		Girl girl = new Girl("韦章倩",23);
		girl.marry(boy);
		
		int compare = girl.compare(boy);
		if(compare > 0) {
			System.out.println(girl.getName() + "大");
		}
		else if(compare < 0) {
			System.out.println(girl.getName() + "小");
		}
		else {
			System.out.println(girl.getName() + "和" + boy.getName() + "一样大");
		}
	}
	
}
```

## 实验1：Account_Customer.doc

### 要求
```
1、写一个名为 Account 的类模拟账户。该类的属性和方法如下图所示。
该类包括的属性：
账号 id，余额 balance，年利率 annualInterestRate；
包含的方法：
访问器方法（getter 和 setter方法），取款方法 withdraw()，存款方法 deposit()。
```
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/329575522240089.png =461x)

```
2. 创建 Customer 类。
```
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/12335622236644.png =465x)

```
a. 声明三个私有对象属性：firstName、lastName 和 account。
b. 声明一个公有构造器，这个构造器带有两个代表对象属性的参数（f 和 l）
c. 声明两个公有存取器来访问该对象属性，方法 getFirstName 和 getLastName 返回相应的属性。
d. 声明 setAccount 方法来对 account 属性赋值。
e. 声明 getAccount 方法以获取 account 属性。
```
```
3.写一个测试程序。
（1） 创建一个 Customer ，名字叫 Jane Smith, 
      他有一个账号为 1000，余额为 2000 元，年利率为 1.23％ 的账户。
（2） 对 Jane Smith 操作。
     存入 100 元，再取出 960 元。再取出 2000 元。
     打印出 Jane Smith 的基本信息
```
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/287495722232398.png =588x)

### 完成

```
package account.customer;

public class Account {
	private int id;
	private double balance; //余额
	private double annualInterestRate; //年利率
	
	public Account(int id, double balance, double annualInterestRate) {
		this.id = id;
		this.balance = balance;
		this.annualInterestRate = annualInterestRate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(double annuallnterestRate) {
		this.annualInterestRate = annuallnterestRate;
	}
	
//	存钱
	public void withdraw(double amount) {
		balance += amount;
		System.out.println("成功存入: " + amount);
	}

//	取钱
	public void deposit(double amount) {
		if(balance >= amount) {
			balance -= amount;
			System.out.println("成功取出: " + amount);
		}
		else {
			System.out.println("余额不足，取款失败");
		}
	}
}
```

```
package account.customer;

public class Customer {
	private String firstName;
	private String lastName;
	private Account account;
	
	public Customer(String f, String l) {
		firstName = f;
		lastName = l;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public void show() {
		System.out.println("Customer[" + firstName + "," + lastName + "] has a account : id is" + account.getId() + ",annualInterestRate is" + account.getAnnualInterestRate() + ",balance is" + account.getBalance());
	}
}
```

```
package account.customer;

public class Test {
	public static void main(String args[]) {
		Customer c1 = new Customer("Jane","Smith");
		Account a1 = new Account(1000,2000,1.23);
		
		c1.setAccount(a1);
		
		c1.getAccount().withdraw(100);
		c1.getAccount().deposit(960);
		c1.getAccount().deposit(2000);
		c1.show();
	}
}
```

## 实验2：Account_Customer_Bank.doc
### 要求
```
1.按照如下的 UML 类图，创建相应的类，提供必要的结构
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/551995923247882.png =277x)

```
在提款方法 withdraw()中，需要判断用户余额是否能够满足提款数额的要求，如果不能，应给出提示。
deposit()方法表示存款。
```

```
2. 按照如下的 UML 类图，创建相应的类，提供必要的结构
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/69510200245385.png =231x)

```
3. 按照如下的 UML 类图，创建相应的类，提供必要的结构
 addCustomer 方法必须依照参数（姓，名）构造一个新的 Customer 对象，然后把它放到 customer 数组中。还必须把 numberOfCustomer 属性的值加 1。
 getNumOfCustomers 方法返回 numberofCustomers 属性值。
 getCustomer 方法返回与给出的 index 参数相关的客户。
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/79480300226626.png =300x)

```
4. 创建 BankTest 类，进行测试
```

### 完成

```
package com.zjk;

public class Account {
	
	private double balance;
	
	public Account (double initBalance) {
		balance = initBalance;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void deposit(double amt) {
		balance += amt;
		System.out.println("成功存入: " + amt);
	}
	
	public void withdraw(double amt) {
		if(balance < amt) {
			System.out.println("余额不足");
		}
		if(balance >= amt) {
			System.out.println("成功取出: " + amt);
			balance -= amt;
		}
	}
}
```

```
package com.zjk;

public class Customer {
	private String firstName;
	private String lastName;
	private Account account;
	
	public Customer(String f, String l) {
		firstName = f;
		lastName = l;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account acc) {
		this.account = acc;
	}
}
```

```
package com.zjk;

public class Bank {
	private Customer[] customers;
	private int numberOfCustomers = 0;
	
	public Bank() {
		customers = new Customer[10];
	}
	
	public void addCustomer(String f,String l) {
//		需要先造数组
		customers[numberOfCustomers++] = new Customer(f,l);  
//		java.lang.NullPointerException
//		private Customer[] customers; 数组还未造
	}
	
	public int getNumOfCustomers() {
		return numberOfCustomers;
	}
	
	public Customer getCustomer(int index) {
		if(index >= 0 && index < numberOfCustomers) {
			return customers[index];
		}
		else {
			return null;
		}
	}
}
```

```
package com.zjk;

public class Test {
	public static void main(String args[]) {
		Bank bank = new Bank();
		bank.addCustomer("理事", "国家");
		Account a = new Account(100);
		bank.getCustomer(0).setAccount(a);
		
		System.out.println("银行用户数： " + bank.getNumOfCustomers());
		
		System.out.println(bank.getCustomer(0).getFirstName() + bank.getCustomer(0).getLastName() + "的余额：" +bank.getCustomer(0).getAccount().getBalance());
		bank.getCustomer(0).getAccount().deposit(1000);
		System.out.println(bank.getCustomer(0).getFirstName() + bank.getCustomer(0).getLastName() + "的余额：" +bank.getCustomer(0).getAccount().getBalance());
		bank.getCustomer(0).getAccount().withdraw(1000);
		bank.getCustomer(0).getAccount().withdraw(1000);
	}
}
```

## package关键字

- package语句作为Java源文件的第一条语句，指明该文件中定义的类所在的包。(若缺省该语句，则指定为无名包)。它的格式为：
    - package 顶层包名.子包名 ;
    - 举例：pack1\pack2\PackageTest.java

```
package pack1.pack2; //指定类PackageTest属于包pack1.pack2

public class PackageTest{
    public void display(){
        System.out.println("in method display()");
    }
}
```

- 包对应于文件系统的目录，package语句中，用 “.” 来指明包(目录)的层次；
- 包通常用小写单词标识。通常使用所在公司域名的倒置：com.atguigu.xxx

**源文件布局：**


**包的作用：**
- 包帮助管理大型软件系统：将功能相近的类划分到同一个包中。比如：MVC的设计模式
- 包可以包含类和子包，划分项目层次，便于管理
- 解决类命名冲突的问题
- 控制访问权限
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/102931408239388.png =656x)

### MVC设计模式

MVC是常用的设计模式之一，将整个程序分为三个层次：视图模型层，控制器层，与数据模型层。这种将程序输入输出、数据处理，以及数据的展示离开来的设计模式使程序结构变的灵活而且清晰，同时也描述了程序各个对象间的通信方式，降低了程序的耦合性。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/70001508227255.png =769x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/300021508247421.png =549x)

### JDK中主要的包介绍
1. java.lang----包含一些Java语言的核心类，如String、Math、Integer、 System和Thread，提供常用功能
2. java.net----包含执行与网络相关的操作的类和接口。
3. java.io ----包含能提供多种输入/输出功能的类。
4. java.util----包含一些实用工具类，如定义系统特性、接口的集合框架类、使用与日期日历相关的函数。
5. java.text----包含了一些java格式化相关的类
6. java.sql----包含了java进行JDBC数据库编程的相关类/接口
7. java.awt----包含了构成抽象窗口工具集（abstract window toolkits）的多个类，这些类被用来构建和管理应用程序的图形用户界面(GUI)。 B/S C/S

## import关键字

- 为使用定义在不同包中的Java类，需用import语句来引入指定包层次下所需要的类或全部类`(.*)`。import语句告诉编译器到哪里去寻找类。
- 语法格式：`import 包名. 类名;`
- 应用举例：

```
import pack1.pack2.Test; //import pack1.pack2.*;表示引入pack1.pack2包中的所有结构

public class PackTest{
    public static void main(String args[]){
        Test t = new Test(); //Test类在pack1.pack2包中定义
        t.display();
    }
}
```

```
package com.zjk;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;

//等同于

import java.util.*;

public class PackageimportTest {
	public static void main(String args[]) {
		String info = Arrays.toString(new int[] {1,2,3};
		
		Circle c1 = new Circle();
		
		ArrayList list = new ArrayList();
		HashMap map = new HashMap();
	}
}
```

- 注意：
1. 在源文件中使用import显式的导入指定包下的类或接口
2. 声明在包的声明和类的声明之间。
3. 如果需要导入多个类或接口，那么就并列显式多个import语句即可
4. 举例：可以使用java.util.*的方式，一次性导入util包下所有的类或接口。
5. 如果导入的类或接口是java.lang包下的，或者是当前包下的，则可以省略此import语句。
6. 如果在代码中使用不同包下的同名的类。那么就需要使用类的全类名的方式指明调用的是哪个类。
7. 如果已经导入java.a包下的类。那么如果需要使用a包的子包下的类的话，仍然需要导入。
8. import static组合的使用：调用指定类或接口下的静态的属性或方法

```
package com.zjk;

import java.util.Date;

import com.zjk.account001.Account;
//import com.zjk.account002.Account;
//已经存在同名类的引用

public class PackageimportTest {
	public static void main(String args[]) {
		Account acc = new Account(1000,10.2,0.1);
		com.zjk.account002.Account acc2 = new com.zjk.account002.Account(100);
//		引用另一个包中的同名类: 要写出完整的
		
		Date date = new Date();
		java.sql.Date date1 = new java.sql.Date(123);
	}
}
```

```
package com.zjk;

import static java.lang.System.*;
import static java.lang.Math.*;

public class PackageimportTest {
	public static void main(String args[]) {
		out.println("hello");
		
		long num = Math.abs(11);
	}
}
```

## 项目二

### 需求
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/525044422220964.png =555x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/81074522239390.png =703x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/241254522227257.png =693x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/488944522247423.png =512x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/590444522240092.png =606x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/92914622236647.png =535x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/183244622232401.png =560x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/289644622250281.png =688x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/513334622247885.png =544x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/16044722245387.png =714x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/123824722226628.png =696x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/215394722249068.png =583x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/313484722244204.png =686x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/410194722237750.png =628x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/501814722230884.png =585x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/43074822221414.png =663x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/151314822223918.png =668x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/272374822232865.png =678x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/51085322225750.png =650x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/146845322226359.png =697x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/260115322253314.png =709x)
### 实现

CMUtility 
```
package com.atguigu.p2;


import java.util.*;
/**
CMUtility工具类：
将不同的功能封装为方法，就是可以直接通过调用方法使用它的功能，而无需考虑具体的功能实现细节。
*/
public class CMUtility {
    private static Scanner scanner = new Scanner(System.in);
    /**
	用于界面菜单的选择。该方法读取键盘，如果用户键入’1’-’5’中的任意字符，则方法返回。返回值为用户键入字符。
	*/
	public static char readMenuSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' && 
                c != '3' && c != '4' && c != '5') {
                System.out.print("选择错误，请重新输入：");
            } else break;
        }
        return c;
    }
	/**
	从键盘读取一个字符，并将其作为方法的返回值。
	*/
    public static char readChar() {
        String str = readKeyBoard(1, false);
        return str.charAt(0);
    }
	/**
	从键盘读取一个字符，并将其作为方法的返回值。
	如果用户不输入字符而直接回车，方法将以defaultValue 作为返回值。
	*/
    public static char readChar(char defaultValue) {
        String str = readKeyBoard(1, true);
        return (str.length() == 0) ? defaultValue : str.charAt(0);
    }
	/**
	从键盘读取一个长度不超过2位的整数，并将其作为方法的返回值。
	*/
    public static int readInt() {
        int n;
        for (; ; ) {
            String str = readKeyBoard(2, false);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("数字输入错误，请重新输入：");
            }
        }
        return n;
    }
	/**
	从键盘读取一个长度不超过2位的整数，并将其作为方法的返回值。
	如果用户不输入字符而直接回车，方法将以defaultValue 作为返回值。
	*/
    public static int readInt(int defaultValue) {
        int n;
        for (; ; ) {
            String str = readKeyBoard(2, true);
            if (str.equals("")) {
                return defaultValue;
            }

            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("数字输入错误，请重新输入：");
            }
        }
        return n;
    }
	/**
	从键盘读取一个长度不超过limit的字符串，并将其作为方法的返回值。
	*/
    public static String readString(int limit) {
        return readKeyBoard(limit, false);
    }
	/**
	从键盘读取一个长度不超过limit的字符串，并将其作为方法的返回值。
	如果用户不输入字符而直接回车，方法将以defaultValue 作为返回值。
	*/
    public static String readString(int limit, String defaultValue) {
        String str = readKeyBoard(limit, true);
        return str.equals("")? defaultValue : str;
    }
	/**
	用于确认选择的输入。该方法从键盘读取‘Y’或’N’，并将其作为方法的返回值。
	*/
    public static char readConfirmSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("选择错误，请重新输入：");
            }
        }
        return c;
    }

    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line = "";

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                if (blankReturn) return line;
                else continue;
            }

            if (line.length() < 1 || line.length() > limit) {
                System.out.print("输入长度（不大于" + limit + "）错误，请重新输入：");
                continue;
            }
            break;
        }

        return line;
    }
}

```
Customer
```
package com.atguigu.p2;

public class Customer {
	private String name;
	private char gender;
	private int age;
	private String phone;
	private String email;

	public Customer(String name, char gender, int age) {
		this(name, gender, age, "", "");
	}

	public Customer(String name, char gender, int age, String phone,
			String email) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDetails() {
		return name + "\t" + gender + "\t" + age + "\t\t" + phone + "\t" + email;
	}
}
```
CustomerList
```
package com.atguigu.p2;
public class CustomerList {
    private Customer[] customers;
    private int total = 0;

    public CustomerList(int totalCustomer) {
        customers = new Customer[totalCustomer];
    }

    public boolean addCustomer(Customer customer) {
        if (total >= customers.length) return false;
        
        customers[total++] = customer;
        return true;
    }
     
    public boolean replaceCustomer(int index, Customer cust) {
        if (index < 0 || index >= total) return false;
        
        customers[index] = cust;
        return true;
    }

    public boolean deleteCustomer(int index) {
        if (index < 0 || index >= total) return false;
        
        for (int i = index; i < total - 1; i++) {
            customers[i] = customers[i + 1];
        }
        
        customers[--total] = null;

        return true;
    }

    public Customer[] getAllCustomers() {
        Customer[] custs = new Customer[total];
        for (int i = 0; i < total; i++) {
            custs[i] = customers[i];
        }
        return custs;
    }

    public int getTotal() {
        return total;
    }

    public Customer getCustomer(int index) {
        if (index < 0 || index >= total) return null;
        
        return customers[index];
    }
}
```
CustomerView
```
package com.atguigu.p2;

public class CustomerView {
	private CustomerList customers = new CustomerList(10);

	public CustomerView() {
		Customer cust = new Customer("张三", '男', 30, "010-56253825",
				"abc@email.com");
		customers.addCustomer(cust);
	}

	public void enterMainMenu() {
		boolean loopFlag = true;
		do {
			System.out
					.println("\n-----------------客户信息管理软件-----------------\n");
			System.out.println("                   1 添 加 客 户");
			System.out.println("                   2 修 改 客 户");
			System.out.println("                   3 删 除 客 户");
			System.out.println("                   4 客 户 列 表");
			System.out.println("                   5 退       出\n");
			System.out.print("                   请选择(1-5)：");

			char key = CMUtility.readMenuSelection();
			System.out.println();
			switch (key) {
			case '1':
				addNewCustomer();
				break;
			case '2':
				modifyCustomer();
				break;
			case '3':
				deleteCustomer();
				break;
			case '4':
				listAllCustomers();
				break;
			case '5':
				System.out.print("确认是否退出(Y/N)：");
				char yn = CMUtility.readConfirmSelection();
				if (yn == 'Y')
					loopFlag = false;
				break;
			}
		} while (loopFlag);
	}

	private void addNewCustomer() {
		System.out.println("---------------------添加客户---------------------");
		System.out.print("姓名：");
		String name = CMUtility.readString(4);
		System.out.print("性别：");
		char gender = CMUtility.readChar();
		System.out.print("年龄：");
		int age = CMUtility.readInt();
		System.out.print("电话：");
		String phone = CMUtility.readString(15);
		System.out.print("邮箱：");
		String email = CMUtility.readString(15);

		Customer cust = new Customer(name, gender, age, phone, email);
		boolean flag = customers.addCustomer(cust);
		if (flag) {
			System.out
					.println("---------------------添加完成---------------------");
		} else {
			System.out.println("----------------记录已满,无法添加-----------------");
		}
	}

	private void modifyCustomer() {
		System.out.println("---------------------修改客户---------------------");

		int index = 0;
		Customer cust = null;
		for (;;) {
			System.out.print("请选择待修改客户编号(-1退出)：");
			index = CMUtility.readInt();
			if (index == -1) {
				return;
			}

			cust = customers.getCustomer(index - 1);
			if (cust == null) {
				System.out.println("无法找到指定客户！");
			} else
				break;
		}

		System.out.print("姓名(" + cust.getName() + ")：");
		String name = CMUtility.readString(4, cust.getName());

		System.out.print("性别(" + cust.getGender() + ")：");
		char gender = CMUtility.readChar(cust.getGender());

		System.out.print("年龄(" + cust.getAge() + ")：");
		int age = CMUtility.readInt(cust.getAge());

		System.out.print("电话(" + cust.getPhone() + ")：");
		String phone = CMUtility.readString(15, cust.getPhone());

		System.out.print("邮箱(" + cust.getEmail() + ")：");
		String email = CMUtility.readString(15, cust.getEmail());

		cust = new Customer(name, gender, age, phone, email);

		boolean flag = customers.replaceCustomer(index - 1, cust);
		if (flag) {
			System.out
					.println("---------------------修改完成---------------------");
		} else {
			System.out.println("----------无法找到指定客户,修改失败--------------");
		}
	}

	private void deleteCustomer() {
		System.out.println("---------------------删除客户---------------------");

		int index = 0;
		Customer cust = null;
		for (;;) {
			System.out.print("请选择待删除客户编号(-1退出)：");
			index = CMUtility.readInt();
			if (index == -1) {
				return;
			}

			cust = customers.getCustomer(index - 1);
			if (cust == null) {
				System.out.println("无法找到指定客户！");
			} else
				break;
		}

		System.out.print("确认是否删除(Y/N)：");
		char yn = CMUtility.readConfirmSelection();
		if (yn == 'N')
			return;

		boolean flag = customers.deleteCustomer(index - 1);
		if (flag) {
			System.out
					.println("---------------------删除完成---------------------");
		} else {
			System.out.println("----------无法找到指定客户,删除失败--------------");
		}
	}

	private void listAllCustomers() {
        System.out.println("---------------------------客户列表---------------------------");
        Customer[] custs = customers.getAllCustomers();
        if (custs.length == 0) {
            System.out.println("没有客户记录！");
        } else {
            System.out.println("编号\t姓名\t性别\t年龄\t\t电话\t\t邮箱");
            for (int i = 0; i < custs.length; i++) {
//            System.out.println(i + 1 + "\t" + custs[i].getName() + "\t" + custs[i].getGender() + "\t" + custs[i].getAge() + "\t\t" + custs[i].getPhone() + "\t" + custs[i].getEmail());
            	System.out.println((i+1) + "\t" + custs[i].getDetails());
            }
        }

        System.out.println("-------------------------客户列表完成-------------------------");
    }

	public static void main(String[] args) {
		CustomerView cView = new CustomerView();
		cView.enterMainMenu();
	}
}
```
## 面向对象特征之二：继承性

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/452381510220962.png =588x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/585981510239388.png =559x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/142261610227255.png =674x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/305161610247421.png =602x)

```
package com.zjk;

public class Person {

	private String name;
	private int age;

	public void eat() {
		System.out.println("吃饭");
	}

	private void sleep() {
		System.out.println("睡觉");
	}
	
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person() {

	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

package com.zjk;

public class Student extends Person{
	private String school;
	
	public void study() {
		System.out.println("学习");
	}
	
	public void show() {
//		System.out.println("name=" + name + "school=" + school);
//		The field Person.name is not visible
//		name Person类中私有private 在子类Student中需要使用Person的公有方法调用
		
		System.out.println("name=" + getName() + "school=" + school);
	}
}

package com.zjk;

public class ExtendsTest {
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.eat();
		
		Student s1 = new Student();
		s1.eat();
//		s1.sleep();
//		The method sleep() from the type Person is not visible
//		Person类的sleep()私有private
	}
}

```


- 为什么要有继承？
    - 多个类中存在相同属性和行为时，将这些内容抽取到单独一个类中，那么多个类无需再定义这些属性和行为，只要继承那个类即可。
- 此处的多个类称为子类(派生类)，单独的这个类称为父类(基类或超类)。可以理解为:“子类 is a 父类”
- 类继承语法规则:

```
class Subclass extends SuperClass{ }
```
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/247221810240090.png =498x)
- 作用：
   1. 继承的出现减少了代码冗余，提高了代码的复用性。
   2. 继承的出现，更有利于功能的扩展。
   3. 继承的出现让类与类之间产生了关系，提供了多态的前提。
- 注意：不要仅为了获取其他类中某个功能而去继承
   - 子类继承了父类，就继承了父类的方法和属性。(获取了直接父类和所有间接父类中声明的属性和方法)
   - 在子类中，可以使用父类中定义的方法和属性，也可以创建新的数据和方法。
   - 在Java 中，继承的关键字用的是“extends”，即子类不是父类的子集，而是对父类的“扩展”。
- 类的继承不改变类成员的访问权限，也就是说，如果父类的成员是公有的、被保护的或默认的，它的子类仍具有相应的这些特性，并且子类不能获得父类的构造方法。
- 注意：如果在父类中存在有参的构造方法而并没有重载无参的构造方法，那么在子类中必须含有有参的构造方法，因为如果在子类中不含有构造方法，默认会调用父类中无参的构造方法，而在父类中并没有无参的构造方法，因此会出错。
- 关于继承的规则：
   - 子类不能直接访问父类中私有的(private)的成员变量和方法(封装性)。(获得了)
   
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/19012010232399.png =350x)

- Java只支持单继承和多层继承，不允许多重继承
   - 一个子类只能有一个父类
   - 一个父类可以派生出多个子类

``` 
class SubDemo extends Demo{ } //ok
class SubDemo extends Demo1,Demo2...//error
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/173902110250279.png =692x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/308232110247883.png =632x)

### java.lang.Object类(根父类)
- 如果我们没有显示的声明一个类的父类时，则此类继承于java.lang.Object类
- 所有的java类(除java.lang.Object类之外)都直接或间接的继承于java.lang.Object类
- 意味着，所有的java类都具有java.lang.Object类声明的功能

```
package com.zjktr;

public class Creature {

}

package com.zjktr;

public class Test {
	public static void main(String[] args) {
		Creature c = new Creature();

		System.out.println(c.toString());
		//Creature类继承自java.lang.Object类的toString()
	}
}

```

### 练习1
#### 1.定义一个学生类Student，它继承自Person类

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/54332210226626.png =419x)

```
public class Person {
	String name;
	char sex;
	int age;
	
	public Person(String name, char sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	
	public String toString() {
		return "person";
	}
}

class Student extends Person{
	long number;
	int math;
	int english;
	int computer;
	
	public Student(String n, char s, int a, long k, int m, int e, int c) {
		super(n,s,a); 
		number = k;
		math = m;
		english = e;
		computer = c;
	}
	
	public double aver() {
		return (math + english + computer) / 3;
	}
	
	public int max() {
		int maxValue = math > english ? math : english;
		maxValue = maxValue > computer ? maxValue : computer;
		return maxValue;
	}
	
	public int min() {
		int min = math < english ? math : english;
		return min = min < computer ? min : computer;
	}
	
	public String toString() {
		return "student";
	}
}
```

#### 
```
(1)定义一个ManKind类，包括
成员变量int sex和int salary；
方法void manOrWoman()：根据sex的值显示“man”(sex==1)或者“woman”(sex==0)；
方法void employeed()：根据salary的值显示“no job”(salary==0)或者“ job”(salary!=0)。
(2)定义类Kids继承ManKind，并包括
成员变量int yearsOld；
方法printAge()打印yearsOld的值。
(3)定义类KidsTest，在类的main方法中实例化Kids的对象someKid，用该对象访问其父类的成员变量及方法。
```

```
package com.zjk;

public class ManKind {
	int sex;
	int salary;
	
	public void manOrWoman() {
		if(sex == 1) {
			System.out.println("man");
		}
		else if(sex == 0) {
			System.out.println("woman");
		}
		else {
			System.out.println("有误");
		}
	}
	
	public void employeed() {
		if(salary == 0) {
			System.out.println("no job");
		}
		else if(salary > 0){
			System.out.println("job");
		}
	}
}

class Kids extends ManKind{
	int yearsOld;
	
	public void printAge() {
		System.out.println("old: " + yearsOld);
	}
}
```

```
package com.zjk;

public class kidTest {
	public static void main(String args[]) {
		Kids somekid = new Kids();
		somekid.sex = 1;
		somekid.manOrWoman();
	}
}
```

#### 根据下图实现类。在CylinderTest类中创建Cylinder类的对象，设置圆柱的底面半径和高，并输出圆柱的体积。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/382592510244202.png =371x)

```
package com.zjk;

public class Circle {
	private double radius;
	
	Circle(){
		radius = 1;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double findArea() {
		return radius * radius * Math.PI;
	}
}

class Cylinder extends Circle{
	private double length;
	
	Cylinder(){
		length = 1;
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public double getLength() {
		return length;
	}
	
	public double findVolume() {
		return super.findArea() * length;
	}
}
```

```
package com.zjk;

public class CircleTest {
	public static void main(String args[]) {
		Cylinder cy = new Cylinder();
		
		cy.setLength(2);
		cy.setRadius(3);
		System.out.println(cy.findVolume());
	}
}
```

### 方法的重写(同名同参)

- 定义：在子类中可以根据需要对从父类中继承来的方法进行改造，也称为方法的重置、覆盖。在程序执行时，子类的方法将覆盖父类的方法。
- 要求：
   1. **子类重写的方法必须和父类被重写的方法具有相同的方法名称、参数列表**
   2. **子类重写的方法的返回值类型不能大于父类被重写的方法的返回值类型**
   - 父类被重写的方法的返回值是void，则子类重写的方法的返回值类型只能是void
   - 父类被重写的方法的返回值是A类型，则子类重写的方法的返回值类型可以是A类或A类的子类
   - 父类被重写的方法的返回值是基本数据类型，则子类重写的方法的返回值类型必须是相同的基本数据类型(父类double则子类只能是double)
   3. **子类重写的方法使用的访问权限不能小于父类被重写的方法的访问权限**
   - 子类不能重写父类中声明为private权限的方法
   4. **子类方法抛出的异常不能大于父类被重写方法抛出的异常**
- 注意：
   - 子类与父类中同名同参数的方法必须同时声明为非static的(即为重写)，或者同时声明为static的（不是重写）。
      - 因为static方法是属于类的，子类无法覆盖父类的方法。静态的方法不能被覆盖，是随着类的加载而加载。
 
#### 区分方法的重载和重写

#### 例1
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/124002610237748.png =733x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/247452610230882.png =704x)


#### 例2

```
package com.zjk;

public class Person {
	String name;
	int age;

	public Person() {

	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void eat() {
		System.out.println("吃饭");
	}

	public void walk(int distance) {
		System.out.println("走路: " + distance + "km");
	}

	private void show() {
		System.out.println("我是人");
	}

	public void test() {
		show();
		eat();
	}
	
	public Object info() {
		return null;
	}
	
	public double info1() {
		return 0.0;
	}
}

package com.zjk;

public class Student extends Person{
	String major;

	public Student() {

	}

	public Student(String major) {
		this.major = major;
	}

	public void study() {
		System.out.println("学习");
	}
	
	//重写
	public void eat() {
		System.out.println("多吃营养的");
	}
	
//	Person类中的show()方法是private权限，Student类不可见
//	此时不认为是重写，
	public void show() {
		System.out.println("我是学生");
	}
	
//	public Object info() {
	public String info() { //String类是Object类的子类
		return null;
	}
	
//	The return type is incompatible with Person.info1()
//	public float info1() {
//		return 1.1f;
//	}
//  返回类型:父类double则子类只能是double
	public double info1() {
		return 0.0;
	}
}

package com.zjk;

public class PersonTest {
	public static void main(String[] args) {
		Student s = new Student("计算机科学与技术");
		s.eat();
		s.walk(10);
		
		s.study();
		s.test();
		s.show(); //没有重写
		//如果重写，test()输出的应该是重写之后的方法
	}
}
```
#### 练习2

1.如果现在父类的一个方法定义成private访问权限，在子类中将此方法声明为default访问权限，那么这样还叫重写吗？(NO)
2. 修改练习1.2中定义的类Kids，在Kids中重新定义employeed()方法，覆盖父类ManKind中定义的employeed()方法，输出“Kids should study and no job.”

```
package com.zjk;

public class ManKind {
	int sex;
	int salary;
	
	public void manOrWoman() {
		if(sex == 1) {
			System.out.println("man");
		}
		else if(sex == 0) {
			System.out.println("woman");
		}
		else {
			System.out.println("有误");
		}
	}
	
	public void employeed() {
		if(salary == 0) {
			System.out.println("no job");
		}
		else if(salary > 0){
			System.out.println("job");
		}
	}
}

class Kids extends ManKind{
	int yearsOld;
	
	public void printAge() {
		System.out.println("old: " + yearsOld);
	}
	
	public void employeed() {
		System.out.println("kids should study and no job");
	}
}
```
 
### 继承的权限修饰符(同之前)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/579543022239392.png =391x)
### super关键字：

- 在Java类中使用super来调用父类中的指定操作：
    - super.属性 super可用于访问父类中定义的属性
    - supere.方法 super可用于调用父类中定义的成员方法
    - super(形参列表) super可用于在子类构造器中调用父类的构造器
- 注意：
    - 尤其当子父类出现同名成员时，可以用super表明调用的是父类中的成员
    - super的追溯不仅限于直接父类
    - super和this的用法相像，this代表本类对象的引用，super代表父类的内存空间的标识

#### 举例

##### 例1
```
class Person {
	protected String name = "张三";
	protected int age;

	public String getInfo() {
		return "Name: " + name + "\nage: " + age;
	}
}

class Student extends Person {
	protected String name = "李四";
	private String school = "New Oriental";

	public String getSchool() {
		return school;
	}

	public String getInfo() {
		return super.getInfo() + "\nschool: " + school;
		//调用父类Person的getInfo()方法
	}
}

public class StudentTest {
	public static void main(String[] args) {
		Student st = new Student();
		System.out.println(st.getInfo());
	}
}
```

- 我们可以通过super关键字来实现对父类成员的访问，用来引用当前对象的父类。

```
class Animal {
  void eat() {
    System.out.println("animal : eat");
  }
}
 
class Dog extends Animal {
  void eat() {
    System.out.println("dog : eat");
  }
  void eatTest() {
    this.eat();   // this 调用自己的方法
    super.eat();  // super 调用父类方法
  }
}
 
public class Test {
  public static void main(String[] args) {
    Animal a = new Animal();
    a.eat();
    Dog d = new Dog();
    d.eatTest();
  }
}
```

##### 例2
教师和学生都属于人，他们具有共同的属性：姓名、年龄、性别和身份证号，而学生还具有学号和所学专业两个属性，教师还具有教龄和所教专业两个属性。

下面编写 Java 程序代码，使教师（Teacher）类和学生（Student）类都继承于人（People）类，具体的实现步骤如下。
```
public class People {
    public String name; // 姓名
    public int age; // 年龄
    public String sex; // 性别
    public String sn; // 身份证号

    public People(String name, int age, String sex, String sn) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.sn = sn;
    }

    public String toString() {
        return "姓名：" + name + "\n年龄：" + age + "\n性别：" + sex + "\n身份证号：" + sn;
    }
}
```

```
public class Student extends People {
    private String stuNo; // 学号
    private String department; // 所学专业

    public Student(String name, int age, String sex, String sn, String stuno, String department) {
        super(name, age, sex, sn); // 调用父类中的构造方法
        this.stuNo = stuno;
        this.department = department;
    }

    public String toString() {
        return "姓名：" + name + "\n年龄：" + age + "\n性别：" + sex + "\n身份证号：" + sn + "\n学号：" + stuNo + "\n所学专业：" + department;
    }
}
```

```
public class Teacher extends People {
    private int tYear; // 教龄
    private String tDept; // 所教专业

    public Teacher(String name, int age, String sex, String sn, int tYear, String tDept) {
        super(name, age, sex, sn); // 调用父类中的构造方法
        this.tYear = tYear;
        this.tDept = tDept;
    }

    public String toString() {
        return "姓名：" + name + "\n年龄：" + age + "\n性别:" + sex + "\n身份证号：" + sn + "\n教龄：" + tYear + "\n所教专业：" + tDept;
    }
}
```

```
public class PeopleTest {
    public static void main(String[] args) {
        // 创建Student类对象
        People stuPeople = new Student("王丽丽", 23, "女", "410521198902145589", "00001", "计算机应用与技术");
        System.out.println("----------------学生信息---------------------");
        System.out.println(stuPeople);

        // 创建Teacher类对象
        People teaPeople = new Teacher("张文", 30, "男", "410521198203128847", 5, "计算机应用与技术");
        System.out.println("----------------教师信息----------------------");
        System.out.println(teaPeople);
    }
} 
```

#### 调用父类的构造器

- **子类中所有的构造器默认都会访问父类中空参数的构造器**
- **当父类中没有空参数的构造器时，子类的构造器必须通过this(参数列表)或者super(参数列表)语句指定调用本类或者父类中相应的构造器。同时，只能”二选一”，且必须放在构造器的首行。**
- 在类的多个构造器中，至少有一个类的构造器中使用了`super(形参列表)`调用父类中的构造器。
- 如果子类构造器中既未在首行显式调用父类`super(参数列表)`或本类`this(参数列表)`的构造器，且父类中又没有无参的构造器，则编译出错

#### this和super的区别
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/596003622227259.png =511x)


#### 例

```
package com.zjk;

public class Person {
	private String name;
	private int age;
	int id = 1001; // 身份证

	public Person() {

	}

	public Person(String name) {
		this.name = name;
	}

	public Person(String name, int age) {
		this(name);
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void eat() {
		System.out.println("eat");
	}

	public void walk() {
		System.out.println("walk");
	}
}

package com.zjk;

public class Student extends Person {
	String major;
	int id = 1002; // 学号

	public Student() {
		super(); 
//		子类中所有的构造器默认都会访问父类中空参数的构造器,
//		如果父类中没有默认空参构造器而子类构造器首行也没有super(参数列表)时报错
//		不管是否删除，在子类的构造器中都存在super(形参列表)
	}

	public Student(String major) {
		this.major = major;
	}

	public Student(String name, int age, String major) {
//		当父类的属性是public等对子类可见时：this
//		this.name  = name;
//		this.age = age;

//      当父类的属性对子类不可见时：如private: super
		super(name, age);
//      super(形参) 调用父类的构造器

		this.major = major;
	}

	@Override
	public void eat() {
		System.out.println("eat well");
	}

	public void study() {
		System.out.println("study");

	}

	public void show() {
		System.out.println("name=" +getName() 
		+ ",age=" + getAge() 
		+ ",major=" +major);
	}

	public void showId() {
		System.out.println("身份证：" + super.id + ", 学号：" + id);
//		子父类属性同名(不是重写)时：必须使用 super.属性 的方法来显式的调用父类的属性
//		super.属性 调用父类的属性
	}

	public void test() {
		this.eat();
		super.eat();
//		在子类中调用父类被重写的方法，必须使用 super.方法 的方式显示调用父类的方法
		walk();
	}
}

package com.zjk;

public class SuperTest {
	public static void main(String[] args) {
		Student s1 = new Student();
		s1.showId();
		s1.eat();
		s1.study();
		s1.test();
		
		Student s2 = new Student("JK",21,"IT");
		s2.show();
	}
}
```

#### 练习

##### 1.
修改1.2中定义的类Kids中employeed()方法，在该方法中调用父类ManKind的employeed()方法，然后再输出“but Kids should study and no job.”
##### 
修改练习1.3中定义的Cylinder类，在Cylinder类中覆盖findArea()方法，计算圆柱的表面积。考虑：findVolume方法怎样做相应的修改？在CylinderTest类中创建Cylinder类的对象，设置圆柱的底面半径和高，并输出圆柱的表面积和体积。
##### 附加题：
在CylinderTest类中创建一个Circle类的对象，设置圆的半径，计算输出圆的面积。体会父类和子类成员的分别调用。


### 子类对象实例化的全过程

1. 从结果上来看，（继承性）
    - 子类继承父类以后，就获取了父类中声明的属性或方法
    - 创建子类的对象，在堆空间中，就会加载所有父类中声明的属性
2. 从过程上来看，
    - 当我们通过子类的构造器创建子类对象时，我们一定会直接或间接的调用其父类的构造器，继而调用其父类的父类的构造器，直到调用了java.lang.Obeject类中空参的构造器为止。正因为加载过所有父类的结构，所以才可以看到内存中有父类中的结构，子类对象才可以考虑进行调用。
    
- 虽然创建子类对象时，调用了父类的构造器，但就只创建了一个对象，即为new的子类对象。
    
 
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/170393723247425.png =775x)

- 无论哪个构造器创建子类对象，需要保证先初始化父类
- 目的：当子类继承父类之后，“继承”父类中所有的属性和方法，因此子类有必要知道父类如何为对象进行初始化
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/82235323236649.png =598x)

#### 例
```
class Creature {
    public Creature() {
        System.out.println("Creature无参数的构造器");
    }
}

class Animal extends Creature {
	public Animal(String name) {
		System.out.println("Animal带一个参数的构造器，该动物的name为" + name);
	}

	public Animal(String name, int age) {
		this(name);
		System.out.println("Animal带两个参数的构造器，其age为" + age);
	}
}

public class Wolf extends Animal {
	public Wolf() {
		super("灰太狼", 3);
		System.out.println("Wolf无参数的构造器");
	}

	public static void main(String[] args) {
		new Wolf();
	}
}
```
#### 练习4
修改练习1.3中定义的Circle类和Cylinder类的构造器，利用构造器参数为对象的所有属性赋初值。

```

```

### 实验-继承&super.doc
#### 1
##### 要求
```
1、写一个名为 Account 的类模拟账户。该类的属性和方法如下图所示。
该类包括的属性：
账号 id，余额 balance，年利率 annualInterestRate；
包含的方法：
访问器方法（getter 和setter 方法），
返回月利率的方getMonthlyInterest()，
取款方法 withdraw()，存款方法deposit()。
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/290665623232403.png =441x)


写一个用户程序测试 Account 类。在用户程序中，创建一个账号为 1122、余额为 20000、年利率 4.5%的 Account 对象。使用 withdraw 方法提款 30000 元，并打印余额。再使用 withdraw 方法提款 2500 元，使用 deposit 方法存款 3000 元，然后打印余额和月利率。

提示：在提款方法 withdraw 中，需要判断用户余额是否能够满足提款数额的要求，如果不能，应给出提示。

运行结果如图所示

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/87385723250283.png =213x)

##### 实现
```
package com.zjk;

public class Account {
	private int id;
	private double balance;
	private double annualInterestRate;

	public Account(int id, double balance, double annualInterestRate) {
		super();
		this.id = id;
		this.balance = balance;
		this.annualInterestRate = annualInterestRate;
	}

	public int getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	/**
	 * 
	 * @Desciption 取款
	 * @param amount
	 */
	public void withdraw(double amount) {
		if (balance >= amount) {
			balance -= amount;
			System.out.println("取款成功");
			System.out.println("余额：" + balance);
		} else {
			System.out.println("余额不足");
			System.out.println("余额：" + balance);
		}
	}

	/**
	 * 
	 * @Desciption 存款
	 * @param amount
	 */
	public void deposit(double amount) {
		balance += amount;
		System.out.println("存款成功");
		System.out.println("余额：" + balance);
		System.out.println("月利率：" + annualInterestRate);
	}
}

package com.zjk;

public class Test001 {
	public static void main(String[] args) {
		Account a1 = new Account(1122,20000,0.045);
		a1.withdraw(100);
		a1.deposit(2300);
	}
}
```

#### 2

##### 要求

2、创建 Account 类的一个子类 CheckAccount 代表可透支的账户，该账户中定义一个属性overdraft 代表可透支限额。在 CheckAccount 类中重写 withdraw 方法，其算法如下：


![](C:/Users/zjk10/OneDrive/NoteBook/pictures/302075923249070.png =439x)

```
要求：写一个用户程序测试 CheckAccount 类。在用户程序中，创建一个账号为 1122、余
额为 20000、年利率 4.5%，可透支限额为 5000 元的 CheckAccount 对象。
使用 withdraw 方法提款 5000 元，并打印账户余额和可透支额。
再使用 withdraw 方法提款 18000 元，并打印账户余额和可透支额。
再使用 withdraw 方法提款 3000 元，并打印账户余额和可透支额。
提示：
（1） 子类 CheckAccount 的构造方法需要将从父类继承的 3 个属性和子类自己的属性全
部初始化。
（2） 父类Account的属性balance被设置为private，但在子类CheckAccount的withdraw
方法中需要修改它的值，因此应修改父类的 balance 属性，定义其为 protected。
运行结果如下图所示
```
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/589225823245389.png =182x)

##### 实现

```
package com.zjk;

public class Account {
	private int id;
	protected double balance;
	private double annualInterestRate;

	public Account(int id, double balance, double annualInterestRate) {
		super();
		this.id = id;
		this.balance = balance;
		this.annualInterestRate = annualInterestRate;
	}

	public int getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	/**
	 * 
	 * @Desciption 取款
	 * @param amount
	 */
	public void withdraw(double amount) {
		if (balance >= amount) {
			balance -= amount;
			System.out.println("取款成功");
			System.out.println("余额：" + balance);
		} else {
			System.out.println("余额不足");
			System.out.println("余额：" + balance);
		}
	}

	/**
	 * 
	 * @Desciption 存款
	 * @param amount
	 */
	public void deposit(double amount) {
		balance += amount;
		System.out.println("存款成功");
		System.out.println("余额：" + balance);
		System.out.println("月利率：" + annualInterestRate);
	}
}

package com.zjk;

public class CheckAccount extends Account {
	private double overdraft;

	public CheckAccount(int id, double balance, double annualInterestRate, double overdraft) {
		super(id, balance, annualInterestRate);
		this.overdraft = overdraft;
	}

	@Override
	public void withdraw(double amount) {
		// TODO Auto-generated method stub
		if (amount <= balance) {
			balance -= amount;
			System.out.println("取款成功");
			System.out.println("余额：" + balance);
		} else if (amount > balance) {
			if (amount <= (balance + overdraft)) {
				amount -= balance;
				balance = 0;
				overdraft -= amount;
				System.out.println("取款成功");
				System.out.println("余额：" + balance);
				System.out.println("透支：" + overdraft);
			} else {
				System.out.println("超过透支限额");
			}
		}
	}
}

package com.zjk;

public class Test001 {
	public static void main(String[] args) {
		Account a1 = new Account(1122,20000,0.045);
		a1.withdraw(100);
		a1.deposit(2300);
		
		CheckAccount c1 = new CheckAccount(1122,20000,0.045,5000);
		c1.withdraw(5000);
		c1.withdraw(18000);
		c1.withdraw(3000);
	}
}
```

## 面向对象特征之三：多态性

- 多态性，是面向对象中最重要的概念，在Java中的体现：
   - 对象的多态性：父类的引用指向子类的对象
   - 可以直接应用在抽象类和接口上
- Java引用变量有两个类型：编译时类型和运行时类型。
   - **编译时类型由声明该变量时使用的类型决定，在编译期，只能调用父类中声明的方法**
   - **运行时类型由实际赋给该变量的对象决定。 在运行期，实际执行的是子类重写父类的方法**
       - 简称：编译时，看左边；运行时，看右边。
- 若编译时类型和运行时类型不一致，就出现了对象的多态性(Polymorphism)
  - 多态情况下，
     - “看左边”看的是父类的引用（父类中不具备子类特有的方法）
     - “看右边”看的是子类的对象（实际运行的是子类重写父类的方法）

- 多态性使用前提：
    1. 类的继承关系
    2. 方法的重写

- 对象的多态性只适用于方法，不适用于属性(属性的编译和运行都看左边)
- 对象的多态 —在Java中,子类的对象可以替代父类的对象使用
- 一个变量只能有一种确定的数据类型
- 一个引用类型变量可能指向(引用)多种不同类型的对象
- 有了对象的多态性以后，内存中实际上是加载了子类特有的属性和方法，
    - 但是由于变量声明为父类类型，导致编译时，只能调用父类中声明的属性和方法。子类特有的属性和方法不能调用。
    
```java
Person p = new Student();
Object o = new Person();//Object类型的变量o，指向Person类型的对象
o = new Student(); //Object类型的变量o，指向Student类型的对象
```

- 子类可看做是特殊的父类，所以父类类型的引用可以指向子类的对象：向上转型(upcasting)。

- 一个引用类型变量如果声明为父类的类型，但实际引用的是子类对象，那么该变量就不能再访问子类中添加的属性和方法

```java
Student m = new Student();
m.school = “pku”; //合法,Student类有school成员变量
Person e = new Student(); 
e.school = “pku”; //非法,Person类没有school成员变量
```

- 属性是在编译时确定的，编译时e为Person类型，没有school成员变量，因而编译错误。

### 例1

```
package com.zjk;

public class PersonTest {
	public static void main(String[] args) {

		Person p1 = new Person();
		p1.eat();

		Man man = new Man();
		man.eat();
		man.age = 25;
		man.earnMoney();

		System.out.println("-------------");

//		对象的多态性: 父类的引用指向子类的对象(子类的对象赋给父类引用)
		Person p2 = new Man();

//		多态的使用：当调用子父类同名同参的方法时，实际执行的是子类重写父类的方法
//		--虚拟方法调用
		p2.eat();
		p2.walk();

//		p2.earnMoney(); 
//		编译时，看左边：Person p2  只能是Person类中声明的
//		执行时，看右边：new Man()  实际执行的是Man类里重写的
//      不能调用子类所特有的方法/属性，编译时，p2是Person类型

//      有了对象的多态性以后，内存中实际上是加载了子类特有的属性和方法，
//      但是由于变量声明为父类类型，导致编译时，只能调用父类中声明的属性和方法。子类特有的属性和方法不能调用。

		System.out.println(p2.id);
//		1001 输出的是父类中定义的属性
//		对象的多态性只适用于方法，不适用于属性
	}
}

class Person {
	String name;
	int age;
	int id = 1001;

	public void eat() {
		System.out.println("吃饭");
	}

	public void walk() {
		System.out.println("走路");
	}
}

class Man extends Person {

	boolean isSmoking;
	int id = 1002;

	public void earnMoney() {
		System.out.println("男人负责赚钱养家");
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("多吃长肉");
	}

	@Override
	public void walk() {
		// TODO Auto-generated method stub
		System.out.println("帅气走路");
	}
}

class Woman extends Person {

	boolean isBeauty;

	public void goShopping() {
		System.out.println("女生喜欢购物");
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("女生少吃，减肥");
	}

	@Override
	public void walk() {
		// TODO Auto-generated method stub
		System.out.println("走路窈窕");
	}
}
```

### 例2

```
package com.zjk;

import java.sql.Connection;

public class AnimalTest {
	public static void main(String[] args) {

		AnimalTest test = new AnimalTest();
		test.function(new Dog());

		test.function(new Cat());

	}

	public void function(Animal animal) {
		animal.eat();
		animal.shout();
	}
//  多态性省去了以下的重载代码
//	public void function(Cat cat) {
//		cat.eat();
//		cat.shout();
//	}
//
//	public void function(Dog dog) {
//		dog.eat();
//		dog.shout();
//	}
}

class Animal {

	public void eat() {
		System.out.println("动物,进食");
	}

	public void shout() {
		System.out.println("动物，叫");
	}
}

class Dog extends Animal {
	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("狗，吃肉");
	}

	@Override
	public void shout() {
		// TODO Auto-generated method stub
		System.out.println("狗，叫");
	}
}

class Cat extends Animal {
	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("猫，吃鱼");
	}

	@Override
	public void shout() {
		// TODO Auto-generated method stub
		System.out.println("猫，叫");
	}
}

//附加例1
class Order {
	public void method(Object obj) {

	}
}

//附加例2
class Driver {

	public void doData(Connection conn) { 
//		conn = new MySQLConnection();
//		conn = new OracleConnection();
//		规范的步骤去操作数据
//		conn.方法1();
//		conn.方法2();
		
	}
}
```

### 多态性应用举例
- 方法声明的形参类型为父类类型，可以使用子类的对象作为实参调用该方法

```
public class Test {
    public void method(Person e) {
        // ……
        e.getInfo();
}
public static void main(Stirng args[]) {
    Test t = new Test();
    Student m = new Student();
    t.method(m); // 子类的对象m传送给父类类型的参数e
    }
}
```

### 多态性的使用：虚拟方法调用(Virtual Method Invocation)

- 正常的方法调用

```java
Person e = new Person();
e.getInfo();
Student e = new Student();
e.getInfo();
```

- 虚拟方法调用(多态情况下)
    - **子类中定义了与父类同名同参数的方法，在多态情况下，将此时父类的方法称为虚拟方法**，父类根据赋给它的不同子类对象，动态调用属于子类的该方法。这样的方法调用在编译期是无法确定的。

```java
Person e = new Student();
e.getInfo(); //调用Student类的getInfo()方法
```

- 编译时类型和运行时类型
    - 编译时e为Person类型，而方法的调用是在运行时确定的，所以调用的是Student类的getInfo()方法。——动态绑定

#### 虚拟方法调用举例：

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/200711710220967.png =621x)

- 前提：
   - Person类中定义了welcome()方法，各个子类重写了welcome()。
- 执行：
   - 多态的情况下，调用对象的welcome()方法，实际执行的是子类重写的方法。

### 方法的重载与重写

1. 二者的定义细节：略
2. 从编译和运行的角度看：
   - **重载，是指允许存在多个同名方法，而这些方法的参数不同**。编译器根据方法不同的参数表，对同名方法的名称做修饰。**对于编译器而言，这些同名方法就成了不同的方法。它们的调用地址在编译期就绑定了**。Java的重载是可以包括父类和子类的，即子类可以重载父类的同名不同参数的方法。
所以：对于**重载而言，在方法调用之前，编译器就已经确定了所要调用的方法，这称为“早绑定”或“静态绑定”；而对于多态，只有等到方法调用的那一刻，解释运行器才会确定所要调用的具体方法，这称为“晚绑定”或“动态绑定”**。引用一句Bruce Eckel的话：“不要犯傻，如果它不是晚绑定，它就不是多态。”

### 多态小结
- 多态作用：
   - 提高了代码的通用性，常称作接口重用
- 前提：
   - 需要存在继承或者实现关系
   - 有方法的重写
- 成员方法：
   - 编译时：要查看引用变量所声明的类中是否有所调用的方法。 
   - 运行时：调用实际new的对象所属的类中的重写方法。
- 成员变量：
   - 不具备多态性，只看引用变量所声明的类。
   
### instanceof 操作符

- x instanceof A：检验x是否为类A的对象，返回值为boolean型。
    - 要求x所属的类与类A必须是子类和父类的关系，否则编译错误。
    - 如果x属于类A的子类B，x instanceof A值也为true。
- 为了避免在向下转型时出现ClassCastException的异常，在向下转型之前，先进行instanceof的判断，一旦返回true，就进行向下转型。如果返回false，不进行向下转型。

```java
public class Person extends Object {…}
public class Student extends Person {…}
public class Graduate extends Person {…}
-------------------------------------------------------------------
public void method1(Person e) {
    if (e instanceof Person) 
        // 处理Person类及其子类对象
    if (e instanceof Student) 
        //处理Student类及其子类对象
    if (e instanceof Graduate)
        //处理Graduate类及其子类对象
}
```

### 对象类型转换 (Casting )

- 基本数据类型的Casting：
   - 自动类型转换：小的数据类型可以自动转换成大的数据类型
     - 如long g=20; double d=12.0f
   - 强制类型转换：可以把大的数据类型强制转换(casting)成小的数据类型
     - 如 float f=(float)12.0; int a=(int)1200L
- 对Java对象的强制类型转换称为造型
   - 从**子类到父类的类型转换可以自动进行**
   - 从**父类到子类的类型转换必须通过造型(强制类型转换)实现**
   - 无继承关系的引用类型间的转换是非法的
   - 在造型前可以使用instanceof操作符测试一个对象的类型

**向下转型**

使用子类中特有的属性和方法

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/381672710239393.png =608x)

#### 例

```
package com.zjk;

public class PersonTest {
	public static void main(String[] args) {

		Person p1 = new Person();
		p1.eat();

		Man man = new Man();
		man.eat();
		man.age = 25;
		man.earnMoney();

		System.out.println("-------------");

//		对象的多态性: 父类的引用指向子类的对象(子类的对象赋给父类引用)
		Person p2 = new Man();

//		多态的使用：当调用子父类同名同参的方法时，实际执行的是子类重写父类的方法
//		--虚拟方法调用
		p2.eat();
		p2.walk();

//		p2.earnMoney(); 
//		编译时，看左边：Person p2  只能是Person类中声明的
//		执行时，看右边：new Man()  实际执行的是Man类里重写的

		System.out.println(p2.id);
//		1001 输出的是父类中定义的属性
//		对象的多态性只适用于方法，不适用于属性(属性的编译和运行都看左边)
		
		Man m1 = (Man)p2;  //向下转型
		m1.earnMoney();
		m1.isSmoking = true;
		
//		Woman w1 = (Woman) p2; //此时p2为man类型强转为Woman类型，并不是子父类的关系
//		w1.goShopping();
//		使用强制向下转型可能：Exception in thread "main" java.lang.ClassCastException
		
		
//		为了避免在向下转型时出现ClassCastException的异常，
//		在向下转型之前，先进行instanceof的判断，一旦返回true，就进行向下转型。如果返回false，不进行向下转型。
		if(p2 instanceof Woman) {
			Woman w1 = (Woman)p2;
			w1.goShopping();
			System.out.println("****Woman****");
		}
		if(p2 instanceof Man) {
			Man m2 = (Man)p2;
			m1.earnMoney();
			System.out.println("****Man****");
		}
	}
}

class Person {
	String name;
	int age;
	int id = 1001;

	public void eat() {
		System.out.println("吃饭");
	}

	public void walk() {
		System.out.println("走路");
	}
}

class Man extends Person {

	boolean isSmoking;
	int id = 1002;

	public void earnMoney() {
		System.out.println("男人负责赚钱养家");
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("多吃长肉");
	}

	@Override
	public void walk() {
		// TODO Auto-generated method stub
		System.out.println("帅气走路");
	}
}

class Woman extends Person {

	boolean isBeauty;

	public void goShopping() {
		System.out.println("女生喜欢购物");
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("女生少吃，减肥");
	}

	@Override
	public void walk() {
		// TODO Auto-generated method stub
		System.out.println("走路窈窕");
	}
}
```

#### 对象类型转换举例

```
public class ConversionTest {
	public static void main(String[] args) {
		double d = 13.4;
		long l = (long) d;
		System.out.println(l);
		int in = 5;
// boolean b = (boolean)in;
		Object obj = "Hello";
		String objStr = (String) obj;
		System.out.println(objStr);
		Object objPri = new Integer(5);
// 所以下面代码运行时引发ClassCastException异常
		String str = (String) objPri;
	}
}
```

```
public class Test {
	public void method(Person e) { // 设Person类中没有getschool() 方法
        // System.out.pritnln(e.getschool()); //非法,编译时错误
		if (e instanceof Student) {
			Student me = (Student) e; // 将e强制转换为Student类型
			System.out.pritnln(me.getschool());
		}
	}

	public static void main(String[] args) {
		Test t = new Test();
		Student m = new Student();
		t.method(m);
	}
}
```

#### 练习
##### 问题一：编译时通过，运行时不通过

```
Person p3 = new Woman();
Man m3 = (Man)p3; 
```

```
Person p4 = new Person();
Man m4 = (Man)p4;
//Man.eranMoney();
```

##### 问题二：编译时通过，运行时也通过

```
Object obj = new Woman();
Person p = (Person)obj;
```

##### 问题三：编译不通过

```
Man m5 = new Woman();

String str = new Date();
```
#### 练习：继承成员变量和继承方法的区别

```
package com.zjk;

class Base {
	int count = 10;

	public void display() {
		System.out.println(this.count);
	}
}

public class FieldMethodTest {
	public static void main(String[] args) {
		Sub s = new Sub();
		System.out.println(s.count); //20
		s.display(); //20
		
		Base b = s;
//      == 对于引用数据类型，比较的是两个引用数据类型的地址值是否相同		
		System.out.println(b == s); //true
		
		System.out.println(b.count); //10
//      对象的多态性不适用于属性
//      调用Base类的count
		
        b.display(); //20
//		虚拟方法调用，调用Sub中的display()方法，输出Sub中的count
	}
}

class Sub extends Base {
	int count = 20;

	public void display() {
		System.out.println(this.count);
	}
}
```

##### 区别

1. 若子类重写了父类方法，就意味着子类里定义的方法彻底覆盖了父类里的同名方法。系统不可以把父类里的方法转移到子类中。编译看左边，运行看右边。
2. 对于实例变量则不存在这样的现象，即使子类里定义了与父类完全相同的实例变量，这个实例变量依然不可能覆盖父类中定义的实例变量。编译运行都看左边。


### 子类继承父类
- 若子类重写了父类方法，就意味着子类里定义的方法彻底覆盖了父类里的同名方法，系统将不可能把父类里的方法转移到子类中。
- 对于实例变量则不存在这样的现象，即使子类里定义了与父类完全相同的实例变量，这个实例变量依然不可能覆盖父类中定义的实例变量

#### 练习5

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/586652910227260.png =698x)

```
package com.zjk;

public class Person {
	protected String name = "person";
	protected int age = 50;

	public String getInfo() {
		return "Name:" + name + "\nage:" + age;
	}
}

class Student extends Person {
	protected String school = "pku";

	public String getInfo() {
		return "name:" + name + "\nage:" + age + "\nschool" + school;
	}
}

class Graduate extends Student {
	public String major = "IT";

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return super.getInfo() + "\nmajor" + major;
	}
}

package com.zjk;

public class InstanceTest {
	public static void main(String[] args) {
		Person p1 = new Person();
		Student s1 = new Student();
		Graduate g1 = new Graduate();
		
		InstanceTest test = new InstanceTest();
		test.method(p1);
		test.method(s1);
		test.method(g1);
	}

	public void method(Person e) {
		e.getInfo();

		if (e instanceof Person) {
			System.out.println("a Person");
		}
		if (e instanceof Student) {
			System.out.println("a Student");
			System.out.println("a Person");
		}
		if (e instanceof Graduate) {
			System.out.println("a graduate student");
			System.out.println("a Student");
			System.out.println("a Person");
		}
	}
}
```

#### 练习6

定义三个类，父类GeometricObject代表几何形状，子类Circle代表圆形，MyRectangle代表矩形。

定义一个测试类GeometricTest，编写equalsArea方法测试两个对象的面积是否相等（注意方法的参数类型，利用动态绑定技术），编写displayGeometricObject方法显示对象的面积（注意方法的参数类型，利用动态绑定技术）。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/175993110247426.png =650x)


```
package com.zjk;

/*
 * 定义一个测试类GeometricTest，编写equalsArea方法测试两个对象的面积是否相等（注意方法的参数类型，利用动态绑定技术）
 * 编写displayGeometricObject方法显示对象的面积（注意方法的参数类型，利用动态绑定技术）。
 */

public class GeometricTest {
	public static void main(String[] args) {
		Circle c1 = new Circle("red",2,3);
		MyRectangle r1 = new MyRectangle("red",2,3,4);
		
		GeometricTest test = new GeometricTest();
		System.out.println(test.equalsArea(c1,r1));
		
		test.displayGeometricObject(c1);
		test.displayGeometricObject(r1);
	}
	
	public boolean equalsArea(GeometricObject o1,GeometricObject o2) {
		return o1.findArea() == o2.findArea();
	}
	
	public void displayGeometricObject(GeometricObject o) {
		System.out.println("面积：" + o.findArea());
	}
}


class GeometricObject{
	protected String color;
	protected double weight;
	
	protected GeometricObject(String color,double weight) {
		this.color = color;
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public double getWeight() {
		return weight;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double findArea() {
		return 0.0;
	}
}

class Circle extends GeometricObject{
	private double radius;

	public Circle(String color,double weight,double radius) {
		super(color,weight);
		this.radius = radius;
	}
	
	
	public double getRadius() {
		return radius;
	}


	public void setRadius(double radius) {
		this.radius = radius;
	}


	public double findArea() {
		return radius * radius * Math.PI;
	}
}

class MyRectangle extends GeometricObject{
	private double width;
	private double height;
	
	public MyRectangle(String color,double weight,double width, double height) {
		super(color,weight);
		this.width = width;
		this.height = height;
	}
	
	public double findArea() {
		// TODO Auto-generated method stub
		return width * getWeight();
	}
}
```

### 面试题：
多态是编译时行为还是运行时行为？如何证明？

#### 证明见：InterviewTest.java
```
package com.atguigu.test;

import java.util.Random;

//面试题：多态是编译时行为还是运行时行为？
//证明如下：
class Animal  {
 
	protected void eat() {
		System.out.println("animal eat food");
	}
}

class Cat  extends Animal  {
 
	protected void eat() {
		System.out.println("cat eat fish");
	}
}

class Dog  extends Animal  {
 
	public void eat() {
		System.out.println("Dog eat bone");

	}

}

class Sheep  extends Animal  {
 

	public void eat() {
		System.out.println("Sheep eat grass");

	}

 
}

public class InterviewTest {

	public static Animal  getInstance(int key) {
		switch (key) {
		case 0:
			return new Cat ();
		case 1:
			return new Dog ();
		default:
			return new Sheep ();
		}

	}

	public static void main(String[] args) {
		int key = new Random().nextInt(3);

		System.out.println(key);

		Animal  animal = getInstance(key);
		
		animal.eat();
		 
	}

}
```
#### 拓展问题：InterviewTest1.java

```
package com.atguigu.test;
//考查多态的笔试题目：
public class InterviewTest1 {

	public static void main(String[] args) {
		Base base = new Sub();
		base.add(1, 2, 3);

//		Sub s = (Sub)base;
//		s.add(1,2,3);
	}
}

class Base {
	public void add(int a, int... arr) {
		System.out.println("base");
	}
}

class Sub extends Base {

	public void add(int a, int[] arr) { //编译器认为int[] arr 与 int...arr相同; 是重写
		System.out.println("sub_1");
	}

//	public void add(int a, int b, int c) {
//		System.out.println("sub_2");
//	}

}
```

### Object类的使用

- Object类是所有Java类的根父类
     - 如果在类的声明中未使用extends关键字指明其父类，则默认父类为java.lang.Object类

```
package com.zjk;

public class ObjectTest {

	public static void main(String[] args) {
		Order order = new Order();
		System.out.println(order.getClass().getSuperclass());
	}
}

class Order {

}
```

```
public class Person {
    ...
}

等价于：
public class Person extends Object {
    ...
}
```

- 数组也可以作为Object类的子类出现，可以调用Object类中声明的方法

```
@Test
public void test1() {
	int[] arr = new int[] {1,2,3};
	print(arr);
	
	System.out.println(arr.getClass());
	System.out.println(arr.getClass().getSuperclass());
}

public void print(Object obj) {
	System.out.println(obj);
}
```

例：
```
method(Object obj){…} //可以接收任何类作为其参数
Person o=new Person();
method(o);
```
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/26161023220968.png =516x)

#### ==操作符与equals方法

##### ==
- 基本类型比较值:只要两个变量的值相等，即为true。(不一定类型相等)
- 引用类型比较引用(是否指向同一个对象)：只有指向同一个对象时，==才返回true。
- 用“==”进行比较时，符号两边的数据类型必须兼容(可自动转换的基本数据类型除外)，否则编译出错

```
package com.zjk;

public class EqualsTest {
	public static void main(String[] args) {

//		基本数据类型的== ************************
		int i = 10;
		int j = 10;
		double d = 10.0;
		System.out.println(i == j);// true
		System.out.println(i == d);// true

		boolean b = true;
//		System.out.println(i == b);编译不通过

		char c = 10;
		System.out.println(i == c);// true

		char c1 = 'A';
		char c2 = 65;
		System.out.println(c1 == c2);// true

//		引用数据类型的== ************************
		Customer cust1 = new Customer("Tom", 21);
		Customer cust2 = new Customer("Tom", 21);
		System.out.println(cust1 == cust2); // false

		Customer cust3 = cust1;
		System.out.println(cust3 == cust1); //true
		
		String str1 = new String("atguigu");
		String str2 = new String("atguigu");
		System.out.println(str1 == str2); // false
	}
}
```

##### equals()：
- 所有类都继承了Object，也就获得了equals()方法。还可以重写。
- 只能比较引用类型，Object类中的equals()其作用和定义与“==”相同,比较是否指向同一个对象。
- Object类中equals()的定义

```
public boolean equals(Object obj){
    return (this == obj);
}
```
- 格式:obj1.equals(obj2)
-  特例：当用equals()方法进行比较时，对类File、String、Date及包装类（Wrapper Class）来说，是比较类型及“实体内容”而不考虑引用的是否是同一对象；
    - 原因：在这些类中重写了Object类的equals()方法。
- 当自定义使用equals()时，可以重写。用于比较两个对象的“内容”是否都相等

```
package com.zjk;

import java.util.Date;

public class EqualsTest {
	public static void main(String[] args) {

//		引用数据类型的== ************************
		Customer cust1 = new Customer("Tom", 21);
		Customer cust2 = new Customer("Tom", 21);
		System.out.println(cust1 == cust2); // false

		Customer cust3 = cust1;
		System.out.println(cust3 == cust1); // true

		String str1 = new String("atguigu");
		String str2 = new String("atguigu");
		System.out.println(str1 == str2); // false

//		equals() *******************************
		System.out.println(cust1.equals(cust2)); // false
		// Object类中的equals()未重写
		System.out.println(str1.equals(str2)); // true
		// String类中的equals()已经重写

		Date date1 = new Date(32432525324L);
		Date date2 = new Date(32432525324L);
		System.out.println(date1.equals(date2)); // true
		
	}
}
```

##### 重写equals()方法的原则

通常情况下，我们自定义的类如果使用equals()的话，也通常是比较这两个对象的“实体内容”是否相同，那么，我们就需要对Object类中的equals()进行重写。
- **比较两个对象的实体内容是否相同**
- 对称性：如果x.equals(y)返回是“true”，那么y.equals(x)也应该返回是“true”。
- 自反性：x.equals(x)必须返回是“true”。
- 传递性：如果x.equals(y)返回是“true”，而且y.equals(z)返回是“true”，那么z.equals(x)也应该返回是“true”。
- 一致性：如果x.equals(y)返回是“true”，只要x和y内容一直不变，不管你重复x.equals(y)多少次，返回都是“true”。
- 任何情况下，x.equals(null)，永远返回是“false”；x.equals(和x不同类型的对象)永远返回是“false”。


- String类中的equals()重写

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/233225614220969.png =616x)

```
package com.zjk;

public class Customer {

	private String name;
	private int age;

	public Customer() {

	}

	public Customer(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj) {
			return true;
		}
		if (obj instanceof Customer) { //只要是子类就true，有一定的漏洞
			Customer cust = (Customer) obj;
//			if (this.age == cust.age && this.name == cust.name) {
//          遇到Customer cust = new Customer(new String("BB"),12) ;== 有误
			// name是String引用数据类型，应该使用equals()

//			if (this.age == cust.age && this.name.equals(cust.name)) {
//				return true;
//			}

			return this.age == cust.age && this.name.equals(cust.name);
		} else {
			return false;
		}
	}
}


package com.zjk;

import java.util.Date;

public class EqualsTest {
	public static void main(String[] args) {

//		引用数据类型的== ************************
		Customer cust1 = new Customer("Tom", 21);
		Customer cust2 = new Customer("Tom", 21);
		System.out.println(cust1 == cust2); // false

//		重写Customer类的equals() *******************************
		System.out.println(cust1.equals(cust2)); // true		
	}
}
```

###### 手写的存在的漏洞

```
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj) {
			return true;
		}
		if (obj instanceof Customer) {
			Customer cust = (Customer) obj;
			return this.age == cust.age && this.name.equals(cust.name);
		} else {
			return false;
		}
	}
```

- 子类

###### 自动生成的equals() //eclipse快捷键

```
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return age == other.age && Objects.equals(name, other.name);
	}
```

##### 面试题：==和equals的区别

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/383161423227261.png =737x)

##### 练 习7

```
int it = 65;
float fl = 65.0f;
System.out.println(“65和65.0f是否相等？” + (it == fl)); //true

char ch1 = 'A'; char ch2 = 12;
System.out.println("65和'A'是否相等？" + (it == ch1));//true
System.out.println(“12和ch2是否相等？" + (12 == ch2));//true

String str1 = new String("hello");
String str2 = new String("hello");
System.out.println("str1和str2是否相等？"+ (str1 == str2));//false

System.out.println("str1是否equals str2？"+(str1.equals(str2)));//true

System.out.println(“hello” == new java.util.Date()); //编译不通过
```

##### 练 习8
###### 1.
编写Order类，有int型的orderId，String型的orderName，相应的getter()和setter()方法，两个参数的构造器，重写父类的equals()方法：public boolean equals(Object obj)，并判断测试类中创建的两个对象是否相等。

```
package com.zjk;

public class Order {

	int orderId;
	String orderName;

	public int getOrderId() {
		return orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (this instanceof Order) {
			Order order = (Order) obj;
			return this.orderId == order.orderId && this.orderName.equals(order.orderName);
		} else {
			return false;
		}
	}
}
```

###### 

请根据以下代码自行定义能满足需要的MyDate类,在MyDate类中覆盖equals方法，使其判断当两个MyDate类型对象的年月日都相同时，结果为true，否则为false。 public boolean equals(Object o)

```
package com.zjk;

public class MyDate {
	private int day;
	private int month;
	private int year;

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj) {
			return true;
		}
		if (this instanceof MyDate) {
			MyDate m = (MyDate) obj;
			return this.day == m.day && this.month == m.month && this.year == m.year;
		} else {
			return false;
		}
	}
}
```

#### toString() 方法

- 当我们输出一个对象的引用时，实际上就是调用当前对象的toString()

- toString()方法在Object类中定义，其返回值是String类型，返回类名和它的引用地址。
- Object类中的toString()的定义

```
public String toString(){  
    return getClass().getName + "@" + Integer.toHexStrign(hashCode());
}

//Java中的地址值并不是真实的物理地址值，而是hashCode算出来的虚拟地址值
```

- 像String/Date/File/包装类等都重写了Object类中的toString()
    - 使得在调用对象的toString()时，返回“实体内容”信息
  
```
package com.zjk;

import java.util.Date;

public class ToStringTest {
	public static void main(String[] args) {
//      未重写，输出地址值
		Customer cust1 = new Customer("Tom", 21);
		System.out.println(cust1.toString());
		// com.zjk.Customer@1d8d30f7
		System.out.println(cust1);
		// com.zjk.Customer@1d8d30f7

//		重写过
		String str = new String("YY");
		System.out.println(str); // YY

		Date date = new Date(131231L);
		System.out.println(date.toString());// Thu Jan 01 08:02:11 CST 1970
	}
}
```
  
- 自定义类也可以重写toString()方法，当调用此方法时，返回对象的“实体内容”

```
package com.zjk;

import java.util.Objects;

public class Customer {

	private String name;
	private int age;

	public Customer() {

	}

	public Customer(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return age == other.age && Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Customer[name = " + name + ",age = " + age + "]";
	}
}


package com.zjk;

public class ToStringTest {
	public static void main(String[] args) {
		Customer cust1 = new Customer("Tom", 21);
		System.out.println(cust1.toString());
		//Customer[name = Tom,age = 21]
		System.out.println(cust1);
		//Customer[name = Tom,age = 21]
	}
}

```
- 在进行String与其它类型数据的连接操作时，自动调用toString()方法

```
Date now=new Date();
System.out.println(“now=”+now); 相当于
System.out.println(“now=”+now.toString());
```

- 可以根据需要在用户自定义类型中重写toString()方法

```
如String 类重写了toString()方法，返回字符串的值。
s1=“hello”;
System.out.println(s1);//相当于System.out.println(s1.toString());
```

- 基本类型数据转换为String类型时，调用了对应包装类的toString()方法

```
int a=10; System.out.println(“a=”+a);
```

##### 面试题 只有char[]数组输出字符串，其他数组都是地址值 

```
public class MyDate {
	public static void main(String[] args) {
		MyDate m1 = new MyDate();
		m1.test();

	}

	public void test() {
		char[] arr = new char[] { 'a', 'b', 'c' };
		System.out.println(arr); //abc
		
		int[] arr1 = new int[] { 1, 2, 3 };
		System.out.println(arr1); //[I@37d31475
		
		double[] arr2 = new double[] { 1.1, 2.2, 3.3 };
		System.out.println(arr2); //[D@27808f31
	}
}
```

##### 当null.toString()时，空指针异常NullPointException

```
@Test
public void test2() {
	String s = "abc";
	System.out.println(s); //abc
	
	s = null;
	System.out.println(s); //null
	
	System.out.println(s.toString()); //当null.toString()时，
	//空指针异常NullPointException	
}
```

#### 练习9
定义两个类，父类GeometricObject代表几何形状，子类Circle代表圆形。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/284831823236651.png =459x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/419631823232405.png =554x)

```
package com.zjk;

import java.util.Objects;

public class GeometricObject {
	protected String color;
	protected double weight;

	protected GeometricObject() {
		color = "white";
		weight = 1.0;

	}

	protected GeometricObject(String color, double weight) {
		this.color = color;
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public double getWeight() {
		return weight;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}

class Circle extends GeometricObject {
	private double radius;

	public Circle() {
		super();
		radius = 1.0;
	}

	public Circle(double radius) {
		this.radius = radius;
	}

	public Circle(double radius, String color, double weight) {
		super(color, weight);
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double findArea() {
		return radius * radius * Math.PI;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circle other = (Circle) obj;
		return Double.doubleToLongBits(radius) == Double.doubleToLongBits(other.radius);
	}

	@Override
	public String toString() {
		return "Circle [radius=" + radius + "]";
	}
}


package com.zjk;

public class GeometricObjectTest {
	public static void main(String[] args) {
		Circle c1 = new Circle();
		Circle c2 = new Circle();
		System.out.println();

		GeometricObjectTest test = new GeometricObjectTest();
		test.colorTest(c1, c2);

		if (c1.equals(c2)) {
			System.out.println("面积相等");
		} else {
			System.out.println("面积不相等");
		}

		System.out.println(c1.toString());
		System.out.println(c2);
	}

	public void colorTest(Circle c1, Circle c2) {
		if (c1.getColor().equals(c2.getColor())) {
			System.out.println("颜色相同: " + c1.getColor());
		} else {
			System.out.println("颜色不相同:" + c1.getColor() + "  " + c2.getColor());
		}
	}
}
```

### JUnit单元测试

1. 右击当前项目 - build path - add libraries - JUnit5 

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/380741100220970.png =454x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/146601300239396.png =409x)


![](C:/Users/zjk10/OneDrive/NoteBook/pictures/154351400227263.png =189x)

2. 创建一个java类，进行单元测试
   - 此时的Java类要求：
       - 此类是public的
       - 此类提供公共的无参的构造器 
3. 此类中声明单元测试方法
   - 此时的单元测试方法：方法的权限是public，没有返回值，没有形参
4. 此单元测试方法上需要声明注释：`@Test`，并在单元测试类中导入: `import org.junit.Test`
5. 声明号单元测试方法以后，就可以在方法体内测试相关的代码
6. 写完代码以后：左键双击方法，右键 Run as - JUnit Test

```
@Test
public void testMehod(){
    ...
}
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/47712200247429.png =522x)

- 一次只执行一个选中的单元测试方法

- 如果执行结果没有任何异常：

```
package com.zjk2;

import java.util.Date;

import org.junit.Test;

public class JUnitTest {

	@Test
	public void testEquals() {
		String s1 = "EE";
		String s2 = "EE";
		System.out.println(s1.equals(s2));
	}
}
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/359272300240098.png =190x)

- 如果执行结果出现异常:

```
package com.zjk2;

import java.util.Date;

import org.junit.Test;

public class JUnitTest {

	@Test
	public void testEquals() {
		String s1 = "EE";
		String s2 = "EE";
		System.out.println(s1.equals(s2));
		
		Object obj = new String("GG");
		Date date = (Date)obj; //类型转换异常
	}
}
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/561952400236653.png =185x)

### 包装类的使用

- 针对八种基本数据类型定义相应的引用类型—包装类（封装类）
- 有了类的特点，就可以调用类中的方法，Java才是真正的面向对象

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/551802023250285.png =367x)

- 基本数据类型包装成包装类的实例 ---装箱
    - 通过包装类的构造器实现：

```
int i = 500; Integer t = new Integer(i);
```

   - 还可以通过字符串参数构造包装类对象：

```
Float f = new Float(“4.56”);
Long l = new Long(“asdf”); //NumberFormatException
```

- 获得包装类对象中包装的基本类型变量 ---拆箱
- 调用包装类的.xxxValue()方法：

```
boolean b = bObj.booleanValue();
```

- JDK5.0之后，支持自动装箱，自动拆箱。但类型必须匹配。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/361952223247889.png =102x)

- **字符串转换成基本数据类型**
1. 通过**包装类的构造器**实现：

```
int i = new Integer(“12”);
```

2. 通过包装类的**parseXxx(String s)静态方法**：

```
Float f = Float.parseFloat(“12.1”);
```

- **基本数据类型转换成字符串**
1.  调用字符串重载的**valueOf()**方法：

```
String fstr = String.valueOf(2.34f);
```

2. 更直接的方式：**数值 + ""**

```
String intStr = 5 + ""
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/10672423226632.png =132x)

#### 基本类型、包装类与String类间的转换

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/521452623244208.png =762x)

```
package com.zjk2;

import org.junit.jupiter.api.Test;

public class WrapperTest {
	
//	基本数据类型-->包装类 调用包装类的构造器
	@Test
	public void test1() {
		
		int num1 = 10;
		Integer in1 = new Integer(num1);
		System.out.println(in1.toString());
		
		Integer in2 = new Integer("123");
		System.out.println(in2.toString());
		
//		Integer in3 = new Integer("123abc");
//		System.out.println(in3.toString());
//		报错
		
		Float f1 = new Float(12.3f);
		Float f2 = new Float("12.3");
		System.out.println(f1);
		System.out.println(f2);
		
		Float f3 = new Float("12.3f");  
//		不报错，float数据类型需要在后面加f
		System.out.println(f3);
		
		Boolean b1 = new Boolean(true);
		Boolean b2 = new Boolean("true");
		Boolean b3 = new Boolean("TRUE");
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		
//		Boolean类：源码中true自动转大小写，不是true的即false
		Boolean b4 = new Boolean("true123");
		System.out.println(b4); //false
		Boolean b5 = new Boolean("");
		System.out.println(b5); //false
		
//		Boolean b6 = new Boolean(); //报错
		
		Order order = new Order();
		System.out.println(order.isMale); //false
		System.out.println(order.isFemale); //null
//		isMale是boolean基本数据类型，默认false，
//		而isFemale是Boolean包装类，默认null
	}
	
//	包装类-->基本数据类型,调用包装类的xxxValue()
	@Test
	public void test2() {
		Integer in1 = new Integer(12);
//		包装类不能进行简单的加减乘除运算
		int i1 = in1.intValue();
		System.out.println(i1 + 1);
		
		Float f1 = new Float(12.3);
		float f2 = f1.floatValue();
		System.out.println(f2);
	}
	
//	JDK5.0之后，支持自动装箱，自动拆箱。但类型必须匹配。

	@Test
	public void test3() {
			
//		自动装箱 --基本数据类型的变量直接赋值给包装类的对象
		int num2 = 10;
		Integer in1 = num2; //自动装箱
		Integer in = 10;
		
		boolean b1 = true;
		Boolean b2 = b1; //自动装箱
		Boolean b = true;

//		自动拆箱
		System.out.println(in1);
		
		int num3 = in1;
	}
	
//	基本数据类型,包装类-->String
	@Test
	public void test4(){

//      基本数据类型-->String
		int num1 = 10;
//		方式1
		String str1 = num1 + "";
//		方式2 调用String类的valueOf()方法 
//		基本数据类型和包装类均可使用
		float f1 = 12.3f;
		String str2 = String.valueOf(f1); //"12.3"
		System.out.println(str2);
		
		Double d1 = new Double(12.3);
		String str3 = String.valueOf(d1);
		System.out.println(str3);
	}
	
//	String类型-->基本数据类型，包装类
//	调用包装类的parseXxx()
	@Test
	public void test5() {
		String str1 = "123";
//		int num1 = (int)str;
//		Integer in1 = (Integer)str1;
//		无关系的类之间强转报错
		
//		可能会报NumberFormatExcption
		int num2 = Integer.parseInt(str1);
		System.out.println(num2 + 1);
		
		String str2 = "true";
		Boolean b1 = Boolean.parseBoolean(str2);
		System.out.println(b1); //true
	}
}


class Order{
	boolean isMale;
	Boolean isFemale;
}
```
#### 包装类用法举例

```
int i = 500;
Integer t = new Integer(i);
装箱：包装类使得一个基本数据类型的数据变成了类。
有了类的特点，可以调用类中的方法。
String s = t.toString(); // s = “500“,t是类，有toString方法
String s1 = Integer.toString(314); // s1= “314“ 将数字转换成字符串。
String s2=“4.56”;
double ds=Double.parseDouble(s2); //将字符串转换成数字

拆箱：将数字包装类中内容变为基本数据类型。
int j = t.intValue(); // j = 500，intValue取出包装类中的数据

包装类在实际开发中用的最多的在于字符串变为基本数据类型。
String str1 = "30" ;
String str2 = "30.3" ;
int x = Integer.parseInt(str1) ; // 将字符串变为int型
float f = Float.parseFloat(str2) ; // 将字符串变为float型
```

#### 面试题 

如下两个题目的输出结果；各是什么？

##### 三元运算符
```
public class Test {
	public static void main(String[] args) {
		
		Object o1 = true ? new Integer(1) : new Double(2.0);
//		三元运算符 ?: 要求同一类型，Integer类型自动提升为Double类型
		System.out.println(o1); //1.0
		
		Object o2;
		if(true) {
			o2 = new Integer(1);
		}
		else {
			o2 = new Double(2.0);
		}
		System.out.println(o2); //1
	}
	
}
```

##### Integer内部定义了IntegerCache结构，IntegerCache中定义了数组Integer[],保存了`-128~127`范围的整数。如果我们使用自动装箱的方法，给Integer赋值的范围在`-128~127`时，可以直接使用数组中的元素，不用再去new了。目的：提高效率。

```
public class Test {
	public static void main(String[] args) {
		Test test = new Test();
		test.method();
	}
	
	public void method() {
		Integer i = new Integer(1);
		Integer j = new Integer(1);
		System.out.println(i == j); //false
		//比较地址值
		
		Integer m = 1;
		Integer n = 1;
		System.out.println(m == n); //true
		//自动装箱

		Integer x = 128;  //相当于new了一个Integer对象
		Integer y = 128;  //相当于new了一个Integer对象
		System.out.println(x == y); //false
		
        //Integer包装类中的IntegerCache结构，
		//提前造好了-128~127的int值
		//自动装箱时，赋值在-128~127之间的，直接取造好的int值，所以m和n地址值相同
		//超出范围的则new一个int值，新建地址值，所以x和y地址值不相同
	}
}
```

### 练习10
利用Vector代替数组处理：从键盘读入学生成绩（以负数代表输入结束），找出最高分，并输出学生成绩等级。

提示：数组一旦创建，长度就固定不变，所以在创建数组前就需要知道它的长度。而向量类java.util.Vector可以根据需要动态伸缩。

- 创建Vector对象：Vector v=new Vector();
- 给向量添加元素：v.addElement(Object obj); //obj必须是对象
- 取出向量中的元素：Object obj=v.elementAt(0);
    - 注意第一个元素的下标是0，返回值是Object类型的。
- 计算向量的长度：v.size();
- 若与最高分相差10分内：A等；20分内：B等；30分内：C等；其它：D等

```
package com.zjk2;

import java.util.Scanner;
import java.util.Vector;

public class ScoreTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Vector v = new Vector();

		int maxScore = 0;

		for (;;) {
//			输入成绩
			System.out.println("请输入学生成绩（负数结束）");
			int score = scan.nextInt();
			if (score < 0) {
				break;
			}
			if (score > 100) {
				System.out.println("数据有误，重新输入");
				continue;
			}
////		jdk5.0之前添加对象
//			Integer inScore = new Integer(score);
//			v.addElement(inScore);
			v.addElement(score); // 自动装箱
//			获取最大值(使用基本数据类型比）
			if (maxScore < score) {
				maxScore = score;
			}
		}
//		评级
		char level;
		for (int i = 0; i < v.size(); i++) {
			Object obj = v.elementAt(i);
//			jdk5.0之前
//			Integer inScore = (Integer) obj;
//			int score = inScore.intValue();
//			jdk5.0之后
			int score = (int)obj;
			
			if (maxScore - score <= 10) {
				level = 'A';
			} else if (maxScore - score <= 20) {
				level = 'B';
			} else if (maxScore - score <= 30) {
				level = 'c';
			} else {
				level = 'D';
			}
		

			System.out.println("student-" + (i + 1) + " score is " + score + ",level is " + level);
		}
	}
}
```

## 关键字：static

当我们编写一个类时，其实就是在描述其对象的属性和行为，而并没有产生实质上的对象，只有通过new关键字才会产生出对象，这时系统才会分配内存空间给对象，其方法才可以供外部调用。我们有时候希望无论是否产生了对象或无论产生了多少对象的情况下，某些特定的数据在内存空间里只有一份，例如所有的中国人都有个国家名称，每一个中国人都共享这个国家名称，不必在每一个中国人的实例对象中都单独分配一个用于代表国家名称的变量。

- **如果想让一个类的所有实例共享数据，就用类变量！**

### static关键字使用

- 使用范围：
   - 在Java类中，可用static修饰属性、方法、代码块、内部类
- 被修饰后的成员具备以下特点：
   - 随着类的加载而加载
   - 优先于对象存在
   - 修饰的成员，被所有对象所共享
   - 访问权限允许时，可不创建对象，直接被类调用

```
class Circle {
    private double radius;

    public static String name = "这是一个圆";
    

    public static String getName() {
        return name;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double findArea() {
        return Math.PI * radius * radius;
    }

    public void display() {
        System.out.println("name:" + name + "radius:" + radius);
    }
}

public class StaticTest {
    public static void main(String[] args) {
        Circle c1 = new Circle(2.0);
        Circle c2 = new Circle(3.0);
        c1.display();
        c2.display();
    }
}
```

### 用static修饰属性 ，类变量（静态变量）

- 实例变量： 
    - 创建了类的多个对象，每个对象都独立的拥有一套类中的非静态属性，归某个对象所有
    - 当修改其中一个对象中的非静态属性时，不会导致其他对象中同样的属性值的修改
- 静态变量（静态属性）（类变量）
    -  创建了类的多个对象，多个对象共享同一个静态变量
    - 当通过某一个对象修改静态变量时，会导致其他对象调用此静态变量时，是修改过的值。

#### 静态变量的特点：

- 静态变量随着类的加载而加载
   - 可以通过`类.静态变量`的方法进行调用 ，而实例变量不可以
- 优先于对象存在，
- 由于类只加载一次，则静态变量在内存中也只会存在一份，存在方法区的静态域中



#### 类变量 vs 实例变量内存解析

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/576250900226364.png =731x)


**静态变量的内存解析**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/224761000253319.png =692x)


### 类方法（静态方法）

#### 使用static修饰方法，静态方法：

1. **随着类的加载而加载**，
   - 可以通过`类.静态方法`的方式进行调用，
2. **静态方法中只能调用静态的方法或属性。**(生命周期)
   - **非静态方法中既可以调用静态的也可以调用非静态的方法或属性 **
- **在静态方法内不能使用this或super关键字**
- **static修饰的方法不能被重写**

### 在开发中，如何确定一个属性或方法是否声明为static的？

- **类属性、类方法的设计思想**

- 类属性作为该类各个对象之间共享的变量。在设计类时,分析哪些属性不因对象的不同而改变，将这些属性设置为类属性。
   - 操作静态变量的相应的方法设置为类方法。
   - 类中的常量也常常声明为static
- 如果方法与调用者无关，则这样的方法通常被声明为类方法，由于不需要创建对象就可以调用类方法，从而简化了方法的调用。
   - 工具类中的方法，习惯上声明为static，如Math，Array，Collections 
   

### 例1

```
package com.zjk;

public class StaticTest {
	public static void main(String[] args) {
		Chinese.nation = "中国";// 静态变量随着类的加载而加载

		Chinese c1 = new Chinese();
		c1.name = "黎明";
		c1.age = 18;
		c1.nation = "CHN";

		Chinese c2 = new Chinese();
		c2.name = "助手";
		c2.age = 30;

		System.out.println(c1.nation); // CHN
		System.out.println(c2.nation); // CHN

		c2.nation = "CHINA";
		System.out.println(c1.nation); // CHIAN
		System.out.println(c2.nation); // CHINA

		c1.eat();

		Chinese.show();// 静态方法随着类的加载而加载
	}
}

class Chinese {
	String name;
	int age;
	static String nation; // 静态变量

	public void eat() {
		System.out.println("吃饭");
		// 调用非静态结构
		this.info();

		// 调用静态结构
		walk(); // 调用的类的Chinese.walk()
		System.out.println(nation); // 调用的是类的Chinese.nation
	}

	public static void show() { // 静态方法
		System.out.println("中国人");
//		eat(); 静态方法中只能调用静态的方法或属性
		walk();

//		System.out.println(name); //name非静态
		System.out.println(nation); // 不是this.nation
		// 而是Chinese.nation 即类.静态属性
	}

	public static void walk() {
		System.out.println("walk");
	}

	public void info() {
		System.out.println("name:" + name + "\tage: " + age);
	}
}
```

### 例2

```
package com.zjk;

public class CircleTest {
	public static void main(String[] args) {

		Circle c1 = new Circle();
		
		Circle c2 = new Circle();
		
		Circle c3 = new Circle(2.0);
		
		System.out.println("c1_id: " + c1.getId());
		System.out.println("c2_id: " + c2.getId());
		
		System.out.println("total: " + Circle.getTotal());
	}
}

class Circle {

	private double radius;
	private int id;

	public Circle() {
		id = init++;
		total++;
	}
	
	public Circle(double radius) {
		this();
		this.radius = radius;
	}

	private static int total; // 圆的总数
	private static int init = 1001; // 给id赋值
	//static声明的属性被所有对象共享

	public double findArea() {
		return Math.PI * radius * radius;
	}

	public int getId() {
		return id;
	}

	public static int getTotal() {
		return total;
	}
}
```
### 练习1
编写一个类实现银行账户的概念，包含的属性有“帐号”、“密码”、“存款余额”、“利率”、“最小余额”，定义封装这些属性的方法。账号要自动生成。编写主类，使用银行账户类，输入、输出3个储户的上述信息。

考虑：哪些属性可以设计成static属性。

```
package com.zjk;

public class AccountTest {
	public static void main(String[] args) {
		Account a1 = new Account();
		a1.show();

		Account a2 = new Account("123",1000);
		a2.show();
		
		System.out.println(a1);
	}
}

class Account {
	private int customer; // 账号
	private String code = "123456";// 密码
	private double balance; // 余额

	private static double rate = 0.15; // 利率
	private static double minBalance = 30000; // 最小余额
	private static int id = 1001; // 用于生成账号

	public Account() {
		customer = id++;
	}

	public Account(String code, double balance) {
		customer = id++;
		this.code = code;
		this.balance = balance;
	}

	public void show() {
		System.out.println("*************************");
		System.out.println("customer:  \t" + customer);
		System.out.println("balance:   \t" + balance);
		System.out.println("rate:      \t" + rate);
		System.out.println("minBalance:\t" + minBalance);
		System.out.println("*************************");
	}

	public void getMinbalance() {
		System.out.println(minBalance);
	}

	@Override
	public String toString() {
		return "Account [customer=" + customer + ", code=" + code + ", balance=" + balance + "]";
	}
}
```

### main()方法可以作为我们与控制台交互的方式

#### eclipse
```
package com.zjk;

public class MainDemo {
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			System.out.println("*****" + args[i]);
		}
		
	}
}
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/3364418239472.png =460x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/25953914221046.png =534x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/125253914239472.png =303x)


```
package com.zjk;

public class MainDemo {
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			System.out.println("*****" + args[i]);

			int num = Integer.parseInt(args[i]);
			System.out.println("#######" + num);
		}

	}
}
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/143835418227339.png =531x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/267235418247505.png =297x)


#### cmd

```
public class MainDemo {
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			System.out.println("*****" + args[i]);

			int num = Integer.parseInt(args[i]);
			System.out.println("#######" + num);
		}

	}
}
```

```
G:\vim_test>javac MainDemo.java 
G:\vim_test>java MainDemo.java "87" 89 "66"
```

#### 如何将控制台获取的数据传给形参 String[] args

```
运行时：java 类名 "Tom" "123" "true"

System.out.println(args[0]) //"Tom"
System.out.println(args[2]) //"true" --> Boolean.parseBoolean(args[2])
System.out.println(args[3]) //报异常
```

### 面试题

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/272661709240097.png =633x)


## 类的成员之四：代码块(初始化块)

- 代码块(或初始化块)的作用：
  - 对Java类或对象进行初始化 
  - 代码块(或初始化块)的分类：
- 一个类中代码块若有修饰符，则只能被static修饰，称为静态代码块(static block)，没有使用static修饰的，为非静态代码块。
  - 有且只能使用static修饰 
- static代码块通常用于初始化static的属性

```
class Person {
    public static int total;
    
    static {
        total = 100;//为total赋初值
    }
    …… //其它属性或方法声明
}
```

-  **静态代码块：用static 修饰的代码块**
1. 可以有输出语句。
2. 可以对类的属性、类的声明进行初始化操作。
3. **不可以对非静态的属性初始化。即：不可以调用非静态的属性和方法。**
4. 若有多个静态的代码块，那么按照**从上到下的顺序**依次执行。
5. **静态代码块的执行要先于非静态代码块。**
6. **静态代码块随着类的加载而加载，且只执行一次**。

- **非静态代码块**：没有static修饰的代码块
1. 可以有输出语句。
2. 可以在创建对象时，对类（对象）的属性、类（对象）的声明进行初始化操作。
3. 除了调用非静态的结构外，还可以调用静态的变量或方法。
4. 若有多个非静态的代码块，那么按照**从上到下的顺序**依次执行。
5. **每次创建对象的时候，都会执行一次。且先于构造器执行**。

```java
package com.zjk;

public class BlockTest {
	public static void main(String[] args) {

		String desc = Person.desc;
//		静态代码块随着类的加载而加载且执行
//		只执行一次
		String desc2 = Person.desc;

		System.out.println(desc);

		Person p1 = new Person();
//		非静态代码块随着对象的创建而执行
//		且每创建一次对象，就执行一次
		Person p2 = new Person();

	}
}

class Person {
	String name;
	int age;
	static String desc = "我是一个人";

	public Person() {

	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

//	代码块
//	如果在类中定义了多个静态代码块
//	则按照声明的先后顺序执行;
//	静态代码块的执行优先与非静态代码块
	static {
		System.out.println("static block - 1");
		desc = "学习的人";
		info();
//		eat();
//		在静态代码块中只能调用静态的属性和结构
	}

	static {
		System.out.println("static block - 2");
	}
//	如果在类中定义了多个非静态代码块
//	则按照声明的先后顺序执行;
	{
		System.out.println("block - 1");
		age = 1; // 给对象赋初值
		desc = "学习的人";
		info();
		eat();
//		非静态代码块都可以调用
	}

	{
		System.out.println("block - 2");
	}

	public void eat() {
		System.out.println("吃饭");
	}
	
	public static void info() {
		System.out.println("快乐的人");
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}
```


### 程序中成员变量赋值的执行顺序

- 对属性可以赋值的位置

1. 默认初始化
2. 显式初始化/在代码块中赋值(谁在上，谁先)
3. 构造器中初始化
4. 有了构造器后：“对象.属性”或“对象.方法”的方式，进行赋值

**变量赋值执行顺序：默认初始化-->静态代码块-->非静态代码块-->构造器-->对象.属性/对象.方法**


![](C:/Users/zjk10/OneDrive/NoteBook/pictures/485915210221046.png =462x)

```
package com.zjk;

public class OrderTest {
	public static void main(String[] args) {
		Order order = new Order();
		System.out.println(order.orderId);
	}
}

class Order {
	
//	显式初始化/在代码块中赋值(谁在上，谁先)
	int orderId = 3;
	{
		orderId = 4;
	}
	
}
```

```
package com.zjk;

class Root{
	static{
		System.out.println("Root的静态初始化块");
	}
	{
		System.out.println("Root的普通初始化块");
	}
	public Root(){
		System.out.println("Root的无参数的构造器");
	}
}
class Mid extends Root{
	static{
		System.out.println("Mid的静态初始化块");
	}
	{
		System.out.println("Mid的普通初始化块");
	}
	public Mid(){
		System.out.println("Mid的无参数的构造器");
	}
	public Mid(String msg){
		//通过this调用同一类中重载的构造器
		this();
		System.out.println("Mid的带参数构造器，其参数值："
			+ msg);
	}
}
class Leaf extends Mid{
	static{
		System.out.println("Leaf的静态初始化块");
	}
	{
		System.out.println("Leaf的普通初始化块");
	}	
	public Leaf(){
		//通过super调用父类中有一个字符串参数的构造器
		super("尚硅谷");
		System.out.println("Leaf的构造器");
	}
}
public class LeafTest{
	public static void main(String[] args){
		new Leaf(); 
		//new Leaf();
	}
}
```

```
package com.zjk;

class Father {
	static {
		System.out.println("11111111111");
	}
	
	{
		System.out.println("22222222222");
	}

	public Father() {
		System.out.println("33333333333");

	}

}

public class Son extends Father {
	static {
		System.out.println("44444444444");
	}

	{
		System.out.println("55555555555");
	}

	public Son() {
		System.out.println("66666666666");
	}

	public static void main(String[] args) { // 由父及子 静态先行
		System.out.println("77777777777");
		System.out.println("************************");
		new Son();
		System.out.println("************************");

		new Son();
		System.out.println("************************");
		new Father();
	}

}
```

## 关键字：final

- 在Java中声明类、变量和方法时，可使用关键字final来修饰,表示“最终的”。
    - **final标记的类不能被继承**。提高安全性，提高程序的可读性。
       - String类、System类、StringBuffer类
    - **final标记的方法不能被子类重写**。
       - 比如：Object类中的getClass()。
    - final标记的变量(成员变量或局部变量)即称为常量。名称大写，且只能被赋值一次。
       - **final标记的成员变量必须在声明时或在<mark>每个</mark>构造器中或代码块中显式赋值，然后才能使用**。
       - final double MY_PI = 3.14;

**final修饰变量--常量**

- 修饰属性。可以考虑赋值的位置：显式初始化/代码块中初始化/构造器中初始化
- 修饰局部变量		
   - 使用final修饰形参时，为常量。当我们调用此方法时，给常量形参赋一个实参，一旦赋值以后，就只能在方法中使用此形参，但不能进行重新赋值
- static final  修饰属性 ： 全局常量
   
```java
package com.zjk;

public class FinalTest {

//	final int WIDTH;
//	final 不适用于默认初始化,必须显式初始化
	final int WIDTH = 10;

	final int LEFT;
	{
		LEFT = 1; // 可以在代码块中赋值
	}

	final int RIGHT;

	public FinalTest() { // 可以在构造器中初始化
		// 每个构造器中都应该要为RIGHT赋值
		RIGHT = 2;
	}

	public FinalTest(int n) {
		RIGHT = n;
	}

//	public FinalTest(int n, int m) {
//	} //每个构造器中都应该要为RIGHT赋值

//	final int DOWN;
//  不可以在方法中调用final赋值。
//	在创建对象，调用构造器时，就需要得到final变量的值
//	public void setDown(int down) {
//		this.DOWN = down;
//	}

	public void doWith() {
//		WIDTH = 20; WIDTH被final修饰
	}
	
	public void show() {
		final int NUM = 10; //常量（局部变量）
//		NUM ++;
	}
	
	public final void show(final int NUM) {
//		NUM = 10; 
		//使用final修饰形参时，为常量
		//当我们调用此方法时，给常量形参赋一个实参，
		//一旦赋值以后，就只能在方法中使用此形参，但不能进行修改
	}

	public static void main(String[] args) {

		int num = 10;

		num = num + 5;
	}
}
```

### 面试题

#### 1

```
public class Something {
	public int addOne(final int x) {
		return ++x;  //编译出错。X的值不能改变
     // return x + 1; //编译通过
	}
}
```

#### 2

```
public class Something {
	public static void main(String[] args) {
		Other o = new Other();
		new Something().addOne(o);
	}

	public void addOne(final Other o) {
     // o = new Other(); //o的地址值不能改变
		o.i++; //但是o的地址值中的内容可以改变 
	}
}

class Other {
	public int i;
}
```

## 抽象类与抽象方法

- 用**abstract关键字来修饰一个类，这个类叫做抽象类**。
    - **抽象类不能被实例化**。抽象类是用来被继承的，（抽象类可以使用多态）
        - new  抽象类()；是非法的
    - 抽象类中一定有构造器，便于子类实例化时调用（涉及子类对象实例化的全过程）
    - 开发中，都会提供抽象类的子类，让子类对象实例化，完成相关操作。
- 用abstract来修饰一个方法，该方法叫做抽象方法。
    - **抽象方法：只有方法的声明，没有方法的实现。以分号结束：（没有代码块{}）**
    - **含有抽象方法的类必须被声明为抽象类**。
         - **而抽象类中不一定拥有抽象方法，可以有普通的方法**
    - **抽象类的子类必须重写父类（包括父类的父类...）的抽象方法**，并提供方法体。才可以进行实例化
         - 若没有重写全部的抽象方法，仍为抽象类。**否则必须使用abstract修饰**，不能进行实例化。
- 不能用abstract修饰变量、代码块、构造器；
- **不能用abstract修饰私有方法private、静态方法static、final的方法、final的类。**
    - 父类中private的结构对子类不可见，不是重写
    - static修饰的方法不能被重写
    - final修饰的方法不能重写 
    - final修饰的类不能被继承

```java
package com.zjk;

public class AbstractTest {
	public static void main(String[] args) {

//		Person p1 = new Person();
//		abstract修饰的类，抽象类不可实例化

//		Person.name;
//	    就算给抽象类的属性static修饰，也不能直接通过类.属性的方式调用
	}
}

abstract class Creature{
	public abstract void breath();
}

abstract class Person extends Creature {
    String name;
    //static String name;
	int age;

	public Person() {

	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

//	不是抽象方法
//	public void eat() {
//		
//	}
	
//	抽象方法
	public abstract void eat();
	
//  若类是抽象类则无需重写继承自抽象父类的抽象方法
	
	public void walk() {
		System.out.println("walk");
	}
}

class Student extends Person {

	public Student(String name,int age) {
		super(name,age);
	}
	
	@Override
	public void eat() {
		System.out.println("eat more");
	}
	
//	Person类中还有继承自Creature类的抽象方法breath()，需要重写
	@Override
	public void breath() {
		System.out.println("breath");
	}
}
```

### 练 习2

```
编写一个Employee类，声明为抽象类，
包含如下三个属性：name，id，salary。
提供必要的构造器和抽象方法：work()。
对于Manager类来说，他既是员工，还具有奖金(bonus)的属性。
请使用继承的思想，设计CommonEmployee类和Manager类，
要求类中提供必要的方法进行属性访问。
```

```java
package com.zjk;

public class EmployeeTest {
	public static void main(String[] args) {
		
		Manager manager = new Manager("King",1001,7000,80000);
//		Employee manager = new Manager("King",1001,7000,80000);
//		抽象类可以使用多态
		manager.work();
		
		CommonEmployee c = new CommonEmployee();
		c.work();
	}
}

abstract class Employee {

	private String name;
	private int id;
	private double salary;

	public Employee() {

	}

	public Employee(String name, int id, int salary) {
		this.name = name;
		this.id = id;
		this.salary = salary;
	}

	public abstract void work();
}

class CommonEmployee extends Employee {

	@Override
	public void work() {
		System.out.println("Common Work");
	}
}

class Manager extends Employee {

	private double bonus;

	public Manager(double bonus) {
		super();
		this.bonus = bonus;
	}

	public Manager(String name, int id, int salary, double bonus) {
		super(name, id, salary);
		this.bonus = bonus;
	}

	@Override
	public void work() {
		System.out.println("Manager Work");
	}
}
```

### 抽象类的匿名子类

```java
package com.zjk;

public class PersonTest {

	public static void main(String[] args) {
		method(new Student());// 匿名对象

		Worker worker = new Worker();
		method1(worker); // 非匿名的类，非匿名的对象

		method1(new Worker()); // 非匿名的类，匿名的对象

		System.out.println();

//		抽象类的匿名子类
//		相当于创建了匿名子类的对象p
		Person p = new Person() {

			@Override
			public void eat() {
				// TODO Auto-generated method stub
				System.out.println("eat food");
			}

			@Override
			public void breath() {
				// TODO Auto-generated method stub
				System.out.println("breath too");
			}

		};

		method1(p); // 此时调用的是匿名子类的对象

		System.out.println();
		
//		创建匿名子类的匿名对象
		method1(new Person() {
			@Override
			public void eat() {
				// TODO Auto-generated method stub
				System.out.println("eat food 2");
			}

			@Override
			public void breath() {
				// TODO Auto-generated method stub
				System.out.println("breath too 2");
			}
		});
	}

	public static void method1(Person p) {
		p.eat();
		p.walk();
	}

	public static void method(Student s) {

	}
}

class Worker extends Person {
	@Override
	public void eat() {
		// TODO Auto-generated method stub

	}

	@Override
	public void breath() {
		// TODO Auto-generated method stub

	}
}
```

### 多态(抽象类)的应用：模板方法设计模式(TemplateMethod)

- 抽象类体现的就是一种模板模式的设计，抽象类作为多个子类的通用模板，子类在抽象类的基础上进行扩展、改造，但子类总体上会保留抽象类的行为方式。

- 解决的问题：
   - 当功能内部一部分实现是确定的，一部分实现是不确定的。这时可以把不确定的部分暴露出去，让子类去实现。
   - 换句话说，在软件开发中实现一个算法时，整体步骤很固定、通用，这些步骤已经在父类中写好了。但是某些部分易变，易变部分可以抽象出来，供不同子类实现。这就是一种模板模式。
   
- 模板方法设计模式是编程中经常用得到的模式。各个框架、类库中都有他的影子，比如常见的有：
   - 数据库访问的封装
   - Junit单元测试
   - JavaWeb的Servlet中关于doGet/doPost方法调用
   - Hibernate中模板程序
   - Spring中JDBCTemlate、HibernateTemplate等
   
```java
package com.zjk;

public class TemplateTest {
	public static void main(String[] args) {
		Template t = new SubTemplate();
		t.spendTime();
	}
}

abstract class Template {

//	计算某段代码执行所需要花费的时间
	public void spendTime() {
		long start = System.currentTimeMillis();

		code();//不确定的部分

		long end = System.currentTimeMillis();

		System.out.println("花费的时间：" + (end - start));
	}

//	不确定（易变）的部分
	public abstract void code();
}

class SubTemplate extends Template {

	@Override
	public void code() {
		// TODO Auto-generated method stub
		for (int i = 2; i <= 1000; i++) {
			boolean isFlag = true;
			for (int j = 2; j < Math.sqrt(i); j++) {
				if (i % j == 0) {
					isFlag = false;
					break;
				}
			}
			if (isFlag) {
				System.out.println(i);
			}
		}
	}

}
```

```java
package com.atguigu.java;
//抽象类的应用：模板方法的设计模式
public class TemplateMethodTest {

	public static void main(String[] args) {
		BankTemplateMethod btm = new DrawMoney();
		btm.process();

		BankTemplateMethod btm2 = new ManageMoney();
		btm2.process();
	}
}
abstract class BankTemplateMethod {
	// 具体方法
	public void takeNumber() {
		System.out.println("取号排队");
	}

	public abstract void transact(); // 办理具体的业务 //钩子方法

	public void evaluate() {
		System.out.println("反馈评分");
	}

	// 模板方法，把基本操作组合到一起，子类一般不能重写
	public final void process() {
		this.takeNumber();

		this.transact();// 像个钩子，具体执行时，挂哪个子类，就执行哪个子类的实现代码

		this.evaluate();
	}
}

class DrawMoney extends BankTemplateMethod {
	public void transact() {
		System.out.println("我要取款！！！");
	}
}

class ManageMoney extends BankTemplateMethod {
	public void transact() {
		System.out.println("我要理财！我这里有2000万美元!!");
	}
}
```

#### 练习3
编写工资系统，实现不同类型员工(多态)的按月发放工资。如果当月出现某个Employee对象的生日，则将该雇员的工资增加100元。

实验说明：
```
（1）定义一个Employee类，该类包含：
private成员变量name,number,birthday，其中birthday 为MyDate类的对象；
abstract方法earnings()；
toString()方法输出对象的name,number和birthday。
（2）MyDate类包含:
private成员变量year,month,day ；
toDateString()方法返回日期对应的字符串：xxxx年xx月xx日
（3）定义SalariedEmployee类继承Employee类，实现按月计算工资的员工处理。
该类包括：private成员变量monthlySalary；
实现父类的抽象方法earnings() ,该方法返回monthlySalary值；
toString()方法输出员工类型信息及员工的name，number,birthday。
（4）参照SalariedEmployee类定义HourlyEmployee类，实现按小时计算工资的员工处理。该类包括：
private成员变量wage和hour；
实现父类的抽象方法earnings(),该方法返回wage*hour值；
toString()方法输出员工类型信息及员工的name，number,birthday。
（5）定义PayrollSystem类，创建Employee变量数组并初始化，该数组存放各类雇员对象的引用。
利用循环结构遍历数组元素，输出各个对象的类型,name,number,birthday,以及该对象生日。
当键盘输入本月月份值时，如果本月是某个Employee对象的生日，还要输出增加工资信息。
提示：
//定义People类型的数组People c1[]=new People[10];
//数组元素赋值
c1[0]=new People("John","0001",20);
c1[1]=new People("Bob","0002",19);
//若People有两个子类Student和Officer，则数组元素赋值时，可以使父类类型的数组元素指向子类。
c1[0]=new Student("John","0001",20,85.0);
c1[1]=new Officer("Bob","0002",19,90.5);
```

```java
package com.zjk;

import java.util.Scanner;

public abstract class Employee {

	private String name;
	private int number;
	private MyDate birthday;

	public Employee(String name, int number, MyDate birthday) {
		super();
		this.name = name;
		this.number = number;
		this.birthday = birthday;
	}

	public abstract double earnings();

	@Override
	public String toString() {
		return "name=" + name + ", number=" + number + ", birthday=" + birthday.toDateString();
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public MyDate getBirthday() {
		return birthday;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(int num) {
		this.number = num;
	}

	public void setBirthday(MyDate birthday) {
		this.birthday = birthday;
	}

}

class MyDate {

	private int year;
	private int month;
	private int day;

	
	public MyDate(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public String toDateString() {
		return year + "年" + month + "月" + day + "日";
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setDay(int day) {
		this.day = day;
	}

}

class SalariedEmployee extends Employee {

	private double monthlySalary;

	public SalariedEmployee(String name, int number, MyDate birthday, double monthlySalary) {
		super(name, number, birthday);
		this.monthlySalary = monthlySalary;
	}

	@Override
	public double earnings() {
		// TODO Auto-generated method stub
		return monthlySalary;
	}

	@Override
	public String toString() {
		return "SalariedEmployee [" + super.toString() + ", monthlySalary=" + monthlySalary + "]";
	}
}

class HourlyEmployee extends Employee {

	private double wage;
	private double hour;

	public HourlyEmployee(String name, int number, MyDate birthday, double wage, double hour) {
		super(name, number, birthday);
		// TODO Auto-generated constructor stub
		this.wage = wage;
		this.hour = hour;
	}

	@Override
	public double earnings() {
		// TODO Auto-generated method stub
		return wage * hour;
	}

	@Override
	public String toString() {
		return "HourlyEmployee [" + super.toString() + ", wage*hour=" + wage * hour + "]";
	}

}

package com.zjk;

import java.util.Scanner;

public abstract class Employee {

	private String name;
	private int number;
	private MyDate birthday;

	public Employee(String name, int number, MyDate birthday) {
		super();
		this.name = name;
		this.number = number;
		this.birthday = birthday;
	}

	public abstract double earnings();

	@Override
	public String toString() {
		return "name=" + name + ", number=" + number + ", birthday=" + birthday.toDateString();
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public MyDate getBirthday() {
		return birthday;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(int num) {
		this.number = num;
	}

	public void setBirthday(MyDate birthday) {
		this.birthday = birthday;
	}

}

class MyDate {

	private int year;
	private int month;
	private int day;

	
	public MyDate(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public String toDateString() {
		return year + "年" + month + "月" + day + "日";
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setDay(int day) {
		this.day = day;
	}

}

class SalariedEmployee extends Employee {

	private double monthlySalary;

	public SalariedEmployee(String name, int number, MyDate birthday, double monthlySalary) {
		super(name, number, birthday);
		this.monthlySalary = monthlySalary;
	}

	@Override
	public double earnings() {
		// TODO Auto-generated method stub
		return monthlySalary;
	}

	@Override
	public String toString() {
		return "SalariedEmployee [" + super.toString() + ", monthlySalary=" + monthlySalary + "]";
	}
}

class HourlyEmployee extends Employee {

	private double wage;
	private double hour;

	public HourlyEmployee(String name, int number, MyDate birthday, double wage, double hour) {
		super(name, number, birthday);
		// TODO Auto-generated constructor stub
		this.wage = wage;
		this.hour = hour;
	}

	@Override
	public double earnings() {
		// TODO Auto-generated method stub
		return wage * hour;
	}

	@Override
	public String toString() {
		return "HourlyEmployee [" + super.toString() + ", wage*hour=" + wage * hour + "]";
	}

}
```
## 接口(interface)

- 一方面，有时必须从几个类中派生出一个子类，继承它们所有的属性和方法。但是，Java不支持多重继承。有了接口，就可以得到多重继承的效果。
- 另一方面，有时必须从几个类中抽取出一些共同的行为特征，而它们之间又没有is-a的关系，仅仅是具有相同的行为特征而已。
- 接口就是规范，定义的是一组规则，体现了现实世界中“如果你是/要...则必须能...”的思想。
    - 继承是一个"是不是"的关系，而接口实现则是 "能不能"的关系。
- 接口的本质是契约，标准，规范

- **接口的特点：**

- 用interface来定义。
- 接口和类是并列关系，或者可以理解为一种特殊的类。
- 接口(interface)是抽象方法和常量值定义的集合。
    - JDK7以之前：只能定义全局常量和抽象方法
    - 从本质上讲，接口是一种特殊的抽象类，这种抽象类中只包含常量和方法的定义(JDK7.0及之前)，而没有变量和方法的实现。
        - 全局常量：public static final修饰的 编译时可以省略
           - **接口中的所有成员变量都默认是由public static final修饰的**。
        - 抽象方法：public abstract修饰的 编译时可以省略
           - **接口中的所有抽象方法都默认是由public abstract修饰的。**
    - JDK8：
        - 除了定义JDK7之前的，还可以：
        - 定义静态方法pulic static
        - 默认方法
- **接口中没有构造器。不能实例化**
   - 接口的主要用途就是被实现类实现。（面向接口编程）
   - 实现接口的类中必须提供接口中所有方法的具体实现内容(覆盖接口中的所有抽象方法)，方可实例化。否则，仍为抽象类。
- 接口采用多继承机制。**Java类可以实现多个接口**. **多态性**
   - 与继承关系类似，接口与实现类之间存在多态性
   - 定义Java类的语法格式：先写extends，后写implements

```java
class SubClass extends SuperClass implements InterfaceA{ 
   ...
}
```

- **一个类可以实现(implements)多个接口，接口也可以继承(extends)其它接口(可以多继承)**。

```java
interface 接口1 extends 接口2,接口3{ //不是implements
   ...
}
```

### 例

```java
package com.zjk;

public class InterfaceTest {
	public static void main(String[] args) {

		System.out.println(Flyable.MAX_SPEED);
		System.out.println(Flyable.MIN_SPEED);

		Plane p = new Plane();
		p.fly();
		p.stop();
	}
}

interface Flyable {
//	全局常量
	public static final int MAX_SPEED = 7900;
	int MIN_SPEED = 1; // 默认public static final
	// 仍然是全局常量

//	抽象方法
	public abstract void fly();

//	仍然是抽象方法 默认public abstract
	void stop();

//	Interfaces cannot have constructors
//	接口不能有构造器，不能实例化
//	public Flyable() {
//		
//	}
}

interface Attackable {
	void attack();
}

class Plane implements Flyable {

	@Override
	public void fly() {
		// TODO Auto-generated method stub
		System.out.println("引擎起飞");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("减速停止");
	}
}

abstract class Kite implements Flyable {
// 实现接口的类必须覆盖接口的所有方法，否则仍然为抽象类
	@Override
	public void fly() {
		// TODO Auto-generated method stub

	}
}

class Bullet extends Object implements Flyable, Attackable {

	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void fly() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}

interface AA{
	
}

interface BB{
	
}

interface CC extends AA,BB{
	
}
```

```java
package com.zjk;

public class USBTest {
	public static void main(String[] args) {
		Computer com = new Computer();
//		1.创建了接口的非匿名实现类的非匿名对象
		Flash falsh = new Flash();
		com.transferDate(falsh);
//		2.创建接口的非匿名实现类的匿名对象
		com.transferDate(new Flash());
//		3.创建接口的匿名实现类的非匿名对象
		USB phone = new USB() {

			@Override
			public void start() {
				// TODO Auto-generated method stub
				System.out.println("手机工作");
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub
				System.out.println("手机结束工作");
			}

		};
		com.transferDate(phone);
//		4.创建接口的匿名实现类的匿名对象
		com.transferDate(new USB() {

			@Override
			public void start() {
				// TODO Auto-generated method stub
				System.out.println("mp3开始工作");
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub
				System.out.println("mp3结束工作");
			}

		});
	}
}

class Computer {
//	接口的多态性
	public void transferDate(USB usb) {
		usb.start();

		System.out.println("具体传输数据细节");

		usb.stop();
	}
}

interface USB {

	void start();

	void stop();
}

class Flash implements USB {

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("U盘开始工作");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("U盘结束工作");
	}

}

class Printer implements USB {

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("打印机开始工作");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("打印机开始工作");
	}

}
```

### 接口的应用：代理模式(Proxy)

代理模式是Java开发中使用较多的一种设计模式。代理设计就是为其他对象提供一种代理以控制对这个对象的访问。

- 应用场景：
   - 安全代理：屏蔽对真实角色的直接访问。
   - 远程代理：通过代理类处理远程方法调用（RMI）
   - 延迟加载：先加载轻量级的代理对象，真正需要再加载真实对象

比如你要开发一个大文档查看软件，大文档中有大的图片，有可能一个图片有100MB，在打开文件时，不可能将所有的图片都显示出来，这样就可以使用代理模式，当需要查看图片时，用proxy来进行大图片的打开。

- 分类
   - 静态代理（静态定义代理类） 
   - 动态代理（动态生成代理类）
      - JDK自带的动态代理，需要反射等知识
   
```java
package com.zjk;

public class NetWorkTest {

	public static void main(String[] args) {
		Server server = new Server();
		ProxyServer proxyServer = new ProxyServer(server);
		
		proxyServer.browse();
	}
}

interface NetWork {
	void browse();
}

//被代理类
class Server implements NetWork {

	@Override
	public void browse() {
		// TODO Auto-generated method stub
		System.out.println("真实的服务器访问网络");
	}

}

//代理类

class ProxyServer implements NetWork {

	private NetWork work;

	public ProxyServer(NetWork work) {
		this.work = work;
	}

	public void check() {
		System.out.println("检查工作");
	}

	@Override
	public void browse() {
		// TODO Auto-generated method stub
		check();

		work.browse();
	}

}
```

### 接口的应用：工厂模式

- 实现了创建者与调用者的分离，即将创建对象的具体过程屏蔽隔离起来，达到提高灵活性的目的。

**面向对象的设计原则（总共六个）：这里说几个和工厂模式相关的**

- OCP（开闭原则，Open-Closed Principle） 
   - 一个软件的实体应当对扩展开放，对修改关闭。
   - 当我们写完的代码，不能因为需求变化就修改。我们可以通过新增代码的方式来解决变化的需求。如果每次需求变动都去修改原有的代码，那原有的代码就存在被修改错误的风险，当然这其中存在有意和无意的修改，都会导致原有正常运行的功能失效的风险，这样很有可能会展开可怕的蝴蝶效应，使维护工作剧增。
   - 说到底，开闭原则除了表面上的可扩展性强以外，在企业中更看重的是维护成本
   - 所以，开闭原则是设计模式的第一大原则，它的潜台词是：控制需求变动风险，缩小维护成本。
- DIP（依赖倒转原则，Dependence Inversion Principle） 
   - 要针对接口编程，不要针对实现编程。
   - 如果 A 中关联 B，那么尽量使得 B 实现某个接口，然后 A 与接口发生关系，不与 B 实现类发生关联关系。
   - 依赖倒置的潜台词是：面向抽象编程，解耦调用和被调用者。
- LOD（迪米特法则，Law Of Demeter） 
   - 只与你直接的朋友通信，而避免和陌生人通信。
   - 要求尽量的封装，尽量的独立，尽量的使用低级别的访问修饰符。这是封装特性的典型体现。
   - 一个类如果暴露太多私用的方法和字段，会让调用者很茫然。并且会给类造成不必要的判断代码。所以，我们使用尽量低的访问修饰符，让外界不知道我们的内部。这也是面向对象的基本思路。这是迪米特原则的一个特性，无法了解类更多的私有信息。另外，迪米特原则要求类之间的直接联系尽量的少，两个类的访问，通过第三个中介类来实现。
   - 迪米特原则的潜台词是：不和陌生人说话，有事去中介。

**工厂模式的分类：**

- 简单工厂模式：用来生产同一等级结构中的任意产品。（对于增加新的产品，需要修改已有代码）
- 工厂方法模式：用来生产同一等级结构中的固定产品。（支持增加任意产品）
- 抽象工厂模式：用来生产不同产品族的全部产品。（对于增加新的产品，无能为力；支持增加产品族

GOF 在《设计模式》一书中将工厂模式分为两类：工厂方法模式（Factory Method）与抽象工厂模式（Abstract Factory）。将简单工厂模式（Simple Factory）看为工厂方法模式的一种特例，两者归为一类。

**核心本质：**
- 实例化对象，用工厂方法代替 new 操作。
- 将选择实现类、创建对象统一管理和控制。从而将调用者跟我们的实现类解耦。

#### 1、无工厂模式

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/271765020247508.png =302x)

```java
package com.atguigu.pattern.factory.nofactory;

interface Car {
	void run();
}

class Audi implements Car {
	public void run() {
		System.out.println("奥迪在跑");
	}
}

class BYD implements Car {
	public void run() {
		System.out.println("比亚迪在跑");
	}
}

public class Client01 {
	public static void main(String[] args) {
		Car a = new Audi();
		Car b = new BYD();
		a.run();
		b.run();
	}
}
```
#### 2、简单工厂模式(广义的工厂)

- 存在的目的很简单：定义一个用于创建对象的工厂类。
- 调用者只要知道他要什么，从哪里拿，如何创建，不需要知道。分工，多出了一个专门生产 Car 的实现类对象的工厂类。把调用者与创建者分离。
- 简单工厂模式也叫静态工厂模式，就是工厂类一般是使用静态方法，通过接收的参数的不同来返回不同的实例对象。
- 缺点：对于增加新产品，不修改代码的话，是无法扩展的。违反了开闭原则（对扩展开放；对修改封闭）。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/127305020227342.png =318x)

```
package com.atguigu.pattern.factory.simple;

interface Car {
	void run();
}

class Audi implements Car {
	public void run() {
		System.out.println("奥迪在跑");
	}
}

class BYD implements Car {
	public void run() {
		System.out.println("比亚迪在跑");
	}
}

//工厂类
class CarFactory {
//方式一
	public static Car getCar(String type) {
		if ("奥迪".equals(type)) {
			return new Audi();
		} else if ("比亚迪".equals(type)) {
			return new BYD();
		} else {
			return null;
		}
	}
//方式二
//public static Car getAudi() {
//    return new Audi();
//}
//
//public static Car getByd() {
//    return new BYD();
//}
}

public class Client02 {
	public static void main(String[] args) {
		Car a = CarFactory.getCar("奥迪");
		a.run();
		Car b = CarFactory.getCar("比亚迪");
		b.run();
	}
}
```
#### 3、工厂方法模式 

- 为了避免简单工厂模式的缺点，不完全满足 OCP（对扩展开放，对修改关闭）。
- 工厂方法模式和简单工厂模式最大的不同在于，简单工厂模式只有一个（对于一个项目或者一个独立的模块而言）工厂类，而工厂方法模式有一组实现了相同接口的工厂类。这样在简单工厂模式里集中在工厂方法上的压力可以由工厂方法模式里不同的工厂子类来分担。



![](C:/Users/zjk10/OneDrive/NoteBook/pictures/523314820239475.png =549x)
简单工厂模式与工厂方法模式真正的避免了代码的改动了？没有。在简单工厂模式中，新产品的加入要修改工厂角色中的判断语句；而在工厂方法模式中，要么将判断逻辑留在抽象工厂角色中，要么在客户程序中将具体工厂角色写死（就像上面的例子一样）。而且产品对象创建条件的改变必然会引起工厂角色的修改。面对这种情况，Java 的反射机制与配置文件的巧妙结合突破了限制——这在Spring 中完美的体现了出来。

```
package com.atguigu.pattern.factory.method;

interface Car {
	void run();
}

//两个实现类
class Audi implements Car {
	public void run() {
		System.out.println("奥迪在跑");
	}
}

class BYD implements Car {
	public void run() {
		System.out.println("比亚迪在跑");
	}
}

//工厂接口
interface Factory {
	Car getCar();
}

//两个工厂类
class AudiFactory implements Factory {
	public Audi getCar() {
		return new Audi();
	}
}

class BydFactory implements Factory {
	public BYD getCar() {
		return new BYD();
	}
}

public class Client {
	public static void main(String[] args) {
		Car a = new AudiFactory().getCar();
		Car b = new BydFactory().getCar();
		a.run();
		b.run();
	}
}
```
#### 4、抽象工厂模式

- 抽象工厂模式和工厂方法模式的区别就在于需要创建对象的复杂程度上。而且抽象工厂模式是三个里面最为抽象、最具一般性的。
- 抽象工厂模式的用意为：给客户端提供一个接口，可以创建多个产品族中的产品对象。
- 而且使用抽象工厂模式还要满足一下条件：
  1. 系统中有多个产品族，而系统一次只可能消费其中一族产品。
  2. 同属于同一个产品族的产品以其使用。

### 接口和抽象类之间的对比

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/58533111239472.png =656x)

在开发中，常看到一个类不是去继承一个已经实现好的类，而是要么继承抽象类，要么实现接口。

- 相同：不能实例化，都可以包含抽象方法的
- 不同：
   - 定义。内部结构等
   - 类 ：单继承 ； 接口：多继承
   -  类与接口：多实现

### 【面试题】排错

#### 1

```
interface A {
	int x = 0;
}

class B {
	int x = 1;
}

class C extends B implements A {

	public void px() {
//		System.out.println(x);
//		The field x is ambiguous
		System.out.println(super.x); // 类B的x
//		没有super.super的写法；如果需要调用父类的父类的x
//		则需要在父类中有调用的方法； 
//		尽量避免变量重名
		System.out.println(A.x); // 接口A的x
	}

	public static void main(String[] args) {
		new C().px();
	}
}
```

#### 2

```
interface Playable {
	void play();
}

interface Bounceable {
	void play();
}

interface Rollable extends Playable, Bounceable {
	Ball ball = new Ball("PingPang");//默认 public static final
}

class Ball implements Rollable {
	private String name;

	public String getName() {
		return name;
	}

	public Ball(String name) {
		this.name = name;
	}

	public void play() {
//		即认为是接口Playable的重写也认为是接口Bounceable的重写，是可以的

//		ball = new Ball("Football");
//		在接口Rollable中定义了ball是public static final修饰的
		System.out.println(ball.getName());
	}
}
```

### 练习4

定义一个接口用来实现两个对象的比较。

```
interface CompareObject{
    public int compareTo(Object o); 
    //若返回值是 0 , 代表相等; 若为正数，代表当前对象大；负数代表当前对象小
}
```

定义一个Circle类，声明redius属性，提供getter和setter方法

定义一个ComparableCircle类，继承Circle类并且实现CompareObject接口。

在ComparableCircle类中给出接口中方法compareTo的实现体，用来比较两个圆的半径大小。

定义一个测试类InterfaceTest，创建两个ComparableCircle对象，调用compareTo方法比较两个类的半径大小。

思 考 ： 参 照 上 述 做 法 定 义 矩 形 类 Rectangle 和 ComparableRectangle 类 ， 
  
在ComparableRectangle类中给出compareTo方法的实现，比较两个矩形的面积大小。

```
package com.zjk;

public class Test {
	public static void main(String[] args) {
		ComparableCircle c1 = new ComparableCircle(1.2);
		ComparableCircle c2 = new ComparableCircle(1.3);

		System.out.println(c1.compareTo(c2));

		ComparableRectangle r1 = new ComparableRectangle(1, 2);
		ComparableRectangle r2 = new ComparableRectangle(2, 1);

		System.out.println(r1.compareTo(r2));
	}
}

interface CompareObject {
	// 若返回值是 0 , 代表相等; 若为正数，代表当前对象大；负数代表当前对象小
	public int compareTo(Object o);
}

class Circle {
	private Double radius;

	public Circle(Double radius) {
		super();
		this.radius = radius;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}
}

class ComparableCircle extends Circle implements CompareObject {

	public ComparableCircle(Double radius) {
		super(radius);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if (this == o) {
			return 0;
		}
		if (o instanceof Circle) {
			ComparableCircle c = (ComparableCircle) o;
////			return (int) (this.getRadius() - c.getRadius()); 强转数据损失的问题
//          方式1
//			if (this.getRadius() > c.getRadius()) {
//				return 1;
//			} else if (this.getRadius() < c.getRadius()) {
//				return -1;
//			} else {
//				return 0;
//			}
			
//			方式二
			return this.getRadius().compareTo(c.getRadius());
		} else {
			throw new RuntimeException("输入的类型不同");
		}
	}

}

class Rectangle {
	private double wide;
	private double length;

	public Rectangle(double wide, double length) {
		super();
		this.wide = wide;
		this.length = length;
	}

	public double getWide() {
		return wide;
	}

	public double getLength() {
		return length;
	}

	public void setWide(double wide) {
		this.wide = wide;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double findArea() {
		return wide * length;
	}

}

class ComparableRectangle extends Rectangle implements CompareObject {

	public ComparableRectangle(double wide, double length) {
		super(wide, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if (this == o) {
			return 0;
		}
		if (o instanceof Rectangle) {
			Rectangle r = (Rectangle) o;
			if (this.findArea() > r.findArea()) {
				return 1;
			} else if (this.findArea() < r.findArea()) {
				return -1;
			} else {
				return 0;
			}
		} else {
			throw new RuntimeException("输入的数据类型不一致");
		}
	}

}
```

### Java 8中关于接口的改进

- Java 8中，除了定义全局常量和抽象方法之外，还可以为接口添加静态方法和默认方法。
   - 从技术角度来说，这是完全合法的，只是它看起来违反了接口作为一个抽象定义的理念。
- 静态方法：使用 static 关键字修饰。
     - 只能通过接口直接调用静态方法，并执行其方法体。
        - 我们经常在相互一起使用的类中使用静态方法。你可以在标准库中找到像Collection/Collections或者Path/Paths这样成对的接口和类。
- 默认方法：默认方法使用 default 关键字修饰。
     - 只能通过实现类对象来调用。
     - 如果实现类重写了接口中的默认方法，调用时，仍然调用重写后的方法
        - 我们在已有的接口中提供新方法的同时，还保持了与旧版本代码的兼容性。
        - 比如：java 8 API中对Collection、List、Comparator等接口提供了丰富的默认方法。

#### 接口中的默认方法

- 若一个接口中定义了一个默认方法，而另外一个接口中也定义了一个同名同参数的方法（不管此方法是否是默认方法），在实现类同时实现了这两个接口时，会出现：接口冲突。
    - 解决办法：实现类必须覆盖(重写)接口中同名同参数的方法，来解决冲突。
- 若一个接口中定义了一个默认方法，而父类中也定义了一个同名同参数的非抽象方法，则不会出现冲突问题。
    - 那么子类在没有重写此方法的情况下，默认调用父类同名同参的方法（类优先原则）
    - 因为此时遵守：类优先原则。接口中具有相同名称和参数的默认方法会被忽略。
- 如何在子类（实现类）的方法中调用父类，接口中被重写的方法
   - 父类：`super.方法();`
   - 接口：`接口.super.方法();`

#### 例

```java
package com.zjk;

public interface CompareA {

//	静态方法
	public static void method1() {
		System.out.println("CompareA:北京");
	}

//	默认方法
	public default void method2() {
		System.out.println("ComparA：上海");
	}

	default void method3() {
		System.out.println("ComparA：上海");
	}
}


package com.zjk;

public class SubClassTest {

	public static void main(String[] args) {
		SubClass s = new SubClass();
		
//		s.method1();
//		SubClass.method1();
//		接口中定义的静态方法，只能通过接口来调用
		CompareA.method1();
		
//		CompareA.method2();
//		SubClass.method2();
//		接口中定义的默认方法，只能通过实现类对象来调用。
//		如果实现类重写了接口中的默认方法，调用时，仍然调用重写后的方法
		s.method2();
		s.method3();
		//父类和接口的method3()重名
//		如果子类（实现类）继承的父类和实现的接口中声明了同名同参的方法。
//		那么子类在没有重写此方法的情况下，默认调用父类同名同参的方法
	}
}

class SubClass extends SuperClass implements CompareA,CompareB{
//class SubClass implements CompareA,CompareB{
//  如果实现类实现了多个接口，而多个接口中定义了同名同参的默认方法
//	那么在实现类没有重写的情况下，报错——>接口冲突
//	此时需要必须在实现类中重写此方法
	
	//重写而不是实现(abstract)
	public void method2() {
		System.out.println("SubClass: 上海"); 
	}
	
	public void method3() {
		System.out.println("SubClass: 深圳");
	}
	
	public void myMethod() {
		method3();//调用自己定义的重写方法
		super.method3();//调用父类中声明的
		CompareA.super.method3();//调用接口中的默认方法
		CompareB.super.method3();//调用接口中的默认方法
		
	}
}

class SuperClass{
	public void method3() {
		System.out.println("SuperClass:上海");
	}
}

interface CompareB{
	default void method3() {
		System.out.println("ComparB：上海");
	}
}
```

#### 接口新特性的应用

```java
package com.zjk;

interface Filial {
	default void help() {
		System.out.println("-Filial");
	}
}

interface Spoony {
	default void help() {
		System.out.println("-Spoony");
	}
}

class Father{
	public void help() {
		System.out.println("Father");
	}
}

public class Man extends Father implements Filial, Spoony {
	@Override
	public void help() {
		// TODO Auto-generated method stub
		System.out.println("who?");
		Filial.super.help();
		Spoony.super.help();
	}
}
```

## 类的内部成员之五：内部类

当一个事物的内部，还有一个部分需要一个完整的结构进行描述，而这个内部的完整的结构又只为外部事物提供服务，那么整个内部的完整结构最好使用内部类。

- 在Java中，允许一个类的定义位于另一个类的内部，前者称为内部类，后者称为外部类。
   - (允许一个类A声明在另一个类B中，则类A是内部类，类B是外部类)
- 内部 class一般用在定义它的类或语句块之内，在外部引用它时必须给出完整的名称。
- 内部 class的名字不能与包含它的外部类类名相同；
- 分类： 
   - 成员内部类（static成员内部类和非static成员内部类）
   - 局部内部类[方法内，代码块内，构造器内]（不谈修饰符）、匿名内部类

- **成员内部类和局部内部类，在编译以后，都会生成字节码文件**
- 格式：
   - 成员内部类：外部类$内部类名.class
   - 局部内部类：外部类$数字 内部类名.class 

### 成员内部类

**成员内部类作为外部类的成员：**

- 和外部类不同，**成员内部类还可以声明为private或protected；（包括public和缺省）**
- 可以调用外部类的结构
- 可以被static修饰，但此时就不能再使用外层类的非static的成员变量；

**成员内部类作为一个类：**

- 可以在内部定义属性、方法、构造器等结构
- 可以被abstract修饰，
- 可以被final修饰,表示此类不能被继承；
    - 没有被final修饰则可以被继承 
- 编译以后生成**OuterClass$InnerClass.class字节码文件**（也适用于局部内部类）

**关注**

1. 如何实例化成员内部类的对象
2. 如何在成员内部类中区分调用外部类的结构
3. 开发中局部内部类的使用

**【注意】**

1. 非static的成员内部类中的成员不能声明为static的，只有在外部类或static的成员内部类中才可声明static成员。
2. 外部类访问成员内部类的成员，需要“内部类.成员”或“内部类对象.成员”的方式
3. 成员内部类可以直接使用外部类的所有成员，包括私有的数据
4. 当想要在外部类的静态成员部分使用内部类时，可以考虑内部类声明为静态的


#### 例

```java
package com.zjk;

public class InnerClassTest {
	public static void main(String[] args) {

//		创建Dog实例（静态的成员内部类）
		Person.Dog dog = new Person.Dog();
		dog.show();
//		创建Bird实例（非静态的成员内部类）
		Person p = new Person();
		Person.Bird bird = p.new Bird();
		bird.sing();
		bird.display("夜莺");
	}
}

class Person {
	String name = "小米";
	int age;

	public void eat() {
		System.out.println("eat-Person");
	}

//	成员内部类(静态)
//	abstract static class Dog {
	static class Dog {
		String name;
		int age;

		public void show() {
			System.out.println("Dog");
//			eat(); 
//			静态成员内部类不能调用外部类中非静态的结构
		}
	}

//	成员内部类(非静态)
	class Bird {
		String name = "鸟";

		public Bird() {

		}

		public void sing() {
			System.out.println("Bird-sing");
			eat();
//			即Person.this.eat();
//			调用外部类的非静态结构
		}

		public void display(String name) {
			System.out.println(name); // 方法的形参
			System.out.println(this.name); // 内部类的属性
			System.out.println(Person.this.name); // 外部类的属性
		}
	}

	public void method() {
//	局部内部类
		class AA {

		}
	}

	{
//	局部内部类
		class BB {

		}
	}

	public Person() {
//	局部内部类
		class CC {

		}
	}
}
```

### 局部内部类(开发中很少见)

- 声明局部内部类

```
class 外部类{
    方法(){
        class 局部内部类{}
    }
    {
    class 局部内部类{}
    }
}
```

- 使用局部内部类
   - 只能在声明它的方法或代码块中使用，而且是先声明后使用。除此之外的任何地方都不能使用该类
   - 但是它的对象可以通过外部方法的返回值返回使用，返回值类型只能是局部内部类的父类或父接口类型
   
**局部内部类的特点**

- 内部类仍然是一个独立的类，在编译之后内部类会被编译成独立的.class文件，但是前面冠以外部类的类名和$符号，以及数字编号。
- **只能在声明它的方法或代码块中使用，而且是先声明后使用**。除此之外的任何地方都不能使用该类。
- 局部内部类可以使用外部类的成员，包括私有的。
- **局部内部类可以使用外部方法的局部变量，但是必须是final的**。
    - 由局部内部类和局部变量的声明周期不同所致。
    - JDK7之前，要求此变量显式声明为final
    - JDK8之后，可以省略final的声明(自动补上)
- 局部内部类和局部变量地位类似，不能使用public,protected,缺省,private
- 局部内部类不能使用static修饰，因此也不能包含静态成员

#### 例

```java
package com.zjk;

public class InnerClassTest1 {

	public void method() {
//		局部内部类
		class AA {

		}
	}

//	返回一个实现了Comparable接口的类的对象
	public Comparable getComparable() {

//		创建一个实现了Comparable接口的类
////		方式1
//		class MyComparable implements Comparable {
//
//			@Override
//			public int compareTo(Object o) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//		}
//		
//		return new MyComparable();
		
//		方式2 
		return new Comparable() {

			@Override
			public int compareTo(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
			
		};
	}
}
```
```
package com.zjk;

public class InnerClassTest {

	public void method() {

//		局部变量
		final int num = 10;
//		局部内部类可以使用外部方法的局部变量，但是必须是final的。
		class AA {
			public void show() {
//				num =20;
				System.out.println(num);
			}
		}
	}
}
```

### 匿名内部类

- 匿名内部类不能定义任何静态成员、方法和类，只能创建匿名内部类的一个实例。
- 一个匿名内部类一定是在new的后面，用其隐含实现一个接口或实现一个类。
- 格式：

```java
new 父类构造器（实参列表）|实现接口(){
    //匿名内部类的类体部分
}
```
**匿名内部类的特点**

- 匿名内部类必须继承父类或实现接口
- 匿名内部类只能有一个对象
- 匿名内部类对象只能使用多态形式引用

## 包

**Java中包是相关类与接口的一个集合，它提供了类的命名空间的管理和访问保护。**

- 为了避免命名冲突和限定类的访问权限，将一组相关类与接口“包裹”在一起形成包(package)，体现了OOP的封装思想，为Java中管理大量的类和接口提供了方便。
- 另外，由于Java编译器要为每个类生成一个字节码文件，且文件名与类名相同，因此有可能由于同名类的存在而导致命名冲突。包的引入为Java提供了包为单位的独立命名空间，位于不同包中的类即使同名也不会冲突，从而有效地解决了命名冲突的问题。
- 同时，包具有特定的访问控制权限，同一个包中的类之间拥有特定的访问权限（protected）。


**Java的JDK提供的包主要有**

- java.applet
- java.awt 
   - java.awt.datatransfer 
   - java.awt.event,
   - java.awt.image,
- java.beans,
- java.io、
- java.lang、
- java.lang.reflect,
- java.math 
- java.net、
- java.rmi,
- java.security、
- java.sql、
- java.util
- 等。


Java平台中的类与接口都是根据功能以包组织的。每个包中都定义了许多功能相关的类和接口。我们也可以定义自己的包来实现自己的应用程序。

- Java编译器把包对应于文件系统的目录和文件管理，还可以使用ZIP或JAR压缩文件的形式保存。
    - 例如，以Windows平台为例，名为java.applet的包中，所有类文件都存储在目录classPath\java\applet下。其中包根目录一classPath由环境变量CLASSPATH来设定。

**包机制的好处主要体现在如下几点。**

- 程序员容易确定包中的类是相关的，并且容易根据所需的功能找到相应的类。
- 每个包都创建一个新的命名空间，因此不同包中的类名不会冲突。
- 同一个包中的类之间有比较宽松的访问控制。

### 定义与使用包。

#### 包的定义

使用package语句指定一个源文件中的类属于一个特定的包。package语句的格式如下：

```java
package pkg1[.pkg2[.pkg3...]];
```

```java
package graphics;
//Circle类成为graphics包中的一个public成员，并存放在classPath\graphics目录中。
public class Circle extends Graphic implements Draggable{
    ...
}
```

**说明：**

- package语句在每个Java源程序中只能有一条，一个类只能属于一个包。
- package语句必须在程序的第一行，该行前可以有空格或注释行
- 包名以”.“为分隔符
- 如果源文件中没有package语句，则指定为无名包。无名包没有路径，一般情况下，会把源文件中的类存储在当前目录（即存放Java源文件的目录）下。

#### 包成员的使用

包中的成员是指包中的类和接口。只有public类型的成员才能被包外的类访问，

**从包外访问public类型的成员**

- 写引入包成员或整个包，然后使用短名(short name,类名或接口名)引用包成员。
- 使用长名(long name,由包名与类/接口名组成)引用包成员。

##### import语句 引入包成员

可以先引人包中的指定类或整个包，再使用该类。这时可以直接使用类名或接口名在Java中引人包（如JDK中的包或用户自定义的包）中的类是通过import语句实现的，

**import语句的格式如下**：

```
import pkgl[.pkg2[.pkg3.].(classname|*)；
```

其中pkgl[.pkg2[.pkg3…]表明包的层次，与package语句相同，它对应于文件目录，classname则指明所要引入的类。
- 如果要从一个包中引人所有类，则可以用通配符(`*`)来代替。
- 注意：import语句必须在源程序所有类声明之前，在package语句之后。
- java包程序的一般结构如下：

```
[package语句] //默认是package.;(属于当前目录)
[import语句] //默认是import java.lang.*;
[类声明]
```

##### 包名.类名 使用长名引用包成员

要在程序中使用其他包中的类，而该包并没有引人，则必须使用长名引用该类。
- 一般只有当两个包中含有同名类时，加以区分使用。

**格式是：**

```
包名.类名
```


## 面向对象内容总结

### 生命周期

# 异常

## 异常概述

Java 中的异常又称为例外，是一个在程序执行期间发生的事件，它中断正在执行程序的正常指令流。为了能够及时有效地处理程序中的运行错误，必须使用异常类，这可以让程序具有极好的容错性且更加健壮。 

**在 Java 中一个异常的产生，主要有如下三种原因：**
 
1. Java 内部错误发生异常，Java 虚拟机产生的异常。
2. 编写的程序代码中的错误所产生的异常，例如空指针异常、数组越界异常等。
3. 通过 throw 语句手动生成的异常，一般用来告知该方法的调用者一些必要信息。

Java 通过面向对象的方法来处理异常。在一个方法的运行过程中，如果发生了异常，则这个方法会**产生代表该异常的一个对象**，并把它交给运行时的系统，运行时系统寻找相应的代码来处理这一异常。

我们把生成异常对象，并把它提交给运行时系统的过程称为拋出（throw）异常。运行时系统在方法的调用栈中查找，直到找到能够处理该类型异常的对象，这一个过程称为捕获（catch）异常。


**Java程序在执行过程中所发生的异常事件可分为两类：**

- Error：定义了在通常环境下不希望被程序捕获的异常。Java虚拟机无法解决的严重问题。
   - Error 错误是任何处理技术都无法恢复的情况，肯定会导致程序非正常终止。并且 Error 错误属于未检查类型，大多数发生在运行时。
    - 如：JVM系统内部错误、资源耗尽等严重情况。比如：StackOverflowError和OOM。
    - 一般不编写针对性的代码进行处理。
       
```java
package com.zjk;

public class ErrorTest {
	public static void main(String[] args) {
//      1.栈溢出: java.lang.StackOverflowError
//		main(args);
		
//		2.堆溢出: java.lang.OutOfMemoryError
//		Integer[] arr = new Integer[1024*1024*1024];
		
		
	}
}
```

- Exception: 其它因编程错误或偶然的外在因素导致的一般性问题，可以使用针对性的代码进行处理。
   - 例如：
   - 空指针访问
   - 试图读取不存在的文件
   - 网络连接中断
   - 数组角标越界
- Exception 又分为可检查（checked）异常和不检查（unchecked）异常，
   - 可检查异常在源码里必须显示的进行捕获处理，这里是编译期检查的一部分
   - 不检查异常就是所谓的运行时异常，通常是可以编码避免的逻辑错误，具体根据需要来判断是否需要捕获，并不会在编译器强制要求。
- 对于这些错误，一般有两种解决方法：
   - 一是遇到错误就终止程序的运行。
   - 另一种方法是由程序员在编写程序时，就考虑到错误的检测、错误消息的提示，以及错误的处理。

- 捕获错误最理想的是在编译期间，但有的错误只有在运行时才会发生。
   - 比如：除数为0，数组下标越界等
   - 分类：编译时异常和运行时异常
 
**Error（错误）和 Exception（异常）都是 java.lang.Throwable 类的子类，在 Java 代码中只有继承了 Throwable 类的实例才能被 throw 或者catch。**

## 异常体系结构

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/291550121221050.png =690x)


- 在 Java 中所有异常类型都是内置类 java.lang.Throwable 类的子类，即 Throwable 位于异常类层次结构的顶层。
    - Throwable 类下有两个异常分支 Exception 和 Error，

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/139441011247510.png =285x)


![](C:/Users/zjk10/OneDrive/NoteBook/pictures/270542822227343.png =525x)

- 运行时异常 (不检查异常（Unchecked Exception）)。
   -  是指编译器不要求强制处置的异常。一般是指编程时的逻辑错误，是程序员应该积极避免其出现的异常。
   - java.lang.RuntimeException类及它的子类都是运行时异常。
   - 对于这类异常，可以不作处理，因为这类异常很普遍，若全处理可能会对程序的可读性和运行效率产生影响。
- 编译时异常 (检查异常（Checked Exception）)
   - 是指编译器要求必须处置的异常。即程序在运行时由于外界因素造成的一般性异常。编译器要求Java程序必须捕获或声明所有编译时异常。
   - 对于这类异常，如果程序不处理，可能会带来意想不到的结果。

## 常见异常

- 运行时异常
  - java.lang.RuntimeException
 
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/218961211240179.png =483x)

- 编译时异常
  - java.io.IOExeption
  - java.lang.ClassNotFoundException
  - java.lang.InterruptedException
  - java.io.FileNotFoundException
  - java.sql.SQLException

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/222711311236734.png =324x)

1. 运行时异常（RuntimeException）：
- NullPropagation：空指针异常；
- ClassCastException：类型强制转换异常
- IllegalArgumentException：传递非法参数异常
- IndexOutOfBoundsException：下标越界异常
- NumberFormatException：数字格式异常

2. 非运行时异常：
- ClassNotFoundException：找不到指定 class 的异常
- IOException：IO 操作异常

3. 错误（Error）：
- NoClassDefFoundError：找不到 class 定义异常
- StackOverflowError：深递归导致栈被耗尽而抛出的异常
- OutOfMemoryError：内存溢出异常

```java
package com.zjk;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import org.junit.Test;

public class ExceptionTest {

//	**运行时异常 java.lang.RuntimeException**********
//	空指针异常：NullPointerException
	@Test
	public void test1() {
//		int[] arr = null;
//		System.out.println(arr[3]);

//		String str = "abc";
//		str = null;
//		System.out.println(str.charAt(0));
	}

//	角标越界
	@Test
	public void test2() {
//	数组角标越界：ArrayIndexOutOfBoundsException
//		int[] arr = new int[10];
//		System.out.println(arr[10]);

//  字符串角标越界：StringIndexOutOfBoundsException
//		String str = "abc";
//		System.out.println(str[3]);
	}

//	类型转换异常：ClassCastException
	@Test
	public void test3() {
//		Object obj = new Date();
//		String str = (String)obj;
	}

//	NumberFormatException
	@Test
	public void test4() {
//		String str = "abc";
//		int num = Integer.parseInt(str);
	}

//	InputMismatchException
	@Test
	public void test5() {
//		Scanner scan = new Scanner(System.in);
//		int score = scan.nextInt(); //输入的不是int数据类型
//		
//		scan.close();
	}

//	ArithmeticException
	@Test
	public void test6() {
//		int a = 10;
//		int b = 0;
//		System.out.println(a / b);
	}
	
//	**编译时异常*****************************
	@Test
	public void test7() {
//		File file = new File("hello.txt");
//		FileInputStream fis = new FileInputStream(file);
//		
//		int data = fis.read();
//		while(data != -1) {
//			System.out.println((char)data);
//			data = fis.read();
//		}
//		
//		fis.close();
	}
}
```

## 异常处理机制

- Java采用的异常处理机制，是将异常处理的程序代码集中在一起，与正常的程序代码分开，使得程序简洁、优雅，并易于维护。

**Java提供的是异常处理的抓抛模型。**

- 过程一：抛出
   - 程序在正常执行的过程中，一旦出现异常，就会在异常代码处**生成一个对应异常类的对象**，并将此对象抛出。
   - **一旦抛出对象之后，其他的代码就不执行**。
- 过程二：捕获 （异常的处理方式）
   1. try-catch-finally
   2. throws

**异常对象的生成**

由虚拟机自动生成：程序运行过程中，虚拟机检测到程序发生了问题，如果在当前代码中没有找到相应的处理程序，就会在后台自动创建一个对应常类的实例对象并抛出——自动抛出

由开发人员手动创建：Exception exception = new ClassCastException();——创建好的异常对象不抛出对程序没有任何影响，和创建一个普通对象一样

如果一个方法内抛出异常，该异常对象会被抛给调用者方法中处理。如果异常没有在调用者方法中处理，它继续被抛给这个调用方法的上层方法。这个过程将一直继续下去，直到异常被处理。这一过程称为捕获(catch)异常。

如果一个异常回到main()方法，并且main()也不处理，则程序运行终止。

程序员通常只能处理Exception，而**对Error无能为力**。

### 异常处理机制一：try-catch-finally  


- 语法:

```java
try{
    //可能出现异常的代码
}catch(异常类型1 变量名1){
    //处理异常的方式1
}catch(异常类型2 变量名2){
    //处理异常的方式2
}
...
finally{
    //一定会执行的代码
}
```

- catch 块和 finally 块都是可选的，但 catch 块和 finally 块至少出现其中之一，也可以同时出现；
   - 多个 catch 块必须位于 try 块之后，finally 块必须位于所有的 catch 块之后。 
- 使用try将可能出现异常的代码块包装起来，一旦出现异常，就会生成一个对应异常类的对象，根据此对象的类型，去catch中进行匹配
- 一旦try中的异常对象匹配到某一个catch时，就进入catch中进行异常处理。一旦处理完成，就跳出当前的try-catch结构（在没有finally的情况下）。进行执行其后的代码。**如果 try 代码块中拋出的异常没有被任何 catch 子句捕捉到，那么将直接执行 finally 代码块中的语句，并把该异常传递给该方法的调用者。finally不管有没有捕获异常都会执行**。
- catch中的异常类型如果满足子父类关系，则要求子类一定声明在父类的上面，否则报错
   - catch中的异常类型如果没有子父类关系，则谁声明在上无所谓

- 在try结构中声明定义的对象，在出了try结构以后，不能被调用
   - 可以在try结构外面先对改变量进行声明初始化 
- try-catch-finally结构可以相互嵌套

**总结**

- 体会1： 使用try-catch-finally处理编译时异常，使得程序在编译时就不再报错，但是运行时仍可能报错，相当于我们使用try-catch-finally将第一个编译时可能出现的异常，延迟到运行时出现(相当于把一个编译时异常变为运行时异常)
- 体会2：开发中，由于运行时异常比较常见，所以我们不针对运行时异常编写try-catch-finally。
   - 针对编译时异常，我们一定要考虑异常的处理。

**常用的异常对象处理方式：**
 
1. `getMessage()`获取异常信息，返回字符串
2. `printStackTrace()` 获取异常类名和异常信息，以及异常出现在程序中的位置。返回值void。

```java
package com.zjk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class ExceptionTest1 {
	@Test
	public void test1() {

		String str = "abc";

		int num = 0;
		try {
			num = Integer.parseInt(str);
//			int num = Integer.parseInt(str);

//			抛出异常,一旦处理完成，就跳出当前的try-catch结构
//			没有执行下面的语句
			System.out.println("1");
		} catch (NumberFormatException e) {
			System.out.println("出现数值转换异常");
//			String getMessage()
			System.out.println(e.getMessage());
//			printStackTrace
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("出现空指针异常");
		} catch (Exception e) {
			System.out.println("出现异常");
		}
//	    } catch (Exception e) {
//			System.out.println("出现异常");
//		//Unreachable catch block
//		catch中的异常类型如果满足子父类关系，则要求子类一定声明在父类的上面，否则报错
//		} catch (NumberFormatException e) {
//			System.out.println("出现数值转换异常");
//		}
//		执行
		System.out.println("2");

//		System.out.println(num);
	}

	@Test
	public void test2() {
		try {
			File file = new File("hello.txt");
			FileInputStream fis = new FileInputStream(file);

			int data = fis.read();
			while (data != -1) {
				System.out.println((char) data);
				data = fis.read();
			}

			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```

#### finally的使用

finally语句为异常处理提供一个统一的出口，使得在控制流转到程序的其它部分以前，能够对程序的状态作统一的管理。

- finally是可选的
- **finally中声明的是一定会被执行的代码**。
   - 即使catch中又出现异常了，try或catch中有return语句等情况。
   - 除非在 try 块、catch 块中调用了退出虚拟机的方法`System.exit(int status)`，否则不管在 try 块或者 catch 块中执行怎样的代码，出现怎样的情况，异常处理的 finally 块总会执行。
- 像数据库连接，输入输出流（IO），网络编程Socket等资源，JVM是不能自动回收的，我们需要自己手动的进行资源的释放。此时的**资源释放**，就需要声明在finally中。(注：Java 9增强的自动资源管理)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/176842210221051.png =421x)

```java
package com.zjk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class ExceptionTest1 {
	@Test
	public void test1() {

		String str = "abc";

		int num = 0;
		try {
			num = Integer.parseInt(str);
//			int num = Integer.parseInt(str);

//			抛出异常,一旦处理完成，就跳出当前的try-catch结构
//			没有执行下面的语句
			System.out.println("1");
		} catch (NumberFormatException e) {
			System.out.println("出现数值转换异常");
//			String getMessage()
			System.out.println(e.getMessage());
//			printStackTrace
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("出现空指针异常");
		} catch (Exception e) {
			System.out.println("出现异常");
		}
//	    } catch (Exception e) {
//			System.out.println("出现异常");
//		//Unreachable catch block
//		catch中的异常类型如果满足子父类关系，则要求子类一定声明在父类的上面，否则报错
//		} catch (NumberFormatException e) {
//			System.out.println("出现数值转换异常");
//		}
//		执行
		System.out.println("2");

//		System.out.println(num);
	}

	@Test
	public void test2() {
		try {
			File file = new File("hello.txt");
			FileInputStream fis = new FileInputStream(file);

			int data = fis.read();
			while (data != -1) {
				System.out.println((char) data);
				data = fis.read();
			}

			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```

##### 增强的自动资源管理

###### Java7
**当 try 代码块结束时，自动释放资源。不再需要显式的调用 close() 方法，该形式也称为“带资源的 try 语句”。**

- 当程序使用 finally 块关闭资源时，程序会显得异常臃肿
   - 为了解决这种问题，Java 7 增加了一个新特性，该特性提供了另外一种管理资源的方式，这种方式能自动关闭文件，被称为自动资源管理（Automatic Resource Management）。该特性是在 try 语句上的扩展，主要释放不再需要的文件或其他资源。

- 自动资源管理替代了 finally 代码块，并优化了代码结构和提高程序可读性。
- 语法

```java
try (声明或初始化资源语句) {
    // 可能会生成异常语句
} catch(Throwable e1){
    // 处理异常e1
} catch(Throwable e2){
    // 处理异常e1
} catch(Throwable eN){
    // 处理异常eN
}
```

**注意：**

- try 语句中声明的资源被隐式声明为 final，资源的作用局限于带资源的 try 语句。
- 可以在一条 try 语句中声明或初始化多个资源，每个资源以;隔开即可。
- 需要关闭的资源必须实现了 AutoCloseable 或 Closeable 接口。
   - Closeable 是 AutoCloseable 的子接口，Closeable 接口里的 close() 方法声明抛出了 IOException，因此它的实现类在实现 close() 方法时只能声明抛出 IOException 或其子类；AutoCloseable 接口里的 close() 方法声明抛出了 Exception，因此它的实现类在实现 close() 方法时可以声明抛出任何异常。
   

Java 7 几乎把所有的“资源类”（包括文件 IO 的各种类、JDBC 编程的 Connection 和 Statement 等接口）进行了改写，改写后的资源类都实现了 AutoCloseable 或 Closeable 接口。

```java
public class AutoCloseTest {
    public static void main(String[] args) throws IOException {
        try (
                // 声明、初始化两个可关闭的资源
                // try语句会自动关闭这两个资源
                BufferedReader br = new BufferedReader(new FileReader("AutoCloseTest.java"));
                PrintStream ps = new PrintStream(new FileOutputStream("a.txt"))) {
            // 使用两个资源
            System.out.println(br.readLine());
            ps.println("C语言中文网");
        }
    }
}
```

###### Java9

Java 9 再次增强了这种 try 语句。Java 9 不要求在 try 后的圆括号内声明并创建资源，只需要自动关闭的资源有 final 修饰或者是有效的 final (effectively final)，Java 9 允许将资源变量放在 try 后的圆括号内。上面程序在 Java 9 中可改写为如下形式。

```java
public class AutoCloseTest {
    public static void main(String[] args) throws IOException {
        // 有final修饰的资源
        final BufferedReader br = new BufferedReader(new FileReader("AutoCloseTest.java"));
        // 没有显式使用final修饰，但只要不对该变量重新赋值，该变量就是有效的
        final PrintStream ps = new PrintStream(new FileOutputStream("a. txt"));
        // 只要将两个资源放在try后的圆括号内即可
        try (br; ps) {
            // 使用两个资源
            System.out.println(br.readLine());
            ps.println("C语言中文网");
        }
    }
}
```


#### 练习1

```java
编写一个类ExceptionTest，在main方法中使用try、catch、finally，要求：
在try块中，编写被零除的代码。
在catch块中，捕获被零除所产生的异常，并且打印异常信息
在finally块中，打印一条语句。
```

```java
public class ExceptionTest01 {

	public static void main(String[] args) {
		int a = 1;
		int b = 0;
		try {
			System.out.println(a / b);
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}finally {
			System.out.println("finally");
		}
	}
}
```

#### 练习2 捕获和处理IOException异常

编译、运行应用程序IOExp.java，体会Java语言中异常的捕获和处理机制。

相关知识：FileInputStream类的成员方法read()的功能是每次从相应的(本地为ASCII码编码格式)文件中读取一个字节，并转换成0~255之间的int型整数返回，到达文件末尾时则返回-1

```java

```

### 异常处理机制二：throws + 异常类型

- `throws + 异常类型`(声明抛出异常)写在方法的声明处，指明此方法执行时，可能会抛出的异常类型
   - 一旦当方法体执行时，出现异常仍然会在异常代码处生成一个异常类的对象，此对象满足throws后异常类型时，就会被抛出。异常代码后续的代码就不再执行。
   - 使用 throws 声明抛出异常的思路是，当前方法不知道如何处理这种类型的异常，该异常应该**由向上一级的调用者处理**；如果 main 方法也不知道如何处理这种类型的异常，也可以使用 throws 声明抛出异常，该异常将交给 JVM 处理。JVM 对异常的处理方法是，打印异常的跟踪栈信息，并中止程序运行，这就是前面程序在遇到异常后自动结束的原因。
-  体会：
   - try-catch-finally：真正地将异常给处理了
   - throws地方式只是将异常抛给了方法的调用者，并没有真正将异常处理掉 。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/162353610239477.png =436x)

#### 例

```java
package com.zjk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionTest2 {

	public static void main(String[] args) {
		try {
			method2();
//		method2()的异常抛给main()方法
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		method3();
//		在method3()中解决异常，不再往上抛

	}

	public static void method3() {
		try {
			method2();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void method2() throws IOException {
		method1();
//		method1()的异常抛给method2()
	}

	public static void method1() throws FileNotFoundException, IOException {
		File file = new File("hello1.txt");
		FileInputStream fis = new FileInputStream(file);

		int data = fis.read();
		while (data != -1) {
			System.out.println((char) data);
			data = fis.read();
		}

		fis.close();
		
		System.out.println("method1");//不执行
//		满足throws后异常类型时，就会被抛出。异常代码后续的代码就不再执行。
	}
}
```

#### 重写方法异常抛出的规则

- 子类重写的方法抛出的异常类型不大于父类被重写的方法抛出的异常类型

```java
package com.zjk;

import java.io.FileNotFoundException;
import java.io.IOException;

public class OverrideTest {
	
	public static void main(String[] args) {
		OverrideTest test = new OverrideTest();
		test.display(new SubClass());
	}

	public void display(SuperClass s) {
		try {
			s.method();
		} catch (IOException e) {
//			只处理了父类抛出的异常，如果子类重写的方法抛出的异常大于父类的异常
//			则子类在调用时出现异常的无法被处理，
			e.printStackTrace();
		}
	}
}

class SuperClass{
	
	public void method() throws IOException{
		
	}
}


class SubClass extends SuperClass{
	@Override
	public void method() throws FileNotFoundException {
//	public void method() throws Exception {
//		子类重写父类的方法，抛出的异常不能大于父类抛出的异常
//		如果父类没有抛出异常，子类也不能抛出
		
	}
}
```

### 开发中选择使用哪一个处理

- 如果父类中被重写的方法没有被throws方式处理异常，则子类重写的方法也不能使用throws，意味着如果子类重写的方法中有异常，必须使用try-catch-finally方式处理。
- 执行的方法中，先后又调用了另外的几个方法，这几个方法是递进关系执行的（A调用B，B调用C...），我们建议这几个方法使用throws的方式进行处理，而执行的方法A可以考虑使用try-catch-finally的方式进行处理。
   - 如果在C方法中使用了try-catch-finally，则C方法中有的代码可能没有执行，传递给B方法的数据可能有误（或没有数据），但B方法仍然执行，但无意义。所以在B,C方法中使用throws，而在A中处理。如果B,C方法中出现异常，则立刻终止执行，将异常抛给A方法解决。 

### 手动抛出异常 throw

- Java异常类对象除在程序执行过程中出现异常时由系统自动生成并抛出，也可根据需要使用人工创建并抛出。
- 首先要生成异常类对象，然后通过throw语句实现抛出操作(提交给Java运行环境)。

- 当 throw 语句执行时，它后面的语句将不执行，此时程序转向调用者程序，寻找与之相匹配的 catch 语句，执行相应的异常处理程序。如果没有找到相匹配的 catch 语句，则再转向上一层的调用程序。这样逐层向上，直到最外层的异常处理程序终止程序并打印出调用栈情况。

- throw 关键字不会单独使用，它的使用完全符合异常的处理机制，但是，一般来讲用户都在避免异常的产生，所以不会手工抛出一个新的异常类的实例，而往往会抛出程序中已经产生的异常类的实例。

```java
IOException e = new IOException();
throw e;
```

- 可以抛出的异常必须是Throwable或其子类的实例.

```java
package com.zjk;

public class StudentTest {

	public static void main(String[] args) {
		Student s = new Student();

		try {
			s.regist(-1);
			System.out.println(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}

class Student {

	private int id;

	public void regist(int id) throws Exception {
		if (id > 0) {
			this.id = id;
		} else {
//			手动抛出异常对象
//			throw new RuntimeException("数据输入非法");
			throw new Exception("输入数据非法");
		}
	}

	@Override
	public String toString() {
		return "Student [id=" + id + "]";
	}

}
```

#### 例 

在某仓库管理系统中，要求管理员的用户名需要由 8 位以上的字母或者数字组成，不能含有其他的字符。当长度在 8 位以下时拋出异常，并显示异常信息；当字符含有非字母或者数字时，同样拋出异常，显示异常信息。代码如下：

```java
import java.util.Scanner;

public class Test05 {
    public boolean validateUserName(String username) {
        boolean con = false;
        if (username.length() > 8) {
            // 判断用户名长度是否大于8位
            for (int i = 0; i < username.length(); i++) {
                char ch = username.charAt(i); // 获取每一位字符
                if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                    con = true;
                } else {
                    con = false;
                    throw new IllegalArgumentException("用户名只能由字母和数字组成！");
                }
            }
        } else {
            throw new IllegalArgumentException("用户名长度必须大于 8 位！");
        }
        return con;
    }

    public static void main(String[] args) {
        Test05 te = new Test05();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = input.next();
        try {
            boolean con = te.validateUserName(username);
            if (con) {
                System.out.println("用户名输入正确！");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}
```

#### throws 关键字和 throw 关键字在使用上的几点区别如下：

**throws**

- throws 处理异常的方式。使用在方法声明处的末尾。
   - 用来声明一个方法可能抛出的所有异常信息，表示出现异常的一种可能性，但并不一定会发生这些异常；
- 通常在一个方法（类）的声明处通过 throws 声明方法（类）可能拋出的异常信息，
- throws 通常不用显示地捕获异常，可由系统自动将所有捕获的异常信息抛给上级方法； <-> try-catch-finally

**throw**

- throw 抛出一个异常对象，并抛出。使用在方法内部<->自动抛出
- throw 则是指拋出的一个具体的异常类型，执行 throw 一定抛出了某种异常对象。 
- 在方法（类）内部通过 throw 声明一个具体的异常信息。
- throw 则需要用户自己捕获相关的异常，而后再对其进行相关包装，最后将包装后的异常信息抛出。

### Java 7新特性：多异常捕获

使用一个 catch 块捕获多种类型的异常时需要注意如下两个地方。

- 捕获多种类型的异常时，多种异常类型之间用竖线|隔开。
- 捕获多种类型的异常时，异常变量有隐式的 final 修饰，因此程序不能对异常变量重新赋值。

```java
public class ExceptionTest {
    public static void main(String[] args) {
        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = a / b;
            System.out.println("您输入的两个数相除的结果是：" + c);
        } catch (IndexOutOfBoundsException | NumberFormatException | ArithmeticException e) {
            System.out.println("程序发生了数组越界、数字格式异常、算术异常之一");
            // 捕获多异常时，异常变量默认有final修饰
            // 所以下面代码有错
            e = new ArithmeticException("test");
        } catch (Exception e) {
            System.out.println("未知异常");
            // 捕获一种类型的异常时，异常变量没有final修饰
            // 所以下面代码完全正确
            e = new RuntimeException("test");
        }
    }
}
```

### 用户自定义异常类

- 一般地，用户自定义异常类都是RuntimeException的子类。
- 自定义异常类通常需要**编写几个重载的构造器**。
- 自定义异常需要提供**serialVersionUID**
- 自定义的异常通过throw抛出。
- 自定义异常最重要的是异常类的名字，当异常出现时，可以根据名字判断异常类型

如果 Java 提供的内置异常类型不能满足程序设计的需求，这时我们可以自己设计 Java 类库或框架，其中包括异常类型。实现**自定义异常类需要继承 Exception 类或其子类，如果自定义运行时异常类需继承 RuntimeException 类或其子类**。

**自定义异常的语法**形式为：

`<class><自定义异常名><extends><Exception>`

- 在编码规范上，一般将自定义异常类的类名命名为**XXXException**，其中 XXX 用来代表该异常的作用。
- 自定义异常类一般包含两个构造方法：
   - 一个是无参的默认构造方法，
   - 另一个构造方法以字符串的形式接收一个定制的异常消息，并将该消息传递给超类的构造方法。
   
```java
class IntegerRangeException extends Exception {
    public IntegerRangeException() {
        super();
    }
    public IntegerRangeException(String s) {
        super(s);
    }
}
```

```java
package com.zjk;

public class MyException extends RuntimeException{
	
	static final long serialVersionUID = 1L; 
//	对该类的唯一标识
	
	public MyException(){
		
	}
	
	public MyException(String msg) {
		super(msg);
	}
}


package com.zjk;

public class StudentTest {

	public static void main(String[] args) {
		Student s = new Student();

		try {
			s.regist(-1);
			System.out.println(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}

class Student {

	private int id;

//	public void regist(int id) throws Exception {
//	非运行时异常需要throws语句声明
    public void regist(int id) {
		if (id > 0) {
			this.id = id;
		} else {
//			手动抛出异常对象
//			throw new RuntimeException("数据输入非法");
			throw new MyException("输入数据非法");
//			throw的只能是Exception或其（间接或直接）子类的异常
		}
	}

	@Override
	public String toString() {
		return "Student [id=" + id + "]";
	}

}
```

####  例

```java
例 1
编写一个程序，对会员注册时的年龄进行验证，即检测是否在 0~100 岁。

1）这里创建了一个异常类 MyException，并提供两个构造方法。MyException 类的实现代码如下：
public class MyException extends Exception {
    public MyException() {
        super();
    }
    public MyException(String str) {
        super(str);
    }
}

2）接着创建测试类，调用自定义异常类。代码实现如下：
import java.util.InputMismatchException;
import java.util.Scanner;
public class Test07 {
    public static void main(String[] args) {
        int age;
        Scanner input = new Scanner(System.in);
        System.out.println("请输入您的年龄：");
        try {
            age = input.nextInt();    // 获取年龄
            if(age < 0) {
                throw new MyException("您输入的年龄为负数！输入有误！");
            } else if(age > 100) {
                throw new MyException("您输入的年龄大于100！输入有误！");
            } else {
                System.out.println("您的年龄为："+age);
            }
        } catch(InputMismatchException e1) {
            System.out.println("输入的年龄不是数字！");
        } catch(MyException e2) {
            System.out.println(e2.getMessage());
        }
    }
}
3）运行该程序，当用户输入的年龄为负数时，则拋出 MyException 自定义异常，执行第二个 catch 语句块中的代码，打印出异常信息。程序的运行结果如下所示。
请输入您的年龄：
-2
您输入的年龄为负数！输入有误！

当用户输入的年龄大于 100 时，也会拋出 MyException 自定义异常，同样会执行第二个 catch 语句块中的代码，打印出异常信息，如下所示。
请输入您的年龄：
110
您输入的年龄大于100！输入有误！

在该程序的主方法中，使用了 if…else if…else 语句结构判断用户输入的年龄是否为负数和大于 100 的数，如果是，则拋出自定义异常 MyException，调用自定义异常类 MyException 中的含有一个 String 类型的构造方法。在 catch 语句块中捕获该异常，并调用 getMessage() 方法输出异常信息。

提示：因为自定义异常继承自 Exception 类，因此自定义异常类中包含父类所有的属性和方法。
```

#### 练习4

```java
编写应用程序EcmDef.java，接收命令行的两个参数，要求不能输入负数，计算两数相除。
对 数 据 类 型 不 一 致 (NumberFormatException) 、 
缺 少 命 令 行 参 数(ArrayIndexOutOfBoundsException、
除0(ArithmeticException)
及输入负数(EcDef 自定义的异常)
进行异常处理。

提示：
(1)在主类(EcmDef)中定义异常方法(ecm)完成两数相除功能。
(2)在main()方法中使用异常处理语句进行异常处理。
(3)在程序中，自定义对应输入负数的异常类(EcDef)。
(4)运行时接受参数 java EcmDef 20 10 //args[0]=“20” args[1]=“10”
(5)Interger类的static方法parseInt(String s)将s转换成对应的int值。
如：int a=Interger.parseInt(“314”); //a=314;
```

```java
package com.zjk1;

public class EcmDef {

	public static void main(String[] args) {

		try {
			int a = Integer.parseInt(args[0]);
			int b = Integer.parseInt(args[1]);

			int result = ecm(a, b);
			System.out.println(result);
		} catch (NumberFormatException e) {
			System.out.println("数据类型不一致");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("缺少命令行参数");
		} catch (ArithmeticException e) {
			System.out.println("除0");
		}catch(EcDef e) {
			System.out.println(e.getMessage());
		}

	}

	public static int ecm(int a, int b) throws EcDef {

		if (a < 0 || b < 0) {
			throw new EcDef("分子或分母为负数");
		}
		return a / b;
	}
}

class EcDef extends Exception {
	public final long serverVesionUID = 1L;

	public EcDef() {
		super();
	}

	public EcDef(String msg) {
		super(msg);
	}
}
```

## assert 断言

- java中提供了专门的assert语句，为java程序提供了一种错误检查机制。
- **每个断言都包含了一个boolean表达式**。
  - 如果程序没有错误，则运行assert语句时，该表达式的值应该为true
  - 如果该表达式的值为false，则系统将抛出一个错误。
- **断言是发现程序错误最快最有效的方法之一**。
   - 相当程序内部处理的文档，增强了程序的可维护性 

### 语法定义

1.

```java
assert boolean表达式;
```

- 当系统运行时，将求出该boolean表达式的值，
    - 如果是false，说明系统处于不正确的状态，将抛出一个没有任何详细信息的AssertionError类型的错误，并且退出
    - 如果是true，进行执行。
    
2.

```java
assert boolean表达式1 : 表达式2;
```

- 表达式2可以是：boolean/char/double/float/int/long基本类型的值或者一个Object类型的对象。
   - 比较常见的是一个描述错误的字符串。
- 当系统运行时，且boolean表达式1的值为false时，
  - 则系统计算出表达式2的值，然后以该boolean表达式2的值为参数调用AssertionError类的构造方法，创建一个包含详细描述信息的**AssetError对象抛出并退出**。
 
### 执行

- 断言语句运行时，默认不执行

**打开断言检查：**

```java
java -ea 类/包
或
java -enableassertions 类/包
```

**关闭断言检查: **

```java
java -da 类/包
或
java -disableassertions 类/包
```

- 如果在`-ea或-da`选项后面无参数，则将对程序中除了系统类之外的所有其他类都打开/关闭断言检查。

### 使用

**1.保证控制流的正确性**

- 在if else语句和switch语句中，可以在不应该执行的控制流下，使用assert false语句，使得如果控制流异常，则报AssertionError异常。

```java
public class AssertDemo {
    public static void main(String[] args) {
        int number = 3;
        switch (number) {
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            default:
                assert false : "该数字为其他数字，不是1 or 2";
                break;
        }
    }
}

****************************************
G:\vim_test>javac AssertionDemo.java

G:\vim_test>java -ea AssertionDemo.java
Exception in thread "main" java.lang.AssertionError: 该数字为其他数字，不是1 or 2
        at AssertionDemo.main(AssertionDemo.java:10)
```

**2.检查私有方法输入参数的有效性**

- 在私有方法调用时，会直接使用传入的参数。如果私有方法对参数有特定要求，可在方法开始处使用断言进行参数检查。

```java
public class AssertDemo {
    public static void main(String[] args) {
        AssertDemo assertDemo = new AssertDemo();
        assertDemo.printX(null);
    }

    public int printX(String num) {
        assert num != null;
        return 1;
    }
}
```

**3.检查方法的返回结果是否有效**

- 对于一些计算型方法，可以通过断言语句在方法返回前，检查返回值是否满足必要的性质。

```java
public class AssertDemo {
    public static void main(String[] args) {
        AssertDemo assertDemo = new AssertDemo();
        assert (assertDemo.printX() >= 0) : "不能小于0";
    }

    public int printX() {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();

        return x;
    }
}
```

**4.检查程序不变量**

- 程序不变量(invariant))是在程序某个特定点或某些特定点都保持为真的一种特性。
- 不变量反映程序的特性，通过分析程序关键点上的不变量，可以监测到程序运行中的异常。

```java
public class AssertDemo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();

        if(x > 0){
            System.out.println("ok");
        }
        else{
            assert  (x <= 0) : "x<=0";
        }
    }
}
```
 
## Java验证用户名和密码

```java
假设在某仓库管理系统的登录界面中需要输入用户名和密码，其中用户名只能由 6~10 位数字组成，密码只能有 6 位，任何不符合用户名或者密码要求的情况都视为异常，并且需要捕获并处理该异常。

下面使用自定义异常类来完成对用户登录信息的验证功能，实现步骤如下。

1）编写自定义异常类 LoginException，该类继承自 Exception。在 LoginException 类中包含两个构造方法，分别为无参的构造方法和含有一个参数的构造方法，代码如下：
public class LoginException extends Exception {
    public LoginException() {
        super();
    }
    public LoginException(String msg) {
        super(msg);
    }
}

2）创建测试类 Test08，在该类中定义 validateLogin() 方法，用于对用户名和密码进行验证。当用户名或者密码不符合要求时，使用自定义异常类 LoginException 输出相应的异常信息。validateLogin() 方法的定义如下：
public boolean validateLogin(String username,String pwd) {
    boolean con = false;    // 用户名和密码是否正确
    boolean conUname = false;    // 用户名格式是否正确
    try {
        if (username.length() >= 6 && username.length() <= 10) {
            for (int i = 0;i < username.length();i++) {
                char ch = username.charAt(i);    // 获取每一个字符
                if (ch >= '0' && ch <= '9') {    // 判断字符是否为0~9的数字
                    conUname = true;    // 设置 conUname 变量值为 true
                } else {    
                    // 如果字符不是0~9的数字，则拋出LoginException异常
                    conUname = false;
                    throw new LoginException("用户名中包含有非数字的字符！");
                }
            }
        } else {    
            // 如果用户名长度不在6~10位之间，拋出异常
            throw new LoginException("用户名长度必须在6〜10位之间！");
        }
        if (conUname) {    // 如果用户名格式正确，判断密码长度
            if (pwd.length() == 6) {    
                // 如果密码长度等于6
                con=true;    // 设置con变量的值为true，表示登录信息符合要求
            } else {    // 如果密码长度不等于6，拋出异常
                con = false;
                throw new LoginException("密码长度必须为 6 位！");
            }
        }
    } catch(LoginException e) {    
        // 捕获 LoginException 异常
        System.out.println(e.getMessage());
    }
    return con;
}
3）在 Test08 类中添加 main() 方法，调用 validateLogin() 方法，如果该方法返回 true，则输出登录成功的信息。main() 方法的定义如下：
public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("用户名：");
    String username = input.next();
    System.out.println("密码：");
    String password = input.next();
    Test08 lt = new Test08 ();
    boolean con = lt.validateLogin(username,password);    // 调用 validateLogin() 方法
    if (con) {
        System.out.println("登录成功！");
    }
}

在本程序的 validateLogin() 方法中使用条件控制语句和  for 循环语句分别对用户名和密码进行了验证。任何不符合用户名或者密码要求的情况都拋出自定义异常 LoginException，并在 catch 语句中捕获该异常，输出异常信息。

运行程序，当用户输入的用户名含有非数字字符时将拋出 LoginException 异常，执行 catch 语句块中的代码打印异常信息，如下所示。
用户名：
xiake8!
密码：
123456
用户名中包含有非数字的字符！

当用户输入的用户名长度不在 6~10 位时同样会拋出 LoginException 异常并打印异常信息，如下所示。
用户名：
administrator
密码：
123456
用户名长度必须在6~10位之间！

当用户输入的登录密码不等于 6 位时也会拋出 LogWException 异常，并打印出异常信息，如下所示。
用户名：
20181024
密码：
12345
密码长度必须为 6 位！

当用户输入的用户名和密码都符合要求时，则打印登录成功的信息，如下所示。
用户名：
20181024
密码：
123456
登录成功！
```

## 总结：异常处理5个关键字

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/150934310227344.png =456x)

## 项目三 《开发团队调度软件》

# 多线程

## 概念

**程序，进程，线程**

- 程序(program)是为完成特定任务、用某种语言编写的一组指令的集合。
    - 即指一段静态的代码，静态对象。
    - 作为执行蓝本的同一段程序，可以被多次加载到系统的不同内存区域执行，形成不同进程。
- **进程(process)是程序的一次执行过程，或是正在运行的一个程序。是由代码，数据，内核状态和一组寄存器组成**
    - 进程是一个内核级的实体，进程结构的所有成分都在内核空间中。
        - 一个用户程序不能直接访问这些数据   
    - 是一个程序的一次动态执行过程：有它自身的产生、存在和消亡的过程。——生命周期
        - **一个进程在其执行的过程中，可以产生多个线程，形成多个执行流。每个线程也有其生命周期。**
    - **进程作为资源分配的单位，系统在运行时会为每个进程分配不同的内存区域**
- 线程(thread)：进程可进一步细化为线程（轻型进程），是一个程序内部的一条执行路径（执行流）。是由表示程序运行状态的寄存器（程序计数器，栈指针等）以及堆栈组成。线程是计算过程在某一时刻的状态。
    - 线程是一个用户级的实体，线程结构驻留在用户空间中，
        - 能被普通的用户级函数直接访问。 
    - 若一个进程同一时间并行执行多个线程，就是支持多线程的。
    - 线程作为调度和执行的单位，每个线程拥有独立的运行栈和程序计数器(pc)，线程切换的开销小
    - 一个进程中的多个线程共享相同的内存单元/内存地址空间，线程包含进程地址空间中的代码和数据。（多个线程共享同一个进程中的方法区和堆）
        - 它们从同一堆中分配对象，可以访问相同的变量和对象。这就使得线程间通信更简便、高效。但多个线程操作共享的系统资源可能就会带来安全的隐患
        - 当两个线程对同一个对象进行访问时，他们将共享数据。
    - **一个执行流是由CPU运行程序代码并操作程序的数据所形成的**，
       - 线程被认为是以CPU为主体的行为，  

**单核CPU和多核CPU的理解**

- 单核CPU，其实是一种假的多线程，因为在一个时间单元内，也只能执行一个线程的任务（可以将一个线程挂起，先执行其他的线程），但是因为CPU时间单元特别短，因此感觉不出来。
- 如果是多核的话，才能更好的发挥多线程的效率。（现在的服务器(Linux,Windos Server)都是多核的）
- 一个Java应用程序java.exe，其实**至少有三个线程：main()主线程，gc()垃圾回收线程，异常处理线程**。
   - 当然如果发生异常，会影响主线程。

**并行与并发**

- 并行：多个CPU同时执行多个任务。
- 并发：一个CPU(采用时间片)同时执行多个任务。

**使用多线程的优点**

1.  提高应用程序的响应。对图形化界面更有意义，可增强用户体验。
2.  提高计算机系统CPU的利用率
3.  改善程序结构。将既长又复杂的进程分为多个线程，独立运行，利于理解和修改

**何时需要多线程**

- 程序需要同时执行两个或多个任务。
- 程序需要实现一些需要等待的任务时，如用户输入、文件读写操作、网络操作、搜索等。
- 需要一些后台运行的程序时。

## 线程的创建和使用

- Java语言的JVM允许程序运行多个线程，它通过`java.lang.Thread类`来体现。

**Thread类的特性**

- 每个线程都是通过某个特定Thread对象的run()方法来完成操作的，经常把run()方法的主体称为线程体
- 通过该Thread对象的start()方法来启动这个线程，而非直接调用run()

**Thread类构造器**

- Thread()：创建新的Thread对象
- Thread(String threadname)：创建线程并指定线程实例名
- Thread(Runnable target)：指定创建线程的目标对象，它实现了Runnable接口中的run方法
- Thread(Runnable target, String name)：创建新的Thread对象
- Thread(TreadGroup group,Runnable target,String name)  
     - 指明该线程所属的线程组 
     - 提供线程体的对象。
     - 线程名称

**API中创建线程的两种方式**

JDK1.5之前创建新执行线程有两种方法：

- 继承Thread类的方式
- 实现Runnable接口的方式

### 方式一：继承Thread类

1. 定义子类继承Thread类。
2. 子类中重写Thread类中的run方法。
   - 将此线程执行的操作声明在run()中
3. 创建Thread子类对象，即创建了线程对象。
4. 调用线程对象start方法：启动线程，调用run方法。

**注意点：**

1. 如果自己手动调用run()方法，那么就只是普通方法，没有启动多线程模式。
2. run()方法由JVM调用，什么时候调用，执行的过程控制都有操作系统的CPU调度决定。
3. 想要启动多线程，必须调用start方法。
4. **一个线程对象只能调用一次start()方法启动，如果重复调用了，则将抛出以上的异常“IllegalThreadStateException”**

#### 例1

```java
public class ThreadTest {
    public static void main(String[] args) { //主线程
//     3.创建Thread类的子类的对象 //主线程
        MyThread t1 = new MyThread();

//        4.通过此对象调用start() //主线程
        t1.start();
//        在主线程中创建t1对象，在当前线程中执行(与main线程一起执行)
//        (1.)启动当前线程
//        (2.)调用当前线程的run()

//        不能通过直接调用run()的方式启动线程
//        t1.run();
//        若直接调用run()则视为直接调用方法，而不是开启线程

//        再启动一个线程
//        t1.start();
//        不能令已经调用start()的对象来再启动一个线程，否则报错
//        需要重新创建一个线程对象来调用start()
        MyThread t2 = new MyThread();
        t2.start();

        //以下仍然是在main线程中执行的。
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(i + "*");
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}

//1.创建Thread类的子类
class MyThread extends Thread {
    @Override
//    2.重写run()方法
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i + "-");
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}
```

#### 练习

创建两个分线程，让其中一个线程输出1-100之间的偶数，另一个线程输出1-100之间的奇数。

```java
public class ThreadDemo {
    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        MyThread2 t2 = new MyThread2();

        t1.start();
        t2.start();
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
```

#### 例：以Thread类的匿名子类的方式

```java
public class ThreadDemo {
    public static void main(String[] args) {
//        以Thread类的匿名子类的方式
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
            }
        }.start();
    }
}
```

#### 例

模拟一个人生产50个玩具，每200毫秒生产一个，当生产到第20个时加入每秒吃1个馒头，共吃完3个后在接着生产的多线程

```java
public class ThreadTest1 {
    public static void main(String[] args) {
        Person person = new Person();
        person.start();
    }
}

class Person extends Thread {
    private int toy = 0;
    private int food = 0;

    public void eatfood(){
        System.out.println("吃了" + ++food +"个馒头");
    }

    @Override
    public void run() {
        while (toy < 50) {
            System.out.println("生产了:" + ++toy);
            if (toy % 20 == 0) {
                for(;food < 3;){
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    eatfood();
                }
                food = 0;
            }
        }
    }
}
```

### Thread类的有关方法

- **start()**: 启动当前线程，并调用当前线程的run()方法
- **run()**: 线程在被调度时执行的操作
    - 通常Thread子类需要重写此方法，将创建的线程要执行的操作声明在此方法
- String **getName():** 返回线程的名称
- void **setName(String name)** : 设置该线程名称
- static Thread **currentThread()**: 返回当前线程。
     - 在Thread子类中就是this，通常用于主线程和Runnable实现类
- static void **yield()**：线程让步(释放当前CPU的执行权)（有可能立刻又被赋予执行权）
  - 暂停当前正在执行的线程，把执行机会让给优先级相同或更高的线程
  - 若队列中没有同优先级的线程，忽略此方法
- **join()** ：当某个程序执行流中调用其他线程的 join() 方法时，该线程将被阻塞，直到 join() 方法加入的 join 线程执行完为止，该线程才结束阻塞状态。
  - 低优先级的线程也可以获得执行
- static void **sleep(long millis)**：让当前线程睡眠(指定时间:毫秒)，在睡眠时间内，当前线程是阻塞状态。
  - 令当前活动线程在指定时间段内放弃对CPU控制,使其他线程有机会被执行,时间到后重排队。
  - 抛出InterruptedException异常
- **stop()**: 强制线程生命期结束，不推荐使用（已过时）
- boolean **isAlive()**：返回boolean，判断线程是否还活着
- String **getState()** : 获得线程的状态，该方法的返回值是Thread.State，它是线程状态的枚举
- **suspend()** :  使另一个线程(`线程对象.suspend()`)暂停执行，要想恢复，需要其他线程使用resume()方法。
   - 容易造成死锁  
      - 如果存在a.b两线程使用同一个锁，在a线程中使用了 `b.suspend()`使得b线程阻塞，但是b线程并未释放锁，那么a线程也无法打开锁。于是死锁。
- **resume()** : 恢复线程
- **interrupt()** ：中断线程的阻塞，并且该线程收到InterruptException异常.

#### 例1

```java
public class ThreadMethodTest {
    public static void main(String[] args) {
        MyThreadTest t = new MyThreadTest("分线程");
//        方式1：给分线程命名
//        t.setName("线程1：");
        t.start();

//        给主线程命名
        Thread.currentThread().setName("主线程");

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }

            System.out.println(Thread.currentThread().isAlive()); //判断线程是否存活(boolean)

            if (i == 20) {
                try {
                    t.join();
//                    中断当前线程的执行，先执行插入的线程，直到插入的线程执行结束，才执行原来的线程
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(t.isAlive()); //判断线程是否存活(boolean)

    }
}

class MyThreadTest extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                System.out.println(this.getState()); //获取t线程的状态
                try {
                    sleep(10); //睡眠
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (i % 20 == 0) {
                this.yield(); //释放当前CPU的执行权
//                等同于
//                Thread.currentThread().yield();
            }
        }
    }

    //    方式二：构造器命名
    public MyThreadTest(String name) {
        super(name);
    }
}
```

#### 练习

编写一个继承Thread类的方式实现多线程的程序。该类MyThread有两个属性，一个字符串WhoAmI代表线程名，一个整数delay代表该线程随机要休眠的时间。构造有参的构造器，线程执行时，显示线程名和要休眠时间。

另外，定义一个测试类TestThread，创建三个线程对象以展示执行情况。

```java
public class MyThreadTest {
    public static void main(String[] args) {
        MyThread myThread= new MyThread("线程1",100);
        myThread.start();
    }
}

class MyThread extends Thread{
    private String whoAmI;
    private int delay;

    public MyThread(String whoAmI, int delay) {
        this.whoAmI = whoAmI;
        this.delay = delay;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(whoAmI);
        try {
            sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName());
    }
}
```

### ThreadGroup类 线程组

Java应用程序中，所有的线程都属于一个线程组，线程组中的线程一般是相关的。java.lang包中的ThreadGroup类实现了线程组，并提供了对线程组或组中的每一个线程进行操作的方法。

- Java中每个线程都属于某个线程组。线程组使一组线程可以作为一个对象进行统一处理或维护。
    - 例如可以用一个方法统一调用、启动或挂起线程组内的所有线程。
- 一个线程只能在创建时设置其所属的线程组，在线程创建后就不允许将线程从一个线程组移到另一个线程组。

**构造方法**

- 线程组是由java.lang包中的ThreadGroup类实现的。在创建线程时可以显式地指定线程组，此时需要从如下3种线程构造方法中选择一种：
   - public Thread(ThreadGroup group,Runnable target);
   - public Thread(ThreadGroup group,String name);
   - public Thread(ThreadGroup group,Runnable target,String name);

**若在线程创建时并没有显式指定线程组，则新创建的线程自动属于父线程所在的线程组。**

- 在Java应用程序启动时，Java运行系统为该应用程序创建了一个称为main的线程组。
- 如果以后创建的线程没有指定线程组，则这些线程都将属于main线程组。
- 程序中可以利用ThreadGroup类显式创建线程组，并将新创建的线程放入该线程组，

**ThreadGroup类对Java应用程序中的线程组进行管理。**

- 一个线程组可以包含任意数目的线程。
- 一个线程组内不仅可以包含线程，还可以包含其他线程组。
- 在Java应用程序中，最顶层线程组是main。
   - 在main中可以创建线程或线程组，并且可以在main的线程组中进一步创建线程组。
   - 因此在Java应用程序中，形成了以main为根的线程与线程组的树状结构。

### 方式二：实现Runnable接口

1. 定义子类，实现Runnable接口。
2. 子类中重写Runnable接口中的run方法。
3. 通过Thread类含参构造器创建线程对象。
4. 将Runnable接口的子类对象作为实际参数传递给Thread类的构造器中。
5. 调用Thread类的start方法：开启线程，调用Runnable子类接口的run方法。

#### 例

```java
public class RunnableTest {
    public static void main(String[] args) {
    //3. 通过Thread类含参构造器创建线程对象。
    //4. 将Runnable接口的子类对象作为实际参数传递给Thread类的构造器中。
        Thread t = new Thread(new RunTest());
    //5. 调用Thread类的start方法：开启线程，调用Runnable子类接口的run方法。
        t.start();
    }

}

//1. 定义子类，实现Runnable接口
class RunTest implements Runnable {
    int i;

//2. 子类中重写Runnable接口中的run方法
    @Override
    public void run() {
        while (true) {
            System.out.println("Hello" + i++);
            if (i == 5) {
                break;
            }
        }
    }
}
```

### 继承方式和实现方式的联系与区别

**联系：**

```java
public class Thread extends Object implements Runnable
```

1. Tread类实现了Runnable接口
2. 都需要重写run()

**区别**

- 继承Thread：线程代码存放Thread子类run方法中。
- 实现Runnable：线程代码存在接口的子类的run方法。

**优点比较：**

- 继承Tread类：
   - 代码简单 
- 实现Runnable接口
  1. 避免了单继承的局限性
  2. 多个线程可以共享同一个接口实现类的对象，非常适合多个相同线程来处理同一份资源。
  3. 符合OOP的设计思想
     - 从OO设计的角度，Tread类时虚拟CPU的封装，其子类也应该与CPU有关，但在继承Tread类的子类构造线程的方法中，其子类大多也与CPU无关。
     
### 线程的调度

虽然概念上多个线程可以并发执行，但由于目前计算机多数是单个CPU,所以一个时刻只能运行一个线程。在单个CPU上以某种顺序运行多个线程，称为线程的调度。

**调度策略**

- 时间片

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/9075515221057.png =212x)

- 抢占式：高优先级的线程抢占CPU

**Java的调度方法**
- 同优先级线程组成先进先出队列（先到先服务），使用时间片策略
- 对高优先级，使用优先调度的抢占式策略

### 线程的优先级

**线程的优先级等级（静态常量）**

- MAX_PRIORITY：10 
- MIN _PRIORITY：1 
- NORM_PRIORITY：5
 
**涉及的方法**

- getPriority() ：返回线程优先值
- setPriority(int newPriority) ：改变线程的优先级

**说明**

- 一般，主线程具有普通优先级NORM_PRIOPITY
- 线程创建时继承父线程的优先级
   - 父线程：执行创建新线程语句所在线程。 

**基于优先级的抢先式调度**

- Java基于线程的优先级选择高优先级的线程进行运行。该线程（当前线程）将持续运行，直到它中止运行，或其他高优先级线程成为可运行的。
   - 在后一种情况，低优先级线程被高优先级线程抢占运行。线程中止运行的原因可能有多种，如执行Thread.sleep()调用，或等待访问共享资源。
   - 低优先级只是获得调度的概率低，并非一定是在高优先级线程之后才被调用
- 在Java运行系统中可以按优先级设置多个线程等待池，JVM先运行高优先级池中的线程，高优先级等待池空后，才考虑低优先级。如果线程运行中有更高优先级的线程成为可运行的，则CPU将被高优先级线程抢占。
- 抢先式调度可能是分时的，即每个同等优先级池中的线程轮流运行，也可能不是，即线程逐个运行，由具体JVM而定。
   - 线程一般通过使用sleep()等方法保证给其他线程运行时间。
   

#### 例 创建三个窗口买票，总票数100

##### 继承Tread 类

```java
public class WindowTest {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }

}

class Window extends Thread {
    private static int tickets = 100;

    @Override
    public void run() {
        while (true) {
            if (tickets > 0) {
                System.out.println(getName() + "卖票，票号为：" + tickets);
                tickets--;
            } else {
                break;
            }
        }
    }
}

//存在线程安全
//窗口3卖票，票号为：100
//窗口2卖票，票号为：100
//窗口1卖票，票号为：100
```

##### 实现Runable接口

```java
public class WindowTest2 {
    public static void main(String[] args) {
        Windows w1 = new Windows();
        Windows w2 = new Windows();
        Windows w3 = new Windows();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w2);
        Thread t3 = new Thread(w3);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }


}

class Windows implements Runnable {
    private static int ticket = 100;

    @Override
    public void run() {
        for (int i = 0; i < 100; i--) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + ": " + ticket);
                ticket--;
            } else {
                break;
            }
        }
    }
}
```

### 线程的分类

Java中的线程分为两类：一种是守护线程，一种是用户线程。

- 它们在几乎每个方面都是相同的，唯一的区别是判断JVM何时离开。
- 守护线程是用来服务用户线程的，通过在start()方法前调用
- `Thread.setDaemon(true)`可以把一个用户线程变成一个守护线程。
- Java垃圾回收就是一个典型的守护线程。
   - 若JVM中都是守护线程，当前JVM将退出。

## 线程的生命周期

**JDK中用Thread.State类定义了线程的几种状态**

要想实现多线程，必须在主线程中创建新的线程对象。Java语言使用Thread类及其子类的对象来表示线程，在它的一个完整的生命周期中通常要经历如下的五种状态：

- 新建状态 new： 当一个Thread类或其子类的对象被声明并创建时，新生的线程对象处于新建状态
   - 线程还未被分配有关的系统资源。 
- 就绪（可运行）状态 runnable：处于新建状态的线程被start()后，将进入线程队列等待CPU时间片，此时它已具备了运行的条件，只是没分配到CPU资源
   - 表示系统处于运行就绪状态，此时线程仅仅是可以运行。
   - start()方法使系统为线程分配必要的资源，将线程中虚拟的CPU置为Runnable状态，并将线程交给系统调度。
   - 在多线程程序设计中，系统中往往会有多个线程同时处于Runnable状态，他们将 竞争有限的CPU资源，有运行系统根据线程调度策略进行调度
- 运行状态：当就绪的线程被调度并获得CPU资源时,便进入运行状态（线程占有CPU并实际运行的状态）， run()方法定义了线程的操作和功能 。此时线程状态的变迁有以下三种：
   1. 如果线程正常执行结束或应用程序停止运行，线程将进人终止状态。
   2. 如果当前线程执行了yild()方法，或者当前线程因调度策略（执行过程中，有一个更高优先级的线程进入可运行状态，这个线程立即被调度执行，当前线程占有的CPU被抢占；或在分时方式时，当前执行线程执行完当前时间片)由系统控制进入可运行状态。
   3. 如果发生下面几种情况时，线程就进入阻塞状态。
       - 线程调用了sleep()方法或join()方法，进入阻塞状态。
       - 线程调用wait()方法时，由运行状态进入阻塞状态。
       - 如果线程中使用synchronized来请求对象的锁未获得时，进入阻塞状态。
       - 如线程中有输入/输出操作，也将进入阻塞状态，待输入/输出操作结束后，线程进入可运行状态。
- 阻塞状态：在某种特殊情况下，被人为挂起或执行输入输出操作时，让出 CPU 并临时中止自己的执行，进入阻塞状态。阻塞状态根据产生的原因又可分为对象锁阻塞(blocked in loek pool)、等待阻塞(blocked in wait pool)和其他阻塞(otherwise blocked)。
   - 对象锁阻塞： 如果线程中使用synchronized来请求对象的锁但未获得时，进人对象锁阻塞状态。
      - 该状态下的线程当获得对象锁后，将进人可运行状态。
   - 等待阻塞：线程调用wait()方法时，线程由运行状态进人等待阻塞状态。
      - 在等待阻塞状态下的线程若被notify()和notifyAll()唤醒，被interrupt()中断或者等待时间到，线程将进入对象锁阻塞状态。
   - 其他阻塞：线程调用了sleep()方法或join()方法时，线程进人其他阻塞状态。
      - 由于调用sleep()方法而进人其他阻塞状态的线程，睡眠时间到时将进人可运行状态，
      - 由于调用join()方法而进人其他阻塞状态的线程，当t线程结束或等待时间到时，进入可运行状态。
- 死亡(终止)状态：线程完成了它的全部工作或线程被提前强制性地中止或出现异常导致结束
   - 终止状态是线程执行结束的状春，没有任何方法可改变它的状态。
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/204051510227351.png =701x)


![](C:/Users/zjk10/OneDrive/NoteBook/pictures/331541510247517.png =529x)

## 线程的同步(线程的安全问题)

**提出问题**

- 多个线程执行的不确定性引起执行结果的不稳定
- 多个线程对账本的共享，会造成操作的不完整性，会破坏数据。

1. 多线程出现了安全问题
2. 问题的原因：
   - 当多条语句在操作同一个线程共享数据时，一个线程对多条语句只执行了一部分，还没有执行完，另一个线程参与进来执行。导致共享数据的错误。
3. 解决办法：
  - 对多条操作共享数据(多个线程共同操作的变量，如static)的语句，只能让一个线程都执行完，在执行过程中，其他线程不可以参与执行。

```java
public class WindowTest {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }

}

class Window extends Thread {
    private static int tickets = 100;

    @Override
    public void run() {
        while (true) {
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

//存在线程安全
//重票
//窗口3卖票，票号为：100
//窗口2卖票，票号为：100
//窗口1卖票，票号为：100

//错票 在线程切换时，可能出现的问题。
//通过sleep()使线程睡眠，来延长for循环满足条件时执行的过程和ticket的值开始变化的时间，只是让这个更直观。
//在这个时间段里（某个过程在操作的过程中，对ticket值的操作尚未完成的时候）：
//启动的进程在ticket的值满足条件的时候都进入执行语句，而此时因为ticket的值没来得及改变，
//因此，启动的进程中的ticket如果刚刚好满足条件，但是已经有多个线程在这段时间经过条件判断进入执行，
//此时，将对ticket的值t产生多次改变。
//窗口3卖票，票号为：0
//窗口1卖票，票号为：-1
```

### 同步锁机制

对于并发工作，你需要某种方式来防止两个任务访问相同的资源（其实就是共享资源竞争）。 防止这种冲突的方法就是当资源被一个任务使用时，在其上加锁。第一个访问某项资源的任务必须锁定这项资源，使其他任务在其被解锁之前，就无法访问它了，而在其被解锁之时，另一个任务就可以锁定并使用它了。

**synchronized的锁(对象锁)**

Java中对共享数据操作的并发控制是采用传统的封锁技术。

一个程序的各个并发线程中对同一个对象进行访问的代码段，称为临界区(critical sections).。在Java语言中，临界区可以是一个语句块或是一个方法，并且用synchronized关键字标识。

临界区的控制是通过对象锁进行的。Java平台将每个由`synchronized(someObject){}`语句指定的对象someObject设置一个锁，称为对象锁(monitor)。对象锁是一种独占的排他锁(exclusive locks)。这种锁的含义是，当一个线程获得了对象的锁后，便拥有该对象的操作权，其他任何线程不能对该对象进行任何操作。线程在进入临界区时，首先通过`synchronized(someObject)`语句测试并获得对象的锁，只有获得对象锁才能继续执行临界区中的代码，否则将进入等待状态。

- 任意对象都可以作为同步锁。所有对象都自动含有单一的锁（监视器）。
- 同步方法的锁：静态方法（类名.class）、非静态方法（this）
- 同步代码块：自己指定，很多时候也是指定为this或类名.class
- 注意：
   - 必须确保使用同一个资源的多个线程共用一把锁，这个非常重要，否则就无法保证共享资源的安全
  - 一个线程类中的所有静态方法共用同一把锁（类名.class），所有非静态方法共用同一把锁（this），同步代码块（指定需谨慎）


**对于对象锁的使用有如下几点说明**

1. 关于对象锁的返还。对象的锁在如下几种情况下由持有线程返还。
   - 当synchronized()语句块执行完后
   - 当在synchronized()语句块中出现异常(Exception)。
   - 当持有锁的线程调用该对象的wait()方法。此时该线程将释放对象的锁，而被放人对象的wait pool中，等待某种事件的发生。
2. 共享数据的所有访问都必须作为临界区，使用synchronized进行加锁控制。
   - 对共享数据所有访问的代码，都应该作为临界区使用synchronized进行标识。这样保证所有的操作都能够通过对象锁的机制进行控制。
   - 如果有一种访问操作未标记为synchronized,则这种操作将绕过对象锁，很可能破坏共享数据的一致性。
3. 用synchronized保护的共享数据必须是私有的。
   - 将共享数据定义为私有的，使线程不能直接访问这些数据，必须通过对象的方法。
   - 而对象的方法中带有由synchronized标记的临界区，实现对并发操作多个线程的控制。
4. 同步方法：如果一个方法的整个方法体都包含在synchronized语句块中，则可以把该关键字放在方法的声明中。
   - 这种方式程序的可读性好，便于理解，因此比较常用。
   - 但控制对象锁的时间稍长，因此并发执行的效率会受到一定的影响，但影响不是很大。
5. Java中对象锁具有可重人性。
   - Java运行系统中，一个线程在持有某个对象的锁的情况下，可以再次请求并获得该对象的锁，这就是对象锁具有可重入性的含义。
   - 锁的可重入性是很重要的因为这可以避免单个线程因为自已已经持有的锁而产生死锁。例如下面的程序。

**同步的范围**

1. 如何找问题，即代码是否存在线程安全？（非常重要）
   1. 明确哪些代码是多线程运行的代码
   2. 明确多个线程是否有共享数据
   3. 明确多线程运行代码中是否有多条语句操作共享数据
2. 如何解决呢？（非常重要）
   - 对多条操作共享数据的语句，只能让一个线程都执行完，在执行过程中，其他线程不可以参与执行。
      - 即所有操作共享数据的这些语句都要放在同步范围中
3. 切记：
   - 范围太小：没锁住所有有安全问题的代码
   - 范围太大：没发挥多线程的功能。**

**释放锁的操作**

-  当前线程的同步方法、同步代码块执行结束。
- 当前线程在同步代码块、同步方法中遇到break、return终止了该代码块、该方法的继续执行。
- 当前线程在同步代码块、同步方法中出现了未处理的Error或Exception，导致异常结束。
- 当前线程在同步代码块、同步方法中执行了线程对象的wait()方法，当前线程暂停，并释放锁。

**不会释放锁的操作**

- 线程执行同步代码块或同步方法时，程序调用`Thread.sleep()、Thread.yield()方法`暂停当前线程的执行
- 线程执行同步代码块时，其他线程调用了该线程的`suspend()方法`将该线程挂起，该线程不会释放锁（同步监视器）。
   - 应尽量避免使用`suspend()和resume()`来控制线程

#### 线程的死锁问题

- 死锁
    - **不同的线程分别占用对方需要的同步资源不放弃**，都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
    - 出现死锁后，**不会出现异常，不会出现提示**，只是所有的线程都处于阻塞状态，无法继续
- 解决方法
   - 专门的算法、原则
   - 尽量减少同步资源的定义
   - 尽量避免嵌套同步

##### 例：死锁：

```java
public class TreadTest {
    public static void main(String[] args) {
        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();
        new Thread(){
            @Override
            public void run() {
                synchronized (s1){
                    s1.append("a");
                    s2.append("1");

                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    synchronized (s2){
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2){
                    s1.append("c");
                    s2.append("3");
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (s2){
                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }).start();
    }
}
```

### synchronized的使用方法 同步机制

#### 1. 同步代码块：

```java
synchronized (同步监视器){  
    // 需要被同步的代码；(即操作共享数据的代码)
}
```  

1. 操作共享数据的代码，即需要被同步的代码
2. 共享数据：多个线程共同操作的变量，如static
3. 同步监视器：即锁。如何一个类的对像都可以充当锁
    - 要求：**多个线程必须要共用同一把锁**
4. 解决了线程的安全问题。
    - 但，操作同步代码时，只能有一个线程参与，其他线程等待。相当于是一个单线程的过程，效率低。
        - 包含同步代码的范围(共享数据)要注意。

**充当锁的方式**

(共用)

- 在实现Runnable接口创建多线程的方式中，可以使用以下充当锁：
   - this  
   - 类名.class 
   - run()方法外的对象
- 在继承Thread类创建多线程的方式中，可以使用以下充当锁：
   - 慎用this 
   - 类名.class 
   - run()方法外的静态对象（static）

#####  解决通过实现Runable接口的方式的线程安全问题

```java
public class WindowTest2 {
    public static void main(String[] args) {
        Windows w1 = new Windows();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w1);
        Thread t3 = new Thread(w1);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Windows implements Runnable {
    private int ticket = 100;
    private static int tickets = 100;
    Object obj = new Object();

    @Override
    public void run() {
        while (true) {
//            Object obj = new Object(); 错误
            //synchronized (obj) { //要求线程必须共用同一把锁
            //synchronized(this){ //使用当前对象作锁。此时的this：唯一的Windows的对象。
            synchroniaed(Window.class){  //通过类作为锁，类也是对象。
            //如果都是使用同一个Runnable接口的实现类的对象来创建线程时。
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + tickets);
                    tickets--;
                } else {
                    break;
                }
            }
        }
    }
}
```

#####  解决通过继承Tread类的方式的线程安全问题

```java
public class WindowTest {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }

}

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

#### 2. 同步方法

**synchronized还可以放在方法声明中，表示整个方法为同步方法。**

- 通过实现**Runnable接口的线程，此时同步监视器为this**
- 通过**继承Tread类的线程，此时的同步方法要是静态的，同步监视器为类**

**实现**

```java
public class WindowTest2 {
    public static void main(String[] args) {
        Windows w1 = new Windows();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w1);
        Thread t3 = new Thread(w1);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }


}

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

**继承**

```java
public class WindowTest {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }

}

class Window extends Thread {
    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            changeTicket();
        }
    }

    public static synchronized void changeTicket() {//同步监视器： 类（静态方法）
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


### Lock(锁)

从JDK 5.0开始，Java提供了更强大的线程同步机制——通过显式定义同步锁对象来实现同步。同步锁使用Lock对象充当。

- `java.util.concurrent.locks.Lock接口`是控制多个线程对共享资源进行访问的工具。锁提供了对共享资源的独占访问，每次只能有一个线程对Lock对象
加锁，线程开始访问共享资源之前应先获得Lock对象。
- ReentrantLock 类实现了 Lock ，它拥有与 synchronized 相同的并发性和内存语义，在实现线程安全的控制中，比较常用的是ReentrantLock，可以显式加锁、释放锁。
- 对于继承Tread类的方式，对ReentrantLock对象加static

```java
public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }

}

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

### synchronized 与 Lock 的对比

1. Lock是显式锁（手动开启(lock())和关闭锁(unlock())），synchronized是隐式锁，出了作用域自动释放同步监视器。
2. Lock只有代码块锁，synchronized有代码块锁和方法锁
3. 使用Lock锁，JVM将花费较少的时间来调度线程，性能更好。并且具有更好的扩展性（提供更多的子类）

**优先使用顺序：**

Lock --> 同步代码块（已经进入了方法体，分配了相应资源）--> 同步方法（在方法体之外）

### 练习

#### 练 习1

```java
银行有一个账户。
有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。
问题：该程序是否有安全问题，如果有，如何解决？
【提示】
1，明确哪些代码是多线程运行代码，须写入run()方法
2，明确什么是共享数据。
3，明确多线程运行代码中哪些语句是操作共享数据的。
拓展问题：可否实现两个储户交替存钱的操作
```

```java
public class AccountTest {
    public static void main(String[] args) {
        Account acct = new Account(0);
        Customer c1 = new Customer(acct);
        Customer c2 = new Customer(acct);

        c1.setName("甲");
        c2.setName("乙");

        c1.start();
        c2.start();
    }
}

class Account{
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

//    存钱
    public void deposit(int amt){
        if(amt > 0){
            synchronized(Account.class){
                balance += amt;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(Thread.currentThread().getName() + "存钱成功，余额：" + balance);
            }
        }
    }

}

class Customer extends Thread{
    private Account acct;

    public Customer(Account acct) {
        this.acct = acct;
    }

    @Override
    public void run() {
        for(int i = 0; i< 3;i++){
            acct.deposit(1000);
        }
    }
}
```

#### 练习2

利用多线程设计一个程序，同时输出 50 以内的奇数和偶数，以及当前运行的线程名。

```java
//利用多线程设计一个程序，同时输出 50 以内的奇数和偶数，以及当前运行的线程名。
public class ThreadTest2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i <= 50; i++) {
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + i);
                } else {
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i <= 50; i++) {
                if (i % 2 == 1) {
                    System.out.println(Thread.currentThread().getName() + i);
                } else {
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
```

## 线程的通信

**wait() 与 notify() 和 notifyAll()**

- wait()：令当前线程挂起并放弃CPU、同步资源并等待，使别的线程可访问并修改共享资源，而当前线程排队等候其他线程调用`notify()或notifyAll()`方法唤醒，唤醒后等待重新获得对监视器的所有权后才能继续执行。
- notify()：唤醒正在排队等待同步资源的线程中优先级最高者结束等待
- notifyAll ()：唤醒正在排队等待资源的所有线程结束等待.

这三个方法**只有在`synchronized方法或synchronized代码块`中才能使用**，否则会报`java.lang.IllegalMonitorStateException`异常。

- 因为这三个方法**必须由锁对象(即必须是同步代码/同步方法的同步监视器)调用**，
   - 而任意对象都可以作为synchronized的同步锁，因此这三个方法只能在**Object类中声明**

### wait() 方法

- 在当前线程中调用方法： 对象名.wait()
- 使当前线程进入等待（某对象）状态 ，直到另一线程对该对象发出 notify (或notifyAll) 为止。
- 调用方法的必要条件：当前线程必须具有对该对象的监控权（加锁）
- 调用此方法后，当前线程将释放对象监控权(释放锁，即打开了锁，其他线程得以进入) ，然后进入等待
- 在当前线程被notify后，要重新获得监控权，然后从断点处继续代码的执行。

#### sleep()和wait()的异同点

**相同：**

- sleep()和wait()两个执行方法，都可以使得当前的线程进入阻塞状态

**不同：**

1. 声明位置不同：
   - sleep()在Tread类中声明
   - wait()在Object类中声明
2. 调用的要求不同：
   - sleep()可以在任何需要的场景下调用
   - wait()必须使用在同步代码块和同步方法中
3. 是否释放同步监视器：（ 如果两个方法都使用在同步代码块/同步方法中 ）
   - sleep()不释放
   - wait()释放

### notify()/notifyAll()

- 在当前线程中调用方法： 对象名.notify()
- 功能：唤醒等待该对象监控权的一个/所有线程(被wait()的线程)。
- 调用方法的必要条件：当前线程必须具有对该对象的监控权（加锁）

### 例题

#### 例

```java
public class CommunicationTest {
    public static void main(String[] args) {
        Print p = new Print();
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(p);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }
}

class Print implements Runnable {
    private int number = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this){

                notify();

                if (number <= 100) {

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + ": " + number++);

                    try {
//                         阻塞：使得调用如下wait()方法的线程进入阻塞状态。
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    break;
                }
            }
        }
    }
}
```

#### 典例：生产者消费者问题

生产者(Productor)将产品交给店员(Clerk)，而消费者(Customer)从店员处取走产品，店员一次只能持有固定数量的产品(比如:20），如果生产者试图生产更多的产品，店员会叫生产者停一下，如果店中有空位放产品了再通知生产者继续生产；如果店中没有产品了，店员会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。

- 这里可能出现两个问题：

1. 生产者比消费者快时，消费者会漏掉一些数据没有取到。
2. 消费者比生产者快时，消费者会取相同的数据。

```java
public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer p = new Producer(clerk);
        Thread p1 = new Thread(p);
        p1.setName("生产者1");

        Consumer c = new Consumer(clerk);
        Thread c1 = new Thread(c);
        c1.setName("消费者1");

        p1.start();
        c1.start();
    }
}

class Clerk {
    private int productCount = 0;

    //    生产产品
    public synchronized void produceProduct() {
        if (productCount < 20) {
            productCount++;
            System.out.println(Thread.currentThread().getName() + ": 开始生产第" + productCount + "个产品");

            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //    消费产品
    public synchronized void consumeProduct() {

        if (productCount > 0) {
            System.out.println(Thread.currentThread().getName() + ": 开始消费第" + productCount + "个产品");
            productCount--;

            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Producer implements Runnable { //消费者

    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":生产产品...");

        while (true) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            clerk.produceProduct();
        }
    }
}

class Consumer implements Runnable { //消费者

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":消费产品...");

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            clerk.consumeProduct();
        }
    }
}
```

#### 生产者-消费者问题

编写生产者-消费者模式的程序。生产者每隔100ms产生0~9的一个数，保存在一个MyNumber类型的对象中，并显示出来。只要这个MyNumber对象中保存了新的数字，消费者就将其取出并显示。试定义MyNumber类，编写消费者和生产者程序，并编写主程序创建一个MyNumber对象，以及一个生产者线程、一个消费者线程，并将这两个线程启动运行。

```java
public class MyNumberTest {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        Productor p1 = new Productor(myNumber);
        Customer c1 = new Customer(myNumber);

        p1.setName("生产者1");
        c1.setName("消费者1");

        p1.start();
        c1.start();
    }
}

class MyNumber {
    private int num;

    public synchronized void productNewNumber() {
        num = (int) (Math.random() * 100 / 10);
        notify();
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void consumNewNumber() {
        if(num == 0){
            notify();
            try {
                wait();
            } catch (InterruptedException e) {
                x
            }
        }
        System.out.println("取出数字" + num);
        num = 0;
        notify();

        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Customer extends Thread {
    public Customer(MyNumber myNumber) {
        this.myNumber = myNumber;
    }

    MyNumber myNumber = new MyNumber();

    @Override
    public void run() {
        while (true) {
            myNumber.consumNewNumber();
            System.out.println(Thread.currentThread().getName() + "消费产品完成");
        }
    }
}

class Productor extends Thread {

    public Productor(MyNumber myNumber) {
        this.myNumber = myNumber;
    }

    private MyNumber myNumber = new MyNumber();

    @Override
    public void run() {
        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            myNumber.productNewNumber();
            System.out.println(Thread.currentThread().getName() + "生产产品完成");
        }
    }
}
```

#### 练 习 2 模拟银行取钱的问题

```
1.定义一个Account类
1）该Account类封装了账户编号（String）和余额（double）两个属性
2）设置相应属性的getter和setter方法
3）提供无参和有两个参数的构造器
4）系统根据账号判断与用户是否匹配，需提供hashCode()和equals()方法的重写
2.提供两个取钱的线程类：小明、小明’s wife
1）提供了Account类的account属性和double类的取款额的属性
2）提供带线程名的构造器
3）run()方法中提供取钱的操作
3.在主类中创建线程进行测试。考虑线程安全问题。
```

## JDK5.0 新增线程创建方式

### 新增方式一：实现Callable接口

与使用Runnable相比， Callable功能更强大些

- call()方法相比run()方法，可以有返回值
- call()方法可以抛出异常,被外面的操作捕获，获取异常的信息
- Callable支持泛型的返回值
- 需要借助FutureTask类，比如获取返回结果

**Future接口**
-  可以对具体Runnable、Callable任务的执行结果进行取消、查询是否完成、获取结果等。
- FutrueTask是Futrue接口的唯一的实现类
- FutureTask 同时实现了Runnable, Future接口。它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值

**使用：**

1. 创建一个Callable接口的实现类
2. 实现call()方法，将此线程需要执行的操作声明在call()方法中
3. 创建一个Callable接口实现类的对象
4. 将Callable接口实现类的对象传递到FutureTask构造器中，创建FutureTask的对象
5. 将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()方法启动线程
6. （可选）获取callable中的call方法的返回值 

**例**

```java
public class TreadNew {
    public static void main(String[] args) {
//      3. 创建一个Callable接口实现类的对象
        NumTread numTread = new NumTread();
//      4. 将Callable接口实现类的对象传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask futureTask = new FutureTask(numTread);
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(numTread); //支持泛型
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
class NumTread implements Callable {
    //class NumTread implements Callable<Integer> { //支持泛型
    //    2. 实现call()方法，将此线程需要执行的操作声明在call()方法中
    @Override
//    public Integer call() throws Exception {//支持泛型
    public Object call() throws Exception {
        int sum = 0; //自动装箱
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}
```

### 新增方式二：使用线程池

背景：经常创建和销毁、使用量特别大的资源，比如并发情况下的线程，对性能影响很大。

思路：提前创建好多个线程，放入线程池中，使用时直接获取，使用完放回池中。可以避免频繁创建销毁、实现重复利用。

好处：

- 提高响应速度（减少了创建新线程的时间）
- 降低资源消耗（重复利用线程池中线程，不需要每次都创建）
- 便于线程管理
   - corePoolSize：核心池的大小
   - maximumPoolSize：最大线程数
   - keepAliveTime：线程没有任务时最多保持多长时间后会终止
   -  ...
   
**例**

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
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

class NumberThread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
```

#### 线程池相关API

- JDK 5.0起提供了线程池相关API：ExecutorService 和 Executors

**ExecutorService：真正的线程池接口。常见子类ThreadPoolExecutor**

-  void execute(Runnable command) ：执行任务/命令，没有返回值，一般用来执行Runnable
   - `<T> Future<T> submit(Callable<T> task)`：执行任务，有返回值，一般又来执行Callable
- void shutdown() ：关闭连接池

**Executors：工具类、线程池的工厂类，用于创建并返回不同类型的线程池**

- Executors.newCachedThreadPool()：创建一个可根据需要创建新线程的线程池
- Executors.newFixedThreadPool(n); 创建一个可重用固定线程数的线程池
- Executors.newSingleThreadExecutor() ：创建一个只有一个线程的线程池
- Executors.newScheduledThreadPool(n)：创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。

# Java常用类

##  字符串相关的类：String

- String类：代表字符串。Java 程序中的所有字符串字面值（如 "abc" ）都作为此类的实例实现。
- String是一个final类，代表不可变的字符序列。（不可变性）
   - 字符串是常量，用双引号引起来表示。它们的值在创建之后不能更改。
- String实现了Serializable接口：表示字符串是支持序列化的。
- String实现了Comparable接口：表示String可以比较大小。
- String对象的字符内容是存储在一个字符数组value[]中的。
   - String内部定义了final char[] value用于存储字符串数据 
- 字符串常量池中是不会存储相同内容(即使用String的equals方法返回true)的字符串的。

**String不可变性的体现**

1. 当字符串重新赋值时，需要重新指定内存区域赋值，不能使用原有的value进行赋值
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/245100421227353.png =562x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/11970921247519.png =564x)
2. 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
3. 当调用String的replace()方法修改指定的字符或字符串时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/185481721240188.png =551x)

```java
@Test
public void test(){
    String s1 = "abc";//字面量的定义方式
    String s2 = "abc";

    System.out.println(s1 == s2); //s1和s2指向同一个对象
    //true

    s1 = "hello";

    System.out.println(s1 == s2); //false

    System.out.println(s1);
    System.out.println(s2);

    String s3 = "abc";
    s3 += "def";
    System.out.println(s3);
    System.out.println(s3 == s2);//true

    String s4 = "abc";
    String s5 = s4.replace('a','m');
    System.out.println(s4);
    System.out.println(s5);
}
```

### String对象的创建

- String str = "hello";
- String s1 = new String();    
   - //本质上this.value = new char[0];
- String s2 = new String(String original); 
   - //this.value = original.value;
- String s3 = new String(char[] a); 
   - //this.value = Arrays.copyOf(value, value.length);
- String s4 = new String(char[] a,int startIndex,int count);

### String的实例方式

1. 方式1：通过字面量定义的方式
2. 方式2：通过new + 构造器的方式

**String s = new String("abc")；方式创建对象，在内存中创建了几个对象？**
- 两个：一个堆空间中的new结构，一个char[]对应的常量池中的数据"abc".

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/377974621232497.png =560x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/350640322245483.png =549x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/207800822226724.png =556x)

```java
@Test
public void test2() {
//      通过字面量定义的方式：此时的s1和s2的数据javaEE声明在方法区中的字符串常量池中。
    String s1 = "javaEE";
    String s2 = "javaEE";
//      通过new + 构造器的方式：此时的s3和s4保存的地址值，是数据在堆空间开辟空间以后对应的地址值。
    String s3 = new String("javaEE");
    String s4 = new String("javaEE");

    System.out.println(s1 == s2);//true
    System.out.println(s1 == s3);//false
    System.out.println(s1 == s4);//false
    System.out.println(s3 == s4);//false

    Person p1 = new Person("Tom",12);
    Person p2 = new Person("Tom",12);
    System.out.println(p1.name == p2.name);//true
    Person p3 = new Person(new String("Tom"),12);
    Person p4 = new Person(new String("Tom"),12);
    System.out.println(p1.name == p3.name);//false
    System.out.println(p3.name == p4.name);//false
}
```

### 字符串的特性

- 常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量。
- 只要其中有一个是变量，结果就在堆中
- 如果拼接的结果调用`intern()方法`，返回值就在常量池中

```java
@Test
public void test3(){
    String s1 = "javaEE";
    String s2 = "hadoop";

    //字面量：s3,s4都在常量池内
    String s3 = "javaEEhadoop";
    String s4 = "javaEE" + "hadoop";
    //变量：堆空间中开辟
    String s5 = s1 + "hadoop";
    String s6 = "javaEE" + s2;
    String s7 = s1 + s2;

    System.out.println(s3 == s4); //true
    System.out.println(s3 == s5); //false
    System.out.println(s3 == s6); //false
    System.out.println(s3 == s7); //false

    System.out.println(s4 == s5); //false
    System.out.println(s4 == s6); //false
    System.out.println(s4 == s7); //false

    System.out.println(s5 == s6); //false
    System.out.println(s5 == s7); //false

    System.out.println(s6 == s7); //false

    String s8 = s5.intern();
    //返回值得到的s8是使用常量池中已经存在的"javaEEhadoop"
    System.out.println(s3 == s8); //true
}
```

```java
@Test
public void test1() {
    String s1 = "javaEEhadoop";
    final String s2 = "javaEE";
    String s3 = s2 + "hadoop";
    System.out.println(s1 == s3); //true
// 常量与常量的拼接结果在常量池。
}
```

#### String陷阱

- String s1 = "a"; 
  - 说明：在字符串常量池中创建了一个字面量为"a"的字符串。
- s1 = s1 + "b"; 
  - 说明：实际上原来的“a”字符串对象已经丢弃了，现在在堆空间中产生了一个字符串s1+"b"（也就是"ab")。如果多次执行这些改变串内容的操作，会导致大量副本字符串对象存留在内存中，降低效率。如果这样的操作放到循环中，会极大影响程序的性能。
- String s2 = "ab";
  - 说明：直接在字符串常量池中创建一个字面量为"ab"的字符串。
- String s3 = "a" + "b";
  - 说明：s3指向字符串常量池中已经创建的"ab"的字符串。
- String s4 = s1.intern();
  - 说明：堆空间的s1对象在调用intern()之后，会将常量池中已经存在的"ab"字符串赋值给s4。

#### 面试题

```java
public class StringTest {
    String str = new String("good");
    char[] ch = {'t', 'e', 's', 't'};

    public void change(String str, char ch[]) {
        str = "test ok";
        ch[0] = 'b';
    }

    public static void main(String[] args) {
        StringTest ex = new StringTest();
        ex.change(ex.str, ex.ch);
        System.out.println(ex.str);//good
        //String的不可变性
        System.out.println(ex.ch);//best
    }
}
```

### String的方法

**1.常用**

- int length()：返回字符串的长度： return value.length
- char charAt(int index)： 返回某索引处的字符return value[index]
- boolean isEmpty()：判断是否是空字符串：return value.length == 0
- String toLowerCase()：使用默认语言环境，将 String 中的所有字符转换为小写
- String toUpperCase()：使用默认语言环境，将 String 中的所有字符转换为大写
- String trim()：返回字符串的副本，忽略前导空白和尾部空白
- boolean equals(Object obj)：比较字符串的内容是否相同
- boolean equalsIgnoreCase(String anotherString)：与equals方法类似，忽略大小写
- String concat(String str)：将指定字符串连接到此字符串的结尾。 等价于用“+”
- int compareTo(String anotherString)：比较两个字符串的大小
- String substring(int beginIndex)：返回一个新的字符串，它是此字符串的从beginIndex开始截取到最后的一个子字符串。
- String substring(int beginIndex, int endIndex) ：返回一个新字符串，它是此字符串从beginIndex开始截取到endIndex(不包含)的一个子字符串。

**2.前缀后缀**

- boolean endsWith(String suffix)：测试此字符串是否以指定的后缀结束
- boolean startsWith(String prefix)：测试此字符串是否以指定的前缀开始
- boolean startsWith(String prefix, int toffset)：测试此字符串从指定索引开始的子字符串是否以指定前缀开始

**3.索引（下标）位置**

- boolean contains(CharSequence s)：当且仅当此字符串包含指定的 char 值序列时，返回 true
- int indexOf(String str)：返回指定子字符串在此字符串中第一次出现处的索引
- int indexOf(String str, int fromIndex)：返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始
- int lastIndexOf(String str)：返回指定子字符串在此字符串中最右边出现处的索引
- int lastIndexOf(String str, int fromIndex)：返回指定子字符串在此字符串中最后一次出现处的索引，从指定的索引开始反向搜索
     - 注：indexOf和lastIndexOf方法如果未找到都是返回-1
     - indexOf(str)和lastIndexOf(str)返回值相同的情况:
        - 存在唯一的一个str
        - 不存在str

**4.正则**

- String replace(char oldChar, char newChar)：返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的。
- String replace(CharSequence target, CharSequence replacement)：使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串。
- String replaceAll(String regex, String replacement) ： 使用给定的replacement 替换此字符串所有匹配给定的正则表达式的子字符串。
- String replaceFirst(String regex, String replacement) ： 使用给定的replacement 替换此字符串匹配给定的正则表达式的第一个子字符串。
- boolean matches(String regex)：告知此字符串是否匹配给定的正则表达式。
- String[] split(String regex)：根据给定正则表达式的匹配拆分此字符串。
- String[] split(String regex, int limit)：根据匹配给定的正则表达式来拆分此字符串，最多不超过limit个，如果超过了，剩下的全部都放到最后一个元素中。

```java
public class StringMethodTest {
    @Test
    public void test1() {
        String s1 = "hellpworld";
        System.out.println(s1.length()); //10
        System.out.println(s1.charAt(0)); // h
        System.out.println(s1.charAt(9)); //d
//        s1 = "";
//        System.out.println(s1.isEmpty()); //true

//        s1.toLowerCase(); s1.toUpperCase() s1不可变，仍然为原来的字符串。
        System.out.println(s1.toLowerCase());
        String s2 = s1.toUpperCase();
        System.out.println(s1);  // s1不可变，仍然为原来的字符串
        System.out.println(s2);

        System.out.println(s2.equals(s1)); //false
        //忽略大小写的equals()
        System.out.println(s2.equalsIgnoreCase(s1)); //true

        String s3 = "  he llo   w o rld   ";
        String s4 = s3.trim();//去除首尾的空格
        System.out.println("-" + s3 + "-"); //-  he llo   w o rld   -
        System.out.println("-" + s4 + "-"); //-he llo   w o rld-

        String s5 = "abc";
        //连接字符串
        String s6 = s5.concat("def");
        System.out.println(s6); //abcdef

        String s7 = "abc";
        String s8 = new String("bda");
//        compareTo()字符串做加减法 //涉及到字符串的排序
//        依照ASCII码的加减：a=97,b=98,c=99,d=100;
//        "abc".compareTo("bda"); (97+98+99) - (98+100+97) == -1;
        System.out.println(s7.compareTo(s8)); //-1

        String s9 = "天下第一，举世无二";
        //substring(n) 截取从下标n(包括n)开始一直到所有的字符
        System.out.println(s9.substring(2));
        //substring(n,m) 截取从下标n(包括n)开始一直到下标m(不包括m)的字符
        System.out.println(s9.substring(2,3));
    }

    @Test
    public void test2(){
        String str1 = "helloworld";

        boolean b1 = str1.endsWith("ld");
        System.out.println(b1); //true

        boolean b2 = str1.startsWith("He");
        System.out.println(b2); //false

        boolean b3 = str1.startsWith("ll",2);
        //从下标2开始的对应字符串是否匹配
        System.out.println(b3); //true

        String str2 = "wo";
//        s1.contains(s2) s1是否包含s2
        System.out.println(str1.contains(str2)); //true

//        s1.indexOf(s2) s2在s1中第一次出现的下标位置;没有则返回-1
        System.out.println(str1.indexOf("lo")); //3
        System.out.println(str1.indexOf("HHH")); //-1
//        s1.indexOf(s2,n) 从指定的下标n（包含该下标n)开始查找,返回的仍然是整个字符串的下标
        System.out.println(str1.indexOf("lo", 3)); //3
//        s1.lastIndexOf(s1) 从字符串后面开始查找(最后一次)出现的下标
        System.out.println(str1.lastIndexOf("l")); //8
//        s1.lastIndexOf(s1,n) 从指定的下标n（包含该下标n)(仍然是正向的下标)开始反向查找，最后一次出现的下标位置
//        ,返回的仍然是整个字符串的下标
        System.out.println(str1.lastIndexOf("l",8)); //8
    }

    @Test
    public void test3(){
        String str1 = "天下第一，下，举世无二";
//        替换字符串中所有匹配的 一个字符''或字符串""
        String str2 = str1.replace('下','世');

        System.out.println(str1); //天下第一，下，举世无二
        System.out.println(str2); //天世第一，世，举世无二
        System.out.println(str1.replace("天下", "世上")); //世上第一，下，举世无二

        String str01 = "12bhbad2sada3ifb73nn2j9";
//        把字符串中的数字替换成'，'如果结果中开头和结尾有'，'的话去掉，
//        d-数字; +-数量; ^-开头; $-结尾
        String string = str01.replaceAll("\\d+",",").replaceAll("^,|,$","");
        System.out.println(string); //bhbad,sada,ifb,nn,j

        String str02 = "12345";
//        判断石头人字符串中是否全部由数字组成，即由1-n个数字组成
        boolean matches = str02.matches("\\d+");
        System.out.println(matches); //true

        String tel = "1885-0035771";
        // \\d{7,8} 是否是7-8位的数字
        boolean result = tel.matches("1885-\\d{7,8}");
        System.out.println(result);//true

        String str03 = "hello|world|java";
//        切片
        String[] strs = str03.split("\\|");
        for(int i =0;i<strs.length;i++){
            System.out.println(strs[i]);
        }

        System.out.println();

        String str04 = "hellp.world.java";
//        切片
        String[] strs2 = str04.split("\\.");
        for(int i = 0;i<strs2.length;i++){
            System.out.println(strs2[i]);
        }
    }
}
```

### String类与其他结构之间的转换

#### String与基本数据类型/包装类转换

**字符串-->基本数据类型/包装类**

- Integer包装类的`public static int parseInt(String s)`：可以将由“数字”字符组成的字符串转换为整型。  
- 类似地,使用java.lang包中的Byte、Short、Long、Float、Double类调相应的类方法可以将由“数字”字符组成的字符串，转化为相应的基本数据类型。

```
基本数据类型/包装类 变量 = 包装类.parseXxx(字符串);
```

**基本数据类型/包装类-->字符串**

- 调用String类重载的`public String valueOf(int n)`可将int型转换为字符串
- 相应的valueOf(byte b)、valueOf(long l)、valueOf(float f)、valueOf(double d)、valueOf(boolean b)可由参数的相应类型到字符串的转换

```
String 变量 = String.valueOf(基本数据类型/包装类)

或

String 变量 = 基本数据类型/包装类 + "";
```

#### String与char[]之间的转换

**String-->char[]**

1. 调用String的toCharArray()方法;
2. `public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)`：提供了将指定索引范围内的字符串存放到数组中的方法。

```java
@Test
public void test1(){
    String str1 = "abc123";

    char[] chars = str1.toCharArray();
    for(int i =0;i<chars.length;i++){
        System.out.println(chars[i]);
    }
}
```

**char[]-->String**

- 调用String的构造器`new String(chars)`即可

```java
public void test2(){
    char chars[] = new char[]{'h','e','l','l','o'};

    String str = new String(chars);
    System.out.println(str); //hello
}
```

#### String与byte[]之间的转换(字节数组)

- 编码: 字符串-->字节(二级制数据)
- 解码: 字节-->字符串 编码的逆过程

**Sting-->byte[] 编码**

- bytes[] 字节数组 = 字符串.getBytes("指定编码集");
- 需要该方法引入throws UnsupportedEncodingException 

**byte[]-->String 解码**

- String 字符串 = new String(字节数组,"指定编码集");

- 解码时使用的字符集必须与编码时使用的字符集相一致
  - 否则会出现乱码

```java
   @Test
    public void test4() throws UnsupportedEncodingException {
        String str1 = "abc123";

//      ----编码
        byte[] bytes = str1.getBytes(); //使用默认的字符集UTF-8，进行转换
//        ASCII码
        System.out.println(Arrays.toString(bytes));
//        [97, 98, 99, 49, 50, 51]

        String str2 = "abc123中国";
//        String str1 = "abc123中国"; 需要指定字符集
        byte[] bytes2 = str2.getBytes();
//        [97, 98, 99, 49, 50, 51, -28, -72, -83, -27, -101, -67] UTF-8中一个中文三个字节
        //使用指定的字符集，进行转换
//        需要throws UnsupportedEncodingException;
        byte[] gbks = str2.getBytes("gbk");
        //使用gbk字符集进行编码
//          编码: 字符串-->字节(二级制数据)
//          解码: 字节-->字符串 编码的逆过程
        System.out.println(Arrays.toString(gbks));
//        [97, 98, 99, 49, 50, 51, -42, -48, -71, -6]

//        ----解码
        String string = new String(bytes2); //使用默认的字符集解码
        System.out.println(string); //abc123中国

        String str3 = new String(gbks);
//        编码解码使用的字符集不一致 乱码
        System.out.println(str3); //abc123�й�
//        指定解码的字符集
        String str4 = new String(gbks, "gbk");
        System.out.println(str4); //abc123中国
    }
```

### 常见算法题目

#### 1. 模拟一个trim方法，去除字符串两端的空格。

```java
public class StringTrimTest {
    public static void main(String[] args) {
        String str1 = "   sad   test   ";
        String str2 = new String(myTrim(str1));

        System.out.println("-" + str1 + "-");
        System.out.println("-" + str2 + "-");
    }

    public static char[] myTrim(String str) {
        //如果空字符串则：
        if(str.isEmpty() == true){
            throw new RuntimeException("空字符串");
        }

        //获取str字符串，并转换为char[]
        char[] copyChars = str.toCharArray();
        //暂存copyChars中的元素（去除空格后，但是存在未赋值的元素）
        char[] intoChars = new char[str.length()];
        int counter = 0; //计数，用来充当returnChar的长度

        //去除空格
        for (int i = 0; i < str.length(); i++) {
            //空格的ASCII码值是32
            if (str.charAt(i) == 32) {
                continue;
            } else {
                intoChars[counter++] = copyChars[i];
            }
        }

        //最终为返回的字符串赋值的char[]
        char[] returnChars = new char[counter];

        //将intoChars的元素放入returnChars
        for (int i = 0; i < returnChars.length; i++) {
            returnChars[i] = intoChars[i];
        }

        return returnChars;
    }
}
```

- 正则切片的方式


#### 2. 将一个字符串进行反转。将字符串中指定部分进行反转。

- 比如“abcdefg”反转为”abfedcg”

```java
public String reverse(String str, int startIndex, int endIndex) {
    if (str != null && str.length() != 0) {
        char[] arr = str.toCharArray();
        for (int x = startIndex, y = endIndex; x < y; x++, y--) {
            char temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
        }
        return new String(arr);
    }
    return null;
}
```

```java
public class StringFlipTest {
    public static void main(String[] args) {
        String str1 = "abcdefg";
        String str2 = new String(flip(str1, 2, 5));
        System.out.println(str2);
    }

    public static char[] flip(String str, int from, int to) {
        //如果空字符串则：
        if (str.isEmpty() == true) {
            throw new RuntimeException("空字符串");
        }

        //如果from>to
        if (from > to) {
            throw new RuntimeException("from的值不能大于to");
        }

        char[] chars = str.toCharArray();
        while (from < to) {
            char temp = chars[to];
            chars[to] = chars[from];
            chars[from] = temp;

            from++;
            to--;
        }

        return chars;
    }
}
```

```java
public String reverse2(String str, int startIndex, int endIndex) {
    if (str != null && str.length() != 0) {
//        第一部分
        String reverseStr = str.substring(0, startIndex);
//        第二部分
        for (int i = endIndex; i >= startIndex; i--) {
            reverseStr += str.charAt(i);
        }
//        第三部分
        reverseStr += str.substring(endIndex + 1);
        return reverseStr;
    }
    return null;
}
```

```java
public String reverse3(String str, int startIndex, int endIndex) {
    if (str != null && str.length() != 0) {
        StringBuffer sbf = new StringBuffer(str.length());

        //第一部分
        sbf.append(str.substring(0,startIndex));

        //第二部分
        for (int i = endIndex; i >= startIndex; i--) {
            sbf.append(str.charAt(i));
        }

        //第三部分
        sbf.append(str.substring(endIndex + 1));

        return sbf.toString();
    }
    return null;
}
```

#### 3. 获取一个字符串在另一个字符串中出现的次数。

- 比如：获取“ ab”在 “abkkcadkabkebfkabkskab” 中出现的次数

```sql
public int getCount(String mainStr, String subStr) {
    int mainLength = mainStr.length();
    int subLength = subStr.length();
    int count = 0;
    int index;

    if (subLength < mainLength) {
        while ((index = mainStr.indexOf(subStr)) != -1) {
            count++;
            mainStr = mainStr.substring((index + subStr.length()));
        }
        return count;
    }
    return 0;
}
```

```sql
public int getCount2(String mainStr, String subStr) {
    int mainLength = mainStr.length();
    int subLength = subStr.length();
    int count = 0;
    int index;

    if (subLength < mainLength) {
        while ((index = mainStr.indexOf(subStr, 0)) != -1) {
            count++;
            index += subLength;
        }
        return count;
    }
    return 0;
}
```

```java
public class StringCountTest {
    public static void main(String[] args) {
        String str1 = "abkkcadkabkebfkabkskab";
        String str2 = "ab";

        System.out.println(countRepeat02(str2, str1));
    }

    public static int countRepeat02(String inStr, String totalStr) {
        //空字符串错误：
        if (inStr.isEmpty() || totalStr.isEmpty()) {
            throw new RuntimeException("空字符串错误");
        }

        //计算重复次数
        int counter = 0;

        //切片
        String[] strs = totalStr.split(inStr);
        //计算切片剩下的总长度
        int length = 0;
        for (int i = 0;i<strs.length;i++){
            length += strs[i].length();
        }

        //计算重复次数
        counter = (totalStr.length() - length) / inStr.length();

        return counter;
    }
}
```

#### 4. 获取两个字符串中最大相同子串。比如：str1 = "abcwerthelloyuiodef“;str2 = "cvhellobnm"

- 提示：将短的那个串进行长度依次递减的子串与较长的串比较。

```java
//只能找到第一个
public String getMaxString(String str1, String str2) {

    if(str1 != null && str1 != null){
        String maxString = (str1.length() >= str2.length()) ? str1 : str2;
        String minString = (str1.length() < str2.length()) ? str1 : str2;

        int length = minString.length();

        for (int i = 0; i < length; i++) {
            for (int x = 0, y = length - i; y <= length; x++, y++) {
                String subStr = minString.substring(x, y);
                if (maxString.contains(subStr)) {
                    return subStr;
                }
            }
        }
    }

    return null;
}
```

```java
public String[] getMaxString(String str1, String str2) {

    if (str1 != null && str1 != null) {
        StringBuffer stringBuffer = new StringBuffer();
        String maxString = (str1.length() >= str2.length()) ? str1 : str2;
        String minString = (str1.length() < str2.length()) ? str1 : str2;

        int length = minString.length();

        for (int i = 0; i < length; i++) {
            for (int x = 0, y = length - i; y <= length; x++, y++) {
                String subStr = minString.substring(x, y);
                if (maxString.contains(subStr)) {
                    stringBuffer.append(subStr + ",");
                }
            }

            if(stringBuffer.length() != 0){
                break;
            }
        }

        String[] strings = stringBuffer.toString().replaceAll(",$","").split("\\,");
        return  strings;
    }

    return null;
}
```

#### 5.对字符串中字符进行自然顺序排序。
- 提示：
1. 字符串变成字符数组。
2. 对数组排序，选择，冒泡，Arrays.sort();
3. 将排序后的数组变成字符串。

```java

```

### StringBuffer,StringBuilder

**转换**

- String --> StringBuffer/StringBuilder
   - 调用StringBuffer/StringBuilder构造器
- StringBuffer/StringBuilder --> String
   - 调用String构造器
   - 对应的toString()方法   

**String/StringBuffer/StringBulilder的异同**

- String: 
   - 不可变的字符序列
   - 底层结构使用char[]存储
   - 效率最低
- StringBuffer: 
   - 可变的字符序列
   - 线程安全（sychronized)  效率较低
   - 底层结构使用char[]存储
- StringBuilder:  jdk5.0新增
   - 可变的字符序列
   - 线程不安全  效率较高
   - 底层使用char[]存储
   
```java
public class StringBufferStringBuilderTest {
    @Test
    public void test1(){
        StringBuffer sb1 = new StringBuffer("abc");
        sb1.setCharAt(0,'m');
        System.out.println(sb1); //mbc
    }
}
```
   
**源码分析**

1. StringBuffer的长度问题
2. 扩容问题: 如果要添加的数据，底层数组存不下，那么需要扩容底层的数组。
  - 开发中，建议使用StringBuffer(int capacity) 或StringBuilder(int capacity);指定额外的数组长度(默认16)   
     - 默认情况下扩容为原来的2倍+2，同时将原有数组中的元素复制到新的数组中
     - 如果还是不满足，则直接将该数组作为新的数组。

```java
String str = new String(); //new char[0];
String str1 = new String("abc"); //new char[3]{'a','b','c'};

StringBuffer sb1 = new StringBuffer(); //char[] value = new char[16]; 相当于底层创建了一个长度是16的数组
System.out.println(sb1.length()); //0 而不是16
sb1.append('a'); //value[0] = 'a';
sb1.append('b'); //value[1] = 'b';

StringBuffer sb2 = new StringBuffer("abc"); // char[] value = new char["abc".length() + 16];
System.out.println(sb2.length()); //3 而不是19
```

#### StringBuffer类的常用方法

- StringBuffer append(xxx)：提供了很多的append()方法，用于进行字符串拼接
- StringBuffer delete(int start,int end)：删除指定位置的内容,包括end位置的内容
- StringBuffer replace(int start, int end, String str)：把[start,end)位置替换为str
- StringBuffer insert(int offset, xxx)：在指定位置插入xxx
- StringBuffer reverse() ：把当前字符序列逆转
- public int indexOf(String str)
- public String substring(int start,int end) : 返回[start,end)的字符串
- public int length()
- public char charAt(int n )
- public void setCharAt(int n ,char ch)

```java
@Test
public void test3() {
    StringBuffer s1 = new StringBuffer("abc");

    s1.append(1); //追加
    s1.append('d'); //追加
    System.out.println(s1); //abc1d

    s1.delete(2, 4); //只删除包括起始下标到（不包括）结束下标
    System.out.println(s1); //abd

    s1.replace(1, 2, "hello"); //替换[1,2)的内容
    System.out.println(s1); //ahellod

    s1.insert(2, false); //在指定位置插入内容
    System.out.println(s1); //ahfalseellod
    System.out.println(s1.length()); //12 false也视为字符串

    s1.reverse();
    System.out.println(s1); //dolleeslafha

    String s2 = s1.substring(1, 3);
    System.out.println(s1); //dolleeslafha
    System.out.println(s2); //ol
}
```

#### 方法链的原理

#### 题

```java
@Test
public void test(){
    String str = null;
    StringBuffer sb = new StringBuffer();
    sb.append(str);
    System.out.println(sb.length());//
    System.out.println(sb);//
    StringBuffer sb1 = new StringBuffer(str);
    System.out.println(sb1);//   
}
```

## 日期时间API

### JDK8之前日期时间API

**日期API的迭代**

1. jdk 1.0 Date类
2. jdk 1.1 Calendar类，一定程度上替换Date类
3. jdk 1.8 提出一套新的API

**前两代的问题**

- 可变性：像日期和时间这样的类应该是不可变的。
- 偏移性：Date中的年份是从1900开始的，而月份都从0开始。
- 格式化：格式化只对Date有用，Calendar则不行。
- 此外，它们也不是线程安全的；不能处理闰秒等。

#### java.lang.System类

- System类提供的`public static long currentTimeMillis()`用来返回当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差。
  - 此方法适于计算时间差。
  - 时间戳
  
**计算世界时间的主要标准有：**

- UTC(Coordinated Universal Time)
- GMT(Greenwich Mean Time)
- CST(Central Standard Time

#### java.util.Date类

**表示特定的瞬间，精确到毫秒**

- 构造器：
  - Date()：使用无参构造器创建的对象可以获取本地当前时间。
  - Date(long date)  ： 通过毫秒数（时间戳）获取时间
- 常用方法
  - `getTime()`: 返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此Date 对象表示的毫秒数。
  - `toString()`: 把此 Date 对象转换为以下形式的 String：
      - 格式：
      ![](C:/Users/zjk10/OneDrive/NoteBook/pictures/166405516247526.png =216x)
      
```java
public void test2(){
//    空参构造器：创建当前时间的Date对象
    Date date1 = new Date();
    System.out.println(date1);
//     Wed Oct 26 16:51:02 CST 2022
//     星期 月 日  时间   时间标准 年
    System.out.println(date1.getTime());
//      1666774415833
//       时间戳

//     指定时间的构造器
    Date date2 = new Date(1666774415833L);
    System.out.println(date2);
//     Wed Oct 26 16:53:35 CST 2022
}
```

#### java.sql.Date类 （继承自java.util.Date类)

- 对应数据库中的日期类型的变量

**java.util.Date和java.sql.Date的转换**

```java
public void test3() {
    //创建java.sql.Date的对象
    java.sql.Date date = new java.sql.Date(174242344342L);
    System.out.println(date); //1975-07-11
    //java.sql.Date --> java.util.Date
//     1.
    Date date1 = new java.sql.Date(13819381293819L);
    java.sql.Date date2 = (java.sql.Date)date1;
//     2.
    Date date3 = new Date();
//     java.sql.Date date4 = (java.sql.Date)date3; 报错
    java.sql.Date date4 = new java.sql.Date(date.getTime());
}
```

#### java.text.SimpleDateFormat类

Date类的API不易于国际化，大部分被废弃了，java.text.SimpleDateFormat类是一个不与语言环境有关的方式来格式化和解析日期的具体类。

**对日期Date类的格式化和解析：**

- 实例化SimpleDateFormat
  - 使用默认构造器 
     - `new SimpleDateFormat();`
  - 指定方式格式化和解析 调用带参的构造器:
     - `new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");`
     - 通过API查找SimpleDateFormat类的具体格式设置
     
**格式化：日期-->字符串**

- format()方法
  - `SimpleDateFormat对象.format(Date对象);`

**解析：字符串-->日期  (格式化的逆过程)**

- parse()方法
  - `SimpleDateFormat对象.parse(字符串);` 
  - 要求字符串必须符合SimpleDateFormat识别的格式
  - 需要方法加上`throws ParseException`
  
```java
@Test
public void testSimpleDateFormat() throws ParseException {
    //实例化SimpleDateFormat 使用默认的构造器
    SimpleDateFormat sdf = new SimpleDateFormat();

    Date date = new Date();
    System.out.println(date);
    //Thu Oct 27 22:11:08 CST 2022

    //格式化：日期-->字符串
    String format = sdf.format(date);
    System.out.println(format);
    //2022/10/27 下午10:11

    //解析
    String str = "2022/10/23 上午10:10";
    //格式要求默认："yyyy/MM/dd aaa hh:mm:ss " 不同版本不一样
    Date date1 = sdf.parse(str);
    System.out.println(date1);
    //Sun Oct 23 10:10:00 CST 2022

    //指定方式格式化和解析 调用带参的构造器
    //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    //格式化
    String format1 = sdf1.format(date);
    System.out.println(format1);
    //02022.十月.27 公元 10:20 下午
    //2022-23-27 10:23:55
    //解析: 要求字符串必须符合SimpleDateFormat识别的格式（通过构造器参数实现)
    //否则报错
    Date date2 = sdf1.parse("1999-12-31 23:59:59");
    System.out.println(date2);
    //Sun Jan 31 23:59:59 CST 1999
}
```

##### 练习

###### 练习1

字符串"2020-09-08"转换为java.sql.Date

```java
@Test
public void test2() throws ParseException {
    String birth = "2020-09-08";
    
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = simpleDateFormat.parse(birth);
    
    java.sql.Date date1 = new java.sql.Date(date.getTime());
    System.out.println(date1); //2020-09-08
}
```

###### 练习2

-“三天打鱼两天晒网”从1990-01-01开始，问xxxx-xx-xx打鱼还是晒网?
  - 即：计算 总天数%5
  - 1,2,3 打鱼
  - 4,0 晒网
  
**1.利用时间戳**

```java
public void fishing(String dateStr) throws ParseException {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //开始时间
    Date startDate = simpleDateFormat.parse("1990-01-01");
    //提问的时间
    Date endDate = simpleDateFormat.parse(dateStr);

    //相差时间
    long totalTime = endDate.getTime() - startDate.getTime();
    //当前的天数
    int day = (int) (totalTime / (24 * 60 * 60 * 1000) + 1) % 5;
    //注意抹零的影响
    switch (day) {
        case 1:
        case 2:
        case 3:
            System.out.println("打鱼");
            break;
        case 4:
        case 0:
            System.out.println("晒网");
            break;
    }
}
```

**2.计算天数**

```java

```

#### java.util.Calendar(日历)类

- Calendar是一个抽象基类，主用用于完成日期字段之间相互操作的功能。

**获取Calendar实例的方法**

1. 使用`Calendar.getInstance()`方法
2. 调用它的子类GregorianCalendar的构造器。
   - 一个Calendar的实例是系统时间的抽象表示，通过`get(int field)`方法来取得想要的时间信息。
   - 比如YEAR、MONTH、DAY_OF_WEEK、HOUR_OF_DAY 、MINUTE、SECOND

具体field查看API

- public int get(int field)
  - 获取日历 
- public void set(int field,int value)
  - 设置时间  
- public void add(int field,int amount) 
  - 增加或减少时间 , 负数减 
- public final Date getTime()
  - 日历类-->Date类 
- public final void setTime(Date date)
  - Date类-->日历类 

**注意:**

- 获取月份时：一月是0，二月是1，... ，12月是11
- 获取星期时：周日是1，周二是2 ... 周六是7

```java
@Test
public void testCalendar(){
//1.实例化
    //方式一：创建子类GregorianCalendar的对象
    //方式二：调用其静态方法getInstance()
    Calendar calendar = Calendar.getInstance();
    //System.out.println(calendar.getClass());
    //class java.util.GregorianCalendar 仍然来自于其子类

//2.常用方法
//get()
    int days = calendar.get(Calendar.DAY_OF_MONTH);
    //get(Calendar.该类内部的静态属性)  具体查看API
    System.out.println(days); //返回本个月的第几天
    System.out.println(calendar.get(Calendar.DATE)); //返回-日
//set()
    calendar.set(Calendar.DAY_OF_MONTH,22);
    //修改calendar对象内的属性 //Calendar类的属性不变：不可变性
    days = calendar.get(Calendar.DAY_OF_MONTH);
    System.out.println(days); //22 返回修改后的日期
//add()
    calendar.add(Calendar.DAY_OF_MONTH,3); //负数则减
    //增加3天
    days = calendar.get(Calendar.DAY_OF_MONTH);
    System.out.println(days); //25 返回增加后的日期
    calendar.add(Calendar.DAY_OF_MONTH,-1); //负数则减
    //减1天
    days = calendar.get(Calendar.DAY_OF_MONTH);
    System.out.println(days); //24 返回减后的日期
//getTime() 日历类-->Date类
    Date time = calendar.getTime();
    System.out.println(time);
    //Mon Oct 24 23:29:51 CST 2022
//setTime() Date类-->日历类
    Date date = new Date();
    calendar.setTime(date);
    System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
    //27
}
```

### JDK8中新日期时间API

- java.time – 包含值对象的基础包
- java.time.chrono – 提供对不同的日历系统的访问
- java.time.format – 格式化和解析时间和日期
- java.time.temporal – 包括底层框架和扩展特性
- java.time.zone – 包含时区支持的类

#### LocalDate、LocalTime、LocalDateTime

LocalDate、LocalTime、LocalDateTime 类是其中较重要的几个类，它们的实例是不可变的对象，分别表示使用 ISO-8601日历系统的日期、时间、日期和时间。它们提供了简单的本地日期或时间，并不包含当前的时间信息，也不包含与时区相关的信息

**不可变的对象**

- LocalDate代表IOS格式（yyyy-MM-dd）的日期,可以存储 生日、纪念日等日期。

- LocalTime表示一个时间，而不是日期。
- LocalDateTime是用来表示日期和时间的，这是一个最常用的类之一
   - LocalDateTime使用频率更高 
   
**方法**

- now() / * now(ZoneId zone) 
    - 静态方法，根据当前时间创建对象/指定时区的对象
- of() 静态方法，根据指定日期/时间创建对象
- getDayOfMonth()/getDayOfYear()
   -  获得月份天数(1-31) /获得年份天数(1-366)
- getDayOfWeek() 获得星期几(返回一个 DayOfWeek 枚举值)
- getMonth() 
   - 获得月份, 返回一个 Month 枚举值
- getMonthValue() / getYear() 
   - 获得月份(1-12) /获得年份
- getHour()/getMinute()/getSecond() 
   - 获得当前对象对应的小时、分钟、秒
- withDayOfMonth()/withDayOfYear()/withMonth()/withYear() 
   - 将月份天数、年份天数、月份、年份修改为指定的值并返回新的对象
- plusDays(), plusWeeks(), plusMonths(), plusYears(),plusHours() 
   - 向当前对象添加几天、几周、几个月、几年、几小时
- minusMonths() / minusWeeks()/minusDays()/minusYears()/minusHours() 
   - 从当前对象减去几月、几周、几天、几年、几小时

```java
@Test
public void test1(){
    //now() 获取当前时间
    LocalDate localDate = LocalDate.now();
    LocalTime localTime = LocalTime.now();
    LocalDateTime localDateTime = LocalDateTime.now();

    System.out.println(localDate);
    System.out.println(localTime);
    System.out.println(localDateTime);
    //2022-10-28
    //15:54:10.480256600
    //2022-10-28T15:54:10.480256600

    //of() 设置指定的年月日时分秒时，没有偏移量
    LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 6, 13, 23, 13);
    System.out.println(localDateTime1);
    //2020-10-06T13:23:13

    //getXxx() 获取日期属性
    System.out.println(localDateTime.getDayOfMonth());
    System.out.println(localDateTime.getDayOfWeek());
    System.out.println(localDateTime.getDayOfYear());
    System.out.println(localDateTime.getMinute());
    //28
    //FRIDAY
    //301
    //28

//体现不可变性
    //withXxx() 修改
    LocalDate localDate1 = localDate.withDayOfMonth(22);
    System.out.println(localDate);
    System.out.println(localDate1);
    //2022-10-28
    //2022-10-22

    LocalDateTime localDateTime2 = localDateTime.withHour(4);
    System.out.println(localDateTime);
    System.out.println(localDateTime2);
    //2022-10-28T16:34:05.769278400
    //2022-10-28T04:34:05.769278400

    //plusXxx() 添加
    LocalDateTime localDateTime3 = localDateTime.plusYears(3);
    System.out.println(localDateTime);
    System.out.println(localDateTime3);
    //2022-10-28T16:35:34.425305800
    //2025-10-28T16:35:34.425305800
}
```

#### Instant类

- 类似于Java.util.Date类

Instant：时间线上的一个瞬时点。 这可能被用来记录应用程序中的事件时间戳。

- java.time包通过值类型Instant提供机器视图。Instant表示时间线上的一点，而不需要任何上下文信息，
    - 例如，时区。概念上讲，它只是简单的表示自1970年1月1日0时0分0秒（UTC）开始的秒数。
    - 因为java.time包是基于纳秒计算的，所以Instant的精度可以达到纳秒级。 
       -  (1 ns = 10-9 s) 1秒 = 1000毫秒 =10^6微秒=10^9纳秒

**方法**

- now()
   - 静态方法，返回默认UTC时区的Instant类的对象
- ofEpochMilli(long epochMilli) 
   - 静态方法，返回在1970-01-01 00:00:00基础上加上指定毫秒数之后的Instant类的对象
- atOffset(ZoneOffset offset) 
   - 结合即时的偏移来创建一个 OffsetDateTime
- toEpochMilli() 
   - 返回1970-01-01 00:00:00到当前时间的毫秒数，即为时间戳

```java
@Test
public void test(){
    Instant instant = Instant.now();
    System.out.println(instant);
    //2022-10-28T08:40:37.774837200Z

    //添加时间的偏移
    OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
    System.out.println(offsetDateTime);
    //2022-10-28T16:42:42.421531800+08:00

    //toEpochMilli() 获取自1970年1月1日0时0分0秒（UTC）开始的毫秒数
    long milli = instant.toEpochMilli();
    System.out.println(milli);
    //1666946757201

    //ofEpochmilli() 通过给定的毫秒数
    Instant instant1 = instant.ofEpochMilli(12312312312L);
    System.out.println(instant1);
    //1970-05-23T12:05:12.312Z
}
```

#### 格式化与解析日期或时间

**java.time.format.DateTimeFormatter 类：**

类似于SimpleDateFormat类

**该类提供了三种格式化方法：**

1. 预定义的标准格式。
   - ISO_LOCAL_DATE_TIME
   - ISO_LOCAL_DATE
   - ISO_LOCAL_TIME
2. 本地化相关的格式。
   - 如：ofLocalizedDateTime(FormatStyle.LONG)
3. 自定义的格式。
   - 如：ofPattern(“yyyy-MM-dd hh:mm:ss”)

**方法**

- ofPattern(String pattern) 
   - 静态方法 ， 返 回 一 个 指 定 字 符 串 格 式 的
- DateTimeFormatterformat(TemporalAccessor t)
   - 格式化一个日期、时间，返回字符串
- parse(CharSequence text) 
   - 将指定格式的字符序列解析为一个日期、时间

```java
@Test
public void test3(){
//方式1预定义的标准格式。
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
      //格式化 日期-->字符串
    LocalDateTime localDateTime = LocalDateTime.now();
    String str1 = dateTimeFormatter.format(localDateTime);
    dateTimeFormatter.format(localDateTime);
    System.out.println(str1);
    System.out.println(localDateTime);
      //2022-10-28T17:00:23.5674663
      //2022-10-28T17:00:23.567466300
      //解析 字符串-->日期
    TemporalAccessor parse = dateTimeFormatter.parse("2019-02-18T17:00:23.22222");
    System.out.println(parse);
       //{},ISO resolved to 2019-02-18T17:00:23.222220
//方式2本地化相关的格式。
    DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
    //格式化
    String format = dateTimeFormatter1.format(localDateTime);
    System.out.println(format);
    //2022/10/28 下午5:04

    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
    //格式化
    String format1 = formatter.format(LocalDate.now());
    System.out.println(format1);
    //2022年10月28日星期五

//方式3自定义的格式。
    DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    //格式化
    String str = dateTimeFormatter2.format(LocalDateTime.now());
    System.out.println(str);
    //2022-10-28 05:10:10

    //解析
    TemporalAccessor parse1 = dateTimeFormatter2.parse("2019-10-23 12:12:23");
    System.out.println(parse1);
    //{MilliOfSecond=0, HourOfAmPm=0, NanoOfSecond=0, SecondOfMinute=23, MinuteOfHour=12, MicroOfSecond=0},ISO resolved to 2019-10-23
}
```

#### 其它API

**ZoneId：**

- 该类中包含了所有的时区信息，一个时区的ID，如 Europe/Paris

**ZonedDateTime：**

- 一个在ISO-8601日历系统时区的日期时间，
   - 如 2007-12-03T10:15:30+01:00 Europe/Paris。
-  其中每个时区都对应着ID，地区ID都为“{区域}/{城市}”的格式，
    - 例如：Asia/Shanghai等

**Clock：**

- 使用时区提供对当前即时、日期和时间的访问的时钟。
-  持续时间：Duration，用于计算两个“时间”间隔
- 日期间隔：Period，用于计算两个“日期”间隔

**TemporalAdjuster**
 
- 时间校正器。有时我们可能需要获取例如：将日期调整到“下一个工作日”等操作。

**TemporalAdjusters :** 

- 该类通过静态方法(`firstDayOfXxx()/lastDayOfXxx()/nextXxx()`)提供了大量的常用TemporalAdjuster 的实现。

## Java比较器

Java中的对象，正常情况下，只能进行比较 == 或  ！= 不能使用>或<，但是在开发场景中，需要对多个对象进行排序时。

### 自然排序 java.lang.Comparable接口

- String和包装类等实现了Comparable接口，重写了compareTo()方法，给出了比较两个对象大小的方法
- String/包装类等重写了compareTo()之后，进行了从大到小的排序

**自然排序**

- 对于自定义类，如果需要排序，可以让自定义类实现Comparable接口，重写comparaTo()方法。在compareTo()中指明如何排序

**重写compareTo()的规则:**

- 如果当前对象this大于形参对象obj，则返回正整数，
- 如果当前对象this小于形参对象obj，则返回负整数，
- 如果当前对象this等于形参对象obj，则返回零。

```sql
public class CompareTest {

    @Test
    public void test1(){
        String[] arr = new String[]{"AA","CC","MM","GG","JJ","DD","KK"};

        Arrays.sort(arr);
        //实现了Comparable接口
        System.out.println(Arrays.toString(arr));
        //[AA, CC, DD, GG, JJ, KK, MM]
    }

    @Test
    public void test2(){
        Goods[] arr = new Goods[4];
        arr[0] = new Goods("联想",199);
        arr[1] = new Goods("华硕",100);
        arr[2] = new Goods("星际",100);
        arr[3] = new Goods("盛大",2321);

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        //[Goods{name='星际', price=99.0}, Goods{name='华硕', price=100.0}, Goods{name='联想', price=199.0}, Goods{name='盛大', price=2321.0}]
    }
}
```

```java
public class Goods implements Comparable {
    private String name;
    private double price;

    public Goods() {
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

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

### 定制排序 java.util.Comparator接口

当元素的类型没有实现java.lang.Comparable接口而又不方便修改代码，或者实现了java.lang.Comparable接口的排序规则不适合当前的操作，那么可以考虑使用 Comparator 的对象来排序，强行对多个对象进行整体排序的比较。

**重写写compare(Object o1,Object o2)方法规则**

- 返回正整数，则表示o1大于o2；
- 返回0，表示相等；
- 返回负整数，表示o1小于o2。

```java
@Test
public void test3() {
    Goods[] arr = new Goods[5];
    arr[0] = new Goods("联想", 199);
    arr[1] = new Goods("华硕", 100);
    arr[2] = new Goods("星际", 100);
    arr[3] = new Goods("盛大", 2321);
    arr[4] = new Goods("盛大", 221);

    Arrays.sort(arr, new Comparator() {
        //指明Goods的排序，按产品名称从低到高排序，再按照价格从高到低排序
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
    System.out.println(Arrays.toString(arr));
    //[Goods{name='星际', price=99.0}, Goods{name='华硕', price=100.0}, Goods{name='联想', price=199.0}, Goods{name='盛大', price=2321.0}]

}

@Test
public void test4() {
    String[] arr = new String[]{"AA", "CC", "MM", "GG", "JJ", "DD", "KK"};
    Arrays.sort(arr, new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            if(o1 instanceof String && o2 instanceof String){
                String s1 = (String) o1;
                String s2 = (String) o2;
                
                return -s1.compareTo(s2);
            }
            
            throw new RuntimeException("数据类型不一致");
        }
    });
    System.out.println(Arrays.toString(arr));
    //[MM, KK, JJ, GG, DD, CC, AA]
}
```

### 对比

- Comparable接口的方式一旦确定`compareTo()`，保证Comparable接口实现类的对象在任何位置都可以比较大小
- Comparator接口属于临时性的比较

## System类

System类代表系统，系统级的很多属性和控制方法都放置在该类的内部。该类位于java.lang包。

- 由于该类的构造器是private的，所以无法创建该类的对象，也就是无法实例化该类。
- 其内部的成员变量和成员方法都是static的，所以也可以很方便的进行调用。

**成员变量**

- System类内部包含in、out和err三个成员变量，分别代表标准输入流(键盘输入)，标准输出流(显示器)和标准错误输出流(显示器)。

**成员方法**

- `native long currentTimeMillis()`：
   - 该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数。
- `void exit(int status)`：
   - 该方法的作用是退出程序。
   - 其中status的值为0代表正常退出，非零代表异常退出。
   - 使用该方法可以在图形界面编程中实现程序的退出功能等。
- `void gc()`:
   - 该方法的作用是请求系统进行垃圾回收。
   - 至于系统是否立刻回收，则取决于系统中垃圾回收算法的实现以及系统执行时的情况。
- `String getProperty(String key)`：
   - 该方法的作用是获得系统中属性名为key的属性对应的值。
   - 系统中常见的属性名以及属性的作用如下表所示：
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/89133321239495.png =422x)


```java
@Test
public void test1() {
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

}
```

## Math类

java.lang.Math提供了一系列静态方法用于科学计算。其方法的参数和返回值类型一般为double型。

- abs 
   - 绝对值
- acos,asin,atan,cos,sin,tan 
   - 三角函数
- sqrt   
   - 平方根
- pow(double a,doble b) 
   - a的b次幂
- log 
   - 自然对数
- exp
   -  e为底指数
- max(double a,double b)
- min(double a,double b)
- random() 
   - 返回0.0到1.0的随机数
- long round(double a) 
   - double型数据a转换为long型（四舍五入）
- toDegrees(double angrad) 
   - 弧度—>角度
- toRadians(double angdeg) 
  - 角度—>弧度

## BigInteger与BigDecimal

### BigInteger类

- Integer类作为int的包装类，能存储的最大整型值为2^31-1，Long类也是有限的，最大为2^63-1。
     - 如果要表示再大的整数，不管是基本数据类型还是他们的包装类都无能为力，更不用说进行运算了。
- java.math包的BigInteger可以表示不可变的任意精度的整数。
- BigInteger 提供所有 Java 的基本整数操作符的对应物，并提供 java.lang.Math 的所有相关方法。
- 另外，BigInteger 还提供以下运算：模算术、GCD 计算、质数测试、素数生成、位操作以及一些其他操作。

**构造器**

- `BigInteger(String val)`：
   - 根据字符串构建BigInteger对象

**方法**

- public BigInteger abs()：
   - 返回此 BigInteger 的绝对值的 BigInteger。
- BigInteger add(BigInteger val) ：
   - 返回其值为 (this + val) 的 BigInteger
- BigInteger subtract(BigInteger val) ：
   - 返回其值为 (this - val) 的 BigInteger
- BigInteger multiply(BigInteger val) ：
   - 返回其值为 (this * val) 的 BigInteger
- BigInteger divide(BigInteger val) ：  
   - 返回其值为 (this / val) 的 BigInteger。整数相除只保留整数部分。
- BigInteger remainder(BigInteger val) ：
   - 返回其值为 (this % val) 的 BigInteger。
- BigInteger[] divideAndRemainder(BigInteger val)：
   - 返回包含 (this / val) 后跟(this % val) 的两个 BigInteger 的数组。
- BigInteger pow(int exponent) ：
   - 返回其值为 (thisexponent) 的 BigInteger。

### BigDecimal类

- 一般的Float类和Double类可以用来做科学计算或工程计算，但在商业计算中，要求数字精度比较高，故用到java.math.BigDecimal类。
- BigDecimal类支持不可变的、任意精度的有符号十进制定点数。

**构造器**

- `public BigDecimal(double val)`
- `public BigDecimal(String val)`

**方法**

- public BigDecimal add(BigDecimal augend)
- public BigDecimal subtract(BigDecimal subtrahend)
- public BigDecimal multiply(BigDecimal multiplicand)
- public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)

```java
@Test
public void test2() {
    BigInteger bi = new BigInteger("2313123232323312223123122222222");
    BigDecimal bd = new BigDecimal("1213123.12312312312");
    BigDecimal bd2 = new BigDecimal("23");
    System.out.println(bd.divide(bd2, BigDecimal.ROUND_HALF_DOWN));
    //52744.48361404883
    System.out.println(bd.divide(bd2, 15, BigDecimal.ROUND_HALF_UP));
    //52744.483614048831304
}
```

# 枚举类

- 类的对象只有有限个，是确定的.
- 当需要定义一组常量时，强烈建议使用枚举类

**枚举类的实现**

- JDK1.5之前需要自定义枚举类
- JDK 1.5 新增的 enum 关键字用于定义枚举类
- 若枚举只有一个对象, 则可以作为一种单例模式的实现方式。

**枚举类的属性**

- 枚举类对象的属性不应允许被改动, 所以应该使用 private final 修饰
- 枚举类的使用 private final 修饰的属性应该在构造器中为其赋值
- 若枚举类显式的定义了带参数的构造器, 则在列出枚举值时也必须对应的传入参数

## 自定义枚举类

1. 私有化类的构造器，保证不能在类的外部创建其对象
2. 在类的内部创建枚举类的实例。声明为：public static final 
3. 对象如果有实例变量，应该声明为private final，并在构造器中初始化

```java
public class SeasonTest {
    public static void main(String[] args) {
        Season season = Season.SPRING;
        System.out.println(season);
    }
}

class Season {
    //    1.声明Season对象的属性
    private final String seasonName;
    private final String seasonDesc;

    //    2.私有化类的构造器,并为对象属性赋值
    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

//    3.提供当前枚举类的多个对象 public static final
    public static final Season SPRING = new Season("春天","万物复苏");
    public static final Season SUMMER = new Season("夏天","赤日炎炎");
    public static final Season AUTUMN = new Season("秋天","硕果累累");
    public static final Season WINTER = new Season("冬天","天寒地冻");

//    其他诉求：getter获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    //其他诉求：toString()

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
```

## enum定义枚举类

**使用说明**

- 使用 `enum关键字`定义的枚举类<mark>默认继承了 java.lang.Enum类 ，因此不能再继承其他类</mark>
- 枚举类的构造器只能使用 private 权限修饰符
- 枚举类的所有实例必须在枚举类中显式列出
   - 多个对象之间用 逗号, 隔开。而不是分号 ; 
   - 最后一个对象以分号 ; 结束
   - 列出的实例系统会自动添加 public static final 修饰
- 必须在枚举类的第一行声明枚举类对象(枚举类的对象要移到最前面。)

JDK 1.5 中可以在 switch 表达式中使用Enum定义的枚举类的对象作为表达式, case 子句可以直接使用枚举值的名字, 无需添加枚举类作为限定。

```java
public class SeasonTest {
    public static void main(String[] args) {
        Season season = Season.SPRING;
        System.out.println(season);

        System.out.println(Season.class.getSuperclass());
        //class java.lang.Enum 定义的枚举类默认继承Enum类
    }
}

enum Season{

//    1.提供当前枚举类的对象,
//    多个对象之间用 逗号, 隔开。而不是分号 ; 最后一个对象以分号 ; 结束
//    枚举类的对象要移到最前面
    SPRING("春天","万物复苏"),
    SUMMER("夏天","赤日炎炎"),
    AUTUMN("秋天","硕果累累"),
    WINTER("冬天","天寒地冻");

//    声明Season对象的属性
    private final String seasonName;
    private final String seasonDesc;

//    私有化类的构造器,并为对象属性赋值
    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //    其他诉求：getter获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    //其他诉求：toString() Enum类中已经重写
    //默认打印当前对象的名字
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
```

## Enum类的主要方法

-  `values()`方法：
   - 返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值。
-  `valueOf(String str)`：
   - 可以把一个字符串转为对应的枚举类对象。
   - 要求字符串必须是枚举类对象的“名字”。如不是，会有运行时异常：IllegalArgumentException。
- `toString()`：
   - 默认返回当前枚举类对象常量的名称

```java
public class SeasonTest {
    public static void main(String[] args) {
        Season season = Season.SPRING;
//        toString()
        System.out.println(season);

        System.out.println(Season.class.getSuperclass());
        //class java.lang.Enum 定义的枚举类默认继承Enum类

//        values()  返回对象数组
        System.out.println(Season.values());
        //[Lcom.zjk.java2.Season;@52cc8049
//        Season[] values = Season.values();
        for(int i = 0 ; i<Season.values().length;i++){
            System.out.println(Season.values()[i]);
        }

        Thread.State[] values = Thread.State.values();
        for(int i = 0;i<values.length;i++){
            System.out.println(values[i]);
        }

//        valueOf(String objName) 根据提供的objName，返回枚举类中对象名是objName的对象。
        Season winter = Season.valueOf("WINTER");
        System.out.println(winter);
//        如果没有符合objName的对象，则异常
//        Season winter1 = Season.valueOf("WINTER1");
        //java.lang.IllegalArgumentException
    }
}
```

## 实现接口的枚举类

和普通 Java 类一样，枚举类可以实现一个或多个接口

- 情况一：实现接口，在枚举类中实现抽象方法
   - 若每个枚举值在调用实现的接口方法呈现相同的行为方式，则只要统一实现该方法即可。
-  情况二：枚举类的对象分别实现接口中的方法
   - 若需要每个枚举值在调用实现的接口方法呈现出不同的行为方式, 则可以让每个枚举值分别来实现该方法
   - 哪怕只有一个枚举类对象实现也可实现该接口，不会报异常
   

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
//        @Override
//        public void show() {
//            System.out.println("春天");
//        }
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

### 练习：枚举类实现内部抽象方法

- 枚举类可以在内部定义抽象方法，但是他的所有实例都要实现该抽象方法。

```java
/**
 * 1)有 RED,BLUE,BLACK,YELLOW,GREEN这个五个枚举值；
 * 2)Color有三个属性redValue，greenValue，blueValue，
 * 3)创建构造方法，参数包括这三个属性，
 * 4)每个枚举值都要给这三个属性赋值，三个属性对应的值分别是red：255,0,0  	blue:0,0,255  black:0,0,0  yellow:255,255,0  green:0,255,0
 * 5)重写toString方法显示三属性的值
 * 6)在Color中添加抽象方法meaning，不同的枚举类的meaning代表的意思各不相同
 */

enum Color {
    RED(255, 0, 0){
        String meaning(){
          return "Red";
        };
    },
    BLUE(0, 0, 255){
        String meaning(){
            return "blue";
        }
    },
    BLACK(0, 0, 0){
        @Override
        String meaning() {
            return "black";
        }
    },
    YELLOW(255, 255, 0){
        @Override
        String meaning() {
            return "yellow";
        }
    },
    GREEN(0, 255, 0){
        @Override
        String meaning() {
            return "green";
        }
    };
    private final int redValue;
    private final int greenValue;
    private final int blueValue;

    Color(int redValue, int greenValue, int blueValue) {
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    @MyAnnotation2(value = "hi")
    @Override
    public String toString() {
        return "Color{" +
                "redValue=" + redValue +
                ", greenValue=" + greenValue +
                ", blueValue=" + blueValue +
                '}';
    }
    
    abstract String meaning();
}
```

# 注解(Annotation)

## 注解 (Annotation) 概述

- 从 JDK 5.0 开始, Java 增加了对元数据(MetaData) 的支持, 也就是Annotation(注解)
- Annotation 其实就是代码里的特殊标记, 这些标记可以在编译, 类加载, 运行时被读取, 并执行相应的处理。通过使用 Annotation, 程序员可以在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息。代码分析工具、开发工具和部署工具可以通过这些补充信息进行验证或者进行部署。
- Annotation 可以像修饰符一样被使用, 可用于修饰包,类, 构造器, 方法, 成员变量, 参数, 局部变量的声明, 这些信息被保存在 Annotation 的 “name=value” 对中。
- 在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。在JavaEE/Android中注解占据了更重要的角色，例如用来配置应用程序的任何切面，代替JavaEE旧版中所遗留的繁冗代码和XML配置等。
- 未来的开发模式都是基于注解的，JPA是基于注解的，Spring2.5以上都是基于注解的，Hibernate3.x以后也是基于注解的，现在的Struts2有一部分也是基于注解的了，
   - 注解是一种趋势，一定程度上可以说：**框架 = 注解 + 反射 + 设计模式**

## 常见的Annotation示例

- 使用 Annotation 时要在其前面增加 @ 符号, 并把该 Annotation 当成一个修饰符使用。用于修饰它支持的程序元素

### 示例一：生成文档相关的注解

- `@author` 标明开发该类模块的作者，多个作者之间使用,分割
- `@version` 标明该类模块的版本
- `@see` 参考转向，也就是相关主题
- `@since` 从哪个版本开始增加的
- `@param` 对方法中某参数的说明，如果没有参数就不能写
- `@return` 对方法返回值的说明，如果方法的返回值类型是void就不能写
- `@exception` 对方法可能抛出的异常进行说明 ，如果方法没有用throws显式抛出的异常就不能写
- 其中
     - `@param @return 和 @exception` 这三个标记都是只用于方法的。
     - `@param`的格式要求：`@param 形参名 形参类型 形参说明`
     - `@return` 的格式要求：`@return 返回值类型 返回值说明`
     - `@exception`的格式要求：`@exception 异常类型 异常说明`
     - `@param和@exception`可以并列多个

### 示例二：在编译时进行格式检查(JDK内置的三个基本注解)

- `@Override`: 限定重写父类方法, 该注解只能用于方法
- `@Deprecated`: 用于表示所修饰的元素(类, 方法等)已过时。
   - 通常是因为所修饰的结构危险或存在更好的选择
- `@SuppressWarnings`: 抑制编译器警告

```java
@Override  //强制校验是否重写 ；不加也可以也是重写，但是没有校验。
public void walk() {
    System.out.println("学生走路");
}

//---------

@SuppressWarnings("unused")
int num = 10;

@SuppressWarnings({"unused","rawtypes"})
ArrayList list = new ArrayList();
```

### 示例三：跟踪代码依赖性，实现替代配置文件功能

- Servlet3.0提供了注解(annotation),使得不再需要在web.xml文件中进行Servlet的部署。
- spring框架中关于“事务”的管理


## 自定义 Annotation

- 注解声明为 `@interface` 
- 自定义注解自动继承了`java.lang.annotation.Annotation接口`
- Annotation 的成员变量在 Annotation 定义中以<mark>无参数方法</mark>的形式来声明。
    - 其方法名和返回值定义了该成员的名字和类型。我们称为**配置参数**。
    - 类型只能是八种基本数据类型、String类型、Class类型、enum类型、Annotation类型、以上所有类型的数组。
- 指定成员的默认值 ，使用`default 关键字`
- 如果只有一个参数成员，建议使用参数名为value
- 如果定义的注解含有配置参数，那么使用时**必须指定参数值，除非它有默认值**。格式是“参数名 = 参数值”，
    - 如果只有一个参数成员，且名称为value，可以省略“value=”
- 没有成员定义的 Annotation 称为**标记**; 
- 包含成员变量的 Annotation 称为**元数据 Annotation**
- 注意：自定义注解必须配上注解的信息处理流程才有意义。

```java
//1.注解声明为@interface
public @interface MyAnnocation {
    String value() default "hi";
}

@MyAnnocation(value = "hello")
class Person {}
```

## JDK 中的元注解

- 对现有的注解进行解释说明的注解
   - JDK 的元 Annotation 用于修饰其他 Annotation 定义
- JDK5.0提供了4个标准的meta-annotation类型，分别是：
  - Retention
  - Target
  - Documented
  - Inherited
- 自定义注解通常都会指明两个元注解：Retention，Target


**@Retention**: 

- 只能用于修饰一个 Annotation 定义, 用于指定该 Annotation 的生命周期, @Rentention 包含一个 RetentionPolicy 类型的成员变量, 使用@Rentention 时必须为该 value 成员变量指定值:
   - RetentionPolicy.SOURCE:在源文件中有效（即源文件保留），编译器直接丢弃这种策略的注释
   - RetentionPolicy.CLASS:在class文件中有效（即class保留） ， 当运行 Java 程序时, JVM 不会保留注解。 
        - 这是默认值
   - RetentionPolicy.RUNTIME:在运行时有效（即运行时保留），当运行 Java 程序时, JVM 会保留注释。
      - 只有说明为RUNTIME生命周期的注解，才可以通过反射获取该注释。

**@Target**: 

- 用于修饰 Annotation 定义, 用于指定被修饰的 Annotation 能用于修饰哪些程序元素。 @Target 也包含一个名为 value 的成员变量。

**@Documented**: 

- 用于指定被该元 Annotation 修饰的 Annotation 类将被javadoc 工具提取成文档。
   - 默认情况下，javadoc是不包括注解的。
- 定义为@Documented的注解必须设置@Retention值为RUNTIME。
   
**@Inherited**: 

- 被它修饰的 Annotation 将具有继承性。如果某个类使用了被@Inherited 修饰的 Annotation, 则其子类将自动具有该注解。
    - 比如：如果把标有@Inherited注解的自定义的注解标注在类级别上，子类则可以继承父类类级别的注解
    - 实际应用中，使用较少

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

### 练 习

1.编写一个Person类，使用Override注解它的toString方法
2.自定义一个名为“MyTiger”的注解类型，它只可以使用在方法上，带一
个String类型的value属性，然后在第1题中的Person类上正确使用。


## 利用反射获取注解信息

- JDK 5.0 在 java.lang.reflect 包下新增了 AnnotatedElement 接口, 该接口代表程序中可以接受注解的程序元素
- 当一个 Annotation 类型被定义为运行时 Annotation 后, 该注解才是运行时可见, 当 class 文件被载入时保存在 class 文件中的 Annotation 才会被虚拟机读取 
  - 要求此注解的元注解@Retention中声明的生命周期状态为: RUNTIME
- 程序可以调用 AnnotatedElement对象的如下方法来访问 Annotation 信息

```java
@Test
public void testGetAnnotation() {
    Class studentClass = Student.class;
    Annotation[] anotations = studentClass.getAnnotations();
    for (int i = 0; i < anotations.length; i++) {
        System.out.println(anotations[i]);
    }
    //@com.zjk.java2.MyAnnocation("hello")
}
```

## JDK8中注解的新特性

Java 8对注解处理提供了两点改进：可重复的注解及可用于类型的注解。此外，反射也得到了加强，在Java8中能够得到方法参数的名称。这会简化标注在方法参数上的注解。

**可重复注解**

1. 注解说明`@Repeatable(存放该重复注解的注解)`
2. 创建新的Annotation注解存放要重复的注解的数组（成员为`注解.class`），(如果存在)同时需要相同的`@Inherited，@Tartget和@Rentention`

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

```java
//重复注解 jdk8.0之前

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
public @interface MyAnnotation {
    String value() default "hi";
}

public @interface MyAnnotations {
    MyAnnotation[] value();
}

@MyAnnotations({@MyAnnotation(value = "hello"),@MyAnnotation(value = "hi")})
class Person(){
    ...
}
```

**类型注解：**

- JDK1.8之后，关于元注解@Target的参数类型ElementType枚举值多了两个：
    - TYPE_PARAMETER
    - TYPE_USE。
-  在Java 8之前，注解只能是在声明的地方所使用，Java8开始，注解可以应用在任何地方。
    - ElementType.TYPE_PARAMETER 
        - 表示该注解能写在类型变量的声明语句中（如：泛型声明）。 
    - ElementType.TYPE_USE 
        - 表示该注解能写在使用类型的任何语句中。

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

# 集合

**集合框架的概述**

1. 集合和数组都是对多个数据进行存储操作的，简称：Java容器。
   - 此时的存储主要指的是内存层面的存储，不涉及到持久化的存储（硬盘中：.txt/.jpg/.avi/数据库中等)

**与数组的比较**

- 数组在存储多个数据方面的特点
  - 一旦初始化以后，其长度就确定了。
  - 数组一旦定义好，其元素的数据类型也就确定了，就只能操作指定类型的数据，
      - 如 String[] arr ; int[] arr 
      - Object[] arr 
- 数组在存储多个数据方面的缺点
  - 一旦初始化以后，其长度就不可修改了 
  - 数组提供的方法非常有限，对于添加，删除，插入数据等操作，非常不便，同时效率不高。
  - 获取数组中实际元素的个数的需求，数组没有现成的属性或方法可用
  - 数组存储数据的特点：有序，可重复
     - 对于无序，不可重复的需求，数组不可满足。 

## 集合框架

**Java集合的两种体系**

- Collection接口 ： 单列数据，定义了存取一组对象(一个一个的对象)的方法的集合
   - List接口: 元素有序，可重复的集合  -->“动态数组”
      -  ArrayList / LinkedList / Vector
   - Set接口: 元素无序，不可重复的集合 --> 类型于数学中的”集合“ 
      - HashSet / LinkedSet / TreeSet
- Map接口：双列数据，保存具有映射关系"key-value"对(一对一对的数据)的集合 --> 类似于数字中的：”函数  y=f(x)“ ；一个key只能对应一个value，而一个value可以对应多个key
   - HashMap / LinkedHashMap / TreeMap / Hashtable / Properties 

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/51232021239497.png =696x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/236772121227364.png =662x)

## Collection接口中的方法的使用

**添加**

- add(Object obj)
  - 将元素e添加到集合中
- addAll(Collection coll)
  - 将coll集合中的元素添加到当前的集合中

**获取有效元素的个数**

- int size()

**清空集合**

-  void clear()
   
**是否是空集合**

- boolean isEmpty()
   
**是否包含某个元素**

- boolean contains(Object obj)：
    - 是通过元素的equals方法来判断是否是同一个对象
    - 向Collection接口的实现类中添加数据obj时，要求obj所在类要重写equals()方法，用来判断
- boolean containsAll(Collection c)：
    - 也是调用元素的equals方法来比较的。拿两个集合的元素挨个比较。

**删除**

- boolean remove(Object obj) ：
    - 通过元素的equals方法判断是否是要删除的那个元素。
    - 只会删除找到的第一个元素
- boolean removeAll(Collection coll)：
    - 取当前集合的差集

**取两个集合的交集**

- boolean retainAll(Collection c)：
   - 把交集的结果存在当前集合中，不影响c
   - （交集）从当前集合中返回coll中匹配的元素,并将当前集合改为交集。

**集合是否相等**

-  boolean equals(Object obj)
   - 调用元素内的equals()方法一一进行比较
   - 且如果是ArrayList() 则还要求有序 

**转成对象数组**

- Object[] toArray()
   - 集合-->数组
- 数组-->集合 调用Arrays类静态方法 aslist()

**获取集合对象的哈希值**

- hashCode()
  - 返回当前对象的哈希值
  
**遍历**

- iterator()：
   - 返回迭代器对象，用于集合遍历
   - 返回Iterator接口的实例 用于遍历元素，放在Iterator.Test.java中
   
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

### Iterator迭代器接口

- Iterator对象称为迭代器(设计模式的一种)，主要用于遍历 Collection 集合中的元素。
- GOF给迭代器模式的定义为：提供一种方法访问一个容器(container)对象中各个元素，而又不需暴露该对象的内部细节。
   - 迭代器模式，就是为容器而生。类似于“公交车上的售票员”、“火车上的乘务员”、“空姐”。
- Collection接口继承了`java.lang.Iterable`接口，该接口有一个iterator()方法，
   - 所有实现了Collection接口的集合类都有一个iterator()方法，用以返回一个实现了Iterator接口的对象。
- Iterator 仅用于遍历集合，Iterator 本身并不提供承装对象的能力。
   - 如果需要创建Iterator 对象，则必须有一个被迭代的集合。
- 集合对象每次调用iterator()方法都得到一个全新的迭代器对象，
   - 默认游标都在集合的第一个元素之前。
   
**Iterator方法**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/337951223221072.png =676x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/265952123239498.png =493x)

#### 迭代器遍历集合的操作

- hasNext()和next()搭配使用
- 在调用it.next()方法之前必须要调用it.hasNext()进行检测。
   - 若不调用，且下一条记录无效，直接调用it.next()会抛出NoSuchElementException异常。

```java
public class IteratorTest {
    @Test
    public void test1() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add("Jack");
        coll.add(false);
        coll.add(new String("Tom"));
        coll.add(new Person("Mac", 20));

        Iterator iterator = coll.iterator();

        //方式1
//        System.out.println(iterator.next()); //123
//        System.out.println(iterator.next()); //Jack
//        System.out.println(iterator.next()); //false
//        System.out.println(iterator.next()); //Tom
//        System.out.println(iterator.next()); //com.zjk.java2.Person@75bd9247
//        System.out.println(iterator.next()); //报异常 NoSuchElementException
        //方式2 不推荐
        for (int i = 0; i < coll.size(); i++) {
            System.out.println(iterator.next());
        }
        //方式3
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //方式4
        coll.forEach(System.out::println);
    }
}
```

**迭代器的执行原理**

- 类似于Oracle的SEQUENCE的 .NEXTVAL 

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

#### Interator接口的remove()

- Iterator可以删除集合的元素，但是是遍历过程中通过迭代器对象的remove方法，不是集合对象的remove方法。
- 如果还未调用next()或在上一次调用 next 方法之后已经调用了 remove 方法，再调用remove都会报IllegalStateException。
   - 在没有使用next()切换时，不能连续remove()同一个位置的 

```java
@Test
public void test2(){
    Collection coll = new ArrayList();
    coll.add(123);
    coll.add("Yo");

    Iterator iterator = coll.iterator();
    while(iterator.hasNext()){
        Object obj = iterator.next();
        if("Yo".equals(obj)){
            iterator.remove();
            //iterator.remove(); //报错 在没有使用next()切换时，不能连续remove()同一个位置的 
        }
    }

    //错误；使用同一个Iterator对象，此时游标还在上次调用.next()的位置
    //while(iterator.hasNext()){
    //    System.out.println(iterator.next());
    //}

    Iterator iterator1 = coll.iterator();
    while(iterator1.hasNext()){
        System.out.println(iterator1.next());
    }

}
```

#### 使用 foreach 循环遍历集合元素

- Java 5.0 提供了 foreach 循环迭代访问 Collection和数组。
- 遍历操作不需获取Collection或数组的长度，无需使用索引访问元素。
- 遍历集合的底层调用Iterator完成操作。
- foreach还可以用来遍历数组。

```java
for(要遍历的集合/数组的元素的类型 在循环中使用的元素名称 : 要遍历的集合/数组){
    ...
}
```

```java
@Test
public void test3() {
    Collection coll = new ArrayList();
    coll.add(123);
    coll.add("Yo");

    for (Object obj : coll) {
        System.out.println(obj);
    }
}
```

```java
@Test
public void test4() {
    String[] arr = new String[]{"GG", "GG", "GG"};

    //普通for循环 
    //成功改变
    for (int i = 0; i < arr.length; i++) {
        arr[i] = "MM";
    }

    for (int i = 0; i < arr.length; i++) {
        System.out.println(arr[i]);
    }

    //增强for循环
    //s类似于形参，不会改变原来数组中的String（不可变性)
    for(String s : arr){
        s = "GG";
    }

    for(String s : arr){
        System.out.println(s);
    }
}
```

### List接口

- 鉴于Java中数组用来存储数据的局限性，我们通常使用List替代数组
- List集合类中元素有序、且可重复，集合中的每个元素都有其对应的顺序索引。
- List容器中的元素都对应一个整数型的序号记载其在容器中的位置，可以根据序号存取容器中的元素。
- JDK API中List接口的实现类常用的有：ArrayList / LinkedList / Vector

**ArrayList / LinkedList / Vector三者的异同**

- 同：
  - 三个类都实现了List接口。存储数据的特点相同：存储有序的，可重复的数据。
- 不同：
   - ArrayList 
      - 作为List接口的主要实现类
      - 线程不安全，效率高
      - 底层使用Object[] elementData存储
   - LinkedList 
      - 对于频繁的插入和删除操作，使用此类比ArrayList效率高 
      - 底层使用双向链表存储 
   - Vector 
      - 作为List接口的古老实现类
      - 线程安全，效率低
      - 底层使用Object[] elementData存储

#### 源码分析

##### ArrayList类

- ArrayList 是 List 接口的典型实现类、主要实现类
   - 本质上，ArrayList是对象引用的一个”变长”数组
- JDK1.7：ArrayList像饿汉式，直接创建一个初始容量为10的数组
   - Arrays.asList(…) 方法返回的 List 集合，既不是 ArrayList 实例，也不是Vector 实例。 
   - Arrays.asList(…) 返回值是一个固定长度的 List 集合

**源码分析 jdk 1.7**

- ArrayList像饿汉式，直接创建一个初始容量为10的数
   - `ArrayList list = new ArrayList();` //底层创建了长度是10的Object[] 数组elementData
   - `list.add(123); //elementData[0] = new Integer(123);`
   - 如果此次的添加导致底层elementData数组容量不够，则扩容。
   - 默认情况下，扩容为原来的容量的1.5倍，同时需要将原来数组中的数据复制到新的数组中
- 建议开发中使用带参的构造器: `ArrayList list = new ArrayList(int capacity);` 指定底层创建Object[] elementData数组的长度
   - 直接指定较大的容量，可以避免扩容 

**源码分析 jdk 1.8**

- ArrayList像懒汉式，一开始创建一个长度为0的数组，当添加第一个元素时再创建一个始容量为10的数组
   - `ArrayList list = new ArrayList(); ` //底层Object[] elementData初始化为{},并没有创建长度
   - `list.add(123)` //从第一调用add()时，底层才创建了长度为10的数组，并将数据123 添加到elementData数组中
- 其他的添加和扩容和jdk1.7相同
   
##### LinkedList类

**源码分析**

- 对于频繁的插入或删除元素的操作，建议使用LinkedList类，效率较高
- LinkedList：双向链表，内部没有声明数组，
   - 而是定义了`Node类型的first和last`，用于记录首末元素。
      - `LinkedList list = new LinkedList(); `//内部声明了Node类型的first和last属性，默认值为null
   - 同时，定义`内部类Node`，作为LinkedList中保存数据的基本结构。
      - `list.add(123);` //将123封装带Node中，创建了Node对象。
   - Node除了保存数据，还定义了两个变量：
      - `prev变量`记录前一个元素的位置
      - `next变量`记录下一个元素的位置
      
```java
//Node定义为：体现了LinkedList的双向链表
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```

##### Vector类

**源码分析**

- jdk1.7和jdk1.8中通过Vector()构造器创建对象时，底层都创建了长度为10的数组.
- 扩容默认2倍

#### List接口方法

- `void add(int index, Object ele)`:在index位置插入ele元素
- `boolean addAll(int index, Collection eles)`:从index位置开始将eles中的所有元素添加进来
- `Object get(int index)`:获取指定index位置的元素
- `int indexOf(Object obj)`:返回obj在集合中首次出现的位置，没有则返回-1
- `int lastIndexOf(Object obj)`:返回obj在当前集合中末次出现的位置
- `Object remove(int index)`:移除指定index位置的元素，并返回此元素
- `Object set(int index, Object ele)`:设置指定index位置的元素为ele，并返回此(原先的)元素
- `List subList(int fromIndex, int toIndex)`:返回从fromIndex到toIndex(不包括toIndex)位置的子集合

**常用方法**

- 增 add(int index, Object obj)
- 插 add(int index Object obj)
- 删 remove(int index) /remove(Object obj) Collection接口内的
- 改 set(int index, Object obj)
- 查 get(int index)
- 长度 size()
- 遍历 
   1. Iterator迭代器
   2. foreach
   3. for
 

```java
package com.zjk.java2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListTest {
    @Test
    public void test1() {
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("Tom");
        list.add(new Person("Mac", 20));

        System.out.println(list); //[123, 456, Tom, com.zjk.java2.Person@75bd9247]

        //void add(int index Object ele) 在index位置插入ele元素
        list.add(1, "BB");
        System.out.println(list); //[123, BB, 456, Tom, com.zjk.java2.Person@75bd9247]

        //boolean addAll(int index,Collection eles) 从index位置开始将eles中的所有元素依序添加到当前的list
        System.out.println(list.size()); //5
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
        System.out.println(list.size()); //8

        //Object get(int index) 从index位置获取元素
        System.out.println(list.get(2)); //456

        //int indexOf(Object obj) 返回obj在集合中首次出现的位置, 没有则返回-1
        int index = list.indexOf(456);
        System.out.println(index); //2

        //int lastIndexOf(Object obj) 返回obj在集合中最后出现的位置，没有则返回-1
        int lastIndex = list.lastIndexOf(456);
        System.out.println(lastIndex); //2

        //Object remove(int index) 移除指定index位置的元素，并返回此元素
        Object obj = list.remove(2);
        System.out.println(obj); //456

        //Object set(int index,Object obj) 设置指定index位置的元素为ele,并返回此(原先的)元素
        System.out.println(list.set(2, "Test"));

        //List subList(int fromIndex, int toIndex) 返回从fromIndex到toIndex(不包括toIndex)位置的子集合
        List list2 = list.subList(1, 3);
        System.out.println(list2); //[BB, Test]
    }

    @Test
    public void test2() {
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("Tom");

        //方式1 Iterator迭代器
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //方式2 foreach
        for (Object obj : list) {
            System.out.println(obj);
        }

        //方式3 for
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
       
        //方式4 forEach()
        list.forEach(System.out::println);
    }
}
``` 

##### Arrays.asList() 的注意

- Arrays.asList() 返回的不是java.util下的ArrayList，而是Arrays的内部类，继承AbstractList类（List），但没有重写add()方法
- 因此，通过Arrays.asList()方法构造的List集合，尽量不要用add()等方法修改。

```java
public class ListTest {
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3);
    //     list.add(new Integer(4));
        //抛出异常：Arrays.asList() 
        // 返回的不是java.util下的ArrayList，
        // 而是Arrays的内部类，继承AbstractList类（List），但没有重写add()方法
    //     System.out.println(list.contains(new Integer(4)));
    }
}
```

##### ArrayList的sublist() 的注意

1. 修改原集合元素的值，会影响子集合
2. 修改原集合的结构，会引起异常：ConcurrentModificationException
3. 修改子集合元素的值，会影响原集合
4. 修子集合的结构，会影响原集合

- subList()返回的是ArrayList的内部类SubList（是ArraysList的一个视图），而不是ArrayList。对于SubList子列表的所有操作最终会反映到原列表上。
- 在subList()中，对父集合元素的增加或删除，均会导致子列表的遍历、增加、删除产生ConcurrentModificationException异常。
    - 对子集合调用的remove()，此时删除的是对应于该子集合的下标位置的元素。且只能是子集合中包含的元素。

```java
@Test
public void test2() {
    List<String> list = new ArrayList<String>();
    list.add("一号");
    list.add("二号");
    list.add("三号");

    //subList() 获取子列表
    List<String> subList = list.subList(1, 3); //["二号","三号"]

    //对原集合元素的修改
    list.set(2, "三号-修改");
    //对原集合的修改影响子集合
    System.out.println(subList); //[二号, 三号-修改]

    //修改原集合的结构
    //list.add("四号");
    //在原集合结构修改后，子集合的遍历、增加、删除出现异常.
    //遍历子集合时，出现异常java.util.ConcurrentModificationException
    //System.out.println(subList);
    //subList.add("五号");
    //subList.remove(3);

    //子集合元素的修改、结构的改变（增加、删除），影响父集合。
    subList.set(1, "二号-sub修改");
    System.out.println(list); //[一号, 二号, 二号-sub修改]
    subList.add("五号");
    System.out.println(list); //[一号, 二号, 二号-sub修改, 五号]
    //此时删除的是对应于该子集合的下标位置的元素。
    subList.remove(1); 
    System.out.println(list); //[一号, 二号, 五号]
}
```

##### remove() ， Collection接口和ArrayList接口

```java
@Test
public void testListRemove() {
    List list = new ArrayList();
    list.add(1);
    list.add(2);
    list.add(3);
    updateList(list);
    System.out.println(list);//[1,2]
}

private static void updateList(List list) {
    list.remove(2);  //视为索引2 而不是数据2
    //list.remove(new Integer(2)); //删除数据2
}
```

```java
//关于添加的数据被修改
public class SetTest {
    @Test
    public void test1() {
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
    }
}
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/227085513239570.png =696x)

### Set接口

- 存储无序，不可重复的数据
- Set接口是Collection的子接口，Set接口没有提供额外的方法，使用的都是Collect接口中的方法
- Set 集合不允许包含相同的元素，如果试把两个相同的元素加入同一个Set 集合中，则添加操作失败。
- Set 判断两个对象是否相同不是使用 == 运算符，而是根据 equals() 方法

**实现类**

- `HashSet `
   - 作为Set接口的主要实现类，线程不安全的，可以存储null值
   - `LinkedHashSet` 作为HashSet的子类；遍历其内部数据时，可以安装添加的顺序遍历
- `TreeSet`
   - 可以按照添加对象的指定属性，进行排序。

**Set存储无序，不可重复的数据**

以HashSet为例：

- 无序性
  - 不等于随机性  遍历的顺序仍然是添加的顺序
  - 存储的数组在底层数组并非按照数组索引的顺序添加。
  - 而是根据数据的Hash值决定的。
- 不可重复性
  - 保证添加的元素按照equals()方法判断时，返回的不是true。相同的元素只能添加一次。

**添加元素的过程**

以HashSet为例

- 像hashSet中添加元素a，首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，此哈希值接着通过某种算法计算出在hashSet底层数组中的存放位置（即为：索引位置），判断数组此位置上是否已经有元素。
- 如果此位置上没有其他元素，则元素a添加成功 --情况1
- 如果此位置上有其他元素b（或以链表形式存在的多个元素），则比较元素a于元素b的hash值
   - 如果hash值不相同，则元素a添加成功 --情况2
   - 如果hash值相同，进而需要调用元素a所在类的equals()方法
      - equals()返回true，元素a添加失败
      - equals()返回false，则元素a添加成功  --情况3
- 对于添加成功的情况2和情况3而言，元素a，与已经存在指定索引位置上数据以链表的方式存储。
- HashSet底层：数组+链表
  - jdk 1.7 ：元素a放到数组中，指向原来的元素
  - jdk 1.8 ：原来的元素在数组中，指向元素a
  ![](C:/Users/zjk10/OneDrive/NoteBook/pictures/539705218221142.png =661x)
  ![](C:/Users/zjk10/OneDrive/NoteBook/pictures/141841619239568.png =669x)
  


```java
public class SetTest {
    @Test
    public void test1(){
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
        set.add(new Person("Mac",21));
        System.out.println(set);//[AA, BB, CC, com.zjk.java2.Person@7d417077, 456, com.zjk.java2.Person@75bd9247, 123]
        //Person类中未重写hashCode()方法和equals()方法,比较的还是地址，所以认为不一样

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        } //遍历的顺序仍然是添加的顺序
    }
}
```

#### 重写hashCode()

以Eclipse/IDEA为例，在自定义类中可以调用工具自动重写equals()和hashCode()。

**要求:**

- 向Set中添加的数据，其所在类一定要重写hashCode()和equals()
- 重写的hashCode()方法和equals()方法尽可能保持一致性。
  - 相等的对象必须具有相等的散列码 

**基本原则**

- 在程序运行时，同一个对象多次调用 hashCode() 方法应该返回相同的值。
- 当两个对象的 equals() 方法比较返回 true 时，这两个对象的 hashCode() 方法的返回值也应相等。
- 对象中用作 equals() 方法比较的 Field，都应该用来计算 hashCode 值。

**问题：为什么用Eclipse/IDEA复写hashCode方法，有31这个数字？**

- 选择系数的时候要选择尽量大的系数。因为如果计算出来的hash地址越大，所谓的“冲突”就越少，查找起来效率也会提高。（减少冲突）
-  并且31只占用5bits,相乘造成数据溢出的概率较小。
- 31可以 由`i*31== (i<<5)-1`来表示,现在很多虚拟机里面都有做相关优化。（提高算法效率）
-  31是一个素数，素数作用就是如果我用一个数字来乘以这个素数，那么最终出来的结果只能被素数本身和被乘数还有1来整除！(减少冲突)

#### HashSet类

- HashSet 是 Set 接口的典型实现，大多数时候使用 Set 集合时都使用这个实现类。
- HashSet 按 Hash 算法来存储集合中的元素，因此具有很好的存取、查找、删除性能。

**HashSet 具有以下特点：**

- 不能保证元素的排列顺序
- HashSet 不是线程安全的
- 集合元素可以是 null
- HashSet 集合判断两个元素相等的标准：两个对象通过 hashCode() 方法比较相等，并且两个对象的 equals() 方法返回值也相等。
- 对于存放在Set容器中的对象，对应的类一定要重写equals()和hashCode(Object obj)方法，以实现对象相等规则。
   - 即：“相等的对象必须具有相等的散列码”。

#### LinkedHashSet类

- LinkedHashSet作为HashSet的子类，在添加数据的同时，每个数据还维护了两个引用，记录此数据前一个数据和后一个数据。
- 对于频繁的遍历操作，LinkedHashSet效率高于HashSet

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/208262222221142.png =609x)

#### TreeSet类

- 向TreeSet中添加的数据，要求是相同类的对象

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/35032923239568.png =563x)

**两种排序方式：自然排序 和 定制排序**

- 自然排序(实现Comparable接口)中，比较两个对象是否相同的标准为：compareTo()返回0，不再是equals()
  - 默认情况下，TreeSet 采用自然排序。
- 定制排序(实现Comparator接口)中，比较两个对象是否相同的标准为：compare()返回0，不再是equals()

```java
public class Test{
    @Test
    public void test2() {
        TreeSet set = new TreeSet();
        //TreeSet不能添加不同类的对象
        set.add(456);
        set.add(123);
        set.add(-12);
        set.add(789);

        //按照集合中对象的大小遍历。 使用Comparable接口的compareTo()
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        TreeSet set1 = new TreeSet();
        set1.add(new Person("Tom", 22));
        set1.add(new Person("Jck", 23));
        set1.add(new Person("Mac", 21));
//        set1.add(new Person("Mac",22)); //Person中只写了name的比较
//        TreeSet中判断是否相同使用的是compareTo()，而不是equals()

        Iterator iterator1 = set1.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
    }

    @Test
    public void test3() {
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Person && o2 instanceof Person) {
                    Person p1 = (Person) o1;
                    Person p2 = (Person) o2;

                    if (p1.getName().compareTo(p2.getName()) == 0) {
                        return Integer.compare(p1.getAge(), p2.getAge());
                    }

                    return p1.getName().compareTo(p2.getName());
                } else {
                    throw new RuntimeException("数据类型不一致");
                }
            }
        }; 

        //使用定制排序
        TreeSet set = new TreeSet(comparator); 
        //此时使用的是comparator对象中的compare()方法来比较是否相同，而不是Person类中的实现Comparable接口的compareTo();
        set.add(new Person("Tom", 22));
        set.add(new Person("Jck", 23));
        set.add(new Person("Mac", 21));
        set.add(new Person("Mac", 24));

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
```

## Map接口

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/453525713247603.png =502x)

-  Map接口的常用实现类：HashMap、TreeMap、LinkedHashMap和Properties。
   - 其中，HashMap是 Map 接口使用频率最高的实现类

**Map ： 双列数据，存储key-value对的数据**

- `HashMap` 作为Map的主要实现类 线程不安全，效率高 可以存储null的key和value
   - HashMap的底层实现：
      - jdk1.7之前 : 数组+链表 
      - jdk1.8 : 数组+链表+红黑树   
   - `LinkedMap` 保证在遍历map元素时，可以按照添加的顺序实现遍历
      - 原因：在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素。
      - 对于频繁的遍历操作 ，此类执行效率高于HashMap。
- `TreeMap` 保证按照添加的key-value进行排序，实现排序遍历。此时考虑key的自然排序或定制排序。
   - 底层使用红黑树。 
- `Hashtable` 作为古老的实现类 线程安全，效率低 不能存储null的key和value
   - `Properties` 常用来处理配置文件，key和value都是String类型。

**Map结构**

- Map与Collection并列存在。用于保存具有映射关系的数据:key-value
   - Map 中的 key 和 value 都可以是任何引用类型的数据
   - Map 中的 key 用Set来存放，不允许重复，即同一个 Map 对象所对应的类，须重写hashCode()和equals()方法
   - 常用String类作为Map的“键”
- key 和 value 之间存在单向一对一关系，即通过指定的 key 总能找到唯一的、确定的 value

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/235545813240272.png =568x)

- Map中的key：无序的，不可重复的，使用Set存储所有的key 
   - key所在的类要重写equals()和hashCode()
- Map中的value：无序的，可重复的，使用Collection存储所有的value
   - value所在的类要重写equals()
- 一个键值对：key-value构成了一个Entry对象。
- Map中的entry：无序的，不可重复的，使用Set存储所有的entry

### Map接口中定义的方法

**添加、删除、修改操作：**

- `Object put(Object key,Object value)`：将指定key-value添加到(或修改)当前map对象中
- `void putAll(Map m)`:将m中的所有key-value对存放到当前map中
- `Object remove(Object key)`：移除指定key的key-value对，并返回value
- `void clear()`：清空当前map中的所有数据

**元素查询的操作：**

- `Object get(Object key)`：获取指定key对应的value
- `boolean containsKey(Object key)`：是否包含指定的key
- `boolean containsValue(Object value)`：是否包含指定的value
- `int size()`：返回map中key-value对的个数
- `boolean isEmpty()`：判断当前map是否为空
- `boolean equals(Object obj)`：判断当前map和参数对象obj是否相等

**元视图操作的方法：**

- `Set keySet()`：返回所有key构成的Set集合
- `Collection values()`：返回所有value构成的Collection集合
- `Set entrySet()`：返回所有key-value对构成的Set集合

```java
public class LinkedHashMapTest {
    @Test
    public void test1() {
        Map map = new HashMap();

        //put() 添加
        map.put("AA", 123);
        map.put("BB", 456);
        map.put("CC", 789);
        //修改 使用key替换原有的value
        map.put("AA", 999);

        System.out.println(map);//{123=AA, 456=BB, 789=CC}

        Map map1 = new HashMap();
        //putAll()
        map1.putAll(map);
        System.out.println(map);

        //remove() 按照指定的key来移除 返回移除的value值
        System.out.println(map1.remove("AA"));//999
        System.out.println(map1.remove("EE"));//null
        System.out.println(map1);//{BB=456, CC=789}

        //clear() 清空
        map1.clear(); //不等于：map = null
        System.out.println(map1);//{}
        System.out.println(map1.size());//0
    }

    @Test
    public void test2(){
        Map map = new HashMap();
        map.put("AA", 123);
        map.put("BB", 456);
        map.put("CC", 789);
        map.put(12,711);

        //get() 根据key来获取value
        System.out.println(map.get(12)); //711

        //containsKey() 是否包含指定的key
        boolean isExit = map.containsKey("AA");
        System.out.println(isExit);//true

        //containsValue() 是否包含指定的value
        boolean isExit1 = map.containsValue(456);
        System.out.println(isExit1);//true

        //size() 获取Map中键值对的个数
        System.out.println(map.size()); //4

        //isEmpty() 是否为空
        System.out.println(map.isEmpty()); //false

        //equals() 判断map和某个对象是否相等
    }

    @Test
    public void test3(){
        Map map = new HashMap();
        map.put("AA", 123);
        map.put("BB", 456);
        map.put("CC", 789);

        //keySet() 获取所有的key类
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //values() 获取所有的value类
        Collection collection = map.values();
        Iterator iterator1 = collection.iterator();
        while(iterator1.hasNext()){
            System.out.println(iterator1.next());
        }

        //entrySet() 遍历所有的key-value
        //方式1
        Set entrySet = map.entrySet();
        Iterator iterator2 = entrySet.iterator();
        while(iterator2.hasNext()){
            Object obj;
            System.out.println(obj = iterator2.next()); //key=value

            //entrySet集合中的元素都是Entry
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        //方式2
        Set keySet = map.keySet();
        Iterator iterator3 = keySet.iterator();
        while(iterator3.hasNext()){
            Object key = iterator3.next();
            Object value = map.get(key);

            System.out.println(key + "=" + value);
        }
    }
}
```

### HashMap类 底层实现原理

**常量**

- `DEFAULT_INITIAL_CAPACITY` : HashMap的默认容量，16
- `MAXIMUM_CAPACITY` ： HashMap的最大支持容量，2^30
- `DEFAULT_LOAD_FACTOR`：HashMap的默认加载因子(0.75)
- `TREEIFY_THRESHOLD`：Bucket中链表长度大于该默认值，转化为红黑树(8)
- `UNTREEIFY_THRESHOLD`：Bucket中红黑树存储的Node小于该默认值，转化为链表
- `MIN_TREEIFY_CAPACITY`：桶中的Node被树化时最小的hash表容量。
   - 当桶中Node的数量大到需要变红黑树时，若hash表容量小于MIN_TREEIFY_CAPACITY时，此时应执行resize扩容操作这MIN_TREEIFY_CAPACITY的值至少是TREEIFY_THRESHOLD的4倍。
- `table`：存储元素的数组，总是2的n次幂
- `entrySet`：存储具体元素的集
- `size`：HashMap中存储的键值对的数量
- `modCount`：HashMap扩容和结构改变的次数。
- `threshold`：扩容的临界值，= 容量*填充因子 (`16*0.75`)
  - 提前扩容，为了减少链表的长度。
- `loadFactor`：填充因子

**面试题：负载因子值的大小，对HashMap有什么影响**

- 负载因子的大小决定了HashMap的数据密度。
- 负载因子越大密度越大，发生碰撞的几率越高，数组中的链表越容易长,造成查询或插入时的比较次数增多，性能会下降。
- 负载因子越小，就越容易触发扩容，数据密度也越小，意味着发生碰撞的几率越小，数组中的链表也就越短，查询和插入时比较的次数也越小，性能会更高。但是会浪费一定的内容空间。而且经常扩容会影响性能，建议初始化预设大一点的空间。
- 按照其他语言的参考及研究经验，会考虑将负载因子设置为0.7~0.75，此时平均检索长度接近于常数

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

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/331595916239572.png =851x)

**HashMap的底层实现原理 jdk1.8相较与jdk1.7的不同**

- `new HashMap()`底层没有创建一个长度为16 的数组
- jdk1.8 底层的数组是 Node[]  而非Entry[];
- 首次调用put()方法时，底层创建长度为16的数组 
- jdk1.7的底层结构只有数组+链表；jdk1.8的底层结构：数组+链表+红黑树
  - jdk1.7新的元素指向旧的元素，jdk1.8旧的元素指向新的元素. 
  - 当数组的某一个索引位置上的元素以链表形式存在的数据个数 > 8 且当前数组的长度 > 64，此时，此索引位置上的所有数据改为使用红黑树存储。 

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/506635916227439.png =920x)

### LinkedHashMap类 底层实现原理

- 继承自HashMap，底层基本相同

- LinkedMap中定义内部类 Entry替换了HashMap中的Node

```java
static class Entry<K,V> extends HashMap.Node<K,V> {
    Entry<K,V> before, after;
    Entry(int hash, K key, V value, Node<K,V> next) {
        super(hash, key, value, next);
    }
}
```

### TreeSet类

- TreeMap存储 Key-Value 对时，需要根据 key-value 对进行排序。
   - TreeMap 可以保证所有的 Key-Value 对处于有序状态。
- TreeSet底层使用红黑树结构存储数据
- TreeMap要求所有的 Key 应该是同一个类的对象，否则将会抛出 ClasssCastException

**TreeMap 的 Key 的排序：**


- 自然排序：TreeMap 的所有的 Key 必须实现 Comparable 接口
- 定制排序：创建 TreeMap 时，传入一个 Comparator 对象，该对象负责对TreeMap 中的所有 key 进行排序。
  - 此时不需要 Map 的 Key 实现Comparable 接口
- TreeMap判断两个key相等的标准：两个key通过compareTo()方法或者compare()方法返回0。

```java
public class TreeMapTest {
    @Test
    public void test1() {
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
}
```

### Properties类

- Properties 类是 Hashtable 的子类，该对象用于处理属性文件
   - 由于属性文件里的 key、value 都是字符串类型，所以 Properties 里的 key 和 value 都是字符串类型
- 存取数据时，建议使用`setProperty(String key,String value)`方法和`getProperty(String key)`方法
  
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/558883523239572.png =506x)

```java
//建立jdbc.properties

name=Tom\u73A9\u5BB6
password=abc123
```

```java
public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();

        FileInputStream fileIn = new FileInputStream("jdbc.properties");

        properties.load(fileIn); //加载流对应的文件

        String name = properties.getProperty("name");
        String password = properties.getProperty("password");

        System.out.println("name=" + name + "\n" + "passsword=" + password);
        fileIn.close();
    }
}
```

## Collections工具类

- Collections 是一个操作 Set、List 和 Map 等集合的工具类
- Collections 中提供了一系列静态的方法对集合元素进行排序、查询和修改等操作，还提供了对集合对象设置不可变、对集合对象实现同步控制等方法

**排序操作：（均为static方法）**

- `reverse(List)`：反转 List 中元素的顺序
- `shuffle(List)`：对 List 集合元素进行随机排序
- `sort(List)`：根据元素的自然顺序对指定 List 集合元素按升序排序
- `sort(List, Comparator)`：根据指定的 Comparator 产生的顺序对 List 集合元素进行排序
- `swap(List, int, int)`：将指定 list 集合中的 i 处元素和 j 处元素进行交换

**查找、替换**

- `Object max(Collection)`：根据元素的自然顺序，返回给定集合中的最大元素
- `Object max(Collection，Comparator)`：根据 Comparator 指定的顺序，返回给定集合中的最大元素
- `Object min(Collection)`
- `Object min(Collection，Comparator)`
- `int frequency(Collection，Object)`：返回指定集合中指定元素的出现次数
- `void copy(List dest,List src)`：将src中的内容复制到dest中
  - 注意dest的长度问题，报错 
- `boolean replaceAll(List list，Object oldVal，Object newVal)`：使用新值替换List 对象的所有旧值

```java
public class CollectionsTest {
    @Test
    public void test1() {
        List list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add(11);
        list.add(789);

        System.out.println(list); //[123, 456, 11, 789]

        //Collections.reverse(list); 反转
        Collections.reverse(list);
        System.out.println(list); //[789, 11, 456, 123]

        //Collections.shuffle(list); 随机排序
        Collections.shuffle(list);

        //Collections.sort(list) 自然排序
        Collections.sort(list);

        //Collections.sort(list,Comparator) 定制排序
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Integer && o2 instanceof Integer) {
                    Integer i1 = (Integer) o1;
                    Integer i2 = (Integer) o2;

                    return i1 - i2;
                }
                throw new RuntimeException("数据类型不一致");
            }
        };
        Collections.sort(list,comparator);
        System.out.println(list); //[11, 123, 456, 789]

        //Collections.swap(list, int x, int y) 交换list集合中的x和y位置的元素
        Collections.swap(list,1,2);
        System.out.println(list);

        //Collections.frequency(Collection, Object)  出现的频率
        System.out.println(Collections.frequency(list, 11)); //1
    }

    @Test
    public void test2(){
        List list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add(11);
        list.add(789);

        //Collections.copy(toList,fromList) 复制

        //报异常: IndexOutOfBoundsException: Source does not fit in dest
        //List copyToList = new ArrayList();
        //Collections.copy(list,copyToList);

        List toList = Arrays.asList(new Object[list.size()]);
        //System.out.println(toList); //[null, null, null, null]
        Collections.copy(toList,list);
        System.out.println(toList);
    }
}
```

### Collections常用方法 : 同步控制

Collections 类中提供了多个 synchronizedXxx() 方法，该方法可使将指定集合包装成线程同步的集合，从而可以解决多线程并发访问集合时的线程安全问题

```java
@Test
public void test3() {
    List list = new ArrayList();
    list.add(123);
    list.add(456);
    list.add(11);
    list.add(789);

    //Collections.synchronizedXxx() 返回线程安全的
    //返回线程安全的list
    List list1 = Collections.synchronizedList(list);
}
```

## 练习

### 1.

请从键盘随机输入10个整数保存到List中，并按倒序、从大到小的顺序显示出来

```java
public class Test01 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List list = new ArrayList();

        synchronized (scan.getClass()) {
            for (int i = 0; i < 10; i++) {
                list.add(scan.nextInt());
            }
        }

        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Integer && o2 instanceof Integer) {
                    Integer i1 = (Integer) o1;
                    Integer i2 = (Integer) o2;

                    return -i1.compareTo(i2);
                }
                throw new RuntimeException("数据类型不一致");
            }
        };

        Collections.sort(list, comparator);
        System.out.println(list);
        
        Collections.reverse(list);
        System.out.println(list);
    }
}
```


### 2.

请把学生名与考试分数录入到集合中，并按分数显示前三名成绩学员的名字。

TreeSet(Student(name,score,id));

```java
package com.zjk.java1;

import java.util.Iterator;
import java.util.Objects;
import java.util.TreeSet;

public class Test02 {
    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet();
        treeSet.add(new Student("Jac", 78, 01));
        treeSet.add(new Student("Mac", 99, 02));
        treeSet.add(new Student("Tom", 50, 03));
        treeSet.add(new Student("Joc", 60, 04));
        treeSet.add(new Student("King", 33, 05));

        Iterator iterator = treeSet.iterator();

        int i = 0;
        while(iterator.hasNext()){
            if(i++ == 3)
                break;
            System.out.println(iterator.next());
        }

    }
}

class Student implements Comparable {
    private String name;
    private int score;
    private int id;

    public Student(String name, int score, int id) {
        this.name = name;
        this.score = score;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return getId() == student.getId() && Objects.equals(getName(), student.getName()) && Objects.equals(getScore(), student.getScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getScore(), getId());
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Student) {
            Student s = (Student) o;

            return -(this.score - s.score);
        }
        throw new RuntimeException("数据类型不一致");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", id=" + id +
                '}';
    }
}
```

### 3.

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/381045210221147.png =493x)

```java

```

### 4.

 对一个Java源文件中的关键字进行计数。

提示：Java源文件中的每一个单词，需要确定该单词是否是一个关键字。为了高效处理这个问题，将所有的关键字保存在一个HashSet中。用contains()来测试。

```java
File file = new File("Test.java");
Scanner scanner = new Scanner(file);
while(scanner.hasNext()){
    String word = scanner.next();
    System.out.println(word);
}
```

```java

```

# 泛型 （钻石操作符）

## 概念

集合容器类在设计阶段/声明阶段不能确定这个容器到底实际存的是什么类型的对象，所以在JDK1.5之前只能把元素类型设计为Object，JDK1.5之后使用泛型来解决。因为这个时候除了元素的类型不确定，其他的部分是确定的，例如关于这个元素如何保存，如何管理等是确定的，因此此时把元素的类型设计成一个参数，这个类型参数叫做泛型。`Collection<E>，List<E>，ArrayList<E>` 这个`<E>`就是类型参数，即泛型。

- 所谓泛型，就是允许在定义类、接口时通过一个标识表示类中某个属性的类型或者是某个方法的返回值及参数类型。这个类型参数将在使用时（例如，继承或实现这个接口，用这个类型声明变量、创建对象时）确定（即传入实际的类型参数，也称为类型实参）。


## 集合中使用泛型

- 集合接口或集合类在jdk5.0时，都修改为带泛型的结构。JDK1.5改写了集合框架中的全部接口和类，为这些接口、类增加了泛型支持，从而可以在声明集合变量、创建集合对象时传入类型实参。
- 在实例化集合类时，可以指明具体的泛型类型。Java引入了“参数化类型（Parameterized type）”的概念，允许我们在创建集合时再指定集合元素的类型，正如：`List<String>`，这表明该List只能保存字符串类型的对象。
- 指明完之后，在集合类或接口中凡是定义类或接口时，内部结构使用到泛型的位置，都指定为指明的泛型类型
  - 如：`add(E e)` 实例化之后 `add(Integer e)`
   
- 注：泛型中的类型必须是类，不能是基本数据类型，需要用到基本数据类型的位置，要使用其包装类。
- 如果实例化时，没有指明泛型的类型。默认类型为java.lang.Object类型

**JDK 7.0新特性：类型推断：可以只写一部分的<>泛型指明**

- 如：`Map<String,Integer> map = new HashMap<>();`

1. 解决元素存储的安全性问题
2. 解决获取数据元素时，需要类型强制转换的问题

- Java泛型可以保证如果程序在编译时没有发出警告，运行时就不会产生`ClassCastException`异常。同时，代码更加简洁、健壮。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/224281213221147.png =582x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/371651213239573.png =564x)

```java
package com.zjk.java1;

import org.junit.Test;

import java.util.*;

public class GenericTest {
    @Test
    public void test1() {
        //在集合中使用泛型的情况
        ArrayList list = new ArrayList();
        //存放学生的成绩
        list.add(78);
        list.add(22);
        list.add(34);

        //问题1：类型不安全
        //list.add("Tom");

        for (Object obj : list) {
            //问题2：强转时，可能出现ClassCastException，类型转换异常
            int score = (Integer) obj;
            System.out.println(score);
        }
    }

    //以ArrayList为例
    @Test
    public void test2() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(78);
        list.add(37);
        //在编译时，就会进行类型检查，保证数据的安全
        //list.add("we");

        //方式1
        for (Integer i : list) {
            //避免了强转操作
            int score = i;
            System.out.println(i);
        }
        //方式2
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int num;
            System.out.println(num = iterator.next());
        }
    }

    //以HashMap为例
    @Test
    public void test3() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        map.put("Jac", 12);
        map.put("Max", 23);
        map.put("Tom", 34);
        //map.put(123,"sad");

        Set<Map.Entry<String, Integer>> entry = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entry.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> e = iterator.next();
            String key = e.getKey();
            Integer value = e.getValue();
            System.out.println(key + "=" + value);
        }
    }
}
```

```java
public class Employee implements Comparable<Employee>{
    
     ...
    
    //指明泛型时
    @Override
    public int compareTo(Employee e) {
        if (e instanceof Employee) {

            return this.name.compareTo(e.getName());
        } else {
            throw new RuntimeException("数据类型不一致");
        }
    }
}
```

```java
Collection<Employee> treeSet = new TreeSet<Employee>(new Comparator<Employee>() {
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getBirthday().getYear() - e2.getBirthday().getYear();
    }
});
```

## 自定义泛型结构

- 泛型类
- 泛型接口
- 泛型方法

1. 泛型的声明
   - `interface List<T> 和 class GenTest<K,V> ` 
      - 其中，T,K,V不代表值，而是表示类型。这里使用任意字母都可以。常用T表示，是Type的缩写。
2. 泛型的实例化：
   - 一定要在类名后面指定类型参数的值（类型）。如：
   - `List<String> strList = new ArrayList<String>();`
   - `Iterator<Customer> iterator = customers.iterator();`
- T只能是类，不能用基本数据类型填充。但可以使用包装类填充
- 把一个集合中的内容限制为一个特定的数据类型，这就是generics背后的核心思想

### 自定义泛型类/泛型接口

```java
package com.zjk.java2;

public class Order<T> {
    private String orderName;
    private int orderId;

    //类的内部结构就可以使用类的泛型
    T orderT;

    public Order() {

    }

    public Order(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }
}
```

```java
package com.zjk.java2;


//在继承时，指明泛型
public class SubOrder extends Order<String> {
}

//在继承时，不指明泛型
//SubOreder1<T>:仍然是泛型类
class SubOrder1<T> extends Order<T> {
}



package com.zjk.java2;

import org.junit.Test;

public class GenericTest1 {
    @Test
    public void test1() {
        Order o1 = new Order();
        //如果定义了泛型类，实例化没有指明类的泛型，则认为此泛型类型为Object类型
        //要求：如果定义了的类是带泛型的，建议在实例化时指明类的泛型
        o1.setOrderT(123);
        o1.setOrderT("Tom");

        //建议：实例化时指明类的泛型
        Order<String> o2 = new Order<String>("order02", 1002, "Test");

        o2.setOrderT("reTest");
    }

    @Test
    public void test2() {
        //由于子类在继承带泛型的父类时，指明了泛型类型，则实例化子类对象时，不再需要指明泛型。
        SubOrder subOrder = new SubOrder();
        subOrder.setOrderT("Test");


        SubOrder1<String> subOrder1 = new SubOrder1();
        subOrder1.setOrderT("Test");
    }
}
```

**注：**

1. 泛型类可能有多个参数，此时应将多个参数一起放在尖括号内。比如：`<E1,E2,E3>`
2. 泛型类的构造器如下：`public GenericClass(){}`。而下面是错误的：`public GenericClass<E>(){}`
3. 实例化后，操作原来泛型位置的结构必须与指定的泛型类型一致。
4. 泛型不同的引用不能相互赋值。
    - 尽管在编译时`ArrayList<String>和ArrayList<Integer>`是两种类型，但是，在运行时只有一个ArrayList被加载到JVM中。
5. 泛型如果不指定，将被擦除，泛型对应的类型均按照Object处理，但不等价于Object。
    - 经验：泛型要使用一路都用。要不用，一路都不要用。
6. 如果泛型结构是一个接口或抽象类，则不可创建泛型类的对象。
7. jdk1.7，泛型的简化操作：`ArrayList<Fruit> flist = new ArrayList<>();`
8. 泛型的指定中不能使用基本数据类型，可以使用包装类替换。
9. 在类/接口上声明的泛型，在本类或本接口中即代表某种类型，可以作为非静态属性的类型、非静态方法的参数类型、非静态方法的返回值类型。  
    - 但在静态方法中不能使用类的泛型。
10. 异常类不能是泛型的
11. 不能使用`E[] arr = new E[]`。
    - 但是可以：`E[] arr = (E[])new Object[10];`
    - 参考：ArrayList源码中声明：`Object[] elementData`，而非泛型参数类型数组。
12. 父类有泛型，子类可以选择保留泛型也可以选择指定泛型类型：
    - 子类不保留父类的泛型：
       - 按需实现
       - 没有类型 擦除具体类型
    - 子类保留父类的泛型：泛型子类
       - 全部保留
       - 部分保留
    - 结论：子类必须是“富二代”，子类除了指定或保留父类的泛型，还可以增加自己的泛型


```java
@Test
public void test3(){
    ArrayList<String> list1 = null;
    ArrayList<Integer> list2 = null;

    //泛型不同的引用不能相赋值
    //list1 = list2;
}
```

```java
//静态方法中不能使用类的泛型
//类的泛型是在实例化的时候指明的，而静态方法在类中实现
//public static void show(T orderT){
//    System.out.println(orderT);
//}
```

```java
class GenericTest {
    public static void main(String[] args) {
    // 1、使用时：类似于Object，不等同于Object
        ArrayList list = new ArrayList();
    // list.add(new Date());//有风险
        list.add("hello");
        test(list);// 泛型擦除，编译不会类型检查
    // ArrayList<Object> list2 = new ArrayList<Object>();
    // test(list2);//一旦指定Object，编译会类型检查，必须按照Object处理
    }
    public static void test(ArrayList<String> list) {
        String str = "";
        for (String s : list) {
            str += s + ",";
        }
        System.out.println("元素:" + str);
    }
}
```

```java
class Father<T1, T2> {
}
// 子类不保留父类的泛型
// 1)没有类型 擦除
class Son1 extends Father {// 等价于class Son extends Father<Object,Object>{
}
// 2)具体类型
class Son2 extends Father<Integer, String> {
}
// 子类保留父类的泛型
// 1)全部保留
class Son3<T1, T2> extends Father<T1, T2> {
}
// 2)部分保留
class Son4<T2> extends Father<Integer, T2> {
}
```

### 自定义泛型方法

- 方法，也可以被泛型化，不管此时定义在其中的类是不是泛型类。在泛型方法中可以定义泛型参数，此时，参数的类型就是传入数据的类型。

**泛型方法的格式：**

```java
[访问权限] <泛型> 返回类型 方法名([泛型标识 参数名称]) 抛出的异常
```

```java
public class Order<T>{
    //泛型方法：在方法中出现泛型结构，泛型的参数与类的泛型参数没有任何关系
    //即：泛型方法所属的类是否是泛型类均可
    //泛型方法可以是static的,在调用时指明泛型,并非实例化时。
    public static <E> List<E> copyFormArrayToList(E[] arr) {
        ArrayList<E> list = new ArrayList<>();

        for (E e : arr) {
            list.add(e);
        }
        return list;
    }
}


@Test
public void test4() {
    Order<String> order = new Order<>();
    Integer[] arr = new Integer[]{1,2,3,4};
    //泛型方法在调用时指明泛型参数的类型
    List<Integer> list = order.copyFormArrayToList(arr);
    System.out.println(list);
}
```

## 泛型在继承方面的体现

- 类A是类B的父类，
   - 但是`G<A>和G<B>`不具备子父类关系，编译不通过。
      - 二者公共的父类是`G<?>` 
   - 而：`A<G>和B<G>`具备子父类关系

```java
@Test
public void test5() {
    Object obj = null;
    String str = null;
    obj = str;

    Object[] objects = null;
    String[] strings = null;;
    objects = strings;

    //此时的list1和list2不具备子父类关系，是并列的。
    List<Object> list1 = null;
    List<String> list2 = null;
    //list1 = list2;

    GenericTest1 test = new GenericTest1();
    test.show(list1);
    //show(List<Object> list) 参数也不能是是list2
    //test.show(list2);
    test.show1(list2);

    //此时的list01和list02具备子父类关系
    List<String> list01 = null;
    ArrayList<String> list02 = null;
    list01 = list02;
}

public void show(List<Object> list){
}

public void show1(List list){
}
```

## 通配符的使用

### `<?>`

**? 通配符**

- 设类A是类B的父类，`G<?>`作为`G<A>`和`G<B>`的公共父类

**添加**

- 使用通配符?的结构，对于List<?>就不能向其内部添加数据。
- 除了添加null之外；
   
**读取**

- 允许读取数据，读取的数据类型为Object

### 有限制条件的通配符

**<?>**

- 允许所有泛型的引用调用

**通配符指定上限**

- `? extends 类`
- 上限extends：使用时指定的类型必须是(该类的子类)继承某个类，或者实现某个接口，即<= 

**通配符指定下限**

- `? super 类`
- 下限super：使用时指定的类型（该类的父类）不能小于操作的类，即>=

```java
@Test
public void test7() {
    List<? extends Person> list1 = null;
    List<? super Person> list2 = null;

    List<Student> list3 = new ArrayList<Student>();
    List<Person> list4 = new ArrayList<Person>();
    List<Object> list5 = new ArrayList<Object>();

    list1 = list3;
    list1= list4;
    //list1 = list5; ? extends Person 需要小于等于Person

    //list2 = list3; ? super Person 需要大于等于Person
    list2 = list4;
    list2 = list5;

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
}
```

## 泛型嵌套

## 练习

- 定义个泛型类 DAO<T>，在其中定义一个 Map 成员变量，Map 的键为 String 类型，值为 T 类型。
- 分别创建以下方法：
  - public void save(String id,T entity)： 保存 T 类型的对象到 Map 成员变量中
  - public T get(String id)：从 map 中获取 id 对应的对象
  - public void update(String id,T entity)：替换 map 中 key 为 id 的内容,改为 entity 对象
  - public List<T> list()：返回 map 中存放的所有 T 对象
  - public void delete(String id)：删除指定 id 对象
  - 定义一个 User 类：
     - 该类包含：private 成员变量（int 类型） id，age；（String 类型）name。
- 定义一个测试类：
- 创建 DAO 类的对象， 分别调用其 save、get、update、list、delete 方法来操作 User 对象，使用 Junit 单元测试类进行测试

```java
package com.zjk.java2;

import org.junit.Test;

import java.util.*;

public class DAO<T> {
    @Test
    public void test(){
        DAO<User> dao = new DAO<>();

        User user1 = new User(1001,19,"Tom");
        User user2 = new User(1002,21,"Mac");
        User user3 = new User(1003,20,"Jac");

        dao.save("AA",user1);
        dao.save("BB",user2);
        dao.save("CC",user3);

        System.out.println(dao.get("BB"));

        User user4 = new User(1004,33,"King");
        dao.update("BB",user4);

        dao.list().forEach(System.out::println);

        dao.delete("AA");
    }
    
    private Map<String, T> map = new HashMap<String, T>();

    public void save(String id,T entity){
        map.put(id,entity);
    }

    public T get(String id){
        return map.get(id);
    }

    public void update(String id,T entity){
        Set set = map.keySet();
        if(set.contains(id))
            map.put(id,entity);
        else
            System.out.println("未找到数据");
    }


    public List list(){
        Collection<T> collection = map.values();
        List<T> list = new ArrayList<T>();

        Iterator<T> iterator = collection.iterator();
        while(iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }

    //public List list(){
    //    Collection collection = map.values();
    //    List list = Arrays.asList(collection.toArray());
    //    return list;
    //}
    
    //编译通过，但强转错误。Collection -> List错误
    //public List list() {
    //    Collection<T> values = map.values();
    //    return (List<T>) values;
    //}

    public void delete(String id){
        map.remove(id);
    }
}

class User implements Comparable<User>{
    private int id;
    private int age;
    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id == user.id && age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, name);
    }

    @Override
    public int compareTo(User u) {
        return this.age - u.age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
```


# I/O

**I/O是从计算机主机系统的角度上看的**

- 主机系统：运算器，控制器，存储器
   - CPU ： 运算器，控制器，合为CPU
      - 控制器：执行代码
      - 运算器：运算
- 输入：将外部的数据写入主机设备。
   - 读取外部数据（磁盘、光盘等存储设备）到程序（内存）中。 
- 输出：将主机设备的数据写入外部设备。
   - 将程序（内存）数据输出到磁盘、光盘等存储设备中。 

- 键盘和显示器合称标准控制台
  - 键盘：标准输入设备
  - 显示器：标准输出设备
   
## 流式输入/输出

在java中，流(stream)是从源到目的地的字节的有序序列。流中的字节依据先进先出，具有严格顺序，因此流式I/O是一种顺序存取方式。



### 流的概念

| 抽象基类 |    字节流     | 字符流 |
| :------: | :----------: | :----: |
|  输入流  | InputStream  | Reader |
|  输出流  | OutputStream | Writer |

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/207913315221148.png =587x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/113763815239574.png =663x)

- 输入流（读）
   - java程序可以打开一个从某种数据源（如文件、内存等）到程序的一个流，从这个流中读取数据，这就是输入流。因为流是有方向的，所以只能从输入流读入，而不能向它写数据，
   ![](C:/Users/zjk10/OneDrive/NoteBook/pictures/590751810239498.png =340x)
- 输出流（写）
   - 同样，程序可以打开到外界某种目的地的流，把数据顺序写到该流中，以把程序中的数据保存在外界，这就是输出流。与输入流相类似，只能向该流写，而不能从该流中读取数据，
   ![](C:/Users/zjk10/OneDrive/NoteBook/pictures/312871910247531.png =350x)
因此，Java中有两种基本的流——输入流(InputStream)与输出流(OutpurStream)，对这两种流都采取相同的顺序读写方式，

**其读写操作过程如下。**

- 流的读操作过程：打开流→当流中还有数据时执行读操作→关闭流。
- 流的写操作过程，打开流→当有数据需要输出时执行写操作→关闭流。

**Java中实现上述流式I/O的类都在java.io包中。**

**这些类根据流相对于程序的另一个端点的不同:**

分为节点流(Node Stream)和过滤流(Filter Stream)。

- 节点流
   - 以特定源如磁盘文件、内存某区域或线程之间的管道为端点构造的输入/输出流，它是一种最基本的流。
   - 是真正完成读写的
- 过滤流
   - 以其他已经存在的流为端点构造的输入/输出流，称为过滤流或处理流，要对与其相连的另一个流进行某种转换。
   - 可以提高读写操作的某一方面的性能
   - 必须依附于某个节点流

**流类根据流中的数据单位不同也分为两个类的层次体系**

- 字节流：
   - 流中的数据以8位字节为单位进行读写，以InputStream与OutputStream为基础类。
   - 主要用于访问非文本
- 字符流：
   - 流中的数据以16位字符为单位进行读写，以Reader与Writer为基础类。
   - 访问文本
      - ASCII码或Unicode码字符组成的文件
      - 文本文件，简单的判定方法：
         - 即用一个通用的文本编辑器能够打开并读懂的文件 
    
java中流常指的是字节流。

**可以发现实际上字节流与字符流主要的区别在于处理的数据类型。**

Reader/Writer与InputStream/OutputStream具有相类似的API,并且每个核心的输入/输出字节流，都有相应的Reader和Writer版本，

例如FilelnputStream/FileOutputStream与FileReader/FileWriter,PipedInputStream/PipedOutputStream PipedReader/PipedWriter等

### 字节流

- InputStream和OutputStream是字节流的两个顶层父类。它们提供了输入流类与输出流类的通用API。
- 字节流一般用于读写二进制数据，如图像和声音数据。
- 有两个字节流ObjectInputStream和ObjectOutputStream是用来实现对象的串行化，即对象的输入/输出，

#### 输入字节流

InputStream是输入字节流类的抽象顶层父类，它包含了所有输入流类都继承并实现的基本数据读取方法

**read() 基本的读方法。**

InputStream类中最基本的方法应该是read()。该类中定义了如下3个read()方法：

- int read(): 
   - 读一个字节作为方法的返回值。如果返回值是一1，则表示文件结束。
- int read(byte[] b):
   - 将读入的数据放在一个字节数组中，并返回所读的字节数。
- int(byte[] b, int off, int len):
   - 将读入的数据放在一个字节数组中，并返回所读的字节数。
   - 两个整型参数表示所读入数据在数组b中的存放位置。

**void close()**

- 当输入流中的数据读取完毕后，使用该方法关闭流。
- 对于过滤流，则把最顶层的流关闭，会自动自顶向下关闭所有流。

**int available()**

- 返同输人流中还有多少可读的字节。
- 在读取大块数据前，常使用该方法测试。

**long skip(long n)**

- 跳过（扔掉）流中指定字节数量的数据

**流的回读方法。**

可以通过下列3个方法提供"书签"功能，在支持回读的流上实现已读取数据的重复读。

- boolean markSupported():
   - 测试打开的流是否支持同读。
- void mark(int readlimt):
   - 标记当前流，并创建大小由readlimt指示的缓冲区。
   - 方法的参数指定了将来通过reset()方法能够重复读取的字节数。
- void reset()
   - 如果用mark()方法对流做了标记，则在继续从流中读取一定数量的字节后调用reset()方法，将使后续的读操作从标记处开始读数据。
   - 如果在做标记后所读取的字节数大于mark()方法所创建的缓冲区的大小，则reset()方法将不起任何作用
   

#### 输出字节流


OutputStream是输出字节流类的抽象顶层父类。它所包含的成员方法如下。

**write() 基本的写方法**

InputStream类中最基本的方法应该是write()。该类中定义了如下3个write()方法。

- void write(int c):
   - 向输出流中写一个字节。
- void write(byte[] b):
   - 向输出流中写一个字节数组。
- void write(byte[] b, int off, int len):
   - 将字节数组中由off和len指示的数据块写入输出流。
   
**void close()**

- 当完成输出流的写操作后关闭流。如使用过滤流，则把最顶层的流关闭，会自动自顶向下关闭所有流。

**void flush()**

- 该方法将强制将缓存的输出数据写出去。
- 有的输出流会把几次写操作的数据缓存，然后一起提交，而该方法将把这些数据立即写到目的地。
- 一般在调用close()方法关闭流前，可以先调用flush()方法。


### 字符流

- Reader和Writer是java.io包中两个字符流类的顶层抽象父类。它们定义了在输入/输出流中读写16位字符的通用API。
- 字符流能够处理Unicode字符集中的所有字符，而字节流仅限于处理ISO-Latin-1的8位字节。
  - 所以使用字符流来读写文本类数据 
  
#### 输入字符流

**read() 基本的读方法**

- int read() 
  - 读一个字符作为返回值
  - 如果返回值是-1，则表示文件结束
- int read(char[] cbuf)
  - 读字符放入数组中，返回所读的字符数  
- int read(char[] cbuf, int offset, int length)
  - 读字符放入数组的指定位置，返回所读的字符数 
  
**void close()**

- 关闭流

**long skip(long n)**

- 跳过n个字符

**boolean markSupported()**

- 测试打开的流是否支持书签

**void mark(int buf)**

- 标记当前的流，并建立由参数buf指示大小的缓冲区

**void reset()**

- 返回标签处

**boolean ready()**

- 测试当前流是否准备好进行读

#### 输出字符流

Writer类是输出字符流抽象顶层父类。它所包含的成员方法如下。

**int write() 基本的写方法**

- int write(intc):
   - 写单个字符。
- int write(char cbuf[]):
   - 写字符数组。
- int write(char cbuf[], int offset, int length):
   - 写字符数组中的部分数据。
- int write(String str)
   - 写一个字符串。
- int write(String str, int offset, int length):
   - 写字符串的一部分。
   
**void close()**

- 关闭流。

**void flush()**

- 强行写。



### Java流式I/O类概述

#### Java节点流分类与描述

##### Memory(内存I/O)

- 从/向内存数组读写数据
   - ByteArrayInputStream
   - ByteArrayOutputStream
   - CharArrayReader
   - CharArrayWriter
- 从/向内存字符串读写数据
   - StringBufferInputStream
   - StringReader
   - StringWriter 
##### Pipe(管道I/O)

- 实现管道的输入和输出
   - PipedInputStream
   - PipedOutoutStream
   - PipedReader
   - PipedWriter

##### File(文件I/O)
- 统称为文件流，对文件进行读写操作
   - FileInputStream
   - FileOutputStream
   - FileReader
   - FileWriter    

#### Java过滤流(处理流)分类与描述

##### Object Serialization(对象I/O)

- 实现对象的输入/输出
    - ObjectInputStream
    - ObjectOutputStream

##### Data Conversion(数据转换)

- 按基本数据类型读写数据
    - DataInputStream
    - DataOutputStream

##### Printing（打印流）

- 包含方便的打印方法，是最简单的输出流
    - PrintStream
    - PrinterWriter

##### Buffering（缓存I/O）

- 在读入或写出时，对数据进行缓存，以减少I/O的次数。缓存流一般比相类似的非缓存流效率高，并且常与其他流一起使用。
- Buffer，缓存区（内存），专用于解决高速设备和低速设备的匹配。
    - BufferedInputStream
    - BufferedOutputStream
    - BufferedReader
    - BufferedWriter      

##### Filtering（流过滤）

- 是抽象类，定义了过滤流的通用方法。这些方法将在数据读写时进行过滤
   - FilterInputStream
   - FilterOutputStream
   - FilterReader
   - FilterWriter 

##### Concatenation（流连接）

- 将多个输入流连接成一个输入流
   - SequenceInputStream

##### Counting（流数据计数）

- 在读入数据时对行计数
   - LineNumberReader
   - LineNumberInputStream
##### Peeking Ahead（流预读）

- 提供缓存机制，进行预读
   - PushbackInputStream
   - PushbackReader
   
##### Convering between Bytes and Characters（字节与字符转换）

- InputStreamReader按照一定的编码/解码标准将InputStream中的字节转换为字符；
- OutputStreamWriter进行反向转换，即把字符转换为字节

### 输入输出流的套接

- 节点流在程序中不是很常用。一般常通过过滤流将多个流套接在一起，利用各种流的特性共同处理数据。套接的多个流构成了一个流链。
- 流链的中间流与程序所读写的最终流是过滤流，而直接对数据源读写的是节点流。
- 程序中可以根据对外界输入/输出数据的需要构造I/O的流链，以方便数据的处理并提高处理的效率。
   - 一个文件流为了提高效率套接了缓存流，最后套接了数据流以实现按基本数据类型的读取。
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/232134814221067.png =591x)
   - 一个输出流，程序中的数据按数据类型写到数据输出流，再经过缓存最后由文件流写到外存的文件中。
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/289645014239493.png =633x)

### 常用输入/输出流

|     抽象基类      | 节点流（文件流）                                    | 缓存流(处理流的一种)                                              |
| :--------------: | :------------------------------------------------ | :-------------------------------------------------------------- |
| **InputStream**  | **FileInputStream** read(byte[] buffer)           | **BufferedInputStream** read(byte[] buffer)                     |
| **OutputStream** | **FileOutputStream** write(byte[] buffer, 0, len) | **BufferedOutputStream** write(byte[] buffer, 0, len) / flush() |
|    **Reader**    | **FileReader** read(char[] cbuf)                  | **BufferedReader** read(char[] cbuf) / readLine()               |
|    **Writer**    | **FileWriter** write(char[] cbuf, 0, len)         | **BufferedWriter** writer(char[] cbuf, 0, len) / flush()        |

#### 文件流
   
**文件流是节点流，包括以下类:** 

- FileInputStream
- FileOutputStream
- FileReader
- FileWriter 

**FileInputStream类的构造方法**
   
- public FileInputStream(String name)
- public FileInputStream(File file)


##### 字符流

###### FileReader 读入的操作

- `read()`： 返回读入的一个字符；如果达到文件末尾，则返回-1
- `read(char[] cbuf)` 返回每次读入cbuf数组中的字符的个数，如果达到文件末尾，返回-1.
- 为了保证流一定可以执行关闭操作，需要使用`try-catch-finally`处理
- 读入的文件一定要存在，否则报：`java.io.FileNotFoundException`


```java
package com.zjk.java;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderWriterTest {
    @Test
    public void test1() {
        //public void test1() throws IOException {
        //使用throws容易出现资源泄露的问题
        //即：throws在抛出异常对象后，并没有关闭流
        //应该使用try-catch-finally
        FileReader fr = null;

        try {
            //将Hello.txt的文件内容读入程序中，并输出到控制台
            //1.实例化File类的对象，指明要操作的文件
            File file = new File("Hello.txt"); //相对路径，相较于当前Module
            //System.out.println(file.getAbsolutePath());//G:\ideaProjects\JavaSerrior\JavaStudy009\Hello.txt

            //2.提供具体的流
            fr = new FileReader(file);

            //3.数据的读入
            //read() 返回读入的一个字符；如果达到文件末尾，则返回-1
            int data;
            while ((data = fr.read()) != -1) {
                System.out.print((char) data);
            }
//        int data = fr.read();
//        while(data != -1){
//            System.out.print((char) data);
//            data = fr.read();
//        }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //4.流的关闭操作
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2() {

        FileReader fileReader = null;

        try {
            //1.File类的实例化
            File file = new File("Hello.txt");
            //2.FileReader流的实例化
            fileReader = new FileReader(file);
            //3.读入的操作
            //read(char[] cbuf) 返回每次读入cbuf数组中的字符的个数，如果达到文件末尾，返回-1.
            char[] cbuffer = new char[6];
            int len;
            while ((len = fileReader.read(cbuffer)) != -1) {
                //方式2
                String str = new String(cbuffer,0,len);
                System.out.print(str);
                //错误
//                System.out.print(str);
                //方式1
//                for (int i = 0; i < len; i++) {
//                    System.out.print(cbuffer[i]);
//                }
                //错误：
//                for (int i = 0; i < cbuffer.length; i++) {
//                    System.out.print(cbuffer[i]);
//                }
                //test
                //Hello World !orld
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源的关闭
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
```

###### FileWriter 写出的操作

- 从内存中写出数据到硬盘的文件里。
- File对应的硬盘中的文件如果不存在，则在输出的过程中，会自动创建此文件。
- File对应的硬盘中的文件如果存在
  - 如果流使用的构造器是：`FileWriter(file, false);`  或 `FileWriter(file)`，对原有文件执行覆盖。
  - 如果流使用的构造器是：`FileWriter(file, true)` ，则对原有文件执行追加操作。

```java
package com.zjk.java;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {
    @Test
    public void test1() {
        FileWriter fileWriter = null;

        try {
            //1.提供File类的对象，指明写出到的文件，对应的File可以不存在
            //FileWriter fileWriter = new FileWriter(file,false);
            //如果该文件不存在，则在输出的过程中，自动创建文件
            //如果该文件存在，则覆盖
            File file = new File("HelloWrite.txt");

            //2.操作FileWriter的对象，用于数据的写出
            fileWriter = new FileWriter(file);
            //FileWriter fileWriter = new FileWriter(file,false);
            //false : 表示不在文件后面追加，而是直接覆盖
            //true ：表示在文件后面追加

            //3.写出的操作
            fileWriter.write("I have a dream.");
            fileWriter.write("\nyou need to have a dream, too.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                //4.流资源的关闭
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void test2() {
        FileReader fileReader = null;
        FileWriter fileWriter = null;

        try {
            //1.创建File类的对象，指明读入和写入的文件
            File readFile = new File("Hello.txt");
            File writeFile = new File("HelloWriteFile.txt");
            //2.创建输入流和输出流
            fileReader = new FileReader(readFile);
            fileWriter = new FileWriter(writeFile);
            //3.数据的读入和写出操作
            char[] cbuf = new char[5];
            int len;
            while ((len = fileReader.read(cbuf)) != -1) {
                fileWriter.write(cbuf, 0, len);
                //错误
//                fileWriter.write(cbuf);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //4.流资源的关闭
            try {
                if (fileReader != null)
                    fileReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
```


**通过文件字符流实现文件的复制**

1. 创建文件字符输入/输出流
  - `new FileReader("读文件路径")`;
  - `new FileWriter("写文件路径")`; 
2. 读写数据
  - `FileReader对象.read()`
  - `FileWriter对象.write()`; 
3. 关闭流
  - `FileReader对象.close()`
  - `FileWriter对象.close()`

```java
import java.io.*;

/**
* 通过文件字符流实现文件的复制
*/

public class Copy {
    public static void main(String[] args) throws IOException {

        //1.创建文件字符输入/输出流。
//        输入文件
        FileReader in = new FileReader("G:\\javaIOtest\\f.txt");
//        输出文件
        FileWriter out = new FileWriter("G:\\javaIOtest\\o.txt");

        int c;

        //2.读、写数据。
        while ((c = in.read()) != -1)
            out.write(c);

        //3.关闭流。
        in.close();
        out.close();
    }
}
```

##### 字节流

- 对于文本文件，使用字符流处理
   - .txt、.java、.c、.cpp
- 对于非文本文件，使用字节流处理
   - .jpg、.mp3、.mp4、.avi、.doc、.ppt…… 
- 对于文本文件的复制，字节流也是可以的
  - 但是非文本文件的复制，字符流仍然不可以 

使用字节流处理文本文件可能出现乱码。

```java
package com.zjk.java;

import org.junit.Test;

import java.io.*;

public class FileInputStreamTest {
    @Test
    public void test1() throws FileNotFoundException {
        FileInputStream fileInputStream = null;

        try {
            //1.文件
            File file = new File("th.jpg");
            //2.流
            fileInputStream = new FileInputStream(file);
            //3.读数据
            byte[] buffer = new byte[5];
            int len; //记录每次读取的字节个数
            while ((len = fileInputStream.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                //4.流资源的关闭
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*实现对文件的复制操作*/
    @Test
    public void test2() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            File inputFile = new File("th.jpg");
            File outputFile = new File("copyTh.jpg");

            fileInputStream = new FileInputStream(inputFile);
            fileOutputStream = new FileOutputStream(outputFile);

            byte[] buff = new byte[5];
            int len;

            while ((len = fileInputStream.read(buff)) != -1) {
                fileOutputStream.write(buff, 0, len);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*指定路径下文件的复制*/
    public void copyFile(String srcPath, String destPath) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            File inputFile = new File(srcPath);
            File outputFile = new File(destPath);

            fileInputStream = new FileInputStream(inputFile);
            fileOutputStream = new FileOutputStream(outputFile);

            byte[] buff = new byte[1024];
            int len;

            while ((len = fileInputStream.read(buff)) != -1) {
                fileOutputStream.write(buff, 0, len);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void test4() {
        long start = System.currentTimeMillis();

        String src = "th.jpg";
        String dest = "copyTh2.jpg";
        copyFile(src, dest);

        long end = System.currentTimeMillis();

        System.out.println("花费的时间：" + (end - start));
    }
}
```

**通过文件字节流实现文件的复制**

1. 创建File对象
    - `new File("文件路径")`; 
2. 创建文件输入/输出字节流
   - `new FileInputStream(作为输入(读)文件的File对象)`;
   - `new FileOutputStream(作为输出(写)文件的File对象)`;
3. 读写文件流中的数据
   - `FileInputStream对象.read()` 
   - `FileOutputStream对象.write()`
4. 关闭字节流
   - `FileInputStream对象.close()` 
   - `FileOutputStream对象.close()`

```java
import java.io.*;

/**
* 通过文件字节流实现文件的复制
*/
public class CopyBytes {
    public static void main(String[] args) throws IOException {

        //1.创建两个File类对象。
//        输入文件 
        File inputFile = new File("G:\\javaIOtest\\f.txt");
//        输出文件 自动创建
        File outputFile = new File("G:\\javaIOtest\\o.txt"); 

        //2.创建文件输入/输出字节流。
        FileInputStream in = new FileInputStream(inputFile);
        FileOutputStream out = new FileOutputStream(outputFile);

        int c;
        //3.读写文件流中的数据。
        while ((c = in.read()) != -1){
            //c=in.read() 从输入流in读取一个整数赋给c
            //该赋值表达式的值就是c被赋的值
            out.write(c);
        }

        //4.关闭流。
        in.close();
        out.close();
    }
}
```

```java
import java.io.*;

/**
* 通过文件字节流实现文件的复制,
* 并将大写转为小写
*/

public class CopyBytes {
    public static void main(String[] args) throws IOException {

        //1.创建两个File类对象。
//        输入文件
        File inputFile = new File("G:\\javaIOtest\\f.txt");
//        输出文件 自动创建
        File outputFile = new File("G:\\javaIOtest\\o.txt");

        //2.创建文件输入/输出字节流。
        FileInputStream in = new FileInputStream(inputFile);
        FileOutputStream out = new FileOutputStream(outputFile);

        int c;
        //3.读写文件流中的数据。
        while ((c = in.read()) != -1){
            //c=in.read() 从输入流in读取一个整数(ASCII码)赋给c
            //该赋值表达式的值就是c被赋的值

            //将读取的转为小写
            char[] c1 = new char[1];
            c1[0] =(char)c;
            String s = new String(c1);
            char[] c2 = s.toLowerCase().toCharArray();
            c = c2[0];

            out.write(c);
        }

        //4.关闭流。
        in.close();
        out.close();
    }
}
```



#### 缓存流

- 是处理流的一种。
   - 缓存流在创建具体流时，需要给出一个InputStream/OutputStream类型的流作为前端流，
   - 处理流就是“套接”在已有的流的基础上的。
- 内部提供了一个缓冲区，并可以指明缓冲区的大小。
   - 提高流的读取和写入速度。
- 缓存流把数据从原始流成块读入或把数据累积到一个大数据块后再成批写出，通过减少系统资源的读写操作来加快程序的执行。

**缓存流包括以下类：**

- BufferedInputStream
- BufferedOutputStream
- BufferedReader
- BufferedWriter

- 注：BufferedOutputStream类和BufferedWriter类仅仅在缓冲区满或调用flush()方法时，才将数据写到目的地。 

**BufferedInputStream类的构造方法**

- public BufferedInputStream(InputStream in)
- public BufferedInputStream(InputStream in, int size)

**方法**

- flush() 刷新缓冲区

**流资源的关闭：**

- 要求：先关闭外层的流，再关闭内层的流
- 在关闭外层流的同时，内层流也会自动进行关闭。内层流的关闭可以省略
   

##### 字节流

- readLine() 读取一行的数据，不包括换行符 BufferedReader
- newLine() 提供换行的操作。 BufferedWriter

```java
package com.zjk.java;

import org.junit.Test;

import java.io.*;

public class BufferedTest2 {
    @Test
    public void test() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            //流
            bufferedReader = new BufferedReader(new FileReader(new File("Hello.txt")));
            bufferedWriter = new BufferedWriter(new FileWriter(new File("HelloCopy.txt")));

            //读写操作
            //方式2
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                //方法2
                bufferedWriter.write(data); //readLine()返回的，不包括换行符
                bufferedWriter.newLine(); //提供换行的操作
                //方法1
                //此时data中不包含换行符 \n
                bufferedWriter.write(data);
            }
            //方式1
//            char[] cbuf = new char[1024];
//            int len;
//            while ((len = bufferedReader.read(cbuf)) != -1) {
//                bufferedWriter.write(cbuf, 0, len);
//            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //流资源的关闭
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

##### 字符流

```java
package com.zjk.java;

import org.junit.Test;

import java.io.*;

public class BufferedTest2 {
    @Test
    public void test() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            //流
            bufferedReader = new BufferedReader(new FileReader(new File("Hello.txt")));
            bufferedWriter = new BufferedWriter(new FileWriter(new File("HelloCopy.txt")));

            //读写操作
            char[] cbuf = new char[1024];
            int len;
            while ((len = bufferedReader.read(cbuf)) != -1) {
                bufferedWriter.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //流资源的关闭
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
```

#### 转换流

- 属于字符流
- 作用：转换流提供了在字节流和字符流之间的转换

**Java API提供了两个转换流：**

- `InputStreamReader`： 
   - `InputStreamReader(FileInputStream fis)`
   - `InputStreamReader(FileInputStream fis, String charsetName)` 
      - 参数2指明了字符集：具体使用哪个字符集，取决于该文件保存时使用的字符集 
   - 将InputStream转换为Reader
   - 将字节的输入流转换为字符的输入流
   - 解码： 字节/字节数组 --> 字符/字符数组
- `OutputStreamWriter`：
   - `OutputStreamWriter(FileOutputStream fos)` 
   - `OutputStreamWriter(FileOutputStream fos, String charsetName)`
     -  参数2指明了文件输出时使用的编码集
   - 将Writer转换为OutputStream
   - 将字符的输出流转换为字节的输出流
   - 编码：字符/字符数组 --> 字节/字节数组

 字节流中的数据都是字符时，转成字符流操作更高效。

很多时候我们使用转换流来处理文件乱码问题。实现编码和解码的功能。

**字符集**

- ASCII：美国标准信息交换码。
   - 用一个字节的7位可以表示。
   - 字符集基本都兼容ASCII
- ISO8859-1：拉丁码表。欧洲码表
   - 用一个字节的8位表示。
- GB2312：中国的中文编码表。
   - 最多两个字节编码所有字符
- GBK：中国的中文编码表升级，融合了更多的中文文字符号。
   - 最多两个字节编码
- Unicode：国际标准码，融合了目前人类使用的所有字符。
   - 为每个字符分配唯一的字符码。
   - 所有的文字都用两个字节来表示。
- UTF-8：变长的编码方式，可用1-4个字节来表示一个字符。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/190814900221150.png =760x)


```java
package com.zjk.java;

import org.junit.Test;

import java.io.*;

public class IOTest {
    @Test
    public void test1() {
        InputStreamReader isr = null;
        try {
            FileInputStream fis = new FileInputStream(new File("Hello.txt"));
            //new InputStreamReader(fis); 使用系统默认的字符集
//        InputStreamReader isr = new InputStreamReader(fis);
            //new InputStreamReader(fis,"UTF-8"); 使用指定的字符集
            //参数2指明了字符集：具体使用哪个字符集，取决于该文件保存时使用的字符集
            isr = new InputStreamReader(fis, "UTF-8");

            char[] cbuf = new char[20];
            int len;
            while ((len = isr.read(cbuf)) != -1) {
                for (int i = 0; i < len; i++) {
                    System.out.print(cbuf[i]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (isr != null)
                    isr.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void test2(){
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            File file1 = new File("Hello.txt");
            File file2 = new File("HelloIOTest.txt");

            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);

            isr = new InputStreamReader(fis, "utf-8");
            osw = new OutputStreamWriter(fos, "gbk");

            char[] cbuf = new char[20];
            int len;
            while ((len = isr.read(cbuf)) != -1) {
                osw.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (isr != null)
                    isr.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (osw != null)
                    osw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

#### 管道流

- 管道流可以实现线程间数据的直接传输。
  - 线程A可以通过它的输出管道发送数据，
  - 另一个线程B把它的输人管道接到A的输出管道上即可接收A发送的数据。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/396373115227360.png =595x)

- 一个管道由管道输出端（管道输出流）与管道输入端（管道输入流）连接而成。
- 管道的连接实际上是使管道的输入流指向管道的输出流，或管道的输出流也指向管道输入流，这样从管道的输入流就可以读取写入管道输出流的数据了。
- PipedReader//PipedInputStream实现管道的输入流，而PipedWriter/PipedOutputStream实现管道的输出流。

**管道流的创建**

- 管道流的创建是将管道输出流和管道输入流进行挂接。
- 基于管道类的构造方法，可以采取下列两种方式创建管道流：
1. 1
   - PipedInputStream pin =  new PipedInputStream();
   - PipedOutputStream pout =new PipedOutputStream(pin)
2. 2
   - PipedInputStream pin = new PipedInputstream();
   - PipedOutputStream pout = new PipedOutputStrea();
   - pin.connect(out)；或pout.connect(in);

- 管道流创建后，需要把它的输出流连接到一个线程的输出流，并且把它的输入流连接到另一个线程的输入流，才能利用该管道流实现这两个线程之间的数据交流。

**例**

```java
/*
一个单词处理程序。该程序从文件中读人一组单词，
先将每个单词逆序(reverse),
再将所有单词排序(sort),
然后将这些词逆序输出。
最后输出的单词列表能够押韵。
由如下3个程序组成。
·RhymingWords.java:包含main(),reverse()与sort()方法。
  在main()方法中调用reverse()与sort()方法对单词进行处理，并将处理结果输出显示。
·ReverseThread.java:包含执行逆序的线程类。
·SortThread.java:包含执行排序的线程类。
*/
```

#### 数据流  基本数据类型和String的串行化

**数据流包括以下类:**

- DataInputStream 
- DataOutputStream
  - 分别“套接”在 InputStream 和 OutputStream 子类的流上 
- 用于读取或写出基本数据类型的变量或字符串
它们允许按Java的基本数据类型读写流中的数据。为了方便地操作Java语言的基本数据类型和String的数据，可以使用数据流。

**方法:**

|  DataInputStream类提供的读取数据   |  DataOutputStream类相对应的写方法   |
| :-- | :-- |
|   byte readByte()  |   void writeByte(int v)  |
| boolean readBoolean()    |  void writeBoolean(Boolean v)   |
|   char readChar()  |   void writeChar(int v)  |
|    double readDouble() |  void writeDouble(double v)   |
|   float readFloat()  |   void writeFloat(float v)  |
|   int readInt()  |  void writeInt (int v)   |
|  long readLong()   |   void writeLong(long v)  |
|  short readshort()   |  void writeshort(int v)   |
|  String readUTF()  读取以UTF格式保存的字符串   |  void writeUTF(String str)  将字符串以UTF格式写出    |
| - |  void writeBytes(String s) |
| - | void writeChars(String s)  |

- 读取不同类型数据的顺序需要与保存不同类型的数据的顺序相同

```java
package com.zjk.java;

import org.junit.Test;

import java.io.*;

public class DataStreamTest {
    //将内存的字符串\基本数据类型的变量写到文件中
    @Test
    public void test() {
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream("data.txt"));
            dataOutputStream.writeUTF("苏打水");
            dataOutputStream.flush(); //刷新操作，将内存中的数据写入文件
            dataOutputStream.writeBoolean(true);
            dataOutputStream.flush();
            dataOutputStream.writeInt(23);
            dataOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //将文件中存储的基本数据类型变量和字符串读取到内存中，保存在变量中。
    @Test
    public void test2() {
        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(new FileInputStream("data.txt"));

            //读取不同类型数据的顺序需要与保存不同类型的数据的顺序相同
            //否则可能出错，或数据不对。
            String name = dataInputStream.readUTF();
            boolean sex = dataInputStream.readBoolean();
            int age = dataInputStream.readInt();

            System.out.println(name + ": " + age + ": " + sex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
```

```java
import java.io.*;

public class DataIOTest {

    public static void main(String[] args) throws IOException {

        // 创建数据输出流，前端套接文件流并以invoice1.txt为输出目的地。
        DataOutputStream out = new DataOutputStream(new FileOutputStream("invoice1.txt"));
//        数据流作为过滤流，可以实现基本数据类型读写数据

        //定义要保存的数据数组。
        double[] prices = {19.99, 9.99, 15.99, 3.99, 4.99};
        int[] units = {12, 8, 13, 29, 50};
        String[] descs = {"Java T-shirt", "Java Mug", "Duke Juggling Dolls", "Java Pin", "Java Key Chain"};

        //将prices,unites以及descs中的数据以Tab键为分割符保存在文件中。
        for (int i = 0; i < prices.length; i++) {
            out.writeDouble(prices[i]);
            out.writeChar('\t');
            out.writeInt(units[i]);
            out.writeChar('\t');
            out.writeUTF(descs[i]);
            out.writeChar('\t');
        }
        out.close();

        // 创建数据输入流，将上面保存的文件再次打开并读取。
        DataInputStream in = new DataInputStream(new FileInputStream("invoice1.txt"));

        double price;
        int unit;
        String desc;
        double total = 0.0;

        for (int i = 0; i < prices.length; i++) {
            price = in.readDouble();
            in.readChar();       // 扔掉tab键
            unit = in.readInt();
            in.readChar();       // 扔掉tab键
            desc = in.readUTF();
            in.readChar();   // 扔掉tab键

            System.out.println("You've ordered " + unit + " units of " + desc + " at $" + price);
            total = total + unit * price;
        }

        System.out.println("For a TOTAL of: $" + total);
        in.close();
    }
}
```

#### 标准输入/输出

- Java中，标准输入是键盘，标准输出是显示器屏幕（更准确地说是加载Java程序的命令窗口)。Java程序使用字符界面与系统标准输入/输出间进行数据通信。
- 因为从键盘读入数据或向屏幕输出数据是十分常见的操作，每次操作都创建输入/输出流将影响系统的运行效率。
- 为此Java在System类中定义了与系统标准输入/输出相联系的3个流
   - System.in 标准输入流 默认从键盘输入
   - System.out 标准输出流 默认从控制台输出
   - System.err

**System类**

- System是Java中一个功能强大的类，利用它可以获得Java运行时的系统信息。
   - System类的所有变量和方法都是static，即通过类名System可以直接调用。
   - System.in，System.out与System.err就是System类的3个静态变量。
- System.in完整定义是`public static final InputStream in`，是标准输入流。
   - 这个流在程序运行时一直打开并准备好提供输入数据。该流一般对应于键盘输入。
- System.out完整定义是`public static final PrintStream out`，是标准输出流。
   - 这个流在程序运行时一直打开并准备好接收输出的数据。
   - 该流一般对应于屏幕。
- System.err完整定义是`public static final PrintStream err`，是标准错误输出流。
   - 这个流在程序运行时一直打开并准备好接收输出的数据。
   - 该流一般对应于屏幕并且用来显示错误消息或其他能够马上引起用户注意的信息。

**重定向输入/输出**

- System类的`setIn(InpuStream in) / setOut(PrintStream out)`重新指定输入和输出的流

##### System.in 标准输入

- Java的标准输人System.in是`InputStream类的对象`。
- 当程序中需要从键盘读入数据的时候， 默认从键盘输入
   - 只需System.in的`read()`方法即可。
   - 也可以在System.in上套接其他过滤流，这样可以使用更方便的方法从标准输人流上读取数据。

**在使用`System.in.read()`方法时需要注意以下几点。**

- 必须使用`try catch`对System.in.read()可能抛出的`IOException`类型的异常进行处理。
- 执行System.in.read()方法将从键盘缓冲区读入一个字节的数据，但返回的是**16位的整型值**，<mark>该整型值只有低位字节是真正输人的数据，高位字节全部是零</mark>
- System.in.read()方法的执行将使整个程序被挂起，直到用户从键盘输人数据才继续运行

##### System.out 标准输出

Java的标准输出System.out是`打印输出流PrintStream类的对象`。

##### 打印流

- 打印流：PrintStream和PrintWriter
- 提供了一系列重载的print()和println()方法，用于多种数据类型的输出
- PrintStream和PrintWriter的输出不会抛出IOException异常
- PrintStream和PrintWriter有自动flush功能
-  PrintStream 打印的所有字符都使用平台的默认字符编码转换为字节。
- 在需要写入字符而不是写入字节的情况下，应该使用 PrintWriter 类。
- System.out返回的是PrintStream的实例

**PrintStream是一种过滤流，其中定义了在屏幕上显示不同类型数据的方法print()和println()。**

**println()方法**

- println()方法向屏幕输出其参数指定的变量或对象，然后再换行，光标停留在屏幕下一行第一个字符的位置。
   - 如果该方法的参数为空，则输出一个空行。
- println()方法可输出多种不同类型的变量或对象，包括boolean、double、float、int、long,char、字符数组以及Object类型的对象。
   - 因为Object类是Java中所有类的父类，所以使用println()方法可以在屏幕上输出所有类的对象。

**print()方法**

- print()方法与println()方法相类似，也可以将不同类型的变量与对象输出到屏幕。
- 不同的是pint()方法输出后不换行，下次输出时将显示在同一行中。

**Juint单元测试不支持标准输入在控制台的输入 read-only**

```java
//从键盘输入字符串，要求将读取到的整行字符串转成大写输出。
//然后继续进行输入操作，直至当输入“e”或者“exit”时，退出程序。
package com.zjk.java;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemIOtest {
    @Test
    public static void main(String[] args) {
        BufferedReader bfr = null;
        try {
            //方法1 使用Scanner类,调用next()返回一个字符串

            //方法2 System.in实现System.in --> 转换流 --> BufferedReader.readLine()
            InputStreamReader isr = new InputStreamReader(System.in);
            bfr = new BufferedReader(isr);

            while (true) {
                System.out.println("请输入");
                String data = bfr.readLine();
                if ("e".equals(data.toLowerCase()) || "exit".equals(data.toLowerCase())) {
                    System.out.println("程序结束");
                    break;
                }

                String upperCase = data.toUpperCase();
                System.out.println(upperCase);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bfr.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

```java
import java.io.*;

public class StandardIO {

    public static void main(String[] args) {
        String s;

//        节点流InputStreamReader和过滤流BufferedReader的套接
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        System.in和System.out类似，是System类的一个对象，表示键盘

        System.out.println("Please input : ");

        //对流操作时，需要try-catch，因为容易出错
        try {
            s = in.readLine();
            while (!s.equals("exit")) {
                System.out.println("  read: " + s);
                s = in.readLine();
            }
            System.out.println("End of Inputting.");
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

```java
package com.zjk.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintTest {

    public static void main(String[] args) {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File("G:\\JavaIOTest\\o.txt"));
            // 创建打印输出流,设置为自动刷新模式(写入换行符或字节 '\n' 时都会刷新输出缓冲区)
            ps = new PrintStream(fos, true);
            if (ps != null) {// 把标准输出流(控制台输出)改成文件
                System.setOut(ps);
            }
            for (int i = 0; i <= 255; i++) { // 输出ASCII字符
                System.out.print((char) i);
                if (i % 50 == 0) { // 每50个数据一行
                    System.out.println(); // 换行
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}
```

#### 练习

##### 1.

分别使用节点流：FileInputStream、FileOutputStream和缓冲流：BufferedInputStream、BufferedOutputStream实现文本文件/图片/视频文件的复制。并比较二者在数据复制方面的效率

```java
package com.zjk.java;

import org.junit.Test;

import java.io.*;

public class Test1 {
    @Test
    public void test() {
        String src = "th.jpg";
        String dest = "thtest.jpg";

        long startF = System.currentTimeMillis();
        fileCopy(src, dest);
        long endF = System.currentTimeMillis();

        long startB = System.currentTimeMillis();
        fileCopy(src, dest);
        long endB = System.currentTimeMillis();

        System.out.println("节点流：" + (endF - startF));
        System.out.println("缓存流：" + (endB - startB));
    }

    public void bufferedCopy(String src, String dest) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(new File(src)));
            bos = new BufferedOutputStream(new FileOutputStream(new File(dest)));

            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void fileCopy(String src, String dest) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(new File(src));
            fos = new FileOutputStream(new File(dest));

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

##### 2.实现图片加密操作。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/527985022221148.png =341x)

```java
package com.zjk.java;

import org.junit.Test;

import java.io.*;

public class Test2 {
    @Test
    public void test() {
        String src = "th.jpg";
        String secretFile = "secretTh.jpg";
        String decryptionFile = "decryptionTh.jpg";

        secret(src, secretFile);
        decryption(secretFile, decryptionFile);
    }


    public void secret(String src, String dest) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(new File(src)));
            bos = new BufferedOutputStream(new FileOutputStream(new File(dest)));

            byte[] buffer = new byte[20];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                for (int i = 0; i < len; i++) {
                    bos.write(buffer[i] ^ 5);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void decryption(String src, String dest) {
        secret(src, dest);
    }
}
```

##### 3.获取文本上每个字符出现的次数

提示：遍历文本的每一个字符；字符及出现的次数保存在Map中；将Map中数据写入文件

```java
public class WordCount {
    @Test
    public void testWordCount() {
        FileReader fr = null;
        BufferedWriter bw = null;
        try {
            //1.创建Map集合
            Map<Character, Integer> map = new HashMap<Character, Integer>();

            //2.遍历每一个字符,每一个字符出现的次数放到map中
            fr = new FileReader("dbcp.txt");
            int c = 0;
            while ((c = fr.read()) != -1) {
                //int 还原 char
                char ch = (char) c;
                // 判断char是否在map中第一次出现
                if (map.get(ch) == null) {
                    map.put(ch, 1);
                } else {
                    map.put(ch, map.get(ch) + 1);
                }
            }

            //3.把map中数据存在文件count.txt
            //3.1 创建Writer
            bw = new BufferedWriter(new FileWriter("wordcount.txt"));

            //3.2 遍历map,再写入数据
            Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
            for (Map.Entry<Character, Integer> entry : entrySet) {
                switch (entry.getKey()) {
                    case ' ':
                        bw.write("空格=" + entry.getValue());
                        break;
                    case '\t'://\t表示tab 键字符
                        bw.write("tab键=" + entry.getValue());
                        break;
                    case '\r'://
                        bw.write("回车=" + entry.getValue());
                        break;
                    case '\n'://
                        bw.write("换行=" + entry.getValue());
                        break;
                    default:
                        bw.write(entry.getKey() + "=" + entry.getValue());
                        break;
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关流
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
```

## 文件 File类

- 声明在java.io包下  

**路径分隔符**

在java中需要换成：`/`或`\\`

- Windows或DOS系统：\  --> `\\`
- Unix或URL：/  --> `/`

**路径**

- 相对路径：相较于某个路径下，指明的路径
- 绝对路径：包含盘符在内的文件或文件的目录的路径

- 在IDEA中
  - 如果使用main，则相对路径在当前工程下
  - 如果使用JUnit，则相对路径在当前Module下(即与src文件同级目录，而不是包内的)
- 在Eclipse中
  - 相对路径都在当前工程下 

**Java中的文件类File是外存文件和目录的抽象表示。**

- File类用来操作文件和获得文件的信息，但不提供对文件数据读取的方法，这些方法由文件流提供。
- 通过File类的方法，可以得到文件或目录的描述信息，包括文件名、路径，可读写性，长度等，还可以生成新的目录，临时文件，改变文件名，删除文件，列出一个目录中所有的文件或满足某种模式的文件等。

### 创建File对象

- 可以使用File类的构造方法来创建File类的对象(在Java中目录也当作文件)，
   - 因此File对象可以表示一个磁盘文件，也可以表示某个目录。

**File类常用的构造方法如下**

- `public File(String pathname)`
   - 参数pathname指定新创建的File对象对应的磁盘文件或目录名及其路径名。
   - pathname可以是绝对路径，如，“d:\works\source\myfile..txt”,
   - 也可以是相对路径，如，“source\myfile.txt”,表示当前目录的source目录下的myfile..txt。
      - 为了保证程序的可移植性，应该尽量使用相对路径。例如：`File myFile =  new File("myfile.txt");`
- `public File(String parent,String child)`
   - 参数parent指定了文件或目录的父目录的绝对或相对路径
   - 参数child指定了文件或目录名。
   - 将路径与名称分开的好处是相同路径的文件或目录可共享同一个路径字符串，便于管理和修改。
- `public File(File parent,String child)`
   - 参数parent是已经存在的代表文件或父目录的File类对象，
   - 参数child表示文件或目录名。
   
```java
public class FileTest {
    public static void main(String[] args) {
        //构造器1：new File(pathname)
        //相对路径：相较于某个路径下，指明的路径
        //即：相对于当前的module
        File file1 = new File("Hello.txt");
        //绝对路径：包含盘符在内的文件或文件的目录的路径
        File file2 = new File("G:\\ideaProjects\\JavaSerrior\\JavaStudy009\\src\\com\\zjk\\java\\Hello.txt");

        //构造器2：new File(parentPath, childPath);
        File file3 = new File("G:\\ideaProjects","JavaSerrior");

        //构造器3：new File(parentPath, childFile)
        File file4 = new File("G:\\javaIOTest","f.txt");        
    }
}
```

### 操作File对象

- 在创建了File对象后，就可以使用下列方法获取文件信息或进行其他操作。
- 并没有涉及到文件内容的操作：需要IO流
- File类的对象常会作为此参数，传递到流的构造器中，指明读取或写入的“终点”

**文件名的操作**

- `public String getName()` 获取文件名
- `public String getParent()` 返回此抽象路径名的父目录的路径名字符串，如果此路径名未命名为父目录，则返回null。
- `public String getPath()` 将此抽象路径名转换为路径名字符串
- `public String getAbsolutePath()` 获取文件的绝对路径 （返回此抽象路径名的绝对形式。）
- `public boolean renameTo(File dest)` 将文件重命名为dest所对应的文件名
  - 要想保证返回true，需要file在硬盘中是存在的，且dest不能在硬盘中存在(即不能存在同名的文件)
   
**文件信息测试**

- `public boolean isAbsolute()` 测试这个抽象路径名是否是绝对的。
- `public boolean canRead()` 文件是否可读
- `public boolean canWrite()` 文件是否可写
- `public boolean exists()` 文件是否存在（文件名）
- `public boolean isDirectory()` 是否是文件夹
- `public boolean isFile()` 是否是文件
- `public boolean isHidden()` 判断文件是否隐藏

**获取文件一般信息**

- `public long length()` 返回由此抽象路径名表示的文件的长度。
- `public long lastModified()` 返回此抽象路径名表示的文件上次修改的时间。
   - 返回的是时间戳 
    
**目录操作**

需要对应的File对象是文件目录；否则空指针。

- `public String[] list()` 将目录中所有文件名保存在字符数组中返回
- `public File[] listFiles()` 获取指定目录下的所有文件或者文件目录的File数组

**创建**

如果在创建文件或文件目录时，没有写盘符路径，那么默认在项目路径下。

- `public boolean mkdir()` 创建由此抽象路径名命名的目录。
   - 如果此文件目录存在，则不创建。
   - 如果此文件目录的上层目录不存在，也不创建。
- `public boolean mkdirs()` 创建由此抽象路径名命名的目录，包括任何必需但不存在的父目录
   - 如果上层文件目录不存在，一并创建 
- `public boolean createNewFile()` 创建文件，若文件存在，则不创建，返回false
  
**删除**

- `public boolean delete()` 删除文件或目录
  - java中的删除不走回收站
  - 要删除一个文件目录，则文件目录内不能包含文件或文件目录。 

```java
@Test
public void test1() {
    File file1 = new File("Hello.txt");
    File file2 = new File("G:\\ideaProjects\\JavaSerrior\\JavaStudy009\\src\\com\\zjk\\java\\Hello.txt");

    System.out.println(file1.getAbsolutePath()); //G:\ideaProjects\JavaSerrior\JavaStudy009\Hello.txt
    System.out.println(file1.getPath()); //Hello.txt
    System.out.println(file1.getName()); //Hello.txt
    //找不到该文件，文件不存在 返回默认值
    System.out.println(file1.getParent()); //null
    System.out.println(file1.length()); //0
    System.out.println(file1.lastModified()); //0

    System.out.println(file2.getAbsolutePath()); //G:\ideaProjects\JavaSerrior\JavaStudy009\Hello.txt
    System.out.println(file2.getPath()); //G:\ideaProjects\JavaSerrior\JavaStudy009\src\com\zjk\java\Hello.txt
    System.out.println(file2.getName()); //Hello.txt
    System.out.println(file2.getParent()); //G:\ideaProjects\JavaSerrior\JavaStudy009\src\com\zjk\java
    System.out.println(file2.length()); //19
    System.out.println(file2.lastModified()); //1667742367361
}

@Test
public void test2() {
    File file = new File("G:\\javaIOTest");
    //list()和listFiles() 需要相应的File对象是文件目录
    //否则:java.lang.NullPointerException

    //list() 返回文件的相对路径名 如：outFile2.txt
    String[] list = file.list();
    for (String str : list) {
        System.out.println(str);
    }

    //listFiles() 返回文件的绝对路径名 如：G:\javaIOTest\date.ser
    File[] files = file.listFiles();
    for (File f : files) {
        System.out.println(f);
    }
}

@Test
public void test3(){
    File file = new File("G:\\javaIOTest\\f.txt");
    File refile = new File("G:\\javaIOTest\\t.txt");
    //renameTo()
    //如果要改名的位置上有同名的文件，则重命名失败
    System.out.println(file.renameTo(refile));
}

@Test
public void test4(){
    File file = new File("G:\\javaIOTest\\f.txt");

    System.out.println(file.isFile()); //true
    System.out.println(file.isDirectory()); //false
    System.out.println(file.exists()); //true
    System.out.println(file.canRead()); //true
    System.out.println(file.canWrite()); //true
    System.out.println(file.isHidden()); //false

    File dir = new File("G:\\javaIOTest");

    System.out.println(dir.isFile()); //false
    System.out.println(dir.isDirectory()); //true
    System.out.println(dir.exists()); //true
    System.out.println(dir.canRead()); //true
    System.out.println(dir.canWrite()); //true
    System.out.println(dir.isHidden()); //false
}

@Test
public void test6() throws IOException {
    //文件的创建和删除
    File file = new File("G:\\javaIOTest\\f.txt");
    if(!file.exists()){
        //创建文件
        file.createNewFile();
        System.out.println("创建成功");
    }else{
        //删除文件
        file.delete();
        System.out.println("删除成功");
    }

    //文件目录的创建和删除
    File dir = new File("G:\\javaIOTest\\javaIODir");
    if(!dir.exists()){
        //创建文件目录
        dir.mkdirs();
        System.out.println(dir.getAbsolutePath());
    }else{
        //删除文件目录
        dir.delete();
        System.out.println("删除成功");
    }
}
```

```java
public class RenameFile {

    //显示文件基本信息。
    //File f = new File("G:/javaIOTest/f.txt");
    private static void fileData(File f) {
        System.out.println(
                "Absolute path: " + f.getAbsolutePath() + //文件绝对路径 G:\javaIOTest\f.txt
                        "\n Can read: " + f.canRead() + //是否可写 true
                        "\n Can write: " + f.canWrite() + //是否可读 true
                        "\n getName: " + f.getName() + //获取文件名（没有路径）f.txt
                        "\n getParent: " + f.getParent() + //获取文件的父目录 G:\javaIOTest
                        "\n getPath: " + f.getPath() + //获取文件完整路径 G:\java\IOTest\f.txt
                        "\n length: " + f.length() + //文件内容的长度
                        "\n lastModified: " + new Date(f.lastModified()));
        if (f.isFile())
            System.out.println("It's a file");
        else if (f.isDirectory())
            System.out.println("It's a directory");
    }

    //将命令行第一个参数是原来的文件名，第二个参数是新文件名。
    public static void main(String[] args) {
        args = new String[3];
        args[0] = "G:/javaIOTest/f.txt";
        args[1] = "G:/javaIOTest/new.txt";

        File old = new File(args[0]);
        File rname = new File(args[1]);

        System.out.println("The original file's information:");
        fileData(old);
        old.renameTo(rname); //文件重命名。
        System.out.println("\n The file information after rename:");
        fileData(rname);
        if (!old.exists()) {  //文件是否存在
            System.out.println("\n The original file never exists.");
        }
    }
}
```

### 练习

#### 1.

利用File构造器，new 一个文件目录file

1)在其中创建多个文件和目录

2)编写方法，实现删除file中指定文件的操作

```java
import java.io.File;
import java.io.IOException;

public class FileTest1 {
    public static void main(String[] args) throws IOException {
        File dir = new File("G:\\javaIOTest");

        if(!dir.exists()){
            dir.mkdirs();
        }

        File file1 = new File(file"file1.txt");
        System.out.println(file1.createNewFile());
        File dir2 = new File("G:\\javaIOTest\\IOdir");
        System.out.println(dir2.mkdir());

        FileTest1 test = new FileTest1();
        test.deleteFile("G:\\javaIOTest\\IOdir");
    }

    public void deleteFile(String path) {
        File deleteFile = new File(path);
        if (deleteFile.exists()) {
            deleteFile.delete();
            System.out.println("删除成功");
        } else {
            System.out.println("该文件不存在");
        }
    }
    public void deleteFile(File file){
        if(file.exists()){
            file.delete();
            System.out.println("删除成功");
        }else{
            System.out.println("该文件不存在");
        }
    }
}
```

#### 2.

判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称

```java
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class FileTest2 {
    public static void main(String[] args) {
        File dir = new File("G:\\javaIOTest\\javaIO");
        System.out.println(checkJpg(dir));
    }

    public static Collection<String> checkJpg(File dir) {
        if (dir.exists()) {
            String[] fileNames = dir.list();
            Collection collection = new ArrayList();

            for (String str : fileNames) {
                if (str.endsWith(".jpg")) {
                    collection.add(str);
                }
            }

            if (!collection.isEmpty()) {
                return collection;
            } else {
                System.out.println("不存在后缀为.jpg的文件");
                return null;
            }

        } else {
            throw new RuntimeException("该文件目录不存在");
        }
    }
}
```

#### 3.

遍历指定目录所有文件名称，包括子文件目录中的文件。

拓展1：并计算指定目录占用空间的大小

拓展2：删除指定文件目录及其下的所有文件

```java
import java.io.File;

public class FileTest3 {
    public static void main(String[] args) {
        File dir = new File("G:\\javaIOTest");
        toList(dir);
    }

    public static void toList(File dir) {
        if (!dir.exists())
            throw new RuntimeException("文件目录不存在");

        File[] fileList = dir.listFiles();

        for (int i = 0; i < fileList.length; i++) {
            System.out.println(fileList[i]);
            if(fileList[i].isDirectory()){
                forFiles(fileList[i]);
            }
        }

    }

    public static void forFiles(File dir){
        System.out.println("----进入文件目录：" + dir);
        File[] list = dir.listFiles();
        for(int i = 0;i < list.length;i++){
            System.out.println(list[i]);
            if(list[i].isDirectory()){
                forFiles(list[i]);
            }
        }
        System.out.println("----离开文件目录：" + dir);
    }
}
```

```java
import java.io.File;

public class FileTest3 {
    public static void main(String[] args) {
        File dir = new File("G:\\javaIO");
        toList(dir);
    }

    public static void toList(File dir) {
        if (!dir.exists())
            throw new RuntimeException("文件目录不存在");

        File[] list = dir.listFiles();

        for (int i = 0; i < list.length; i++) {
            if (list[i].isDirectory()) {
                forFiles(list[i]);
                deleteFile(list[i]);
            }else{
                deleteFile(list[i]);
            }
        }
    }

    public static void forFiles(File dir) {
        System.out.println("----进入文件目录：" + dir);
        File[] list = dir.listFiles();
        for (int i = 0; i < list.length; i++) {

            if (list[i].isDirectory()) {
                forFiles(list[i]);
                deleteFile(list[i]);
            } else {
                deleteFile(list[i]);
            }
        }
        System.out.println("----离开文件目录：" + dir);
    }

    public static void deleteFile(File file) {
        if (file.delete()) {
            System.out.println(file + "已删除");
        } else {
            System.out.println(file + "删除失败");
        }
    }
}
```

## RandomAccessFile类 随机存取文件

- 随机存取文件类RandomAccessFile，可以实现对文件的随机读写操作，
- RandomAccessFile类不是从InputStream类或OutputStream类派生的，而是继承Object类
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/350645010221143.png =201x)

- 既可以作为输入流也可以作为输出流。
- RandomAccessFile类实现了DataInput和DataOutput接口，所以对于支持这两个接口的过滤器将适用于RandomAccessFile。

### 随机存取文件的操作

**RandomAccessFile类提供两个构造方法：**

- `public RandomAccessFile(String name,String mode) throws FileNotFoundException`
- `public RandomAccessFile(File file,String mode) throws FileNotFoundException`

     - 参数mode有4种取值：""
     - r  —— 以只读方式打开文件；
        - 如果模式为只读r。则不会创建文件，而是会去读取一个已经存在的文件，如果读取的文件不存在则会出现异常。 
     - rw —— 以读写方式打开文件，
        - 则用一个RandomAccessFile对象就可以同时进行读写两种操作；
        - 如果模式为rw读写。如果文件不存在则会去创建文件，如果存在则不会创建。
     - rwd —— 以读写方式打开文件，并且要求对文件内容的更新要同步地写到底层存储设备；
       - "rwd"模式可用于减少执行的I / O操作数。 使用"rwd"只需要更新要写入存储的文件内容 
     - rws —— 与rwd基本相同，只是还可以更新文件的元数据(MetaData)。
       - 使用"rws"需要更新要写入的文件的内容及其元数据，这通常需要至少一个低级I / O操作。

**作为输入流 RandomAccessFile类的读方法**

- read()
- readBoolean()
- readChar()
- readInt()
- readLong()
- readFloat()
- readDouble()
- readLine()
  - 从当前位置开始，到第一个\n'为止，读取一行文本，它将返回一个String对象。 
- readUTF()
- readFully(byte[] b)
    - 从此文件读取 b.length字节到字节数组，从当前文件指针开始。
- void readFully(byte[] b, int off, int len)
    - 从此文件中读取 len个字节到字节数组，从当前文件指针开始。

**作为输出流 RandomAccessFile类包含的写方法**

- write()
- writeBoolean()
- writeChar()
- writeUTF()
- writeInt()
- writeLong()
- writeFloat()
- writeDouble()

**RandomAccessFile作为输出流时 (至少是rw模式时)**

- 如果写出到的文件不存在，则在执行过程中自动创建
- 如果写出到的文件存在，则会对原有文件内容进行覆盖（默认情况下，从头覆盖）

**其他**

- `getChannel()` 
   - 返回与此文件关联的唯一的FileChannel对象。
- `getFD()`
   - 返回与此流关联的不透明文件描述符对象。

#### 文件指针的操作

- 文件指针是指以字节为单位的相对于文件开头的偏移量，是下次读写的起点。

**文件指针的运行规律**

- RandomAceessFile对象的文件指针位于文件的开头处；
- 操作后，文件位置指针都相应后移读写的字节数。

**文件指针操作方法**

- `long getFilePointer()`:
   - 返回当前文件指针，即从文件开头算起的绝对位置。
- `void seek(long pos)`
   - 将文件指针定位到指定位置。
   - 参数pos是相对于文件开头的绝对偏移量。
- `long length()`:
   - 返回文件长度。
   - 可以通过将文件长度与文件指针相比较，判断是否读到了文件尾。
- `int skipBytes(int n)`
   - 从当前位置开始跳过n个字节，返回值表示实际跳过的字节数。

**可通过相关的操作，实现RandomAccessFile插入数据的效果**

```java
package com.zjk.java2;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {
    @Test
    public void test1() {
        RandomAccessFile randomAccessFile1 = null;
        RandomAccessFile randomAccessFile2 = null;
        try {
            randomAccessFile1 = new RandomAccessFile(new File("Hello.txt"), "r");
            randomAccessFile2 = new RandomAccessFile(new File("HelloRandomAccessFileTest.txt"), "rw");

            byte[] buf = new byte[20];
            int len;
            while ((len = randomAccessFile1.read(buf)) != -1) {
                randomAccessFile2.write(buf, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (randomAccessFile1 != null)
                    randomAccessFile1.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (randomAccessFile2 != null)
                    randomAccessFile2.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void test2() {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(new File("Hello.txt"), "rw");

            //将指针调到下标为3的位置。
            randomAccessFile.seek(3);

            //RandomAccessFile作为输出流时 rw
            //如果写出到的文件不存在，则在执行过程中自动创建
            //如果写出到的文件存在，则会对原有文件内容进行覆盖（默认情况下，从头覆盖）
            randomAccessFile.write("take a test".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*使用RandomAccessFile实现数据的插入操作*/
    @Test
    public void test3() throws IOException {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile("Hello.txt", "rw");

            //将指针调到下标3的位置
            randomAccessFile.seek(3);

            //保存指针下标3以后的所有数据到StringBuilder中
            StringBuilder stringBuilder = new StringBuilder((int) new File("Hello.txt").length());
            byte[] buffer = new byte[20];
            int len;
            while ((len = randomAccessFile.read(buffer)) != -1) {
                stringBuilder.append(new String(buffer, 0, len));
            }

            //调回指针到下标3，并写入数据
            randomAccessFile.seek(3);
            randomAccessFile.write("juts for a test".getBytes());

            //将StringBuilder中的数据从当前指针的位置插入
            randomAccessFile.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

```java
public class RandomAccessTest {

    public static void main(String args[]) throws Exception {
        long filePoint = 0;
        String s;

        RandomAccessFile file = new RandomAccessFile("G:\\ideaProjects\\JavaSerrior\\JavaStudy007\\src\\com\\zjk\\java2\\RandomAccessTest.java", "r");
        long fileLength = file.length();

        while (filePoint < fileLength) {
            s = file.readLine();
            //作为文本文件，每一行的行末有（看不见的）回车符\n和换行符\a
            System.out.println(s);
            filePoint = file.getFilePointer();
        }

        file.seek(7); //移动文件指针，实现随机存取
        System.out.println(file.readLine());

        file.close();
    }
}
```

## 对象流 对象的串行化

### 串行化概念和目的

- 将java程序中的对象保存在外存中，称为对象永久化。

**对象流 ObjectInputStream和ObjectOutputStream**

- 用于存储和读取基本数据类型数据或对象的处理流。
- 可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
   - 序列化：用ObjectOutputStream类保存基本类型数据或对象的机制
   - 反序列化：用ObjectInputStream类读取基本类型数据或对象的机制
- ObjectOutputStream和ObjectInputStream<mark>不能序列化static和transient修饰</mark>的成员变量
   - `static关键字` 属于类，反序列化时会被变成默认值。
   - `transient关键字` 不允许序列化

**对象序列化机制**

- 对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流，从而允许把这种二进制流持久地保存在磁盘上，或通过网络将这种二进制流传输到另一个网络节点。
    - 当其它程序获取了这种二进制流，就可以恢复成原来的Java对象
- 序列化的好处在于可将任何实现了Serializable接口的对象转化为字节数据，使其在保存和传输时可被还原
- 序列化是 RMI（Remote Method Invoke – 远程方法调用）过程的参数和返回值都必须实现的机制，而 RMI 是 JavaEE 的基础。
    - 因此序列化机制是JavaEE 平台的基础

### 使用对象流序列化对象

**对象序列化需要实现的接口**

- 如果需要让某个对象支持序列化机制，则必须让对象所属的类及其内部的成员属性是可序列化的，
   - 基本数据类型，String都是可序列化的  
   - 如果内部成员是自定义类，则也需要实现Serializable接口。
- 为了让某个类是可序列化的，该类必须实现如下两个接口之一。否则，会抛出`NotSerializableException`异常
   - `Serializable`
   - `Externalizable`

#### Serializable接口 标识接口

**serialVersionUID**

- 凡是实现Serializable接口的类都有一个表示序列化版本标识符的静态变量：
   - `private static final long serialVersionUID`;
   - 自定义为： `public static final long serialVersionUID = 值L;`
- `serialVersionUID`用来表明类的不同版本间的兼容性。
  - 其目的是以序列化对象进行版本控制，有关各版本反序列化时是否兼容。
  - 如果类没有显示定义这个静态常量，它的值是Java运行时环境根据类的内部细节自动生成的。
     - 若类的实例变量做了修改，serialVersionUID 可能发生变化。故建议，显式声明。
- 简单来说，Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。
- 在进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地相应实体类的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常。(InvalidCastException)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/482332600239578.png =540x)

#### 序列化和反序列化

**序列化**

- 若某个类实现了 Serializable 接口，该类的对象就是可序列化的：
1. 创建一个 `ObjectOutputStream`
2. 调用 ObjectOutputStream 对象的 `writeObject(对象)` 方法输出可序列化对象
    - 注意写出一次，操作flush()一次

**反序列化 ObjectOutputStream**

- 将磁盘文件中的对象还原为内存的一个java对象 
1. 创建一个 `ObjectInputStream`
2. 调用 `readObject()` 方法读取流中的对象
- 强调：如果某个类的属性不是基本数据类型或 String 类型，而是另一个引用类型，那么这个引用类型必须是可序列化的，否则拥有该类型的Field 的类也不能序列化

```java
package com.zjk.java2;

import org.junit.Test;

import java.io.*;

public class ObjectInputStreamTest {
    @Test
    public void test() {

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.myobj"));

            oos.writeObject(new String("时代"));

            //刷新操作
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void test2() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.myobj"));

            Object obj = ois.readObject();

            String str = (String) obj;

            System.out.println(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

```java
package com.zjk.java2;

import org.junit.Test;

import java.io.*;

public class ObjectIOTest {
    @Test
    public void test1() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("Person01.person"));

            oos.writeObject(new Person("Jac", 20));

            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void test2() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("Person01.person"));

            Person p1 = (Person) ois.readObject();

            System.out.println(p1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


package com.zjk.java2;

import java.io.Serializable;

//实现Serializable接口
public class Person implements Serializable {
    //当前类提供一个全局常量：serialVersionUID
    //建议自定义值
    public static final long serialVersionUID = 1054860443L;
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

```java
public class UnSerializeDate {

    Date date = null;

    UnSerializeDate() {

        try {
            FileInputStream fileInputStream = new FileInputStream("G:/javaIOTest/date.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            date = (Date) objectInputStream.readObject();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        UnSerializeDate unSerializeDate = new UnSerializeDate();
        System.out.println("The date read is :" + unSerializeDate.date.toString());
    }
}
```

## Java NIO

Java NIO (New IO，Non-Blocking IO)是从Java 1.4版本开始引入的一套新的IO API，可以替代标准的Java IO API。

- NIO支持面向缓冲区的(IO是面向流的)、基于通道的IO操作。更加高效

**Java API中提供了两套NIO**

- 标准输入输出NIO
- 网络编程NIO。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/73665915239579.png =531x)

**NIO.2**

随着 JDK 7 的发布，Java对NIO进行了极大的扩展，增强了对文件处理和文件系统特性的支持。

**Path、Paths和Files核心API**

### Path接口

- NIO. 2引入了Path接口，代表一个平台无关的平台路径，描述了目录结构中文件的位置。
- Path可以看成是File类的升级版本，实际引用的资源也可以不存在。
  - ![](C:/Users/zjk10/OneDrive/NoteBook/pictures/355120216227446.png =305x)
  
**构造器**

- static Path get(String first, String … more) : 用于将多个字符串串连成路径
- static Path get(URI uri): 返回指定uri对应的Path路径

**Path 常用方法**

- String toString() ： 返回调用 Path 对象的字符串表示形式
- boolean startsWith(String path) : 判断是否以 path 路径开始
- boolean endsWith(String path) : 判断是否以 path 路径结束
- boolean isAbsolute() : 判断是否是绝对路径
- Path getParent() ：返回Path对象包含整个路径，不包含 Path 对象指定的文件路径
- Path getRoot() ：返回调用 Path 对象的根路径
- Path getFileName() : 返回与调用 Path 对象关联的文件名
- int getNameCount() : 返回Path 根目录后面元素的数量
- Path getName(int idx) : 返回指定索引位置 idx 的路径名称
- `Path toAbsolutePath()` : 作为绝对路径返回调用 Path 对象
- Path resolve(Path p) :合并两个路径，返回合并后的路径对应的Path对象
- `File toFile()`: 将Path转化为File类的对象

### Files 类

- java.nio.file.Files 用于操作文件或目录的工具类。

**常用方法**

- Path copy(Path src, Path dest, CopyOption … how) : 文件的复制
- `Path createDirectory(Path path, FileAttribute<?> … attr)` : 创建一个目录
- `Path createFile(Path path, FileAttribute<?> … arr)` : 创建一个文件
- void delete(Path path) : 删除一个文件/目录，如果不存在，执行报错
- `void deleteIfExists(Path path)` : Path对应的文件/目录如果存在，执行删除
- Path move(Path src, Path dest, CopyOption…how) : 将 src 移动到 dest 位置
- `long size(Path path)` : 返回 path 指定文件的大小

**Files常用方法：用于判断**

- boolean exists(Path path, LinkOption … opts) : 判断文件是否存在
- boolean isDirectory(Path path, LinkOption … opts) : 判断是否是目录
- boolean isRegularFile(Path path, LinkOption … opts) : 判断是否是文件
- boolean isHidden(Path path) : 判断是否是隐藏文件
- boolean isReadable(Path path) : 判断文件是否可读
- boolean isWritable(Path path) : 判断文件是否可写 
- boolean notExists(Path path, LinkOption … opts) : 判断文件是否不存在

**Files常用方法：用于操作内容**

- SeekableByteChannel newByteChannel(Path path, OpenOption…how) : 获取与指定文件的连接，how 指定打开方式。
- DirectoryStream<Path> newDirectoryStream(Path path) : 打开 path 指定的目录
- InputStream newInputStream(Path path, OpenOption…how):获取 InputStream 对象
- OutputStream newOutputStream(Path path, OpenOption…how) : 获取 OutputStream 对象

# 网络编程

## 概要

Java提供的网络类库，可以实现无痛的网络连接，联网的底层细节被隐藏在 Java 的本机安装系统里，由 JVM 进行控制。并且 Java 实现了一个跨平台的网络库，程序员面对的是一个统一的网络编程环境。

**计算机网络：**

把分布在不同地理区域的计算机与专门的外部设备用通信线路互连成一个规模大、功能强的网络系统，从而使众多的计算机可以方便地互相传递信息、共享硬件、软件、数据信息等资源。

**网络编程的目的：**

直接或间接地通过网络协议与其它计算机实现数据交换，进行通讯。

**网络编程中有两个主要的问题：**

- 如何准确地定位网络上一台或多台主机；定位主机上的特定的应用
- 找到主机后如何可靠高效地进行数据传输

**网络通信要素**

- 通信双方地址
   - IP
   - 端口号
- 一定的规则（即：网络通信协议。有两套参考模型）
  - OSI参考模型：模型过于理想化，未能在因特网上进行广泛推广
  - TCP/IP参考模型(或TCP/IP协议)：事实上的国际标准。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/430412716221153.png =497x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/20712816239579.png =397x)

## 通信要素1：IP 和 端口号  --> Socket

**IP 地址：InetAddress类**

- 唯一的标识 Internet 上的计算机（通信实体）
- 本地回环地址(hostAddress)：127.0.0.1 主机名(hostName)：localhost
- IP地址分类方式1：IPV4 和 IPV6
   - IPV4：4个字节组成，4个0-255。大概42亿，30亿都在北美，亚洲4亿。2011年初已经用尽。以点分十进制表示，如192.168.0.1
   - IPV6：128位（16个字节），写成8个无符号整数，每个整数用四个十六进制位表示，数之间用冒号（：）分开，如：3ffe:3201:1401:1280:c8ff:fe4d:db39:1984
- IP地址分类方式2：
   - 公网地址(万维网使用)
   - 私有地址(局域网使用)。
       - 192.168.开头的就是私有址址，范围即为192.168.0.0--192.168.255.255，专门为组织机构内部使用
- 特点：不易记忆

**域名**

- 域名解析
  - 域名容易记忆，当在连接网络时输入一个主机的域名后，域名服务器(DNS)负责将域名转化成IP地址，这样才能和主机建立连接。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/229354916240281.png =613x)

**本地回路地址 127.0.0.1**

- 对应着：localhost 即本机地址

**端口号标识正在计算机上运行的进程（程序）**

- 不同的进程有不同的端口号
- 被规定为一个 16 位的整数 0~65535。
- 端口分类：
   - 公认端口：0~1023。被预先定义的服务通信占用（如：HTTP占用端口80，FTP占用端口21，Telnet占用端口23）
   - 注册端口：1024~49151。分配给用户进程或应用程序。（如：Tomcat占用端口8080，MySQL占用端口3306，Oracle占用端口1521等）。
   - 动态/私有端口：49152~65535。

**端口号与IP地址的组合得出一个网络套接字：Socket。**

## InetAddress类

**Internet上的主机有两种方式表示地址：**

- 域名(hostName)：www.atguigu.com
- IP 地址(hostAddress)：202.108.35.210

**InetAddress类主要表示IP地址，两个子类**：

- Inet4Address
- Inet6Address。

InetAddress 类 对 象 含 有 一 个 Internet 主 机 地 址 的 域 名 和 IP 地 址 ：www.atguigu.com 和 202.108.35.210。

**实例化InetAddress类**

- getByName(String host)
   - 获取对应IP地址
- getLocalHost()
   - 获取本机地址 (127.0.0.1) 一般是局域网中的地址  
   
**常用方法**

- getHostName()
   - 获取域名 
- getHostAddress()
   - 获取IP地址

## 通信要素2：网络通信协议

**网络通信协议**

计算机网络中实现通信必须有一些约定，即通信协议，对速率、传输代码、代码结构、传输控制步骤、出错控制等制定标准。
  
**TCP/IP协议簇**

- 传输层协议中有两个非常重要的协议：
  - 传输控制协议TCP(Transmission Control Protocol)
  - 用户数据报协议UDP(User Datagram Protocol)。
- TCP/IP 以其两个主要协议：
   - 实际上是一组协议，包括多个具有不同功能且互为关联的协议。
        - TCP/IP协议模型从更实用的角度出发，形成了高效的四层体系结构，即物理链路层、IP层、传输层和应用层 
   - 传输控制协议(TCP)
   - 网络互联协议(IP)  
       - IP(Internet Protocol)协议是网络层的主要协议，支持网间互连的数据通信。

**TCP 和 UDP**

- TCP协议：
    - 使用TCP协议前，须先建立TCP连接，形成传输数据通道
    - 传输前，采用“三次握手”方式，点对点通信，是可靠的
    - TCP协议进行通信的两个应用进程：客户端、服务端。
    - 在连接中可进行大数据量的传输
    - 传输完毕，需释放已建立的连接，效率低
- UDP协议：
    - 将数据、源、目的封装成数据包，不需要建立连接
    - 每个数据报的大小限制在64K内
    - 发送不管对方是否准备好，接收方收到也不确认，故是不可靠的
    - 可以广播发送
    - 发送数据结束时无需释放资源，开销小，速度快

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/595062117236836.png =638x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/230152217232590.png =639x)

## Socket类

- 利用套接字(Socket)开发网络应用程序早已被广泛的采用，以至于成为事实上的标准。
- 网络上具有唯一标识的IP地址和端口号组合在一起才能构成唯一能识别的标识符套接字。
- 通信的两端都要有Socket，是两台机器间通信的端点。
- 网络通信其实就是Socket间的通信。
- Socket允许程序把网络连接当成一个流，数据在两个Socket间通过IO传输。
- 一般主动发起通信的应用程序属客户端，等待通信请求的为服务端。
- Socket分类
  - 流套接字（stream socket）：使用TCP提供可依赖的字节流服务
  - 数据报套接字（datagram socket）：使用UDP提供“尽力而为”的数据报服务

## TCP网络编程

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

## UDP 网络编程

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

## URL 网络编程

- URL(Uniform Resource Locator)：统一资源定位符，它表示 Internet 上某一资源的地址。
- 它是一种具体的URI，即URL可以用来标识一个资源，而且还指明了如何locate这个资源。
- 通过 URL 我们可以访问 Internet 上的各种网络资源，比如最常见的 www，ftp 站点。浏览器通过解析给定的 URL 可以在网络上查找相应的文件或其他资源。

**URL的基本结构由5部分组成：**'

- `<传输协议>://<主机名>:<端口号>/<文件名>#片段名?参数列表`
    - 例如: `http://192.168.1.100:8080/helloworld/index.jsp#a?username=shkstart&password=123`
    - #片段名：即锚点，例如看小说，直接定位到章节
    - 参数列表格式：参数名=参数值&参数名=参数值....
    
**URL类 构造器**

URL对象一旦创建就不可修改。

- public URL (String spec)
     - 通过一个表示URL地址的字符串可以构造一个URL对象。
     - 例如：URL url = new URL ("http://www. atguigu.com/"); 
- public URL(URL context, String spec)
     - 通过基 URL 和相对 URL 构造一个 URL 对象。
     - 例如：URL downloadUrl = new URL(url, “download.html")
- public URL(String protocol, String host, String file)
     - 例如：new URL("http", "www.atguigu.com", “download. html");
- public URL(String protocol, String host, int port, String file);
     - 例如: URL gamelan = new URL("http", "www.atguigu.com", 80, “download.html");

**常用方法**

- public String getProtocol( ) 获取该URL的协议名
- public String getHost( ) 获取该URL的主机名
- public String getPort( ) 获取该URL的端口号,如果没有则返回-1
- public String getPath( ) 获取该URL的文件路径
- public String getRef() 获取URL的相对位置（引用）
- public String getFile( ) 获取该URL的文件名
- public String getQuery( ) 获取该URL的查询名

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

# 反射机制

## 概述

- java 准动态语言
- 反射：动态语言的关键。
   - 在运行时借助于Reflection API取得任何类的内部信息，并能直接操作任意对象的内部属性及方法
   ![](C:/Users/zjk10/OneDrive/NoteBook/pictures/54754423239580.png =438x)

**Java反射机制提供的功能**

- 在运行时判断任意一个对象所属的类
- 在运行时构造任意一个类的对象
- 在运行时判断任意一个类所具有的成员变量和方法
- 在运行时获取泛型信息
- 在运行时调用任意一个对象的成员变量和方法
- 在运行时处理注解
- 生成动态代理

**反射相关的API**

- java.lang.Class:代表一个类
- java.lang.reflect.Method:代表类的方法
- java.lang.reflect.Field:代表类的成员变量
- java.lang.reflect.Constructor:代表类的构造器

**反射之前**

1. 不能通过类的对象调用其内部的私有结构

**反射**

1. 通过反射可以调用运行时类的私有结构。

**反射与封装**

 - 封装性：建议能不能调用
 - 反射：非要调用的问题。

**new 和 反射 调用公共结构的选择**

- 建议 new 的方式
- 在编译时，无法确定创建哪个类的对象时，使用反射的方式。

```java
package com.zjk.java2;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
    //反射之后，对Person类的操作
    @Test
    public void test1() throws Exception {
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
        //通过反射可以调用Person类的私有结构
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
    }
}


package com.zjk.java2;

public class Person {
    private String name;
    public int age;

    private Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void show() {
        System.out.println("人");
    }

    private String showNation(String nation) {
        System.out.println("国籍：" + nation);
        return nation;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

## Class 类

- Class本身也是一个类
- Class类是Reflection的根源，针对任何你想动态加载、运行的类，唯有先获得相应的Class对象
- Class 对象只能由系统建立对象，一个Class对象对应的是一个加载到JVM中的一个.class文件
- 对于每个类而言，JRE 都为其保留一个不变的 Class 类型的对象。即：每个类的实例都会记得自己是由哪个 Class 实例所生成
   - 一个 Class 对象包含了特定某个结构(`class / interface / enum / annotation / primitive type 基本数据类型 / void / [] 数组 `)的有关信息。
      - class包括 外部类，成员(成员内部类，静态内部类)，局部内部类，匿名内部类 
      - 对于数组，只要数组的数据类型和维度一样，则属于同一个Class，即：地址相同。
      - 一个加载的类在 JVM 中只会有一个Class实例 
   - 某个类的属性、方法和构造器、某个类到底实现了哪些接口。
     - 通过Class可以完整地得到一个类中的所有被加载的结构

**获取Class实例**

- 加载到内存中的运行时类，会缓存一定的时间，在此时间之内，可以通过各种方式来获取此运行时类

1. 前提：若已知具体的类，通过类的class属性获取，该方法最为安全可靠，程序性能最高
   - 实例：`Class clazz = String.class;`
2. 前提：已知某个类的实例，调用该实例的getClass()方法获取Class对象
   - 实例：`Class clazz = "www.atguigu.com".getClass();`
3. 前提：已知一个类的全类名，且该类在类路径下，可通过Class类的静态方法forName()获取，可能抛出ClassNotFoundException
   - 实例：`Class clazz = Class.forName("java.lang.String");`
4. 其他方式(不做要求) 类的加载器
   - `ClassLoader cl = this.getClass().getClassLoader();`
   - `Class clazz4 = cl.loadClass("类的全类名");`

**常用方法**

- static Class forName(String name) 
   - 返回指定类名 name 的 Class 对象
- Object newInstance() 
   - 调用缺省构造函数，返回该Class对象的一个实例
- getName() 
   - 返回此Class对象所表示的实体（类、接口、数组类、基本类型或void）名称
- Class getSuperClass() 
   - 返回当前Class对象的父类的Class对象
- Class[] getInterfaces() 
   - 获取当前Class对象的接口
- ClassLoader getClassLoader() 
   - 返回该类的类加载器
- Constructor[] getConstructors() 
   - 返回一个包含某些Constructor对象的数组
- Field[] getDeclaredFields() 
   - 返回Field对象的一个数组
- Method getMethod(String name,Class … paramTypes)
   - 返回一个Method对象，此对象的形参类型为paramType

```java
@Test
public void test2() throws ClassNotFoundException {
    //获取Class实例
    //1. 调用运行时类的属性，.class
    Class<Person> personClass = Person.class;
    System.out.println(personClass); //class com.zjk.java2.Person
    //2. 通过运行时类的对象，调用getClass()
    Person p1 = new Person();
    Class<? extends Person> personClass2 = p1.getClass();
    System.out.println(personClass2); //class com.zjk.java2.Person
    //3. 调用Class的静态方法，forName(String class)
    Class<?> personClass3 = Class.forName("com.zjk.java2.Person");
    System.out.println(personClass3); //class com.
    // zjk.java2.Person

    //指向的都是同一个对象
    System.out.println(personClass == personClass2); //true
    System.out.println(personClass == personClass3); //true
    System.out.println(personClass2 == personClass3); //true

    //Class实例可以是哪些结构
    Class<Object> objectClass = Object.class;//class 类
    Class<Comparable> comparableClass = Comparable.class; //interface 接口
    Class<String[]> aClass = String[].class; //数组
    Class<int[][]> aClass1 = int[][].class;  //数组
    Class<ElementType> elementTypeClass = ElementType.class; //enum 枚举
    Class<Override> overrideClass = Override.class; //annotion 注解
    Class<Integer> integerClass = int.class; //基本数据类型
    Class<Void> voidClass = void.class; //void
    Class<Class> classClass = Class.class;//Class类

    //对于数组而言 只要数组的元素类型和维度相同，则就是同一个Class
    int[] a = new int[10];
    int[] b = new int[1];
    System.out.println(a.getClass() == b.getClass()); //true


    System.out.println(personClass.getName()); //com.zjk.java2.Person
    System.out.println(personClass.getSuperclass()); //class java.lang.Object
    System.out.println(personClass.getClassLoader()); //jdk.internal.loader.ClassLoaders$AppClassLoader@78308db1
    Constructor<?>[] constructors = personClass.getConstructors();
    for (Constructor<?> con : constructors) {
        System.out.println(con);
        //public com.zjk.java2.Person(java.lang.String,int)
        //public com.zjk.java2.Person()
    }
}
```

## 类的加载过程

1. 程序经过javac.exe命令以后，会生成一个或多个字节码文件（.class文件），
2. 接着，使用java.exe命令对某个字节码文件进行解释运行。
    - 即：将某个字节码文件加载到内存中。此过程就称为类的加载
3. 加载到内存中的类，称为运行时类，作为Class的一个实例。
    - 即：Class的实例就对应着一个运行时类 

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/408071312221155.png =620x)

1. 加载：将class文件字节码内容加载到内存中，并将这些静态数据转换成方法区的运行时数据结构，然后生成一个代表这个类的java.lang.Class对象，作为方法区中类数据的访问入口（即引用地址）。所有需要访问和使用类数据只能通过这个Class对象。这个加载的过程需要类加载器参与。
2. 链接：将Java类的二进制代码合并到JVM的运行状态之中的过程。
   - 验证：确保加载的类信息符合JVM规范，例如：以cafe开头，没有安全方面的问题
   - 准备：正式为类变量（static）分配内存并设置类变量默认初始值的阶段，这些内存都将在方法区中进行分配。
   - 解析：虚拟机常量池内的符号引用（常量名）替换为直接引用（地址）的过程。
3. 初始化：
    - 执行类构造器`<clinit>()`方法的过程。类构造器`<clinit>()`方法是由编译期自动收集类中所有类变量的赋值动作和静态代码块中的语句合并产生的。（类构造器是构造类信息的，不是构造该类对象的构造器）。
    - 当初始化一个类的时候，如果发现其父类还没有进行初始化，则需要先触发其父类
的初始化。
    - 虚拟机会保证一个类的`<clinit>()`方法在多线程环境中被正确加锁和同步。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/273142511221156.png)

### ClassLoader 类加载器

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/378702711239582.png)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/313352911227449.png)

- 对于自定义类，使用系统类加载器进行加载。
- 引导类加载器主要负责加载Java的核心类库，无法加载自定义类。

```java
package com.zjk.java2;

import org.junit.Test;

public class ClassLoaderTest {
    @Test
    public void test1(){
        //系统类加载器
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader); //jdk.internal.loader.ClassLoaders$AppClassLoader@78308db1

        //扩展类加载器 调用系统类加载器的getParent() 获取上一级加载器（扩展类加载器）
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent); //jdk.internal.loader.ClassLoaders$PlatformClassLoader@35bbe5e8

        //引导类加载器  调用扩展类加载器的getParent() 获取不到引导类加载器 null
        //引导类加载器主要负责加载Java的核心类库，无法加载自定义类。
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1); //null

        //引导类加载器 无法获取到null
        //String是引导类加载器加载的，核心类库。
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1); //null
    }
}
```

### ClassLoader 用于读取Properties

```java
package com.zjk.java2;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClassLoaderTest {
    @Test
    public void test2() throws IOException {
        //Properties 用于读取配置文件
        Properties pros = new Properties();
        //方式1 此时默认在Module下
//            FileInputStream fis = new FileInputStream("jdbcTest.properties");
//            pros.load(fis);

        //方式2 此时默认在Module的src下
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("jdbcTest.properties");
        pros.load(resourceAsStream);

        String user = pros.getProperty("user");
        String passwd = pros.getProperty("passwd");
        System.out.println("user = " + user + " , passwd = " + passwd);
    }
}
```

## 创建运行时类的对象

**newInstance()** 创建对应的运行时类的对象，内部调用了运行时类的空参构造器。

- 要求：
1. 运行时类必须提供空参构造器。
2. 空参构造器的访问权限得够。

**在JavaBean中要求提供一个空参构造器**

1. 便于通过反射，创建运行时类得对象
2. 便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器。

```java
@Test
public void test1() throws InstantiationException, IllegalAccessException {
    Class<Person> personClass = Person.class;
    //newInstance() 创建对应的运行时类的对象。内部调用了运行时类的空参构造器
    //如果没有空参构造器，则报错 找不到合适的构造器 java.lang.InstantiationException
    Person person = personClass.newInstance();
    System.out.println(person);
}
```

```java
@Test
public void test2() {
    String classPath = null;
    int num = new Random().nextInt(3);
    switch (num) {
        case 0:
            classPath = "java.util.Date";
            break;
        case 1:
            classPath = "java.sql.Date";
            break;
        case 2:
            classPath = "com.zjk.java1.Person";
            break;
    }
    try {
        Object instance = this.getInstance(classPath);
        System.out.println(instance);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } catch (InstantiationException e) {
        throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
    }
}

public Object getInstance(String classPath) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    return Class.forName(classPath).newInstance();
}
```

## 获取运行时类的完整结构

Field、Method、Constructor、Superclass、Interface、Annotation

```java
package com.zjk.java1;

@MyAnnnotation(value = "Hi")
public class Person extends Creature implements Comparable<String>, MyInterface {
    private String name;
    int age;
    public int id;

    @MyAnnnotation("Person name")
    private Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @MyAnnnotation
    private void show() {
        System.out.println("人");
    }

    private String showNation(String nation) {
        System.out.println("国籍：" + nation);
        return nation;
    }

    private static String display(String interest) {
        System.out.println("兴趣爱好");
        return "兴趣爱好" + interest;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id= " + id +
                '}';
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    @Override
    public void info() {
        System.out.println("人 info");
    }
}

//MyInterface
package com.zjk.java1;

public interface MyInterface {
    void info();
}

//MyAnnotation
package com.zjk.java1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD,ElementType.PARAMETER,ElementType.CONSTRUCTOR,ElementType.LOCAL_VARIABLE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnnotation {
    String value() default "Hello";
}

//Creature
package com.zjk.java1;

import java.io.Serializable;

public class Creature implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃");
    }
}
```

### 1. 实现的全部接口

- `public Class<?>[] getInterfaces()` 
   - 确定此对象所表示的类或接口实现的接口。
   - 获取当前运行时类（不包括父类）实现的接口

```java
@Test
public void test(){
    Class<Person> personClass = Person.class;
    //getInterfaces() 获取当前运行时类（不包括父类）实现的接口
    Class<?>[] interfaces = personClass.getInterfaces();
    for(Class<?> inter:interfaces){
        System.out.println(inter);
        //interface java.lang.Comparable
        //interface com.zjk.java1.MyInterface
    }
    //可以通过getSuperClass()的方法获取父类实现的接口
    Class<? super Person> superclass = personClass.getSuperclass();
    Class<?>[] interfaces1 = superclass.getInterfaces();
    for(Class<?> inter:interfaces1){
        System.out.println(inter);
        //interface java.io.Serializable
    }
}
```

### 2. 所继承的父类

- `public Class<? Super T> getSuperclass()`
   - 返回表示此 Class 所表示的实体（类、接口、基本类型）的父类的Class。

### 3. 全部的构造器

- `public Constructor<T>[] getConstructors()`
   - 返回此 Class 对象所表示的类的所有public构造方法。
   - 获取当前运行时类声明为public的构造器
- `public Constructor<T>[] getDeclaredConstructors()`
   - 返回此 Class 对象表示的类声明的所有构造方法。
   - 获取当前运行时类中所有的构造器

- Constructor类中：
   - 取得修饰符: `public int getModifiers()`;
   - 取得方法名称: `public String getName()`
   - 取得参数的类型：`public Class<?>[] getParameterTypes()`;

```java
package com.zjk.java1;

import oracle.sql.OPAQUE;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

public class ConstuctorTest {
    @Test
    public void test1(){
        Class<Person> personClass = Person.class;
        //gerConstructors() 获取当前运行时类声明为public的构造器
        Constructor<?>[] constructors = personClass.getConstructors();
        for(Constructor<?> constructor : constructors){
            System.out.println(constructor);
            //public com.zjk.java1.Person(java.lang.String,int,int)
            //public com.zjk.java1.Person()
        }

        //getDeclaredConstructors() 获取当前运行时类中所有的构造器
        Constructor<?>[] declaredConstructors = personClass.getDeclaredConstructors();
        for(Constructor<?> constructor : declaredConstructors){
            System.out.println(constructor);
            //private com.zjk.java1.Person(java.lang.String)
            //public com.zjk.java1.Person(java.lang.String,int,int)
            //com.zjk.java1.Person(java.lang.String,int)
            //public com.zjk.java1.Person()
        }
    }
}
```

### 4. 全部的方法

- `public Method[] getDeclaredMethods()`
   - 返回此Class对象所表示的类或接口的全部方法
   - 获取当前运行时类中声明的所有方法，不包含父类
- `public Method[] getMethods() `
   - 返回此Class对象所表示的类或接口的public的方法
   - 获取当前运行时类及其所有父类（包括Object类）中声明为public的所有方法
- Method类中：@注解 权限修饰符 返回值类型 方法名(形参列表) throws XxxException{}
   - `getAnnotations()`
       - 获取方法的注解  
   - `public Class<?> getReturnType()` 
       - 取得全部的返回值
   - `public Class<?>[] getParameterTypes()`
       - 取得全部的参数
   - `getParameterTypes()`
       - 获取形参类型
   - `public int getModifiers()`
       - 取得修饰符
   - `public Class<?>[] getExceptionTypes()`
       - 取得异常信息

```java
package com.zjk.java1;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodTest {
    @Test
    public void test1() {
        Class<Person> personClass = Person.class;

        //getMethods() 获取当前运行时类及其父类中声明为public的所有方法
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println();

        //getDeclaredMethods() 获取当前运行时类中声明的所有方法，不包含父类
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method);
        }
    }

    /*
     * @注解
     * 权限修饰符 返回值类型 方法名(参数类型 参数名) throws XxxException{}
     */

    @Test
    public void test2() {
        Class<Person> personClass = Person.class;
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            //1. 获取方法的注解
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
                //@com.zjk.java1.MyAnnnotation("Hello")
            }

            //2. 获取方法的修饰符
            System.out.println(Modifier.toString(method.getModifiers()));

            //3. 获取方法的返回值类型
            System.out.println(method.getReturnType());

            //4. 获取方法的方法名
            System.out.println(method.getName());

            //5. 获取方法的形参列表
            //获取形参类型  //获取形参名：。。。
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (!(parameterTypes == null && parameterTypes.length == 0)) {
                for (Class parameterType : parameterTypes) {
                    System.out.print(parameterType.getName());
                }
            }

            //6. 抛出的异常
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            if (!(exceptionTypes == null && exceptionTypes.length == 0)) {
                for (Class exceptionType : exceptionTypes) {
                    System.out.println(exceptionType.getName());
                }
            }
        }
    }
}
```

### 5. 全部的Field

- `public Field[] getFields() `
   - 返回此Class对象所表示的类或接口的public的Field。
   - 获取当前运行时类及其父类中声明为public的所有属性
- `public Field[] getDeclaredFields() `
   - 返回此Class对象所表示的类或接口的全部Field。
   - 获取当前运行时类的所有属性，不包含父类
- Field方法中：权限修饰符 数据类型 属性名 
   - `public int getModifiers()` 
      - 以整数形式返回此Field的修饰符
      - private-2，public-1，缺省-0 
          - `Modifier.toString(field.getModifiers())` 转为修饰符
   - `public Class<?> getType()`
      -  得到Field的属性类型
   - `public String getName()` 
      - 返回Field的名称。
   
```java
package com.zjk.java1;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FiledTest {
    @Test
    public void test1() {
        Class<Person> personClass = Person.class;

        //获取属性结构
        //getFileds() 获取当前运行时类及其父类中声明为public的所有属性
        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
            //public int com.zjk.java1.Person.id
            //public double com.zjk.java1.Creature.weight
        }
        //getDeclaredFileds() 获取当前运行时类的所有属性,不包含父类
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field);
            //private java.lang.String com.zjk.java1.Person.name
            //int com.zjk.java1.Person.age
            //public int com.zjk.java1.Person.id
        }
    }

    @Test
    public void test2() {
        Class<Person> personClass = Person.class;
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field field : declaredFields) {
            //1. 权限修饰符
            int modifiers = field.getModifiers();
              //2 = private ，1 = public ，0 = 缺省
            System.out.println(modifiers);
              //从数字转为对应的权限修饰符 缺省--空
            System.out.println(Modifier.toString(modifiers));

            //2. 数据类型
            Class<?> type = field.getType();
            System.out.println(type);
            //class java.lang.String
            //int

            //3. 变量名
            String name = field.getName();
            System.out.println(name);

            System.out.println("----");
        }
    }
}
```

### 6. Annotation相关

- `get Annotation(Class<T> annotationClass) `
   - 获取运行时类声明的注解，（不包括父类）
- `getDeclaredAnnotations() `

```java
    @Test
    public void test4(){
        Class<Person> personClass = Person.class;
        for (Annotation annotation : personClass.getAnnotations()) {
            System.out.println(annotation);
            //@com.zjk.java1.MyAnnnotation("Hi")
        }
    }
```

### 7. 泛型相关

- `Type getGenericSuperclass()`
    - 获取父类泛型类型
- 泛型类型：`ParameterizedType`
    - `getActualTypeArguments()`
    - 获取实际的泛型类型参数数组

```java
@Test
public void test2() {
    Class<Person> personClass = Person.class;
    System.out.println(personClass.getSuperclass());
    //class com.zjk.java1.Creature

    //获取运行时类带泛型的父类
    Type genericSuperclass = personClass.getGenericSuperclass();
    System.out.println(genericSuperclass);
    //com.zjk.java1.Creature<java.lang.String>

    ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
    //获取泛型类型
    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
    for (Type type : actualTypeArguments) {
        System.out.println(((Class) type).getTypeName());
        //java.lang.String
    }
}
```

### 8. 类所在的包 

- `Package getPackage()`

```java
@Test
public void test3(){
    Class<Person> personClass = Person.class;
    Package aPackage = personClass.getPackage();
    System.out.println(aPackage);
    //package com.zjk.java1
}
```

## 调用运行时类的指定结构

**获取指定的方法属性**

- `getField(String fieldName) `
    - 获取指定的某个属性,要求运行时类获取的属性是public的
- `getDeclaredFiled(String fieldName)`  
    - 获取指定的属性
- `getDeclaredMethod(Sting methodName, Class ... args)` 
    - 获取指定的某个方法，指定方法名和形参列表
- `getDeclaredConstruct(Class<?> ... parameterType)` 
    - 获取指定的构造器，指定参数列表，空：则空参构造器

**调用指定方法**

- `Object invoke(Object obj, Object ... args)`
1. invoke()方法的返回值对应原方法的返回值，若原方法无返回值，此时返回null
2. 若原方法若为静态方法，此时形参Object obj可为null
   - 调用静态方法 `invoke(Class class, Object ... args)`
3. 若原方法形参列表为空，则Object[] args为null
4. 若原方法声明为private,则需要在调用此invoke()方法前，显式调用方法对象的`setAccessible(true)`方法，将可访问private的方法。

**调用指定属性**

- `public Object get(Object obj)`
    -  取得指定对象obj上此Field的属性内容
- `public void set(Object obj,Object value)` 
    - 设置指定对象obj上此Field的属性内容

**关于setAccessible方法的使用**

- Method和Field、Constructor对象都有setAccessible()方法。
- setAccessible启动和禁用访问安全检查的开关。
- 参数值为true则指示反射的对象在使用时应该取消Java语言访问检查。
- 提高反射的效率。如果代码中必须用反射，而该句代码需要频繁的被调用，那么请设置为true。
- 使得原本无法访问的私有成员也可以访问
- 参数值为false则指示反射的对象应该实施Java语言访问检查。

```java
package com.zjk.java1;

import org.junit.jupiter.api.Test;

import java.io.PipedOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
    //调用指定的属性1
    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class<Person> personClass = Person.class;

        //创建运行时类的对象
        Person person = personClass.newInstance();

        //getField(String fieldName) 获取指定的某个属性,要求运行时类获取的属性是public的
        Field id = personClass.getField("id");

        //set(Object obj, Object value) 设置当前属性的值
        id.set(person,1001);

        //get(Object obj)
        System.out.println(id.get(person)); //1001
    }

    //调用指定的属性2
    @Test
    public void test2() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<Person> personClass = Person.class;
        Person person = personClass.newInstance();

        //getDeclaredFiled(String fieldName) 获取指定的属性
        Field name = personClass.getDeclaredField("name");

        //对于非public权限的属性：需要启动和禁用访问安全检查的开关
        //保证当前属性是可访问的
        name.setAccessible(true);

        name.set(person,"西山");

        System.out.println(name.get(person));
    }

    //调用指定的方法
    @Test
    public void test3() throws Exception{
        Class<Person> personClass = Person.class;

        Person person = personClass.newInstance();

        //getDeclaredMethod(Sting methodName, Class ... args) 获取指定的某个方法，指定方法名和形参列表
        Method showNation = personClass.getDeclaredMethod("showNation", String.class);

        showNation.setAccessible(true);
        //invoke(Object obj, Object ... args) 调用相关的方法
        showNation.invoke(person,"China");

        //invoke(Class class, Object ... args) 调用静态方法
        Method display = personClass.getDeclaredMethod("display", String.class);
        display.setAccessible(true);

        String interest = (String)display.invoke(personClass, "Java");
        System.out.println(interest);
    }

    //调用指定的构造器
    @Test
    public void test4() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Person> personClass = Person.class;
        //getDeclaredConstruct(Class<?> ... parameterType) 获取指定的构造器
        //空：则空参构造器
        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);

        Person person = declaredConstructor.newInstance("Tom", 21);
        System.out.println(person);
        //Person{name='Tom', age=21, id= 0}
    }
}
```

## 动态代理

使用一个代理将对象包装起来, 然后用该代理对象取代原始对象。任何对原始对象的调用都要通过代理。代理对象决定是否以及何时将方法调用转到原始对象上

- 动态代理是指客户通过代理类来调用其它对象的方法，并且是在程序运行时根据需要动态创建目标类的代理对象。
- 动态代理使用场合:
  - 调试
  - 远程方法调用

**动态代理相比于静态代理的优点：**

抽象角色中（接口）声明的所有方法都被转移到调用处理器一个集中的方法中处理，这样，我们可以更加灵活和统一的处理众多的方法。

**静态代理**

```java
package com.zjk.java1;

interface ClothFactory {
    void produceCloth();
}

//代理类
class ProxyClothFactory implements ClothFactory{

    private ClothFactory factory; //用被代理类对象进行实例化

    public ProxyClothFactory(ClothFactory factory){
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂");

        factory.produceCloth();

        System.out.println("后续");
    }
}

//被代理类
class NikeClothFactory implements ClothFactory{
    @Override
    public void produceCloth() {
        System.out.println("Nike工厂生产");
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        //创建被代理类的对象
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        //创建代理类的对象
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nikeClothFactory);

        proxyClothFactory.produceCloth();
    }
}
```  

### Proxy类

专门完成代理的操作类，是所有动态代理类的父类。通过此类为一个或多个接口动态地生成实现类。

**提供用于创建动态代理类和动态代理对象的静态方法**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/139852309221157.png =683x)

**动态代理**

```java
package com.zjk.java1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Human {

    String getBelief();

    void eat(String food);
}

//被代理类
class SuperMan implements Human {

    @Override
    public String getBelief() {
        return "no belief";
    }

    @Override
    public void eat(String food) {
        System.out.println("i like eat " + food);
    }
}

/*
 * 实现动态代理要解决的问题：
 * 1. 如何根据加载到内存中的被代理类，动态的创建一个代理类及其对象。
 * 2. 当通过代理对象调用方法时，如何动态的调用被代理类的同名方法。
 * */

class ProxyFactory {
    //返回一个代理类的对象，解决问题1
    public static Object getProxyInstance(Object obj) {
        //obj 被代理类的对象
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), myInvocationHandler);
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object obj; //需要使用被代理类的对象赋值

    public MyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    //当我们通过代理类的对象，调用方法a时，就会自动的调用如下的方法：invoke()
    //将被代理类要执行的方法a的功能就声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method 即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        return method.invoke(obj, args);
    }
}

public class ProxyTest1 {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        //proxyInstance 代理类的对象
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        //当通过代理类对象调用方法时，自动调用被代理类中同名的方法
        System.out.println(proxyInstance.getBelief());
        proxyInstance.eat("❄");

        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        ClothFactory proxyInstance1 = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);
        proxyInstance1.produceCloth();
    }
}
```

### 动态代理与AOP（Aspect Orient Programming)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/439643715239583.png =456x)

```java
class HumanUtil{
    public void method1(){
        System.out.println("通用方法1");
    }

    public void method2(){
        System.out.println("通用方法2");
    }
}

@Override
public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    HumanUtil humanUtil = new HumanUtil();
    humanUtil.method1();
    humanUtil.method2();

    //method 即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
    return method.invoke(obj, args);
}
```

# GUI 基于Swing的图形化用户界面

## Java GUI 概述

**JFC**

- AWT
   - AWT是重量级组件，依赖本地操作系统，可移植性差。
- Java 2D
- Accessibility
- Drag&Drop
- Swing
- Internationalization

**配色**

- 三原色 红绿蓝 RGB
- 三基色 红黄蓝

## Swing组件

- Swing中的所有组件全部用Java语言实现。
- 采用分离模型：组件及与组件相关的数据模型（或简称模型） 
- 可设置的组件外观感觉（look and feel ）
- 与AWT相比提供更丰富的GUI组件，引入新的特征，并提供更丰富的功能。
- 一般如果使用Swing组件，则程序中只使用Swing组件和Swing容器。

**Swing包中定义了两类组件：**

- 顶层容器（JFrame,Japplet,Jwindow,Jdialog)
- 轻型组件（‘J’开头的所有组件，如Jbutton,Jpanel,Jmenu等）

**Swing组件分类**

1. 顶层容器组件 JFrame、JApplet、JDialog等
2. 通用容器组件 JPanel、JScrollPane、JToolBar等
3. 特殊容器 JIternalFrame、JLayeredPane、JRootPane等
4. 基本控制组件 JButton、JComboBox、JList、JSlider、JTestField等
5. 不可编辑的信息显示组件 JLabel、JProgressBar等
6. 可编辑的信息显示组件 JTable、JFileChooser、JTree等 

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/420302910221148.png =490x)

```java
package com.zjk.java;

//引入Swing 包及其它程序包

import javax.swing.*;

public class HelloWorldSwing {

    //所有J开头的类都是Swing组件
    public static void main(String[] args) {
        //1.选择GUI的外观风格L&F
        //2.创建并设置窗口容器

        //JFrame 窗体 最大的容器：其他的组件都包含在窗体内
        //new JFrame("窗体标题")
        JFrame frame = new JFrame("窗体标题"); //窗体的标题栏

        //JLabel 标签 一般用来显示静态的文字，也可以改变
        JLabel label = new JLabel("Hello World!");

        //3.创建与添加Swing组件

        //getContentPane() 窗体的内容面板
        //add(label) 将标签组件添加到窗体内
        frame.getContentPane().add(label);

        //setDefaultCloseOperation() 设置缺省的关闭操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setSize() 设置窗体的初始长宽
        frame.setSize(2000, 700);

        //4.显示顶层容器，将整个GUI显示出来
        //setVisible() 将窗体从内存中显示
        frame.setVisible(true);
    }
}
```

### JComponent类

- Swing组件中,除了顶层容器组件，以J开头的都是JComponent类的子类
- JComponent类是java.awt.Container的子类，是抽象类。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-21_10-34-34.png =600x)

**常用方法**

**组件设置**

- setLocation(int x, int y)
   - 设置组件大小
- setSize(int width, int height)
   - 设置组件位置
- setBounds(int x, int y, int width, int height)
   - 设置组件大小位置

**文本**

- getText()       
   -  获取String,组件内容 
- setText()      
   - 组件内容设置        

**焦点**

- requestFocus()                 
   - 请求焦点

**选择内容**

- setSelectionStart() 和 setSelectionEnd()
   - 设置选择的区域

### 常用容器

- 容器本身也是一个组件，具有组件的所有性质，另外还具有容纳其它功能
- 所有的组件都可以通过`add()`方法向容器中添加组件
- 在Swing中，常用的三种类型的容器是JFrame，JPanel，JApplet 
- Swing GUI形成顶层容器-中间层容器-基本组件的层次包容关系

#### 顶层容器

- 具有Swing GUI的应用必须有至少一个顶层容器 
- 顶层Swing容器是JFrame、JDialog或JApplet的实例

**向顶层容器中加入组件**

- Swing组件不能直接添加到顶层容器中，必须添加到一个与顶层容器相关联的内容面板（Content Pane）上

##### JFrame 框架

- JFrame是最常用的顶层容器，一般作为Java Application的主窗口 

**JFrame的构造器**

- `JFrame()` 创建一个最初不可见的框架
- `JFrame(String title)` 创建一个带有标题的最初不可见的框架

**方法**

- `set size(int length, int weigth)` 
   - 设置初始窗体大小 
- `setTitle(String title)` 
   - 设置窗体标题
- `void setContentPane(Container contentPane)`
   - 设置 contentPane属性。
- `Container	getContentPane()` 
   - 返回此框架的 contentPane对象。
- `void setDefaultCloseOperation(int operation)`
    - 设置用户在此框架上启动“关闭”时默认执行的操作
   - `WindowConstants.DO_NOTHING_ON_CLOSE` 
       - 不要做任何事情; 要求程序处理WindowListener对象的windowClosing方法的操作。
   - `WindowConstants.HIDE_ON_CLOSE` 
       - 默认，在调用任何已注册的WindowListener对象后自动隐藏框架。
   - `WindowConstants.DISPOSE_ON_CLOSE` 
       - 在调用任何已注册的WindowListener对象后自动隐藏和处理该框架。
   - `JFrame.EXIT_ON_CLOSE`
       - 使用`System exit`方法退出exit程序。 仅在应用程序中使用。 
- `int getDefaultCloseOperation()` 
    - 返回当用户在此框架上启动“关闭”时发生的操作。
- `static void setDefaultLookAndFeelDecorated(boolean defaultLookAndFeelDecorated)`
   - 提供关于新创建的 JFrame是否应该具有由当前外观提供的窗口装饰
   - 必须在创建JFrame窗口前设定
- `static boolean isDefaultLookAndFeelDecorated()`
   - 如果新创建的 JFrame应该具有当前外观提供的窗口装饰，则返回true。
- `void setLayout(LayoutManager manager)`
   - 设置 布局管理器 LayoutManager 。
- `public Component add(Component comp)`
   - 将指定的组件附加到此容器的末尾。
- `public Component add(Component comp, int index)`
   - 在给定的位置将指定的组件添加到此容器。
- `pack()` 
   - 使此窗口的大小适合其子组件的首选大小和布局。
- `setVisible(boolean b)`
   - 使窗体可视 
- `public void setPreferredSize(Dimension preferredSize)`
   - 将此组件的首选大小设置为常量值
   - `new Dimension(int x, int y)`

```java
package com.zjk.java;

import javax.swing.*;
import java.awt.*;

public class JFrameDemo {
    public static void main(String[] args) {
        //setDefaultLookAndFeelDecorated() 指定使用当前的Look&Feel装饰窗口
        JFrame.setDefaultLookAndFeelDecorated(true);

        //new JFrame(String title) 创建最初不可视的窗体，并设置标题
        JFrame frame = new JFrame("Hello World ！");
        //setDefaultCloseOperation() 设定关闭窗口操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //new JLabel() 创建标签组件
        JLabel emptyLabel = new JLabel("Hello");
        emptyLabel.setPreferredSize(new Dimension(175,100));
        //add() 将组件添加到窗体
        frame.getContenPane().add(emptyLabel);

        //pack() 整理窗体布局
        frame.pack();
        //setViesible() 使窗口可视
        frame.setVisible(true);
    }
}
```

##### JOptionPane类 对话框类

**方法**

- `showXxxDialog()` 
  - 显示简单的模式对话框。包括确认对话框、输入对话框、消息对话框、选项对话框。
  - `showConfirmDialog()` 确认对话框
  - `showInputDialog()` 输入对话框
  - `showMessageDialog()`消息对话框
  - `showOptionDialog()` 选项对话框  
  - 参数：
     - `Component parentComponent` 
        - 定义对话框所依赖的窗口。该参数可为null，这时使用默认的窗口，并且对话框将在屏幕中央显示。
     - `Object message`
        - 该参数指定了对话框中要显示的内容。一般指定为一个字符串。
     - `String title`
        - 指定对话框的标题。
     - `int optionType`
        - 指定对话框底部要出现的一组按钮。从下列标准设置中选取，静态常量：
        - DEFAULT_OPTION
        - YES_NO_OPTION
        - YES_NO_CANCEL_OPTION
        - OK_CANCEL_OPTION
     - `int messageType`
        - 指定信息显示的风格，可以从下列值中选取，静态常量，它们都带有默认的图标：
        - PLAIN_MESSAGE (无图标)
        - ERROR_MESSAGE
        - INFORMATION_MESSAGE
        - WARNING_MESSAGE
        - QUESTION_MESSAGE
     - `Icon icon`
        - 指定对话框中显示的图标。该参数的默认值是由`massage Type`参数所指定的值。
     - `Object[] options`
        - 是对话框底部按钮更详细的描述。一般是一个字符串数组。
     - `Object initialValue`
        - 指定要选取项的默认输人值。
- `showInternalXxx()` 使用内部窗口的方式显示相应的对话框

```java
package com.zjk.java;

import javax.swing.*;

public class JOptionPaneTest {
    public static void main(String[] args) {

        JFrame frame = new JFrame("JOption测试");

        //信息对话框
        //带信息图标的对话框
        JOptionPane.showMessageDialog(frame, "似乎出现了一些奇怪的错误");
        //带警告图标的对话框
        JOptionPane.showMessageDialog(frame, "出现不可预计的错误", "警告",
                JOptionPane.WARNING_MESSAGE);

        //选项对话框
        Object[] options = {"是的", "不了", "???"};
        String optionText = "你想要一些???吗?";
        String optionTitle = "一个简单的问题";
        int n = JOptionPane.showOptionDialog(frame, optionText, optionTitle,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[2]);

        //输入对话框
        //1
        Object[] possibleValues = {"first", "second", "third"};
        String message = "请选择选项: ";
        String messageTile = "选择";
        Object selectedValue = JOptionPane.showInputDialog(null, message, messageTile,
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        String inputValue = JOptionPane.showInputDialog("请输入数据");
        //2
        JOptionPane.showConfirmDialog(null, "请选择：", "选择", JOptionPane.YES_NO_OPTION);
    }
}
```

#### 通用容器

##### JPanel

- JPanel是存放轻型组件的通用容器，缺省情况下是透明的，其对象可作为顶层容器的Content Pane使用。 

**构造器**

- `JPanel()`
   - 创建一个新的 JPanel双缓冲区和流布局。
- `JPanel(boolean isDoubleBuffered)`
   - 创建一个新的 JPanel与 FlowLayout和指定的缓冲策略。
- `JPanel(LayoutManager layout)`
   - 使用指定的布局管理器创建一个新的缓冲JPanel
- `JPanel(LayoutManager layout, boolean isDoubleBuffered)`
   - 使用指定的布局管理器和缓冲策略创建一个新的JPanel。

```java
import java.awt.*;
import javax.swing.*;

public class FrameWithPanel extends JFrame {
    public static void main(String args[]) {
        FrameWithPanel fr = new FrameWithPanel("Hello !");
        fr.setSize(200, 200);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pan = new JPanel();
        pan.setBackground(Color.yellow);
        pan.setLayout(new GridLayout(2, 1));
        pan.add(new JButton("确定"));
        fr.setContentPane(pan);
        fr.setVisible(true);
    }

    public FrameWithPanel(String str) {
        super(str);
    }
} 
```

##### JScrollPane 滚动面板

**构造器**

- `public  JScrollPane()`
- `public  JScrollPane(Comonent view)`
- `public  JScrollPane(int vsbPolicym, int hsbPolicy)`
- `public  JScrollPane(Component view, int vsbPolicym, int hsbPolicy)`
   - view参数 客户组件
   - int vsbPolicym, int hsbPolicy 参数 指定水平和垂直滚动条的显示策略
      - JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
      - JScrollPane.VERTICAL_SCROLLBAR_NEVER
      - JScrollPane.VERTICAL_SCROLLBARA_ALWAYS

```java
package com.zjk.java;

import javax.swing.*;
import java.awt.*;

public class JScrollPaneTest {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame();

        FlowLayout flowLayout = new FlowLayout();

        frame.setLayout(flowLayout);

        JTextArea textArea = new JTextArea(5, 30);
        JScrollPane jScrollPane = new JScrollPane(textArea);

        JLabel jLabel = new JLabel("Test");

        frame.add(jScrollPane);
        frame.add(jLabel);

        frame.setSize(100,400);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
```

##### JSplitPane 分隔面板

**构造器**

- `JSplitPane()`
- `JSplitPane(int newOrientation)`
- `JSplitPane(int newOrientation, boolean newContinuousLayout)`
- `JSplitPane(int newOrientation, Component newLeftComponent, Component newRightComponent)`
- `JSplitPane(int newOrientation, boolean newContinuousLayout, Component newLeftComponent, Component newRightComponent)`
  - newOrientation参数 指定JSplitPane的分隔方向
     - JSplitPane.HORIZONTAL_SPLIT
     - JSplitPane.VERTICAL_SPLIT
  - newLeftComponent参数 指定位于JSplitPane左边或上边的组件
  - newRightComponent参数 指定位于JSplitPane右边或下边的组件

```java
package com.zjk.java;

import javax.swing.*;
import java.awt.*;

public class JSplitPaneTest {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("test");
        FlowLayout flowLayout = new FlowLayout();
        frame.setLayout(flowLayout);

        JLabel label = new JLabel("内容区域");
        JTextArea text = new JTextArea();
        //指定组件的最小尺寸
        JScrollPane jScrollPane = new JScrollPane(text);
        label.setMinimumSize(new Dimension(200,200));
        jScrollPane.setMinimumSize(new Dimension(200,200));

        //设置分面板
        JSplitPane jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,label,jScrollPane);
        //设置每个分面板的快速扩展按钮
        jSplitPane.setOneTouchExpandable(true);
        //设置分割条的位置
        jSplitPane.setDividerLocation(150);

        frame.add(jSplitPane);

        frame.setSize(300,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
```

##### JTabbedPane类 标签面板

**构造器**

- JTabbedPane()
- JTabbedPane(int tabPlacement)
   - tabPlacement 指定组件标签的显示位置，默认JTabbedPane.TOP 
   - JTabbedPane.TOP
   - JTabbedPane.BOTTOM
   - JTabbedPane.LEFT
   - JTabbedPane.RIGHT
- JTabbedPane(int tabPlacement, int tabLayoutPolicy) 
   - tabLayoutPolicy 指定布局
   - WRAP_TAB_LAYOUT
   - SCROLL_TAB_LAYOUT 

**添加组件**

- addTab(String title, Icon icon, Component Component, String tip)
   - 指定标签上的文字，指定标签上的图标，指定选择该标签时要显示的组件，指定标签的提示信息。
- addTab(String title, Icon icon, Conponent component)
- addTab(String title, Component component)

### 常用组件

#### JButton类  按钮

#### JLabel类  标签

- JLabel对象可以显示文本，图像或两者。 
- 可以通过设置垂直和水平对齐来指定标签的显示区域中标签内容对齐的位置。 
- 默认情况下，标签在其显示区域中垂直居中，纯文本标签是前缘对齐的，仅图像标签水平居中。文本位于图像的后端，文本和图像垂直对齐。

**构造器**

- `JLabel()`
   - 创建一个没有图像的 JLabel实例，标题为空字符串。
- `JLabel(Icon image)`
   - 使用指定的图像创建一个 JLabel实例。
- `JLabel(Icon image, int horizontalAlignment)`
   - 创建一个具有指定图像和水平对齐的 JLabel实例。
-  `JLabel(String text)`
   - 使用指定的文本创建一个 JLabel实例。
- `JLabel(String text, Icon icon, int horizontalAlignment)`
   - 创建具有 JLabel文本，图像和水平对齐的JLabel实例。
- `JLabel(String text, int horizontalAlignment)`
    - 创建一个具有指定文本和水平对齐的 JLabel实例。

#### 文本类

##### JTextArea类

- 可编辑的，无格式文本
- 默认不自动换行

```java
package com.zjk.java;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class myGUI {
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);

		JFrame frame = new JFrame("myGUI");
		frame.setLayout(new FlowLayout());

		JTextArea textArea = new JTextArea("从这开始编辑");
		frame.add(textArea);

		JLabel label = new JLabel();
		frame.add(label);

		JButton button = new JButton("OK");
		frame.add(button);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				button.setForeground(Color.green);

				// getText() 从JTextArea对象中获取内容
				String text = textArea.getText();
				// setText() 设置内容
				label.setText(text);
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
```


#### 选择类

##### JComboBox类 选择框

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/540921511236840.png =323x)

**构造器**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/243341411247616.png =414x)

**方法**

- `setModel(new DefaultComboBoxModel(数组))` 
   - 设置选项框的内容 
- `int getSelectedIndex()`
   - 返回当前选项框在setModel()的数组中的索引 

##### JCheckBox 复选框

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/295231511240285.png =344x)

**构造器**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/18201311239583.png =518x)

##### JRadioButton 单选钮

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/195341611232594.png =351x)

**构造器**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/376401311227450.png =518x)

##### ButtonGroup类 组合按钮

- 使选择之间互斥，一次只能选择一个。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/565802011250474.png =216x)

**构造器**

- ButtonGroup()

**方法**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/265392111248078.png =563x)

```java
package com.zjk.java1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GUITest {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Test Title");
        frame.setSize(400,300);

        frame.setLayout(null);

        JLabel jobShow = new JLabel();
        jobShow.setBounds(20, 50, 100, 40);
        frame.add(jobShow);

        JComboBox job = new JComboBox();
        String[] jobs = {"军人", "学生", "教师", "办公", "无业"};
        job.setModel(new DefaultComboBoxModel(jobs));
        job.setBounds(50, 50, 100, 40);
        job.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                jobShow.setText(jobs[job.getSelectedIndex()]);
            }
        });
        frame.add(job);

        JRadioButton choice1 = new JRadioButton("断网");
        choice1.setBounds(20,10,100,40);
        frame.add(choice1);
        JRadioButton choice2 = new JRadioButton("有网");
        choice2.setBounds(70,10,100,40);
        frame.add(choice2);
        ButtonGroup choices = new ButtonGroup();
        choices.add(choice1);
        choices.add(choice2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
```

### 菜单组件

- JComponent
    - JMenuBar
    - JPopupMenu
    - JAbsta\ractButton
    - JSeparator
        - JMenuItem
           - JMenu
           - JCheckboxMenuItem
           - JRadioButtonMenuItem 

**结构**

- 根菜单--> 次级菜单 -->菜单选项

#### 根菜单

##### JMenuBar 下拉式菜单

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-21_10-25-00.png =300x)



##### JPopupMenu 弹出式菜单/上下文菜单

#### 次级菜单

##### JMenu

#### 菜单选项

##### JMenuItem

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-21_11-24-19.png =500x)

```java
JMenuBar toolbars = new JMenuBar();
frame.setJMenuBar(toolbars);

//设置-菜单
JMenu settingMenu = new JMenu("设置(s)");
settingMenu.setMnemonic('s');
toolbars.add(settingMenu);
//设置选项-背景颜色
JMenu backGround = new JMenu("背景颜色");
settingMenu.add(backGround);
//粉色
JMenuItem setBackPink = new JMenuItem("粉色(p)", 'p');
setBackPink.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        frame.setBackground(Color.RED);
    }
});
backGround.add(setBackPink);
```

### 布局管理器

- FlowLayout 流式布局管理器 
   - Panel和Applet的默认
- BorderLayout 边界布局管理器
   - Window、Dialog、Frame的默认 
- GridLayout 网格布局管理器
- CardLayout 卡片布局管理器
- GirdBagLayout 网格包布局管理器
- BoxLayout 箱式布局管理器

**设置布局管理器**

- 每个容器都有一个默认的布局管理器，可以通过`setLayout(布局管理器对象)`方法，指定布局管理器。

#### FlowLayout 流式布局管理器

**构造器**

- `FlowLayout()` 
  - 构造一个新的 FlowLayout中心对齐和默认的5单位水平和垂直间隙 
- `FlowLayout(int align)`
  - 构造一个新的 FlowLayout具有指定的对齐和默认的5单位水平和垂直间隙。 
  - `FlowLayout.CENTER` 居中对齐
  - `FlowLayout.LEFT` 左对齐
  - `FlowLayout.RIGHT` 右对齐
  - `FlowLayout.LEADING` 该值表示组件的每一行应该对齐到容器方向的前端，例如从左到右的方向向左。
  - `FlowLayout.TRAILIN` 该值表示组件的每一行应该对齐到容器方向的后端，例如从左到右的方向向右。
- `FlowLayout(int align, int hgap, int vgap)`
  - 创建一个新的流程布局管理器，具有指示的对齐方式和指示的水平和垂直间距。

```java
package com.zjk.java;

import javax.swing.*;
import java.awt.*;

public class FlowLayoutDemo {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("流式布局管理器测试");

        //new FlowLayout()默认的流式布局管理器
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER,100,200);
        //setLayout() 指定布局管理器
        frame.setLayout(flowLayout);

        JLabel label1 = new JLabel("序号1");
        JLabel label2 = new JLabel("序号2");

        frame.add(label1);
        frame.add(label2);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
```

```java
package com.zjk.java;

import javax.swing.*;
import java.awt.*;

public class FlowLayoutWindow extends JFrame {

    public FlowLayoutWindow() {
        //FlowLayoutWindow的setLayout()方法
        //创建流式布局管理器
        setLayout(new FlowLayout());

        //从左到右依次添加组件
        //FlowLayoutWindow的add()方法
        add(new JLabel("Buttons:"));
        //new JButton() 新建按钮
        add(new JButton("Button 1"));
        add(new JButton("2"));
        add(new JButton("Button 3"));
        add(new JButton("Long-Named Button 4"));
        add(new JButton("Button 5"));
    }

    public static void main(String args[]) {
        FlowLayoutWindow window = new FlowLayoutWindow();
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("FlowLayoutWindow Application");
        window.pack();//窗口的大小设置为适合组件最佳尺寸与布局所需的空间。
        window.setVisible(true);
    }
}
```

#### BorderLayout 边界布局管理器

**构造器**

- `BorderLayout()`
  - 组件之间没有水平间隙与垂直间隙 
- `BorderLayout(int hgap, int vgap)`
  - 指定组件之间水平间隙与垂直间隙 

**注**

- 当用户改变窗口大小时，各个组件的相对位置不变，而组件的大小改变

**add() 使用Containeri类的**

- `public Component add(String name, Component comp)`
- `public void add(Component comp, Object constraints)`

- BorderLayout布局管理器中，在向容器中加入组件时，要指定各个摆放的方位，否则组件将不能显示。
  - 默认放在中心位置 
- 若添加时，要添加的位置上已经有组件，则会执行覆盖

comp 组件

name和contraints参数: 

| 参数 | 值(String)   |
| :--- | :--- |
| BorderLayout.WEST     | "West"     |
|   BorderLayout.EAST   |    "East"  |
|   BorderLayout.NORTH   |   "North"   |
|   BorderLayout.SOUTH   |   "South"   |

```java
package com.zjk.java;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutTest {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("边界布局管理器测试");

        //创建边界布局管理器
        BorderLayout borderLayout = new BorderLayout();
        frame.setLayout(borderLayout);

        JLabel labelWest = new JLabel("西边");
        JButton buttonEast = new JButton("东边");
        JButton buttonNorth = new JButton("北边");
        JButton buttonSouth = new JButton("南边");

        frame.add(labelWest,BorderLayout.WEST);
        frame.add(buttonEast,BorderLayout.EAST);
        frame.add(BorderLayout.NORTH,buttonNorth);
        frame.add(BorderLayout.SOUTH,buttonSouth);

        JButton buttonCenter = new JButton("中心");
        //默认放在中心位置，同时会覆盖原有的。
        frame.add(buttonCenter);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
```

#### setLayout(null) 无布局管理器

**方法**

- setLocation(int x, int y)
   - 设置组件大小
- setSize(int width, int height)
   - 设置组件位置
- setBounds(int x, int y, int width, int height)
   - 设置组件大小位置

## GUI中的事件处理

### 监听器

**委托方式的事件处理机制**

- 监听器:每个事件有一个相应的监听器接口，定义了接收事件的方法。实现该接口的类，可作为监听器注册。
- 每个组件都注册有一个或多个监听器（类），该监听器包含了能接收和处理事件的事件处理。
- 事件对象只向已注册的监听器报告

1. 实现监听器接口 `ActionListener`
2. 实现监听器接口的方法 `public void actionPerformed(){}`
3. 注册监听器到组件中 `addXxxListener(监听器的实现类对象)`

```java
package com.zjk.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerTest {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("监听器测试");
        frame.setSize(200,100);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton button = new JButton("Press");
        //给组件添加监听器
        button.addActionListener(new ButtonPress());

        frame.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

//定义监听器类
class ButtonPress implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action occurred");
        System.out.println("标签：" + e.getActionCommand());
    }
}
```

### 事件类

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/160351619227442.png =414x)

| 事件             | 操作                                         | 接口                 | 适配器类            | 方法                                                                                                                                   |
| :-------------- | :------------------------------------------- | :------------------ | :----------------- | :------------------------------------------------------------------------------------------------------------------------------------ |
| ActionEvent     | 激活组件操作                                  | ActionListener      |                    | ActionPerformed()                                                                                                                     |
| AdjustmentEvent | 移动滚动条                                    | AdjustmentListener  |                    | adjustmentValueChanged()                                                                                                              |
| ComponentEvent  | 组件移动、缩放、显示、隐藏等                    | ComponentListener   | ComponentAdapter   | componentHidden() / componentMoved() / componentResized() / componentShown()                                                          |
| ContainerEvent  | 容器中增加或删除组件                           | ContainerListener   | ContainerAdapter   | componentAdded() / componentRemoved()                                                                                                 |
| FocusEvent      | 组件得到或失去聚焦                             | FocusListener       | FocusAdapter       | focusGained() / focusLost()                                                                                                           |
| ItemEvent       | 条目状态改变                                  | ItemListener        |                    | itemStateChanged()                                                                                                                    |
| KeyEvent        | 键盘输入                                      | KeyListener         | KeyAdapter         | keyPressed() / keyReleased() / keyTtyped()                                                                                            |
| MouseEvent      | 单击鼠标                                      | MouseListener       | MouseAdapter       | mouseClicked() / mouseEntered() / mouseExited() / mousePressed() / mouseReleased()                                                    |
| -               | 移动鼠标                                      | MouseMotionListener | MouseMotionAdapter | mouseDragged() / mouseMoved()                                                                                                         |
| TextEvent       | 文本域或文本区值改变                           | TestListener        |                    | testValueChanged()                                                                                                                    |
| WindowEvent     | 窗口激活、去活、打开、关闭、最小化、从图标恢复等 | WindowListener      | WindowAdapter      | windowActivated() / windowClosed() / windowClosing() / windowDeactivated() / windowDeiconified() / windowIconified() / windowOpened() |

**MoseEvent**
- MouseListener / MouseAdapter
  - mouseClicked() 鼠标点击之后
  - mouseEntered()
  - mouseExited() 鼠标离开时
  - mousePressed() 鼠标按压时
  - mouseReleassed() 

```java
package com.zjk.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JEventTest {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("测试事件");
        FlowLayout flowLayout = new FlowLayout();
        frame.setLayout(flowLayout);
        frame.setSize(new Dimension(600,700));

        JLabel label01 = new JLabel("测试1");
        JButton button01 = new JButton("按钮1");
        JTextArea textArea01 = new JTextArea("输入：");

        JScrollPane textArea01Scroll = new JScrollPane(textArea01);
        textArea01Scroll.setSize(400,300);
        //添加事件监听器
        textArea01Scroll.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("鼠标点击");
                textArea01Scroll.setForeground(Color.BLUE);
                textArea01Scroll.setBackground(Color.RED);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("鼠标Pressed");
                textArea01Scroll.setForeground(Color.GRAY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("鼠标Released");
                textArea01Scroll.setForeground(Color.GREEN);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        JSplitPane button01AndLabel01 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,label01,button01);
        button01AndLabel01.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                button01.setBackground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button01.setBackground(Color.RED);
            }
        });

        frame.add(textArea01Scroll);
        frame.add(button01AndLabel01);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
```

```java
//监听多种事件的监听器
package com.zjk.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComplexListener implements
        MouseMotionListener, MouseListener, ActionListener {
    JFrame f;
    JTextArea tf;
    JButton bt;
    int number = 1;


    public ComplexListener() {
        JLabel label = new JLabel("click and Drag the mouse");
        f = new JFrame("Complex Listener");
        tf = new JTextArea();
        bt = new JButton("退出");

        tf.addMouseMotionListener(this);
        tf.addMouseListener(this);
        bt.addActionListener(this);

        f.add(label, BorderLayout.NORTH);
        f.add(tf, BorderLayout.CENTER);
        f.add(bt, BorderLayout.SOUTH);
        f.setSize(300, 200);
        f.setVisible(true);
    }

    // MouseMotionListener 的方法。
    public void mouseDragged(MouseEvent e) {
        String s = number++ + " " + "The mouse Dragged: x= " + e.getX() + " y=" + e.getY() + "\n";
        tf.append(s);
    }

    //MouseListener 的方法。
    public void mouseEntered(MouseEvent e) {
        String s = number++ + " " + "The mouse entered" + "\n";
        tf.append(s);
    }

    public void mouseClicked(MouseEvent e) {
        String s = number++ + " " + "The mouse clicked." + "\n";
        tf.append(s);
    }

    public void mouseExited(MouseEvent e) {
        String s = number++ + " " + "The mouse exit." + "\n";
        tf.append(s);
    }

    // 未使用的 MouseMotionListener 方法。.
    public void mouseMoved(MouseEvent e) {
    }

    // 未使用的 MouseListener 方法。
    public void mouseReleased(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    //ActionListener的方法。
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public static void main(String args[]) {
        ComplexListener two = new ComplexListener();
    }
}
```

```java
package com.zjk.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MultiListener extends JFrame implements ActionListener {

    JTextArea topTextArea;
    JTextArea bottomTextArea;
    JButton button1, button2;

    public MultiListener(String s) {
        super(s);

        JLabel l = null;
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);    //Frame设置为GridBagLayout布局管理器。

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        l = new JLabel("监听器听到的:");
        gridbag.setConstraints(l, c);
        add(l);

        c.weighty = 1.0;
        topTextArea = new JTextArea(5, 20);
        topTextArea.setEditable(false);
        gridbag.setConstraints(topTextArea, c);
        add(topTextArea);

        c.weightx = 0.0;
        c.weighty = 0.0;
        l = new JLabel("偷听者听到的:");
        gridbag.setConstraints(l, c);
        add(l);

        c.weighty = 1.0;
        bottomTextArea = new JTextArea(5, 20);
        bottomTextArea.setEditable(false);
        gridbag.setConstraints(bottomTextArea, c);
        add(bottomTextArea);

        c.weightx = 1.0;
        c.weighty = 0.0;
        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 0, 10);
        button1 = new JButton("啦 啦 啦");
        gridbag.setConstraints(button1, c);
        add(button1);

        c.gridwidth = GridBagConstraints.REMAINDER;
        button2 = new JButton("你别说话!");
        gridbag.setConstraints(button2, c);
        add(button2);

        //当前MultiListener对象同时监听两个Button的事件。
        button1.addActionListener(this);
        button2.addActionListener(this);

        //为第二个Button再注册一个监听器。
        button2.addActionListener(new Eavesdropper());

        //向窗口注册响应关闭窗口操作的监听器。
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        topTextArea.append(e.getActionCommand() + "\n");
    }

    //第二个Button的监听器类。
    class Eavesdropper implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            bottomTextArea.append("OK," + e.getActionCommand() + "\n");
        }
    }

    public static void main(String[] args) {

        MultiListener m = new MultiListener("Multilistener example");

    }
}
```

### 事件适配器

- Adapter类实现了相应Listener接口，但所有方法体都是空的。
- 用户可以把自己的监听器类声明为adapter类的子类，便可以只重写需要的方法

## WindowBuilder 插件的使用

**1.创建文件**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/13771911236831.png =487x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/574291911232585.png =402x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/44372111250465.png =408x)

**2.进入可视化编辑**

- Design

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/227493211245571.png =718x)

# Applet

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

## 生命周期

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

![](c:/users/zjk10/onedrive/notebook/pictures/Snipaste_2023-03-02_22-54-14.png =800x)

## Applet的类层次结构

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

## Applet的显示

**Applet类继承了Component类**的组件绘制和显示的方法，有**paint()、update()、repaint()**方法。

- paint(Graphics g)： 向Applet中画图、显示字符串时使用该方法。每当Applet初次显示或更新时，浏览器都将调用paint(Graphics g)方法。
   - 该方法的参数为java.awt.Graphics类的实例，包括了Applet的Panel的图形上下文信息。 
- update()：用于更新Applet的显示。该方法将首先清除背景，然后再调用paint()方法进行Applet的具体绘制。用户定义的Applet该方法一般不用重写。
- repaint()：主要用于对Applet的重新显示，调用update()方法实现对Applet的更新。Applet程序可以在需要显示更新时调用该方法，通知系统刷新显示。

**Applet的线程更新由一个AWT线程控制，有以下两种控制：**

1. 在Applet的**初次显示，或运行过程中浏览器窗口大小发生变化**而引起Applet的显示发生变化时，该线程将调用Applet的**paint()**方法来进行Applet绘制。
2. Applet代码需要**更新显示内容**，从程序中调用repaint()方法，则AWT线程在接受到该方法的调用后，将调用Applet的update()方法，而update()方法再调用组件的paint()方法实现显示的更新。即：**repaint()-->update()-->paint()**

**Graphics类**

# JDBC

- JDBC驱动管理器是Java虚拟机的一个组成部分，即负责针对各种类型DBMS的JDBC驱动程序，也负责和用户的应用程序交互，为Java建立数据库连接。
- Java应用程序通过JBDC API向JDBC驱动管理器发出请求，指定要安装的JDBC驱动程序类型和数据源。驱动管理器根据这些要求装载合适的JDBC驱动程序并使该驱动连通相应的数据源。一旦连接成功，该JDBC驱动就负责Java应用与该数据源的一切交互。

## 准备：JDBC驱动

### Oracle

1. 到Oracle的路径 C:\app\zjk10\product\11.2.0\dbhome_1\jdbc\lib
2. 在IDEA中添加ojdbc6.jar和ojdbc6_g.jar

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/114651322247614.png =300x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/87841222239581.png =512x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/340241022221155.png)

## 基本方法

**DriverManager类**

1. 建立连接
- Class.forName("驱动")   //JDBC4.0新特性，可以忽略该语句
   - 加载相应数据库驱动(DriverManager类)的实例并注册到驱动管理器 
   - Oracle驱动 `oracle.jdbc.driver.OracleDriver`
- getConnection(String url, String user, String passwd) 
   - DriverManger类的方法，连接数据库
   - url : 指定使用的数据库访问协议和数据源
      - 格式`jdbc:数据库访问协议:数据源`
      - `jdbc:oracle:thin:@localhost:1521:ORCL`
         - 即：协议(jdbc):子协议(oracle:thin):数据源标识(@localhost:1521:ORCL)
         - `jdbc` 即Java数据库连接，实质是一个Java API，可以为多种关系数据库提供统一访问，它由一组用Java语言编写的类和接口组成
         - `oracle:thin` 指出连接的是oracle数据库，同时连接方式为thin方式，即瘦方式，不需要客户端的方式。与之对的另一种连接方式为胖方式：cli，这种方式需要安装客户端。 
         - `@localhost:1521:orcl`
            - `localhost`  数据库的地址，非本地时可按为数据库的IP地址；
            - `1521` orcal数据库默认的监听端口，可换为其他监听端口；
            - `orcl` orcal数据库默认的一个实例SID，可按为其他实例名。

**Statement类**

2. 执行SQL语句
- createStatement()
   - 创建Statement对象
- executeQuery(String sql)
   - 执行查询的SQL语句，返回一个结果集ResultSet对象 
- executeUpdate(String sql)
   - 执行INSERT、UPDATE、DELETE、DDL语句等的SQL语句
- 注：
   - SQL语句的空格不能忽略，即使字符串换行了，也必须留有空格。
   - SQL语句结尾不能有分号 `;`  

**ResultSet类**
 
- 在ResultSet的对象中，结果集通过游标控制具体记录的访问，游标指向结果集中的当前记录。

3. 处理结果集（ResultSet类的对象）
- next() 
   - 将游标移到下一行，并将该行作为用户操作的当前行。
   - 如果没有下一行，则返回false
- getXxx(String  colName)   
   - 通过列名，获取指定列的值
   - Xxx表示该列的数据类型 
- getXxx(int columnIndex)
   - 通过结果集中列的序号，获取指定的值。
   - 以1开始，而不是0
   
4. 关闭数据库的连接
- 一般先关闭Statement对象
- close()

```java
package com.zjk.java1;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) {
        try {
            //1.获取驱动
            //Oracle驱动 oracle.jdbc.driver.OracleDriver
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        Statement statement = null;

        try {
            //2.建立连接
            //Oracle的url jdbc:oracle:thin:@localhost:1521:ORCL
            String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
            connection = DriverManager.getConnection(url, "scott", "tiger");
            statement = connection.createStatement();

            //3.执行SQL语句
            //查询
            String selectSQL = "SELECT employee_id,department_id,last_name " +
                    "FROM employees " +
                    "WHERE department_id = 80";
            ResultSet resultSet = statement.executeQuery(selectSQL);
            //4.处理结果集
            int emp_id;
            int dep_id;
            String emp_name;
            while (resultSet.next()) {
                emp_id = resultSet.getInt(1);
                dep_id = resultSet.getInt(2);
                emp_name = resultSet.getString("last_name");
                System.out.println(emp_id + " : " + dep_id + " : " + emp_name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                //5.关闭连接
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

## 高级特性

### 预编译语句 PraparedStatement接口

**对比**

- 在创建Statement对象时，并未指定和执行SQL语句，在执行SQL语句时，需要将SQL语句发送给DBMS，再由DBMS进行编译后执行。
- 在创建PreparedStatement对象时，就指定了SQL语句并立刻发送给DBMS进行编译，当该PraparedStatement对象执行SQL语句时，DBMS只需要执行已经编译好的语句即可。

**创建预编译语句**

- prepareStatement(String sql)
  - 创建PreparedStatement对象
  - 该SQL语句可带有`参数表示符 ? `，表示SQL语句中的参数。

**设置预编译语句中的SQL语句参数**

- setXxx(int paramIndex, Xxx x)
  -  通过在SQL语句中参数的序号paramIndex（从1开始），设置相应数据类型Xxx的参数值x。
  - 预编译语句中的SQL语句参数经过设置后，会一直保留，直到被设为新值或调用了clearParameters()方法
- clearParameters()
  - 清除预编译语句的SQL语句的所有参数设置。 

```java
import java.sql.*;

public class JDBCTest2 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
            connection = DriverManager.getConnection(url, "scott", "tiger");

            //创建预编译语句
            String sql = "SELECT employee_id,department_id,last_name " +
                    "FROM employees " +
                    "WHERE department_id = ? " +
                    "  AND salary < ?";
            preparedStatement = connection.prepareStatement(sql);

            //设置预编译语句SQL语句的参数
            preparedStatement.setInt(1, 10);
            preparedStatement.setInt(2, 10000);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("employee_id") + " : " + resultSet.getInt("department_id") + " : " + resultSet.getString("last_name"));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

### 存储过程的调用 CallableStatement类

**创建CallableStatement对象**

- prepareCall("{call 存储过程名}")

**执行**

- executeQuery()
   - 存储过程中执行的是查询时
- execute()
   - 存储过程中有更新操作时
- execute()
   - 存储过程中既有查询有更新时。 

```java
package com.zjk;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest3 {
    public static void main(String[] args) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
            String callSQL = "{call emp_id_sal(1002,'小红','女')}";
            callableStatement = connection.prepareCall(callSQL);
            connetion.setAutoCommit(true);

            callableStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                callableStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
```

### 自动提交 Connection接口

**Connection接口的方法**

- setAutoCommit(boolean b)
  - 自动提交 
- commit()
  - 显示调用提交COMMIT
-  rollback()
  - 显示调用回滚ROLLBACK

```java
package com.zjk.java2;

import java.sql.*;

public class JDBCTest4 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");

            connection.setAutoCommit(true);

            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM jdbc_table");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " : " + resultSet.getString("name") + " : " + resultSet.getString("sex"));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

## JDBC2.0和JDBC3.0新特性

### 游标定位的操作 ResultSet类

- last()
   - 将游标定位到最后一行 

### 直接通过Java实现更新操作 ResultSet类

- createStatement(ResultSet.TYPE_SCROILL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
   - 设置返回的结果集X 
- updateXxx(String colName, Xxx x)
   - 根据指定的列名colName来更新相应的数据（Xxx数据类型的x）
   - 指定的列名必须是在JDBC的SQL语句中出现的(不包括`*`)，否则：java.sql.SQLException: 对只读结果集的无效操作
- updateRow() 
   - 使更新操作生效

```java
package com.zjk.java2;

import java.sql.*;

public class JDBCTest4 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");

            connection.setAutoCommit(true);

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = statement.executeQuery("SELECT id,name,sex FROM jdbc_table");

            resultSet.last();
            resultSet.updateString("name", "小均");
            resultSet.updateRow();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

### 批量更新操作 Statement类

- addBatch(Stirng sql)
  - 向列表中添加SQL语句，将列表中的SQL语句作为一个单元发送到DBMS进行批量执行操作。
- executeBatch()
  - 执行批量操作 

```java
package com.zjk.java2;

import java.sql.*;

public class JDBCTest4 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");

            connection.setAutoCommit(true);

            statement = connection.createStatement();

            statement.addBatch("INSERT INTO jdbc_table VALUES(1001,'Jac','男')");
            statement.addBatch("INSERT INTO jdbc_table VALUES(1003,'Tom','男')");
            statement.addBatch("INSERT INTO jdbc_table VALUES(1004,'Ju','女')");

            statement.executeBatch();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

## JDBC4.0新特性

### 驱动和连接管理

- 不再需要Class.forName()来显示地加载驱动。可以直接忽略。
- 当建立数据库连接时，DriverManager.getConnection()，自动再程序地CLASSPATH中找到驱动。

### 连锁异常处理的获取 forEach

```java
try{
    ...
}catch(SQLException sx){
    for(Throwable e: sx){
        System.err.println(e)
    }
}
```

### 数据类型支持

# Java8新特性

## Lambda表达式

- 在Java中的本质：作为函数式接口的实例。

**格式**

`->` Lambda操作符，箭头操作符
   - `->` 左边：Lambda形参列表（即：接口中的抽象方法的形参列表）
   - `->` 右边：Lambda体（其实就是重写的抽象方法的方法体）
- 参数名称可以随便定

**语法格式**

- 语法格式1：无参无返回值
- 语法格式2：有参无返回值
- 语法格式3：类型推断，数据类型可以省略，可由编译器推断得出
- 语法格式4：Lambda如果只需要一个参数，参数的小括号可以省略
- 语法格式5：Lambda需要两个或以上的参数，多条执行语句，并且可以有返回值
- 语法格式6：当Lambda体只有一条语句时，return与大括号可以省略

1. 左边：Lambda形参列表参数类型可以省略（类型推断），如果Lambda形参列表只有一个参数，其一对()可以省略。
2. 右边：Lambda体应该使用一对{}包裹，如果Lambda体只有一条执行语句（可能是return语句），可以省略这对{}和return关键字。(要么一起省略，要么不省略)

```java
package com.zjk.java1;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;

public class LambdaTest1 {
    @Test
    public void test1() {
        //语法格式1：无参无返回值
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我是中国人");
            }
        };

        r1.run();

        //Lambda表达式
        Runnable r2 = () -> System.out.println("我是中国人");
        r2.run();
    }

    @Test
    public void test2() {
        //语法格式2：有参无返回值
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Consumer" + s);
            }
        };
        consumer.accept("客户端");

        //lambda表达式
        Consumer<String> consumer1 = (String s) -> {
            System.out.println(s);
        };
        consumer1.accept("客服端 Lambda");
    }

    @Test
    public void test3() {
        //语法格式3：类型推断，数据类型可以省略，可由编译器推断得出。
        //lambda表达式
        Consumer<String> consumer1 = (s) -> {
            System.out.println(s);
        };
        consumer1.accept("客服端 Lambda");
    }

    @Test
    public void test4() {
        //语法格式4：Lambda如果只需要一个参数，参数的小括号可以省略。
        Consumer<String> consumer = s -> {
            System.out.println(s);
        };
        consumer.accept("你好");
    }

    @Test
    public void test5() {
        //语法格式5：Lambda需要两个或以上的参数，多条执行语句，并且可以有返回值
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(01, 02);
            }
        };

        //Lambda表达式
        Comparator<Integer> comparator1 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(comparator1.compare(21, 12));
    }

    @Test
    public void test6() {
        //语法格式6：当Lambda体只有一条语句时，return与大括号可以省略。
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(comparator.compare(12, 21));
    }
}
```

## 函数式接口

- OOF 面向函数编程
- 如果一个接口中只声明了一个抽象方法，则此接口就是函数式接口。
    - 注解：`@FunctionalInterface`，(就算不加该注解也是，注解Annotation的使用)
- 可以通过Lambda表达式创建该接口的实例
    - Lambda表达式的本质 
- 在`java.util.function`包下定义了Java8丰富的函数式接口

```java
@FunctionalInterface
public interface MyInterface {
    void method();
}
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-17_14-48-16.png =600x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-17_14-52-04.png =600x)

```java
package com.zjk.java1;

import org.junit.jupiter.api.Test;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdaTest2 {
    @Test
    public void test1() {
        happyTime(500, money -> System.out.println("花了" + money));
    }

    public void happyTime(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList();
        list.add("java");
        list.add("python");
        list.add("redius");

        System.out.println(filterString(list, s -> s.contains("j")));
    }

    //根据给定的规则，过滤集合中的字符串。此规则由Predicate的方法决定
    public List<String> filterString(List<String> list, Predicate<String> predicate) {
        ArrayList<String> filterLister = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                filterLister.add(s);
            }
        }
        return filterLister;
    }
}
```

## 方法引用

- 当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！
- 方法引用可以看做是Lambda表达式深层次的表达。换句话说，方法引用就是Lambda表达式，也就是函数式接口的一个实例，通过方法的名字来指向一个方法，可以认为是Lambda表达式的一个语法糖。

**要求：**

- 实现接口的抽象方法的参数列表和返回值类型，必须与方法引用的方法的参数列表和返回值类型保持一致！

**格式：** 

- 使用操作符 “::” 将类(或对象) 与 方法名分隔开来。
- 如果有返回值，返回值的类型也需要在泛型中声明，按顺序在泛型声明的最后。

**如下三种主要使用情况：**

- `对象::实例方法名`
- `类::静态方法名`
- `类::实例方法名`
   - 此时，在方法调用中，第一个参数作为第二个参数的调用者 

```java
package com.zjk.java2;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 方法引用的使用
 * <p>
 * Created by shkstart.
 */
public class MethodRefTest {

    // 情况一：对象 :: 实例方法
    //Consumer中的void accept(T t)
    //PrintStream中的void println(T t)
    @Test
    public void test1() {
        Consumer<String> consumer = str -> System.out.println(str);

        //方法引用
        PrintStream ps = System.out;
        Consumer<String> consumer1 = ps::println;
        consumer1.accept("你好");
    }

    //Supplier中的T get()
    //Employee中的String getName()
    @Test
    public void test2() {
        Employee employee = new Employee(1001, "Tom", 24, 26000);
        Supplier<String> stringSupplier = () -> employee.getName();

        //方法引用
        Supplier<String> supplier = employee::getName;
        System.out.println(supplier.get());
    }

    // 情况二：类 :: 静态方法
    //Comparator中的int compare(T t1,T t2)
    //Integer中的int compare(T t1,T t2)
    @Test
    public void test3() {
        Comparator<Integer> comparator = (i1, i2) -> Integer.compare(i1, i2);

        //方法引用
        Comparator<Integer> comparator1 = Integer::compare;
        System.out.println(comparator1.compare(12, 23));
    }

    //Function中的R apply(T t)
    //Math中的Long round(Double d)
    @Test
    public void test4() {
        Function<Double, Long> function = Math::round;
        //需要与方法的参数列表和返回值类型一致
        System.out.println(function.apply(23.32));
    }

    // 情况三：类 :: 实例方法
    //在方法调用中，第一个参数作为第二个参数的调用者
    // Comparator中的int comapre(T t1,T t2)
    // String中的int t1.compareTo(t2)
    @Test
    public void test5() {
        Comparator<String> comparator = (t1, t2) -> t1.compareTo(t2);

        //方法引用
        Comparator<String> comparator1 = String::compareTo;

        System.out.println(comparator1.compare("12", "你好"));
    }

    //BiPredicate中的boolean test(T t1, T t2);
    //String中的boolean t1.equals(t2)
    @Test
    public void test6() {
        BiPredicate<String, String> biPredicate = String::equals;
        System.out.println(biPredicate.test("test", "test"));
    }

    // Function中的R apply(T t)
    // Employee中的String getName();
    @Test
    public void test7() {
        Function<Employee, String> function = Employee::getName;
        System.out.println(function.apply(new Employee(1002, "Tom", 24, 24000)));
    }
}
```

### 构造器引用

- 和方法引用类似，函数式接口的抽象方法的参数列表和构造器的形参列表一致。抽象方法的返回值即为构造器所属类的类型。

```java
package com.zjk.java2;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、构造器引用
 * <p>
 * 二、数组引用
 * <p>
 * <p>
 * Created by shkstart
 */
public class ConstructorRefTest {
    //构造器引用
    //Supplier中的T get()
    @Test
    public void test1() {
        Supplier<Employee> supplier = () -> new Employee();

        //构造器引用 空参构造器
        Supplier<Employee> supplier1 = Employee::new;
        System.out.println(supplier1.get());
    }

    //Function中的R apply(T t)
    @Test
    public void test2() {
        Function<Integer, Employee> function1 = id -> new Employee(id);
        System.out.println(function1.apply(1001));

        //构造器引用 带一个参数的构造器
        Function<Integer, Employee> function = Employee::new;
        System.out.println(function.apply(1002));

    }

    //BiFunction中的R apply(T t,U u)
    @Test
    public void test3() {
        //构造器引用 带两个参数的构造器
        BiFunction<Integer, String, Employee> biFunction = Employee::new;
        System.out.println(biFunction.apply(1003, "Jac"));
    }
}
```

### 数组引用 

```java
//数组引用  类似于构造器引用
//Function中的R apply(T t)
@Test
public void test4() {
    Function<Integer, String[]> function1 = length -> new String[length];
    String[] arr1 = function1.apply(5);

    //数组引用
    Function<Integer, String[]> function = String[]::new;
    String[] apply = function.apply(10);
}
```

## Stream API

- 是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。

**注意：**

1. Stream 自己不会存储元素。
2. Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
3. Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。

**Stream 和 Collection 集合的区别：**

Collection 是一种静态的内存数据结构，而 Stream 是有关计算的。前者是主要面向内存，存储在内存中，后者主要是面向 CPU，通过 CPU 实现计算。

**Stream 的操作三个步骤**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_13-07-50.png =600x)

### 实例化

**1. 通过集合的方式**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_13-14-03.png =500x)

**2. 通过数组**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_13-14-57.png =450x)

**3. 通过Stream的of()**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_13-16-01.png =500x)

**4. 创建无限流**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_13-16-40.png =600x)

```java
package com.zjk.java2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPITest {
    //1. 通过集合获取Stream实例
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();
        //stream() 返回顺序流
        Stream<Employee> stream = employees.stream();
        //parallelStream 返回并行流
        Stream<Employee> employeeStream = employees.parallelStream();
    }

    //2. 通过数组获取Stream实例
    @Test
    public void test2() {
        //调用Arrays类中的stream(arr)
        int[] arr = {1, 2, 3};
        IntStream stream = Arrays.stream(arr);
        Employee[] employees = {new Employee(), new Employee()};
        Stream<Employee> stream1 = Arrays.stream(employees);
    }

    //3. 通过Stream的of()
    @Test
    public void test3() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
    }

    //4. 创建无限流
    @Test
    public void test4() {
        //iterate() 迭代
        Stream.iterate(0, t -> t + 2).forEach(System.out::println);
        //generate() 生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
```

### Stream 的中间操作

多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理！而在终止操作时一次性全部处理，称为“惰性求值”

**1-筛选与切片**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_13-17-52.png =650x)

**2-映 射**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_13-18-40.png =650x)

**3-排序**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_13-18-59.png =500x)

```java
package com.zjk.java2;

import org.junit.jupiter.api.Test;

import javax.print.DocFlavor;
import java.util.*;
import java.util.stream.Stream;

public class StreamAPITest1 {
    //1-筛选与切片
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();
        //filter(Predicate p) 接收 Lambda ， 从流中排除某些元素
        Stream<Employee> stream = employees.stream();
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);
        //limit(long maxSize) 截断流，使其元素不超过给定数量
        employees.stream().limit(3).forEach(System.out::println);
        //skip(long n) 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
        employees.stream().skip(3).forEach(System.out::println);
        //distinct() 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
        employees.stream().distinct().forEach(System.out::println);
    }

    //2-映射
    @Test
    public void test2() {
        List<String> list = Arrays.asList("aa", "bb", "cc");
        //map(Function f) 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
//        获取员工姓名长度大于3的
        Stream<String> stringStream = EmployeeData.getEmployees().stream().map(Employee::getName);
        stringStream.filter(name -> name.length() > 3).forEach(System.out::println);
        //mapToDouble(ToDoubleFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 DoubleStream。
        //mapToInt(ToIntFunction f)接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 IntStream。
        //mapToLong(ToLongFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 LongStream。
//
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::fromStringToStream);
        streamStream.forEach(s -> {
            s.forEach(System.out::println);
        });
        //flatMap(Function f) 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::fromStringToStream);
        characterStream.forEach(System.out::println);

    }

    public static Stream<Character> fromStringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    //3-排序
    @Test
    public void test3() {
        //sorted() 自然排序
        List<Integer> integers = Arrays.asList(12, 43, 23, 344, 32);
        integers.stream().sorted().forEach(System.out::println);

        //sorted(Comparator comparator) 定制排序
//        Comparator comparator = new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                if (o1 instanceof Employee && o2 instanceof Employee) {
//                    Employee e1 = (Employee) o1;
//                    Employee e2 = (Employee) o2;
//                    return e1.getAge() - e2.getAge();
//                } else {
//                    throw new RuntimeException("数据类型不一致");
//                }
//            }
//        };
        List<Employee> employees = EmployeeData.getEmployees();
//        employees.stream().sorted(comparator).forEach(System.out::println);
        employees.stream().sorted((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge())).forEach(System.out::println);

        employees.stream().sorted((e1, e2) -> {
            int num;
            if ((num = Integer.compare(e1.getAge(), e2.getAge())) != 0) {
                return num;
            } else {
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
        }).forEach(System.out::println);
    }
}
```

### Stream 的终止操作

- 终端操作会从流的流水线生成结果。其结果可以是任何不是流的值，例如：List、Integer，甚至是 void 。
- 流进行了终止操作后，不能再次使用。

**1-匹配与查找**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_13-22-27.png =500x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_13-20-38.png =500x)

**2-归约**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_13-23-10.png =550x)

**3-收集**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_13-24-04.png =550x)

- Collector 接口中方法的实现决定了如何对流执行收集的操作(如收集到 List、Set、Map)。
- 另外， Collectors 实用类提供了很多静态方法，可以方便地创建常见收集器实例，具体方法与实例如下表：
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_23-43-27.png =650x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_23-44-41.png =650x)

```java
package com.zjk.java2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPITest2 {
    //1-匹配与查找
    @Test
    public void test1() {
        List<Employee> employeeList = EmployeeData.getEmployees();
        //allMatch(Predicate p) 检查是否匹配所有元素
//        判断是否所有的员工的年龄大于18
        System.out.println(employeeList.stream().allMatch(e -> e.getAge() > 18)); //false
        //anyMatch(Predicate p) 检查是否至少匹配一个元素
        System.out.println(employeeList.stream().anyMatch(e -> e.getName().equals("马云"))); //true
        //noneMatch(Predicate p) 检查是否没有匹配所有元素
        System.out.println(employeeList.stream().noneMatch(e -> e.getSalary() > 0)); //false
        //findFirst() 返回第一个元素
        System.out.println(employeeList.stream().sorted((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge())).findFirst());
//        Optional[Employee{id=1002, name='马云', age=12, salary=9876.12}]
        //findAny() 返回当前流中的任意元素
        System.out.println(employeeList.parallelStream().findAny());
        //count() 返回流中元素总数
        System.out.println(employeeList.stream().count()); //8
//        工资大于5000的人个数
        System.out.println(employeeList.stream().filter(e -> e.getSalary() > 5000).count()); //5
        //max(Comparator c) 返回流中最大值
//        返回最大年龄的
        System.out.println(employeeList.stream().max((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge())));
//        Optional[Employee{id=1005, name='李彦宏', age=65, salary=5555.32}]
        Stream<Integer> integerStream = employeeList.stream().map(Employee::getAge);
        System.out.println(integerStream.max(Integer::compare)); //Optional[65]
        //min(Comparator c) 返回流中最小值
//        返回工资最低的
        System.out.println(employeeList.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
//        Optional[Employee{id=1008, name='扎克伯格', age=35, salary=2500.32}]
        System.out.println(employeeList.stream().map(Employee::getSalary).min(Double::compare));
//        Optional[2500.32]
        //forEach(Consumer c) 内部迭代(使用 Collection 接口需要用户去做迭代，称为外部迭代。
        // 相反，Stream API 使用内部迭代——它帮你把迭代做了)
    }

    //2-归约
    @Test
    public void test2() {
        //reduce(T iden, BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回 T
//        计算1~10的和
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(integers.stream().reduce(0, Integer::sum)); //55
//        0 --设置的初始值
        //reduce(BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回 Optional<T>
        System.out.println(integers.stream().reduce(Integer::sum)); //Optional[55]

//        计算员工工资的和
        System.out.println(EmployeeData.getEmployees().stream().map(Employee::getSalary).reduce(Double::sum));
//        Optional[48424.08]
        System.out.println(EmployeeData.getEmployees().stream().map(Employee::getSalary).reduce((e1, e2) -> e1 + e2));
    }

    //3-收集
    @Test
    public void test3() {
        //collect(Collector c) 将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
//        返回工资大于6000 的集合
        List<Employee> collect = EmployeeData.getEmployees().stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }
}
```

## Optional 类

- `Optional<T> `类(java.util.Optional) 是一个容器类，它可以保存类型T的值，代表这个值存在。或者仅仅保存null，表示这个值不存在。原来用 null 表示一个值不存在，现在 Optional 可以更好的表达这个概念。并且可以避免空指针异常。
- Optional类的Javadoc描述如下：这是一个可以为null的容器对象。如果值存在则`isPresent()`方法会返回true，调用`get()`方法会返回该对象。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_13-28-19.png =650x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-20_16-50-36.png =550x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-20_16-59-09.png =400x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-20_17-02-46.png =550x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-20_17-04-33.png =550x)

# Java9 新特性

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-20_17-11-53.png =400x)

## JDK 和 JRE 目录结构的改变

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-20_17-13-20.png =600x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-20_17-14-03.png =600x)

## 模块化系统: Jigsaw --> Modularity



# Java10 新特性
# Java11新特性