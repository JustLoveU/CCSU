package io.renren.main.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.main.entity.CommentAndUserEntity;
import io.renren.main.entity.CommentEntity;
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
public interface CommentDao extends BaseMapper<CommentEntity> {

    @Select("SELECT * FROM `comment` left join `user` on `comment`.user_id = `user`.id where university_id =#{id}  ORDER BY `time` desc")
    IPage<CommentAndUserEntity> getCommentAndUser(Page<CommentAndUserEntity> page, Integer id);


    @Select("SELECT * FROM `comment`where  user_id = #{id}  ORDER BY `time` desc")
    IPage<CommentEntity> getCommentAndUserByUser(Page<CommentEntity> page,Integer id);
}
