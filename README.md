[프로그램 소개]
PayFlow는 결제 데이터를 조회/관리하고, 집계 및 정산 데이터를 
조회/재처리 하기 위해 만들어진 관리자 시스템입니다.
JWT 인증 기반의 공통 API 응답 처리, 사용자 권한 관리, 
MyBatis 기반 DB 접근 구조를 구현한 프로젝트입니다.

[기술 스택]
• Backend : Spring Boot, MyBatis
• Build Tool : Gradle
• Database : Oracle
• Authentication : JWT
• Logging : Logback, AOP

[환경]
1. 개발 환경 (Development Environment)
• Java 17.0.18
• IntelliJ IDEA
2. 프로젝트 환경 (Project Setup)
• Spring Boot 3.5.14
• Gradle 8.14.4 (gradle-wrapper.properties)
* 프로젝트 생성: https://start.spring.io
3. IDE 세팅 (IDE Configuration)
• Gradle build/run: IntelliJ IDEA
• File Encoding: UTF-8 (global / project / default)
* 설정 변경 후: Invalidate Cache / Restart

[프로젝트 패키지 구조]
docs
    └─ db                  // 파티션 및 인덱스 설계 이유 문서. table/data/procedure init 스크립트 
src
└─ java.com.dhwon.payflow_api
    └─ aop                 // 공통 로깅 aop
    └─ api
        └─ payment         // 도메인
            └─ controller
            └─ service
            └─ mapper
            └─ dto
    └─ cmm
        └─ auth.context    // 로그인 사용자 정보 Context 관리
        └─ paging          // 공통 페이징 dto
        └─ utils           
    └─ config              // Spring, Mybatis, Web 설정
    └─ exception           // 공통 예외처리
    └─ interceptor         // Auth인증, Mybatis로깅 인터셉터 
    └─ jwt                 // JWT 로직
    └─ response            // 공통 응답 객체 및 코드
        └─ annotation      
        └─ enums
└─ resource
    └─ sql                 // SQL

[권한 구조]
┌──────────────┐   N:1   ┌──────────────┐   1:N   ┌────────────────────┐   N:1   ┌──────────────┐
│  MST_USERS   │────────▶│   MST_ROLE   │────────▶│ MST_ROLE_MENU_MAP  │────────▶│   MST_MENU   │
└──────────────┘         └──────────────┘         └────────────────────┘         └──────────────┘
• 사용자별 Role 기반 메뉴 접근 제어
• Role과 Menu는 매핑 테이블(MST_ROLE_MENU_MAP)로 관리
• 메뉴별 공통 버튼 권한(select/add/delete 등) 처리

[인증 절차(client 내용 포함)]
1. 로그인 시
1) client 로그인 요청(/api/auth/login)
2) server 인증 성공 시
• refreshToken : cookie 저장
• accessToken : json 응답
3) client 데이터 저장
• accessToken : localStorage 저장
4) 이후 api 요청 시
• Axios Interceptor에서 Authorization Header 자동 주입

2. AccessToken 만료 시
1) server : AccessToken 만료 시 2001에러코드 반환
2) client : axios interceptor에서 2001코드일 경우 api(/api/auth/reissue) 요청
3) server : cookie의 refresh토큰과 db에 저장된 user의 refresh토큰값과 같을 경우 access/refresh token 재발급
이후는 로그인 시 절차와 같음.

[핵심 설정 및 구현]
1. application.yaml
• application.yaml : spring.profiles.active 값으로 실행 환경 분리(local / dev / prod)
• application-*.yaml : 환경에 따른 값 세팅

2. oracle 설정
1) application-*.yml
• datasource 값 입력

3. mybatis 설정
1) DatabaseConfig.java
• db 설정
2) mybatis-config.xml
• mybatis 설정
3) MybatisLogInterceptor.java
• sql로깅 시 '?'로 출력되는 파라미터를 값으로 치환 후 로깅함.
• logger.debug(sql) 로 출력.

4. 테이블 설계 및 생성
• 대표 테이블인 PAYMENT 테이블 PARTITION 및 INDEX 설정
• 자세한 내용은 docs/db/DB_DESIGN.md 참고

5. logging 설정
1) logback-spring
• 콘솔/파일 로깅 사용 (error, info, mybatis sql을 별도의 파일에 저장)
* mybatis sql은 MybatisLogInterceptor의 log.debug()을 로깅
* 파일경로 설정은 application-*.yaml 참고
2) LoggingAspect
• Controller, Service 함수의 시작과 종료 로깅
• 출력값 : 실행시간, client정보, request정보, response정보
예시) <API_RESPONSE> elapsedTime=6163ms client=tempTestClient ip=0:0:0:0:0:0:0:1 status=200 uri=/api/mybatistest/selectEmployees func=MybatistestController.selectEmployees result=[EmployeeDto(employeeId=100, firstName=Steven, lastName=King...(truncated)

6. 공통 response
1)성공 시
• Service -> Controller -> @UseCommonResponse 사용했을 경우 -> GlobalResponseHandler (return타입 CommonResponse)
* response 예시) {code : "0000", message : "SUCCESS", data: []}
2)예외 시
• Service -> throw new CustomException() -> GlobalExceptionHandler (return타입 CommonResponse)
* response 예시) {code : "2000", message : "UNAUTHORIZED"}

7. 보안
1) CORS 세팅
• WebConfig.java
2) jwt
(1) WebConfig.java
• http 요청 시 authInterceptor 실행 (로그인과 refresh토큰 재발급은 제외)
(2) /api/auth/** 요청 시
• access토큰 / refresh토큰 발행 (refresh토큰 db저장)
(3) /api/** 요청 시
• authInterceptor에서 access token 인증 후 성공 시 -> ContextUser정보 저장 -> Controller...
• authInterceptor에서 access token 인증 후 실패 시 -> 예외처리

8. 공통 paging 처리
• 공통 request : {page, size}
• 공통 response : data : {list, totalCount}

[추가 개선 예정]
- 테스트 코드 작성
- 공통 sort 기능 추가
- Swagger 기반 API 문서화
- CI/CD 자동 배포 환경 구축
- 일부 화면/API 기능 고도화
- spring security 적용