# IO 输入/输出

- java.io：流式IO是一种顺序存取方式，流中的字节依据先进先出的规则。

<img src="../../pictures/Java-IOsystem.drawio.svg" />

> Java中输入输出的相对路径为用户工作目录起始：`System.getProperty("user.dir");`。

# (In/Out)putStream 输入/输出流

<img src="../../pictures/Java-IOCloseable-Flushable.drawio.svg" width="700"/>

| 流类型   | 字节流                        | 字符流              |
| -------- | ----------------------------- | ------------------- |
| 读写单位 | 8位字节                       | 16位字符（Unicode） |
| 流接口   | InputStream<br />OutputStream | Reader<br />Writer  |

| 输入流          | InputStream                                                  | Reader         |
| --------------- | ------------------------------------------------------------ | -------------- |
| **读**          | **说明**                                                     | **字节改字符** |
| read()          | 返回所读的字节，若返回-1，文件结束。<br />空参：每次调用读取一个字节。<br />`byte[] b`：读取的数据存入并返回该字节数组。<br />off、len：指明读取的数据在数组中的存放位置。 | `char[] cbuf`  |
| available()     | 返回当前可读取的总字节数。                                   | -              |
| skip()          | 跳过指定字节数（n）。                                        | -              |
| **标记**        | **说明**                                                     | -              |
| mark()          | 标记当前流。<br />readlimit指定reset()方法能够重复读取的字节数（缓冲区大小）。<br />若已读取的字节数多于readlimit，则允许忽略该标记。 | -              |
| markSupported() | 判断该流是否支持标记。                                       | -              |
| reset()         | 若当前流被标记，则返回到最后一个标记位置，之后的read()操作从该位置开始。 | -              |

| 输出流  | OutputStream                                                 | Writer                          |
| ------- | ------------------------------------------------------------ | ------------------------------- |
| **写**  | **说明**                                                     | **字节改字符**                  |
| write() | `int c`：写一个字节<br />`byte[] b`：写一个字节数组。<br />off、len：写出的字节数组内数据的位置。 | `char cbuf[]`<br />`String str` |

```java
File readFile = new File("Hello.txt");
File writeFile = new File("HelloWriteFile.txt");

try (FileReader fileReader = new FileReader(readFile);
     FileWriter fileWriter = new FileWriter(writeFile)) {
    char[] cbuf = new char[5];
    int len;
    while ((len = fileReader.read(cbuf)) != -1) {
        fileWriter.write(cbuf, 0, len);
    }
} catch (IOException e) {
    throw new RuntimeException(e);
}
```

## File 文件

- File：外存文件和目录的抽象表示，用来操作文件和获得文件的信息，由文件流提供对文件数据读取的方法。

| 文件字节流                            | 文件字符流                 |
| ------------------------------------- | -------------------------- |
| FileInputStream<br />FileOutputStream | FileReader<br />FileWriter |

## Filter(In/Out)putStream 组合过滤器

- 套接流（流链）：过滤流将多个流套接在一起，利用各种流的特性共同处理数据。 在关闭外层流的同时，内层流也会自动进行关闭。

| FilterInputStream过滤器      | 说明                                                         |
| ---------------------------- | ------------------------------------------------------------ |
| PushbackInputStream          | （预览）回推缓冲区（size指定大小）。<br />unread()：回推一个字节。 |
| BufferedInputStream          | 缓冲区空时才读入一个数据块。                                 |
| **FilterOutputStream过滤器** | **说明**                                                     |
| BufferedOutputStream         | 缓冲区满、被冲刷时，写出。                                   |

##  Piped(In/Out)putStream 管道流

- 管道流：实现线程间数据的直接传输，一个管道由管道输出端（管道输出流）与管道输入端（管道输入流）连接而成。线程A可以通过它的输出管道发送数据，线程B把它的输人管道接到A的输出管道上即可接收A发送的数据。

> 管道的连接实际上是使管道的输入流指向管道的输出流，或管道的输出流也指向管道输入流，这样从管道的输入流可以读取写入管道输出流的数据。
> 管道流有时会使依赖于管道通信的程序造成**死锁** ： Java使用管道进行线程连接时不用考虑线程的同步问题。

| 管道         | 类                                 |
| ------------ | ---------------------------------- |
| 管道的输入流 | PipedReader<br />PipedInputStream  |
| 管道的输出流 | PipedWriter<br />PipedOutputStream |

