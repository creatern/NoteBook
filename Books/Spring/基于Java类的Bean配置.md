# @Bean

- <code>@Bean</code>  将方法的返回值作为Bean实例注册到Spring容器中。

```java
public @interface Bean {
   @AliasFor("name")
   String[] value() default {};

   @AliasFor("value")
   String[] name() default {};

   boolean autowireCandidate() default true;

   String initMethod() default "";

   String destroyMethod() default "(inferred)";
}
```

1. 默认使用当前方法的名称作为当前Bean实例的beanName，可使用@Bean的value或name属性来指定当前返回的Bean实例的beanName。

