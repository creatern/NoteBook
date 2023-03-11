# HTML

- HTML是解释型的标记语言，不区分大小写
- 浏览器是容错的
- 所有和设置页面，页面美观的标签/属性都已经被淘汰。被CSS取代。

# HTML文档结构

- HTML文档由4个主要标记组成：`<html> <head> <title> <body>`，是HTML最基本的元素。

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

</body>
</html>
```

## `<html>`

- `<html>`标记是HTML文件的开头，所有HTML文件都是以`<html>`开头和`</html>`结尾的。
- HTML页面的所有标记都要放置在`<html></html>`标记内。
- `<html>`标记没有实际的作用，但是必不可少。

## `<head>`

- `<head>`是HTML文件的头标记，放置HTML文件的信息。如CSS等可以放在`<head></head>`中。

## `<title>`

- `<title>`是标题标记，可以将网页的标题定义在`<title><title>`中。
- `<title>`标记被定义在`<head>`标记中。

## `<body>`

# HTML常用标记

## 注解

- `<!--` 注解开头
- `-->` 注解结尾

## `<meta>` 设置编码格式

- `<meta>` 
    - charset 编码集

## `<br>` 换行

- 在HTML文件中输入换行符是无用的，必须使用`<br>`标记来实现换行操作。
- `<br/>`或`<br>` br标签是一个单标签。开始和结束标签是同一个，斜杠放在单词后面

## `<p></p>` 段落

- `<p></p>` 段落标签在段前和段后各添加一个空行，而定义在段落标记中的内容不受该标记的影响。

## `<img/>` 图片

| 属性    | 描述                                         |
| :----- | :------------------------------------------- |
| src    | 图片的url                                     |
| width  | 宽度                                         |
| height | 高度                                         |
| border | 边框的宽度，默认0                              |
| alt    | 图片的提示，图片显示失败后，在图片位置显示的文字 |


## `<h></h>` 标题

- `<h></h>` 标题，共由6级标题：h1~h6，没有h7，最多h6

## `<center></center>` 居中标记

- HTML页面的默认布局方式是从左到右。
- 在`<center></center>`标记中的内容为居中显示。

## 列表

### `<ol><ol/>` 有序列表

- start 表示从显示类型的第n个开始
- type 显示的类型 A、a、I、i、1
    - A、a 26个字母
    - I、i 罗马字母
    - 1 数字，默认

### `<ul></ul>` 无序列表

- type 显示的类型 disc、square
- disc 实心圆点
    - square 实心方块
    - circle 空心圆点

### `<li></li>` 列表内的每一列

## 字体 

- 可嵌套，注意嵌套的内外层需要一致

- `<b></b>` 字体加粗
- `<i></i>` 字体倾斜
- `<u></u> `下划线

## 上下标

- `<sub></sub>` 下标
- `<sup></sup> `上标

## 符号 HTML字符实体

- `&gt;` >
- `&lt;` <
- `&ge; `>=
- `&le;` <=
- `&reg; `注册商标®
- `&copy; `版权符号©
- `&nbsp;` 空格

## `<span></span>` 独立修饰

- `<span></span>`不换行的块标记，表示要独立修饰

## `<a></a>` 超链接

- href 链接的地址
- target 窗口打开的方式
    - _self 在本窗口打开，默认
    - _blank 打开一个新的标签页
    - _parent 在父窗口打开
    - _top 在顶层窗口打开
    

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-17_02-00-52.png" width="600"/> 

## `<div></div>` 层

- `<div></div>` 

## 路径的问题

- 绝对路径 从盘符开始，从协议开始
- 相对路径 从该HTML文件所在位置开始

```html
<html>
    <head>
        <title>我的第一个网页</title>
        <meta charset="UTF-8"> 
    </head>
    <body>
        Hello World ! <br/> 你好 世界 !
        <p> 第一段落 </P>
        <P> 第二段落 </p>
        <img src="G:\\HTML\\images\\th.jpg" width="500" height="260" alt="剪短你的网线"/>
        <h1>标题一</h1>
        <h2>标题二</h2>
        <h3>标题三</h3>
        <h4>标题四</h4>
        <h5>标题五</h5>
        <h6>标题六</h6>

        武林高手排行榜
        <ol type="i" start="3">
            <li>扫地僧</li>
            <li>萧远山</li>
            <li>慕容博</li>
            <li>虚竹</li>
            <li>阿紫</li>
        </ol>

        武林大会成员名单
        <ul type="circle">
            <li>路人甲</li>
            <li>路人乙</li>
            <li>龙套丙</li>
        </ul>

        你是喜欢<b>甜</b>月饼还是<i>咸</i><b><i><u>月饼</u></i></b>

        <br/>
        
        水分子的化学式：H<sub>2</sub>O<br/>
        2的n次方 2<sup>n</sup><br/>

        5&lt;10<br/>
        5&le;10<br/>
        &reg;<br/>
        &copy;<br/>

        <span>emmm</span>

        <a href="http://www.baidu.com" target="_blank">百度</a>
    </body>
