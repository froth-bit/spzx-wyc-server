package com.itperson.spzx.manager.service;

import com.itperson.spzx.model.dto.system.LoginDto;
import com.itperson.spzx.model.vo.system.LoginVo;

public interface SysUserService {
    LoginVo login(LoginDto loginDto);

}
