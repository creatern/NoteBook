## Tomcat安装和配置

**目录结构说明**

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_00-15-23.png =300x)

**配置环境变量**

1. 需要配置JAVA_HOME（安装JDK时配置的），否则Tomcat打开一闪而过，
  - 因为Tomcat是用C和Java编写的，需要JRE。 
  - 需要指向JDK的JAVA_HOME，否则不行。

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_00-58-11.png =400x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_01-00-14.png =400x)


2.  配置Tomcat环境变量
    1. 新建变量名：CATALINA_HOME，变量值：E:\Tomcat8
    2. 打开PATH，添加变量值：%CATALINA_HOME%\lib;%CATALINA_HOME%\bin
    3.  将命令行转入到Tomcat安装Bin目录，例如：E:\tomcat8\bin  ，cmd输入命令：`service.bat install`

**启动Tomcat，访问主页**

- 在Tomcat解压的bin目录中，打开startup.bat

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_00-24-36.png =500x)

##  部署项目

### Web项目

**部署**

- 到Tomcat解压目录的webapps目录中创建文件目录
- 并且在该目录下创建文件目录：`WEB-INFO`

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_01-17-51.png =200x)

**打开**

`http://localhost:8080/myTestWeb/demo1.html`

## IDEA 部署

1. 先新建一个项目Project，再新建一个Module，然后将Moudle设置为Web
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_10-24-42.png =300x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_10-27-17.png =550x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_10-28-00.png =300x)

2. 配置IDEA的Tomcat设置
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_10-16-09.png =300x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_10-17-22.png =300x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_10-19-28.png =550x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_10-32-24.png =550x)

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_10-39-54.png =550x)


### 404和`$END$`

- 修改web.xml中的内容，添加`<welcome-file-list></welcome-file-list>`
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-24_14-53-50.png =550x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-24_11-20-55png.png =550x)

### 405

- 当前请求的方法不支持，如POST必须对应doPost()。

### 空指针 NumberFormatException

- 获取的是null时，却进行了操作。

### 乱码问题

#### 控制台编码

- 修改IDEA中的配置setting
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_11-17-35.png =550x)
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_11-25-51.png =550x)
- 修改IDEA中的VM：
  - -Dfile.encoding=utf-8
  - -Dconsole.encoding=utf-8

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-19_11-26-57.png =550x)

- 修改IDEA中Tomcat的配置
![](c:/users/zjk10/onedrive/notebook/pictures/Snipaste_2023-03-05_17-23-02.png =550x)

- 以及修改Tomcat的"D:\Tomcat8\conf\ **logging.properties**"文件
![](c:/users/zjk10/onedrive/notebook/pictures/Snipaste_2023-03-05_17-19-23.png =550x)

- 重新启动IDEA即可

### Servlet 输出中文字符
