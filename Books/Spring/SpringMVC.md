# SpringMvcSuport

| 名称 | @EnableWebMvc             |
| ---- | ------------------------- |
| 类型 | 配置类注解                |
| 位置 | SpringMVC配置类定义上方   |
| 作用 | 开启SpringMVC多项辅助功能 |

- WebMvcConfigurer接口：定义了多个Spring MVC的配置方法（default），所有配置类都可以实现该接口并覆盖其方法。

```java
@Configuration
@ComponentScan("com.zjk.controller")
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    //拦截器
    @Autowired
    private ProjextInterceptor projextInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(projextInterceptor).addPathPatterns("/books","/books/**");
    }

    //视图控制器
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
}
```

- WebMvcConfigurationSupport：WebMvcConfigurer接口的实现类。

```java
public class SpringMvcSupport extends WebMvcConfigurationSupport {
    @Autowired
    private ProjextInterceptor projextInterceptor;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //当调用/book请求时，使用拦截器
        registry.addInterceptor(projextInterceptor).addPathPatterns("/books","/books/**");
    }
}
```

```java
@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //当访问/pages/*时，走/pages目录下的内容
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
    }
}
```

```java
public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    //部分方法

    protected String[] getServletMappings() {
        return new String[]{"/"}; //拦截路径
    }
}
```

# MVC功能扩展

## ServletInitializer 前端控制器

| 前端控制器抽象类                                     | 说明                                                         |
| ---------------------------------------------------- | ------------------------------------------------------------ |
| AbstractAnnotationConfigDispatcherServletInitializer | Spring和SpringMVC环境整合。<br />SpringMVC环境可以访问Spring环境，而Spring环境访问不了SpringMVC环境。 |
| AbstractDispatcherServletInitializer                 |                                                              |

```java
public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class}; //Spring环境
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class}; //SpringMVC环境
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; //拦截路径
    }
}
```

```java
public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer {

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        //将Spring容器添加到Web容器中
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(SpringMvcConfig.class);
        return applicationContext;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; //设置被拦截的路径
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
```

## Converter 类型转换器

- Converter接口：由SpringMVC对传递参数进行类型转换。

> Converter所属的包为org.springframework.core.convert.converter。

```java
@FunctionalInterface
public interface Converter<S, T> {
    @Nullable
    T convert(S var1);

    default <U> Converter<S, U> andThen(Converter<? super T, ? extends U> after) {
        Assert.notNull(after, "After Converter must not be null");
        return (s) -> {
            T initialResult = this.convert(s);
            return initialResult != null ? after.convert(initialResult) : null;
        };
    }
}
```

- HttpMessageConvert接口的内部通过Converter接口（HttpMessageConvert接口）的实现类完成类型转换（pojo \-\> json、Collection \-\> json）。

## HandlerInterceptor 拦截器

- 拦截器（Interceptor）：动态拦截控制器（Controller）方法的执行。在指定的方法调用前/后执行预先设定的代码、或阻止原始方法的执行。

> 拦截器链：拦截器链的运行顺序按照拦截器的添加顺序先后执行。当拦截器中出现对原始处理器的拦截，后面的拦截器均终止运行。

| HandlerInterceptor        | 拦截器接口                                                   |
| ------------------------- | ------------------------------------------------------------ |
| **方法**                  | **说明**                                                     |
| preHandle()               | 在方法执行之前进行校验。<br />返回false则终止原方法操作。    |
| postHandle()              | 在方法执行之后进行校验。                                     |
| afterCompletion()         |                                                              |
| **参数**                  | **说明**                                                     |
| handler                   | class org.springframework.web.method.HandlerMethod<br />原方法的反射（Method）。 |
| ModelAndView modelAndView | 页面跳转                                                     |
| Exception ex              | 抛出的异常对象                                               |

```java
@Component
public class ProjectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { //
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod(); //获得原始执行方法
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
```

## @RestControllerAdvice 异常处理器

| 注解 | @RestControllerAdvice |
| ---- | --------------------- |
| 位置 | 类注解                |
| 作用 | 标注异常处理器        |

| 注解 | @ExceptionHandler                                            |
| ---- | ------------------------------------------------------------ |
| 位值 | 方法注解                                                     |
| 作用 | 设置指定异常的处理方案，功能等同于控制器方法。<br />出现异常后终止原始控制器执行，并转入当前方法执行。 |

```java
@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex) {
        //记录日志
        //发送消息给运维
        //发送消息给开发人员
        return new Result(ex.getCode(), null, ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex) {
        return new Result(ex.getCode(), null, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex) {
        return new Result(Code.SYSTEM_UNKNOW, null, "安抚客户");
    }
}
```

# @Controller 控制器

| 名称 | @Controller                                                  |
| ---- | ------------------------------------------------------------ |
| 类型 | 类注解                                                       |
| 作用 | 设定SpringMVC的控制器bean<br />返回值放入模型中，并传递给视图渲染 |

## @RestController REST控制器

