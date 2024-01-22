package Project.UniApply.Hub;

import Project.UniApply.Hub.Controllers.AuthenticationController;
import Project.UniApply.Hub.Models.Students;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter implements HandlerInterceptor {

    @Autowired
    AuthenticationController authenticationController;

    // Allow certain pages and static resources to be seen by the public (not logged in)


    private static final List<String> whitelist = Arrays.asList( "/index", "/login", "/studentRegister","/UniversityRegister","/css","/img/img", "/img","/assets","/images", "/about");

    // Check all pages and static resources against blacklist
    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.equals("/") || path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        // Don't require sign-in for whitelisted pages
        if (isWhitelisted(request.getRequestURI())) {
            // Early return to allow request to go through
            return true;
        }

        HttpSession session = request.getSession();
        Students student = authenticationController.getStudentFromSession(session);
        Universities university=authenticationController.getUserFromSession(session);

        // The user is logged in
        if (student != null || university!=null) {
            return true;
        }

        // The user is NOT logged in
        response.sendRedirect("/login");
        return false;
    }
}