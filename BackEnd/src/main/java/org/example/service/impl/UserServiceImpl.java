package org.example.service.impl;

import org.example.dao.PasswordDOMapper;
import org.example.dao.UserDOMapper;
import org.example.dataobject.PasswordDO;
import org.example.dataobject.UserDO;
import org.example.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private PasswordDOMapper PasswordDOMapper;

    @Override
    public UserModel getUserById(Integer id){
        //调用userdomapper获取到对应的用户dataobject
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);

        if(userDO == null){
            return null;
        }

        //通过用户id获取对应用户的加密密码信息
        PasswordDO PasswordDO = PasswordDOMapper.selectByUserId(userDO.getId());

        return convertFromDataObject(userDO,PasswordDO);
    }


    private UserModel convertFromDataObject(UserDO userDO, PasswordDO userPasswordDO){
        if(userDO == null){
            return null;
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);

        if(userPasswordDO != null ){
            userModel.setEncrptPssword(userPasswordDO.getEncrptPassword());
        }

        return userModel;
    }
}
