package com.dhwon.payflow_api.api.cardReconciliation.dto;

import com.dhwon.payflow_api.cmm.paging.PagingRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardReconciliationSelectRequestDto extends PagingRequestDto {

    private String merchantId;

    private String baseDateFrom;
    private String baseDateTo;

    private String reconciliationStatus;
}