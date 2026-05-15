package com.dhwon.payflow_api.api.refundHandle.mapper;

import com.dhwon.payflow_api.api.refundHandle.dto.RefundHandleSelectRequestDto;
import com.dhwon.payflow_api.api.refundHandle.dto.RefundHandleSelectResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RefundHandleMapper {

    List<RefundHandleSelectResponseDto> selectRefundHandleList(
            @Param("dto") RefundHandleSelectRequestDto dto
    );

    int selectRefundHandleListCount(
            @Param("dto") RefundHandleSelectRequestDto dto
    );
}

