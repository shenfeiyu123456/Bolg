package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.NoteService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2021/1/6 15:56
 */
@RestController
public class ShareNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/share.do")
    public JsonResult execute(String noteId){
        JsonResult jsonResult = noteService.shareNote(noteId);
        return jsonResult;
    }
}
