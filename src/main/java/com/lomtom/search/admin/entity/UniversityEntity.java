package com.lomtom.search.admin.entity;


import com.lomtom.search.admin.es.proxy.InfoDataProxy;
import com.lomtom.search.admin.es.proxy.UniversityDataProxy;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.ViewType;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTableType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@Entity
@Table(name = "university")
@Erupt(name = "院校",dataProxy = UniversityDataProxy.class)
public class UniversityEntity extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 大学编号
	 */
//	private Integer id;

	/**
	 * 大学名字
	 */

	@EruptField(
			views = @View(title = "名字"),
			edit = @Edit(title = "名字",search = @Search(vague = true)  )
	)
	private String name;


	@EruptField(
			views = @View(title = "官网链接",type = ViewType.LINK),
			edit = @Edit(title = "官网链接")
	)
	private String url;

	/**
	 * 大学简介
	 */
	
	@EruptField(
			edit = @Edit(title = "简介", type = EditType.TEXTAREA)
	)
	private String intro;

	/**
	 * 大学评价星级
	 */
	private Integer star;
	/**
	 * 大学学费
	 */
	private String tuition;
	/**
	 * 大学浏览次数
	 */

	@EruptField(
			views = @View(title = "浏览次数"),
			edit = @Edit(title = "浏览次数")
	)
	private Integer view;


	@ManyToOne
	@EruptField(
			views = @View(title = "地区",column = "name"),
			edit = @Edit(title = "地区", type = EditType.REFERENCE_TABLE,
					referenceTableType = @ReferenceTableType(id = "id", label = "name")
			)
	)
	@JoinColumn(name = "info_type_id")
	private InfoTypeEntity infoTypeEntity;

	/**
	 * 大学头像
	 */

	@EruptField(
			views = @View(title = "logo",type = ViewType.IMAGE),
			edit = @Edit(title = "logo")
	)
	private String img;


	@EruptField(
			views = @View(title = "成立时间"),
			edit = @Edit(title = "成立时间")
	)
	private String setData;

	@EruptField(
			views = @View(title = "位置"),
			edit = @Edit(title = "位置")
	)
	private String location;

	@EruptField(
			views = @View(title = "电话"),
			edit = @Edit(title = "电话")
	)
	private String tel;

	@EruptField(
			views = @View(title = "首图",type = ViewType.IMAGE),
			edit = @Edit(title = "首图")
	)
	private String pic;


}

