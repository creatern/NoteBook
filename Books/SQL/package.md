## PL/SQL软件包

将相关的PL/SQL数据类型，变量，数据结构，异常和子程序捆绑在一起放入一个容器中（软件包）；

- PL/SQL软件包将逻辑上相关的组件放在一起
  - PL/SQL数据类型
  - 变量，数据结构和异常
  - 子程序（过程和函数）

- PLSOL软件包通常是由两部分所组成，分别是：软件包说明部分，软件包体部分（可选的）。
- 软件包本身不能被调用，也不能被参数化，更不能被嵌套。
  - 但是软件包中的子程序（过程或函数)是可以被调用的，当然软件包中的变量等也可以被引用。
  - 当一个软件包编译成功之后，其中的内容（如子程序或变量）就可以为许多应用程序所共享。

- 与存储过程或存储函数有所不同，当一个软件包中的内容（如一个过程、一个函数或一个变量）被第一次引用时，整个软件包都会被装入内存。而后续对相同软件包的访问（哪怕是不同的结构，如不同的函数、不同的过程、不同的常量等)都是直接访问内存，不需要磁盘输入输出(IO)操作。
  - 因此与使用单独的存储过程或存储函数相比，软件包可以明显提高系统的效率。将常用的一些软件包常驻内存以进一步提高系统整体的效率。牺牲一些内存空间，这是一个典型的以空间换时间的优化方法。
- 创建过程和函数的方法都是将过程和函数作为独立的模式对象存储在数据库中的，即所谓的存储过程和存储函数，而PL/SQL软件包则提供了一种替代这种存储过程与函数的方法，而且软件包还具有一些其他的优点，其主要优点如下：

1. 模块化和更易于维护：
   - 将逻辑相关的程序结构封装在一个命名的模块（软件包）中。
   - 这样每一个软件包更容易理解，并且软件包之问的接口变得简单、清晰、易于辨认和理解。
2. 更易于应用程序的设计：
   - 软件包的说明部分和体部分的编码和编译可以分开。
   - 在开始设计程序时，只需要软件包中接口（界面）信息的说明，可以在没有程序体的情况下开发和编译软件包说明部分的代码。
   - 因此，引用这个软件包的存储子程序也可以进行编译，直到准备完成应用程序之前都不需要定义软件包的体（程序的逻辑流程）。
3. 隐藏信息：
   - 用户来决定哪些程序结构是公共的（可见的和可以访问的）而哪些是私有的(隐藏的和不能访问的)。
   - 只有在软件包的说明中声明的结构对应于程序才是可见和可以访问的。
   - 因为软件包体隐藏了私有结构的定义，所以如果该定义发生了变化，只有这一个软件包受影响（不会影响应用程序或任何调用程序)。这就使用户能够在改变软件包实现的同时而不需要重新编译调用程序。
   - 通过将软件包的实现细节隐藏起来（用户无法知道软件包实现的细节），可以保护软件包的一致性
4. 附加了额外的功能：
   - 软件包的公共变量和游标在整个会话期间是持续的，
   - 因此，在这个环境中执行的所有子程序都可以共享这些公共变量和游标。它们也使用户能够在不用将其存储在数据库中的情况下跨事务地维护数据。
   - 私有结构在整个会话期间也是持续的，但是它们只在软件包内部是可以访问的。
5. 较好的性能：
   - 当第一次引用软件包中的一个子程序时，整个软件包都被装入内存，因此在后续调用该软件包中的相关子程序时就不需要额外进行磁盘输入/输出(IO)操作了。
   - 对所有用户，软件包在内存中只有一份拷贝。软件包中的子程序也避免了子程序之间的级联依赖，因此也就避免了不必要的编译，也使得依赖的层次简单化
6. 重载：

  - 利用软件包，可以重载过程和函数，即在同一个软件包中多个子程序可以同名，每一个具有不同的参数数量或不同的参数数据类型。

### PL/SQL软件包的组件及可见性

**软件包说明(package specification):**

是应用程序的接口，在软件包说明中声明公共数据类型、变量、常量、异常、游标以及可以使用的子程序。软件包说明中也可以包括伪指令(PRAGMAS）——编译器指令。

**软件包体(package body):**

定义了自身的子程序并且必须完全实现在软件包说明部分中声明的子程序。软件包体也可以定义PL/SQL结构，如数据类型、变量、常量、异常和游标。.

- 公共组件是在软件包说明部分中定义的。说明部分为软件包的用户定义了一个公共的应用程序设计接口（Application Programming Interface,.API),以使用这一软件包的特性和功能，即公共组件可以在软件包之外的任何Oracle服务器环境中引用。
- 私有组件是放在软件包体之内的并且只能被同一软件包中的其他结构所引用。但是私有组件可以引用该软件包中的公共组件。

