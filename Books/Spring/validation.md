| 注解              | 位置 | 检验                                                         |
| ----------------- | ---- | ------------------------------------------------------------ |
| @Valid            | 形参 | 执行该对象（属性已存在以下检验）的检验。<br />如果检验错误，返回Errors对象到该方法的Errors参数<br />（errors# `Boolean hasErrors()`）。 |
| @NotNull          | 属性 | 非null                                                       |
| @NotBlank         | 属性 | 非空""                                                       |
| @Size             | 属性 | min、max：长度限制<br />message：提示信息                    |
| @CreditCardNumber | 属性 | 合法的信用卡号                                               |
| @Pattern          | 属性 | 正则<br />regexp                                             |
| @Digits           | 属性 | integer：整数位数上限<br />fraction：小数位数上限            |
