## CSS代码语法

- CSS样式由选择器和声明组成
- 声明必须放在`{}`内，并且声明可以是多条。
- 每条声明由一对属性和值组成，属性和值之间用冒号`:`分开，每条声明以分号`;`结尾
- 使用`/* */`声明注释。

**选择器**

1. 标签样式表 标签
2. 类样式表 class属性 `.类名`
3. ID样式表 id属性 `#ID名`

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-18_15-59-57.png =400x)

- 组合样式表

## CSS从位置上的分类

1. 嵌入式样式表(行内样式表)  
     - `style`作为标签内属性
     - `<p><span style="font-size:60px;font-weight:bolder;color:yellow";>HELLO</span></p>`
2. 内部样式表   
     - `<style></style>`作为`<head></head>`内部标签区域
3. **外部样式表** 重点
- 就近原则：如果有相同的样式设置，采用就近的

```html
<html>
	<head>
		<meta charset="UTF-8">
		<!--内部样式表-->
		<style type="text/css">
			/* 被style标签包围的是CSS环境 在这里编写CSS代码 */

			/* 标签样式 */
			p{
				color:red;
			}

			/* . class 类样式 */
			.f20{
				font-size:20px;
			}

			/* # id ID样式 */
			/* id属性在整个html中尽量唯一，虽然重复不报错*/
			#p4{
				background-color:pink;
				font-size:24px;
				font-weight:bolder;
				font-style:italic;
				font-family:"华文彩云";
			}

			/* 组合样式*/
			div p{
				color:blue;
			}

			div .f32{
				font-size:32px;
				font-family:"黑体";
			}
		</style>
	</head>
	<body>
		<p>段落1</p>
		<p>段落2</p>
		<p class="f20">段落3</p>
		<p id="p4">段落4</p>

		<div>
			<!--嵌入式样式表-->
			<p><span style="font-size:60px;font-weight:bolder;color:yellow";>HELLO</span></p>
			<span class="f32">WORLD </span>
			<p class="f32">!!!</p>
		</div>
	</body>
</html>
```

## CSS文件 外部样式表 

- 在CSS文件中不需要`<style>`标签，直接编写样式即可
     
```css
/* # id ID样式 */
/* id属性在整个html中尽量唯一，虽然重复不报错*/
#p4{
	background-color:pink;
	font-size:24px;
	font-weight:bolder;
	font-style:italic;
	font-family:"华文彩云";
}

/* 组合样式*/
div p{
	color:blue;
}

div .f32{
	font-size:32px;
	font-family:"黑体";
}
```

### 1. 链接外部表
     
-  在`<head></head>`标签区域内部: `<link/>`
    - `<link rel="stylesheet" href="CSS文件路径" type="text/css"/>`
        - `rel="stylesheet"` 在这个页面中使用这个外部样式表
        - `href` CSS文件路径 
        - `type="text/css"` 指定为样式表文件 (可省略)
  
**html文件**

```html
<html>
	<head>
		<meta charset="UTF-8">
		<style type="text/css">
			/* 被style标签包围的是CSS环境 在这里编写CSS代码 */

			/* 标签样式 */
			p{
				color:red;
			}

			/* . class 类样式 */
			.f20{
				font-size:20px;
			}
		</style>
		<link rel="stylesheet" href="CSS/demo07.css"/>
	</head>
	<body>
		<p>段落1</p>
		<p>段落2</p>
		<p class="f20">段落3</p>
		<p id="p4">段落4</p>

		<div>
			<p><span>HELLO</span></p>
			<span class="f32">WORLD </span>
			<p class="f32">!!!</p>
		</div>
	</body>
</html>
```

**CSS文件**

```css
/* # id ID样式 */
/* id属性在整个html中尽量唯一，虽然重复不报错*/
#p4{
	background-color:pink;
	font-size:24px;
	font-weight:bolder;
	font-style:italic;
	font-family:"华文彩云";
}

/* 组合样式*/
div p{
	color:blue;
}

div .f32{
	font-size:32px;
	font-family:"黑体";
}
```

### 2. 导入外部表

- `@import url("CSS文件路径")`
- 必须是在`<head></head>`内的`<style></style>`中导入