如果一个软件包的说明部分没有包含子程序声明，那么就不需要定义软件包体

**一个软件包组件的可见性是指这个组件是否可以被看见，即是否可以被其他的组件或对象所引用，软件包组件的可见性依赖于这些组件是声明为本地的还是全局的**

- 本地变量在它们被声明的结构之中是可见的，例如：

1. 在一个子程序中定义的变量只能在这个子程序中被引用，并且对于外部组件是不可见的，即本地变量local var只能在过程a中使用。
2. 在一个软件包体中声明的私有软件包变量可以被同一软件包体中的其他组件所引用，但是它们对于软件包之外的任何子程序或对象都是不可见的，即在软件包体中的过程a和过程b是可以使用私有变量private var的，但是软件包之外的子程序或对象就不可以使用了。

- 而全局声明的组件在软件包的内部和外部都是可见的，例如：

1. 在一个软件包说明部分声明的一个公共变量可以在软件包之外引用和修改，即公共变量public_var可以在软件包之外引用。
2. 在个软件包说明部分声明的一个软件包子程序可以被外部的程序代码所调用——过程a可以从软件包之外的一个环境中调用。

- 私有子程序（如过程b)只能被该软件包中的公共子程序调用（如过程Λ）或者由被该软件包中的其他私有软件包结构所调用。

### 软件包的开发

**原则：**

1. 将一个软件包说明的语句正文与这个软件包体的语句分开存放在两个脚本文件中，方便对软件包说明或软件包体的修改
2. 一个软件包说明可以在没有软件包体的情况下存在，即软件包说明可以不说明子程序也不需要包体。
   - 而如果软件包说明不存在是不能创建软件包体的。

- Oracle服务器将软件包说明和软件包体分开存放。
  - 从而在改变软件包体中某个程序的实现时不会使所调用或引用程序结构的其他模式对象变为无效。

**创建软件包的说明**

```
create or replace PACKAGE 包名 is|AS
    声明的公共变量，常量，游标，异常，用户定义的数据类型和子类型
    公共过程和函数的声明
end [包名];
```

**创建软件包体**



```
--软件包说明
create or replace PACKAGE salary_pkg is
  v_std_salary number := 1380;
  procedure reset_salary(p_new_sal number, p_grade number);
end salary_pkg;

--软件包体
create or replace PACKAGE BODY salary_pkg is
  FUNCTIon validate
  (
  p_sal number
  ,p_grade number
  ) 
  RETURN BOOLEAN 
  is
    v_min_sal   salgrade.losal%type;
    v_max_sal   salgrade.hisal%type;
  begin
    select losal, hisal 
    into   v_min_sal, v_max_sal
    from   salgrade
    where  grade = p_grade;
    RETURN (p_sal between v_min_sal and v_max_sal);
  end validate;

  procedure reset_salary
  (
  p_new_sal number
  ,p_grade number
  ) 
  is 
  begin
    if validate(p_new_sal, p_grade) then
      v_std_salary := p_new_sal;
    else RAisE_APPLICATIon_ERROR(-20038, '工资超限!');
    end if;
  end reset_salary;
end salary_pkg;
```

**软件包的调用**

```
SQL> execute salary_pkg.reset_salary(2450,4);
```

**查看错误**

```
SHOW ERRORS;
```

#### 创建和使用无体的PL/SQL软件包

- 无体软件包

在一个独立子程序中声明的变量和常量只在这个子程序执行期间存在。为了在一整个用户会话期间提供所存在的数据，可以创建一个公共（全局）变量和常量声明的软件包说明部分。在这个情况下不需要软件包体。

### 软件包的查找与删除

#### 查询

- 使用 user_objects 数据字典
  - PACKAGE
  - PACKAGE BODY

```
select object_id
      ,object_name
      ,object_type
      ,created
      ,status
from user_objects
where object_type IN ('PACKAGE','PACKAGE BODY');
```

#### 删除

- 删除包的说明和包体

```
DROP PACKAGE 包名;
```

- 只删除包体

```
DROP PACKAGE BODY 包名;
```

### 重载

