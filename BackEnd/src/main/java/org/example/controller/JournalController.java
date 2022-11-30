package org.example.controller;


import org.example.controller.viewobject.CanteenVO;
import org.example.controller.viewobject.JournalVO;
import org.example.error.BusinessException;
import org.example.response.CommonReturnType;
import org.example.service.FoodService;
import org.example.service.JournalService;
import org.example.service.model.CanteenModel;
import org.example.service.model.FoodModel;
import org.example.service.model.JournalModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/journal")
@RequestMapping("/journal")
public class JournalController extends BaseController{
    @Autowired
    private JournalService journalService;
    @Autowired
    private FoodService foodService;

    /**
     * 通过用户id展示日志接口
     * 接收参数统一使用字符串，接收后再进行类型转换
     *
     * @param uid        用户
     *
     * @return 通用返回对象
     */
    @RequestMapping(value = "/listbyusr", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType ListJournalByUsrId(@RequestParam(name = "uid") Integer uid) {
        List<JournalModel> journalModelList = journalService.ListJournalByUsrId(uid);

        //使用stream api将list内的journalModel转化为JournalVO；
        List<JournalVO> journalVOList = journalModelList.stream().map(journalModel -> {
            JournalVO journalVO = this.convertVOFromModel(journalModel);
            return journalVO;
        }).collect(Collectors.toList());

        return CommonReturnType.create(journalVOList);

    }

    /**
     * 增加日志接口
     * 接收参数统一使用字符串，接收后再进行类型转换
     *
     * @param uid        用户
     * @param fid       食物编号
     * @param calorie       卡路里
     * @param price         价格
     *
     * @return 通用返回对象
     */
    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createJournal(@RequestParam(name = "uid") Integer uid,
                                          @RequestParam(name = "fid") Integer fid,
                                          @RequestParam(name = "meal") Integer meal,
                                          @RequestParam(required=false, name = "calorie") Integer calorie,
                                          @RequestParam(required=false, name = "price") Integer price) throws BusinessException {
        //封装service请求用来增加日志
        JournalModel journalModel = new JournalModel();
        journalModel.setUid(uid);
        journalModel.setFid(fid);
        journalModel.setMeal(meal);

        //自定义
        if(journalModel.getFid() == 0){
            journalModel.setCalorie(calorie);
            journalModel.setPrice(price);
        }
        else{
            FoodModel foodModel = foodService.getFoodById(fid);
            journalModel.setCalorie(foodModel.getCalorie());
            journalModel.setPrice(foodModel.getPrice());
        }

        journalService.createJournal(journalModel);

        return CommonReturnType.create(null);
    }

    private JournalVO convertVOFromModel(JournalModel journalModel) {
        if (journalModel == null) {
            return null;
        }
        JournalVO journalVO = new JournalVO();
        BeanUtils.copyProperties(journalModel, journalVO);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        journalVO.setDatetime(sf.format(journalModel.getDatetime()));
        return journalVO;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType deleteJournel(@RequestParam(name = "id") Integer id,
                                          @RequestParam(name = "uid") Integer uid
                                         ) throws BusinessException {
        journalService.deleteJournal(id, uid);
        return CommonReturnType.create(null);
    }
}
