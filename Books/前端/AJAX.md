# AJAX概述

- AJAX（Asynchronous JavaScript And XML）：异步的JavaScript和XML。

| AJAX作用             | 说明                                                         |
| -------------------- | ------------------------------------------------------------ |
| 与服务器进行数据交换 | 给服务器发送请求，服务器将数据直接响应回给浏览器。<br />AJAX和服务器进行通信，以达到使用 HTML+AJAX来替换JSP页面。 |
| 异步交互             | 不重新加载整个页面的情况下，与服务器交换数据并更新部分网页的技术 |

<img src="../../pictures/Snipaste_2023-03-24_18-27-13.png" width="700"/> 

# XMLHttpRequest（xhr）

- XMLHttpRequest：Ajax的本质核心。

<img src="../../pictures/ajax.gif" width=600x/>

```javascript
//1.创建核心对象
var xhttp;
if (window.XMLHttpRequest) {
    xhttp = new XMLHttpRequest();
} else {
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
}
//2.发送请求 url使用全路径
xmlhttp.open("GET", "http://localhost:8080/ajaxServlet");
xmlhttp.send();
//3.获取响应
xmlhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
        alert(this.responseText);
        // document.getElementById("demo").innerHTML = this.responseText;
    }
}
```

| 请求方法           | 描述                                                         |
| :----------------- | :----------------------------------------------------------- |
| open()             | method：请求类型（GET、POST）<br>url：服务器（文件）位置 <br>async：true（异步）、 false（同步） |
| send()             | 向服务器发送GET、POST请求                                    |
| setRequestHeader() | 向请求添加 HTTP 头部<br>header：头部名称<br>value：头部值    |

| 响应属性               | 描述                                                         |
| :--------------------- | :----------------------------------------------------------- |
| onreadystatechange     | 定义readyState属性发生改变时所调用的函数。                   |
| readyState             | 保存XMLHttpRequest的状态。 <br>0: 请求未初始化 <br>1: 服务器连接已建立  <br>2: 请求已接收  <br>3: 正在处理请求   <br>4: 请求已完成且响应已就绪 |
| status<br />statusText | 请求状态码（200、404..）<br />返回状态文本（ "OK"、"Not Found"） |

# jQuery `$`

| 参数    | 说明                     |
| ------- | ------------------------ |
| url     | 待载入页面的URL。        |
| data    | 待发送的数据（键值对）。 |
| success | 回调函数。               |

```js
function home() {
    $.post({
        url: "/",
        data: {"name": $("#userName").val()},
        success: function (data, status) {
            console.log(data);
            console.log(status);
        }
    });

    $("textName").val();
}
```

```js
$(function () {
    $("#btnData").click(function () {
        $.post("/", function (data) {
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr>" +
                    "<td>" + data[i].name + "</td>" +
                    "<td>" + data[i].age + "</td>"
                    + "</tr>";

            }
            $("#content").html(html);
        })
    })
})
```

# axios

- axios异步框架：对原生的AJAX进行封装，简化书写。

## axios()

| axios()             | 发送异步请求，使用js对象传递请求相关的参数。                 |
| ------------------- | ------------------------------------------------------------ |
| **属性**            | **说明**                                                     |
| method              | 请求方式（get、post）。                                      |
| url                 | 资源路径。<br> `get` ： `url?参数名=参数值&参数名2=参数值2`。 |
| data                | 请求体发送的数据。<br>`post` ：数据需要作为 `data` 属性的值。 |
| **方法**            | **说明**                                                     |
| **then(回调函数) ** | 回调函数在发送请求时不会被调用，而是在成功响应后调用的函数。<br />resp：对响应的数据进行封装的对象， `resp.data` 获取到响应的数据。 |

```js
axios({
    method:"post",
    url="http://localhost:8080/aJAXDemo1",
    data:"username=zjk"
}).then(function(resp){
    alert(resp.data);
})
```

## axios实例

- axios.create([config])

```js
const instance = axios.create({
  baseURL: 'https://some-domain.com/api/',
  timeout: 1000,
  headers: {'X-Custom-Header': 'foobar'}
});
```

## 请求别名

| 请求    | 别名                            |
| :------ | :------------------------------ |
| get     | axios.get(url[,config])         |
| delete  | axios.delete(url[,config])      |
| head    | axios.head(url[,config])        |
| options | axios.option(url[,config])      |
| post    | axios.post(url[,data[,config])  |
| put     | axios.put(url[,data[,config])   |
| patch   | axios.patch(url[,data[,config]) |

```javascript
axios.post('/user', {
    firstName: 'Fred',
    lastName: 'Flintstone'
  })
  .then(response => {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
```

