package com.itperson.spzx.manager.interceptor;

import com.alibaba.fastjson.JSON;
import com.itperson.spzx.model.entity.system.SysUser;
import com.itperson.spzx.model.vo.common.Result;
import com.itperson.spzx.model.vo.common.ResultCodeEnum;
import com.itperson.spzx.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class LoginInterceptor  implements HandlerInterceptor {


    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }

        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            responseNoLoginInfo(response);
            return false;
        }

        String userInfoString = redisTemplate.opsForValue().get("user:login:" + token);

        if (StringUtils.isEmpty(userInfoString)) {
            responseNoLoginInfo(response);
            return false;
        }

        AuthContextUtil.setAuthContext(JSON.parseObject(userInfoString, SysUser.class));

        //刷新过期时间
        redisTemplate.expire("user:login" + token, 30, TimeUnit.MINUTES);

        return true;
    }

    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContextUtil.removeAuthContext();
    }

}
