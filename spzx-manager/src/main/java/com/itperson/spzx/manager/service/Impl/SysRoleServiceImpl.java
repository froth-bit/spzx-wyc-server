package com.itperson.spzx.manager.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itperson.spzx.manager.mapper.SysRoleMapper;
import com.itperson.spzx.manager.mapper.SysRoleUserMapper;
import com.itperson.spzx.manager.service.SysRoleService;
import com.itperson.spzx.model.dto.system.SysRoleDto;
import com.itperson.spzx.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysRoleUserMapper sysRoleUserMapper;

    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit) {
        PageHelper.startPage(current,limit);
        return new PageInfo<>(sysRoleMapper.findByPage(sysRoleDto));
    }

    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.saveSysRole(sysRole);
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    @Override
    public void deleteSysRole(Integer id) {
        sysRoleMapper.deleteSysRole(id);
    }

    @Override
    public Map<String, Object> findAll(Long userId) {
        List<SysRole> sysRoles = sysRoleMapper.findAll();

        //分配过的角色
        List<Long> roleIds = sysRoleUserMapper.selectRoleIdsByUserId(userId);

        Map<String,Object> map = new HashMap<>();
        map.put("allRolesList",sysRoles);
        map.put("sysUserRoles",roleIds);
        return map;
    }

}
