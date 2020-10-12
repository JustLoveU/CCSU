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
@TableName("collection")
public class CollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 收藏编号
	 */
	@TableId
	private Integer id;
	/**
	 * 用户编号
	 */
	private Integer userId;
	/**
	 * 大学编号
	 */
	private Integer universityId;

}
