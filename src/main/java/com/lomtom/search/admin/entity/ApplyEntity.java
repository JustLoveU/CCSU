package com.lomtom.search.admin.entity;


import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTableType;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@Erupt(name = "申请")
@Table(name = "apply")
@Entity
public class ApplyEntity extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
	@ManyToOne
	@EruptField(
			views = @View(title = "关联用户",column = "username",width = "10%"),
			edit = @Edit(title = "关联用户", type = EditType.REFERENCE_TABLE,
					referenceTableType = @ReferenceTableType(id = "id", label = "username")
			)
	)
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;

	/**
	 * 留学申请预选区域
	 */
	@EruptField(
			edit = @Edit(title = "预选区域")
	)
	private String address;
	/**
	 * 留学申请计划攻读学位
	 */
	@EruptField(
			views = @View(title = "预选区域"),
			edit = @Edit(title = "预选区域")
	)
	private String degree;
	/**
	 * 留学申请已学习年限
	 */
	@EruptField(
			edit = @Edit(title = "已学习年限")
	)
	private Integer years;
	/**
	 * 留学申请在读专业
	 */
	@EruptField(
			views = @View(title = "申请在读专业"),
			edit = @Edit(title = "申请在读专业")
	)
	private String major;
	/**
	 * 留学申请出国时间
	 */
	@EruptField(
			edit = @Edit(title = "申请出国时间")
	)
	private String outsideDate;
	/**
	 * 留学申请就读院校
	 */
	@EruptField(
			views = @View(title = "申请就读院校"),
			edit = @Edit(title = "申请就读院校")
	)
	private String university;
	/**
	 * 留学申请成绩
	 */
	@EruptField(
			edit = @Edit(title = "申请成绩")
	)
	private Double score;
	/**
	 * 留学申请手机号码
	 */
	@EruptField(
			views = @View(title = "手机号码"),
			edit = @Edit(title = "预选区域")
	)
	private String tel;

	@EruptField
	private Date applyDate = new Date();

}
