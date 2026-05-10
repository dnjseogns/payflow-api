package com.dhwon.payflow_api.response.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target({ElementType.METHOD}) : 함수에서만 해당 annotation 사용 가능
 * @Retention(RetentionPolicy.RUNTIME) : 유지기간은 코드실핼 중에도(runtime)
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCommonResponse {
}