- 需要子程序的形参在个数/顺序/数据类型不同
  - 同一个类型系列的数据类型视为相同数据类型（子类型） 
  - 与返回值类型无关
- 不能重载独立子程序

```
create or replace PACKAGE dept_pkg is
 procedure add_dept
   (
    p_dept_id IN copy_dept.department_id%type
   ,p_dept_name IN copy_dept.department_name%type
   ,p_loc_id IN copy_dept.location_id%type
   );
--重载过程add_dept
 procedure add_dept
   (
    p_dept_name IN copy_dept.department_name%type
   ,p_loc_id IN copy_dept.location_id%type
   );
end dept_pkg;
```

```
create or replace PACKAGE BODY dept_pkg is
 procedure add_dept
   (
    p_dept_id IN copy_dept.department_id%type
   ,p_dept_name IN copy_dept.department_name%type
   ,p_loc_id IN copy_dept.location_id%type
   )
   is
   begin
     insert into copy_dept(department_id,department_name,location_id)
     values(p_dept_id,p_dept_name,p_loc_id);
   end add_dept;
   
 procedure add_dept
   (
    p_dept_name IN copy_dept.department_name%type
   ,p_loc_id IN copy_dept.location_id%type
   )
   is
   begin
     insert into copy_dept(department_name,location_id)
     values(p_dept_name,p_loc_id);
   end add_dept;
end dept_pkg;
```

### STandARD软件包与子程序重载

- 定义了PL/SQL环境和PL/SQL程序可以自动获取的全局数据类型，异常和子程序的声明，在Oracle系统安装时自动安装。

- 查看：DESC STandARD

- 如果在一个PL/SQL程序中重新声明了一个与内置函数同名的函数(如：`TO_char()`)；则在调用内置函数时需要`STandARD.TO_char()`
- 如果重新声明的与内置函数同名的是一个独立子程序，则在访问该子程序时需要`用户名.TO_char()`


### 前向声明

- 在软件包体的开始部分声明一个子程序的头（说明），并以分号`；`结束。

**使用情况**

1. 按照逻辑或字母顺序调用子程序
2. 定义相互递归的子程序。
3. 在一个软件包体中将子程序按分组或逻辑的顺序存放

**注意**

1. 子程序的所有形参必须出现在前向声明中和子程序的体中
2. 在前向声明之后子程序体可以出现在任何地方，但是前向声明和子程序体必须出现在同一个程序单元中。
   - 通常子程序说明放在软件包的说明部分，公共子程序声明不需要前向声明。

**前向引用的问题**

- PL/SQL语言不允许前向引用的，在使用一个标识符之前必须先声明它。即先声明后引用的原则。
- 使用前向声明的方法解决。 

#### 例

```
create or replace PACKAGE sal_pkg is
 procedure reset_sal(p_sal number,p_grade number,p_id number);
end;
```

```
create or replace PACKAGE BODY sal_pkg is
--向前声明
  --检查工资是否符合要求
  FUNCTIon check_sal(p_sal number,p_grade number) RETURN BOOLEAN;
  --根据员工号码更新工资
  procedure update_sal_emp(p_sal number,p_id number);
  
  procedure reset_sal
    (
     p_sal number
    ,p_grade number
    ,p_id number
    )
    is
     sal_error exception;
    begin
      if check_sal(p_sal,p_grade) then
        update_sal_emp(p_sal,p_id);
        dbms_output.put_line('更新成功');
      else 
        RAisE sal_error;
      end if;
    exception
      when sal_error then
        dbms_output.put_line('更新失败');
        rollback;
      when OTHERS then
        dbms_output.put_line('其他错误');
        rollback;
    end reset_sal;
    
  FUNCTIon check_sal
    (
     p_sal number
    ,p_grade number
    )
    RETURN BOOLEAN
    is
     v_min_sal salgrade.losal%type;
     v_max_sal salgrade.hisal%type;
    begin
      select losal
            ,hisal
      into v_min_sal
          ,v_max_sal
      from salgrade
      where grade = grade;
      
      RETURN (p_sal between v_min_sal and v_max_sal);
    end check_sal;
    
   procedure update_sal_emp
     (
      p_sal number
     ,p_id number
     )
     is
     begin
       UPDATE copy_emp
       set salary = p_sal
       where employee_id = p_id;
     end update_sal_emp;
end sal_pkg;
```

### 软件包的初始化

- 当一个软件包中的某个组件被第一次引用时，整个软件包都被装入内存中，为整个用户会话所使用。
- 如果没有显式的初始化，则变量的初始值默认为空值null

