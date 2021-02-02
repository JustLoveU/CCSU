package com.lomtom.website_university.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @date 2020-07-16 19:41:32
 */
@Data
@TableName("university")
public class UniversityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 大学编号
	 */
	@TableId(type= IdType.AUTO)
	private Integer id;
	/**
	 * 大学简介
	 */
	private String intro;
	/**
	 * 大学名字
	 */
	private String name;
	/**
	 * 大学评价星级
	 */
	private Integer star;
	/**
	 * 大学学费
	 */
	private String tuition;
	/**
	 * 大学浏览次数
	 */
	private Integer view;
	/**
	 * 大学头像
	 */
	private String img;

	private Integer infoTypeId;


	private String setData;

	private String location;

	private String tel;

	private String pic;

	private String url;

}
