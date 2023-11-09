# RequestMappingHandlerMapping

- RequestMappingHandlerMapping负责解析HTTP请求的URL，遍历所有@RequestMapping的信息，寻找条件属性与请求信息最匹配的@RequestMapping作为最终的处理器方法。

# RequestCondition接口 属性规则

- @RequestMapping的每个条件都是RequestCondition接口的实现，每个属性都有以下特性类型。

1. 匹配规则：与请求的匹配逻辑。
2. 多值匹配规则：属性数组提供多个值时的匹配逻辑。
3. 合并规则：方法与类型上的属性合并规则。
4. 排序规则：该请求有多个注解的属性匹配时，对匹配结果的排序规则。
5. 若注解中的属性没有提供值，则不对该条件过滤（任意请求都匹配）。

## path 请求路径

- 在@RequestMapping中，path和value互为别名（@AliasFor("path") 注解属性别名机制）。
- @Request会自动为path路径匹配加上“/”，若已经存在“/”，则不添加。

```java
// 匹配所有路径的请求
@Request

// 匹配根路径请求
@Request("/")

// 匹配/path1的请求
@Request("path1")

// 匹配/path1或/path2的请求
@Request({"path1","path2"})

//类@RequestMapping("/home")搭配方法@XxxMapping("/design")：请求映射为/home/design。
```

- 路径模式支持方法和类级别的注解，同时出现时，对二者进行合并。

1. path属性在类和方法上的注解都指定，则拼接路径。
2. path属性只在方法/类上的注解指定，则以方法/类上的path为准。
3. path属性在类和方法上的注解都不指定，则匹配根路径。

- 模式字符串（path）的排序规则：越少通配符（Ant风格）越靠前，按如下顺序依次判断。

1. 与请求路径完全相同的模式字符串（无通配符），最靠前。
2. /\*\*匹配所有模式，最靠后。以/\*\*结尾的path排在不包含/\*\*结尾的path之后。
3. \*和\{\}总属性越少的越靠前。
4. path的长度越长越靠前。
5. \*数量越少越靠前。（不包括\*\*）
6. \{\}数量越少越靠前。

```properties
# SpringBoot默认关闭后缀匹配(.*)
spring.mvc.pathmatch.useSuffixPattern=false
```

## method 请求方法

- method属性指定HTTP请求中的Reuqest Method（分别与RequestMethod枚举类的实例一一对应），不指定则匹配所有请求方法。
- @XxxMapping中的method已经在注解的名称中表现，该类注解等同于method=Xxx的@RequestMapping。

```java
// 匹配路径为path1、不限请求方式的请求
@Request(path = "path1")

// 匹配路径为path1、请求方式为GET的请求
@Request(path = "path1", method = RequestMethod.GET)

// 匹配路径为path1、请求方式为GET或POST的请求
@Request(path = "path1", method = {RequestMethod.GET,RequestMethod.POST})
```

## params 请求参数

- params属性指定匹配HTTP请求中的参数，（1）URL上的查询参数；（2）请求体中的表单类型的参数（指定name属性的表单元素）。

- 若指定该params属性，则必须满足该属性的所有参数的请求才匹配。

1. name=value：请求中必须存在参数名为name且值为value的参数。
2. name\!=value：请求中不能存在参数名为name且值为value的参数。
3. name：请求中必须存在参数名为name的参数。
4. \!name：请求中不能存在参数名为name的参数。

- 类和方法注解中的params属性通过与（\&）的方式合并。
- 排序规则：提供的匹配属性数组越长（匹配条件越多），越靠前。

```java
// 匹配所有路径、存在参数名为username且值为Tom的参数的请求
@RequestMapping(params = "username=Tom")
```

> 不同的表单元素提交的值可能不同，如 checkBox默认未选中则不提交该参数，选中则提交on。

## headers 请求头

- headers属性指定匹配HTTP请求头中的参数。headers属性的大部分规则和params属性相同，只有2点不同，（1）headers的参数名不区分大小写；（2）支持Accept和Content\-Type两种请求头条件的通配符类型。

```java
// 模糊匹配
headers = "Accept: text/*"
```

## 内容类型 Content\-Type \& Accept

- Content\-Type和Accept请求头的值的规范都遵循MimeType的标准定义格式，在HTTP请求的数据传输中使用MediaType（MimeType的扩展集）。
- MediaType枚举类内封装了MediaType的大部分类型。

```
类型/子类型(;参数名=参数值)*n
```

