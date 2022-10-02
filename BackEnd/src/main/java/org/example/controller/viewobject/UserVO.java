package org.example.controller.viewobject;

//前端用户需要的信息
public class UserVO {
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;

    public Integer getID(){
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

    public Byte getGender(){
        return gender;
    }

    public void setGender(Byte gender){
        this.gender = gender;

    }

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age = age;
    }
}
