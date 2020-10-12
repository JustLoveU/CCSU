package com.lomtom.website_university.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: LOMTOM
 * @Date: 2020/7/20
 * @Time: 9:36
 * @Email: lomtom@qq.com
 */

@RestController
@RequestMapping({"website_university/file","file"})
public class FileUploadControlller {
    @RequestMapping(value = "/img")
    public JSONObject Img(@RequestParam("image") MultipartFile file, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try {
            // 保存图片
            byte[] bytes = file.getBytes();
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File upload = new File(path.getAbsolutePath(), "static/user_img/");
            String format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_").format(new Date());
            Path filePath = Paths.get(String.valueOf(upload), format + file.getOriginalFilename());
            String url = "";
            url = request.getScheme() +"://" + request.getServerName()
//                    + ":"
//                    + request.getServerPort()
                    +"/";
            jsonObject.put("url",url + "user_img/" + format + file.getOriginalFilename());
            jsonObject.put("url1",filePath);
            Files.write(filePath, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
