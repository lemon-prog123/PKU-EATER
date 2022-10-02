package org.example.controller;

import org.example.dataobject.UserDO;
import org.example.service.UserService;
import org.example.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("user")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //调用service服务获取对应id的用户对象并返回给前端
    //输入localhost:8090/user/get?
    //记得加了ResponseBody才能在前端显示，类注解RestBody也可以……
    @RequestMapping("/get")
    @ResponseBody
    public UserModel getUser(@RequestParam(name = "id") Integer id) {
        UserModel userModel = userService.getUserById(id);
        return userModel;
    }
}
