package com.lomtom.website_1.service;

import com.github.pagehelper.PageInfo;
import com.lomtom.website_1.entity.ArticleEntity;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/7
 * @Time: 16:02
 * @Email: lomtom@qq.com
 */

public interface ArticleService {
    ArticleEntity getArticleByNavId(String nav);

    PageInfo getArticlesByNavId(String nav, Integer pageNum, Integer pageSize);

    ArticleEntity getArticleById(Integer id);

    List<ArticleEntity> getArticleInner();

    List<ArticleEntity> getArticleNews(String s);

    PageInfo getArticlesByname(String title, Integer pageNum, Integer pageSize);

}
