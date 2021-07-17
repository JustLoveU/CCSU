package com.lomtom.search.common.util;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/12
 * @Time: 20:27
 */

public enum HttpStatus {



    /**
     * 验证码
     */
    VERIFICATION_EXCEPTION(1001,"请检查验证码或邮箱"),

    /**
     * 用户
     */
    USER_USERNAME_EXIST(1101,"用户已存在"),
    USER_LOGIN_FAIL(1102,"登陆失败"),
    USER_LOGIN_SUCCESS(1103,"登陆成功"),
    USER_REGISTER_SUCCESS(1104,"注册成功"),
    USER_EMAIL_EMPTY(1105,"邮箱不能为空"),
    USER_EMAIL_EXIST(1106,"邮箱已被注册"),
    USER_UN_ACTIVE(1107,"用户未激活"),
    USER_UNAUTHORIZED(1108,"token失效，请重新登录"),



    /**
     *  参数
     */
    ARGS_NULL(2001,"输入不能为空"),


    /**
     * 同一请求
     */
    NOT_FOUND(4000,"输入不能为空"),
    ;


    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;


    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
