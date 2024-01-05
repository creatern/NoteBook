
# Android简述

## 平台架构

<img title="" src="../../pictures/android_stack_2x.png" alt="" width="600"> 

## 项目结构

<img title="" src="../../pictures/Android-AndroidProjectStruct.drawio.svg" alt="" width="655"> 

# 界面编程

## AndroidMainifest.xml

- Android界面注册文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.dell.mytabhost">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".TabHostActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".FirstPageActivity" />
        <activity android:name=".SecondPageActivity" />
        <activity android:name=".ThirdPageActivity" />
        <activity android:name=".FourPageActivity" />
        <activity android:name=".FivePageActivity" />
    </application>

</manifest>
```

## 布局容器

### Constraint Layout 限制布局

| 约束   | 说明                                     |
| :----- | :--------------------------------------- |
| `<<<`  | wrap content，容器跟内容的宽度变化而变化 |
| `---`  | 固定的宽度或高度值                       |
| `-^v-` | match constraint                         |

### LinearLayout 线性布局

<table>
    <tr>
        <th width="20%">属性</th>
        <th width="80%" colspan="2">意义</th>
    </tr>
    <tr>
        <td rowspan="3"><code>android:orientation</code></td>
        <td colspan="2">排列方向</td>
    </tr>
    <tr>
        <td width="20%">vertical</td>
        <td width="60%">垂直</td>
    </tr>
    <tr>
        <td>horizontal</td>
        <td>水平</td>
    </tr>
</table>

```xml
<LinearLayout
              android:id="@+id/myLinearLayout"
              android:orientation="vertical">
	<!--其他的控件元素-->
</LinearLayout>
```

## 基础控件

### View 视图

- 视图文件（View）：此处的视图（View）仅仅是指在App界面上无法直接编辑的控件，即一定程度上的只读。

#### TextView 文本视图

<table>
    <tr>
        <td width="35%">常用的属性</td>
        <td width="10%">意义</td>
        <td width="25%">控制的方法</td>
        <td width="30%">意义</td>
    </tr>
    <tr>
        <td rowspan="2"><code>android:text</code></td>
        <td rowspan="2">文本内容</td>
        <td>getText()</td>
        <td>获取文本内容（String）</td>
    </tr>
    <tr>
        <td>setText()</td>
        <td>设置文本内容（String）</td>
    </tr>
</table>

##### 滚动文本

#### ImageView 图像视图

<table>
    <tr>
        <th width="35%">常用的属性</th>
        <th width="10%">意义</th>
        <th width="25%">控制的方法</th>
        <th width="30%">意义</th>
    </tr>
    <tr>
        <td rowspan="2"><code>app:srcCompat="@drawable/文件前缀"</code></td>
        <td rowspan="2">图像URI</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
    </tr>
</table>

#### ImageTextView 图像文本视图

#### AutoCompleteTextView 自动完成文本视图

- AutoCompleteTextVIew：用户在该文本框中输入内容时，`AutoCompleteTextView`会根据已设置的数据源（如数组、数据库查询结果等）动态匹配并显示可能的建议选项列表，用户可以选择列表中的项来快速完成输入

<table>
    <tr>
        <th width="20%">属性</th>
        <th width="80%" colspan="2">意义</th>
    </tr>
    <tr>
        <td><code>android:completionThreshold</code></td>
        <td colspan="2">触发补齐的字符数，默认为2</td>
    </tr>
    <tr>
        <td><code>android:hint</code></td>
        <td>提示信息（相当于placehold）</td>
    </tr>
    <tr>
        <td><code>android:inputType</code></td>
        <td>输入类型</td>
    </tr>
    <tr>
        <td><code>android:maxLines</code>
        </td>
        <td>控制输入框最大显示行数</td>
    </tr>
</table>

```xml
<AutoCompleteTextView
                      android:id="@+id/my_autocomplete"
                      android:completionThreshold="1"
                      android:hint="请输入搜索关键词"
                      android:inputType="text|textAutoComplete"
                      android:maxLines="1"/>
```

```xml
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/myKeyword"
    android:layout_width="match_parent"
    android:layout_height="40dp"
    android:gravity="left"
    android:singleLine="true"
    android:textColor="#4CAF50"
    android:textSize="24sp"
