package org.example.service.impl;

import org.apache.commons.lang3.StringUtils;//注意不是mysql里的StringUtils包
import org.example.dao.PasswordDOMapper;
import org.example.dao.UserDOMapper;
import org.example.dataobject.PasswordDO;
import org.example.dataobject.UserDO;
import org.example.error.BusinessException;
import org.example.error.EmBusinessError;
import org.example.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.example.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private PasswordDOMapper passwordDOMapper;

    @Override
    public UserModel getUserById(Integer id){
        //调用userdomapper获取到对应的用户dataobject
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);

        if(userDO == null){
            return null;
        }

        //通过用户id获取对应用户的加密密码信息
        PasswordDO PasswordDO = passwordDOMapper.selectByUserId(userDO.getId());

        return convertFromDataObject(userDO,PasswordDO);
    }


    private UserModel convertFromDataObject(UserDO userDO, PasswordDO userPasswordDO){
        if(userDO == null){
            return null;
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);

        if(userPasswordDO != null ){
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }

        return userModel;
    }

    //userModel->userDO
    private UserDO convertFromModel(UserModel userModel) {
        if(userModel == null) return null;
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }

    private PasswordDO convertPasswordFromModel(UserModel userModel) {
        if(userModel == null) return null;
        PasswordDO passwordDO = new PasswordDO();
        passwordDO.setEncrptPassword(userModel.getEncrptPassword());
        passwordDO.setUsrId(userModel.getId());
        return passwordDO;
    }

    @Override
    @Transactional //同一事务，防止什么用户表插进去了password没插进去
    public void register(UserModel userModel) throws BusinessException{
        if(userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if(StringUtils.isEmpty(userModel.getName()) ||
            userModel.getGender() == null ||
            userModel.getAge() == null) { //不是合法注册信息
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        UserDO userDO = convertFromModel(userModel);//将userModel转为数据库可用的userDO
        try {
            userDOMapper.insertSelective(userDO);
        }catch (DuplicateKeyException ex){
            // 手动回滚事务
            // DuplicateKeyException：Unique的Key被重复使用
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户名已重复注册");
        }
        //insertSelective不会插入为null的字段，而是将其设为数据库的默认值

        /*
        一旦insertSelective成功，user表的id就会自增（这需要去UserDOMapper.xml文件中设置id为主键自增）
        这时候就可以通过userDo进行get了
        将get到的id赋值给userModel，并传递给convertPasswoedFromModel方法
         */
        userModel.setId(userDO.getId());

        PasswordDO passwordDO = convertPasswordFromModel(userModel);
        passwordDOMapper.insertSelective(passwordDO);
    }

    @Override
    public UserModel validateLogin(String name, String password) throws BusinessException{
        //通过用户名获取用户信息
        UserDO userDO = userDOMapper.selectByUserName(name);
        if(userDO == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        PasswordDO passwordDO = passwordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO, passwordDO);

        //比对用户信息内加密的密码是否和传输进来的密码相匹配
        if(!StringUtils.equals(password, userModel.getEncrptPassword())) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }

        //返回用户信息
        return userModel;
    }
}
