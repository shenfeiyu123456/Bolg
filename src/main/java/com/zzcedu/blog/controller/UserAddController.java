package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.UserService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2020/12/30 15:03
 */
@RestController
public class UserAddController {
    @Resource
    private UserService userService;
    @PostMapping("/user/add.do")
    public JsonResult execute(String username,String password,String nick){
        JsonResult jsonResult = userService.addUser(username,password,nick);
        return jsonResult;
    }
}
