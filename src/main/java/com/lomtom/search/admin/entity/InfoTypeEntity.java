package com.lomtom.search.admin.entity;


import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.jpa.model.BaseModel;

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
@Table(name = "info_type")
@Erupt(name = "资讯分类")
public class InfoTypeEntity extends BaseModel  implements Serializable{

	/**
	 * 资讯分类编号
	 */
//	@EruptField(
//			views = @View(title = "编号"),
//			edit = @Edit(title = "编号")
//	)
//	private Long id;
	/**
	 * 资讯分类名称
	 */
	@EruptField(
			views = @View(title = "名字"),
			edit = @Edit(title = "名字")
	)
	private String name;

	/**
	 * 资讯分类状态
	 */

	@EruptField(
			views = @View(title = "是否显示"),
			edit = @Edit(title = "是否显示")
	)
	private Boolean status;


	/**
	 * 资讯分类分类
	 */
	@EruptField(
			views = @View(title = "分类"),
			edit = @Edit(title = "分类")
	)
	private String type;

}
