package com.zzcedu.blog.service;

import com.zzcedu.blog.dao.BookDao;
import com.zzcedu.blog.entity.Book;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Evan
 * @Date: 2020/12/30 16:12
 */
@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookDao bookDao;
    @Override
    public JsonResult loadUserBooks(String userId) {
        JsonResult jsonResult = new JsonResult();
        List<Book> books = bookDao.findByUserId(userId);
        //创建返回结果
        jsonResult.setStatus(0);
        jsonResult.setData(books);
        jsonResult.setMsg("查询笔记本成功");
        return jsonResult;
    }
}
