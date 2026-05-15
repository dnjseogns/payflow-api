package com.dhwon.payflow_api.api.refundHandle.controller;

import com.dhwon.payflow_api.api.refundHandle.dto.RefundHandleSelectRequestDto;
import com.dhwon.payflow_api.api.refundHandle.dto.RefundHandleSelectResponseDto;
import com.dhwon.payflow_api.api.refundHandle.service.RefundHandleService;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/refund-handle")
public class RefundHandleController {

    private final RefundHandleService refundHandleService;

    @PostMapping("/select")
    @UseCommonResponse
    public PagingResponseDto<RefundHandleSelectResponseDto> selectRefundHandleList(
            @RequestBody RefundHandleSelectRequestDto dto
    ) {

        return refundHandleService.selectRefundHandleList(dto);
    }
}