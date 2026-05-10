package com.dhwon.payflow_api.cmm.auth.context;

import lombok.Builder;
import lombok.Getter;

/**
 * userId 외 추가적인 정보를 추후에 저장해도 됨.
 */
@Getter
@Builder
public class UserContextDto {
    private String userId;
    private String roleCode;
}
