package com.lomtom.search.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lomtom.search.main.entity.UniversityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/13
 * @Time: 17:56
 */
@Mapper
public interface UniversityMapper extends BaseMapper<UniversityEntity> {

    @Select("select id from info_type where name=#{source}")
    Integer getRegion(String source);

    @Select("SELECT `university`.id,`university`.`name`,`university`.intro,`university`.img  FROM `collection` left join `university` on `university`.id = `collection`.university_id where user_id = #{id}")
    List<UniversityEntity> ListByUniversity(Integer id);
}
