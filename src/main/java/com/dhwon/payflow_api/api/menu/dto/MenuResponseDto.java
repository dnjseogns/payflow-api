package com.dhwon.payflow_api.api.menu.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponseDto {

    private Long menuId;
    private Long parentMenuId;
    private String menuName;
    private String menuUrl;
    private Integer menuLevel;
    private Integer sortOrder;
    private String iconName;
    private String useYn;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}