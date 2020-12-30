package com.zzcedu.blog.util;

import com.alibaba.druid.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Author: Evan
 * @Date: 2020/12/29 16:45
 * 1.生成主键UUID
 * 2.将密码的明文经过MD5散列+base64序列化得到密文
 */
public class NoteUtil {
    /**
     *  1.生成主键UUID
     */
     public static String getUUID(){
         UUID uuid = UUID.randomUUID();
         String uuidStr = uuid.toString().replace("-", "");
         return uuidStr;
     }
    /**
     * 2.明文经过MD5加密
     */
    public static String md5(String src) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(src.getBytes());
        String s = Base64.byteArrayToBase64(digest);
        return s;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(getUUID());
        System.out.println(md5("123456"));
    }
}
