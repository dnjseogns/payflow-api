package com.dhwon.payflow_api.api.paymentCancel.mapper;

import com.dhwon.payflow_api.api.paymentCancel.dto.PaymentCancelSelectRequestDto;
import com.dhwon.payflow_api.api.paymentCancel.dto.PaymentCancelSelectResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentCancelMapper {

    List<PaymentCancelSelectResponseDto> selectPaymentCancelList(
            @Param("dto") PaymentCancelSelectRequestDto dto
    );

    int selectPaymentCancelListCount(
            @Param("dto") PaymentCancelSelectRequestDto dto
    );
}