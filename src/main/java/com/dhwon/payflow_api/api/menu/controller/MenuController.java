package com.dhwon.payflow_api.api.menu.controller;

import com.dhwon.payflow_api.api.menu.dto.MenuRequestDto;
import com.dhwon.payflow_api.api.menu.dto.MenuResponseDto;
import com.dhwon.payflow_api.api.menu.service.MenuService;
import com.dhwon.payflow_api.cmm.auth.context.UserContext;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    @UseCommonResponse
    public List<MenuResponseDto> selectMenuList() {
        return menuService.selectMenuList();
    }

    @GetMapping("/{menuId}")
    @UseCommonResponse
    public MenuResponseDto selectMenuDetail(@PathVariable("menuId") Long menuId) {
        return menuService.selectMenuDetail(menuId);
    }

    @PostMapping
    @UseCommonResponse
    public int insertMenu(@RequestBody MenuRequestDto menuRequestDto) {
        return menuService.insertMenu(menuRequestDto, UserContext.get().getUserId());
    }

    @PutMapping("/{menuId}")
    @UseCommonResponse
    public int updateMenu(@PathVariable("menuId") Long menuId,
                          @RequestBody MenuRequestDto menuRequestDto) {
        return menuService.updateMenu(menuRequestDto, menuId, UserContext.get().getUserId());
    }

    @DeleteMapping("/{menuId}")
    @UseCommonResponse
    public int deleteMenu(@PathVariable("menuId") Long menuId) {
        return menuService.deleteMenu(menuId);
    }
}