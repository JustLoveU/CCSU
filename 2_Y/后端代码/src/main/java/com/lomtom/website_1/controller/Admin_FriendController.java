package com.lomtom.website_1.controller;

import com.alibaba.fastjson.JSONObject;
import com.lomtom.website_1.entity.FriendEntity;
import com.lomtom.website_1.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-06-29 09:16:27
 */
@RestController
@RequestMapping({"admin/friend","friend"})
public class Admin_FriendController {
    @Autowired
    private FriendService friendService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public JSONObject list(@RequestParam Map<String, Object> params){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("page", friendService.queryPage(params));
        return jsonObject;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public JSONObject info(@PathVariable("id") Integer id){
        JSONObject jsonObject = new JSONObject();
		FriendEntity friend = friendService.getById(id);
        jsonObject.put("code",0);
		jsonObject.put("friend",friend);
        return jsonObject;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public JSONObject save(@RequestBody  FriendEntity friend){
        JSONObject jsonObject = new JSONObject();
		friendService.save(friend);
        jsonObject.put("code",0);
        return jsonObject;
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public JSONObject update(@RequestBody FriendEntity friend){
        JSONObject jsonObject = new JSONObject();
		friendService.updateById(friend);
        jsonObject.put("code",0);
        return jsonObject;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public JSONObject delete(@RequestBody String s){
        JSONObject jsonTest = JSONObject.parseObject(s);
        List<Integer> ids = jsonTest.getObject("ids",List.class);
        JSONObject jsonObject = new JSONObject();
		friendService.removeByIds(ids);
        jsonObject.put("code",0);
        return jsonObject;
    }

}
