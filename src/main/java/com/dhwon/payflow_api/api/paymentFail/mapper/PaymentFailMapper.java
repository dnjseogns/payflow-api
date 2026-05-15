package com.dhwon.payflow_api.api.paymentFail.mapper;

import com.dhwon.payflow_api.api.paymentFail.dto.PaymentFailSelectRequestDto;
import com.dhwon.payflow_api.api.paymentFail.dto.PaymentFailSelectResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentFailMapper {

    List<PaymentFailSelectResponseDto> selectPaymentFailList(
            @Param("dto") PaymentFailSelectRequestDto dto
    );

    int selectPaymentFailListCount(
            @Param("dto") PaymentFailSelectRequestDto dto
    );
}