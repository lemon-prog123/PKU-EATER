package org.example.controller.viewobject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CanteenVO {
    private Integer id;

    //食堂名称
    private String name;
    //食堂图片
    private String imgurl;
    //食堂介绍
    private String intro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
