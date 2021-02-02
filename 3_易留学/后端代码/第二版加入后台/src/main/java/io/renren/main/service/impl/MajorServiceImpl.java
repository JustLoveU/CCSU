package io.renren.main.service.impl;

import io.renren.main.common.utils.PageUtils;
import io.renren.main.common.utils.Query;
import io.renren.main.dao.MajorDao;
import io.renren.main.entity.MajorEntity;
import io.renren.main.service.MajorService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("majorService")
public class MajorServiceImpl extends ServiceImpl<MajorDao, MajorEntity> implements MajorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MajorEntity> page = this.page(
                new Query<MajorEntity>().getPage(params),
                new QueryWrapper<MajorEntity>()
        );

        return new PageUtils(page);
    }

}