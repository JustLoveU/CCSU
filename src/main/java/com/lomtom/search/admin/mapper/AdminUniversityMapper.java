package com.lomtom.search.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lomtom.search.main.entity.UniversityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/19
 * @Time: 9:54
 */
@Mapper
public interface AdminUniversityMapper extends BaseMapper<UniversityEntity> {
    @Select("select count(university_id) as collection,university.* from university " +
            "left join collection on university.id = university_id group by university.id")
    List<UniversityEntity> selectAll();

    @Select("select count(*) from university")
    long count();

    @Select("select count(university_id) as collection,university.* from university " +
            "left join collection on university.id = university_id where university.id = #{id} limit 1")
    UniversityEntity selectById1(Long id);
}
