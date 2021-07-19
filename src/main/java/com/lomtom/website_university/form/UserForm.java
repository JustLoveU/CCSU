package com.lomtom.website_university.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lomtom.website_university.inter.InsertInterface;
import com.lomtom.website_university.inter.UpdateInterface;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: LOMTOM
 * @Date: 2021/2/2
 * @Time: 15:17
 * @Email: lomtom@qq.com
 */
@Data
@TableName("user")
public class UserForm implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(type= IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空",groups = {InsertInterface.class, UpdateInterface.class})
    private String username;
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空",groups = {InsertInterface.class, UpdateInterface.class})
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


    @TableField(exist = false)
    @NotNull(message = "验证码不能为空",groups = {InsertInterface.class})
    private String code;

}

