package com.lomtom.search.main.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: LOMTOM
 * @date: 2021/7/15
 * @time: 22:42
 */

@Data
public class Log {
    private String address;
    private String chrome;
    private String cid;
    private String ip;
    private Date date = new Date();
}
