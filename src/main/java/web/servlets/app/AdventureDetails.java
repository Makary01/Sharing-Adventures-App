package web.servlets.app;

import dao.AdventureDao;
import model.Adventure;
import utils.AdventureTypesUtil;
import utils.DateUtil;
import utils.RegexUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/app/adventure")
public class AdventureDetails extends HttpServlet {

    Integer requestedAdventureId = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        LocalDate startDate = DateUtil.stringToDate(request.getParameter("startDate"));
        LocalDate endDate = DateUtil.stringToDate(request.getParameter("endDate"));
        String type = request.getParameter("type");
        Boolean isDateCorrect = DateUtil.isEarlierOrSame(startDate,endDate);

        if(RegexUtil.validateTitle(title)
                && AdventureTypesUtil.verifyType(type)
                && isDateCorrect){
            Adventure adventure = new Adventure(requestedAdventureId,
                    (Integer) request.getSession().getAttribute("userId"),
                    type,title,content,startDate,endDate
            );
            AdventureDao adventureDao = new AdventureDao();
            adventureDao.update(adventure);

            //Successfully updated
            response.sendRedirect("/app/home");
        }else {
            //Wrong data
            response.sendRedirect("/app/home");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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