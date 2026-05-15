package com.dhwon.payflow_api.api.paymentAggStatus.mapper;

import com.dhwon.payflow_api.api.paymentAggStatus.dto.PaymentAggStatusSelectRequestDto;
import com.dhwon.payflow_api.api.paymentAggStatus.dto.PaymentAggStatusSelectResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentAggStatusMapper {

    List<PaymentAggStatusSelectResponseDto> selectPaymentAggStatusList(@Param("dto") PaymentAggStatusSelectRequestDto dto);

    int selectPaymentAggStatusListCount(@Param("dto") PaymentAggStatusSelectRequestDto dto);
}