# 基础知识

## JavaWeb 概述

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/26080321227450.png =709x)



![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-17_01-43-35.png =600x)

## C/S和B/S体系结构

### C/S 客户端/服务器结构

- 在这种结构中，服务器通常采用高性能的PC机或工作站，并采用大型数据库系统，客户端则需要安装专用的客户端软件。可以充分发挥两端硬件环境的优势，将任务合理分配到客户端和服务器，从而降低了系统的通信开销。

![](c:/users/zjk10/onedrive/notebook/pictures/Snipaste_2023-03-08_16-34-12.png =400x)

### B/S 浏览器/服务器结构

- 在这种结构中，客户端不需要开发任何用户界面，而统一采用浏览器，通过Web浏览器向Web服务器发送请求，由Web服务器进行处理，并将处理结果逐级传回客户端。通过利用浏览器技术来减少开发成本，成为当今应用软件的首选体系结构。

![](c:/users/zjk10/onedrive/notebook/pictures/Snipaste_2023-03-08_16-34-48.png =400x)

### C/S和B/S的比较

**开发和维护成本上：**

- C/S结构的开发和维护成本都比B/S结构高。采用C/S结构时对于不同的客户端要开发不同的应用程序而且软件的安装、调试和升级均需要在所有的客户机上进行。
- 而B/S结构的软件则不必在客户端进行安装和维护，只需要对服务器进行升级维护即可。

**客户端负载**

- C/S结构充分利用客户端机器的资源，减轻服务器的负荷（一部分安全要求不高的计算任务存储任务放在客户端执行，不需要把所有的计算和存储都在服务器端执行，从而能够减轻服务器的压力，也能够减轻网络负荷)。
- B/S结构所有的计算和存储任务都是放在服务器端的，服务器的负荷较重；在服务端计算完成，之后把结果再传输给客户端，因此客户端和服务器端会进行非常频繁的数据通信，从而网络负荷较重。

**安全性**

- C/S结构适用于专人使用的系统，经过严格的管理派发软件，安全性高于B/S结构。

## Web应用程序的工作原理

- Web应用程序可以分为静态网站和动态网站。

### 静态网站

- 使用HTML语言编写的，放在Web服务器上，用户使用浏览器通过HTTP协议请求服务器上的Web页面。服务器上的Web服务器将接受到的用户请求处理后，再发送给客户端浏览器，显示给用户。

![](c:/users/zjk10/onedrive/notebook/pictures/Snipaste_2023-03-08_16-50-52.png =500x)

### 动态网站

- **根据用户的请求动态地生成页面信息**。通常使用HTML语言和动态脚本语言（JSP、ASP、PHP等）编写，并将编写后的程序部署到Web服务器上，由Web服务器对动态脚本代码进行处理，并转化为浏览器可以解析的HTML代码，返回给客户端浏览器，显示给客户。
- 并不等同于带有动画效果的网页，而是具有交互功能的网页。

![](c:/users/zjk10/onedrive/notebook/pictures/Snipaste_2023-03-08_16-51-06.png =500x)

# Servlet

## 导入对应的API

**所需要的API不在JDK内，需要从Tomcat中导入**

- 方式1
   - 从路径"E:\Tomcat8\lib\servlet-api.jar"导入

- 方式2
   - 从IDEA中导入
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-21_23-17-14.png =600x)


## Servlet

### 处理Web请求的过程

1. 服务器接收从客服端发出的请求
2. 服务器将请求信息发送至Servlet
3. Servlet经过处理之后，生成响应的内容
4. 服务器将响应的内容返回给客服端

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-21_23-49-44.png =650x)

### Servlet结构体系

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-23_16-02-35.png =600x)

#### HttpServlet类

**HttpServlet容器响应Web客户请求流程如下：**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-25_09-43-35.png =550x)

1. Web客户向Servlet容器发出Http请求；
2. Servlet容器解析Web客户的Http请求；
3. Servlet容器创建一个HttpRequest对象，在这个对象中封装Http请求信息；
4. Servlet容器创建一个HttpResponse对象；
5. Servlet容器调用HttpServlet的service方法，把HttpRequest和HttpResponse对象作为service方法的参数传给HttpServlet对象；
6. HttpServlet调用HttpRequest的有关方法，获取HTTP请求信息；
7. HttpServlet调用HttpResponse的有关方法，生成响应数据；
8. Servlet容器把HttpServlet的响应结果传给Web客户