```java
PipedInputStream pin = new PipedInputStream();
//在管道输出流创建时挂接
PipedOutputStream pout = new PipedOutputStream(pin);
```

```java
PipedInputStream pin = new PipedInputstream();
PipedOutputStream pout = new PipedOutputStrea();
//在管道输出流和输入流创建后挂接
pin.connect(out); //或者 pout.connect(in);
```

# 文本输入/输出

| System标准输入/输出重定向 | 说明           |
| ------------------------- | -------------- |
| setIn(InpuStream in)      | 重新指定输入流 |
| setOut(PrintStream out)   | 重新指定输出流 |

## PrintWriter、PrintStream

| PrintWriter文本输出                                          | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| PrintWriter(Writer w)<br />PrintWriter(Writer w, boolean autoFlush) | 指定编码向写出器写出。<br />autoFlush：是否打开自动冲刷。    |
| PrintWriter(String filename, String encoding)<br />PrintWriter(File file, String encoding) | 指定编码向文件写出。                                         |
| print()                                                      | 打印， 不抛出异常。                                          |
| println()                                                    | 打印字符串，带有行终止符。<br />若打开自动冲刷，则冲刷该流。 |
| printf()                                                     | 格式打印。                                                   |
| checkError()                                                 | 返回true，则受到污染、输出错误、格式化错误。                 |

```java
try (PrintWriter pw = new PrintWriter(System.out,true)) {
    pw.println(System.getProperty("user.dir"));
    //System.out.println()
} catch (Exception ex) {
    ex.printStackTrace();
}
```

## InputStreamReader、OutputStreamWriter 转换流

| 转换流             | 参数                              | 返回         |
| ------------------ | --------------------------------- | ------------ |
| InputStreamReader  | InputStream<br />encoding：编码集 | Reader       |
| OutputStreamWriter | Writer<br />encoding：编码集      | OutputStream |

## CharSet 编码集

| 方式                       | 返回的编码集         |
| -------------------------- | -------------------- |
| `Charset.defaultCharset()` | 当前平台的编码方式。 |
| `Standard.UTF_8`           | utf-8编码方式。      |

# 二进制输入/输出

## Data(In/Out)putStream 数据流

- 数据流：基本数据类型、String（底层存储）的串行化，读取或写出基本数据类型的变量/字符串。

> Java中的所有数据都是高位在前（MSB）的模式写出。

| 二进制流 | DataInputStream                                              | DataOutputStream                                             |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 方法     | readInt()<br />readShort()<br />readLong()<br />readFloat()<br />readDouble()<br />readChar()<br />readBoolean()<br />readUTF() | writeChars()<br />writeByte()<br />writeInt()<br />writeShort()<br />writeLong()<br />writeFloat()<br />writeDouble()<br />writeChar()<br />writeBoolean()<br />writeUTF() |
| 说明     | 以固定的字节读取                                             | 以固定的字节写出                                             |

- 读取不同类型数据的顺序需要与保存不同类型的数据的顺序相同：否则可能报错、数据顺序出错。

```java
File file = new File(System.getProperty("user.dir") + "/test.txt");
try (DataInputStream din = new DataInputStream(new FileInputStream(file));
     DataOutputStream dout = new DataOutputStream(new FileOutputStream(file))) {
    dout.writeInt(10086);
    while (din.available() != 0) {
        System.out.println(din.readInt());
    }
} catch (Exception ex) {
}
```

## RandomAccessFile 随机访问

| RandomAccessFile          | 随机访问                                                     |
| ------------------------- | ------------------------------------------------------------ |
| 实现DataOutput、DataInput | 可以在文件的任意位置读/写数据。                              |
| 文件指针                  | 下一个将被读/写的字节所处位置。                              |
| **方法**                  | **说明**                                                     |
| RandomAccessFile()        | filename：文件。<br />"r"（只读）、"rw"（读写）<br />"rws"（每次更新，对数据和元数据的写进行同步的读/写模式）<br />"rws"（每次更新，对数据的写进行同步的读/写模式）。 |
| seek()                    | 设置文件指针距离文件开头（0）的位置（`0~n`）。               |
| getFilePointer()          | 返回文件指针当前位置。                                       |
| length()                  | 返回文件总字节长度。                                         |

## Zip(In/Out)putStream、ZipEntry