</html>
```

```html
<html>
    <head>
        <title>我的第一个网页</title>
        <meta charset="UTF-8"> 
        <style type="text/css">
            div{
                width:200px;
                height:200px;
                position:absolute;
            }

            #div1{
                background-color:#AF8;
            }
            #div2{
                background-color:#BCA;
                left:100px;
                top:100px;
            }
            #div3{
                background-color:#FF8;
                left:200px;
                top:200px;
            }
        </style>
    </head>
    <body>
        <div id="div1">div1</div>
        <div id="div2">div2</div>
        <div id="div3">div3</div>
    </body>
</html>
```

## `<table></table>` 表格 

### `<table></table>`  表格

**已淘汰的属性：**

- borde 表格边框的粗细
- width 表格的宽度
- cellspacing 单元格间隙
- cellpadding 单元格填充

###  `<caption></caption>` 标题

### `<th></th>` 表头

###  `<tr></tr>` 行

### `<td></td>` 列

- rowspan 跨行合并 （需要保证合并的下一行的对应单元格上没有数据）
- colspan 跨列合并
- 已淘汰的属性：
    - align 居中

```html
<html>
    <head>
        <title>我的网页</title>
        <meta charset="UTF-8">
        <style type="text/css"></style>
    </head>
    <body>
        <table border="1" cellspacing="0" cellpadding="4" width="600">
		    <caption>水果表单</caption>
            <tr>
                <th>名称</th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
                <th>操作</th>
            </tr>
            <tr align="center">
                <td>苹果</td>
                <td rowspan="2">5</td>
                <td>20</td>
                <td>100</td>
                <td><img src="E:\\HTML\\images\\垃圾桶.png" width="20" height="25"/></td>
            </tr>
            <tr align="center">
                <td>菠萝</td>
                <td>40</td>
                <td>200</td>
                <td><img src="E:\\HTML\\images\\垃圾桶.png" width="20" height="25"/></td>
            </tr>
            <tr align="center">
                <td>香蕉</td>
                <td>2</td>
                <td>10</td>
                <td>20</td>
                <td><img src="E:\\HTML\\images\\垃圾桶.png" width="20" height="25"/></td>
            </tr>
            <tr>
                <td>总计</td>
                <td colspan="4">320</td>
            </tr>
        </table>
    </body>
