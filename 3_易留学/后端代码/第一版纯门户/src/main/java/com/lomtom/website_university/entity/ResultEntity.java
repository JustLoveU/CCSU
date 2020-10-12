package com.lomtom.website_university.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@Data
@TableName("result")
public class ResultEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 留学申请编号
	 */
	@TableId(type= IdType.AUTO)
	private Integer id;
	/**
	 * 用户编号
	 */
	private Integer userId;


	private Integer applyId;

	private Integer rate;

	private Integer universityId1;
	private Integer universityId2;
	private Integer universityId3;


	private Integer language;
	private Integer number;
	private Integer fee;
	private Integer time;
	private Integer major;

}
