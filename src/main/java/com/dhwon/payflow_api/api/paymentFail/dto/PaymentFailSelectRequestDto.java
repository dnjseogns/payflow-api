package com.dhwon.payflow_api.api.paymentFail.dto;

import com.dhwon.payflow_api.cmm.paging.PagingRequestDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentFailSelectRequestDto extends PagingRequestDto {

    private String merchantId;

    private String baseDateFrom;
    private String baseDateTo;
}