| 流                 | ZipInputStream                                               | ZipOutputStream                                              |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 构造器             | ZipInputStream(InputStream in)                               | ZipOutputStream(OutputStream out)                            |
| 输入输出<br />定位 | getNextEntry()<br />返回并定位输入流到下一项的ZipEntry。     | putNextEntry(ZipEntry ze)<br />写出并定位输出流到下一项的ZipEntry。 |
| 结束当前项         | closeEntry()<br />先关闭当前打开的ZipEntry，getNextEntry()才能获取下一项 | closeEntry()<br />先关闭当前打开的ZipEntry，putNextEntry()才能设置下一项。 |

- Zip(In/Out)putStream可以在外部嵌套流，并通过定位来对Zip中的每一项操作，不需要对每个项都设置一个输入/出流。

| ZipEntry                  | 对应Zip文件的一项                                |
| ------------------------- | ------------------------------------------------ |
| ZipEntry(String filename) | 指定文件名创建。                                 |
| getCrc()                  | 返回该ZipEntry的CRC232校验和的值。               |
| getName()                 | 返回该项的名字。                                 |
| getSize()                 | 返回一项未压缩的尺寸。<br />若不可知，则返回-1。 |
| isDirectory()             | 判断是否为目录。                                 |
| setMethod(int method)     | 指定压缩方法（DEFALTED、STORED）。               |
| setSize(long size)        | 设置该项的尺寸（STORED必须）。                   |
| setSrc(long crc)          | 设置该项的CRC32校验和（CRC32类）（STORED必须）。 |

| ZipFile                                      | 压缩文件                                               |
| -------------------------------------------- | ------------------------------------------------------ |
| ZipFile(String name)<br />ZipFile(File file) | 从指定的字符串、File对象读取数据。                     |
| entries()                                    | 返回Enumeration对象，枚举该ZipFile中各个项的ZipEntry。 |
| getEntry(String name)                        | 返回指定文件名的项。<br />若该项不存在，则返回null。   |
| getInputStream(ZipEntry ze)                  | 返回用于指定项的InputStream。                          |
| getName()                                    | 返回该Zip文件的路径。                                  |

```java
try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("test.zip"));
     DataOutputStream dout = new DataOutputStream(zout)) {
    zout.putNextEntry(new ZipEntry("t1.txt"));
    dout.writeInt(100);
    zout.closeEntry();

    zout.putNextEntry(new ZipEntry("t2.txt"));
    dout.writeBoolean(true);
    zout.closeEntry();
} catch (Exception ex) {
}

try (ZipInputStream zin = new ZipInputStream(new FileInputStream("test.zip"));
     DataInputStream din = new DataInputStream(zin)) {
    zin.getNextEntry();
    System.out.println(din.readInt());
    zin.closeEntry();

    zin.getNextEntry();
    System.out.println(din.readBoolean());
    zin.closeEntry();
} catch (Exception ex) {
}
```

## Serializable 对象序列化

- 对象序列化机制：允许把内存中的对象转换成平台无关的二进制流，持久地保存在磁盘或网络。任何实现Serializable接口的对象都可被转化为字节数据，在保存和传输时可被还原。

> 序列化是 RMI过程（Remote Method Invoke 远程方法调用）的参数和返回值都必须实现的机制，而 RMI 是 JavaEE 的基础。

| 操作     | 对象流             | 基本流           |
| -------- | ------------------ | ---------------- |
| 序列化   | ObjectOutputStream | DataInputStream  |
| 反序列化 | ObjectInputStream  | DataOutputStream |

> 对象流都实现了DataInput、DataOutput接口。

### transient 瞬态

| 关键字    | 不能序列化static、transient修饰的成员变量 |
| --------- | ----------------------------------------- |
| static    | 类成员，反序列化时会被变成默认值          |
| transient | 瞬态，不允许序列化                        |

1. 变量被transient修饰，变量将不会被序列化
2. transient关键字只能修饰变量，而不能修饰方法和类。
3. 被static关键字修饰的变量不参与序列化，一个静态static变量不管是否被transient修饰，均不能被序列化。
4. final变量值参与序列化。如果final transient同时修饰变量，final不会影响transient，一样不会参与序列化。

### 对象流 （Serializable 标识接口）

