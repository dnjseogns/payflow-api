package com.dhwon.payflow_api.api.aggpayment.controller;

import com.dhwon.payflow_api.api.aggpayment.dto.*;
import com.dhwon.payflow_api.api.aggpayment.service.AggPaymentService;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agg-payment")
public class AggPaymentController {

    private final AggPaymentService aggPaymentService;

    @PostMapping("/select-summary")
    @UseCommonResponse
    public AggPaymentSummaryResponseDto selectSummary(
            @RequestBody AggPaymentSelectRequestDto dto
    ) {
        return aggPaymentService.selectSummary(dto);
    }

    @PostMapping("/select-detail")
    @UseCommonResponse
    public PagingResponseDto<AggPaymentDetailResponseDto> selectDetail(
            @RequestBody AggPaymentSelectRequestDto dto
    ) {
        return aggPaymentService.selectDetail(dto);
    }
}