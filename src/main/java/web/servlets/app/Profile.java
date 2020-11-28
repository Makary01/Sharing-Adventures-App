package web.servlets.app;

import dao.AdventureDao;
import dao.UserDao;
import model.User;
import utils.RegexUtil;

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

        UserDao userDao = new UserDao();
        User userToEdit = userDao.read((Integer) request.getSession().getAttribute("userId"));

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String oldPassword = request.getParameter("oldPassword");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String city = request.getParameter("city");
        String country = request.getParameter("country");

        if(!(username!=null&&email!=null&&oldPassword!=null&&password!=null
                &&password2!=null&&city!=null&&country!=null)){
            response.sendRedirect("/app/home");
        }

        userToEdit = userDao.verify(userToEdit.getUsername(),oldPassword);
        if(userToEdit!=null) {

            if (RegexUtil.validateUserName(username)
                    && RegexUtil.validatePassword(password)
                    && RegexUtil.validateEmail(email)
                    && RegexUtil.validateCity(city)
                    && RegexUtil.validateCountry(country)
                    && password.equals(password2)) {


                userToEdit.setPassword(password);
                userToEdit.setUsername(username);
                userToEdit.setEmail(email);
                userToEdit.setCity(city);
                userToEdit.setCountry(country);
                User editedUser = userDao.update(userToEdit);

                if (editedUser != null) {
                    request.getSession().setAttribute("userId", editedUser.getId());
                    response.sendRedirect("/app/home");
                } else {
                    //Error while creating
                    response.sendRedirect("/app/home");
                }
            } else {
                //Wrong data
                response.sendRedirect("/app/home");
            }
        }else {
            //wrong password
            response.sendRedirect("/app/home");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer requestedUserId = null;
        try {
            String id = request.getParameter("id").trim();
            requestedUserId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            response.sendRedirect("/app/home");
            e.printStackTrace();
        }
        if(requestedUserId!=null){
            UserDao userDao = new UserDao();
            User requestedUser = userDao.read(requestedUserId);
            if(requestedUser!=null){
                request.setAttribute("user",requestedUser);
                AdventureDao adventureDao = new AdventureDao();
                request.setAttribute("adventures", adventureDao.readByUserId(requestedUserId));

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
