package com.lomtom.website_university.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lomtom.website_university.entity.ApplyEntity;
import com.lomtom.website_university.entity.Verification;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@Mapper
public interface VerificationDao extends BaseMapper<Verification> {
	
}
