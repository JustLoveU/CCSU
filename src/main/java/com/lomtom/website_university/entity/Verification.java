package com.lomtom.website_university.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author: LOMTOM
 * @Date: 2021/2/2
 * @Time: 14:49
 * @Email: lomtom@qq.com
 */

@Data
@TableName("verification")
public class Verification {

    @TableId(type= IdType.AUTO)
    private Integer id;

    private String code;

    private Date date;

    private String email;

    public Verification(String code,String email) {
        this.code = code;
        this.date = new Date();
        this.email = email;
    }
}
