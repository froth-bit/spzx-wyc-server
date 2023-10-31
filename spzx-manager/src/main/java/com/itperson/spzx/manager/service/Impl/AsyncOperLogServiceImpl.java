package com.itperson.spzx.manager.service.Impl;

import com.itperson.spzx.common.log.service.AsyncOperLogService;
import com.itperson.spzx.manager.mapper.SysOperLogMapper;
import com.itperson.spzx.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    //保存日志数据
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
