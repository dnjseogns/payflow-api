package com.dhwon.payflow_api.api.aggpayment.service;

import com.dhwon.payflow_api.api.aggpayment.dto.*;
import com.dhwon.payflow_api.api.aggpayment.mapper.AggPaymentMapper;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AggPaymentService {

    private final AggPaymentMapper aggPaymentMapper;

    public AggPaymentSummaryResponseDto selectSummary(
            AggPaymentSelectRequestDto dto
    ) {
        return aggPaymentMapper.selectSummary(dto);
    }

    public PagingResponseDto<AggPaymentDetailResponseDto> selectDetail(
            AggPaymentSelectRequestDto dto
    ) {

        List<AggPaymentDetailResponseDto> list =
                aggPaymentMapper.selectDetail(dto);

        int totalCount =
                aggPaymentMapper.selectDetailCount(dto);

        return PagingResponseDto.<AggPaymentDetailResponseDto>builder()
                .list(list)
                .totalCount(totalCount)
                .build();
    }
}