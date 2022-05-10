package com.nowcoder.community.dao;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

/**
 * @author Deng_Ruonan
 * @create 2022-05-04-20:23
 */
@Repository("alphaHibernate")
//从数据库里获取用Repository注解 bean命名为alphaHibernate
public class AlphaDaoHibernateImpl implements AlphaDao{
    //AlphaDao的实现类
    @Override
    public String select() {
        return "Hibernate";
    }
}
