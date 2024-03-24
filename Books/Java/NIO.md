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

# Path

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

# Files

- java.nio.file.Files：操作文件、目录的工具类。

| 来源  | 转换方法 | 返回                         |
| ----- | -------- | ---------------------------- |
| Path  | toFile() | 基于该路径创建一个File对象。 |
| Files | toPath() | 基于该文件创建一个Path对象。 |

## 标准选项

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

## 文件操作

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

## 文件信息 BasicFileAttributes

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

## 目录操作

### 目录流

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

### FileVisitor 访问器（walkFileTree()）

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

# FileSystems 文件系统（ZIP）

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

# FileChannel 内存映射文件

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

## Buffer 缓冲区

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

## FileLock 文件加锁机制

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
