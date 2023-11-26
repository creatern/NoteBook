# 视图类型

- org.springframework.web.servlet.View封装了所有的视图实现，该接口的render(..)方法依据传入的Map类型的model对视图文件进行渲染，并传入到Responce完成处理。控制器只负责调用视图的渲染方法，将视图与Model绑定；而视图的渲染则是由视图的实现来执行。

## 内部资源视图 InternalResourceView

- InternalResourceView从服务器端渲染页面，使用RequestDispatcher接口（getRequestDispatcher()）将请求转发到服务器上的其他JSP或Servlet，并且不生成新的请求，URL不会出现变化。
- 转发前模型中的数据通过req.setAttribute()加入到req的属性中，而在转发的过程中req不改变，可通过req.getAttribute()来获取数据，实现数据共享。

## 重定向视图 RedirectView

- RedirectView重定向浏览器到一个新的URL上，生成新的请求（原有的请求和Model中的数据都丢失）。

```java
return "forward:/user"; //服务器内部转发
return "redirect:/user"; //服务器重定向
```

## 模板引擎视图

| 模板引擎                                 | 启用/禁用缓存属性（默认true） |
| ---------------------------------------- | ----------------------------- |
| FreeMarker                               | spring.freemarker.cache       |
| Groovy Templates                         | spring.groovy.template.cache  |
| Mustache                                 | spring.mustache.cache         |
| [Thymeleaf](./Books/Spring/Thymeleaf.md) | spring.thymeleaf.cache        |

# 视图解析 ViewResolver

## BeanName视图解析 BeanNameViewResolver

- BeanNameViewResolver执行解析要求bean类型为View接口的实现类，使用该bean来渲染视图。

```java
@GetMapping("/beanNameView")
public String beanNameView(Model model){
    model.addAttribute("name","Tom");
    return "beanNameView";
}
```

```java
@Component("beanNameView")
class CustomView implements View {

    @Override
    public String getContentType() {
        return MediaType.TEXT_HTML_VALUE;
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Object name = map.get("name");
        httpServletResponse.getWriter().append("name is " + name);
    }
}
```

```java
@Bean("beanNameView")
public View beanNameView(){
    return new View() {

        @Override
        public String getContentType() {
            return MediaType.TEXT_HTML_VALUE;
        }

        @Override
        public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
            httpServletResponse.getWriter().append("name is " + map.get("name"));
        }
    };
}
```

## 模板引擎视图解析

### [ThymeleafViewResolver](./Thymeleaf.md)

## 内部资源视图解析 InternalResourceViewResolver

- InternalResourceViewResolver用于处理各种内部特殊视图，判断视图的前缀类型（forward:、redirect:），并返回对应类型的视图。

## 直接指定视图

- 控制器的@XxxMapping方法中直接返回View类型的实例，跳过视图解析的过程。	
