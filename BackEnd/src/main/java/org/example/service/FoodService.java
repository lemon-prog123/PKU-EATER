package org.example.service;

import org.apache.wicket.markup.repeater.Item;
import org.example.error.BusinessException;
import org.example.service.model.ItemModel;

import java.util.List;

public interface FoodService {

    //创建菜品
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    //菜品列表浏览
    List<ItemModel> listItem();

    //菜品详情浏览
    ItemModel getItemById(Integer id);
}
