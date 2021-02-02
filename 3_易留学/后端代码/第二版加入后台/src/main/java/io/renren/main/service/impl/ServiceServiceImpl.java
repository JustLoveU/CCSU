package io.renren.main.service.impl;

import io.renren.main.common.utils.PageUtils;
import io.renren.main.common.utils.Query;
import io.renren.main.dao.ServiceDao;
import io.renren.main.entity.ServiceEntity;
import io.renren.main.service.ServiceService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("serviceService")
public class ServiceServiceImpl extends ServiceImpl<ServiceDao, ServiceEntity> implements ServiceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ServiceEntity> page = this.page(
                new Query<ServiceEntity>().getPage(params),
                new QueryWrapper<ServiceEntity>()
        );

        return new PageUtils(page);
    }

}