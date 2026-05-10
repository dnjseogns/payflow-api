package com.dhwon.payflow_api.api.role.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDto {
    private String roleCode;
    private String roleName;
    private String roleDesc;
    private String useYn;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}