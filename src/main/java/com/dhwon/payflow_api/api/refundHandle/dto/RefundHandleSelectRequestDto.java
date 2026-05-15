package com.dhwon.payflow_api.api.refundHandle.dto;

import com.dhwon.payflow_api.cmm.paging.PagingRequestDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundHandleSelectRequestDto extends PagingRequestDto {

    private String merchantId;

    private String baseDateFrom;

    private String baseDateTo;
}