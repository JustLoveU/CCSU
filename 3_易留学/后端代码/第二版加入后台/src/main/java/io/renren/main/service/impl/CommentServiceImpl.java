package io.renren.main.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.main.entity.CommentAndUserEntity;
import io.renren.main.common.utils.PageUtils;
import io.renren.main.common.utils.Query;
import io.renren.main.dao.CommentDao;
import io.renren.main.entity.CommentEntity;
import io.renren.main.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, CommentEntity> implements CommentService {

    @Autowired
    CommentDao commentDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentEntity> page = this.page(
                new Query<CommentEntity>().getPage(params),
                new QueryWrapper<CommentEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public IPage<CommentAndUserEntity> queryPage(Map<String, Object> params, Integer id) {
        long current =  Long.parseLong((String)params.get("page"));
        long size =  Long.parseLong((String)params.get("limit"));
        Page<CommentAndUserEntity> page = new Page<>(current,size);
        return commentDao.getCommentAndUser(page,id);
    }


    @Override
    public IPage<CommentEntity> queryPageByUser(Map<String, Object> params, Integer id) {
        long current =  Long.parseLong((String)params.get("page"));
        long size =  Long.parseLong((String)params.get("limit"));
        Page<CommentEntity> page = new Page<>(current,size);
        return commentDao.getCommentAndUserByUser(page,id);
    }
}