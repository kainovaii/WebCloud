package fr.kainovaii.blogspring.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TurboInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        if (modelAndView == null) return;
        String turboFrame = request.getHeader("Turbo-Frame");
        if (turboFrame != null) {
            String originalView = modelAndView.getViewName();
            modelAndView.setViewName(originalView + "::fragment");
        }
    }
}