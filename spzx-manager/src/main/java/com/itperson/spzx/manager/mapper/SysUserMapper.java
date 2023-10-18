package com.itperson.spzx.manager.mapper;

import com.itperson.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {

    SysUser selectUserInfoByUserName(String userName);

}