| 对象流               | ObjectInputStream                 | ObjectOutputStream                   |
| -------------------- | --------------------------------- | ------------------------------------ |
| 构造器               | ObjectInputStream(InputStream in) | ObjectOutputStream(OutputStream out) |
| 序列化<br />反序列化 | readObject()                      | writeObject(Object obj)              |

- 可序列化：如果需要让某个对象支持序列化机制（Serializable、Externalizable接口），则必须让对象所属的类及其内部的成员属性是可序列化的（若某个类实现了 Serializable 接口，该类的对象就是可序列化的），否则NotSerializableException。

```java
//实现Serializable接口
public class Person implements Serializable {
    public static final long serialVersionUID = 1054860443L;
}
```

- serialVersionUID（序列化版本标识符）：表明类的不同版本间的兼容性、代替内存地址。对序列化对象进行版本控制，有关各版本反序列化时是否兼容。如果没有显示定义serialVersionUID ，则其值是Java运行时环境根据类的内部细节自动生成的，此时若类的实例变量做了修改，serialVersionUID 可能发生变化，在反序列化时很可能出错。

- 版本一致性：运行时判断类的serialVersionUID来验证版本一致性。在进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地相应实体类的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常(InvalidCastException)。

<img src="../../pictures/482332600239578.png" width="540"/>   

```java
try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Person01.person"))){
    oos.writeObject(new Person("Jac", 20));
    oos.flush();
} catch (IOException e) {
}

try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Person01.person")){
    Person p1 = (Person) ois.readObject();
} catch (IOException e) {
} catch (ClassNotFoundException e) {
}
```

#### 序列化的方法签名

- 方法签名：可序列化的类（实现Serializable接口）中定义如下的方法后，数据域就不会被自动序列化，而是调用如下方法。

```java
private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException;
private void writeObject(ObjectOutputStream out) throws IOException,ClassNotFoundException;
```

| 方法                 | 说明                                                         |
| -------------------- | ------------------------------------------------------------ |
| defaultWriteObject() | 获取对象描述符、String域label。<br />属于ObjectWriteOutputStream，只能在可序列化的writeObject()中调用。 |
| defaultReadObject()  | 获取对象描述符、String域label。<br />属于ObjectReadOutputStream，只能在可序列化的readObject()中调用。 |

```java
public class Ingredient implements Serializable {
    
    private final long serialVersionUID = 10086;

    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{
        in.defaultReadObject();
        System.out.println("readObject");
    }

    private void writeObject(ObjectOutputStream out) throws IOException,ClassNotFoundException{
        out.defaultWriteObject();
        System.out.println("outObject");
    }
    
}
```

#### 序列化单例（深拷贝）readResolve()

- 即使构造器是私有的，序列化机制也能创建新的对象（这对单例模式不合适）。将对象序列化到输出流之后，读回的新对象就是对现有对象的一次深拷贝（使用enum结构则无需担心这点）。
- readResolve()：对象被序列化后调用，返回一个对象，该对象之后会成为readObject()的返回值。（避免产生新的对象影响单例模式的类）

```java
//该类实现了Serializable
protected Object readResolve() throws ObjectStreamException {
    if (value == 1) return Type.SAUCE;
    throw new ObjectStreamException(){};
}
```

### Externalizable 保存和恢复对象数据

- 实现Externalizable接口，接口方法对包括超类数据在内的整个对象的存储和恢复负全责。对象重建时，使用公共无参构造器创建实例，然后调用readExternal()方法，从ObjectInputStream读取数据来还原对象。

> 当一个类同时实现Serializable和Externalizable时，只能通过Externalizable的方法来完成对对象数据的操作，而不会自动调用readObject()和writeObject()。

| 机制           | 方法                                | 说明                               |
| -------------- | ----------------------------------- | ---------------------------------- |
| Serializable   | readObject()<br />writeObject()     | 私有，只能被序列化机制调用。       |
| Externalizable | readExternal()<br />writeExternal() | 公有，潜在允许修改现有对象的状态。 |

```java
public class Ingredient implements Externalizable{

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(name);
        out.writeObject(type);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readUTF();
        name = in.readUTF();
        type = (Ingredient.Type)in.readObject();
    }
    
    public Ingredient(){};
}
```

### 对象序列化文件

# NIO （Non-Blocking IO）

- Java NIO (New IO、Non-Blocking IO)：支持面向缓冲区的、基于通道的IO操作。

