package com.itperson.spzx.utils;

import com.itperson.spzx.model.entity.system.SysUser;

public class AuthContextUtil {

    //创建一个ThreadLocal对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    public static void setAuthContext(SysUser user) {
        threadLocal.set(user);
    }

    public static SysUser getAuthContext() {
        return threadLocal.get();
    }

    public static void removeAuthContext() {
        threadLocal.remove();
    }



}
