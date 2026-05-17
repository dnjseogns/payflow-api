package com.dhwon.payflow_api.api.settlementCard.controller;

import com.dhwon.payflow_api.api.settlementCard.dto.SettlementCardSelectRequestDto;
import com.dhwon.payflow_api.api.settlementCard.dto.SettlementCardSelectResponseDto;
import com.dhwon.payflow_api.api.settlementCard.service.SettlementCardService;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/settlementCard")
public class SettlementCardController {

    private final SettlementCardService settlementCardService;

    @PostMapping("/select")
    @UseCommonResponse
    public PagingResponseDto<SettlementCardSelectResponseDto> selectSettlementCardList(
            @RequestBody SettlementCardSelectRequestDto dto
    ) {

        return settlementCardService.selectSettlementCardList(dto);
    }
}