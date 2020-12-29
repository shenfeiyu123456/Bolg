package com.zzcedu.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Evan
 * @Date: 2020/12/29 15:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String cn_user_id;
    private String cn_user_name;
    private String cn_user_password;
    private String cn_user_token;
    private String cn_user_nick;
}
