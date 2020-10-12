package io.renren.main.service.impl;

import io.renren.main.common.utils.PageUtils;
import io.renren.main.common.utils.Query;
import io.renren.main.dao.ApplyDao;
import io.renren.main.entity.ApplyEntity;
import io.renren.main.service.ApplyService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("applyService")
public class ApplyServiceImpl extends ServiceImpl<ApplyDao, ApplyEntity> implements ApplyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ApplyEntity> page = this.page(
                new Query<ApplyEntity>().getPage(params),
                new QueryWrapper<ApplyEntity>()
        );

        return new PageUtils(page);
    }

}