> IO是面向流的。

| 对比 |         IO         |   NIO   |
| ---- | :----------------: | :-----: |
| 数据 | byte[]<br />char[] | Buffer  |
| 面向 |       Stream       | Channel |

<img src="../../pictures/Snipaste_2023-05-29_10-13-31.png" width="1300"/> 

> java.nio.channels.Channel
>
> | 类型            | 类/接口                                                    |
> | --------------- | ---------------------------------------------------------- |
> | 标准输入输出NIO | FileChannel                                                |
> | 网络编程NIO     | SocketChannel<br />ServerSocketCannel<br />DatagramChannel |

## Path

- Path：代表一个平台无关的路径，描述目录结构中文件的位置，实际引用的资源可以不存在。

| Paths | 返回Path对象             |
| ----- | ------------------------ |
| get() | 静态，指定（抽象）路径。 |

| Path             | 说明                                                         |
| ---------------- | ------------------------------------------------------------ |
| resolve()        | 若参数是绝对路径，则返回该参数。<br />否则，返回调用者（this）和该参数的拼接路径。 |
| resolveSibling() | 若参数是绝对路径，则返回该参数。<br />否则，返回调用者（this）的parentPath和该参数的拼接路径。 |
| relativize()     | 返回调用者（this）相对该参数的相对路径。                     |
| toAbsolutePath() | 返回与该路径等价的绝对路径。                                 |
| getParent()      | 返回父路径。<br />若不存在，则返回null。                     |
| getFileName()    | 返回该路径的最后一个部件。<br />若不存在，则返回null。       |
| getRoot()        | 返回该路径的根目录。<br />若不存在，则返回null。             |
| normalize()      | 移除冗余的路径部件。                                         |

## Files

- java.nio.file.Files：操作文件、目录的工具类。

| 来源  | 转换方法 | 返回                         |
| ----- | -------- | ---------------------------- |
| Path  | toFile() | 基于该路径创建一个File对象。 |
| Files | toPath() | 基于该文件创建一个Path对象。 |

### 标准选项

| StandardOpenOption     | 搭配writer()、new(In/Out)putStream()、newBuffered(Reader/Writer)() |
| ---------------------- | ------------------------------------------------------------ |
| READ                   | 用于读取而打开。                                             |
| WRITER                 | 用于写入而打开。                                             |
| APPEND                 | 若用于写入而打开，则执行追加。                               |
| TRUNCATE_EXISTING      | 若用于写入而打开，则移除已有内容。                           |
| CREATE_NEW             | 创建新文件，若文件已经存在，则创建失败。                     |
| CREATE                 | 文件不存在时，自动创建新文件。                               |
| DELETE_ON_CLOSE        | 当文件被关闭，尽力删除该文件。                               |
| SPARSE                 | 提示文件系统该文件是稀疏的。                                 |
| DSYN<br />SYN          | 要求对文件数据的每次更新都必须同步写入到存储。<br />要求对文件数据、元数据的每次更新都必须同步写入到存储。 |
| **StandardCopyOption** | **搭配copy()、move()**                                       |
| ATOMIC_MOVE            | 原子性移动文件。                                             |
| COPY_ATTRIBUTES        | 复制文件的属性。                                             |
| REPLACE_EXISTING       | 若目标已存在，则替换之。                                     |
| **LinkOption**         | **搭配以上所有方法、以及exists、isDirectory、isRegularFile等** |
| NOFOLLOW_LINKS         | 不跟踪符号链接。                                             |
| **FileVisitOption**    | **搭配find、walk、walkFileTree**                             |
| FOLLOW_LINKS           | 跟踪符号链接。                                               |

### 文件操作

