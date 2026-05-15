package com.dhwon.payflow_api.api.paymentFail.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentFailSelectResponseDto {

    private String merchantId;

    private String baseDate;

    private Long txSeq;

    private String failCode;

    private String failMessage;

    private LocalDateTime createdAt;
}