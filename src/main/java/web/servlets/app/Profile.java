package web.servlets.app;

import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.IOException;

@WebServlet("/app/profile")
public class Profile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer requestedUserId = null;
        try {
            String id = request.getParameter("id").trim();
            requestedUserId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(requestedUserId!=null){
            UserDao userDao = new UserDao();
            User requestedUser = userDao.read(requestedUserId);
            if(requestedUser!=null){
                request.setAttribute("user",requestedUser);
                getServletContext().getRequestDispatcher("/app/profile.jsp")
                        .forward(request,response);
            }else {
                //no such profile
                response.sendRedirect("/app/home");
            }
        }else {
            //NaN
            response.sendRedirect("/app/home");
        }
    }
}
