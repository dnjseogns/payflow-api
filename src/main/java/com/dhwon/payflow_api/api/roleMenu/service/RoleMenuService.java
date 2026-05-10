package com.dhwon.payflow_api.api.roleMenu.service;

import com.dhwon.payflow_api.api.roleMenu.dto.RoleMenuRequestDto;
import com.dhwon.payflow_api.api.roleMenu.dto.RoleMenuResponseDto;
import com.dhwon.payflow_api.api.roleMenu.mapper.RoleMenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleMenuService {

    private final RoleMenuMapper roleMenuMapper;

    public List<RoleMenuResponseDto> selectRoleMenuList() {
        return roleMenuMapper.selectRoleMenuList();
    }

    public RoleMenuResponseDto selectRoleMenuDetail(String roleCode, Long menuId) {
        return roleMenuMapper.selectRoleMenuDetail(roleCode, menuId);
    }

    public int insertRoleMenu(RoleMenuRequestDto dto, String loginUserId) {
        return roleMenuMapper.insertRoleMenu(dto, loginUserId);
    }

    public int updateRoleMenu(
            RoleMenuRequestDto dto,
            String roleCode,
            Long menuId,
            String loginUserId
    ) {
        return roleMenuMapper.updateRoleMenu(dto, roleCode, menuId, loginUserId);
    }

    public int deleteRoleMenu(String roleCode, Long menuId) {
        return roleMenuMapper.deleteRoleMenu(roleCode, menuId);
    }
}