package com.lomtom.search.admin.entity;


import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTableType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:31
 */
@Entity
@Table(name = "progress")
@Erupt(name = "进度")
public class ProgressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "native")
	@Column(name = "id")
	@EruptField
	private Integer id;
	/**
	 * 申请编号
	 */

	@ManyToOne
	@EruptField(
			views = @View(title = "关联用户",column = "id",width = "10%"),
			edit = @Edit(title = "申请用户", type = EditType.REFERENCE_TABLE,
					referenceTableType = @ReferenceTableType(id = "id", label = "id")
			)
	)
	@JoinColumn(name = "apply_id")
	private ApplyEntity applyEntity;
	/**
	 * 进度
	 */
	@EruptField(
			views = @View(
					title = "进度"
			),
			edit = @Edit(title = "进度")
	)
	private String progress;

}
