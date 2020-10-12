package com.lomtom.website.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-06-07 15:44:22
 */
@Data
public class NavEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private NavEntity parentNav;

	private String url;

	private Integer type;


	private List<NavEntity> sonNav = new ArrayList<>();

}
