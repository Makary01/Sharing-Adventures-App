package web.servlets;

import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LogIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(!(username!=null&&password!=null)){
            response.sendRedirect("/home");
        }

        UserDao userDao = new UserDao();
        User verifiedUser = userDao.verify(username,password);
        if(verifiedUser!=null){
            request.getSession().setAttribute("userId",verifiedUser.getId());
            response.sendRedirect("/app/home");
        }else {
            response.sendRedirect("/home");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
