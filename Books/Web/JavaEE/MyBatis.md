# MyBatis介绍

## 持久层框架

[mybatis](https://mybatis.net.cn)

- MyBatis是持久层(DAO层)框架、ORM框架。

**持久层**

- 负责将数据保持到数据库的那层代码。
- JavaEE三层架构：表现层、业务层、持久层。

## IDEA配置与数据库的连接

- 解决SQL映射文件的警告

<img src="../../../pictures/Snipaste_2023-04-01_10-31-59.png" width="800"/> 

# MyBatis工作流程

```java
String resource = "mybatis-config.xml";
InputStream inputStream = Resources.getResourceAsStream(resource);
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuild().build(inputStream);
SqlSesison sqlSession = sqlSessionFactory.openSession();

UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
List<User> userList = userMapper.selectAllUser();

sqlSession.close();
```

1. 读取主配置文件mybatis-config.xml，获得运行环境和数据库连接。
2. 加载SQL映射文件Mapper.xml。
3. 根据主配置文件，SqlSessionFactoryBuild对象创建SqlSessionFactory对象。
4. 由SqlSessionFactory对象创建SqlSession对象，通过SqlSession对象进行CRUD操作（或者通过代理开发sqlSession.getMapper()进行CRUP操作的调用）。
5. Executor接口操作数据库。
6. 对输入参数进行映射，在执行SQL语句前，将输入的Java对象映射到SQL语句中。
7. 将输出结果映射为Java对象

# 核心配置文件 mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    
</configuration>
```

- configuration（配置）
  - properties（属性）
  - settings（设置）
  - typeAliases（类型别名）
  - typeHandlers（类型处理器）
  - objectFactory（对象工厂）
  - plugins（插件）
  - environments（环境配置）
    - environment（环境变量）
      - transactionManager（事务管理器）
      - dataSource（数据源）
  - databaseIdProvider（数据库厂商标识）
  - mappers（映射器）

## properties

```xml
<properties resource="jdbc.properties"/>
```

## setting

## enviroments

```xml
<environments default="development">
    <!--配置数据库的环境连接信息-->
    <!--通过不同的default属性来切换不同id的environment-->
    <environment id="development"> <!--对应的数据库环境-->
        <transactionManager type="JDBC"/>
        <dataSource type="POOLED"> <!--数据库连接池-->
            <property name="driver" value="com.mysql.jdbc.Driver"/>
            <property name="url" value="jdbc:mysql://127.0.0.1:3306/db1?useSSL=false"/>
            <property name="username" value="root"/>
            <property name="password" value="tiger"/>
        </dataSource>
    </environment>
</environments> 
```

## typeAliases

- 在核心配置文件mybatis-config.xml中设置`<typeAliases>` 后，在SQL映射文件Mapper.xml中可以直接使用类名（不区分大小写），而不用写全限定名。

```java
<typeAliases>
    <package name="pojo类的包"/> 包扫描的方式
</typeAliases>
```

## mappers

- 只加载单个SQL映射文件：

```xml
<mappers>
    <mapper resource="com\zjk\mapper\UserMapper.xml"/>
</mappers>
```

- 包扫描SQL映射文件

```xml
<mappers>
    <package name="com.zjk.mapper"/>
</mappers>
```

# SQL映射文件 Mapper.xml

## Mapper代理开发 

**Mapper.xml和Mapper接口**

1. 定义**与SQL映射文件同名的Mapper接口**，并且将Mapper接口和SQL映射文件放置在同一目录下。
   - 如果Mapper接口名称和SQL映射文件名称相同，并在同一目录下(java和resouces建立相同的目录)，则核心配置文件mybatis-config.xml可以使用 **包扫描** 的方式简化SQL映射文件的加载。
2. 设置SQL映射文件的 **nameSpace** 属性为Mapper接口全限定名。
3. 在Mapper接口中定义方法，**方法名就是SQL映射文件中sql语句的id** ，并保持参数类型和返回值类型一致。
4. 通过SqlSession对象的getMapper(UserMapper.class)方法来获取Mapper接口的对象，进行查询操作。

```java
UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
List<User> list = userMapper.selectAll(); 
```

**设置Mapper接口(java中的com.zjk.mapper)和SQL映射文件(resouce中的com/zjk/mapper)**

- SQL映射文件：UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace 对应的UserMapper接口的全限定名-->
<mapper namespace="com.zjk.mapper.UserMapper">  
    <select id="selectAllUser" resultType="com.zjk.pojo.User">
        SELECT *
        FROM tb_user;
    </select>
</mapper>   
```

- UserMapper接口

```java
package com.zjk.mapper;

import com.zjk.pojo.User;

import java.util.List;

public interface UserMapper {
    public List<User> selectAllUser(); //该方法名为SQL映射文件中对应的id
}
```

- Mybatis配置文件 mybatis-config.xml：扫描SQL映射文件。

```xml
<mappers>
    <package name="com.zjk.mapper"/>
</mappers>
```

## 参数

### 属性名称不一致 

**数据库表的字段名称和实体类的属性名称不一样，则不能自动封装数据**

#### SQL片段

- 在SQL语句中为字段起与实体类的属性名称相同的别名。
- sql片段
  - 定义： `<sql id="片段id1">sql语句片段</sql>`
  - 调用：在sql语句中插入`<include refid="片段id1" />`

#### resultMap

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.zjk.mapper.BrandMapper">
    <!--<resultMap id="resultMap的ID" type="对应类型">-->
    <resultMap id="brandResultMap" type="brand">
        <!--<result column="表中的列名" property="对应类型的相应属性名"/>-->
        <result column="brand_name" property="brandName"></result>
        <result column="company_name" property="companyName"></result>
    </resultMap>
    <!--<select id="selectAll" resultMap="resultMap的ID">-->
    <select id="selectAll" resultMap="brandResultMap">
        SELECT *
        FROM tb_brand;
    </select>
</mapper>
```

### 参数占位符 `#{} ${}` 特殊字符处理 `CDATA`

**sql语句的参数占位符**

- `#{}` 相当于?
  - 参数传递时使用 如`#{id}`
- `${}` 拼sql。存在SQL注入问题
  - 表名或列名不固定时使用

**参数类型 parameterType 可以忽略**

**特殊字符的处理**

- 转义字符；如：`&lt;`=`<`等
- CDATA区：`<![CDATA[特殊字符]]>`

```xml
<select id="selectById" resultMap="brandResultMap">
    SELECT *
    FROM tb_brand
    WHERE id = #{id};
</select>
<select id="selectByIdIn" resultMap="brandResultMap">
    SELECT *
    FROM tb_brand
    WHERE id <![CDATA[ <= ]]> #{id};
</select>
```

### 多参数接收

- mapper.xml配置

```xml
<select id="selectByCondition" resultMap="brandResultMap">
    SELECT *
    FROM tb_brand
    WHERE status = #{status} 尽量在#{}内使用相应类型的对象属性名
    AND brand_name like #{brandName}
    AND company_name like #{companyName};
</select>
```

- BrandMapper接口

```xml
//散装参数 @Param("")
List<Brand> selectByCondition(@Param("status") int status, @Param("brandName") String brandName, @Param("companyName") String companyName);
//对象参数
List<Brand> selectByCondition(Brand brand);
//map集合参数
List<Brand> selectByCondition(Map map);
```

- test

```java
@Test
public void test03() throws Exception {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();

    BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

    //1.散装参数
//        List<Brand> brandList = brandMapper.selectByCondition(1, "%华为%", "%华为%");
    //2.对象参数
//        Brand brand = new Brand();
//        brand.setStatus(1);
//        brand.setBrandName("%华为%");
//        brand.setCompanyName("%华为%");
//        List<Brand> brandList = brandMapper.selectByCondition(brand);
    //3.map参数
    Map map = new HashMap();
    map.put("status",1);
    map.put("brandName","%华为%");
    map.put("companyName","%华为%");
    List<Brand> brandList = brandMapper.selectByCondition(map);

    System.out.println(Arrays.asList(brandList));
}
```

#### 散装参数 @Param

- 如果方法中有多个参数，需要使用`@Param`来指定。

```java
返回值类型 方法名(@Param("sql参数占位符名称")  int id);
```

#### 对象参数

- 传入设置好相应属性的对象。
- 对象属性的名称必须和sql参数占位符内的名称相一致（resultMap）。

#### map集合参数

- 传入一个`Map map = new HashMap();`
- 在map内put放入相应的参数和属性值。
- 需要保证sql参数占位符的参数名和map集合的key键对应，map集合的value值即为需要传入的属性值。

### 参数传递

- MyBatis接口方法中可以接收各种各样的参数，MyBatis底层对这些参数进行不同的封装处理方式。

- MyBatis提供了ParamNameResolver类来进行参数封装。

#### 单个参数

1. POJO类型：直接使用，属性名和sql参数占位符名称相一致即可
2. Map集合：直接使用，键名和sql参数占位符名称相一致即可
3. Collection：封装为Map集合 可以使用@Param注解，替换Map集合中默认的arg键名
   - map.put("arg0", collection集合);
   - map.put("collection", collection集合);
4. List：封装为Map集合 可以使用@Param注解，替换Map集合中默认的arg键名
   - map.put("arg0", list集合);
   - map.put("collection", list集合);
   - map.put("list", list集合);
5. Array：封装为Map集合 可以使用@Param注解，替换Map集合中默认的arg键名
   - map.put("arg0", 数组);
   - map.put("array", 数组);
6. 其他类型：直接使用

#### 多个参数

- 封装为Map集合，可以使用@Param注解，替换Map集合中默认的arg键名

- 默认：arg0、praram1为键分别开始存放值

```java
map.put("arg0",值1);
map.put("arg1",值2);
map.put("param1",值1);
map.put("param2",值2);
```

- 在mapper接口的方法中使用@Param注解替换：`返回值类型 方法名(@Param("id") 参数类型 参数1,@Param("name") 参数类型 参数2)`
- 其中的id和name均为mapper.xml文件中的相应的`#{}`的内容
- 此时修改后的键名为：param1~n、id、name

```java
map.put("id",值1);
map.put("name",值2);
map.put("param1",值1);
map.put("param2",值2);
```

**应用**

- mapper接口

```java
List<Brand> selectByCondition(@Param("status") int status, @Param("brandName") String brandName, @Param("companyName") String companyName);
```

- mapper.xml配置

```xml
<select id="selectByCondition" resultMap="brandResultMap">
    SELECT *
    FROM tb_brand
    WHERE status = #{status} 尽量在#{}内使用相应类型的对象属性名
    AND brand_name like #{brandName}
    AND company_name like #{companyName};
</select>
```

## 动态SQL

[动态 SQL_MyBatis中文网](https://mybatis.net.cn/dynamic-sql.html)

- sql语句会随用户输入或外界条件的变化而变化

**主要语法**

- if
- choose (when, otherwise)
- trim (where, set)
- foreach

### if 条件判断

- 注意：如果实体类的属性有基本数据类型，要小心成为默认值。

```xml
<if test="逻辑表达式">  该逻辑表达式中的变量为#{}内的参数名称
    部分sql语句
</if>
```

**在语法格式的处理上**：对于不满足条件而造成一些特殊sql语句的报错，如：对于WHERE子句内多条件判断的and子句的错误

- 在sql语句上修改：`WHERE 1 = 1` 作为WHERE语句的第一条，避免第一条WHERE子句的特殊性。
- 使用Mybatis的`<where>`标签来替代sql语句中的WHERE子句。

```java
<select id="selectByCondition" resultMap="brandResultMap">
    SELECT *
    FROM tb_brand
    WHERE 1 = 1
        <if test="status != null">
            AND status = #{status}
        </if>
        <if test="brandName != null and brandName != ''">
            AND brand_name like #{brandName}
        </if>
        <if test="companyName != null and companyName != ''">
            AND company_name like #{companyName}
        </if>
</select>
```

```java
<select id="selectByCondition" resultMap="brandResultMap">
    SELECT *
    FROM tb_brand
    <where>
        <if test="status != null">
            status = #{status}
        </if>
        <if test="brandName != null and brandName != ''">
            AND brand_name like #{brandName}
        </if>
        <if test="companyName != null and companyName != ''">
            AND company_name like #{companyName}
        </if>
    </where>
</select>
```

### choose 单项选择

- 在choose中只会有一个满足条件的部分sql语句生效。类似于SQL中CASE-WHEN语句。
- 当所有when的条件都满足时，只会选择第一个满足的when。
  - 注意：如果实体类的属性有基本数据类型，要小心成为默认值。

```xml
<choose>
    <when test="逻辑表达式">
        部分sql语句
    </when>
    <when test="逻辑表达式">
        部分sql语句
    </when>
    <otherwise>  可以省略
        部分sql语句
    </otherwise>
</choose>
```

```xml
<select id="selectByConditionSingle" resultMap="brandResultMap">
    SELECT *
    FROM tb_brand
    WHERE
    <choose>
        <when test="status != null">
            status = #{status}
        </when>
        <when test="companyName != null and companyName != ''">
            company_name like #{companyName}
        </when>
        <when test="brandName != null and brandName != ''">
            brand_name like #{brandName}
        </when>
        <otherwise>
            1 = 1
        </otherwise>
    </choose>
</select>
```

### foreach

```xml
<foreach collection="ids" item="id" separator="," open="(" close=")">
    collection 传入的数组 | item 数组内的属性 | separator 分隔符 | open\close 开始\结束符
    #{id}
</foreach>
```

**应用**

- mapper.xml配置

```xml
<delete id="deleteByIds">
    DELETE FROM tb_brand
    WHERE id IN
    <foreach collection="ids" item="id" separator="," open="(" close=")">
           collection 传入的数组 | item 数组内的属性 | separator 分隔符 | open\close 开始\结束符
        #{id}
    </foreach>
</delete>
```

- mapper接口

```java
int deleteByIds(@Param("ids") int[] ids); 
//MyBatis默认将数组参数封装为一个Map集合，默认为array = 数组
//可以通过@Param("新的key")的方式来将array修改为新的key，在<foreach>中的collection使用
```


### trim

- `<where>`的自定义类型`<trim>`

```xml
<trim prefix="WHERE" prefixOverrides="AND |OR ">
    
</trim>
```

- `<set>`的自定义类型`<trim>`

```xml
<trim prefix="SET" suffixOverrides=",">
    
</trim>
```

#### where

- where 元素只会在子元素返回任何内容的情况下才插入 “WHERE” 子句。而且，若子句的开头为 “AND” 或 “OR”，where 元素也会将它们去除。

```xml
<select id="selectByCondition" resultMap="brandResultMap">
    SELECT *
    FROM tb_brand
    <where>
        <if test="status != null">
            status = #{status}
        </if>
        <if test="brandName != null and brandName != ''">
            AND brand_name like #{brandName}
        </if>
        <if test="companyName != null and companyName != ''">
            AND company_name like #{companyName}
        </if>
    </where>
</select>
```

#### set

- set 元素可以用于动态包含需要更新的列，忽略其它不更新的列

## MyBatis事务

- `SqlSession sqlSession = sqlSessionFactory.openSession()` 默认开启事务，需要手动提交：`sqlSession.commit()`
- `SqlSession sqlSession = sqlSessionFactory.openSession(true)` 自动提交事务 

### 插入`<insert>`

```xml
<insert id="mapper接口中对应的方法">
    insert语句
</insert>
```

#### INSERT的主键返回

- 主键返回的值和mapper接口中的返回值无关，需要使用实体对象的getXxx()方法来获取

**应用**

- 配置mapper文件

```xml
useGeneratedKeys="true"
keyProperty="指向要返回的属性值" 返回给相应的实体对象
<insert id="add" useGeneratedKeys="true" keyProperty="id">
    INSERT INTO tb_brand (brand_name, company_name, ordered, description, status)
    VALUES (#{brandName}, #{companyName}, #{ordered}, #{description}, #{status});
</insert>
```

- mapper接口

```java
void add(Brand brand); //主键返回的值和mapper接口中的返回值无关
```

- test

```java
@Test
public void test04() throws Exception {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession(true);

    BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
    Brand brand = new Brand();
    brand.setBrandName("香飘飘");
    brand.setCompanyName("香飘飘有限公司");
    brand.setOrdered(1);
    brand.setDescription("香飘飘，香飘飘~~~");
    brand.setStatus(1);
    mapper.add(brand);
    System.out.println(brand.getId());  //在插入之后，返回了插入的行的id值

    sqlSession.close();
}
```

### 更新 `<update>`

- 返回被影响的行数

**应用**

- 配置mapper.xml

```xml
<update id="update">
    UPDATE tb_brand
    SET brand_name   = #{brandName},
        company_name = #{companyName},
        ordered      = #{ordered},
        description  = #{description},
        status       = #{status}
    WHERE id = #{id};
</update>
```

- mapper接口

```java
int update(Brand brand); //返回被影响的行数
```

#### 更新动态字段 `<set>`

- 自动消除部分sql语句后缀`,`，使得update语句正确。

- 通过`<if>`判断的设置，使得在更新时，不会影响到没有输入的列的属性值。

```xml
<update id="update">
    UPDATE tb_brand
    <set>
        <if test="brandName != null and brandName != ''">
            brand_name = #{brandName},
        </if>
        <if test="companyName != null and brandName !=''">
            company_name = #{companyName},
        </if>
        <if test="ordered != null and ordered != ''">
            ordered = #{ordered},
        </if>
        <if test="description != null and description != ''">
            description=#{description},
        </if>
        <if test="status != null and status != ''">
            status = #{status}
        </if>
    </set>
    WHERE id = #{id};
</update>
```

### 删除 `<delete>`

```xml
<delete id="指定的mapper接口方法">
    sql语句
</delete>
```

**应用**

- mapper.xml配置

```xml
<delete id="deleteById">
    DELETE
    FROM tb_brand
    WHERE id = #{id};
</delete>
```

- mapper接口

```java
int deleteById(int id);
```

#### 批量删除 `<foreach>`

- 传入数组

**应用**

- mapper.xml配置

```xml
<delete id="deleteByIds">
    DELETE FROM tb_brand
    WHERE id IN
    <foreach collection="ids" item="id" separator="," open="(" close=")">
              collection 传入的数组 | item 数组内的属性 | separator 分隔符 | open\close 开始\结束符
        #{id}
    </foreach>
</delete>
```

- mapper接口

```java
int deleteByIds(@Param("ids"))int[] ids); 
//MyBatis默认将数组参数封装为一个Map集合，默认为array = 数组
//可以通过@Param("新的key")的方式来将array修改为新的key，在<foreach>中的collection使用
```


# 注解方式

- 注解开发只适用于简单的语句。
- 省略了配置文件，但复杂的功能还是需要使用配置文件来完成。

**类型**

- @Select 查询
- @Insert 插入
- @Update 更新
- @Delete 删除

**应用**

- mapper接口

```java
@Select("SELECT * FROM tb_user WHERE id = #{id}")
public List<User> selectUserById(int id);
```

