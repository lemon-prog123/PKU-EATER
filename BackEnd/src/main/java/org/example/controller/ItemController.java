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
    /**
     * 创建菜品接口
     * 接收参数统一使用字符串，接收后再进行类型转换
     *
     * @param window       所在窗口
     * @param name         名称
     * @param canteen_id   食堂编号
     * @param calorie      卡路里
     * @param avoidance    忌口
     * @param price        价格
     * @param type         类型
     * @param imgaddr      图片地址
     * @return 通用返回对象
     */
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
    /**
     * 创建菜品详情页浏览接口
     * 接收参数统一使用字符串，接收后再进行类型转换
     * 通过编号选择菜品进行详情浏览
     *
     * @param       id    编号
     * @return   通用返回对象
     */
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "id") Integer id) {
        ItemModel itemModel = foodService.getItemById(id);

        ItemVO itemVO = convertVOFromModel(itemModel);

        return CommonReturnType.create(itemVO);
    }


    //菜品列表页面浏览
    /**
     * 浏览菜品接口
     * 接收参数统一使用字符串，接收后再进行类型转换
     *
     * @return 通用返回对象
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listItem() {
        List<ItemModel> itemModelList = foodService.listItem();

        //使用stream api将list内的itemModel转化为ITEMVO；
        List<ItemVO> itemVOList = itemModelList.stream().map(itemModel -> {
            ItemVO itemVO = this.convertVOFromModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());

        return CommonReturnType.create(itemVOList);
    }

}