- 子类型为RFC 2046标准定义的类型值，可使用"\+"（类型家族\+格式类型）
- 参数可以没有，可以多个（使用`;`分割）；参数值可以放在\"\"、\'\'、或直接表示。
- 除了参数值，其他的都不区分大小写。

```
text/html;name=value
application/xhtml+xml
```

1. 类型、子类型部分支持\*代表所有类型，若类型为\*，则子类型也必须是\*。
2. 子类型带\+的，若\+前为\*，表示所有子类型中\+前的任意类型。
3. 标准的q属性（质量因子 quality）0\~1，1最好、0不接受。

### consumes

- consumes属性指定处理方法能够接受的请求内容类型（请求的Content\-Type），即处理器是否消费该类型，匹配Accept请求头的值。

- consumes的整个条件构成MediaRange。

### produces

- produces属性指定处理方法能够返回的响应内容类型（响应的Content\-Type），即处理器是否生产该类型。

# RequestMappingInfo 属性条件

- RequestMappingInfo是RequestCondition\<RequestMappingInfo\>的实现类，包含了@RequestMapping的所有属性条件。

```java
public interface RequestCondition<T> {
    //合并
    T combine(T var1);

    //匹配顺序
    @Nullable
    T getMatchingCondition(HttpServletRequest var1);
    
    //比较
    int compareTo(T var1, HttpServletRequest var2);
}
```

- RequestMappingInfo对请求进行匹配时，依次获取以下每个条件的匹配结果，只要有一个返回null，则该RequestMappingInfo对应的@RequestMapping与该请求不匹配。

```java
@Nullable
private final String name;
@Nullable
private final PathPatternsRequestCondition pathPatternsCondition;
@Nullable
private final PatternsRequestCondition patternsCondition; //path
private final RequestMethodsRequestCondition methodsCondition; //method
private final ParamsRequestCondition paramsCondition; //params
private final HeadersRequestCondition headersCondition; //headers
private final ConsumesRequestCondition consumesCondition; //consumers
private final ProducesRequestCondition producesCondition; //produces
private final RequestConditionHolder customConditionHolder; //自定义条件
```

- @RequestMapping的方法注解和类注解的合并就是RequestMappingInfo的合并，对所有条件的同类条件执行合并。每个条件都继承自AbstractRequestCondition，带有各自的combine(..)方法。

```java
//分别调用每个条件的combine(..)方法实现合并
public RequestMappingInfo combine(RequestMappingInfo other)
```

- RequestMappingInfo的匹配请求规则为methods、params、headers、consumes、produces、pathPatterns、patterns、custom。

```java
//匹配条件
public RequestMappingInfo getMatchingCondition(HttpServletRequest request)
```

- 多个@RequestMapping对同一个请求匹配时，RequestMappingInfo的排序规则为path、params、headers、consumes、produces、method、custom。

```java
//多个匹配的@RequestMapping的排序
public int compareTo(RequestMappingInfo other, HttpServletRequest request){
    //如果请求方法是HEAD，则优先判断method。
    if (HttpMethod.HEAD.matches(request.getMethod())) {...}
    
    //常规的排序规则path、params、headers、consumes、produces、method、custom。
}
```

# RequestMappingHandlerAdapter 适配器

- @RequestMapping标注的方法，返回的Handler类型为HandlerMethod，对应可执行的适配器为RequestMappingHandlerAdapter 。

```java
public class RequestMappingHandlerAdapter extends AbstractHandlerMethodAdapter implements BeanFactoryAware, InitializingBean {
    private List<HandlerMethodArgumentResolver> customArgumentResolvers;
}
```

## HandlerMethodArgumentResolver 参数解析

```java
public interface HandlerMethodArgumentResolver {
    
    // 根据方法中参数的元信息判断是否可以直接解析该参数的参数值，true支持解析
    boolean supportsParameter(MethodParameter var1); //MethodParameter封装处理器方法的参数

    // 传入请求相关信息和参数信息后，返回Object的解析后当前处理中的参数对应值
    @Nullable
    Object resolveArgument(MethodParameter var1, @Nullable ModelAndViewContainer var2, NativeWebRequest var3, @Nullable WebDataBinderFactory var4) throws Exception;
}
```

```java
// RequestMappingHandlerAdapter
@Nullable
public List<HandlerMethodArgumentResolver> getArgumentResolvers() {
    // RequestMappingHandlerAdapter处理适配器解析方法中的参数时
    // 遍历customArgumentResolvers并调用每个元素的supportsParameter(MethodParameter var1)。
    return this.argumentResolvers != null ? this.argumentResolvers.getResolvers() : null;
}
```

