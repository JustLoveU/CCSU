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
 * @date 2020-07-16 19:41:32
 */
@Entity
@Table(name = "result")
@Erupt(name = "结果")
public class ResultEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 留学申请编号
	 */
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "native")
	@Column(name = "id")
	@EruptField
	private Integer id;
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


	@ManyToOne
	@EruptField(
			views = @View(title = "关联申请",column = "id",width = "10%"),
			edit = @Edit(title = "关联申请", type = EditType.REFERENCE_TABLE,
					referenceTableType = @ReferenceTableType(id = "id", label = "id")
			)
	)
	@JoinColumn(name = "apply_id")
	private ApplyEntity applyEntity;


	@EruptField(
			views = @View(
					title = "进度"
			),
			edit = @Edit(title = "进度")
	)
	private Integer rate;

	@ManyToOne
	@EruptField(
			views = @View(title = "关联大学1",column = "name",width = "10%"),
			edit = @Edit(title = "关联大学1", type = EditType.REFERENCE_TABLE,
					referenceTableType = @ReferenceTableType(id = "id", label = "name")
			)
	)
	@JoinColumn(name = "university_id1")
	private UniversityEntity universityEntity1;

	@ManyToOne
	@EruptField(
			views = @View(title = "关联大学2",column = "name",width = "10%"),
			edit = @Edit(title = "关联大学2", type = EditType.REFERENCE_TABLE,
					referenceTableType = @ReferenceTableType(id = "id", label = "name")
			)
	)
	@JoinColumn(name = "university_id2")
	private UniversityEntity universityEntity2;

	@ManyToOne
	@EruptField(
			views = @View(title = "关联大学3",column = "name",width = "10%"),
			edit = @Edit(title = "关联大学3", type = EditType.REFERENCE_TABLE,
					referenceTableType = @ReferenceTableType(id = "id", label = "name")
			)
	)
	@JoinColumn(name = "university_id3")
	private UniversityEntity universityEntity3;


	@EruptField(
			views = @View(
					title = "语言概率"
			),
			edit = @Edit(title = "语言概率")
	)
	private Integer language;

	@EruptField(
			views = @View(
					title = "成绩概率"
			),
			edit = @Edit(title = "成绩概率")
	)
	private Integer number;

	@EruptField(
			views = @View(
					title = "费用概率"
			),
			edit = @Edit(title = "费用概率")
	)
	private Integer fee;

	@EruptField(
			views = @View(
					title = "时间概率"
			),
			edit = @Edit(title = "时间概率")
	)
	private Integer time;

	@EruptField(
			views = @View(
					title = "专业概率"
			),
			edit = @Edit(title = "专业概率")
	)
	private Integer major;

}
