package com.dhwon.payflow_api.api.aggpayment.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AggPaymentSummaryResponseDto {

    private Long approveCnt;
    private Long approveAmount;

    private Long failCnt;
    private Long cancelCnt;

    private Double failRate;
    private Double cancelRate;
}