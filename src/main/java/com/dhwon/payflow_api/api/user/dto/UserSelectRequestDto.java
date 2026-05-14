package com.dhwon.payflow_api.api.user.dto;

import com.dhwon.payflow_api.cmm.paging.PagingRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSelectRequestDto extends PagingRequestDto {

    private String userId;
    private String userName;

}
