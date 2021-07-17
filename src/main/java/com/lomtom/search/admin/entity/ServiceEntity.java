package com.lomtom.search.admin.entity;

import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;

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
@Table(name = "service")
@Erupt(name = "服务分类")
public class ServiceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 服务编号
	 */
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "native")
	@Column(name = "id")
	@EruptField
	private Integer id;
	/**
	 * 服务名字
	 */
	@EruptField(
			views = @View(
					title = "服务名字"
			),
			edit = @Edit(title = "服务名字")
	)
	private String name;
	/**
	 * 服务链接
	 */
	@EruptField(
			views = @View(
					title = "服务链接"
			),
			edit = @Edit(title = "服务链接")
	)
	private String url;
	/**
	 * 服务是否显示
	 */
	@EruptField(
			views = @View(
					title = "服务是否显示"
			),
			edit = @Edit(title = "服务是否显示")
	)
	private Boolean status;

}
