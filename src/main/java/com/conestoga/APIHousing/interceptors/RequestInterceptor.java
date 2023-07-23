package com.conestoga.APIHousing.interceptors;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



@Component
public class RequestInterceptor implements HandlerInterceptor {

    Logger logger = Logger.getLogger(RequestInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String apiPath = request.getRequestURI();
        
        System.out.println("------------------");
        // print path and method and other info
        System.out.println("API Path: " + apiPath + " - " + request.getMethod() + " - " + response.getStatus());
        //print agent
        System.out.println("Agent: " + request.getHeader("User-Agent"));
        //print IP
        System.out.println("IP: " + request.getRemoteAddr());
     
        return true;
    }
}
