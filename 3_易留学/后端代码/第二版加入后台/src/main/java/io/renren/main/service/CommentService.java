package io.renren.main.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.main.common.utils.PageUtils;
import io.renren.main.entity.CommentAndUserEntity;
import io.renren.main.entity.CommentEntity;

import java.util.Map;

/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
public interface CommentService extends IService<CommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
    IPage<CommentAndUserEntity> queryPage(Map<String, Object> params, Integer id);

    IPage<CommentEntity> queryPageByUser(Map<String, Object> params, Integer id);
}

