

| 领域类注解                | 说明                                                         |
| ------------------------- | ------------------------------------------------------------ |
| @Entity                   | 声明JPA实体（类）。<br />（JPA需要实体带有空参构造器）       |
| @Id                       | 对象的唯一标识属性。<br />（javax.persistence，而不是org.springframwork.data.annotation） |
| @GeneratedValue(strategy) | 生成ID值。<br />strategy：生成策略。                         |
| @ManyToMany()             | 该属性（对应的类型）和类是多对多的关系。                     |
| @OneToMany(cascade)       | 所有的该属性（对应的类型）都属于该类（一对多）。<br />cascade：级联范围。 |

