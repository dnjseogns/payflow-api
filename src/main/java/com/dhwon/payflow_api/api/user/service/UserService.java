package com.dhwon.payflow_api.api.user.service;

import com.dhwon.payflow_api.api.user.dto.UserRequestDto;
import com.dhwon.payflow_api.api.user.dto.UserResponseDto;
import com.dhwon.payflow_api.api.user.dto.UserSelectRequestDto;
import com.dhwon.payflow_api.api.user.mapper.UserMapper;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public PagingResponseDto<UserResponseDto> selectUserList(UserSelectRequestDto dto) {
        List<UserResponseDto> list = userMapper.selectUserList(dto);

        int totalCount = userMapper.selectUserListCount(dto);

        return PagingResponseDto
                .<UserResponseDto>builder()
                .list(list)
                .totalCount(totalCount)
                .build();
    }
    public int selectUserListCount(UserSelectRequestDto userSelectRequestDto) {
        return userMapper.selectUserListCount(userSelectRequestDto);
    }


    public UserResponseDto selectUserDetail(String userId) {
        return userMapper.selectUserDetail(userId);
    }

    public int insertUser(UserRequestDto dto, String loginUserId) {
        String encodedPw = passwordEncoder.encode(dto.getUserPw());
        dto.setUserPw(encodedPw);
        return userMapper.insertUser(dto, loginUserId);
    }

    public int updateUser(
            UserRequestDto dto,
            String userId,
            String loginUserId
    ) {
        String encodedPw = passwordEncoder.encode(dto.getUserPw());
        dto.setUserPw(encodedPw);
        return userMapper.updateUser(dto, userId, loginUserId);
    }

    public int deleteUser(String userId) {
        return userMapper.deleteUser(userId);
    }
}