```html
<html>
    <head>
        <style>
            @import url("G:/html/css/demo07.css")
        </style>
    </head>
    <body></body>
</html>
```

### 对比链接和导入
  
1. `<link/>`标签属于XHTML范筹，而`@import` 是CSS2.1中独有的。
2. 使用`<link/>`标签链接，在客户端浏览网页时，先加载CSS文件，再加载HTML文件。
3. 使用`@import`导入，在客户端浏览网页时，先加载HTML文件，再加载CSS文件，当网速慢时，可能会先展示没有经过CSS文件渲染的原始HTML页面。

## CSS盒子模型

- border 边框
- margin 间隙
- padding 填充

```html
<html>
	<head>
		<meta charset="utf-8">
		<style type="text/css">
			#div1{
				width:400px;
				height:400px;
				background-color:greenyellow;

				/* border 边框样式*/
				
				/*border-width:4px;*/    /*边框粗细*/
				/*border-style:solid;*/  /*边框样式 solid-实线 dotted-点状线*/
				/*border-color:blue;*/   /*边框颜色*/

				border:4px double blue;

				/*border-top:4px dotted blue;*/
			}
			#div2{
				width:200px;
				height:200px;
				background-color:red;

				
				margin-top:100px;
				margin-left:100px;
				
				/*margin:100px 100px 50px 150px;*/ 
				/*提供值的个数 1: 四个方向统一 2:(上下)(左右) 3:上(左右)下 4:上右下左*/

				/*padding 填充*/
				/*padding-top:50px;
				padding-left:50px;*/
			}
			#div3{
				width:100px;
				height:100px;
				background-color:orange;
				margin:50px; /*以div1为参照物*/
			}
			body{
				margin:0px;
				padding:0px;
			}
		</style>
	</head>
	<body>
		<div id="div1">
			<div id="div2">
				<div id="div3">&nbsp;</div>
			</div>
		</div>
	</body>
</html>
```

## CSS布局

- position 定位
   - absolute 绝对定位，需要配合使用left、top
   - relative 相对定位，一般会和float、margin、padding ... 一起使用


```html
<html>
	<head>
		<meta charset="utf-8">
		<style>
			#div1{
				width:200px;
				height:50px;
				background-color:greenyellow;

				/*绝对定位*/
				position:absolute;
				left:100px;
				top:100px;
			}

			#div2{
				width:200px;
				height:50px;
				background-color:yellow;
				
				/*相对定位*/
				position:relative;
				float:left;
				margin-left:20px;
			}
			#div3{
				height:50px;
				background-color:pink;
			}
			#div4{
				width:200px;
				height:50px;
				background-color:blue;
				float:left;
			}
			#div5{
				width:200px;
				height:50px;
				background-color:black;
				float:left;
			}
			div{
				position:relative;
			}
		</style>
	</head>
	<body>
		<div id="div1">&nbsp;</div>
		<div id="div2">&nbsp;</div>
		<div id="div3">
			<div id="div4">&nbsp;</div>
			<div id="div5">&nbsp;</div>
		</div>
	</body>
</html>
```


## HTML中的使用

### HTML文档内

- JavaScript代码要写在script标签内`<script type="text/javascript"></script>`
- script标签可以写在文档内的任意位置
- 为了能够方便查询或操作HTML标签（元素）script标签可以写在body标签后面

```html
<html>
    <head>
        <script type="text/javascript">
            <!--
               javascript语句;        
            -->
        </script>
    </head>
    <body></body>
</html>
```

### 使用外部JS文件

- 在script标签内通过src属性指定外部xxx.js文件的路径即可。
**注意**

1. 引用外部JavaScript文件的script标签里面不能写JavaScript代码
2. 引用外部JavaScript文件的script标签不能改成单标签
3. 外部JavaScript文件一定要先引入再使用

```html
<html>
    <head>
        <script type="text/javascript" src="JS文件路径">
            <!--
               javascript语句;        
            -->
        </script>
    </head>
    <body></body>
</html>
```

