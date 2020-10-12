package com.lomtom.website.dao;

import com.lomtom.website.entity.ArticleEntity;
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

    @Select("select  * from article where nav_id = #{id} limit 1")
    ArticleEntity getArticleByNavId(Integer id);

    @Select("select  * from article where nav_id = #{id} order by data desc")
    List<ArticleEntity> getArticlesByNavId(Integer id);

    @Select("select  * from article where id = #{id} limit 1")
    ArticleEntity getArticleById(Integer id);

    @Select("select  * from article where ischart = 1 order by data desc")
    List<ArticleEntity> getArticleInner();

    @Select("select  * from article where nav_id = 6 order by data desc limit 10 ")
    List<ArticleEntity> getArticleNews();

    @Select("select  * from article where nav_id = 7 order by data desc limit 10")
    List<ArticleEntity> getArticleNews2();

    @Select("select  * from article where title like #{title} order by data desc")
    List<ArticleEntity> getArticlesByname(String title);

    @Update("update article set view= view + #{randomNum} where id = #{id} ")
    void update(Integer id,Integer randomNum);
}
