package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author Deng_Ruonan
 * @create 2022-05-04-21:06
 */
@Configuration
//表示这个类是一个配置类，不是普通的类
public class AlphaConfig {

    //定义一个第三方的bean
    @Bean
    public SimpleDateFormat simpleDateFormat(){//bean的名字就是simpleDateFormat
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }//这个方法返回的对象将被装配到容器中
}
