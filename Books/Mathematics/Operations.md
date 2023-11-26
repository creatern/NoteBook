# 线性规划

## 标准型与解的性质

### 标准型

- 将一般线性规划问题转换为标准型：

1. 若目标函数求最小，可以将目标函数系数乘-1，等价为求最大。
2. 引入松弛变量和剩余变量作为初始基变量（单位矩阵）转换为标准型。松弛变量用于表示不足量；剩余变量用于表示剩余量。
3. 对于取值非正的决策变量x<sub>j</sub>，变量替换x<sub>j</sub> = -x<sub>j</sub>\'，（x<sub>j</sub>\' &ge;0）
4. 对于取值自由的决策变量x<sub>j</sub>，变量替换x<sub>j</sub> = x<sub>j</sub>\' - x<sub>j</sub>\'\'，（x<sub>j</sub>\' &ge;0， x<sub>j</sub>\'\' &ge;0）

 <img src="../../pictures/20231124150118.png" width="600"/> 

### 解的性质

1. 若线性规划问题的可行域有界，则其最优值必可在某个顶点处获得；若在两个顶点同时得到最优解，则它们连线上的任意一点都是最优解（无穷多最优解）。
2. 线性规划问题的基可行解X刚好对应于可行域的某个顶点。

<img src="../../pictures/20231124151704.png" width="300"/>  

## 线性规划问题的方法

### 单纯形表

<img src="../../pictures/运筹学-单纯形表.drawio.svg" width="600"/> 

1. 通过单位矩阵来确定初始基
2. 若非基变量的检验数全部非正（求最大的问题），则得到最优解；否则，执行下一步
3. 若存在某检验数&lambda;<sub>k</sub>大于零，则检查其对应的决策变量x<sub>k</sub>的列向量P<sub>k</sub>，若P<sub>k</sub>的所有元素均小于零，则该问题无有界最优解；否则执行下一步
4. 换基迭代：将最大非负检验数对应的决策变量作为入基变量（修改所在的行，使其和其他基向量满足单位矩阵），根据最小&theta;值确定出基变量，再执行第2步。

<img src="../../pictures/20231123134203.png" width="220"/> 

<img src="../../pictures/20231123134430.png" width="200"/> 

- 特殊情况：

1. 无穷多最优解：一般情况下，若换基迭代后，非基变量存在0通常可以（不绝对）推断无穷多最优解，而无穷多最优解必定非基变量存在0。
2. 退化解：换基迭代后的基变量不同，但最优解完全相同。
3. 对于求极小的问题，与求极大问题相反，检验数要求大于0，进基列的&theta;要求为最大的负数。

### 人工变量法

#### 大M法

- 引入人工变量x<sub>n</sub>，对应的目标函数系数为M（无穷大）。若人工变量无法出基，则原问题无可行解。

#### 两阶段法

## 线性规划对应的管理模型

### 合理利用线材

<details>
    <summary>某工厂要做100套钢架，每套用长为2.9m、2.1m、1.5m的元钢各一根。已知每根原料长7.4m，问应如何下料，能在完成任务的前提下使用的原材料最省。</summary>
    1. 考虑以下几种套裁方案：<br/>
    <table>
        <tr>
            <th rowspan="2">长度（m）</th>
            <th colspan="5">方案</th>
        </tr>
        <tr>
            <td>&#8544;</td>
            <td>&#8545;</td>
            <td>&#8546;</td>
            <td>&#8547;</td>
            <td>&#8548;</td>
        </tr>
        <tr>
            <td>2.9</td>
            <td>1</td>
            <td>2</td>
            <td></td>
            <td>1</td>
            <td></td>
        </tr>
        <tr>
            <td>2.1</td>
            <td>0</td>
            <td></td>
            <td>2</td>
            <td>2</td>
            <td>1</td>
        </tr>
        <tr>
            <td>1.5</td>
            <td>3</td>
            <td>1</td>
            <td>2</td>
            <td></td>
            <td>3</td>
        </tr>
        <tr>
            <th>合计</th>
            <td>7.4</td>
            <td>7.3</td>
            <td>7.2</td>
            <td>7.1</td>
            <td>6.6</td>
        </tr>
        <tr>
            <th>料头</th>
            <td>0</td>
            <td>0.1</td>
            <td>0.2</td>
            <td>0.3</td>
            <td>0.8</td>
        </tr>
    </table>
    2. 假定决策变量x<sub>1</sub>表示采用方案&#8544;的原料根数，x<sub>2</sub>表示采用方案&#8545;的原料根数，x<sub>3</sub>表示采用方案&#8546;的原料根数，x<sub>4</sub>表示采用方案&#8547;的原料根数，x<sub>5</sub>表示采用方案&#8548;的原料根数。<br/>
    3. 建立线性规划的目标函数和约束条件：<br/>
    <img src="../../pictures/20231124160024.png" width="430"/><br/>
    4. 化为标准型：<br/>
    <img src="../../pictures/20231124161055.png" width="650"/><br/>
    5. 单纯形表：