```html
<body>
</body>

<!-- 引入外部JS文件 -->
<script src="/pro02-JavaScript/scripts/outter.js" type="text/javascript" charset="utf-8"></script>
<!-- HTML内部的JS -->
<script type="text/javascript">
	// 调用外部JavaScript文件中声明的方法
	showMessage();
</script>
```

## 数据类型和变量

### 数据类型

**基本数据类型**

- number 数值型：JavaScript不区分整数、小数
- string 字符串：JavaScript不区分字符、字符串；单引号、双引号意思一样。
- boolean 布尔型：true、false
    - 在JavaScript中，其他类型和布尔类型的自动转换。
    - true：非零的数值，非空字符串，非空对象
    - false：零，空字符串，null，undefined
- null 空
- undefined 未定义

**引用类型**

- 所有new出来的对象
- 用[]声明的数组
- 用{}声明的对象

### 变量

- 关键字：var
- 数据类型：JavaScript变量可以接收任意类型的数据
- 标识符：严格区分大小写
- 可以不声明变量类型，但使用时必须声明（即：被赋值）

**变量使用规则**

- 如果使用了一个没有声明的变量，那么会在运行时报错 Uncaught ReferenceError: b is not defined
- 如果声明一个变量没有初始化，那么这个变量的值就是undefined

```html
<html>
	<head>
		<meta charset="utf-8">
		<script language="javascript">
			var str = "hello Javascript";
			alert(typeof str);
			str = 9999;
			alert(typeof str);
		</script>
	</head>
	<body></body>
</html>
```

## 函数

- 函数本身就是一个对象

### 系统函数

- parseInt()
- parseFloat()
- isNaN() 判断非数字
- eval() 计算表达式的值

### 自定义函数

#### 声明函数

```javascript
function 函数名(参数1, 参数2) {
    ...语句;
	return 返回值;
}
```

#### 调用函数

1. 直接调用

```javascript
var 变量 = 函数名(参数1, 参数2);
```

2. 事件调用

```javascript
事件名 = "函数名()"
```

3. 直接使用

```javascript
函数名(参数);
```

#### 匿名函数

- 声明一个函数，相当于创建了一个函数对象，将这个对象的引用赋值给变量。

```javascript
var 变量 = function(参数1, 参数2) {
    ...语句;
	return 返回值;
};
```

## 对象

- JavaScript中没有『类』的概念，对于系统内置的对象可以直接创建使用。

### 创建对象

#### 使用new关键字创建对象

- 也可以省略new关键字

```javascript
var 实例 = new 对象名(参数);
```

```javascript
//创建对象
var obj = new Object();
//控制台输出
console.log(obj);
```

#### 使用{}创建对象

- 在创建对象的同时，设置对象属性

```javascript
var 对象名{
    "对象属性1":"值1",
    ...
    "对象属性n":"值n" //最后一个不要逗号 ,
};
```

```javascript
// 创建对象
var obj02 = {
    "soldierName":"john",
    "soldierAge":35,
    "soldierWeapon":"gun"
};

console.log(obj02);
```

### 设置对象属性

**普通属性**

```javascript
对象.属性 = "值";
```

**函数属性**

- 可以通过对象的函数属性调用函数 ：`对象名.函数属性(参数)`

```javascript
对象.函数属性 = 函数名 | 匿名函数 ;
```

1. new关键字创建

```javascript
obj = new Object();

obj.name = "Tom";
obj.age = "12";
obj.sex = "男";
obj.show = function () {
    console.log(this.sex);
};

//控制台输出对象
console.log(obj);
//调用对象的函数属性
obj.show();
```

2. {}创建

```javascript
obj = {
    "name": "Tom",
    "age": 12,
    "sex": "男",
    "show": function () {
        console.log(this.sex);
    }
}

//控制台输出
console.log(obj);
//调用obj对象的函数属性
obj.show();
```

### this关键字

**this关键字只有两种情况：**

- 在函数外面：this关键字指向window对象（代表当前浏览器窗口）
- 在函数里面：this关键字指向调用函数的对象

### 系统对象

| 对象    | 说明         |
| :----- | :---------- |
| Date   | 日期时间处理 |
| Array  | 数组处理     |
| String | 字符串处理   |
| Math   | 数学处理     |

