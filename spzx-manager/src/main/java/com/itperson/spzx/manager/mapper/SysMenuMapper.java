package com.itperson.spzx.manager.mapper;

import com.itperson.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {
    
    List<SysMenu> findAll();

    void save(SysMenu sysMenu);

    void update(SysMenu sysMenu);

    void deleteById(Long id);

    Integer selectCountById(Long id);

    List<SysMenu> findMenusByUserId(Long id);

    SysMenu selectParentMenu(Long parentId);
}
