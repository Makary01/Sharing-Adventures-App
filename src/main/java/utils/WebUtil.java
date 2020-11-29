package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtil {
    public static void redirectHome(HttpServletRequest request, HttpServletResponse response, String msg, Boolean isBad) throws IOException {
        request.getSession().setAttribute("msg", msg);
        request.getSession().setAttribute("color",(isBad) ? "red":"green");
        request.getSession().setAttribute("shouldDisplay",true);
        response.sendRedirect("/app/home");
    }
}
