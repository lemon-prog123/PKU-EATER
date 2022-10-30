package org.example.service.model;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//真正层面上处理业务逻辑的核心模型
public class UserModel {
    private Integer id;
    @NotNull(message = "用户名不能为空")
    private String name;
    @Min(value = 1,message = "性别不合法")
    @Max(value = 2,message = "性别不合法")
    private Integer gender;
    @Min(value = 0,message = "年龄必须大于0岁")
    @Max(value = 100,message = "年龄必须小于100岁")
    private Integer age;
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

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age = age;
    }


}
