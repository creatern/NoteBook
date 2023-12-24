# JPA概述

```xml
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-jpa</artifactId>
```

| 领域类注解                | 说明                                                         |
| ------------------------- | ------------------------------------------------------------ |
| @Entity                   | 声明JPA实体（类）。<br />（JPA需要实体带有空参构造器）       |
| @Id                       | 对象的唯一标识属性。<br />（javax.persistence，而不是org.springframwork.data.annotation） |
| @GeneratedValue(strategy) | 生成ID值。<br />strategy：生成策略。                         |
| @ManyToMany()             | 该属性（对应的类型）和类是多对多的关系。                     |
| @OneToMany(cascade)       | 所有的该属性（对应的类型）都属于该类（一对多）。<br />cascade：级联范围。 |

```xml
<!--H2DB 嵌入式-->
<groupId>com.h2database</groupId>
<artifactId>h2</artifactId>
<scope>runtime</scope>
```

# Repository 存储库接口

> org.springframework.data.repository.Repository;

- Repository是Spring Data数据库操作的最顶级父类，继承该接口会基于存储库规范接口自动创建存储库，而不需要编写实现类。

<img src="../../pictures/RepositorySystemIdea.png" width="1000"/>

```java
Xxx extends Repository<持久化对象类型,持久化对象ID类型>{}
```

> 数据预加载：CommandLineRunner、ApplicationRunner接口，对关系型、非关系型数据库均有效。

| 常用接口                | 存储库规范接口操作             |
| ----------------------- | ------------------------------ |
| CrudRepository          | 常用的CRUD                     |
| PagingAndSortRepository | 分页、排序                     |
| JpaRepository           | 关系型数据库                   |
| SimpleJpaRepository     | 关系型数据库的Repository实现类 |

## Repository通用

### Sort 排序

```java
Sort sort;
if ("asc".equals(sortOrder) && sortField != null && !sortField.isEmpty()) {
    // Sort.by(排序规则, 实体类属性)
    sort = Sort.by(Sort.Direction.ASC, sortField);
} else if ("desc".equals(sortOrder) && sortField != null && !sortField.isEmpty()) {
    sort = Sort.by(Sort.Direction.DESC, sortField);
} else {
    sort = Sort.by(Sort.Direction.ASC, "id");
}
return sort;
```

### Pageable 分页

- Pageable：分页，可设置Sort参数添加排序要求。

```java
public Pageable pageable(int pageNum, String sortField, String sortOrder) {
    // PageRequest.of(当前所在的页码[从0开始], 每页的行数, 排序规则)
    return PageRequest.of(pageNum, 10, sort(sortField, sortOrder));
}

public Sort sort(String sortField, String sortOrder) {
    Sort sort;
    if ("asc".equals(sortOrder) && sortField != null && !sortField.isEmpty()) {
        sort = Sort.by(Sort.Direction.ASC, sortField);
    } else if ("desc".equals(sortOrder) && sortField != null && !sortField.isEmpty()) {
        sort = Sort.by(Sort.Direction.DESC, sortField);
    } else {
        sort = Sort.by(Sort.Direction.ASC, "id");
    }
    return sort;
}
```

## JpaRepository

<table>
    <tr>
        <td>List findAll(@Nullable Specification spec)</td>
        <td>规范查询，没有数据时返回空列表。</td>
    </tr>
    <tr>
        <td>Page findAll(@Nullable Specification spec, Pageable pageable)</td>
        <td>规范查询，同时进行分页查询。</td>
    </tr>
    <tr>
        <td>List findAll(@Nullable Specification spec, Sort sort)</td>
        <td>规范查询，同时指定排序字段。</td>
    </tr>
    <tr>
        <td>Optional findOne(@Nullable Specification spec)</td>
        <td>规范查询单条数据。注意如果结果多余一条，则抛异常。</td>
    </tr>
</table>

### JpaSpecificationExecutor 规范查询接口

```java
@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, String>, JpaSpecificationExecutor<Appliance> {
}
```

```java
public Specification<Appliance> applianceSpecification(
    String id, String category, String box, String base, String minRequire, String maxRequire) {
    return (root, query, cb) -> {
        List<Predicate> predicateList = new ArrayList<>();
        // predicateList.add(cb.equal(root.get("实体类的属性").as(属性对应的class), 属性参数));
        if (id != null && !"".equals(id)) {
            predicateList.add(cb.equal(root.get("id").as(String.class), id));
        }
        if (category != null && !"".equals(category)) {
            predicateList.add(cb.like(root.get("category").as(String.class), category));//equal(=)
        }
        if (box != null && !"".equals(box)) {
            predicateList.add(cb.like(root.get("box").as(String.class), box));
        }
        if (base != null && !"".equals(base)) {
            predicateList.add(cb.like(root.get("base").as(String.class), base));
        }
        if (minRequire != null && !"".equals(minRequire)) {
            predicateList.add(cb.greaterThanOrEqualTo(root.get("require").as(Integer.class), Integer.valueOf(minRequire)));
        }
        if (maxRequire != null && !"".equals(maxRequire)) {
            predicateList.add(cb.lessThanOrEqualTo(root.get("require").as(Integer.class), Integer.valueOf(maxRequire)));
        }
        // 返回添加谓词后的查询语句
        return query.where(predicateList.toArray(new Predicate[predicateList.size()])).getRestriction();
    };
}

public Page<Appliance> findAppliancesBySpecification(
    int pageNum, String id, String category, String box, String base, String minRequire, String maxRequire,
    String sortFiled, String sortOrder
) {
    return applianceRepository.findAll(applianceSpecification(id, category, box, base, minRequire, maxRequire), pageable(pageNum, sortFiled, sortOrder));
}
```

# 自定义查询方法

| 注解 | @EnableJpaRepositories        |
| ---- | ----------------------------- |
| 位置 | 配置类                        |
| 作用 | 所有仓库的查询策略            |
| 参数 | queryLookupStrategy：查询策略 |

| QueryLookupStrategy\.Key | 查询策略                                         |
| ------------------------ | ------------------------------------------------ |
| Create                   | 直接根据（符合规则的）方法名创建。               |
| USE\_DECLARED\_QUERY     | 声明方式@Query创建。                             |
| CREATE\_IF\_NOT\_FOUND   | 默认，以上两种。先@Query创建，不行则方法名创建。 |

## DQM 方法名创建及语法

- Spring Data在生成存储库实现时，检查存储库接口的所有方法，解析方法的名称，并基于被持久化的对象来试图推测方法的目的（DSL 领域特定语言）。持久化的细节都是通过存储库方法的签名实现的。

```java
public Interface TacoOrder extends CrudRepository{
    //动词+[主题]+关键词By+断言
    findByDeliveryZip(String deliveryZip);//动词find、主题暗含TacoOrder、关键词By、断言DeliveryZip。
}
```

- `org.springframework.data.repository.query.parser.PartTree`：解析方法名称。

- `org.springframework.data.repository.query.parser.Part.Type$Type`：所有关键字的枚举。

## @Query 声明创建

| 注解 | @Query                                         |
| ---- | ---------------------------------------------- |
| 位置 | 查询方法                                       |
| 作用 | 指明方法调用时执行的查询，而不是根据方法签名。 |
| 参数 | SQL查询语句（JPA中可以使用JPA查询）            |

### JPA-QL

```java
@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    @Query("select u from User u where u.account = :account and u.password = :password")
    // User是@Entity标注的实体类
    List<User> findByAccountAndPassword(@Param("account") String account,
                                        @Param("password") String password);
}
```

