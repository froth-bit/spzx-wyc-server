package com.itperson.spzx.manager.service;

import com.itperson.spzx.model.dto.system.AssginMenuDto;

import java.util.Map;

public interface SysRoleMenuService {
    Map<String, Object> findSysRoleMenuByRoleId(Integer roleId);

    void doAssign(AssginMenuDto assginMenuDto);
}
