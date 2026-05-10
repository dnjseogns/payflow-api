package com.dhwon.payflow_api.api.role.controller;

import com.dhwon.payflow_api.api.role.dto.RoleRequestDto;
import com.dhwon.payflow_api.api.role.dto.RoleResponseDto;
import com.dhwon.payflow_api.api.role.service.RoleService;
import com.dhwon.payflow_api.cmm.auth.context.UserContext;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @UseCommonResponse
    public List<RoleResponseDto> selectRoleList() {
        return roleService.selectRoleList();
    }

    @GetMapping("/{roleCode}")
    @UseCommonResponse
    public RoleResponseDto selectRoleDetail(@PathVariable("roleCode") String roleCode) {
        return roleService.selectRoleDetail(roleCode);
    }

    @PostMapping
    @UseCommonResponse
    public int insertRole(@RequestBody RoleRequestDto roleRequestDto) {
        return roleService.insertRole(roleRequestDto, UserContext.get().getUserId());
    }

    @PutMapping("/{roleCode}")
    @UseCommonResponse
    public int updateRole(@PathVariable("roleCode") String roleCode,
                          @RequestBody RoleRequestDto roleRequestDto) {
        return roleService.updateRole(roleRequestDto,roleCode, UserContext.get().getUserId());
    }

    @DeleteMapping("/{roleCode}")
    @UseCommonResponse
    public int deleteRole(@PathVariable("roleCode") String roleCode) {
        return roleService.deleteRole(roleCode);
    }
}