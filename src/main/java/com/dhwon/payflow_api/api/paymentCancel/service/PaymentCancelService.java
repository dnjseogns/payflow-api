package com.dhwon.payflow_api.api.paymentCancel.service;

import com.dhwon.payflow_api.api.paymentCancel.dto.PaymentCancelSelectRequestDto;
import com.dhwon.payflow_api.api.paymentCancel.dto.PaymentCancelSelectResponseDto;
import com.dhwon.payflow_api.api.paymentCancel.mapper.PaymentCancelMapper;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentCancelService {

    private final PaymentCancelMapper paymentCancelMapper;

    public PagingResponseDto<PaymentCancelSelectResponseDto> selectPaymentCancelList(
            PaymentCancelSelectRequestDto dto
    ) {

        List<PaymentCancelSelectResponseDto> list =
                paymentCancelMapper.selectPaymentCancelList(dto);

        int totalCount =
                paymentCancelMapper.selectPaymentCancelListCount(dto);

        return PagingResponseDto.<PaymentCancelSelectResponseDto>builder()
                .list(list)
                .totalCount(totalCount)
                .build();
    }
}