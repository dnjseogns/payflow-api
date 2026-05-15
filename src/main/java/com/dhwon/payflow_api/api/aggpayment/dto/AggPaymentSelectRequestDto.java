package com.dhwon.payflow_api.api.aggpayment.dto;

import com.dhwon.payflow_api.cmm.paging.PagingRequestDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AggPaymentSelectRequestDto extends PagingRequestDto {

    private String merchantId;

    private String baseDateFrom;
    private String baseDateTo;

    private String payMethod;
    private String payStatus;
}