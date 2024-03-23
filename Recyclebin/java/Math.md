# Math

- java.lang.Math提供了一系列静态方法用于科学计算，其方法的参数和返回值类型一般为double型。

| 方法             | 说明                                  |
| ---------------- | ------------------------------------- |
| abs()            | 绝对值                                |
| sqrt()           | 平方根                                |
| pow()            | a的b次幂                              |
| log()            | 自然对数                              |
| exp()            | e为底指数                             |
| max()<br />min() | 最大/小值                             |
| random()         | 返回0.0到1.0的随机数（double）        |
| round()          | double型数据a转换为long型（四舍五入） |

# BigInteger、BigDecimal

- java.math.BigInteger：不可变的任意精度的整数，提供所有 Java 的基本整数操作符的对应物，并提供 java.lang.Math 的所有相关方法。BigInteger还提供以下运算：模算术、GCD 计算、质数测试、素数生成、位操作以及一些其他操作。

> Integer类作为int的包装类，能存储的最大整型值为2^31-1，Long类也是有限的，最大为2^63-1。如果要表示再大的整数，不管是基本数据类型还是他们的包装类都无能为力。

- BigDecimal：支持不可变的、任意精度的有符号十进制定点数。一般的Float类和Double类可以用来做科学计算或工程计算，但在商业计算中，要求数字精度比较高，故用到java.math.BigDecimal类。

```java
BigInteger bi = new BigInteger("2313123232323312223123122222222");
BigDecimal bd = new BigDecimal("1213123.12312312312");
BigDecimal bd2 = new BigDecimal("23");
System.out.println(bd.divide(bd2, BigDecimal.ROUND_HALF_DOWN));
//52744.48361404883
System.out.println(bd.divide(bd2, 15, BigDecimal.ROUND_HALF_UP));
//52744.483614048831304
```
