package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Deng_Ruonan
 * @create 2022-05-04-20:41
 */
@Service
//业务组件加的注解是Service
//@Scope("prototype")  //此注解默认是单例模式"singleton"只实例化一次  通常用单例模式的
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;//依赖注入 service依赖默认优先级的dao

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }
    //想让容器在合适的时候自动调用方法，使用注解@PostConstruct 初始化方法会在构造器后调用
    @PostConstruct
    public void init(){//初始化方法
        System.out.println("初始化AlphaService");
    }

    //在销毁之前调用此方法 使用注解@PreDestroy
    @PreDestroy
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

    public String find(){
        return alphaDao.select();//service依赖Dao
    }
}
