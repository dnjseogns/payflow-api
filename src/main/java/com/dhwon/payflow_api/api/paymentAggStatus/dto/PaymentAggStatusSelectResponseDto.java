package com.dhwon.payflow_api.api.paymentAggStatus.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAggStatusSelectResponseDto {

    private String baseDate;

    private String aggStatus;
    private String aggStatusName;

    private String aggStartAt;
    private String aggEndAt;

    private Integer retryCount;

    private String errorMessage;

    private String createdAt;
    private String updatedAt;
}