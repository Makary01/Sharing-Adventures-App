package web.servlets.app;

import dao.AdventureDao;
import model.Adventure;
import utils.AdventureTypesUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/app/home")
public class Home extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageStr = request.getParameter("page");
        Integer pageInt = 1;
        try{
            pageInt = Integer.parseInt(pageStr);
        }catch (NumberFormatException e){

        }
        if(pageInt<1){
            pageInt =1;
        }
        ArrayList<Adventure> adventures = new ArrayList<>();
        AdventureDao adventureDao = new AdventureDao();
        if(pageInt >=1){
            adventures = adventureDao.readFromTo((pageInt-1)*20,pageInt*20);
        }
        if(adventures.size() == 0){
            pageInt=1;
            adventures = adventureDao.readFromTo(0,20);
        }
        request.setAttribute("adventures", adventures);
        request.setAttribute("page",pageInt);

        HttpSession session = request.getSession();
        Boolean shouldDisplay = (Boolean) session.getAttribute("shouldDisplay");
        if(shouldDisplay!=null){
            if(shouldDisplay==false){
                session.setAttribute("msg",null);
                session.setAttribute("color",null);
            }else {
                session.setAttribute("shouldDisplay",false);
            }
        }

        getServletContext().getRequestDispatcher("/app/home.jsp").forward(request,response);

    }
}