#### Date对象

**创建**

1. 指定日期时间

```javascript
var 日期实例 = new Date("MM DD,YYYY,hh:mm:ss);
```

2. 当前日期时间

```javascript
var 日期实例 = new Date();
```

**常用方法**

| 方法                | 说明                                              |
| :----------------- | :------------------------------------------------ |
| getDate()          | 	返回月中的第几天（从 1 到 31）。                   |
| getDay()           | 	返回星期几（0-6）。                               |
| getFullYear()      | 	返回年份。                                        |
| getHours()	     | 返回小时（从 0-23）。                              |
| getMilliseconds()	 | 返回毫秒（0-999）。                                |
| getMinutes()	     | 返回分钟（从 0-59）。                              |
| getMonth()	     | 返回月份（从 0-11）。                              |
| getSeconds()	     | 返回秒数（从 0-59）。                              |
| getTime()	         | 返回自 1970 年 1 月 1 日午夜以来与指定日期的毫秒数。 |

```javascript
//当前日期时间
var toDay = new Date();
console.log(toDay); //2022-11-22T11:01:18.711Z
//指定日期时间
var myDay = new Date("12 31,2001,12:12:12");
console.log(myDay);

//getDate()
console.log(myDay.getDate()); //2001-12-31T04:12:12.000Z
//getDay()
console.log(myDay.getDay()); //31
//getFullYear()
console.log(myDay.getFullYear()); //1
//getHours()
console.log(myDay.getHours()); //2001
//getMilliseconds()
console.log(myDay.getMilliseconds()); //0
//getMonth()
console.log(myDay.getMonth()); //11
//getSeconds()
console.log(myDay.getSeconds()); //12
//getTime()
console.log(myDay.getTime()); //1009771932000
```

##### 定时函数

| 函数                                    | 说明                         |
| :------------------------------------- | :--------------------------- |
| setTimeout("要执行的语句",时间)          | 在指定毫秒时间后执行          |
| clearTimeout(timeoutID)                | 清除定时设置                  |
| setInterval("要执行的语句",周期间隔时间) | 按照指定的周期（毫秒时间）执行 |
| clearInterval()                        | 清除定时设置                  |

```javascript
function myShowTime(){  
    console.log(new Date());
}

function myRun(){
    console.log("等待3秒");    
    setTimeout(3000);
    myShowTime();
    console.log("结束");
}

var counter = 0;

function myCycle(){
    setInterval(1000);
    console.log("开始" + ++counter + "次");
    myShowTime();
}

myRun();
myCycle();
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavaScript</title>
</head>
<body>
    <p>当前时间为：<span id="clock"></span></p>
    <button onclick="stopClock(this);">停止</button><hr>
    <button onclick="delayedAlert(this);">2秒后弹出一个提示框</button>
    <button onclick="clearAlert();">取消</button>
    <script type="text/javascript">
        var intervalID;
        function showTime() {
            var d = new Date();
            document.getElementById("clock").innerHTML = d.toLocaleTimeString();
        }

        function stopClock(e) {
            clearInterval(intervalID);
            e.setAttribute('disabled', true)
        }
        intervalID = setInterval(showTime, 1000);

        var timeoutID;
        var that;
        function delayedAlert(e) {
            that = e;
            timeoutID = setTimeout(showAlert, 2000);
            e.setAttribute('disabled', true)
        }
        function showAlert() {
            alert('这是一个提示框。');
            that.removeAttribute('disabled');
        }
        function clearAlert() {
            clearTimeout(timeoutID);
            that.removeAttribute('disabled');
        }
    </script>
</body>
</html>
```

## 数组

### 创建数组

#### new关键字 Array对象

```javascript
var 数组名 = new Array();
```

```javascript
// 1.创建数组对象
var arr01 = new Array();
```

#### []创建

```javascript
var 数组名 = [值1,值2,...];
```

```javascript
var arr = ["cat","dog","tiger"];
console.log(arr);
```

### 数组方法

