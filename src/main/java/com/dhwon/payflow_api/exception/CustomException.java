package com.dhwon.payflow_api.exception;

import com.dhwon.payflow_api.response.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private String code;
    private String message;

    public CustomException(ResponseCode responseCode){
        super(responseCode.getMessage());
        setCode(responseCode.getCode());
        setMessage(responseCode.getMessage());
    }
}
