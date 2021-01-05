package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.NoteService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2021/1/5 14:20
 */
@RestController
public class AddNoteController {
    @Resource
    private NoteService noteService;
    @PostMapping("/note/add.do")
    public JsonResult execute(String userId,String bookId,String noteTitle){
       JsonResult jsonResult = noteService.addNote(userId,bookId,noteTitle);
       return jsonResult;
    }
}