**HTTP的请求方式包括DELETE,GET,OPTIONS,POST,PUT和TRACE,**

在HttpServlet类中分别提供了相应的服务方法,它们是,

- doDelete(),
- doGet(),
- doOptions(),
- doPost(),
-  doPut()
- doTrace().

#### ServletRequest 接口

| 方法                                           | 说明                         |
| :-------------------------------------------- | :--------------------------- |
| Object getAttribute(String name)              | 获取名称为name的属性值         |
| void setAttribute(String name, Object object) | 在请求中保存名称为name的属性值 |
| void removeAttibute(String name)              | 清除请求中名称为name的属性值   |
| String getParameter()                         | 获取表单中传递的参数           |

#### HttpServletRequest 接口

| 增加的方法                 | 说明                                                                         |
| :------------------------ | :--------------------------------------------------------------------------- |
| String getContextPath()   | 返回请求URL中表示请求上下文的路径（URL开始部分）                                 |
| Cookie[] getCookies()     | 返回客户端在此次请求中发送的所有Cookie对象                                      |
| HttpSession  getSession() | 返回和此次请求相关联的Session，如果没有给客户端分配Session，则创建一个新的Session |
| String getMethod()        | 返回此次请求使用的Http方法的名称，如GET、POST                                   |


#### ServletResponse 接口

| 方法                              | 说明                                                 |
| :------------------------------- | :-------------------------------------------------- |
| PrintWriter getWriter()          | 返回PrintWriter对象，用于向客户端发送文本              |
| String getCharacterEncoding()    | 返回在响应中发送的正文所使用的字符编码                  |
| void setCharacterEncoding()      | 设置发送到客户端的响应的字符集编码                     |
| void setContentType(String type) | 设置发送到客户端的响应的内容类型，此时响应的状态尚未提交 |

#### HttpServletResponse 接口

| 增加的方法                                 | 说明                                                                    |
| :---------------------------------------- | :---------------------------------------------------------------------- |
| void addCookie(Cookie cookie)             | 增加一个Cookie到响应，此方法可以多次调用，设置多个Cookie                    |
| void addHeader(String name, String value) | 将一个名称为name，值为value的响应报头添加到响应中                          |
| void sendRedirect(String location)        | 发送一个临时的重定向响应到客户端，以便客户端访问新的URL，抛出一个IOException |
| void encodeURL(String url)                | 使用SessionID对用于重定向的URL进行编码，以便用于sendRedirect()方法中        |

### Servlet生命周期

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-23_15-52-30.png =400x)


- 第一次接收请求时，这个Servlet会进行实例化(调用构造方法)、初始化(调用init())、然后服务(调用service())
- 从第二次请求开始，每一次都是服务。
- 当容器关闭时，其中的所有的servlet实例会被销毁，调用销毁方法

**Servlet的初始化时机：**
        
- 默认情况下，第一次请求时，tomcat才会去实例化，初始化。 第一次请求时，耗时较长。如果需要提高系统的启动速度，当前默认情况就是这样。如果需要提高响应速度，我们应该设置Servlet的初始化时机。
- 我们可以通过`<servlet>`元素中的`<load-on-startup>`来设置servlet启动的先后顺序，数字越小，启动越靠前，最小值0

**Servlet在容器中是：单例的、线程不安全的**

- 单例：Servlet实例tomcat只会创建一个，所有的请求都是这个实例去响应。
- 线程不安全：

### 使用Servlet

#### 1.创建Servlet类

1. 实现Servlet接口
2. 继承GenericServlet类
3. 继承HttpSevlet类

**创建HttpServlet的步骤——“四部曲”**

1. 扩展HttpServlet抽象类； 
2. 覆盖HttpServlet的部分方法，如覆盖doGet()或doPost()方法； 
3. 获取HTTP请求信息。通过HttpServletRequest对象来检索HTML表单所提交的数据或URL上的查询字符串； 
4. 生成HTTP响应结果。通过HttpServletResponse对象生成响应结果，它有一个getWriter()方法，该方法返回一个PrintWriter对象。

#### 2.Servlet的部署 web.xml

