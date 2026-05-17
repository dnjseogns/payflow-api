package com.dhwon.payflow_api.api.settlementCard.service;

import com.dhwon.payflow_api.api.settlementCard.dto.SettlementCardSelectRequestDto;
import com.dhwon.payflow_api.api.settlementCard.dto.SettlementCardSelectResponseDto;
import com.dhwon.payflow_api.api.settlementCard.mapper.SettlementCardMapper;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementCardService {

    private final SettlementCardMapper settlementCardMapper;

    public PagingResponseDto<SettlementCardSelectResponseDto> selectSettlementCardList(
            SettlementCardSelectRequestDto dto
    ) {

        List<SettlementCardSelectResponseDto> list
                = settlementCardMapper.selectSettlementCardList(dto);

        int totalCount
                = settlementCardMapper.selectSettlementCardListCount(dto);

        return PagingResponseDto.<SettlementCardSelectResponseDto>builder()
                .list(list)
                .totalCount(totalCount)
                .build();
    }
}