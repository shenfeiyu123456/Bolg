package com.zzcedu.blog.dao;

import com.zzcedu.blog.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Evan
 * @Date: 2020/12/29 15:35
 */
public interface UserDao {
    /**
     * 根据名字查询用户信息
     * @param username
     * @return 用户信息
     */
    @Select("select * from cn_user where cn_user_name = #{username}")
    User findByName(String username);
    @Insert("insert into cn_user(cn_user_id,cn_user_name,cn_user_password,cn_user_token,cn_user_nick) values(#{cn_user_id},#{cn_user_name},#{cn_user_password},#{cn_user_token},#{cn_user_nick})")
    void save(User user);
}
