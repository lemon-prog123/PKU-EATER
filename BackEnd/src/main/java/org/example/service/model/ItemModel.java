package org.example.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

//菜品模型
public class ItemModel {
    private Integer id;

    //菜品名称
    @NotBlank(message = "菜品名称不能为空")
    private String title;
    //菜品价格
    @NotNull(message = "菜品价格不能为空")
    @Min(value = 0 ,message = "菜品价格必须大于0")
    private BigDecimal price;
    //菜品描述
    @NotNull(message = "菜品描述信息不能为空")
    private String description;
    //菜品所属食堂
    private String canteen;
    //菜品图片的url
    @NotNull(message = "菜品图片不能为空")
    private String imgUrl;
    //菜品卡路里
    private Integer Calorie;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCanteen() {
        return canteen;
    }

    public void setCanteen(String canteen) {
        this.canteen = canteen;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getCalorie() {
        return Calorie;
    }

    public void setCalorie(Integer calorie) {
        Calorie = calorie;
    }
}
