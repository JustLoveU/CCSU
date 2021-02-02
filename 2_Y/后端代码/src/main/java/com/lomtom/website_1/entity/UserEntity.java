package com.lomtom.website_1.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-06-29 16:24:02
 */
@Data
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private String uuid;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private Integer id;

}
