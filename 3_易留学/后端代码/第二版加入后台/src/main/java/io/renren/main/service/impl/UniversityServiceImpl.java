package io.renren.main.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.main.common.utils.PageUtils;
import io.renren.main.common.utils.Query;
import io.renren.main.dao.UniversityDao;
import io.renren.main.entity.UniversityEntity;
import io.renren.main.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("universityService")
public class UniversityServiceImpl extends ServiceImpl<UniversityDao, UniversityEntity> implements UniversityService {

    @Autowired
    UniversityDao universityDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UniversityEntity> page = this.page(
                new Query<UniversityEntity>().getPage(params),
                new QueryWrapper<UniversityEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public IPage<UniversityEntity> queryPage(Map<String, Object> params, Integer id) {
        long current =  Long.parseLong((String)params.get("page"));
        long size =  Long.parseLong((String)params.get("limit"));
        Page<UniversityEntity> page = new Page<>(current,size);
        return universityDao.getUniversityBYUser(page,id);
    }


}