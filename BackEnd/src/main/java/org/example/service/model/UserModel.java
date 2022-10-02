package org.example.service.model;


//真正层面上处理业务逻辑的核心模型
public class UserModel {
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;

    private String encrptPssword;

    public String getEncrptPssword() {
        return encrptPssword;
    }

    public void setEncrptPssword(String encrptPssword) {
        this.encrptPssword = encrptPssword;
    }


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
