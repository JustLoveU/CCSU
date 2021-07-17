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
@Table(name = "history")
@Erupt(name = "历史")
public class HistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 历史编号
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
	/**
	 * 分类
	 */

	@EruptField(
			views = @View(
					title = "分类"
			),
			edit = @Edit(title = "分类")
	)
	private String type;
	/**
	 * 大学或者咨询编号
	 */
	@ManyToOne
	@EruptField(
			views = @View(title = "关联大学",column = "name",width = "10%"),
			edit = @Edit(title = "关联大学", type = EditType.REFERENCE_TABLE,
					referenceTableType = @ReferenceTableType(id = "id", label = "name")
			)
	)
	@JoinColumn(name = "university_id")
	private UniversityEntity universityEntity;

}