- `<servlet>`元素，将Servlet内部名映射到一个Servlet类名，"包名+类名"
   - `<servlet-name>类名</servlet-name>`
   - `<servlet-class>包名+类名（即：完整的类位置）<servlet-class>`  
- `<servlet-mapping>`元素，将用户访问的URL映射到Servlet内部名
   - 一个 `<servlet>`可以对应多个`<servlet-mapping>`，即：可以将同一个Servlet程序应用于多个不同的页面
   - `<servlet-name></servlet-name>` 必须与`<servlet>`元素中的`<servlet-name>`一致
   - `<url-pattern></url-pattern>` 容器无法识别同时拥有两种匹配规则的pattern
      1. 精确匹配：类似于/myServlet的精确路径
      2. 通配符匹配：`/*`
      3. 扩展名匹配：`*.html，*.jpg ，.do ，.action`之类的
      4. 默认匹配（`/`）——当之前匹配都不成功时

#### 注：

**一个html只能有一个对应的Servlet，但可以有多个Filter**

**编码**

- post方式下需要设置编码，防止中文乱码
  - `req.setCharacterEncoding("utf-8")` 
  - 必须在获取参数之前完成设置
- get方式不需要设置编码，基于Tomcat8
  - Tomcat8之前
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-24_15-13-46.png =600x)

**例**

- MyServletTest.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
</head>
<body>
<!--
    action:提交到服务器的路径
    method:提交的方式
 -->
<form action="MyServlet" method="post">
    <!-- servlet通过name属性获取用户输入的数据 -->
    用户名:<input type="text" name="username"><br>
    密码:<input type="password" name="userpwd"><br>
    <input type="submit" value="注册">
