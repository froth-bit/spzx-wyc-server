package com.itperson.spzx.manager.mapper;

import com.itperson.spzx.model.dto.system.SysRoleDto;
import com.itperson.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {

    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteSysRole(Integer id);

    List<SysRole> findAll();

}
