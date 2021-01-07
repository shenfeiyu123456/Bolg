package com.zzcedu.blog.dao;

import com.zzcedu.blog.entity.Share;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Evan
 * @Date: 2021/1/6 16:04
 */
public interface ShareDao {
    /**
     * 插入分享笔记
     * @param share
     */
    @Insert("insert into cn_share(cn_share_id,cn_share_title,cn_share_body,cn_note_id) values(#{cn_share_id},#{cn_share_title},#{cn_share_body},#{cn_note_id})")
    void save(Share share);
    @Select("select * from cn_share where cn_share_title like #{keyword} limit #{start},5")
    List<Share> findLikeTitle(@Param("keyword") String keyword, @Param("start") int start);
    @Select("select * from cn_share where cn_share_id = #{shareId}")
    Share findById(String shareId);
}
