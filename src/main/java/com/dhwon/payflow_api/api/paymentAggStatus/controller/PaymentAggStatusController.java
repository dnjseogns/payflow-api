package com.dhwon.payflow_api.api.paymentAggStatus.controller;

import com.dhwon.payflow_api.api.paymentAggStatus.dto.PaymentAggStatusSelectRequestDto;
import com.dhwon.payflow_api.api.paymentAggStatus.dto.PaymentAggStatusSelectResponseDto;
import com.dhwon.payflow_api.api.paymentAggStatus.service.PaymentAggStatusService;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment-agg-status")
public class PaymentAggStatusController {

    private final PaymentAggStatusService paymentAggStatusService;

    @PostMapping("/select")
    @UseCommonResponse
    public PagingResponseDto<PaymentAggStatusSelectResponseDto> selectPaymentAggStatusList(
            @RequestBody PaymentAggStatusSelectRequestDto dto
    ) {
        return paymentAggStatusService.selectPaymentAggStatusList(dto);
    }
}