/>
```

```java
private Drawable myDrawable;
private AutoCompleteTextView myAutoCompleteTextView;
private String[] keyWords = {"ZG", "ZGM", "ZGNP", "ZGJZ", "ZHMZWDFX", "ZHRMGHGJG70ZN"};
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    myAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.my_autocomplete");
    myDrawable = getResources().getDrawable(R.drawable.search);
    myDrawable.setBounds(0, 0, myDrawable.getMinimumWidth(), myDrawable.getMinimumHeight());
    myAutoCompleteTextView.setCompoundDrawables(myDrawable, null, null, null);	//顶行首显示
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.keyword_item, keyWords);
    myAutoCompleteTextView.setAdapter(adapter);
}
```

### Button 按钮

#### Button 普通按钮

<table>
    <tr>
        <td width="35%">常用的属性</td>
        <td width="10%">意义</td>
        <td width="25%">控制的方法</td>
        <td width="30%">意义</td>
    </tr>
    <tr>
        <td><code>android:enabled="true"</code></td>
        <td>是否可用</td>
        <td>setEnabled(boolean f)</td>
        <td>设置是否可用</td>
    </tr>
</table>

#### ImageButton 图像按钮

#### ToggleButton

### 选项/列表

#### CheckBox 复选框

- <span name="CheckBox">CheckBox</span>：复选框的选项，每个CheckBox之间其实没有任何联系，Java需要分别提取不同的CheckBox控件，以提供事件监听（[setOnCheckedChangeListener](#setOnCheckedChangeListener)）或获取数据等操作，而不是直接提取提供复选组。

```xml
<CheckBox    
          android:id="@+id/myCheckBox_sz"
          android:button="@null"
          android:drawableLeft="@drawable/checkbox_style"
          android:text="苏州"/>
```

<table>
    <tr>
        <th width="50%">事件</th>
        <th width="50%">意义</th>
    </tr>
    <tr>
        <td><code>onCheckedChanged</code></td>
        <td>选中状态改变</td>
    </tr>
</table>

#### RadioGroup 单选组

- <span name="RadioGroup">RadioGroup </span>：

```xml
<RadioGroup
            android:id="@+id/myRadioGroup">
    <RadioButton
                 android:id="@+id/myRadioButton_nj"
                 android:button="@null"
                 android:drawableLeft="@drawable/radiobutton_style"
                 android:text="南京" />
    <RadioButton
                 android:id="@+id/myRadioButton_sz"
                 android:button="@null"
                 android:drawableLeft="@drawable/radiobutton_style"
                 android:text="苏州"/>
</RadioGroup>
```

- 单选组的事件监听接口（`new RadioGroup.OnCheckedChangeListener() `）会接收两个参数，第一个是当前的单选组对象；第二个则是int类型的单选组内元素id（`R.id.myRediobutton_one`）

<table>
    <tr>
        <th width="50%">事件</th>
        <th width="50%">意义</th>
    </tr>
    <tr>
        <td><code>onCheckedChanged</code></td>
        <td>选中状态改变</td>
    </tr>
</table>

#### Spinner 列表选项

- Spinner：列表选项控件，其选项需要在特定的模板文件中编辑，并通过在Java中的代码进行选项绑定和事件监听等操作

<table>
    <tr>
        <th width="20%">属性</th>
        <th width="80%" colspan="2">意义</th>
    </tr>
    <tr>
        <td rowspan="3"><code>android:spinnerMode</code></td>
        <td colspan="2">菜单样式</td>
    </tr>
    <tr>
        <td>dropdown</td>
        <td>默认，下拉列表</td>
    </tr>
    <tr>
        <td width="20%">dialog</td>
        <td width="60%">弹出一个对话框样式的选择器</td>
    </tr>
</table>

<table>
    <tr>
        <th width="50%">事件</th>
        <th width="50%">意义</th>
    </tr>
    <tr>
        <td><code>OnItemSelectedListener</code></td>
        <td>用户选择一个新的项时触发回调</td>
    </tr>
</table>

```xml
<Spinner
         android:id="@+id/mySpinner"
         android:spinnerMode="dialog"/>
```

```xml
<!-- myspinner_item文件用于定义每一个选项的模板即可，也就是定义控件的样式和选项中需要的控件 -->
<ImageView
           android:id="@+id/myItem_icon"/>
<TextView
          android:id="@+id/myItem_name"/>
```

```java
// 在Activity等中执行Spinner控件和其Item的绑定与事件监听等操作
private Spinner mySpinner = findViewById(R.id.mySpinner);
private TextView itemTxt = findeViewById(R.id.myItem_name);
private ImageView itemImg = findeViewById(R.id.myItem_icon);

