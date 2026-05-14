package com.dhwon.payflow_api.api.code.controller;

import com.dhwon.payflow_api.api.code.dto.CodeGroupResponseDto;
import com.dhwon.payflow_api.api.code.service.CodeService;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/code")
public class CodeController {

    private final CodeService codeService;

    @GetMapping
    @UseCommonResponse
    public List<CodeGroupResponseDto> selectCodeList() {
        return codeService.selectCodeList();
    }
}