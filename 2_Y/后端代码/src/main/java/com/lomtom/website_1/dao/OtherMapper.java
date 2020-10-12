package com.lomtom.website_1.dao;

import com.lomtom.website_1.entity.Other;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: LOMTOM
 * @Date: 2020/7/3
 * @Time: 17:41
 * @Email: lomtom@qq.com
 */
@Mapper
public interface OtherMapper {


    @Select("select * from lab_other where id=#{id}  limit 1")
    Other getOther( Integer id);

    @Update("UPDATE lab_other" +
            " SET `content` = #{content} " +
            " WHERE id=#{id}  ;\n")
    void upateOther(Other other);
}
