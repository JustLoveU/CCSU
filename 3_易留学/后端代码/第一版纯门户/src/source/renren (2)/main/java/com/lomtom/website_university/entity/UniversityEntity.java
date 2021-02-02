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
 * @date 2020-08-28 12:18:04
 */
@Data
@TableName("university")
public class UniversityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 大学编号
	 */
	@TableId
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
	/**
	 * 地区
	 */
	private Integer infoTypeId;
	/**
	 * 成立时间
	 */
	private String setData;
	/**
	 * 坐标
	 */
	private String location;
	/**
	 * 电话
	 */
	private String tel;
	/**
	 * 图片
	 */
	private String pic;
	/**
	 * 网站链接
	 */
	private String url;

}
