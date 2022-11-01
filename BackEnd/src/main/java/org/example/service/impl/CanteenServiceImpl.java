package org.example.service.impl;

import org.example.dao.CanteenDOMapper;
import org.example.dataobject.CanteenDO;
import org.example.dataobject.FoodDO;
import org.example.error.BusinessException;
import org.example.error.EmBusinessError;
import org.example.service.CanteenService;
import org.example.service.model.CanteenModel;
import org.example.service.model.ItemModel;
import org.example.validator.ValidationResult;
import org.example.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CanteenServiceImpl implements CanteenService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private CanteenDOMapper canteenDOMapper;

    @Override
    @Transactional
    public CanteenModel createCanteen(CanteenModel canteenModel) throws BusinessException {
        //校验入参
        ValidationResult result = validator.validate(canteenModel);
        if(result.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }
        //转化canteenmodel->dataobject
        CanteenDO canteenDO = this.convertCanteenDOFromCanteenModel(canteenModel);

        //写入数据库
        canteenDOMapper.insertSelective(canteenDO);
        canteenModel.setId(canteenDO.getId());

        //返回创建完成的对象
        return this.getCanteenById(canteenModel.getId());
    }

    private CanteenDO convertCanteenDOFromCanteenModel(CanteenModel canteenModel) {
        if (canteenModel == null) {
            return null;
        }
        CanteenDO canteenDO = new CanteenDO();
        BeanUtils.copyProperties(canteenModel, canteenDO);
        return canteenDO;
    }

    @Override
    public List<CanteenModel> listCanteen() {
        List<CanteenDO> canteenDOList = canteenDOMapper.listCanteen();
        List<CanteenModel> canteenModelList = canteenDOList.stream().map(canteenDO -> {

            CanteenModel canteenModel = new CanteenModel();
            BeanUtils.copyProperties(canteenDO,canteenModel);
            return canteenModel;
        }).collect(Collectors.toList());
        return canteenModelList;
    }

    @Override
    public CanteenModel getCanteenById(Integer id) {
        CanteenDO canteenDO = canteenDOMapper.selectByPrimaryKey(id);
        if (canteenDO == null) {
            return null;
        }
        //将dataobject->Model
        CanteenModel canteenModel = new CanteenModel();
        BeanUtils.copyProperties(canteenDO, canteenModel);
        return canteenModel;
    }
}
