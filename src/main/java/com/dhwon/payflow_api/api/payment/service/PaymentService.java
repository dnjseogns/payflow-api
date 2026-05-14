package com.dhwon.payflow_api.api.payment.service;

import com.dhwon.payflow_api.api.payment.dto.PaymentSelectRequestDto;
import com.dhwon.payflow_api.api.payment.dto.PaymentSelectResponseDto;
import com.dhwon.payflow_api.api.payment.mapper.PaymentMapper;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentMapper paymentMapper;

    public PagingResponseDto<PaymentSelectResponseDto> selectPaymentList(PaymentSelectRequestDto dto) {

        List<PaymentSelectResponseDto> list = paymentMapper.selectPaymentList(dto);
        int totalCount = paymentMapper.selectPaymentListCount(dto);

        return PagingResponseDto.<PaymentSelectResponseDto>builder()
                .list(list)
                .totalCount(totalCount)
                .build();
    }
}