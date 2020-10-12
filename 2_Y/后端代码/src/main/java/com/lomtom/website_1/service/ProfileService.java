package com.lomtom.website_1.service;

import com.lomtom.website_1.entity.ProfileEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/7
 * @Time: 16:03
 * @Email: lomtom@qq.com
 */

public interface ProfileService {
    List<ProfileEntity> getAll();

    ProfileEntity getOneById(Integer id);

    ProfileEntity getOneByName(String name);


    List<ProfileEntity> getAll_admin();

    ProfileEntity getArticleById(Integer id);

    Boolean add(ProfileEntity articleEntity);

    Boolean update(ProfileEntity articleEntity);

    Boolean delete(Integer id);

    void updateStatus(ProfileEntity profileEntity);
}
