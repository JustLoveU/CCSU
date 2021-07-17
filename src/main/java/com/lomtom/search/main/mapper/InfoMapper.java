package com.lomtom.search.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lomtom.search.main.entity.InfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/13
 * @Time: 18:03
 */

@Mapper
public interface InfoMapper extends BaseMapper<InfoEntity> {

    @Select("select  source  from info where source is not NULL group BY source ORDER BY count(*) desc")
    List<String> selectSources();
}
