package org.example.service.impl;

import org.example.dao.FoodDOMapper;
import org.example.dataobject.FoodDO;
import org.example.service.FoodService;
import org.example.service.model.FoodModel;
import org.example.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private FoodDOMapper foodDOMapper;
    private FoodDO convertFoodDOFromFoodModel(FoodModel foodModel) {
        if (foodModel == null) {
            return null;
        }
        FoodDO foodDO = new FoodDO();
        BeanUtils.copyProperties(foodModel, foodDO);
        return foodDO;
    }
    @Override
    public List<FoodModel> listFood() {
        List<FoodDO> canteenDOList = foodDOMapper.listFood();
        List<FoodModel> canteenModelList = canteenDOList.stream().map(canteenDO -> {

            FoodModel canteenModel = new FoodModel();
            BeanUtils.copyProperties(canteenDO,canteenModel);
            return canteenModel;
        }).collect(Collectors.toList());
        return canteenModelList;
    }

    @Override
    public FoodModel getFoodById(Integer id) {
        FoodDO foodDO = foodDOMapper.selectByPrimaryKey(id);
        if (foodDO == null) {
            return null;
        }
        //将dataobject->Model
        FoodModel foodModel = new FoodModel();
        BeanUtils.copyProperties(foodDO, foodModel);
        return foodModel;
    }
}
