package com.dhwon.payflow_api.api.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSelectResponseDto {

    private String merchantId;
    private String baseDate;
    private Long txSeq;

    private String merchantOrderId;
    private String merchantOrderDate;

    private String payMethod;
    private String payMethodName;

    private Long amount;

    private String payStatus;
    private String payStatusName;

    private String createdAt;
}