**使用如下方法初始化软件包的变量**

1. 在软件包的说明中的变量声明中使用赋值操作
2. 对于复杂的初始化操作：一般在软件包体的末尾添加初始化代码块
   - 即使用一次性过程（在用户会话中第一次调用这个软件包时只执行一次的过程）
   - 如果使用了在软件包的说明中的变量声明中使用赋值操作，则会被软件包体末尾初始化代码所覆盖。

#### 例

```
create or replace PACKAGE select_emp is
  v_emp_dept copy_emp.department_id%type := 1;
  v_emp_name copy_emp.last_name%type;
  
  procedure emp_find(p_id number);
end;
```

```
create or replace PACKAGE BODY select_emp is
 procedure emp_find
   (
    p_id number
   )
   is
   begin
     select last_name
     into v_emp_name
     from copy_emp
     where employee_id = p_id;
   end emp_find;
   
--一次性过程：为软件包的变量赋初始值
  begin
    v_emp_name := 'KING';
    select department_id
    into v_emp_dept
    from copy_emp
    where employee_id = 117;
  --没有end；直接以软件包体的end结束
end select_emp;
```

```
execute dbms_output.put_line(select_emp.v_emp_dept);
execute dbms_output.put_line(select_emp.v_emp_name);
execute select_emp.emp_find(117);
```

### 在SQL中使用软件包中的函数

- 当执行一个调用存储函数（包括存储软件包中的函数）的SQL语句时，Oracle服务器必须指定这些存储函数的纯净级别（即这些函数有没有副作用）
- 通常这些副作用包括对数据库表中数据的修改或对软件包中公共变量（在软件包说明中声明的变量）的修改。控制副作用是非常重要的，因为这些副作用可能阻止正确的并行查询，产生顺序依赖并因此而产生不确定的结果，或需要一些越轨的操作（如在不同的用户会话中维护一个软件包的状态)。当在一个SQL查询语句或DML语句中调用一个函数时，一些限制是不允许出现在这个SQL语句中的。
- 一个函数所具有的副作用越少，则这个函数在一个查询语句中越容易优化，特别是当使用启示(Hint)PARALLEL_ENABLE或DETERMINisTIC时。如果要在SQL语句中调用一个存储函数,那么这个存储函数（以及任何它所调用的子程序）就必须遵守如下的纯净级别的规定：

1. 当在一个select语句或一个并行的DML语句中调用一个存储函数时，该函数不能更改数据库中任何表中的数据。
2. 当在一个DML语句中调用一个存储函数时，该函数不能查询也不能更改这个语句所更改的任何表
3. 当在-个select语句或一个DML语句中调用一个存储函数时，该函数不能执行SQL事务控制语句、会话控制语句和系统控制语句。

- 以上这些规则的目的就是控制函数使用的副作用。如果任何SQL语句中使用的函数体（程序代码）违反了以上的规则，该语句在执行时（在对这个语句进行词法分析时）将产生运行错误

### 软件包中变量的持续状态

公有和私有软件包变量的集合代表一个用户会话的软件包的状态，即在某一个指定的时间内存储在所有软件包变量中的值。通常，一个软件包的状态存在于用户会话的整个生命周期。

对于一个用户会话来说，当一个软件包被第一次装入内存时该软件包的变量被初始化。对于每一个会话，默认软件包变量都是唯一的并且这些变量的值一直保持到这个用户会话终止。换句话说，变量是存储在为每一个用户由数据库所分配的用户全局区(User Global Area,UGA)的内存中，当一个软件包中的子程序被调用，该子程序的逻辑变更了变量的状态时，软件包的状态就发生了变化。公共软件包的状态也可能直接由对其类型的适当操作所改变。

- PRAGMA代表这个语句是一个编译指令，由PRAGMA说明的语句是在编译期间处理的，而不是在运行时处理的。

- PRAGMA SERIALLY_RESUABLE指令
  - 这些指令并不影响一个程序的含义，它们仅仅将信息传输给编译器。如果在软件包说明中添加了PRAGMA SERIALLY_RESUABLE指令，那么Oracle数据库将软件包的变量存储在由多个用户会话共享的系统全局区(system GlobalArea,SGA)内存中。在这种情况下，软件包的状态被维护在一个子程序调用的生命周期中或对一个软件包结果的一个单一的引用生命周期中。如果想要节省内存并且如果不需要为每一个用户会话保持软件包的状态，那么SERIALLY_RESUABLE指令就非常有用。

