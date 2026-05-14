package com.dhwon.payflow_api.api.user.mapper;

import com.dhwon.payflow_api.api.role.dto.RoleRequestDto;
import com.dhwon.payflow_api.api.role.mapper.RoleMapper;
import com.dhwon.payflow_api.api.user.dto.UserRequestDto;
import com.dhwon.payflow_api.api.user.dto.UserResponseDto;
import com.dhwon.payflow_api.api.user.dto.UserSelectRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Test
    @DisplayName("사용자 목록 조회")
    void selectUserListTest() {

//        List<UserSelectRequestDto> result =
//                userMapper.selectUserList();
//
//        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사용자 단건 조회")
    void selectUserDetailTest() {

        String roleCode = "ROLE01";

        RoleRequestDto roleDto =
                RoleRequestDto.builder()
                        .roleCode(roleCode)
                        .roleName("관리자권한")
                        .roleDesc("관리자권한설명")
                        .useYn("Y")
                        .build();

        roleMapper.insertRole(roleDto, "SYSTEM");

        String userId = "testuser01";

        UserRequestDto insertDto =
                UserRequestDto.builder()
                        .userId(userId)
                        .userPw("1234")
                        .userName("홍길동")
                        .roleCode(roleCode)
                        .useYn("Y")
                        .build();

        userMapper.insertUser(insertDto, "SYSTEM");

        UserResponseDto result =
                userMapper.selectUserDetail(userId);

        assertThat(result).isNotNull();

        assertThat(result.getUserId())
                .isEqualTo(userId);

        assertThat(result.getUserName())
                .isEqualTo("홍길동");

        assertThat(result.getRoleCode())
                .isEqualTo(roleCode);
    }

    @Test
    @DisplayName("사용자 등록")
    void insertUserTest() {

        String roleCode = "ROLE02";

        RoleRequestDto roleDto =
                RoleRequestDto.builder()
                        .roleCode(roleCode)
                        .roleName("사용자권한")
                        .roleDesc("사용자권한설명")
                        .useYn("Y")
                        .build();

        roleMapper.insertRole(roleDto, "SYSTEM");

        String userId = "testuser02";

        UserRequestDto dto =
                UserRequestDto.builder()
                        .userId(userId)
                        .userPw("1234")
                        .userName("등록사용자")
                        .roleCode(roleCode)
                        .useYn("Y")
                        .build();

        int result =
                userMapper.insertUser(dto, "SYSTEM");

        assertThat(result).isEqualTo(1);

        UserResponseDto saved =
                userMapper.selectUserDetail(userId);

        assertThat(saved).isNotNull();

        assertThat(saved.getUserId())
                .isEqualTo(userId);

        assertThat(saved.getUserName())
                .isEqualTo("등록사용자");

        assertThat(saved.getRoleCode())
                .isEqualTo(roleCode);
    }

    @Test
    @DisplayName("사용자 수정")
    void updateUserTest() {

        String roleCode = "ROLE03";

        RoleRequestDto roleDto =
                RoleRequestDto.builder()
                        .roleCode(roleCode)
                        .roleName("수정권한")
                        .roleDesc("수정권한설명")
                        .useYn("Y")
                        .build();

        roleMapper.insertRole(roleDto, "SYSTEM");

        String userId = "testuser03";

        UserRequestDto insertDto =
                UserRequestDto.builder()
                        .userId(userId)
                        .userPw("1234")
                        .userName("기존사용자")
                        .roleCode(roleCode)
                        .useYn("Y")
                        .build();

        userMapper.insertUser(insertDto, "SYSTEM");

        UserRequestDto updateDto =
                UserRequestDto.builder()
                        .userPw("5678")
                        .userName("수정사용자")
                        .roleCode(roleCode)
                        .useYn("N")
                        .build();

        int result =
                userMapper.updateUser(
                        updateDto,
                        userId,
                        "SYSTEM"
                );

        assertThat(result).isEqualTo(1);

        UserResponseDto updated =
                userMapper.selectUserDetail(userId);

        assertThat(updated).isNotNull();

        assertThat(updated.getUserName())
                .isEqualTo("수정사용자");

        assertThat(updated.getUserPw())
                .isEqualTo("5678");

        assertThat(updated.getUseYn())
                .isEqualTo("N");
    }

    @Test
    @DisplayName("사용자 삭제")
    void deleteUserTest() {

        String roleCode = "ROLE04";

        RoleRequestDto roleDto =
                RoleRequestDto.builder()
                        .roleCode(roleCode)
                        .roleName("삭제권한")
                        .roleDesc("삭제권한설명")
                        .useYn("Y")
                        .build();

        roleMapper.insertRole(roleDto, "SYSTEM");

        String userId = "testuser04";

        UserRequestDto insertDto =
                UserRequestDto.builder()
                        .userId(userId)
                        .userPw("1234")
                        .userName("삭제사용자")
                        .roleCode(roleCode)
                        .useYn("Y")
                        .build();

        userMapper.insertUser(insertDto, "SYSTEM");

        int result =
                userMapper.deleteUser(userId);

        assertThat(result).isEqualTo(1);

        UserResponseDto deleted =
                userMapper.selectUserDetail(userId);

        assertThat(deleted).isNull();
    }
}