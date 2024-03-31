- Aware接口：框架辅助属性注入。

<table>
	<thead>
		<tr>
			<th width="20%">Aware接口</th>
			<th width="30%">回调方法</th>
			<th width="50%">作用</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>ServletContextAware</td>
			<td>setServletContext(ServletContext context)</td>
			<td>Spring框架回调方法注入ServletContext对象。
				<br />web环境下才生效
			</td>
		</tr>
		<tr>
			<td>BeanFactoryAware</td>
			<td>setBeanFactory(BeanFactory factory)</td>
			<td>Spring框架回调方法注入beanFactory对象</td>
		</tr>
		<tr>
			<td>BeanNameAware</td>
			<td>setBeanName(String beanName)</td>
			<td>Spring框架回调方法注入当前Bean在容器中的beanName</td>
		</tr>
		<tr>
			<td>ApplicationContextAware</td>
			<td>setApplicationContext(ApplicationContext applicationContext)</td>
			<td>Spring框架回调方法注入applicationContext对象</td>
		</tr>
	</tbody>
</table>


```java
public class UserServiceImpl implements UserService, ServletContextAware, ApplicationContextAware, BeanFactoryAware, BeanNameAware {
    private UserDao userDao;

    public UserServiceImpl() {
        System.out.println("userService创建");
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        System.out.println("userDao setUserDao");
        this.userDao = userDao;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        System.out.println(servletContext);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
        //org.springframework.context.support.ClassPathXmlApplicationContext@366e2eef, started on Thu Apr 06 22:57:14 CST 2023

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println(beanFactory);
        //org.springframework.beans.factory.support.DefaultListableBeanFactory@358ee631: defining beans [userService,userDao]; root of factory hierarchy
    }

    @Override
    public void setBeanName(String s) {
        System.out.println(s);
        //userService
    }
}
```
