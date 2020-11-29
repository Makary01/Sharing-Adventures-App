package web.servlets.app;

import dao.AdventureDao;
import model.Adventure;
import utils.AdventureTypesUtil;
import utils.DateUtil;
import utils.RegexUtil;
import utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet("/app/adventure")
public class AdventureDetails extends HttpServlet {

    Integer requestedAdventureId = null;
    AdventureDao adventureDao = new AdventureDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("delete")!=null){
            if(adventureDao.delete(requestedAdventureId)){
                //successfully Deleted
                WebUtil.redirectHome(request,response,"successfully deleted",false);
            } else {
                // not deleted
                WebUtil.redirectHome(request,response,"Error, adventure not deleted",true);
            }
        }else {


            String title = request.getParameter("title");
            String content = request.getParameter("content");
            LocalDate startDate = DateUtil.stringToDate(request.getParameter("startDate"));
            LocalDate endDate = DateUtil.stringToDate(request.getParameter("endDate"));
            String type = request.getParameter("type");
            Boolean isDateCorrect = DateUtil.isEarlierOrSame(startDate, endDate);
            Boolean notNull = (title !=null && content !=null && startDate!=null && endDate!=null&&
            type!=null) ? true : false;




            if (RegexUtil.validateTitle(title)
                    && AdventureTypesUtil.verifyType(type)
                    && isDateCorrect
            &&notNull) {
                Adventure adventure = new Adventure(requestedAdventureId,
                        (Integer) request.getSession().getAttribute("userId"),
                        type, title, content, startDate, endDate
                );

                adventureDao.update(adventure);

                //Successfully updated
                WebUtil.redirectHome(request,response,"Successfully updated adventure",false);
            } else {
                //Wrong data
                WebUtil.redirectHome(request,response,"Error adventure not updated",false);
            }
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
                ArrayList<String> adventureTypes = AdventureTypesUtil.getTypes();
                adventureTypes.remove(requestedAdventure.getType());
                request.setAttribute("types", adventureTypes);
                request.setAttribute("adventure",requestedAdventure);
                getServletContext().getRequestDispatcher("/app/adventure.jsp")
                        .forward(request,response);
            }else {
                //no such adventure
                WebUtil.redirectHome(request,response,"There is no such adventure",true);
            }
        }else {
            //NaN
            WebUtil.redirectHome(request,response,"There is no such adventure",true);
        }
    }
}
