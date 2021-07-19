package com.lomtom.website_university.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: LOMTOM
 * @Date: 2020/9/21
 * @Time: 20:16
 * @Email: lomtom@qq.com
 */

@Data
@TableName("ip_log")
public class LogEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Date date;

    private String ip;

    private String cid;

    private String address;

    private String chrome;

}
