package com.lomtom.search.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 
 * 
 * @author lomtom
 * @date 2020-07-16 19:41:32
 */
@Data
@TableName("university")
@Document(indexName = "university", shards = 5)
@Table(name = "university")
public class UniversityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 大学编号
	 */
	@Id
	@TableId(type= IdType.AUTO)
	@Field(type = FieldType.Keyword)
	private Long id;

	/**
	 * 大学简介
	 */
	@Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
	private String intro;

	/**
	 * 大学名字
	 */
	@Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
	private String name;

	/**
	 * 大学评价星级
	 */
	@Field
	private Integer star;
	/**
	 * 大学学费
	 */
	@Field
	private String tuition;
	/**
	 * 大学浏览次数
	 */
	@Field
	private Integer view;
	/**
	 * 大学头像
	 */
	@Field
	private String img;

	@Field
	private Integer infoTypeId;

	@Field(type = FieldType.Date)
	private String setData;

	@Field
	private String location;

	@Field
	private String tel;

	@Field
	private String pic;

	@Field
	private String url;

	@Field
	@TableField(exist = false)
	private Integer collection;

}