| 方法            | 说明                                          |
| :-------------- | :-------------------------------------------- |
| push(value)    | 向数组中压入value值                            |
| reverse()       | 数组元素反序                                   |
| join("分隔符")  | 数组元素拼接成字符串，每个元素之间以分隔符连接    |
| split("分隔符") | 以分隔符将字符串拆分为数组                      |
| pop()           | （删除）弹出数组中的最后一个元素，同时返回该元素  |

```javascript
arr01 = new Array();

// 压入数据
arr01.push("apple");
arr01.push("orange");
arr01.push("banana");
arr01.push("grape");

// for循环 遍历数组
for (var i = 0; i < arr01.length; i++) {
    console.log(arr01[i]);
}

// 数组元素反序
arr01.reverse();
for (var i = 0; i < arr01.length; i++) {
    console.log(arr01[i]);
}

// 数组元素拼接成字符串
var arrStr = arr01.join(",");
console.log(arrStr);

// 字符串拆分成数组
var arr02 = arrStr.split(",");
for (var i = 0; i < arr02.length; i++) {
    console.log(arr02[i]);
}

// 弹出数组中最后一个元素
var ele = arr01.pop();
console.log(ele);
console.log(arr01);
```

## BOM 浏览器对象模型

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-22_14-58-35.png =600x)

**主要包含的对象**

| 对象      | 说明     |
| :------- | :------- |
| window   | 整个窗口 |
| history  | 历史页面 |
| location | 地址栏   |
| document | 网页内容 |

### window对象

**属性**

| 属性           | 说明                                                 |
| :------------ | :-------------------------------------------------- |
| closed        | 返回窗口是否已被关闭。                                 |
| defaultStatus | 设置或返回窗口状态栏中的默认文本。                      |
| document      | 对 Document 对象的只读引用。请参阅 Document 对象。     |
| history       | 对 History 对象的只读引用。请参数 History 对象。       |
| innerheight   | 返回窗口的文档显示区的高度。                           |
| innerwidth    | 返回窗口的文档显示区的宽度。                           |
| length        | 设置或返回窗口中的框架数量。                           |
| location      | 用于窗口或框架的 Location 对象。请参阅 Location 对象。  |
| name          | 设置或返回窗口的名称。                                 |
| Navigator     | 对 Navigator 对象的只读引用。请参数 Navigator 对象。   |
| opener        | 返回对创建此窗口的窗口的引用。                         |
| outerheight   | 返回窗口的外部高度。                                  |
| outerwidth    | 返回窗口的外部宽度。                                  |
| pageXOffset   | 设置或返回当前页面相对于窗口显示区左上角的 X 位置。      |
| pageYOffset   | 设置或返回当前页面相对于窗口显示区左上角的 Y 位置。      |
| parent        | 返回父窗口。                                          |
| Screen        | 对 Screen 对象的只读引用。请参数 Screen 对象。         |
| self          | 返回对当前窗口的引用。等价于 Window 属性。              |
| status        | 设置窗口状态栏的文本。                                 |
| top           | 返回最顶层的先辈窗口。                                 |
| window        | window 属性等价于 self 属性，它包含了对窗口自身的引用。 |

- screenXxx只读整数。声明了窗口的左上角在屏幕上的的 x 坐标和 y 坐标
    - IE、Safari 和 Opera 支持 screenLeft 和 screenTop
    - 而 Firefox 和 Safari 支持 screenX 和 screenY

**方法**

| 方法    | 说明    |
| :-- | :-- |
|alert() |显示带有一段消息和一个确认按钮的警告框。|
|blur() | 把键盘焦点从顶层窗口移开。|
|clearInterval() |取消由 setInterval() 设置的 timeout。|
|clearTimeout()  |取消由 setTimeout() 方法设置的 timeout。|
|close() |关闭浏览器窗口。|
|confirm()   |显示带有一段消息以及确认按钮和取消按钮的对话框。|
|createPopup()  | 创建一个 pop-up 窗口。|
|focus()| 把键盘焦点给予一个窗口。|
|moveBy()    |可相对窗口的当前坐标把它移动指定的像素。|
|moveTo()   | 把窗口的左上角移动到一个指定的坐标。|
|open() | 打开一个新的浏览器窗口或查找一个已命名的窗口。|
|print() |打印当前窗口的内容。|
|prompt() |  显示可提示用户输入的对话框。|
|resizeBy() | 按照指定的像素调整窗口的大小。|
|resizeTo() | 把窗口的大小调整到指定的宽度和高度。|
|scrollBy() | 按照指定的像素值来滚动内容。|
|scrollTo() | 把内容滚动到指定的坐标。|
|setInterval()  | 按照指定的周期（以毫秒计）来调用函数或计算表达式。|
|setTimeout() |   在指定的毫秒数后调用函数或计算表达式。|

