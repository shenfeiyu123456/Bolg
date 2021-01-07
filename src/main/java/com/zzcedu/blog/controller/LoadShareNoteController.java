package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.NoteService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2021/1/7 17:09
 */
@RestController
public class LoadShareNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/load_share.do")
    public JsonResult execute(String shareId){
        JsonResult jsonResult = noteService.loadShareNote(shareId);
        return jsonResult;
    }
}
