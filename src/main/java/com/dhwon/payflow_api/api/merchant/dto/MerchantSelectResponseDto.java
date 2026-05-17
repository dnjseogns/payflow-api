package com.dhwon.payflow_api.api.merchant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantSelectResponseDto {

    private String merchantId;
    private String merchantName;
    private String merchantStatus;
}