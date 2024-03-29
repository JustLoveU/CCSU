package io.renren.main.entity;

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
@TableName("apply")
public class ApplyEntity implements Serializable {
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
	/**
	 * 留学申请预选区域
	 */
	private String address;
	/**
	 * 留学申请计划攻读学位
	 */
	private String degree;
	/**
	 * 留学申请已学习年限
	 */
	private Integer years;
	/**
	 * 留学申请在读专业
	 */
	private String major;
	/**
	 * 留学申请出国时间
	 */
	private String outsideDate;
	/**
	 * 留学申请就读院校
	 */
	private String university;
	/**
	 * 留学申请成绩
	 */
	private Double score;
	/**
	 * 留学申请手机号码
	 */
	private String tel;

	private Date applyDate;

}
