package com.lomtom.website.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lomtom.website.dao.ArticleDao;
import com.lomtom.website.entity.ArticleEntity;
import com.lomtom.website.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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
    public ArticleEntity getArticleByNavId(Integer id) {
        ArticleEntity articleEntity =articleDao.getArticleByNavId(id);
        if (articleEntity != null) {
            Random random = new Random();
            Integer randomNum = random.nextInt(8) +1;
            articleDao.update(id,randomNum);
        }
        return articleEntity;
    }

    @Override
    public PageInfo getArticlesByNavId(Integer id,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(articleDao.getArticlesByNavId(id));
    }

    @Override
    public ArticleEntity getArticleById(Integer id) {
        Random random = new Random();
        Integer randomNum = random.nextInt(8) +1;
        articleDao.update(id,randomNum);
        return  articleDao.getArticleById(id);
    }

    @Override
    public List<ArticleEntity> getArticleInner() {
        return articleDao.getArticleInner();
    }

    @Override
    public List<ArticleEntity> getArticleNews() {
        return articleDao.getArticleNews();
    }
    @Override
    public List<ArticleEntity> getArticleNews2() {
        return articleDao.getArticleNews2();
    }

    @Override
    public PageInfo getArticlesByname(String title, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(articleDao.getArticlesByname(title));
    }
}
