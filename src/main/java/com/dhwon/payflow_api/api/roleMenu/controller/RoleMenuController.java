package com.dhwon.payflow_api.api.roleMenu.controller;

import com.dhwon.payflow_api.api.roleMenu.dto.RoleMenuRequestDto;
import com.dhwon.payflow_api.api.roleMenu.dto.RoleMenuResponseDto;
import com.dhwon.payflow_api.api.roleMenu.service.RoleMenuService;
import com.dhwon.payflow_api.cmm.auth.context.UserContext;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role-menu")
public class RoleMenuController {

    private final RoleMenuService roleMenuService;

    @GetMapping
    @UseCommonResponse
    public List<RoleMenuResponseDto> selectRoleMenuList() {
        return roleMenuService.selectRoleMenuList();
    }

    @GetMapping("/{roleCode}/{menuId}")
    @UseCommonResponse
    public RoleMenuResponseDto selectRoleMenuDetail(
            @PathVariable("roleCode") String roleCode,
            @PathVariable("menuId") Long menuId
    ) {
        return roleMenuService.selectRoleMenuDetail(roleCode, menuId);
    }

    @PostMapping
    @UseCommonResponse
    public int insertRoleMenu(@RequestBody RoleMenuRequestDto roleMenuRequestDto) {
        return roleMenuService.insertRoleMenu(
                roleMenuRequestDto,
                UserContext.get().getUserId()
        );
    }

    @PutMapping("/{roleCode}/{menuId}")
    @UseCommonResponse
    public int updateRoleMenu(
            @PathVariable("roleCode") String roleCode,
            @PathVariable("menuId") Long menuId,
            @RequestBody RoleMenuRequestDto roleMenuRequestDto
    ) {
        return roleMenuService.updateRoleMenu(
                roleMenuRequestDto,
                roleCode,
                menuId,
                UserContext.get().getUserId()
        );
    }

    @DeleteMapping("/{roleCode}/{menuId}")
    @UseCommonResponse
    public int deleteRoleMenu(
            @PathVariable("roleCode") String roleCode,
            @PathVariable("menuId") Long menuId
    ) {
        return roleMenuService.deleteRoleMenu(roleCode, menuId);
    }
}