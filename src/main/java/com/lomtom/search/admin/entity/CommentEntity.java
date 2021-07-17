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
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@Entity
@Table(name = "comment")
@Erupt(name = "评论")
public class CommentEntity  extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 评论编号
	 */
//	@Id
//	@GeneratedValue(generator = "generator")
//	@GenericGenerator(name = "generator", strategy = "native")
//	@Column(name = "id")
//	@EruptField
//	private Integer id;
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
	 * 评论内容
	 */
	@EruptField(
			views = @View(title = "评论内容"),
			edit = @Edit(title = "评论内容")
	)
	private String content;
	/**
	 * 评论时间
	 */
	@EruptField(
			views = @View(title = "评论时间")
	)
	private Date time;


	@ManyToOne
	@EruptField(
			views = @View(title = "关联父级评论",column = "id",width = "10%"),
			edit = @Edit(title = "关联父级评论", type = EditType.REFERENCE_TABLE,
					referenceTableType = @ReferenceTableType(id = "id", label = "id")
			)
	)
	@JoinColumn(name = "parent_id")
	private CommentEntity commentEntity;

}
