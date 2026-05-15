package com.dhwon.payflow_api.api.aggpayment.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AggPaymentDetailResponseDto {

    private String baseDate;

    private String merchantId;

    private String payMethod;

    private Long approveCnt;
    private Long failCnt;
    private Long cancelCnt;

    private Long approveAmount;
}