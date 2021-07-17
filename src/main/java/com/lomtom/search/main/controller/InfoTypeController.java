package com.lomtom.search.main.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.lomtom.search.common.pojo.Result;
import com.lomtom.search.main.entity.InfoTypeEntity;
import com.lomtom.search.main.service.InfoTypeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@RestController
@RequestMapping("/infoType")
@Api(tags = "资讯分类接口")
public class InfoTypeController {

    @Autowired
    private InfoTypeService infoTypeService;

    /**
     * 热门分类
     */
    @GetMapping("/hot")
    public Result hotInfoType(InfoTypeEntity data){
        List<InfoTypeEntity> list = infoTypeService.list(new QueryWrapper<InfoTypeEntity>().eq("status", data.getStatus())
                .eq("type", data.getType()));
        return Result.ok().put("list",list);
    }
}
