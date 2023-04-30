package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class AuthCheckInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception{
        HttpSession session = req.getSession(false);
        if(session != null){
            Object authInfo = session.getAttribute("authInfo");
            if(authInfo != null){
                return false;
            }
        }
        res.sendRedirect(req.getContextPath() + "/login");
        return false;
    }
}