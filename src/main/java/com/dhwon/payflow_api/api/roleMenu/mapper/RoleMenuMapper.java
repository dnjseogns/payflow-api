package com.dhwon.payflow_api.api.roleMenu.mapper;

import com.dhwon.payflow_api.api.roleMenu.dto.RoleMenuRequestDto;
import com.dhwon.payflow_api.api.roleMenu.dto.RoleMenuResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMenuMapper {

    List<RoleMenuResponseDto> selectRoleMenuList();

    RoleMenuResponseDto selectRoleMenuDetail(
            @Param("roleCode") String roleCode,
            @Param("menuId") Long menuId
    );

    int insertRoleMenu(
            @Param("dto") RoleMenuRequestDto dto,
            @Param("loginUserId") String loginUserId
    );

    int updateRoleMenu(
            @Param("dto") RoleMenuRequestDto dto,
            @Param("roleCode") String roleCode,
            @Param("menuId") Long menuId,
            @Param("loginUserId") String loginUserId
    );

    int deleteRoleMenu(
            @Param("roleCode") String roleCode,
            @Param("menuId") Long menuId
    );
}