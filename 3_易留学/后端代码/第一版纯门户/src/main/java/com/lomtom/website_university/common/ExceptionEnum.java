package com.lomtom.website_university.common;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/28
 * @Time: 20:21
 * @Email: lomtom@qq.com
 */

public enum ExceptionEnum {
    DATA_EXCEPTION(400,"数据不合法");

    private Integer code;
    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
