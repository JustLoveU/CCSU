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
@TableName("info")
public class InfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 资讯编号
	 */
	@TableId(type= IdType.AUTO)
	private Integer id;
	/**
	 * 资讯分类编号
	 */
	private Integer typeId;
	/**
	 * 资讯标题
	 */
	private String title;
	/**
	 * 资讯发布时间
	 */
	private Date time;
	/**
	 * 资讯内容
	 */
	private String content;
	/**
	 * 资讯浏览次数
	 */
	private Integer view;
	/**
	 * 资讯链接
	 */
	private String url;
	/**
	 * 大学编号
	 */
	private Integer universityId;

	private String source;

}
