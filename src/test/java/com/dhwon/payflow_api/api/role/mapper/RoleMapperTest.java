package com.dhwon.payflow_api.api.role.mapper;

import com.dhwon.payflow_api.api.role.dto.RoleRequestDto;
import com.dhwon.payflow_api.api.role.dto.RoleResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RoleMapperTest {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    @DisplayName("권한 목록 조회")
    void selectRoleListTest() {

        // when
        List<RoleResponseDto> result = roleMapper.selectRoleList();

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("권한 단건 조회")
    void selectRoleDetailTest() {

        // given
        String roleCode = "TEST_ROLE_01";

        RoleRequestDto insertDto =
                RoleRequestDto.builder()
                        .roleCode(roleCode)
                        .roleName("테스트권한")
                        .roleDesc("테스트설명")
                        .useYn("Y")
                        .build();

        roleMapper.insertRole(insertDto, "SYSTEM");

        // when
        RoleResponseDto result =
                roleMapper.selectRoleDetail(roleCode);

        // then
        assertThat(result).isNotNull();

        assertThat(result.getRoleCode())
                .isEqualTo(roleCode);

        assertThat(result.getRoleName())
                .isEqualTo("테스트권한");
    }

    @Test
    @DisplayName("권한 등록")
    void insertRoleTest() {

        // given
        String roleCode =
                "TEST_" + System.currentTimeMillis();

        RoleRequestDto dto =
                RoleRequestDto.builder()
                        .roleCode(roleCode)
                        .roleName("등록테스트")
                        .roleDesc("등록설명")
                        .useYn("Y")
                        .build();

        // when
        int result =
                roleMapper.insertRole(dto, "SYSTEM");

        // then
        assertThat(result).isEqualTo(1);

        RoleResponseDto saved =
                roleMapper.selectRoleDetail(roleCode);

        assertThat(saved).isNotNull();

        assertThat(saved.getRoleName())
                .isEqualTo("등록테스트");

        assertThat(saved.getUseYn())
                .isEqualTo("Y");
    }

    @Test
    @DisplayName("권한 수정")
    void updateRoleTest() {

        // given
        String roleCode =
                "TEST_" + System.currentTimeMillis();

        RoleRequestDto insertDto =
                RoleRequestDto.builder()
                        .roleCode(roleCode)
                        .roleName("기존권한")
                        .roleDesc("기존설명")
                        .useYn("Y")
                        .build();

        roleMapper.insertRole(insertDto, "SYSTEM");

        RoleRequestDto updateDto =
                RoleRequestDto.builder()
                        .roleName("수정권한")
                        .roleDesc("수정설명")
                        .useYn("N")
                        .build();

        // when
        int result =
                roleMapper.updateRole(
                        updateDto,
                        roleCode,
                        "SYSTEM"
                );

        // then
        assertThat(result).isEqualTo(1);

        RoleResponseDto updated =
                roleMapper.selectRoleDetail(roleCode);

        assertThat(updated).isNotNull();

        assertThat(updated.getRoleName())
                .isEqualTo("수정권한");

        assertThat(updated.getRoleDesc())
                .isEqualTo("수정설명");

        assertThat(updated.getUseYn())
                .isEqualTo("N");
    }

    @Test
    @DisplayName("권한 삭제")
    void deleteRoleTest() {

        // given
        String roleCode =
                "TEST_" + System.currentTimeMillis();

        RoleRequestDto insertDto =
                RoleRequestDto.builder()
                        .roleCode(roleCode)
                        .roleName("삭제권한")
                        .roleDesc("삭제설명")
                        .useYn("Y")
                        .build();

        roleMapper.insertRole(insertDto, "SYSTEM");

        // when
        int result =
                roleMapper.deleteRole(roleCode);

        // then
        assertThat(result).isEqualTo(1);

        RoleResponseDto deleted =
                roleMapper.selectRoleDetail(roleCode);

        assertThat(deleted).isNull();
    }
}