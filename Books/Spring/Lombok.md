- Lombok：编译期自动生成类的方法（@Data）。（生成jar、war时自动剔除Lombok）

> IDE中需要安装相应的Lombok插件。

```xml
<groupId>org.projectlombok</groupId>
<artifactId>lombok</artifactId>
```

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <excludes>
                    <exclude>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

| 注解                              | 说明                                                         |
| --------------------------------- | ------------------------------------------------------------ |
| @Data                             | 自动为需要构造器、初始化方法...的创建对应方法。              |
| @Slf4j                            | 自动生成SLF4J Logger静态属性。                               |
| @AllArgsConstructor               | 所有属性的构造器                                             |
| @NoArgsConstructor(access, force) | 空参构造器<br /><br />access：权限修饰<br />force：设置默认值（final属性需要） |