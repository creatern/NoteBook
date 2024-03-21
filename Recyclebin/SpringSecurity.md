# Spring Security

```xml
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-security</artifactId>
```

> Spring Security提供的默认登陆页（user：日志 Using generated security password）。

# PasswordEncoder 密码转码器

| PasswordEncoder         | 加密                                |
| ----------------------- | ----------------------------------- |
| BCryptPasswordEncoder   | bcrypt强哈希                        |
| NoOpPasswordEncoder     | 不使用任何转码<br />（开发中使用）  |
| Pbkdf2PasswordEncoder   | PBKDF2                              |
| SCryptPasswordEncoder   | Scrypt哈希                          |
| StandardPasswordEncoder | SHA-256哈希<br />（不安全，已废弃） |

- `PasswordEncoder#matches()`：将用户登录时输入的密码转码，并与数据库中的密码对比。

> 数据库中的（已编码的）密码永远不会被解码，需要使用转码器。

# UserDetailsService 用户详情服务

| UserDetailsService         | 基于Xxx的用户详情服务 |
| -------------------------- | --------------------- |
| InMemoryUserDetailsManager | 内存                  |
|                            | JDBC                  |
|                            | LDAP                  |

# UserDetails 用户实体

```java
List<UserDetails> usersList = new ArrayList<>();
usersList.add(new User(
    "buzz", encoder.encode("password"),
    Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
```

| UserDetails方法           | 说明                             |
| ------------------------- | -------------------------------- |
| getAuthorities()          | 返回集合，表明用户被授予的权限。 |
| isAccountNonExpired()     |                                  |
| isAccountNonLocked()      | 用户账户是否锁定。               |
| isCredentialsNonExpired() |                                  |
| isEnabled()               | 用户账户是否可用。               |

# SecurityFilterChain Web请求保护

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.authorizeRequests()
        .antMatchers("/design", "/orders").hasRole("USER")
        .antMatchers("/", "/**").permitAll()
        .and()
        .formLogin().loginPage("/login")
        .and()
        .build();
}
```

# 第三方登录

## OAuth2

```xml
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-oauth2-client</artifactId>
```

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          <oauth2 or openid provider name>:
            clientId: <client id>
            clientSecret: <client secret>
            scope: <comma-separated list of requested scopes>
```

# @PreAuthorize、@PostAuthorzie 方法保护

| 注解 | @PreAuthorize                                                |
| ---- | ------------------------------------------------------------ |
| 位置 | 方法                                                         |
| 参数 | SpEL表达式                                                   |
| 时机 | 调用方法之前                                                 |
| 说明 | 若SpEl表达式返回true，则方法允许调用。<br />否则，阻止方法调用，并返回AccessDeniedException。 |

| 注解 | @PreAuthorize                                                |
| ---- | ------------------------------------------------------------ |
| 位置 | 方法                                                         |
| 参数 | SpEL表达式                                                   |
| 时机 | 方法调用完成并返回                                           |
| 说明 | 将方法的返回值在SpEl表达式中比较，若返回true，则方法允许调用。<br />否则，抛出AccessDeniedException。 |

# Principal 用户识别
