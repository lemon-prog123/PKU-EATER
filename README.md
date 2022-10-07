# software_class

# BackEnd

后端框架（服务器）

基于**Maven+Mybatis+SpringBoot**

目前代码部署在本地，数据库在服务器。

## 基本运行方式

运行方式：使用IDEA打开，点击右侧栏Maven，再点击左上角刷新图标下载相关依赖，下载需要一段时间，记得等待右下角进度条下载完毕后再运行。

构建/运行选项选择App则直接运行，在浏览器输入地址`localhost:8090`可以得到Hello World（或者我们正在输出的调试信息）

处于调试阶段的页面可用浏览器输入地址访问，为GET请求。此后大部分功能均使用POST表单，编码格式`application/x-www-form-urlencoded`，POST用法详见前端代码`post.kt`

若希望修改数据库，请下载数据库管理工具如Mysql Workbench远程连接服务器的数据库，配置不清楚的话可咨询FKL和MJY（雾）。

若只是希望查看数据库，可以不下载工具，直接咨询FKL和MJY（大雾）。

## 目前的get可用url

**用户信息查询**

`/user/get?id=1`

目前为调试用，仅仅支持GET表单（浏览器访问）

**注册**

`/user/register?name=user2&gender=2&age=2&password=23333`

用户名要求不能重复注册，性别年龄暂不能为空

**登录**

`/user/login?name=user2&password=23333`

## 其他运行方式

修改数据库后，需要在构建/运行选项选择mybatis-generator可以生成数据库对应的dataObject类以及Mapper。