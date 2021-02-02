package io.renren.main.service.impl;

import io.renren.main.common.utils.PageUtils;
import io.renren.main.common.utils.Query;
import io.renren.main.dao.InfoDao;
import io.renren.main.entity.InfoEntity;
import io.renren.main.service.InfoService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("infoService")
public class InfoServiceImpl extends ServiceImpl<InfoDao, InfoEntity> implements InfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InfoEntity> page = this.page(
                new Query<InfoEntity>().getPage(params),
                new QueryWrapper<InfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Integer id) {

        IPage<InfoEntity> page = this.page(
                new Query<InfoEntity>().getPage(params),
                new QueryWrapper<InfoEntity>().eq("type_id",id).orderByDesc("time")
        );

        return new PageUtils(page);
    }


    @Override
    public PageUtils queryPage(Map<String, Object> params,String search) {
        IPage<InfoEntity> page = this.page(
                new Query<InfoEntity>().getPage(params),
                new QueryWrapper<InfoEntity>().like("title",search)
        );
        return new PageUtils(page);
    }

}