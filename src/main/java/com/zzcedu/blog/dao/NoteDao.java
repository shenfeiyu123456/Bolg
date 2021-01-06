package com.zzcedu.blog.dao;

import com.zzcedu.blog.entity.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: Evan
 * @Date: 2020/11/13 14:19
 */
public interface NoteDao {
    @Select("select cn_note_id,cn_note_title,cn_note_type_id from cn_note where cn_notebook_id = #{bookId} and cn_note_status_id='1'")
    List<Note> findByBookId(@Param("bookId") String bookId);
    @Select("select * from cn_note where cn_note_id=#{noteId}")
    Note findById(String noteId);
//    @Update("update cn_note set cn_note_title = #{cn_note_title},cn_note_body= #{cn_note_body},cn_note_last_modify_time = #{cn_note_last_modify_time} where cn_note_id = #{cn_note_id}")
//    int update(Note note);
    @Insert("insert into cn_note(cn_note_id,cn_note_title,cn_notebook_id,cn_user_id,cn_note_status_id,cn_note_type_id,cn_note_create_time,cn_note_last_modify_time,cn_note_body) values(#{cn_note_id},#{cn_note_title},#{cn_notebook_id},#{cn_user_id},#{cn_note_status_id},#{cn_note_type_id},#{cn_note_create_time},#{cn_note_last_modify_time},#{cn_note_body})")
    int save(Note note);

    /**
     * 逻辑删除
     * @param noteId
     * @return
     */
//    @Update("update cn_note set cn_note_status_id = '2' where cn_note_id = #{noteId}")
//    int updateStatus(String noteId);

    /**
     * 根据笔记Id修改笔记本Id
     * @param noteId
     * @param bookId
     * @return
     */
//    @Update("update cn_note set cn_notebook_id = #{bookId} where cn_note_id = #{noteId}")
//    int updateNote(@Param("noteId") String noteId, @Param("bookId") String bookId);

    /**
     * 更新笔记状态为分享状态
     * @param noteId
     * @return
     */
//    @Update("update cn_note set cn_note_type_id = '2' where cn_note_id = #{noteId}")
//    int updateType(String noteId);

    /**
     * 动态更新笔记信息
     * @param note
     * @return
     */
    @Update("<script> update cn_note\n" +
            "        <set>\n" +
            "            <if test=\"cn_notebook_id != null\">\n" +
            "                cn_notebook_id = #{cn_notebook_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_user_id != null\">\n" +
            "                cn_user_id = #{cn_user_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_status_id != null\">\n" +
            "                cn_note_status_id = #{cn_note_status_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_type_id != null\">\n" +
            "                cn_note_type_id = #{cn_note_type_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_title != null\">\n" +
            "                cn_note_title = #{cn_note_title},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_body != null\">\n" +
            "                cn_note_body = #{cn_note_body},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_create_time != null\">\n" +
            "                cn_note_create_time = #{cn_note_create_time},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_last_modify_time != null\">\n" +
            "                cn_note_last_modify_time = #{cn_note_last_modify_time},\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where cn_note_id = #{cn_note_id} </script>")
    int dynamicUpdate(Note note);
}
