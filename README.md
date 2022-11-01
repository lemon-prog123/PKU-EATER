# software_class

# BackEnd

后端框架（服务器）

基于**Maven+Mybatis+SpringBoot**

## 基本运行方式

运行方式：使用IDEA打开，点击右侧栏Maven，再点击左上角刷新图标下载相关依赖，下载需要一段时间，记得等待右下角进度条下载完毕后再运行。

构建/运行选项选择App则直接运行，在浏览器输入地址`localhost:8080`可以得到Hello World（或者我们正在输出的调试信息）

处于调试阶段的页面可用浏览器输入地址访问，为GET请求。此后大部分功能均使用POST表单，编码格式`application/x-www-form-urlencoded`，POST用法详见前端代码`post.kt`

**目前测试代码已上载服务器，访问 [http://47.94.139.212:8080/](http://47.94.139.212:8080/) 即可得到Hello World**

若希望修改数据库，请下载数据库管理工具如Mysql Workbench远程连接服务器的数据库，配置不清楚的话可咨询FKL和MJY（雾）。

构建方法：点击IDEA右侧栏Maven，依次点开quickstart，生命周期，双击clean等待完成后，再双击package即可。`.jar`包生成在`target`文件夹下。

## 可用url

**用户信息**

`/user/get?id=1`

返回的信息详见json

**注册**

`/user/register?name=user2&gender=2`

用户名要求不能重复注册

**登录**

`/user/login?name=user2&password=23333`

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

**菜品信息修改**

`/user/update`

参数：

id：菜品id，该参数不可缺省，自增

window：窗口号，int类型

name：菜品名称，string类型

canteen_id：所在食堂id，int类型（范围还未进行限制，应根据前端设置的选项设置）

calorie：卡路里，int类型

avoidance：忌口，int类型，以二进制串的形式存储每一项忌口

price：价格，int类型，以分为单位存储，输出时乘100输出，手动加点，避免精度问题

imgaddr：图片url，以文件形式存储，string类型

type: 菜品分类，int类型，同样还没有设置范围

**图片url**

图片url的映射文件夹暂时采用绝对路径（见`application.properties`），因为我不会JAVA的相对路径（别在意细节了）

`/foodimg/test.jpg`，其中`test.jpg`替换为对应文件名可访问菜品图片url。

## 其他运行方式

修改数据库后，需要在构建/运行选项选择mybatis-generator可以生成数据库对应的dataObject类以及Mapper。

请务必确认`pom.xml`中的`<overwrite>`标签为false时再运行。

# 待修复BUG

校验中的问题（可不修）：`@NotBlank`无论如何都会报错, 传参缺少的情况下会直接报未知错误，`@NotNull`不生效。

session的用法（如何产生cookie）

菜品属性中的canteen_id和type需要根据前端设置的选项来设定范围规范输入
