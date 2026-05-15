package com.dhwon.payflow_api.api.paymentAggStatus.service;

import com.dhwon.payflow_api.api.paymentAggStatus.dto.PaymentAggStatusSelectRequestDto;
import com.dhwon.payflow_api.api.paymentAggStatus.dto.PaymentAggStatusSelectResponseDto;
import com.dhwon.payflow_api.api.paymentAggStatus.mapper.PaymentAggStatusMapper;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentAggStatusService {

    private final PaymentAggStatusMapper paymentAggStatusMapper;

    public PagingResponseDto<PaymentAggStatusSelectResponseDto> selectPaymentAggStatusList(PaymentAggStatusSelectRequestDto dto) {

        List<PaymentAggStatusSelectResponseDto> list =
                paymentAggStatusMapper.selectPaymentAggStatusList(dto);

        int totalCount =
                paymentAggStatusMapper.selectPaymentAggStatusListCount(dto);

        return PagingResponseDto.<PaymentAggStatusSelectResponseDto>builder()
                .list(list)
                .totalCount(totalCount)
                .build();
    }
}