</form>
</body>
</html>
```

- MyServlet.class

```java
package com.zjk.JSP;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MyServlet", value = "/MyServlet")
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /* 对应form表单的get请求方式
     * HttpServletRequest：请求对象
     * HttpServletResponse：响应对象
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设定请求的字符集
        req.setCharacterEncoding("utf-8");
        //设置响应的文本类型
        resp.setContentType("text/html;charset=utf-8");
        //通过请求对象获取用户输入的内容
        String username = req.getParameter("username");
        String password = req.getParameter("userpwd");

        System.out.println(username + " : " + password);
        //如果输入的用户名是abc，密码是123，则表示注册成功，反之注册失败
//        if ("abc".equals(username) && "123".equals(password)) {
//            //使用响应对象，重定向到成功页面
//            //resp.sendRedirect("success.html");
//            //请求转发
//            req.getRequestDispatcher("success.html").forward(req, resp);
//        } else {
//            //使用响应对象，重定向到注册页面
//            resp.sendRedirect("MyServletTest.html");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设定请求的字符集
        req.setCharacterEncoding("utf-8");
        //设置响应的文本类型
        resp.setContentType("text/html;charset=utf-8");
        //通过请求对象获取用户输入的内容
        String username = req.getParameter("username");
        String password = req.getParameter("userpwd");
        System.out.println(username + " " + password);
        //如果输入的用户名是abc，密码是123，则表示注册成功，反之注册失败
//        if ("abc".equals(username) && "123".equals(password)) {
//            //使用响应对象，重定向到成功页面
//            resp.sendRedirect("success.html");
//        } else {
//            //使用响应对象，重定向到注册页面
//            resp.sendRedirect("MyServletTest.html");
//        }
    }
}
```

- web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <servlet>
        <servlet-name>MyServlet</servlet-name> <!--用来映射的名字，可以随便起-->
        <servlet-class>com.zjk.JSP.MyServlet</servlet-class> <!--指向该类的地址 包名+类名-->
    </servlet>
    <servlet-mapping>
        <servlet-name>MyServlet</servlet-name> <!--映射中的servlet-name要与servlet中的一致-->
        <url-pattern>/MyServlet</url-pattern> <!--指向的网页的URL（地址）-->
    </servlet-mapping>
</web-app>
```

### 服务器内部转发以及重定向

| 服务器内部转发                                           |          客户端重定向           |
| :------------------------------------------------------ | ------------------------------ |
| req.getRequestDispatcher("网页路径").forward(req,resp); | resp.sendRedirect("网页路径"); |
| 一次请求响应的过程，客户端不知道服务器内部的转发            | 两次请求响应的过程              |
| 地址栏不变                                               | 地址栏改变                      |

**服务器内部转发**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-27_10-06-35.png =600x)

**客服端重定向**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-27_10-09-04.png =600x)

```java
package com.zjk.redirect;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDemo01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo01");
        //服务器内部转发
        //req.getRequestDispatcher("demo02");

        //客户端重定向
        resp.sendRedirect("demo02");
    }
}
```

## HTTP 超文本传输协议

- Http是无状态的
- Http请求响应包含两个部分：请求和响应

### 请求
    
- 请求包含三个部分： 1.请求行 ； 2.请求消息头 ； 3.请求主体

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-25_11-26-34.png =500x)


#### 请求行

**1. 请求的方式 ； 2.请求的URL ； 3.请求的协议（一般都是HTTP1.1）**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-25_11-31-49.png =650x)

#### 请求消息头

- 包含了很多客户端需要告诉服务器的信息

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-25_11-29-04.png =500x)

#### 请求体

- get方式，没有请求体，但是有一个queryString
- post方式，有请求体，form data
- json格式，有请求体，request payload

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-25_11-29-54.png =650x)

### 响应：

**1. 响应行 ； 2.响应头 ； 3.响应体**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-25_11-32-42.png =500x)

#### 响应行

**1.协议 2.响应状态码(200) 3.响应状态(ok)**

##### 响应状态码

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-25_11-33-24.png =500x)

#### 响应头

- 包含了服务器的信息；服务器发送给浏览器的信息（内容的媒体类型、编码、内容长度等）

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-25_11-36-19.png =500x)

#### 响应体

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-25_11-35-01.png =300x)

## session 会话

**Http是无状态的**

- HTTP 无状态 ：服务器无法判断这两次请求是同一个客户端发过来的，还是不同的客户端发过来的
- 无状态带来的现实问题：第一次请求是添加商品到购物车，第二次请求是结账；如果这两次请求服务器无法区分是同一个用户的，那么就会导致混乱
- 通过会话跟踪技术来解决无状态的问题。

### 会话跟踪技术

- 客户端第一次发请求给服务器，服务器获取session，获取不到，则创建新的，然后响应给客户端
- 下次客户端给服务器发请求时，会把sessionID带给服务器，那么服务器就能获取到了，那么服务器就判断这一次请求和上次某次请求是同一个客户端，从而能够区分开客户端

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-25_14-33-58.png =500x)

| 方法                              | 说明                                                                                                    |
| :------------------------------- | :------------------------------------------------------------------------------------------------------ |
| request.getSession(boolean)      | 获取当前的会话，没有则创建一个新的会话；true效果和不带参数相同；false获取当前会话，没有则返回null，不会创建新的 |
| session.getId()                  | 获取sessionID                                                                                           |
| session.isNew()                  | 判断当前session是否是新的                                                                                |
| session.getMaxInactiveInterval() | session的非激活间隔时长，默认1800秒                                                                       |
| session.setMaxInactiveInterval() | 设置session的非激活间隔时长，默认1800秒                                                                   |
| session.invalidate()             | 强制性让会话立即失效                                                                                     |

1. HttpServlet

```java
package com.zjk.sessions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ServletSession extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取SessionID
        HttpSession session = req.getSession();
        System.out.println("SessionID: " + session.getId());
        System.out.println(session.isNew());
        System.out.println(session.getMaxInactiveInterval());
    }
}
```

2. web.xml部分配置

```xml
<servlet>
    <servlet-name>ServletSession</servlet-name>
    <servlet-class>com.zjk.sessions.ServletSession</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>ServletSession</servlet-name>
    <url-pattern>/fruit</url-pattern>
</servlet-mapping>
```

### session保存作用域

- session保存作用域是和具体的某一个session对应的，切换其他session时，保存域不同。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-25_14-51-25.png =800x)

| 方法                            | 说明 |
| :----------------------------- | :--- |
| void session.setAttribute(k,v) |      |
| Object session.getAttribute(k) |      |
| void removeAttribute(k)        |      |

1. Servlet

```java
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirstSession extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("fname","Tom");
        System.out.println("1");
    }
}

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecondeSession extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("2" + req.getSession().getAttribute("fname"));
    }
}
```

2. web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <servlet>
        <servlet-name>FirstSession</servlet-name>
        <servlet-class>FirstSession</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>FirstSession</servlet-name>
        <url-pattern>/demo1</url-pattern>
    </servlet-mapping>
    <servlet>
        <servlet-name>SecondeSession</servlet-name>
        <servlet-class>SecondeSession</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>SecondeSession</servlet-name>
        <url-pattern>/demo2</url-pattern>
    </servlet-mapping>
</web-app>
```

## Filter接口 过滤器

**过滤器的工作原理**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-24_23-53-56.png =500x)

**过滤器链**

- 多个Filter接口的实现类，在web.xml配置文件中，按顺序执行
- 多个具有特定操作和功能的过滤器的组合，且在配置文件中也要存在相应的多个过滤器配置，按配置文件中的顺序，逐一进行处理，直到Web资源。

**应用**

1. 统一处理请求和响应
2. 对请求进行日志记录和审核
3. 对数据屏蔽和替换
4. 对数据加密和解密

### Filter接口 方法

**生命周期**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-25_00-07-59.png =300x)

**init(FilterConfig filterConfig)**
     
1. 在web应用程序启动时，web服务器将根据 web.xml文件中的配置信息来创建每个注册的Filter实例对象，并将其保存在服务器的内存中。
2. Web容器创建Filter对象实例后，将立即调用该Filter对象的init()方法。
      - Init方法在Filter生命周期中仅执行一次
      - 传递一个包含Filter的配置和运行环境的FilterConfig对象
3. 利用FilterConfig对象可以得到ServletContext对象，以及部署描述符中配置的过滤器的初始化参数。

<mark>**doFilter(ServletRequest request, ServletResponse response, FilterChain chain)**</mark>

1. 当客户端请求目标资源的时候，容器就会调用与这个目标资源相关联的过滤器的 doFilter()方法。
2. 参数 request, response 为 web 容器或 Filter 链(即：<mark>chain参数的doFilter()</mark>使用的参数)的上一个 Filter 传递过来的请求和相应对象；参数 chain 为代表当前 Filter 链的对象，
3. 可以在当前 Filter 对象的 doFilter() 方法的内部调用 `FilterChain对象参数的 chain.doFilter(request,response)方法`把请求交付给 Filter 链中的下一个 Filter 或者目标 Servlet 程序去处理。<mark>否则无法使用其他的程序：其他的Fliter或Servlet等</mark>
   - 过滤器链中的任何一个 Filter 没有调用 `FilterChain.doFilter()` 方法，请求都不会到达目标资源。

**public void destroy()**

- 释放过滤器使用的资源。

### 使用过滤器

**1. 实现Filter接口**

**2. 配置web.xml文件**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-25_10-28-42.png =700x)

```java
package com.zjk.filters;

import javax.servlet.*;
import java.io.IOException;

public class FilterTest implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        //如果没有 filterChain.doFilter(servletRequest, servletResponse)
        //则无法使用其他的应用程序：如其他的Filter、Servlet等
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Filter OK!");
    }

    @Override
    public void destroy() {
        System.out.println("filter is destoried");
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <servlet>
        <servlet-name>FruitServlet</servlet-name> <!--用来映射的名字，可以随便起-->
        <servlet-class>com.zjk.servlets.FruitServlet</servlet-class> <!--指向该类的地址 包名+类名-->
    </servlet>
    <servlet-mapping>
        <servlet-name>FruitServlet</servlet-name> <!--映射中的servlet-name要与servlet中的一致-->
        <url-pattern>/fruit</url-pattern> <!--指向的网页的URL（地址）-->
    </servlet-mapping>
    <filter>
        <display-name>FilterTest</display-name>
        <filter-name>FilterTest</filter-name> <!--用来映射的名字，可以随便起-->
        <filter-class>com.zjk.filters.FilterTest</filter-class> <!--指向该类的地址 包名+类名-->
    </filter>
    <filter-mapping>
        <filter-name>FilterTest</filter-name> <!--映射中的filter-name要与filter中的一致-->
        <url-pattern>/fruit</url-pattern> <!--指向的网页的URL（地址）-->
    </filter-mapping>
</web-app>
```

### FilterChain接口



## Servlet监听器




