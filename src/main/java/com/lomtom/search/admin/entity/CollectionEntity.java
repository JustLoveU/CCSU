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
@Table(name = "collection")
@Erupt(name = "收藏")
public class CollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 收藏编号
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
			views = @View(title = "用户",column = "username"),
			edit = @Edit(title = "用户", type = EditType.REFERENCE_TABLE,
					referenceTableType = @ReferenceTableType(id = "id", label = "username")
			)
	)
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;
	/**
	 * 大学编号
	 */
	@ManyToOne
	@EruptField(
			views = @View(title = "大学",column = "name"),
			edit = @Edit(title = "大学", type = EditType.REFERENCE_TABLE,
					referenceTableType = @ReferenceTableType(id = "id", label = "name")
			)
	)
	@JoinColumn(name = "university_id")
	private UniversityEntity universityEntity;

}
