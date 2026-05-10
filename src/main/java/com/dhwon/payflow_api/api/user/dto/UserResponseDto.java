package com.dhwon.payflow_api.api.user.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String userId;
    private String userPw;
    private String userName;
    private String roleCode;
    private String useYn;

    private LocalDateTime lastLoginDate;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}