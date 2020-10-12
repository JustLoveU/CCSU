package com.lomtom.website.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lomtom.website.dao.NavDao;
import com.lomtom.website.entity.NavEntity;
import com.lomtom.website.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/28
 * @Time: 10:47
 * @Email: lomtom@qq.com
 */
@Service
public class NavServiceImpl implements NavService {

    @Autowired
    NavDao navDao;


    @Override
    public List<NavEntity> getAll() {
        return getAll(navDao.getAll());
    }

    @Override
    public NavEntity getByid(Integer id) {
        return navDao.getById(id);
    }


    /**
     * 分离根节点与子节点
     * @param list
     * @return
     */
    public List<NavEntity> getAll(List<NavEntity> list){
        List<NavEntity> root = new ArrayList<>();
        List<NavEntity> body = new ArrayList<>();
        for (NavEntity menu:list){
            if (menu.getParentNav().getId()==0){
                root.add(menu);
            }
            else {
                body.add(menu);
            }
        }
        return getTree(root, body);
    }

    /**
     *
     * @param rootList
     * @param bodyList
     * @return
     */
    public List<NavEntity> getTree(List<NavEntity> rootList,List<NavEntity> bodyList){
        if(bodyList != null && !bodyList.isEmpty()){
            //声明一个map，用来过滤已操作过的数据
            Map<Integer,Integer> map = Maps.newHashMapWithExpectedSize(bodyList.size());
            rootList.forEach(beanTree -> getChild(beanTree,map,bodyList));
            return rootList;
        }
        return null;
    }

    /**
     * 每个处于根节点的节点获取属于自己的子节点
     * @param treeDto
     * @param map
     * @param bodyList
     */
    public void getChild(NavEntity treeDto,Map<Integer,Integer> map,List<NavEntity> bodyList){
        List<NavEntity> childList = Lists.newArrayList();
        bodyList.stream()
                .filter(c -> !map.containsKey(c.getId()))
                .filter(c ->c.getParentNav().getId()==treeDto.getId())
                .forEach(c ->{
                    map.put(c.getId(),c.getParentNav().getId());
                    getChild(c,map,bodyList);
                    childList.add(c);
                });
        treeDto.setSonNav(childList);
        //子数据集
    }
}
