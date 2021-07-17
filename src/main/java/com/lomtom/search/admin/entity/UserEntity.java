package com.lomtom.search.admin.entity;


import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@Erupt(name = "用户")
@Table(name = "user")
@Entity
public class UserEntity  extends BaseModel implements Serializable {

	/**
	 * 用户名
	 */
	@EruptField(
			views = @View(title = "用户名"),
			edit = @Edit(title = "用户名",search = @Search(vague = true)  )
	)
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 头像
	 */
	@EruptField(
			views = @View(title = "头像"),
			edit = @Edit(title = "头像")
	)
	private String img;

	/**
	 * 简介
	 */
	@EruptField(
			views = @View(title = "简介"),
			edit = @Edit(title = "简介")
	)
	private String intro;

	/**
	 * 邮箱
	 */
	@EruptField(
			views = @View(title = "邮箱"),
			edit = @Edit(title = "邮箱")
	)
	private String email;

	/**
	 * 注册时间
	 */

	@EruptField(
			views = @View(title = "注册时间"),
			edit = @Edit(title = "注册时间")
	)
	private Date date;

	/**
	 * 是否可用
	 */

	@EruptField(
			views = @View(title = "是否激活"),
			edit = @Edit(title = "是否激活")
	)
	private Boolean status;

	/**
	 * 角色
	 */
	private String role;

}


