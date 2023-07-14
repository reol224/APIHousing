package com.conestoga.APIHousing.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;


@Component
public class RequestInterceptor implements HandlerInterceptor {

    Logger logger = Logger.getLogger(RequestInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String apiPath = request.getRequestURI();

        // print path and method and other info
        logger.info("API Path: " + apiPath + " - " + request.getMethod());

        return true;
    }
}
