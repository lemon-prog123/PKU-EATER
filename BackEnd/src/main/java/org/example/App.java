
package org.example;

import org.example.controller.CanteenController;
import org.example.dao.CanteenDOMapper;
import org.example.dao.FoodDOMapper;
import org.example.dao.PasswordDOMapper;
import org.example.dao.UserDOMapper;
import org.example.dataobject.CanteenDO;
import org.example.dataobject.FoodDO;
import org.example.dataobject.PasswordDO;
import org.example.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"org.example"})
@RestController
@MapperScan("org.example.dao")
public class App {

    @Autowired
    private PasswordDOMapper foodDOMapper;

    @RequestMapping("/")
    public String hello() {
        PasswordDO foodDO = foodDOMapper.selectByPrimaryKey(1);
        if(foodDO == null) {
            return "用户不存在.";
        }
        else {
            return foodDO.getEncrptPassword();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(App.class, args);
    }
}