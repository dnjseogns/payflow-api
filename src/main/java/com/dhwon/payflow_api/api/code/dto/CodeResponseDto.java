package com.dhwon.payflow_api.api.code.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeResponseDto {

    private String code;
    private String codeName;

    private String codeDescription;

    private Integer sortOrder;
}