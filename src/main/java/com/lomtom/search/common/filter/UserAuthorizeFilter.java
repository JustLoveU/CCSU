package com.lomtom.search.common.filter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lomtom.search.common.exception.RRException;
import com.lomtom.search.common.util.HttpStatus;
import com.lomtom.search.common.util.JwtUtil;
import com.lomtom.search.main.entity.UserTokenEntity;
import com.lomtom.search.main.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserAuthorizeFilter implements HandlerInterceptor {

    @Autowired
    private UserTokenService userTokenService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        //获取当前的所有请求头信息获取
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            //如果不存在,则向客户端返回错误提示信息
            response.sendError(HttpStatus.USER_UNAUTHORIZED.getCode(),"需要验证");
            return false;
        }
        //如果令牌存在,解析jwt令牌,判断该令牌是否合法,如果令牌不合法,则向客户端返回错误提示信息
        try {
            //解析令牌
            UserTokenEntity tokenEntity = userTokenService
                    .getOne(new QueryWrapper<UserTokenEntity>().lambda().eq(UserTokenEntity::getToken,token));
            //token失效
            if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
                throw new RRException(HttpStatus.USER_UNAUTHORIZED.getMessage(),HttpStatus.USER_UNAUTHORIZED.getCode());
            }

        }catch (Exception e){
            e.printStackTrace();
            //令牌解析失败
            //向客户端返回错误提示信息
            response.sendError(HttpStatus.USER_UNAUTHORIZED.getCode(),"验证失败");
            return false;
        }
        return true;
    }

}
