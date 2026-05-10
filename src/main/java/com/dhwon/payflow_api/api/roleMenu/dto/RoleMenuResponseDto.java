package com.dhwon.payflow_api.api.roleMenu.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuResponseDto {

    private String roleCode;
    private Long menuId;

    private String readYn;
    private String createYn;
    private String updateYn;
    private String deleteYn;
    private String excelYn;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}