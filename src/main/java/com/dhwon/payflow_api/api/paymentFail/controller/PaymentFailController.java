package com.dhwon.payflow_api.api.paymentFail.controller;

import com.dhwon.payflow_api.api.paymentFail.dto.PaymentFailSelectRequestDto;
import com.dhwon.payflow_api.api.paymentFail.dto.PaymentFailSelectResponseDto;
import com.dhwon.payflow_api.api.paymentFail.service.PaymentFailService;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment-fail")
public class PaymentFailController {

    private final PaymentFailService paymentFailService;

    @PostMapping("/select")
    @UseCommonResponse
    public PagingResponseDto<PaymentFailSelectResponseDto> selectPaymentFailList(
            @RequestBody PaymentFailSelectRequestDto dto
    ) {
        return paymentFailService.selectPaymentFailList(dto);
    }
}