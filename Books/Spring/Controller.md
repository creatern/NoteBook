| 注解     | @Controller                                                  |
| -------- | ------------------------------------------------------------ |
| 位置     | 类注解                                                       |
| 作用     | 设定SpringMVC的控制器bean<br />返回值放入模型中，并传递给视图渲染 |
| **注解** | **@RestController**                                          |
| 位置     | 类注解                                                       |
| 作用     | @Controller \+ @ResponseBody                                 |

# DispatcherServlet

- DispatcherServlet：基于Java Servlet API的前端控制器，它是整个Spring Web MVC框架的核心组件，负责接收、处理和转发所有的HTTP请求。

1. 初始化阶段：在服务器启动后，DispatcherServlet首先会被加载并创建一个新的WebApplicationContext实例，用于管理所有与Spring Web相关的bean。
2. 请求处理阶段：当接收到一个HTTP请求时，DispatcherServlet会根据请求URL、HTTP方法等信息决定由哪个处理器（Handler）来处理这个请求。
3. 分发阶段：确定了处理器之后，DispatcherServlet会调用该处理器的方法，并传递一个代表当前HTTP请求的对象（如HttpServletRequest和HttpServletResponse）作为参数。
4. 响应处理阶段：处理器处理完请求后，通常会返回一个 ModelAndView 对象，DispatcherServlet会根据这个对象的内容生成响应并发送回客户端。
5. 销毁阶段：在服务器关闭或者应用程序退出时，DispatcherServlet会清理资源并销毁WebApplicationContext。

# Handle 处理器

- Model的查找和执行在DispatcherServlet中是解耦的。控制器负责通过HandlerMapping查找Handler，再交由HandlerAdapter处理。

## HandlerMapping 映射器

<img src="../../pictures/MatchableHandlerMapping.png" width="1000"/>

### URL直接映射

- SimpleUrlHandlerMapping提供了一种简单的方式来定义URL和处理程序之间的映射关系，将特定的URL与特定的处理程序类或方法进行关联，当有请求匹配到指定的URL时，就会调用相应的处理程序进行处理。

```java
@Bean
public SimpleUrlHandlerMapping simpleUrlHandlerMapping(MyHandle myHandle){
    SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
    mapping.setOrder(1);
    mapping.setUrlMap(Collections.singletonMap("/simpleUrl",myHandle));
    return mapping;
}
```

### BeanName映射

- BeanNameUrlHandlerMapping将URL与bean名称映射，使用bean名称作为映射的key，将URL与bean名称存储在一个Map中。在处理请求时，它会根据URL查找相应的处理程序bean，并调用处理程序的方法来处理请求。（相当于通过URL来调用该bean）

>Bean名称映射：指定Bean的名称与其他组件的依赖关系，而无需了解具体的Bean的URL。 
>
>通过URL来调用Bean：BeanNameUrlHandlerMapping的bean名称映射可以被认为是通过URL来调用该bean的一种方式。

```java
@Bean(name="/beanNameUrl") //http://localhost:8080/beanNameUrl
public HttpRequestHandler beanNameUrlHandler(){
    return (req,resp) ->{
        resp.getWriter().println("beanNameUrl");
    };
}
```

### [@RequestMapping](./RequestMapping.md)

| 注解           | @RequestMapping                                              |
| -------------- | ------------------------------------------------------------ |
| 位置           | 类、方法注解                                                 |
| 作用           | 设置当前控制器方法请求访问路径。<br />@RequestMapping注解控制器类时，与方法上的@XxxMapping的信息合并。 |
| **注解**       | **@GetMapping、@PostMapping、@PutMapping、@DeleteMapping**   |
| 位置           | 方法注解                                                     |
| 作用           | 设置当前控制器方法请求访问路径与请求动作，每种对应一个请求动作。<br />只要访问路径或请求动作有一个不同，就可以使用多个该注解。 |
| **返回值**     | **说明**                                                     |
| String         | 响应的视图名称、重定向到的URL。                              |
| void           | 不需要返回任何响应。                                         |
| ModelAndView   | 响应的视图和模型数据的容器。                                 |
| ResponseEntity | 带有自定义HTTP头和状态代码的HTTP响应。                       |
| 其他类型       | 响应的序列化数据类型。                                       |

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

### HttpRequestHandler

<img src="../../pictures/HttpRequestHandler.png" width="600"/>

- HttpRequestHandler接口的实现类可以处理 HTTP 请求并将响应返回给客户端。实现类需要实现handleRequest()方法，该方法接受一个HttpServletRequest对象和一个HttpServletResponse对象作为参数，并且没有返回值。

- 仅实现了HttpRequestHandler接口的Handler，其查找和执行是分离的，需要被注册到对应的Mapping中，才能生效。

