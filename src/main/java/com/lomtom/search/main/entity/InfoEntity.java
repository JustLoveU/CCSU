package com.lomtom.search.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@Data
@TableName("info")
@Document(indexName = "info", shards = 5)
@Table(name = "info")
public class InfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 资讯编号
	 */
	@Id
	@TableId(type= IdType.AUTO)
	@Field(type = FieldType.Keyword)
	private Long id;


	@Field(type = FieldType.Keyword)
	private String source;
	/**
	 * 资讯分类编号
	 */
	@Field(type = FieldType.Integer)
	private Integer typeId;

	/**
	 * 资讯标题
	 */
	@Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
	private String title;

	/**
	 * 资讯发布时间
	 */
	@Field(type = FieldType.Date)
	private Date time = new Date();

	/**
	 * 资讯内容
	 */
	@Field(type = FieldType.Text, analyzer = "ik_smart",searchAnalyzer = "ik_smart")
	private String content;

	/**
	 * 资讯浏览次数
	 */
	@Field(type = FieldType.Integer)
	private Integer view;

	/**
	 * 资讯链接
	 */
	@Field(type = FieldType.Text)
	private String url;
	/**
	 * 大学编号
	 */
	@Field(type = FieldType.Integer)
	private Integer universityId;


	@Field(type = FieldType.Text)
	private String pic;
}
