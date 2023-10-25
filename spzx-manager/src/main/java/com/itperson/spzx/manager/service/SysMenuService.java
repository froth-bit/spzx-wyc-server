package com.itperson.spzx.manager.service;

import com.itperson.spzx.model.entity.system.SysMenu;
import com.itperson.spzx.model.vo.system.SysMenuVo;

import java.util.List;

public interface SysMenuService {
    List<SysMenu> findNodes();

    void save(SysMenu sysMenu);

    void update(SysMenu sysMenu);

    void deleteById(Long id);

    List<SysMenuVo> findMenusByUserId();
}
