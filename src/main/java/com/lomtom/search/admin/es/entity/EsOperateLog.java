package com.lomtom.search.admin.es.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/11
 * @Time: 22:25
 */

@Data
@Table(name = "es_operate_log")
public class EsOperateLog implements Serializable {

    @TableId(type= IdType.AUTO)
    private Long id;

    private Long userId;

    private Long infoId;

    private String ip;

    private String apiName;

    private Date createTime;

    private String reqAddr;

    private Integer type;

}
