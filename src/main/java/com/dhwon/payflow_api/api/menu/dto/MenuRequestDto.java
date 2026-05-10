package com.dhwon.payflow_api.api.menu.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequestDto {

    private Long menuId;
    private Long parentMenuId;
    private String menuName;
    private String menuUrl;
    private Integer menuLevel;
    private Integer sortOrder;
    private String iconName;
    private String useYn;
}