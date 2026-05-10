package com.dhwon.payflow_api.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 0000	성공
 * 1xxx	사용자 요청 오류
 * 2xxx	인증/인가
 * 3xxx	비즈니스 오류
 * 4xxx	DB
 * 5xxx	외부 API
 * 9xxx	시스템 오류
 */

@Getter
@AllArgsConstructor
public enum ResponseCode {

    /**
     * 정상 처리
     */
    SUCCESS("0000", "SUCCESS"),

    /**
     * 알 수 없는 시스템 오류
     * 예: NullPointerException, 예상하지 못한 Exception
     */
    UNKNOWN_ERROR("9999", "UNKNOWN_ERROR"),

    /**
     * 요청 파라미터 값 오류
     * 예: 필수값 누락, 형식 불일치, validation 실패
     */
    INVALID_PARAMETER("1000", "INVALID_PARAMETER"),

    /**
     * 잘못된 요청
     * 예: 잘못된 API 호출, 지원하지 않는 요청 방식
     */
    INVALID_REQUEST("1001", "INVALID_REQUEST"),

    /**
     * 인증 실패
     * 예: 로그인 안됨
     */
    UNAUTHORIZED("2000", "UNAUTHORIZED"),

    /**
     * 인증 실패
     * 예: 토큰 만료
     */
    EXPIRED_TOKEN("2001", "EXPIRED_TOKEN"),

    /**
     * 권한 없음
     * 예: 일반 사용자가 관리자 API 호출
     */
    FORBIDDEN("2002", "FORBIDDEN"),

    /**
     * 데이터 없음
     * 예: 조회 결과 없음
     */
    DATA_NOT_FOUND("3000", "DATA_NOT_FOUND"),

    /**
     * 중복 데이터 존재
     * 예: 중복 회원가입, UNIQUE 제약조건 위반
     */
    DUPLICATE_DATA("3001", "DUPLICATE_DATA"),

    /**
     * 데이터베이스 처리 오류
     * 예: SQL 오류, DB 연결 실패
     */
    DATABASE_ERROR("4000", "DATABASE_ERROR"),

    /**
     * 외부 API 호출 실패
     * 예: 타 시스템 연동 실패, Timeout
     */
    API_CALL_ERROR("5000", "API_CALL_ERROR"),

    /**
     * object -> json 변환 오류
     */
    OBJECT_TO_JSON_CONVERT_ERROR("9001", "OBJECT_TO_JSON_CONVERT_ERROR");

    private String code;
    private String message;


}
