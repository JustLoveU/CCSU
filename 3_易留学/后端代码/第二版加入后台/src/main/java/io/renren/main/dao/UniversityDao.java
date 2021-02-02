package io.renren.main.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.main.entity.UniversityEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@Mapper
public interface UniversityDao extends BaseMapper<UniversityEntity> {

    @Select("SELECT `university`.id,`university`.`name`,`university`.intro,`university`.img  FROM `collection` left join `university` on `university`.id = `collection`.university_id where user_id = #{id}")
    IPage<UniversityEntity> getUniversityBYUser(Page<UniversityEntity> page, Integer id);

}
