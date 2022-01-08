package fr.ul.projetcovid.servlets;

import fr.ul.projetcovid.persistence.Activity;
import fr.ul.projetcovid.persistence.MyActivity;
import fr.ul.projetcovid.persistence.UserAccount;
import fr.ul.projetcovid.persistence.dao.ActivityDAO;
import fr.ul.projetcovid.persistence.dao.MyActivityDAO;
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
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@WebServlet(name = "addMyActivitydPOST", value = "/addMyActivity")
public class AddMyActivity extends HttpServlet {


    private final MyActivityDAO myActivityDAO = new MyActivityDAO();
    private final ActivityDAO activityDAO = new ActivityDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String activityId = Objects.nonNullOrElse(request.getParameter("nameActivity"), "");
        final String date = Objects.nonNullOrElse(request.getParameter("date"), "");
        final String startTime = Objects.nonNullOrElse(request.getParameter("startTime"), "");
        final String endTime = Objects.nonNullOrElse(request.getParameter("endTime"), "");
        final String city = Objects.nonNullOrElse(request.getParameter("ville"), "");
        final String codePostal = Objects.nonNullOrElse(request.getParameter("cp"), "");

        /*System.out.println("city   " + city);
        Activity activity = activityDAO.getById(activityId);
        final MyActivity myActivity = new MyActivity();
        myActivity.setActivity(activity);
        myActivity.setCity(city);
        myActivity.setCodePostal(codePostal);


        try {
            myActivity.setStartTime(new SimpleDateFormat("hh:mm").parse(startTime));
            myActivity.setEndTime(new SimpleDateFormat("hh:mm").parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }



        try {
            myActivity.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            request.setAttribute("error", "Date invalide???");
            getServletContext().getRequestDispatcher("/myaActivity.jsp").forward(request, response);
            return;
        }

        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<MyActivity>> violations = validator.validate(myActivity);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (final ConstraintViolation<MyActivity> violation : violations)
                sb.append(violation.getMessage()).append("\n");
            request.setAttribute("error", sb.toString());
            getServletContext().getRequestDispatcher("/myActivity.jsp").forward(request, response);
            return;
        }


        myActivityDAO.save(myActivity);
        response.sendRedirect(this.getServletContext().getContextPath() + "/myactivity.jsp");
*/


    }

}
