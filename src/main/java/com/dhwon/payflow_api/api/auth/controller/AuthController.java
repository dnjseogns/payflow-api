package com.dhwon.payflow_api.api.auth.controller;

import com.dhwon.payflow_api.api.auth.dto.AuthRequestDto;
import com.dhwon.payflow_api.api.auth.dto.AuthResponseDto;
import com.dhwon.payflow_api.api.auth.dto.ReissueRequestDto;
import com.dhwon.payflow_api.api.auth.dto.ReissueResponseDto;
import com.dhwon.payflow_api.api.auth.service.AuthService;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @UseCommonResponse
    public AuthResponseDto login(@Valid @RequestBody AuthRequestDto authRequestDto){
        return authService.login(authRequestDto);
    }

    @PostMapping("/reissue")
    @UseCommonResponse
    public ReissueResponseDto reissue(@RequestBody ReissueRequestDto dto) {
        return authService.reissue(dto);
    }
}
