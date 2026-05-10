package com.dhwon.payflow_api.api.role.service;

import com.dhwon.payflow_api.api.role.dto.RoleRequestDto;
import com.dhwon.payflow_api.api.role.dto.RoleResponseDto;
import com.dhwon.payflow_api.api.role.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleMapper roleMapper;

    public List<RoleResponseDto> selectRoleList() {
        return roleMapper.selectRoleList();
    }

    public RoleResponseDto selectRoleDetail(String roleCode) {
        return roleMapper.selectRoleDetail(roleCode);
    }

    public int insertRole(RoleRequestDto roleRequestDto, String loginUserId) {
        return roleMapper.insertRole(roleRequestDto, loginUserId);
    }

    public int updateRole(RoleRequestDto roleRequestDto, String roleCode, String loginUserId) {
        return roleMapper.updateRole(roleRequestDto, roleCode, loginUserId);
    }

    public int deleteRole(String roleCode) {
        return roleMapper.deleteRole(roleCode);
    }
}