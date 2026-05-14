package com.dhwon.payflow_api.api.user.mapper;

import com.dhwon.payflow_api.api.user.dto.UserRequestDto;
import com.dhwon.payflow_api.api.user.dto.UserResponseDto;
import com.dhwon.payflow_api.api.user.dto.UserSelectRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserResponseDto> selectUserList(@Param("dto") UserSelectRequestDto userSelectRequestDto);
    int selectUserListCount(@Param("dto") UserSelectRequestDto userSelectRequestDto);

    UserResponseDto selectUserDetail(
            @Param("userId") String userId
    );

    int insertUser(
            @Param("dto") UserRequestDto dto,
            @Param("loginUserId") String loginUserId
    );

    int updateUser(
            @Param("dto") UserRequestDto dto,
            @Param("userId") String userId,
            @Param("loginUserId") String loginUserId
    );

    int deleteUser(
            @Param("userId") String userId
    );
}