package com.dhwon.payflow_api.api.payment.mapper;

import com.dhwon.payflow_api.api.payment.dto.PaymentSelectRequestDto;
import com.dhwon.payflow_api.api.payment.dto.PaymentSelectResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {

    List<PaymentSelectResponseDto> selectPaymentList(@Param("dto") PaymentSelectRequestDto dto);

    int selectPaymentListCount(@Param("dto") PaymentSelectRequestDto dto);
}