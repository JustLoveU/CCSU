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
 * @date 2020-07-16 19:41:31
 */
@Data
@TableName("history")
public class HistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 历史编号
	 */
	@TableId
	private Integer id;
	/**
	 * 用户编号
	 */
	private Integer userId;
	/**
	 * 分类
	 */
	private String type;
	/**
	 * 大学或者咨询编号
	 */
	private Integer universityInfoId;

}
