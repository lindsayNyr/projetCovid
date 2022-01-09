package fr.ul.projetcovid.servlets;

import fr.ul.projetcovid.persistence.Activity;
import fr.ul.projetcovid.persistence.MyActivity;
import fr.ul.projetcovid.persistence.Place;
import fr.ul.projetcovid.persistence.UserAccount;
import fr.ul.projetcovid.persistence.dao.ActivityDAO;
import fr.ul.projetcovid.persistence.dao.MyActivityDAO;
import fr.ul.projetcovid.persistence.dao.PlaceDAO;
import fr.ul.projetcovid.persistence.dao.UserAccountDAO;
import javaf.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@WebServlet(name = "editMyActivitydPOST", value = "/editMyActivity")
public class EditMyActivityServlet extends HttpServlet {


    private final MyActivityDAO myActivityDAO = new MyActivityDAO();
    private final ActivityDAO activityDAO = new ActivityDAO();
    private final PlaceDAO placeDAO = new PlaceDAO();
    private final UserAccountDAO userAccountDAO = new UserAccountDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String idActivity = Objects.nonNullOrElse(request.getParameter("idActivity"), "");
        final String date = Objects.nonNullOrElse(request.getParameter("date"), "");
        final String startTime = Objects.nonNullOrElse(request.getParameter("startTime"), "");
        final String endTime = Objects.nonNullOrElse(request.getParameter("endTime"), "");
        final String placeId = Objects.nonNullOrElse(request.getParameter("idPlace"), "");
        final String idMyActivity= Objects.nonNullOrElse(request.getParameter("idMyActivity"), "");


        Activity activity = activityDAO.getById(idActivity);
        MyActivity myActivity = myActivityDAO.getById(idMyActivity);
        Place place = placeDAO.getById(placeId);

        HttpSession session = request.getSession();
        Optional<UserAccount> userAccount = userAccountDAO.getById(String.valueOf(session.getAttribute("id")));

        if(!userAccount.isPresent()){
            response.sendError(403);
            return;
        }

        myActivity.setUserAccount(userAccount.get());


        myActivity.setPlace(place);
        myActivity.setActivity(activity);



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
            getServletContext().getRequestDispatcher("/myActivity.jsp").forward(request, response);
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



        myActivityDAO.update(myActivity);
        response.sendRedirect(this.getServletContext().getContextPath() + "/myActivity.jsp");


    }

}