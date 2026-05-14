package com.dhwon.payflow_api.api.auth.controller;

import com.dhwon.payflow_api.api.auth.dto.AuthRequestDto;
import com.dhwon.payflow_api.api.auth.dto.AuthResponseDto;
import com.dhwon.payflow_api.api.auth.dto.ReissueRequestDto;
import com.dhwon.payflow_api.api.auth.dto.ReissueResponseDto;
import com.dhwon.payflow_api.api.auth.service.AuthService;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public AuthResponseDto login(@Valid @RequestBody AuthRequestDto authRequestDto,
                                 HttpServletResponse response){
        return authService.login(authRequestDto, response);
    }

    @PostMapping("/reissue")
    @UseCommonResponse
    public ReissueResponseDto reissue(@RequestBody ReissueRequestDto dto,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        return authService.reissue(dto, request, response);
    }
}
