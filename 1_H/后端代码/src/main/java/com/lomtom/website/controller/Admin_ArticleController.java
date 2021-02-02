package com.lomtom.website.controller;

import com.alibaba.fastjson.JSONObject;
import com.lomtom.website.dao.ArticleMapper;
import com.lomtom.website.dao.ProfileMapper;
import com.lomtom.website.entity.ArticleEntity;
import com.lomtom.website.entity.Other;
import com.lomtom.website.entity.ProfileEntity;
import org.apache.ibatis.annotations.Delete;
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
 * @Date: 2020/6/22
 * @Time: 20:39
 * @Email: lomtom@qq.com
 */

@RestController
@RequestMapping({"/admin/article","article"})
public class Admin_ArticleController {

    @Autowired
    ArticleMapper articleMapper;


    @RequestMapping("/list")
    public List<ArticleEntity> All(){
        return articleMapper.getAll();
    }
    
    @RequestMapping("/one/{id}")
    public ArticleEntity One(@PathVariable("id") Integer id){
        return articleMapper.getArticleById(id);
    }

    @RequestMapping(value = "/add",produces = "application/json;charset=utf-8")
    public JSONObject Add(@RequestBody String s){
        ArticleEntity articleEntity = new ArticleEntity();
        JSONObject jsonTest = JSONObject.parseObject(s);
        articleEntity.setId(jsonTest.getInteger("id"));
        articleEntity.setContent(jsonTest.getString("content"));
        articleEntity.setData(new Date());
        articleEntity.setImg(jsonTest.getString("img"));
        articleEntity.setIschart(jsonTest.getInteger("ischart"));
        articleEntity.setNavId(jsonTest.getInteger("navId"));
        articleEntity.setTitle(jsonTest.getString("title"));
        String s1 = jsonTest.getString("url");
        articleEntity.setUrl("".equals(s1)?null:s1);
        articleEntity.setView(jsonTest.getInteger("view"));
        if (articleEntity.getId()==null){
            articleEntity.setView(0);
            articleMapper.add(articleEntity);
        }
        else {
            articleMapper.update(articleEntity);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",articleEntity);
        return jsonObject;
    }

    @RequestMapping(value = "/delete/{id}")
    public JSONObject Delete(@PathVariable("id") Integer id){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",articleMapper.delete(id));
        return jsonObject;
    }

//    @RequestMapping(value = "/change/{id}")
//    public JSONObject Change(@PathVariable("id") Integer id){
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("code",articleMapper.update(id));
//        return jsonObject;
//    }

    @RequestMapping(value = "/img")
    public JSONObject Img(@RequestParam("image") MultipartFile file){
        JSONObject jsonObject = new JSONObject();
        try {
            // 保存图片
            byte[] bytes = file.getBytes();
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File upload = new File(path.getAbsolutePath(), "static/article_img/");
            String format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_").format(new Date());
            Path filePath = Paths.get(String.valueOf(upload), format+file.getOriginalFilename());
            System.out.println(filePath);
            jsonObject.put("url","/article_img/" + format+file.getOriginalFilename());
            jsonObject.put("url1",filePath);
            Files.write(filePath, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }






















    @RequestMapping("/other/get/{id}")
    public Other One1(@PathVariable("id") Integer id){
        return articleMapper.getOther(id);
    }


    @RequestMapping("/other/add")
    public JSONObject One2(@RequestBody Other other){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        articleMapper.upateOther(other);
        return jsonObject;
    }

}
