package org.example.service;

import org.example.error.BusinessException;
import org.example.service.model.CanteenModel;
import org.example.service.model.FoodModel;

import java.util.List;

public interface CanteenService {
    CanteenModel createCanteen(CanteenModel canteenModel) throws BusinessException;

    //食堂列表浏览
    List<CanteenModel> listCanteen();

    //食堂详情浏览
    CanteenModel getCanteenById(Integer id);
}
