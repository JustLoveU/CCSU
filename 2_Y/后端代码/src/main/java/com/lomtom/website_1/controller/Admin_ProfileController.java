package com.lomtom.website_1.controller;

import com.alibaba.fastjson.JSONObject;
import com.lomtom.website_1.entity.ProfileEntity;
import com.lomtom.website_1.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/25
 * @Time: 12:39
 * @Email: lomtom@qq.com
 */

@RestController
@RequestMapping({"/admin/profile","profile"})
public class Admin_ProfileController {
    @Autowired
    ProfileService profileService;


    @GetMapping("/list")
    public List<ProfileEntity> list_Profile(){
        return profileService.getAll_admin();
    }
    @GetMapping("/one/{id}")
    public ProfileEntity One_Profile(@PathVariable("id") Integer id){
        return profileService.getArticleById(id);
    }

    @ResponseBody
    @PostMapping("/update")
    public JSONObject One_Profile_Update_Status(@RequestBody ProfileEntity profileEntity){
        JSONObject jsonObject = new JSONObject();
        profileService.updateStatus(profileEntity);
        jsonObject.put("code",0);
        return jsonObject;
    }
    @PostMapping("/add")
    public JSONObject Save_Profile(ProfileEntity profileEntity){
        if (profileEntity.getProfileId()==null){
            profileService.add(profileEntity);
        }
        else {
            profileService.update(profileEntity);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",profileEntity);
        return jsonObject;
    }
    @DeleteMapping("/one/{id}")
    public JSONObject delete_Profile(@PathVariable("id") Integer id){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", profileService.delete(id));
        return jsonObject;
    }

    @RequestMapping(value = "/img")
    public JSONObject Img(@RequestParam("image") MultipartFile file){
        JSONObject jsonObject = new JSONObject();
        try {
            // 保存图片
            byte[] bytes = file.getBytes();
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File upload = new File(path.getAbsolutePath(), "static/profile_img/");
            String format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_").format(new Date());
            Path filePath = Paths.get(String.valueOf(upload), format + file.getOriginalFilename());
            System.out.println(filePath);
            jsonObject.put("url","profile_img/" + format + file.getOriginalFilename());
            jsonObject.put("url1",filePath);
            Files.write(filePath, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
