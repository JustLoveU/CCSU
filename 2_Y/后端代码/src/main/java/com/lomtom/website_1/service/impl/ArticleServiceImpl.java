package com.lomtom.website_1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lomtom.website_1.dao.ArticleDao;
import com.lomtom.website_1.entity.ArticleEntity;
import com.lomtom.website_1.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/7
 * @Time: 16:03
 * @Email: lomtom@qq.com
 */

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleDao;

    @Override
    public ArticleEntity getArticleByNavId(String nav) {
        ArticleEntity articleEntity =articleDao.getArticleByNavId(nav);
        if (articleEntity != null) {
            articleDao.update(articleEntity.getId());
        }
        return articleEntity;
    }

    @Override
    public PageInfo getArticlesByNavId(String nav,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(articleDao.getArticlesByNavId(nav));
    }

    @Override
    public ArticleEntity getArticleById(Integer id) {
        articleDao.update(id);
        return  articleDao.getArticleById(id);
    }

    @Override
    public List<ArticleEntity> getArticleInner() {
        return articleDao.getArticleInner();
    }

    @Override
    public List<ArticleEntity> getArticleNews(String s) {
        return articleDao.getArticleNews(s);
    }

    @Override
    public PageInfo getArticlesByname(String title, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(articleDao.getArticlesByname(title));
    }
}
