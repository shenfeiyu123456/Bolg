package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.NoteService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2020/11/13 14:39
 */
@RestController
public class LoadNotesController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/loadNotes.do")
    public JsonResult execute(String bookId){
        JsonResult notes = noteService.loadNotes(bookId);
        return notes;
    }
}