</details>

### 生产安排

<details>
    <summary>
        某加工厂主要生产甲、乙、丙三种产品，都需要经过A、B两道加工工序。已知A工序可以在设备A1或A2上完成，B工序可以在设备B1或B2上完成。每种产品在每个设备上的加工时间和其他数据如表：<br/>
        <table>
            <tr>
                <td rowspan="2">设备</td>
                <td colspan="3">产品</td>
                <td rowspan="2">设备有效台时</td>
            </tr>
            <tr>
                <td>甲</td>
                <td>乙</td>
                <td>丙</td>
            </tr>
            <tr>
                <td>A1</td>
                <td>4</td>
                <td>9</td>
                <td>/</td>
                <td>8000</td>
            </tr>
            <tr>
                <td>A2</td>
                <td>6</td>
                <td>8</td>
                <td>10</td>
                <td>12000</td>
            </tr>
            <tr>
                <td>B1</td>
                <td>/</td>
                <td>6</td>
                <td>2</td>
                <td>4000</td>
            </tr>
            <tr>
                <td>B2</td>
                <td>3</td>
                <td>6</td>
                <td>8</td>
                <td>7000</td>
            </tr>
            <tr>
                <td>原料费（元/件）</td>
                <td>30</td>
                <td>50</td>
                <td>35</td>
                <td></td>
            </tr>
            <tr>
                <td>售价（元/件）</td>
                <td>200</td>
                <td>400</td>
                <td>300</td>
                <td></td>
            </tr>
        </table>
    </summary>
    1. 假设决策变量x<sub>ik</sub>表示利用设备A<sub>i</sub>加工产品k的数量，y<sub>jk</sub>表示利用设备B<sub>j</sub>加工产品k的数量，w<sub>k</sub>表示产品k的总加工数量（i=1,2、j=1,2、k=1,2,3）。<br/>
    2. 列出目标函数和约束条件：<br/>
    <img src="../../pictures/20231124163121.png" width="500"/><br/>
    3. 解单纯形表
</details>

### 连续投资

<details>
    <summary>
        某人有10万元闲置资金，面临下列投资机会：<br/>
        A：为期2年的定期存款，即每年年初投资，次年年末收回本金和利息，投资回报15%；<br/>
        B：第3年初投资，到第5年年末收回本金和利息，投资回报25%，最大投资额4万元；<br/>
        C：第2年初投资，到第5年年末收回本金和利息，投资回报40%，最大投资额3万元；<br/>
        D：为期1年的定期存款，即每年年初投资，年末收回本金和利息，投资回报6%；<br/>
        问：应该如何搭配投资组合，使得五年末总资产最大化？
    </summary>
    1. 假设决策变量：x<sub>ik</sub>表示第i年初投资项目k的资金数（单位：万元）。根据A、B、C、D四个项目的投资时间表，得到如下11个决策变量：<br/>
    <table>
        <tr>
            <td>项目</td>
            <td>第1年</td>
            <td>第2年</td>
            <td>第3年</td>
            <td>第4年</td>
            <td>第5年</td>
        </tr>
        <tr>
            <td>A</td>
            <td>x<sub>1A</sub></td>
            <td>x<sub>2A</sub></td>
            <td>x<sub>3A</sub></td>
            <td>x<sub>4A</sub></td>
            <td></td>
        </tr>
        <tr>
            <td>B</td>
            <td></td>
            <td></td>
            <td>x<sub>3B</sub></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>C</td>
            <td></td>
            <td>x<sub>2C</sub></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>D</td>
            <td>x<sub>1D</sub></td>
            <td>x<sub>2D</sub></td>
            <td>x<sub>3D</sub></td>
            <td>x<sub>4D</sub></td>
            <td>x<sub>5D</sub></td>
        </tr>
    </table>
    2. 目标函数和约束条件的模型：<br/>
    <img src="../../pictures/20231124171909.png" width="500"/>
