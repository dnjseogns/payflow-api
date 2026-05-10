package com.dhwon.payflow_api.api.user.controller;

import com.dhwon.payflow_api.api.user.dto.UserRequestDto;
import com.dhwon.payflow_api.api.user.dto.UserResponseDto;
import com.dhwon.payflow_api.api.user.service.UserService;
import com.dhwon.payflow_api.cmm.auth.context.UserContext;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    @UseCommonResponse
    public List<UserResponseDto> selectUserList() {
        return userService.selectUserList();
    }

    @GetMapping("/{userId}")
    @UseCommonResponse
    public UserResponseDto selectUserDetail(
            @PathVariable("userId") String userId
    ) {
        return userService.selectUserDetail(userId);
    }

    @PostMapping
    @UseCommonResponse
    public int insertUser(
            @RequestBody UserRequestDto userRequestDto
    ) {
        return userService.insertUser(
                userRequestDto,
                UserContext.get().getUserId()
        );
    }

    @PutMapping("/{userId}")
    @UseCommonResponse
    public int updateUser(
            @PathVariable("userId") String userId,
            @RequestBody UserRequestDto userRequestDto
    ) {
        return userService.updateUser(
                userRequestDto,
                userId,
                UserContext.get().getUserId()
        );
    }

    @DeleteMapping("/{userId}")
    @UseCommonResponse
    public int deleteUser(
            @PathVariable("userId") String userId
    ) {
        return userService.deleteUser(userId);
    }
}