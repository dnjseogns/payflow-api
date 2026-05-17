package com.dhwon.payflow_api.api.cardReconciliation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardReconciliationSelectResponseDto {

    private String merchantId;
    private String baseDate;
    private Long txSeq;

    private String merchantOrderId;

    private String payProvider;
    private String cardCompany;
    private String vanName;

    private Long paymentAmount;
    private Long settlementRequestAmount;

    private Long feeAmount;
    private Long vatAmount;
    private Long settlementAmount;

    private String payStatus;
    private String settlementStatus;

    private String reconciliationStatus;
    private String reconciliationStatusName;

    private String settlementDate;

    private String createdAt;
}