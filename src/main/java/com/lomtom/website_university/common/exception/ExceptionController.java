package com.lomtom.website_university.common.exception;

import com.lomtom.website_university.common.utils.R;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: LOMTOM
 * @Date: 2020/7/17
 * @Time: 13:38
 * @Email: lomtom@qq.com
 */

@RestControllerAdvice("com.lomtom.website_university.controller")
public class ExceptionController {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        Map<String,String> map = new LinkedHashMap<>();
        result.getFieldErrors().forEach(item -> {
            String message = item.getDefaultMessage();
            String field = item.getField();
            map.put(field, message);
        });
        return R.error(400, "数据不合法").put("data", map);
    }
}
