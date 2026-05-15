package com.dhwon.payflow_api.api.paymentCancel.dto;

import com.dhwon.payflow_api.cmm.paging.PagingRequestDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCancelSelectRequestDto extends PagingRequestDto {

    private String merchantId;

    private String baseDateFrom;

    private String baseDateTo;
}