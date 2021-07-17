package com.lomtom.search.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: LOMTOM
 * @date: 2021/7/11
 * @time: 21:24
 */

@Controller
public class BaseController {

    @GetMapping("/")
    public String index(){
        return "forward:index.html";
    }
}
