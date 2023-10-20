package com.itperson.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.itperson.spzx.manager.service.SysUserService;
import com.itperson.spzx.model.dto.system.SysUserDto;
import com.itperson.spzx.model.entity.system.SysUser;
import com.itperson.spzx.model.vo.common.Result;
import com.itperson.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    //用户查询
    @GetMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize,
                             SysUserDto sysUserDto) {
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(pageNum, pageSize,sysUserDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    //用户添加
    @PostMapping(value = "/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser) {
        sysUserService.saveSysUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //用户修改
    @PutMapping(value = "/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //用户删除
    @DeleteMapping(value = "/deleteSysUser/{userId}")
    public Result deleteSysUser(@PathVariable("userId") Integer id) {
        sysUserService.deleteSysUser(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}
