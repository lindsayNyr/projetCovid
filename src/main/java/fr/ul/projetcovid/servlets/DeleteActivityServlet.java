package fr.ul.projetcovid.servlets;

import fr.ul.projetcovid.persistence.Activity;
import fr.ul.projetcovid.persistence.dao.ActivityDAO;
import javaf.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "deleteActivity", value = "/deleteActivity")
public class DeleteActivityServlet extends HttpServlet {


    private final ActivityDAO activityDAO = new ActivityDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        final String id = Objects.nonNullOrElse(request.getParameter("idActivity"), "");


        Activity activity = activityDAO.getById(id);
        if(activity == null) {
            request.setAttribute("error", "L'activité n'existe pas");
            this.getServletContext().getRequestDispatcher("/activity.jsp").forward(request, response);
            return;
        }
        activityDAO.delete(activity);
        response.sendRedirect(getServletContext().getContextPath() + "/activity.jsp");

    }

}