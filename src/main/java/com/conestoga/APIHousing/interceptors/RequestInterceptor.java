package com.conestoga.APIHousing.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String apiPath = request.getRequestURI();

        // print path and method and other info
        System.out.println("API Path: " + apiPath + " - " + request.getMethod());

        return true;
    }
}
