package com.dhwon.payflow_api.cmm.paging;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagingResponseDto<T> {
    private List<T> list;
    private int totalCount;
}
