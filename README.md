# software_class

# BackEnd

后端框架（服务器）

基于**Maven+Mybatis+SpringBoot**

## 基本运行方式

运行方式：使用IDEA打开，点击右侧栏Maven，再点击左上角刷新图标下载相关依赖，下载需要一段时间，记得等待右下角进度条下载完毕后再运行。

构建/运行选项选择App则直接运行，在浏览器输入地址`localhost:8090`可以得到Hello World（或者我们正在输出的调试信息）

浏览器输入地址`localhost:8090/user/get?id=1`可以获得用户信息json，其中id=后面接对应的用户id。

地址`localhost:8090/user/register?name=user2&gender=2&age=2&password=23333`为注册地址，其中后几个参数都是注册参数。

表单类型为POST，编码格式为`application/x-www-form-urlencoded`（以后应该都会使用同样的表单类型和编码格式）

目前浏览器输入没用，因为浏览器默认使用GET表单。

## 其他运行方式

修改数据库后，需要在构建/运行选项选择mybatis-generator可以生成数据库对应的dataObject类以及Mapper。