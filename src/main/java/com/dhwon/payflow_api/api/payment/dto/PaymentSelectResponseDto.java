package com.dhwon.payflow_api.api.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private String payProvider;
    private String payProviderName;

    private Long amount;

    private String payStatus;
    private String payStatusName;

    private LocalDateTime createdAt;
}