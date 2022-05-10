package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Deng_Ruonan
 * @create 2022-05-07-11:16
 */

@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);
    //offset和limit用于sql语句实现分页显示时使用的limit语句，offset为起始行，limit为每页显示多少个

    int selectDiscussPostRows(@Param("userId") int userId);
    //在参数之前加注解@Param用于取别名。如果sql里需要用到动态的条件，并且条件里需要用到这个参数，
    // 而恰好这个方法只有这一个参数，则这个参数之前必须要加@Param
}
