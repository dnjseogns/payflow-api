package com.dhwon.payflow_api.exception;

import com.dhwon.payflow_api.response.CommonResponse;
import com.dhwon.payflow_api.response.enums.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;

@RestControllerAdvice
public class GlobalExceptionHandler {
    static private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * CustomException으로 처리된 예외들.
     */
    @ExceptionHandler(CustomException.class)
    public CommonResponse HandlerCustomException(CustomException e){
        logger.info("CustomException 에러발생",e);

        return CommonResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }

    /**
     * @Valid 검증 실패 시 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse handleValidationException(MethodArgumentNotValidException e) {
        logger.info("Validation 에러발생",e);

        // 첫 번째 에러 메시지 추출
        String errorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse("잘못된 요청입니다.");

        return CommonResponse.builder()
                .code(ResponseCode.INVALID_PARAMETER.getCode())
                .message(errorMessage)
                .build();
    }

    /**
     * 기타 처리하지 못한 예외둘
     */
    @ExceptionHandler(Exception.class)
    public CommonResponse HandlerException(Exception e){
        logger.error("에러발생",e);

        return CommonResponse.builder()
                .code(ResponseCode.UNKNOWN_ERROR.getCode())
                .message(ResponseCode.UNKNOWN_ERROR.getMessage())
                //.message(e.getMessage()) e.getMessage() 는 내부 시스템 정보가 노출됨
                .build();
    }
}
