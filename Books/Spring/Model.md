# Model 模型 概述

<img src="../../pictures/ExtendedModelMap.png" width="800"/> 

- Model对应于一次用户的请求处理（一个请求内所有的Model操作都是同一个实例），负责控制器和视图之间的数据传递，Model属性中的数据被复制到Servlet Request属性。

| 模型相关类型       | 说明                                                        |
| ------------------ | ----------------------------------------------------------- |
| Map                | 模型数据存储的基本类型，视图渲染时也使用该类型              |
| Model              | SpringMVC对模型数据操作的抽象，可通过asMap(..)方法转换为Map |
| RedirectAttributes | addFlashAttribute(..)方法来添加重定向可用的Map参数          |
| ExtendedModelMap   | @XxxMapping处理前，SpringMVC自动绑定ExtendedModelMap        |

```java
@GetMapping("/thymeleafHello")
public String hello(Model model){
    model.addAttribute("name","张三");
    model.addAttribute("age",18);
    return "thymeleafHello";
}
```

# 模型数据绑定

## ExtendedModelMap自动绑定

- SpringMVC自动为@XxxMapping标志的方法绑定ExtendedModelMap。ExtendedModelMap的子类或父类（大部分可以）实现以下操作：

1. 方法参数声明为该类型的，会自动绑定模型数据，只要不返回就不会对原有的模型数据造成影响。
2. 方法内部声明为该类型的，返回的模型数据会对原有的模型数据造成影响，且视图为@XxxMapping指定的URL路径。

```java
@GetMapping("/showUser")
public String showUser(Model model){
    model.addAttribute("username","Tom");
    return "showUser";
}

@GetMapping("/showUser")
public Model showUser(){
    Model model<String,Object> = new ExtendedModelMap();
    model.addAttribute("username","Tom");
    return model;
}
```

## @ModelAttribute

| 注解 | @ModelAttribute                                              |
| ---- | ------------------------------------------------------------ |
| 位置 | 形参、方法注解                                               |
| 方法 | 返回值自动存入ExtendedModelMapModel                          |
| 形参 | 数据绑定，该形参的值从模型取得，对该参数的修改不影响模型内的值。<br />若模型中找不到名称匹配的，则该参数会先被实例化（必须能够实例化），然后被添加到模型中。 |
| 属性 | name：添加/匹配到model的属性名称（默认为当前标注的参数名称）。 |

- 同个控制器内的@ModelAttribute方法先于@RequestMapping方法被调用。（如果同时被@XxxMapping和@ModelAttribute标注，则返回值不再是视图名，而是Model的一个属性）

```java
@GetMapping("/showUser")
@ModelAttribute("username")
public String showUser(){
    return "Tom";
}

@GetMapping("/showUser")
@ModelAttribute("username")
public String showUser(@ModelAttribute("username") String username){
    //从模型中获取的username仍然是空的，为什么？？？
    username = "Tom";
    return username;
}
```

## ModelAndView

- ModelAndView同时封装模型数据和视图信息，设置模型数据和视图名称，并将其返回给 DispatcherServlet，然后由 DispatcherServlet 进行视图的解析和渲染。

```java
@RequestMapping("/user")
public ModelAndView getUser() {
    User user = userService.getUser();  // 假设从数据库中获取到了用户数据

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("user", user);  // 将用户数据添加到模型
    modelAndView.setViewName("user");  // 设置视图名称

    return modelAndView;
}
```

## RedirectAttributes

- RedirectAttributes只对重定向视图有效。

# BindingResult 捕获绑定和验证过程中的错误信息
