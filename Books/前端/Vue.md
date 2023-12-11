# Vue安装与入门

## Vue安装

### npm

## Vue入门

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>登录</title>
        <script type="application/javascript" th:src="@{/js/vue.js}"></script>
    </head>
    <body>
        
        <div id="login">
            <!-- {{field}} 插值语法（js表达式）
				1. a
				2. a + b
				3. sum(a, b)
				4. x === y ? 'a' : 'b'
			-->
            <!-- 指令语法
				v-bind: 绑定，可简写为 :
			-->
            <a v-bind:href="stu.url">你好，{{stu.name}}</a>
            <p>你好，{{name}}</p>
        </div>

        <script type="application/javascript">
            Vue.config.productionTip = false; // 阻止Vue在启动时生成生产提示

            //创建Vue实例 <Root>
            const v = new Vue({
                el: '#login', // el指定当前Vue服务的容器，可以通过css选择器来绑定
                data: { // 存储数据，一旦data内的数据改变，模板中的内容也更新
                    name: '李四'
                    stu: {
	                    name: '张三',
    	                url = 'http://localhost/home'                        
                    }
                }
            });
        </script>
    </body>
</html>
```

| 容器与Vue实例的对应关系 | 情况                                                     |
| ----------------------- | -------------------------------------------------------- |
| 一个容器对一个Vue实例   | 正常使用，通常配合组件使用                               |
| 多个容器对一个Vue实例   | 排在前面的容器获得数据（data），而后面的容器不能获得数据 |
| 一个容器对多个Vue实例   | 不允许，一个容器只能被一个Vue实例接管                    |

- Vue模板语法：

1. 插值语法 `{{field}}`：标签体；解析标签体内容，field为js表达式，且可以直接读取data内的所有属性
2. 指令语法 `v-bind:`等：标签属性；解析标签，包括标签属性、标签体内容、绑定事件等，js表达式的使用同插值语法

```js
new Vue({
    el: '#login', 
    data: { // 1.对象式
        name: '李四'                  
    }
});

const v = new Vue({
    // el: '#login', 
    data(){ // 2.函数式，搭配组件时使用
        return{
            name:'李四'
        }
    }
});

// el 绑定的另一种方式
v.$mount('#login');
```

## MVVM 模型 v-model:

<img src="../../pictures/20200204123438.png" width="500"/> 

> Model是位于请求的，对应于数据层（Spring中的Model），Vue和Spring（Servlet）操作的是同一个Model。

## Object.defineProperty()

```js
let person = {
    name: '张三',
    age: 18,
    address: '北京'
}

let sex = '男'

Object.defineProperty(person, 'sex', {
    // value: '男',
    // enumerable: true, // 默认是false，不被枚举
    // writable:true, // 默认是false，不可写（修改）
    // configurable: true, // 默认是false，不可配置（是否可以删除属性）

    // 数据代理
    get(){ // getter sex属性读取后，会触发get方法，返回sex变量
        return sex;
    },
    set(newVal){ // setter，sex属性修改后，会触发set方法，将newVal赋值给sex变量
        sex = newVal;
    }
})
```

<img src="../../pictures/20231209131920.png" width="550"/> 

## 事件处理 v-on: @

```html
<div id="login">
<input type="submit" value="登录" v-on:click.stop.prevent="showMessage($event,$account)"/></td> 
<!--事件绑定可简写-->
<button @click="account++"></button>
</div>

<script type="application/javascript">
    Vue.config.productionTip = false; // 阻止Vue在启动时生成生产提示

    const vm = new Vue({
        el: '#login',
        data: {
            account: '1054'
        },
        methods: {
            showMessage(event, account) {
                alert('正在登录...');
            }
        }
    });
