| Pattern                          | 正则表达式                                                   |
| -------------------------------- | ------------------------------------------------------------ |
| compile()                        | 静态，返回一个Pattern对象，编译正则表达式。<br />flag：模式标识。<br />`Pattern.CASE_INSENSITIVE`、r：匹配时忽略大小写，默认只考虑US ASCII字符。<br />`Pattern.UNICODE_CASE`、u：搭配r，匹配Unicode字符的大小写。<br />`Pattern.UNICODE_CHARACTER`、U：包含了u，使用Unicode替代POSIX。<br />`Pattern.MULTILINE`、m：指定`^、$`用于匹配行，而不是全文。<br />`Pattern.UNIX_LINES`、d：搭配m，只有`\n`作为行终止符。<br />`Pattern.DOTALL`、s：指定`.`匹配所有字符（包括行终止符）。<br />`Pattern.COMMENTS`、x：指定忽略空白字符、注释（`#`）。<br />`Pattern.LITERAL`：指定逐字地采纳，必须精确匹配（字母大小写的差异除外）。<br />`Pattern.CANON_EQ`：考虑Unicode字符的等价性， |
| matcher()                        | 返回对应的匹配器（Matcher）                                  |
| split()<br />splitAsStream()     | 返回将输入按指定分隔符分割的数组/Stream。<br />limit：数组的最大长度。<br />若分割到第limit-1个分隔符，则剩余的全部存入数组的最后一个位置。<br />limit&le;0时，分割整个输入；limit=0时，坠尾的字符串不会置于数组中。 |
| **Matcher**                      | **匹配器（任何实现CharSequence接口的）**                     |
| matches()                        | 若匹配模式，则返回true。                                     |
| lookingAt()                      | 若输入的开头匹配，则返回true。                               |
| find()                           | 查找下一个匹配，若找到下一个匹配，则返回true。<br />start：指定查找起始点。 |
| start()<br />end()               | 返回当前匹配的开始、结尾索引位置。<br />groupIndex：群组索引（从1开始，0表示整个匹配）。 |
| group()                          | 返回当前的匹配。<br />groupIndex：指定群组的匹配。           |
| replaceAll()<br />replaceFirst() | 返回将匹配器的输入中所有/第一个匹配替换后的结果。<br />replacement：替换字符串（`$n`指定群组的引用，`\$`来表示`$`）。 |
| reset()                          | 复位。<br />input：将匹配器用于另一个输入。                  |

- 群组`()`（子表达式）。

```java
Pattern compile = Pattern.compile("(([1-9]|1[0-2]):([0-5][0-9]))[ap]m", Pattern.CASE_INSENSITIVE);
Matcher matcher = compile.matcher("11:59am");
if (matcher.matches()) {
    for (int i = 0; i <= matcher.groupCount(); i++) {
        System.out.println("groupIndex:" + i + "\t" + matcher.group(i));
    }
}
```
