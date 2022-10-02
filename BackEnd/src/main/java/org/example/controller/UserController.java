package org.example.controller;

import org.example.controller.viewobject.UserVO;
import org.example.response.CommonReturnType;
import org.example.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

//如果不加ResponseBody的话
//就要用RestController
@Controller("user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id")Integer id){
        //调用service服务获取对应id的用户对象并返回给前端
       UserModel userModel = userService.getUserById(id);

       //将核心领域模型用户对象转化为可供UI使用的viewobject
       UserVO userVO =  convertFromModel(userModel);

       //返回通用对象
       return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel){
        if(userModel == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}
