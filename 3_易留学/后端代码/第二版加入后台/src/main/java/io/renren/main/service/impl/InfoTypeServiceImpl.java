package io.renren.main.service.impl;

import io.renren.main.common.utils.PageUtils;
import io.renren.main.common.utils.Query;
import io.renren.main.dao.InfoTypeDao;
import io.renren.main.entity.InfoTypeEntity;
import io.renren.main.service.InfoTypeService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("infoTypeService")
public class InfoTypeServiceImpl extends ServiceImpl<InfoTypeDao, InfoTypeEntity> implements InfoTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InfoTypeEntity> page = this.page(
                new Query<InfoTypeEntity>().getPage(params),
                new QueryWrapper<InfoTypeEntity>()
        );

        return new PageUtils(page);
    }

}