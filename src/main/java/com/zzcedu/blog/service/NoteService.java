package com.zzcedu.blog.service;
import com.zzcedu.blog.util.JsonResult;

/**
 * @Author: Evan
 * @Date: 2020/11/13 14:35
 */
public interface NoteService {
    /**
     * 加载笔记列表
     * @param bookId
     * @return
     */
    JsonResult loadNotes(String bookId);

    /**
     * 加载笔记详情
     * @param noteId
     * @return
     */
    JsonResult loadNote(String noteId);

    JsonResult updateNote(String title, String body, String noteId);

    JsonResult addNote(String userId, String bookId, String noteTitle);

    JsonResult deleteNote(String noteId);

    JsonResult moveNote(String noteId, String bookId);

    JsonResult shareNote(String noteId);
}
