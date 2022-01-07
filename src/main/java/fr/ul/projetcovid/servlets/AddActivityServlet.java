package fr.ul.projetcovid.servlets;

import com.sun.xml.bind.v2.TODO;
import fr.ul.projetcovid.persistence.Activity;
import fr.ul.projetcovid.persistence.UserAccount;
import fr.ul.projetcovid.persistence.dao.ActivityDAO;
import fr.ul.projetcovid.persistence.dao.FriendsDAO;
import fr.ul.projetcovid.persistence.dao.UserAccountDAO;
import javaf.util.Objects;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@WebServlet(name = "addActivityPOST", value = "/addActivity")
public class AddActivityServlet extends HttpServlet {
    private final ActivityDAO activityDAO = new ActivityDAO();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String name = Objects.nonNullOrElse(request.getParameter("nameActivity"), "");

        if(name.equals("")) {
            request.setAttribute("error", "L'activité est vide");
            this.getServletContext().getRequestDispatcher("/activity.jsp").forward(request, response);
            return;
        }


        //TODO erreur doublon 

        final Activity activity = new Activity();
        activity.setName(name);
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<Activity>> violations = validator.validate(activity);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (final ConstraintViolation<Activity> violation : violations)
                sb.append(violation.getMessage()).append("\n");
            request.setAttribute("error", sb.toString());
            getServletContext().getRequestDispatcher("/activity.jsp").forward(request, response);
            return;
        }


        activityDAO.save(activity);



        response.sendRedirect(this.getServletContext().getContextPath() + "/activity.jsp");
    }
}