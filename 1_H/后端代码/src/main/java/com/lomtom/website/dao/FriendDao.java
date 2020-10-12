package com.lomtom.website.dao;

import com.lomtom.website.entity.FriendEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-06-29 09:16:27
 */
@Mapper
public interface FriendDao {
    @Select("select * from lab_friend")
    List<FriendEntity> queryPage();


    @Select("select * from lab_friend where status = 1")
    List<FriendEntity> query();

    @Select("select * from lab_friend where id = #{id} limit 1")
    FriendEntity getById(Integer id);


    @Insert("INSERT INTO `lab_friend`( `url`, `name`, `status`) VALUES ( #{url} , #{name} , #{status} );")
    void save(FriendEntity friend);

    @Delete("delete from lab_friend where id = #{id} ")
    void removeByIds(Integer id);

    @Update("UPDATE `lab_friend` SET `url` = #{url}, `name` = #{name}, `status` = #{status} WHERE `id` = #{id};")
    void updateById(FriendEntity friend);
}
