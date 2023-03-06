# gvim

## 用vim修改文件编码为utf-8 

### 1.手动

```
:set fileencoding=utf-8
```

- 如果用vim打开文件时里面有乱码，可能用上面的命令修改文件后无法保存。可以用其他软件打开文件，然后把内容拷贝到vim里再保存就行了。  

**注意：**

1. vim 默认不是utf-8
2. 再次用vim打开文件会是再次乱码，需要重新再来一次set 

**vim关于文件编码常用的命令**

```
:set fileencoding    查看文件编码

:set fileformat       查看文件格式

:set fileencoding=utf-8 转换文件编码为utf-8
```

### 2.一劳永逸

1. 启动gvim8.0，菜单 ”编辑“->"启动设定"   
2. 在文件最开始处添加如下两行  

```
set fileencodings=utf-8,ucs-bom,cp936,big5
set fileencoding=utf-8
```

- https://www.cnblogs.com/itech/archive/2009/04/17/1438439.html


    