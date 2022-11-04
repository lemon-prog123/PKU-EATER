package org.example.controller;

import org.example.controller.viewobject.CanteenVO;
import org.example.response.CommonReturnType;
import org.example.service.FoodService;
import org.example.service.model.CanteenModel;
import org.example.service.model.FoodModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/food")
@RequestMapping("/food")
public class FoodController extends BaseController{
    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getFood(@RequestParam(name = "id") Integer id) {
        FoodModel foodModel = foodService.getFoodById(id);

        //CanteenVO canteenVO = convertVOFromModel(canteenModel);

        return CommonReturnType.create(foodModel);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listFood() {
        List<FoodModel> foodModelList = foodService.listFood();
        return CommonReturnType.create(foodModelList);
    }
}
