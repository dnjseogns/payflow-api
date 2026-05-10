package com.dhwon.payflow_api.response;

import com.dhwon.payflow_api.response.enums.ResponseCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //null 값인 필드는 JSON 응답에서 제외
@Builder
public class CommonResponse<T> {
    private String code;
    private String message;
    private T data;

    public CommonResponse(ResponseCode responseCode){
        //data가 없으면, @JsonInclude(JsonInclude.Include.NON_NULL)에 의해 data필드는 사라짐
        setCode(responseCode.getCode());
        setMessage(responseCode.getMessage());
    }
    public CommonResponse(T data){
        //data만 넣을 경우 success로 판단
        setCode(ResponseCode.SUCCESS.getCode());
        setMessage(ResponseCode.SUCCESS.getMessage());
        setData(data);
    }
    public CommonResponse(ResponseCode responseCode, T data){
        setCode(responseCode.getCode());
        setMessage(responseCode.getMessage());
        setData(data);
    }
}

