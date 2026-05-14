package com.dhwon.payflow_api.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AuthResultDto {
    private String userId;
    private String userPw;
    private String userName;
    private String roleCode;
}
