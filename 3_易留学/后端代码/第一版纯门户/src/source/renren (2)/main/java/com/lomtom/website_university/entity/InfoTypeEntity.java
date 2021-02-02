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
@TableName("info_type")
public class InfoTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 资讯分类编号
	 */
	@TableId
	private Integer id;
	/**
	 * 资讯分类名称
	 */
	private String name;
	/**
	 * 资讯分类状态
	 */
	private Boolean status;
	/**
	 * 资讯分类的分类
	 */
	private String type;
	/**
	 * 图片
	 */
	private String img;

}
