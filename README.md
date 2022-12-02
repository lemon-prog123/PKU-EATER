# software_class

后端框架（服务器）

基于**Maven+Mybatis+SpringBoot**

# 基本运行方式

运行方式：使用IDEA打开，点击右侧栏Maven，再点击左上角刷新图标下载相关依赖，下载需要一段时间，记得等待右下角进度条下载完毕后再运行。

构建/运行选项选择App则直接运行，在浏览器输入地址`localhost:3000`可以得到Hello World（或者我们正在输出的调试信息）

处于调试阶段的页面可用浏览器输入地址访问，为GET请求。此后大部分功能均使用POST表单，编码格式`application/x-www-form-urlencoded`，POST用法详见前端代码`post.kt`

**目前测试代码已上载服务器，访问 [http://47.94.139.212:3000/](http://47.94.139.212:3000/) 即可得到Hello World**

若希望修改数据库，请下载数据库管理工具如Mysql Workbench远程连接服务器的数据库，配置不清楚的话可咨询FKL和MJY（雾）。

构建方法：点击IDEA右侧栏Maven，依次点开quickstart，生命周期，双击clean等待完成后，再双击package即可。`.jar`包生成在`target`文件夹下。

# 可用url

**所有“获取信息”均为GET方法，所有“请求功能”，包括在数据库中增加、删除、修改条目的，都为POST方法**

## 用户类

**用户信息**

`/user/get?id=1`

返回的信息详见json

**注册**

`/user/register?name=user2&gender=2`

用户名要求不能重复注册

注册时将对收到的报文密码采用凯撒密码加密的方式存入数据库

**登录**

`/user/login?name=user2&password=23333`

登录时也将对密码进行加密后在数据库比对

**用户信息修改**

`/user/update`

参数：

id：用户id，除该参数外其余参数均可缺省，该参数不可缺省（即不修改）

gender：性别，int类型

birthday：生日，string类型，格式2002-09-07

weight：体重，int类型

height：身高，int类型

avoidance：忌口，int类型，以二进制串的形式存储每一项忌口

budget：预算，int类型，表示选定的预算方案

state：锻炼状态，int类型，表示选定的锻炼状态

## 食物类

**按照id查找某一菜品的详情浏览**

`/food/get`

参数：id

**菜品列表浏览**

`/food/list`

**按照食堂查找菜品**

`/food/listbycanteen`

参数：id

但是输入的是食堂的id

# 食堂类

**特定食堂的详情浏览**

`/canteen/get`

参数：id

**食堂列表浏览**

`/canteen/list`

# 日志类

**添加日志**

参数：

uid：用户id

fid：食物id

meal:早/中/晚/加餐，int类型

若fid为0，表示自定义食物，则需填入calorie和price

`/journal/create?uid=1&fid=0&calorie=88&price=12345`

若fid为已有菜品id，则后两项自动填充

`/journal/create?uid=1&fid=1`

**展示特定用户的日志**

参数：uid

`/journal/listbyusr?uid=12`

**删除日志**

参数：id，uid。id为唯一标识日志号，可以通过listbyusr获得，uid为用户id，用于验证删除权限（假装这是个安全的cookie）

`/journal/delete?uid=1&id=48`

# 其他

**图片url**

图片url的映射文件夹暂时采用绝对路径（见`application.properties`），因为我不会JAVA的相对路径（别在意细节了）

目前设置了两张图片用于测试，可以访问 `http://47.94.139.212:3000/img/canteen/416.png` 和 `http://47.94.139.212:3000/img/food/416.png` 尝试一下。

## 其他运行方式

修改数据库后，需要在构建/运行选项选择mybatis-generator可以生成数据库对应的dataObject类以及Mapper。

请务必确认`pom.xml`中的`<overwrite>`标签为false时再运行。

# 待修复BUG

校验中的问题（可不修）：`@NotBlank`无论如何都会报错, 传参缺少的情况下会直接报未知错误，`@NotNull`不生效。

session的用法（如何产生cookie）

菜品属性中的canteen_id和type需要根据前端设置的选项来设定范围规范输入

