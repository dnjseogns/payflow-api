package com.dhwon.payflow_api.api.aggpayment.mapper;

import com.dhwon.payflow_api.api.aggpayment.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AggPaymentMapper {

    AggPaymentSummaryResponseDto selectSummary(
            @Param("dto") AggPaymentSelectRequestDto dto
    );

    List<AggPaymentDetailResponseDto> selectDetail(
            @Param("dto") AggPaymentSelectRequestDto dto
    );

    int selectDetailCount(
            @Param("dto") AggPaymentSelectRequestDto dto
    );
}