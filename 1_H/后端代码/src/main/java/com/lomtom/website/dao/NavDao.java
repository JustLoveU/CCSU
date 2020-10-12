package com.lomtom.website.dao;

import com.lomtom.website.entity.NavEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-06-07 15:44:22
 */
@Mapper
public interface NavDao{

    @Select("select * from nav")
    @Results(value = {
            @Result(property = "parentNav.id",column = "parent_id")
    })
    List<NavEntity> getAll();


    @Select("select a.id as aid,a.name as aname,b.id as bid,b.name as bname from nav a left JOIN nav b on a.parent_id=b.id where a.id = #{id}")
    @Results(value = {
            @Result(property = "id",column = "aid"),
            @Result(property = "name",column = "aname"),
            @Result(property = "parentNav.id",column = "bid"),
            @Result(property = "parentNav.name",column = "bname")
    })
    NavEntity getById(Integer id);
}
