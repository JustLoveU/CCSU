package com.lomtom.website_1.dao;

import com.lomtom.website_1.entity.ArticleEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-06-07 15:44:22
 */
@Mapper
public interface ArticleMapper {



    @Select("select  * from article order by data desc")
    List<ArticleEntity> getAll();

    @Select("select  * from article where id = #{id} limit 1")
    ArticleEntity getArticleById(Integer id);

    @Insert("INSERT INTO article" +
            "( `view`, `data`, `content`, `title`, `img`, `nav`, `url`, ischart )" +
            "VALUES (#{view},#{data},#{content},#{title},#{img},#{nav},#{url},#{ischart})")
    Boolean add(ArticleEntity articleEntity);

    @Update("UPDATE article" +
            " SET `view` = #{view} ," +
            " `data` = #{data} , " +
            " `content` = #{content} , `title` =  #{title} , " +
            " `img` = #{img}," +
            " `nav` = #{nav} ," +
            " `url` = #{url}," +
            " `ischart` = #{ischart} " +
            " WHERE `id` = #{id} ;\n")
    Boolean update(ArticleEntity articleEntity);

//    @Update("UPDATE article" +
//            " SET `ischart` = 1 " +
//            " WHERE `id` = #{id} ;\n")
//    Boolean update(Integer id);

    @Delete("delete from article where id = #{id} ")
    Boolean delete(Integer id);
}