| Files                                        | 可搭配文件操作标准选项                                       |
| -------------------------------------------- | ------------------------------------------------------------ |
| **读写**                                     | **说明**                                                     |
| readAllBytes()                               | 返回`byte[]`，读取文件中的所有数据。                         |
| readAllLines()                               | 返回List，以行序列读取文件。                                 |
| write()                                      | 写出到文件（覆盖）。（数组、可迭代数据的写出）<br />StandardOpenOption.APPEND：追加。 |
| **流式**                                     | **说明**                                                     |
| newInputStream()<br />newOutputStream()      | 字节流                                                       |
| newBufferedReader()<br />newBufferedWriter() | 字符流                                                       |
| **创建**                                     | **说明**                                                     |
| createDirectory()<br />createDirectorys()    | 创建新目录（父路径必须存在）。<br />创建新目录（自动创建中间目录）。 |
| createFile()                                 | 创建空文件，此时，其他程序无法执行文件创建<br />若文件已经存在，则抛出异常。 |
| createTempDirectory()<br />createTempFile()  | 指定位置创建临时文件、目录。<br />prefix：前缀，null则只有序列号做为前缀。<br />suffix：后缀，null则".tmp"。 |
| **复制、移动、删除**                         | **说明**                                                     |
| copy(from, to)                               | 复制，若目标路径已经存在，则失败。<br />选项：StandardCopyOption。 |
| move(from, to)                               | 移动，若目标路径已经存在，则失败。<br />选项：StandardCopyOption。 |
| delete()<br />deleteIfExists()               | 删除，若不存在，则抛出异常。<br />若存在，则删除。           |

```java
Path from = Paths.get("/home/zjk/IdeaProjects/untitled/test.txt");
Path to = from.resolveSibling("target01.txt");
Files.write(to,Files.readAllBytes(from), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
Files.createTempFile(from.getParent(),"temp001",".txt");
Files.copy(from,Paths.get("test_copy.txt"), StandardCopyOption.REPLACE_EXISTING);
Files.deleteIfExists(to);
Files.move(from,from.resolveSibling("test_move.txt"),StandardCopyOption.REPLACE_EXISTING);
```

### 文件信息 BasicFileAttributes

| Files                   | 说明                                                         |
| ----------------------- | ------------------------------------------------------------ |
| getOwner()              | 返回文件的拥有者（UserPrincipal）。                          |
| readAttributes()        | 读取指定类型的文件信息。<br />type：指定返回的文件信息类型(class)。 |
| **BasicFileAttributes** | **基本属性集**                                               |
| **PosixFileAttributes** | **可移植操作系统接口的属性集（Unix）**                       |

- 所有的文件系统都会返回一个基本属性集，被封装在BasicFileAttributes接口，包括：文件创建/最后一次访问/修改的时间（FileTime）、文件类型（常规文件、目录、符号链接、其他）、文件尺寸、文件主键。
- 若文件系统兼容POSIX，则可获取PosixFileAttributes，包括：以上所有信息、组拥有者、文件拥有者、组、访问权限。

```java
Path path = Paths.get("test_move.txt");
PosixFileAttributes posixFileAttributes = Files.readAttributes(path, PosixFileAttributes.class);
UserPrincipal owner = Files.getOwner(path);
```

### 目录操作

#### 目录流

| Files                | 说明                                                         |
| -------------------- | ------------------------------------------------------------ |
| **访问目录项**       | **返回Stream**                                               |
| list()               | 读取目录中每一项的`Stream<Path>`对象。<br />不会进入子目录中。 |
| walk()               | 只有遍历的项是目录，就在进入该目录之前访问其兄弟项。<br />depth：限制访问树的深度。 |
| find()               | 读取目录每一项的同时，执行过滤。<br />type：BasicFileAttributes过滤条件。 |
| **目录流**           | **返回Iterable，而不是Stream**                               |
| newDirectoryStream() | 产生一个DirectoryStream（Iterable子接口，而不是Stream），遍历目录。<br />glob：Glob模式过滤文件。 |

| Glob模式 | 匹配                       |
| -------- | -------------------------- |
| `*`      | 路径中`0~n`个字符          |
| `**`     | 跨目录边界的`0~n`个字符    |
| `?`      | 一个字符                   |
| `[..]`   | 一个字符集合               |
| `{..}`   | 由逗号隔开的多个可选项之一 |

```java
System.out.println("总文件数量：" +
                   Files.walk(Paths.get(""), 3, FileVisitOption.FOLLOW_LINKS)
                   .sorted()
                   .peek(p -> {
                       String name = p.getFileName().toString();
                       Matcher matcher = Pattern.compile("/").matcher(p.toString());
                       while (matcher.find()) {
                           name = "\t".concat(name);
                       }
                       System.out.println(name);
                   }).count());
```

#### FileVisitor 访问器（walkFileTree()）

