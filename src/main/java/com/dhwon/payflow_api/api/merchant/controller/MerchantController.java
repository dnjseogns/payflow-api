package com.dhwon.payflow_api.api.merchant.controller;

import com.dhwon.payflow_api.api.merchant.dto.MerchantSelectResponseDto;
import com.dhwon.payflow_api.api.merchant.service.MerchantService;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/select")
    @UseCommonResponse
    public List<MerchantSelectResponseDto> selectMerchantList() {

        return merchantService.selectMerchantList();
    }
}