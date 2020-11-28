package web.servlets.app;

import dao.AdventureDao;
import model.Adventure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/adventure")
public class AdventureDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer requestedAdventureId = null;
        try {
            String id = request.getParameter("id").trim();
            requestedAdventureId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(requestedAdventureId!=null){
            AdventureDao adventureDao = new AdventureDao();
            Adventure requestedAdventure = adventureDao.read(requestedAdventureId);
            if(requestedAdventure!=null){
                request.setAttribute("adventure",requestedAdventure);
                getServletContext().getRequestDispatcher("/app/adventure.jsp")
                        .forward(request,response);
            }else {
                //no such adventure
                response.sendRedirect("/app/home");
            }
        }else {
            //NaN
            response.sendRedirect("/app/home");
        }
    }
}