| 注解 | @RestController              |
| ---- | ---------------------------- |
| 位置 | 类注解                       |
| 作用 | @Controller \+ @ResponseBody |

# 请求响应

## @ResponseBody  响应体

| 注解 | @ResponseBody        |
| ---- | -------------------- |
| 位置 | 类、方法注解         |
| 作用 | 返回值直接写入响应体 |

| 返回值 | 说明                                            |
| ------ | ----------------------------------------------- |
| String | 文本内容响应给前端（而不是Mapping的页面跳转）。 |
| 对象   | 对象转换成JSON响应给前端。                      |

## @XxxMapping 请求映射

| 注解           | @RequestMapping                                              |
| -------------- | ------------------------------------------------------------ |
| 位置           | 类、方法注解                                                 |
| 作用           | 设置当前控制器方法请求访问路径。<br />@RequestMapping注解控制器类时，作为请求路径的前置。 |
| **注解**       | **@GetMapping、@PostMapping、@PutMapping、@DeleteMapping**   |
| 位置           | 方法注解                                                     |
| 作用           | 设置当前控制器方法请求访问路径与请求动作，每种对应一个请求动作。 |
| **参数**       | **说明**                                                     |
| value/path     | 请求映射路径（默认根路径"/"）                                |
| method         | 指定请求方法                                                 |
| produces       | 限定能够处理的请求                                           |
| **返回值**     | **说明**                                                     |
| String         | 响应的视图名称、重定向到的URL。                              |
| void           | 不需要返回任何响应。                                         |
| ModelAndView   | 响应的视图和模型数据的容器。                                 |
| ResponseEntity | 带有自定义HTTP头和状态代码的HTTP响应。                       |
| 其他类型       | 响应的序列化数据类型。                                       |

```java
//String进行页面跳转
return "/user";
return "redirect:/user"; //重定向
```

> @RequestMapping("/home")搭配@XxxMapping("/design")：请求映射为/home/design。

```java
@RestController
@RequestMapping("/users")
public class UserController {

//    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @PostMapping
    public String save() {
        System.out.println("user save...");
        return "{'module':'user save'}";
    }

//    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        System.out.println("user delete..." + id);
        return "{'module':'user delete'}";
    }

//    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    @PutMapping
    public String update(@RequestBody User user) {
        System.out.println("user update..." + user);
        return "{'module':'user update'}";
    }

//    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id) {
        System.out.println("user getById..." + id);
        return "{'module':'user getById'}";
    }

//    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @GetMapping
    public String getAll() {
        System.out.println("user getAll...");
        return "{'module':'user getAll'}";
    }
}
```

## @CrossOrigin 跨域资源访问

| 注解 | @CrossOrigin                                         |
| ---- | ---------------------------------------------------- |
| 位置 | 类注解                                               |
| 作用 | 跨域资源共享（Cross Origin Resource Sharing，RCORS） |

## @ResponseStatus 响应状态码

| 注解 | @ResponseStatus      |
| ---- | -------------------- |
| 位置 | 方法注解             |
| 作用 | 返回指定的响应状态码 |
| 参数 | HttpStatus.XXX       |

# 数据传递

## 参数类型

| 参数类型 | 传递方式                                                     |
| -------- | ------------------------------------------------------------ |
| 值       | url地址传参，地址参数名与形参变量名相同时，自动接收参数。    |
| POJO     | 请求参数名与形参对象属性名相同时，自动接收参数。             |
| 嵌套POJO | 请求参数名与形参对象属性名相同时，按照对象层次结构关系接收嵌套POJO属性参数。 |
| 数组     | 请求参数名与形参对象属性名相同且请求参数为多个，定义数组类型即可接收参数 |
| 集合     | 同名请求参数可以使用@RequestParam注解映射到对应名称的集合对象中作为数据 |

### POJO参数

```java
//http://localhost:8080/user/userParam?id=9&name=zjk&age=18
@RequestMapping("/userParam")
@ResponseBody
public String commonParam(User user){ //id、name、age
    return "{'info':'userParam'}";
}
```

### 嵌套POJO参数

- 按照对象层次结构关系：作为属性的POJO.属性。

```java
@RequestMapping("/userParam")
@ResponseBody
public String commonParam(User user){ //User:id,name,age,address(Address:province,city)
    return "{'info':'userParam'}";
}
```

### 数组参数

- 数组名必须一致才能封装到一个数组中。

```java
@RequestMapping("/arrParam")
@ResponseBody
public String arrParam(String[] infos) {
    return "{'info':'arrParam'}";
}
```

### 日期参数 @DateTimeFormat

| 注解     | @DateTimeFormat                 |
| -------- | ------------------------------- |
| 位置     | 形参注解                        |
| 作用     | 设定日期时间型数据格式          |
| 相关属性 | pattern：指定日期时间格式字符串 |

```java
@RequestMapping("/dateParam")
@ResponseBody
public String dateParam(@DateTimeFormat(pattern = "yyyy-mm-dd") Date date1,
                        @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss") Date date2){
    return "{'info':'date'}";
}
```

