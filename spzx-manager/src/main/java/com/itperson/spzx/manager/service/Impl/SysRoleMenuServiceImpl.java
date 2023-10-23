package com.itperson.spzx.manager.service.Impl;

import com.itperson.spzx.manager.mapper.SysRoleMenuMapper;
import com.itperson.spzx.manager.service.SysMenuService;
import com.itperson.spzx.manager.service.SysRoleMenuService;
import com.itperson.spzx.model.dto.system.AssginMenuDto;
import com.itperson.spzx.model.entity.system.SysMenu;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    SysMenuService sysMenuService;

    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Integer roleId) {
        //查询所有的菜单
        List<SysMenu> sysMenus = sysMenuService.findNodes();
        //查询角色分配过的菜单id
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);

        Map<String,Object> map = new HashMap<>();
        map.put("sysMenuList",sysMenus);
        map.put("roleMenuIds",roleMenuIds);
        return map;
    }

    @Override
    public void doAssign(AssginMenuDto assginMenuDto) {
        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());

        List<Map<String, Number>> menuInfo = assginMenuDto.getMenuIdList();
        if (CollectionUtils.isNotEmpty(menuInfo)){
            sysRoleMenuMapper.doAssign(assginMenuDto);
        }
    }
}
