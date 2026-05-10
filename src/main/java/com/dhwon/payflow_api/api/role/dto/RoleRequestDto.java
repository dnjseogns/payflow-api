package com.dhwon.payflow_api.api.role.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDto {
    private String roleCode;
    private String roleName;
    private String roleDesc;
    private String useYn;
}
