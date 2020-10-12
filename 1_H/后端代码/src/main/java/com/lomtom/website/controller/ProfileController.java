package com.lomtom.website.controller;

import com.lomtom.website.dao.ArticleMapper;
import com.lomtom.website.service.NavService;
import com.lomtom.website.service.ProfileService;
import com.lomtom.website.dao.FriendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/9
 * @Time: 20:43
 * @Email: lomtom@qq.com
 */

@Controller
public class ProfileController {

    @Autowired
    NavService navService;

    @Autowired
    ProfileService profileService;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    FriendDao friendDao;
    @GetMapping("/profile/{id}")
    public String profileById(@PathVariable(value = "id") Integer id, Model model){
        model.addAttribute("other1",articleMapper.getOther(2));
        model.addAttribute("nav",navService.getAll());
        model.addAttribute("friend",friendDao.query());
        model.addAttribute("profile",profileService.getOneById(id));
        return "detail-member";
    }
}