#### 事件

| 事件         | 说明                     |
| :---------- | :---------------------- |
| onload      | 一个页面/一副图像完成加载 |
| onmouseover | 鼠标指针移到某元素上      |
| onclick     | 鼠标点击                 |
| onmouseout  | 鼠标移开                 |
| onkeydown   | 某个键盘按键             |
| onchange    | 域的内容改变             |

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-22_21-34-58.png =900x)

**例1**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!--HTML内部-->
<button id="test" onclick="myShow()"> 按钮2</button>
<!--外部JS文件-->
<button id="testId" onclick="changeContent()"> 按钮1</button>
<script type="text/javascript" charset="UTF-8" src="EventTest01.js"></script>
<script type="text/javascript">
    function myShow(){
        alert("按钮2");
    }
</script>
</body>

</html>
```

```javascript
function changeContent() {
    window.alert("按钮1");
}

alert("成功导入");
```

**例2**

```html
<html>
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" href="css/demo05.css">
		<script type="text/javascript" src="js/demo08.js"></script>
	</head>
	<body>
		<div id="div_container">
			<div id="div_fruit_list">
				<table id="tbl_fruit">
					<tr>
						<th class="w20">名称</th>
						<th class="w20">单价</th>
						<th class="w20">数量</th>
						<th class="w20">小计</th>
						<th>操作</th>
					</tr>
					<tr>
						<td>苹果</td>
						<td>5</td>
						<td>20</td>
						<td>100</td>
						<td><img src="imgs/del.jpg" class="delImg"/></td>
					</tr>
					<tr>	
						<td>西瓜</td>
						<td>3</td>
						<td>20</td>
						<td>60</td>
						<td><img src="imgs/del.jpg" class="delImg"/></td>
					</tr>
					<tr>
						<td>菠萝</td>
						<td>4</td>
						<td>25</td>
						<td>100</td>
						<td><img src="imgs/del.jpg" class="delImg"/></td>
					</tr>
					<tr>
						<td>榴莲</td>
						<td>3</td>
						<td>30</td>
						<td>90</td>
						<td><img src="imgs/del.jpg" class="delImg"/></td>
					</tr>
					<tr>
						<td>总计</td>
						<td colspan="4">999</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>
```

```javascript
window.onload=function(){
	updateZJ();
	//当页面加载完成，我们需要绑定各种事件
	//根据id获取到表格
	var fruitTbl =  document.getElementById("tbl_fruit");
	//获取表格中的所有的行
	var rows = fruitTbl.rows ;
	for(var i = 1 ; i<rows.length-1 ; i++){
		var tr = rows[i];
		//1.绑定鼠标悬浮以及离开时设置背景颜色事件
		tr.onmouseover=showBGColor;
		tr.onmouseout=clearBGColor;
		//获取tr这一行的所有单元格
		var cells = tr.cells;
		var priceTD = cells[1];
		//2.绑定鼠标悬浮在单价单元格变手势的事件
		priceTD.onmouseover = showHand ;
		//3.绑定鼠标点击单价单元格的事件
		priceTD.onclick=editPrice;
	}

}

