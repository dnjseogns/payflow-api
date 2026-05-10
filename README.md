[1] 환경
1. Development Environment (개발 환경)
• Java 17.0.18
• IntelliJ IDEA

2. Project Setup (프로젝트 환경)
• Spring Boot 3.5.14
• Gradle 8.14.4 (gradle-wrapper.properties)
* 프로젝트 생성: https://start.spring.io

3. IDE Configuration (IDE 세팅)
• Gradle build/run: IntelliJ IDEA
• File Encoding: UTF-8 (global / project / default)
* 설정 변경 후: Invalidate Cache / Restart

[2] 프로젝트 구조
src
⤷ java\...
    ⤷ aop
    ⤷ api
    ⤷ cmm
    ⤷ config
    ⤷ exception
    ⤷ interceptor
    ⤷ jwt
    ⤷ response
⤷ resource
    ⤷ sql
    ⤷ templates

[3] 주요 설정/파일 구조
1. application.yaml
• application.yaml : spring.profiles.active 값으로 실행 환경 분리(local / dev / prod)
• application-*.yaml : 환경에 따른 값 세팅

2. oracle 설정
1) application-*.yml
• datasource 값 입력

3. mybatis 설정
1) DatabaseConfig.java
• db 세팅
2) mybatis-config.xml
• mybatis 세팅
3) MybatisLogInterceptor.java
• sql로깅 시 ?로 출력되는 파라미터를 값으로 치환 후 로깅함.
• logger.debug(sql) 로 출력.

4. logging 세팅
1) logback-spring
• 콘솔 로깅, 파일 로깅(info.log, error.log, sql.log)
• sql.log : MybatisLogInterceptor의 log.debug() 로깅
* 파일경로 : application-*.yaml
2) LoggingAspect
   Controller, Service 함수의 시작과 종료 로깅
   ex) <API_RESPONSE> elapsedTime=6163ms client=tempTestClient ip=0:0:0:0:0:0:0:1 status=200 uri=/api/mybatistest/selectEmployees func=MybatistestController.selectEmployees result=[EmployeeDto(employeeId=100, firstName=Steven, lastName=King...(truncated)

5. 공통response(성공/예외)
1)성공 시
• Controller -> Service -> ... -> Controller -> @UseCommonResponse 사용했을 경우 -> GlobalResponseHandler (return타입 CommonResponse)
2)예외 시
• Controller -> Service -> throw new CustomException() -> GlobalExceptionHandler (return타입 CommonResponse)

6. 보안
1) CORS 세팅
• WebConfig.java

2) jwt
(1) WebConfig.java
• http 요청 시 authInterceptor 실행(auth api를 제외)
(2) /api/auth/** 요청 시
• access토큰 / refresh토큰 발행. refresh토큰 db저장.
(4) /api/** 요청 시
• authInterceptor에서 access token 인증 후 성공 -> ContextUser정보 저장 -> Controller...
• authInterceptor에서 access token 인증 후 실패 -> 예외처리