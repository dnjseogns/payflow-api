package com.dhwon.payflow_api.api.roleMenu.mapper;

import com.dhwon.payflow_api.api.menu.dto.MenuRequestDto;
import com.dhwon.payflow_api.api.menu.mapper.MenuMapper;
import com.dhwon.payflow_api.api.roleMenu.dto.RoleMenuRequestDto;
import com.dhwon.payflow_api.api.roleMenu.dto.RoleMenuResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RoleMenuMapperTest {

    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Test
    @DisplayName("권한 메뉴 목록 조회")
    void selectRoleMenuListTest() {

        List<RoleMenuResponseDto> result =
                roleMenuMapper.selectRoleMenuList();

        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("권한 메뉴 단건 조회")
    void selectRoleMenuDetailTest() {
        Long menuId = 10001L;
        MenuRequestDto menuDto =
                MenuRequestDto.builder()
                        .menuId(menuId)
                        .menuName("테스트메뉴")
                        .menuUrl("/test/menu")
                        .menuLevel(1)
                        .sortOrder(1)
                        .iconName("home")
                        .useYn("Y")
                        .build();
        menuMapper.insertMenu(menuDto,"SYSTEM");

        String roleCode = "ADMIN";
        RoleMenuRequestDto insertDto =
                RoleMenuRequestDto.builder()
                        .roleCode(roleCode)
                        .menuId(menuId)
                        .readYn("Y")
                        .createYn("Y")
                        .updateYn("Y")
                        .deleteYn("N")
                        .excelYn("Y")
                        .build();

        roleMenuMapper.insertRoleMenu(insertDto, "SYSTEM");

        RoleMenuResponseDto result =
                roleMenuMapper.selectRoleMenuDetail(roleCode, menuId);

        assertThat(result).isNotNull();

        assertThat(result.getRoleCode())
                .isEqualTo(roleCode);

        assertThat(result.getMenuId())
                .isEqualTo(menuId);
    }

    @Test
    @DisplayName("권한 메뉴 등록")
    void insertRoleMenuTest() {
        Long menuId = 10001L;
        MenuRequestDto menuDto =
                MenuRequestDto.builder()
                        .menuId(menuId)
                        .menuName("테스트메뉴")
                        .menuUrl("/test/menu")
                        .menuLevel(1)
                        .sortOrder(1)
                        .iconName("home")
                        .useYn("Y")
                        .build();
        menuMapper.insertMenu(menuDto,"SYSTEM");

        RoleMenuRequestDto dto =
                RoleMenuRequestDto.builder()
                        .roleCode("ADMIN")
                        .menuId(menuId)
                        .readYn("Y")
                        .createYn("N")
                        .updateYn("N")
                        .deleteYn("N")
                        .excelYn("N")
                        .build();

        int result =
                roleMenuMapper.insertRoleMenu(dto, "SYSTEM");

        assertThat(result).isEqualTo(1);

        RoleMenuResponseDto saved =
                roleMenuMapper.selectRoleMenuDetail("ADMIN", 10001L);

        assertThat(saved).isNotNull();

        assertThat(saved.getReadYn())
                .isEqualTo("Y");
    }

    @Test
    @DisplayName("권한 메뉴 수정")
    void updateRoleMenuTest() {
        Long menuId = 10001L;

        MenuRequestDto menuDto =
                MenuRequestDto.builder()
                        .menuId(menuId)
                        .menuName("테스트메뉴")
                        .menuUrl("/test/menu")
                        .menuLevel(1)
                        .sortOrder(1)
                        .iconName("home")
                        .useYn("Y")
                        .build();
        menuMapper.insertMenu(menuDto,"SYSTEM");

        String roleCode = "ADMIN";

        RoleMenuRequestDto insertDto =
                RoleMenuRequestDto.builder()
                        .roleCode(roleCode)
                        .menuId(menuId)
                        .readYn("Y")
                        .createYn("N")
                        .updateYn("N")
                        .deleteYn("N")
                        .excelYn("N")
                        .build();

        roleMenuMapper.insertRoleMenu(insertDto, "SYSTEM");

        RoleMenuRequestDto updateDto =
                RoleMenuRequestDto.builder()
                        .readYn("Y")
                        .createYn("Y")
                        .updateYn("Y")
                        .deleteYn("Y")
                        .excelYn("Y")
                        .build();

        int result =
                roleMenuMapper.updateRoleMenu(
                        updateDto,
                        roleCode,
                        menuId,
                        "SYSTEM"
                );

        assertThat(result).isEqualTo(1);

        RoleMenuResponseDto updated =
                roleMenuMapper.selectRoleMenuDetail(roleCode, menuId);

        assertThat(updated).isNotNull();

        assertThat(updated.getCreateYn())
                .isEqualTo("Y");

        assertThat(updated.getDeleteYn())
                .isEqualTo("Y");
    }

    @Test
    @DisplayName("권한 메뉴 삭제")
    void deleteRoleMenuTest() {
        Long menuId = 10001L;
        MenuRequestDto menuDto =
                MenuRequestDto.builder()
                        .menuId(menuId)
                        .menuName("테스트메뉴")
                        .menuUrl("/test/menu")
                        .menuLevel(1)
                        .sortOrder(1)
                        .iconName("home")
                        .useYn("Y")
                        .build();
        menuMapper.insertMenu(menuDto,"SYSTEM");

        String roleCode = "ADMIN";

        RoleMenuRequestDto insertDto =
                RoleMenuRequestDto.builder()
                        .roleCode(roleCode)
                        .menuId(menuId)
                        .readYn("Y")
                        .createYn("N")
                        .updateYn("N")
                        .deleteYn("N")
                        .excelYn("N")
                        .build();

        roleMenuMapper.insertRoleMenu(insertDto, "SYSTEM");

        int result =
                roleMenuMapper.deleteRoleMenu(roleCode, menuId);

        assertThat(result).isEqualTo(1);

        RoleMenuResponseDto deleted =
                roleMenuMapper.selectRoleMenuDetail(roleCode, menuId);

        assertThat(deleted).isNull();
    }
}