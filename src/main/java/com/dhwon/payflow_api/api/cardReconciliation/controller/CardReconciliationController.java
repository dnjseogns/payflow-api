package com.dhwon.payflow_api.api.cardReconciliation.controller;

import com.dhwon.payflow_api.api.cardReconciliation.dto.CardReconciliationSelectRequestDto;
import com.dhwon.payflow_api.api.cardReconciliation.dto.CardReconciliationSelectResponseDto;
import com.dhwon.payflow_api.api.cardReconciliation.service.CardReconciliationService;
import com.dhwon.payflow_api.cmm.paging.PagingResponseDto;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cardReconciliation")
public class CardReconciliationController {

    private final CardReconciliationService cardReconciliationService;

    @PostMapping("/select")
    @UseCommonResponse
    public PagingResponseDto<CardReconciliationSelectResponseDto> selectCardReconciliationList(
            @RequestBody CardReconciliationSelectRequestDto dto
    ) {

        return cardReconciliationService.selectCardReconciliationList(dto);
    }
}