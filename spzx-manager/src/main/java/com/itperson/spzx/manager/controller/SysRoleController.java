package com.itperson.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.itperson.spzx.manager.service.SysRoleService;
import com.itperson.spzx.model.dto.system.SysRoleDto;
import com.itperson.spzx.model.entity.system.SysRole;
import com.itperson.spzx.model.vo.common.Result;
import com.itperson.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    //查询角色列表的方法
    //current:当前页 limit:每页显示记录数
    //SysRoleDto:条件角色名称对象
    @PostMapping("/findByPage/{current}/{limit}")
    public Result<SysRole> findByPage(@PathVariable("current") Integer current,
                                      @PathVariable("limit") Integer limit,
                                      @RequestBody SysRoleDto sysRoleDto) {
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto,current, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    //角色添加的方法
    @PostMapping("/saveSysRole")
    public Result<SysRole> saveSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.saveSysRole(sysRole);
        return Result.build(sysRole, ResultCodeEnum.SUCCESS);
    }

    //角色修改的接口
    @PutMapping("/updateSysRole")
    public Result<SysRole> updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.build(sysRole, ResultCodeEnum.SUCCESS);
    }

    //角色删除的接口
    @DeleteMapping("/deleteSysRole/{roleId}")
    public Result<SysRole> deleteSysRole(@PathVariable("roleId") Integer roleId) {
        sysRoleService.deleteSysRole(roleId);
        return Result.build(null, ResultCodeEnum.SUCCESS);  //表示删除成功
    }

    //查询所有角色的接口
    @GetMapping("/findAllRole/{userId}")
    public Result<SysRole> findAll(@PathVariable("userId") Long userId) {
        Map<String,Object> sysRoles = sysRoleService.findAll(userId);
        return Result.build(sysRoles, ResultCodeEnum.SUCCESS);
    }

}