</script>
```

| 事件修饰符 | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| prevent    | 阻止默认事件                                                 |
| stop       | 阻止事件冒泡（在引起冒泡的标签处添加）                       |
| once       | 事件只触发一次                                               |
| capture    | 事件的捕获模式                                               |
| self       | 只有event.target是当前操作的元素时，才触发事件（在需要阻止冒泡的标签处添加） |
| passive    | 事件的默认行为立即执行，无需等待事件回调执行完毕             |

- 对于按键事件（keyup、keydown）Vue提供如下方案：

1. 按键别名：enter、delete、esc、space、tab（keydown）、up、down、left、right
2. 原始的key值绑定
3. 系统修饰符：ctrl、alt、shift、meta。（1）keyup：按下修饰键的同时再按下其他键（keyup.ctrl.y），释放其他键后触发；（2）keydown：正常触发。
4. 可以通过e.target.value获取对应的e.keyCode（不建议，即将移除）
5. 定制按键别名：Vue.config.keyCodes.自定义键名 = 键码

## 计算属性 computed

```js
new Vue({
    el: '#login',
    data: {
        firstName: '张',
        lastName: '三'
    },
    computed: {
        fullName: {
            get() { //get()调用时机：（1）初次读取该属性时；（2）所依赖的数据发生变化时
                return this.firstName + ' ' + this.lastName;
            },
            set(newVal) { // 若要修改数据则需要setter，通常不修改计算属性
                //通过setter改变firstName和lastName，而firstName和lastName改变触发getter
                let names = newVal.split(' ');
                if (names.length === 2) {
                    this.firstName = names[0];
                    this.lastName = names[1];
                }
            }
        },
        // 可简写（不需要setter时）
        //fullName() {
        //    return this.firstName + ' ' + this.lastName;
        //}
    }
});
```

## 监视属性（侦听属性） watch

```js
const vm = new Vue({
    el: '#login',
    data: {
        account: '',
    },
    watch: { //监视（普通属性和计算属性）
        account:{
            immutable:true, //初始化时调用一次handler
            // 绑定的属性的值改变时调用handler
            handler(newVal,oldVal){
                console.log(oldVal + '被修改为' + newVal);
            }
        }
    }
    
    // 简写 1. 
    // watch: { //监视（普通属性和计算属性）
    //     account(newVal,oldVal){
    //         console.log(oldVal + '被修改为' + newVal);
    //     }
    // }
});

// 简写 2.
vm.$watch('account', {
    immutable:true,
    handler(newVal,oldVal){
        console.log(oldVal + '被修改为' + newVal);
    },
})

// 简写 3.
vm.$watch('account',function(newVal,oldVal){
    console.log(oldVal + '被修改为' + newVal);
})
```

### 深度监视

```js
const vm = new Vue({
    el: '#login',
    data: {
        numbers: {
            a: 1,
            b: 2
        }
    },
    // watch: {
    //     'numbers.a':{ //监测多级结构中某个具体属性的变化，需要使用正确的写法（字符串）
    //         immutable:true,
    //         handler(newVal,oldVal){
    //             console.log(oldVal + '被修改为' + newVal);
    //         }
    //     }
    // }
    watch: {
        numbers: {
            deep: true, // 开启深度监测，监测多级结构中每个属性的变化，默认false
            handler(newVal, oldVal) { // 此处的newVal和oldVal都是numbers，而非numbers.a和numbers.b
                console.log(newVal, oldVal);
            }
        }
    }
});
```

### watch与computed

- watch可以完成异步操作，而computed不行。

## 普通函数与箭头函数的采用

1. 所有被Vue管理的函数，应该写成普通函数，此时，this指向Vue的实例。
2. 所有不被Vue管理的函数（定时器的回调函数等），应该写成箭头函数（即箭头函数的this从外层找），此时，this仍然指向Vue。定时器的回调函数的this原本应该是window，而箭头函数使得其从外层查找this，也就是Vue实例，所以该箭头函数内的this仍然指向Vue。

## 绑定样式 v-bind:

### 绑定class样式 :class

- 动态指定样式的类名

```html
<div :class="tipsMessage" @click="showTips()"></div>

<script>
    const vm = new Vue({
        el: '#formContainer',
        data:{
            tipsMessage: 'tipsMessageHidden'
        },
        methods:{
            showTips(){
                this.tipsMessage = 'tipsMessageShow';
            }
        }
    });
</script>
```



### 绑定style样式