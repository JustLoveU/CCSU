package com.lomtom.search.admin.entity;


import com.lomtom.search.admin.es.proxy.InfoDataProxy;
import org.hibernate.annotations.SQLDelete;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.LinkTree;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.ViewType;
import xyz.erupt.annotation.sub_field.sub_edit.HtmlEditorType;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTableType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static xyz.erupt.annotation.sub_field.sub_edit.HtmlEditorType.Type.CKEDITOR;

/**
 * 
 * 
 * @author lomtom
 * @date 2020-07-16 19:41:32
 */
@Erupt(name = "资讯",linkTree = @LinkTree(field = "infoTypeEntity"),dataProxy = InfoDataProxy.class)
@Table(name = "info")
@SQLDelete(sql="update info set deleted = 1 where id = ?")
@Entity
public class InfoEntity extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 资讯分类编号
	 */
	@ManyToOne
	@EruptField(
			views = @View(title = "关联分类",column = "name",width = "10%"),
			edit = @Edit(title = "关联分类", type = EditType.REFERENCE_TABLE,
					referenceTableType = @ReferenceTableType(id = "id", label = "name")
			)
	)
	@JoinColumn(name = "type_id")
	private InfoTypeEntity infoTypeEntity;

	/**
	 * 资讯标题
	 */
	@EruptField(
			views = @View(title = "标题"),
			edit = @Edit(title = "标题",search = @Search(vague = true)  )
	)
	private String title;

	/**
	 * 资讯发布时间
	 */
	@EruptField(
			views = @View(title = "发布时间"),
			edit = @Edit(
					title = "发布时间",
					type = EditType.HIDDEN
			)
	)
	private Date time = new Date();

	/**
	 * 资讯内容
	 */
	@Lob
	@EruptField(
			views = @View(title = "内容",type = ViewType.HTML),
			edit = @Edit(title = "内容(UEditor)",
					type = EditType.HTML_EDITOR,
					htmlEditorType = @HtmlEditorType(CKEDITOR))
	)
	private String content;

	/**
	 * 资讯浏览次数
	 */
	@EruptField(
			views = @View(title = "访问次数"),
			edit = @Edit(
					title = "访问次数",
					type = EditType.HIDDEN
			)
	)
	private Integer view;
	/**
	 * 资讯链接
	 */

	@EruptField(
			views = @View(
					title = "链接",
					type = ViewType.LINK
			),
			edit = @Edit(title = "来源链接")
	)
	private String url;
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


	@EruptField(
			views = @View(
					title = "来源"
			),
			edit = @Edit(title = "来源")
	)
	private String source;

	@EruptField(
			views = @View(
					title = "是否删除",
					type = ViewType.BOOLEAN
			),
			edit = @Edit(title = "是否删除")
	)
	private Boolean deleted;


	@Override
	public String toString() {
		return "InfoEntity{" +
				", infoTypeEntity=" + infoTypeEntity +
				'}';
	}
}