| Files                     | walkFileTree()：访问指定目录的所有子孙成员。<br />fv：FileVisitor对象。<br />以下方法操作导致的异常均由该方法抛出。 |
| ------------------------- | ------------------------------------------------------------ |
| **FileVisitor通知**       | **返回FileVisitResult**                                      |
| visitFile()               | 遇到一个文件/目录。                                          |
| preVisitDirectory()       | 目录被处理前。                                               |
| postVisitDirectory()      | 目录被处理后。                                               |
| visitFileFailed()         | 试图访问文件/目录时发生错误。                                |
| **FileVisitorResult操作** | **对于通知的每种情况，都可以指定是否希望执行以下操作。**     |
| `CONTINUE`                | 继续访问下一个文件。                                         |
| `SKIP_SUSBTREE`           | 继续访问，但不再访问该目录下的任何项。                       |
| `SKIP_SIBLINGS`           | 继续访问，但不在访问该文件的兄弟文件。                       |
| `TERMINATE`               | 终止访问。                                                   |

- SimpleFileVisitor：FileVisitor的简单实现类，其除了visitFileFailed()之外的所有方法都默认不做任何操作而继续访问，而visitFileFailed()会抛出由失败导致的异常并终止访问。

> 删除目录时，需要先删除目录内的所有内容（包括子目录），而postVisitDirectory()可以在搭配其余方法后，轻松实现对文件树的全部删除。（其他方式一般需要复杂的操作）

```java
Files.walkFileTree(Paths.get("testDir"), new SimpleFileVisitor<>() {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Files.delete(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("prepare to visit " + dir + ".");
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        if (exc != null) System.out.println("After visit " + dir + " happen Exception.");
        Files.delete(dir);
        System.out.println(dir + " has deleted.");
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        if (exc != null) System.out.println("visit " + file + " failed.");
        return FileVisitResult.CONTINUE;
    }
});
```

## FileSystems 文件系统（ZIP）

| FileSystems     | 文件系统                                                     |
| --------------- | ------------------------------------------------------------ |
| newFileSystem() | 对所安装的文件系统提供者进行迭代，返回第一个可以接受指定路径的文件系统。<br />默认ZIP文件系统是有一个提供者的，接受名字以`.zip、.jar`结尾的文件。<br />loader：若不为null，则迭代指定的类加载器能够加载的文件系统。 |
| **FileSystem**  | **文件系统**                                                 |
| getPath()       | 拼接指定的字符串。                                           |

```java
FileSystem fs = FileSystems.newFileSystem(Paths.get("test.zip"), ClassLoader.getPlatformClassLoader());
Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<>() {
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file);
        return FileVisitResult.CONTINUE;
    }
});
```

## FileChannel 内存映射文件

| FileChannel            | 内存映射文件：从文件中获取一个通道（抽象的磁盘文件）         |
| ---------------------- | ------------------------------------------------------------ |
| open()                 | 静态，返回指定文件的FileChannel。<br />StandardOpenOption选项。 |
| map()                  | 指定映射文件和映射模式获取ByteBuffer（MappedByteBuffer）。<br />缓冲区支持顺序和随机数据访问。<br />FileChannel.Mapmode指定映射模式。 |
| **ByteBuffer**         | **缓冲区：ByteBuffer、Buffer**                               |
| get()<br />getXxx()    | 读取数据，并向下移动一个字节。<br />index：从指定索引处获取一个字节。 |
| put()<br />putXxx()    | 写入数据，并向下移动一个字节，并返回该缓冲区的引用。<br />index：向指定索引处推入一个字节。 |
| order()                | 返回字节顺序（ByteOrder）。<br />order：设置字节顺序，ByteOrder（`BIG_ENDIAN、LITTLE_ENDIAN`）。 |
| hasRemaining()         | 若当前缓冲区位置没有到达界限，则返回true。                   |
| limit()                | 返回当前缓冲区的界限（第一个没有可用值的位置）。             |
| allocate()<br />wrap() | 静态，构建指定容量的缓冲区。<br />静态，构建指定容量的缓冲区（对指定数组的包装）。 |
| asCharBuffer()         | 构建字符缓冲区（对缓冲区的包装，从当前缓冲区的位置开始构造）。<br />对该字符缓冲区（有自己的位置、界限、标记）的变更都在该缓冲区中反映。 |
| **CharBuffer**         | **字符缓冲区**                                               |
| get()                  | 若方法有参数，则返回该字符缓冲区。<br />从该字符缓冲区的当前位置开始，获取一个/范围内所有char，之后将位置向前移动越过所有读入的字符。 |
| put()                  | 返回该字符缓冲区。<br />从该字符缓冲区的当前位置开始，放置一个/范围内所有char，之后将位置向前移动越过所有被写出的字符。 |
| **CRC32**              | **校验和**                                                   |
| update()               | 传入一个字节，并更新该检验和。                               |
| getValue()             | 返回检验和。                                                 |

