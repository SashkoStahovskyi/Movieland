package com.stahovskyi.movieland.web.controller.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    private static final String REQUEST_ID = "requestId";
    private static final String USER = "user";
    private static final String GUEST = "guest";


    @Override
    public boolean preHandle(HttpServletRequest request,
                             @Nullable HttpServletResponse response,
                             @Nullable Object handler) {
        String uuid = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID, uuid);

        Principal principal = request.getUserPrincipal();
        String user = isNull(principal) ? GUEST : principal.getName();
        MDC.put(USER, user);

        return true;
    }

}
