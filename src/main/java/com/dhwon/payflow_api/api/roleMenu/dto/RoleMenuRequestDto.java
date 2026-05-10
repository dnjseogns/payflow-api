package com.dhwon.payflow_api.api.roleMenu.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuRequestDto {

    private String roleCode;
    private Long menuId;

    private String readYn;
    private String createYn;
    private String updateYn;
    private String deleteYn;
    private String excelYn;
}