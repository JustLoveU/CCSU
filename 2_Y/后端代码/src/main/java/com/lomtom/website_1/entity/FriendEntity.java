package com.lomtom.website_1.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-06-29 09:16:27
 */
@Data
public class FriendEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	private Integer id;
	/**
	 * 链接
	 */
	private String url;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 是否显示
	 */
	private Boolean status;

}
