package com.dhwon.payflow_api.config;

import com.dhwon.payflow_api.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    /**
     * http요청 시 로그인인 /api/auth/login을 제외한 모든 /api는 authInterceptor 실행
     */
    @Override
    public void addInterceptors(
            InterceptorRegistry registry
    ) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns(
                        "/api/auth/login",
                        "/api/auth/reissue"
                );
    }



    /**
     * 같은 oragin일 경우, 브라우저에서 허용
     * 다른 origin일 경우, 브라우저에서 아래의 cors해더에 해당하는 경우에만 허용
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**")
                // 현재는 localhost만 허용
                .allowedOriginPatterns("http://localhost:*")
                // 허용된 http 메소드 목록
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PATCH.name())
                // 허용된 요청 헤더 목록
                .allowedHeaders("*")
                // 브라우저에서 아래 정보 읽을 수 있도록 허용
                // 이걸 하지 않으면 font에서 response header에 Authorization 값이 표출되지 않음
                .exposedHeaders(
                        "Authorization",
                        "X-Request-Id"
                )
                // 브라우저가 쿠키, 세션, Authorization 같은 인증 정보를 포함해서 요청해도 허용한다
                // ex) Cookie, Session ID (JSESSIONID), Authorization (Bearer token 포함 케이스), 인증 관련 header
                // cross-origin 인증 요청 허용 (JWT/Session 사용 시 필요)
                .allowCredentials(true)
                // preflight를 3600초(1시간) 동안 브라우저 캐싱하여 cors검사 비용을 줄임(매 요청마다 발생하면 느려지므로)
                .maxAge(3600);
    }
}
