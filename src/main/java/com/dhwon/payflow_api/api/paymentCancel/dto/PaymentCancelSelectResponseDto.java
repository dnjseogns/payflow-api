package com.dhwon.payflow_api.api.paymentCancel.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCancelSelectResponseDto {

    private String baseDate;

    private String merchantId;

    private Long txSeq;

    private String cancelCode;

    private String cancelMessage;

    private String originalMerchantId;

    private String originalBaseDate;

    private Long originalTxSeq;

    private LocalDateTime createdAt;
}