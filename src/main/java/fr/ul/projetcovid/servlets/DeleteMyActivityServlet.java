package fr.ul.projetcovid.servlets;

import fr.ul.projetcovid.persistence.MyActivity;
import fr.ul.projetcovid.persistence.dao.MyActivityDAO;
import javaf.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteMyActivity", value = "/deleteMyActivity")
public class DeleteMyActivityServlet extends HttpServlet {

    private final MyActivityDAO myActivityDAO = new MyActivityDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String id = Objects.nonNullOrElse(request.getParameter("idMyActivity"), "");

        if(id.equals("")){
            request.setAttribute("error", "L'activité n'existe pas");
            this.getServletContext().getRequestDispatcher("/myActivity.jsp").forward(request, response);
        }


        MyActivity myActivity = myActivityDAO.getById(id);
        myActivityDAO.remove(myActivity);

        response.sendRedirect(getServletContext().getContextPath() + "/myActivity.jsp");



    }

}