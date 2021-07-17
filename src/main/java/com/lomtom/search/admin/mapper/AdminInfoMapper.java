package com.lomtom.search.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lomtom.search.main.entity.InfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/11
 * @Time: 12:26
 */

@Mapper
public interface AdminInfoMapper extends BaseMapper<InfoEntity> {

    /**
     * 查询总数
     * @return 总数
     */
    @Select("select count(*) from info")
    long count();

    @Select("select * from info where deleted = 0")
    List<InfoEntity> selectAll();
}
