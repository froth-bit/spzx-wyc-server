package com.itperson.spzx.manager.utils;

import com.itperson.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {

    public static List<SysMenu> buildTree(List<SysMenu> menus) {
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu menu : menus) {
            if (menu.getParentId().longValue() == 0) {
                trees.add(findChildren(menu, menus));
            }
        }
        return trees;
    }

    private static SysMenu findChildren(SysMenu menu, List<SysMenu> menus) {
        menu.setChildren(new ArrayList<>());
        for (SysMenu menu2 : menus) {
            if (menu2.getParentId().longValue() == menu.getId().longValue()) {
                menu.getChildren().add(findChildren(menu2, menus));
            }
        }
        return menu;
    }

}
