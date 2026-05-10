package com.dhwon.payflow_api.api.role.mapper;

import com.dhwon.payflow_api.api.role.dto.RoleRequestDto;
import com.dhwon.payflow_api.api.role.dto.RoleResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<RoleResponseDto> selectRoleList();

    RoleResponseDto selectRoleDetail(@Param("roleCode") String roleCode);

    int insertRole(@Param("dto") RoleRequestDto roleRequestDto, @Param("loginUserId") String loginUserId);

    int updateRole(@Param("dto") RoleRequestDto roleRequestDto, @Param("roleCode") String roleCode, @Param("loginUserId") String loginUserId);

    int deleteRole(@Param("roleCode") String roleCode);
}