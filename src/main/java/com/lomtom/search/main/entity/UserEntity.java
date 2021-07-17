package com.lomtom.search.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/12
 * @Time: 20:15
 */
@Data
@TableName("user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(type= IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String img;

    /**
     * 简介
     */
    private String intro;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 注册时间
     */
    private Date date;

    /**
     * 是否可用
     */
    private Boolean status;

    /**
     * 角色
     */
    private String role;


}
