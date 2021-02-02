package io.renren.main.service.impl;

import io.renren.main.common.utils.PageUtils;
import io.renren.main.common.utils.Query;
import io.renren.main.dao.UserDao1;
import io.renren.main.entity.UserEntity;
import io.renren.main.service.UserService1;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("userService1")
public class UserServiceImpl extends ServiceImpl<UserDao1, UserEntity> implements UserService1 {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

}