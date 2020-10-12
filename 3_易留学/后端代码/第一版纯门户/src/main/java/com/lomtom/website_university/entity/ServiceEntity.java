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
 * @date 2020-07-16 19:41:31
 */
@Data
@TableName("service")
public class ServiceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 服务编号
	 */
	@TableId
	private Integer id;
	/**
	 * 服务名字
	 */
	private String name;
	/**
	 * 服务链接
	 */
	private String url;
	/**
	 * 服务是否显示
	 */
	private Boolean status;

}
