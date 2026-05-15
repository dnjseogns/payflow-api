package com.dhwon.payflow_api.api.paymentAggStatus.dto;

import com.dhwon.payflow_api.cmm.paging.PagingRequestDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAggStatusSelectRequestDto extends PagingRequestDto {

    private String baseDateFrom;
    private String baseDateTo;
}