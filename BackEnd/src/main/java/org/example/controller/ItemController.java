package org.example.controller;

import org.example.controller.viewobject.ItemVO;
import org.example.error.BusinessException;
import org.example.response.CommonReturnType;
import org.example.service.FoodService;
import org.example.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/item")
@RequestMapping("/item")
//@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class ItemController extends BaseController{

    @Autowired
    private FoodService foodService;

    //创建菜品的controller
    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name = "window") Integer window,
                                       @RequestParam(name = "name") String name,
                                       @RequestParam(name = "canteen_id") Integer canteen_id,
                                       @RequestParam(name = "calorie") Integer calorie,
                                       @RequestParam(name = "avoidance") Integer avoidance,
                                       @RequestParam(name = "price") Integer price,
                                       @RequestParam(name = "type") Integer type,
                                       @RequestParam(name = "imgaddr") String imgaddr) throws BusinessException {
        //封装service请求用来创建菜品
        ItemModel itemModel = new ItemModel();
        itemModel.setCalorie(calorie);
        itemModel.setCanteen_id(canteen_id);
        itemModel.setPrice(price);
        itemModel.setType(type);
        itemModel.setName(name);
        itemModel.setAvoidance(avoidance);
        itemModel.setImgaddr(imgaddr);  //由于转化DO时会发生错误，所以去掉了下划线
        itemModel.setWindow(window);

        ItemModel itemModelForReturn = foodService.createItem(itemModel);
        ItemVO itemVO = convertVOFromModel(itemModelForReturn);
        return CommonReturnType.create(itemVO);

    }

    private ItemVO convertVOFromModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        return itemVO;
    }

    //菜品详情页浏览
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "id") Integer id) {
        ItemModel itemModel = foodService.getItemById(id);

        ItemVO itemVO = convertVOFromModel(itemModel);

        return CommonReturnType.create(itemVO);
    }

    //前端逻辑
    //商品列表页面浏览
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listItem() {
        List<ItemModel> itemModelList = foodService.listItem();

        List<ItemVO> itemVOList = itemModelList.stream().map(itemModel -> {
            ItemVO itemVO = this.convertVOFromModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());

        return CommonReturnType.create(itemVOList);
    }

}
