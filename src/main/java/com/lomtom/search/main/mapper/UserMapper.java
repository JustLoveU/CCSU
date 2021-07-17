package com.lomtom.search.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lomtom.search.main.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/12
 * @Time: 20:16
 */

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
