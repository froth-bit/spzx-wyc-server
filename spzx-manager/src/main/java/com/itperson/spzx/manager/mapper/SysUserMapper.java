package com.itperson.spzx.manager.mapper;

import com.itperson.spzx.model.dto.system.SysUserDto;
import com.itperson.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {

    SysUser selectUserInfoByUserName(String userName);

    List<SysUser> findByPage(SysUserDto sysUserDto);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteSysUser(Integer id);
}