</details>

### 现金流管理

<details>
    <summary>
        某工厂的回款主要集中在每年的年中和年末。已知该厂下一年度每月的现金流(单位：万元)如表所示；其中现金流为负表示公司要支出相应的金额，现金流为正则表示公司要回收相应的款项。假设所有现金流都发生在月中。为了应付现金流的需求，该厂可能需要借助于银行借款。 有两种方式：（1）为期一年的长期借款，即于上一年年末借一年期贷款，一次得到全部贷款额，从下一年度1月起每月末偿还1%的利息，于12月底偿还本金和最后一期；（2）为期一个月的短期借款，即可以每月初获得短期贷款，于当月底偿还本金和利息，假设月利率为1.4%。当该厂有多余现金时，也可以以短期存款的方式获取部分利息收入。假设该厂只能每月初存入，月末取出，月息0.4%。<br/>
        如果将每个月的月末和下月月初看作同一时间点，已知工厂在1月初持有资金5万元，请问该厂应如何进行存贷款操作来管理现金流？<br/>
        <table>
            <tr>
                <td>月份</td>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>10</td>
                <td>11</td>
                <td>12</td>
            </tr>
            <tr>
                <td>现金流</td>
                <td>-8</td>
                <td>-7</td>
                <td>-12</td>
                <td>-4</td>
                <td>-1</td>
                <td>4</td>
                <td>10</td>
                <td>-5</td>
                <td>-4</td>
                <td>-10</td>
                <td>10</td>
                <td>45</td>
            </tr>
        </table>
    </summary>
    1. 定义决策变量：x = 为期一年的长期借款金额；y<sub>i</sub> = 第i月初的短期借款金额；s<sub>i</sub> = 第i月初的短期存款金额（i=1,...,12）<br/>
    2. 目标函数和约束条件：<br/>
    <img src="../../pictures/20231124165602.png" width="500"/>
</details>

### 排班问题

<details>
    <summary>
        某昼夜服务的公交线路每天各时间区段内所需司机和乘务人员数如表：<br/>
        <table>
            <tr>
                <td>班次</td>
                <td colspan="2">时间</td>
                <td>所需人数</td>
            </tr>
            <tr>
                <td>1</td>
                <td>06:00</td>
                <td>10:00</td>
                <td>60</td>
            </tr>
            <tr>
                <td>2</td>
                <td>10:00</td>
                <td>14:00</td>
                <td>70</td>
            </tr>
            <tr>
                <td>3</td>
                <td>14:00</td>
                <td>18:00</td>
                <td>60</td>
            </tr>
            <tr>
                <td>4</td>
                <td>18:00</td>
                <td>22:00</td>
                <td>50</td>
            </tr>
            <tr>
                <td>5</td>
                <td>22:00</td>
                <td>02:00</td>
                <td>20</td>
            </tr>
            <tr>
                <td>6</td>
                <td>02:00</td>
                <td>06:00</td>
                <td>30</td>
            </tr>
        </table>
        设司机和乘务人员分别在各时间区段一开始时上班，并连续工作8小时，问该公交线路至少需要配备多少名司机和乘务人员。
    </summary>
