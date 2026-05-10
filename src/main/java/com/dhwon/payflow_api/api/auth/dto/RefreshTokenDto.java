package com.dhwon.payflow_api.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenDto {

    private String userId;

    private String refreshToken;

    private Date expireDate;
}
