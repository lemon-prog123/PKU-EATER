package org.example.controller.viewobject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

//展示给前端用户
public class ItemVO {
    private Integer id;

    //窗口
    private Integer window;
    //菜品名称
    private String name;
    //菜品所属食堂
    private Integer canteen_id;
    //菜品卡路里
    private Integer calorie;
    //菜品忌口,二进制形式存储
    private Integer avoidance;
    //菜品价格
    private Integer price;
    //菜品图片的url,以文件形式存储
    private String imgaddr;
    //菜品分类
    private Integer type;


}
