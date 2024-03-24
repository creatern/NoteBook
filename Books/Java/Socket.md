# 网络

> Java提供的网络类库可以实现无痛的网络连接，联网的底层细节被隐藏在 Java 的本机安装系统里，由 JVM 进行控制。并且 Java 实现了一个跨平台的网络库，程序员面对的是一个统一的网络编程环境。

# Socket 套接字

| Socket                                    | 网络套接字                                                   |
| ----------------------------------------- | ------------------------------------------------------------ |
| Socket()                                  | 创建空套接字。<br />若指定host、port，则连接指定地址（可能一直阻塞）。 |
| **套接字超时**                            | **说明：**连接到套接字、通过套接字读写的过程中，线程会被阻塞，需要设置超时。 |
| connect()                                 | 将套接字连接到指定地址，超时则返回。<br />address：InetSocketAddress，指定主机和端口创建地址对象。<br />（若不能解析，则unresolved设为true）<br />timeout：最大尝试连接时间（ms）。 |
| setSoTimeOut()                            | 设置该套接字上读请求的阻塞时间，超时则抛出InterruptedIOException。 |
| isConnected()                             | 若该套接字已经被连接，则返回true。                           |
| isClosed()                                | 若该套接字已经被关闭，则返回true。                           |
| **输入输出流**                            | **说明**                                                     |
| getInputStream()                          | 返回字节输入流。                                             |
| getOutputStream()                         | 返回字节输出流。                                             |
| **半关闭**                                | **说明**                                                     |
| shutdownOutput()<br />shutdownInput()     | 关闭输出流，停止发送数据。<br />关闭输入流，停止接收数据。   |
| isOutputShutdown()<br />isInputShutdown() | 若输入/输出流关闭，则返回true。                              |

```java
try (Socket socket = new Socket()) {
    socket.connect(new InetSocketAddress("time-a.nist.gov", 13), 10000);
    try (BufferedInputStream in = new BufferedInputStream(socket.getInputStream())) {
        System.out.println(new String(in.readAllBytes()));
    }
}
```

## InetAddress IP地址

- InetAddress对象含有一个网络主机地址的域名和IP地址（Inet4Address、Inet6Address）。

| InetAddress                         | 返回                                                         |
| ----------------------------------- | ------------------------------------------------------------ |
| getByName()<br />getAllByName()     | 返回InetAddress对象，指定域名对应的IP地址。<br />返回`InetAddress[]`，该域名所对应的所有IP地址。 |
| getAddress()                        | 返回包含IP地址的`byte[]`。                                   |
| getLocalHost()                      | 返回返回InetAddress对象，本地主机127.0.0.1。                 |
| getHostName()<br />getHostAddress() | 返回本机名。<br />返回本机IP。                               |

## ServerSocket 服务器套接字

| ServerSocket  | 服务器套接字                                                 |
| ------------- | ------------------------------------------------------------ |
| SeverSocket() | 指定监听端口的服务器套接字。                                 |
| accept()      | 等待连接，将一直阻塞，直到建立连接。<br />与客户端建立连接后，返回一个Scoket对象。 |
| close()       | 关闭。                                                       |

```java
try (ServerSocket serverSocket = new ServerSocket(8081)) {
    try (Socket socket = serverSocket.accept();
         OutputStream out = socket.getOutputStream()) {
        out.write("Hello! I`m Server.".getBytes());
    }
}

//socket.connect(new InetSocketAddress("127.0.0.1", 8081), 30000);
```

## SocketChannel 可中断套接字

| SocketChannel                           | 说明                                                         |
| --------------------------------------- | ------------------------------------------------------------ |
| open()                                  | 打开一个套接字通道（Channel）。<br />address：InetSocketAddress。 |
| **Channels**                            | **说明**                                                     |
| newInputStream()<br />newOutputStream() | 返回字节输入/输出流。<br />channel：SocketChannel。          |

# URL、URI

| 资源符号                                               | 说明                                                         |
| ------------------------------------------------------ | ------------------------------------------------------------ |
| URL（统一资源定位符）<br />Uniform Resource Locator    | URL是URI的一个特例。<br />包含了用于定位Web资源的足够信息，可打开到达资源的流。 |
| URI（统一资源标识符）<br />Uniform Resource Identifier | 不包含任何用于访问资源的方法，只能用于解析。                 |

| URI                         | 统一资源标识符                                               |
| --------------------------- | ------------------------------------------------------------ |
| getXxx()                    | 读取标识符的各部分解析。<br />`[scheme:]schemeSpecificPart[#fragment]`<br />schemeSpecificPart：`[//authority][path][?query]`<br />authority：`[user-info@]host[:port]`。 |
| relativize()<br />resolve() | 解析相对URI。<br />相对化。                                  |

> scheme：若存在，则为绝对URI，否则为相对URI。<br />schemeSpecificPart：若不是/开头，则不透明（所有透明、相对的URI都是分层的）。

## URLConnection

| URL                                     | 统一资源定位符                                               |
| --------------------------------------- | ------------------------------------------------------------ |
| openConnect()                           | 返回URLConnection对象。                                      |
| **URLConnection**                       | **URL连接**                                                  |
| setXxx()                                | 设置请求属性。                                               |
| setDoInput()<br />setDoOutput()         | 默认建立的连接只产生输入流读取服务器信息，并不产生输出流。<br />setDoOutput(true)：允许产生输出流。 |
| setRequestProperty()                    | 设置对特定协议作用的键值对（Base64编码）。                   |
| connect()                               | 连接远程资源。                                               |
| getXxx()                                | 查询头信息（建立连接之后）。                                 |
| getInputStream()<br />getOutputStream() | 返回输入/输出流。                                            |

```java
connect.setRequestProperty("Authorization","Basic " + 
                           Base64.getEncoder().encodeToString(
                               (username + ":" + password).getBytes(StandardCharsets.UTF_8))); 
```
