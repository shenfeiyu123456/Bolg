package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.BookService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2020/12/30 16:15
 */
@RestController
public class LoadBooksController {
    @Resource
    private BookService bookService;
    @PostMapping("/book/loadbooks.do")
    public JsonResult execute(String userId){
        JsonResult jsonResult = bookService.loadUserBooks(userId);
        return jsonResult;
    }
}