//当鼠标点击单价单元格时进行价格编辑
function editPrice(){
	if(event && event.srcElement && event.srcElement.tagName=="TD"){
		var priceTD = event.srcElement ;
		//目的是判断当前priceTD有子节点，而且第一个子节点是文本节点 ， TextNode对应的是3  ElementNode对应的是1
		if(priceTD.firstChild && priceTD.firstChild.nodeType==3){
			//innerText 表示设置或者获取当前节点的内部文本
			var oldPrice = priceTD.innerText ;
			//innerHTML 表示设置当前节点的内部HTML
			priceTD.innerHTML="<input type='text' size='4'/>";
			// <td><input type='text' size='4'/></td>
			var input = priceTD.firstChild;
			if(input.tagName=="INPUT"){
				input.value = oldPrice ;
				//选中输入框内部的文本
				input.select();
				//4.绑定输入框失去焦点事件 , 失去焦点，更新单价
				input.onblur=updatePrice ;
			}
		}
		
	}
}

function updatePrice(){
	if(event && event.srcElement && event.srcElement.tagName=="INPUT"){
		var input = event.srcElement ;
		var newPrice = input.value ;
		//input节点的父节点是td
		var priceTD = input.parentElement ;
		priceTD.innerText = newPrice ;

		//更新当前行的小计这一个格子的值
		//priceTD.parentElement td的父元素是tr
		updateXJ(priceTD.parentElement);

	}
}

//更新指定行的小计
function updateXJ(tr){
	if(tr && tr.tagName=="TR"){
		var tds = tr.cells;
		var price = tds[1].innerText ;
		var count = tds[2].innerText ;
		//innerText获取到的值的类型是字符串类型，因此需要类型转换，才能进行数学运算
		var xj = parseInt(price) * parseInt(count);
		tds[3].innerText = xj ;

		//更新总计
		updateZJ();
	}

}

//更新总计
function updateZJ(){
	var fruitTbl = document.getElementById("tbl_fruit");
	var rows = fruitTbl.rows ;
	var sum = 0 ;
	for(var i = 1; i<rows.length-1 ; i++){
		var tr = rows[i];
		var xj = parseInt(tr.cells[3].innerText);		//NaN    not a number 不是一个数字
		sum = sum + xj ;
	}
	rows[rows.length-1].cells[1].innerText = sum ;
}




//当鼠标悬浮时，显示背景颜色
function showBGColor(){
	//event : 当前发生的事件
	//event.srcElement : 事件源
	//alert(event.srcElement);
	//alert(event.srcElement.tagName);	--> TD
	if(event && event.srcElement && event.srcElement.tagName=="TD"){
		var td = event.srcElement ;
		//td.parentElement 表示获取td的父元素 -> TR
		var tr = td.parentElement ;
		//如果想要通过js代码设置某节点的样式，则需要加上 .style
		tr.style.backgroundColor = "navy" ;

		//tr.cells表示获取这个tr中的所有的单元格
		var tds = tr.cells;
		for(var i = 0 ; i<tds.length ; i++){
			tds[i].style.color="white";
		}
	}
}

//当鼠标离开时，恢复原始样式
function clearBGColor(){
	if(event && event.srcElement && event.srcElement.tagName=="TD"){
		var td = event.srcElement ;
		var tr = td.parentElement ;
		tr.style.backgroundColor="transparent";
		var tds = tr.cells;
		for(var i = 0 ; i<tds.length ; i++){
			tds[i].style.color="threeddarkshadow";
		}
	}
}

