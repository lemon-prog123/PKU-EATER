package org.example.service.impl;

import org.example.dao.FoodDOMapper;
import org.example.dataobject.FoodDO;
import org.example.error.BusinessException;
import org.example.error.EmBusinessError;
import org.example.service.FoodService;
import org.example.service.model.ItemModel;
import org.example.validator.ValidationResult;
import org.example.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private FoodDOMapper foodDOMapper;

    private FoodDO convertFoodDOFromItemModel(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        FoodDO foodDO = new FoodDO();
        BeanUtils.copyProperties(itemModel,foodDO);
        foodDO.setPrice(itemModel.getPrice().doubleValue());
        return foodDO;
    }

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException{
        //校验入参
        ValidationResult result = validator.validate(itemModel);
        if (result.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }
        //转化itemmodel->dataobject
        FoodDO foodDO = this.convertFoodDOFromItemModel(itemModel);

        //写入数据库
        itemModel.setId(foodDO.getId());

        //返回创建完成的对象
        return this.getItemById(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() {
        return null;
    }

    @Override
    public ItemModel getItemById(Integer id){
        FoodDO foodDO = foodDOMapper.selectByPrimaryKey(id);
        if (foodDO == null) {
            return null;
        }
        //将dataobject->Model
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(foodDO, itemModel);
        //手动处理价格防止精度问题
        itemModel.setPrice(new BigDecimal(foodDO.getPrice()));
        return itemModel;
    }

}
