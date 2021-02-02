package com.lomtom.website.service;

import com.github.pagehelper.PageInfo;
import com.lomtom.website.entity.FriendEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-06-29 09:16:27
 */
public interface FriendService{

    PageInfo queryPage(Map<String, Object> params);

    FriendEntity getById(Integer id);

    void save(FriendEntity friend);

    void updateById(FriendEntity friend);

    void removeByIds(List<Integer> asList);
}

