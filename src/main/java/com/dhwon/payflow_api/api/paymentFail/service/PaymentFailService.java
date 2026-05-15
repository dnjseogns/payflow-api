package com.dhwon.payflow_api.api.paymentFail.service;

import com.dhwon.payflow_api.api.paymentFail.dto.PaymentFailSelectRequestDto;
import com.dhwon.payflow_api.api.paymentFail.dto.PaymentFailSelectResponseDto;
import com.dhwon.payflow_api.api.paymentFail.mapper.PaymentFailMapper;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentFailService {

    private final PaymentFailMapper paymentFailMapper;

    public PagingResponseDto<PaymentFailSelectResponseDto> selectPaymentFailList(
            PaymentFailSelectRequestDto dto
    ) {

        List<PaymentFailSelectResponseDto> list =
                paymentFailMapper.selectPaymentFailList(dto);

        int totalCount =
                paymentFailMapper.selectPaymentFailListCount(dto);

        return PagingResponseDto.<PaymentFailSelectResponseDto>builder()
                .list(list)
                .totalCount(totalCount)
                .build();
    }
}