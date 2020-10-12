package com.lomtom.website_1.dao;

import com.lomtom.website_1.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-06-07 15:44:22
 */
@Mapper
public interface ArticleDao{

    @Select("select  * from article where nav = #{nav} limit 1")
    ArticleEntity getArticleByNavId(String nav);

    @Select("select  * from article where nav = #{nav} order by data desc")
    List<ArticleEntity> getArticlesByNavId(String nav);

    @Select("select  * from article where id = #{id} limit 1")
    ArticleEntity getArticleById(Integer id);

    @Select("select  * from article where ischart = 1 order by data desc limit 8")
    List<ArticleEntity> getArticleInner();

    @Select("select  * from article where nav = #{s} order by data desc  limit 10 ")
    List<ArticleEntity> getArticleNews(String s);

    @Select("select  * from article where title like #{title} order by data desc ")
    List<ArticleEntity> getArticlesByname(String title);

    @Update("update article set view=view+1 where id = #{id} ")
    void update(Integer id);
}
