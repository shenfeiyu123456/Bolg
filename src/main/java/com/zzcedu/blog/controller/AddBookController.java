package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.BookService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2021/1/4 16:23
 */
@RestController
public class AddBookController {
    @Resource
    private BookService bookService;
    @PostMapping("/book/add.do")
    public JsonResult execute(String userId,String bookName){
        JsonResult jsonResult = bookService.addBook(userId,bookName);
        return jsonResult;
    }
}