</details>
# 对偶理论与敏感性分析

## 对偶线性规划问题

- 对偶问题（dual problem）每个线性规划问题都有一个与之对应的另一个线性规划问题。
- 原问题和对偶问题之间的对称关系：

1. 原问题的每个约束对应对偶问题的一个决策变量
2. 原问题为求极大（极小），则对偶问题为求极小（极大）
3. 原问题的目标函数系数对应于对偶问题的约束右边项，而原问题的约束右边项对应于对偶问题的目标函数系数
4. 原问题的系数矩阵和对偶问题的系数矩阵互为转置关系（X为列向量，而Y为行向量）
5. 原问题的约束条件方向上为小于等于，则对偶问题的约束条件方向上为大于等于

<img src="../../pictures/20231124172940.png" width="350"/> 

- 原问题的约束条件个数对应对偶问题的变量个数；原问题的变量个数对应对偶问题的约束条件个数。

- 约束条件的等价变换：（1）等号对应的对偶变量自由；（2）不符合标准的约束条件对应的对偶变量需要变换符号。

<img src="../../pictures/20231124183505.png" width="500"/> 

1. 原问题的最优目标函数值和对偶问题的最优目标函数值相等（z\* = w\*）
2. 原问题的最优解恰好对应对偶问题最终单纯形表的检验系数
3. 原问题最终单纯形表的检验系数乘以（-1）恰好对应对偶问题的最优解

- 对称性：对偶问题的对偶问题即为原问题
- 弱对偶性：若$\overline{X}$是原问题的任一可行解，$\overline{Y}$是对偶问题的任一可行解，则有C$\overline{X}$&le;$\overline{Y}$b
- 最优性：若$\hat{X}$、$\hat{Y}$分别是问题（P）和（D）的一个可行解，且满足C$\hat{X}$=$\hat{Y}$b，则它们分别是问题（P）和（D）的最优解。
- 最优对偶解：若B为原问题（P）的最优基，则$\hat{Y}$=C<sub>B</sub>B<sup>-1</sup>是对偶问题（D）的最优解。
- 互补松弛性：若$\hat{X}$、$\hat{Y}$分别是如下标准形式的原问题和对偶问题的可行解，则$\hat{X}$、$\hat{Y}$是两个问题的最优解的充分必要条件是$\hat{Y}$X<sub>S</sub>=0 且 Y<sub>S</sub>$\hat{X}$ = 0

<img src="../../pictures/20231124185857.png" width="450"/> 

## 对偶单纯形法

- 单纯形法是在可行域的顶点（基可行解）上进行搜索；而对偶单纯形法是在可行域的外部（非基可行解）进行搜索，也就是对偶问题可行域的顶点（基可行解）上搜索。

## 线性规划的敏感性分析

### 约束右边项的敏感性分析

- 线性规划约束右边项（b<sub>i</sub>）发生变化（&Delta;b）时，问题的可行域发生变化，但目标函数及其等值线方向保持不变。若变化之后，存在基变量x对应的b&lt;0，则需要进行换基迭代。
- 最终单纯形表中对应的约束方程式是在最初方程式的基础上左乘B<sup>-1</sup>得到的，可以直接从最终单纯形表中读出基阵的逆矩阵。

