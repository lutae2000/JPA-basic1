package com.example.aroundhubstudy.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class HttpInterceptor implements HandlerInterceptor {

    //실행
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[preHandle] preHandle is performed");
        log.info("[preHandle] request : {}", request);
        log.info("[preHandle] request path info : {}", request.getPathInfo());
        log.info("[preHandle] request header names : {}", request.getHeaderNames());
        log.info("[preHandle] request request URL : {}", request.getRequestURL());
        log.info("[preHandle] request request URI: {}", request.getRequestURI());
        log.info("[preHandle] request Requested Session Id : {}", request.getRequestedSessionId());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("[postHandle] postHandle is performed");
        log.info("[postHandle] request : {}", request);
        log.info("[postHandle] response : {}", response);
        log.info("[postHandle] response : {}", response.getHeaderNames());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("[afterCompletion] afterCompletion is performed");
    }
}
