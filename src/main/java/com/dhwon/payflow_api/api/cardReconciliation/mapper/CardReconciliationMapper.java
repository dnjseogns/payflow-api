package com.dhwon.payflow_api.api.cardReconciliation.mapper;

import com.dhwon.payflow_api.api.cardReconciliation.dto.CardReconciliationSelectRequestDto;
import com.dhwon.payflow_api.api.cardReconciliation.dto.CardReconciliationSelectResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CardReconciliationMapper {

    List<CardReconciliationSelectResponseDto> selectCardReconciliationList(
            @Param("dto") CardReconciliationSelectRequestDto dto
    );

    int selectCardReconciliationListCount(
            @Param("dto") CardReconciliationSelectRequestDto dto
    );
}