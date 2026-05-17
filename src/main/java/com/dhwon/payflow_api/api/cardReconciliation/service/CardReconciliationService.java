package com.dhwon.payflow_api.api.cardReconciliation.service;

import com.dhwon.payflow_api.api.cardReconciliation.dto.CardReconciliationSelectRequestDto;
import com.dhwon.payflow_api.api.cardReconciliation.dto.CardReconciliationSelectResponseDto;
import com.dhwon.payflow_api.api.cardReconciliation.mapper.CardReconciliationMapper;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardReconciliationService {

    private final CardReconciliationMapper cardReconciliationMapper;

    public PagingResponseDto<CardReconciliationSelectResponseDto> selectCardReconciliationList(
            CardReconciliationSelectRequestDto dto
    ) {

        List<CardReconciliationSelectResponseDto> list
                = cardReconciliationMapper.selectCardReconciliationList(dto);

        int totalCount
                = cardReconciliationMapper.selectCardReconciliationListCount(dto);

        return PagingResponseDto.<CardReconciliationSelectResponseDto>builder()
                .list(list)
                .totalCount(totalCount)
                .build();
    }
}