package com.dhwon.payflow_api.api.menu.service;

import com.dhwon.payflow_api.api.menu.dto.MenuRequestDto;
import com.dhwon.payflow_api.api.menu.dto.MenuResponseDto;
import com.dhwon.payflow_api.api.menu.dto.MenuTreeResponseDto;
import com.dhwon.payflow_api.api.menu.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuMapper menuMapper;

    public List<MenuResponseDto> selectMenuList() {
        return menuMapper.selectMenuList();
    }

    public MenuResponseDto selectMenuDetail(Long menuId) {
        return menuMapper.selectMenuDetail(menuId);
    }

    public int insertMenu(MenuRequestDto menuRequestDto, String loginUserId) {
        return menuMapper.insertMenu(menuRequestDto, loginUserId);
    }

    public int updateMenu(MenuRequestDto menuRequestDto, Long menuId, String loginUserId) {
        return menuMapper.updateMenu(menuRequestDto, menuId, loginUserId);
    }

    public int deleteMenu(Long menuId) {
        return menuMapper.deleteMenu(menuId);
    }


    public List<MenuTreeResponseDto> selectMyMenuTree(String userId) {

        List<MenuTreeResponseDto> menuList =
                menuMapper.selectMyMenuList(userId);

        Map<Long, MenuTreeResponseDto> menuMap = new HashMap<>();

        for (MenuTreeResponseDto menu : menuList) {
            menuMap.put(menu.getMenuId(), menu);
        }

        List<MenuTreeResponseDto> rootMenus = new ArrayList<>();

        for (MenuTreeResponseDto menu : menuList) {

            if (menu.getParentMenuId() == null) {
                rootMenus.add(menu);
                continue;
            }

            MenuTreeResponseDto parent =
                    menuMap.get(menu.getParentMenuId());

            if (parent != null) {
                parent.getChildren().add(menu);
            }
        }

        rootMenus.removeIf(menu -> menu.getChildren().isEmpty()
                && menu.getMenuUrl() == null);

        return rootMenus;
    }
}