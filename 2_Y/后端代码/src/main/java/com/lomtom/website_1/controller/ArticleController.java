package com.lomtom.website_1.controller;

import com.lomtom.website_1.config.Config;
import com.lomtom.website_1.dao.FriendDao;
import com.lomtom.website_1.dao.OtherMapper;
import com.lomtom.website_1.entity.ArticleEntity;
import com.lomtom.website_1.service.ArticleService;
import com.lomtom.website_1.service.OtherService;
import com.lomtom.website_1.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/7
 * @Time: 15:59
 * @Email: lomtom@qq.com
 */

@Controller
public class ArticleController {


    @Autowired
    ArticleService articleService;


    @Autowired
    FriendDao friendDao;

    @Autowired
    OtherService otherService;

    @Autowired
    ProfileService profileService;

    @GetMapping(value = {"/index","/"})
    public String index(Model model){
        model.addAttribute("other1",otherService.getOther(2));
        model.addAttribute("inner",articleService.getArticleInner());
        model.addAttribute("friend",friendDao.query());
        model.addAttribute("article1",articleService.getArticleNews("工作动态"));
        model.addAttribute("article2",articleService.getArticleNews("国家政策"));
        model.addAttribute("article3",articleService.getArticleNews("学者新论"));
        model.addAttribute("profile",profileService.getAll());
        return "index1";
    }



    @Autowired
    Config config;

    @GetMapping("/nav")
    public String nav(@RequestParam("nav") Integer nav, Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        String s = config.getMaps().get(nav);
        model.addAttribute("s",s);
        model.addAttribute("nav",nav);
        model.addAttribute("other1",otherService.getOther(2));
        model.addAttribute("friend",friendDao.query());
        model.addAttribute("article",articleService.getArticlesByNavId(s,pageNum,pageSize));
        return "list";
    }

    @GetMapping("/nav1")
    public String nav1(@RequestParam("nav") String nav, Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        model.addAttribute("nav",nav);
        model.addAttribute("other1",otherService.getOther(2));
        model.addAttribute("friend",friendDao.query());
        model.addAttribute("article",articleService.getArticlesByNavId(nav,pageNum,pageSize));
        return "list";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(value = "title") String title, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        model.addAttribute("search",title);
        model.addAttribute("other1",otherService.getOther(2));
        model.addAttribute("friend",friendDao.query());
        model.addAttribute("article",articleService.getArticlesByname("%"+title+"%",pageNum,pageSize));
        return "list";
    }

    @GetMapping("/article/{id}")
    public String article(@PathVariable(value = "id") Integer id, Model model){
        model.addAttribute("other1",otherService.getOther(2));
        model.addAttribute("friend",friendDao.query());
        ArticleEntity articleEntity = articleService.getArticleById(id);
        if (null == articleEntity){
            return "error/404";
        }
        else {
            model.addAttribute("article", articleEntity);
            return "detail";
        }
    }

}
