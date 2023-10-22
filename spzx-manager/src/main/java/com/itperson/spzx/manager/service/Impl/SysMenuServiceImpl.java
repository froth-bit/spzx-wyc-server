package com.itperson.spzx.manager.service.Impl;

import com.itperson.spzx.common.exception.PersonException;
import com.itperson.spzx.manager.mapper.SysMenuMapper;
import com.itperson.spzx.manager.service.SysMenuService;
import com.itperson.spzx.manager.utils.MenuHelper;
import com.itperson.spzx.model.entity.system.SysMenu;
import com.itperson.spzx.model.vo.common.ResultCodeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findNodes() {

        List<SysMenu> sysMenus = sysMenuMapper.findAll();
        if (CollectionUtils.isEmpty(sysMenus)){
            return null;
        }

        List<SysMenu> sysMenus1 = MenuHelper.buildTree(sysMenus);

        return sysMenus1;
    }

    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu);
    }

    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    @Override
    public void deleteById(Long id) {
        Integer integer = sysMenuMapper.selectCountById(id);
        if (integer>0){
            throw new PersonException(ResultCodeEnum.NODE_ERROR);
        }
        sysMenuMapper.deleteById(id);
    }
}
