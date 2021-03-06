package com.lomtom.website_1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lomtom.website_1.dao.FriendDao;
import com.lomtom.website_1.entity.FriendEntity;
import com.lomtom.website_1.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("friendService")
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendDao friendDao;

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt(String.valueOf(params.get("page"))),Integer.parseInt(String.valueOf(params.get("limit"))));

        return new PageInfo<>(friendDao.queryPage());
    }

    @Override
    public FriendEntity getById(Integer id) {
        return friendDao.getById(id);
    }

    @Override
    public void save(FriendEntity friend) {
        friendDao.save(friend);
    }

    @Override
    public void updateById(FriendEntity friend) {
        friendDao.updateById(friend);
    }

    @Override
    public void removeByIds(List<Integer> asList) {
        for (Integer id: asList) {
            friendDao.removeByIds(id);
        }
    }

}