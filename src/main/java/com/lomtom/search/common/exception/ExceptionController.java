package com.lomtom.search.common.exception;

import com.lomtom.search.common.pojo.Result;
import com.lomtom.search.common.util.HttpStatus;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/12
 * @Time: 21:28
 */

@RestControllerAdvice("com.lomtom.search")
public class ExceptionController {

    @ExceptionHandler(value = BindException.class)
    public Result validException(BindException e) {
        BindingResult result = e.getBindingResult();
        Map<String, String> map = new LinkedHashMap<>();
        result.getFieldErrors().forEach(item -> {
            String message = item.getDefaultMessage();
            String field = item.getField();
            map.put(field, message);
        });
        return Result.error(HttpStatus.ARGS_NULL).put("error", map);
    }


//    @ExceptionHandler(value = Exception.class)
//    public Result exception(Exception e) {
//        return Result.error(HttpStatus.NOT_FOUND);
//    }

}