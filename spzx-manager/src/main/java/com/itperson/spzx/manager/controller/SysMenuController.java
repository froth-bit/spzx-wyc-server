package com.itperson.spzx.manager.controller;

import com.itperson.spzx.manager.service.SysMenuService;
import com.itperson.spzx.model.entity.system.SysMenu;
import com.itperson.spzx.model.vo.common.Result;
import com.itperson.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;

    //查询菜单的接口
    @GetMapping("/findNodes")
    public Result findNodes(){
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    //菜单的添加和修改
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu){
        sysMenuService.save(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //菜单的添加和修改
    @PutMapping("/update")
    public Result update(@RequestBody SysMenu sysMenu){
        sysMenuService.update(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //菜单的添加和修改
    @DeleteMapping("/removeById/{id}")
    public Result removeById(@PathVariable(value = "id") Long id){
        sysMenuService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}