<table>
    <tr>
        <td colspan="3">c<sub>j</sub>&rarr;</td>
        <td>80</td>
        <td>100</td>
        <td>0</td>
        <td>0</td>
        <td>0</td>
        <td>0</td>
        <td></td>
    </tr>
    <tr>
        <td>C<sub>B</sub></td>
        <td>基</td>
        <td>b</td>
        <td>x<sub>1</sub></td>
        <td>x<sub>2</sub></td>
        <td>x<sub>3</sub></td>
        <td>x<sub>4</sub></td>
        <td>x<sub>5</sub></td>
        <td>x<sub>6</sub></td>
        <td>&theta;<sub>i</sub></td>
    </tr>
    <tr>
        <td>0</td>
        <td>x<sub>5</sub></td>
        <td>72.5</td>
        <td>0</td>
        <td>0</td>
        <td>0.6875</td>
        <td>0</td>
        <td>1</td>
        <td>-2.125</td>
        <td></td>
    </tr>
    <tr>
        <td>0</td>
        <td>x<sub>4</sub></td>
        <td>5</td>
        <td>0</td>
        <td>0</td>
        <td>-0.125</td>
        <td>1</td>
        <td>0</td>
        <td>-0.75</td>
        <td></td>
    </tr>
    <tr>
        <td>100</td>
        <td>x<sub>2</sub></td>
        <td>5</td>
        <td>0</td>
        <td>1</td>
        <td>-0.125</td>
        <td>0</td>
        <td>0</td>
        <td>0.25</td>
        <td></td>
    </tr>
    <tr>
        <td>80</td>
        <td>x<sub>1</sub></td>
        <td>42.5</td>
        <td>1</td>
        <td>0</td>
        <td>0.1875</td>
        <td>0</td>
        <td>0</td>
        <td>-0.125</td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td>0</td>
        <td>0</td>
        <td>-2.5</td>
        <td>0</td>
        <td>0</td>
        <td>-15</td>
        <td></td>
    </tr>
</table>

<img src="../../pictures/20231124200128.png" width="550"/> 

- 将该基解带入原先的最终单纯形表：

<table>
    <tr>
        <td colspan="3">c<sub>j</sub>&rarr;</td>
        <td>80</td>
        <td>100</td>
        <td>0</td>
        <td>0</td>
        <td>0</td>
        <td>0</td>
        <td></td>
    </tr>
    <tr>
        <td>C<sub>B</sub></td>
        <td>基</td>
        <td>b</td>
        <td>x<sub>1</sub></td>
        <td>x<sub>2</sub></td>
        <td>x<sub>3</sub></td>
        <td>x<sub>4</sub></td>
        <td>x<sub>5</sub></td>
        <td>x<sub>6</sub></td>
        <td>&theta;<sub>i</sub></td>
    </tr>
    <tr>
        <td>0</td>
        <td>x<sub>5</sub></td>
        <td>157.5</td>
        <td>0</td>
        <td>0</td>
        <td>0.6875</td>
        <td>0</td>
        <td>1</td>
        <td>-2.125</td>
        <td></td>
    </tr>
    <tr>
        <td>0</td>
        <td>x<sub>4</sub></td>
        <td>35</td>
        <td>0</td>
        <td>0</td>
        <td>-0.125</td>
        <td>1</td>
        <td>0</td>
        <td>-0.75</td>
        <td></td>
    </tr>
    <tr>
        <td>100</td>
        <td>x<sub>2</sub></td>
        <td>-5</td>
        <td>0</td>
        <td>1</td>
        <td>[-0.125]</td>
        <td>0</td>
        <td>0</td>
        <td>0.25</td>
        <td></td>
    </tr>
    <tr>
        <td>80</td>
        <td>x<sub>1</sub></td>
        <td>47.5</td>
        <td>1</td>
        <td>0</td>
        <td>0.1875</td>
        <td>0</td>
        <td>0</td>
        <td>-0.125</td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td>0</td>
        <td>0</td>
        <td>-2.5</td>
        <td>0</td>
        <td>0</td>
        <td>-15</td>
        <td></td>
    </tr>
</table>

- 判断约束条件的右边项，x<sub>2</sub>对应的b&lt;0，显然需要继续换基迭代。

### 目标函数系数的敏感性分析

- 决策变量的目标函数发生变化只可能导致等值线的方向发生变化，并不影响到线性问题的可行域。只有各非基变量检验数均非正，最优解就不发生变化。

