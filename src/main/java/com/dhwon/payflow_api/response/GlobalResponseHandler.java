package com.dhwon.payflow_api.response;

import com.dhwon.payflow_api.exception.CustomException;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import com.dhwon.payflow_api.response.enums.ResponseCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    /**
     * Spring이 설정한 ObjectMapper 재사용
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * UseCommonResponse 어노테이션 사용 시에만 beforeBodyWrite 함수 실행
     */
    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        if(returnType.hasMethodAnnotation(UseCommonResponse.class)){
            return true;
        }
        return false;
    }

    /**
     *
     */
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // 이미 CommonResponse면 그대로 반환
        if(body instanceof CommonResponse){
            return body;
        }

        CommonResponse<?> commonResponse = CommonResponse.builder()
                .code(ResponseCode.SUCCESS.getCode())
                .message(ResponseCode.SUCCESS.getMessage())
                .data(body)
                .build();

        // controller return타입 String일 경우 예외 처리
        if(returnType.getParameterType().equals(String.class)){
            try {
                return objectMapper.writeValueAsString(commonResponse);
            } catch (JsonProcessingException e) {
                throw new CustomException(ResponseCode.OBJECT_TO_JSON_CONVERT_ERROR);
            }
        }
        return commonResponse;
    }
}