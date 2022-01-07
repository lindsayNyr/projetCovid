package fr.ul.projetcovid.servlets;

import fr.ul.projetcovid.persistence.Activity;
import fr.ul.projetcovid.persistence.UserAccount;
import fr.ul.projetcovid.persistence.dao.ActivityDAO;
import fr.ul.projetcovid.persistence.dao.FriendsDAO;
import fr.ul.projetcovid.persistence.dao.UserAccountDAO;
import javaf.util.Objects;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "addActivity", value = "/addActivity")
public class AddActivityServlet extends HttpServlet {
    private final ActivityDAO activityDAO = new ActivityDAO();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String name = Objects.nonNullOrElse(request.getParameter("nameActivity"), "");

        System.out.println("ok");


        if(name.equals("")) {
            request.setAttribute("error", "L'acitivé est vide");
            this.getServletContext().getRequestDispatcher("/activity.jsp").forward(request, response);
            return;
        }





        final Activity activity = new Activity();
        activity.setName(name);
        activityDAO.save(activity);


        response.sendRedirect(this.getServletContext().getContextPath() + "/activity.jsp");
    }
}