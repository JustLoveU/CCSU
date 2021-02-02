package com.lomtom.website_1.dao;

import com.lomtom.website_1.entity.ProfileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/9
 * @Time: 20:44
 * @Email: lomtom@qq.com
 */

@Mapper
public interface ProfileDao {

//    order by rand()

    @Select("select * from lab_profile where profile_status = 1 limit 12")
    List<ProfileEntity> getAll();

    @Select("select * from lab_profile where profile_id = #{id} limit 1")
    ProfileEntity getOneById(Integer id);

    @Select("select * from lab_profile where profile_cnname = #{name} limit 1")
    ProfileEntity getOneByName(String name);
}