</html>
```

<img src="C:/users/zjk10/onedrive/notebook/pictures/Snipaste_2023-03-08_18-11-33.png" width="600"/>

##  `<form></form>` 表单

- 表单就是一个容器，

<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-17_20-14-04.png" width="600"/> 

- 当属性名和属性值相同是，属性值可以省略

### `<form></form>` 表单

```html
<form action = "url" method = "get|post" name = "name" onSubmit = "" target = ""></form>
```

### 通用属性

#### action  服务器接收地址

- 填写服务器端能够接收表单数据的地址。不写则仍然是提交到当前页面。

#### name 表单名称

**name 必须要指定，否则这个文本框的数据将来是不会发送给服务端的。**

- 建议在同一系列的单选框和复选框保持相同的name 
- 需要name的：与同级的标签：`<input/>``<select></select>``<textarea></textarea>`
- 以下显示的都是name具有属性的，没有name属性的不显示（不发送）
<img src="C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-17_21-08-32.png" width="400"/> 

#### value 值

**value 发送给服务器的值**

- `<textarea></textarea>`的value值是开始和结束标签之间的区域。 

#### onSubmit 指定党用户单击提交按钮时触发事件

#### method 指定数据传输到服务器的方式

**有get和post两种方式**

- **get**  默认，将输入的数据追加到action指定的地址后面，且最多2KB
- **post** 会将输入的数据按照HTTP协议中的post传输方式传送到服务器。

#### target 指定输入数据结果显示的窗口

**该属性可以设置为：**

- _blank 在新窗口中打开目标文件。
- _self 在同一个窗口打开，默认。
- _parent 在父级窗口打开，一般使用框架页时使用。
- _top 在浏览器的整个窗口中打开，忽略任何框架。

### `<input/>` 输入 

**type属性的值**

| 属性值    | 描述     |
| :------- | :------- |
| text     | 文本框   |
| password | 密码域   |
| file     | 文件域   |
| radio    | 单选按钮 |
| checkbox | 复选框   |
| submit   | 提交按钮 |
| reset    | 重置按钮 |
| button   | 普通按钮 |
| hidden   | 隐藏域   |
| image    | 图像域   |

**属性**

| 属性             | 描述                           |                      属性值                       |                                    type属性                                    |
| :-------------- | :---------------------------- | :-----------------------------------------------: | :----------------------------------------------------------------------------: |
| type            | 指定输入字段的类型              |                         -                         |                                       -                                        |
| disabled        | 指定输入字段不可用（变为灰色）。 |                 null 或 disabled                  |                                       -                                        |
| checked         | 指定输入字段是否处于被选中状态。 |                  null 或 checked                  |                               radio 和 checkbox                                |
| maxlength       | 指定指定可输入文字的个数。       |                  默认没有字符限制                  |                                text 和 password                                |
| readonly        | 指定字段是否只读。              |                 null 或 readonly                  |                                                                                |
| size            | 指定输入字段的宽度。            | text和password以文字个数为单位。其他时，以像素为单位 |                                       -                                        |
| src             | 指定图片的url。                 |                         -                         |                                     image                                      |
| width 和 height | 指定输入字段的宽和高。          |                         -                         |                                     image                                      |
| usemap          | 为图片设置热点地图              |      URI，URI格式为："#+`<map>`标记的name值"       |                                     image                                      |
| alt             | 指定当图片无法显示时显示的文字   |                         -                         |                                     image                                      |
| name            | 输入指定字段的名称              |                         -                         |                                       -                                        |
| value           | 指定输入字段默认的数据值         |     checkbox和radio不可忽略该属性；其他可忽略。     | button、reset和submit指定的是按钮上的文字；checkbox和radio指定的是数据项选定时的值 |


**示例：**

- `<input type="text"/>` 文本框
- `<input type="password">` 密码框
- `<input type="radio">` 单选框
     - name的属性值需要保持一致，这样才会有互斥的效果
     - 可以通过checked属性设置默认选中的项
- `<input type="checkbox">` 复选框
     - name的属性值建议保持一致，以便将来获取数组
     - 可以通过checked属性设置默认选中的项
- `<input type="submit">` 提交
     - 通过form标签的action属性
- `<input type="reset">` 重置
    - reset 重置 恢复到默认状态，不等于清空
- `<input type="button">` 普通按钮 
    - 需要通过JavaScript绑定单击响应函数
- `<input type="hidden" name="userId" value="2233"/>` 表单隐藏域
    - 通过表单隐藏域设置的表单项不会显示到页面上，用户看不到。但是提交表单时会一起被提交。用来设置一些需要和表单一起提交但是不希望用户看到的数据

### `<select></select>` 下拉选项

- `<select></select>`  
   - selected 默认选中 
- `<option></option>` 选项

### `<textarea></textarea>` 多行文本框/文本域 

- `<textarea></textarea>` 
    - rows 默认显示n行
    - cols 默认显示n列
- 其value值就是开始`<textarea>`和结束`</textarea>`标签之间的内容

**属性**

| 属性      | 描述                               |
| :------- | :-------------------------------- |
| name     | 用于指定多行文本的名称              |
| cols     | 用于指定多行文本框显示的列数（宽度） |
| rows     | 用于指定多行文本框显示的行数（高度） |
| disabled | 用于指定当前多行文本框不可用（灰色） |
| readonly | 用于指定当前多行文本框为只读         |
| wrap     | 用于设置多行文本框的文字是否自动换行 |

**wrap属性的可选值**

| 可选值 | 描述                                                                                      |
| :----- | :--------------------------------------------------------------------------------------- |
| hard   | 默认，表示自动换行，如果文字超过cols属性所指的列数就自动换行，并且提交到服务器时换行符自动被提交 |
| soft   | 表示自动换行，如果文字超过cols属性所指的列数就自动换行，但提交到服务器时换行符不被提交          |
| off    | 表示不自动换行，如果想让文字换行，只能按下Enter回车强制换行                                   |

```html
<html>
    <head>
        <title>我的网页</title>
        <meta charset="UTF-8">
        <style type="text/css"></style>
    </head>
    <body>
        <form action="demo04.html">
            昵称：<input type="text" name="nicName"/><br/><br/>
            密码：<input type="password" name="pwd"/><br><br/>
            性别：<input type="radio" name="gender" value="male"/> 男
                  <input type="radio" name="gender" value="female" checked/> 女<br/><br/>
            爱好：<input type="checkbox" name="hobby" value="basketball"/> 篮球
                  <input type="checkbox" name="hobby" value="football"/> 足球
                  <input type="checkbox" name="hobby" value="ping-pong"/> 乒乓球 <br/><br/>
            星座：<select name="start">
                    <option value="1">据星</option>
                    <option value="2" selected>明星</option>
                    <option value="3">后星</option>
                  </select><br/><br/>
            备注：<textarea name="remark" rows="4" cols="50"></textarea><br/><br/>
            <input type="submit" value="注册"/>
            <input type="reset" value="重置"/>
            <input type="button" value="普通按钮"/>
        </form>
    </body>
