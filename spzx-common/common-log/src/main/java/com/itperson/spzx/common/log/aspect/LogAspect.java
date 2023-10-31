package com.itperson.spzx.common.log.aspect;

import com.itperson.spzx.common.log.annotation.Log;
import com.itperson.spzx.common.log.service.AsyncOperLogService;
import com.itperson.spzx.common.log.utils.LogUtil;
import com.itperson.spzx.model.entity.system.SysOperLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private AsyncOperLogService operLogService;

    @Around(value = "@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Log sysLog){

        //业务方法调用之前，封装数据
        SysOperLog sysOperLog = new SysOperLog();
        LogUtil.beforeHandleLog(sysLog,joinPoint,sysOperLog);

        //业务方法
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();

//            System.out.println("在业务方法之后执行....");
            //调用业务方法之后，封装数据
            LogUtil.afterHandlLog(sysLog,proceed,sysOperLog,0,null);
        } catch (Throwable e) {
            e.printStackTrace();
            LogUtil.afterHandlLog(sysLog,proceed,sysOperLog,1,e.getMessage());
            //抛出运行时异常
            throw new RuntimeException();
        }

        //调用service方法把日志信息添加数据库里面
        operLogService.saveSysOperLog(sysOperLog);
        return proceed;
    }

}
