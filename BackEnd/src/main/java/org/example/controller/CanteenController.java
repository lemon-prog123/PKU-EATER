package org.example.controller;

import org.example.controller.viewobject.CanteenVO;
import org.example.error.BusinessException;
import org.example.response.CommonReturnType;
import org.example.service.CanteenService;
import org.example.service.model.CanteenModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/canteen")
@RequestMapping("/canteen")
public class CanteenController extends BaseController{
        @Autowired
        private CanteenService canteenService;

        //创建餐厅的controller
    /**
     * 创建食堂接口
     * 接收参数统一使用字符串，接收后再进行类型转换
     *
     * @param name         名称
     * @param intro        简介
     * @param imgurl      图片地址
     *
     * @return 通用返回对象
     */
        @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
        @ResponseBody
        public CommonReturnType createCanteen(@RequestParam(name = "name") String name,
                                           @RequestParam(name = "imgurl") String imgurl,
                                           @RequestParam(name = "intro") String intro) throws BusinessException {
            //封装service请求用来创建食堂
            CanteenModel canteenModel = new CanteenModel();
            canteenModel.setName(name);
            canteenModel.setImgurl(imgurl);
            canteenModel.setIntro(intro);

            CanteenModel canteenModelForReturn = canteenService.createCanteen(canteenModel);
            CanteenVO canteenVO = convertVOFromModel(canteenModelForReturn);
            return CommonReturnType.create(canteenVO);

        }

        private CanteenVO convertVOFromModel(CanteenModel canteenModel) {
            if (canteenModel == null) {
                return null;
            }
            CanteenVO canteenVO = new CanteenVO();
            BeanUtils.copyProperties(canteenModel, canteenVO);
            return canteenVO;
        }

        //食堂详情页浏览
    /**
     * 浏览食堂详情接口
     * 接收参数统一使用字符串，接收后再进行类型转换
     * 通过id浏览特定食堂信息
     *
     * @param id     食堂编号
     *
     * @return 通用返回对象
     */
        @RequestMapping(value = "/get", method = {RequestMethod.GET})
        @ResponseBody
        public CommonReturnType getCanteen(@RequestParam(name = "id") Integer id) {
            CanteenModel canteenModel = canteenService.getCanteenById(id);

            CanteenVO canteenVO = convertVOFromModel(canteenModel);

            return CommonReturnType.create(canteenModel);
        }

    //食堂列表页面浏览
    /**
     * 浏览食堂列表接口
     * 接收参数统一使用字符串，接收后再进行类型转换
     *
     * @return 通用返回对象
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listCanteen() {
        List<CanteenModel> canteenModelList = canteenService.listCanteen();
        //使用stream api将list内的canteenModel转化为CANTEENVO；
        List<CanteenVO> canteenVOList = canteenModelList.stream().map(canteenModel -> {
            CanteenVO canteenVO = this.convertVOFromModel(canteenModel);
            return canteenVO;
        }).collect(Collectors.toList());

        return CommonReturnType.create(canteenVOList);
    }
}