// 定义用于映射的数据
List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
// 提供需要的选项数据
for (int i = 0; i < degreeName.length; i++) {
    Map<String, Object> item = new HashMap<String, Object>();
    item.put("name", myNames[i]);
    item.put("icon", myIcons[i]);
    itemList.add(item);
}

// 该适配器负责将数据绑定到指定的Spinner项布局，并指定了哪些数据映射到布局中的哪些视图ID
SimpleAdapter adapter = new SimpleAdapter(this, itemList, R.layout.myspinner_item, new String[]{"name", "icon"}, new int[]{R.id.myItem_name, R.id.myItem_icon});
// 适配器绑定下拉列表项的显示样式资源（即绑定选项文件myspinner_item.xml）
adapter.setDropDownViewResource(R.layout.myspinner_item);

// 设置提示信息
mySpinner.setPrompt("请选择学历");
// 设置mySpinner的适配器
mySpinner.setAdapter(adapter);
// 设置Spinner默认选中的项目索引位置（下标从0开始）
mySpinner.setSelection(1);

// 设置mySpinner的选项点击事件
mySpinner.setOnItemSelectedListener(v -> {

});
```

### EditText 文本编辑

<table>
    <tr>
        <th width="35%">常用的属性</th>
        <th width="65%">意义</th>
    </tr>
    <tr>
        <td rowspan="2"><code>app:inputType="numberDecimal"</code></td>
        <td>输入源的数据类型限定</td>
    </tr>
    <tr>
        <td>numberDecimal</td>
    </tr>
</table>

```xml
<EditText
          android:id="@+id/myEditText"
          android:background="@drawable/editext_style"
          android:gravity="center|center_vertical"
          android:inputType="numberDecimal"
          android:textCursorDrawable="@android:color/black"
          android:textSize="36sp"
          android:textStyle="bold" />
