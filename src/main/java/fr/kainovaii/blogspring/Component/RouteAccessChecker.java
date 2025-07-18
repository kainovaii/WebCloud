package fr.kainovaii.blogspring.Component;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.regex.Pattern;

@Component("webAccess")
public class RouteAccessChecker
{
    @Autowired
    private HttpServletRequest request;

    public String getCurrentPath()
    {
        return request.getRequestURI();
    }

    public boolean isRouteMatch(String pattern) {
        String currentPath = getCurrentPath();
        if ("/posts/:id".equals(pattern)) {
            return currentPath.startsWith("/posts/") && currentPath.substring(7).matches("\\d+");
        }
        return currentPath.equals(pattern);
    }
}