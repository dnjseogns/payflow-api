package com.dhwon.payflow_api.api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDto {
    @NotBlank(message = "userId는 필수값입니다.")
    private String userId;

    @NotBlank(message = "password는 필수값입니다.")
    private String userPw;
}

