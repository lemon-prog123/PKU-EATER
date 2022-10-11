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

## 目前的get可用url

**用户信息查询**

`/user/get?id=1`

目前为调试用，仅仅支持GET表单（浏览器访问）

**注册**

`/user/register?name=user2&gender=2&age=2&password=23333`

用户名要求不能重复注册，性别年龄暂不能为空，性别为1（男性）或2（女性），年龄范围0~100

**登录**

`/user/login?name=user2&password=23333`

## 其他运行方式

修改数据库后，需要在构建/运行选项选择mybatis-generator可以生成数据库对应的dataObject类以及Mapper。

# 待修复BUG

`@NotBlank`无论如何都会报错

传参缺少的情况下会直接报未知错误，`@NotNull`不生效。