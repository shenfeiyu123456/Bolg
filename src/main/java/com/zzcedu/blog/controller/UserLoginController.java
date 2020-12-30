package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.UserService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: Evan
 * @Date: 2020/12/29 17:24
 */
@RestController
public class UserLoginController {
    @Resource
    private UserService userService;
    @PostMapping("/user/login.do")
    public JsonResult execute(String username,String password) throws NoSuchAlgorithmException {
        JsonResult jsonResult = userService.checkLogin(username, password);
        return jsonResult;
    }
}
