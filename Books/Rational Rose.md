# Rational Rose

[RationalRose](https://blog.csdn.net/gz153016/article/details/49641847)

[RationalRose简易教学](file:///c:/notebook/Attach/Rational_Rose/Rational%20Rose简明实用教程_gz153016的博客-CSDN博客_rational%20rose.html)

# UML概述

**UML** 可视化的面向对象的统一**建模语言**

- 软件开发过程中的分析设计阶段使用。
- 支持面向对象
- 独立与软件的实现

## 基本元素

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_14-47-34.png" width="500"/> 

### 结构事物 

- 构成模型的静态部分，负责描述静态功能和客观元素
- UML中一共定义了7种结构事物：类、接口、用例、构件、节点 等

#### 类

- 完全对应于面向对象分析中的类，具有自己的属性和操作。 


**创建类**

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_15-13-11.png" width="500"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_14-48-28.png" width="500"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_14-50-03.png" width="500"/> 

#### 接口

- 由一组对操作的定义组成，不包括对操作的实现进行详细的描述。
- 用于描述一个类或构件的一个服务的操作集。
- 描述了元素的外部可见的操作。

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_14-57-14.png" width="200"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_14-58-48.png" width="500"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_15-01-53.png" width="500"/> 

#### 用例

- 用于表示系统所提供的服务
- 定义了系统是如何被参与者使用的，描述的是参与者为了使用系统所提供的某一完整功能而与系统发生的一段对话。
- 用例是对一组动作序列的抽象描述。
- 系统执行这些动作将产生一个对特点的参与者有价值而且可观察的结果。
- 用例可结构化系统中的行为事物，从而可视化的概括系统需求。

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_15-21-22.png" width="500"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_15-22-17.png" width="500"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_15-27-17.png" width="500"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_16-45-09.png" width="500"/> 

#### 构件

**目的**

1. 将一个大系统分解成若干个小软件（子系统），有助于降低系统开发的复杂度。
2. 采用构件机制开发的系统，在系统需要修改是，更为容易。
3. 构件机制有助于提高复用水平，同时也是一种好的销售方法。

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_15-30-11.png" width="500"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_15-30-48.png" width="500"/> 

#### 节点

- 是系统在运行时切实存在的物理对象，表示某种可计算资源，这些资源往往具有一定的存储能力和处理能力。
- 一个节点可以代表一个物理机或一个虚拟物理机。

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_15-40-18.png" width="300"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_15-38-40.png" width="500"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_15-39-44.png" width="400"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_15-42-09.png" width="400"/> 

### 行为事物

- UML模型中相关的动态行为，通常使用动名词来描述。
- 将行为事物分为两类：
  - 交互
  - 状态机：结构事物状态的变化

#### 交互

- 交互的可视化表示主要通过消息来表示

##### 顺序图

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_15-51-02.png" width="500"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_15-54-57.png" width="500"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_15-56-34.png" width="500"/> 

#### 状态机

- 是一个类对象所有的**生命历程**模型，因此状态机可以用于描述一个对象在其生命周期内响应时间所经历的**状态序列**。
- 当对象探测到一个**外部事件**后，它依照当前的状态做出反应，这种反应包括执行一个相关动作或转换到一个新的状态中去。
- 单个类的状态变换或多个类之间的协作过程都可以使用状态机描述。

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_16-33-07.png" width="400"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_16-34-45.png" width="500"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_16-37-09.png" width="500"/> 

### 分组事物

- 是UML对模型中的各种组成部分进行事物分组的机制。可以把分组事物当成是一个“盒子“，不同的盒子存放不同的模型。

**包**

- 目前只有一种分组事物：包
- 包是一种在概念上对UML模型中各种组成部分进行分组的机制，只存在于软件的开发阶段
- 通常和类图等一起使用   

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_16-57-10.png" width="500"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_16-53-19.png" width="300"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_16-57-47.png" width="500"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_16-58-16.png" width="500"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_16-58-34.png" width="300"/> 
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-02-23_16-59-03.png" width="500"/> 

### 注释事物

- 注释事物是UML模型的解释部分，用于进一步解释UML模型。

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-03-09_14-25-44.png" width="500"/>

### 关系

- UML是由各种事物以及这些事物之间的各种关系构成的。

- 关系是指支配、协调各种模型元素存在并相互使用的规则

- 主要包含四种关系：依赖、关联、泛化、实现

#### 依赖关系

- 依赖关系指的是两个事物之间的一种语义关系，当其中一个事物（独立事物）发生变化就会影响另外一个事物（依赖事物）的语义。

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-03-09_14-30-46.png" width="500"/>

#### 关联关系

- 关联关系是事物之间的结构关系。当一个类“知道”另一个类时，可以使用关联关系来表示。
- 如：车间内工人和车间主任之间建立关联关系。

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-03-09_14-44-38.png" width="500"/>

#### 泛化关系

- 是事物之间的一种特殊/一般关系，特殊元素（子元素）的对象可替代一般元素（父元素）的对象（继承）。

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-03-09_14-46-14.png" width="500"/>

#### 实现关系

- 实现关系描述了一组操作的定义和一组对操作的具体实现之间的语义关系。

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-03-09_14-56-13.png" width="500"/>

## 图

- 最常用的UML图包括：用例图、类图、序列图、状态图、活动图、构件图、部署图。

### 用例图

- 用例图是用来描述系统功能的技术，主要用于需求分析阶段，，帮助开发团队以一种可视化的方式理解系统的功能需求。

- Use Case是指系统的外部事物（参与者）与系统的交互，表达了系统的功能，即系统向用户所提供的服务。
- Use Case图是后续的系统分析与设计工作的依据，也是系统测试的依据。

- Use Case图对需求的描述规范化，较好地避免了表达地歧义性，便于用户和开发人员理解系统的需求，取得共识。

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-03-09_15-18-20.png" width="500"/>

**含义**

- 由参与者（Actor）、用例（Use Case）依据他们之间的关系构成的用于描述系统功能的动态视图称为用例图。
- 用例：椭圆
- 参与者：人形图
  - 参与者是指存在于系统外部并直接与系统进行交互的人、其他系统或设备。
  - 每个参与者可以参与一个或多个用例，一个用例可以有多个参与者。
- 关系：带箭头和不带箭头；箭尾连接对话的主动发起者，箭头所指的是对话的被动接受者。   
  - 参与者可以是主动发起者和被动接收者。

**命名规则**

- 通常是doing之类的动名词为名。

#### 参与者

##### 如何确定参与者：

- 负责支持或维护系统中信息的人。
- 与系统进行信息交换（包括数据信息和控制信息交换）的外部事物可以确定为参与者；包括：人、设备、第三方系统。
- 时间是否会触发某些事件。时间也可以是参与者。

##### 识别参与者： 以图书管理系统为例

1. 系统开发以后，使用系统主要功能的对象。 （学生、教师）
2. 需要借助系统来完成日常工作的对象。 （图书管理员）
3. 系统需要从哪些对象（人或系统等）中获取数据。 （扫码枪、读卡器、学籍管理系统）
4. 系统会为哪些人或系统提供数据。 （借阅人员、学籍系统、图书管理员）
5. 系统会与哪些系统交互。包括计算机系统和计算机中的其他应用软件。其他系统可以分为两类：
      - 该系统要使用的系统 （E卡通系统）
      - 启动该系统的系统 （WindowsXP系统）
6. 系统是由谁来维护和管理的，以保证系统处于工作状态 （系统管理员）
7. 系统控制的硬件设备有哪些。 （扫码枪、读卡器）
8. 对本系统产生的结果感兴趣的对象。（馆长、图书供应商）

##### 对参与者的分类

**主要参与者和次要参与者**

- 主要参与者：执行系统主要功能的参与者。
- 次要参与者：使用系统次要功能的参与者。

**发起参与者和参加参与者**

- 参与者发起了用例的执行过程，一个用例只有一个发起参与者，可以有若干个参加参与者。

##### 参与者之间的泛化关系

- 参与者本质上也是类，参与者和参与者之间主要是泛化关系（继承）。
- 泛化关系：提取共同的用例，将用例都是共同的用例的参与者当作父类来继承。


#### 用例

- 用例是外部可见的系统功能，是参与者可以感受到的系统服务或功能单元。
- 每个用例在起所属包里都有唯一的位置，往往是一个能准确描述功能的动词或者动名词短语。
- 用例必须由某一个参与者触发激活后才能执行，即每个用例至少拥有一个参与者。

##### 如何从业务中查找用例

1. 参与者会将哪些外部事件通知给系统。 （续借图书）
2. 系统中发生的哪些事件会通知参与者。 （图书超期）
3. 是否存在影响系统的外部事件。 （E通卡升级） 
4. 参与者希望系统提供哪些功能。
5. 参与者是否会读取、创建、修改、存储系统的某种信息。

##### 用例粒度

- **用例的粒度指的是用例所包含的系统服务或功能单元的多少**。用例的粒度越大，用例包含的功能越大，反之则包含的功能越少。
- 如果用例的粒度很小，得到的用例数就越多。
- 如果用例数目过多会造成用例模型过大，用例之间的关系过于负责，导致后续的系统设计难度大大提高。如果用例的数目过少会造成用例的粒度太大，也不便于进一步的充分分析。

**一般的做法是负责系统的用例粒度大一些，简单系统的用例粒度小一些。**

##### 用例描述与文档

- 对于每一个用例，我们还需要有详细的描述信息，以便于别人对于者系统有一个更加详细的了解，这些信息包含在用例规范中。

**每一个用例的用例规约都应该包含以下：**

1. 简要说明：对用例名称、编号、相关参与者、作用和目的的简要说明。
2. 事件流：事件流包括基本事件流和扩展事件流。（基本流和备选流）
     - 基本事件流描述的是用例的基本流程，是指用例“正常“运行是的场景。
     - 扩展事件流包括用例中很少使用的逻辑路径，如异常情况、错误情况下所执行的逻辑路径。
3. 前置条件：执行用例之前系统必须所处的状态。例如，前置条件是要求用户有访问的权限或是要求某个用例必须先被执行等。
4. 后置条件：用例执行完毕之后系统可能处于的一组状态。如货品出库用例执行后，需要执行应付款处理用例。
5. 非功能性需求：包括可靠性、性能、可用性和可扩展性等。如：法律或法规方面的需求、应用程序标准和所构建系统的质量属性等。

##### 用例之间的关系

- 泛化、扩展

###### 泛化

- 用户的泛化指的是一个父用例可以被特化形成多个子用例，而父用例和子用例之间的关系就是泛化关系。
- 在用例的泛化关系中，子用例继承了父用例所有的结构、行为和关系，子用例是父用例的一种特殊形式。
- 子用例还可以添加、覆盖、改变继承的行为。
- 在UML中用例的泛化关系通过一个三角箭头从子用例指向父用例来表示。

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-03-09_16-51-46.jpg" width="500"/>

###### 扩展

- 在一定条件下，把新的行为加入到已有的用例中，获得新用例叫做扩展用例，原有的用例叫做基础用例，从扩展用例到基础用例的关系就是扩展关系。
- 扩展关系往往被用来处理异常或者构建灵活的系统框架。
- 通过扩展用例来添加对小概率事件的扩展。   

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2023-03-09_16-50-32.jpg" width="500"/>

###### 



<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/" width="500"/>

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/" width="500"/>

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/" width="500"/>

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/" width="500"/>

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/" width="500"/>

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/" width="500"/>

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/" width="500"/>

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/" width="500"/>