```

<table>
    <tr>
        <th width="50%">事件</th>
        <th width="50%">意义</th>
    </tr>
    <tr>
        <td><code>afterTextChanged</code></td>
        <td>文本内容改变</td>
    </tr>
    <tr>
        <td><code>setOnKeyListener</code></td>
        <td>键盘按键</td>
    </tr>
</table>


### Toast 消息提醒

- Toast：消息提醒弹窗是通过在Java中定义的事件监听中的预计生成的

```java
// Toast
Toast.makeText(this, "message", Toast.LENGTH_LONG).show();
```

### 日期时间选择器

#### DatePicker 日期选择器

<table>
    <tr>
        <th width="30%">事件</th>
        <th width="70%" colspan="2">意义</th>
    </tr>
    <tr>
        <td rowspan="45"><code>setOnDateChangedListener</code></td>
        <td colspan="2">日期选择改变</td>
    </tr>
    <tr>
        <td>DatePicker view</td>
        <td>该日期选择器控件</td>
    </tr>
    <tr>
        <td>int year</td>
        <td>日期选择器选择的年</td>
    </tr>
    <tr>
        <td>int month</td>
        <td>日期选择器选择的月</td>
    </tr>
    <tr>
        <td>int day</td>
        <td>日期选择器选择的日</td>
    </tr>
</table>


```java
// 生成弹窗式的日期选择器，可在事件中设置
DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, DateListener, myYear, myMonth, myDay);                         
datePickerDialog.show();
```

#### TimePicker 时间选择器

<table>
    <tr>
        <th width="30%">事件</th>
        <th width="70%" colspan="2">意义</th>
    </tr>
    <tr>
        <td rowspan="4"><code>setOnTimeChangedListener</code></td>
        <td colspan="2">时间选择改变</td>
    </tr>
    <tr>
        <td>TimePicker view</td>
        <td>该时间选择器控件</td>
    </tr>
    <tr>
        <td>int hour</td>
        <td>时间选择器选择的小时</td>
    </tr>
    <tr>
        <td>int minute</td>
        <td>时间选择器选择的分钟</td>
    </tr>
</table>

# Java控件管理

## 基础控制

### findViewById 控件查找

- <span name="findViewById">标签变量绑定</span>： <a href="#Resources">R</a>代表resource目录的类
- `R.id.tv_name`返回resource目录下id为`tv_name`的控件id（int）并通过该数据的枚举来找出对应的控件。

```java
// 从对应的界面文件根据控件id来获取控件对象；不需要强转，自动匹配控件类型。
TextView tvName = findViewById(R.id.tv_name);
```

```xml
<!-- 对应界面文件的该控件的id属性 -->
android:id="@+id/tv_name"
```

### Listener 事件监听

<table>
    <tr>
        <th width="60%">常用的事件监听</th>
        <th width="40%">对应的事件</th>
    </tr>
    <tr>
        <td><code>setOnClickListener</code></td>
        <td>点击</td>
    </tr>
    <tr>
        <td><code>setOnLongClickListener</code></td>
        <td>长点击</td>
    </tr>
    <tr>
        <td><span name="setOnCheckedChangeListener"></span><code>setOnCheckedChanged</code></td>
        <td><a href="#CheckBox">复选框</a>或<a href="#RadioGroup">单选组</a>的选中状态改变</td>
    </tr>
</table>

### Resources 资源目录

- <span name="Resources">resources</span>目录：对应于Java中的R类型对象，<a href="#findViewById">findViewById</a>等查找控件的方法都是基于该类型对象的

#### Drawable 图像文件

```java
// 从resources目录下的drawable目录中根据文件前缀来获取对应的文件
getResources().getDrawable(R.drawable.yuan_1);
```

## Adapter 适配器

## Acitivity 活动类

### Intent 数据传递

<img src="../../pictures/20240105134930.png" width="450"/> 

<table>
    <tr>
        <td rowspan="3" width="10%">Activity</td>
        <td colspan="2">活动页</td>
    </tr>
    <tr>
        <td><code>new Intent(this, ToActivity.class);</code></td>
        <td>创建 Intent 并设置目标 Activity</td>
    </tr>
    <tr>
        <td width="30%"><code>getIntent()</code></td>
        <td width="60%">获取接收到的Intent对象</td>
    </tr>
    <tr>
        <td rowspan="4">Intent</td>
        <td colspan="2">信使对象</td>
    </tr>
    <tr>
        <td><code>putExtra("key", value)</code></td>
        <td>将需要传递的数据以 key-value 存入Intent，这些数据会被封装到一个Bundle</td>
    </tr>
    <tr>
        <td><code>putExtra(bundle)</code></td>
        <td>直接将装好数据的Bundle作为Intent内封装的Bundle</td>
    </tr>
    <tr>
        <td><code>getExtras()</code></td>
        <td>获取当前Intent对象内封装的Bundle，通过该Bundle来获取<code>putExtra</code>方法存入的数据</td>
    </tr>
    <tr>
        <td>Bundle</td>
        <td colspan="2">有序的 Key-Value 集合，通常用于存储数据以提供给Intent传递</td>
    </tr>
</table>


```java
private Intent intent = new Intent(this, ToActivity.class);
private Bundle bundle = new Bundle();

btnSub.setOnClickListener(v -> {
    bundle.putDouble("a", Double.parseDouble(eA.getText().toString()));

    intent.putExtras(bundle);

    startActivity(intent)
});
```

```java
private Intent intent = new Intent(this, FromActivity.class);
private Bundle bundle = getIntent().getExtras();

bundle.putString("x", "1");

intent.putExtras(bundle);

