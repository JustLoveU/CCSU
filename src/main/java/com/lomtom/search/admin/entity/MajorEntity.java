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
@Table(name = "major")
@Erupt(name = "专业")
public class MajorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 专业编号
	 */
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "native")
	@Column(name = "id")
	@EruptField
	private Integer id;
	/**
	 * 大学编号
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
	/**
	 * 专业名字
	 */
	@EruptField(
			views = @View(
					title = "专业名字"
			),
			edit = @Edit(title = "专业名字")
	)
	private String name;

}
