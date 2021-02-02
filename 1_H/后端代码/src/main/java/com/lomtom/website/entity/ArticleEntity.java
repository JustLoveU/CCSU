package com.lomtom.website.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-06-07 15:44:22
 */
@Data
public class ArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private Integer view;
	/**
	 * 
	 */
	private Date data;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private String title;
	/**
	 * 
	 */
	private String img;
	/**
	 * 
	 */
	private Integer navId;


	private String url;


	private  Integer ischart;

}
