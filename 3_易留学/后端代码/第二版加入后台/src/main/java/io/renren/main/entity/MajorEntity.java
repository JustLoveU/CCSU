package io.renren.main.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@Data
@TableName("major")
public class MajorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 专业编号
	 */
	@TableId
	private Integer id;
	/**
	 * 大学编号
	 */
	private Integer universityId;
	/**
	 * 专业名字
	 */
	private String name;

}
