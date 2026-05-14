package com.dhwon.payflow_api.api.code.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeSelectResponseDto {

    private String codeGroup;
    private String groupName;
    private String groupDescription;

    private String code;
    private String codeName;
    private String codeDescription;

    private Integer sortOrder;
}