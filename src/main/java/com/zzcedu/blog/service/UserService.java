package com.zzcedu.blog.service;

import com.zzcedu.blog.util.JsonResult;

import java.security.NoSuchAlgorithmException;

/**
 * @Author: Evan
 * @Date: 2020/12/29 17:10
 */
public interface UserService {
    /**
     * 检查用户登录
     * @param username
     * @param password
     * @return响应实体类
     */
    JsonResult checkLogin(String username,String password) throws NoSuchAlgorithmException;

    JsonResult addUser(String username, String password, String nick);
}