<table>
    <tr>
        <td colspan="3">c<sub>j</sub>&rarr;</td>
        <td>80+r<sub>1</sub></td>
        <td>100+r<sub>2</sub></td>
        <td>0</td>
        <td>0</td>
        <td>0</td>
        <td>0</td>
        <td></td>
    </tr>
    <tr>
        <td>C<sub>B</sub></td>
        <td>基</td>
        <td>b</td>
        <td>x<sub>1</sub></td>
        <td>x<sub>2</sub></td>
        <td>x<sub>3</sub></td>
        <td>x<sub>4</sub></td>
        <td>x<sub>5</sub></td>
        <td>x<sub>6</sub></td>
        <td>&theta;<sub>i</sub></td>
    </tr>
    <tr>
        <td>0</td>
        <td>x<sub>5</sub></td>
        <td>157.5</td>
        <td>0</td>
        <td>0</td>
        <td>0.6875</td>
        <td>0</td>
        <td>1</td>
        <td>-2.125</td>
        <td></td>
    </tr>
    <tr>
        <td>0</td>
        <td>x<sub>4</sub></td>
        <td>35</td>
        <td>0</td>
        <td>0</td>
        <td>-0.125</td>
        <td>1</td>
        <td>0</td>
        <td>-0.75</td>
        <td></td>
    </tr>
    <tr>
        <td>100+r<sub>2</sub></td>
        <td>x<sub>2</sub></td>
        <td>-5</td>
        <td>0</td>
        <td>1</td>
        <td>-0.125</td>
        <td>0</td>
        <td>0</td>
        <td>0.25</td>
        <td></td>
    </tr>
    <tr>
        <td>80+r<sub>1</sub></td>
        <td>x<sub>1</sub></td>
        <td>47.5</td>
        <td>1</td>
        <td>0</td>
        <td>0.1875</td>
        <td>0</td>
        <td>0</td>
        <td>-0.125</td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td>0</td>
        <td>0</td>
        <td>-2.5-0.1875r<sub>1</sub>+0.125r<sub>2</sub></td>
        <td>0</td>
        <td>0</td>
        <td>-15+0.125r<sub>1</sub>-0.25r<sub>2</sub></td>
        <td></td>
    </tr>
</table>
<img src="../../pictures/20231124204705.png" width="700"/> 

# 运输问题

## 运输问题模型

- 对于m个产地、n个销地的运输问题，包含m\*n个变量，(m+n)个约束方程，模型最多有m+n-1个独立约束方程，即系数矩阵的秩（基变量个数）&le; m+n-1

- 产销平衡的运输问题一定存在可行解x<sub>ij</sub>=a<sub>i</sub>b<sub>j</sub>/Q

## 表上作业法

### 确定基可行解

#### 最小元素法

- 从单位运价表中逐次挑选最小元素，m\*n表（产x销）对应m+n-1个基变量。将选中的元素入产销平衡表，若产&gt;销，则划去元素所在列；若产&lt;销，则划去元素所在行；若产=销，则划去元素所在的行列（出现退化）。

#### Vogel 伏格尔法

- 计算各行列最小运费和次最小运费的差额，并填入该表的最右列和最下行。从行或列差额中选出最大者，选择其所在行或列中的最小元素，执行最小元素法的划线规则。对未划去的元素再次执行以上的步骤，直到给出初始解为止。

### 判断最优解

#### 闭合回路法

- 确定基可行解后，在产销平衡表的基础上，从每一空格出发寻找闭合回路（一定存在且唯一）。水平或垂直向前划，遇到数字格（基变量）之后，可选择旋转90度后继续前行，直到形成闭合回路为止。
- 检验数：通常为求最小运费，检验数 c<sub>ij</sub> - C<sub>B</sub>B<sup>-1</sup>P<sub>ij</sub> &ge; 0，即回路中的各个数字格（运量）的变化值和运价的乘积的总和。
- 调整闭合回路：若不是最优解，则选择最小的负检验数所在的空格（入基），再次重复闭合回路法。

## 产销不平衡

### 销大于产

- 分离最低需求和最高需求，最低需求为A，最高需求-最低需求为B，二者运价和原先相同。

1. 假想产地，供应最高需求-最低需求组（B）

2. 最低需求不能由假想产地供给，运价为M（无穷大）

### 产大于销