package com.groupfour.foodbox.interceptor;

import com.groupfour.foodbox.domain.AdminDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("UserInterceptor 작동");

        HttpSession session = request.getSession();
        Object userLoginDto = session.getAttribute("userLoginDto");

        // 로그인이 안된 경우
        if(userLoginDto == null){
            session.setAttribute("loginMsg", "로그인이 필요한 서비스 입니다!!");
            response.sendRedirect(request.getContextPath()+"/user/userLogin");
            return false; // 흐름 중단
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
