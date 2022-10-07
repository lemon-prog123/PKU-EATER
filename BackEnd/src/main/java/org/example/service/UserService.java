package org.example.service;

import org.example.error.BusinessException;
import org.example.service.model.UserModel;

public interface UserService {
    //通过用户ID获取用户对象的方法
    UserModel getUserById(Integer id);

    //完成注册请求
    void register(UserModel userModel) throws BusinessException;

    UserModel validateLogin(String name, String password) throws BusinessException;
}
