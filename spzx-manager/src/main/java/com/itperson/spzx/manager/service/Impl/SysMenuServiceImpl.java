package com.itperson.spzx.manager.service.Impl;

import com.itperson.spzx.common.exception.PersonException;
import com.itperson.spzx.manager.mapper.SysMenuMapper;
import com.itperson.spzx.manager.mapper.SysRoleMenuMapper;
import com.itperson.spzx.manager.service.SysMenuService;
import com.itperson.spzx.manager.utils.MenuHelper;
import com.itperson.spzx.model.entity.system.SysMenu;
import com.itperson.spzx.model.entity.system.SysUser;
import com.itperson.spzx.model.vo.common.ResultCodeEnum;
import com.itperson.spzx.model.vo.system.SysMenuVo;
import com.itperson.spzx.utils.AuthContextUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {

        List<SysMenu> sysMenus = sysMenuMapper.findAll();
        if (CollectionUtils.isEmpty(sysMenus)){
            return null;
        }

        return MenuHelper.buildTree(sysMenus);
    }

    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu);

        //新添加的菜单，把父菜单isHalf=1
        updateSysRoleMenu(sysMenu);
    }

    private void updateSysRoleMenu(SysMenu sysMenu) {
        SysMenu  parentMenu = sysMenuMapper.selectParentMenu(sysMenu.getParentId());
        if (parentMenu != null) {
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId());

            updateSysRoleMenu(parentMenu);
        }

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

    @Override
    public List<SysMenuVo> findMenusByUserId() {
        //获取当前用户id
        SysUser sysUser = AuthContextUtil.getAuthContext();
        Long id = sysUser.getId();

        //根据id查可以操作的菜单
        return buildMenus(MenuHelper.buildTree(sysMenuMapper.findMenusByUserId(id)));
    }


    private List<SysMenuVo> buildMenus(List<SysMenu> menus){
        List<SysMenuVo> sysMenuVoList = new LinkedList<>();
        for (SysMenu s : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(s.getTitle());
            sysMenuVo.setName(s.getComponent());
            List<SysMenu> children = s.getChildren();
            if (CollectionUtils.isNotEmpty(children)){
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }

}