finish();
```

### Activity 页面跳转

<table>
    <tr>
        <td width="50%"><code>startActivity(intent)</code></td>
        <td width="50%">根据Intent内的信息，执行启动新的目标Activity、传递数据等操作</td>
    </tr>
    <tr>
        <td><code>finish()</code></td>
        <td>关闭自己以返回结果给发起请求的 Activity</td>
    </tr>
    <tr>
        <td><code>startActivityForResult(intent, requestCode)</code></td>
        <td>启动新的 Activity，并期待结果返回；</td>
    </tr>
    <tr>
        <td><code>setResult(Activity.RESULT_OK, returnIntent);</code></td>
        <td>设置结果码和结果Intent，此时并没有结束当前页面</td>
    </tr>
    <tr>
        <td rowspan="4"><code>onActivityResult(requestCode, resultCode, data)</code></td>
        <td>重写该方法，以处理收到的结果码和结果Intent</td>
    </tr>
    <tr>
        <td><code>int requestCode</code> 当前页面发出的请求码</td>
    </tr>
    <tr>
        <td><code>int resultCode</code> 当前页面接收到的结果码</td>
    </tr>
    <tr>
        <td><code>Intent data</code> 当前页面接收到的结果Intent</td>
    </tr>
</table>

<img src="../../pictures/Android-Activity交互流程.drawio.svg" width="600"/> 

# 高级控件

## TabHost 标签页

```xml
<TabHost xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@android:id/tabhost"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent" >

        <FrameLayout
            android:id="@android:id/tabcontent"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_marginBottom="@dimen/tabbar_height" />

        <TabWidget
            android:id="@android:id/tabs"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:visibility="gone" />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="@dimen/tabbar_height"
            android:layout_alignParentBottom="true"
            android:gravity="bottom"
            android:orientation="horizontal" >

            <LinearLayout
                android:id="@+id/first_linear"
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:orientation="vertical" >

                <TextView
                    style="@style/TabLabel"
                    android:drawableTop="@drawable/first_tab_selector"
                    android:text="@string/first_tab" />
            </LinearLayout>

            <LinearLayout
                android:id="@+id/second_linear"
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:orientation="vertical" >

                <TextView
                    style="@style/TabLabel"
                    android:drawableTop="@drawable/second_tab_selector"
                    android:text="@string/second_tab" />
            </LinearLayout>

            <LinearLayout
                android:id="@+id/third__linear"
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:orientation="vertical" >

                <TextView
                    style="@style/TabLabel"
                    android:drawableTop="@drawable/third_tab_selector"
                    android:text="@string/third_tab" />
            </LinearLayout>

            <LinearLayout
                android:id="@+id/four_linear"
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:orientation="vertical" >

                <TextView
                    style="@style/TabLabel"
                    android:drawableTop="@drawable/four_tab_selector"
                    android:text="@string/four_tab" />
            </LinearLayout>

            <LinearLayout
                android:id="@+id/five_linear"
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:orientation="vertical" >

                <TextView
                    style="@style/TabLabel"
                    android:drawableTop="@drawable/five_tab_selector"
                    android:text="@string/five_tab" />
            </LinearLayout>
        </LinearLayout>

    </RelativeLayout>

</TabHost>
```

```java
package com.example.dell.mytabhost;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TabHost;

public class TabHostActivity extends TabActivity implements OnClickListener {
	private TabHost myTabHost;
	private LinearLayout first_linear, second_linear, third_linear, four_linear, five_linear;
	private String TAG1 = "first";
	private String TAG2 = "second";
	private String TAG3 = "third";
	private String TAG4 = "four";
	private String TAG5 = "five";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_host);
		first_linear = findViewById(R.id.first_linear);
		first_linear.setOnClickListener(this);
		second_linear = findViewById(R.id.second_linear);
		second_linear.setOnClickListener(this);
		third_linear = findViewById(R.id.third__linear);
		third_linear.setOnClickListener(this);
		four_linear = findViewById(R.id.four_linear);
		four_linear.setOnClickListener(this);
		five_linear = findViewById(R.id.five_linear);
		five_linear.setOnClickListener(this);
		myTabHost = getTabHost();
		myTabHost.addTab(myTabHost.newTabSpec(TAG1).setIndicator(getString(R.string.first_tab), getResources().getDrawable(R.drawable.first_tab_selector)).setContent(new Intent(this, FirstPageActivity.class)));
		myTabHost.addTab(myTabHost.newTabSpec(TAG2).setIndicator(getString(R.string.second_tab), getResources().getDrawable(R.drawable.second_tab_selector)).setContent(new Intent(this, SecondPageActivity.class)));
		myTabHost.addTab(myTabHost.newTabSpec(TAG3).setIndicator(getString(R.string.third_tab), getResources().getDrawable(R.drawable.third_tab_selector)).setContent(new Intent(this, ThirdPageActivity.class)));
		myTabHost.addTab(myTabHost.newTabSpec(TAG4).setIndicator(getString(R.string.four_tab), getResources().getDrawable(R.drawable.four_tab_selector)).setContent(new Intent(this, FourPageActivity.class)));
		myTabHost.addTab(myTabHost.newTabSpec(TAG5).setIndicator(getString(R.string.five_tab), getResources().getDrawable(R.drawable.five_tab_selector)).setContent(new Intent(this, FivePageActivity.class)));
		setTabView(first_linear);
	}

	@Override
	public void onClick(View view) {
		setTabView(view);
	}

	private void setTabView(View v) {
		first_linear.setSelected(false);
		second_linear.setSelected(false);
		third_linear.setSelected(false);
		four_linear.setSelected(false);
		five_linear.setSelected(false);
		v.setSelected(true);
		if (v == first_linear) {
			myTabHost.setCurrentTabByTag(TAG1);
		} else if (v == second_linear) {
			myTabHost.setCurrentTabByTag(TAG2);
		} else if (v == third_linear) {
			myTabHost.setCurrentTabByTag(TAG3);
		} else if (v == four_linear) {
			myTabHost.setCurrentTabByTag(TAG4);
		} else if (v == five_linear) {
			myTabHost.setCurrentTabByTag(TAG5);
		}
	}
}
```

## GridView 网格视图

# SQLite

- SQLite：嵌入式关系型数据库管理系统，以单个文件形式存储数据，跨平台兼容，并全面支持SQL标准及事务处理等功能；在Android开发中，SQLite用于本地设备上存储结构化数据，实现离线功能并有效管理数据持久化。

<table>
    <tr>
        <td width="10%">SQLiteDatabase</td>
        <td width="90%">Android SDK 中用于操作 SQLite 数据库的核心类，它提供了丰富的接口来执行 SQL 语句、管理数据库版本以及进行数据的增删改查等操作。在 Android 应用开发中，通常通过继承 SQLiteOpenHelper 类并重写其方法来获取 SQLiteDatabase 实例。</td>
    </tr>
    <tr>
        <td>SQLiteOpenHelper</td>
        <td>Android 系统中用于操作 SQLite 数据库的一个辅助类，封装了数据库的创建、版本管理以及打开关闭等操作。开发者在使用 SQLite 数据库时，通常需要继承 SQLiteOpenHelper 类并重写部分方法来实现对数据库的操作。</td>
    </tr>
</table>


```java
public class MySQLiteAdapter {
    // 声明数据库的基本信息
    private static final int DB_VERSION = 1; // 数据库版本
    private static final String SQLITE_NAME = "prophet";// 数据库名称

