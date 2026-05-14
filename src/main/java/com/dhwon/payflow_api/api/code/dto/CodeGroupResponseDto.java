package com.dhwon.payflow_api.api.code.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeGroupResponseDto {

    private String codeGroup;

    private String groupName;

    private String groupDescription;

    private List<CodeResponseDto> codes;
}