package com.lomtom.search.main.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author: LOMTOM
 * @Date: 2021/2/2
 * @Time: 14:49
 * @Email: lomtom@qq.com
 */

@TableName("verification")
public class Verification {

    @TableId(type= IdType.AUTO)
    private Integer id;

    private String code;

    private Date date;

    private String email;

    public Verification() {
    }

    public Verification(String code,String email) {
        this.code = code;
        this.date = new Date();
        this.email = email;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
