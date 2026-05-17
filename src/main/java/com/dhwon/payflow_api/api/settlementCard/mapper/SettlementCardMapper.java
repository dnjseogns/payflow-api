package com.dhwon.payflow_api.api.settlementCard.mapper;

import com.dhwon.payflow_api.api.settlementCard.dto.SettlementCardSelectRequestDto;
import com.dhwon.payflow_api.api.settlementCard.dto.SettlementCardSelectResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SettlementCardMapper {

    List<SettlementCardSelectResponseDto> selectSettlementCardList(
            @Param("dto") SettlementCardSelectRequestDto dto
    );

    int selectSettlementCardListCount(
            @Param("dto") SettlementCardSelectRequestDto dto
    );
}