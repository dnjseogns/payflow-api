package com.dhwon.payflow_api.api.payment.controller;

import com.dhwon.payflow_api.api.payment.dto.PaymentSelectRequestDto;
import com.dhwon.payflow_api.api.payment.dto.PaymentSelectResponseDto;
import com.dhwon.payflow_api.api.payment.service.PaymentService;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/select")
    @UseCommonResponse
    public PagingResponseDto<PaymentSelectResponseDto> selectPaymentList(
            @RequestBody PaymentSelectRequestDto dto
    ) {
        return paymentService.selectPaymentList(dto);
    }
}