## 传递方式

| 注解          | 区别：接收参数                     | 应用                                                      |
| ------------- | ---------------------------------- | --------------------------------------------------------- |
| @RequestBody  | url地址/表单传参                   | 发送请求参数超过1个时，以json格式为主。                   |
| @RequestParam | json数据                           | 发送非json格式数据，接收请求参数。                        |
| @PathVariable | 路径参数，\{参数名称\}描述路径参数 | RESTful，参数数量较少时，接收请求路径变量，通常传递id值。 |

### @RequestParam 请求参数

| 注解 | @RequestParam                                                |
| ---- | ------------------------------------------------------------ |
| 位置 | 形参注解                                                     |
| 作用 | 绑定形参和地址参数。<br />（形参与地址参数名不一致时，需要该注解指定） |
| 参数 | required：是否为必传参数 <br>defaultValue：参数默认值        |

```java
//http://localhost:8080/user/userParam?id=9&name=zjk&age=18
@RequestMapping("/commonParam")
@ResponseBody
public String commonParam(@RequestParam("name") String userName,
                          @RequestParam("age") Integer age){
    System.out.println(userName);
    System.out.println(age);
    return "{'info':'commonParam'}";
}

@RequestMapping("/listParam")
@ResponseBody
public String listParam(@RequestParam List<String> list) {
    return "{'info':'arrParam'}";
}
```

### @RequestBody 请求体

| 注解 | @RequestBody                                                 |
| ---- | ------------------------------------------------------------ |
| 位置 | 形参注解                                                     |
| 作用 | 将请求中请求体所包含的数据传递给请求参数<br />一个方法只能使用存在一个该注解。 |

```java
@RequestMapping("/userParamForJson")
@ResponseBody
public String userParamForJson(@RequestBody User user) {
    return "{'info':'userParamForJson'}";
}

@RequestMapping("/listParamForJson")
@ResponseBody
public String listParamForJson(@RequestBody List<String> list) {
    return "{'info':'listParamForJson'}";
}

@RequestMapping("/userListParamForJson")
@ResponseBody
public String userListParamForJson(@RequestBody List<User> list) {
    return "{'info':'userListParamForJson'}";
}
```

### @PathVariable 路径参数绑定

| 注解 | @PathVariable                                  |
| ---- | ---------------------------------------------- |
| 位置 | 形参注解                                       |
| 作用 | 绑定路径参数和形参，路径参数名与形参名一一对应 |

```java
@RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
@ResponseBody
public String getById(@PathVariable Integer id){
    return "{'module':'user getById'}";
}
```

## Model

- Model对象负责控制器和视图之间的数据传递：Model属性中的数据被复制到Servlet Request属性中。

> Controller 将数据存储在 Model（或者 Map）对象中，再将视图名称和 Model 对象返回给 DispatcherServlet，DispatcherServlet 根据视图名称找到对应的视图（View），并将 Model 对象传递给它。（在方法的参数中声明一个 Model（或者 Map）类型的变量，然后在方法中通过该变量来存储数据）

- Model 接口的实现类：Spring MVC默认使用ExtendedModelMap（继承 LinkedHashMap），可用于存储和检索数据。

```java
@GetMapping("/thymeleafHello")
public String hello(Model model){
    model.addAttribute("name","张三");
    model.addAttribute("age",18);
    return "thymeleafHello";
}
```

### @ModelAttribute

| 注解 | @ModelAttribute                                              |
| ---- | ------------------------------------------------------------ |
| 位置 | 形参、方法注解                                               |
| 方法 | 返回值存入Model的属性。                                      |
| 形参 | 数据绑定，该形参的值将由model中取得。<br />如果model中找不到，那么该参数会先被实例化，然后被添加到model中。<br />（在model中存在以后，请求中所有名称匹配的参数都会填充到该参数中） |
| 属性 | name：添加/匹配到model的属性名称（默认为当前标注的参数名称）。 |

- 同个控制器内的@ModelAttribute方法先于@RequestMapping方法被调用。且如果同时被@XxxMapping注解，则返回值不再是视图名，而是Model的一个属性。

```java
//默认将方法的返回值存入Model
@ModelAttribute
public Account addAccount(@RequestParam String number) {
    return accountManager.findAccount(number);
}

//通过addAttribute()，向Model中存入多个数据。
@ModelAttribute
public void populateModel(@RequestParam String number, Model model) {
    model.addAttribute(accountManager.findAccount(number));
    // add more ...
}
```

### @SessionAttributes

| 注解 | @SessionAttributes                                           |
| ---- | ------------------------------------------------------------ |
| 位置 | 控制器类                                                     |
| 作用 | 注解类中存放到Model中的属性在会话中会一直保持。<br />搭配@ModelAttribute使用。 |

> SessionStatus 接口会话状态
>
> ```java
> //将当前处理程序的会话处理标记为完成，允许清理会话属性。
> sessionStatus.setComplete();
> ```

