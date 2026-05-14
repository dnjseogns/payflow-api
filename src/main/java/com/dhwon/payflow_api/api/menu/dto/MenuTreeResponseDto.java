package com.dhwon.payflow_api.api.menu.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MenuTreeResponseDto {

    private Long menuId;
    private Long parentMenuId;

    private String menuName;
    private String menuUrl;

    private Integer menuLevel;
    private Integer sortOrder;

    private String iconName;

    private String readYn;
    private String createYn;
    private String updateYn;
    private String deleteYn;

    private List<MenuTreeResponseDto> children = new ArrayList<>();
}