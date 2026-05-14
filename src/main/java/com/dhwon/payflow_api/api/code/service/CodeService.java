package com.dhwon.payflow_api.api.code.service;

import com.dhwon.payflow_api.api.code.dto.CodeGroupResponseDto;
import com.dhwon.payflow_api.api.code.dto.CodeResponseDto;
import com.dhwon.payflow_api.api.code.dto.CodeSelectResponseDto;
import com.dhwon.payflow_api.api.code.mapper.CodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeMapper codeMapper;

    public List<CodeGroupResponseDto> selectCodeList() {

        List<CodeSelectResponseDto> rows = codeMapper.selectCodeList();

        Map<String, CodeGroupResponseDto> resultMap = new LinkedHashMap<>();

        for (CodeSelectResponseDto row : rows) {

            CodeGroupResponseDto group =
                    resultMap.computeIfAbsent(
                            row.getCodeGroup(),
                            key -> CodeGroupResponseDto.builder()
                                    .codeGroup(row.getCodeGroup())
                                    .groupName(row.getGroupName())
                                    .groupDescription(row.getGroupDescription())
                                    .codes(new ArrayList<>())
                                    .build()
                    );

            group.getCodes().add(
                    CodeResponseDto.builder()
                            .code(row.getCode())
                            .codeName(row.getCodeName())
                            .codeDescription(row.getCodeDescription())
                            .sortOrder(row.getSortOrder())
                            .build()
            );
        }

        return new ArrayList<>(resultMap.values());
    }
}