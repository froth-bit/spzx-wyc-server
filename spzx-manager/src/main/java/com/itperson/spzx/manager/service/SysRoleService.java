package com.itperson.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.itperson.spzx.model.dto.system.SysRoleDto;
import com.itperson.spzx.model.entity.system.SysRole;

import java.util.Map;

public interface SysRoleService {
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteSysRole(Integer id);

    Map<String, Object> findAll(Long userId);
}
