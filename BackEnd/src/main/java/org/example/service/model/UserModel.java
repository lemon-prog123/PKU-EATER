package org.example.service.model;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

//真正层面上处理业务逻辑的核心模型
public class UserModel {
    private Integer id;
    @NotNull(message = "用户名不能为空")
    private String name;
    @Min(value = 1,message = "性别不合法")
    @Max(value = 2,message = "性别不合法")
    private Integer gender;

    private Date birthday;

    private String intro;

    private Integer weight;

    private Integer height;

    private Integer avoidance;

    private Integer budget;

    private Integer state;
    @NotNull(message = "密码不能为空")
    private String encrptPassword;

    public String getEncrptPassword() {
        return encrptPassword;
    }

    public void setEncrptPassword(String encrptPssword) {
        this.encrptPassword = encrptPssword;
    }


    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getGender(){
        return gender;
    }

    public void setGender(Integer gender){
        this.gender = gender;

    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAvoidance() {
        return avoidance;
    }

    public void setAvoidance(Integer avoidance) {
        this.avoidance = avoidance;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
