package io.renren.main.service.impl;

import io.renren.main.common.utils.PageUtils;
import io.renren.main.common.utils.Query;
import io.renren.main.dao.ProgressDao;
import io.renren.main.entity.ProgressEntity;
import io.renren.main.service.ProgressService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("progressService")
public class ProgressServiceImpl extends ServiceImpl<ProgressDao, ProgressEntity> implements ProgressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProgressEntity> page = this.page(
                new Query<ProgressEntity>().getPage(params),
                new QueryWrapper<ProgressEntity>()
        );

        return new PageUtils(page);
    }

}