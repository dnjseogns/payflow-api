package com.dhwon.payflow_api.api.refundHandle.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class RefundHandleSelectResponseDto {

    private String merchantId;

    private String baseDate;

    private Long refundSeq;

    private Long refundAmount;

    private Long refundCount;

    private String refundCode;

    private String refundMessage;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}