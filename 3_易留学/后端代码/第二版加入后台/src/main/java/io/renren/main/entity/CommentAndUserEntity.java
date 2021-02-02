package io.renren.main.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@Data
@TableName("comment")
public class CommentAndUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 评论编号
	 */
	@TableId
	private Integer id;
	/**
	 * 用户编号
	 */
	private Integer userId;
	/**
	 * 大学编号
	 */
	private Integer universityId;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 评论时间
	 */
	private Date time;

	private String username;

	private String img;

}
