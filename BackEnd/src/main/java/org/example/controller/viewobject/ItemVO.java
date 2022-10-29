package org.example.controller.viewobject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

//展示给前端用户
public class ItemVO {

    private Integer id;

    //菜品名称
    private String title;
    //菜品价格
    private BigDecimal price;
    //菜品描述
    private String description;
    //菜品所属食堂
    private String canteen;
    //菜品图片的url
    private String imgUrl;
    //菜品卡路里
    private Integer Calorie;

}
