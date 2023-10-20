package com.itperson.spzx.manager.service.Impl;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itperson.spzx.common.exception.PersonException;
import com.itperson.spzx.manager.mapper.SysUserMapper;
import com.itperson.spzx.manager.service.SysUserService;
import com.itperson.spzx.model.dto.system.LoginDto;
import com.itperson.spzx.model.dto.system.SysUserDto;
import com.itperson.spzx.model.entity.system.SysUser;
import com.itperson.spzx.model.vo.common.ResultCodeEnum;
import com.itperson.spzx.model.vo.system.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {

        String captcha = loginDto.getCaptcha();
        String key = loginDto.getCodeKey();

        String redisCode = redisTemplate.opsForValue().get("user:validate" + key);
        if (StringUtils.isEmpty(redisCode) || !StringUtils.equalsAnyIgnoreCase(redisCode, captcha)) {
            throw new PersonException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        redisTemplate.delete("user:validate" + key);

        String userName = loginDto.getUserName();
        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(userName);
        if (sysUser == null) {
            throw new PersonException(ResultCodeEnum.LOGIN_ERROR);
        }
        String database_password = sysUser.getPassword();
        //把输入的密码进行加密，再比较数据库密码，MD5
        String input_password = SecureUtil.md5(loginDto.getPassword());
        if (!database_password.equals(input_password)) {
            throw new PersonException(ResultCodeEnum.LOGIN_ERROR);
        }
        String token = UUID.randomUUID().toString().replace("-", "");

        redisTemplate.opsForValue().set("user:login:"+token,
                JSON.toJSONString(sysUser),
                7, TimeUnit.DAYS);

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String userJson = redisTemplate.opsForValue().get("user:login:" + token);
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login:" + token);
    }

    @Override
    public PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(sysUserMapper.findByPage(sysUserDto));
    }

    @Override
    public void saveSysUser(SysUser sysUser) {
        String userName = sysUser.getUserName();
        SysUser dbSysUser = sysUserMapper.selectUserInfoByUserName(userName);
        if (dbSysUser != null) {
            throw new PersonException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        //对输入密码加密
        String md5_password = SecureUtil.md5(sysUser.getPassword());
        sysUser.setPassword(md5_password);

        sysUserMapper.saveSysUser(sysUser);
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteSysUser(Integer id) {
        sysUserMapper.deleteSysUser(id);
    }

}
