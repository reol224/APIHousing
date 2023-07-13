package com.conestoga.APIHousing.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String apiPath = request.getRequestURI();

        // print path and method and other info
        System.out.println("API Path: " + apiPath + " - " + request.getMethod());

        return true;
    }
}
