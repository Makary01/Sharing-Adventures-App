package web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/home","/login","/register"})
public class AutoLogIn implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId==null){
            chain.doFilter(req, resp);
        }else {
            response.sendRedirect("/app/home");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
