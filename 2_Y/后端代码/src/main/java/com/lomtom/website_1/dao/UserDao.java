package com.lomtom.website_1.dao;

import com.lomtom.website_1.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-06-29 16:24:02
 */
@Mapper
public interface UserDao {

    @Select("select * from lab_user")
    List<UserEntity> queryPage();

    @Select("select * from lab_user where id = #{id} limit 1")
    UserEntity getById(Integer id);


    @Insert("INSERT INTO `lab_user`( `password`, `username`, `uuid`,`name`) VALUES ( #{password} , #{username} , #{uuid}, #{name} );")
    void save(UserEntity userEntity);

    @Delete("delete from lab_user where id = #{id} ")
    void removeByIds(Integer id);

    @Update("UPDATE `lab_user` SET `password` = #{password}, `username` = #{username}, `uuid` = #{uuid}, `name` = #{name} WHERE `id` = #{id};")
    void updateById(UserEntity userEntity);

    @Select("select * from lab_user where username = #{username} and password = #{password} ")
    UserEntity login(UserEntity user);
}
