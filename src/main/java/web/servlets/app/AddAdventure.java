package web.servlets.app;

import dao.AdventureDao;
import jdk.dynalink.linker.support.TypeUtilities;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet("/app/add")
public class AddAdventure extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        LocalDate startDate = DateUtil.stringToDate(request.getParameter("startDate"));
        LocalDate endDate = DateUtil.stringToDate(request.getParameter("endDate"));
        String type = request.getParameter("type");
        Boolean isDateCorrect = DateUtil.isEarlierOrSame(startDate,endDate);
        Boolean notNull = (title !=null&& content !=null&&startDate!=null&& endDate!=null&&
                type!=null) ? true : false;

        if(RegexUtil.validateTitle(title)
        && AdventureTypesUtil.verifyType(type)
        && isDateCorrect && notNull){
            Adventure adventure = new Adventure();
            adventure.setTitle(title);
            adventure.setContent(content);
            adventure.setStartDate(startDate);
            adventure.setEndDate(endDate);
            adventure.setType(type);
            adventure.setUserId((Integer) request.getSession().getAttribute("userId"));

            AdventureDao adventureDao = new AdventureDao();
            adventureDao.create(adventure);

            //Successfully added
            WebUtil.redirectHome(request,response,"Successfully added adventure",false);
        }else {
            //Wrong data
            WebUtil.redirectHome(request,response,"Error, adventure not added",true);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<String> adventureTypes = AdventureTypesUtil.getTypes();
            request.setAttribute("types", adventureTypes);
            request.getServletContext().getRequestDispatcher("/app/add.jsp").forward(request,response);
    }
}
