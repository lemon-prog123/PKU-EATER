package org.example.controller;

import org.apache.tomcat.util.security.MD5Encoder;
import org.example.controller.viewobject.UserVO;
import org.example.response.CommonReturnType;
import org.example.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//如果不加ResponseBody的话
//就要用RestController
@Controller("user")
@RequestMapping("/user")
public class UserController {

    /**
     * 用户注册接口
     * 接收参数统一使用字符串，接收后再进行类型转换
     *
     * @param name      姓名
     * @param age    年龄
     * @param gender 性别
     * @param password  密码
     * @return 通用返回对象
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public CommonReturnType register(@RequestParam(name="name")String name,
                                     @RequestParam(name="gender")Integer gender,
                                     @RequestParam(name="age")Integer age,
                                     @RequestParam(name="password")String password) {
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(gender);
        userModel.setAge(age);
        //密码按道理是需要加密的，目前暂不加密
        //userModel.setEncrptPassword(MD5Encoder.encode(password.getBytes()));
        userModel.setEncrptPassword(password);

        userService.register(userModel);

        return CommonReturnType.create(null);
    }

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

    //用户注册接口
}
