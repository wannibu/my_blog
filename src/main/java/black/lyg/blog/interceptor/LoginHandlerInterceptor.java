package black.lyg.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object admin = request.getParameter("username");
        Object user = session.getAttribute("user");
        if (!(admin!=null || user!=null)){
            request.setAttribute("message","没有权限，请先登录");
            request.getRequestDispatcher("/admin").forward(request,response);
        }
        return true;
    }
}
