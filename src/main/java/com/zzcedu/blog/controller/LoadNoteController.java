package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.NoteService;
import com.zzcedu.blog.util.JsonResult;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2020/11/13 16:16
 */
@Log
@RestController
public class LoadNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/loadNote.do")
    public JsonResult execute(String noteId){
        log.info("笔记id为:"+noteId);
        JsonResult noteResult = noteService.loadNote(noteId);
        return noteResult;
    }
}
