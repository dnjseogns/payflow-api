package com.dhwon.payflow_api.api.menu.service;

import com.dhwon.payflow_api.api.menu.dto.MenuRequestDto;
import com.dhwon.payflow_api.api.menu.dto.MenuResponseDto;
import com.dhwon.payflow_api.api.menu.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}