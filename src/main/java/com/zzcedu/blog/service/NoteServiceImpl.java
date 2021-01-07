package com.zzcedu.blog.service;

import com.zzcedu.blog.dao.NoteDao;
import com.zzcedu.blog.dao.ShareDao;
import com.zzcedu.blog.entity.Note;
import com.zzcedu.blog.entity.Share;
import com.zzcedu.blog.util.JsonResult;
import com.zzcedu.blog.util.NoteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Evan
 * @Date: 2020/11/13 14:36
 */
@Service
public class NoteServiceImpl implements NoteService{
    @Resource
    private NoteDao noteDao;
    @Resource
    private ShareDao shareDao;
    @Override
    public JsonResult loadNotes(String bookId) {
        JsonResult noteResult = new JsonResult();
        List<Note> notes = noteDao.findByBookId(bookId);
        noteResult.setStatus(0);
        noteResult.setMsg("查询笔记成功");
        noteResult.setData(notes);
        return noteResult;
    }

    @Override
    public JsonResult loadNote(String noteId) {
        JsonResult noteResult = new JsonResult();
        Note note = noteDao.findById(noteId);
        noteResult.setStatus(0);
        noteResult.setMsg("查询笔记成功");
        noteResult.setData(note);
        return noteResult;
    }

    @Override
    public JsonResult updateNote(String title, String body, String noteId) {
        JsonResult jsonResult = new JsonResult();
        Note note = Note.builder().cn_note_id(noteId).cn_note_title(title).cn_note_body(body).cn_note_last_modify_time(System.currentTimeMillis()).build();
        int i = noteDao.dynamicUpdate(note);
        if(i > 0){
            jsonResult.setMsg("保存笔记成功");
            jsonResult.setStatus(0);
            return jsonResult;
        }
        jsonResult.setMsg("保存笔记失败");
        jsonResult.setStatus(1);
        return jsonResult;
    }

    @Override
    public JsonResult addNote(String userId, String bookId, String noteTitle) {
        JsonResult jsonResult = new JsonResult();
        Note note = Note.builder().cn_note_title(noteTitle).cn_note_id(NoteUtil.getUUID()).cn_notebook_id(bookId).cn_user_id(userId).cn_note_status_id("1").cn_note_create_time(System.currentTimeMillis()).cn_note_last_modify_time(System.currentTimeMillis()).cn_note_type_id("1").cn_note_body("").build();
        int i = noteDao.save(note);
        if(i > 0){
            jsonResult.setStatus(0);
            jsonResult.setMsg("创建笔记成功");
            jsonResult.setData(note);
            return jsonResult;
        }
        jsonResult.setStatus(1);
        jsonResult.setMsg("创建笔记失败");
        jsonResult.setData(null);
        return jsonResult;
    }

    @Override
    public JsonResult deleteNote(String noteId) {
        JsonResult jsonResult = new JsonResult();
        Note note = Note.builder().cn_note_id(noteId).cn_note_status_id("2").build();
        int i = noteDao.dynamicUpdate(note);
        if(i > 0){
            jsonResult.setStatus(0);
            jsonResult.setMsg("删除笔记成功");
            return jsonResult;
        }
        jsonResult.setStatus(1);
        jsonResult.setMsg("删除笔记失败");
        return jsonResult;
    }

    @Override
    public JsonResult moveNote(String noteId, String bookId) {
        JsonResult jsonResult = new JsonResult();
        Note note = Note.builder().cn_note_id(noteId).cn_notebook_id(bookId).build();
        int i = noteDao.dynamicUpdate(note);
        if(i > 0){
            jsonResult.setStatus(0);
            jsonResult.setMsg("移动笔记成功");
            return jsonResult;
        }
        jsonResult.setStatus(1);
        jsonResult.setMsg("移动笔记失败");
        return jsonResult;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JsonResult shareNote(String noteId) {
        JsonResult jsonResult = new JsonResult();
        //1.查询笔记是否被分享过
        Note note = noteDao.findById(noteId);
        if(note.getCn_note_type_id().equals("2")){
            jsonResult.setStatus(1);
            jsonResult.setMsg("该笔记已经被分享过了");
            return jsonResult;
        }
        //2.修改笔记状态为分享 cn_note_type_id = '2'
        note.setCn_note_type_id("2");
        int i = noteDao.dynamicUpdate(note);
        if(i > 0){
            //3.分享过的笔记插入到share表中
            Share share = Share.builder().cn_share_id(NoteUtil.getUUID()).cn_share_title(note.getCn_note_title()).cn_share_body(note.getCn_note_body()).cn_note_id(note.getCn_note_id()).build();
            shareDao.save(share);
            jsonResult.setStatus(0);
            jsonResult.setMsg("分享笔记成功");
            return jsonResult;
        }
        return null;
    }

    @Override
    public JsonResult searchShareNote(String keyword, Integer page) {
        JsonResult jsonResult = new JsonResult();
        int start = (page - 1) * 5;
        //模糊查询需要手动加%
        keyword = "%" + keyword + "%";
        List<Share> shares = shareDao.findLikeTitle(keyword,start);
        jsonResult.setStatus(0);
        jsonResult.setMsg("查询分享笔记成功");
        jsonResult.setData(shares);
        return jsonResult;
    }

    @Override
    public JsonResult loadShareNote(String shareId) {
        JsonResult jsonResult = new JsonResult();
        Share share = shareDao.findById(shareId);
        jsonResult.setStatus(0);
        jsonResult.setMsg("查询分享笔记成功");
        jsonResult.setData(share);
        return jsonResult;
    }
}
