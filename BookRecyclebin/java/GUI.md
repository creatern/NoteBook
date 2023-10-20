# Java GUI 概述

- GUI：基于Swing的图形化用户界面。

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

# Swing组件

- Swing中的所有组件全部用Java语言实现。
- 采用分离模型：组件及与组件相关的数据模型（或简称模型） 
- 可设置的组件外观感觉（look and feel ）
- 与AWT相比提供更丰富的GUI组件，引入新的特征，并提供更丰富的功能。
- 一般如果使用Swing组件，则程序中只使用Swing组件和Swing容器。
- JavaSwing组件和不能直接添加到顶层容器中，必须添加到一个与Swing顶层容器相关联的内容面板（ContentPane）上，内容面板即一个中间容器，是一个轻量级组件。

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

<img src="../../../pictures/420302910221148.png" width="490"/>   

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

## JComponent类

- Swing组件中,除了顶层容器组件，以J开头的都是JComponent类的子类
- JComponent类是java.awt.Container的子类，是抽象类。

<img src="../../../pictures/Snipaste_2022-11-21_10-34-34.png" width="600"/>   

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

## 常用容器

- 容器本身也是一个组件，具有组件的所有性质，另外还具有容纳其它功能
- 所有的组件都可以通过`add()`方法向容器中添加组件
- 在Swing中，常用的三种类型的容器是JFrame，JPanel，JApplet 
- Swing GUI形成顶层容器-中间层容器-基本组件的层次包容关系

### 顶层容器

- 具有Swing GUI的应用必须有至少一个顶层容器 
- 顶层Swing容器是JFrame、JDialog或JApplet的实例

**向顶层容器中加入组件**

- Swing组件不能直接添加到顶层容器中，必须添加到一个与顶层容器相关联的内容面板（Content Pane）上

#### JFrame 框架

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
- `Container    getContentPane()` 
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

#### JOptionPane类 对话框类

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

### 通用容器

#### JPanel

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

#### JScrollPane 滚动面板

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

#### JSplitPane 分隔面板

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

#### JTabbedPane类 标签面板

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

## 常用组件

### JButton类  按钮

### JLabel类  标签

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
- `JLabel(String text)`
  - 使用指定的文本创建一个 JLabel实例。
- `JLabel(String text, Icon icon, int horizontalAlignment)`
  - 创建具有 JLabel文本，图像和水平对齐的JLabel实例。
- `JLabel(String text, int horizontalAlignment)`
  - 创建一个具有指定文本和水平对齐的 JLabel实例。

### 文本类

#### JTextArea类

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


### 选择类

#### JComboBox类 选择框

<img src="../../../pictures/540921511236840.png" width="323"/>   

**构造器**

<img src="../../../pictures/243341411247616.png" width="414"/>   

**方法**

- `setModel(new DefaultComboBoxModel(数组))` 
  - 设置选项框的内容 
- `int getSelectedIndex()`
  - 返回当前选项框在setModel()的数组中的索引 

#### JCheckBox 复选框

<img src="../../../pictures/295231511240285.png" width="344"/>   

**构造器**

<img src="../../../pictures/18201311239583.png" width="518"/>   

#### JRadioButton 单选钮

<img src="../../../pictures/195341611232594.png" width="351"/>   

**构造器**

<img src="../../../pictures/376401311227450.png" width="518"/>   

#### ButtonGroup类 组合按钮

- 使选择之间互斥，一次只能选择一个。

<img src="../../../pictures/565802011250474.png" width="216"/>   

**构造器**

- ButtonGroup()

**方法**

<img src="../../../pictures/265392111248078.png" width="563"/>   

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

## 菜单组件

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

### 根菜单

#### JMenuBar 下拉式菜单

<img src="../../../pictures/Snipaste_2022-11-21_10-25-00.png" width="300"/>   

#### JPopupMenu 弹出式菜单/上下文菜单

### 次级菜单

#### JMenu

### 菜单选项

#### JMenuItem

<img src="../../../pictures/Snipaste_2022-11-21_11-24-19.png" width="500"/>   

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

## 布局管理器

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

### FlowLayout 流式布局管理器

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

### BorderLayout 边界布局管理器

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

| 参数               | 值(String) |
| :----------------- | :--------- |
| BorderLayout.WEST  | "West"     |
| BorderLayout.EAST  | "East"     |
| BorderLayout.NORTH | "North"    |
| BorderLayout.SOUTH | "South"    |

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

### setLayout(null) 无布局管理器

**方法**

- setLocation(int x, int y)
  - 设置组件大小
- setSize(int width, int height)
  - 设置组件位置
- setBounds(int x, int y, int width, int height)
  - 设置组件大小位置

# GUI中的事件处理

## 监听器

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

## 事件类

<img src="../../../pictures/160351619227442.png" width="414"/>   

| 事件            | 操作                                             | 接口                | 适配器类           | 方法                                                         |
| :-------------- | :----------------------------------------------- | :------------------ | :----------------- | :----------------------------------------------------------- |
| ActionEvent     | 激活组件操作                                     | ActionListener      |                    | ActionPerformed()                                            |
| AdjustmentEvent | 移动滚动条                                       | AdjustmentListener  |                    | adjustmentValueChanged()                                     |
| ComponentEvent  | 组件移动、缩放、显示、隐藏等                     | ComponentListener   | ComponentAdapter   | componentHidden() / componentMoved() / componentResized() / componentShown() |
| ContainerEvent  | 容器中增加或删除组件                             | ContainerListener   | ContainerAdapter   | componentAdded() / componentRemoved()                        |
| FocusEvent      | 组件得到或失去聚焦                               | FocusListener       | FocusAdapter       | focusGained() / focusLost()                                  |
| ItemEvent       | 条目状态改变                                     | ItemListener        |                    | itemStateChanged()                                           |
| KeyEvent        | 键盘输入                                         | KeyListener         | KeyAdapter         | keyPressed() / keyReleased() / keyTtyped()                   |
| MouseEvent      | 单击鼠标                                         | MouseListener       | MouseAdapter       | mouseClicked() / mouseEntered() / mouseExited() / mousePressed() / mouseReleased() |
| -               | 移动鼠标                                         | MouseMotionListener | MouseMotionAdapter | mouseDragged() / mouseMoved()                                |
| TextEvent       | 文本域或文本区值改变                             | TestListener        |                    | testValueChanged()                                           |
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

## 事件适配器

- Adapter类实现了相应Listener接口，但所有方法体都是空的。
- 用户可以把自己的监听器类声明为adapter类的子类，便可以只重写需要的方法

# WindowBuilder 插件的使用

**1.创建文件**

<img src="../../../pictures/13771911236831.png" width="487"/>   
<img src="../../../pictures/574291911232585.png" width="402"/>   
<img src="../../../pictures/44372111250465.png" width="408"/>   

**2.进入可视化编辑**

- Design

<img src="../../../pictures/227493211245571.png" width="718"/>   