```java
public class MyHttpRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (httpServletRequest.getMethod().equals("GET")) {
            httpServletResponse.setContentType("text/html;charset=utf-8");
            httpServletResponse.getWriter().write("<h1>Hello World</h1>");
        } else if (httpServletRequest.getMethod().equals("POST")) {
            httpServletResponse.setContentType("text/html;charset=utf-8");
            httpServletResponse.getWriter().write("<h1>Hello World</h1>");
        } else {
            httpServletResponse.setContentType("text/html;charset=utf-8");
            httpServletResponse.getWriter().write("<h1>Hello World</h1>");
        }
    }
}
```

### Controller接口

- org\.springframework\.web\.servlet\.mvc\.Controller用于定义处理 HTTP 请求的控制器类（不是@Controller）。实现Controller接口的类可以接收HTTP请求并生成HTTP响应。Spring Web MVC 框架会自动将符合特定规则的控制器类处理的方法注册为处理特定URL的处理方法。

```java
@Component("/myController") //通过BeanNameUrlHandlerMapping调用
public class MyHttpRequestHandler implements org.springframework.web.servlet.mvc.Controller {
    
    // 接收HttpServletRequest和HttpServletResponse对象作为参数
    // 返回一个ModelAndView对象（用于将数据模型和视图名称返回给Servlet容器）
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Map<String,Object> model = new HashMap<>();
        model.put("name","Tom");
        return new ModelAndView("testView",model);
    }
}
```

## HandlerAdapter 适配器

- SpringMVC为@RequestMapping标注的处理器方法设置了参数自动解析。DispatcherServlet获取到处理器方法后，先执行拦截器的preHandler逻辑，若返回true，则当前的处理器Handler可以交给该Handler的HandlerAdapter（适配器）执行。
- [HandlerMethodArgumentResolver](./RequestMapping.md#HandlerMethodArgumentResolver)（参数解析）：HandlerAdapter（适配器）执行HandlerMethod（处理器方法）前，遍历当前处理器方法中的所有参数签名，根据每个参数签名自动从请求信息或当前应用信息中获取最合适的值，执行时传递给该处理器方法，作为执行处理器方法的参数列表。

## HandlerInterceptor 拦截器

- 拦截器是HandlerInterceptor的实现类。在查找处理器时，HandlerMapping并不直接返回Handler，而是Handler的执行链HandlerExecutionChain，其封装需要应用到该Handler上的所有拦截器。

1. 定位拦截器：Spring MVC首先会扫描所有的拦截器，并将它们存储在一个列表中。  
2. 实现拦截器逻辑：每个拦截器都需要实现preHandle()方法、postHandle()方法、afterCompletion()方法。preHandle()方法在控制器方法调用之前执行，postHandle()方法在控制器方法调用之后执行，afterCompletion()方法在控制器方法抛出异常时执行。  
3. 执行控制器方法：当所有的拦截器都执行完毕后，Spring MVC会调用控制器方法。
4. 回收：在控制器方法返回结果之后，Spring MVC会执行所有的postHandle()方法、afterCompletion()方法，然后拦截器链执行完毕。

- 定义拦截器之后，还需要将其通过WebMvcConfigurer\#addInterceptors\(\.\.\)、@Intercepts等方式注册到Spring MVC环境中，才能生效。

<img src="../../pictures/Spring-HandlerInterceptor-run.drawio.svg" width="600"/> 

| 注解     | @Order                                                       |
| -------- | ------------------------------------------------------------ |
| 位置     | 拦截器类                                                     |
| 作用     | 标注拦截器的执行顺序，如果两个拦截器的Order值相同，会根据它们的实现类名称的字母顺序来确定它们的执行顺序 |
| **注解** | **@Intercepts**                                              |
| 位置     | 拦截器类                                                     |
| 作用     | 指定一个URL路径、或包含一个FilterRegistrationBean对象被拦截器拦截，可用于配置更多的拦截器参数<br />（仅SpringMVC中存在） |
| **注解** | **@Filter**                                                  |
| 位置     | 拦截器类                                                     |
| 作用     | 指定一个URL路径，该路径将被拦截器拦截，只能配置一个拦截器<br />（仅SpringMVC中存在） |

```java
@Order(1)
@Intercepts(@Filter("/login"))
public class ProjectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { 
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod(); //获得原始执行方法
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(ex != null){
            ex.printStack();
        }
    }
}
```

## HandlerExecutionChain 处理器执行链

- HandlerExecutionChain包含了处理器方法中的HandlerMethod对象、Handler层次结构、Interceptors（拦截器）的顺序。

1. 在处理请求时，Spring MVC会通过RequestMappingHandlerMapping来寻找处理器，RequestMappingHandlerMapping会根据请求的路径来匹配处理器方法，并返回一个HandlerExecutionChain。
2. 接着，Spring MVC会通过HandlerAdapter来执行处理器方法，HandlerAdapter会根据请求的类型和处理器方法的方法signature来执行对应的方法，并返回处理结果。
3. 最后，Spring MVC会通过HandlerInterceptor来执行拦截器，在处理器方法执行前后进行一些操作。
