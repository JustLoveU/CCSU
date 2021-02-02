package com.lomtom.website.service;

import com.github.pagehelper.PageInfo;
import com.lomtom.website.entity.ArticleEntity;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/7
 * @Time: 16:02
 * @Email: lomtom@qq.com
 */

public interface ArticleService {
    ArticleEntity getArticleByNavId(Integer id);

    PageInfo getArticlesByNavId(Integer id, Integer pageNum, Integer pageSize);

    ArticleEntity getArticleById(Integer id);

    List<ArticleEntity> getArticleInner();

    List<ArticleEntity> getArticleNews();

    List<ArticleEntity> getArticleNews2();

    PageInfo getArticlesByname(String title, Integer pageNum, Integer pageSize);
}
