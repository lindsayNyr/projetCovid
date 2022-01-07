package fr.ul.projetcovid.servlets;


import fr.ul.projetcovid.persistence.Activity;
import fr.ul.projetcovid.persistence.UserAccount;
import fr.ul.projetcovid.persistence.dao.ActivityDAO;
import javaf.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;


@WebServlet(name = "editActivity", value = "/editActivity")
public class EditActivityServlet extends HttpServlet {


        private final ActivityDAO activityDAO = new ActivityDAO();


        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



            final String idActivity = Objects.nonNullOrElse(request.getParameter("idActivity"), "");
            final String name = Objects.nonNullOrElse(request.getParameter("nameActivity"), "");

            if(idActivity.equals("")) {
                request.setAttribute("error", "L'activité n'existe pas");
                this.getServletContext().getRequestDispatcher("/activity.jsp").forward(request, response);
                return;
            }


            Activity activity = activityDAO.getById(idActivity);

            if(activity == null) {
                request.setAttribute("error", "L'activité n'existe pas");
                getServletContext().getRequestDispatcher("/editActivity.jsp?idActivity="+ idActivity).forward(request, response);
                return;
            }

            final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            final Set<ConstraintViolation<Activity>> violations = validator.validate(activity);
            activity.setName(name);
            if (!violations.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (final ConstraintViolation<Activity> violation : violations)
                    sb.append(violation.getMessage()).append("\n");
                request.setAttribute("error", sb.toString());
                getServletContext().getRequestDispatcher("/editActivity.jsp?idActivity="+ idActivity).forward(request, response);
                return;
            }


            activityDAO.update(activity);
            response.sendRedirect(getServletContext().getContextPath() + "/activity.jsp");

        }

    }


