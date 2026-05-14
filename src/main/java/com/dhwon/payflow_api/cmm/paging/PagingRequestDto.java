package com.dhwon.payflow_api.cmm.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagingRequestDto {
    private int page;
    private int size;

    public int getOffset() {
        return (page - 1) * size;
    }
}
