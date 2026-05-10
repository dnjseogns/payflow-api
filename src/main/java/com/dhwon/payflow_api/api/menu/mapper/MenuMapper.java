package com.dhwon.payflow_api.api.menu.mapper;

import com.dhwon.payflow_api.api.menu.dto.MenuRequestDto;
import com.dhwon.payflow_api.api.menu.dto.MenuResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<MenuResponseDto> selectMenuList();

    MenuResponseDto selectMenuDetail(@Param("menuId") Long menuId);

    int insertMenu(@Param("dto") MenuRequestDto menuRequestDto,
                   @Param("loginUserId") String loginUserId);

    int updateMenu(@Param("dto") MenuRequestDto menuRequestDto,
                   @Param("menuId") Long menuId,
                   @Param("loginUserId") String loginUserId);

    int deleteMenu(@Param("menuId") Long menuId);
}