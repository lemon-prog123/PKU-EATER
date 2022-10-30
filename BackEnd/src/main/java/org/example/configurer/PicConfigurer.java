package org.example.configurer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PicConfigurer implements WebMvcConfigurer {
    //谨记：url的映射文件夹应以/结尾
    @Value("${out.resource.food.path}")
    private String food_path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置server虚拟路径，handler为jsp中访问的目录，locations为image相对应的本地路径
        registry.addResourceHandler("/foodimg/**").addResourceLocations(food_path);
    }

}