# IDEF方法

- IDEF（ICAM DEFinition Method）方法由IDEF0、IDEF1、IDEF2三部分组成，之后被扩展为一个系列。

<table>
    <tr>
        <td width="10%">IDEF0</td>
        <td width="90%">功能建模方法，描述系统功能及相互关系</td>
    </tr>
    <tr>
        <td>IDEF1</td>
        <td>数据建模方法，描述系统信息及其数据之间的联系</td>
    </tr>
    <tr>
        <td>IDEF2</td>
        <td>用于系统模拟，建立动态模型</td>
    </tr>
    <tr>
        <td colspan="2">扩展</td>
    </tr>
    <tr>
        <td>IDEF3</td>
        <td>过程描述及获取方法</td>
    </tr>
    <tr>
        <td>IDEF4</td>
        <td>面向对象设计方法</td>
    </tr>
    <tr>
        <td>IDEF5</td>
        <td>本体论获取方法</td>
    </tr>
    <tr>
        <td>IDEF6</td>
        <td>设计原理获取方法</td>
    </tr>
    <tr>
        <td>IDEF7</td>
        <td>信息系统审定方法</td>
    </tr>
    <tr>
        <td>IDEF8</td>
        <td>用户接口建模方法</td>
    </tr>
    <tr>
        <td colspan="2">...</td>
    </tr>
</table>


# IDEF0模型 功能建模方法

<table>
    <tr>
        <td rowspan="5" width="30%"><img src="../../pictures/数据库系统概念-IDEF0.drawio.svg" width="300"/></td>
        <td width="10%">功能活动</td>
        <td width="60%">矩形框内的单词短语描述功能活动的名称；活动的编号写在右下角</td>
    </tr>
    <tr>
        <td>输入</td>
        <td>表示完成活动需要的数据</td>
    </tr>
    <tr>
        <td>控制</td>
        <td>描述了影响该活动执行的事件或约束条件</td>
    </tr>
    <tr>
        <td>输出</td>
        <td>说明由活动产生的结果及信息</td>
    </tr>
    <tr>
        <td>机制</td>
        <td>表示实施该活动的物理手段或完成活动需要的资源</td>
    </tr>
</table>

- IDEF0模型结构化地把一个复杂事务按自顶向下逐步细化的方法分解成一个简单的事务或多个组成部分，这些图形形成了一个由父到子的层次结构图。

1. 当一个功能活动被分解成几个子功能活动时，用箭头表示各子功能活动之间的接口。每个子功能活动的名称加上带标记的接口确定了一个范围，规定了子功能活动细节的内容。
2. 通常，子功能活动必须忠诚地（不增加也不减少）描述父功能活动的细节，反映父功能活动所包含的信息。

<img src="../../pictures/数据库系统概念-IDEF0层次结构图.drawio.svg" width="500"/> 

# IDEF1X 数据建模方法

- IDEF1X（数据建模方法）侧重分析、抽象和概括应用领域中的数据需求。

## 实体集

- 在IDEF1X中，每个实体集定义有一个唯一的名字和编码，之间用`/`分隔。

<table>
    <tr>
        <td>独立实体集</td>
        <td>一个独立实体集的每个实例都能被唯一地标识而不是决定于它与其他实体集的联系</td>
    </tr>
    <tr>
        <td>从属实体集</td>
        <td>从属实体集的一个实例的唯一标识依赖于该实体集与其他实体集的联系</td>
    </tr>
</table>

<img src="../../pictures/数据库系统概念-IDEF1X实体集.drawio.svg" width="300"/> 

## 联系

<img src="../../pictures/数据库系统概念-IDEF1X联系.drawio.svg" width="350"/> 

### 确定型连接联系

- 连接联系（Specific Connection Relationship，确定型连接联系、父子联系、依存联系）是实体集之间的一种连接或关系。在该连接联系中，被称为双亲实体集的每一个实例与子女实体集的0个、1个或多个实例相连接，子女实体集中的每个实例精确地同双亲实体集的一个实例相联系。

#### 标定型联系

- 标定型联系（Identifying Relationship）：在连接联系中，如果子女实体集中的每个实例都是由它与双亲的联系而确定的，那么这个联系就被称为是标定型联系。

<img src="../../pictures/数据库系统概念-标定型联系.drawio.svg" width="260"/> 

#### 非标定型联系

- 非标定型联系（Non-Identoifying Relationship）：在连接联系中，如果子女实体集中的每个实例都能被唯一地确认而无须了解与之相联系的双亲实体集的实例，那么这个联系就被称为非标定联系。

<img src="../../pictures/数据库系统概念-非标定型联系.drawio.svg" width="110"/> 

### 分类联系

- 分类联系（Categorization Relationship）是两个或多个实体集之间的联系，且在这些实体集中存在一个一般实体集（Genrice Entity），该实体集的每一个实例都恰好与有且只有与一个分类实体的一个实例相联系（具有相同的唯一标识符）。

<img src="../../pictures/数据库系统概念-分类联系.drawio.svg" width="280"/> 

### 非确定联系

- 非确定联系（Many to Many Relationship，多对多关系、m:n联系）：在非确定联系关联的两个实体集之间，任一实体集的一个实例都将对应另一实体集的0个、1个或多个实例。
- 在最终的IDEF1X模型中，所有实体集之间的联系都必须用确定联系来描述。

<img src="../../pictures/数据库系统概念-非确定联系.drawio.svg" width="110"/> 