package com.dhwon.payflow_api.interceptor;

import com.dhwon.payflow_api.cmm.auth.context.UserContext;
import com.dhwon.payflow_api.cmm.auth.context.UserContextDto;
import com.dhwon.payflow_api.exception.CustomException;
import com.dhwon.payflow_api.jwt.JwtConst;
import com.dhwon.payflow_api.jwt.JwtProvider;
import com.dhwon.payflow_api.response.enums.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {

        // Authorization 헤더 추출
        // Authorization: Bearer xxx.xxx.xxx 형태
        String authorization = request.getHeader(
                JwtConst.AUTHORIZATION_HEADER
        );

        // 헤더 없으면 예외
        if (authorization == null
                || !authorization.startsWith(JwtConst.BEARER_PREFIX)) {
            throw new CustomException(ResponseCode.UNAUTHORIZED);
        }

        // Bearer 제거
        String token = authorization.substring(7);

        // 토큰 검증
        jwtProvider.validateToken(token);

        // JWT에서 userId,roleCode 추출
        String userId = jwtProvider.getUserId(token);
        String roleCode = jwtProvider.getRoleCode(token);

        // http요청 동안만 전역에서 사용할 수 있도록 사용자 정보 임시 저장
        UserContext.set(
                UserContextDto.builder()
                        .userId(userId)
                        .roleCode(roleCode)
                        .build()
        );

        return true;
    }

    /**
     * 요청 완료 후 사용자 정보 clear
     */
    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ) {
        UserContext.clear();
    }
}