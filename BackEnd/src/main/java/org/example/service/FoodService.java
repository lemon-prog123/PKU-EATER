package org.example.service;

import org.example.error.BusinessException;
import org.example.service.model.CanteenModel;
import org.example.service.model.FoodModel;

import java.util.List;

public interface FoodService {
    //食品列表
    List<FoodModel> listFood();

    //食品详情浏览
    FoodModel getFoodById(Integer id);
}
