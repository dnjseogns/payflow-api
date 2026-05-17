package com.dhwon.payflow_api.api.settlementCard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementCardSelectResponseDto {

    private String merchantId;
    private String baseDate;
    private Long txSeq;

    private String settlementDate;
    private String cardCompany;

    private Long amount;
    private Long feeAmount;
    private Long vatAmount;
    private Long settlementAmount;

    private String settlementStatus;
    private String settlementStatusName;

    private String createdAt;
}