    // 声明操作Sqlite数据库的实例
    private SQLiteDatabase sqliteDb;
    private MyDBOpenHelper sqliteHelper;

    //构造方法
    public MySQLiteAdapter(Context context) {
        sqliteHelper = new MyDBOpenHelper(context, SQLITE_NAME, null, DB_VERSION);
        sqliteDb = sqliteHelper.getWritableDatabase();// 获得可写的数据库，此时有执行OnCreate方法
    }

    //自定义的帮助类，继承SQLitOpenHelper
    private static class MyDBOpenHelper extends SQLiteOpenHelper {
        public MyDBOpenHelper(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, dbname, factory, version);
        }

        // 创建数据时执行的操作
        @Override
        public void onCreate(SQLiteDatabase db) {
            // 创建数据库时执行的SQL语句
            db.execSQL("drop table if exists user");
            db.execSQL("create table user(id varchar(100) primary key, name varchar(255),password varchar(255))");
            db.execSQL("insert into user values('admin','admin','admin')");
        }

        // 再次打开数据库时执行的操作
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // 重新执行onCreate方法内定义的操作
            onCreate(db);
        }
    }

    //获取SQLite数据库实例
    public SQLiteDatabase getSqliteDb() {
        return sqliteDb;
    }
}
```

```java
public class SuperDao {
    private MySQLiteAdapter mySqLiteAdapter = null;
    private SQLiteDatabase sqLiteDb = null;

    public Cursor getOne(Context context, String tableName, String[] fields) {
        mySqLiteAdapter = new MySQLiteAdapter(context);
        //创建SQLiteAdapter对象，得到SQLite实例
        sqLiteDb = mySqLiteAdapter.getSqliteDb();
        // //获取注册的用户数据
        Cursor cursor = sqLiteDb.query(tableName, fields, null, null, null, null, null);
        return cursor;
    }
}
```

```java
public class UserDao extends SuperDao {

    private static final String TABLE_NAME = "user";
    private static final String[] FIELDS = {"id", "name", "password"};

    public User getUser(Context context) {
        Cursor cursor = getOne(context, TABLE_NAME, FIELDS);
        // 默认用户
        User user = new User("C10001", "Common", "1234");
        String id;
        String name;
        String password;
        // 获取用户数据
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            id = cursor.getString(0);
            name = cursor.getString(1);
            password = cursor.getString(2);
            user = new User(id, name, password);
        }
        return user;
    }

}
```

