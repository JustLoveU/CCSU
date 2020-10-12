package com.lomtom.website_1.controller;

import com.lomtom.website_1.dao.FriendDao;
import com.lomtom.website_1.service.OtherService;
import com.lomtom.website_1.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: LOMTOM
 * @Date: 2020/7/4
 * @Time: 11:22
 * @Email: lomtom@qq.com
 */

@Controller
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @Autowired
    FriendDao friendDao;

    @Autowired
    OtherService otherService;

    @GetMapping("/profile/{id}")
    public String One_Profile(@PathVariable("id") Integer id, Model model){
        model.addAttribute("profile",profileService.getArticleById(id));
        model.addAttribute("other1",otherService.getOther(2));
        model.addAttribute("friend",friendDao.query());
        return "profile";
    }
}
