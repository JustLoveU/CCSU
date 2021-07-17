package com.lomtom.search.common.filter;

import com.lomtom.search.common.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminAuthorizeFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        //获取当前的所有请求头信息获取jwt令牌信息
        String jwtToken = request.getHeader("token");
        //判断当前令牌是否存在,
        if (StringUtils.isEmpty(jwtToken)){
            //如果不存在,则向客户端返回错误提示信息
            response.sendError(HttpStatus.UNAUTHORIZED.value(),"需要验证");
            return false;
        }
        //如果令牌存在,解析jwt令牌,判断该令牌是否合法,如果令牌不合法,则向客户端返回错误提示信息
        try {
            //解析令牌
            JwtUtil.parseJWT(jwtToken);
        }catch (Exception e){
            e.printStackTrace();
            //令牌解析失败
            //向客户端返回错误提示信息
            response.sendError(HttpStatus.UNAUTHORIZED.value(),"验证失败");
            return false;
        }
        return true;
    }

}
