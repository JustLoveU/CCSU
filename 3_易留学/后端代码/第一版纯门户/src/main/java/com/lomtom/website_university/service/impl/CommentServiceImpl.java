package com.lomtom.website_university.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lomtom.website_university.entity.CommentAndUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.Query;

import com.lomtom.website_university.dao.CommentDao;
import com.lomtom.website_university.entity.CommentEntity;
import com.lomtom.website_university.service.CommentService;


@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, CommentEntity> implements CommentService {

    @Autowired
    CommentDao commentDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentEntity> page = this.page(
                new Query<CommentEntity>().getPage(params),
                new QueryWrapper<CommentEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public IPage<CommentAndUserEntity> queryPage(Map<String, Object> params, Integer id) {
        long current =  Long.parseLong((String)params.get("page"));
        long size =  Long.parseLong((String)params.get("limit"));
        Page<CommentAndUserEntity> page = new Page<>(current,size);
        return commentDao.getCommentAndUser(page,id);
    }


    @Override
    public IPage<CommentEntity> queryPageByUser(Map<String, Object> params, Integer id) {
        long current =  Long.parseLong((String)params.get("page"));
        long size =  Long.parseLong((String)params.get("limit"));
        Page<CommentEntity> page = new Page<>(current,size);
        return commentDao.getCommentAndUserByUser(page,id);
    }
}