package com.itperson.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.itperson.spzx.model.dto.system.AssginRoleDto;
import com.itperson.spzx.model.dto.system.LoginDto;
import com.itperson.spzx.model.dto.system.SysUserDto;
import com.itperson.spzx.model.entity.system.SysUser;
import com.itperson.spzx.model.vo.system.LoginVo;

public interface SysUserService {
    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteSysUser(Integer id);

    void doAssign(AssginRoleDto assginRoleDto);
}