//当鼠标悬浮在单价单元格时，显示手势
function showHand(){
	if(event && event.srcElement && event.srcElement.tagName=="TD"){
		var td = event.srcElement ;
		//cursor : 光标
		td.style.cursor="hand";
	}

}
```

### hitory对象

**方法**

| 属性       | 说明                              |
| :-------- | :-------------------------------- |
| back()    | 加载 history 列表中的前一个 URL。   |
| forward() | 加载 history 列表中的下一个 URL。   |
| go()      | 加载 history 列表中的某个具体页面。 |

### location对象

**属性**

| 属性      | 说明                                       |
| :------- | :----------------------------------------- |
| hash     | 设置或返回从井号 (#) 开始的 URL（锚）。       |
| host     | 设置或返回主机名和当前 URL 的端口号。         |
| hostname | 设置或返回当前 URL 的主机名。                |
| href     | 设置或返回完整的 URL。                       |
| pathname | 设置或返回当前 URL 的路径部分。              |
| port     | 设置或返回当前 URL 的端口号。                |
| protocol | 设置或返回当前 URL 的协议。                  |
| search   | 设置或返回从问号 (?) 开始的 URL（查询部分）。 |

**方法**

| 方法       | 说明                    |
| :-------- | :--------------------- |
| assign()  | 加载新的文档。          |
| reload()  | 重新加载当前文档。       |
| replace() | 用新的文档替换当前文档。 |

### document对象

**属性**

|    属性     | 说明                     |
| :------- | :------------------- |
| referrer | 返回载入当前文档的URL |
| URL      | 返回当前文档的URL     |

**方法**

| 方法                   | 说明                                   |
| :--------------------- | :------------------------------------- |
| getElementById()       | 返回对拥有指定id的第一个对象的引用        |
| getElementsByName()    | 返回带有指定名称的对象的集合              |
| getElementsByTagName() | 返回带有指定标签名的对象的集合            |
| write()                | 向文档写文本、HTML表达式、JavaScript代码 |

#### DOM 文档对象模型

**组成**

- Core DOM 核心DOM编程，定义了一套标准的针对任何结构化文档的对象，包括HTML、XHTML、XML
- XML DOM 定义了一套标准的针对XML文档的对象
- HTML DOM 定义了一套标准的针对HTML文档的对象

**文档节点树**

- 数据结构：整个HTML文档中标签、文本、属性、注释等等节点对象组成的一个树形结构

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-22_21-21-35.png =300x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-22_21-14-08.png =300x)

##### Core DOM操作节点

**访问节点**

|  方法   | 说明    |
| :-- | :-- |
|  getElementById()   |   返回对拥有指定id的第一个对象的引用  |
| getElementByName()    |   返回带有指定名称的对象的集合  |
|  getElementByTagName()   |  返回带有指定标签名的对象的集合   |

| 属性        | 说明                                                            |
| :--------- | :------------------------------------------------------------- |
| parentNode | 该节点的父节点                                                  |
| firstChild | 首个子节点，（如果某有，文本和属性节点返回空数组，元素节点返回null） |
| lasstChild | 最后一个字节点                                                   |

**操作节点属性值**

| 方法                   | 说明       |
| :--------------------- | :-------- |
| getAttribute("属性名") | 获取属性值 |
| setAttribute("属性名") | 设置属性值 |

**创建和增加属性**

| 方法                           | 说明                                                           |
| :---------------------------- | :------------------------------------------------------------- |
| createElement(tagName)        | 按照给定的标签名创建一个新的元素节点                              |
| appendChild(nodeName)         | 向已存在节点列表的末尾添加新的节点                                |
| insertBefore(newNode,oldNode) | 向指定的节点之前插入一个新的节点                                  |
| cloneNode(boolean)            | 复制某个指定的节点（true复制该节点及其字节点，false只复制当前节点） |

**删除和替换节点**

| 方法                           | 说明                     |
| :---------------------------- | :----------------------- |
| removeChild(node)             | 删除指定的节点            |
| replaceChild(newNode,oldNode) | 用其他的节点替换指定的节点 |

##### HTML DOM

**table对象**

| 属性    | 说明                       |
| :----- | :------------------------- |
| rows[] | 返回该表格中所有行的一个数组 |

| 方法         | 说明                |
| :---------- | :----------------- |
| insertRow() | 从表格中插入一个新行 |
| deleteRow() | 从表格中删除一行     |

**tableRow对象**

| 属性     | 说明                           |
| :------- | :---------------------------- |
| cells[]  | 返回包含行中所有单元格的一个数组 |
| rowIndex | 返回该行在表中的位置                |

| 方法               | 说明                                   |
| :---------------- | :------------------------------------ |
| insertCell(index) | 在一行中的指定位置插入一个空的`<td>`标签 |
| deleteCell()      | 删除行中指定的单元格                    |

**tableCell对象**

| 属性       | 说明                                |
| :-------- | :---------------------------------- |
| cellIndex | 返回单元格在某行单元格集合中的位置     |
| innerHTML | 设置或返回单元格                     |
| align     | 设置或返回单元格内部数据的水平排列方式 |
| className | 设置或返回元素的class属性             |

### navigator对象

### screen对象
