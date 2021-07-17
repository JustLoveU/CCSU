package com.lomtom.search.common.aspect;

import com.lomtom.search.admin.entity.InfoEntity;
import com.lomtom.search.admin.entity.UniversityEntity;
import com.lomtom.search.common.annotation.EsOperate;
import com.lomtom.search.admin.es.entity.EsOperateLog;
import com.lomtom.search.common.util.RequestUtils;
import com.lomtom.search.admin.es.service.EsOperateLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.erupt.upms.service.EruptUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/11
 * @Time: 21:02
 */

@Aspect
@Component
public class EsOperateAspect {

    private Logger logger = LoggerFactory.getLogger(EsOperateAspect.class);

    @Autowired
    private EruptUserService eruptUserService;


    @Autowired
    private EsOperateLogService esOperateLogService;

    @Pointcut("@annotation(com.lomtom.search.common.annotation.EsOperate)")
    public void point(){}

    @AfterReturning("point() && @annotation(arg)")
    public void  after(JoinPoint joinPoint, EsOperate arg) throws Throwable {
        HttpServletRequest request = RequestUtils.getHttpServletRequest();
        EsOperateLog log = new EsOperateLog();
        log.setApiName(arg.value());
        log.setCreateTime(new Date());
        Long userId = eruptUserService.getCurrentUid();
        log.setUserId(userId);
        log.setIp(RequestUtils.getIp(request));
        log.setReqAddr(request.getRequestURI());
        log.setType(arg.type());
        Object[] args = joinPoint.getArgs();
        if (args.length != 0 && args != null){
            Object arg1 = args[0];
            log.setInfoId((Long)arg1);
        }
        esOperateLogService.save(log);
        logger.info("方法： "+arg.value() +"用户id"+ (userId!=null?userId:0));
    }
}