当一个软件包需要声明大量的临时工作区，但是这些临时工作区只使用一次而且在同一个会话中后续数据库调用也不再需要它们时，PRAGMA指令非常适合。可以将一个无体软件包标记为串行可重用。但是如果一个软件包具有说明和体两部分，就必须标记说明和体两部分，不能只标记体。

串行可重用软件包的全局内存是统一存储在系统全局区(SGA)中的，而不是在每个用户所具有的用户全局区(UGA)中分配的，这样软件包的工作区就可以重用了。当对服务器的调用结束时，这些内存返回给SGA。每次软件包被重用时，它的公共变量都被初始化为它们的默认值或空值。

- 数据库触发器或在SQL语句中调用的PL/SQL子程序时不能访问串行可重用软件包的。否则产生错误

#### 例

### 软件包中游标的持续状态

- 与软件包中变量的持续状态相同，软件包中游标的持续状态时在整个会话期间跨越事物的。然而对于相同用户的不同会话（同一个用户的不同连接），它们的状态并不保持。

#### 例

```
create or replace PACKAGE emp_pkg is
 procedure open_emp;
 FUNCTIon next_employee(p_n number := 1) RETURN BOOLEAN;
 procedure close_emp;
end emp_pkg;
```

```
create or replace PACKAGE BODY emp_pkg is
 CURSOR emp_cursor is
  select employee_id
  from employees;
 
 procedure open_emp
   is
   begin
     if NOT emp_cursor%isOPEN then
       OPEN emp_cursor;
     end if;
   end open_emp;
   
 FUNCTIon next_employee
   (
    p_n number := 1
   )
   RETURN BOOLEAN 
   is
    v_emp_id employees.employee_id%type;
   begin
     for count IN emp_cursor loop
       FETCH emp_cursor 
       into v_emp_id;
       
       dbms_output.put_line('employee_id:'||v_emp_id);
     end loop;
     
     RETURN emp_cursor%FOUND;
   end next_employee;
   
 procedure close_emp 
   is
   begin
     if emp_cursor%isOPEN then
       CLOSE emp_cursor;
     end if;
   end close_emp;
end emp_pkg;
```

### 在软件包中使用PL/SQL记录表（记录数组）

- 可以在PL/SQL软件包中声明自定义的数据类型，在利用自定义的数据类型声明变量

#### 例

```
create or replace PACKAGE emp_dog is
 type emp_table_type is table OF employees%ROWtype
      INDEX by PLS_INTEGER;
 
 procedure get_emp(p_emp OUT emp_table_type);
end;
```

```
create or replace PACKAGE BODY emp_dog is
 procedure get_emp
   (
   p_emp OUT emp_table_type
   )
   is
    v_count BINARY_INTEGER := 1;
   begin
     for emp_record IN (select * from employees) loop
       p_emp(v_count) := emp_record;
       v_count := v_count + 1;
     end loop;
   end get_emp;
end;
```

### 标准化

#### 标准化异常和异常处理

- 创建一个标准的错误处理软件包，在该软件包中包括所有在应用程序中用户命名的和用户定义的异常
- 标准化异常处理既可以通过使用独立的存储子程序实现，也可以通过将一个子程序添加到定义标准异常的软件包中实现

**考虑：**

1. 在该程序中使用的每个命名的异常
2. 在这个程序中使用的无名或用户定义的异常。
3. 调用RAisE_APPLICATIon_ERROR的过程
4. 显示SQLCODE和SQLERRM的程序
5. 附加的对象（错误日志表等）

#### 标准化常量

- 创建一个独立的共享软件包，该软件包中包含了应用程序中所有的常量。
  - 只能在PL/SQL程序中使用，SQL语句不能使用
- 为提高数据库系统的效率  ： 该过程或软件包应该在数据库启动时就装入系统内存缓冲区

#### 本地子程序进行标准化

- 本地子程序：在一个程序（软件包/过程/匿名PL/SQL块）声明段的结尾定义的一个函数或过程
  - 如：在过程中定义使用另一个过程 
- 主要应用于：自上而下的设计方法。可以移除冗余的程序代码以减少程序模块的大小。
  - 如果一个 模块需要多次使用相同的一段代码，并且只有这个模块需要这些代码时。

**特点：**

1. 本地子程序只在定义他们的程序块中可以访问
2. 本地子程序作为包含他们的程序块的一部分被编译。
