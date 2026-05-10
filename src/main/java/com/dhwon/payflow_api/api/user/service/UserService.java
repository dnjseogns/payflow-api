package com.dhwon.payflow_api.api.user.service;

import com.dhwon.payflow_api.api.user.dto.UserRequestDto;
import com.dhwon.payflow_api.api.user.dto.UserResponseDto;
import com.dhwon.payflow_api.api.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public List<UserResponseDto> selectUserList() {
        return userMapper.selectUserList();
    }

    public UserResponseDto selectUserDetail(String userId) {
        return userMapper.selectUserDetail(userId);
    }

    public int insertUser(UserRequestDto dto, String loginUserId) {
        return userMapper.insertUser(dto, loginUserId);
    }

    public int updateUser(
            UserRequestDto dto,
            String userId,
            String loginUserId
    ) {
        return userMapper.updateUser(dto, userId, loginUserId);
    }

    public int deleteUser(String userId) {
        return userMapper.deleteUser(userId);
    }
}