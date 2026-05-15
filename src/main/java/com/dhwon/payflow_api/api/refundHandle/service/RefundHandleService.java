package com.dhwon.payflow_api.api.refundHandle.service;

import com.dhwon.payflow_api.api.refundHandle.dto.RefundHandleSelectRequestDto;
import com.dhwon.payflow_api.api.refundHandle.dto.RefundHandleSelectResponseDto;
import com.dhwon.payflow_api.api.refundHandle.mapper.RefundHandleMapper;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefundHandleService {

    private final RefundHandleMapper refundHandleMapper;

    public PagingResponseDto<RefundHandleSelectResponseDto> selectRefundHandleList(
            RefundHandleSelectRequestDto dto
    ) {

        List<RefundHandleSelectResponseDto> list =
                refundHandleMapper.selectRefundHandleList(dto);

        int totalCount =
                refundHandleMapper.selectRefundHandleListCount(dto);

        return PagingResponseDto.<RefundHandleSelectResponseDto>builder()
                .list(list)
                .totalCount(totalCount)
                .build();
    }
}