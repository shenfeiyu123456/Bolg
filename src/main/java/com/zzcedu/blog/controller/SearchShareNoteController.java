package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.NoteService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2021/1/7 14:31
 */
@RestController
public class SearchShareNoteController {
    @Resource
    private NoteService noteService;

    @PostMapping("/note/search_share.do")
    public JsonResult execute(String keyword, Integer page) {
        JsonResult jsonResult = noteService.searchShareNote(keyword, page);
        return jsonResult;
    }
}
