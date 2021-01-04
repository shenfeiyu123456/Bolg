package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.NoteService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2021/1/4 14:56
 */
@RestController
public class UpdateNoteController {
    @Resource
    private NoteService noteService;
    @PostMapping("/note/update.do")
    public JsonResult execute(String title,String body,String noteId){
       JsonResult jsonResult = noteService.updateNote(title,body,noteId);
       return jsonResult;
    }
}
