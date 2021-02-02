package com.lomtom.website.controller;

import com.github.pagehelper.PageInfo;
import com.lomtom.website.dao.ArticleDao;
import com.lomtom.website.dao.ArticleMapper;
import com.lomtom.website.dao.FriendDao;
import com.lomtom.website.entity.ArticleEntity;
import com.lomtom.website.entity.NavEntity;
import com.lomtom.website.service.ArticleService;
import com.lomtom.website.service.NavService;
import com.lomtom.website.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/7
 * @Time: 15:59
 * @Email: lomtom@qq.com
 */

@Controller
public class ArticleController {

    @Autowired
    NavService navService;

    @Autowired
    ArticleService articleService;

    @Autowired
    ProfileService profileService;

    @Autowired
    FriendDao friendDao;

    @Autowired
    ArticleMapper articleMapper;

//    @GetMapping(value = {"/"})
//    public String main(Model model){
//        model.addAttribute("other1",articleMapper.getOther(2));
//        return "main";
//    }

    @GetMapping(value = {"/index","/"})
    public String index(Model model){
        model.addAttribute("friend",friendDao.query());
        model.addAttribute("other",articleMapper.getOther(1));
        model.addAttribute("other1",articleMapper.getOther(2));
        model.addAttribute("nav",navService.getAll());
        model.addAttribute("inner",articleService.getArticleInner());
        model.addAttribute("article1",articleService.getArticleNews());
        model.addAttribute("article2",articleService.getArticleNews2());
//        model.addAttribute("profile1",profileService.getOneByName("李克勤"));
//        model.addAttribute("profile2",profileService.getOneByName("李肯立"));
        model.addAttribute("profile_home",profileService.getHome());
        model.addAttribute("profile",profileService.getAll());
        return "index";
    }

    @GetMapping(value = {"/index1"})
    public String index1(Model model){
        model.addAttribute("other1",articleMapper.getOther(2));
        model.addAttribute("nav",navService.getAll());
        model.addAttribute("inner",articleService.getArticleInner());
        model.addAttribute("article1",articleService.getArticleNews());
        model.addAttribute("article2",articleService.getArticleNews2());
        model.addAttribute("profile",profileService.getAll());
        return "index1";
    }

    @GetMapping("/nav/{type}/{id}")
    public String nav(@PathVariable(value = "type") Integer type, @PathVariable(value = "id") Integer id, Model model,  @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        model.addAttribute("nav",navService.getAll());
        model.addAttribute("other1",articleMapper.getOther(2));
        model.addAttribute("friend",friendDao.query());
        if (type==0){
            ArticleEntity articleEntity = articleService.getArticleByNavId(id);
            if (null == articleEntity){
                return "error/404";
            }
            else {
                model.addAttribute("nav_now",navService.getByid(articleEntity.getNavId()));
                model.addAttribute("nav_tree",navService.getTreeByid(articleEntity.getNavId()));
                model.addAttribute("article", articleEntity);
                return "detail";
            }
        }
        model.addAttribute("nav_now",navService.getByid(id));
        model.addAttribute("nav_tree",navService.getTreeByid(id));
        model.addAttribute("article",articleService.getArticlesByNavId(id,pageNum,pageSize));
        return "list";
    }

    @GetMapping("/search")
    public String search( Model model,  @RequestParam(value = "title") String title,   @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        model.addAttribute("friend",friendDao.query());
        model.addAttribute("other1",articleMapper.getOther(2));
        model.addAttribute("nav",navService.getAll());
        model.addAttribute("search",title);
        model.addAttribute("article",articleService.getArticlesByname("%"+title+"%",pageNum,pageSize));
        return "search";
    }

    @GetMapping("/article/{id}")
    public String article(@PathVariable(value = "id") Integer id, Model model){
        model.addAttribute("friend",friendDao.query());
        model.addAttribute("nav",navService.getAll());
        model.addAttribute("other1",articleMapper.getOther(2));
        ArticleEntity articleEntity = articleService.getArticleById(id);
        if (null == articleEntity){
            return "error/404";
        }
        else {
            model.addAttribute("nav_now",navService.getByid(articleEntity.getNavId()));
            model.addAttribute("nav_tree",navService.getTreeByid(articleEntity.getNavId()));
            model.addAttribute("article", articleEntity);
            return "detail";
        }
    }


    @ResponseBody
    @GetMapping("/test")
    public PageInfo nav1(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        return articleService.getArticlesByNavId(6,pageNum,pageSize);
    }
}
