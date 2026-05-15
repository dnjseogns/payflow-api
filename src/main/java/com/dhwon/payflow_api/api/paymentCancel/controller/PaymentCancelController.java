package com.dhwon.payflow_api.api.paymentCancel.controller;

import com.dhwon.payflow_api.api.paymentCancel.dto.PaymentCancelSelectRequestDto;
import com.dhwon.payflow_api.api.paymentCancel.dto.PaymentCancelSelectResponseDto;
import com.dhwon.payflow_api.api.paymentCancel.service.PaymentCancelService;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment-cancel")
public class PaymentCancelController {

    private final PaymentCancelService paymentCancelService;

    @PostMapping("/select")
    @UseCommonResponse
    public PagingResponseDto<PaymentCancelSelectResponseDto> selectPaymentCancelList(
            @RequestBody PaymentCancelSelectRequestDto dto
    ) {

        return paymentCancelService.selectPaymentCancelList(dto);
    }
}