</html>
```

# HTML5 新增

## `<section></section>` 

- `<section></section>`元素表示页面中的一个区域。例如页眉、章节、页脚等部分。可以和h1、h2等元素结合使用，标示文档结构。

## `<acticle></acticle>`

- `<acticle></acticle>`表示页面中一块与上下文不相关的独立内容。例如博客中的一篇文章、评论等。
- 该元素通常有自己的标题、脚注等内容

## `<header></header>` 

- 表示页面中一个内容区域或整个页面的标题。

## `<footer></footer>`

- 表示整个页面或页面中一个内容区域的脚注。

## `<aside></aside>`

- 表示当前页面或文字的附属信息部分。

# frame和iframe

## `<frameset></frameset>` 框架 

- 已淘汰的标签
- 替换了body标签，即：使用frame时，不能有body标签

```html
<html>
    <head>
        
    </head>
    <frameset rows="20%,*">  <!--frameborder="no"-->
        <frame src="demo05/top.html"/>
        <frameset cols="15%,*">
            <frame src="demo05/left.html"/>
            <frameset rows="80%,*">
                <frame src="demo05/main.html"/>
                <frame src="demo05/botton.html"/>
            </frameset>
        </frameset>
    </frameset>
</html>
```

## 嵌入子页面 `<iframe/>`

```html
<html>
    <head>
        <meta charset="UTF-8">
    </head>
    <body>
        demo06页面内容
        <iframe src="demo05/top.html"/>
    </body>
</html>
```