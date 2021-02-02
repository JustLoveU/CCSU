package com.lomtom.website.dao;

import com.lomtom.website.entity.ProfileEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/25
 * @Time: 12:28
 * @Email: lomtom@qq.com
 */

@Mapper
public interface ProfileMapper {
    @Select("select  * from lab_profile")
    List<ProfileEntity> getAll();

    @Select("select * from lab_profile where profile_id = #{id} limit 1")
    ProfileEntity getArticleById(Integer id);

    @Insert("INSERT INTO lab_profile" +
            "(`profile_picpath`, `profile_intro`, `profile_cnname`, `profile_education`,`profile_position`, `profile_telephone`, `profile_email`, `profile_studydir`, `profile_address`, `profile_workexp`, `profile_edubackground`,`project`,`is_home`,`weight`) " +
            "VALUES (#{profilePicpath} , #{profileIntro} , #{profileCnname} , #{profileEducation} ,#{profilePosition} , #{profileTelephone} , #{profileEmail} , #{profileStudydir} , #{profileAddress} , #{profileWorkexp} , #{profileEdubackground},#{project}  ,#{isHome},#{weight} );\n")
    Boolean add(ProfileEntity articleEntity);

    @Update("UPDATE `lab_profile` SET " +
            "`profile_picpath` = #{profilePicpath} , " +
            "`profile_intro` = #{profileIntro} , " +
            "`profile_cnname` = #{profileCnname} , " +
            "`profile_education` = #{profileEducation} , " +
            "`profile_position` = #{profilePosition} , " +
            "`profile_telephone` = #{profileTelephone} , " +
            "`profile_email` = #{profileEmail} , " +
            "`profile_studydir` = #{profileStudydir} , " +
            "`profile_address` = #{profileAddress} , " +
            "`profile_workexp` = #{profileWorkexp} , " +
            "`profile_edubackground` = #{profileEdubackground} , " +
            "`project` = #{project} , " +
            "`is_home` = #{isHome} , " +
            "`weight` = #{weight} , " +
            " profile_status = #{profileStatus} " +
            "WHERE `profile_id` = #{profileId} ;\n")
    Boolean update(ProfileEntity articleEntity);

//    @Update("UPDATE article" +
//            " SET `ischart` = 1 " +
//            " WHERE `id` = #{id} ;\n")
//    Boolean update(Integer id);

    @Delete("delete from lab_profile where profile_id = #{id} ")
    Boolean delete(Integer id);

    @Update("UPDATE `lab_profile` SET " +
            " profile_status = #{profileStatus} " +
            "WHERE `profile_id` = #{profileId} ;\n")
    void updateStatus(ProfileEntity profileEntity);
}
