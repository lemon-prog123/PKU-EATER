package org.example.service.impl;

import org.example.dao.PasswordDOMapper;
import org.example.dao.UserDOMapper;
import org.example.dataobject.PasswordDO;
import org.example.dataobject.UserDO;
import org.example.service.UserService;
import org.example.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//这个Service注解记得加
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private PasswordDOMapper passwordDOMapper;

    @Override
    public UserModel getUserById(Integer id) {
        //调用userDOMappeer获取对应用户的dataobject
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if(userDO == null) return null;

        //通过用户id获取对应的用户加密密码信息
        PasswordDO passwordDO = passwordDOMapper.selectByUserId(userDO.getId());

        return convertFromDataObject(userDO, passwordDO);
    }

    //组装成返回给前端的userModel
    private UserModel convertFromDataObject(UserDO userDO, PasswordDO passwordDO) {
        if(userDO == null) {
            return null;
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);

        if(passwordDO != null) {
            userModel.setEncrptPassword(passwordDO.getEncrptPassword());
        }

        return userModel;
    }
}
