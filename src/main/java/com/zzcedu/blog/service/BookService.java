package com.zzcedu.blog.service;

import com.zzcedu.blog.util.JsonResult;

/**
 * @Author: Evan
 * @Date: 2020/12/30 16:10
 */
public interface BookService {
    /**
     * 根据用户id加载对应的用户笔记
     * @param userId
     * @return Json响应实体类
     */
    JsonResult loadUserBooks(String userId);

    JsonResult addBook(String userId, String bookName);
}
