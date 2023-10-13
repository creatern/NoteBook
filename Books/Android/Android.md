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

- CheckBox（复选框）需要单独设置一个界面文件，

### Spinner 单选组

- Spinner（单选组）需要单独配置一个界面文件来设置选项，而一个主界面中不能存在两个Spinner。

```xml
<!--主界面-->
<Spinner
         android:id="@+id/mySpinner"
         android:spinnerMode="dialog"
         />
```

```xml
<!--选项界面-->
<TextView
          android:id="@+id/degree_name" />

<ImageView
           android:id="@+id/degree_icon" />
```

```java
Spinner mySpinner = findViewById(R.id.mySpinner);

List<Map<String, Object>> degrees = new ArrayList<>(); //选项的内容
String[] degreeName = {"学士", "硕士", "博士"};
int[] degreeIcon = {R.drawable.bachelor, R.drawable.master, R.drawable.doctor}; 
for (int i = 0; i < degreeName.length; i++) {
    Map<String, Object> item = new HashMap<>();
    item.put("name", degreeName[i]);
    item.put("icon", degreeIcon[i]);
    degrees.add(item);
}

adapter.setDropDownViewResource(R.layout.myspinner_item);
mySpinner.setAdapter(new SimpleAdapter(this, degrees, R.layout.myspinner_item, new String[]{"name", "icon"}, new int[]{R.id.degree_name, R.id.degree_icon}));

mySpinner.setPrompt("选择你的学位："); //选项界面标题

mySpinner.setSelection(1); //设置选中的选项
```

## 事件监听



