package io.renren.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.main.common.utils.PageUtils;
import io.renren.main.entity.InfoTypeEntity;

import java.util.Map;

/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
public interface InfoTypeService extends IService<InfoTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

