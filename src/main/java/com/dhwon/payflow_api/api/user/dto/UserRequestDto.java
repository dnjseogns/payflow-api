package com.dhwon.payflow_api.api.user.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String userId;
    private String userPw;
    private String userName;
    private String roleCode;
    private String useYn;
}