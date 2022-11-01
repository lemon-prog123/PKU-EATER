package org.example.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

//菜品模型
public class ItemModel {
    private Integer id;

    //窗口
    @NotBlank(message = "菜品窗口不能为空")
    private Integer window;
    //菜品名称
    @NotBlank(message = "菜品名称不能为空")
    private String name;
    //菜品所属食堂
    @NotNull(message = "菜品所属食堂不能为空")
    private Integer canteen_id;
    //菜品卡路里
    @NotNull(message = "菜品卡路里不能为空")
    @Min(value = 0 ,message = "菜品卡路里必须大于0")
    private Integer calorie;
    //菜品忌口,二进制形式存储
    private Integer avoidance;
    //菜品价格
    @NotNull(message = "菜品价格不能为空")
    @Min(value = 0 ,message = "菜品价格必须大于0")
    private Integer price;
    //菜品图片的url,以文件形式存储
    @NotNull(message = "菜品图片不能为空")
    private String imgaddr;
    //菜品分类
    @NotNull(message = "菜品描述信息不能为空")
    private Integer type;

    public String getImgaddr() {
        return imgaddr;
    }

    public void setImgaddr(String imgaddr) {
        this.imgaddr = imgaddr;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWindow() {
        return window;
    }

    public void setWindow(Integer window) {
        this.window = window;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCanteen_id() {
        return canteen_id;
    }

    public void setCanteen_id(Integer canteen_id) {
        this.canteen_id = canteen_id;
    }

    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    public Integer getAvoidance() {
        return avoidance;
    }

    public void setAvoidance(Integer avoidance) {
        this.avoidance = avoidance;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }







}