| 映射模式（FileChannel.Mapmode）  | 需要FileChanel也打开相应的权限（StandardOpenOption） |
| -------------------------------- | ---------------------------------------------------- |
| `FileChannel.MapModeREAD_ONLY`   | 缓冲区只读。                                         |
| `FileChannel.MapMode.READ_WRITE` | 缓冲区可写，修改在某个时刻写回文件。                 |
| `FileChannel.MapMode.PRIVATE`    | 缓冲区可写，修改是私有的，不会传播到文件。           |

```java
try (FileChannel channel = FileChannel.open(Paths.get("target01.txt"), StandardOpenOption.READ,StandardOpenOption.WRITE)) {
    MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, (int) channel.size());
    CRC32 crc32 = new CRC32();
    while (buffer.hasRemaining()) {
        int c = buffer.get();
        crc32.update(c);
    }
    System.out.println(Long.toHexString(crc32.getValue()));
} catch (Exception ex) {
    ex.printStackTrace();
}
```

### Buffer 缓冲区

- 缓冲区（Buffer）：由具有相同类型的数值构成的数组。

​	<img src="../../pictures/Java-Buffer-struct.drawio.svg" width="700"/>

| Buffer      | 缓冲区                                                       |
| ----------- | ------------------------------------------------------------ |
| clear()     | 将位置复位到0，并将界限设置到容量，为缓冲区写出准备。<br />返回当前缓冲区。 |
| flip()      | 将界限设置到位置，并将位置复位到0，为缓冲区读入准备。<br />返回当前缓冲区。 |
| rewind()    | 将读写位置复位到0，并保持界限不变，为缓冲区重新读入相同的值准备。<br />返回当前缓冲区。 |
| mark()      | 将缓冲区的标记设置到读写位置。<br />返回当前缓冲区。         |
| reset()     | 将缓冲区的位置设置到标记，允许被标记的部分可以再次被读入/写出。<br />返回当前缓冲区。 |
| remaining() | 返回剩余可读入/写出的值的数量（界限 - 位置）。               |
| position()  | 返回该缓冲区的位置。                                         |
| capacity()  | 返回该缓冲区的容量。                                         |

```java
try (FileChannel channel = FileChannel.open(Paths.get("target01.txt"), StandardOpenOption.READ,StandardOpenOption.WRITE)) {
    MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, (int) channel.size());
    channel.read(buffer);
    channel.position(1);
    buffer.flip();
    channel.write(buffer);
} catch (Exception ex) {
    ex.printStackTrace();
}
```

### FileLock 文件加锁机制

| FilChannel   | 说明                                                         |
| ------------ | ------------------------------------------------------------ |
| lock()       | 返回FileLock，在整个文件获得独占的锁，若无法获得，则阻塞直至获得锁。<br />position：指定文件区域获得锁。<br />shared：是否共享锁。 |
| tryLock()    | 返回FileLock，在整个文件获得独占的锁（阻塞该方法），若无法获得，则返回null。<br />position、size：指定文件区域获得锁。<br />shared：是否共享锁。<br />false，默认，锁定文件用于读写（独占）。<br />true，表明共享锁，允许多个进程从该文件读入，并阻止任何进程获得独占的锁。 |
| **FileLock** | **说明**                                                     |
| close()      | 释放锁。<br />实现了AutoCloseable接口，可通过try自动释放。   |
| isShared()   | 查询持有的锁类型。                                           |

- 若锁定了文件的尾部，而该文件的长度随后增长超过了锁定的部分，则增长的额外区域是未锁定的。
- 文件加锁机制依赖于操作系统。

```java
try (FileChannel channel = FileChannel.open(Paths.get("target01.txt"), StandardOpenOption.READ, StandardOpenOption.WRITE);
     FileLock lock = channel.lock()) {
    System.out.println(lock.isShared());
} catch (Exception ex) {
    ex.printStackTrace();
}
```

# 
