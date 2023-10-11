# Android简述

## 平台架构

<img title="" src="../../pictures/android_stack_2x.png" alt="" width="1200">

## 项目结构

<img title="" src="../../pictures/Android-AndroidProjectStruct.drawio.svg" alt="" width="655">

# 界面编程

## 布局

### Constraint Layout

| 约束   | 说明                                       |
| :----- | :----------------------------------------- |
| `<<<`  | wrap content，容器跟内容的宽度变化而变化。 |
| `---`  | 固定的宽度或高度值                         |
| `-^v-` | match constraint                           |

## 控件

### 基本控件

| 控件          | 说明 |
| :------------ | :--- |
| TextView      |      |
| ImageView     |      |
| Drawable      |      |
| MoveTextView  |      |
| ImageTextView |      |
| Button        |      |
| ImageButton   |      |

- 标签变量绑定： R（代表res目录的类）。

> R.id.myTextView：返回res目录下id为myTextView的控件id。

```java
findViewById(int id); //不需要强转，自动匹配控件类型。
```

### CheckBox 复选框

- CheckBox（复选框）：单独设置一个.xml界面文件，

### 单选组

## 事件监听



