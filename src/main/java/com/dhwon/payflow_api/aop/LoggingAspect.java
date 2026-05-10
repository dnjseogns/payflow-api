package com.dhwon.payflow_api.aop;

import com.dhwon.payflow_api.cmm.utils.CommUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class LoggingAspect {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private static final String CONTROLLER_REQUEST_LOG_FORMAT = "<API_REQUEST> client={} ip={} method={} uri={} func={} params={}";
    private static final String CONTROLLER_RESPONSE_LOG_FORMAT = "<API_RESPONSE> elapsedTime={}ms client={} ip={} status={} uri={} func={} result={}";
    private static final String CONTROLLER_EXCEPTION_LOG_FORMAT = "<CONTROLLER_EXCEPTION> elapsedTime={}ms client={} ip={} status={} uri={} func={} params={} ";
    private static final String SERVICE_RESULT_LOG_FORMAT ="<SERVICE_RESPONSE> elapsedTime={}ms func={} params={} result={}";
    private static final String SERVICE_EXCEPTION_LOG_FORMAT ="<SERVICE_EXCEPTION> elapsedTime={}ms func={} params={}";


    //    private final AuthUtil authUtil; //jwt 도입 시 추가
    private String clientNm;
    private String clientAddr;
    private String funcNm;
    private String requestUri;
    private String httpMethod;
    private String httpStatus;
    private String params;


    @Pointcut("execution(* com.dhwon.payflow_api.api.*.controller.*Controller.*(..))")
    private static void controllerPoint() {}
    @Pointcut("execution(* com.dhwon.payflow_api.api.*.service.*Service.*(..))")
    private static void servicePoint() {}


//    @Autowired  //jwt 도입 시 추가
//    public LoggingConfig(AuthUtil authUtil) {
//        this.authUtil = authUtil;
//    }




    @Around("controllerPoint()")
    public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        loggingControllerRequest(joinPoint);

        try {
            Object result = joinPoint.proceed();
            long elapsedTimeMs = System.currentTimeMillis() - start;
            loggingControllerResponse(joinPoint, elapsedTimeMs, result);

            return result;
        } catch (Exception e) {
            long elapsedTimeMs = System.currentTimeMillis() - start;
            loggingControllerException(joinPoint, elapsedTimeMs, e);

            throw e;
        }
    }


    @Around("servicePoint()")
    public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long elapsedTimeMs = System.currentTimeMillis() - start;
            loggingServiceResponse(joinPoint, elapsedTimeMs, result);

            return result;
        } catch (Exception e) {
//            long elapsedTimeMs = System.currentTimeMillis() - start;
//            loggingServiceException(joinPoint, elapsedTimeMs, e); //주석사유 : constroller+service 예외 로그 중복 출력

            throw e;
        }
    }


    private void loggingControllerRequest(JoinPoint joinPoint) {
        setRequestInfo();
        setFuncInfo(joinPoint);
        logger.info(CONTROLLER_REQUEST_LOG_FORMAT
                , clientNm
                , clientAddr
                , httpMethod
                , requestUri
                , funcNm
                , params
        );
    }
    private void loggingControllerResponse(JoinPoint joinPoint, long elapsedTimeMs, Object result) {
        setRequestInfo();
        setFuncInfo(joinPoint);
        if(result != null){
            String returns = CommUtils.truncateStr(result.toString(), 100);
            logger.info(CONTROLLER_RESPONSE_LOG_FORMAT
                    , elapsedTimeMs
                    , clientNm
                    , clientAddr
                    , httpStatus
                    , requestUri
                    , funcNm
                    , returns
            );
        }
    }

    private void loggingControllerException(JoinPoint joinPoint, long elapsedTimeMs, Throwable exception) {
        setRequestInfo();
        setFuncInfo(joinPoint);
        logger.error(CONTROLLER_EXCEPTION_LOG_FORMAT
                , elapsedTimeMs
                , clientNm
                , clientAddr
                , httpStatus
                , requestUri
                , funcNm
                , params
                , exception);
    }

    private void loggingServiceResponse(JoinPoint joinPoint, long elapsedTimeMs, Object result) {
        setRequestInfo();
        setFuncInfo(joinPoint);
        if(result != null){
            String returns = CommUtils.truncateStr(result.toString(), 100);
            logger.info(SERVICE_RESULT_LOG_FORMAT
                    , elapsedTimeMs
                    , funcNm
                    , params
                    , returns
            );
        }
    }
//    private void loggingServiceException(JoinPoint joinPoint, long elapsedTimeMs, Throwable exception) {
//        setRequestInfo();
//        setFuncInfo(joinPoint);
//        logger.error(SERVICE_EXCEPTION_LOG_FORMAT
//                , elapsedTimeMs
//                , funcNm
//                , params
//                , exception);
//    }

    private void setRequestInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
//        clientNm = authUtil.getUsername(request, response);//jwt 도입시 사용
        clientNm = "tempTestClient";
        clientAddr = request.getRemoteAddr();
        requestUri = request.getRequestURI();
        httpMethod = request.getMethod();
        httpStatus = Integer.toString(response.getStatus());
    }

    private void setFuncInfo(JoinPoint joinPoint) {
        funcNm = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
        params = getParamString(joinPoint);
    }

    /**
     * 파라미터를 로깅하는 보안 문제 있을 경우 수정 필요
     * @param joinPoint
     * @return
     */
    private String getParamString(JoinPoint joinPoint) {
        Map<String, Object> paramMap = new HashMap<>();

        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = signature.getParameterNames();

        for (int i = 0; i < args.length; i++) {
            Object value = args[i];

            // HTTP 객체 제외
            if (value instanceof HttpServletRequest ||
                    value instanceof HttpServletResponse) {
                continue;
            }

            // param name 없는 경우 대비
            String key = (paramNames != null && i < paramNames.length)
                    ? paramNames[i]
                    : "arg" + i;
            paramMap.put(key, value);
        }

        try {
            return objectMapper.writeValueAsString(paramMap);
        } catch (Exception e) {
            return paramMap.toString();
        }
    }
}
