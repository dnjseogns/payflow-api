package com.dhwon.payflow_api.cmm.auth.context;

/**
 * 각자 독립된 thread 에 저장하며,
 * clear할 경우 독립된 thead만 비워줌.
 * session 간 저장이 아니라, request 동안 임시저장하여
 * 다른 함수에서도 사용할 수 있게 함.
 */
public class UserContext {
    private static final ThreadLocal<UserContextDto> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(UserContextDto userContextDto) {
        USER_THREAD_LOCAL.set(userContextDto);
    }

    public static UserContextDto get() {
        return USER_THREAD_LOCAL.get();
    }

    public static void clear() {
        USER_THREAD_LOCAL.remove();
    }
}
