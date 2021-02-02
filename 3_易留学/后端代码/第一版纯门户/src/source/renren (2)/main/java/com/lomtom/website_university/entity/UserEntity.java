package com.lomtom.website_university.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-08-28 12:18:05
 */
@Data
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Integer id;
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
