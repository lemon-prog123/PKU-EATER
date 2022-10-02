# software_class

# BackEnd

后端框架（服务器）

基于**Maven+Mybatis+SpringBoot**

使用IDEA打开，点击右侧栏Maven和刷新按钮下载相关依赖

构建/运行选项选择mybatis-generator可以生成数据库对应的dataObject类以及Mapper。

选择App则直接运行，在地址localhost:8090可以得到Hello World

地址localhost:8090/user/get?id=1可以获得用户信息json，其中id=后面接对应的用户id。