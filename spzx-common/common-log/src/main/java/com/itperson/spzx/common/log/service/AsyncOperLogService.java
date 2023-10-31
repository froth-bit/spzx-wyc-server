package com.itperson.spzx.common.log.service;

import com.itperson.spzx.model.entity.system.SysOperLog;

public interface AsyncOperLogService {

    public abstract void saveSysOperLog(SysOperLog sysOperLog) ;

}
