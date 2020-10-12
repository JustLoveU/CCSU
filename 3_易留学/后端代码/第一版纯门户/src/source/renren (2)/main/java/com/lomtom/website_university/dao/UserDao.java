package com.lomtom.website_university.dao;

import com.lomtom.website_university.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-08-28 12:18:05
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
