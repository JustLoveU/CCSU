package com.lomtom.search.common.pojo;



import com.lomtom.search.common.util.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果实体类
 * @Author: LOMTOM
 * @Date: 2021/4/11
 * @Time: 12:50
 */
public class Result  extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;


    public Result() {
        put("status", 200);
        put("message", "success");
    }

    public static Result error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static Result error(String message) {
        return error(500, message);
    }

    public static Result error(int status, String message) {
        Result r = new Result();
        r.put("status", status);
        r.put("message", message);
        return r;
    }

    public static Result error(HttpStatus httpStatus) {
        Result r = new Result();
        r.put("status", httpStatus.getCode());
        r.put("message", httpStatus.getMessage());
        return r;
    }

    public static Result ok(String message) {
        Result r = new Result();
        r.put("message", message);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }


    public static Result ok(HttpStatus httpStatus) {
        Result r = new Result();
        r.put("status", httpStatus.getCode());
        r.put("message", httpStatus.getMessage());
        return r;
    }

    public static Result ok() {
        return new Result();
    }



    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
