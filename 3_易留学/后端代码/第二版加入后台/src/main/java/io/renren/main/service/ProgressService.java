package io.renren.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.main.common.utils.PageUtils;
import io.renren.main.entity.ProgressEntity;

import java.util.Map;

/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:31
 */
public interface ProgressService extends IService<ProgressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

