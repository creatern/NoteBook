# 基本元素

| HTML的基本元素    | 说明                                                         |
| ----------------- | ------------------------------------------------------------ |
| `<!DOCTYPE html>` | 文档类型                                                     |
| `<html></html>`   | 根元素                                                       |
| `<head>`          | 信息容器  <br>包含所有想包含在 HTML 页面中但不在 HTML 页面中显示的内容 |
| `<body>`          | 页面内容                                                     |

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

| 原义字符  | 等价字符引用 |
| :-------- | :----------- |
| `<`       | `&lt;`       |
| `>`       | `&gt;`       |
| `"`       | `&gt;`       |
| `'`       | `&apos;`     |
| `&`       | `&amp;`      |
| `>=`      | `&ge;`       |
| `<=`      | `&le;`       |
| 注册商标® | `&reg;`      |
| 版权符号© | `&copy;`     |
| 空格      | `&nbsp;`     |

# 普通元素

| 普通元素          | 作用                 |
| :---------------- | :------------------- |
| \<br\>            | 换行                 |
| \<h1\>\</h1\>     | 标题：\<h1\>\~\<h6\> |
| \<span\>\</span\> | 无语义               |

| \<li\>\</li\> | 列表选项 |
| ------------- | -------- |
| \<ol\>\</ol\> | 有序列表 |
| \<ul\>\</ul\> | 无序列表 |

```html
<ul>
  <li>豆浆</li>
  <li>油条</li>
</ul>
```

| \<table\>\</table\> | 表格（可嵌套） |
| ------------------- | -------------- |
| \<tr\>\</tr\>       | 行             |
| \<td\>\</td\>       | 列             |

````html
<table>
    <th>
    	<td></td>
    </th>
	<tr>
		<td></td>
	</tr>
</table>
````

# 元素属性

| 属性  | 说明                           |
| ----- | ------------------------------ |
| id    | 唯一性标识                     |
| class | 分类标识                       |
| name  | 返回给服务器的名称（封装数据） |

# \<form\>\</form\>  表单

| form属性 | 说明             |
| -------- | ---------------- |
| action   | 服务器响应url    |
| name     | 表单名称         |
| value    | 发送给服务器的值 |
| method   | 请求类型         |

| 内部元素    | 说明     |
| ----------- | -------- |
| \<input/\>  | 表单填写 |
| \<button/\> | 提交按钮 |

## \<input/\> 表单填写

| type属性 | 类型                                                         |
| :------- | :----------------------------------------------------------- |
| text     | 文本框                                                       |
| password | 密码域                                                       |
| file     | 文件域                                                       |
| radio    | 单选按钮<br />单选组的name必须一致                           |
| checkbox | 复选框                                                       |
| submit   | 提交按钮                                                     |
| reset    | 重置按钮<br />恢复到默认状态（不等于清空）                   |
| button   | 普通按钮                                                     |
| hidden   | 隐藏域<br />隐藏域不会在页面显示，但提交表单时会一起被提交。 |
| image    | 图像域                                                       |

```html
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
```

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
